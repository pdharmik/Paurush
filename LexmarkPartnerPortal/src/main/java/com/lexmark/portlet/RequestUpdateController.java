/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: RequestUpdateController
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.ChildSRContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestUpdateContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.TechnicianInstruction;
import com.lexmark.domain.UserDetails;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.form.ClaimDetailForm;
import com.lexmark.form.CreateChildSRForm;
import com.lexmark.form.ServiceRequestDetailForm;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.CreateChildSRResult;
import com.lexmark.result.PartnerClaimCreateIdResult;
import com.lexmark.result.RequestResult;
import com.lexmark.result.RequestUpdateResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.ExceptionUtil;
import com.lexmark.util.MailUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XMLEncodeUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.webservice.api.RequestService;

@Controller
@RequestMapping("VIEW")
@SessionAttributes("serviceRequestDetailForm")
public class RequestUpdateController {
	
	@Autowired
	private PartnerRequestsService partnerRequestsService;
	@Autowired
	private RequestTypeService requestTypeService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private RequestService requestWebService;
	
	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private GridSettingController gridSettingController;

	private static Logger logger = LogManager.getLogger(RequestUpdateController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	

	// Getting the File Path from attachment properties file
	static ResourceBundle bundle = ResourceBundle.getBundle("attachment");
	private static String path=bundle.getString("SrUpdateFileDestination");
	
	private static String attachmentSize=bundle.getString("AttachmentSize");
	private static String strFileSize=bundle.getString("MaxFileSize");

	private static String maxFileSize_Message=bundle.getString("maxFileSizeMessage");
	private static String duplicateFile_Message=bundle.getString("duplicateFileMessage");
	private static String maxNumberOfFiles_Message=bundle.getString("maxNumberOfFilesMessage");

	// File Type Validation
	private static String attachmentFileType=bundle.getString("attachmentFileType");
	private static String attachmentFileTypeMessage=bundle.getString("attachmentFileTypeMessage");
	
	//added for BRD #14-07-01
	
	@RequestMapping(params = "action=createChildSRPage")
	public String createChildSRPage(RenderRequest request, RenderResponse response, Model model) throws Exception{	
		
		//Newly added
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));
		PortletSession session = request.getPortletSession();
		//Checking whether this is FSE or Not as for FSE the address link won't be visible
		boolean fseAddressLink = false;
		boolean fseUser = false;
		if(null != session.getAttribute("FSE_ADDRESS_LINK",javax.portlet.PortletSession.APPLICATION_SCOPE)){
		fseUser = (Boolean)session.getAttribute("FSE_ADDRESS_LINK",javax.portlet.PortletSession.APPLICATION_SCOPE);
		}
		logger.debug("The FSEUserflag is --->>>"+fseUser);
		session.setAttribute("partnerId", request.getParameter("partnerId"));
		Locale locale = request.getLocale();
		RequestContract contract1 = new RequestContract();
		contract1.setCreateChildSR(true);
		contract1.setSessionHandle(crmSessionHandle);
		contract1.setServiceRequestNumber(request.getParameter("serviceRequestId"));
		ObjectDebugUtil.printObjectContent(contract1, logger);
		long timeBeforeCall=System.currentTimeMillis();
		RequestResult serviceRequestResult = requestTypeService.retrieveSupplyRequestDetail(contract1);
		long timeAfterCall=System.currentTimeMillis();
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_CREATE_CHILD_SR, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract1);
		logger.debug("The request # is--->>>>"+serviceRequestResult.getServiceRequest().getServiceRequestNumber());
		
		logger.debug("Partner id-------->>>>"+request.getParameter("partnerId"));
		logger.debug("organizationID ------->>>>"+request.getParameter("organizationID"));
		logger.debug("NewChildSR --------------->>>>>>> "+serviceRequestResult.getNewChildSR());
		CreateChildSRForm createChildSRDetailForm = new CreateChildSRForm();		
		createChildSRDetailForm.setServiceRequest(serviceRequestResult.getServiceRequest());		
		
		String productTLINew = serviceRequestResult.getServiceRequest().getAsset().getProductTLI();
		if(!StringUtil.isStringEmpty(productTLINew)){
			serviceRequestResult.getServiceRequest().getAsset().setProductImageURL(ControllerUtil.retrieveProductImageUrl(productImageService, productTLINew));
		}
		float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)).floatValue();
		createChildSRDetailForm.setTimezoneOffset(timezoneOffset);
		
		Map<String, String> fccCodeListLOV = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.CALL_BACK_FCC.getValue(), null,
				serviceRequestLocaleService, locale);
		if(fseUser)
		{
			fseAddressLink = true;
		}
		
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		String recommendedPartXML = xmlOutputGenerator.convertRecommendedPartToXML(serviceRequestResult.getServiceRequest().getRecommendedParts());
		model.addAttribute("FSCDoNotShowAddress",fseAddressLink);
		model.addAttribute("fccCodeListLOV", fccCodeListLOV);
		model.addAttribute("createChildSRDetailForm", createChildSRDetailForm);
		model.addAttribute("accountId", request.getParameter("partnerId"));
		model.addAttribute("organizationID", request.getParameter("organizationID"));
		model.addAttribute("newChildSR", serviceRequestResult.getNewChildSR());
		model.addAttribute("recommendedPartXML", recommendedPartXML);
		
		//
		logger.debug("Insdie the creatre page");
		
		return "serviceRequest/createChildSR";
	}
	
	@RequestMapping(params = "action=updateORCancelChildSRDetailUpdateURL")
	public void updateChildSRDetail(ActionRequest request, ActionResponse response, Model model,
			@ModelAttribute("createChildSRDetailForm") CreateChildSRForm createChildSRForm) throws Exception{
		String returnPage = "";		
		ChildSRContract childSRContract = new ChildSRContract();
		childSRContract.setServiceRequest(createChildSRForm.getServiceRequest());
		childSRContract.setActivity(createChildSRForm.getActivity());
		childSRContract.setChildSRStatus(createChildSRForm.getChildSRStatus());
		childSRContract.setFccCodeListLOV(createChildSRForm.getFccCodeListLOV());
		String messageCode = null;
		boolean success = false;		
			try{
				CreateChildSRResult result = requestWebService.createChildSR(childSRContract);
				success = result.isSuccess();
			}catch(Exception e){
				messageCode = "exception.siebel.updateClaimException";
				String errorMessage = ExceptionUtil.getLocalizedExceptionMessage(messageCode, request.getLocale(),e);
				MailUtil.sendEmail("shiva.juluru@perficient.com", "Error while updating Service Request Activity", errorMessage, false);
				MailUtil.sendEmail("dan.wellborn@perficient.com", "Error while updating Service Request Activity", errorMessage, false);									
				ServiceStatusUtil.checkServiceStatus(model,messageCode , request.getLocale(), true);
				return;
			}
			if(success){
				if(createChildSRForm.getChildSRStatus().equalsIgnoreCase("Ready For Service")){
				messageCode = "message.createChildSR.Success";			
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
				}
				else{
					messageCode = "message.createChildSR.Cancel";			
					ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
				}
				response.setRenderParameter("action", returnPage);
			}else{
				messageCode = "exception.siebel.updateClaimException";
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
				response.setRenderParameter("action", returnPage);
			}	
			logger.debug("Create Child SR ends----->>>>");
	}
	

	@RequestMapping(params = "action=showUpdateRequestPage")
	public String showUpdateRequestPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		
		ActivityDetailContract contract = ContractFactory.createActivityDetailContract(request);
		contract.setDebriefFlag(false);
		String contactId =  PortalSessionUtil.getContactId(request.getPortletSession());
		String requestFromPage = request.getParameter("requestFromPage");
			
		ServiceRequestDetailForm form = new ServiceRequestDetailForm();
		
		//retrieve grid settings for ordered parts grid
		List<UserGridSettingResult> gridSettingList = new ArrayList<UserGridSettingResult>();
		UserGridSettingResult gridSettingResult= new UserGridSettingResult(); 
		gridSettingController.retrieveGridSetting("gridRDVOrderedPartsTable", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		gridSettingList.add(gridSettingResult);
		
		//retrieve grid settings for parts to be returned grid
 
		gridSettingController.retrieveGridSetting("gridRDVbeReturnedParts", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		gridSettingList.add(gridSettingResult);
		
		//retrieve grid settings for recommended parts grid
		 
		gridSettingController.retrieveGridSetting("gridRDURecommendPart", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		gridSettingList.add(gridSettingResult);
		
		model.addAttribute("gridSettingList",gridSettingList);
		
		long timeBeforeCall=System.currentTimeMillis();		
		ActivityDetailResult activityDetailResult = partnerRequestsService.retrieveActivityDetail(contract);
		ObjectDebugUtil.printObjectContent(contract, logger);
		
		long timeAfterCall=System.currentTimeMillis();
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SERVICE_REQUEST_UPDATE_PAGE, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
		if(activityDetailResult==null) {
			throw new IllegalArgumentException("Failed to open Requests Update page: <br/> claimDetailResult is null");
		}
		if(activityDetailResult.getActivity() == null){
			throw new IllegalArgumentException("Failed to open Requests Update page: <br/> Activity is null");
		}
		if(activityDetailResult.getActivity().getServiceRequest() == null){
			throw new IllegalArgumentException("Failed to open Requests Update page: <br/> ServiceRequest is null");
		}
		if(activityDetailResult.getActivity().getServiceRequest().getAsset() == null){
			throw new IllegalArgumentException("Failed to open Requests Update page: <br/> Asset is null");
		}
		
		Activity activity = activityDetailResult.getActivity();
		
		Locale locale = request.getLocale();
		ControllerUtil.localizeActivity(activity, serviceRequestLocaleService, locale);
		//localize order part
		if (activity.getOrderPartList() != null) {
			ControllerUtil.batchLocalizePart(activity.getOrderPartList(), serviceRequestLocaleService, locale);
		}
		if (activity.getReturnPartList() != null) {
			ControllerUtil.batchLocalizePart(activity.getReturnPartList(), serviceRequestLocaleService, locale);
		}
		//localize additional payments
		if(activity.getAdditionalPaymentRequestList() != null){
			ControllerUtil.batchLocalizeAdditionPayments(activity.getAdditionalPaymentRequestList(), serviceRequestLocaleService, locale);
		}	
		logger.debug("ShipTo Default---->>>>"+activity.getAddressStatus());	
		logger.debug("ServiceType---->>>>"+activity.getActivityType().getValue());
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		Boolean shipToDefault = ((activity.getAddressStatus().equalsIgnoreCase(LexmarkConstants.SHIP_TO_CUSTOMER)&&(activity.getActivityType().getValue().equalsIgnoreCase(LexmarkConstants.CONSUMABLE_SVC_PART)))?false:true);
		logger.debug("Final ShipToDefault is-->>>"+shipToDefault.toString());
		String recommendedPartListXML = xmlOutputGenerator.convertRecommendedPartListToXML(activity.getRecommendedPartList(),shipToDefault);
		String activityNoteListXML = xmlOutputGenerator.convertNoteListToXML(activity.getActivityNoteList(), contactId);
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("retrievePartnerAddressListURL");
		
		//get image url
		String productTLI = activity.getServiceRequest().getAsset().getProductTLI();
		if(!StringUtil.isStringEmpty(productTLI)){
			activity.getServiceRequest().getAsset().setProductImageURL(ControllerUtil.retrieveProductImageUrl(productImageService, productTLI));
		}
		
		//address status 
		List<String> addressStatusList = Arrays.asList(LexmarkConstants.ADDRESS_STATUS_DO_NOT_SHIP_PARTS,LexmarkConstants.ADDRESS_STATUS_SHIP_TO_CUSTOMER, LexmarkConstants.ADDRESS_STATUS_SHIP_TO_SERVICE_PARTNER, LexmarkConstants.ADDRESS_STATUS_PARTNER_TO_PROVIDE);
		/*Added by sankha for LEX:AIR00068124 start*/
		convertCustomerAccount(activity);
		/*End*/
		//account contact information
		form.setContactId(contactId);
		form.setFirstName(PortalSessionUtil.getFirstName(request.getPortletSession()));
		form.setLastName(PortalSessionUtil.getLastName(request.getPortletSession()));
		form.setActivity(activity);
		
		List<TechnicianInstruction> technicianInstructions = activity.getServiceInstructionList();
		if (technicianInstructions != null && !technicianInstructions.isEmpty()) {
			form.setTechnicianInformationListXML(
					xmlOutputGenerator.convertTechnicianInformationListToXML(technicianInstructions));
		}
		form.setRecommendedPartsXML(recommendedPartListXML);
		form.setNotesXML(activityNoteListXML);
		form.setAddressStatusList(addressStatusList);
		form.refreshSubmitToken(request);
		form.setRequestFromPage(requestFromPage);
		
		Map<String, String> activityStatusList = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue(), null, globalService,serviceRequestLocaleService,request.getLocale());
		form.setActivityStatusList(activityStatusList);
		
		List<PartLineItem> orderPartList = activity.getOrderPartList();
		if (orderPartList != null && !orderPartList.isEmpty())
			{form.setOrderPartsXML(xmlOutputGenerator.convertOrderPartListToXML(orderPartList));}
		List<PartLineItem> returnPartList = activity.getReturnPartList();
		if (returnPartList != null && !returnPartList.isEmpty())
			{form.setReturnPartsXML(xmlOutputGenerator.convertReturnPartListToXML(returnPartList, true));}
		form.setPartnerAddressListURL(resURL.toString());
		float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)).floatValue();
		form.setTimezoneOffset(timezoneOffset);
		model.addAttribute("serviceRequestDetailForm", form);
		model.addAttribute("attachmentFormDisplay", form);
		PortletSession session = request.getPortletSession();
		if(session!=null&& session.getAttribute("attachmentList")!=null){
			session.removeAttribute("attachmentList");
		}
		
		if(shipToDefault){
			logger.debug("shipToDefault == true");
			model.addAttribute("ShipToDefault", "true");
		}
		else{
			logger.debug("shipToDefault == false");
			model.addAttribute("ShipToDefault", "false");
		}
		model.addAttribute("ShipToDefault",shipToDefault);
		return "serviceRequest/requestsDetailUpdate";
	}
	/*Added by sankha for LEX:AIR00068124 start*/	
	public void convertCustomerAccount(Activity activity){
		
		activity.getCustomerAccount().getAddress().setAddressName(XMLEncodeUtil.escapeXML(activity.getCustomerAccount().getAddress().getAddressName()));
		activity.getCustomerAccount().getAddress().setAddressLine1(XMLEncodeUtil.escapeXML(activity.getCustomerAccount().getAddress().getAddressLine1()));
		activity.getCustomerAccount().getAddress().setAddressLine2(XMLEncodeUtil.escapeXML(activity.getCustomerAccount().getAddress().getAddressLine2()));
		activity.getCustomerAccount().getAddress().setAddressLine3(XMLEncodeUtil.escapeXML(activity.getCustomerAccount().getAddress().getAddressLine3()));
		activity.getCustomerAccount().getAddress().setAddressLine4(XMLEncodeUtil.escapeXML(activity.getCustomerAccount().getAddress().getAddressLine4()));
		activity.getCustomerAccount().getAddress().setCity(XMLEncodeUtil.escapeXML(activity.getCustomerAccount().getAddress().getCity()));
		activity.getCustomerAccount().getAddress().setState(XMLEncodeUtil.escapeXML(activity.getCustomerAccount().getAddress().getState()));
		activity.getCustomerAccount().getAddress().setProvince(XMLEncodeUtil.escapeXML(activity.getCustomerAccount().getAddress().getProvince()));
		activity.getCustomerAccount().getAddress().setCountry(XMLEncodeUtil.escapeXML(activity.getCustomerAccount().getAddress().getCountry()));
		
	}
	
	@RequestMapping(params="action=showUpdateNotePopup")
	public String showUpdateNotePopup(Model model, 
									  RenderRequest request, 
									  RenderResponse response){
		
		String noteDate = request.getParameter("noteDate");
		String noteAuthor = request.getParameter("noteAuthor");
		String noteDetail = request.getParameter("noteDetail");
		String rowId = request.getParameter("rowId");
		String handleGridFlag = request.getParameter("handleGridFlag");
		
		
		if (StringUtil.isStringEmpty(noteDate)) {
			noteDate = DateLocalizationUtil.localizeDateTime(new java.util.Date(), false, request.getLocale());
		}
		if (StringUtil.isStringEmpty(noteAuthor)) {
			String firstName = PortalSessionUtil.getFirstName(request.getPortletSession());
			String lastName = PortalSessionUtil.getLastName(request.getPortletSession());
			noteAuthor = firstName+" "+lastName;
		}
		if ("update".equals(handleGridFlag)) {
			model.addAttribute("rowId",rowId);
		}
		model.addAttribute("handleGridFlag",handleGridFlag);
		model.addAttribute("noteDate",noteDate);
		model.addAttribute("noteAuthor", noteAuthor);
		model.addAttribute("noteDetail",noteDetail);
		return "claims/updateNote";
	}
	
	
	
	@RequestMapping(params="action=updateRequestDetailURL")
	public void updateRequestDetail(Model model, 
			  					   ActionRequest request, 
			  					   ActionResponse response,
			  					   @ModelAttribute("serviceRequestDetailForm") ServiceRequestDetailForm serviceRequestDetailForm) throws Exception{
		logger.debug("[START] updateRequestDetail");
		if(PortalSessionUtil.isDuplicatedSubmit(request, serviceRequestDetailForm)){
			String returnPage ="";
			if(serviceRequestDetailForm.getRequestFromPage().equalsIgnoreCase("requestsListView")){
				returnPage = "";
			}else if(serviceRequestDetailForm.getRequestFromPage().equalsIgnoreCase("requestsDetailView")){
				returnPage = "showRequestDetailPage";
			}
			response.setRenderParameter("action", returnPage);
			return;
		}
		// added for LEX:AIR00062693		
			if(serviceRequestDetailForm.getActivity().getShipToAddress().getAddressId().equalsIgnoreCase("-1,")){
				logger.debug("*********"+"Inside addressId -1");
				serviceRequestDetailForm.getActivity().getShipToAddress().setAddressId(null);
				serviceRequestDetailForm.getActivity().getShipToAddress().setNewAddressFlag("true");
			}		
		transformServiceRequestDetailForm(request,serviceRequestDetailForm );
		RequestUpdateContract contract = new RequestUpdateContract();
		logger.debug("serviceRequestDetailForm.getActivity().getServiceProviderStatus()------------ "+serviceRequestDetailForm.getActivity().getServiceProviderStatus());		
		contract.setActivity(serviceRequestDetailForm.getActivity());
		boolean success =false;
		
		String serviceRequestId = request.getParameter("serviceRequestId");
		String activityId = request.getParameter("activityId");
		String messageCode = null;
		
		try{
			RequestUpdateResult result = requestWebService.updateRequest(contract);
			success = result.isSuccess();
		}catch(Exception e){
			messageCode = "exception.siebel.updateClaimException";
				
			String errorMessage = ExceptionUtil.getLocalizedExceptionMessage(messageCode, request.getLocale(),e);
			MailUtil.sendEmail("shiva.juluru@perficient.com", "Error while updating Service Request Activity", errorMessage, false);
			MailUtil.sendEmail("dan.wellborn@perficient.com", "Error while updating Service Request Activity", errorMessage, false);
				
			response.setRenderParameter("serviceRequestId" , serviceRequestId);
			response.setRenderParameter("activityId", activityId);
			response.setRenderParameter("requestFromPage", serviceRequestDetailForm.getRequestFromPage());
			response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET,
					String.valueOf(serviceRequestDetailForm.getTimezoneOffset()));
			ServiceStatusUtil.checkServiceStatus(model,messageCode , request.getLocale(), true);
			response.setRenderParameter("action", "showUpdateRequestPage");
			return;
		}

		response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET,
				String.valueOf(serviceRequestDetailForm.getTimezoneOffset()));
		if(success){
			messageCode = "message.requestUpdate.updateSuccess";
			response.setRenderParameter("serviceRequestId" , serviceRequestId);
			response.setRenderParameter("activityId", activityId);
			
			ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			String returnPage ="";
			if(serviceRequestDetailForm.getRequestFromPage().equalsIgnoreCase("requestsListView")){
				returnPage = "";
			}else if(serviceRequestDetailForm.getRequestFromPage().equalsIgnoreCase("requestsDetailView")){
				returnPage = "showRequestDetailPage";
			}
			response.setRenderParameter("action", returnPage);
		}else{
			messageCode = "exception.requestUpdate.updateFailed";
			response.setRenderParameter("serviceRequestId" , serviceRequestId);
			response.setRenderParameter("activityId", activityId);
			response.setRenderParameter("requestFromPage", serviceRequestDetailForm.getRequestFromPage());
			ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			response.setRenderParameter("action", "showUpdateRequestPage");
		}
		
		//calling aMind method..
		//Get all attachment names
		PortletSession session = request.getPortletSession();
		
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		
		if(attachmentList == null) {
			attachmentList = new ArrayList<Attachment>();
			logger.debug("--------------------- We dont have anything as attachment Request Update---------------------------");
		}else{
			if(attachmentList.size()>0){
		for(int i=0;i<attachmentList.size();i++){
			logger.debug("---------------- Printing attachment name Request Update--------------");
			logger.debug("----------"+attachmentList.get(i).getAttachmentName());
		}
		
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		AttachmentContract attachmentContract = new AttachmentContract();
		attachmentContract.setSessionHandle(crmSessionHandle);
		logger.debug("------------------------------- activityid we are sending Request Update----------------------"+serviceRequestDetailForm.getActivity().getActivityId());
		attachmentContract.setIdentifier(serviceRequestDetailForm.getActivity().getActivityId());
		attachmentContract.setRequestType("Sr Update");
		attachmentContract.setAttachments(attachmentList);
		logger.debug("----------------- crmSessionHandle value is "+crmSessionHandle);
		
		logger.debug("-------------------------- Calling attachment service Request Update ----------------------");
		attachmentService.uploadAttachments(attachmentContract);
		logger.debug("-------------------------- Amind method call done Request Update ---------------------------");
		}
			else{
				logger.debug("--------------------- We dont have anything as attachment List Size is Zero Request Update---------------------------");
			}
		}
		if(session!=null&& session.getAttribute("attachmentList")!=null){
		session.removeAttribute("attachmentList");
		}
		
		logger.debug("[END] updateRequestDetail");
	}
	
	@RequestMapping(params="action=cancelRequestUpdate")
	public void cancelRequestUpdate(Model model, 
			  					   ActionRequest request, 
			  					   ActionResponse response,
			  					   @ModelAttribute("serviceRequestDetailForm") ServiceRequestDetailForm serviceRequestDetailForm) throws Exception{
		logger.debug("[START] cancelRequestUpdate");
		String returnPage = "";
		if(serviceRequestDetailForm.getRequestFromPage().equalsIgnoreCase("requestsListView")){
			returnPage = "";
		}else if(serviceRequestDetailForm.getRequestFromPage().equalsIgnoreCase("requestsDetailView")){
			String serviceRequestId = serviceRequestDetailForm.getActivity().getServiceRequest().getId();
			String activityId = serviceRequestDetailForm.getActivity().getActivityId();
			response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET,
					String.valueOf(serviceRequestDetailForm.getTimezoneOffset()));
			response.setRenderParameter("serviceRequestId" , serviceRequestId);
			response.setRenderParameter("activityId", activityId);
			returnPage ="showRequestDetailPage";
		}
		PortletSession session = request.getPortletSession();
		if(session!=null&& session.getAttribute("attachmentList")!=null){
			session.removeAttribute("attachmentList");
		}
		response.setRenderParameter("action", returnPage);
		
		logger.debug("[END] cancelRequestUpdate");
	}
	
	private void transformServiceRequestDetailForm(ActionRequest request, ServiceRequestDetailForm serviceRequestDetailForm) throws Exception {
		Activity requestToBeUpdated = serviceRequestDetailForm.getActivity();
		requestToBeUpdated.getServiceRequest().setRequestor(PortalSessionUtil.retrieveLoginAccountContact(request));
		requestToBeUpdated.setServiceProviderReferenceNumber(serviceRequestDetailForm.getServiceProviderReferenceNumber());
		ControllerUtil.formatHTMLTagForNotes(requestToBeUpdated.getActivityNoteList());
		setTechinicianName(serviceRequestDetailForm);
		
		if(serviceRequestDetailForm.getCustomerRequestedResponseStr()!= null & serviceRequestDetailForm.getCustomerRequestedResponseStr()!= ""){
			Date customerRequestedResponseGMT = DateLocalizationUtil.parseDate(
				serviceRequestDetailForm.getCustomerRequestedResponseStr() + LexmarkConstants.ZERO_SECOND, true, request.getLocale());
			TimezoneUtil.adjustDate(customerRequestedResponseGMT, serviceRequestDetailForm.getTimezoneOffset());
			requestToBeUpdated.setCustomerRequestedResponseDate(customerRequestedResponseGMT);
		}
		
		if(serviceRequestDetailForm.getEstimatedTimeofArrivalStr()!= null & serviceRequestDetailForm.getEstimatedTimeofArrivalStr()!= "" ){
			Date estimatedTimeofArrivalGMT = DateLocalizationUtil.parseDate(
				serviceRequestDetailForm.getEstimatedTimeofArrivalStr() + LexmarkConstants.ZERO_SECOND, true, request.getLocale());
			TimezoneUtil.adjustDate(estimatedTimeofArrivalGMT, serviceRequestDetailForm.getTimezoneOffset());
			requestToBeUpdated.setEstimatedArrivalTime(estimatedTimeofArrivalGMT);
		}
		
		if(serviceRequestDetailForm.getStatusAsOfStr()!= null & serviceRequestDetailForm.getStatusAsOfStr()!= ""){
			Date statusAsOfGMT = DateLocalizationUtil.parseDate(
				serviceRequestDetailForm.getStatusAsOfStr() + LexmarkConstants.ZERO_SECOND, true, request.getLocale());
			TimezoneUtil.adjustDate(statusAsOfGMT, serviceRequestDetailForm.getTimezoneOffset());
			requestToBeUpdated.setStatusUpdateDate(statusAsOfGMT);
		}
	}
	
	private void setTechinicianName(ServiceRequestDetailForm serviceRequestDetailForm) {
		String technicianFullName = serviceRequestDetailForm.getTechnicianFullName();
		AccountContact contact = serviceRequestDetailForm.getActivity().getTechnician();
		if(!StringUtil.isStringEmpty(technicianFullName)){
			if(contact == null){
				contact = new AccountContact();
				serviceRequestDetailForm.getActivity().setTechnician(contact);
			}
			if(!"other".equals(technicianFullName)){
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
				contact.setNewContactFlag(false);
				contact.setUpdateContactFlag(true);
			}
			else{
				contact.setNewContactFlag(true);
				contact.setUpdateContactFlag(false);			
			}			
		}
	}
//	added by for CI5 attachment feature
	
	@RequestMapping(params="action=addAttachmentsRequestUpdate")
	public void addAttachment(Model model, ActionRequest request, ActionResponse response,
			@ModelAttribute("attachmentFormDisplay") ServiceRequestDetailForm serviceRequestDetailForm) throws Exception {
		logger.debug("Inside fileUploadmethod ");
		logger.debug("Inside fileUploadmethod "+ serviceRequestDetailForm);
		logger.debug("Inside fileUploadmethod "+ serviceRequestDetailForm.getFileData());
		
		
		PortletSession session = request.getPortletSession();
		Long fileSize=serviceRequestDetailForm.getFileData().getSize();

		// Size For display
		
		double fileSizeDisplay=serviceRequestDetailForm.getFileData().getSize();
		logger.debug("File Size is " + fileSizeDisplay);
		fileSizeDisplay=fileSizeDisplay/1024;
		BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
		roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
		logger.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
		
		FileItem fileItem=serviceRequestDetailForm.getFileData().getFileItem();
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
		String inputFileType=serviceRequestDetailForm.getFileData().getContentType();
		logger.debug("inputFileType is :::  :: " + inputFileType);
		String fileExt = inputFileName.substring(inputFileName.lastIndexOf(".")+1, inputFileName.length()).toLowerCase();
		logger.debug("fileExt is :::  :: " + fileExt);

		boolean fileSizeInValid=false;
		
		if(attachmentFileType.indexOf(fileExt)==-1){
			fileSizeInValid=true;
			logger.debug("File Type is Not Valid :: fileSizeInValid !!!! "+fileSizeInValid);
		}
		
		if(fileSize<0) {throw new Exception("File Size is empty!!!!!!!");}
		else 
		{
			if(fileSize>Long.valueOf(strFileSize)){
				errorMessage.append(maxFileSize_Message);
				maxFileSize=true;
				logger.debug("Not Attached Filename  File Size is More Than 1 MB  maxFileSize !!!! "+maxFileSize + " File Name is :: "+inputFileName);

				serviceRequestDetailForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
					serviceRequestDetailForm.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
				
			}// File Type Check
			else if(fileSizeInValid){
				errorMessage.append(attachmentFileTypeMessage);
				fileSizeInValid=true;
				logger.debug("File Type is Not Valid :: fileSizeInValid !!!! "+fileSizeInValid);

				serviceRequestDetailForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
					serviceRequestDetailForm.setFileCount(attachmentList.size());
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
			serviceRequestDetailForm.setAttachmentList(attachmentList);
			logger.debug("Attachment size is "+attachmentList.size());
			serviceRequestDetailForm.setFileCount(attachmentList.size());
			
			session.setAttribute("attachmentList", attachmentList);
			logger.debug("Attachment File Path :::: "+path);
			try {
				fileItem.write(new File(path + inputFileName));
			} catch (Exception e) {
				throw new Exception("Could not write file to drive!!!!!!!");
			}
			}
			else{
				logger.debug("Not Attached Filename already existing in the File share DUPLICATE !!!! "+inputFileName);
				serviceRequestDetailForm.setAttachmentList(attachmentList);
				logger.debug("Attachment size is "+attachmentList.size());
				serviceRequestDetailForm.setFileCount(attachmentList.size());
				
				session.setAttribute("attachmentList", attachmentList);
			}
		
				}
		}
			logger.debug("errorMessage is ::  "+errorMessage.toString());
			serviceRequestDetailForm.setErrorMessage(errorMessage.toString());		
			
		
		request.setAttribute("attachmentFormDisplay",serviceRequestDetailForm);
		model.addAttribute("attachmentFormDisplay",serviceRequestDetailForm);

		response.setRenderParameter("action", "attachDocumentTestRequestUpdate");
	}
	
	@RequestMapping(params = "action=attachDocumentTestRequestUpdate")
	public String attachDocument(Model model, RenderRequest request,
	RenderResponse response) throws Exception {

		return "serviceRequest/requestsDetailUpdate";
	}	
	
	@RequestMapping(params="action=removeAttachmentActionRequestUpdate")
	public void removeAttachment(ActionRequest request, ActionResponse response,
			@RequestParam("fileName") String fileName,@ModelAttribute("attachmentFormDisplay") ServiceRequestDetailForm serviceRequestDetailForm, Model model) throws Exception {
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
			// Invalidating the Attachment Session
			if(session!=null&& session.getAttribute("attachmentList")!=null){
				session.removeAttribute("attachmentList");
			}
			session.setAttribute("attachmentList", newAttachmentList);
			//just check if the file is still there
			for(Attachment attachment:newAttachmentList){
				logger.debug("@@@@@@@@@@@@@ After deleting the remaining filenames are "+attachment.getAttachmentName());
			}
		
			if(newAttachmentList.size()==0){
				newAttachmentList=null;
			}
			serviceRequestDetailForm.setErrorMessage("");	
		serviceRequestDetailForm.setAttachmentList(newAttachmentList);
		request.setAttribute("attachmentFormDisplay",serviceRequestDetailForm);
		model.addAttribute("attachmentFormDisplay",serviceRequestDetailForm);
		response.setRenderParameter("action", "removeDocumentRenderRequestUpdate");	
		
	}
	
	@RequestMapping(params = "action=removeDocumentRenderRequestUpdate")
	public String removeAttachmentRender(Model model, RenderRequest request,
	RenderResponse response) throws Exception {

		return "serviceRequest/requestsDetailUpdate";
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