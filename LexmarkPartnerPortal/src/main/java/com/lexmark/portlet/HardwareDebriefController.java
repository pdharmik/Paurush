/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: HardwareDebriefController.java
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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.ActivityDetailContract;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.FRUPartDetailContract;
import com.lexmark.contract.FRUPartListContract;
import com.lexmark.contract.HardwareDebriefContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.source.RequestAcceptContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.Debrief;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exporter.DataExporter;
import com.lexmark.exporter.DataExporterFactory;
import com.lexmark.form.FileUploadForm;
import com.lexmark.form.HardwareDebriefForm;
import com.lexmark.form.MapForm;
import com.lexmark.portlet.common.AttachmentController;
import com.lexmark.result.ActivityDetailResult;
import com.lexmark.result.ActivityListResult;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.AttachmentResult;
import com.lexmark.result.FRUPartDetailResult;
import com.lexmark.result.FRUPartListResult;
import com.lexmark.result.HardwareDebriefResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.source.RequestAcceptResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.LBSLocationService;
import com.lexmark.service.api.PartnerHardwareInstallDebriefOfflineModeService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.mock.PartnerRequestsServiceImpl;
import com.lexmark.service.impl.real.domain.LBSAddress;
import com.lexmark.service.impl.real.domain.LBSLocationBuilding;
import com.lexmark.service.impl.real.domain.LBSLocationFloor;
import com.lexmark.service.impl.real.domain.LBSLocationSite;
import com.lexmark.service.impl.real.domain.LBSLocationZone;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.ExceptionUtil;
import com.lexmark.util.ImageUtil;
import com.lexmark.util.JSONEncryptUtil;
import com.lexmark.util.JsonUtil;
import com.lexmark.util.LexmarkUserUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.webservice.api.HardwareDebriefService;
import com.liferay.portal.util.PortalUtil;
/**
 * @author wipro 
 * @version 2.1 
 */
@Controller
@RequestMapping("VIEW")

public class HardwareDebriefController extends BaseController{
	
	private static Logger LOGGER = LogManager.getLogger(HardwareDebriefController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String CLASS_NAME="HardwareDebriefController.java";
	
	private static final String METH_SHOW_PART_LIST_POPUP ="showPartListPopUp";
	private static final String METH_RETRIEVE_PART_LIST ="retrievePartList";
	private static final String EXCEPTION_CLAIM_CREATE_RETRIEVE_PART = "exception.claimCreate.retrievePart";
	private static final String MESSAGE_CLAIM_CREATE_RETRIEVE_PART = "message.claimCreate.retrievePart";
	
	
	@Autowired
	private GlobalService globalService;
	

	private AttachmentController attachmentController;
	@Autowired
	private GridSettingController gridSettingController;
	
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	
	
	@Autowired
	private PartnerRequestsService partnerRequestsService;
	
	
	@Autowired
	private HardwareDebriefService hardwareDebriefService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private PartnerHardwareInstallDebriefOfflineModeService partnerHardwareInstallDebriefOfflineModeService;
	
	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private LBSLocationService lbsLocationService;
	
	private String lbsEndpointURL;
	
	/**
	 * @param binder 
	 * @param locale 
	 */
	@InitBinder(value = { "hardwareDebriefForm"})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		String METHOD_NAME = "initBinder";

		LOGGER.debug("target is " + binder.getTarget().toString()+"Language is " + locale.getLanguage()
				+"country is " + locale.getCountry()+"format is " + DateUtil.getDateFormatByLang_partner(locale.getLanguage()));
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang_partner(locale.getLanguage())+" HH:mm");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

	}


	
	/**
	 * @param request 
	 * @param model 
	 * @return strign 
	 * This method returns the defualt page for the hardware debrief.
	 * It also handles request for accept and update call from view page.
	 * After returning from webmethods call this method is called to show the 
	 * close out pages.
	 * 
	 */
	@RequestMapping
	public String getHardwarePage(RenderRequest request , Model model){
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

		//Three constants for "INSTALL" "DEINSTALL" "MOVE" are to be set for JSP's
		//Timezoneoffset needs to be sent
		
		/*This is for checking if 
		 * accessed from wrong URL
		 * */
		if(StringUtils.isBlank(httpReq.getParameter("activityId")) && StringUtils.isBlank(request.getParameter("activityId"))){
			return "/exception/notAuthorized";
		}
		
		String viewOrCloseOut=httpReq.getParameter(LexmarkPPConstants.viewOrCloseout);
		LOGGER.debug("Accept Update Value "+httpReq.getParameter(LexmarkPPConstants.ACCEPTANDUPDATE));
		if(StringUtils.isNotBlank(httpReq.getParameter(LexmarkPPConstants.ACCEPTANDUPDATE))){
			/**
			 * this section is for accept and update from view pages
			 * */
			LOGGER.debug("the request is from ACcept and update from View page");
			
			RequestAcceptContract requestContract= ContractFactory.getRequestAcceptContract(request);
			requestContract.setActivityId(StringUtils.isNotBlank(httpReq.getParameter("activityId"))?httpReq.getParameter("activityId"):"");
			requestContract.setRequestNumber(StringUtils.isNotBlank(httpReq.getParameter("srNumber"))?httpReq.getParameter("srNumber"):"");
			requestContract.setStatus(StringUtils.isNotBlank(httpReq.getParameter("status"))?httpReq.getParameter("status"):"");
			
			RequestAcceptResult result=amindAcceptRejectResult(requestContract);
			if(!result.getResult()){
				viewOrCloseOut="view";
				model.addAttribute("exceptionOccured", LexmarkPPConstants.TRUE_ATTR);
			}
			
		}
		LOGGER.debug("after checking accept and update");
		
		/*FSE Selection Flag Set CR 13606*/
		boolean showFseSelector = false;
		PortletSession portletSession = request.getPortletSession();
		if (PortalSessionUtil.getLexmarkFSEFlag(portletSession)){
			showFseSelector = true;
			List<String> userRoleNameList = LexmarkUserUtil.getUserRoleNameList(request);
			for (String roleName : userRoleNameList) {
				if (LexmarkConstants.ROLE_PUBLISHING.equalsIgnoreCase(roleName) || 
						LexmarkConstants.ROLE_ANALYST.equalsIgnoreCase(roleName) ||
						LexmarkConstants.ROLE_SERVICES_PORTAL_ADMINISTRATOR.equalsIgnoreCase(roleName)) {
					//Employee is more than an FSE, need to treat them as not an FSE for this portlet
					showFseSelector = false;
				}
			}
		}
		/*End FSE Flag Selection*/
		model.addAttribute("showFseSelector",showFseSelector);
		/**
		 * Below section is to check whether return from wmcall 
		 * is successful or not
		 * if its a failure need to provide user entered data
		 * otherwise go for new service call .
		 * */
		boolean isReturnFromActionRequest=false;
		if(model.containsAttribute("hardwareDebriefForm")&&model.containsAttribute("exceptionOccured")){
			isReturnFromActionRequest=true;
		}
		//Ends checking wmcall
		LOGGER.debug("model.containsAttribute(\"hardwareDebriefForm\")"+model.containsAttribute("hardwareDebriefForm")
		+ "\nmodel.containsAttribute(\"savedSuccess\")"+model.containsAttribute("savedSuccess")+
		"\n after checking model" +isReturnFromActionRequest);
		
		PortletSession session = request.getPortletSession();
		
		/**
		 * This section is for clearing the 
		 * session file map if the page is refreshed.
		 * */
		if(!isReturnFromActionRequest){		
			try{
				attachmentController.clearUploadedFileFromSession(request, LexmarkPPConstants.SESSION_FILE_MAP_ForHWDebrief_CloseOut);
			}catch(Exception e){
				LOGGER.error("error occured while clearing the map"+e.getMessage());
			}
		}
		
		String srType=httpReq.getParameter(LexmarkPPConstants.SRTYPE);
		
		
		if(StringUtils.isBlank(srType)){
			//This will satisfy when returning from save or closeout debrief
			srType=request.getParameter(LexmarkPPConstants.SRTYPE);
			
			LOGGER.debug("srtype is coming as "+srType);
		}
		
		HardwareDebriefForm form=null;
		if(!isReturnFromActionRequest){		
			
				ActivityDetailContract contract = ContractFactory.getHWDebriefDetailContract(httpReq,request);
				LOGGER.debug("------- start activity Contract details -----");
				ObjectDebugUtil.printObjectContent(contract, LOGGER);
				LOGGER.debug("------- end activity Contract details -----");
				
				
				/**
				 * This section is for gettting the detail.
				 * */
				ActivityDetailResult result=null;
				try {
					if(contract.isDebriefFlag()){
						LOGGER.debug("this is offline debrief call");
						result =partnerHardwareInstallDebriefOfflineModeService.retrieveOfflineModeActivityDetails(contract);
					}else{
						LOGGER.debug("this is hardware debrief call");
						LOGGER.debug("-------------------------- Before Partner Activity Detail Call ----------------------");
						long timeBeforeDetailCall=System.currentTimeMillis();
						result =partnerRequestsService.retrieveActivityDetail(contract);
						
						LOGGER.debug("-------------------------- After Partner Activity Detail Call ----------------------");	
						PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_ACTIVITYDETAILS, timeBeforeDetailCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
					}
					
				} catch (Exception e) {
					model.addAttribute(LexmarkPPConstants.EXCEPTIONOCCURED_detail, true);
					LOGGER.error("error occured getting detail"+e.getMessage());
				}
				
				String customerAccountId = "";				
				form = new HardwareDebriefForm();
				/*
				 * This section is for offline debrief 
				 * if the request comes from offline debreif then don't show the 
				 * buttons in the view
				 * */
				if(contract.isDebriefFlag()){
					form.setOfflineDebrief(true);
				}
				form.setBackURL(StringUtils.isBlank(httpReq.getParameter(LexmarkPPConstants.BACKURL))==true?"services-requests":httpReq.getParameter(LexmarkPPConstants.BACKURL));
				form.setTimezoneOffset(Float.valueOf(httpReq.getParameter(LexmarkConstants.TIMEZONE_OFFSET)));
				form.refreshSubmitToken(request);
				if(result!=null && result.getActivity() != null){
					
					
					
					
					List<PageCounts> deInstalledPageCount=new ArrayList<PageCounts>();
					List<PageCounts> InstalledPageCount=new ArrayList<PageCounts>();
					for(PageCounts pageCount:result.getActivity().getServiceRequest().getAsset().getPageCounts()){
						
					if(pageCount.getDeinstalledType()!=null){
							
							
							PageCounts pc=new PageCounts();
							pc.setCount(pageCount.getDeinstalledCount());
							pc.setType(pageCount.getDeinstalledType());
							deInstalledPageCount.add(pc);
						}
						else if(pageCount.getType()!=null){
							

							PageCounts pc=new PageCounts();
							pc.setCount(pageCount.getCount());
							pc.setType(pageCount.getType());
							InstalledPageCount.add(pc);
						}
						
					}
					
					result.getActivity().getServiceRequest().getAsset().setDeInstAssetPageCounts(deInstalledPageCount);
					result.getActivity().getServiceRequest().getAsset().setPageCounts(InstalledPageCount);
					LOGGER.debug("INSTALL TYPE COUNT--"+result.getActivity().getServiceRequest().getAsset().getPageCounts().size());
					LOGGER.debug("DEINSTALL TYPE COUNT--"+result.getActivity().getServiceRequest().getAsset().getDeInstAssetPageCounts().size());
					
					
					form.setActivity(result.getActivity());
					
					form.setUserEnteredActivity(result.getActivity());
					
					form.setSrType(srType);
					
					// This is temporary
					
					if(result.getActivity().getCustomerAccount()!=null && result.getActivity().getCustomerAccount().getAccountId()!=null){
						customerAccountId = result.getActivity().getCustomerAccount().getAccountId();
					}
					portletSession.setAttribute(LexmarkConstants.CUSTOMERACCOUNTID, customerAccountId);
					LOGGER.debug("Customer Account Id "+result.getActivity().getCustomerAccount().getAccountId());					
				}
				LOGGER.debug("srtype ="+srType+"srNum="+httpReq.getParameter(LexmarkPPConstants.SRNUMBER));
				form.setSrType(srType);
				model.addAttribute("hardwareDebriefForm", form);
				
				if(StringUtils.isNotBlank(viewOrCloseOut) && !viewOrCloseOut.equalsIgnoreCase("view")){
					//These will be populated if the request is not from view request.
					populateCloseOutInformation(request,form);
				}if(!isReturnFromActionRequest){
					populateCloseOutInformation(request,form);
				}
		
		}
		
		FileUploadForm fileUploadForm=new FileUploadForm();
		fileUploadForm.setSessionFileKey(LexmarkPPConstants.SESSION_FILE_MAP_ForHWDebrief_CloseOut);
		fileUploadForm.setPageType(LexmarkPPConstants.HARDWAREORDER);
		LOGGER.debug("session file is"+session.getAttribute(LexmarkPPConstants.SESSION_FILE_MAP_ForHWDebrief_CloseOut));
		model.addAttribute("fileUploadForm", fileUploadForm);
		if(session.getAttribute(LexmarkPPConstants.SESSION_FILE_MAP_ForHWDebrief_CloseOut)!=null){
			model.addAttribute("fileMapInSesion", session.getAttribute(LexmarkPPConstants.SESSION_FILE_MAP_ForHWDebrief_CloseOut));
		}
		
		model.addAttribute(LexmarkPPConstants.SRTYPE,srType );
		
		setModelParams(form,session);//Addded for LBS 1.5
		
		
		
		if(StringUtils.isNotBlank(viewOrCloseOut) && viewOrCloseOut.equalsIgnoreCase("view")){
			
			changePartList(form);
			changeAdditionalPartList(form);
			LOGGER.debug("Page is hwdebriefInstallDeInstallMoveView");
			return "/hardwareDebrief/HWInstallDeInstallMove/hwdebriefInstallDeInstallMoveView";
		}else {
			if(srType.toUpperCase().contains("INSTALL/DECOMMISSION")){
				model.addAttribute(LexmarkPPConstants.isHWINSTALL,true);
				/**
				 * The part list should not change 
				 * if any exception has occured.
				 * this is checking for exception and 
				 * preserving user data.
				 * */
				if(!isReturnFromActionRequest){		
					changePartList(form);
					changeAdditionalPartList(form);
				}
				
				LOGGER.debug("Page is hwDebriefInstallCloseOut 1");
				return "/hardwareDebrief/HWInstallDeInstallMove/hwDebriefInstallCloseOut";
			}
			else if(srType.toUpperCase().contains("DECOMMISSION")){
				model.addAttribute(LexmarkPPConstants.isHWDEINSTALL,true);
				LOGGER.debug("Page is hwDebriefDeinstallCloseOut");
				return "/hardwareDebrief/HWInstallDeInstallMove/hwDebriefDeinstallCloseOut";
			}else if(srType.toUpperCase().contains("MOVE")){
				model.addAttribute(LexmarkPPConstants.isHWMOVE,true);
				LOGGER.debug("Page is hwDebriefMoveCloseOut");
				return "/hardwareDebrief/HWInstallDeInstallMove/hwDebriefMoveCloseOut";
				
			}else if(srType.toUpperCase().contains("INSTALL")||srType.toUpperCase().contains("CHANGE")){
				model.addAttribute(LexmarkPPConstants.isHWINSTALL,true);
				/**
				 * The part list should not change 
				 * if any exception has occured.
				 * this is checking for exception and 
				 * preserving user data.
				 * */
				if(!isReturnFromActionRequest){		
					changePartList(form);
					changeAdditionalPartList(form);
				}
				
				LOGGER.debug("Page is hwDebriefInstallCloseOut 2");
				return "/hardwareDebrief/HWInstallDeInstallMove/hwDebriefInstallCloseOut";
			}
			
			
			else{
				return "/exception/notAuthorized";
			}
		}
		
	}
	
	
	/**
	 * @param request  
	 * @param form 
	 * This method populate data in the form for close out
	 */
	private void populateCloseOutInformation(PortletRequest request,HardwareDebriefForm form){
		
		PortletSession session=request.getPortletSession();
		Locale locale=request.getLocale();
		
		Boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(session);
		Boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(session);
		String partnerType = ControllerUtil.getPartnerType(isIndirectPartner, isDirectPartner);
		
		Map<String, String> requestsubStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ACTIVITY_SUB_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		form.setRequestStatusMap(requestsubStatusMap);
		
		Map<String, String> pageCountsMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		Map<String, String> pageCountsMonoMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PAGE_COUNTS_MONO.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		if(pageCountsMonoMap !=null){
		pageCountsMap.putAll(pageCountsMonoMap);
		LOGGER.debug("pageCountsMap ::: " + pageCountsMap);
		}
		form.setPageCountsMap(pageCountsMap);
		
		Map<String, String> faxStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.FAX_CONNECTED_TYPE.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		form.setFaxConnectedTypeMap(faxStatusMap);
		/**
		 * 
		 * Below is for populating contact types 
		 * */

		Map<String, String> contactTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.CONTACT_TYPE_FOR_ASSET.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		
		form.setContactTypeMap(contactTypeMap);
		
		Map<String, String> requestStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String srType=httpReq.getParameter(LexmarkPPConstants.SRTYPE);
		if(StringUtils.isBlank(srType)){
			//This will satisfy when returning from save or closeout debrief
			srType=request.getParameter(LexmarkPPConstants.SRTYPE);
			
			LOGGER.debug("srtype is coming as "+srType);
		}
		LOGGER.debug("SR TYPE = " + srType);
		form.setSrType(srType);
		if(srType.toUpperCase().contains("DECOMMISSION") || srType.toUpperCase().contains("MOVE")){
			Map<String, String> partlistStatusMapPrinterType = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.PARTNER_PRINTER_PART_STATUS.getValue(), partnerType,
					serviceRequestLocaleService, locale);
			form.setRecommendedPartlistStatusForPrinterType(partlistStatusMapPrinterType);
		}
		
		
		
		Map<String, String> deviceWorkingCondition = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_DEVICE_CONDITION.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		
		
	
		form.setDeviceWorkingConditionMap(deviceWorkingCondition);
		
		
		/**
		 * Putting transated values to form for 
		 * status sub status
		 * */
		Map<String, String> hwDebriefRequestType = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_REQUEST_LIST.getValue(), null,
				serviceRequestLocaleService, locale);
		/**
		 * Putting transated values to form for 
		 * Special Usage
		 * */
		Map<String, String> specialUsage = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.SPECIAL_USAGE.getValue(), null,
				serviceRequestLocaleService, locale);
		LOGGER.debug("specialUsage ::: " + specialUsage);
		form.setSpecialUsageMap(specialUsage);
		Activity activity=form.getActivity();
		Activity actv = form.getUserEnteredActivity();
		if(activity!=null){
			if(activity.getActivityType()!=null && activity.getActivityType().getValue()!=null){
				
				changeRequestType(activity,hwDebriefRequestType);		
				
			}
			
			if (activity.getActivitySubStatus() != null && activity.getActivitySubStatus().getValue() != null) {
				String localizedActivitySubStatus=null;
				localizedActivitySubStatus = requestsubStatusMap.get(activity.getActivitySubStatus().getValue());
				if (localizedActivitySubStatus == null) {
					localizedActivitySubStatus = activity.getActivitySubStatus().getValue();
				}
				activity.getActivitySubStatus().setName(localizedActivitySubStatus);
			}
			if (activity.getActivityStatus() != null && activity.getActivityStatus().getValue() != null) {
				String localizedActivityStatus=null;
				localizedActivityStatus = requestStatusMap.get(activity.getActivityStatus().getValue());
				if (localizedActivityStatus == null) {
					localizedActivityStatus = activity.getActivityStatus().getValue();
				}
				activity.getActivityStatus().setName(localizedActivityStatus);
			}
			Debrief debrief=activity.getDebrief();
			
			if (debrief!=null && debrief.getDeviceCondition() != null && debrief.getDeviceCondition().getValue()!= null) {
				String localizedDeviceCondition=null;
				localizedDeviceCondition = deviceWorkingCondition.get(debrief.getDeviceCondition().getValue());
				if (localizedDeviceCondition == null) {
					localizedDeviceCondition = debrief.getDeviceCondition().getValue();
				}
				debrief.getDeviceCondition().setName(localizedDeviceCondition);
			}
			//Added for product image url
			Asset device=activity.getServiceRequest()==null?null:activity.getServiceRequest().getAsset();
			//setProductImageURL(device);
			if(device!=null && device.getProductTLI() != null && StringUtils.isNotBlank(device.getProductTLI()))
			{
			device.setProductImageURL(ImageUtil.getPartImage(device.getProductTLI()));	
		}
			if(srType.toUpperCase().contains("INSTALL") || srType.toUpperCase().contains("INSTALL/DECOMMISSION") || srType.toUpperCase().contains("CHANGE")){
				if(activity.getRecommendedPartList()!=null){
					LOGGER.debug("Recommended Parts");
					Map<String, String> partlistStatusMapPrinterType = ControllerUtil.retrieveLocalizedLOVMap(
						SiebelLocalizationOptionEnum.PARTNER_PRINTER_PART_STATUS.getValue(), partnerType,
						serviceRequestLocaleService, locale);
				form.setRecommendedPartlistStatusForPrinterType(partlistStatusMapPrinterType);
				Map<String, String> nonPrinterPartlistStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
						SiebelLocalizationOptionEnum.PARTNER_NON_PRINTER_PART_STATUS.getValue(), partnerType,
						serviceRequestLocaleService, locale);
				form.setRecommendedPartlistStatusForNonPrinterType(nonPrinterPartlistStatusMap);
				}
		}
			}
		if(actv != null){
			if(srType.toUpperCase().contains("INSTALL") || srType.toUpperCase().contains("INSTALL/DECOMMISSION") || srType.toUpperCase().contains("CHANGE")){
				if(actv.getAdditionalPartList() != null){
					LOGGER.debug("Additional Parts");
					Map<String, String> partlistStatusMapPrinterType = ControllerUtil.retrieveLocalizedLOVMap(
							SiebelLocalizationOptionEnum.PARTNER_PRINTER_PART_STATUS.getValue(), partnerType,
							serviceRequestLocaleService, locale);
					form.setRecommendedPartlistStatusForPrinterType(partlistStatusMapPrinterType);
					Map<String, String> nonPrinterPartlistStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
							SiebelLocalizationOptionEnum.PARTNER_NON_PRINTER_PART_STATUS.getValue(), partnerType,
							serviceRequestLocaleService, locale);
					form.setRecommendedPartlistStatusForNonPrinterType(nonPrinterPartlistStatusMap);
				}
		
			}
			
		}
		
	}
	
	/**
	 * @param request 
	 * @param response  
	 * @param model 
	 * @param requestType 
	 * @param  offlineRequests 
	 * @param srID 
	 * @return string 
	 * This method retrieves the hardwaredebrief list from amind. 
	 */
	@ResourceMapping("retrieveOrderHardwareList")
	public String getHardwareOrderList(ResourceRequest request,ResourceResponse response,Model model,
			@RequestParam(required=false,value="requestType") String requestType,
			@RequestParam(required=false,value="offlineRequests") String offlineRequests,
			@RequestParam(required=false,value="srID") String srID){
		
		LOGGER.debug("from Request Tab-->"+request.getParameter("isRequests"));
		ActivityListContract contract = ContractFactory
		.getHardwareDebriefContract(request);
	
		/**
		 * These two variables are to be used for offline hardware requests*/
		if(StringUtils.isNotBlank(offlineRequests)){
			LOGGER.debug("This request is for offline REquests");
			contract.setOfflineInstallDebrief(true);
			contract.setChangeManagementFlag(true);
			Map<String,Object> filterCriteria=	contract.getFilterCriteria();
			if(StringUtils.isNotBlank(srID)){			
				filterCriteria.put("Activity.serviceRequest.serviceRequestNumber", srID);
			}else{
				if(filterCriteria.get("Activity.activityStatus")!=null){
					filterCriteria.put("Activity.serviceRequest.statusType",filterCriteria.get("Activity.activityStatus"));
					filterCriteria.remove("Activity.activityStatus");
				}
			}
			
			
		}
		
		if(request.getParameter("isRequests")!=null && request.getParameter("isRequests").equalsIgnoreCase("true")){
			//LOGGER.debug("isRequests Flag = " + request.getParameter("isRequests"));
			contract.setChangeManagementFlag(false);
		}
		else if(request.getParameter("isMassUpload")!=null && request.getParameter("isMassUpload").equalsIgnoreCase("true")){
			//LOGGER.debug("isMassUpload Flag = " + request.getParameter("isMassUpload"));
			contract.setChangeManagementFlag(true);
		}
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		
		//Service Call To get the list
		
		LOGGER.debug(" after crm session ");
		request.getPortletSession().setAttribute(LexmarkPPConstants.HARDWAREORDERGRIDID, contract.clone());
				
		ActivityListResult activityListResult=null;
		
		try {
			
			activityListResult = amindHardwareList(contract,request);
			
		} catch (Exception e) {
			LOGGER.error("Error occured while retrieving the activity list"+e.getMessage());
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
			
		LOGGER.debug("order list size "+activityListResult.getActivityList()!=null?activityListResult.getActivityList().size():"list is null");
		
		/*FSE Selection Flag Set CR 13606*/
		boolean showFseSelector = false;
		PortletSession portletSession = request.getPortletSession();
		if (PortalSessionUtil.getLexmarkFSEFlag(portletSession)){
			showFseSelector = true;
			List<String> userRoleNameList = LexmarkUserUtil.getUserRoleNameList(request);
			for (String roleName : userRoleNameList) {
				if (LexmarkConstants.ROLE_PUBLISHING.equalsIgnoreCase(roleName) || 
						LexmarkConstants.ROLE_ANALYST.equalsIgnoreCase(roleName) ||
						LexmarkConstants.ROLE_SERVICES_PORTAL_ADMINISTRATOR.equalsIgnoreCase(roleName)) {
					//Employee is more than an FSE, need to treat them as not an FSE for this portlet
					showFseSelector = false;
				}
			}
		}
		/*End FSE Flag Selection*/
		model.addAttribute("showFseSelector",showFseSelector);
		model.addAttribute(LexmarkPPConstants.ORDERLIST, activityListResult.getActivityList());
		model.addAttribute(LexmarkPPConstants.STARTPOS, contract.getStartRecordNumber());
		model.addAttribute(LexmarkPPConstants.TOTALCOUNT,activityListResult.getTotalcount());
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, 
				Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)));
		
		
		LOGGER.debug("request type is "+requestType);
		model.addAttribute("requestType",requestType);
		/**
		 * These two variables are to be used for offline hardware requests*/
		model.addAttribute("offlineRequests",offlineRequests==null?false:offlineRequests.equalsIgnoreCase("true")?true:false);
		model.addAttribute("showActionLink",offlineRequests==null?true:false);
		
		for(Activity activity:activityListResult.getActivityList()){
			activity.getServiceRequest().setExpediteOrder(false);
			
			if(activity.getExpectedCompletionDate()!=null){
				long diffDays=(activity.getExpectedCompletionDate().getTime()-new Date().getTime())/(24 * 60 * 60 * 1000);
				LOGGER.debug("diffDays ="+diffDays);
				if(diffDays<1){
					activity.getServiceRequest().setExpediteOrder(true);	
				}
				
			}
			
		}
		LOGGER.debug("isMassUpload--->"+request.getParameter("isMassUpload"));
		LOGGER.debug("isRequests--->"+request.getParameter("isRequests"));
		if(request.getParameter("isRequests")!=null && request.getParameter("isRequests").equalsIgnoreCase("true")){
			
			model.addAttribute("isRequests", true);
		}
		else if(request.getParameter("isMassUpload")!=null && request.getParameter("isMassUpload").equalsIgnoreCase("true")){
			model.addAttribute("isMassUpload", true);
			
		}
		response.setContentType(LexmarkPPConstants.XMLCONTENT);
		return "hardwareDebrief/hardwareListXml";
		
	}
	
	/**
	 * @param contract 
	 * @param request 
	 * @return ActivityListResult  
	 * @throws Exception 
	 * This is the common method for getting the hardware debrief list from amind 
	 */
	public ActivityListResult amindHardwareList(ActivityListContract contract,PortletRequest request)throws Exception{
		contract.setRequestGridLoading(true);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		
		ActivityListResult activityListResult=null;
		
		
		if(contract.isOfflineInstallDebrief()){
			LOGGER.debug(" calling offline debrief service ");
			activityListResult=partnerHardwareInstallDebriefOfflineModeService.retrieveOfflineModeActivitiesList(contract);
		}else{
			LOGGER.debug(" calling hardware debrief install service");
			activityListResult=partnerRequestsService.retrieveActivityList(contract);
		}
		
		
		
		/**
		 * Request is sent null when this method is called from
		 * MAssupload to get the template download.
		 * */
		if(request!=null){
			PortletSession session=request.getPortletSession();
			Locale locale=request.getLocale();
			Boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(session);
			Boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(session);
			String partnerType = ControllerUtil.getPartnerType(isIndirectPartner, isDirectPartner);
			
			
			Map<String, String> hwdebriefRequestStatus = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), partnerType,
					serviceRequestLocaleService, locale);
		
			
			Map<String, String> hwdebriefRequestStatusDetails = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.ACTIVITY_SUB_STATUS.getValue(), partnerType,
					serviceRequestLocaleService, locale);
			
			Map<String, String> hwDebriefRequestType = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.PARTNER_REQUEST_LIST.getValue(), null,
					serviceRequestLocaleService, locale);
			
			Map<String, String> serviceRequestStatus = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), null,
					serviceRequestLocaleService, locale);
			
			
			List<Activity> activityList=activityListResult.getActivityList();
			for (Activity activity : activityList) {
				if(activity.getActivityType()!=null && activity.getActivityType().getValue()!=null){
					changeRequestType(activity,hwDebriefRequestType);					
					
					
				}
				if (activity.getActivityStatus() != null && activity.getActivityStatus().getValue() != null) {
					String localizedActivityStatus=null;
					localizedActivityStatus = hwdebriefRequestStatus.get(activity.getActivityStatus().getValue());
					if (localizedActivityStatus == null) {
						localizedActivityStatus = activity.getActivityStatus().getValue();
					}
					activity.getActivityStatus().setName(localizedActivityStatus);
				}
				if (activity.getActivitySubStatus() != null && activity.getActivitySubStatus().getValue() != null) {
					String localizedActivitySubStatus=null;
					localizedActivitySubStatus = hwdebriefRequestStatusDetails.get(activity.getActivitySubStatus().getValue());
					if (localizedActivitySubStatus == null) {
						localizedActivitySubStatus = activity.getActivitySubStatus().getValue();
					}
					activity.getActivitySubStatus().setName(localizedActivitySubStatus);
				}
				if (activity.getServiceRequest() != null && activity.getServiceRequest().getStatusType() != null) {
					String localizedserviceRequestStatus=null;
					localizedserviceRequestStatus = hwdebriefRequestStatusDetails.get(activity.getServiceRequest().getStatusType().getValue());
					if (localizedserviceRequestStatus == null) {
						localizedserviceRequestStatus = activity.getServiceRequest().getStatusType().getValue();
					}
					activity.getServiceRequest().getStatusType().setName(localizedserviceRequestStatus);
				}
				
				
				//This section is for retrieving product image url
				if(activity.getRecommendedPartList()!=null){
				for(PartLineItem list:activity.getRecommendedPartList()){
					
				
					if(list.isTypePrinter() && list.getPartNumber()!=null){
						 
						activity.getServiceRequest().getAsset().setProductImageURL(ImageUtil.getPartImage(list.getPartNumber()));
						LOGGER.debug("Printer URL for ACTIVITY --->>"+activity.getServiceRequest().getAsset().getProductImageURL());
						
					}
				}
				}
				//Asset device=activity.getServiceRequest()==null?null:activity.getServiceRequest().getAsset();
				//setProductImageURL(device);
			
				
			}
			
			
			
		}
		
		LOGGER.debug("size is "+activityListResult.getActivityList()!=null?activityListResult.getActivityList().size():"list is null");
		
		
		return activityListResult;
	}
	
	/**
	 * sets Product Image URL
	 * 
	 * @param device 
	 *  
	 */
	private void setProductImageURL(Asset device){
		if(device!=null){
			ProductImageContract productImageContract = new ProductImageContract();
			productImageContract.setPartNumber(device.getProductTLI());
			ProductImageResult productImageResult = null;
			try {
				productImageResult = productImageService
						.retrieveProductImageUrl(productImageContract);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.error("[Exception occured while retrieving product image url]"+e.getMessage());
				return;
			}
			device.setProductImageURL(productImageResult
					.getProductImageUrl());					
		}
	}
	
	/**
	 * retrieve Hardware debrief  Requests and download it csv and pdf format.
	 * 
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("downloadHardwareRequestsURL")
	public void downloadHardwareRequestsURL(ResourceRequest request, ResourceResponse response) {
		String METHOD_NAME = "downloadOrderRequestsURL";

		LOGGER.debug("[START]download hardware order request");
		String downloadType = request.getParameter(LexmarkPPConstants.DOWNLOADTYPE);
		
		
		PortletSession session = request.getPortletSession();
		
		ActivityListContract contract = (ActivityListContract) session.getAttribute(LexmarkPPConstants.HARDWAREORDERGRIDID);
		session.setAttribute(LexmarkPPConstants.HARDWAREORDERGRIDID, contract.clone());
		
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(LexmarkPPConstants.MINUES_ONE));

		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		
		
		
		
		try {
		
			ActivityListResult activityListResult=amindHardwareList(contract,request);
			List<Activity> activityList = activityListResult.getActivityList();
			
			
			
			DataExporter dataExporter = DataExporterFactory.getDataExporter(downloadType);
			dataExporter.setLocale(request.getLocale());
			if(request.getParameter("fromInstallDebrief")!=null && request.getParameter("fromInstallDebrief").equalsIgnoreCase("true"))
			{
				dataExporter.export(response, LexmarkPPConstants.HARDWAREORDER_OFFLINEDEBRIEFS, LexmarkPPConstants.PATTERNS_HARDWAREORDER_EXPORT_OFFLINEDEBRIEFS, activityList);
			}
			else if(request.getParameter("isMassUpload")!=null && request.getParameter("isMassUpload").equalsIgnoreCase("true")){
				
				dataExporter.export(response, LexmarkPPConstants.MASSUPLOAD_EXPORT, LexmarkPPConstants.PATTERNS_MASSUPLOAD, activityList);
			}
			else
			{
				dataExporter.export(response, LexmarkPPConstants.HARDWAREORDER, LexmarkPPConstants.PATTERNS_HARDWAREORDER_EXPORT, activityList);
			}
		}catch(Exception e ){
			LOGGER.error("Error occured "+e.getMessage());
			
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		LOGGER.debug("[END] download order request");


	}
	
	/**
	 * @return string 
	 * This method returns the print view of the Hw Debrief 
	 */
	@RequestMapping(params = "action=printHardwareOrderList")
	public String printOrderListDetailPage() {
		String METHOD_NAME = "printOrderListDetailPage";
		

		return LexmarkPPConstants.ORDERLISTPRINTVIEW;
	}
	
	
	
	

	
	/**
	 * @param request 
	 * @param response 
	 * @param hardwareDebriefForm 
	 * @param model 
	 * @throws Exception 
	 * This method is hit when HW Debrief Install is submitted 
	 */
	@ActionMapping(params="action=submitDebriefRequest")
	public void submitInstallDebrief(
			ActionRequest request, 
			ActionResponse response, 
			@ModelAttribute("hardwareDebriefForm")  HardwareDebriefForm hardwareDebriefForm, 
			Model model) throws Exception{

		
		
		HardwareDebriefResult hwResult=null;
		try{
			
			
			
			checkFormBeforeProcessing(hardwareDebriefForm,request);
			
			
			 
			
			
			
			wmSaveCloseOutDebrief(hardwareDebriefForm,model,hwResult);
			amindAttachmentCall(request,hardwareDebriefForm.getActivity().getActivityId());
			
		}catch(Exception exception){
			 model.addAttribute("exceptionOccured",true);
			 model.addAttribute("hardwareDebriefForm", hardwareDebriefForm);
			 populateCloseOutInformation(request,hardwareDebriefForm);
			 //exception.printStackTrace();
			LOGGER.error("Exception occured during service call "+exception.getCause());
			
		}finally{
		
			LOGGER.debug("model.containsAttribute(\"hardwareDebriefForm\")"+model.containsAttribute("hardwareDebriefForm"));
			setInformationAfterAction(hardwareDebriefForm,response,request,model);
			
		}
		
		
		
		
	
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param hardwareDebriefForm 
	 * @param model 
	 * @throws Exception 
	 * This method is to handle DeInstall request submit 
	 */
	@ActionMapping(params="action=submitDeInstallRequest")
	public void submitDeInstallDebrief(
			ActionRequest request, 
			ActionResponse response, 
			@ModelAttribute("hardwareDebriefForm")  HardwareDebriefForm hardwareDebriefForm, 
			Model model) throws Exception{

		HardwareDebriefResult hwResult=null;
		try{
			checkFormBeforeProcessing(hardwareDebriefForm,request);
			wmSaveCloseOutDebrief(hardwareDebriefForm,model,hwResult);
			amindAttachmentCall(request,hardwareDebriefForm.getActivity().getActivityId());
		}catch(Exception exception){
			model.addAttribute("exceptionOccured",true);
			 
			 //exception.printStackTrace();
			LOGGER.error("Exception occured during service call "+exception.getCause());
			 model.addAttribute("hardwareDebriefForm", hardwareDebriefForm);
			 populateCloseOutInformation(request,hardwareDebriefForm);
		}finally{
		

			
			
			setInformationAfterAction(hardwareDebriefForm,response,request,model);
			
		}
		
		
	
	}
	

	/**
	 * This method is used by Massupload to download the template 
	 * @param request 
	 * @return list 
	 * */
	public List<Activity> getMassUploadTemplate(PortletRequest request){

		/*
		 * 
		 * Get List Starts
		 * */
		String gridId=request.getParameter("gridId");
		PortletSession session = request.getPortletSession();
		ActivityListContract contract = (ActivityListContract)session.getAttribute(gridId);
		session.setAttribute(gridId, contract.clone());
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(LexmarkPPConstants.MINUES_ONE));
		
		ActivityListResult activityListResult=null;
		
		try {
			
			activityListResult = amindHardwareList(contract,null);
			
		} catch (Exception e) {
			LOGGER.error("Error occured while retrieving the activity list"+e.getMessage());
			activityListResult.setActivityList(new ArrayList<Activity>());
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		if(activityListResult.getActivityList()!=null){
			
			LOGGER.debug("order list size "+activityListResult.getActivityList().size());
		}else{
			LOGGER.debug("activity list is null");
			activityListResult.setActivityList(new ArrayList<Activity>());
		}
		
		List<Activity> hardwareList = activityListResult.getActivityList();

		return hardwareList;
		
		
	}
	
	/**
	 * @param resp 
	 * @return string 
	 * This method is for showing partlist popup 
	 */
	@RequestMapping(params = "action=showPartListPopUp")
	public String showPartListPopUp( RenderResponse resp) {
		LOGGER.debug("............... in showPartListPopUp ................. ");

		resp.setContentType("UTF-8");
		return "/hardwareDebrief/HWInstallDeInstallMove/partListPopUp";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return string 
	 * @throws Exception 
	 * This method retrieves partlist for Install request 
	 */
	@ResourceMapping(value="retrievePartList")
	public String retrievePartList (ResourceRequest request, ResourceResponse response, Model model) throws Exception{

		
		
		String modelNumber = request.getParameter("modelNumber");
		FRUPartListContract contract = ContractFactory.createFRUPartListContract(modelNumber);
		contract.setHardwarePartsRequest(true);
		FRUPartListResult result = new FRUPartListResult();
		try{
			
			ObjectDebugUtil.printObjectContent(contract, LOGGER);			
			
			LOGGER.debug("-------------------------- Calling Retrieve Additional Part List ----------------------");
			long timeBeforePartListCall=System.currentTimeMillis();
			result = partnerRequestsService.retrieveFRUPartList(contract);
			
			LOGGER.debug("-------------------------- After Calling Retrieve Additional Part List ----------------------");
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_FRUPARTLIST, timeBeforePartListCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		}catch(Exception e){
			throw new Exception(ExceptionUtil.getLocalizedExceptionMessage(EXCEPTION_CLAIM_CREATE_RETRIEVE_PART, request.getLocale(),e));
		}
		List<Part> partList = result.getPartList();
		
				
		model.addAttribute("partList", partList);
		
		

		response.setContentType("text/xml");
		return "/hardwareDebrief/HWInstallDeInstallMove/convertPartsToXML";
	}
	

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("retrieveSinglePart")
	public void retrieveSinglePart(ResourceRequest request,ResourceResponse response,Model model) throws Exception{
		try{
			FRUPartDetailContract contract = new FRUPartDetailContract();
			String partNumber = request.getParameter("partNumber");
			String modelNumber = request.getParameter("modelNumber");
			String orgId =  request.getParameter("organizationId");
			if(request.getParameter("replacementFlag")==null){
				contract.setReplacementFlag(false);
			}else{
				contract.setReplacementFlag(Boolean.valueOf(request.getParameter("replacementFlag")));
			}
			contract.setHardwarePartRequest(true);
			contract.setPartNumber(partNumber);
			contract.setPartnerOrg(orgId);
			contract.setModelNumber(modelNumber);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			FRUPartDetailResult result = ControllerUtil.retrieveFRUPartPRF(partnerRequestsService, contract);
			String partStr="" ;
			if(result.getPart() != null){
				Part part = formatPart(result.getPart());
				partStr += part.getPartNumber()+"///"+part.getPartName()+"///"+part.isReturnRequiredFlag()+"///"+part.getReplacementPartNumber()+"///"+part.getPartId();
			}
			ServiceStatusUtil.responseResult(response, MESSAGE_CLAIM_CREATE_RETRIEVE_PART, request.getLocale(), "\""+partStr+"\"");
		}catch(Exception e){
			LOGGER.debug(" Exception occured "+e.getMessage());
			ServiceStatusUtil.responseResult(response, EXCEPTION_CLAIM_CREATE_RETRIEVE_PART, request.getLocale());
		}
	}
	

	/**
	 * @param part  
	 * @return part 
	 * transform all " and ' for part name 
	 */
	private Part formatPart(Part part){
		if(part != null){
			part.setPartName(StringUtil.formatStringForJS(part.getPartName()));
		}
		
		return part;
	}
	

	
	/**
	 * @param hardwareDebriefFrom 
	 * @param request 
	 * @param actResponse 
	 * @param model 
	 * @throws Exception 
	 */
	@ActionMapping(params="action=saveMoveDebrief")
	public void submitMoveDebrief(@ModelAttribute("hardwareDebriefForm") HardwareDebriefForm hardwareDebriefFrom,
			ActionRequest request,
			ActionResponse actResponse,
			Model model) throws Exception{

		HardwareDebriefResult hwResult=null;
				
		try{
			checkFormBeforeProcessing(hardwareDebriefFrom,request);
			wmSaveCloseOutDebrief(hardwareDebriefFrom,model,hwResult);
			amindAttachmentCall(request,hardwareDebriefFrom.getActivity().getActivityId());
		}catch(Exception exception){
			 model.addAttribute("exceptionOccured",true);
			 
			 LOGGER.error("Exception occured during service call "+exception.getCause());
			 if(LOGGER.isDebugEnabled()){
			 LOGGER.debug(" Printing request information ");
				debugSR(hardwareDebriefFrom.getActivity().getServiceRequest());
			 LOGGER.debug(" end Printing request information ");
			 }
			 model.addAttribute("hardwareDebriefForm", hardwareDebriefFrom);
			 populateCloseOutInformation(request,hardwareDebriefFrom);
		}finally{
			
			setInformationAfterAction(hardwareDebriefFrom,actResponse,request,model);

		}
		
		
		
		
		
		
	}
	
	/**
	 * @param accountId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 * This method is for retrieving technician information 
	 */
	@ResourceMapping("setTechnicianInformation")
	public void setTechnicianInformation(@RequestParam("accountId") String accountId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		LOGGER.debug("[START] setTechnicianInformation");
		LOGGER.debug("accountId is "+ accountId);
		
		StringBuilder technicianInfo =  new StringBuilder();
		PartnerRequestsService partnerRequestsServiceMock = new PartnerRequestsServiceImpl();
		try {
			technicianInfo.append("}");
			
			if(StringUtils.isNotEmpty(accountId)) {
				List<AccountContact> accountContactList = ControllerUtil.retrieveTechnicianListSorted(accountId, partnerRequestsService);
				
				for(AccountContact accountContact : accountContactList){
					technicianInfo.append(
							accountContact.getLastName()+", "+
							accountContact.getFirstName()+"/"+
							accountContact.getContactId()+"}");
				}
			}
		
			success = true;
		} catch(Exception e){
			LOGGER.error("exception occured"+e.getMessage());
			success = false;
		}
		LOGGER.debug("setTechnicianInformation success: "+success);
		String errorCode = success?"message.gerneral.success"
				:"exception.claimCreate.retrieveTechnicianlist";
		
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+technicianInfo.toString()+"\"");
		LOGGER.debug("[END] setTechnicianInformation");
	}
	
	

	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("acceptRejectRequest")
	public void acceptRejectRequest(			
			ResourceRequest request,
			ResourceResponse response) {
		String METHOD_NAME = "acceptRequest";
;
		
		
		
		RequestAcceptContract contract = ContractFactory.getRequestAcceptContract(request);
		
		
		RequestAcceptResult result=null;
		result=amindAcceptRejectResult(contract);
		
		StringBuffer json=new StringBuffer("{");
		JsonUtil.appendToJSON("error", result.getResult()?"success":"failure", json, false);
		json.deleteCharAt(json.length()-1);
		json.append("}");
		
		
		try {
			final PrintWriter out = response.getWriter();
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setProperty("Expires", "max-age=0,no-cache,no-store");
			response.setContentType("UTF-8");
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
		
		


	}
	
	/**
	 * @param contract 
	 * @return result 
	 * Common method for AMIND call for Accept and reject request.
	 */
	private RequestAcceptResult amindAcceptRejectResult(RequestAcceptContract contract){
		
		RequestAcceptResult result = new RequestAcceptResult();
		long timeBeforeCall=System.currentTimeMillis();
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		LOGGER.info("end printing lex logger");
		try {
			result = partnerRequestsService.acceptRejectRequest(contract);
		} catch (Exception e) {
			LOGGER.error("exception occured"+e.getCause());
			result.setResult(Boolean.FALSE);
			
		}
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ACCEPTSERVICEORDERREQUEST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
		
		
		return result;
		
		
	}
	
	/**
	 * @param address 
	 * This is for Debugging address information 
	 */
	private void debugAddress(GenericAddress address){
		if(address==null){
			LOGGER.debug(" address is nulll ");
			return;
		}
		String addressArr[]={
				
				"addressId","addressName",
				"storeFrontName","addressLine1","addressLine2",
				"city","country","state","province",
				"postalCode",
				"county",
				"stateCode","stateFullName","district",
				"region","latitude","longitude",
				"countryISOCode","officeNumber","savedErrorMessage",
				"physicalLocation1","physicalLocation2","physicalLocation3"
				};
		printObject(addressArr,address);
		
	}
	/**
	 * @param contact 
	 * This is for Debugging contact information 
	 */
	private void debugContact(AccountContact contact){
		if(contact==null){
			LOGGER.debug("Contact is null");
			return;
		}
		String contactsArr[]={
				"contactId","workPhone",
				"emailAddress","firstName","lastName",
				"alternatePhone","contactType"
				};
		printObject(contactsArr,contact);
	}
	/**
	 * @param pageCounts 
	 * This is for Debugging page count  information 
	 */
	private void debugPageCount(PageCounts pageCounts){
		String pageContsArr[]={
				"type","count"
				};
		printObject(pageContsArr,pageCounts);
		
	}
	
	/**
	 * @param note 
	 * This is for Debugging notes information 
	 */
	private void debugNotes(ActivityNote note){
		String notesArray[]={
				"noteId","noteDate","noteDetails",
				"noteAuthor.contactId","noteAuthor.firstName","noteAuthor.lastName"
				};
		printObject(notesArray,note);
		
	}
	/**
	 * @param serviceRequest  
	 * This is for Debugging Service request information 
	 */
	private void debugSR(ServiceRequest serviceRequest){
		String SRArray[]={
				"serviceRequestNumber","referenceNumber","serviceRequestDate",
				"serviceRequestStatus","statusDetail","projectName","projectPhase"
				};
		printObject(SRArray,serviceRequest);
		
	}
	
	
	/**
	 * @param part 
	 */
	private void debugPart(Part part){
		String partArray[]={
				"partNumber","description",
				"orderQuantity","orderNumber","status"
				};
		printObject(partArray,part);
		
	}
	
	/**
	 * @param fields 
	 * @param o   
	 */
	private void printObject(String[] fields,Object o){
		for(String s:fields){
			try {
				LOGGER.debug(s+" = "+PropertyUtils.getProperty(o, s));
			} catch (IllegalAccessException e) {
				
				LOGGER.error("Error occured "+e.getMessage());
				
			} catch (InvocationTargetException e) {
				
				LOGGER.error("Error occured "+e.getMessage());
				
			} catch (NoSuchMethodException e) {
				
				LOGGER.error("Error occured "+e.getMessage());
				
			}
		}
	}
	
	
	

	/**
	 * @param attachmentController 
	 */
	public void setAttachmentController(AttachmentController attachmentController) {
		this.attachmentController = attachmentController;
	}

	
	/**.
	 * This utility method to print the SR. 
	 * @return String 
	 */
	// Method added for print
	@RequestMapping(params = "action=printDebrif")
	public String showDebrifPrint(){
		String METHOD_NAME = "DebrifPrint";
		
		return "/hardwareDebrief/HWInstallDeInstallMove/hwdebriefInstallDeInstallMoveViewPrint";
	}
	/**.
	 * This utility method to email the SR. 
	 * @return String 
	 */
	@RequestMapping(params = "action=emailDebrif")
	public String showDebrifEmail(){
		String METHOD_NAME = "DebrifEmail";
		
		return "/hardwareDebrief/HWInstallDeInstallMove/hwdebriefInstallDeInstallMoveViewEmail";
	}
	
	/**.
	 * This method renders the popup page to email the 
	 * change management confirmation page.
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return string  
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=emailConfirmationPage")
	public String emailConfirmationPage(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		
		return "common/emailConfirmationPage";
	}
	
	/**
	 * @param hardwareDebriefFrom 
	 * @param actResponse 
	 * @param actRequest 
	 * @param model 
	 * @throws IOException 
	 */
	private void setInformationAfterAction(HardwareDebriefForm hardwareDebriefFrom, ActionResponse actResponse,ActionRequest actRequest,Model model) throws IOException{
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("sr number "+hardwareDebriefFrom.getActivity().getServiceRequest().getServiceRequestNumber());
			LOGGER.debug("activity id"+hardwareDebriefFrom.getActivity().getActivityId());
			LOGGER.debug("srType"+hardwareDebriefFrom.getActivity().getActivityType().getValue());
		}
		if(!model.containsAttribute("exceptionOccured")){
			model.addAttribute(LexmarkPPConstants.SRTYPE,hardwareDebriefFrom.getActivity().getActivityType().getValue());
			model.addAttribute(LexmarkPPConstants.SRNUMBER, hardwareDebriefFrom.getActivity().getServiceRequest().getServiceRequestNumber());
			model.addAttribute(LexmarkPPConstants.ACTIVITYID, hardwareDebriefFrom.getActivity().getActivityId());
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET,hardwareDebriefFrom.getTimezoneOffset());
			if(hardwareDebriefFrom.isSaveCloseoutFlag()){
				actResponse.sendRedirect("/group/partner-portal/services-requests?back=hw");
			}else{
				actResponse.setRenderParameter("action", "redirectToView");
			}
						
		}else{
			actResponse.setRenderParameter(LexmarkPPConstants.SRTYPE,hardwareDebriefFrom.getActivity().getActivityType().getValue());
			actResponse.setRenderParameter(LexmarkPPConstants.ACTIVITYID, hardwareDebriefFrom.getActivity().getActivityId());
		}
		
		
	}
	/**
	 * @param request  
	 * @param response  
	 * @param model  
	 * @return string 
	 */
	@RequestMapping(params="action=redirectToView")
	private String redirectToVeiwPage(RenderRequest request,RenderResponse response,Model model){
		
		return"/hardwareDebrief/HWInstallDeInstallMove/redirectView";
		
	}
	
	/**
	 * @param hardwareDebriefForm 
	 * @param model 
	 * @param hwResult 
	 * @throws Exception 
	 * This is common method to call WEb methods webservice 
	 */
	private void wmSaveCloseOutDebrief(HardwareDebriefForm hardwareDebriefForm,Model model, HardwareDebriefResult hwResult)throws Exception{
		
		HardwareDebriefContract hwContract=new HardwareDebriefContract();
		hwContract.setActivity(hardwareDebriefForm.getActivity());
		hwContract.setUserEnteredActivity(hardwareDebriefForm.getUserEnteredActivity());
		hwContract.setTimeZoneOffset(hardwareDebriefForm.getTimezoneOffset());
		//String debriefType = LexmarkPPConstants.DEBRIEF_TYPE_SAVE;
		if(hardwareDebriefForm.isSaveCloseoutFlag()){
			hwContract.setDebriefStatus(LexmarkPPConstants.CLOSEOUT_STATUS);
			//debriefType = LexmarkPPConstants.DEBRIEF_TYPE_CLOSEOUT;
		}	
		
		LOGGER.debug("-------------------------- Calling Partner Debrief Service123 ----------------------");
	
	
		hwContract.setSrType(hardwareDebriefForm.getSrType());
	
		
	
	
		long timeBeforeDebriefCall=System.currentTimeMillis();
		hwResult=hardwareDebriefService.saveDebriefRquest(hwContract);
		
		LOGGER.debug("-------------------------- After Calling Debrief service ----------------------"+hwResult.getReturnResult());
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_SAVEDEBRIEF, timeBeforeDebriefCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,hwContract);
		model.addAttribute("savedSuccess",true);
	}
	
	
	
	
	
	/**
	 * @param activity 
	 * This method is to debug the form information 
	 */
	private void debugForm(Activity activity){
		LOGGER.debug(" estimated arrival time ="+activity.getEstimatedArrivalTime());
		LOGGER.debug(" actual Start date ="+activity.getDebrief().getServiceStartDate());
		LOGGER.debug(" actual End date="+activity.getDebrief().getServiceEndDate());
		LOGGER.debug(" estimated End date ="+activity.getServiceRequest().getResolveWithin());
		LOGGER.debug(" Printing request information ");
		debugSR(activity.getServiceRequest());
		LOGGER.debug(" end Printing request information \n ----printing the Page counts values -----");
		List<PageCounts> pageCounts=activity.getServiceRequest().getAsset().getPageCounts();
		int i=0;
		if(pageCounts!=null){
			for(PageCounts pageCount:pageCounts){
				
				LOGGER.debug(" printing "+i+"pageCount ");
				debugPageCount(pageCount);
				i++;
			}
		}
		LOGGER.debug("----printing the Page counts values  Ends-----\n----printing the contact information for this device -----");
		i=0;
		List<AccountContact> contacts=activity.getServiceRequest().getContactInfoForDevice();
		if(contacts != null){
			for(AccountContact contact:contacts){
				
				LOGGER.debug(" printing "+i+"contact ");
				debugContact(contact);
				LOGGER.debug(" printing "+i+"contact address");
				debugAddress(contact.getAddress());
				i++;
			}
		}
		
		LOGGER.debug("----printing the contact information ENDS -----\n----printing the Device Installed ADderss-----");
		if(activity.getServiceRequest().getAsset().getInstallAddress()!=null){
			debugAddress(activity.getServiceRequest().getAsset().getInstallAddress());
		}
		
		LOGGER.debug("----printing the Device Installed ADderss ENDS -----\n----printing the Device Installed ADderss-----");
		if(activity.getServiceRequest().getAsset().getMoveToAddress()!=null){
			debugAddress(activity.getServiceRequest().getAsset().getMoveToAddress());
		}
		
		LOGGER.debug("----printing the Device Installed ADderss ENDS -----\n----printing the NOTES-----");
		if(activity.getActivityNoteList()!=null){
			for(ActivityNote an:activity.getActivityNoteList()){
				debugNotes(an);
			}
		}
		
		LOGGER.debug("----END printing the NOTES-----\n----printing the Recomended Part List-----");
		if(activity.getServiceRequest().getAsset().getPartList()!=null){
			for(Part part:activity.getServiceRequest().getAsset().getPartList()){
				debugPart(part);
			}
		}
		
		LOGGER.debug("----END printing the Recomended Part List-----");
	}


	
	/**
	 * @param request 
	 * @param activityNumber 
	 */
	private void amindAttachmentCall(PortletRequest request,String activityNumber) {
		try{
			
		
		
		PortletSession session=request.getPortletSession();
		Map<?, ?> fileMap = (Map<?, ?>) session.getAttribute(LexmarkPPConstants.SESSION_FILE_MAP_ForHWDebrief_CloseOut);
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		if (fileMap != null) {

			
			Set<?> fileNames = (Set<?>)fileMap.keySet();
			Attachment file=null;
			String userType = PortalSessionUtil.getUserType(session);
			LOGGER.debug("usertype is "+userType);
			for(Object filename:fileNames){
				FileObject fileDetails = (FileObject)fileMap.get((String)filename);
				file=new Attachment();
				file.setVisibility(userType);
				file.setAttachmentName(fileDetails.getFileName());
				file.setSize(Integer.parseInt(fileDetails
						.getFileSizeInBytes()));
				LOGGER.debug("File name ::: "
						+ fileDetails.getFileName());
				
				attachmentList.add(file);
			}
		}
		else{
			return;
		}
		AttachmentContract atchmntContract = new AttachmentContract();
		CrmSessionHandle crmSessionHandle=null;
		crmSessionHandle = globalService
		.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		atchmntContract.setSessionHandle(crmSessionHandle);
		atchmntContract.setIdentifier(activityNumber);
		
		atchmntContract.setRequestType(LexmarkPPConstants.HARDWARE_DEBRIEF);
		atchmntContract.setAttachments(attachmentList);
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(atchmntContract,LOGGER);
		LOGGER.info("end printing lex loggger");
		LOGGER.debug("-------------------------- Calling attachment service ----------------------");
		long timeBeforeAttachmentCall=System.currentTimeMillis();
		attachmentService.uploadAttachments(atchmntContract);
		
		LOGGER.debug("-------------------------- After Calling attachment service ----------------------");
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeAttachmentCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,atchmntContract);
		}catch(Exception e){
			LOGGER.debug(" exception occured while attaching so Ignored "+e.getCause());
		}
	}
	
	

	/**
	 * @param form 
	 */
	private void changePartList(HardwareDebriefForm form){

			try{
		        List<Part> newPartList=new ArrayList<Part>();
		        List<Part> partlist=form.getUserEnteredActivity().getServiceRequest().getAsset().getPartList();
		        if(partlist==null){
		        	return;
		        }
		        for(Part part:partlist){
		            String orderQuantity=part.getOrderQuantity();
		            int intOrderQuantity=0;
		            if(orderQuantity!=null){
		                try{
		                intOrderQuantity=Integer.valueOf(orderQuantity);
		                }catch(NumberFormatException ex){
		                    LOGGER.debug(" cannot parse order Quantity" +ex.getCause());
		                }
		            }
		            int usedQty=part.getUsedQuantity()==null?0:part.getUsedQuantity();
		            LOGGER.debug("Used Qty = " + usedQty);
		            int notUsedQty=part.getNotUsedQuantity()==null?0:part.getNotUsedQuantity();
		            LOGGER.debug("Not Used Qty = " + notUsedQty);
		            int doaQty=part.getDoaQuantity()==null?0:part.getDoaQuantity();
		            LOGGER.debug("DOA = " + doaQty);
		            
		            LOGGER.debug("used qty="+usedQty+"notUsedQty="+notUsedQty+"DOA="+doaQty);
		          
		           	
		            setStatusToPart(usedQty,part,newPartList,"Used");
		            setStatusToPart(notUsedQty,part,newPartList,"Not Used");
		            setStatusToPart(doaQty,part,newPartList,"Defective On Arrival");
		            
		            if(intOrderQuantity<(usedQty+notUsedQty+doaQty)){
		                LOGGER.debug(" the quantity doesnot match removing extra from the list");
		                newPartList.subList(0, intOrderQuantity);
		            }else{
		            	setStatusToPart(intOrderQuantity-(usedQty+notUsedQty+doaQty),part,newPartList,"");
		            }
		        }
		        
		        form.getUserEnteredActivity().getServiceRequest().setParts(newPartList);
			}catch(Exception e){
				LOGGER.error("error occured in change part list"+e.getCause());
			}
	    }
	    
	    /**
	     * @param toIndex 
	     * @param source 
	     * @param totalPart 
	     * @param status 
	     */
	    private void setStatusToPart(int toIndex,Part source,List<Part> totalPart,String status){
	        int i=0;
	        for(i=0;i<toIndex;i++){
	            Part tempPart=new Part();
	            
	                tempPart.setDescription(source.getDescription());
	                LOGGER.debug("Desc =" + tempPart.getDescription());
	                tempPart.setOrderQuantity("1");
	                LOGGER.debug("Quantity =" + tempPart.getOrderQuantity());
	                tempPart.setPartNumber(source.getPartNumber());
	                LOGGER.debug("Part No. =" + tempPart.getPartNumber());
	                tempPart.setPartName(source.getPartName());
	                LOGGER.debug("Part Name =" + tempPart.getPartName());
	                tempPart.setStatus(status);
	                LOGGER.debug("Status =" + tempPart.getStatus());
	                tempPart.setTypePrinter(source.isTypePrinter());
					LOGGER.debug("Type Printer =" + source.isTypePrinter());
	            
	            totalPart.add(tempPart);
	        }
	    }
	    
	    
	    /**
	     * @param form 
	     */
	    private void changeAdditionalPartList(HardwareDebriefForm form){

				try{
			        List<PartLineItem> newPartList=new ArrayList<PartLineItem>();
			        List<PartLineItem> partlist=form.getUserEnteredActivity().getAdditionalPartList();
			        if(partlist==null){
			        	return;
			        }
			        for(PartLineItem part:partlist){
			        	LOGGER.debug("Additional Part List length "+partlist.size());
			        	LOGGER.debug("Additional Part "+part.getPartNumber());
			        	
			            String orderQuantity=part.getOrderQuantity();
			            /*int intOrderQuantity=0;
			            if(orderQuantity!=null){
			                try{
			                intOrderQuantity=Integer.valueOf(orderQuantity);
			                }catch(NumberFormatException ex){
			                    LOGGER.debug(" cannot parse order Quantity" +ex.getCause());
			                }
			            }*/
			            int usedQty=part.getUsedQuantity()==null?0:part.getUsedQuantity();
			            LOGGER.debug("Used Qty = " + usedQty);
			            int notUsedQty=part.getNotUsedQuantity()==null?0:part.getNotUsedQuantity();
			            LOGGER.debug("Not Used Qty = " + notUsedQty);
			            int doaQty=part.getDoaQuantity()==null?0:part.getDoaQuantity();
			            LOGGER.debug("DOA = " + doaQty);
			            
			            LOGGER.debug("used qty="+usedQty+"notUsedQty="+notUsedQty+"DOA="+doaQty);
			          
			            
			            if(usedQty!=0){
			            	PartLineItem newPart = new PartLineItem();
				            newPart.setPartNumber(part.getPartNumber());
				            LOGGER.debug("UsedPartNo. = " + newPart.getPartNumber());
				            newPart.setDescription(part.getDescription());
				            LOGGER.debug("UsedDesc = " + newPart.getDescription());
				            newPart.setOrderQuantity(part.getOrderQuantity());
				            LOGGER.debug("UsedOrderQty1 = " + newPart.getOrderQuantity());
			            	newPart.setStatus("Used");
			            	LOGGER.debug("UsedStatus = " + newPart.getStatus());
			            	newPart.setOrderQuantity(String.valueOf(usedQty));
			            	LOGGER.debug("UsedOrderQty2 = " + newPart.getOrderQuantity());
			            	newPartList.add(newPart);
			            }
			            if(notUsedQty!=0){
			            	PartLineItem newPart = new PartLineItem();
				            newPart.setPartNumber(part.getPartNumber());
				            LOGGER.debug("NotUsedPartNo. = " + newPart.getPartNumber());
				            newPart.setDescription(part.getDescription());
				            LOGGER.debug("NotUsedDesc = " + newPart.getDescription());
				            newPart.setOrderQuantity(part.getOrderQuantity());
				            LOGGER.debug("NotUsedOrderQty1 = " + newPart.getOrderQuantity());
			            	newPart.setStatus("Not Used");
			            	LOGGER.debug("NotUsedStatus = " + newPart.getStatus());
			            	newPart.setOrderQuantity(String.valueOf(notUsedQty));
			            	LOGGER.debug("NotUsedOrderQty2 = " + newPart.getOrderQuantity());
			            	newPartList.add(newPart);
			            }
			            if(doaQty!=0){
			            	PartLineItem newPart = new PartLineItem();
				            newPart.setPartNumber(part.getPartNumber());
				            LOGGER.debug("DOAPartNo. = " + newPart.getPartNumber());
				            newPart.setDescription(part.getDescription());
				            LOGGER.debug("DOADesc = " + newPart.getDescription());
				            newPart.setOrderQuantity(part.getOrderQuantity());
				            LOGGER.debug("DOAOrderQty1 = " + newPart.getOrderQuantity());
			            	newPart.setStatus("Defective On Arrival");
			            	LOGGER.debug("DOAStatus = " + newPart.getStatus());
			            	newPart.setOrderQuantity(String.valueOf(doaQty));
			            	LOGGER.debug("DOAOrderQty2 = " + newPart.getOrderQuantity());
			            	newPartList.add(newPart);
			            }
			            
			        }
			        
			        form.getUserEnteredActivity().getAdditionalPartList().clear();
			        form.getUserEnteredActivity().setAdditionalPartList(newPartList);
				}catch(Exception e){
					LOGGER.error("error occured in change part list"+e.getCause());
				}
		    }
		    
		    
	    
	   
	    /**
	     * @param form 
	     * @param request 
	     * @throws Exception 
	     */
	    private void checkFormBeforeProcessing(HardwareDebriefForm form,PortletRequest request)throws Exception{

	    	
	    	if(form.getActivity()==null){
				throw new Exception("Activity is null");
			}
			Long sessionKey=(Long)request.getPortletSession().getAttribute(LexmarkConstants.SUBMIT_TOKEN,PortletSession.PORTLET_SCOPE);
			if(StringUtils.isBlank(form.getSubmitToken().toString())||!sessionKey.equals(form.getSubmitToken())){
				throw new Exception("duplicate submit detected");
			}
	    	
	   

	   	 }  
	    
	   
	   /**
	 * @param activity 
	 * @param hwDebriefRequestType 
	 */
	private void changeRequestType(Activity activity,Map<String,String>hwDebriefRequestType){
		   String requestType=activity.getActivityType().getValue();
			
			if(StringUtils.isNotBlank(requestType)){
				String localizedValue=null;
				 if(requestType.toUpperCase().contains("DECOMMISSION")){
					 if(requestType.toUpperCase().contains("INSTALL/DECOMMISSION"))
						 localizedValue	 = hwDebriefRequestType.get(LexmarkPPConstants.INSTALLDEINSTALL);
					 else
					localizedValue	 = hwDebriefRequestType.get(LexmarkPPConstants.DECOMMISSION);
				}else if(requestType.toUpperCase().contains("MOVE")){
					localizedValue	 = hwDebriefRequestType.get(LexmarkPPConstants.HW_MOVE);
				}else if(requestType.toUpperCase().contains("CHANGE")){
					localizedValue	 = hwDebriefRequestType.get(LexmarkPPConstants.CHANGE);							
				}else if(requestType.toUpperCase().contains("INSTALL")){
					localizedValue	 = hwDebriefRequestType.get(LexmarkPPConstants.INSTALLATION);							
				}
				 
				if (localizedValue == null) {
					localizedValue = requestType;
				}
				
				activity.getActivityType().setName(localizedValue);
				
				
			}
	   }
	
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @param fileName 
	 * @param activityId 
	 */
	@ResourceMapping("downloadHardwareAttachment")
	public void downloadAttachment(ResourceRequest resourceRequest,ResourceResponse resourceResponse,
			@RequestParam("fName") String fileName,@RequestParam("activityId") String activityId){

		final String METHOD_NAME="downloadAttachment";
		
		OutputStream out=null;
		InputStream inputStream=null;
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(resourceRequest));
		
		AttachmentContract contract = new AttachmentContract();
				
		contract.setRequestType(LexmarkPPConstants.HARDWARE_DEBRIEF);
		contract.setIdentifier(activityId);
		contract.setUserFileName(fileName);
		
		contract.setSessionHandle(crmSessionHandle);
		
		
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract,LOGGER);
		LOGGER.info("end printing lex loggger");
		long timeBeforeCall=System.currentTimeMillis();
		
		try{
		/*Mock Call
		 * */	
		//AttachmentResult downldReslt=new AttachmentResult();
		/*
		 * Real call
		 * */
		AttachmentResult downldReslt = attachmentService.downloadAttachment(contract);
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_DOWNLOADATTACHMENT, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("start printing lex logger");
			
			LOGGER.info("end printing lex loggger");
		}
		resourceResponse.setProperty("Content-disposition", "attachment; filename=\""
				+ fileName+"\"");
		LOGGER.debug("downldReslt.getFileStream() : " + downldReslt.getFileStream()
		+ " downldReslt.getFileName() : " + downldReslt.getFileName());
		if(downldReslt.getFileStream() == null){
			LOGGER.error("Error occured while getting the inputstream ");
			throw new Exception("Error occured while getting the inputstream");
		}
		
		resourceResponse.setContentType(ControllerUtil.getContentTypeAccordingToFile(fileName));
		
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
			resourceResponse.setContentType("text/csv");
			
			LOGGER.error(exception.getMessage());
						
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
			
			
			
			
		}
		
				
		

	
	}
	
	
	//For LBS addresses
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getAllLocation")
	public void getCountryStateCityBuildingSite(ResourceRequest request,ResourceResponse response) {
		AddressListResult result=getSiteBuildingFloorZone(request);
		StringBuffer sb=new StringBuffer("{\"country\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(convertToOptions(result.getLbsAddressList(),LexmarkConstants.COUNTRY)));
		sb.append("\",\"state\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(generateRegions(result.getLbsAddressList())));
		sb.append("\",\"city\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(convertToOptions(result.getLbsAddressList(),LexmarkConstants.CITY)));
		sb.append("\",\"site\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(convertSiteToOptions(result.getLbsLocationSiteList())));
		sb.append("\",\"building\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(convertBuildingToOptions(result.getLbsLocationBuildingList())));
		sb.append("\",\"zone\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(convertZoneToOptions(result.getLbsLocationZoneList())));
		sb.append("\",\"floor\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(convertFloorToOptions(result.getLbsLocationFloorList())));
		sb.append("\"}");
		writeResonse(response,sb.toString());
	}
	
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getFloor")
	public void getFloor(ResourceRequest request,ResourceResponse response) {
		
		AddressListResult result=getSiteBuildingFloorZone(request);
		writeResonse(response,convertFloorToOptions(result.getLbsLocationFloorList()));
		/*LOGGER.debug("site"+HTMLOutputGenerator.convertSiteToOptions(result.getLbsLocationSiteList()));
		StringBuffer sb=new StringBuffer("{\"site\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertSiteToOptions(result.getLbsLocationSiteList())));
		sb.append("\",\"floor\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertFloorToOptions(result.getLbsLocationFloorList())));
		LOGGER.debug("floor"+HTMLOutputGenerator.convertFloorToOptions(result.getLbsLocationFloorList()));
		//sb.append("\",\"zone\":\"");
		//sb.append(StringEscapeUtils.escapeJavaScript(HTMLOutputGenerator.convertZoneToOptions(result.getLbsLocationZoneList())));
		sb.append("\"}");
		writeResonse(response,sb.toString());*/
		
		
	}
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getSite")
	public void getSiteList(ResourceRequest request,ResourceResponse response) {
		
		AddressListResult result=getSiteBuildingFloorZone(request);
		writeResonse(response,convertSiteToOptions(result.getLbsLocationSiteList()));
	}
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getZone")
	public void getZone(ResourceRequest request,ResourceResponse response) {
		AddressListResult result=getSiteBuildingFloorZone(request);
		writeResonse(response,convertZoneToOptions(result.getLbsLocationZoneList()));		
	}
	
	/**
	 * @param request 
	 * @return AddressListResult  
	 */
	private AddressListResult getSiteBuildingFloorZone(PortletRequest request){
		AddressListContract contract=ContractFactory.getLBSAddressContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));

		contract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		AddressListResult result=null;
		try{
			result=lbsLocationService.retrieveLBSLocationList(contract);
			LOGGER.debug("res"+result.getLbsLocationSiteList());
		}catch(Exception exception){
			LOGGER.debug("Exception"+exception.getMessage()); 
			result=new AddressListResult();
			LOGGER.debug("[ Exception occured while retrieving locations ]");
		}/*finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}*/
		return result;
		
	}
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getSiteBuildingZone")
	public void getSite(ResourceRequest request,ResourceResponse response) {
		
		
		AddressListResult result=getSiteBuildingFloorZone(request);
		StringBuffer sb=new StringBuffer("{\"site\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(convertSiteToOptions(result.getLbsLocationSiteList())));
		sb.append("\",\"building\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(convertBuildingToOptions(result.getLbsLocationBuildingList())));
		sb.append("\",\"zone\":\"");
		sb.append(StringEscapeUtils.escapeJavaScript(convertZoneToOptions(result.getLbsLocationZoneList())));
		sb.append("\"}");
		writeResonse(response,sb.toString());
	}
	
	/**
	 * @param address 
	 * @param type 
	 * @return String 
	 */
	public static String convertToOptions(List<LBSAddress> address,String type){
			
			StringBuffer html = new StringBuffer();
			if(address==null){
				return html.toString();
			}
			for(LBSAddress location:address){
				
				if(LexmarkConstants.COUNTRY.equalsIgnoreCase(type)){
					html.append("<option value=\""+location.getCountry()+"\">"+StringEscapeUtils.escapeHtml(location.getCountry())+"</option>\n");
				}/*else if(LexmarkConstants.STATE.equalsIgnoreCase(type)){
					html.append("<option value=\""+(location.getStateId()==null?location.getState():location.getStateId())+"\">"+StringEscapeUtils.escapeHtml(location.getState())+"</option>\n");
				}*/else if (LexmarkConstants.CITY.equalsIgnoreCase(type) && StringUtils.isNotBlank(location.getCity())){				
					html.append("<option value=\""+location.getCity()+"\">"+StringEscapeUtils.escapeHtml(location.getCity())+"</option>\n");
				}
			}
			return html.toString();
			
		}
		
	/**
	 * @param address 
	 * @return String 
	 */
	public static String convertBuildingToOptions(List<LBSLocationBuilding> address){
		
		StringBuffer html = new StringBuffer();
		if(address==null){
			return html.toString();
		}
		for(LBSLocationBuilding location:address){
			html.append("<option value=\""+(location.getBuildingId()==null?location.getBuilding():location.getBuildingId())+"\">"+StringEscapeUtils.escapeXml(location.getBuilding())+"</option>\n");			
		}
		return html.toString();
		
	}
	/**
	 * @param address 
	 * @return String 
	 */
	public static String convertFloorToOptions(List<LBSLocationFloor> address){
		
		StringBuffer html = new StringBuffer();
		if(address==null){
			return html.toString();
		}
		for(LBSLocationFloor location:address){
			html.append("<option value=\""+(location.getFloorId()==null?location.getFloor():location.getFloorId())+"\" lod=\""+location.getFloorLevelOfDetails()+"\">"+StringEscapeUtils.escapeXml(location.getFloor())+"</option>\n");			
		}
		return html.toString();
		
	}
	/**
	 * @param address 
	 * @return String 
	 */
	public static String convertZoneToOptions(List<LBSLocationZone> address){
	
		StringBuffer html = new StringBuffer();
		if(address==null){
			return html.toString();
		}
		for(LBSLocationZone location:address){
			html.append("<option value=\""+(location.getZoneId()==null?location.getZone():location.getZoneId())+"\">"+StringEscapeUtils.escapeXml(location.getZone())+"</option>\n");			
		}
		return html.toString();
	
	}
	/**
	 * @param address 
	 * @return String 
	 */
	public static String convertSiteToOptions(List<LBSLocationSite> address){
	
		StringBuffer html = new StringBuffer();
		if(address==null){
			return html.toString();
		}
		for(LBSLocationSite location:address){
			html.append("<option value=\""+(location.getSiteId()==null?location.getSite():location.getSiteId())+"\">"+StringEscapeUtils.escapeXml(location.getSite())+"</option>\n");			
		}
		return html.toString();
	
	}
	
	/**
	 * @param response 
	 * @param val 
	 */
	private void writeResonse(ResourceResponse response,String val){
		try {
			final PrintWriter out = response.getWriter();
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setProperty("Expires", "max-age=0,no-cache,no-store");
			response.setContentType("text/html");
			out.write(val);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
	}
	}
	
	
	/**
	 * This method is to check whether 
	 * state , or county or province or district is 
	 * present in list. On that basis the state/region will be generated.
	 * */
	/**
	 * @param address 
	 * @return String 
	 */
	public static String generateRegions(List<LBSAddress> address){
		StringBuffer regionHtml=new StringBuffer();
		for(LBSAddress location:address){
			
			
			
			if(StringUtils.isNotBlank(location.getState())){
				regionHtml.append("<option value=\""+(location.getStateId()==null?location.getState():location.getStateId())+"^s\">"+StringEscapeUtils.escapeHtml(location.getState())+"</option>\n");
			}
			if(StringUtils.isNotBlank(location.getCounty())){
				regionHtml.append("<option value=\""+location.getCounty()+"^c\">"+StringEscapeUtils.escapeHtml(location.getCounty())+"</option>\n");
			}if(StringUtils.isNotBlank(location.getProvince())){
				regionHtml.append("<option value=\""+location.getProvince()+"^p\">"+StringEscapeUtils.escapeHtml(location.getProvince())+"</option>\n");
			}if(StringUtils.isNotBlank(location.getDistrict())){
				regionHtml.append("<option value=\""+location.getDistrict()+"^d\">"+StringEscapeUtils.escapeHtml(location.getDistrict())+"</option>\n");
			}
		}
		
		
		return regionHtml.toString();
	}

	/**
	 * @param json 
	 * @param response 
	 */
	@ResourceMapping("encryptJSON")
	public void encryptJSON(@RequestParam("jsonString") String json,ResourceResponse response){
		LOGGER.debug("[in encryptJSON]");
		
		
		String encryptedJSON=null;
		try {
			encryptedJSON = JSONEncryptUtil.encrypt(json);
		} catch (Exception e) {
			LOGGER.error("Exception occured in encryption"+e.getMessage());
		} 
		LOGGER.debug(String.format("encrypted string is =[%s]", encryptedJSON));
		
		
		try {
			final PrintWriter out = response.getWriter();
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setProperty("Expires", "max-age=0,no-cache,no-store");
			response.setContentType("text/html");
			out.write(encryptedJSON);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
		
				
		LOGGER.debug("[out encryptJSON]");
	}
	
	private void setModelParams(HardwareDebriefForm form,PortletSession session){
		MapForm mapForm=new MapForm();
		mapForm.setMdmId(PortalSessionUtil.getMdmId(session));
		mapForm.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
		mapForm.setFormPostUrl(getLbsEndpointURL());
		mapForm.setEmailaddress(PortalSessionUtil.getEmailAddress(session));
		form.setMapForm(mapForm);
				
	}

	/**
	 * @param lbsEndpointURL the lbsEndpointURL to set
	 */
	public void setLbsEndpointURL(String lbsEndpointURL) {
		this.lbsEndpointURL = lbsEndpointURL;
	}
	
	
	
	/**
	 * @return the lbsEndpointURL
	 */
	public String getLbsEndpointURL() {
		return lbsEndpointURL;
	}
}
