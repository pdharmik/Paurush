/**
 * Copyright@ 2013. This document has been created & written by 
 * Wipro technologies for Lexmark and this is copyright to Lexmark 
 *
 * File         	: AddressListPopUpController.java 
 * Package     		: com.lexmark.portlet 
 * Creation Date 	: 
 *
 * Modification History: 
 *
 * ---------------------------------------------------------------------
 * Author  			Date		 		Version  	 	Comments 
 * ---------------------------------------------------------------------
 *
 */

package com.lexmark.portlet;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.OfflineModeAttachmentContract;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.PartnerTypeEnum;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.service.api.PartnerHardwareInstallDebriefOfflineModeService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.webservice.api.AcceptOfflineActivity;
/**
* Class used for OfflineInstallRequest
*
* @version 1.10 10 Nov 2014 
* @author Wipro technologies  
*/
@Controller
@RequestMapping("VIEW")
public class OfflineInstallRequestController {
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(OfflineInstallRequestController.class);
	private static String CLASS_NAME="OfflineInstallRequestController.java";
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private GridSettingController gridSettingController;
	@Autowired
	private PartnerHardwareInstallDebriefOfflineModeService partnerHardwareInstallDebriefOfflineModeService;
	@Autowired
	private AcceptOfflineActivity acceptOfflineActivityService;
	
	
	/**
	 * @param request 
	 * @param model 
	 * @return string  
	 */
	@RequestMapping
	public String showDefaultView(RenderRequest request,Model model){
		LOGGER.enter(CLASS_NAME, "showDefaultView");
		Locale locale = request.getLocale();
		PortletSession session = request.getPortletSession();
		Boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(session);
		Boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(session);
		String partnerType = getPartnerType(isIndirectPartner, isDirectPartner);
		/**
		 * These three maps are for hardware debrief
		 * */
		Map<String, String> serviceRequestStatus = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		
		
		Map<String, String> hwdebriefRequestStatus = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
	
		
		Map<String, String> hwdebriefRequestStatusDetails = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ACTIVITY_SUB_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		
		Map<String, String> hwDebriefRequestType = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_REQUEST_LIST.getValue(), partnerType,
				serviceRequestLocaleService, locale);
		
		model.addAttribute("hwDebriefRequestType", hwDebriefRequestType);
		model.addAttribute("hwdebriefRequestStatus", hwdebriefRequestStatus);
		model.addAttribute("hwdebriefRequestStatusDetails", hwdebriefRequestStatusDetails);
		model.addAttribute("serviceRequestStatus", serviceRequestStatus);
		
		gridSettingController.retrieveGridSetting("hardwareActivityRequestList", request, model);
		LOGGER.exit(CLASS_NAME, "showDefaultView");
		return "offlineInstallRequest/installRequestOffline";
	}
	
	
	/**
	 * @param request 
	 * @param response 
	 * @param filename 
	 * @param fileSourcePath 
	 * @param fileSourceType 
	 * @param activityId 
	 */
	@ResourceMapping("generateInstallationDoc")
	public void generateInstallationDoc(ResourceRequest request,ResourceResponse response,
			@RequestParam("fileName") String filename,
			@RequestParam("fileSourcePath") String fileSourcePath,
			@RequestParam("fileSourceType") String fileSourceType,
			@RequestParam("activityId") String activityId){
		LOGGER.enter(CLASS_NAME, "generateInstallationDoc");
		OfflineModeAttachmentContract contract = new OfflineModeAttachmentContract();
		
		contract.setActivityId(activityId);
		try {
			partnerHardwareInstallDebriefOfflineModeService.generateInstallationDoc(contract);
		} catch (Exception e) {
			LOGGER.error("[ERROR GENERATING FILE FOR activity id="+activityId+"] \n Exception "+e.getCause());
		}
		LOGGER.exit(CLASS_NAME, "generateInstallationDoc");
		
	}
	/**
	 * @param isIndirectPartner 
	 * @param isDirectPartner 
	 * @return string 
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
	
	@ResourceMapping("acceptActivity")
	public void acceptActivity(ResourceRequest request,ResourceResponse response,
			@RequestParam("srNumber") String srNumber){
		LOGGER.debug("srNumber---------->>" + srNumber);
		try {
			String output =acceptOfflineActivityService.createofflineActivity(srNumber);
			LOGGER.debug("output---------->>" + output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exit(CLASS_NAME, "acceptActivity");
		
	}
}
