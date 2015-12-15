package com.lexmark.services.webService;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_PortType;
import com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_ServiceLocator;
import com.lexmark.SAPWebServices.customerInvoice.ZGSF_MPS_INVOICE_LIST;
import com.lexmark.SAPWebServices.customerInvoice.holders.TABLE_OF_ZGSF_MPS_INVOICE_LISTHolder;
import com.lexmark.contract.InvoiceListContract;
import com.lexmark.domain.Invoice;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.InvoiceListResult;
import com.lexmark.services.api.CustomerInvoiceService;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;


public class CustomerInvoiceServiceImpl implements CustomerInvoiceService{
	
	private static Logger logger = LogManager.getLogger(CustomerInvoiceServiceImpl.class);
	private static final LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(CustomerInvoiceServiceImpl.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging

	private String endpointInvoice;
	private String userName; 
	private String password;
	
	public static DateFormat DATE_FORMAT_SAP = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat INVOICE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	
	public String getEndpointInvoice() {
		return endpointInvoice;
	}

	public void setEndpointInvoice(String endpointInvoice) {
		this.endpointInvoice = endpointInvoice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @param _date
	 * @return
	 */
	public static String fomatWSDate(Date _date) {
		if (_date == null)
			return null;
			return DATE_FORMAT_SAP.format(_date);
	}

	/* (non-Javadoc)
	 * @see com.lexmark.services.api.CustomerInvoiceService#retrieveInvoiceList(com.lexmark.contract.InvoiceListContract, java.lang.String[])
	 */
	public InvoiceListResult retrieveInvoiceList(InvoiceListContract contract,String[] filterColumns)
			throws Exception {
		
		ZGRFC_MPS_INVOICE_LIST_ServiceLocator locator =new ZGRFC_MPS_INVOICE_LIST_ServiceLocator();
		logger.debug("end point is "+getEndpointInvoice());
		ZGRFC_MPS_INVOICE_LIST_PortType  port=locator.getZGRFC_MPS_INVOICE_LIST(new URL(getEndpointInvoice()));
		if(logger.isDebugEnabled()){
			logger.debug("end point is username "+getUserName());
			logger.debug("end point is pwd "+getPassword());
		}
		
		org.apache.axis.client.Stub stub = ((org.apache.axis.client.Stub) port);
		stub.setUsername(getUserName());
		stub.setPassword(getPassword());
		
			
		TABLE_OF_ZGSF_MPS_INVOICE_LISTHolder holder = new TABLE_OF_ZGSF_MPS_INVOICE_LISTHolder();
		logger.debug("before port call");
		String fromDate=DATE_FORMAT_SAP.format(contract.getFromDate());
		String toDate=DATE_FORMAT_SAP.format(contract.getToDate());
		if(logger.isDebugEnabled()){
			logger.debug("fromDate="+fromDate);
			logger.debug("toDate="+toDate);
		}
		long timeBeforeCall = System.currentTimeMillis();
		port.ZGRFC_MPS_INVOICE_LIST(contract.getCompanyCode(), fromDate, contract.getStatus(), contract.getInvoiceNum(), contract.getSoldToNumber(), toDate, holder);
		long timeAfterCall=System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CUSTOMERINVOICE_MSG_ZGRFC_MPS_INVOICE_LIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SAP,contract);
		//LEXLOGGER.logTime("** MPS PERFORMANCE TESTING CUSTOMER INVOICE SERVICE CALL ==>: " + (System.currentTimeMillis() - timeBeforeCall) / 1000.0);
		
		return convertToResult(holder,contract.getFilterCriteria(),filterColumns);
	}
	
	/**
	 * @param holder
	 * @param filterCriteria
	 * @param filterColumns
	 * @return
	 */
	private InvoiceListResult convertToResult(TABLE_OF_ZGSF_MPS_INVOICE_LISTHolder holder,Map<?,?>filterCriteria,String[]filterColumns){
		InvoiceListResult result=new InvoiceListResult();
		List<Invoice> list=new ArrayList<Invoice>();
		for(int i=0;i<holder.value.length;i++){
			if(StringUtils.isNotBlank(holder.value[i].getKUNNR())||StringUtils.isNotBlank(holder.value[i].getXBLNR()) ){
				if(checkFilter(filterColumns,filterCriteria,holder.value[i])){
					Invoice invoice=new Invoice();
					invoice.setInvoiceNumber(holder.value[i].getXBLNR()!=null?holder.value[i].getXBLNR().replaceAll("^0*", ""):"");
					try{
						if(holder.value[i].getBUDAT()!=null && !holder.value[i].getBUDAT().equalsIgnoreCase("0000-00-00")){								
							invoice.setInvoiceDate(DATE_FORMAT_SAP.parse(holder.value[i].getBUDAT()));
						}
						if(holder.value[i].getAUGDT()!=null && !holder.value[i].getAUGDT().equalsIgnoreCase("0000-00-00")){	
							invoice.setPaidDate(DATE_FORMAT_SAP.parse(holder.value[i].getAUGDT()));
						}
						if(holder.value[i].getZZDUEDATE()!=null && !holder.value[i].getZZDUEDATE().equalsIgnoreCase("0000-00-00")){	
							invoice.setDueDate(DATE_FORMAT_SAP.parse(holder.value[i].getZZDUEDATE()));
						}
					}catch(ParseException pe){
						logger.error("Date parsing error occured in service call");
					}
					invoice.setStatus(holder.value[i].getZZSTATUS());
					BigDecimal tempVal=holder.value[i].getWRBTR();
					if(tempVal!=null){
						invoice.setAmount(tempVal.setScale(2, BigDecimal.ROUND_DOWN));
					}else{
						invoice.setAmount(new BigDecimal("0.00"));
					}
					
					invoice.setCurrencyType(holder.value[i].getWAERS());
					invoice.setSapDocId1(holder.value[i].getZZDOC_ID1());
					invoice.setSapDocId2(holder.value[i].getZZDOC_ID2());
					list.add(invoice);
				}
			}
		}
		result.setInvoice(list);
		return result;
		
	}
	
	/**
	 * @param filterColumns
	 * @param filterCriteria
	 * @param value
	 * @return
	 */
	private boolean checkFilter(String[] filterColumns,Map<?,?>filterCriteria,ZGSF_MPS_INVOICE_LIST value){
		String[] SAPFieldMap={"XBLNR","ZZSTATUS"};
		int i=0;
		boolean correctData=true;
		for(String col:filterColumns){
			String filterVal=(String)filterCriteria.get(col);
			if(StringUtils.isNotBlank(filterVal)){
				logger.debug("filter val is "+filterVal);
				String beanVal=null;
				try {
					beanVal=(String)PropertyUtils.getProperty(value, SAPFieldMap[i]);
				} catch (IllegalAccessException e) {
					logger.error("IllegalAccessException occured");
				} catch (InvocationTargetException e) {
					logger.error("InvocationTargetException occured");
				} catch (NoSuchMethodException e) {
					logger.error("NoSuchMethodException occured");
				}
				logger.debug("the data is for beanVal "+beanVal+"filterVal "+filterVal);
				if(StringUtils.isNotBlank(beanVal) && beanVal.toLowerCase().contains(filterVal.toLowerCase())){
					logger.debug("the data is correct");
					correctData=true;
				}else{
					logger.debug("the data is incorrect");
					correctData=false;
					break;
				}
			}
			i++;
		}
		return correctData;
	}
	
}
