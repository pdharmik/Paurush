package com.lexmark.portlet;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
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
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ActivityDebriefSubmitContract;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.contract.ValidateInstalledPrinterSerialNumberContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.DebriefActionStatusEnum;
import com.lexmark.enums.ServiceTypeEnum;
import com.lexmark.form.ClaimDebriefForm;
import com.lexmark.form.RequestsDebriefForm;
import com.lexmark.result.ActivityDebriefSubmitResult;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.result.ValidateInstalledPrinterSerialNumberResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.CollectionSorter;
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
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.webservice.api.RequestService;

@Controller
@RequestMapping("VIEW")
public class RequestsDebriefController {
	@Autowired
	private PartnerRequestsService partnerRequestsService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private RequestService requestWebService;

	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private GridSettingController gridSettingController;

	private static Logger logger = LogManager.getLogger(RequestsDebriefController.class);
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

	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=showRequestsDebriefPage")
	public String showRequestsDebriefPage (Model model, RenderRequest request, RenderResponse response) throws Exception{
		logger.debug("[START] showRequestsDebriefPage"); 	
		try{
			
			//retrieve gridCCVPartDebrief1 grids order 
			List<UserGridSettingResult> gridSettingList = new ArrayList<UserGridSettingResult>();
			UserGridSettingResult gridSettingResult= new UserGridSettingResult(); 
			gridSettingController.retrieveGridSetting("gridCCVPartDebrief1", request, model);		
			gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
			gridSettingList.add(gridSettingResult);
			
			gridSettingController.retrieveGridSetting("gridAdditionalParts1", request, model);		
			gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
			gridSettingList.add(gridSettingResult);
			
			model.addAttribute("gridSettingList",gridSettingList);
			
			ActivityDetailContract activityDetailContract = ContractFactory.createActivityDetailContract(request);
			activityDetailContract.setDebriefFlag(true);
			ObjectDebugUtil.printObjectContent(activityDetailContract, logger);
			long timeBeforeCall=System.currentTimeMillis();
			ActivityDetailResult activityDetailResult = partnerRequestsService.retrieveActivityDetail(activityDetailContract);			
			
			long timeAfterCall=System.currentTimeMillis();
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SERVICE_REQUEST_CLOSE_OUT, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,activityDetailContract);		
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
			
			Activity activity = activityDetailResult.getActivity();
			activity.getServiceRequest().getAsset().setProductImageURL(ControllerUtil.retrieveProductImageUrl(
					productImageService, activity.getServiceRequest().getAsset().getProductTLI()));
			
			ControllerUtil.localizeActivity(activity, serviceRequestLocaleService, request.getLocale());
			
			RequestsDebriefForm requestsDebriefForm = createRequestsDebriefForm(activityDetailResult.getActivity(),request);
	
			ResourceURL resURL = response.createResourceURL();
			resURL.setResourceID("retrievePartnerAddressListURL");
			requestsDebriefForm.setPartnerAddressListURL(resURL.toString());
			requestsDebriefForm.refreshSubmitToken(request);
			float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)).floatValue();
			requestsDebriefForm.setTimezoneOffset(timezoneOffset);
			
			// Invalidating the Attachment Session
			PortletSession session = request.getPortletSession();
			if(session!=null&& session.getAttribute("attachmentList")!=null){
				session.removeAttribute("attachmentList");
			}
			
			model.addAttribute("requestsDebriefForm", requestsDebriefForm);
			model.addAttribute("attachmentFormDisplay", requestsDebriefForm);
			model.addAttribute("assetId", requestsDebriefForm.getActivity().getServiceRequest().getAsset().getAssetId());
			
		}catch(Exception e){
			throw new Exception(ExceptionUtil.getLocalizedExceptionMessage("exception.openRequestCloseOutFailed", request.getLocale(),e));
		}
		logger.debug("[END] showRequestsDebriefPage");
		return "serviceRequest/requestDebriefView";
	}
	
	/**
	 * @param requestsDebriefForm 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=submitRequestsDebrief")
	public void submitRequestsDebrief(@ModelAttribute("requestsDebriefForm") RequestsDebriefForm requestsDebriefForm,
								   Model model, 
			  					   ActionRequest request, 
			  					   ActionResponse response) throws Exception{
		logger.debug("[START] submitRequestsDebrief"); 
		//TODO: render to the right page.
		Locale locale = request.getLocale();
		Activity activity =  transformRequestsDebriefForm(request,requestsDebriefForm,locale);
		
		logger.debug("Problem Code Level  is ------------------- "+activity.getDebrief().getActualFailureCode().getValue());
		logger.debug("Full Problem Code  is ------------------- "+activity.getActualFailureCode().getValue());
		if(activity.getActualFailureCode().getValue()== "" || activity.getActualFailureCode().getValue()==null){
			activity.getDebrief().getActualFailureCode().setValue(activity.getDebrief().getActualFailureCode().getValue());
			
		}else{
			activity.getDebrief().getActualFailureCode().setValue(activity.getActualFailureCode().getValue());
		}
		String fromPage = requestsDebriefForm.getFromPage();
		if(PortalSessionUtil.isDuplicatedSubmit(request, requestsDebriefForm)){
			if("requestDetailView".equalsIgnoreCase(fromPage)){
				response.setRenderParameter("serviceRequestId" , activity.getServiceRequest().getId());
				response.setRenderParameter("activityId", activity.getActivityId());
				response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET,
						String.valueOf(requestsDebriefForm.getTimezoneOffset()));
				response.setRenderParameter("action", "showRequestDetailPage");
			}
			return;
		}		
		String messageCode = null;
		ActivityDebriefSubmitResult activityDebriefSubmitResult = null;
		try{
			ActivityDebriefSubmitContract contract = ContractFactory.createActivityDebriefSubmitContract(activity);
			activityDebriefSubmitResult  = requestWebService.submitActivityDebrief(contract);
		}catch(Exception e){
			messageCode = "exception.siebel.closeOutRequestException";
			
			String errorMessage = ExceptionUtil.getLocalizedExceptionMessage(messageCode, request.getLocale(),e);
			MailUtil.sendEmail("sjuluru@lexmark.com", "Error while closing out Request", errorMessage, false);
			MailUtil.sendEmail("bwellbor@lexmark.com", "Error while closing out Request", errorMessage, false);

			ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			response.setRenderParameter("serviceRequestId" , activity.getServiceRequest().getId());
			response.setRenderParameter("activityId", activity.getActivityId());
			response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET,
					String.valueOf(requestsDebriefForm.getTimezoneOffset()));
			response.setRenderParameter("action", "showRequestsDebriefPage");
			response.setRenderParameter("fromPage", requestsDebriefForm.getFromPage());
		}

		response.setRenderParameter(LexmarkConstants.TIMEZONE_OFFSET,
				String.valueOf(requestsDebriefForm.getTimezoneOffset()));
		if(activityDebriefSubmitResult !=null && activityDebriefSubmitResult.getSuccess() !=null && activityDebriefSubmitResult.getSuccess().booleanValue()){
			messageCode = "message.request.closeOutSuccess";
			if("requestListView".equalsIgnoreCase(fromPage)){
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			}
			if("requestDetailView".equalsIgnoreCase(fromPage)){
				ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
				response.setRenderParameter("serviceRequestId" , activity.getServiceRequest().getId());
				response.setRenderParameter("activityId", activity.getActivityId());
				response.setRenderParameter("action", "showRequestDetailPage");
			}
		}else{
			messageCode = "exception.submitCloseOutRequestFailed";
			ServiceStatusUtil.checkServiceStatus(model, messageCode, request.getLocale(), true);
			response.setRenderParameter("serviceRequestId" , activity.getServiceRequest().getId());
			response.setRenderParameter("activityId", activity.getActivityId());
			response.setRenderParameter("action", "showRequestsDebriefPage");
			response.setRenderParameter("fromPage", requestsDebriefForm.getFromPage());
		}
		
		//calling aMind method..
		//Get all attachment names
		PortletSession session = request.getPortletSession();
		
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		
		if(attachmentList == null) {
			attachmentList = new ArrayList<Attachment>();
			logger.debug("--------------------- We dont have anything as attachment Null---------------------------");
		}else{
			if(attachmentList.size()>0){
		for(int i=0;i<attachmentList.size();i++){
			logger.debug("---------------- Printing attachment name --------------");
			logger.debug("----------"+attachmentList.get(i).getAttachmentName());
		}
		
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		AttachmentContract attachmentContract = new AttachmentContract();
		attachmentContract.setSessionHandle(crmSessionHandle);
		logger.debug("------------------------------- activityid we are sending ----------------------"+requestsDebriefForm.getActivity().getActivityId());
		attachmentContract.setIdentifier(requestsDebriefForm.getActivity().getActivityId());
		attachmentContract.setRequestType("Sr Update");
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
		logger.debug("[END] submitRequestsDebrief"); 
	}

	
	
	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("validateInstalledPrinterSerialNumber")
	public void validateInstalledPrinterSerialNumber(ResourceRequest request, ResourceResponse response) throws Exception {
		logger.debug("[START] validateManualAsset");
		ValidateInstalledPrinterSerialNumberContract contract  = ContractFactory.createValidateInstalledPrinterSerialNumberContract(request);
		String errorCode = "message.servicerequest.setContactFavouriteFlag";
		boolean validateSuccess = true;
		
		try {
			ValidateInstalledPrinterSerialNumberResult validateResult = partnerRequestsService.validateInstalledPrinterSerialNumber(contract);
			validateSuccess = validateResult.getSuccess();
		} catch (Exception e) {
			logger.debug("Exception"+e.getMessage()); 
			 errorCode = "exception.servicerequest.installedPrinterSerialNumber";
		}
		
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),String.valueOf(validateSuccess));
		logger.debug("[END] validateManualAsset");
	}
	
	/**
	 * @param activity 
	 * @param request 
	 * @return RequestsDebriefForm 
	 * @throws Exception 
	 */
	private RequestsDebriefForm createRequestsDebriefForm(Activity activity, RenderRequest request) throws Exception{
		logger.debug("[START] createClaimDebriefForm"); 
		Locale locale = request.getLocale();
		RequestsDebriefForm requestsDebriefForm = new RequestsDebriefForm();
		requestsDebriefForm.setActivity(activity);
		
		Map<String, String> tempMap = null;
		
		tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_TRAVEL_UNIT_OF_MEASURE.getValue(), null, globalService, serviceRequestLocaleService, locale);
		requestsDebriefForm.setTravelUnitOfMeasureMap(tempMap);
		tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), null, globalService, serviceRequestLocaleService, locale);
		requestsDebriefForm.setProblemCodeMap(tempMap);
		tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue(), null, globalService, serviceRequestLocaleService, locale);
		
		
		// adding to show only new values for resolution code start  ========================================
					SiebelLOVListContract lovListcontract = ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue(), null);
					SiebelLOVListResult listResult;
					listResult = globalService.retrieveSiebelLOVList(lovListcontract);
					for(int i=0; i<listResult.getLovList().size();i++){
						logger.debug(i+"th value is "+listResult.getLovList().get(i).getValue());
					}
					
					// retrieve map from db start
					LocalizedSiebelLOVListContract localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_RESOLUTION_CODE.getValue(), null, locale);
					LocalizedSiebelLOVListResult localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
					Map<String, String> dbLOVMap = new HashMap<String, String>();
					if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
						for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
							dbLOVMap.put(localizedLOV.getValue(), localizedLOV.getName());
						}
					}
					logger.debug("db lov map for new resolution code is "+dbLOVMap);
					Map<String, String> resolutionCodeMap = new HashMap<String, String>();
					for(int i=0; i<listResult.getLovList().size();i++){
						logger.debug(i+"th value is "+listResult.getLovList().get(i));
						String key = listResult.getLovList().get(i).getValue();
						String value = dbLOVMap.get(key);
						if(null != value && !value.toCharArray().equals("")){
							resolutionCodeMap.put(key,value);
						}
					}
					
					
					
					// retrieve map from db end
					// adding to show only new values for resolution code end ===============================================
					requestsDebriefForm.setResolutionCodeMap(resolutionCodeMap);

		tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_ADDITIONAL_PAYMENT_TYPE.getValue(), null, globalService, serviceRequestLocaleService, locale);
		requestsDebriefForm.setPaymentTypes(tempMap);
		tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(), null, globalService, serviceRequestLocaleService, locale);
		
		// adding to show only new values for part status code start  ========================================
		lovListcontract = ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(), null);						 
		listResult = globalService.retrieveSiebelLOVList(lovListcontract);
		for(int i=0; i<listResult.getLovList().size();i++){
			logger.debug(i+"th value is "+listResult.getLovList().get(i).getValue());
		}
		
		// retrieve map from db start
		 localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_PART_STATUS.getValue(), null, locale);
		 localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
		//Map<String, String> dbLOVMap = new HashMap<String, String>();
		if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
			for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
				dbLOVMap.put(localizedLOV.getValue(), localizedLOV.getName());
			}
		}
		logger.debug("db lov map for new part status code is "+dbLOVMap);
		Map<String, String> partStatusMap = new HashMap<String, String>();
		for(int i=0; i<listResult.getLovList().size();i++){
			logger.debug(i+"th value is "+listResult.getLovList().get(i));
			String key = listResult.getLovList().get(i).getValue();
			String value = dbLOVMap.get(key);
			if(null != value && !value.toCharArray().equals("")){
				partStatusMap.put(key,value);
			}
		}
		
		
		
		// retrieve map from db end
		// adding to show only new values for part status code end ===============================================
		requestsDebriefForm.setPartStatusList(partStatusMap);
		
		tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_1.getValue(), null, globalService, serviceRequestLocaleService, locale);
		requestsDebriefForm.setErrorCode1List(tempMap);
		tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_CARRIER.getValue(), null, globalService, serviceRequestLocaleService, locale);
		requestsDebriefForm.setCarrierList(tempMap);
		tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_DEVICE_CONDITION.getValue(), null, globalService, serviceRequestLocaleService, locale);
		requestsDebriefForm.setWorkingConditionaMap(tempMap);
		tempMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_PART_SOURCE.getValue(), null, globalService, serviceRequestLocaleService, locale);
		requestsDebriefForm.setSourceList(tempMap);
		
		List<String> addressStatusList = Arrays.asList(LexmarkConstants.ADDRESS_STATUS_DO_NOT_SHIP_PARTS,LexmarkConstants.ADDRESS_STATUS_SHIP_TO_CUSTOMER, LexmarkConstants.ADDRESS_STATUS_SHIP_TO_SERVICE_PARTNER, LexmarkConstants.ADDRESS_STATUS_PARTNER_TO_PROVIDE);
		requestsDebriefForm.setAddressStatusList(addressStatusList);
		
		if(activity.getActivityType() == null){
			throw new IllegalArgumentException("Failed to open Requests Debrief page: <br/> ActivityType is null");
		}
		String serviceType = activity.getActivityType().getValue();
		
		if(ServiceTypeEnum.INSTALLATION.getValue().equals(serviceType) || ServiceTypeEnum.INSTALLATION_DELINSTALLATION.getValue().equals(serviceType)
				|| ServiceTypeEnum.MECHANICAL_REPLACEMENT_WITH_TECHNICIAN.getValue().equals(serviceType) || ServiceTypeEnum.ONSITE_EXCHANGE.getValue().equals(serviceType)){
			requestsDebriefForm.setRemoveDeviceSectionFlag(true);
			requestsDebriefForm.setNetworkConnectedFlag(false);
			
			Map<String,Asset> orderedAssetMap = new HashMap<String, Asset>();
			orderedAssetMap.put(activity.getServiceRequest().getAsset().getSerialNumber(), activity.getServiceRequest().getAsset());
			List<PartLineItem> orderPartList = activity.getOrderPartList();
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
			requestsDebriefForm.setAssetList(sorter.sort(assetList,sortCriteria));
		}else{
			requestsDebriefForm.setRemoveDeviceSectionFlag(false);
			
			String ipAddress  = activity.getServiceRequest().getAsset().getIpAddress();
			String macAddress  = activity.getServiceRequest().getAsset().getMacAddress();
			if((ipAddress!=null && !"".equals(ipAddress)) || (macAddress!=null && !"".equals(macAddress))){
				requestsDebriefForm.setNetworkConnectedFlag(true);
			}else{
				requestsDebriefForm.setNetworkConnectedFlag(false);
			}
		}
		
		requestsDebriefForm.setTechnicianList(ControllerUtil.retrieveTechnicianListSorted(activity.getPartnerAccount().getAccountId(), partnerRequestsService));
		
		if (PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(request.getPortletSession())) {
			requestsDebriefForm.setShowAdditionalPaymentRequestListFlag(true);
		} else {
			requestsDebriefForm.setShowAdditionalPaymentRequestListFlag(false);
		}
		
		AccountContact technician = activity.getTechnician();
		if(technician != null && StringUtil.isStringEmpty(technician.getContactId()) && (!StringUtil.isStringEmpty(technician.getFirstName()) || !StringUtil.isStringEmpty(technician.getLastName()))){
			technician.setNewContactFlag(true);
		}
		
		Map<String, String> paymentTypesMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_ADDITIONAL_PAYMENT_TYPE.getValue(), null, globalService, serviceRequestLocaleService, request.getLocale());
		requestsDebriefForm.setPaymentTypes(paymentTypesMap);
		
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(request.getLocale());
		
		String contactId = PortalSessionUtil.getContactId(request.getPortletSession());
		requestsDebriefForm.setActivityNoteListXML(xmlOutputGenerator.convertActivityNoteListToXMLForEditSRCloseOut(activity.getActivityNoteList(), 
				contactId));
		requestsDebriefForm.setContactId(contactId);
		
		requestsDebriefForm.setAdditionalPaymentRequestListXML(xmlOutputGenerator.convertAdditionalPaymentListToXMLForEdit(
				activity.getAdditionalPaymentRequestList(),requestsDebriefForm.getPaymentTypes()));
		
		requestsDebriefForm.setFromPage(request.getParameter("fromPage"));
		
		logger.debug("[END] createClaimDebriefForm"); 
		return requestsDebriefForm;
	}
	
	
	/**
	 * @param request 
	 * @param requestsDebriefForm 
	 * @param locale 
	 * @return Activity 
	 * @throws Exception 
	 */
	private Activity transformRequestsDebriefForm(ActionRequest request,RequestsDebriefForm requestsDebriefForm,Locale locale) throws Exception{
		Activity activity = requestsDebriefForm.getActivity();
		setTechinicianName(requestsDebriefForm);
		
		String serviceEndDate = requestsDebriefForm.getServiceEndDate();
		String serviceStartDate = requestsDebriefForm.getServiceStartDate();
		if(serviceStartDate != null && !"".equals(serviceStartDate)){
			serviceStartDate += LexmarkConstants.ZERO_SECOND;
			Date serviceStartDateGMT = DateLocalizationUtil.parseDate(serviceStartDate, true, request.getLocale());
			TimezoneUtil.adjustDate(serviceStartDateGMT, requestsDebriefForm.getTimezoneOffset());
			activity.getDebrief().setServiceStartDate(serviceStartDateGMT);
		}
		if(serviceEndDate != null && !"".equals(serviceEndDate)){
			serviceEndDate += LexmarkConstants.ZERO_SECOND;
			Date serviceEndDateGMT = DateLocalizationUtil.parseDate(serviceEndDate, true, request.getLocale());
			TimezoneUtil.adjustDate(serviceEndDateGMT, requestsDebriefForm.getTimezoneOffset());
			activity.getDebrief().setServiceEndDate(serviceEndDateGMT);
		}
		
		boolean repairCompleteFlag = activity.isRepairCompleteFlag();
		//TODO service status
		if(repairCompleteFlag){
			activity.getDebrief().setDebriefActionStatus(DebriefActionStatusEnum.INCOMPLETE.getValue());
		}else{
			activity.getDebrief().setDebriefActionStatus(DebriefActionStatusEnum.COMPLETE.getValue());
		}
		
		AccountContact requestor = PortalSessionUtil.retrieveLoginAccountContact(request);
		activity.getServiceRequest().setRequestor(requestor);
		ControllerUtil.formatHTMLTagForNotes(activity.getActivityNoteList());
		activity.setAdditionalPaymentRequestList(requestsDebriefForm.getNewAdditionalPaymentRequestList());
		ControllerUtil.checkAdditionalPaymentsWithEmptyID(activity.getAdditionalPaymentRequestList());
		return activity;
	}
	
	/**
	 * @param requestsDebriefForm 
	 */
	private void setTechinicianName(RequestsDebriefForm requestsDebriefForm) {
		String technicianFullName = requestsDebriefForm.getTechnicianFullName();
		AccountContact contact = requestsDebriefForm.getActivity().getTechnician();
		if(!StringUtil.isStringEmpty(technicianFullName)){
			if(contact == null){
				contact = new AccountContact();
				requestsDebriefForm.getActivity().setTechnician(contact);
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
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param requestsDebriefForm 
	 * @param requestsFormDebrief 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=addAttachmentsDebriefRequest")
	public void addAttachment(Model model, ActionRequest request, ActionResponse response,
			@ModelAttribute("attachmentFormDisplay") RequestsDebriefForm requestsDebriefForm
			,@ModelAttribute("requestsDebriefForm") RequestsDebriefForm requestsFormDebrief)
	throws Exception {
		logger.debug("Inside fileUploadmethod ");
		logger.debug("Inside fileUploadmethod "+ requestsDebriefForm);
		logger.debug("Inside fileUploadmethod "+ requestsDebriefForm.getFileData());
		
		
		PortletSession session = request.getPortletSession();
		Long fileSize=requestsDebriefForm.getFileData().getSize();
		
		// Size For display
		
		double fileSizeDisplay=requestsDebriefForm.getFileData().getSize();
		logger.debug("File Size is " + fileSizeDisplay);
		fileSizeDisplay=fileSizeDisplay/1024;
		BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
		roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
		logger.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
		
		FileItem fileItem=requestsDebriefForm.getFileData().getFileItem();
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
		String inputFileType=requestsDebriefForm.getFileData().getContentType();
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

				requestsDebriefForm.setAttachmentList(attachmentList);
				requestsFormDebrief.setAttachmentList(attachmentList);

				if(attachmentList!=null){
					requestsDebriefForm.setFileCount(attachmentList.size());
					requestsFormDebrief.setFileCount(attachmentList.size());
				}
				session.setAttribute("attachmentList", attachmentList);
				
			}// File Type Check
			else if(fileSizeInValid){
				errorMessage.append(attachmentFileTypeMessage);
				fileSizeInValid=true;
				logger.debug("File Type is Not Valid :: fileSizeInValid !!!! "+fileSizeInValid);

				requestsDebriefForm.setAttachmentList(attachmentList);
				if(attachmentList!=null){
					requestsDebriefForm.setFileCount(attachmentList.size());
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
			requestsDebriefForm.setAttachmentList(attachmentList);
			requestsFormDebrief.setAttachmentList(attachmentList);
			if(attachmentList!=null){
				logger.debug("Attachment size is "+attachmentList.size());
				requestsDebriefForm.setFileCount(attachmentList.size());
				requestsFormDebrief.setFileCount(attachmentList.size());
			}
							
			session.setAttribute("attachmentList", attachmentList);
			logger.debug("Attachment File Path :::: "+path);
			try {
				fileItem.write(new File(path + inputFileName));
			} catch (Exception e) {
				logger.debug("Exception"+e.getMessage()); 
				throw new Exception("Could not write file to drive!!!!!!!");
			}
			}
			else{
				logger.debug("Not Attached Filename already existing in the File share DUPLICATE !!!! "+inputFileName);
				requestsDebriefForm.setAttachmentList(attachmentList);
				requestsFormDebrief.setAttachmentList(attachmentList);
				if(attachmentList!=null){
					logger.debug("Attachment size is "+attachmentList.size());
					requestsDebriefForm.setFileCount(attachmentList.size());
					requestsFormDebrief.setFileCount(attachmentList.size());
				}
				
				session.setAttribute("attachmentList", attachmentList);
			}
		
				} }
				
		logger.debug("---------------------Setting the RequestDebriefForm  !!!!------------------- ");
		
		logger.debug("errorMessage is ::  "+errorMessage.toString());
		requestsDebriefForm.setErrorMessage(errorMessage.toString());				
		requestsFormDebrief.setErrorMessage(errorMessage.toString());
		

		request.setAttribute("attachmentFormDisplay",requestsDebriefForm);
		model.addAttribute("attachmentFormDisplay",requestsDebriefForm);

		request.setAttribute("requestsDebriefForm",requestsFormDebrief);
		model.addAttribute("requestsDebriefForm",requestsFormDebrief);
		logger.debug("---------------------Setting the claimDebriefForm  Complete !!!!------------------- ");
		response.setRenderParameter("action", "attachDocumentTestRequestDebrief");
		
		
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=attachDocumentTestRequestDebrief")
	public String attachDocument(Model model, RenderRequest request,
	RenderResponse response) throws Exception {
		logger.debug("---------------------attachDocumentTestDebrief is called !!!!------------------- ");
		return "serviceRequest/requestDebriefView";
	}

	/**
	 * @param request 
	 * @param response 
	 * @param fileName 
	 * @param requestsDebriefForm 
	 * @param requestsFormDebrief 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=removeAttachmentActionRequestDebrief")
	public void removeAttachment(ActionRequest request, ActionResponse response,
			@RequestParam("fileName") String fileName,@ModelAttribute("attachmentFormDisplay") RequestsDebriefForm requestsDebriefForm,
			@ModelAttribute("requestsDebriefForm") RequestsDebriefForm requestsFormDebrief, Model model) throws Exception {
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
			requestsDebriefForm.setAttachmentList(newAttachmentList);
			requestsFormDebrief.setAttachmentList(newAttachmentList);
			
			requestsDebriefForm.setErrorMessage("");		
			requestsFormDebrief.setErrorMessage("");		
		request.setAttribute("attachmentFormDisplay",requestsDebriefForm);
		model.addAttribute("attachmentFormDisplay",requestsDebriefForm);
		
		request.setAttribute("requestsDebriefForm",requestsFormDebrief);
		model.addAttribute("requestsDebriefForm",requestsFormDebrief);

		
		response.setRenderParameter("action", "removeDocumentRenderRequestDebrief");	
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=removeDocumentRenderRequestDebrief")
	public String removeAttachmentRender(Model model, RenderRequest request,
	RenderResponse response) throws Exception {

		return "serviceRequest/requestDebriefView";
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
		@ResourceMapping("retriveProblemCode")
		public void retriveProblemCode(ResourceRequest request, ResourceResponse response) throws Exception
		{
			logger.debug("in retrieve Error Code Request Debrief Controller  ");
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
			logger.debug("in retriveNumericCode2 Request Debrief Controller ");
			logger.debug("---------- errorNumericCode2 value is ----------- "+request.getParameter("errorNumericCode2"));
			
			ArrayList<String> numericCode2 = new ArrayList<String>();
			for(int i=0;i<101;i++){
				numericCode2.add(Integer.toString(i));
			}
			
			String errorCodeNum = request.getParameter("errorNumericCode2");
			String problemCode = request.getParameter("problemCode");
			
			SiebelLOVListContract lovListcontract = ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), problemCode);
		  
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
			//logger.debug("numericCode2 list size is "+numericCode2.size());
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
