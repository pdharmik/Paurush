package com.lexmark.portlet.partnerPayments;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.lexmark.webservice.impl.mock.APSRServiceMockImpl;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.AccountPayableDetailContract;
import com.lexmark.contract.AccountPayableListContract;
import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.InvoiceListContract;
import com.lexmark.contract.SRListContract;
import com.lexmark.domain.AccountPayable;
import com.lexmark.domain.AccountReceivable;
import com.lexmark.domain.Invoice;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.exporter.DataExporter;
import com.lexmark.exporter.DataExporterFactory;
import com.lexmark.form.InvoiceAPForm;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.portlet.BaseController;
import com.lexmark.result.AccountPayableDetailResult;
import com.lexmark.result.AccountPayableListResult;
import com.lexmark.result.AccountReceivableListResult;
import com.lexmark.result.InvoiceListResult;
import com.lexmark.result.SRListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PaymentsService;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.webservice.api.InvoiceService;
import com.lexmark.webservice.api.SRService;
import com.lexmark.util.PerformanceUtil;

@SuppressWarnings("unused")
@Controller
@RequestMapping("VIEW")

public class PartnerPaymentController extends BaseController{
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private PaymentsService paymentsService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private SRService srServiceAP;
	@Autowired
	private SRService srServiceAR;
	
	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(PartnerPaymentController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String CLASS_NAME = "PartnerPaymentController.java" ;
	private static final String METH_RETRIEVEVENDORLIST ="retrieveAccountList";
	private static final String METH_SETCURRENTACCOUNTSPAYABLE ="setCurrentAccountsPayable";
	private static final String METH_SETACCNTS_RECIVABLVALUES= "setAccountsRecivableValues";
	private static final String METH_SHOW_S_DETAILS = "showSRDetails";
	private static final String METH_ACCNTSRCVLLIST ="accountsRecvblList";
	public static final String POS_START = "posStart";
	
	/**
	 * This method is used to show the initial popup or AP Vendor selection page
	 * @param model
	 * @param request
	 * @param response
	 * @return jsp name as string
	 * @throws Exception
	 */
	@RequestMapping
	public String showVendorListPage(Model model, RenderRequest request, RenderResponse response) throws Exception{			
			//PARTHA ADDED for CI
			request.getPortletSession().setAttribute("fromDateAR", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("toDateAR", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("fromDateAP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("toDateAP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("paymentStatusAR", "Open", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("paymentStatusAP", "Open", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("sortingColumn_AP", "3", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("sortingColumnVal_AP", "asc", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("sortingColumn_AR", "0", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("sortingColumnVal_AR", "asc", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("filterColumn_0_AP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("filterColumn_3_AP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("filterColumn_5_AP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("filterColumn_0_AR", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("filterColumn_2_AR", "", PortletSession.APPLICATION_SCOPE);
		return "partnerPayments/vendorSelectionPopUp";
	}
	
	@ResourceMapping(value="populateVendorList")
	public void retrieveVendorList(Model model,ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVEVENDORLIST);
		String isCatalogPage = null;
		PortletSession portletSession = resourceRequest.getPortletSession();
		AccountPayableListContract accountPayableListContract = ContractFactory.getAccountPayableListContract(resourceRequest);
		
		CrmSessionHandle crmSessionHandle = globalService
											.initCrmSessionHandle(PortalSessionUtil
											.getSiebelCrmSessionHandle(resourceRequest));
		accountPayableListContract.setSessionHandle(crmSessionHandle);
		
		LOGGER.info("start printing lex logger");		
		ObjectDebugUtil.printObjectContent(accountPayableListContract, LOGGER);
		LOGGER.info("end printing lex loggger");
		

		
		long timeBeforeCall=System.currentTimeMillis();
		AccountPayableListResult accountPayableListResult = paymentsService.retrieveAccountPayableList(accountPayableListContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_VENDORLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,accountPayableListContract);
		
		List<AccountPayable> accountsPaybleList = accountPayableListResult.getAccountPayableList();
		
		if(accountsPaybleList.size()==0){
			portletSession.setAttribute("isError", "TRUE", PortletSession.APPLICATION_SCOPE);
		}else{
			portletSession.setAttribute("isError", "FALSE", PortletSession.APPLICATION_SCOPE);
		}
		
		LOGGER.debug("-------------Converting Account List to XML STARTS---------");
		
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(resourceRequest.getLocale());
		String content = xmlOutputGenerator.convertAccountsPayableListToXML(resourceRequest,accountsPaybleList,accountsPaybleList.size(), 0);
		LOGGER.debug("content " + content);
		LOGGER.debug("-------------Converting Account List to XML ENDS---------");
		globalService.releaseSessionHandle(crmSessionHandle);
		try{
		PrintWriter out = resourceResponse.getWriter();
		resourceResponse.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		}catch(IOException e){
			LOGGER.debug("Exception occured while writing the content in retrieveAccountList");
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVEVENDORLIST);
	}
	
	@ResourceMapping(value="setCurrentAccountsPayableValuesToSession")
	public void setCurrentAccountsPayable(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		LOGGER.enter(this.getClass().getSimpleName(), METH_SETCURRENTACCOUNTSPAYABLE);
		Enumeration<String> paramNames=resourceRequest.getParameterNames();
		Map<String,String> accountsPayableDetailsValues=new HashMap<String,String>();
		while(paramNames.hasMoreElements()){
			String paramName=paramNames.nextElement();
			LOGGER.debug("session:"+paramName+ " " +resourceRequest.getParameter(paramName) );
			accountsPayableDetailsValues.put(paramName, resourceRequest.getParameter(paramName));
		}
		PortletSession portletSession = resourceRequest.getPortletSession();
		
		if (accountsPayableDetailsValues == null)
		 {
			accountsPayableDetailsValues =new HashMap<String,String>();
		 }
		portletSession.setAttribute("vendorCurrentDetails", accountsPayableDetailsValues ,PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("request values"+accountsPayableDetailsValues.toString());
		
		try{
			PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setContentType("text/plain");
			out.print("success");
			out.flush();
			out.close();
		}catch(IOException ie){
			LOGGER.debug("In IOException"+ie.getMessage());
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_SETCURRENTACCOUNTSPAYABLE);
	}
	
	/**
	 * Redirects to either AP invoice jsp or AR invoice jsp
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=redirectToInvoicePage")
	public String redirectToInvoicePage(RenderRequest request, RenderResponse response, Model model)
	{
		
		boolean isAR = false;
		if(request.getParameter("typeId")!=null && request.getParameter("typeId").equalsIgnoreCase("AR"))
		{
			LOGGER.debug("type Id " + request.getParameter("typeId"));
			isAR = true;
		}
		
		if(request.getParameter("navPage")!=null && request.getParameter("navPage").equalsIgnoreCase("DETAIL")){
			
		}else{
			//PARTHA ADDED for CI
			request.getPortletSession().setAttribute("fromDateAR", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("toDateAR", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("fromDateAP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("toDateAP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("paymentStatusAR", "Open", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("paymentStatusAP", "Open", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("sortingColumn_AP", "3", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("sortingColumnVal_AP", "asc", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("sortingColumn_AR", "0", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("sortingColumnVal_AR", "asc", PortletSession.APPLICATION_SCOPE);
			
			request.getPortletSession().setAttribute("filterColumn_0_AP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("filterColumn_3_AP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("filterColumn_5_AP", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("filterColumn_0_AR", "", PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("filterColumn_2_AR", "", PortletSession.APPLICATION_SCOPE);
		}

		if(isAR)
		{
			return LexmarkPPConstants.ARINVOICEVIEW;
		}else{
			return LexmarkPPConstants.APINVOICEVIEW;
		}
	}
	/**
	 * Retrieves and displays AP Invoice Details for Payment.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return invoice details in the form of a JSP Page
	 * @throws Exception
	 */
	
	@SuppressWarnings("unchecked")
	@ResourceMapping(value="showInvoiceListURL")
	public void retrieveInvoiceList(Model model, ResourceRequest request,ResourceResponse response) throws Exception
	{
		String METHOD_NAME = "retrieveInvoiceList";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		boolean isAR = false;
		InvoiceListResult invoiceListResult = null;
		
				
		try{
			InvoiceListContract contract = null;
			
		if(request.getParameter("typeId")!=null && request.getParameter("typeId").equalsIgnoreCase("AR")){
			LOGGER.debug("type Id " + request.getParameter("typeId"));
			isAR = true;
		}
		
				
		if(isAR)
		{
			request.getPortletSession().setAttribute("fromDateAR", request.getParameter("fromDate"), PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("toDateAR", request.getParameter("endDate"), PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("paymentStatusAR", request.getParameter("paymentStatus"), PortletSession.APPLICATION_SCOPE);
			
			contract = ContractFactory.createInvoiceListContractforAR(request);
		}else{	
			request.getPortletSession().setAttribute("fromDateAP", request.getParameter("fromDate"), PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("toDateAP", request.getParameter("endDate"), PortletSession.APPLICATION_SCOPE);
			request.getPortletSession().setAttribute("paymentStatusAP", request.getParameter("paymentStatus"), PortletSession.APPLICATION_SCOPE);
			
			contract = ContractFactory.createInvoiceListContractforAP(request);
		}
			
			
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			LOGGER.info("end printing lex logger");
			
			LOGGER.debug("Before invoking retrieveInvoiceList");
			long timeBeforeCall=System.currentTimeMillis();
			invoiceListResult = invoiceService.retrieveInvoiceList(contract);
			
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_INVOICELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,contract);
		}
		catch(Exception e)
		{
			LOGGER.debug("In IOException"+e.getMessage());
			LOGGER.error("Exception occured while retrieving Invoice details ");
			model.addAttribute(LexmarkPPConstants.EXCEPTIONOCCURED, LexmarkPPConstants.TRUE_ATTR);
			
		}
		
		
		Locale locale = request.getLocale();
		
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		InvoiceAPForm invoiceAPForm = new InvoiceAPForm();
		
		
		
		String outputXML= xmlOutputGenerator.convertInvoiceAPListToXML(invoiceListResult.getInvoice(),isAR, invoiceListResult.getInvoice().size(), request.getParameter("fromDate"), request.getParameter("endDate"));
		
		String invoiceCurrency = null;
		
		if(invoiceListResult.getInvoice()!=null && invoiceListResult.getInvoice().size()>0)
		{
			if(invoiceListResult.getInvoice().get(0).getCurrencyType()!=null)
			{
				invoiceCurrency = invoiceListResult.getInvoice().get(0).getCurrencyType();
			}else{
				invoiceCurrency = "USD";
			}
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		
		try {
			PrintWriter out = response.getWriter();
			response.setContentType(LexmarkPPConstants.XMLCONTENT);
					
			out.print(outputXML);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.debug("In IOException"+e.getMessage());;
		}
	}
	
	
	/**
	 * Retrieves and displays AP SR Details for Payment.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return invoice details in the form of a JSP Page
	 * @throws Exception
	 */
	
	@RequestMapping(params = "action=showInvoicePageAP")
	public String showInvoicePageAP(Model model, RenderRequest request,RenderResponse response) throws Exception
	{
		String METHOD_NAME = "showInvoicePageAP";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		Map<String,String> vendorDetailsMap=(Map<String,String>)request.getPortletSession().getAttribute("vendorCurrentDetails",PortletSession.APPLICATION_SCOPE);
		
		vendorDetailsMap.put("invoiceDt", request.getParameter("invoiceDt"));
		vendorDetailsMap.put("dueDt", request.getParameter("dueDt"));
		vendorDetailsMap.put("paidDt", request.getParameter("paidDt"));
		vendorDetailsMap.put("checkNo", request.getParameter("checkNo"));
		vendorDetailsMap.put("amount", request.getParameter("amount"));
		vendorDetailsMap.put("currency", request.getParameter("currency"));
		
		request.getPortletSession().setAttribute("vendorCurrentDetails", vendorDetailsMap, PortletSession.APPLICATION_SCOPE);		
		//Partha Added for 13-10-06
		request.getPortletSession().setAttribute("sortingColumn_AP", request.getParameter("sortColumn"), PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("sortingColumnVal_AP", request.getParameter("sortColumnVal"), PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("filterColumn_0_AP", request.getParameter("filterColumnVal_0"), PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("filterColumn_3_AP", request.getParameter("filterColumnVal_3"), PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("filterColumn_5_AP", request.getParameter("filterColumnVal_5"), PortletSession.APPLICATION_SCOPE);
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);		
		return LexmarkPPConstants.SRDETAILSAP;
	}
	
	/**
	 * Retrieves and displays AP SR Details for Payment.
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return invoice details in the form of a JSP Page
	 * @throws Exception
	 */
	
	@RequestMapping(params = "action=showInvoicePageAR")
	public String showInvoicePageAR(Model model, RenderRequest request,RenderResponse response) throws Exception
	{
		String METHOD_NAME = "showInvoicePageAR";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		Map<String,String> vendorDetailsMap=(Map<String,String>)request.getPortletSession().getAttribute("accRecivablDetails",PortletSession.APPLICATION_SCOPE);
		
		vendorDetailsMap.put("invoiceDt", request.getParameter("invoiceDt"));
		vendorDetailsMap.put("amount", request.getParameter("amount"));
		vendorDetailsMap.put("currency", request.getParameter("currency"));
		request.getPortletSession().setAttribute("accRecivablDetails", vendorDetailsMap, PortletSession.APPLICATION_SCOPE);
		
		//Partha Added for 13-10-06
		request.getPortletSession().setAttribute("sortingColumn_AR", request.getParameter("sortColumn"), PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("sortingColumnVal_AR", request.getParameter("sortColumnVal"), PortletSession.APPLICATION_SCOPE);
		
		request.getPortletSession().setAttribute("filterColumn_0_AR", request.getParameter("filterColumnVal_0"), PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("filterColumn_2_AR", request.getParameter("filterColumnVal_2"), PortletSession.APPLICATION_SCOPE);
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);		
		return LexmarkPPConstants.SRDETAILSAR;
	}
	
	@ResourceMapping("retrieveSRListXMLAR")
	public void retrieveSRListXMLAR(ResourceRequest request, ResourceResponse response, Model model) throws Exception
	{
		String METHOD_NAME = "retrieveSRListXMLAR";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		int posStart = NumberUtils.isNumber(request.getParameter(POS_START)) ? Integer.parseInt(request
				.getParameter(POS_START)) : 0;
		SRListResult SRListResult = null;
		String outputXML = null;
		try{
			SRListContract contract = ContractFactory.createARSRListContract(posStart,request);
			request.getPortletSession().setAttribute("ARRequestContract", contract); 
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			LOGGER.info("end printing lex logger");
			LOGGER.debug("Before invoking  SR List");

			
			long timeBeforeCall=System.currentTimeMillis();
			SRListResult = srServiceAR.retrieveAPSRList(contract);	
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_SRLISTFORAR, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,contract);	
		
		PortletSession session = request.getPortletSession();
		Locale locale = request.getLocale();
		
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		
		
		if(SRListResult !=null && SRListResult.getServiceRequest()!=null)
		{
			outputXML = xmlOutputGenerator.convertSRListToXML(SRListResult.getServiceRequest(),posStart,SRListResult.getTotalCount());
		}
		else{

			outputXML = "<?xml version=\\\"1.0\\\" ?><rows></rows>" ;
		}
		
	}catch(Exception e)
		{
			
			LOGGER.error("Exception occured while retrieving Invoice details ");
			
			outputXML = "<?xml version=\\\"1.0\\\" ?><rows></rows>" ;
		}
		try {
			PrintWriter out = response.getWriter();
			response.setContentType(LexmarkPPConstants.XMLCONTENT);
					
			out.print(outputXML);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.debug("In IOException"+e.getMessage());;
		}
		
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);		
		
	}
	
	
	@ResourceMapping("retrieveSRListXML")
	public void retrieveSRListXML(ResourceRequest request, ResourceResponse response, Model model) throws LGSCheckedException,LGSRuntimeException
	{
		String METHOD_NAME = "retrieveSRListXML";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		int posStart = NumberUtils.isNumber(request.getParameter(POS_START)) ? Integer.parseInt(request
				.getParameter(POS_START)) : 0;
		SRListResult SRListResult = null;
		String outputXML = null;
		try{
			SRListContract contract = ContractFactory.createAPSRListContract(posStart,request);
			request.getPortletSession().setAttribute("APRequestContract", contract);
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			LOGGER.info("end printing lex logger");
			LOGGER.debug("Before invoking  SR List");
			
			long timeBeforeCall=System.currentTimeMillis();
			SRListResult = srServiceAP.retrieveAPSRList(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_SRLISTFORAP, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,contract);
		
		
		PortletSession session = request.getPortletSession();
		Locale locale = request.getLocale();
		
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		
		
		if(SRListResult.getServiceRequest()!=null)
		{
			
			LOGGER.debug("total count in controller "+SRListResult.getTotalCount());
			
			outputXML = xmlOutputGenerator.convertSRListToXML(SRListResult.getServiceRequest(),posStart,SRListResult.getTotalCount());

			
		}else{
			
			outputXML = "<?xml version=\\\"1.0\\\" ?><rows></rows>" ;	
		}
		
		}catch(Exception e)
		{
			LOGGER.error("Exception occured while retrieving Invoice details ");
			outputXML = "<?xml version=\\\"1.0\\\" ?><rows></rows>" ;
		}
		
		
		 
		try {
			PrintWriter out = response.getWriter();
			response.setContentType(LexmarkPPConstants.XMLCONTENT);
			
			
			out.print(outputXML);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.debug("In IOException"+e.getMessage());
		}		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	@ResourceMapping(value="accountsRecvblList")
	public void accountsRecvblList(Model model,ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		LOGGER.enter(this.getClass().getSimpleName(), METH_ACCNTSRCVLLIST);
		String isCatalogPage = null;
		PortletSession portletSession = resourceRequest.getPortletSession();
		AccountReceivableListContract accountRcvblListContract = ContractFactory.getAccountRecievableListContract(resourceRequest);
		

		
		CrmSessionHandle crmSessionHandle = globalService
											.initCrmSessionHandle(PortalSessionUtil
											.getSiebelCrmSessionHandle(resourceRequest));
		accountRcvblListContract.setSessionHandle(crmSessionHandle);
		
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(accountRcvblListContract, LOGGER);
		LOGGER.info("end printing lex logger");
		
			
		
		long timeBeforeCall=System.currentTimeMillis();
		AccountReceivableListResult accountRcvblListResult = paymentsService.retrieveAccountReceivableList(accountRcvblListContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CUSTOMERINVOICE_MSG_RETRIEVEACCOUNTRECEIVABLELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,accountRcvblListContract);
		
		List<AccountReceivable> accountsRcvblList = accountRcvblListResult.getAccountReceivableList();
		
		LOGGER.debug("-------------Converting Account List to XML STARTS---------");
		
		
		if(accountsRcvblList.size()==0){
			portletSession.setAttribute("isError", "TRUE", PortletSession.APPLICATION_SCOPE);
		}else{
			portletSession.setAttribute("isError", "FALSE", PortletSession.APPLICATION_SCOPE);
		}
		
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(resourceRequest.getLocale());
		String content = xmlOutputGenerator.convertAccountsRcvblListToXML(accountsRcvblList,accountsRcvblList.size(), 0);
		
		LOGGER.debug("-------------Converting Account List to XML ENDS---------");
		globalService.releaseSessionHandle(crmSessionHandle);
		try{
		PrintWriter out = resourceResponse.getWriter();
		resourceResponse.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		}catch(IOException e){
			LOGGER.debug("Exception occured while writing the content in accountsRecvblList");
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVEVENDORLIST);
	}
	
	@ResourceMapping(value="setAccountsRecivableValues")
	public void setAccountsRecivableValues(ResourceRequest request,ResourceResponse response){
		LOGGER.enter(this.getClass().getSimpleName(), METH_SETACCNTS_RECIVABLVALUES);
		Enumeration<String> paramNames=request.getParameterNames();
		Map<String,String> accntsRecivblsMap=new HashMap<String,String>();
		while(paramNames.hasMoreElements()){
			String paramName=paramNames.nextElement();
			LOGGER.debug("param Name "+paramName+ " " +request.getParameter(paramName) );
			accntsRecivblsMap.put(paramName, request.getParameter(paramName));
		}
		PortletSession portletSession = request.getPortletSession();
		
		if (accntsRecivblsMap == null)
		 {
			accntsRecivblsMap =new HashMap<String,String>();
		 }
		portletSession.setAttribute("accRecivablDetails", accntsRecivblsMap ,PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("request values"+accntsRecivblsMap.toString());
		
		LOGGER.exit(this.getClass().getSimpleName(), METH_SETACCNTS_RECIVABLVALUES);
		try{
			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.print("success");
			out.flush();
			out.close();
		}catch(IOException ie){
			LOGGER.debug("In IOException"+ie.getMessage());
		}
	}
	
	@ResourceMapping(value="showSRDetails")
	public void showSRDetails(ResourceRequest request,ResourceResponse response, Model model){
		LOGGER.enter(this.getClass().getSimpleName(), METH_SHOW_S_DETAILS);		
		PortletSession portletSession = request.getPortletSession();
		
		StringBuilder strBulder = new StringBuilder();
		
		String partNo = PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME,"partnerPayments.label.part", request.getLocale());
		String suppliesDesc = PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME,"partnerPayments.label.suppliesDesc", request.getLocale());
		String quantity = PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME,"partnerPayments.label.quantity", request.getLocale());
		String part_Fee = PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME,"partnerPayments.label.partFee", request.getLocale());
		String fulfillment_Fee = PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME,"partnerPayments.label.fulfillmentFee", request.getLocale());
		String amount = PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME,"partnerPayments.label.amount", request.getLocale());
		
		try{

			
			AccountPayableDetailContract contract = ContractFactory.createSRDetailContract(request, portletSession);			
			
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			LOGGER.info("end printing lex logger");
			LOGGER.debug("Before invoking  SR List");
			
				
			
			LOGGER.info("end printing lex logger");
			long timeBeforeCall=System.currentTimeMillis();
			AccountPayableDetailResult srDetailResult = paymentsService.retrieveAccountPayableDetail(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_ACCOUNTPAYABLEDETAIL, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		
		
			strBulder.append("<table class=\"displayGrid rounded shadow wFull\"><thead><tr><th>").append(partNo)
			.append("</th><th>").append(suppliesDesc).append("</th><th>").append(quantity).append("</th><th>")
			.append(part_Fee).append("</th><th>").append(fulfillment_Fee).append("</th><th>").append(amount)
			.append("</th></tr></thead><tbody>");
			
			if(srDetailResult != null && srDetailResult.getAccountPayableDetail() != null)
			{
				if(srDetailResult.getAccountPayableDetail().getActivities()!=null && srDetailResult.getAccountPayableDetail().getActivities().size()>0)
				{
					for(int j=0;j<srDetailResult.getAccountPayableDetail().getActivities().size();j++)				
					{
						if(j%2==0)
						{
							strBulder.append("<tr class=\"altRow\">");
						}
						else{
							strBulder.append("<tr>");
						}
						
						
						String partNumber = srDetailResult.getAccountPayableDetail().getActivities().get(j).getPartNumber() != null ? srDetailResult.getAccountPayableDetail().getActivities().get(j).getPartNumber() : "";
						String partDesc = srDetailResult.getAccountPayableDetail().getActivities().get(j).getPartDescription() != null ? srDetailResult.getAccountPayableDetail().getActivities().get(j).getPartDescription() : "";
						
						strBulder.append("<td>"+partNumber+"</td>");
						strBulder.append("<td>"+partDesc+"</td>");
						strBulder.append("<td>"+String.valueOf(srDetailResult.getAccountPayableDetail().getActivities().get(j).getQuantity())+"</td>");
						strBulder.append("<td>"+String.valueOf(srDetailResult.getAccountPayableDetail().getActivities().get(j).getPartFee())+"</td>");
						strBulder.append("<td>"+String.valueOf(srDetailResult.getAccountPayableDetail().getActivities().get(j).getFulfillmentFee())+"</td>");
						strBulder.append("<td>"+String.valueOf(srDetailResult.getAccountPayableDetail().getActivities().get(j).getAmount())+"</td>");
						strBulder.append("</tr>");
						
					}
				}
				strBulder.append("<input type=\"hidden\" name=\"custAccName\" id=\"custAccName\" value = \""+srDetailResult.getAccountPayableDetail().getCustomerAccountName()+"\"  ></input>");
				strBulder.append("<input type=\"hidden\" name=\"partFee\" id=\"partFeeHidden\" value = \""+srDetailResult.getAccountPayableDetail().getTotalPartFee()+"\"  ></input>");
				strBulder.append("<input type=\"hidden\" name=\"FullfillmentFee\" id=\"FullfillmentFee\" value = \""+srDetailResult.getAccountPayableDetail().getTotalFulfillmentFee()+"\"  ></input>");
				strBulder.append("<input type=\"hidden\" name=\"laborFee\" id=\"laborFee\" value = \""+srDetailResult.getAccountPayableDetail().getTotalLabor()+"\"  ></input>");
				strBulder.append("<input type=\"hidden\" name=\"additionalPayments\" id=\"additionalPayments\" value = \""+srDetailResult.getAccountPayableDetail().getTotalAdditionalPayments()+"\"  ></input>");
			}
			
			
			
			strBulder.append("</tbody></table>");
		
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(strBulder.toString());
			out.flush();
			out.close();
			LOGGER.exit(this.getClass().getSimpleName(), METH_SHOW_S_DETAILS);
		
		}catch(Exception e)
		{
			LOGGER.error("Exception occured while retrieving Invoice details ");
			model.addAttribute(LexmarkPPConstants.EXCEPTIONOCCURED, LexmarkPPConstants.TRUE_ATTR);
			
		}
	}
	
	@RequestMapping(params = "action=printInvoiceHistoryList")
	public String showPrintInvoiceHistoryList(RenderRequest request, RenderResponse response, Model model) throws Exception{
		LOGGER.debug("-------------showPrint started---------");
		return "partnerPayments/InvoiceHistoryPrint";
	}
	//Added By arko for PDF and EXCEL
	@SuppressWarnings("unchecked")
	@ResourceMapping(value="downloadAPARInvoiceHistoryList")
	public void downloadPDFEXCEL(ResourceRequest request,ResourceResponse response){
		boolean isAR=false;
		String downloadType=request.getParameter(LexmarkPPConstants.DOWNLOADTYPE);
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat localeSdf = new SimpleDateFormat(DateLocalizationUtil.getDateFormatByLanguage(request.getLocale().getLanguage()));
		
		String fromDateStr = request.getParameter("fromDate");
		String toDateStr = request.getParameter("endDate");
		Date fromDateFinal = null;
		Date toDateFinal = null;
		
			
		try {
			if(toDateStr!=null && !"".equals(toDateStr)){
				toDateFinal = localeSdf.parse(toDateStr);
			}else{
				toDateFinal = new Date();
			}
			
			if(fromDateStr!=null && !"".equals(fromDateStr)){
				fromDateFinal = localeSdf.parse(fromDateStr);
			}else{
				fromDateFinal = new Date();
			}
		} catch (Exception e) {
			LOGGER.error("Locale Conversion Error: "+e);
		}
		
		
		LOGGER.debug("Download type is "+downloadType);
		if(request.getParameter("typeId")!=null && request.getParameter("typeId").equalsIgnoreCase("AR"))
		{
			LOGGER.info("type Id " + request.getParameter("typeId"));
			isAR = true;
		}
		InvoiceListResult invoiceListResult = null;
		
		try{
			InvoiceListContract contract = null;
			if(isAR){
				contract = ContractFactory.createInvoiceListContractforAR(request);
			}else{				
				contract = ContractFactory.createInvoiceListContractforAP(request);
			}
			
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			LOGGER.info("end printing lex logger");
			long timeBeforeCall=System.currentTimeMillis();
			invoiceListResult = invoiceService.retrieveInvoiceList(contract);
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_INVOICELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,contract);
			
			// added by sbag to be in sync with the data as shown in the grid as well as in the pfd defect# 8173	
			
			List<Invoice> invoiceData = invoiceListResult.getInvoice();	
			List<Invoice> filterInvoiceData = new ArrayList<Invoice>(); 
			
			
			
			Invoice invoice = null;
			Date invoiceDate = null;
			String invoiceDateToBeShown = null;
			Date invoiceDateToBeShownFinal =null;
			
			Iterator<Invoice> itr = invoiceData.iterator();
			
			if(isAR){
				//For AR Only
				while(itr.hasNext()){
					invoice = itr.next();				
					invoiceDate = invoice.getInvoiceDate();
					invoiceDateToBeShown = sdf.format(invoiceDate);
					invoiceDateToBeShownFinal = sdf.parse(invoiceDateToBeShown);
					
					
					
					if((fromDateFinal.before(invoiceDateToBeShownFinal) || fromDateFinal.equals(invoiceDateToBeShownFinal)) && (invoiceDateToBeShownFinal.before(toDateFinal) || invoiceDateToBeShownFinal.equals(toDateFinal))){
						filterInvoiceData.add(invoice);
					}
				}
			}
			else{
				//For AP Only
				while(itr.hasNext()){
					invoice = itr.next();				
					Date paidDate = null;
					String paidDateToBeShown = null;
					Date paidDateToBeShownFinal =null;
					
					paidDate = invoice.getPaidDate();
					if(paidDate!=null && !"".equals(paidDate)){
						paidDateToBeShown = sdf.format(paidDate);	
						paidDateToBeShownFinal = sdf.parse(paidDateToBeShown);
					}
					boolean flag = false;
					
					if(paidDateToBeShownFinal ==  null){
						flag = true;
					}else{
						if((fromDateFinal.before(paidDateToBeShownFinal) || fromDateFinal.equals(paidDateToBeShownFinal)) && (paidDateToBeShownFinal.before(toDateFinal) || paidDateToBeShownFinal.equals(toDateFinal))){
							flag = true;
						}
					}
					if(flag){
						filterInvoiceData.add(invoice);
					}
					
				}
			} 
			
			
		
			
			//ends here
						
			
			LOGGER.debug("After invoking SR List");
			
			DataExporter dataExporter = DataExporterFactory.getDataExporter(downloadType);
			dataExporter.setLocale(request.getLocale());
			LOGGER.debug("before export");
			if(isAR)
				dataExporter.exportOrder(response, LexmarkPPConstants.AR, LexmarkPPConstants.ARCOLUMNSPATTERN, filterInvoiceData);
			else
				dataExporter.exportOrder(response, LexmarkPPConstants.AP, LexmarkPPConstants.APCOLUMNSPATTERN, filterInvoiceData);
			LOGGER.debug("after export");
			
		}
		catch(Exception e)
		{
			LOGGER.error("Exception occured while retrieving Invoice details ");
			
			
		}
	}
		/**
		 * 
		 * @param request
		 * @param response
		 * 
		 * This is for AP and AR request history download
		 */
		@SuppressWarnings("unchecked")
		@ResourceMapping(value="downloadAPARRequestHistoryList")
		public void downloadRequestPDFEXCEL(ResourceRequest request,ResourceResponse response){
			
			LOGGER.info("<--- Entering into downloadRequestPDFEXCEL ---->");
			String downloadType=request.getParameter(LexmarkPPConstants.DOWNLOADTYPE);
			LOGGER.info("The download type is--->>>"+downloadType);
			String METHOD_NAME = "retrieveSRListXML";
			LOGGER.enter(CLASS_NAME, METHOD_NAME);
			int posStart = NumberUtils.isNumber(request.getParameter(POS_START)) ? Integer.parseInt(request
					.getParameter(POS_START)) : 0;
			SRListResult SRListResult = null;
			String outputXML = null;
			
			boolean isAR= false;
			LOGGER.info("type Id is --->>>" + request.getParameter("typeId"));
			if(request.getParameter("typeId")!=null && request.getParameter("typeId").equalsIgnoreCase("AR"))
			{				
				isAR = true;
			}
			try{
											
				long timeBeforeCall=System.currentTimeMillis();
				
				if(isAR){
					SRListContract contract=(SRListContract)request.getPortletSession().getAttribute("ARRequestContract");					
					LOGGER.info("end printing lex logger AR");
					ObjectDebugUtil.printObjectContent(contract, LOGGER);
					LOGGER.info("end printing lex logger");
					LOGGER.info("Before invoking  SR List");
					SRListResult = srServiceAR.downloadExcelAPSRList(contract);
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_SRLISTFORAR, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,contract);
				}else{
					SRListContract contract=(SRListContract)request.getPortletSession().getAttribute("APRequestContract");
					LOGGER.info("end printing lex logger AP");
					ObjectDebugUtil.printObjectContent(contract, LOGGER);
					LOGGER.info("end printing lex logger");
					LOGGER.info("Before invoking  SR List");
					SRListResult = srServiceAP.downloadExcelAPSRList(contract); //real call
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_SRLISTFORAP, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,contract);
				}				
				
				DataExporter dataExporter = DataExporterFactory.getDataExporter("CSV");
				dataExporter.setLocale(request.getLocale());
				LOGGER.info("After the data exporter---->>>");											 							
				if(isAR)
					dataExporter.exportOrder(response, LexmarkPPConstants.REQ_HIS_AR, LexmarkPPConstants.APRequestHistoryList, SRListResult.getServiceRequest());
				else
					dataExporter.exportOrder(response, LexmarkPPConstants.REQ_HIS_AP, LexmarkPPConstants.APRequestHistoryList, SRListResult.getServiceRequest());
				LOGGER.info("<--- Exiting downloadRequestPDFEXCEL ---->");
			LOGGER.exit(CLASS_NAME, METHOD_NAME);			
		}catch(Exception e){
			/* added by sbag for downloading the excel when there is no data */
			//Character encoding attached for Defect #7854
			response.setProperty("Content-disposition", "attachment; filename=" + "BlankData.csv");
			response.setContentType("text/csv;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			/* ends here */
			LOGGER.debug("In Exception"+e.getMessage());
		}
		}
}
	

