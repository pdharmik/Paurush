package com.lexmark.services.portlet.customerinvoice;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

//import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.AccountReceivableListContract;
import com.lexmark.contract.InvoiceListContract;
import com.lexmark.domain.AccountCustomerReceivable;
import com.lexmark.domain.Invoice;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AccountCustomerReceivableListResult;
import com.lexmark.result.InvoiceListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.CustomerPaymentsService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.services.api.CustomerInvoiceService;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ResourceResponseUtil;
import com.lexmark.services.util.XmlOutputGenerator;
import com.lexmark.services.util.PerformanceUtil; //Added for performance logging
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.DownloadFileUtil;
import com.lexmark.util.report.PdfPReportGenerator;

import com.liferay.portal.util.PortalUtil;

/**
 * @author wipro
 * @version 2.1
 *
 */

@Controller
@RequestMapping("VIEW")
public class CustomerInvoiceController extends BaseController{

	private static final String METH_SHOWPRINTLSTPG = "showPrintInvoiceListPage";
	
	@Autowired
	private CustomerInvoiceService invoiceService;
	
	@Autowired
	private GlobalService globalService;
	
	@Autowired
	private CustomerPaymentsService customerPaymentsService;
	
	/**. Holds the reference of CommonController bean **/
	@Autowired
	private CommonController commonController;
	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(CustomerInvoiceController.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	
	
	private static String CLASS_NAME = "CustomerInvoiceController.java" ;
	private static final String METH_ACCNTSRCVLLIST ="accountsRecvblList";
	private static final String METH_RETRIEVEVENDORLIST ="retrieveAccountList";

	private static final String METH_SETACCNTS_RECIVABLVALUES = "setAccountsRecivableValues";
	
	/**
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping
	public String customerInvoiceView(Model model, RenderRequest request,
			RenderResponse response){
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		boolean accSelDn=StringUtils.isNotBlank(httpReq.getParameter("accSelDn"));
		if(StringUtils.isNotBlank(httpReq.getParameter("invoiceNumber"))){
			accSelDn=true;
		}
		model.addAttribute("accountSelectionDone", accSelDn);
		LOGGER.debug("account selecti on done "+accSelDn);
		Enumeration<?> paramNames=httpReq.getParameterNames();
		Map<String,String> accntsRecivblsMap=new HashMap<String,String>();
		while(paramNames.hasMoreElements()){
			String paramName=(String)paramNames.nextElement();
			accntsRecivblsMap.put(paramName, httpReq.getParameter(paramName));
		}
		PortletSession portletSession = request.getPortletSession();
		
	
		portletSession.setAttribute("accRecivablDetails", accntsRecivblsMap ,PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("request values"+accntsRecivblsMap.toString());
		
		Map<String,String> requestStatusLOVMap = null;
		
		
			 try {
				requestStatusLOVMap = getInvoiceStatusMap(request.getLocale());
			} catch (Exception e) {
				LOGGER.error("Unable to retrieve Status map");
				e.getMessage();
			}
		
		model.addAttribute("requestStatusLOVMap", requestStatusLOVMap);
		 return("customerInvoice/customerInvoice");
	}
	
	
	
	/**
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param timeZoneOffset 
	 * @throws Exception 
	 */
	@ResourceMapping("showInvoiceListURL")
	public void retrieveInvoiceList(Model model, ResourceRequest request,ResourceResponse response,
			@RequestParam(ChangeMgmtConstant.TIMEZNOFFSET) float timeZoneOffset) throws Exception
	{

		String METHOD_NAME = "retrieveInvoiceList";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		
		PortletSession session=request.getPortletSession();
		InvoiceListResult invoiceListResult = null;
		
		
		try{
			
			  InvoiceListContract	contract = ContractFactory.createInvoiceListContractforAR(request,ChangeMgmtConstant.CUSTOMER_INVOICE_FILTER_COLUMN);
			  
			 
			  session.setAttribute("downloadContract", contract);
			  invoiceListResult=SAPInvoiceCall(contract);
		
		}
		catch(Exception e)
		{			
			LOGGER.error("Exception occured while retrieving Invoice details "+e.getCause());
			
			
		}
		
		
		Locale locale = request.getLocale();
		
		XmlOutputGenerator xmlOutputGenerator = getXmlOutputGenerator(locale);
		
		
		
		Map<String,String> requestStatusLOVMap = getInvoiceStatusMap(locale);
			// Map added to parameters
		String outputXML= xmlOutputGenerator.convertInvoiceAPListToXML(invoiceListResult.getInvoice(),invoiceListResult.getInvoice().size(),timeZoneOffset,requestStatusLOVMap);
		
		
		
				
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
					
			out.print(outputXML);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.debug("IO Exception message" + e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param model 
	 * @param resourceRequest 
	 * @param resourceResponse 
	 */
	@ResourceMapping(value="accountsRecvblList")
	public void accountsRecvblList(Model model,ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		LOGGER.enter(this.getClass().getSimpleName(), METH_ACCNTSRCVLLIST);
		
		AccountReceivableListContract accountRcvblListContract = ContractFactory.getAccountRecievableListContract(resourceRequest);
		

		
		CrmSessionHandle crmSessionHandle = globalService
											.initCrmSessionHandle(PortalSessionUtil
											.getSiebelCrmSessionHandle(resourceRequest));
		accountRcvblListContract.setSessionHandle(crmSessionHandle);
		
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(accountRcvblListContract, LOGGER);
		LOGGER.info("end printing lex logger");
		
		
		long timeBeforeCall=System.currentTimeMillis();
		AccountCustomerReceivableListResult accountRcvblListResult = customerPaymentsService.retrieveAccountReceivableList(accountRcvblListContract);
		long timeAfterCall=System.currentTimeMillis();
		LOGGER.info("start printing lex logger");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CUSTOMERINVOICE_MSG_RETRIEVEACCOUNTRECEIVABLELIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,accountRcvblListContract);
		
		LOGGER.info("end printing lex loggger");
		
		List<AccountCustomerReceivable> accountsRcvableList = accountRcvblListResult.getAccountReceivableList();
		if(accountsRcvableList==null){
			accountsRcvableList=new ArrayList<AccountCustomerReceivable>();
			LOGGER.info("accountsPayblList.size()" + accountsRcvableList.size());
		}
	 
		
		LOGGER.debug("-------------Converting Account List to XML STARTS---------");
		
		XmlOutputGenerator xmlOutputGenerator=getXmlOutputGenerator(resourceResponse.getLocale());
		
		String content = xmlOutputGenerator.convertAccountsRcvblListToXML(accountsRcvableList,accountsRcvableList.size(), 0);
		
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
			e.getMessage();
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVEVENDORLIST);
	}
	/**
	 * 
	 * @param request 
	 * @param response 
	 * @param timeZoneOffset 
	 * @param downloadType 
	 */
	@ResourceMapping(value="download")
	public void downloadInvoiceList(ResourceRequest request,ResourceResponse response,
			@RequestParam(ChangeMgmtConstant.TIMEZNOFFSET) float timeZoneOffset,
			@RequestParam(ChangeMgmtConstant.DOWNLOADTYPE) String downloadType){
		
		
		LOGGER.enter(this.getClass().getSimpleName(), "downloadInvoiceList");
		
		String fileName=null;
		
		String fileHeader = DownloadFileLocalizationUtil
		.getLocalizedFileHeader(ChangeMgmtConstant.downloadFildeHeader,request.getLocale());
		PortletSession session=request.getPortletSession();
		LOGGER.debug(" fileHeader "+fileHeader);
		LOGGER.debug(" download type ="+downloadType);
		InvoiceListResult invoiceListResult=null;
		try{
			InvoiceListContract	contract =(InvoiceListContract)session.getAttribute("downloadContract");
			 invoiceListResult=SAPInvoiceCall(contract);
		}catch(Exception ex){
			List<Invoice> list=new ArrayList<Invoice>();
			invoiceListResult.setInvoice(list);
			LOGGER.error("Exception occured while getting invoice llist");
			ex.getMessage();
		}
		OutputStream responseOut=null;
		PrintWriter out=null;
		try{
			if(StringUtils.isNotBlank(downloadType) && downloadType.equalsIgnoreCase("PDF")){
				fileName= DownloadFileLocalizationUtil
				.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
						ChangeMgmtConstant.invoiceFileName,request.getLocale());
				LOGGER.debug(" in pdf generator");
				PdfPReportGenerator generator = new PdfPReportGenerator(
						fileHeader.split(","), ChangeMgmtConstant.invoiceListColumns,
						invoiceListResult.getInvoice());
				
				LOGGER.debug(" File name"+fileName);
				response.setProperty("Content-disposition", "attachment; filename=\""
						+ fileName+"\"");
				response.setContentType("application/pdf;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				responseOut = response.getPortletOutputStream();
				
				generator.generate(responseOut);
				responseOut.flush();
				
				
				
			}else{
				 fileName= DownloadFileLocalizationUtil
				.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
						ChangeMgmtConstant.invoiceFileName,request.getLocale());
				 
				 LOGGER.debug(" File name"+fileName);
				LOGGER.debug("in csv generator.");
				response.setProperty("Content-disposition", "attachment; filename=\""
						+ fileName+"\"");
					
				response.setContentType("text/csv;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				out = ResourceResponseUtil
						.getUTF8PrintWrtierWithBOM(response);
				out.print(DownloadFileUtil.fillInvoiceList(fileHeader,invoiceListResult.getInvoice(),timeZoneOffset,
						request.getLocale()));
			}
		}catch(Exception ex){
			LOGGER.error("error occured downloading");
			ex.getMessage();
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
			
			LOGGER.exit(this.getClass().getSimpleName(), "downloadInvoiceList");
		}	
	
		
	}
	/**
	 * 
	 * @param contract 
	 * @return InvoiceListResult 
	 * @throws Exception 
	 */
	private InvoiceListResult SAPInvoiceCall(InvoiceListContract contract)throws Exception{
		
			
			
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			LOGGER.info("end printing lex logger");
			
			LOGGER.debug("Before invoking retrieveInvoiceList");
			long timeBeforeCall=System.currentTimeMillis();
			
			
			InvoiceListResult invoiceListResult = invoiceService.retrieveInvoiceList(contract,ChangeMgmtConstant.CUSTOMER_INVOICE_FILTER_COLUMN);
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CUSTOMERINVOICE_MSG_RETRIEVEINVOICELIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SAP,contract);
			
			LOGGER.info("end printing lex loggger");
			LOGGER.debug("After invoking retrieveInvoiceList");
			
			
			return invoiceListResult;
			
	}
	
	/**
	 * Modified For Invoice Print Functionality for MPS ---- Invoice Grid
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printInvoiceList")
	public String showPrintDeviceListPage() throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), METH_SHOWPRINTLSTPG);
		LOGGER.exit(this.getClass().getSimpleName(), METH_SHOWPRINTLSTPG);
		return "customerInvoice/customerInvoiceListPrint";
	}
	/**
	 * 
	 * @param locale 
	 * @return Map 
	 * @throws Exception 
	 */
	public Map<String,String> getInvoiceStatusMap(Locale locale) throws Exception{
		
		 Map<String, String> requestStatusLOVMap =null;
			try {
				requestStatusLOVMap =  commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CUSTOMER_INVOICE_STATUS.getValue(),locale);						
			}catch(LGSDBException e){
				LOGGER.error("Unable to retrieve Status map");
				requestStatusLOVMap=new HashMap<String,String>();
				e.getMessage();
			}
			return requestStatusLOVMap;
		
	}
	
}
