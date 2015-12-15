/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ClaimRequestCreateController
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

import com.lexmark.contract.AttachmentContract;
import com.lexmark.service.api.CrmSessionHandle;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.ClaimUpdateContract;
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.contract.FRUPartListContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.contract.GlobalAssetDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.PartnerAddressListContract;
import com.lexmark.contract.PartnerFavoriteAddressUpdateContract;
import com.lexmark.contract.PartnerIndirectAccountListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.WarrantyClaimCreateContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.Debrief;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.ServiceRequestTypeEnum;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.form.ClaimDebriefForm;
import com.lexmark.form.ClaimRequestConfirmationForm;
import com.lexmark.form.CreateNewAddressForm;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.ClaimUpdateResult;
import com.lexmark.result.FRUPartDetailResult;
import com.lexmark.result.FRUPartListResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.GeographyListResult;
import com.lexmark.result.GlobalAssetDetailResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.PartnerAddressListResult;
import com.lexmark.result.PartnerClaimCreateIdResult;
import com.lexmark.result.PartnerFavoriteAddressUpdateResult;
import com.lexmark.result.PartnerIndirectAccountListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.WarrantyClaimCreateResult;
import com.lexmark.service.api.GeographyService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.ExceptionUtil;
import com.lexmark.util.MailUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XMLEncodeUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.webservice.api.ClaimService;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.impl.real.jdbc.GeographyServiceImpl;
import com.lexmark.util.ChangeMgmtConstant;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.portlet.GridSettingController;
import javax.portlet.PortletSession;
import com.lexmark.util.PerformanceUtil;

@Controller
@RequestMapping("VIEW")
@SessionAttributes("claimRequestConfirmationForm")
public class ClaimRequestCreateController {
	private static Logger logger = LogManager.getLogger(ClaimRequestCreateController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	
	@Autowired
	private PartnerRequestsService requestService;

	@Autowired
	private ClaimService claimWebService;

    @Autowired
    private GlobalService  globalService;
    
    @Autowired
    private ServiceRequestLocale serviceRequestLocaleService;
    
    @Autowired 
	private GeographyService geographyService; 
    
	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private ServiceRequestService serviceRequestService;
	
	@Resource
	private List<GeographyListResult> allCountryList;
	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ClaimRequestCreateController.class);
	
	private static final String MESSAGE_CLAIM_CREATE_RETRIEVE_PART = "message.claimCreate.retrievePart";
	private static final String EXCEPTION_CLAIM_CREATE_RETRIEVE_PART = "exception.claimCreate.retrievePart";
	// Getting the File Path from attachment properties file
	static ResourceBundle bundle = ResourceBundle.getBundle("attachment");
	private static String path=bundle.getString("ClaimCreateUploadFileDestination");
	
	private static String attachmentSize=bundle.getString("AttachmentSize");
	private static String strFileSize=bundle.getString("MaxFileSize");

	private static String maxFileSize_Message=bundle.getString("maxFileSizeMessage");
	private static String duplicateFile_Message=bundle.getString("duplicateFileMessage");
	private static String maxNumberOfFiles_Message=bundle.getString("maxNumberOfFilesMessage");

	// File Type Validation
	private static String attachmentFileType=bundle.getString("attachmentFileType");
	private static String attachmentFileTypeMessage=bundle.getString("attachmentFileTypeMessage");
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=showCreateClaimRequestPage")
	public String showCreateClaimPage(Model model, 
									  RenderRequest request, 
									  RenderResponse response)  throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), "showCreateClaimPage");
		String assetId = request.getParameter("assetId");
		String notMyPrinterFlag = request.getParameter("notMyPrinterFlag");
		String serialNumber = request.getParameter("serialNumber");
		String machineType = request.getParameter("machineType");
		String productTLI = request.getParameter("productTLI");
		String duplicateDevice = request.getParameter("duplicateDeviceFlag");
		logger.debug("duplicateDevice is ------  "+duplicateDevice);
		// ******* ADDITION start *******
		String mki = request.getParameter("mki");
		String serviceProvider = request.getParameter("serviceProvider");
		logger.debug("mki in show create claim page is "+mki);
		logger.debug("serviceProvider in show create claim page is "+serviceProvider);
		// ******* ADDITION end *******
		
		String currentContactId = PortalSessionUtil.getContactId(request.getPortletSession());
		String noteAuthorFirstName = PortalSessionUtil.getFirstName(request.getPortletSession());
		String noteAuthorLastName = PortalSessionUtil.getLastName(request.getPortletSession());
		String timezoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET,timezoneOffset);
		model.addAttribute("currentContactId", currentContactId);
		model.addAttribute("noteAuthorFirstName", noteAuthorFirstName);
		model.addAttribute("noteAuthorLastName", noteAuthorLastName);
		model.addAttribute("duplicateDevice", duplicateDevice);
		model.addAttribute("mki", mki);  // ******* ADDED
		model.addAttribute("serviceProvider", serviceProvider); // ******* ADDED
		Map<String, String> claimTypeMap = new HashMap<String, String>();
		//claimTypeMap.put("Maintenance Kit Install", "Maintainance Kit Install");
		//claimTypeMap.put("Support and Warranty", "Support and Warranty");		
		LocalizedSiebelLOVListContract localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract("PARTNER_CLAIM_TYPE", null, request.getLocale());
		LocalizedSiebelLOVListResult localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
		//Map<String, String> dbLOVMap = new HashMap<String, String>();
		if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
			for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
				claimTypeMap.put(localizedLOV.getValue(), localizedLOV.getName());
			}
		}
		logger.debug("db lov map for claim type is "+claimTypeMap);
		model.addAttribute("claimType", claimTypeMap);
		try{
			if (!"true".equals(notMyPrinterFlag)) {
				showClaimRequestSubmitPageDeviceFound(request, response, assetId, model);
			}
			else{
				showClaimRequestSubmitPageDeviceNotFound(
					request, response, assetId, serialNumber, machineType, productTLI, model);			
			}
		}catch(Exception e){
			throw new Exception(ExceptionUtil.getLocalizedExceptionMessage("exception.openCreateClaimFailed", request.getLocale(),e));
		}
		
		// Invalidating the Attachment Session
		PortletSession session = request.getPortletSession();
		if(session!=null&& session.getAttribute("attachmentList")!=null){
			session.removeAttribute("attachmentList");
		}
		Calendar startTime = Calendar.getInstance();
		PortalSessionUtil.setCreateClaimStartTime(request.getPortletSession(), startTime);
		LOGGER.exit(this.getClass().getSimpleName(), "showCreateClaimPage");
		return "claims/claimCreateSubmit";
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param claimForm 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=createClaimRequest")
	public void createClaimRequest(Model model, 
			  					   ActionRequest request, 
			  					   ActionResponse response,
			  					   @ModelAttribute("claimRequestConfirmationForm") ClaimRequestConfirmationForm claimForm) throws Exception{
		//Added for CI BRD13-10-01
		logger.debug("in create claim controller creating claim");
		logger.debug("Review flag value is ------------ "+claimForm.getActivity().isRequestLexmarkReviewFlag());
				String pageSubmitType = claimForm.getPageSubmitType();
				if(pageSubmitType.equalsIgnoreCase("draftClaimRequest")){
					logger.debug("in draft");
					submitSaveAsDraft(model, request, response, claimForm);
					
					}
				else{
					if(PortalSessionUtil.isDuplicatedSubmit(request, claimForm))
					{
						logger.debug("in duplicate");
						model.addAttribute("elapsedCreateTime", "");
						response.setRenderParameter("action", "retrieveClaimThankYouPage");
						return;
					}
					
					logger.debug("before form in model");
		model.addAttribute("claimRequestConfirmationForm", claimForm);
		model.addAttribute("attachmentFormDisplay", claimForm);
		logger.debug("after form in model");
		
		transformClaimForm(request, claimForm);
		logger.debug("problemCodes is "+request.getParameter("problemCodes"));
		String problemCodes = request.getParameter("problemCodes");
		claimForm.getActivity().getActualFailureCode().setValue(problemCodes);
		
		logger.debug("claim Type is ======================= "+request.getParameter("claimType"));
		String claimType = request.getParameter("claimType");
		claimForm.getActivity().setClaimCoveredService(claimType);
		// If block added as siebel wants the value to be blank if claim type is selected as "Warranty Claim"
		if("Warranty Claim".equalsIgnoreCase(claimType)){
			logger.debug("claim type selected is Warranty claim "+claimType);
			claimForm.getActivity().setClaimCoveredService("");
		}
		if(null != claimType && !"".equals(claimType.trim())){
			model.addAttribute("claimType", claimType);
		}
		else{
			model.addAttribute("claimType", "");
		}
		
		
		
		logger.debug("after transform form "+claimForm.getActivity().getActualFailureCode().getValue());
		
		Activity activity = claimForm.getActivity();
		
		//logger.debug("Problem Code Level is ------------------- "+activity.getDebrief().getActualFailureCode().getValue());

		Asset asset = claimForm.getAsset();
		if(activity.getServiceRequest() == null){
			activity.setServiceRequest(new ServiceRequest());
			
		}
		
		ListOfValues serviceRequestTypeLOV = new ListOfValues();
		serviceRequestTypeLOV.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_TYPE.getValue());
		serviceRequestTypeLOV.setValue(ServiceRequestTypeEnum.CLAIM_REQUEST.getValue());
		activity.getServiceRequest().setServiceRequestType(serviceRequestTypeLOV);
		activity.getServiceRequest().setRequestor(PortalSessionUtil.retrieveLoginAccountContact(request));
		activity.getServiceRequest().setAsset(asset);
		activity.setActivityDate(new Date());
		if(activity.getShipToAddress().getAddressId()!=null){
			if(activity.getShipToAddress().getAddressId().equals("-1")){
				activity.getShipToAddress().setAddressId("");
			}
		} //changes for CI-5 2098
		
		try{
			WarrantyClaimCreateContract warrantyClaimCreateContract = ContractFactory.createWarrantyClaimContract(request, activity);
			
			warrantyClaimCreateContract.setCreateNewCustomerAddressFlag(claimForm.getActivity().getNewCustomerAccountFlag());
			
			WarrantyClaimCreateResult result = claimWebService.createWarrantyClaim(warrantyClaimCreateContract);
			claimForm.getActivity().getServiceRequest().setServiceRequestNumber(result.getRequestNumber());
			
			//TODO: how to set the service request status
			claimForm.getActivity().getServiceRequest().setServiceRequestStatus("Pending");
			activity.getServiceRequest().setServiceRequestNumber(result.getRequestNumber());
		
			//Get all attachment names
			PortletSession session = request.getPortletSession();
			List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
			
			if(attachmentList == null) {
				attachmentList = new ArrayList<Attachment>();
			}else{
				if(attachmentList.size()>0){
			String inputClaimId=result.getRequestId();
									
			if(!inputClaimId.trim().equals("")){
          
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			AttachmentContract attachmentContract = new AttachmentContract();
			attachmentContract.setSessionHandle(crmSessionHandle);
			attachmentContract.setIdentifier(inputClaimId);
			
			attachmentContract.setRequestType("Claim Create");
			attachmentContract.setAttachments(attachmentList);
			attachmentService.uploadAttachments(attachmentContract);
			}
				}
			}
			if(session!=null&& session.getAttribute("attachmentList")!=null){
				session.removeAttribute("attachmentList");
				}
			
		}catch(Exception e){
			String errorMessage = ExceptionUtil.getLocalizedExceptionMessage("exception.submitCreateClaimFailed", request.getLocale(),e);
			MailUtil.sendEmail("shiva.juluru@perficient.com", "Error while creating Claim", errorMessage, false);
			MailUtil.sendEmail("dan.wellborn@perficient.com", "Error while creating Claim", errorMessage, false);

			throw new Exception(errorMessage);
		}
		Calendar startTime = PortalSessionUtil.getCreateClaimStartTime(request.getPortletSession());
		Calendar endTime = Calendar.getInstance();
		long milliseconds1 = startTime.getTimeInMillis();
		long milliseconds2 = endTime.getTimeInMillis();
		long diff = milliseconds2 - milliseconds1;
		logger.debug("*****ELAPSED TIME TO CREATE IN SECONDS: " + diff / 1000);
		model.addAttribute("elapsedCreateTime", Long.valueOf(diff / 1000));
		
		response.setRenderParameter("action", "retrieveClaimThankYouPage");
		}
	}
	


	/**
	 * @param request 
	 * @param claimForm 
	 * @throws Exception 
	 */
	private void transformClaimForm(ActionRequest request, ClaimRequestConfirmationForm claimForm) throws Exception {
		setAccountByNewCustomerAccountFlag(request, claimForm);
		setDates(request, claimForm);
		setTechinicianName(claimForm);
		ControllerUtil.formatHTMLTagForNotes(claimForm.getActivity().getActivityNoteList());
		Locale locale = request.getLocale();
		Activity newClaim = claimForm.getActivity();
		// localize activity
		ControllerUtil.localizeActivity(newClaim, serviceRequestLocaleService, locale);
		//localize partLineItem
		if (newClaim.getOrderPartList() != null) {
			ControllerUtil.batchLocalizePart(newClaim.getOrderPartList(), serviceRequestLocaleService, locale);
		}
		if (newClaim.getPendingPartList() != null) {
			ControllerUtil.batchLocalizePart(newClaim.getPendingPartList(), serviceRequestLocaleService, locale);
		}
		if (newClaim.getReturnPartList() != null) {
			ControllerUtil.batchLocalizePart(newClaim.getReturnPartList(), serviceRequestLocaleService, locale);
		}
		//localize additional payments
		if(newClaim.getAdditionalPaymentRequestList() != null){
			ControllerUtil.batchLocalizeAdditionPayments(newClaim.getAdditionalPaymentRequestList(), serviceRequestLocaleService, locale);
		}
	}
	
	/**
	 * @param request 
	 * @param claimForm 
	 * @throws Exception 
	 */
	private void setAccountByNewCustomerAccountFlag(ActionRequest request,
			  ClaimRequestConfirmationForm claimForm) throws Exception {
		if(claimForm.getActivity().getNewCustomerAccountFlag().equalsIgnoreCase("true")){//changed for CI-5 2098 // true if new a customer account, make sure only name is not empty.
			Account customerAccount = new Account();
			customerAccount.setAccountName(claimForm.getNewAccountName());
			claimForm.getActivity().setCustomerAccount(customerAccount);
		}else{
			claimForm.getActivity().getServiceRequest().setServiceAddress(claimForm.getActivity().getCustomerAccount().getAddress());	
		}
	}

	/**
	 * @param claimForm 
	 */
	private void setTechinicianName(ClaimRequestConfirmationForm claimForm) {
		String technicianFullName = claimForm.getTechnicianFullName();
		AccountContact contact = claimForm.getActivity().getTechnician();
		if(!StringUtil.isStringEmpty(technicianFullName)){
			if(contact == null){
				contact = new AccountContact();
				claimForm.getActivity().setTechnician(contact);
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

	/**
	 * @param request 
	 * @param claimForm 
	 * @throws ParseException 
	 */
	private void setDates(ActionRequest request,
						  ClaimRequestConfirmationForm claimForm) throws ParseException {
		String serviceStartDate = claimForm.getServiceStartDate();
		String serviceEndDate = claimForm.getServiceEndDate();
		String serviceRequestedDate = claimForm.getServiceRequestedDate();//Added for CI BRD13-10-07
		if(!StringUtil.isStringEmpty(serviceStartDate)){
			serviceStartDate += LexmarkConstants.ZERO_SECOND;
			Date serviceStartDateGMT = DateLocalizationUtil.parseDate(serviceStartDate, true, request.getLocale());
			TimezoneUtil.adjustDate(serviceStartDateGMT, claimForm.getTimezoneOffset());
			claimForm.getActivity().getDebrief().setServiceStartDate(serviceStartDateGMT);
		}
		if(!StringUtil.isStringEmpty(serviceEndDate)){
			serviceEndDate += LexmarkConstants.ZERO_SECOND;
			Date serviceEndDateGMT = DateLocalizationUtil.parseDate(serviceEndDate, true, request.getLocale());
			TimezoneUtil.adjustDate(serviceEndDateGMT, claimForm.getTimezoneOffset());
			claimForm.getActivity().getDebrief().setServiceEndDate(serviceEndDateGMT);
		}
		//Added for CI BRD13-10-07--STARTS
		if(!StringUtil.isStringEmpty(serviceRequestedDate)){
			serviceRequestedDate += LexmarkConstants.ZERO_SECOND;
			Date serviceRequestedDateGMT = DateLocalizationUtil.parseDate(serviceRequestedDate, true, request.getLocale());
			TimezoneUtil.adjustDate(serviceRequestedDateGMT, claimForm.getTimezoneOffset());
			claimForm.getActivity().getDebrief().setServiceRequestedDate(serviceRequestedDateGMT);
		}
		//Added for CI BRD13-10-07--ENDS
		ServiceRequest serviceRequest = claimForm.getActivity().getServiceRequest();
		if(serviceRequest == null){
			serviceRequest = new ServiceRequest();
			serviceRequest.setServiceRequestDate(new Date());
			claimForm.getActivity().setServiceRequest(serviceRequest);
		}
		else{
			serviceRequest.setServiceRequestDate(new Date());
		}
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping(params="action=showUpdateNotePopup")
	public String showUpdateNotePopup(Model model, 
									  RenderRequest request, 
									  RenderResponse response){
		
		String noteDate = request.getParameter("noteDate");
		String noteAuthor = request.getParameter("noteAuthor");
		String noteDetailId = request.getParameter("noteDetailId");
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
		model.addAttribute("noteDetailId",noteDetailId);
		return "claims/updateNote";
	}
	

	/**
	 * @param request 
	 * @param response 
	 * @param assetId 
	 * @param serialNumber 
	 * @param machineType 
	 * @param productTLI 
	 * @param model 
	 * @throws Exception 
	 */
	private void showClaimRequestSubmitPageDeviceNotFound(
			RenderRequest request, RenderResponse response, String assetId,
			String serialNumber, String machineType, String productTLI,
			Model model) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "showClaimRequestSubmitPageDeviceNotFound");
		ClaimRequestConfirmationForm form = initClaimRequestForm(request,response);
		List<Account> accntList = form.getPartnerIndirectAccountList();
		boolean accountFlag = false;
		for(int i=0; i<accntList.size(); i++){
			logger.debug(i+"th account name in showClaimRequestSubmitPageDeviceNotFound is "+accntList.get(i).getAccountName());
			logger.debug(i+"th isMaintenanceKit flag in showClaimRequestSubmitPageDeviceNotFound is "+accntList.get(i).isMaintenanceKit());
			if(accntList.get(i).isMaintenanceKit()){
				accountFlag = true;
			}
		}
		Asset asset = null;
		if(StringUtil.isStringEmpty(assetId)){
			asset = createNewAsset(serialNumber, machineType, productTLI);
		}
		else{
			asset = getAssetById(assetId, request);
			asset.getPartnerAccount().setOrderPartsFlag(true);
			asset.setProductImageURL(ControllerUtil.retrieveProductImageUrl(productImageService, productTLI));
		}
		form.setAsset(asset);
		form.refreshSubmitToken(request);
		
		boolean exchangeflag = false;
		if(asset.getEntitlement()!=null){
			int entitlementsize=asset.getEntitlement().getServiceDetails().size();
			if(entitlementsize>0){
				for(int i=0;i<entitlementsize;i++){
					if(asset.getEntitlement().getServiceDetails().get(i).getServiceDetailDescription().equals("Onsite Exchange")) {
						exchangeflag=true;
					}
				}
			}
			
			
		}
		form.getActivity().setExchangeFlag(exchangeflag);
		//added by ragesree -2098 end
		/*Added by sankha for AIR65413 start*/
		if (PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(request.getPortletSession())) {
			logger.info("Additional Payment Request is enabled for the partner");
			form.setShowAdditionalPaymentRequestListFlag(true);
		} else {
			logger.info("Additional Payment Request is disabled for the partner");
			form.setShowAdditionalPaymentRequestListFlag(false);
		}
		/*Added by sankha for AIR65413 end*/
		
		// ******* ADDITION start *******
				String mki = request.getParameter("mki");
				logger.debug("mki value in showClaimRequestSubmitPageDeviceNotFound is "+mki);
				String serviceProvider = request.getParameter("serviceProvider");
				logger.debug("serviceProvider value in showClaimRequestSubmitPageDeviceNotFound is "+serviceProvider);
				String condition = createConditionForMKI(mki,serviceProvider,accountFlag,accntList,model);
				logger.debug("Condition returned is "+condition);
				model.addAttribute("condition", condition);
				//******* ADDITION end *******
		model.addAttribute("claimRequestConfirmationForm", form);
		model.addAttribute("attachmentFormDisplay", form);
		LOGGER.exit(this.getClass().getSimpleName(), "showClaimRequestSubmitPageDeviceNotFound");
	}

	/**
	 * @param serialNumber 
	 * @param machineType 
	 * @param productTLI 
	 * @return Asset 
	 */
	private Asset createNewAsset(String serialNumber, String machineType,
			String productTLI) {
		Asset asset = new Asset();
		asset.setSerialNumber(serialNumber);
		asset.setModelNumber(machineType);
		asset.setProductTLI(productTLI);
		asset.setProductImageURL(ControllerUtil.retrieveProductImageUrl(productImageService, productTLI));
		asset.setInstallAddress(new GenericAddress());
		return asset;
	}

	/**
	 * @param request 
	 * @param response 
	 * @param assetId 
	 * @param model 
	 * @throws Exception 
	 */
	private void showClaimRequestSubmitPageDeviceFound(RenderRequest request,
			RenderResponse response, String assetId, Model model) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "showClaimRequestSubmitPageDeviceFound");
		ClaimRequestConfirmationForm form = initClaimRequestForm(request,response);
		List<Account> accntList = form.getPartnerIndirectAccountList();
		boolean accountFlag = false;
		for(int i=0; i<accntList.size(); i++){
			//logger.debug(i+"th account name in showClaimRequestSubmitPageDeviceFound is "+accntList.get(i).getAccountName());
			//logger.debug(i+"th isMaintenanceKit flag in showClaimRequestSubmitPageDeviceFound is "+accntList.get(i).isMaintenanceKit());
			if(accntList.get(i).isMaintenanceKit()){
				accountFlag = true;
			}
		}
		
		Asset asset = getAssetById(assetId, request);
		if (asset.getPartnerAccount() != null) {
			asset.getPartnerAccount().setOrderPartsFlag(true);
		}
		
		String productTLI = asset.getProductTLI();
		asset.setProductImageURL(ControllerUtil.retrieveProductImageUrl(productImageService, productTLI));
		
		form.setAsset(asset);
		form.refreshSubmitToken(request);
		

		//added by ragesree -2098 start
		boolean exchangeflag = false;
		if(asset.getEntitlement()!=null){
			int entitlementsize=asset.getEntitlement().getServiceDetails().size();
			if(entitlementsize>0){
				for(int i=0;i<entitlementsize;i++){
					if(asset.getEntitlement().getServiceDetails().get(i).getServiceDetailDescription().equals("Onsite Exchange")) {
						exchangeflag=true;
					}
				}
			}
		}
		form.getActivity().setExchangeFlag(exchangeflag);
		//added by ragesree -2098 end
		/*Added by sankha for AIR65413 start*/
		if (PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(request.getPortletSession())) {
			logger.info("Additional Payment Request is enabled for the partner");
			form.setShowAdditionalPaymentRequestListFlag(true);
		} else {
			logger.info("Additional Payment Request is disabled for the partner");
			form.setShowAdditionalPaymentRequestListFlag(false);
		}
		/*Added by sankha for AIR65413 end*/
		// ******* ADDITION start *******
		String mki = request.getParameter("mki");
		logger.debug("mki value in showClaimRequestSubmitPageDeviceFound is "+mki);
		String serviceProvider = request.getParameter("serviceProvider");
		logger.debug("serviceProvider value in showClaimRequestSubmitPageDeviceFound is "+serviceProvider);
		String condition = createConditionForMKI(mki,serviceProvider,accountFlag,accntList,model);
		logger.debug("Condition returned is "+condition);
		model.addAttribute("condition", condition);
		//******* ADDITION end *******
		model.addAttribute("claimRequestConfirmationForm", form);
		model.addAttribute("attachmentFormDisplay", form);
		LOGGER.exit(this.getClass().getSimpleName(), "showClaimRequestSubmitPageDeviceFound");
	}
	

	/**
	 * @param request 
	 * @param response 
	 * @return ClaimRequestConfirmationForm 
	 * @throws Exception 
	 */
	private ClaimRequestConfirmationForm initClaimRequestForm(PortletRequest request,RenderResponse response) throws Exception {
		ClaimRequestConfirmationForm claimRequestConfirmationForm = new ClaimRequestConfirmationForm();
		Activity activity = new Activity();
		ServiceRequest serviceRequest = new ServiceRequest();
		AccountContact primaryContact = new AccountContact();
		serviceRequest.setPrimaryContact(primaryContact);
		activity.setServiceRequest(serviceRequest);
		
		AccountContact technician = new AccountContact();
		activity.setTechnician(technician);
		
		Debrief debrief = new Debrief();
		activity.setDebrief(debrief);
		
		List<AdditionalPaymentRequest> additionalPaymentRequestList = new ArrayList<AdditionalPaymentRequest>();
		activity.setAdditionalPaymentRequestList(additionalPaymentRequestList);
		
		GenericAddress shipToAddress = new GenericAddress();
		List<PartLineItem> orderPartList = new ArrayList<PartLineItem>();
		activity.setShipToAddress(shipToAddress);
		activity.setOrderPartList(orderPartList);
		
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("retrievePartnerAddressListURL");
		
		claimRequestConfirmationForm.setPartnerAddressListURL(resURL.toString());
		claimRequestConfirmationForm.setActivity(activity);
		
		initValuesInClaimRequestForm(request, claimRequestConfirmationForm);
		return claimRequestConfirmationForm;
	}
	

	/**
	 * @param request 
	 * @param claimRequestConfirmationForm 
	 * @throws Exception 
	 */
	private void initValuesInClaimRequestForm(PortletRequest request,
											  ClaimRequestConfirmationForm claimRequestConfirmationForm)
											  throws Exception {
		Map<String, String> problemCodes = 
			retrieveSiebelLovValuePairs(request, SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue());
		claimRequestConfirmationForm.setProblemCodeList(problemCodes);
		
		Map<String, String> partStatus = 
			retrieveSiebelLovValuePairs(request, SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue());
		claimRequestConfirmationForm.setPartStatusList(partStatus);
		
		Map<String, String> errorCode1 = 
			retrieveSiebelLovValuePairs(request, SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_1.getValue());
		claimRequestConfirmationForm.setErrorCode1List(errorCode1);
		
		Map<String, String> carriers = 
			retrieveSiebelLovValuePairs(request, SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue());
		claimRequestConfirmationForm.setCarriers(carriers);
		
		Map<String, String> resolutionCode = 
			retrieveSiebelLovValuePairs(request, SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue());
		
		// adding to show only new values for resolution code start  ========================================
					SiebelLOVListContract lovListcontract = ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue(), null);
					SiebelLOVListResult listResult;
					listResult = globalService.retrieveSiebelLOVList(lovListcontract);
					/*for(int i=0; i<listResult.getLovList().size();i++){
						logger.debug(i+"th value is "+listResult.getLovList().get(i).getValue());
					}*/
					
					// retrieve map from db start
					LocalizedSiebelLOVListContract localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_RESOLUTION_CODE.getValue(), null, request.getLocale());
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
					
		claimRequestConfirmationForm.setResolutionCodeList(resolutionCodeMap);
		
		// adding to show only new values for part status code start  ========================================
				lovListcontract = ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(), null);						 
				listResult = globalService.retrieveSiebelLOVList(lovListcontract);
				/*for(int i=0; i<listResult.getLovList().size();i++){
					logger.debug(i+"th value is "+listResult.getLovList().get(i).getValue());
				}*/
				
				// retrieve map from db start
				 localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_PART_STATUS.getValue(), null, request.getLocale());
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
				
				claimRequestConfirmationForm.setPartStatusList(partStatusMap);
				
				// retrieve map from db end
				// adding to show only new values for part status code end ===============================================
		
		Map<String, String> paymentTypes = 
			retrieveSiebelLovValuePairs(request, SiebelLocalizationOptionEnum.PARTNER_ADDITIONAL_PAYMENT_TYPE.getValue());
		claimRequestConfirmationForm.setPaymentTypes(paymentTypes);
		
		Map<String, String> workingConditionList = 
			retrieveSiebelLovValuePairs(request, SiebelLocalizationOptionEnum.PARTNER_DEVICE_CONDITION.getValue());
		claimRequestConfirmationForm.setWorkingConditionList(workingConditionList);
		
		List<Account> partnerIndirectAccountList = retrievePartnerAccounts(request);
		setPartnerAccountOrderPartFlagToTrue(partnerIndirectAccountList);
		claimRequestConfirmationForm.setPartnerIndirectAccountList(partnerIndirectAccountList);

	}
	
	/**
	 * @param request 
	 * @return List<Account> 
	 * @throws Exception 
	 */
	private List<Account> retrievePartnerAccounts(PortletRequest request) throws Exception {
		
		List<Account> accounts = new ArrayList<Account>();
		PartnerIndirectAccountListContract contract = 
			ContractFactory.createPartnerIndirectAccountListContract(request);
		LOGGER.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		LOGGER.info("end printing lex loggger");
		PartnerIndirectAccountListResult result = 
			requestService.retrievePartnerIndirectAccountList(contract);
		
		if(result != null && result.getAccountList() != null && result.getAccountList().size() > 0){
			accounts = result.getAccountList();
		}
		
		return accounts; 
	}

	/**
	 * @param request 
	 * @param lovListName 
	 * @return Map<String, String> 
	 * @throws Exception 
	 */
	private Map<String, String> retrieveSiebelLovValuePairs(PortletRequest request, 
															String lovListName) throws Exception {
		Map<String, String> result = 
			ControllerUtil.retrieveLocalizedLOVMapForSelection(lovListName, null,
											  	  globalService, 
											  	  serviceRequestLocaleService,
											      request.getLocale());
		return result;
	}
	
	

	/**
	 * @param assetId 
	 * @param request 
	 * @return Asset 
	 * @throws Exception 
	 */
	private Asset getAssetById(String assetId, PortletRequest request) throws Exception{
		LOGGER.enter("ClaimRequestCreateController", "getAssetById");
		logger.info("trying to get the asset by its id");
		GlobalAssetDetailContract contract = ContractFactory.createGlobalAssetDetailContract(assetId, request);
		Asset asset = null;
		
		
		GlobalAssetDetailResult detailResult = requestService.retrieveGlobalAssetDetail(contract); 
		if (detailResult != null && detailResult.getAsset() != null) {
			logger.info("the asset is not null");
			asset = detailResult.getAsset();
		}else{
			throw new IllegalArgumentException("Failed to open page: <br/> Asset is null");
		}
		LOGGER.exit("ClaimRequestCreateController", "getAssetById");
		return asset;
	}
	
	/**
	 * @param accountId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("setTechnicianInformation")
	public void setTechnicianInformation(@RequestParam("accountId") String accountId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "setTechnicianInformation");
		boolean success = false;
		StringBuilder technicianInfo =  new StringBuilder();
		
		try {
			technicianInfo.append("}");
			
			if(StringUtils.isNotEmpty(accountId)) {
				List<AccountContact> accountContactList = ControllerUtil.retrieveTechnicianListSorted(accountId, requestService);
				
				for(AccountContact accountContact : accountContactList){
					technicianInfo.append(
							accountContact.getLastName()+", "+
							accountContact.getFirstName()+"/"+
							accountContact.getContactId()+"}");
				}
			}
		
			success = true;
		} catch(Exception e){
			logger.debug("Exception"+e.getMessage()); 
			success = false;
		}
		String errorCode = success?"message.gerneral.success"
				:"exception.claimCreate.retrieveTechnicianlist";
		
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+technicianInfo.toString()+"\"");
		LOGGER.exit(this.getClass().getSimpleName(), "setTechnicianInformation");
	}
	
	/**
	 * @param accountId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("setShiptoAddress")
	public void setShiptoAddress(@RequestParam("accountId") String accountId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "setShiptoAddress");
		boolean success = false;
		StringBuilder shipToAddress = new StringBuilder();
		String createAddressFlag = "";
		
		try {
			List<Account> accountList = retrievePartnerAccounts(request);			
			for(Account account : accountList){				
				if(accountId != ""){					
					if(account.getAccountId().equals(accountId)){			
						
						createAddressFlag = (account.getCreateShipToAddressFlag() == null)?"false":"true"; //changed for CI-5 2098 // modified for LEX:AIR00061505
						createAddressFlag = createAddressFlag.equalsIgnoreCase("false")?"false":"true"; // added for for LEX:AIR00061505
						

						shipToAddress.append(account.getAddress().getPrimaryAddressID()!=null?account.getAddress().getPrimaryAddressID()+"/" :"/");
						shipToAddress.append(account.getAddress().getPrimaryAddressName()!=null?account.getAddress().getStoreFrontName()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryAddressName()!=null?account.getAddress().getPrimaryAddressName()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryAddressLine1()!=null?account.getAddress().getPrimaryAddressLine1()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryAddressLine1()!=null?account.getAddress().getPrimaryOfficeNumber()+"/":"/");

						shipToAddress.append(account.getAddress().getPrimaryAddressLine2()!=null?account.getAddress().getPrimaryAddressLine2()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryCity()!=null?account.getAddress().getPrimaryCity()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryCounty()!=null?account.getAddress().getPrimaryCounty()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryState()!=null?account.getAddress().getPrimaryState()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryProvince()!=null?account.getAddress().getPrimaryProvince()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryProvince()!=null?account.getAddress().getPrimaryRegion()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryCountry()!=null?account.getAddress().getPrimaryCountry()+"/":"/");
						shipToAddress.append(account.getAddress().getPrimaryPostalCode()!=null?account.getAddress().getPrimaryPostalCode()+"/":"/");
						shipToAddress.append("");
						/*shipToAddress.append(account.getAddress().getAddressId()!=null?account.getAddress().getAddressId()+"/" :"/");
						shipToAddress.append(account.getAddress().getAddressName()!=null?account.getAddress().getStoreFrontName()+"/":"/");
						shipToAddress.append(account.getAddress().getAddressName()!=null?account.getAddress().getAddressName()+"/":"/");
						shipToAddress.append(account.getAddress().getAddressLine1()!=null?account.getAddress().getAddressLine1()+"/":"/");
						shipToAddress.append(account.getAddress().getAddressLine1()!=null?account.getAddress().getOfficeNumber()+"/":"/");
						shipToAddress.append(account.getAddress().getAddressLine2()!=null?account.getAddress().getAddressLine2()+"/":"/");
						shipToAddress.append(account.getAddress().getCity()!=null?account.getAddress().getCity()+"/":"/");
						shipToAddress.append(account.getAddress().getStateProvince()!=null?account.getAddress().getCounty()+"/":"/");
						shipToAddress.append(account.getAddress().getState()!=null?account.getAddress().getState()+"/":"/");
						shipToAddress.append(account.getAddress().getProvince()!=null?account.getAddress().getProvince()+"/":"/");
						shipToAddress.append(account.getAddress().getProvince()!=null?account.getAddress().getRegion()+"/":"/");
						shipToAddress.append(account.getAddress().getCountry()!=null?account.getAddress().getCountry()+"/":"/");
						shipToAddress.append(account.getAddress().getPostalCode()!=null?account.getAddress().getPostalCode()+"/":"/");
						shipToAddress.append("");*/
						
					}
				}else{
					shipToAddress.append("");
				}
			}
			
			success = true;
		} catch (Exception e) {
			logger.debug("Exception"+e.getMessage()); 
			success = false;
		}
		String errorCode = success?"message.gerneral.success"
				:"exception.claimCreate.retrieveShiptoAddress";
		
		String result = createAddressFlag + "/" + shipToAddress.toString();
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+result+"\"");
		LOGGER.exit(this.getClass().getSimpleName(), "setShiptoAddress");
	}
	
	/**
	 * @param accountId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("retrieveOrganizationId")
	public void retrieveOrganizationId(@RequestParam("accountId") String accountId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "retrieveOrganizationId");
		boolean success = false;
		
		String organizationId="";
		if(accountId!=null && accountId!=""){
			try {
				List<Account> accountList = retrievePartnerAccounts(request);
				for(Account account : accountList){
					if(account.getAccountId().equals(accountId)){
						organizationId = account.getOrganizationID();
						break;
					}
				}
			} catch (Exception e) {
				logger.debug("Exception"+e.getMessage()); 
				LOGGER.debug("Exception in retrieving account ID");
			}
		}
		ServiceStatusUtil.responseResult(response, "message.gerneral.success", request.getLocale(),"\""+organizationId+"\"");
		LOGGER.exit(this.getClass().getSimpleName(), "retrieveOrganizationId");
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 */
	@RequestMapping(params = "action=showPartAndToolPage")
	public String showPartAndToolPage(RenderRequest request, RenderResponse response,Model model){
		ResourceURL resURL = response.createResourceURL();
		//June Release BRD 14_06_06
		String isMaintenanceKit = request.getParameter("isMaintenance");
		LOGGER.debug("isMaintenance--------->>>>"+isMaintenanceKit);
		if(isMaintenanceKit == null || isMaintenanceKit == "")
		{
			isMaintenanceKit = "false"; //Will return all the parts
		}
		model.addAttribute("isMaintenance", isMaintenanceKit);
		//June Release BRD 14_06_06 Ends
		resURL.setResourceID("showPartList");
		model.addAttribute("showPartList", resURL.toString());
		return "claims/partList";	
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("showPartList")
	public void showPartList(ResourceRequest request,ResourceResponse response) throws Exception{
		 
		String modelNumber = request.getParameter("modelNumber");
		////June Release BRD 14_06_06
		String isMaintenanceKit = request.getParameter("isMaintenance");
		LOGGER.debug("isMaintenance--->>>"+isMaintenanceKit);	
		FRUPartListContract contract = ContractFactory.createFRUPartListContract(modelNumber);
		contract.setMaintenanceKitValidation(Boolean.parseBoolean(isMaintenanceKit));
		//June Release BRD 14_06_06 Ends
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		FRUPartListResult result = new FRUPartListResult();
		try{
			Long startTime = System.currentTimeMillis();
			result = requestService.retrieveFRUPartList(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_FRUPARTLIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		}catch(Exception e){
			throw new Exception(ExceptionUtil.getLocalizedExceptionMessage(EXCEPTION_CLAIM_CREATE_RETRIEVE_PART, request.getLocale(),e));
		}
		List<Part> partList = result.getPartList();
		String xml = new XmlOutputGenerator(request.getLocale()).convertPartListToXML(partList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("retrievePart")
	public void retrievePart(ResourceRequest request,ResourceResponse response,Model model) throws Exception{
		try{
			FRUPartDetailContract contract = ContractFactory.createFRUPartDetailContract(request);
			String isMaintenanceKit = request.getParameter("isMaintenance");
			if(isMaintenanceKit == null || isMaintenanceKit == "")
			{
				isMaintenanceKit = "false"; 
			}
			LOGGER.debug("isMaintenance--->>>"+isMaintenanceKit);
			contract.setMaintenanceKitValidation(Boolean.parseBoolean(isMaintenanceKit));
			ObjectDebugUtil.printObjectContent(contract, logger);
			FRUPartDetailResult result = ControllerUtil.retrieveFRUPartPRF(requestService, contract);
			String partStr="" ;
			if(result.getPart() != null){
				Part part = formatPart(result.getPart());
				partStr += part.getPartNumber()+"///"+part.getPartName()+"///"+part.isReturnRequiredFlag()+"///"+part.getReplacementPartNumber()+"///"+part.getPartId();
			}
			ServiceStatusUtil.responseResult(response, MESSAGE_CLAIM_CREATE_RETRIEVE_PART, request.getLocale(), "\""+partStr+"\"");
		}catch(Exception e){
			logger.debug("Exception"+e.getMessage()); 
			ServiceStatusUtil.responseResult(response, EXCEPTION_CLAIM_CREATE_RETRIEVE_PART, request.getLocale());
		}
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="retrievePartnerAddressListURL")
	public void retrieveServiceAddressList(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), "retrieveServiceAddressList");
		
		String contextPath = request.getContextPath();
		PartnerAddressListContract contract = ContractFactory.createPartnerAddressListContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		PartnerAddressListResult result = requestService.retrievePartnerAddressList(contract);
		
		String addressXml="";
		
		addressXml = new XmlOutputGenerator(request.getLocale()).convertAddressListToXML(result.getAddressList(), 
				 result.getTotalCount(), contract.getStartRecordNumber(), contextPath);
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(addressXml);
		out.flush();
		out.close();
		LOGGER.exit(this.getClass().getSimpleName(), "retrieveServiceAddressList");
	}
	
	/**
	 * @param favoriteAddressId 
	 * @param accountId 
	 * @param flagStatus 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("setPartnerAddressFavouriteFlag")
	public void setPartnerAddressFavouriteFlag(@RequestParam("favoriteAddressId") String favoriteAddressId,
			@RequestParam("accountId") String accountId,
			@RequestParam("flagStatus") boolean flagStatus,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		try {			
			PartnerFavoriteAddressUpdateContract contract	=  ContractFactory.createPartnerFavoriteAddressUpdateContract(request);
			PartnerFavoriteAddressUpdateResult result;
			contract.setFavoriteAddressId(favoriteAddressId);
			contract.setPartnerAccountId(accountId);
			contract.setFavoriteFlag(!flagStatus);
			
			result = requestService.updatePartnerUserFavoriteAddress(contract);
			
			success = result.isResult();
		} catch (Exception e) {
			logger.debug("Exception"+e.getMessage()); 
			success = false;
		}
		String errorCode = success?"message.claimCreate.setPartnerAddressFavouriteFlag"
				:"exception.claimCreate.setPartnerAddressFavouriteFlag";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteAddressId+"\"");
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showCreateNewAddressPage")
	public String showCreateNewAddressPage(RenderRequest request,RenderResponse response,Model model)throws Exception {
		CreateNewAddressForm createNewAddressForm = new CreateNewAddressForm();
		GeographyListResult countryListResult = geographyService.getCountryDetails();
		if(countryListResult == null){
			throw new IllegalArgumentException("Failed to open Create New Address page: <br/> countryListResult is null");
		}
		List<GeographyCountryContract> countries = countryListResult.getCountryList();
		createNewAddressForm.setCountries(countries);
		String stateData = "<select id=\"state\" onChange=\"removeErrMsg();\"><option value=\"\">"+ControllerUtil.localizeMessage("claim.label.pleaseSelect", null, request.getLocale())+"</option></select>";
		model.addAttribute("stateData", stateData);
		model.addAttribute("createNewAddressForm", createNewAddressForm);

		return "claims/createNewAddress";
	}

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showSelectAddressPage")
	public String showSelectAddressPage(RenderRequest request,RenderResponse response,Model model)throws Exception {
		LOGGER.enter("ClaimRequestCreateController", "showSelectAddressPage");
		
		String createNewFlag = request.getParameter("createNewFlag");
		String accountId = request.getParameter("accountId");
		String partnerAddressListURL = request.getParameter("partnerAddressListURL");
		
		PortletSession session=request.getPortletSession();
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		if(accDetails != null){
			String accountName=accDetails.get("accountName");
			model.addAttribute("accountName", accountName);	
		}
		
		model.addAttribute("createNewFlag", createNewFlag);
		model.addAttribute("accountId", accountId);
		model.addAttribute("partnerAddressListURL", partnerAddressListURL);
		
		GridSettingController gridSetting = new GridSettingController();
		gridSetting.retrieveGridSetting("serviceAddressGridbox", request, response, model);
		LOGGER.exit("ClaimRequestCreateController", "showSelectAddressPage");
		response.setContentType("UTF-8");
		return "claims/addressListPopup";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("getState")
	public void getState(ResourceRequest request,ResourceResponse response, Model model) throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), "getState");
		String countryCode = request.getParameter("countryCode");
		if(countryCode==null||countryCode==""){
			String responseString="<select id=\"state\" onChange=\"removeErrMsg();\"><option value=\"\">"+ControllerUtil.localizeMessage("claim.label.pleaseSelect", null, request.getLocale())+"</option></select>";
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(responseString);
			out.flush();
			out.close();
		}else{
			GeographyListResult countryListResult = geographyService.getStateDetails(countryCode);
			List<GeographyStateContract> states = countryListResult.getStateList();
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			//changed for LEX:AIR00072122 
			out.print(getXmlContent(states,request.getLocale()));
			out.flush();
			out.close();
		}
		LOGGER.exit(this.getClass().getSimpleName(), "getState");
	}
	
	/**
	 * @param result 
	 * @param locale 
	 * @return String 
	 */
	private String getXmlContent(List<GeographyStateContract> result,Locale locale) {
		StringBuilder sb = new StringBuilder();
		if(result.size()!=0){
		sb.append("<select id=\"state\" onChange=\"removeErrMsg();\">");
		sb.append("<option value=\"\">Please Select</option>");
		for(int i=0;i<result.size();i++) {
										
				//added for LEX:AIR00072122 in 13.5
			sb.append("<option value=\""+ 	XMLEncodeUtil.escapeXML(result.get(i).getStateCode())  + "\">");
			if(result.get(i).getStateCode().equalsIgnoreCase("UA"))
			{
	 			sb.append(ControllerUtil.localizeMessage("state.value.unavailable", null,locale));

			}
			else {
 			sb.append(result.get(i).getStateName());
			}
			sb.append("</option>");
		
		   
		}
		sb.append("</select>");
		}else{
			sb.append("<select id=\"state\"><option value=\"nostate\">No State Available</option></select>");
		}
		return sb.toString();
	}
	
	//Regarding CR, User is allowed to add part and tools in despite of OrderPartsFlag.
	/**
	 * @param partnerIndirectAccountList 
	 */
	private void setPartnerAccountOrderPartFlagToTrue(List<Account> partnerIndirectAccountList){
		if(partnerIndirectAccountList==null || partnerIndirectAccountList.isEmpty())
		{
			return;
		}
		for(Account partnerAccount:partnerIndirectAccountList){
			partnerAccount.setOrderPartsFlag(true);
		}
	}
	
	//transform all " and ' for part name
	/**
	 * @param part 
	 * @return Part 
	 */
	private Part formatPart(Part part){
		if(part != null)
		{
			part.setPartName(StringUtil.formatStringForJS(part.getPartName()));
		}
		
		return part;
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param claimRequestConfirmationForm 
	 * @param claimRequestConfirmationFormClaim 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=addAttachmentsCreateCreateClaim")
	public void addAttachment(Model model, ActionRequest request, ActionResponse response,
			@ModelAttribute("attachmentFormDisplay") ClaimRequestConfirmationForm claimRequestConfirmationForm , 
			@ModelAttribute("claimRequestConfirmationForm") ClaimRequestConfirmationForm claimRequestConfirmationFormClaim) throws Exception {

		PortletSession session = request.getPortletSession();
		
		//put filename in the attachment list
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		
		Long fileSize=claimRequestConfirmationForm.getFileData().getSize();
		
		// Size For display
		
		double fileSizeDisplay=claimRequestConfirmationForm.getFileData().getSize();
		fileSizeDisplay=fileSizeDisplay/1024;
		BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
		roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
		
		
		FileItem fileItem=claimRequestConfirmationForm.getFileData().getFileItem();
		String displayFileName=returnFileName(fileItem.getName());
		StringBuffer errorMessage=new StringBuffer();
		boolean maxFileSize=false;
		
		// Setting for duplicate File
		boolean fileExists=false;
		boolean maxNumberOfFiles=false;
		
		// File Type Validation
		String fileExt = displayFileName.substring(displayFileName.lastIndexOf(".")+1, displayFileName.length()).toLowerCase();

		boolean fileSizeInValid=false;
		
		if(attachmentFileType.indexOf(fileExt)==-1){
			fileSizeInValid=true;
		}
		if(fileSize<0)
			{throw new Exception("File Size is empty!!!!!!!");}
		else 
		{
			if(fileSize>Long.valueOf(strFileSize)){
				errorMessage.append(maxFileSize_Message);
				maxFileSize=true;

				claimRequestConfirmationForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
				claimRequestConfirmationForm.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
				
			}// File Type Check
			else if(fileSizeInValid){
				errorMessage.append(attachmentFileTypeMessage);
				fileSizeInValid=true;

				claimRequestConfirmationForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
					claimRequestConfirmationForm.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
				
			}else{
			// Checking for Duplicate File and File Size
			if(attachmentList!=null){
				
				int maxAattachmentSize = Integer.valueOf(attachmentSize);
				if(attachmentList.size()<maxAattachmentSize){
			for(int i=0;i<attachmentList.size();i++)
			{
				Attachment Attachment_1=(Attachment)attachmentList.get(i);
				if(displayFileName.equals(Attachment_1.getDisplayAttachmentName())){
				errorMessage.append(duplicateFile_Message);				
				fileExists=true;
				break;
				}
			}
			}else{
				errorMessage.append(maxNumberOfFiles_Message);
				maxNumberOfFiles=true;
			}}
			else{
				attachmentList = new ArrayList<Attachment>();
			}
				
			if(!fileExists && !maxNumberOfFiles && !maxFileSize){
			Attachment fileAttachment = new Attachment();
			fileAttachment.setAttachmentName(displayFileName);
			fileAttachment.setDisplayAttachmentName(displayFileName);
			fileAttachment.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
			attachmentList.add(fileAttachment);
			claimRequestConfirmationForm.setAttachmentList(attachmentList);
			claimRequestConfirmationForm.setFileCount(attachmentList.size());
			
			session.setAttribute("attachmentList", attachmentList);
			try {
				fileItem.write(new File(path + displayFileName));
			} catch (Exception e) {
				logger.debug("Exception"+e.getMessage()); 
				throw new Exception("Could not write file to drive!!!!!!!");
			}
			}
			else{
				claimRequestConfirmationForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
				claimRequestConfirmationForm.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
				
			}}}
		
		claimRequestConfirmationForm.setErrorMessage(errorMessage.toString());				
		claimRequestConfirmationFormClaim.setErrorMessage(errorMessage.toString());
		
		request.setAttribute("attachmentFormDisplay",claimRequestConfirmationForm);
		model.addAttribute("attachmentFormDisplay",claimRequestConfirmationForm);
		
		request.setAttribute("claimRequestConfirmationForm",claimRequestConfirmationFormClaim);
		model.addAttribute("claimRequestConfirmationForm",claimRequestConfirmationFormClaim);
		
		response.setRenderParameter("action", "attachDocumentTestCreateClaim");
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */ 
	@RequestMapping(params = "action=attachDocumentTestCreateClaim")
	public String attachDocument(Model model, RenderRequest request,
	RenderResponse response) throws Exception {

		return "claims/claimCreateSubmit";
	}

	/**
	 * @param request 
	 * @param response 
	 * @param fileName 
	 * @param claimRequestConfirmationForm 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=removeAttachmentActionClaimCreate")
	public void removeAttachment(ActionRequest request, ActionResponse response,
			@RequestParam("fileName") String fileName,@ModelAttribute("attachmentFormDisplay") ClaimRequestConfirmationForm claimRequestConfirmationForm, Model model) throws Exception {
		if("".equals(fileName)){
			return;
		}
			PortletSession session = request.getPortletSession();
		
			
			//Delete the file from the filesystem
			String localWebPath = composeFilePath(path,fileName);
			File tempFile = new File(localWebPath);
			try {
				tempFile.delete();
			} 
			catch(Exception ex){
				logger.error("Error occurred while trying to delete temporary file for Siebel file upload");
				logger.error(ex.getMessage());
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
			if(newAttachmentList.size()==0){
				newAttachmentList=null;
			}
			claimRequestConfirmationForm.setAttachmentList(newAttachmentList);
			
			claimRequestConfirmationForm.setErrorMessage("");				
			
		request.setAttribute("attachmentFormDisplay",claimRequestConfirmationForm);
		model.addAttribute("attachmentFormDisplay",claimRequestConfirmationForm);
		response.setRenderParameter("action", "removeDocumentRenderClaimCreate");	
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=removeDocumentRenderClaimCreate")
	public String removeAttachmentRender(Model model, RenderRequest request,
	RenderResponse response) throws Exception {

		return "claims/claimCreateSubmit";
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
    		String str_Return=inputStr .substring(i+1,inputStr.length());
    		return str_Return;
    		
	}   	
	else{
		return inputStr;
		
	}
	
}
	
/**
 * @param fName 
 * @return String 
 * @throws LGSBusinessException 
 */
private String setTimestampInAttachment(String fName) throws LGSBusinessException{
		
		int index = fName.lastIndexOf(".");
		
		String fExtension = fName.substring(index);
		
		fName = fName.substring(0, index);
		
		String fNameFinal = fName + "_" + System.currentTimeMillis()+ fExtension;
		
		return fNameFinal;
	}

/**
 * @param resourceRequest 
 * @param resourceResponse 
 * @throws Exception 
 */
@RequestMapping(params = "action=countryDropDownPopulate")
public void populateCountryList(RenderRequest resourceRequest,RenderResponse resourceResponse)throws Exception{
	LOGGER.enter(this.getClass().getSimpleName(), "populateCountryList");
	List<GeographyCountryContract> countries =null;
	PrintWriter out =null;
	String htmlContentCountryList=null;
	try{
		
	if(allCountryList.get(0)!=null)
	{
		
		countries = allCountryList.get(0).getCountryList();
		htmlContentCountryList =new  XmlOutputGenerator(resourceRequest.getLocale()).convertCountriesToHTML(countries);
	}
	}catch(Exception ex)
	{	logger.debug("Exception"+ex.getMessage()); 
		GeographyListResult countryListResult = geographyService.getCountryDetails();
		countries = countryListResult.getCountryList();
		htmlContentCountryList = new  XmlOutputGenerator(resourceRequest.getLocale()).convertCountriesToHTML(countries);
		
	}finally{
		out = resourceResponse.getWriter();
		resourceResponse.setContentType(ChangeMgmtConstant.CONTENTTYPEHTML);
		out.print(htmlContentCountryList);
		if(out!=null)
		{
			out.flush();
			out.close();	
		}
	}
	LOGGER.exit(this.getClass().getSimpleName(), "populateCountryList");
}
  
  
/**
 * @param resourceRequest 
 * @param resourceResponse 
 * @throws Exception 
 */
@RequestMapping(params = "action=stateDropDownPopulate")
public void populateStateList(RenderRequest resourceRequest,RenderResponse resourceResponse)throws Exception{
	LOGGER.enter(this.getClass().getSimpleName(), "populateStateList");
	String countryCodeVal=resourceRequest.getParameter(ChangeMgmtConstant.COUNTRYCODE);
	GeographyListResult stateListResult= new GeographyListResult();
	List<GeographyStateContract> stateList= null;
	String htmlContentStateList = null;
	PrintWriter out = null;
	try{
	if(GeographyServiceImpl.allStateMap!=null)
	{
		stateListResult.setStateList(GeographyServiceImpl.allStateMap.get(countryCodeVal.trim()));
	}
	else{
	stateListResult = geographyService.getStateDetails(countryCodeVal);
	}
	
	stateList = stateListResult.getStateList();
	htmlContentStateList=new  XmlOutputGenerator(resourceRequest.getLocale()).convertStateToHTML(stateList);
	}catch(Exception ex)
	{
		logger.debug("Exception"+ex.getMessage()); 
		stateListResult = geographyService.getStateDetails(countryCodeVal);
		stateList = stateListResult.getStateList();
		htmlContentStateList=new  XmlOutputGenerator(resourceRequest.getLocale()).convertStateToHTML(stateList);
	}
	finally{
		out = resourceResponse.getWriter();
		resourceResponse.setContentType(ChangeMgmtConstant.CONTENTTYPEHTML);
		out.print(countryCodeVal+htmlContentStateList);
		out.flush();
		out.close();	
	}
	LOGGER.exit(this.getClass().getSimpleName(), "populateStateList");
}

/**
 * @param favoriteAddressId 
 * @param contactId 
 * @param flagStatus 
 * @param request 
 * @param response 
 * @throws Exception 
 */
@ResourceMapping("setServiceAddressFavouriteFlag")
public void setServiceAddressFavouriteFlag(@RequestParam("favoriteAddressId") String favoriteAddressId,
		@RequestParam("contactId") String contactId,
		@RequestParam("flagStatus") boolean flagStatus,
		ResourceRequest request, ResourceResponse response) throws Exception {
	LOGGER.enter(this.getClass().getSimpleName(),"setServiceAddressFavouriteFlag");
	boolean success = false;
	try {
        CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));		
		FavoriteAddressContract favoriteAddressContract	=  ContractFactory.getFavoriteAddressContract();
		FavoriteResult favoriteResult;
		favoriteAddressContract.setFavoriteAddressId(favoriteAddressId);
		
		favoriteAddressContract.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		favoriteAddressContract.setFavoriteFlag(!flagStatus);
		favoriteAddressContract.setSessionHandle(crmSessionHandle);
		favoriteAddressContract.setAccountId(request.getParameter("accountId"));

		LOGGER.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContent(favoriteAddressContract, LOGGER);
		LOGGER.info("end printing lex loggger");
		Long startTime = System.currentTimeMillis();
		
		favoriteResult = serviceRequestService.updateUserFavoriteAddress(favoriteAddressContract);
		
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_UPDATEUSERFAVORITEADDRESS, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,favoriteAddressContract);
		success = favoriteResult.isResult();
	} catch (Exception e) {
		logger.debug("Exception"+e.getMessage()); 
		success = false;
	}
	String errorCode = success? flagStatus ? "message.servicerequest.unBookmarkAddressSuccessfully" : "message.servicerequest.bookmarkAddressSuccessfully"
			:"exception.servicerequest.setServiceAddressFavouriteFlag";
	ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteAddressId+"\"");
	
	LOGGER.exit(this.getClass().getSimpleName(),"setServiceAddressFavouriteFlag");
}

//Added for CI BRD13-10-01 --STARTS
/**
 * @param model 
 * @param request 
 * @param response 
 * @param claimForm 
 * @throws Exception 
 */
private void submitSaveAsDraft(Model model, 
		   ActionRequest request, 
			   ActionResponse response,
			   @ModelAttribute("claimRequestConfirmationForm") ClaimRequestConfirmationForm claimForm) throws Exception{
	
	LOGGER.enter(this.getClass().getSimpleName(),"submitSaveAsDraft");
	 String srNumber = null;
	model.addAttribute("claimRequestConfirmationForm", claimForm);
	model.addAttribute("attachmentFormDisplay", claimForm);
	
	transformClaimForm(request, claimForm);
	Activity activity = claimForm.getActivity();
	String serviceRequestNumber = null;
	Asset asset = claimForm.getAsset();
	if(claimForm.getActivity().getServiceRequest()!=null){
		serviceRequestNumber = claimForm.getActivity().getServiceRequest().getServiceRequestNumber();
 }
	
	
	
	if(activity.getServiceRequest() == null){
		activity.setServiceRequest(new ServiceRequest());
		
	}
	
	ListOfValues serviceRequestTypeLOV = new ListOfValues();
	serviceRequestTypeLOV.setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_TYPE.getValue());
	serviceRequestTypeLOV.setValue(ServiceRequestTypeEnum.CLAIM_REQUEST.getValue());
	activity.getServiceRequest().setServiceRequestType(serviceRequestTypeLOV);
	activity.getServiceRequest().setRequestor(PortalSessionUtil.retrieveLoginAccountContact(request));
	activity.getServiceRequest().setAsset(asset);
	activity.setActivityDate(new Date());
	if(activity.getShipToAddress().getAddressId()!=null){
		if(activity.getShipToAddress().getAddressId().equals("-1")){
			activity.getShipToAddress().setAddressId(null);
		}
	} //changes for CI-5 2098
	

	
	try{
		if (serviceRequestNumber == null) {
			WarrantyClaimCreateContract warrantyClaimCreateContract = ContractFactory
					.createWarrantyClaimContract(request, activity);
			warrantyClaimCreateContract
					.setCreateNewCustomerAddressFlag(claimForm
							.getActivity().getNewCustomerAccountFlag());
			ObjectDebugUtil.printObjectContent(warrantyClaimCreateContract,
					logger);				
			WarrantyClaimCreateResult result = claimWebService
					.createWarrantyClaim(warrantyClaimCreateContract);
			srNumber = result.getRequestNumber();
			claimForm.getActivity().getServiceRequest()
					.setServiceRequestNumber(result.getRequestNumber());

			// TODO: how to set the service request status
			claimForm.getActivity().getServiceRequest()
					.setServiceRequestStatus("Pending");
			

			// Get all attachment names
			PortletSession session = request.getPortletSession();
			List<Attachment> attachmentList = (ArrayList<Attachment>) session
					.getAttribute("attachmentList");

			if (attachmentList == null) {
				attachmentList = new ArrayList<Attachment>();

			} else {
				if (attachmentList.size() > 0) {
					String inputClaimId = result.getRequestId();
					if (!inputClaimId.trim().equals("")) {
						CrmSessionHandle crmSessionHandle = globalService
								.initCrmSessionHandle(PortalSessionUtil
										.getSiebelCrmSessionHandle(request));
						AttachmentContract attachmentContract = new AttachmentContract();
						attachmentContract
								.setSessionHandle(crmSessionHandle);
						attachmentContract.setIdentifier(inputClaimId);

						attachmentContract.setRequestType("Claim Create");
						attachmentContract.setAttachments(attachmentList);
						attachmentService
								.uploadAttachments(attachmentContract);
					}
				}
			}
			if (session != null
					&& session.getAttribute("attachmentList") != null) {
				session.removeAttribute("attachmentList");
			}
		}
		else{
			 activity.getServiceRequest().setServiceRequestNumber(serviceRequestNumber);
			 ClaimUpdateContract contract =
			 ContractFactory.createClaimUpdateContract(request, activity);
			 ObjectDebugUtil.printObjectContent(contract, logger);
			 ClaimUpdateResult result =
			 claimWebService.updateWarrantyClaim(contract);
		}
	}
	catch(Exception e){
		String errorMessage = ExceptionUtil.getLocalizedExceptionMessage("exception.submitCreateClaimFailed", request.getLocale(),e);
		MailUtil.sendEmail("shiva.juluru@perficient.com", "Error while creating Claim", errorMessage, false);
		MailUtil.sendEmail("dan.wellborn@perficient.com", "Error while creating Claim", errorMessage, false);

		throw new Exception(errorMessage);
	}
	Calendar startTime = PortalSessionUtil.getCreateClaimStartTime(request.getPortletSession());
	Calendar endTime = Calendar.getInstance();
	long milliseconds1 = startTime.getTimeInMillis();
	long milliseconds2 = endTime.getTimeInMillis();
	long diff = milliseconds2 - milliseconds1;
	logger.debug("*****ELAPSED TIME TO CREATE IN SECONDS: " + diff / 1000);
	model.addAttribute("elapsedCreateTime", Long.valueOf(diff / 1000));
	String  returnPage = "claims/claimCreateSubmit";
	request.setAttribute("returnPage", returnPage);
	model.addAttribute("draftConfirmation","draftConfirmation");
	model.addAttribute("srNumber",srNumber);
	response.setRenderParameter("action", "saveAsDraftRender");
	LOGGER.exit(this.getClass().getSimpleName(),"submitSaveAsDraft");
}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=saveAsDraftRender")
	public String saveAsDraftRender(Model model, 
			RenderRequest request, 
			RenderResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(),"saveAsDraftRender");
		model.addAttribute("draftConfirmation","draftConfirmation");		
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID("displayAttachment");
		model.addAttribute("displayAttachment", resURL1.toString());
		String returnPage = (String)request.getAttribute("returnPage");
		LOGGER.exit(this.getClass().getSimpleName(),"saveAsDraftRender");
		return returnPage;
	}
	
	//Added for CI BRD13-10-01 --ENDS

	
	
	
	
	// started for CI 15.4 Problem Code 2 retrieve method
	/**
	 * ResourceMapping for the retrieve Price List for Parts 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	
	@ResourceMapping("retriveProblemCode")
	public void retriveProblemCode(ResourceRequest request, ResourceResponse response) throws Exception
	{
		logger.debug("in Claim Create Request Controller retrieve Error Code method  ");
		logger.debug("---------- Problem Code value is ----------- "+request.getParameter("problemCode"));
		logger.debug("---------- problemCodeLevel value is ----------- "+request.getParameter("problemCodeLevel"));

		String parentProblemCode = request.getParameter("problemCode");
		
		StringBuffer responseBody=new StringBuffer();
		response.setContentType("text/html");
		StringBuffer sb = new StringBuffer();
		
		if(request.getParameter("problemCodeLevel").trim().equalsIgnoreCase("1")){
			
			//Map<String,String> LOVfromDB = null;
			//LOVfromDB = retrieveLocalizedLOVMap("PARTNER_PROBLEM_CODE_2", request.getLocale());
			//logger.debug("LOVfromDB is ------------------ for code 2 "+LOVfromDB);
			
			Map<String, String> tempMap = null;
			
			tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
					SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), parentProblemCode, globalService, serviceRequestLocaleService, request.getLocale());
			logger.debug("----------------- Problem Code Map 2 is --------------------- "+tempMap);
			//StringBuffer responseBody=new StringBuffer();
			//response.setContentType("text/html");
			//StringBuffer sb = new StringBuffer();
			if(tempMap.size()>1){
			sb.append("<select id=\'probCode2\' name=\'probCode2\' onchange=\'getprobCode(this.value,2);\'>");
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
	@ResourceMapping("retriveNumericCode2")
	public void retriveNumericCode2(ResourceRequest request, ResourceResponse response) throws Exception
	{
		logger.debug("in Claim Create Request Controller retriveNumericCode2 ");
		logger.debug("---------- errorNumericCode2 value is ----------- "+request.getParameter("errorNumericCode2"));
		
		ArrayList<String> numericCode2 = new ArrayList<String>();
		for(int i=0;i<101;i++){
			numericCode2.add(Integer.toString(i));
		}
		
		String errorCodeNum = request.getParameter("errorNumericCode2");
		String problemCode = request.getParameter("problemCode");
	
		SiebelLOVListContract lovListcontract = ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), problemCode);
	    //String str2 = "76.39";
	    float errorCodeVal = Float.parseFloat(errorCodeNum);
	    logger.debug("float value is ----->>> "+errorCodeVal);	    
	    lovListcontract.setValue(errorCodeNum);
	    
		logger.debug("Berofe errorCode contract1");
		ObjectDebugUtil.printObjectContent(lovListcontract, logger);
		logger.debug("After errorCode contract1");
		SiebelLOVListResult listResult = globalService.retrieveNonCacheableSiebelLOVList(lovListcontract);
		
		StringBuffer responseBody=new StringBuffer();
		response.setContentType("text/html");
		boolean inSiebel = false;
		if(listResult.getLovList().size()>0){
			inSiebel = true;
		}
		/*logger.debug("numericCode2 list size is "+numericCode2.size());
		for(int i =0;i<numericCode2.size();i++){			
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
	public String createConditionForMKI(String mki, String serviceProvider, boolean accountFlag,List<Account> accntList, Model model){
		logger.debug("in createConditionForMKI");
		String condition = "";
		logger.debug("serviceProvider in createConditionForMKI is ============= "+serviceProvider);
		for(Account accnt : accntList){
			logger.debug("account name is "+accnt.getAccountName()+" and account Id is "+accnt.getAccountId());
			if(accnt.getAccountName().equalsIgnoreCase(serviceProvider)){
				model.addAttribute("accountId", accnt.getAccountId());
				logger.debug("same account is "+accnt.getAccountName());
				condition = "1";
				logger.debug("returning 1");
				return condition;
			}
		}
		if("y".equalsIgnoreCase(mki)){
			accountFlag = true;
		}
		if(accountFlag){
			condition = "2";
			logger.debug("returning 2");
			return condition;
		}
		else{
			condition = "3";
			logger.debug("returning 3");
			return condition;
		}
		
	}
}
