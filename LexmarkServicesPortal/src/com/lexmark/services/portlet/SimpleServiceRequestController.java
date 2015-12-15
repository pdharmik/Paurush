/**
 * Copyright@ 2012. This document has been created & written by 
 * Wipro technologies for Lexmark and this is copyright to Lexmark 
 *
 * File         	: SimpleServiceRequestController 
 * Package     		: com.lexmark.services.portlet 
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments 
 * ---------------------------------------------------------------------
 * Wipro 						 		              
 *
 */
package com.lexmark.services.portlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AttachmentResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.util.PerformanceTracker;


@Controller
@RequestMapping("VIEW")
public class SimpleServiceRequestController extends BaseController{
	private static LEXLogger logger = LEXLogger.getLEXLogger(SimpleServiceRequestController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static int maximumCount = 10;
	@Autowired
	private ServiceRequestService serviceRequestService;	
	@Autowired
	private SharedPortletController sharedPortletController;
    @Autowired
    private GlobalService  globalService;
    @Autowired
	public RequestTypeService requestTypeService;
    @Autowired
	private CommonController commonController;
	
    //Added for CI BRD 13-10-22
	/**. Holds AmindAttachmentService bean reference **/
	@Autowired
    public AttachmentService attachmentService;
	
    private static final String FILE_DELIMITER = ".";
    private static final String CLASS_NAME = "SimpleServiceRequestController";
    
	/**
	 * @param model   
	 * @param request  
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showServiceRequestPreviewPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		
		String gridId = "allRequestHistory";
		retrieveGridSetting(gridId,request,response,model);
		return "serviceRequest/serviceRequestPreview";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=serviceRequestDrillDown")
	public String showServiceRequestDrillDownPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		logger.debug("-------------serviceRequestDrillDown started---------");
		logger.info(request.getParameter("lightBox"));
		sharedPortletController.showServiceRequestDrillDownPage(request, response, model);
		return "serviceRequest/serviceRequestDrillDownLightBox";
	}
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="retrieveAssociatedServiceTicketList")
	public void retrieveAssociatedServiceTicketList(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{
		sharedPortletController.retrieveAssociatedServiceTicketList(request, response, model);
	}
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showNotificationDescriptionDetail")
	public String showNotificationDescriptionDetail(RenderRequest request, RenderResponse response, Model model)
			throws Exception { 
		return "serviceRequest/notificationDescriptionDetail";
	}
	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping
	public void retrieveServiceRequestPreviewList(ResourceRequest request, ResourceResponse response) throws Exception{
		logger.debug("------------- Step 1---retrieveServiceRequestPreviewList started---------["+System.nanoTime()+"]");
		float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)).floatValue();
		logger.debug("timezoneOffset---->"+timezoneOffset);
		RequestListContract contract = ContractFactory.getHistoryListContract(request, "ALL_REQUESTS",ChangeMgmtConstant.ALLHISTORYCOLUMNS);
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			contract.setIncrement(10);
			
			logger.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			logger.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, logger);
			logger.info("end printing lex loggger");
			

			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveServiceRequestList", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			
			Long startTime = System.currentTimeMillis();
			RequestListResult serviceRequestListResult=	requestTypeService.retrieveRequestList(contract);
			logger.logTime("** MPS PERFORMANCE TESTING RETRIEVE Request list ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEREQUESTLIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			
			
			PerformanceTracker.endTracking(lexmarkTran);
			int totalCount = (serviceRequestListResult.getTotalCount()) > maximumCount ? maximumCount : (serviceRequestListResult.getTotalCount());
			
			List<ServiceRequest>  serviceRequestList = serviceRequestListResult.getRequestList();
						
			String xmlContent="";
			
			if(serviceRequestList!=null && !serviceRequestList.isEmpty()){
				serviceRequestList=serviceRequestList.subList(0, totalCount);
				 xmlContent = getXmlOutputGenerator(request.getLocale()).buildXMLForAllRequestType(serviceRequestList,totalCount, contract.getStartRecordNumber(), timezoneOffset);
			}else{
				xmlContent=getXmlOutputGenerator(request.getLocale()).generateEmptyXML();
			}
			
			
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(xmlContent);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("------------- Step 1---retrieveServiceRequestPreviewList end---------["+System.nanoTime()+"]");
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showSRDetailPage")
	public String showSRDetailPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		sharedPortletController.showServiceRequestDrillDownPage(request, response, model);
		return "serviceRequest/serviceRequestDrillDown";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showServiceRequestDrillDownPrintPage")
	public String showServiceRequestDrillDownPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "serviceRequest/serviceRequestDrillDownPrint";
	}
	
	/**
	 * @param request  
	 * @param response 
	 * @param model 
	 * @throws IOException 
	 */
	@RequestMapping(params="action=viewAllServiceRequests")
    public void viewAllServiceRequests(ActionRequest request, ActionResponse response, Model model) throws IOException
    {
        
        response.sendRedirect(request.getParameter("friendlyURL"));
    }
	//Added for CI BRD13-10-22--STARTS
	
	/**
	 * @param request  
	 * @param response  
	 * @param model  
	 * @throws Exception  
	 */
	
	@ResourceMapping("downloadAttachment")
	public void downloadAttachment(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		String METHOD_NAME = "downloadAttachment";
		logger.enter(CLASS_NAME, METHOD_NAME );	
		StringBuffer dispAttchmntName = new StringBuffer(request.getParameter(ChangeMgmtConstant.DISP_ATTACHMENT_NAME));
		StringBuffer attchmntName = new StringBuffer(request.getParameter(ChangeMgmtConstant.ATTACHMENT_NAME));
		StringBuffer fileExtension = new StringBuffer(request.getParameter(ChangeMgmtConstant.FILE_EXTENSION));
		String identifier = request.getParameter(ChangeMgmtConstant.IDENTIFIER);
		
		if(!fileExtension.toString().equalsIgnoreCase("URL")){
			attchmntName.append(FILE_DELIMITER);
			attchmntName.append(fileExtension);
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			InputStream fileStream = getInputStreamForAttachment(attchmntName.toString(), identifier, crmSessionHandle); 
			try{
				if(fileStream!=null){
					response.setProperty("Content-disposition", "attachment; filename=\"" + dispAttchmntName + FILE_DELIMITER + fileExtension +"\"");
					if(attchmntName.indexOf("jpg") > 0) {
						response.setContentType("image/jpg");
			        }else if(attchmntName.indexOf("gif") > 0) {
			        	response.setContentType("image/gif");
			        }else if(attchmntName.indexOf("pdf") > 0) {
			        	response.setContentType("application/pdf");
			        	
			        }else if(attchmntName.indexOf("html") > 0) {
			        	response.setContentType("text/html");
			        }else if(attchmntName.indexOf("zip") > 0) {
			        	response.setContentType("application/zip");
			        }else if(attchmntName.indexOf("txt") > 0 ){
			        	response.setContentType("text/plain");
			        }else if(attchmntName.indexOf("xls")>0){
			        	response.setContentType("application/vnd.ms-excel");
			        }else if(attchmntName.indexOf("pdf")>0){
			        	response.setContentType("application/pdf");
			        }else if(attchmntName.indexOf("doc")>0){
			        	response.setContentType("application/msword");
			        }else if(attchmntName.indexOf("xlsx")>0){
			        	response.setContentType("application/vnd.ms-excel");
			        }else if(attchmntName.indexOf("docx")>0){
			        	response.setContentType("application/msword");
			        }else if(attchmntName.indexOf("csv")>0){
			        	response.setContentType("application/vnd.ms-excel");
			        }else if(attchmntName.indexOf("vsd")>0){
			        	response.setContentType("application/x-visio");
			        }
					
					 InputStream inputStream = fileStream;
					 OutputStream out = response.getPortletOutputStream();
					 byte buf[]=new byte[1024];
					 int inputStreamBufferlen=inputStream.read(buf);
					 while(inputStreamBufferlen>0){
						 out.write(buf,0,inputStreamBufferlen);
						 inputStreamBufferlen=inputStream.read(buf);
					 }
					 out.close();
					 inputStream.close();
				}
				else{
					response.setProperty("Content-disposition", "attachment; filename=Error_in_file_download.doc");
					response.setContentType("application/msword");
					OutputStream out = response.getPortletOutputStream();
					byte buf[]=("The file '"+ dispAttchmntName + FILE_DELIMITER + fileExtension + "' could not downloaded.").getBytes();
					out.write(buf);
					out.flush();
					out.close();
				}

					
			}catch (IOException e){
				logger.debug("HistoryController.showRequestDetails exception : " + e.getMessage());
			}
			catch(Exception exception){
				logger.debug("HistoryController.showRequestDetails exception : " + exception.getMessage());
			}
		}
		
		logger.exit("SimpleServiceRequestController", METHOD_NAME );

	}
	
	/**. Gets input stream for download attachment in details page.
	 * 
	 * @param attchmntName String  
	 * @param identifier String 
	 * @param crmSessionHandle CrmSessionHandle 
	 * @return InputStream  
	 */
	private InputStream getInputStreamForAttachment(String attchmntName, String identifier,
			CrmSessionHandle crmSessionHandle){
		String METHOD_NAME = "getInputStreamForAttachment";
		logger.enter(CLASS_NAME, METHOD_NAME );
		try{
				logger.debug("AttchmntName : " + attchmntName + " Identifier : " +identifier);
				
				AttachmentContract attContract = new AttachmentContract();
				attContract.setUserFileName(attchmntName);
				attContract.setRequestType(ChangeMgmtConstant.ATTACHMENTREQTYPE);
				attContract.setIdentifier(identifier);
				attContract.setSessionHandle(crmSessionHandle);
				logger.info("start printing lex logger");
				ObjectDebugUtil.printObjectContent(attContract,logger);
				logger.info("end printing lex loggger");
				long timeBeforeCall=System.currentTimeMillis();
				AttachmentResult downldReslt = attachmentService.downloadAttachment(attContract);
				logger.info("start printing lex logger");
				logger.logTime("** MPS PERFORMANCE TESTING Download Attachment ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
				logger.info("end printing lex loggger");
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_DOWNLOADATTACHMENT, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,attContract);

				logger.debug("downldReslt.getFileStream() : " + downldReslt.getFileStream()
				+ " downldReslt.getFileName() : " + downldReslt.getFileName());
				if(downldReslt.getFileStream() == null){
					throw new Exception();
				}
				else{
					logger.exit(CLASS_NAME, METHOD_NAME );
					return downldReslt.getFileStream();
				}
			
		}catch (Exception e) {
			logger.debug("Exception occurred in attachment retrieval ::: "+ e.getMessage());
			return null;
		}
		
	}
	//Added for CI BRD13-10-22--ENDS
}
	