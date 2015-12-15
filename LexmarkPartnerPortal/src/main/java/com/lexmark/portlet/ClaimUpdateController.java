/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ClaimUpdateController
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

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.ClaimUpdateContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.form.ClaimDetailForm;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.result.ClaimUpdateResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.ExceptionUtil;
import com.lexmark.util.MailUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.webservice.api.ClaimService;


@Controller
@RequestMapping("VIEW")
@SessionAttributes("claimDetailFormForUpdate")
public class ClaimUpdateController {
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
	private AttachmentService attachmentService;
	@Autowired
	private GridSettingController gridSettingController;

	private static Logger logger = LogManager.getLogger(ClaimUpdateController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	
	// Getting the File Path from attachment properties file
	static ResourceBundle bundle = ResourceBundle.getBundle("attachment");
	private static String path=bundle.getString("ClaimFileDestination");
	
	
	// CI6 Attachments 
	private static String attachmentSize=bundle.getString("AttachmentSize");
	private static String strFileSize=bundle.getString("MaxFileSize");

	private static String maxFileSize_Message=bundle.getString("maxFileSizeMessage");
	private static String duplicateFile_Message=bundle.getString("duplicateFileMessage");
	private static String maxNumberOfFiles_Message=bundle.getString("maxNumberOfFilesMessage");
	
	// File Type Validation
	private static String attachmentFileType=bundle.getString("attachmentFileType");
	private static String attachmentFileTypeMessage=bundle.getString("attachmentFileTypeMessage");
	
	
	@RequestMapping(params="action=showClaimUpdatePage")
	public String showClaimUpdatePage(Model model, RenderRequest request, RenderResponse response) throws Exception {
		logger.debug("[START] showClaimUpdatePage");
		
		//retrieve requested Parts grids order 
		List<UserGridSettingResult> gridSettingList = new ArrayList<UserGridSettingResult>();
		UserGridSettingResult gridSettingResult= new UserGridSettingResult(); 
		gridSettingController.retrieveGridSetting("gridCDtlVRPRequest", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		logger.debug("gridCDtlVRPRequest cols order "+gridSettingResult.getColsOrder());
		gridSettingList.add(gridSettingResult);
		
		//retrieve ordered Parts grids order 
		gridSettingController.retrieveGridSetting("gridOrderedParts1", request, model);
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		logger.debug("gridOrderedParts cols order "+gridSettingResult.getColsOrder());
		gridSettingList.add(gridSettingResult);
		
		//retrieve parts to be returned grids order
		gridSettingController.retrieveGridSetting("gridCDtlVBRPRequest", request, model);
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		logger.debug("gridCDtlVBRPRequest cols order "+gridSettingResult.getColsOrder());
		gridSettingList.add(gridSettingResult);
		
		//retrieve parts and tools grids order
		gridSettingController.retrieveGridSetting("gridCCVPartDefault", request, model);
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		logger.debug("gridCCVPartDefault cols order "+gridSettingResult.getColsOrder());
		gridSettingList.add(gridSettingResult);
		
		model.addAttribute("gridSettingList",gridSettingList);
		
		
		ClaimDetailContract contract = ContractFactory.createClaimDetailContract(request);
		contract.setDebriefFlag(false);
		logger.debug("The contract is as below---------------->>>");
		ObjectDebugUtil.printObjectContent(contract, logger);
		Long startTime = System.currentTimeMillis();
		ClaimDetailResult claimDetailResult = partnerRequestsService.retrieveClaimDetail(contract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.PARTNER_CLAIM_DETAIL, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		if(claimDetailResult==null) {
			throw new IllegalArgumentException("Failed to open Claim Update page: <br/> claimDetailResult is null");
		}
		if(claimDetailResult.getActivity() == null){
			throw new IllegalArgumentException("Failed to open Claim Update page: <br/> Activity is null");
		}
		if(claimDetailResult.getActivity().getServiceRequest() == null){
			throw new IllegalArgumentException("Failed to open Claim Update page: <br/> ServiceRequest is null");
		}
		if(claimDetailResult.getActivity().getServiceRequest().getAsset() == null){
			throw new IllegalArgumentException("Failed to open Claim Update page: <br/> Asset is null");
		}
		
		Activity claimDetail = claimDetailResult.getActivity();
		// show partnerAccount.address, and mapped to Activity.shipToAddress when submitted.
		claimDetail.setShipToAddress(claimDetail.getPartnerAccount().getAddress());
		
		Locale locale = request.getLocale();
		Map<String, String> carrierDropDown = new HashMap<String, String>() ;

		carrierDropDown = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
		ControllerUtil.localizeActivity(claimDetail, serviceRequestLocaleService, locale);
		//localize returnPartList
		if (claimDetail.getReturnPartList() != null) {
			ControllerUtil.batchLocalizePart(claimDetail.getReturnPartList(), serviceRequestLocaleService, locale);
		}
		//localize orderPartList
		if (claimDetail.getOrderPartList() != null) {
			ControllerUtil.batchLocalizePart(claimDetail.getOrderPartList(), serviceRequestLocaleService, locale);
		}
		//localize additional payments
		if(claimDetail.getAdditionalPaymentRequestList() != null){
			ControllerUtil.batchLocalizeAdditionPayments(claimDetail.getAdditionalPaymentRequestList(), serviceRequestLocaleService, locale);
		}		
		
		// TODO: localize partLineItem.carrier, partLineItem.lineStatus
		String serviceType = request.getParameter("serviceType");
		logger.debug("---------------------show update claims----------------"+serviceType);
		claimDetail.setServiceType(serviceType);
		ClaimDetailForm claimDetailForm = createClaimDetailForm(claimDetail, locale,carrierDropDown, request);
		
		
		List<PartLineItem> partList = claimDetail.getPendingPartList();
		if (partList == null || partList.size() == 0) {
			model.addAttribute("showRequestedPartsFlag", false);
		} else {
			model.addAttribute("showRequestedPartsFlag", true);
		}

		partList = claimDetail.getOrderPartList();
		if (partList == null || partList.size() == 0) {
			model.addAttribute("showOrderPartsFlag", false);
		} else {
			model.addAttribute("showOrderPartsFlag", true);
		}

		partList = claimDetail.getReturnPartList();
		if (partList == null || partList.size() == 0) {
			model.addAttribute("showReturnPartsFlag", false);
		} else {
			model.addAttribute("showReturnPartsFlag", true);
		}
		
		
		//For activity
		if(serviceType!=null && (serviceType.equalsIgnoreCase("Onsite Exchange"))){
		
		claimDetail.setExchangeFlag(true);
		}
		model.addAttribute("showAdditionalPaymentRequestListFlag", claimDetail.getPartnerAccount().isAllowAdditionalPaymentRequestFlag());

		ResourceURL resourceURL = response.createResourceURL();
		resourceURL.setResourceID("getServiceHistoryList");
		resourceURL.setParameter("assetId", claimDetail.getServiceRequest().getAsset().getAssetId());

		claimDetailForm.setFromPage(request.getParameter("fromPage"));
		claimDetailForm.refreshSubmitToken(request);
		String timezoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, timezoneOffset);
		model.addAttribute("claimDetailFormForUpdate", claimDetailForm);
		model.addAttribute("attachmentFormDisplay", claimDetailForm);
		model.addAttribute("orderPartListXML",claimDetailForm.getOrderPartListXML());
		model.addAttribute("downServiceHistoryURL", resourceURL.toString());
		PortletSession session = request.getPortletSession();
		if(session!=null&& session.getAttribute("attachmentList")!=null){
			session.removeAttribute("attachmentList");
		}
		logger.debug("[END] showClaimUpdatePage");
		return "claims/claimDetailUpdateView";
	}
	
	private ClaimDetailForm createClaimDetailForm(Activity claimDetail, Locale locale,Map<String, String> carrierDropDown, PortletRequest request) throws Exception{
		logger.debug("[START] createClaimDetailForm");
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		ClaimDetailForm claimDetailForm = new ClaimDetailForm();
		
		Map<String, String> paymentTypes = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_ADDITIONAL_PAYMENT_TYPE.getValue(), null, globalService, serviceRequestLocaleService, locale);
		claimDetailForm.setPaymentTypes(paymentTypes);
		
		Asset device = claimDetail.getServiceRequest().getAsset();
		device.setProductImageURL(ControllerUtil.retrieveProductImageUrl(productImageService, device.getProductTLI()));
		claimDetailForm.setClaimDetail(claimDetail);
		
		claimDetailForm.setPendingPartListXML(xmlOutputGenerator.convertPendingPartListToXML(claimDetail
				.getPendingPartList()));
		
		claimDetailForm.setOrderPartListXML(xmlOutputGenerator
				.convertOrderPartListToXML(claimDetail.getOrderPartList()));
	
		claimDetailForm.setReturnPartListXML(xmlOutputGenerator.convertUpdateReturnPartListToXML(claimDetail
				.getReturnPartList(),carrierDropDown));
		
		claimDetailForm.setAdditionalPaymentRequestListXML(xmlOutputGenerator.convertAdditionalPaymentListToXMLForEdit(
				claimDetail.getAdditionalPaymentRequestList(), claimDetailForm.getPaymentTypes()));		
		
		claimDetailForm.setActivityNoteListXML(xmlOutputGenerator.convertActivityNoteListToXMLForEdit(claimDetail.getActivityNoteList(), 
				PortalSessionUtil.getContactId(request.getPortletSession())));
		
		claimDetailForm.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));

		logger.debug("[END] createClaimDetailForm");
		return claimDetailForm;
	}
	
	@RequestMapping(params="action=submitClaimUpdate")
	public void submitClaimUpdate(Model model, 
			   					  ActionRequest request, 
			   					  ActionResponse response,
			   					  @ModelAttribute("claimDetailFormForUpdate") ClaimDetailForm claimForm) throws Exception{
		logger.debug("[START] submitClaimUpdate");
		ClaimUpdateResult result;
		if(PortalSessionUtil.isDuplicatedSubmit(request, claimForm)){
			if(claimForm.getFromPage().equalsIgnoreCase("createClaimMultipleClaimView")){ 
				logger.debug("[fromPage] DuplicatedSubmit createClaimMultipleClaimView");
				response.setRenderParameter("action", "showGlobalPartnerAssetSectionView");
			}
			if(claimForm.getFromPage().equalsIgnoreCase("claimDetailView")){
				logger.debug("[fromPage] DuplicatedSubmit claimDetailView");
				response.setRenderParameter("serviceRequestId", claimForm.getClaimDetail().getServiceRequest().getId());
				response.setRenderParameter("activityId", claimForm.getClaimDetail().getActivityId());
				response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(claimForm.getTimezoneOffset()));
				response.setRenderParameter("action", "retrieveClaimDetail");
			}
			if(claimForm.getFromPage().equalsIgnoreCase("requestList")){
				logger.debug("[fromPage] DuplicatedSubmit requestList");
			}			
			return;
		}
		transformClaimForm(request,claimForm);
		Activity activity = claimForm.getClaimDetail();
		setServiceRequestor(request, activity);
		String messageCode = null;
		try{
			ClaimUpdateContract contract = ContractFactory.createClaimUpdateContract(request, activity);
			result = claimWebService.updateWarrantyClaim(contract);
		}catch(Exception e){
			messageCode = "exception.siebel.updateClaimException";
			
			String errorMessage = ExceptionUtil.getLocalizedExceptionMessage(messageCode, request.getLocale(),e);
			MailUtil.sendEmail("shiva.juluru@perficient.com", "Error while updating Claim", errorMessage, false);
			MailUtil.sendEmail("dan.wellborn@perficient.com", "Error while updating Claim", errorMessage, false);

			ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			response.setRenderParameter("serviceRequestId", claimForm.getClaimDetail().getServiceRequest().getId());
			response.setRenderParameter("activityId", claimForm.getClaimDetail().getActivityId());
			response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(claimForm.getTimezoneOffset()));
			response.setRenderParameter("fromPage", claimForm.getFromPage());

			response.setRenderParameter("action", "showClaimUpdatePage");
			return;
		}	
		
		if(true){
			messageCode = "message.claim.updateSuccess";
			if(claimForm.getFromPage().equalsIgnoreCase("createClaimMultipleClaimView")){ 
				logger.debug("[fromPage] createClaimMultipleClaimView");
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
				response.setRenderParameter("action", "showGlobalPartnerAssetSectionView");
			}
			if(claimForm.getFromPage().equalsIgnoreCase("claimDetailView")){
				logger.debug("[fromPage] claimDetailView");
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
				
				response.setRenderParameter("serviceRequestId", claimForm.getClaimDetail().getServiceRequest().getId());
				response.setRenderParameter("activityId", claimForm.getClaimDetail().getActivityId());
				response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(claimForm.getTimezoneOffset()));
				response.setRenderParameter("action", "retrieveClaimDetail");
			}
			if(claimForm.getFromPage().equalsIgnoreCase("requestList")){
				logger.debug("[fromPage] requestList");
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			}
		}else{
			messageCode = "exception.submitUpdateClaimFailed";
			ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			response.setRenderParameter("serviceRequestId", claimForm.getClaimDetail().getServiceRequest().getId());
			response.setRenderParameter("activityId", claimForm.getClaimDetail().getActivityId());
			response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET, String.valueOf(claimForm.getTimezoneOffset()));
			response.setRenderParameter("fromPage", claimForm.getFromPage());

			response.setRenderParameter("action", "showClaimUpdatePage");
		}
		//calling aMind method..
		//Get all attachment names
		PortletSession session = request.getPortletSession();
		
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		
		if(attachmentList == null) {
			attachmentList = new ArrayList<Attachment>();
			logger.debug("--------------------- We dont have anything as attachment ---------------------------");
		}else{
			if(attachmentList.size()>0){
		for(int i=0;i<attachmentList.size();i++){
			logger.debug("---------------- Printing attachment name --------------");
			logger.debug("----------"+attachmentList.get(i).getAttachmentName());
		}
		
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		AttachmentContract attachmentContract = new AttachmentContract();
		attachmentContract.setSessionHandle(crmSessionHandle);
		logger.debug("------------------------------- activityid we are sending ----------------------"+claimForm.getClaimDetail().getActivityId());
		attachmentContract.setIdentifier(claimForm.getClaimDetail().getActivityId());
		attachmentContract.setRequestType("Claim Update");
		attachmentContract.setAttachments(attachmentList);
		logger.debug("----------------- crmSessionHandle value is "+crmSessionHandle);
		
		logger.debug("-------------------------- Calling attachment service ----------------------");
		attachmentService.uploadAttachments(attachmentContract);
		logger.debug("-------------------------- Amind method call done ---------------------------");
		}
			else{
				logger.debug("--------------------- We dont have anything as attachment List Size is Zero ---------------------------");
			}
		}
		if(session!=null&& session.getAttribute("attachmentList")!=null){
			session.removeAttribute("attachmentList");
			}
		logger.debug("[END] submitClaimUpdate");
	}

	private void setServiceRequestor(ActionRequest request, Activity activity) {
		if(activity.getServiceRequest() == null){
			ServiceRequest serviceRequest = new ServiceRequest();
			activity.setServiceRequest(serviceRequest);
		}
		activity.getServiceRequest().setRequestor(PortalSessionUtil.retrieveLoginAccountContact(request));
	}
	
	@RequestMapping(params="action=cancelClaimUpdate")
	public void cancelClaimUpdate(Model model, 
								  ActionRequest request, 
								  ActionResponse response, 
								  @ModelAttribute("claimDetailFormForUpdate") ClaimDetailForm claimForm){
		logger.debug("[START] cancelclaimUpdate");
		if(claimForm.getFromPage().equalsIgnoreCase("createClaimMultipleClaimView")){
			logger.debug("[fromPage] createClaimMultipleClaimView");
			response.setRenderParameter("action", "showGlobalPartnerAssetSectionView");
		}
		if(claimForm.getFromPage().equalsIgnoreCase("claimDetailView")){
			logger.debug("[fromPage] claimDetailView");
			response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET,request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
			response.setRenderParameter("serviceRequestId", claimForm.getClaimDetail().getServiceRequest().getId());
			response.setRenderParameter("activityId", claimForm.getClaimDetail().getActivityId());
			response.setRenderParameter("action", "retrieveClaimDetail");
		}
		if(claimForm.getFromPage().equalsIgnoreCase("requestList")){
			logger.debug("[fromPage] requestList");
		}
		
		PortletSession session = request.getPortletSession();
		if(session!=null&& session.getAttribute("attachmentList")!=null){
			session.removeAttribute("attachmentList");
		}
		logger.debug("[END] cancelclaimUpdate");
	}
	
	private void transformClaimForm(ActionRequest request, ClaimDetailForm claimDetailForm) throws Exception {
		ControllerUtil.formatHTMLTagForNotes(claimDetailForm.getClaimDetail().getActivityNoteList());
		setTechinicianName(claimDetailForm);
		claimDetailForm.getClaimDetail().setAdditionalPaymentRequestList(claimDetailForm.getNewAdditionalPaymentRequestList());
		ControllerUtil.checkAdditionalPaymentsWithEmptyID(claimDetailForm.getClaimDetail().getAdditionalPaymentRequestList());
	}

	private void setTechinicianName(ClaimDetailForm claimDetailForm) {
		String technicianFullName = claimDetailForm.getTechnicianFullName();
		AccountContact contact = claimDetailForm.getClaimDetail().getTechnician();
		
		if(contact == null ){
			contact = new AccountContact();
			claimDetailForm.getClaimDetail().setTechnician(contact);
		}
		else if("other".equals(technicianFullName)){
			contact.setContactId(null);
			contact.setNewContactFlag(true);
			contact.setUpdateContactFlag(false);
			
		}else{
			contact.setNewContactFlag(false);
			contact.setUpdateContactFlag(true);
			String[] splitedNameId = technicianFullName.split("/");
			String fullName = splitedNameId[0];
			String[] splitedNames = fullName.split(",");
			String lastName = splitedNames[0];
			contact.setLastName(lastName);
			if(splitedNames.length > 1){
				contact.setFirstName(splitedNames[1]);
			}
			if(splitedNameId.length > 1){
				String id = splitedNameId[1];
				contact.setContactId(id);				
			}
		}
	}
// for CI5 attachment feature
	
	@RequestMapping(params="action=addAttachmentsCreate")
	public void addAttachment(Model model, ActionRequest request, ActionResponse response,
			@ModelAttribute("attachmentFormDisplay") ClaimDetailForm claimForm) throws Exception {
		logger.debug("Inside fileUploadmethod ");
		logger.debug("Inside fileUploadmethod "+ claimForm);
		logger.debug("Inside fileUploadmethod "+ claimForm.getFileData());
		
		
		PortletSession session = request.getPortletSession();
		Long fileSize=claimForm.getFileData().getSize();
		
		// Size For display
		
		double fileSizeDisplay=claimForm.getFileData().getSize();
		logger.debug("File Size is " + fileSizeDisplay);
		fileSizeDisplay=fileSizeDisplay/1024;
		BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
		roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
		logger.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
	
		
		logger.debug("File Size is " + fileSize);
		
		FileItem fileItem=claimForm.getFileData().getFileItem();
		String inputFileName=returnFileName(fileItem.getName());
		logger.debug("Inside fileUploadmethod fileItem.getFieldName()"+ fileItem.getFieldName()+" fileItem.getName is "+inputFileName);
		//put filename in the attachment list
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		
		StringBuffer errorMessage=new StringBuffer();
        boolean maxFileSize=false;
		
		// Setting for duplicate File
		boolean fileExists=false;
		boolean maxNumberOfFiles=false;

		// File Type Validation
		String inputFileType=claimForm.getFileData().getContentType();
		logger.debug("inputFileType is :::  :: " + inputFileType);
		String fileExt = inputFileName.substring(inputFileName.lastIndexOf(".")+1, inputFileName.length()).toLowerCase();
		logger.debug("fileExt is :::  :: " + fileExt);
		boolean fileSizeInValid=false;
		
		if(attachmentFileType.indexOf(fileExt)==-1){
			fileSizeInValid=true;
			logger.debug("File Type is Not Valid :: fileSizeInValid !!!! "+fileSizeInValid);
		}
		if(fileSize<0){ throw new Exception("File Size is empty!!!!!!!");}
		else 
		{
			if(fileSize>Long.valueOf(strFileSize)){
				errorMessage.append(maxFileSize_Message);
				maxFileSize=true;
				logger.debug("Not Attached Filename  File Size is More Than 1 MB  maxFileSize !!!! "+maxFileSize + " File Name is :: "+inputFileName);

				claimForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
					claimForm.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
				
			}// File Type Check
			else if(fileSizeInValid){
				errorMessage.append(attachmentFileTypeMessage);
				fileSizeInValid=true;
				logger.debug("File Type is Not Valid :: fileSizeInValid !!!! "+fileSizeInValid);

				claimForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
					claimForm.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
				
			}else{
			// Checking for Duplicate File and Number of Files
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
				}}
				else{
					attachmentList = new ArrayList<Attachment>();
				}
				
			logger.debug("Final FileExists :: "+fileExists);
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
			claimForm.setAttachmentList(attachmentList);
			logger.debug("Attachment size is "+attachmentList.size());
			claimForm.setFileCount(attachmentList.size());
			
			session.setAttribute("attachmentList", attachmentList);
			
			try {
				fileItem.write(new File(path + inputFileName));
			} catch (Exception e) {
				throw new Exception("Could not write file to drive!!!!!!!");
			}
			}
			else{
				logger.debug("Not Attached Filename already existing in the File share DUPLICATE !!!! "+inputFileName);
				claimForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
				logger.debug("Attachment size is "+attachmentList.size());
				claimForm.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
			}
		
				}
		}
		
			logger.debug("errorMessage is ::  "+errorMessage.toString());
			claimForm.setErrorMessage(errorMessage.toString());		
			
		
		request.setAttribute("attachmentFormDisplay",claimForm);
		model.addAttribute("attachmentFormDisplay",claimForm);

		response.setRenderParameter("action", "attachDocumentTest");
	}
	
	@RequestMapping(params = "action=attachDocumentTest")
	public String attachDocument(Model model, RenderRequest request,
	RenderResponse response) throws Exception {

		return "claims/claimDetailUpdateView";
	}	
	@RequestMapping(params="action=removeAttachmentActionClaimUpdate")
	public void removeAttachment(ActionRequest request, ActionResponse response,
			@RequestParam("fileName") String fileName,@ModelAttribute("attachmentFormDisplay") ClaimDetailForm claimForm, Model model) throws Exception {
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
			claimForm.setErrorMessage("");
		claimForm.setAttachmentList(newAttachmentList);
		request.setAttribute("attachmentFormDisplay",claimForm);
		model.addAttribute("attachmentFormDisplay",claimForm);
		response.setRenderParameter("action", "removeDocumentRenderClaimUpdate");	
	}
	
	@RequestMapping(params = "action=removeDocumentRenderClaimUpdate")
	public String removeAttachmentRender(Model model, RenderRequest request,
	RenderResponse response) throws Exception {

		return "claims/claimDetailUpdateView";
	}	
	private String composeFilePath(String path, String fileName)
	{
		StringBuilder filePathBuilder = new StringBuilder();
		filePathBuilder.append(path);
		filePathBuilder.append(fileName);
		return filePathBuilder.toString();
	}
	
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
}
