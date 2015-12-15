/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: UploadHistoryController.java
 * Package     		: com.lexmark.portlet
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */
package com.lexmark.portlet;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AttachmentListResult;
import com.lexmark.result.AttachmentResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerOrderService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PortalSessionUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Wipro 
 * @version 2.1
 */
@Controller
@RequestMapping("VIEW")
public class UploadHistoryController {
	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(UploadHistoryController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String CLASS_NAME="UploadHistoryController.java";
	

	
	@Autowired
	private PartnerOrderService partnerOrderService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	
	/**
	 * @param request 
	 * @param model 
	 * @return string 
	 */
	@RequestMapping
	public String showHistoryPage(RenderRequest request,Model model){
		LOGGER.enter(CLASS_NAME, "showHistoryPage");
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		LOGGER.debug("httpReq.getParameter(LexmarkPPConstants.VENDORACCID_AVAIL)"+httpReq.getParameter(LexmarkPPConstants.VENDORACCID_AVAIL));
		if(httpReq.getParameter(LexmarkPPConstants.VENDORACCID_AVAIL)==null){
			model.addAttribute(LexmarkPPConstants.ACCOUNT_SELECTION_DONE,"false");			
		}else{
			model.addAttribute(LexmarkPPConstants.ACCOUNT_SELECTION_DONE,"true");
		}
		setLOVMapForUploadHistory(request,model);
		return "uploadHistory/uploadHistory";
	}
	
	/**
	 * @param resourceRequest 
	 * @param model 
	 * @param resourceResponse 
	 * @param timezoneOffset 
	 * @return String 
	 */
	@SuppressWarnings("unchecked")
	@ResourceMapping("uploadHistoryList")
	public String retrieveUploadHistory(ResourceRequest resourceRequest,Model model,
			ResourceResponse resourceResponse,@RequestParam("timezoneOffset")String timezoneOffset){
		LOGGER.enter(CLASS_NAME, "retrieveUploadHistory");
		
		Map<String,String> accountSelectionDetails=(Map<String,String>)resourceRequest.getPortletSession().getAttribute(LexmarkPPConstants.PARTNER_ACCOUNT_SELECTION_DETAILS, PortletSession.APPLICATION_SCOPE);
		resourceRequest.setAttribute(LexmarkPPConstants.VENDORACC_ID, accountSelectionDetails.get(LexmarkPPConstants.VENDORACC_ID));
		
		AttachmentContract contract=ContractFactory.getUploadHistoryAttachmentContract(resourceRequest);
		
		

		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(resourceRequest));
		contract.setSessionHandle(crmSessionHandle);
		
		
		
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		AttachmentListResult attachmentListResult=null; 	
		int size=0;
		List<Attachment> attachmentlist=null;
		try {
			
			 attachmentListResult=partnerOrderService.retrieveRequestAttachmentList(contract);
			 if(attachmentListResult.getAttachmentList()!=null){
				 size=attachmentListResult.getTotalCount();
				 attachmentlist=attachmentListResult.getAttachmentList();
			 }else{
				 attachmentlist=new ArrayList<Attachment>();
			 }
		} catch (Exception exception) {
			
			LOGGER.error("Error occured while retrieving upload history list"+exception.getMessage());
			
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}
	
		
		
		model.addAttribute(LexmarkPPConstants.TOTALCOUNT,size);
		
		model.addAttribute(LexmarkPPConstants.STARTPOS, contract.getStartRecordNumber());
		model.addAttribute("uploadDetailList", attachmentlist);
		model.addAttribute("timeZoneOffset", Float.valueOf(timezoneOffset));
		setLOVMapForUploadHistory(resourceRequest,model);
		resourceResponse.setContentType(LexmarkPPConstants.XMLCONTENT);
		return "uploadHistory/uploadHistoryListXML";
		
	}
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @param requestNumber 
	 * @param fileName 
	 */
	@SuppressWarnings("unchecked")
	@ResourceMapping("downloadErrorFile")
	public void downloadErrorFile(ResourceRequest resourceRequest,ResourceResponse resourceResponse,
			@RequestParam("rNo") String  requestNumber,@RequestParam("fName") String fileName){
		final String METHOD_NAME="downloadErrorFile";
		
		OutputStream out=null;
		InputStream inputStream=null;
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(resourceRequest));
		
		
		Map<String,String> accountSelectionDetails=(Map<String,String>)resourceRequest.getPortletSession().getAttribute(LexmarkPPConstants.PARTNER_ACCOUNT_SELECTION_DETAILS, PortletSession.APPLICATION_SCOPE);
		resourceRequest.setAttribute(LexmarkPPConstants.VENDORACC_ID, accountSelectionDetails.get(LexmarkPPConstants.VENDORACC_ID));
		
		AttachmentContract contract=ContractFactory.getUploadHistoryAttachmentContract(resourceRequest);
				
		contract.setRequestType(LexmarkPPConstants.ATTACHMENT_REQUEST_TYPE);
		contract.setIdentifier(requestNumber);
		contract.setUserFileName(fileName);
		
		contract.setSessionHandle(crmSessionHandle);
		
		
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract,LOGGER);
		LOGGER.info("end printing lex loggger");
		long timeBeforeCall=System.currentTimeMillis();
		
		try{
		
		AttachmentResult downldReslt = attachmentService.downloadAttachment(contract);
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_DOWNLOADATTACHMENT, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("start printing lex logger");
			LOGGER.logTime("** MPS PERFORMANCE TESTING Download Attachment ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
		}
		LOGGER.debug("downldReslt.getFileStream() : " + downldReslt.getFileStream()
		+ " downldReslt.getFileName() : " + downldReslt.getFileName());
		if(downldReslt.getFileStream() == null){
			LOGGER.error("Error occured while getting the inputstream ");
			throw new Exception("Error occured while getting the inputstream");
		}
		resourceResponse.setContentType(ControllerUtil.getContentTypeAccordingToFile(fileName));
		resourceResponse.setProperty("Content-disposition", "attachment; filename=\""
				+ fileName+"\"");
		 inputStream = downldReslt.getFileStream();
		 
		 out = resourceResponse.getPortletOutputStream();
		 
		 byte buf[]=new byte[1024];
		 int inputStreamBufferlen=inputStream.read(buf);
		 while(inputStreamBufferlen>0){
			 out.write(buf,0,inputStreamBufferlen);
			 inputStreamBufferlen=inputStream.read(buf);
		 }
		 
		 out.close();
		 
		 LOGGER.debug("\nFile is created...................................");
		 LOGGER.debug(" Going for deleting the temporary file created in Amind");
		 ControllerUtil.amindRemoveTemporaryAttachmentFile(ContractFactory.getDeleteAttachmentContract(downldReslt), attachmentService);
		 LOGGER.debug(" Ended deleting the temporary file created in Amind");
		
		}catch(Exception exception){
			LOGGER.error(exception.getMessage());
			resourceResponse.setContentType(ControllerUtil.getContentTypeAccordingToFile(fileName));
			resourceResponse.setProperty("Content-disposition", "attachment; filename=\""
					+ fileName+"\"");			
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
			
			
		}
		
				
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
	}
	
	/**
	 * @param request 
	 * @param model 
	 */
	private void setLOVMapForUploadHistory(PortletRequest request,Model model){
		//Added for retrieving upload history type map.
		PortletSession session=request.getPortletSession();
		Boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(session);
		Boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(session);
		String partnerType = ControllerUtil.getPartnerType(isIndirectPartner, isDirectPartner);
		Map<String, String> uploadHistoryTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.UPLOAD_HISTORY_TYPE.getValue(), partnerType,
				serviceRequestLocaleService, request.getLocale());
		model.addAttribute("uploadHistoryType", uploadHistoryTypeMap);
	}

}
