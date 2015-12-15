/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ServiceRequestController
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.AccountPayableDetailContract;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.PartnerNotificationsContract;
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.domain.AccountPayableDetail;
import com.lexmark.domain.AccountPayableDetailActivity;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.TechnicianInstruction;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.DebriefActionStatusEnum;
import com.lexmark.exporter.PdfDataExporter;
import com.lexmark.form.PaymentRequestDrillDownForm;
import com.lexmark.form.ServiceRequestDetailForm;
import com.lexmark.result.AccountPayableDetailResult;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.AttachmentResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.PartnerClaimCreateIdResult;
import com.lexmark.result.PartnerNotificationsResult;
import com.lexmark.result.ServiceActivityHistoryListResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.PaymentsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.XmlOutputGenerator;

@Controller
@RequestMapping("VIEW")
public class ServiceRequestController {

	@Autowired
	private PartnerRequestsService partnerRequestsService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private PaymentsService paymentsService;
	@Autowired
    private AttachmentService attachmentService;
	
	@Autowired
	private GlobalService globalService;
	@Autowired
	private GridSettingController gridSettingController;
	
	private static Logger logger = LogManager.getLogger(ServiceRequestController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	
	// Getting the File Path from attachment properties file
	static ResourceBundle bundle = ResourceBundle.getBundle("attachment");

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showRequestDetailPage")
	public String showRequestDetailPage(Model model, RenderRequest request,RenderResponse response) throws Exception {
		logger.debug("in service Request controller");
		ActivityDetailContract contract = ContractFactory.createActivityDetailContract(request);
		contract.setDebriefFlag(false);
		long timeBeforeCall=System.currentTimeMillis();
		
		//retrieve order Parts grids order 
		List<UserGridSettingResult> gridSettingList = new ArrayList<UserGridSettingResult>();
		UserGridSettingResult gridSettingResult= new UserGridSettingResult(); 
		gridSettingController.retrieveGridSetting("gridRDVOrderedPartsTable", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		gridSettingList.add(gridSettingResult);
		
		//retrieve returned Parts grids order 
		gridSettingController.retrieveGridSetting("gridRDVbeReturnedParts", request, model);
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		gridSettingList.add(gridSettingResult);
		
		//retrieve recommended parts grids order
		gridSettingController.retrieveGridSetting("gridRDVRecommendedParts", request, model);
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		gridSettingList.add(gridSettingResult);
		
		model.addAttribute("gridSettingList",gridSettingList);
		logger.debug("printing multiple grids settings ");
		for(int i =0;i<gridSettingList.size();i++){
			logger.debug("in for Grid Id "+gridSettingList.get(i).getGridId());
			logger.debug("in for Cols Order "+gridSettingList.get(i).getColsOrder());
		}
		logger.debug("after contract setting");
		ActivityDetailResult activityDetailResult = partnerRequestsService.retrieveActivityDetail(contract);
		logger.debug("after service call");	
		long timeAfterCall=System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.PARTNER_SERVICE_REQUEST_DETAILS, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);		
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
		
		PortletSession session = request.getPortletSession();
		XmlOutputGenerator xmlGenerator = new XmlOutputGenerator(request.getLocale());
		ServiceRequestDetailForm form = new ServiceRequestDetailForm();
		Asset device = activity.getServiceRequest().getAsset();
		device.setProductImageURL(ControllerUtil.retrieveProductImageUrl(productImageService, device.getProductTLI()));

		// localize activityStatus, etc
		ControllerUtil.localizeActivity(activity, serviceRequestLocaleService, request.getLocale());

		// localize additional service required
		localizeAdditionalServiceRequired(activity, request.getLocale());

		// localize partLineItem
		if (activity.getOrderPartList() != null) {
			ControllerUtil.batchLocalizePart(activity.getOrderPartList(), serviceRequestLocaleService,
					request.getLocale());
		}
		if (activity.getReturnPartList() != null) {
			logger.debug("------------------RMA Caling the getReturnPartList-------------");
			ControllerUtil.batchLocalizePart(activity.getReturnPartList(), serviceRequestLocaleService,
					request.getLocale());
		}
		if (activity.getRecommendedPartList() != null) {
			ControllerUtil.batchLocalizePart(activity.getRecommendedPartList(), 
					serviceRequestLocaleService, request.getLocale());
		}
		//localize additional payments
		if(activity.getAdditionalPaymentRequestList() != null){
			ControllerUtil.batchLocalizeAdditionPayments(activity.getAdditionalPaymentRequestList(), serviceRequestLocaleService, request.getLocale());
		}		

		form.setActivity(activity);
		form.setAllowAdditionalPaymentRequestFlag(PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(session));
		
		List<PartLineItem> orderPartList = activity.getOrderPartList();
		
		// started here
		LocalizedSiebelLOVListContract localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_PART_STATUS_DETAIL.getValue(), null, request.getLocale());
		LocalizedSiebelLOVListResult localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
		Map<String, String> dbLOVMap = new HashMap<String, String>();
		if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
			for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
				dbLOVMap.put(localizedLOV.getValue(), localizedLOV.getName());
			}
		}
		logger.debug("db lov map is "+dbLOVMap);
		// ended here
		
		if (orderPartList != null && !orderPartList.isEmpty()){
			logger.debug("Inside if");
			for(PartLineItem partLineItem : orderPartList){
				logger.debug("Inside For");					
				if(partLineItem.getPartStatus() != null){
					logger.debug("status from siebel is "+partLineItem.getPartStatus().getValue());
					String status = dbLOVMap.get(partLineItem.getPartStatus().getValue());
					if(null != status){
						logger.debug("status from map is "+partLineItem.getPartStatus().getValue());
						partLineItem.getPartStatus().setValue(status);
					}	
				}								
				
			}
			form.setOrderPartsXML(xmlGenerator.convertOrderPartListToXML(orderPartList));}
		logger.debug("Outside if");
		List<PartLineItem> returnPartList = activity.getReturnPartList();
		// RMA
		
		if (returnPartList != null && !returnPartList.isEmpty())
			{form.setReturnPartsXML(xmlGenerator.convertRMAReturnPartListToXML(returnPartList, true));}
		List<ActivityNote> notes = activity.getActivityNoteList();
		form.setNotesXML(xmlGenerator.convertActivityNoteListToXML(notes));
		form.setRecommendedPartsXML(xmlGenerator.convertRecommendedPartList(activity.getRecommendedPartList()));
		List<AdditionalPaymentRequest> additionalPaymentRequestList = activity.getAdditionalPaymentRequestList();
		if (additionalPaymentRequestList != null && !additionalPaymentRequestList.isEmpty()) {
			form.setAdditionalPaymentRequestsXML(xmlGenerator.convertAdditionalPaymentListToXML(
					additionalPaymentRequestList));
		}
		List<TechnicianInstruction> technicianInstructions = activity.getServiceInstructionList();
		if (technicianInstructions != null && !technicianInstructions.isEmpty())
			{form.setTechnicianInformationListXML(xmlGenerator
					.convertTechnicianInformationListToXML(technicianInstructions));}
		
		ListOfValues activitySubStatus = activity.getActivitySubStatus();
		if(activitySubStatus == null){
			throw new IllegalArgumentException("Failed to open Claim Detail page: <br/> ActivitySubStatus is null");
		}
		String timezoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, timezoneOffset);
		String debriefStatus = "";
		if(activity.getDebrief() != null){
			debriefStatus = activity.getDebrief().getDebriefStatus();
		}
		// Added for Showing the attachments in Details Page
		ResourceURL resOutPutURL = response.createResourceURL();
        resOutPutURL.setResourceID("outPutFileURLRequest");
        model.addAttribute("outPutFileURLRequest", resOutPutURL.toString());
        
		if(activity.getAttachmentList()!=null){
			logger.debug(" Attachment activity.getAttachmentList() size "+activity.getAttachmentList().size());
			
			List<Attachment> attachmentList = activity.getAttachmentList();
			List<Attachment> modifiedAttachmentList = new ArrayList<Attachment>();
			// Setting size for display	
			if(attachmentList!=null){
				for(Attachment attachment:attachmentList){
					Attachment modifiedAttachment = new Attachment();
					logger.debug("Attachment name : "+attachment.getAttachmentName());
					logger.debug("Attachment ActivityId : "+attachment.getActivityId());
					logger.debug("Attachment Size : "+attachment.getSize());
					modifiedAttachment.setAttachmentName(attachment.getAttachmentName());
					modifiedAttachment.setActivityId(attachment.getActivityId());
					modifiedAttachment.setActualFileName(attachment.getActualFileName());
					modifiedAttachment.setIdentifier(attachment.getIdentifier());
					modifiedAttachment.setType(attachment.getType());
										
					double fileSizeDisplay=attachment.getSize();
					logger.debug("File Size is " + fileSizeDisplay);
					fileSizeDisplay=fileSizeDisplay/1024;
					BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
					roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
					logger.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
					modifiedAttachment.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
					modifiedAttachmentList.add(modifiedAttachment);
				}
			}
		form.setAttachmentList(modifiedAttachmentList);
		}
		//
		model.addAttribute("showCloseButtonFlag", ServiceStatusUtil.isRequestAbleToBeDebrief(activitySubStatus.getValue(),debriefStatus));
		model.addAttribute("showUpdateButtonFlag",ServiceStatusUtil.isRequestAbleToBeUpdate(activitySubStatus.getValue(),debriefStatus,activity.getServiceRequest().getServiceRequestType().getValue()));
		model.addAttribute("closeOutRequestFlag", request.getParameter("closeOutRequestFlag"));
		
		logger.debug("failure code is ==== "+form.getActivity().getActualFailureCode().getValue());
		logger.debug("failure code is 1111 ==== "+form.getActivity().getDebrief().getActualFailureCode().getValue());
		// added to translate each value for problem code levels start
		
		// retrieve the lov for problem code ========================================
				 localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract("ACTUAL_FAIL_CD", null, request.getLocale());
				 localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
				 dbLOVMap = new HashMap<String, String>();
				if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
					for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
						dbLOVMap.put(localizedLOV.getValue().toLowerCase(), localizedLOV.getName());
					}
				}
				//	String value = (String) newMap.get("my_code");
				logger.debug("db LOV map size is "+dbLOVMap.size());
				logger.debug("My Map in DB is "+dbLOVMap);
				//end ==========================================
		
		String[] failureCodeValues = null;
		String failureCode = "";
		if(form.getActivity().getDebrief().getActualFailureCode().getValue().indexOf(";") != -1){
			failureCodeValues = form.getActivity().getDebrief().getActualFailureCode().getValue().split(";");
			for(int i=0; i<failureCodeValues.length; i++ ){
				logger.debug("failureCodeValues "+i+" "+failureCodeValues[i]);
				String valuefromDB = (String) dbLOVMap.get(failureCodeValues[i].toLowerCase());
				logger.debug("from map value is "+valuefromDB);
					if(null != valuefromDB && !valuefromDB.trim().equals("")){
						if(!failureCode.equals("")){
							failureCode = failureCode+"   ;   "+valuefromDB; 
						}
						else{
							failureCode = valuefromDB;
						}
					}
					else{
						if(!failureCode.equals("")){
							failureCode = failureCode+"   ;   "+failureCodeValues[i]; 
						}
						else{
							failureCode = failureCodeValues[0];
						}
						//failureCode += failureCodeValues[i];
					}
				}
		}
		else{
			failureCode = form.getActivity().getDebrief().getActualFailureCode().getValue();
				
				String valuefromDB = (String) dbLOVMap.get(failureCode);
				logger.debug("from map value is "+valuefromDB);
					if(null != valuefromDB && !valuefromDB.trim().equals("")){						
							failureCode = valuefromDB;						
					}					
				
		}
		
		
		// added to translate each value for problem code levels end
		
		
		logger.debug("failure code is 2222 ==== "+form.getActivity().getDebrief().getActualFailureCode().getName());
		
		//form.getActivity().getActualFailureCode().setName(failureCode);
		logger.debug("Translated value is === "+failureCode);
		form.getActivity().getDebrief().getActualFailureCode().setName(failureCode);
		
		model.addAttribute("serviceRequestDetailForm", form);
		
		// started here
				 localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue(), null, request.getLocale());
				 localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
				//Map<String, String> dbLOVMap = new HashMap<String, String>();
				if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
					for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
						dbLOVMap.put(localizedLOV.getValue().toLowerCase(), localizedLOV.getName());
					}
				}
				logger.debug("db lov map for resolution code is "+dbLOVMap);
				// ended here
				
				String resolutionCodeValue = form.getActivity().getResolutionCode().getValue();
				if(null != resolutionCodeValue && !resolutionCodeValue.trim().equals("")){
					String valuefromDB = (String) dbLOVMap.get(resolutionCodeValue.toLowerCase());
					logger.debug("value from db is "+valuefromDB);
					if(null != valuefromDB){
						form.getActivity().getResolutionCode().setValue(valuefromDB);
					}					
					
				}
				logger.debug("form.getActivity().getResolutionCode().setValue(valuefromDB) is "+form.getActivity().getResolutionCode().getValue());
		
		/**********Added as part of MPS Roles ***************/
		boolean isTechnician = containsIgnoreCase(PortalSessionUtil.getUserRoles(session),
				LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
		boolean isServiceMgr = PortalSessionUtil.getUserRoles(session).contains(LexmarkConstants.ROLE_SERVICE_MANAGER)?true:false;
		model.addAttribute("isServiceMgr", isServiceMgr);
		model.addAttribute("isTechnician", isTechnician);
		/*********** End ************/

		final String from = request.getParameter("from");
		PortletURL url = response.createRenderURL();
		if ("paymentDetail".equals(from) && StringUtils.isNotEmpty(request.getParameter("paymentId"))) {
			url.setParameter("action", "showPaymentDetail");
			url.setParameter("paymentId", request.getParameter("paymentId"));
			url.setParameter(LexmarkConstants.TIMEZONE_OFFSET, timezoneOffset);
			model.addAttribute("returnURL", url.toString());
		} else {
			model.addAttribute("returnURL", url.toString());
		}
		model.addAttribute("from", from);

		return "serviceRequest/requestsDetailView";
	}
	
	
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=paymentRequestDrillDown")
	public String showPaymentRequestDrillDownPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		logger.debug("-------------paymentRequestDrillDown started---------");
		logger.info(request.getParameter("lightBox"));
		String serviceRequestNumber = request.getParameter("serviceRequestNumber");
		PortletSession session = request.getPortletSession();
		
		AccountPayableDetailContract accountPayableDetailContract = new AccountPayableDetailContract();
		accountPayableDetailContract.setServiceRequestNumber(serviceRequestNumber);
		accountPayableDetailContract.setMdmId(PortalSessionUtil.getMdmId(session));
		accountPayableDetailContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		
		ObjectDebugUtil.printObjectContent(accountPayableDetailContract, logger);
		AccountPayableDetailResult accountPayableDetailResult = paymentsService.retrieveAccountPayableDetail(accountPayableDetailContract);
		PaymentRequestDrillDownForm paymentRequestDrillDownForm = new PaymentRequestDrillDownForm();
		if(accountPayableDetailResult.getAccountPayableDetail()!=null){
			AccountPayableDetail accountPayableDetail = accountPayableDetailResult.getAccountPayableDetail();
			paymentRequestDrillDownForm.setRequestNumber(accountPayableDetail.getRequestNumber());
			paymentRequestDrillDownForm.setCustomerAccountName(accountPayableDetail.getCustomerAccountName());
			paymentRequestDrillDownForm.setTotalPartFee(accountPayableDetail.getTotalPartFee());
			paymentRequestDrillDownForm.setTotalFulfillmentFee(accountPayableDetail.getTotalFulfillmentFee());
			paymentRequestDrillDownForm.setTotalLabor(accountPayableDetail.getTotalLabor());
			paymentRequestDrillDownForm.setTotalAdditionalPayments(accountPayableDetail.getTotalAdditionalPayments());
			paymentRequestDrillDownForm.setRequestNumber(accountPayableDetail.getRequestNumber());
			List<AccountPayableDetailActivity> activityList = new ArrayList<AccountPayableDetailActivity>();
			for (AccountPayableDetailActivity accountPayableDetailActivity : accountPayableDetail.getActivities()){
				AccountPayableDetailActivity activity = new AccountPayableDetailActivity();
				activity.setPartNumber(accountPayableDetailActivity.getPartNumber());
				activity.setPartDescription(accountPayableDetailActivity.getPartDescription());
				activity.setQuantity(accountPayableDetailActivity.getQuantity());
				activity.setPartFee(accountPayableDetailActivity.getPartFee());
				activity.setFulfillmentFee(accountPayableDetailActivity.getFulfillmentFee());
				activity.setAmount(accountPayableDetailActivity.getAmount());
				activityList.add(activity);
			}
			paymentRequestDrillDownForm.setActivities(activityList);
		}
		model.addAttribute("paymentRequestDrillDownForm", paymentRequestDrillDownForm);
		return "serviceRequest/paymentRequestDrillDownLightBox";
	}
	
	/**
	 * Added as part of MPS Roles
	 * @param l 
	 * @param s 
	 * @return boolean 
	 */
	private boolean containsIgnoreCase(List <String> l, String s){
		Iterator <String> it = l.iterator();
		while(it.hasNext()){
		if(it.next().equalsIgnoreCase(s))
		{ return true;}
		}
		return false;
	}
	
	/**
	 * @return String 
	 */
	@RequestMapping(params = "action=printServiceRequestDetail")
	public String showPrintServiceRequestDetailPage() {
		return "serviceRequest/requestsDetailPrintView";
	}

	/**
	 * @param activity 
	 * @param locale 
	 */
	private void localizeAdditionalServiceRequired(Activity activity, Locale locale) {
		if (activity.getDebrief() != null && activity.getDebrief().getDebriefActionStatus() != null) {
			String debriefActionStatus = activity.getDebrief().getDebriefActionStatus();
			if (DebriefActionStatusEnum.INCOMPLETE.getValue().equals(debriefActionStatus)) {
				activity.getDebrief().setDebriefActionStatus(
						PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME,
								"claim.lebel.returnRequired.yes", locale));
			} else {
				activity.getDebrief().setDebriefActionStatus(
						PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME,
								"claim.lebel.returnRequired.no", locale));
			}
		}
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("downServiceRequestDetailPdfURL")
	public void downServiceRequestDetailPdfURL(Model model, ResourceRequest request, ResourceResponse response) 
			throws Exception {
		ActivityDetailContract contract = ContractFactory.createActivityDetailContract(request);
		contract.setDebriefFlag(false);
		ActivityDetailResult activityDetailResult = partnerRequestsService.retrieveActivityDetail(contract);
		
		Activity activity = activityDetailResult.getActivity();
		activity.getServiceRequest().getAsset().setProductImageURL(
				ControllerUtil.retrieveProductImageUrl(productImageService, activity.getServiceRequest().getAsset()
						.getProductTLI()));
		Locale locale = request.getLocale();

		PartnerNotificationsContract partnerNotificationsContract = new PartnerNotificationsContract();
		partnerNotificationsContract.setServiceRequestId(activity.getServiceRequest().getId());
		partnerNotificationsContract.setEmailAddress(activity.getEmailAddress());
		PartnerNotificationsResult partnerNotificationsResult = null;
		partnerNotificationsResult = partnerRequestsService.retrievePartnerNotifications(partnerNotificationsContract);
		List<ServiceRequestActivity> serviceRequestActivityList = partnerNotificationsResult.getServiceRequestActivityList();
		
		ServiceActivityHistoryListContract historyListContract = ContractFactory.createServiceActivityHistoryListContract(activity.getServiceRequest().getAsset().getAssetId(),activity.getServiceRequest().getId());
		ServiceActivityHistoryListResult historyListResult = partnerRequestsService.retrieveServiceActivityHistoryList(historyListContract);
		ControllerUtil.localizeActivityListForServiceHistory(historyListResult.getActivityList(), serviceRequestLocaleService, locale);
		
		PortletSession session = request.getPortletSession();
		if(!PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(session)){
			activity.setAdditionalPaymentRequestList(null);
		}
		
		// localize activityStatus, etc
		ControllerUtil.localizeActivity(activity, serviceRequestLocaleService, request.getLocale());

		// localize additional service required
		localizeAdditionalServiceRequired(activity, request.getLocale());

		// localize partLineItem
		if (activity.getOrderPartList() != null) {
			ControllerUtil.batchLocalizePart(activity.getOrderPartList(), serviceRequestLocaleService,
					request.getLocale());
		}
		if (activity.getReturnPartList() != null) {
			ControllerUtil.batchLocalizePart(activity.getReturnPartList(), serviceRequestLocaleService,
					request.getLocale());
		}
		if (activity.getRecommendedPartList() != null) {
			ControllerUtil.batchLocalizePart(activity.getRecommendedPartList(), 
					serviceRequestLocaleService, request.getLocale());
		}
		//localize additional payments
		if(activity.getAdditionalPaymentRequestList() != null){
			ControllerUtil.batchLocalizeAdditionPayments(activity.getAdditionalPaymentRequestList(), serviceRequestLocaleService, request.getLocale());
		}
		float timezoneOffset = 0;
		String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		if (!StringUtils.isEmpty(timezoneOffsetStr)) {
			timezoneOffset = Float.valueOf(timezoneOffsetStr).floatValue();
		}
		PdfDataExporter pdfDataExporter = new PdfDataExporter();
		pdfDataExporter.setLocale(locale);
		pdfDataExporter.setTimezoneOffset(timezoneOffset);
		pdfDataExporter.generateServiceRequestDetailPdf(activity,serviceRequestActivityList,historyListResult.getActivityList(),response);
	}
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("outPutFileURLRequest")
	public void outPutCSVFile(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{
		logger.debug("------------- --outPutDownloadFile start---------["+System.nanoTime()+"]");
		AttachmentContract contract = new AttachmentContract();
		String attachmentName=request.getParameter("userFileName");
		String displayAttachmentName=request.getParameter("attachmentName");
		
		contract.setUserFileName(attachmentName);
		contract.setIdentifier(request.getParameter("activityId"));
		contract.setRequestType("Sr Update");
		logger.debug("Contract value we are sending userFileName "+request.getParameter("userFileName")+" ::: activity id is "+request.getParameter("activityId")
				+" ::: attachmentName is "+request.getParameter("attachmentName"));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		
		try {
			logger.debug("Calling the amind service for download attachment");
			
			AttachmentResult attachmentResult = attachmentService.downloadAttachment(contract);
			logger.debug("------------- attachmentResult "+attachmentResult);
			
			InputStream fileStream = attachmentResult.getFileStream();
			logger.debug("service call done fileStream :: "+fileStream);
	        
			// Reading the File Stream CI6
			
			if(fileStream!=null){
				logger.debug("service call done fileStream Not Null:: "+fileStream.toString());	
			
			response.setProperty("Content-disposition", "attachment; filename=\"" + displayAttachmentName +"\"");
			if(attachmentName.indexOf("jpg") > 0) {
				response.setContentType("image/jpg");
	        }else if(attachmentName.indexOf("gif") > 0) {
	        	response.setContentType("image/gif");
	        }else if(attachmentName.indexOf("pdf") > 0) {
	        	response.setContentType("application/pdf");
	        	
	        }else if(attachmentName.indexOf("html") > 0) {
	        	response.setContentType("text/html");
	        }else if(attachmentName.indexOf("zip") > 0) {
	        	response.setContentType("application/zip");
	        }else if(attachmentName.indexOf("txt") > 0 ){
	        	response.setContentType("text/plain");
	        }else if(attachmentName.indexOf("xls")>0){
	        	response.setContentType("application/vnd.ms-excel");
	        }else if(attachmentName.indexOf("pdf")>0){
	        	response.setContentType("application/pdf");
	        }else if(attachmentName.indexOf("doc")>0){
	        	response.setContentType("application/msword");
	        }else if(attachmentName.indexOf("xlsx")>0){
	        	response.setContentType("application/vnd.ms-excel");
	        }else if(attachmentName.indexOf("docx")>0){
	        	response.setContentType("application/msword");
	        }else if(attachmentName.indexOf("csv")>0){
	        	response.setContentType("application/vnd.ms-excel");
	        }else if(attachmentName.indexOf("vsd")>0){
	        	response.setContentType("application/x-visio");
	        }
			
			 InputStream inputStream = fileStream;
			
			  OutputStream out = response.getPortletOutputStream();
			  byte buf[]=new byte[1024];
			  int len;
			  while((len=inputStream.read(buf))>0)
			  {out.write(buf,0,len);}
			  
			  out.close();
			  inputStream.close();
			  logger.debug("\nFile is created...................................");
		}
		else{
			logger.debug("service call done fileStream is Null !!!! ");	
			response.setProperty("Content-disposition", "attachment; filename=Error_in_file_download.doc");
			response.setContentType("application/msword");
			OutputStream out = response.getPortletOutputStream();
			byte buf[]=("The file "+ attachmentName + " could not downloaded.").getBytes();
			out.write(buf);
			out.flush();
			out.close();
			logger.debug("\nFile is created...................................");
		}
		
			
	}catch (IOException e){
		logger.debug("RequestController exception IOException : " + e.getMessage());
	}
	catch(Exception exception){
		logger.debug("RequestController  exception : " + exception.getMessage());
		OutputStream out = response.getPortletOutputStream();
		
	}
		finally {
		    	logger.debug("--------------- Reached finally block -----------------");
				}
	logger.debug("----------------outPutDownloadFile ended---------["+System.nanoTime()+"]");
	    	logger.debug("--------------- Reached finally block -----------------");
	    	logger.debug("----------------outPutDownloadFile ended---------["+System.nanoTime()+"]");
	    }
	
	
		/**
		 * @param fileUrl 
		 * @return String 
		 * @throws java.io.IOException 
		 * @throws MalformedURLException 
		 */
		public static String getMimeType(String fileUrl) throws java.io.IOException, MalformedURLException 
		{
			String type = null;
			URL u = new URL(fileUrl);
			URLConnection uc = null;
			uc = u.openConnection();
			type = uc.getContentType();
			return type;
		}
	}
