/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: RequestController
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
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.ActivityListContract;
import com.lexmark.contract.AssignedTechnicianUpdateContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.ClaimDetailContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.PartnerNotificationsContract;
import com.lexmark.contract.ServiceActivityHistoryDetailContract;
import com.lexmark.contract.ServiceActivityHistoryListContract;
import com.lexmark.contract.SiebelLOVListContract;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.DownloadClaim;
import com.lexmark.domain.DownloadRequest;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.PartnerTypeEnum;
import com.lexmark.exporter.DataExporter;
import com.lexmark.exporter.DataExporterFactory;
import com.lexmark.exporter.PdfDataExporter;
import com.lexmark.form.ClaimDetailForm;
import com.lexmark.form.RequestListForm;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.ActivityListResult;
import com.lexmark.result.AssignedTechnicianUpdateResult;
import com.lexmark.result.AttachmentResult;
import com.lexmark.result.ClaimDetailResult;
import com.lexmark.result.DownloadClaimListResult;
import com.lexmark.result.DownloadRequestListResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.PartnerNotificationsResult;
import com.lexmark.result.ServiceActivityHistoryListResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.real.enums.QueryType;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.ExceptionUtil;
import com.lexmark.util.GridXmlBuilder;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.ServiceStatusUtil;
import com.lexmark.util.XMLEncodeUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XmlOutputGenerator;
import com.lexmark.webservice.api.AddressCleansingService;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("VIEW")
public class RequestController extends BaseController {

	
	private static final ArrayList<Activity> EMPTY_LIST = new ArrayList<Activity>();
	@Autowired
	private PartnerRequestsService partnerRequestsService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private GridSettingController gridSettingController;
	
	@Autowired
    private AttachmentService attachmentService;
	//Added for CI BRD13-10-01

	ClaimRequestCreateController claimRequestCreateController = new ClaimRequestCreateController();
	
	
	
	private static LEXLogger logger = LEXLogger.getLEXLogger(RequestController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static final String EXCEPTION_UPDATE_ASSIGNED_TECHINICIAN = "exception.requestList.updateAssignedTechnician";

	private static final String SUCCESS_UPDATE_ASSIGNED_TECHINICIAN = "message.requestList.updateAssignedTechnician";
	
	private static final String LB_ADDRCLEANSING_MSG = "addressCleansing.error.friendly.message";
	
	
	// Getting the File Path from attachment properties file
	static ResourceBundle bundle = ResourceBundle.getBundle("attachment");

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showRequestList(RenderRequest request, RenderResponse response, Model model) throws Exception {
		//Added for CI BRD13-10-01 --STARTS
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String requestTyepeis = ((httpReq.getParameter("requestTyepeis")== null)?"notDraft":httpReq.getParameter("requestTyepeis"));
		//Added for CI BRD13-10-01 --ENDS
		RequestListForm requestListForm = new RequestListForm();
		final PortletSession session = request.getPortletSession();

		Boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(session);
		Boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(session);
		Boolean isCreateClaim = PortalSessionUtil.getCreateClaimFlag(session);
		List<String> userRoles = PortalSessionUtil.getUserRoles(session);
		Boolean isUploadClaim = PortalSessionUtil.getUploadClaimFlag(session);
		Boolean isUploadRequest = PortalSessionUtil.getUploadRequestFlag(session);
		
		if (userRoles == null) {
			throw new IllegalArgumentException("Failed to open request list page: <br/> userRoles is null");
		}

		boolean isManager = containsIgnoreCase(userRoles, LexmarkConstants.ROLE_SERVICE_MANAGER);
		boolean isAdministration = containsIgnoreCase(userRoles, LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR);
		boolean isTechnician = containsIgnoreCase(userRoles, LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
		boolean isFSE = PortalSessionUtil.getLexmarkFSEFlag(session);
		boolean isAnalyst = containsIgnoreCase(userRoles, LexmarkConstants.ROLE_ANALYST);	
		
		/*Added for mps 2.1 hardware debrief*/
		Boolean isHardwareDebrief=false;
		Map<String, String> ldapUserData = (Map<String, String>) session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE);
		logger.debug("ldapUserData.get(LexmarkConstants.USERSEGMENT) "+ldapUserData.get(LexmarkConstants.USERSEGMENT));
		if(PortalSessionUtil.getHardwareDebriefFlag(session) && (isTechnician || isManager || ldapUserData.get(LexmarkConstants.USERSEGMENT).equalsIgnoreCase("EMPLOYEE"))){			
			isHardwareDebrief=true;
		}
		requestListForm.setHardwareDebriefFlag(isHardwareDebrief);
		//Ends changes Hardware debrief
		
		
		if (isIndirectPartner && isCreateClaim) {
			if (isFSE){
				//FSE cannot create claim
				requestListForm.setCreateClaimFlag(false);				
			}else{
				requestListForm.setCreateClaimFlag(isManager || isAdministration);				
			}
		}
		
		if (isUploadClaim && isIndirectPartner){
			requestListForm.setUploadClaimFlag(isAnalyst || isManager || isAdministration);
		}
		
		if(isUploadRequest && isDirectPartner){
			requestListForm.setUploadRequestFlag(isAnalyst|| isManager|| isAdministration);
		}
		
		if (isIndirectPartner && isDirectPartner && isUploadClaim && isUploadRequest){
			requestListForm.setUploadClaimFlag(isAnalyst || isManager || isAdministration);
			requestListForm.setUploadRequestFlag(isAnalyst|| isManager|| isAdministration);
		}
		

		if (isDirectPartner) {
			requestListForm.setDisplayUnassignedReqeustsFlag(isTechnician || isManager || isAdministration);
		}

		requestListForm.setDisplayRequestTypeFilterFlag(isDirectPartner || isIndirectPartner);

		Locale locale = request.getLocale();
		String partnerType = getPartnerType(isIndirectPartner, isDirectPartner);
		
		Map<String, String> allowChildSRMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ALLOW_CHILD_SR.getValue(), null,
				serviceRequestLocaleService, locale);
		requestListForm.setAllowChildSR(allowChildSRMap);
		
		Map<String, String> requestTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_TYPE.getValue(), null,
				serviceRequestLocaleService, locale);
		
		requestListForm.setRequestTypeMap(requestTypeMap);

		Map<String, String> requestStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		requestListForm.setRequestStatusMap(requestStatusMap);

		Map<String, String> serviceTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue(), null, serviceRequestLocaleService,
				locale);
		requestListForm.setServiceTypeMap(serviceTypeMap);

		Map<String, String> requestStatusDetailMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		requestListForm.setRequestStatusDetailMap(requestStatusDetailMap);
		
		Map<String, String> debriefRequestStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ACTIVITY_SUB_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		requestListForm.setDebriefRequestStatusMap(debriefRequestStatusMap);
		// request type LOV
		Map<String, String> requestListLOV = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_REQUEST_LIST.getValue(), null,
				serviceRequestLocaleService, locale);
		model.addAttribute("requestListLOV", requestListLOV);
		
		
		
		if (isDirectPartner && isIndirectPartner) {
			Map<String, String> claimRequestStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), PartnerTypeEnum.INDIRECT
							.getValue(), serviceRequestLocaleService, locale);
			requestListForm.setClaimRequestStatusMap(claimRequestStatusMap);

			Map<String, String> serviceRequestStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), PartnerTypeEnum.DIRECT
							.getValue(), serviceRequestLocaleService, locale);
			requestListForm.setServiceRequestStatusMap(serviceRequestStatusMap);

			Map<String, String> claimRequestStatusDetailMap = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue(), PartnerTypeEnum.INDIRECT
							.getValue(), serviceRequestLocaleService, locale);
			requestListForm.setClaimRequestStatusDetailMap(claimRequestStatusDetailMap);

			Map<String, String> serviceRequestStatusDetailMap = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue(), PartnerTypeEnum.DIRECT
							.getValue(), serviceRequestLocaleService, locale);
			requestListForm.setServiceRequestStatusDetailMap(serviceRequestStatusDetailMap);
		}
		
		model.addAttribute("requestListForm", requestListForm);
		model.addAttribute("claimUpdateFlag", request.getParameter("claimUpdateFlag"));
		model.addAttribute("closeOutFlag", request.getParameter("closeOutFlag"));
		model.addAttribute("closeOutRequestFlag", request.getParameter("closeOutRequestFlag"));
		//Added for BRD13-10-01
		model.addAttribute("requestTyepeis", requestTyepeis);
		/*Changes MPS 2.1
		 * Commented as diff param will be send from hardware
		 * && "true".equalsIgnoreCase(request.getParameter("back")*/
		String gridId="gridRLVRequestList";
		if(StringUtils.isNotBlank(httpReq.getParameter("back"))){
			model.addAttribute("back", httpReq.getParameter("back"));
			httpReq.removeAttribute("back");
			//This is for hardware
			if("hw".equalsIgnoreCase(httpReq.getParameter("back"))){
				gridId="hardwareRequestList";
			}
		}else{
			if(requestListForm.isHardwareDebriefFlag() && !requestListForm.isDisplayRequestTypeFilterFlag()){
				gridId="hardwareRequestList";
			}
		}
		gridSettingController.retrieveGridSetting(gridId, request, model);
		/* Ends Changes MPS 2.1*/
		logger.debug("Before redirection to the jsp page of the request tab");
		logger.debug("before forwarding to the jsp ----->>>>");
		return "request/requestsListView";
	}

	/**
	 * @param isIndirectPartner 
	 * @param isDirectPartner 
	 * @return String 
	 */ 
	private String getPartnerType(boolean isIndirectPartner, boolean isDirectPartner) {
		String partnerType = null;
		if (isDirectPartner && isIndirectPartner) {
			partnerType = PartnerTypeEnum.BOTH.getValue();
		} else if (isDirectPartner && !isIndirectPartner) {
			partnerType = PartnerTypeEnum.DIRECT.getValue();
		} else if (!isDirectPartner && isIndirectPartner) {
			partnerType = PartnerTypeEnum.INDIRECT.getValue();
		}
		return partnerType;
	}

	/**
	 * @return String 
	 */
	@RequestMapping(params = "action=printRequests")
	public String showServiceRequestPrintPage() {
		return "request/requestsPrint";
	}

	/**
	 * @return String 
	 */
	@RequestMapping(params = "action=printClaimDetail")
	public String showPrintClaimDetailPage() {
		return "claims/claimDetailPrintView";
	}

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@ResourceMapping("retrieveRequestList")
	public String getRequestList(ResourceRequest request, ResourceResponse response, Model model) throws Exception {
		logger.enter(this.getClass().getSimpleName(), "getRequestList");		
		
		PortletSession session = request.getPortletSession();
		boolean isPendingChecked = false; 
		String queryType = request.getParameter("queryType");
		if("Pending".equals(queryType))
		{
			isPendingChecked = true;
		}
		ActivityListContract contract = null;
		if(session.getAttribute("pendingDebrief",session.APPLICATION_SCOPE) == null  || 
				!(Boolean.parseBoolean(session.getAttribute("pendingDebrief",session.APPLICATION_SCOPE).toString())) || isPendingChecked
				)
		{
			logger.debug("First contract");
			contract = ContractFactory.pendingActivityListContract(request);
			
		}
		else
		{
		contract = ContractFactory.createActivityListContract(request);
		}
		
		ObjectDebugUtil.printObjectContent(contract, logger);
		
		String serviceRequestType = request.getParameter("requestType");

		
		long timeBeforeCall=System.currentTimeMillis();
		ActivityListResult activityListResult = new ActivityListResult();
		/**
		 * No activities is available when user checked both "unassigned" and /*
		 * "Claim Request"
		 */
		if (!(QueryType.Unassigned.toString().equals(queryType) && "Claim Request".equals(serviceRequestType))) {
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
					.getSiebelCrmSessionHandle(request));
			contract.setSessionHandle(crmSessionHandle);
			contract.setRequestGridLoading(true);
			ObjectDebugUtil.printObjectContent(contract, logger);
			session.setAttribute(LexmarkPPConstants.SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT, contract.clone());
			logger.debug("SESSION HANDLE = " + contract.getSessionHandle());
			logger.debug("EMPLOYEE ID = " + contract.getEmployeeId());
			logger.debug("EMPLOYEE FLAG = " + contract.isEmployeeFlag());
			try {
				
				ObjectDebugUtil.printObjectContent(contract, logger);
				activityListResult = partnerRequestsService.retrieveActivityList(contract);
				
			} catch (Exception e) {
				logger.error("Exception while retrieving activity list, The root cause is " + e.getMessage());
				ServiceStatusUtil.responseResult(response, "exception.generalError.title",
						"exception.siebel.retrieveListException", request.getLocale());
				return null;
			} finally {
				globalService.releaseSessionHandle(crmSessionHandle);
				
			}
		}
		logger.debug("The count flag is--->>"+activityListResult.isCountFlag());
		
		
		//added
		if(activityListResult.isCountFlag())
		{
			logger.debug("sss--->>> IF");
			contract.setCountFlag(false);
			contract.setRequestGridLoading(true);
			activityListResult = partnerRequestsService.retrieveActivityList(contract);
			session.setAttribute("pendingDebrief", true, session.APPLICATION_SCOPE);
			if(isPendingChecked){
			model.addAttribute("pendingDebrief","false");
			}
			else
			{
				model.addAttribute("pendingDebrief","true");	
			}
		}
		else
		{
			logger.debug("cccc--->>> ELSE");
			if(session.getAttribute("pendingDebrief",session.APPLICATION_SCOPE) == null  || 
					!(Boolean.parseBoolean(session.getAttribute("pendingDebrief",session.APPLICATION_SCOPE).toString()))){
				logger.debug("A new contract is about to be created--->>>");
				
				logger.debug("trying to find the result set-->>>");
				contract = ContractFactory.createActivityListContract(request);	
				contract.setCountFlag(false);
				ObjectDebugUtil.printObjectContent(contract, logger);
				try{
				CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
					.getSiebelCrmSessionHandle(request));
			contract.setSessionHandle(crmSessionHandle);
			contract.setRequestGridLoading(true);
			ObjectDebugUtil.printObjectContent(contract, logger);
			session.setAttribute(LexmarkPPConstants.SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT, contract.clone());
				activityListResult = partnerRequestsService.retrieveActivityList(contract);
				}
				catch(Exception e)
				{
					logger.debug("Exception"+e.getMessage()); 
					logger.debug("In the portal retrieveActivityList exception---->>>>");
				}
			}
			
			session.setAttribute("pendingDebrief", true, session.APPLICATION_SCOPE);
			model.addAttribute("pendingDebrief","false");
		}
		long timeAfterCall=System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.PARTNER_REQUEST_GRID, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
		//Added for Partner Portal Request TAb Allow Child SR July Release 2014
		ControllerUtil.sortActivityListOnCHildSR(activityListResult, contract.getSortCriteria());
		List<Activity> activityList = activityListResult.getActivityList();
		/*if(activityList!=null){
			logger.debug("The list size is---->>>"+activityList.size());
			for(Activity acv : activityList){
				logger.debug("IsChild Flag is  "+acv.isIsChildSR());
				logger.debug("date time created is "+acv.getActivityDate());
				logger.debug("Service Provider ETA is "+acv.getServiceProviderETA());
				logger.debug("Must Resolve By is "+acv.getCommittedDate());
				logger.debug("Parent SR Num is "+acv.getParentSRNum());
			}
		}*/
		activityList = activityList == null ? EMPTY_LIST : activityList;
		
		// localize request list
		localizeRequestList(activityList, request.getPortletSession(), request.getLocale());

		boolean isTechnician = containsIgnoreCase(PortalSessionUtil.getUserRoles(session),
				LexmarkConstants.ROLE_SERVICE_TECHNICIAN);
		/**********Added as part of MPS Roles ***************/
		boolean isServiceMgr = PortalSessionUtil.getUserRoles(session).contains(LexmarkConstants.ROLE_SERVICE_MANAGER)?true:false;
		model.addAttribute("isServiceMgr", isServiceMgr);
		/*********** End ************/
		
		boolean isPartnerAdministrator = containsIgnoreCase(PortalSessionUtil.getUserRoles(session),
				LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR);
		model.addAttribute("isPartnerAdministrator", isPartnerAdministrator);
		logger.debug(" For DEBRIEF / UPDATE OF SERVICE REQUEST is PartnerAdministrator ::: "+isPartnerAdministrator);
		
		model.addAttribute("isDirectPartner", PortalSessionUtil.getDirectPartnerFlag(session));
		model.addAttribute("isTechnician", isTechnician);
		model.addAttribute("startPos", contract.getStartRecordNumber());
		model.addAttribute("totalcount", activityListResult.getTotalcount());	
		model.addAttribute("activityList", activityList);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, 
		request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		response.setContentType("text/xml");
		logger.exit(this.getClass().getSimpleName(), "getRequestList");
		return "request/xml/requestListXML";
	}
	
	/**
	 * @param requestList 
	 * @param session 
	 * @param locale 
	 * @throws Exception 
	 */
	private void localizeRequestList(List<Activity> requestList, PortletSession session, Locale locale) throws Exception {
		Boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(session);
		Boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(session);
		String partnerType = getPartnerType(isIndirectPartner, isDirectPartner);
		// localize activity status
		Map<String, String> activityStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), partnerType, serviceRequestLocaleService, locale);
		ControllerUtil.batchLocalizeActivityStatus(requestList, activityStatusMap);
		// localize service type (activity type)
		Map<String, String> serviceTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ENTITLEMENT_SERVICE_DETAILS.getValue(), null, serviceRequestLocaleService, locale);
		ControllerUtil.batchLocalizeServiceType(requestList, serviceTypeMap);
		// localize subStatus
		Map<String, String> activitySubStatusMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue(), partnerType, serviceRequestLocaleService, locale);
		ControllerUtil.batchLocalizeActivitySubStatus(requestList, activitySubStatusMap);
		
		Map<String, String> serviceRequestTypeMap = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_TYPE.getValue(), null, serviceRequestLocaleService, locale);
		ControllerUtil.batchLocalizeServiceRequestType(requestList, serviceRequestTypeMap);
		
		//Added for Partner Portal Request TAb Allow Child SR July Release 2014
		Map<String, String> allowChildSRLOV = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ALLOW_CHILD_SR.getValue(), null,
				serviceRequestLocaleService, locale);
		ControllerUtil.localizeAllowChildSR(requestList,allowChildSRLOV);
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=retrieveClaimDetail")
	public String retrieveClaimDetail(Model model, RenderRequest request, RenderResponse response) throws Exception {
		logger.debug("[START] retrieveClaimDetail");
		
		//Added for CI BRD13-10-01
		final PortletSession session = request.getPortletSession();
		List<String> userRoles = PortalSessionUtil.getUserRoles(session);
		//added for BRD #14-07-08
		boolean isTechnician = containsIgnoreCase(userRoles, LexmarkConstants.ROLE_SERVICE_TECHNICIAN);		
		boolean isManager = containsIgnoreCase(userRoles, LexmarkConstants.ROLE_SERVICE_MANAGER);
		boolean isAdministration = containsIgnoreCase(userRoles, LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR);
		//ends here
		
		//retrieve requested Parts grids order 
		List<UserGridSettingResult> gridSettingList = new ArrayList<UserGridSettingResult>();
		UserGridSettingResult gridSettingResult= new UserGridSettingResult(); 
		gridSettingController.retrieveGridSetting("gridCDtlVRPRequest", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		logger.debug("gridAdditionalParts cols order "+gridSettingResult.getColsOrder());
		gridSettingList.add(gridSettingResult);
		
		//retrieve ordered parts grids order 		 
		gridSettingController.retrieveGridSetting("gridCDtlVOPRequest", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		logger.debug("gridAdditionalParts cols order "+gridSettingResult.getColsOrder());
		gridSettingList.add(gridSettingResult);
		
		//retrieve parts to be returned grids order 		 
		gridSettingController.retrieveGridSetting("gridCDtlVBRPRequest", request, model);		
		gridSettingResult = (UserGridSettingResult)request.getAttribute("gridSettingList");
		logger.debug("gridAdditionalParts cols order "+gridSettingResult.getColsOrder());
		gridSettingList.add(gridSettingResult);
		
		model.addAttribute("gridSettingList",gridSettingList);
		
		// retrieve the lov for problem code ========================================
		LocalizedSiebelLOVListContract localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract("ACTUAL_FAIL_CD", null, request.getLocale());
		LocalizedSiebelLOVListResult localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
		Map<String, String> dbLOVMap = new HashMap<String, String>();
		if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
			for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
				dbLOVMap.put(localizedLOV.getValue(), localizedLOV.getName());
			}
		}
		//	String value = (String) newMap.get("my_code");
		logger.debug("db LOV map size is "+dbLOVMap.size());
		logger.debug("My Map in DB is "+dbLOVMap);
		//end ==========================================
		
		ClaimDetailContract contract = ContractFactory.createClaimDetailContract(request);
		contract.setDebriefFlag(false);
		long timeBeforeCall=System.currentTimeMillis();
		logger.debug("-------Start-------");
		ObjectDebugUtil.printObjectContent(contract, logger);
		logger.debug("-------End-------");
		ClaimDetailResult claimDetailResult = partnerRequestsService.retrieveClaimDetail(contract);
		long timeAfterCall=System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.PARTNER_CLAIM_DETAIL, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
		ResourceURL resOutPutURL = response.createResourceURL();
        resOutPutURL.setResourceID("outPutFileURL");
        model.addAttribute("outPutFileURL", resOutPutURL.toString());
        
		if(claimDetailResult==null) {
			throw new IllegalArgumentException("Failed to open Claim Detail page: <br/> claimDetailResult is null");
		}
		if(claimDetailResult.getActivity() == null){
			throw new IllegalArgumentException("Failed to open Claim Detail page: <br/> Activity is null");
		}
		if(claimDetailResult.getActivity().getServiceRequest() == null){
			throw new IllegalArgumentException("Failed to open Claim Detail page: <br/> ServiceRequest is null");
		}
		if(claimDetailResult.getActivity().getServiceRequest().getAsset() == null){
			throw new IllegalArgumentException("Failed to open Claim Detail page: <br/> Asset is null");
		}

		//==================
		String[] failureCodeValues = null;
		String failureCode = "";
		if(claimDetailResult.getActivity().getActualFailureCode().getValue().indexOf(";") != -1){
			failureCodeValues = claimDetailResult.getActivity().getActualFailureCode().getValue().split(";");
			for(int i=0; i<failureCodeValues.length; i++ ){
				logger.debug("failureCodeValues "+i+" "+failureCodeValues[i]);
				String valuefromDB = (String) dbLOVMap.get(failureCodeValues[i]);
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
					}
				}
		}
		else{
			failureCode = claimDetailResult.getActivity().getActualFailureCode().getValue();
				
				String valuefromDB = (String) dbLOVMap.get(failureCode);
				logger.debug("from map value is "+valuefromDB);
					if(null != valuefromDB && !valuefromDB.trim().equals("")){						
							failureCode = valuefromDB;						
					}					
				
		}
		
	
		//=====================
		
		
		Activity claimDetail = claimDetailResult.getActivity();
		claimDetail.getServiceRequest().getAsset().setProductImageURL(
				ControllerUtil.retrieveProductImageUrl(productImageService, claimDetail.getServiceRequest().getAsset()
						.getProductTLI()));
		Locale locale = request.getLocale();

		// localize activity
		ControllerUtil.localizeActivity(claimDetail, serviceRequestLocaleService, locale);
		// localize partLineItem
		if (claimDetail.getOrderPartList() != null) {
			ControllerUtil.batchLocalizePart(claimDetail.getOrderPartList(), serviceRequestLocaleService, locale);
		}
		if (claimDetail.getPendingPartList() != null) {
			ControllerUtil.batchLocalizePart(claimDetail.getPendingPartList(), serviceRequestLocaleService, locale);
		}
		if (claimDetail.getReturnPartList() != null) {
			ControllerUtil.batchLocalizePart(claimDetail.getReturnPartList(), serviceRequestLocaleService, locale);
		}
		//localize additional payments
		if(claimDetail.getAdditionalPaymentRequestList() != null){
			ControllerUtil.batchLocalizeAdditionPayments(claimDetail.getAdditionalPaymentRequestList(), serviceRequestLocaleService, locale);
		}
		XmlOutputGenerator xmlGenerator = new XmlOutputGenerator(request.getLocale());
		
		//Localise Part status
		
		Map<String, String> dbLOVMap1 = new HashMap<String, String>();
		LocalizedSiebelLOVListContract localizedLOVListContract1 = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_PART_STATUS.getValue(), null, request.getLocale());
		LocalizedSiebelLOVListResult localizedLOVListResult1 = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract1);
		if (localizedLOVListResult1 != null && localizedLOVListResult1.getLocalizedSiebelLOVList().size() > 0) {
			for (ListOfValues localizedLOV : localizedLOVListResult1.getLocalizedSiebelLOVList()) {
				dbLOVMap1.put(localizedLOV.getValue(), localizedLOV.getName());
			}
		}
		logger.debug("db lov map is "+dbLOVMap1);
		if (claimDetail.getOrderPartList() != null) {
			List<PartLineItem> orderPartList  = claimDetail.getOrderPartList();
					
			if (orderPartList != null && !orderPartList.isEmpty()){
				logger.debug("Inside if");
				for(PartLineItem partLineItem : orderPartList){
					logger.debug("Inside For");					
					if(partLineItem.getPartStatus() != null){
						logger.debug("status from siebel is "+partLineItem.getPartStatus().getValue());
						String status = dbLOVMap1.get(partLineItem.getPartStatus().getValue());
						if(null != status){
							logger.debug("status from map is "+partLineItem.getPartStatus().getValue());
							partLineItem.getPartStatus().setValue(status);
						}	
					}								
					
				}
				claimDetail.setOrderPartList(orderPartList);}
			logger.debug("Outside if");
		}
		
		if (claimDetail.getPendingPartList() != null) {
			List<PartLineItem> pendingPartList  = claimDetail.getPendingPartList();
					
			if (pendingPartList != null && !pendingPartList.isEmpty()){
				logger.debug("Inside if");
				for(PartLineItem partLineItem : pendingPartList){
					logger.debug("Inside For");					
					if(partLineItem.getPartStatus() != null){
						logger.debug("status from siebel is "+partLineItem.getPartStatus().getValue());
						String status = dbLOVMap1.get(partLineItem.getPartStatus().getValue());
						if(null != status){
							logger.debug("status from map is "+partLineItem.getPartStatus().getValue());
							partLineItem.getPartStatus().setValue(status);
						}	
					}								
					
				}
				claimDetail.setPendingPartList(pendingPartList);}
			logger.debug("Outside if");
		}
		
		ClaimDetailForm claimDetailForm = createClaimDetailForm(claimDetail, locale);
		
		logger.debug("failure code value is after form creation ============== "+claimDetailForm.getClaimDetail().getActualFailureCode().getValue());
		
		//The below code is to format the date to the user specific language
		claimDetailForm.setFormattedServiceRequestedDate(DateUtil.convertToLocaleSpecificDateTime(claimDetail.getDebrief().getServiceRequestedDate(), locale));
		claimDetailForm.setFormattedServiceStartDate(DateUtil.convertToLocaleSpecificDateTime(claimDetail.getDebrief().getServiceStartDate(), locale));
		claimDetailForm.setFormattedServiceEndDate(DateUtil.convertToLocaleSpecificDateTime(claimDetail.getDebrief().getServiceEndDate(), locale));
		//ends here
		
		if(claimDetail.getActivityStatus() == null){
			throw new IllegalArgumentException("Failed to open Claim Detail page. <br/> ActivityStatus is null");
		}
		
		
		ListOfValues activitySubStatus = claimDetail.getActivitySubStatus();
		if(activitySubStatus == null){
			throw new IllegalArgumentException("Failed to open Claim Detail page: <br/> ActivitySubStatus is null");
		}
		String debriefStatus = "";
		
		if(claimDetail.getDebrief() != null){
			debriefStatus = claimDetail.getDebrief().getDebriefStatus();
		}
		
		model.addAttribute("showCloseButtonFlag", (isManager || isAdministration) && (ServiceStatusUtil.isRequestAbleToBeDebrief(activitySubStatus.getValue(),debriefStatus))); //modified for BRD #14-07-08
		model.addAttribute("showUpdateButtonFlag",((isManager || isAdministration) && ServiceStatusUtil.isRequestAbleToBeUpdate(activitySubStatus.getValue(),debriefStatus,claimDetail.getServiceRequest().getServiceRequestType().getValue()))); //modified for BRD #14-07-08
		
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

		if (PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(session)
				&& claimDetail.getAdditionalPaymentRequestList() != null
				&& claimDetail.getAdditionalPaymentRequestList().size() > 0) {
			model.addAttribute("showAdditionalPaymentRequestListFlag", true);
		} else {
			model.addAttribute("showAdditionalPaymentRequestListFlag", false);
		}
		//added by ragesree --2098 begin//
		String serviceType = request.getParameter("serviceType");
		logger.debug("===========================Claim Detail==================="+serviceType);
		if(serviceType!=null && serviceType.equals("Onsite Exchange")){
			claimDetailForm.getClaimDetail().setExchangeFlag(true);
		}
		if(serviceType!=null){
			claimDetailForm.getClaimDetail().setServiceType(serviceType);
		}
		//added by ragesree --2098 end//
		claimDetailForm.getClaimDetail().getActualFailureCode().setName(failureCode);
		model.addAttribute("claimDetailForm", claimDetailForm);
		model.addAttribute("closeOutFlag", request.getParameter("closeOutFlag"));
		model.addAttribute("claimUpdateFlag", request.getParameter("claimUpdateFlag"));
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET,
		request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		logger.debug("[END] retrieveClaimDetail");
		return "claims/claimDetailView";
	}

	private ClaimDetailForm createClaimDetailForm(Activity claimDetail, Locale locale) throws Exception {
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		ClaimDetailForm claimDetailForm = new ClaimDetailForm();
		claimDetailForm.setActivityNoteListXML(xmlOutputGenerator.convertActivityNoteListToXML(claimDetail
				.getActivityNoteList()));
		claimDetailForm.setAdditionalPaymentRequestListXML(xmlOutputGenerator.convertAdditionalPaymentListToXML(
				claimDetail.getAdditionalPaymentRequestList()));
		claimDetailForm.setOrderPartListXML(xmlOutputGenerator
				.convertOrderPartListToXML(claimDetail.getOrderPartList()));
		claimDetailForm.setPendingPartListXML(xmlOutputGenerator.convertPendingPartListToXML(claimDetail
				.getPendingPartList()));
		// RMA CHANGES
		claimDetailForm.setReturnPartListXML(xmlOutputGenerator.convertRMAReturnPartListToXML(claimDetail
				.getReturnPartList(), true));
		claimDetailForm.setClaimDetail(claimDetail);
		List<Attachment> modifiedAttachmentList = new ArrayList<Attachment>();
		if(claimDetail.getAttachmentList()!=null){
			logger.debug(" Attachment activity.getAttachmentList() size "+claimDetail.getAttachmentList().size());
			
			List<Attachment> attachmentList = claimDetail.getAttachmentList();
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
			claimDetailForm.setAttachmentList(modifiedAttachmentList);
		}
			
		
		
		// Page Count
		claimDetailForm.setPageCountAll(claimDetail.getPageCountAll());
		return claimDetailForm;
	}

	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception  
	 */
	@ResourceMapping("downloadRequestsURL")
	public void downloadRequestsURL(ResourceRequest request, ResourceResponse response, Model model) throws Exception {
		logger.debug("[START]download request");
		String downloadType = request.getParameter("downloadType");
		float timezoneOffset = 0;
		String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		if (!StringUtils.isEmpty(timezoneOffsetStr)) {
			timezoneOffset = Float.valueOf(timezoneOffsetStr).floatValue();
		}
		
		PortletSession session = request.getPortletSession();
		ActivityListContract contract = (ActivityListContract) session.getAttribute(LexmarkPPConstants.SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT);
		session.setAttribute(LexmarkPPConstants.SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT, contract.clone());
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));
		if((Boolean)request.getPortletSession().getAttribute("IS_CREATE_CHILD_SR", javax.portlet.PortletSession.APPLICATION_SCOPE)){
		contract.setCurrentTimeStamp(DateUtil.converCurrentTimeStampToEquivalentGMTTime(new Date()));
		}
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);		
			ObjectDebugUtil.printObjectContent(contract, logger);
			ActivityListResult activityListResult = partnerRequestsService.retrieveActivityList(contract);
			//Added for Partner Portal Request TAb Allow Child SR July Release 2014
			ControllerUtil.sortActivityListOnCHildSR(activityListResult, contract.getSortCriteria());
			List<Activity> requestList = activityListResult.getActivityList();
			localizeDate(requestList, timezoneOffset);
			
			localizeRequestList(requestList, request.getPortletSession(), request.getLocale());
			
			DataExporter dataExporter = DataExporterFactory.getDataExporter(downloadType);
			dataExporter.setLocale(request.getLocale());
			dataExporter.export(response, "activity", LexmarkPPConstants.PATTERNS_REQUEST_EXPORT, requestList);
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("[END] download service");
	}
	/**
	 * @param activityList 
	 * @param timezoneOffset 
	 */
	private void localizeDate(List<Activity> activityList, float timezoneOffset){
		for (Activity activity : activityList){
			TimezoneUtil.adjustDate(activity.getActivityDate(),(0-timezoneOffset));
			/*Added by AMS for LEX:AIR00066001 start*/
			if(activity.getCustomerRequestedResponseDate()!= null){
				TimezoneUtil.adjustDate(activity.getCustomerRequestedResponseDate(),(0-timezoneOffset));
			}
			/*End */
		}
	}
	
	/**
	 * @param activityId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("assignToMe")
	public void assignToMe(@RequestParam("activityId") String activityId, ResourceRequest request,
			ResourceResponse response) throws Exception {
		AssignedTechnicianUpdateResult result = new AssignedTechnicianUpdateResult();
		AssignedTechnicianUpdateContract contract = ContractFactory.createAssignedTechnicianUpdateContract(activityId,
				request);
		ObjectDebugUtil.printObjectContent(contract, logger);
		try {
			long timeBeforeCall=System.currentTimeMillis();
			result = partnerRequestsService.updateAssignedTechnician(contract);
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.PARTNER_ASSIGN_TO_ME, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
		} catch (Exception e) {
			logger.debug("Exception"+e.getMessage()); 
			result.setResult(Boolean.FALSE);
		}
		logger.debug("User (" + contract.getEmployeeId() + ") tried to take Request (" + activityId
				+ "), the result is " + (result.getResult() ? "successful" : "failure"));
		String errorCode = result.getResult() ? SUCCESS_UPDATE_ASSIGNED_TECHINICIAN
				: EXCEPTION_UPDATE_ASSIGNED_TECHINICIAN;
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(), "\"" + activityId + "\"");
	}

	/**
	 * @param activityId 
	 * @param serviceRequestId 
	 * @param request 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showServiceHistoryDetailPage")
	public String showServiceHistoryPage(@RequestParam("activityId") String activityId, 
			@RequestParam("serviceRequestId") String serviceRequestId, 
			RenderRequest request,
			Model model) throws Exception {
		ServiceActivityHistoryDetailContract contract = ContractFactory
				.createServiceActivityHistoryDetailContract(activityId,serviceRequestId);

		Locale locale = request.getLocale();
		Map<String, String> errorCode1Map = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_1.getValue(), null, globalService, serviceRequestLocaleService, locale);
		Map<String, String> errorCode2Map = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_2.getValue(), null, globalService, serviceRequestLocaleService, locale);
		Map<String, String> resolutionCodeMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_RESOLUTION_CODE.getValue(), null, globalService, serviceRequestLocaleService, locale);
		Map<String, String> problemCodeMap = ControllerUtil.retrieveLocalizedLOVMapForSelection(
				SiebelLocalizationOptionEnum.PARTNER_PROBLEM_CODE.getValue(), null, globalService, serviceRequestLocaleService, locale);

		Activity activity = partnerRequestsService.retrieveServiceActivityHistoryDetail(contract).getActivity();
		if (activity == null){
			throw new IllegalArgumentException("Failed to open Service History Detail Page:<br/>Activity is null");
		}
		if(activity.getServiceRequest() == null){
			throw new IllegalArgumentException("Failed to open Service History Detail Page: <br/> ServiceRequest is null");
		}
		if(activity.getServiceRequest().getAsset() == null){
			throw new IllegalArgumentException("Failed to open Service History Detail Page: <br/> Asset is null");
		}
		// localize activity
		localizeActivity(activity, serviceRequestLocaleService, locale);

		Asset device = activity.getServiceRequest().getAsset();
		device.setProductImageURL(ControllerUtil.retrieveProductImageUrl(productImageService, device.getProductTLI()));
		ListOfValues resolutionCode = activity.getResolutionCode();
		if (resolutionCode != null)
		{
			resolutionCode.setName(resolutionCodeMap.get(resolutionCode.getValue()));
		}
		activity.getDebrief().setResolutionCode(resolutionCode);
		
		ListOfValues problemCode = activity.getActualFailureCode();
		if (problemCode != null)
		{
			problemCode.setName(problemCodeMap.get(problemCode.getValue()));
		}

		List<PartLineItem> orderPartList = activity.getOrderPartList();
		orderPartList = orderPartList == null ? new ArrayList<PartLineItem>() : orderPartList;

		final GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(orderPartList.size());
		int i = 0;
		
		// changes to translate error code 2
		LocalizedSiebelLOVListContract localizedLOVListContract = ContractFactory.createLocalizedSiebelLOVListContract(SiebelLocalizationOptionEnum.PARTNER_DEBRIEF_ERROR_CODE_2.getValue(), null, locale);
		LocalizedSiebelLOVListResult localizedLOVListResult = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(localizedLOVListContract);
		Map<String, String> dbLOVMap = new HashMap<String, String>();		
		if (localizedLOVListResult != null && localizedLOVListResult.getLocalizedSiebelLOVList().size() > 0) {
			for (ListOfValues localizedLOV : localizedLOVListResult.getLocalizedSiebelLOVList()) {
				dbLOVMap.put(localizedLOV.getValue().toLowerCase(), localizedLOV.getName());
			}
		}
		logger.debug("size for dbLOVMap is "+dbLOVMap.size());
		logger.debug("dbLOVMap is "+dbLOVMap);
		
		
		
		for (PartLineItem partLineItem : orderPartList) {
			gridXmlBuilder.nextRow(String.valueOf(i++));
			String quantity = String.valueOf(partLineItem.getQuantity());
			String partNumber = partLineItem.getPartNumber();
			String partName = XMLEncodeUtil.escapeXML(partLineItem.getPartName());
			//Part Status fetched for CI BRD 13-10-15
			String partStatus = partLineItem.getPartDisposition().getValue();
			String errorCode1 = "";
			if (partLineItem.getErrorCode1() != null)
			{
				errorCode1 = XMLEncodeUtil.escapeXML(errorCode1Map.get(partLineItem.getErrorCode1().getValue()));
			}
			String errorCode2 = "";
			String errorCodeTranslatedValue = dbLOVMap.get(partLineItem.getErrorCode2().getValue().toLowerCase());
			logger.debug("errorCodeTranslatedValue is "+errorCodeTranslatedValue);
			if (partLineItem.getErrorCode2() != null)
			{
				if(null != errorCodeTranslatedValue){
					errorCode2 = XMLEncodeUtil.escapeXML(errorCodeTranslatedValue);
					logger.debug("errorCode2 is "+errorCode2);
				}
				else{
					errorCode2 = XMLEncodeUtil.escapeXML(partLineItem.getErrorCode2().getValue());
				}
			}
			//Part Status added for CI BRD 13-10-15
			gridXmlBuilder.addCells(quantity, partNumber, partName, errorCode1, errorCode2,partStatus);
		}
		String timezoneOffset = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, timezoneOffset);
		model.addAttribute("partsAndToolsXML", gridXmlBuilder.getGridXmlString());
		model.addAttribute("activity", activity);
		return "request/viewServiceHistoryPopup";
	}

	/**
	 * @param activity 
	 * @param serviceRequestLocaleService 
	 * @param locale 
	 * @throws Exception 
	 */
	private void localizeActivity(Activity activity, ServiceRequestLocale serviceRequestLocaleService, Locale locale)throws Exception  {
		activity.getActivityStatus().setType(SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue());
		activity.setActivityStatus(ControllerUtil.getLocalizedSiebelValue(activity.getActivityStatus(),
				serviceRequestLocaleService, locale));
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param assetId 
	 * @param serviceRequestId 
	 * @return String 
	 * @throws Exception 
	 */
	@ResourceMapping("getServiceHistoryList")
	public String getServiceHistoryListXML(Model model, ResourceRequest request, ResourceResponse response,
			@RequestParam("assetId") String assetId, @RequestParam("serviceRequestId") String serviceRequestId) throws Exception {
		if (StringUtils.isEmpty(assetId))
		{
			return "request/xml/serviceHistoryXML";
		}

		ServiceActivityHistoryListContract contract = ContractFactory.createServiceActivityHistoryListContract(assetId,serviceRequestId);
		logger.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContent(contract, logger);
		logger.info("end printing lex loggger");
		ServiceActivityHistoryListResult result = partnerRequestsService.retrieveServiceActivityHistoryList(contract);

		ControllerUtil.localizeActivityListForServiceHistory(result.getActivityList(), serviceRequestLocaleService, request.getLocale());

		model.addAttribute("activityList", result.getActivityList());
		model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		response.setContentType("text/xml");
		return "request/xml/serviceHistoryXML";
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("showEmailNotificationsURL")
	public void showEmailNotificationsURL(Model model, ResourceRequest request, ResourceResponse response)
			throws Exception {
		String serviceRequestId = request.getParameter("serviceRequestId");
		String emailAddress = request.getParameter("emailAddress");
		PartnerNotificationsContract contract = new PartnerNotificationsContract();
		contract.setServiceRequestId(serviceRequestId);
		contract.setEmailAddress(emailAddress);
		PartnerNotificationsResult result = null;
		try {
			result = partnerRequestsService.retrievePartnerNotifications(contract);
		} catch (Exception e) {
			throw new Exception(ExceptionUtil.getLocalizedExceptionMessage(
					"exception.retrieveEmailNotificationsFailed", request.getLocale(), e));
		}
		String xml = new XmlOutputGenerator(request.getLocale())
				.convertServiceRequestActivityListToXML((result == null ? null : result.getServiceRequestActivityList()));
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();
	}

	/**
	 * @param partnerRequestsService 
	 */
	public void setPartnerRequestsService(PartnerRequestsService partnerRequestsService) {
		this.partnerRequestsService = partnerRequestsService;
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("downClaimDetailPdfURL")
	public void downClaimDetailPdfURL(Model model, ResourceRequest request, ResourceResponse response) 
			throws Exception {
		ClaimDetailContract contract = ContractFactory.createClaimDetailContract(request);
		contract.setDebriefFlag(false);
		ClaimDetailResult claimDetailResult = partnerRequestsService.retrieveClaimDetail(contract);
		
		Activity claimDetail = claimDetailResult.getActivity();
		claimDetail.getServiceRequest().getAsset().setProductImageURL(
				ControllerUtil.retrieveProductImageUrl(productImageService, claimDetail.getServiceRequest().getAsset()
						.getProductTLI()));
		Locale locale = request.getLocale();
		PartnerNotificationsContract partnerNotificationsContract = new PartnerNotificationsContract();
		partnerNotificationsContract.setServiceRequestId(claimDetail.getServiceRequest().getId());
		partnerNotificationsContract.setEmailAddress(claimDetail.getEmailAddress());
		PartnerNotificationsResult partnerNotificationsResult = null;
		partnerNotificationsResult = partnerRequestsService.retrievePartnerNotifications(partnerNotificationsContract);
		List<ServiceRequestActivity> serviceRequestActivityList = partnerNotificationsResult.getServiceRequestActivityList();
		
		
		ServiceActivityHistoryListContract historyListContract = ContractFactory.createServiceActivityHistoryListContract(claimDetail.getServiceRequest().getAsset().getAssetId(), claimDetail.getServiceRequest().getId());
		ServiceActivityHistoryListResult historyListResult = partnerRequestsService.retrieveServiceActivityHistoryList(historyListContract);
		ControllerUtil.localizeActivityListForServiceHistory(historyListResult.getActivityList(), serviceRequestLocaleService, locale);
		
		PortletSession session = request.getPortletSession();
		if(!PortalSessionUtil.getAllowAdditionalPaymentRequestFlag(session)){
			claimDetail.setAdditionalPaymentRequestList(null);
		}
		
		// localize activity
		ControllerUtil.localizeActivity(claimDetail, serviceRequestLocaleService, locale);
		// localize partLineItem
		if (claimDetail.getOrderPartList() != null) {
			ControllerUtil.batchLocalizePart(claimDetail.getOrderPartList(), serviceRequestLocaleService, locale);
		}
		if (claimDetail.getPendingPartList() != null) {
			ControllerUtil.batchLocalizePart(claimDetail.getPendingPartList(), serviceRequestLocaleService, locale);
		}
		if (claimDetail.getReturnPartList() != null) {
			ControllerUtil.batchLocalizePart(claimDetail.getReturnPartList(), serviceRequestLocaleService, locale);
		}
		//localize additional payments
		if(claimDetail.getAdditionalPaymentRequestList() != null){
			ControllerUtil.batchLocalizeAdditionPayments(claimDetail.getAdditionalPaymentRequestList(), serviceRequestLocaleService, locale);
		}
		float timezoneOffset = 0;
		String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		if (!StringUtils.isEmpty(timezoneOffsetStr)) {
			timezoneOffset = Float.valueOf(timezoneOffsetStr).floatValue();
		}
		PdfDataExporter pdfDataExporter = new PdfDataExporter();
		pdfDataExporter.setLocale(locale);
		pdfDataExporter.setTimezoneOffset(timezoneOffset);
		pdfDataExporter.generateClaimDetailPdf(claimDetail,serviceRequestActivityList,historyListResult.getActivityList(),response);
	}
	
	/**
	 * @param l 
	 * @param s 
	 * @return boolean 
	 */
	public boolean containsIgnoreCase(List <String> l, String s){
		 Iterator <String> it = l.iterator();
		 while(it.hasNext()){
		  if(it.next().equalsIgnoreCase(s))
		  {
		  return true;
		  }
		 }
		 return false;
		}
// Download Claims and Request
	
	/**
	 * @param activityList 
	 * @param timezoneOffset 
	 */
	private void localizeDateRequest(List<DownloadRequest> activityList, float timezoneOffset){
		for (DownloadRequest activity : activityList){
			
			if((activity.getActualEnd() !=null)) 
			{
			TimezoneUtil.adjustDate(activity.getActualEnd(),(0-timezoneOffset));
			}
			
			if( (activity.getActualStart() !=null))
			{
			TimezoneUtil.adjustDate(activity.getActualStart(),(0-timezoneOffset));
			}
			
			if(activity.getCustomerRequestResponse()!=null)
			{
			TimezoneUtil.adjustDate(activity.getCustomerRequestResponse(),(0-timezoneOffset));
			}
			
			if(activity.getEstTimeArrival()!=null)
			{
			TimezoneUtil.adjustDate(activity.getEstTimeArrival(),(0-timezoneOffset));
			}
			
			if(activity.getStatusAsOf()!=null)
			{
			TimezoneUtil.adjustDate(activity.getStatusAsOf(),(0-timezoneOffset));
			}
			}
		
	}
	
	/**
	 * @param activityList 
	 * @param timezoneOffset 
	 */
	private void localizeDateClaim(List<DownloadClaim> activityList, float timezoneOffset){
		for (DownloadClaim activity : activityList){
			if(activity.getSrvcEndDate() !=null) 
			{
			TimezoneUtil.adjustDate(activity.getSrvcEndDate(),(0-timezoneOffset));
			}
			if (activity.getSrvcStartDate() !=null) 
			{
			TimezoneUtil.adjustDate(activity.getSrvcStartDate(),(0-timezoneOffset));
			}
			
			}
		
	}

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("downloadClaimsRequestsView")
	public void downloadClaimsRequestsView(ResourceRequest request, ResourceResponse response, Model model) throws Exception {

		logger.debug("[START]downloadClaimsRequestsView request");
		

		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		boolean dataExported=false;
		String downloadType = request.getParameter("downloadType");
		String reportType = request.getParameter("reportType");
		try {
	
		String[] columnPatterns = null;

	
		PortletSession session = request.getPortletSession();
		ActivityListContract contract = (ActivityListContract) session.getAttribute(LexmarkPPConstants.SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT);
		session.setAttribute(LexmarkPPConstants.SESSION_KEY_REQUESTS_DOWNLOAD_CONTRACT, contract.clone());
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));

		logger.debug("reportType = " + reportType);

		if("downloadRequest".equalsIgnoreCase(reportType)) {
		
			contract.setServiceRequestType("Service Request");
        	columnPatterns = LexmarkPPConstants.PATTERNS_DONWLOAD_REQUEST_EXPORT_1;
        	
        	logger.debug("reportType = PATTERNS_DONWLOAD_REQUEST_EXPORT");
        } 
		else if("downloadClaim".equalsIgnoreCase(reportType)) {
		
			contract.setServiceRequestType("Claim Request");
			columnPatterns = LexmarkPPConstants.PATTERNS_DONWLOAD_CLAIMS_EXPORT;
        	
        	logger.debug("reportType = PATTERNS_DONWLOAD_CLAIMS_EXPORT");
        }


		
			List requestList = null;
			contract.setSessionHandle(crmSessionHandle);
			float timezoneOffset = 0;
			
			String inputTimezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
			

			if("downloadRequest".equalsIgnoreCase(reportType)) {
				logger.debug("downloadRequest() is  called");				
				DownloadRequestListResult downloadRequestListResult = partnerRequestsService.retrieveDownloadRequestList(contract);
				requestList = (List<DownloadRequest>) downloadRequestListResult.getActivityList();
				if(requestList!=null){

					if (!StringUtils.isEmpty(inputTimezoneOffsetStr)) {
						timezoneOffset = Float.valueOf(inputTimezoneOffsetStr).floatValue();
					}
					logger.debug("downloadRequest List is not null !!! ");
				localizeDateRequest(requestList, timezoneOffset);
				}
				else{
					logger.debug("downloadRequest List is null !!! ");
				}
			} else if("downloadClaim".equalsIgnoreCase(reportType)) {
				logger.debug("downloadClaim () is  called");
				DownloadClaimListResult downloadRequestListResult = partnerRequestsService.retrieveDownloadClaimList(contract);
				requestList = (List<DownloadClaim>) downloadRequestListResult.getActivityList();
				if(requestList!=null){
					if (!StringUtils.isEmpty(inputTimezoneOffsetStr)) {
						timezoneOffset = Float.valueOf(inputTimezoneOffsetStr).floatValue();
					}
					logger.debug("downloadClaim List is not null !!! ");
				localizeDateClaim(requestList, timezoneOffset);
				}else{
					logger.debug("downloadClaim List is null !!! ");
				}
	        }

					
			DataExporter dataExporter = DataExporterFactory.getDataExporter(downloadType);
			dataExporter.setLocale(request.getLocale());
			logger.debug("Before export showDownloadClaimsView service");
			dataExporter.exportRequestClaims(response, reportType, columnPatterns, requestList);			
			dataExported=true;
			
			
			
		}
		  catch(Exception e){
			  logger.error("Exception in downloadClaimsRequestsView "+e);
		  }
		  finally {
			  logger.info("In the Finally releasing the crmSessionHandle :: ");
			  logger.info("DataExported  is  :: "+dataExported);
			  if(!dataExported){
				  try{					  
           		StringBuffer cvsContent = new StringBuffer();
              	cvsContent.append("No Data For ");
              	cvsContent.append(reportType);
              	response.setProperty("Content-disposition", "attachment; filename=" +reportType+".csv");
                response.setContentType("text/csv");
                OutputStream outputStream = response.getPortletOutputStream();
				// now get a PrintWriter to stream the chars.
                PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            	out.write(cvsContent.toString());
        		out.flush();
        		out.close();
				  }
				  catch(Exception e){
					  logger.error("Exception in blank downloadClaimsRequestsView "+e);
				  }
			  }
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		  logger.debug("[END] downloadClaimsRequestsView service");
	
	}

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("outPutFileURL")
	public void outPutCSVFile(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{
		logger.debug("------------- --outPutDownloadFile start---------["+System.nanoTime()+"]");
		AttachmentContract contract = new AttachmentContract();
		logger.debug("------------- --actualFileName---------"+request.getParameter("actualFileName"));
		logger.debug("------------- --activityId---------"+request.getParameter("activityId"));
		logger.debug("------------- --userFileName---------"+request.getParameter("userFileName"));
		logger.debug("------------- --identifier---------"+request.getParameter("identifier"));
		logger.debug("------------- --type---------"+request.getParameter("type"));

		String userFileName=request.getParameter("userFileName");
		String attachmentName=request.getParameter("actualFileName");
		
		contract.setUserFileName(request.getParameter("actualFileName"));
		contract.setIdentifier(request.getParameter("identifier"));
		
		// CI6 Claim Create Attachment
		contract.setRequestType(request.getParameter("type"));
		logger.debug("Contract value we are sending actualFileName "+request.getParameter("actualFileName")+" and activity id is "+request.getParameter("activityId"));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		
		logger.debug(" Contract :: contract.getIdentifier() :: "+contract.getIdentifier());
		logger.debug(" Contract :: contract.getParentId() :: "+contract.getParentId());
		logger.debug(" Contract :: contract.getRequestType() :: "+contract.getRequestType());
		logger.debug(" Contract :: contract.getUserFileName() :: "+contract.getUserFileName());
		
		
		try {
			logger.debug("Calling the amind service for download attachment");
			
			logger.debug("-------START Contract-------");
			ObjectDebugUtil.printObjectContent(contract, logger);
			logger.debug("-------END Contract-------");
			
			AttachmentResult attachmentResult = attachmentService.downloadAttachment(contract);
			logger.debug("------------- attachmentResult "+attachmentResult);
			InputStream fileStream = attachmentResult.getFileStream();

			
					if(fileStream!=null){
						logger.debug("service call done file stream Not Null "+fileStream.toString());
						userFileName = userFileName.replace(" ", "%20");
						attachmentName = attachmentName.replace(" ", "%20");
					
					response.setProperty("Content-disposition", "attachment; filename=\"" + attachmentName +"\"");
					
					
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
			        }else if(attachmentName.indexOf("ppt")>0){
			        	response.setContentType("application/vnd.ms-powerpoint");
			        }else if (attachmentName.indexOf("pptx") > 0) {
						response.setContentType("application/vnd.ms-powerpoint");
			        }
					 InputStream inputStream = fileStream;
					
					  OutputStream out = response.getPortletOutputStream();
					  byte buf[]=new byte[1024];
					  int len;
					  while((len=inputStream.read(buf))>0)
					  {
					  out.write(buf,0,len);
					  }
					  
					  out.close();
					  inputStream.close();
					  logger.debug("\nFile is created...................................");
				}
				else{
					logger.debug("service call done file stream is Null");

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
				logger.debug("RequestController exception : " + e.getMessage());
			}
			catch(Exception exception){
				logger.debug("RequestController  exception : " + exception.getMessage());
				response.getPortletOutputStream();
				
			}
	    finally {
	        	logger.debug("--------------- Reached finally block -----------------");
	    }
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
	
	
	
	/**
	 * @param info 
	 */
	private void printLogger(String info){
		logger.info(info);
	}
	
	//Added for CI BRD13-10-01 --STARTS
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=retrieveSaveAsDraftDetail")
	public String retrieveSaveAsDraftDetail(Model model, RenderRequest request, RenderResponse response) throws Exception {
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String assetID = ((httpReq.getParameter("assetID")== null)?"":(String)request.getAttribute("assetID"));
		request.setAttribute("DraftChecking", "isAdraft");		
		ClaimDetailContract contract = ContractFactory.createClaimDetailContract(request);
		contract.setDebriefFlag(false);
		ObjectDebugUtil.printObjectContent(contract, logger);
		ClaimDetailResult claimDetailResult = partnerRequestsService.retrieveClaimDetail(contract);
		request.setAttribute("ClaimDetailResultForm", claimDetailResult);
		request.setAttribute("assetIDFormReq", assetID);
		model.addAttribute("ClaimDetailResultForm", claimDetailResult);
		return claimRequestCreateController.showCreateClaimPage(model, request, response);		
		
	}
	//Added for CI BRD13-10-01 --ENDS
}
