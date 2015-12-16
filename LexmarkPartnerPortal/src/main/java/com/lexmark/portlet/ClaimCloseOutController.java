/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ClaimCloseOutController
 * Package     		: com.lexmark.portlet
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.portlet;

import com.lexmark.service.api.AttachmentService;
import com.lexmark.contract.AttachmentContract;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.lexmark.service.api.CrmSessionHandle;
import java.util.LinkedHashMap;

import java.util.HashMap;
import java.util.List;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ActivityDebriefSubmitContract;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.ClaimDebriefSubmitContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.form.ClaimDebriefForm;
import com.lexmark.form.ClaimRequestConfirmationForm;
import com.lexmark.result.ActivityDebriefSubmitResult;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.ClaimDebriefSubmitResult;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.CollectionSorter;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.ExceptionUtil;
import com.lexmark.util.MailUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XMLEncodeUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.webservice.api.ClaimService;
import com.lexmark.webservice.api.RequestService;
import javax.portlet.PortletSession;
import org.apache.commons.fileupload.FileItem;
import com.lexmark.domain.Attachment;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.util.PerformanceUtil;

@Controller
@RequestMapping("VIEW")
public class ClaimCloseOutController {
	@Autowired
	private PartnerRequestsService partnerRequestsService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ClaimService claimWebService;
	@Autowired
	private RequestService requestWebService;

	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private GridSettingController gridSettingController;
	
	private static Logger logger = LogManager.getLogger(ClaimCloseOutController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	
	
	// Getting the File Path from attachment properties file
	static ResourceBundle bundle = ResourceBundle.getBundle("attachment");
	private static String path=bundle.getString("ClaimFileDestination");
	
		
	private static String attachmentSize=bundle.getString("AttachmentSize");
	private static String strFileSize=bundle.getString("MaxFileSize");

	private static String maxFileSize_Message=bundle.getString("maxFileSizeMessage");
	private static String duplicateFile_Message=bundle.getString("duplicateFileMessage");
	private static String maxNumberOfFiles_Message=bundle.getString("maxNumberOfFilesMessage");

	// File Type Validation
	private static String attachmentFileType=bundle.getString("attachmentFileType");
	private static String attachmentFileTypeMessage=bundle.getString("attachmentFileTypeMessage");

	
	
	
	private Map<String, CommonsMultipartFile> fileAttachmentMap = new LinkedHashMap<String, CommonsMultipartFile>();	// for CI5 file attachment
	private Map<String, AttachmentFile> fileMap = new LinkedHashMap<String, AttachmentFile>();	// for CI5 file attachment
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=showCloseOutClaimPage")
	public String showCloseOutClaimPage (Model model, RenderRequest request, RenderResponse response) throws Exception{
		logger.debug("[START] showCloseOutClaimPage"); 
		
		ClaimDetailResult claimDetailResult = null;
		ActivityDetailResult activityDetailResult = null;
		Activity claimDetail = null;
		
		//added by ragesree --2098 begin//
		String serviceType = request.getParameter("serviceType");
		logger.debug("---------------------show claim clse out----------------"+serviceType);
		
		//retrieve additional Parts grids order 
		List<UserGridSettingResult> gridSettingList = new ArrayList<UserGridSettingResult>();
		UserGridSettingResult gridSettingResult= new UserGridSettingResult(); 
		gridSettingController.retrieveGridSetting("gridAdditionalParts", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		logger.debug("gridAdditionalParts cols order "+gridSettingResult.getColsOrder());
		gridSettingList.add(gridSettingResult);
		
		//retrieve Parts and tools grids order 		 
		gridSettingController.retrieveGridSetting("gridCCVPartDebrief", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		logger.debug("gridAdditionalParts cols order "+gridSettingResult.getColsOrder());
		gridSettingList.add(gridSettingResult);
		
		model.addAttribute("gridSettingList",gridSettingList);
		
		//For activity
		if(serviceType!=null && (serviceType.equals("Onsite Exchange"))){
			
			ActivityDetailContract activityDetailContract = ContractFactory.createActivityDetailContract(request);
			activityDetailContract.setDebriefFlag(true);
			ObjectDebugUtil.printObjectContent(activityDetailContract, logger);
			Long startTime = System.currentTimeMillis();
			activityDetailResult = partnerRequestsService.retrieveActivityDetail(activityDetailContract);
			for(int listOfDebriefpart = 0;listOfDebriefpart <claimDetail.getDebrief().getPartDebriefList().size();listOfDebriefpart++){
				claimDetail.getDebrief().getPartDebriefList().get(listOfDebriefpart).setPartName(XMLEncodeUtil.escapeXML(claimDetail.getDebrief().getPartDebriefList().get(listOfDebriefpart).getPartName()));
			}
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_ACTIVITYDETAILS, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,activityDetailContract);
			
			if(activityDetailResult==null) {
				throw new IllegalArgumentException("Failed to open Requests Detail page: <br/> claimDetailResult is null");
			}
			if(activityDetailResult.getActivity() == null){
				throw new IllegalArgumentException("Failed to open Requests Detail page: <br/> Activity is null");
			}
			if(activityDetailResult.getActivity().getServiceRequest() == null){
				throw new IllegalArgumentException("Failed to open Requests Detail page: <br/> ServiceRequest is null");
			}
			if(activityDetailResult.getActivity().getServiceRequest().getAsset() == null){
				throw new IllegalArgumentException("Failed to open Requests Detail page: <br/> Asset is null");
			}
			claimDetail = activityDetailResult.getActivity();
			claimDetail.setExchangeFlag(true);
			claimDetail.setServiceType(serviceType);
		}else{//For Claim
			ClaimDetailContract contract = ContractFactory.createClaimDetailContract(request);	
			contract.setDebriefFlag(true);
			ObjectDebugUtil.printObjectContent(contract, logger);	
			Long startTime = System.currentTimeMillis();
			claimDetailResult = partnerRequestsService.retrieveClaimDetail(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.PARTNER_CLAIM_DETAIL, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			if(claimDetailResult==null) {
				throw new IllegalArgumentException("Failed to open Claim Debrief page: <br/> claimDetailResult is null");
			}
			if(claimDetailResult.getActivity() == null){
				throw new IllegalArgumentException("Failed to open Claim Debrief page: <br/> Activity is null");
			}
			if(claimDetailResult.getActivity().getServiceRequest() == null){
				throw new IllegalArgumentException("Failed to open Claim Debrief page: <br/> ServiceRequest is null");
			}
			if(claimDetailResult.getActivity().getServiceRequest().getAsset() == null){
				throw new IllegalArgumentException("Failed to open Claim Debrief page: <br/> Asset is null");
			}
			claimDetail = claimDetailResult.getActivity();
			for(int listOfDebriefpart = 0;listOfDebriefpart <claimDetail.getDebrief().getPartDebriefList().size();listOfDebriefpart++){
				claimDetail.getDebrief().getPartDebriefList().get(listOfDebriefpart).setPartName(XMLEncodeUtil.escapeXML(claimDetail.getDebrief().getPartDebriefList().get(listOfDebriefpart).getPartName()));
			}
			// show partnerAccount.address, and mapped to Activity.shipToAddress when submitted.
			claimDetail.setShipToAddress(claimDetail.getPartnerAccount().getAddress());
			claimDetail.setServiceType(serviceType); // added to get service type if srvice type is not Onsite Exchange
		}
		
		
		
		String productTLI = claimDetail.getServiceRequest().getAsset().getProductTLI();
		if(!StringUtil.isStringEmpty(productTLI)){
			claimDetail.getServiceRequest().getAsset().setProductImageURL(ControllerUtil.retrieveProductImageUrl(
					productImageService,productTLI));
		}
		Locale locale = request.getLocale();
		ControllerUtil.localizeActivity(claimDetail, serviceRequestLocaleService, locale);
		
		PortletSession session = request.getPortletSession();
		if(session!=null&& session.getAttribute("attachmentList")!=null){
			session.removeAttribute("attachmentList");
		}
		
		logger.debug("claim detail activity ---------- actual failure code is ---------- "+claimDetail.getActualFailureCode().getValue());
		ClaimDebriefForm claimDebriefForm = createClaimDebriefForm(claimDetail,locale,request);
		claimDebriefForm.setFormattedServiceRequestedDate(DateUtil.convertToLocaleSpecificDateTime(claimDetail.getDebrief().getServiceRequestedDate(),locale));
		claimDebriefForm.setFormattedServiceStartDate(DateUtil.convertToLocaleSpecificDateTime(claimDetail.getDebrief().getServiceStartDate(),locale));
		claimDebriefForm.setFormattedServiceEndDate(DateUtil.convertToLocaleSpecificDateTime(claimDetail.getDebrief().getServiceEndDate(),locale));		
		claimDebriefForm.refreshSubmitToken(request);
		String timezoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		ResourceURL resOutPutURL = response.createResourceURL();
        resOutPutURL.setResourceID("outPutFileURL");
        model.addAttribute("outPutFileURL", resOutPutURL.toString());
		model.addAttribute("claimDebriefForm", claimDebriefForm);
		model.addAttribute("attachmentFormDisplay", claimDebriefForm);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET,timezoneOffset);
		logger.debug("[END] showCloseOutClaimPage"); 
		return "claims/claimDebriefView";
		
	}
	

	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("getAttachmentsClose")
	public void getCustomersForReport(ResourceRequest request, ResourceResponse response) 
	throws Exception {
		logger.debug("########getting close xml ");
		String content = ControllerUtil.getAttachmentXML(fileAttachmentMap);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param claimFormForAttachment 
	 * @param fileName 
	 * @throws Exception 
	 */
	@ActionMapping(params = "action=removeCloseAttachmentFile")
	public void removeDocument(Model model,ActionRequest request, ActionResponse response, 
			@ModelAttribute("claimDebriefForm") ClaimDebriefForm 
			claimFormForAttachment,  @RequestParam(value="fileName") String fileName) throws Exception {

		fileMap.remove(fileName);
		claimFormForAttachment.setFileMap(fileMap);
		model.addAttribute("claimDebriefForm", claimFormForAttachment);
		response.setRenderParameter("action", "removeCloseAttachmentFileRender");
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=removeCloseAttachmentFileRender")
	public String removeDocument(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		logger.debug("Inside removeDocument RenderRequest");
		return "claims/claimDebriefView";
	}
//	end of addition for CI5 attachment feature
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 */
	@RequestMapping(params="action=cancelClaimDebrief")
	public void cancelClaimUpdate(Model model, 
								  ActionRequest request, 
								  ActionResponse response){
		logger.debug("[START] cancelClaimDebrief");
		if(request.getParameter("fromPage").equalsIgnoreCase("requestListView")){
			logger.debug("[fromPage] requestList");
		}
		if(request.getParameter("fromPage").equalsIgnoreCase("claimDetailView")){
			logger.debug("[fromPage] claimDetailView");
			response.setRenderParameter("serviceRequestId" , request.getParameter("serviceRequestId"));
			response.setRenderParameter("activityId", request.getParameter("activityId"));
			response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
			response.setRenderParameter("action", "retrieveClaimDetail");
		}
		logger.debug("[END] cancelClaimDebrief");
	}

	/**
	 * @param claimDebriefForm 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=submitClaimDebriefs")
	public void submitClaimDebrief(@ModelAttribute("claimDebriefForm") ClaimDebriefForm claimDebriefForm,
								   Model model, 
			  					   ActionRequest request, 
			  					   ActionResponse response) throws Exception{
		
		logger.debug("[START] submitClaimDebriefs");
		Activity claim =  transformClaimDebriefForm(request, claimDebriefForm);
		logger.debug("Problem Code Level  is ------------------- "+claim.getDebrief().getActualFailureCode().getValue());
		logger.debug("The status is-->>>"+request.getParameter("debriefStatus"));
		if(PortalSessionUtil.isDuplicatedSubmit(request, claimDebriefForm)){
			if(claimDebriefForm.getFromPage().equalsIgnoreCase("claimDetailView")){
				response.setRenderParameter("serviceRequestId" , claim.getServiceRequest().getId());
				response.setRenderParameter("activityId", claim.getActivityId());
				response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(claimDebriefForm.getTimezoneOffset()));
				response.setRenderParameter("action", "retrieveClaimDetail");
			}
			return;
		}
		ClaimDebriefSubmitResult claimDebriefSubmitResult = null;
		String messageCode = null;
		ActivityDebriefSubmitResult activityDebriefSubmitResult = null; //added for 2098
		
		logger.debug("error code values are ::::::::::::::::::::: "+claim.getActualFailureCode().getValue());
		claim.getDebrief().getActualFailureCode().setValue(claim.getActualFailureCode().getValue());
		
		logger.debug("Full Problem level are =============== "+claim.getDebrief().getActualFailureCode().getValue());
		
		
		try{
			if(claim.getServiceType()!=null && (claim.getServiceType()=="Onsite Exchange") ){
				ActivityDebriefSubmitContract contract = ContractFactory.createActivityDebriefSubmitContract(claim);
				Long startTime = System.currentTimeMillis();
				activityDebriefSubmitResult  = requestWebService.submitActivityDebrief(contract);
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_SUBMITACTIVITYDEBRIEF, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			}else{
				logger.debug("Inside ELSE");
				ClaimDebriefSubmitContract contract = ContractFactory.createClaimDebriefSubmitContract(claim);
				contract.setDebriefStatus(request.getParameter("debriefStatus"));
				Long startTime = System.currentTimeMillis();
				claimDebriefSubmitResult = claimWebService.submitClaimDebrief(contract);
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_SUBMITCLAIMDEBRIEF, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			}
			logger.debug("------------------claimresult-----------"+claimDebriefSubmitResult);
			logger.debug("------------------requestresult-----------"+activityDebriefSubmitResult);

			
			//calling aMind method..
			//Get all attachment names
			PortletSession session = request.getPortletSession();
			
			List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
			
			if(attachmentList == null) {
				attachmentList = new ArrayList<Attachment>();
				logger.debug("--------------------- We dont have anything as attachment NULL !!!!---------------------------");
			}else{
				if(attachmentList.size()>0){
			for(int i=0;i<attachmentList.size();i++){
				logger.debug("---------------- Printing attachment name --------------");
				logger.debug("----------"+attachmentList.get(i).getAttachmentName());
			}
			
			
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			AttachmentContract attachmentContract = new AttachmentContract();
			attachmentContract.setSessionHandle(crmSessionHandle);
			logger.debug("------------------------------- activityid we are sending ----------------------"+claimDebriefForm.getActivity().getActivityId());
			attachmentContract.setIdentifier(claimDebriefForm.getActivity().getActivityId());
			attachmentContract.setRequestType("Claim Update");
			attachmentContract.setAttachments(attachmentList);
			logger.debug("----------------- crmSessionHandle value is "+crmSessionHandle);
			
			logger.debug("-------------------------- Calling attachment service ----------------------");
			
			Long startTime = System.currentTimeMillis();
			attachmentService.uploadAttachments(attachmentContract);
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,attachmentContract);
			
			logger.debug("-------------------------- Amind method call done ---------------------------");
			}
				else{
					logger.debug("--------------------- We dont have anything as attachment List Size is Zero !!!!---------------------------");
				}
			}	
			
			if(session!=null&& session.getAttribute("attachmentList")!=null){
				session.removeAttribute("attachmentList");
				}
			
			logger.debug("[END] submitClaimUpdate");
		}catch(Exception e){
			messageCode = "exception.siebel.closeOutClaimException";
			String errorMessage = ExceptionUtil.getLocalizedExceptionMessage(messageCode, request.getLocale(),e);
			MailUtil.sendEmail("shiva.juluru@perficient.com", "Error while Closing out Claim", errorMessage, false);
			MailUtil.sendEmail("dan.wellborn@perficient.com", "Error while Closing out Claim", errorMessage, false);
			
			ServiceStatusUtil.checkServiceStatus(model, "exception.siebel.closeOutClaimException", request.getLocale(), true);
			response.setRenderParameter("serviceRequestId" , claim.getServiceRequest().getId());
			response.setRenderParameter("activityId", claim.getActivityId());
			response.setRenderParameter("serviceType", claim.getServiceType()); //added for 2098
			response.setRenderParameter("fromPage",claimDebriefForm.getFromPage());
			response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(claimDebriefForm.getTimezoneOffset()));
			response.setRenderParameter("action", "showCloseOutClaimPage");
			return;
		}
		
		if(claimDebriefSubmitResult != null && claimDebriefSubmitResult.isSuccess()){
			
			messageCode = "message.claim.closeOutDraftSaved";  //added
			if(claimDebriefForm.getFromPage().equalsIgnoreCase("requestListView")){
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			}
			if(claimDebriefForm.getFromPage().equalsIgnoreCase("claimDetailView")){
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
				response.setRenderParameter("serviceRequestId" , claim.getServiceRequest().getId());
				response.setRenderParameter("activityId", claim.getActivityId());
				response.setRenderParameter("serviceType", claim.getServiceType()); //added for 2098
				response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(claimDebriefForm.getTimezoneOffset()));
				response.setRenderParameter("action", "retrieveClaimDetail");
			}
		}else if(activityDebriefSubmitResult != null && activityDebriefSubmitResult.getSuccess()){
			messageCode = "message.claim.closeOutSuccess";
			if(claimDebriefForm.getFromPage().equalsIgnoreCase("requestListView")){
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			}
			if(claimDebriefForm.getFromPage().equalsIgnoreCase("claimDetailView")){
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
				response.setRenderParameter("serviceRequestId" , claim.getServiceRequest().getId());
				response.setRenderParameter("activityId", claim.getActivityId());
				response.setRenderParameter("serviceType", claim.getServiceType()); //added for 2098
				response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(claimDebriefForm.getTimezoneOffset()));
				response.setRenderParameter("action", "retrieveClaimDetail");
			}
		}else{
			messageCode = "exception.submitCloseOutClaimFailed";
			ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			response.setRenderParameter("serviceRequestId" , claim.getServiceRequest().getId());
			response.setRenderParameter("activityId", claim.getActivityId());
			response.setRenderParameter("serviceType", claim.getServiceType());
			response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(claimDebriefForm.getTimezoneOffset()));
			response.setRenderParameter("action", "showCloseOutClaimPage");
			response.setRenderParameter("fromPage",claimDebriefForm.getFromPage());
		}
			
		logger.debug("[END] submitClaimDebriefs"); 
	}

	
	
	/**
	 * @param claimDetail 
	 * @param locale 
	 * @param request 
	 * @return ClaimDebriefForm 
	 * @throws Exception 
	 */
	private ClaimDebriefForm createClaimDebriefForm(Activity claimDetail, Locale locale,RenderRequest request) throws Exception{
		logger.debug("[START] createClaimDebriefForm"); 
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		ClaimDebriefForm claimDebriefForm = new ClaimDebriefForm();
		claimDebriefForm.setActivity(claimDetail);
		claimDebriefForm.setActivityNoteListXML(xmlOutputGenerator.convertActivityNoteListToXMLForEdit(claimDetail.getActivityNoteList(), 
				PortalSessionUtil.getContactId(request.getPortletSession())));
		claimDebriefForm.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));

			Map<String, String> tempMap = null;
			
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), null, globalService, serviceRequestLocaleService, locale);
			logger.debug("----------------- Problem Code Map is --------------------- "+tempMap);
			claimDebriefForm.setProblemCodeMap(tempMap); 
			
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue(), null, globalService, serviceRequestLocaleService, locale);
			logger.debug("----------------- PARTNER_RESOLUTION_CODE is --------------------- "+tempMap);
			
			// adding to show only new values for resolution code start  ========================================
			SiebelLOVListContract lovListcontract = ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue(), null);
			SiebelLOVListResult listResult;
			listResult = globalService.retrieveSiebelLOVList(lovListcontract);
			/*for(int i=0; i<listResult.getLovList().size();i++){
				logger.debug(i+"th value is "+listResult.getLovList().get(i).getValue());
			}*/
			
			// retrieve map from db start
			LocalizedSiebelLOVListContract localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_RESOLUTION_CODE.getValue(), null, locale);
			LocalizedSiebelLOVListResult localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
			Map<String, String> dbLOVMap = new HashMap<String, String>();
			if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
				for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
					dbLOVMap.put(localizedLOV.getValue(), localizedLOV.getName());
				}
			}
			//logger.debug("db lov map for new resolution code is "+dbLOVMap);
			Map<String, String> resolutionCodeMap = new HashMap<String, String>();
			for(int i=0; i<listResult.getLovList().size();i++){
				//logger.debug(i+"th value is "+listResult.getLovList().get(i));
				String key = listResult.getLovList().get(i).getValue();
				String value = dbLOVMap.get(key);
				if(null != value && !value.toCharArray().equals("")){
					resolutionCodeMap.put(key,value);
				}
			}
			
			
			
			// retrieve map from db end
			// adding to show only new values for resolution code end ===============================================
			
			claimDebriefForm.setResolutionCodeMap(resolutionCodeMap);
			
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_1.getValue(), null, globalService, serviceRequestLocaleService, locale);
			claimDebriefForm.setErrorCode1List(tempMap);
			
			
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_ADDITIONAL_PAYMENT_TYPE.getValue(), null, globalService, serviceRequestLocaleService, locale);
			claimDebriefForm.setPaymentTypes(tempMap);
			
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_DEVICE_CONDITION.getValue(), null, globalService, serviceRequestLocaleService, locale);
			claimDebriefForm.setWorkingConditionMap(tempMap);
			logger.debug("--------------------2098----------------"+tempMap);
			
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(), null, globalService, serviceRequestLocaleService, locale);
			
			// adding to show only new values for part status code start  ========================================
						lovListcontract = ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(), null);						 
						listResult = globalService.retrieveSiebelLOVList(lovListcontract);
						/*for(int i=0; i<listResult.getLovList().size();i++){
							logger.debug(i+"th value is "+listResult.getLovList().get(i).getValue());
						}*/
						
						// retrieve map from db start
						 localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_PART_STATUS.getValue(), null, locale);
						 localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
						//Map<String, String> dbLOVMap = new HashMap<String, String>();
						if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
							for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
								dbLOVMap.put(localizedLOV.getValue(), localizedLOV.getName());
							}
						}
						//logger.debug("db lov map for new part status code is "+dbLOVMap);
						Map<String, String> partStatusMap = new HashMap<String, String>();
						for(int i=0; i<listResult.getLovList().size();i++){
							//logger.debug(i+"th value is "+listResult.getLovList().get(i));
							String key = listResult.getLovList().get(i).getValue();
							String value = dbLOVMap.get(key);
							if(null != value && !value.toCharArray().equals("")){
								partStatusMap.put(key,value);
							}
						}
						
						
						
						// retrieve map from db end
						// adding to show only new values for part status code end ===============================================
			
			claimDebriefForm.setPartStatusList(partStatusMap);
			
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, globalService, serviceRequestLocaleService, locale);
			claimDebriefForm.setCarrierList(tempMap);
			
			//added for 2098 start
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_TRAVEL_UNIT_OF_MEASURE.getValue(), null, globalService, serviceRequestLocaleService, locale);
			claimDebriefForm.setTravelUnitOfMeasureMap(tempMap);
			
			
			
			String serviceType=claimDetail.getServiceType();
			logger.debug("----------------SErVICE TYPE-------------"+serviceType);
			if(serviceType!=null ){
				if("Onsite Exchange".equals(serviceType)){
					claimDebriefForm.setRemoveDeviceSectionFlag(true);
					claimDebriefForm.setNetworkConnectedFlag(false);
					
					
					Map<String,Asset> orderedAssetMap = new HashMap<String, Asset>();
					orderedAssetMap.put(claimDetail.getServiceRequest().getAsset().getSerialNumber(), claimDetail.getServiceRequest().getAsset());
					List<PartLineItem> orderPartList = claimDetail.getOrderPartList();
					if(orderPartList != null){
						for(PartLineItem partLineItem : orderPartList){
							Asset asset = new Asset();
							asset.setSerialNumber(partLineItem.getSerialNumber());
							asset.setModelNumber(partLineItem.getModelNumber());
							orderedAssetMap.put(partLineItem.getSerialNumber(), asset);
						}
					}
					List<Asset> assetList = new ArrayList<Asset>(orderedAssetMap.values());
					CollectionSorter sorter = new CollectionSorter();
					String sortCriteria = "serialNumber:" + CollectionSorter.SORT_ASCENDING;
					claimDebriefForm.setAssetList(sorter.sort(assetList,sortCriteria));
				}
				
			}

			
			
			AccountContact technician = claimDetail.getTechnician();
			if(technician != null && StringUtil.isStringEmpty(technician.getContactId()) && (!StringUtil.isStringEmpty(technician.getFirstName()) || !StringUtil.isStringEmpty(technician.getLastName()))){
				technician.setNewContactFlag(true);
			}
			
			if(claimDetail.getPartnerAccount() != null){
				claimDebriefForm.setTechnicianList(ControllerUtil.retrieveTechnicianListSorted(claimDetail.getPartnerAccount().getAccountId(),partnerRequestsService));
			}
			claimDebriefForm.setAdditionalPaymentRequestListXML(xmlOutputGenerator.convertAdditionalPaymentListToXMLForEdit(
					claimDetail.getAdditionalPaymentRequestList(),claimDebriefForm.getPaymentTypes()));
			claimDebriefForm.setFromPage(request.getParameter("fromPage"));
			if (PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(request.getPortletSession())) {
				claimDebriefForm.setShowAdditionalPaymentRequestListFlag(true);
			} else {
				claimDebriefForm.setShowAdditionalPaymentRequestListFlag(false);
			}
		logger.debug("[END] createClaimDebriefForm"); 
		return claimDebriefForm;
	}
	
	/**
	 * @param errorCode1 
	 * @param errorCode2Value 
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("retrieveErrorCode2")
	public void retrieveErrorCode2ByCode1(@RequestParam("errorCode1") String errorCode1,@RequestParam("errorCode2Value") String errorCode2Value,
			ResourceRequest request, ResourceResponse response, Model model) throws Exception{
		StringBuilder errorCode2 = new StringBuilder();
		if (!StringUtil.isStringEmpty(errorCode1)) {
			try {
				Map<String, String> errorCode2Map = null;
				errorCode2Map = ControllerUtil.retrieveLocalizedLOVMapForSelection(
						SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_2.getValue(), errorCode1, globalService, serviceRequestLocaleService, request.getLocale());
				if (errorCode2Map != null) {
					for (Map.Entry<String, String> entry : errorCode2Map.entrySet()) {
						if (entry.getKey().equals(errorCode2Value)) {
							errorCode2.append(entry.getKey()).append("=").append(entry.getValue()).append("=").append(errorCode2Value).append(",");
						}else {
							errorCode2.append(entry.getKey()).append("=").append(entry.getValue()).append(",");
						}
					}
				}
			} catch (Exception e) {
				logger.debug("Exception"+e.getMessage()); 
				errorCode2 = new StringBuilder();
			}
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(errorCode2.toString());
			out.flush();
			out.close();
		}
	}
	
	/**
	 * @param request 
	 * @param claimDebriefForm 
	 * @return Activity 
	 * @throws Exception 
	 */
	private Activity transformClaimDebriefForm(ActionRequest request,ClaimDebriefForm claimDebriefForm) throws Exception{
		logger.debug("[START] transformClaimDebriefForm"); 
		Locale locale = request.getLocale();
		Activity claim = claimDebriefForm.getActivity();
		String serviceEndDate = claimDebriefForm.getServiceEndDate();
		String serviceStartDate = claimDebriefForm.getServiceStartDate();
		String serviceRequestedDate = claimDebriefForm.getDateServicedRequested();
		logger.debug("serviceRequestedDate---->>>>"+serviceRequestedDate);
		claim.getDebrief().setServiceProviderReferenceNumber(claim.getServiceProviderReferenceNumber());
		if(serviceRequestedDate != null && !"".equals(serviceRequestedDate)){
			serviceRequestedDate += LexmarkConstants.ZERO_SECOND;
			Date serviceRequestedDateGMT = DateLocalizationUtil.parseDate(serviceRequestedDate, true, locale);
			
				claim.getDebrief().setServiceRequestedDate(serviceRequestedDateGMT);
				logger.debug("serviceRequestedDateGMT--->>>"+serviceRequestedDateGMT);
		}
		if(serviceStartDate != null && !"".equals(serviceStartDate)){
			serviceStartDate += LexmarkConstants.ZERO_SECOND;
			Date serviceStartDateGMT = DateLocalizationUtil.parseDate(serviceStartDate, true, locale);
			
				claim.getDebrief().setServiceStartDate(serviceStartDateGMT);
		}
		if(serviceEndDate != null && !"".equals(serviceEndDate)){
			serviceEndDate += LexmarkConstants.ZERO_SECOND;
			Date serviceEndDateGMT = DateLocalizationUtil.parseDate(serviceEndDate, true, locale);
			
			claim.getDebrief().setServiceEndDate(serviceEndDateGMT);
		}
		AccountContact requestor = PortalSessionUtil.retrieveLoginAccountContact(request);
		claim.getServiceRequest().setRequestor(requestor);
		ControllerUtil.formatHTMLTagForNotes(claim.getActivityNoteList());
		claim.setAdditionalPaymentRequestList(claimDebriefForm.getNewAdditionalPaymentRequestList());
		ControllerUtil.checkAdditionalPaymentsWithEmptyID(claim.getAdditionalPaymentRequestList());
		logger.debug("[END] transformClaimDebriefForm"); 
		return claim;
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param claimDebriefForm 
	 * @param claimDebriefFormDebrief 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=addAttachmentsCreateDebrief")
	public void addAttachment(Model model, ActionRequest request, ActionResponse response,
			@ModelAttribute("attachmentFormDisplay") ClaimDebriefForm claimDebriefForm
			,@ModelAttribute("claimDebriefForm") ClaimDebriefForm claimDebriefFormDebrief) 
	throws Exception {
		logger.debug("Inside fileUploadmethod ");
		logger.debug("Inside fileUploadmethod "+ claimDebriefForm);
		logger.debug("Inside fileUploadmethod "+ claimDebriefForm.getFileData());
		
		
		PortletSession session = request.getPortletSession();
		//put filename in the attachment list
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		
		Long fileSize=claimDebriefForm.getFileData().getSize();
		FileItem fileItem=claimDebriefForm.getFileData().getFileItem();
		String inputFileName=returnFileName(fileItem.getName());
		
		logger.debug("Inside fileUploadmethod inputFileName ::  "+inputFileName);
		
		// Size For display
		
		double fileSizeDisplay=claimDebriefForm.getFileData().getSize();
		logger.debug("File Size is " + fileSizeDisplay);
		fileSizeDisplay=fileSizeDisplay/1024;
		BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
		roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
		logger.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
	
		StringBuffer errorMessage=new StringBuffer();
		
		logger.debug("File Size is " + fileSize);
		boolean maxFileSize=false;
		
		// Setting for duplicate File
		boolean fileExists=false;
		boolean maxNumberOfFiles=false;

		// File Type Validation
		String inputFileType=claimDebriefForm.getFileData().getContentType();
		logger.debug("inputFileType is :::  :: " + inputFileType);
		String fileExt = inputFileName.substring(inputFileName.lastIndexOf(".")+1, inputFileName.length()).toLowerCase();
		logger.debug("fileExt is :::  :: " + fileExt);

		boolean fileSizeInValid=false;
		
		if(attachmentFileType.indexOf(fileExt)==-1){
			fileSizeInValid=true;
			logger.debug("File Type is Not Valid :: fileSizeInValid !!!! "+fileSizeInValid);
		}
		
		if(fileSize<0)
			{throw new Exception("File Size is empty!!!!!!!");}
		else 
		{
			if(fileSize>Long.valueOf(strFileSize)){
				errorMessage.append(maxFileSize_Message);
				maxFileSize=true;
				logger.debug("Not Attached Filename  File Size is More Than 1 MB  maxFileSize !!!! "+maxFileSize + " File Name is :: "+inputFileName);

				claimDebriefForm.setAttachmentList(attachmentList);
				claimDebriefFormDebrief.setAttachmentList(attachmentList);
				if(attachmentList!=null){
					claimDebriefForm.setFileCount(attachmentList.size());
					claimDebriefFormDebrief.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
				
			}// File Type Check
			else if(fileSizeInValid){
				errorMessage.append(attachmentFileTypeMessage);
				fileSizeInValid=true;
				logger.debug("File Type is Not Valid :: fileSizeInValid !!!! "+fileSizeInValid);

				claimDebriefForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
					claimDebriefForm.setFileCount(attachmentList.size());
					claimDebriefFormDebrief.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
				
			}else{
				// Checking for Duplicate File and File Size
				if(attachmentList!=null){
					
					int maxAattachmentSize = Integer.valueOf(attachmentSize);
					logger.debug("maxAattachmentSize Size is " + maxAattachmentSize);
					if(attachmentList.size()<maxAattachmentSize){
				for(int i=0;i<attachmentList.size();i++)
				{
					Attachment Attachment_1=(Attachment)attachmentList.get(i);
					if(inputFileName.equals(Attachment_1.getAttachmentName())){
					errorMessage.append(duplicateFile_Message);				
					fileExists=true;
					logger.debug("File Already Exists Uploading Duplicate File ::  fileExists :: "+fileExists);
					break;
					}
				}
				}else{
					errorMessage.append(maxNumberOfFiles_Message);
					maxNumberOfFiles=true;
					logger.debug("Max number can be uploaded is 2 :: maxNumberOfFiles :: "+maxNumberOfFiles);
				}}else{
					attachmentList = new ArrayList<Attachment>();
				}						
			
				logger.debug("Final FileExists :: "+fileExists);
				logger.debug("maxNumberOfFiles :: "+maxNumberOfFiles);
				logger.debug("maxFileSize :: "+maxFileSize);
				if(!fileExists && !maxNumberOfFiles && !maxFileSize){
					logger.debug("Adding the file attachment !!!! "+inputFileName);
					Attachment fileAttachment = new Attachment();
					logger.debug("Filename setting is "+inputFileName);
					fileAttachment.setAttachmentName(inputFileName);
					fileAttachment.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
					attachmentList.add(fileAttachment);
					logger.debug(" Attaching the File !!!!");
					claimDebriefForm.setAttachmentList(attachmentList);
					claimDebriefFormDebrief.setAttachmentList(attachmentList);					
					logger.debug("Attachment size is "+attachmentList.size());
					claimDebriefForm.setFileCount(attachmentList.size());
					claimDebriefFormDebrief.setFileCount(attachmentList.size());
					
					
					session.setAttribute("attachmentList", attachmentList);
					logger.debug("path is is ::  "+path);
					try {
						fileItem.write(new File(path + inputFileName));
					} catch (Exception e) {
						logger.debug("Exception"+e.getMessage()); 
						throw new Exception("Could not write file to drive!!!!!!!");
					}
					}
			else{
				logger.debug("Not Attached Filename already existing in the File share DUPLICATE !!!! "+inputFileName);
				claimDebriefForm.setAttachmentList(attachmentList);
				claimDebriefFormDebrief.setAttachmentList(attachmentList);
				logger.debug("Attachment size is "+attachmentList.size());
				if(attachmentList!=null){
				claimDebriefForm.setFileCount(attachmentList.size());
				claimDebriefFormDebrief.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
			}
		
				}
		}
				
		
		logger.debug("---------------------Setting the claimDebriefForm  !!!!------------------- ");
		logger.debug("errorMessage is ::  "+errorMessage.toString());
		claimDebriefForm.setErrorMessage(errorMessage.toString());				
		claimDebriefFormDebrief.setErrorMessage(errorMessage.toString());
		
		request.setAttribute("attachmentFormDisplay",claimDebriefForm);
		model.addAttribute("attachmentFormDisplay",claimDebriefForm);
		
		request.setAttribute("claimDebriefForm",claimDebriefFormDebrief);
		model.addAttribute("claimDebriefForm",claimDebriefFormDebrief);
		logger.debug("---------------------Setting the claimDebriefForm  Complete !!!!------------------- ");
		response.setRenderParameter("action", "attachDocumentTestDebrief");
		
		
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=attachDocumentTestDebrief")
	public String attachDocument(Model model, RenderRequest request,
	RenderResponse response) throws Exception {
		logger.debug("---------------------attachDocumentTestDebrief is called !!!!------------------- ");
		return "claims/claimDebriefView";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param fileName 
	 * @param claimDebriefForm 
	 * @param claimDebriefFormDebrief 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=removeAttachmentActionClaimCloseout")
	public void removeAttachment(ActionRequest request, ActionResponse response,
			@RequestParam("fileName") String fileName,@ModelAttribute("attachmentFormDisplay") ClaimDebriefForm claimDebriefForm,
			@ModelAttribute("claimDebriefForm") ClaimDebriefForm claimDebriefFormDebrief, Model model) throws Exception {
		logger.debug("Inside removeAttachment ");
		logger.debug("Filename to be deleted "+ fileName);
		if("".equals(fileName)){
			return;
		}
			PortletSession session = request.getPortletSession();
		
			
			//Delete the file from the filesystem
			String localWebPath = composeFilePath(path,fileName);
			File tempFile = new File(localWebPath);
			logger.debug("------------------- localWebPath from where we have to delete the file "+localWebPath);
			try {
				tempFile.delete();
			} 
			catch(Exception ex){
				logger.error("Error occurred while trying to delete temporary file for Siebel file upload");
				logger.error(ex.getMessage());
				if( ex.getCause() != null && ex.getCause().getMessage() != null) {
					logger.error("Cause: " + ex.getCause().getMessage());
				}
			}
			//Delete the file from the list
			List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
			List<Attachment> newAttachmentList = new ArrayList<Attachment>();
			for(Attachment attachment:attachmentList){
				if(!fileName.equalsIgnoreCase(attachment.getAttachmentName())){
					newAttachmentList.add(attachment);
				}
			}
			session.removeAttribute("attachmentList");
			session.setAttribute("attachmentList", newAttachmentList);
			//just check if the file is still there
			for(Attachment attachment:newAttachmentList){
				logger.debug("@@@@@@@@@@@@@ After deleting the remaining filenames are "+attachment.getAttachmentName());
			}
			
			if(newAttachmentList.size()==0){
				newAttachmentList=null;
			}
			
			claimDebriefFormDebrief.setErrorMessage("");
			claimDebriefForm.setErrorMessage("");	
			
			claimDebriefForm.setAttachmentList(newAttachmentList);
			claimDebriefFormDebrief.setAttachmentList(newAttachmentList);
		request.setAttribute("attachmentFormDisplay",claimDebriefForm);
		model.addAttribute("attachmentFormDisplay",claimDebriefForm);
		
		request.setAttribute("claimDebriefForm",claimDebriefFormDebrief);
		model.addAttribute("claimDebriefForm",claimDebriefFormDebrief);
		
		
		
		response.setRenderParameter("action", "removeDocumentRenderClaimCloseout");	
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=removeDocumentRenderClaimCloseout")
	public String removeAttachmentRender(Model model, RenderRequest request,
	RenderResponse response) throws Exception {

		return "claims/claimDebriefView";
	}	
	/**
	 * @param path 
	 * @param fileName 
	 * @return String 
	 */
	private String composeFilePath(String path, String fileName)
	{
		StringBuilder filePathBuilder = new StringBuilder();
		filePathBuilder.append(path);
		filePathBuilder.append(fileName);
		return filePathBuilder.toString();
	}

	/**
	 * @param inputStr 
	 * @return String 
	 */
	private String returnFileName(String inputStr){
		if(inputStr.indexOf("\\")!=-1){
    		int i=inputStr.lastIndexOf("\\");
    		logger.info("Index is "+i);
    		String str_Return=inputStr .substring(i+1,inputStr.length());
    		logger.info("Final return string is "+str_Return);
    		return str_Return;
    		
	}   	
	else{
		return inputStr;
		
	}
	
}
	
	
	// started for CI 15.4 Problem Code 2 retrieve method
	/**
	 * ResourceMapping for the retrieve Price List for Parts 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("retriveProblemCodeClaimCloseOut")
	public void retriveProblemCode(ResourceRequest request, ResourceResponse response) throws Exception
	{
		logger.debug("in retrieve Error Code  ");
		logger.debug("---------- Problem Code value is ----------- "+request.getParameter("problemCode"));
		String parentProblemCode = request.getParameter("problemCode");
		logger.debug("---------- problemCodeLevel value is ----------- "+request.getParameter("problemCodeLevel"));
		
		StringBuffer responseBody=new StringBuffer();
		response.setContentType("text/html");
		StringBuffer sb = new StringBuffer();
		
		if(request.getParameter("problemCodeLevel").trim().equalsIgnoreCase("1")){
			
			
			Map<String, String> newSiebelMap = null;
			
			newSiebelMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), parentProblemCode, globalService, serviceRequestLocaleService, request.getLocale());
			logger.debug("----------------- newSiebelMap is --------------------- "+newSiebelMap.size());
			logger.debug("----------------- newSiebelMap is --------------------- "+newSiebelMap);
			
			//Map<String,String> LOVfromDB = null;
			//LOVfromDB = retrieveLocalizedLOVMap("PARTNER_PROBLEM_CODE_2", request.getLocale());
			//logger.debug("LOVfromDB is ------------------ for code 2 "+LOVfromDB);
			
			//Map<String, String> tempMap = null;
			
			//tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				//	SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
			//logger.debug("----------------- Problem Code Map 2 is --------------------- "+tempMap);
			//StringBuffer responseBody=new StringBuffer();
			//response.setContentType("text/html");
			//StringBuffer sb = new StringBuffer();
			if(newSiebelMap.size()>1){
			sb.append("<select id=\'probCode2\' name=\'probCode2\' onchange=\'getprobCode(this.value,2);\'>");
			sb.append("<option value=''></option>");
			Iterator it = newSiebelMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        logger.debug("Key is "+pair.getKey() + " = " + pair.getValue());
		        sb.append("<option value=\'"+pair.getKey()+"\'>"+pair.getValue()+"</option>");
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    sb.append("</select>");
			}
		    /*responseBody.append("\"problCode2\":\""+sb.toString()+"\"");
			//responseBody.insert(responseBody.length(), "}");
			PrintWriter out =  response.getWriter();
			responseBody.insert(0, "{");
			responseBody.insert(responseBody.length(), "}");
			logger.debug("response body finally is " + responseBody.toString());
			out.print(responseBody.toString());
			out.flush();
			out.close();*/
			logger.debug("----------------- exiting retrieve error code 2 -------------------");
		}
				
		if(request.getParameter("problemCodeLevel").trim().equalsIgnoreCase("2")){
			logger.debug("in retrieve Error Code 3 ");
			logger.debug("---------- Problem Code2 value is ----------- "+request.getParameter("problemCode"));
			
			//Map<String,String> LOVfromDB = null;
			//LOVfromDB = retrieveLocalizedLOVMap("PARTNER_PROBLEM_CODE_3", request.getLocale());
			//logger.debug("LOVfromDB is ------------------ for code 3 "+LOVfromDB);
			
			
			Map<String, String> tempMap = null;
			
			if(parentProblemCode.indexOf(";") != -1){
				String [] problemcodeArray = parentProblemCode.split(";");
				for(int i =0 ; i<problemcodeArray.length;i++){
					logger.debug("problemcodeArray "+i+" is "+problemcodeArray[i]);
					parentProblemCode = problemcodeArray[i];
				}				
			}
			
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), parentProblemCode, globalService, serviceRequestLocaleService, request.getLocale());
			logger.debug("----------------- Problem Code Map3 is --------------------- "+tempMap);
			//StringBuffer responseBody=new StringBuffer();
			response.setContentType("text/html");
			//StringBuffer sb = new StringBuffer();
			if(tempMap.size()>1){
			sb.append("<select id=\'probCode3\' name=\'probCode3\' >");
			sb.append("<option value=''></option>");
			Iterator it = tempMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        logger.debug("Key is "+pair.getKey() + " = " + pair.getValue());
		        sb.append("<option value=\'"+pair.getKey()+"\'>"+pair.getValue()+"</option>");
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    sb.append("</select>");
			}
		    /*responseBody.append("\"problCode3\":\""+sb.toString()+"\"");
			//responseBody.insert(responseBody.length(), "}");
			PrintWriter out =  response.getWriter();
			responseBody.insert(0, "{");
			responseBody.insert(responseBody.length(), "}");
			logger.debug("response body finally is " + responseBody.toString());
			out.print(responseBody.toString());
			out.flush();
			out.close();*/
			logger.debug("----------------- exiting retrieve error code 3 -------------------");	
		}
		
		responseBody.append("\"problCode\":\""+sb.toString()+"\"");
		//responseBody.insert(responseBody.length(), "}");
		PrintWriter out =  response.getWriter();
		responseBody.insert(0, "{");
		responseBody.insert(responseBody.length(), "}");
		logger.debug("response body finally is " + responseBody.toString());
		out.print(responseBody.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * ResourceMapping for the retrieve Price List for Parts 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("retriveNumericCode2ClaimCloseOut")
	public void retriveNumericCode2(ResourceRequest request, ResourceResponse response) throws Exception
	{
		logger.debug("in retriveNumericCode2 ");
		logger.debug("---------- errorNumericCode2 value is ----------- "+request.getParameter("errorNumericCode2"));
		
		String errorCodeNum = request.getParameter("errorNumericCode2");
		String problemCode = request.getParameter("problemCode");
		
		ArrayList<String> numericCode2 = new ArrayList<String>();
		for(int i=0;i<101;i++){
			numericCode2.add(Integer.toString(i));
		}
		
		//Map<String, String> newSiebelMap = null;
		
		//newSiebelMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
		//		SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), errorCodeNum, globalService, serviceRequestLocaleService, request.getLocale());
		//logger.debug("----------------- newSiebelMap is --------------------- "+newSiebelMap);
		SiebelLOVListContract lovListcontract = ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), problemCode);
	    //String str2 = "76.39";
	    float errorCodeVal = Float.parseFloat(errorCodeNum);
	    logger.debug("float value is ----->>> "+errorCodeVal);	    
	    lovListcontract.setValue(errorCodeNum);
	    
		logger.debug("Berofe errorCode contract1");
		ObjectDebugUtil.printObjectContent(lovListcontract, logger);
		logger.debug("After errorCode contract1");
		SiebelLOVListResult listResult = globalService.retrieveNonCacheableSiebelLOVList(lovListcontract);
		
		if(null != listResult.getLovList()){
			logger.debug("list for num code is "+listResult.getLovList().size());
		}	
		else{
			logger.debug("list for num code is null");
		}
		boolean inSiebel = false;
		if(listResult.getLovList().size()>0){
			inSiebel = true;
		}
		
		StringBuffer responseBody=new StringBuffer();
		response.setContentType("text/html");
		
		logger.debug("numericCode2 list size is "+numericCode2.size());
		/*for(int i =0;i<numericCode2.size();i++){			
			if(request.getParameter("errorNumericCode2").trim().equalsIgnoreCase(numericCode2.get(i))){
				logger.debug("in for check value is present in the list");
				inSiebel=true;
			}
		}*/
		logger.debug("inSiebel Flag is "+inSiebel);
		if(inSiebel){
			responseBody.append("\"message\":\"Numeric Code Found\"");
		}
		else{
		responseBody.append("\"message\":\"Numeric Code Not Found\"");
		}
		PrintWriter out =  response.getWriter();
		responseBody.insert(0, "{");
		responseBody.insert(responseBody.length(), "}");
		logger.debug("response body finally is " + responseBody.toString());
		out.print(responseBody.toString());
		out.flush();
		out.close();
 		
		logger.debug("-------------------- exiting retrieve Numric Error Code 2 ------------------------- ");
		
		
	}
	
	// end for CI 15.4 
}
