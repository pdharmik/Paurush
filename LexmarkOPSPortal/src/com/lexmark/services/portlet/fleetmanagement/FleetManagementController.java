package com.lexmark.services.portlet.fleetmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.amind.common.util.StringUtils;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.LBSAssetListContract;
import com.lexmark.contract.LBSCHLContract;
import com.lexmark.contract.UserFilterSettingContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.domain.UserFieldsViewSetting;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.LBSCHLListResult;
import com.lexmark.result.LBSFloorPlanListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.LBSCHLMappingService;
import com.lexmark.service.api.LBSFloorPlanService;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.UserFilterSettingService;
import com.lexmark.service.impl.real.domain.LBSAddress;
import com.lexmark.service.impl.real.domain.LBSCHLMapping;
import com.lexmark.service.impl.real.domain.LBSLocationBuildingType;
import com.lexmark.services.form.FleetManagementForm;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.HTMLOutputGenerator;
import com.lexmark.services.util.JSONEncryptUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.URLImageUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.liferay.portal.util.PortalUtil;
/**
 * @author wipro  
 *
 */
@Controller
@RequestMapping("VIEW")
public class FleetManagementController extends BaseController{
	
	private static Logger LOGGER = LogManager.getLogger(FleetManagementController.class);
	
	@Autowired
	private FilterManagementController filterMgmtcontroller;
	
	@Autowired
	private CommonController commonController;
	
	@Autowired
	private PreferenceManagementController preferenceMgmtController;
	
	@Autowired
	private OrderSuppliesAssetService orderSuppliesAssetService;
	
	@Autowired
	private GlobalService globalService;
	
	@Autowired
	private SharedPortletController sharedPortletController;
	
	@Autowired
	private LBSFloorPlanService lBSFloorPlanService;
	
	@Autowired
	private LBSCHLMappingService lBSCHLMappingService;
	
	@Autowired
	private UserFilterSettingService filterFieldService;
	
	private String lbsEndpointURL;
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping
	public String showMapView(Model model, RenderRequest request,
			RenderResponse response){
		
		FleetManagementForm form=new FleetManagementForm();
		PortletSession session=request.getPortletSession();
		
		
		
		form.setContactId(PortalSessionUtil.getContactId(session));
		form.setEmailAddress(PortalSessionUtil.getEmailAddress(session));
		form.setEndPointURL(getLbsEndpointURL());
		
		LOGGER.debug("endPoint = "+getLbsEndpointURL());
		
		
		
		
		getAccessForCreateRequest(request,form);
		
		setURlUtils(request,form);
		
		/*List<LBSAddress> countries=filterMgmtcontroller.getCountry(request);
		LOGGER.debug(String.format("[country list length is = %s]", countries!=null?countries.size():0));
		form.setCountryString(HTMLOutputGenerator.convertToOptions(countries, LexmarkConstants.COUNTRY));*/

		
		
		//Below check is for showing extra fields for Device Filter
		if(LexmarkConstants.USER_SEGMENT_EMPLOYEE.equalsIgnoreCase(PortalSessionUtil.getUserSegment(request.getPortletSession()))) {
			form.setEmployee(false);
		}
		
		
		/*//DB Retrieval of Fields to be displayed
		UserFieldsViewSetting fieldSetting=preferenceMgmtController.retrievePopupSettings(request);

		//Db retireval for the values that are already saved in the database.
		String preferences=preferenceMgmtController.retrievePreferences(request);
		form.setFilterPreferences(StringEscapeUtils.escapeJavaScript(preferences));
		
		//For canned queries
		form.setCannedQueries(filterMgmtcontroller.retrieveCannedQueriesList(request));
		
		if(fieldSetting!=null){
			LOGGER.debug("field Setting "+fieldSetting.getFieldsDisplayed());
			form.setDefaultFieldsView(fieldSetting.getFieldsDisplayed());
		}*/
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String backJson=httpReq.getParameter("backJson");
		LOGGER.debug("backJSON == "+backJson);
		form.setBackInfo(StringUtils.isNotBlank(backJson)==true?StringEscapeUtils.escapeJavaScript(backJson):"");
		setFleetManagementDataToForm(request,form,model);
		
		form.setCompanyName(PortalSessionUtil.getServiceUser(session).getCompanyName());
		model.addAttribute("fleetMgmtForm", form);
		request.setAttribute("fleetManagementFlag", "true");
		model.addAttribute("fleetManagementFlag", "true");
		LOGGER.debug("Setting fleetManagementFlag to true in fleet");
		
		
		return "fleetmanagement/defaultView";
	}
	
	
	/**
	 * @param request 
	 * @param form 
	 * @param model 
	 * @return boolean  
	 */
	private boolean setFleetManagementDataToForm(final PortletRequest request,final FleetManagementForm form,
			final Model model){
		long time1=System.currentTimeMillis();
		try{
			ExecutorService executor = Executors.newFixedThreadPool(4);
			Future<List<LBSAddress>> countryListFuture=null;
			Future<List<LBSLocationBuildingType>> buildingListFuture=null;
			
			Future<Boolean> localDBCall=null;
			Future<Boolean> chkL5User=null;
			countryListFuture= executor.submit(new Callable<List<LBSAddress>>() {
						public List<LBSAddress> call() throws Exception {
							List<LBSAddress> countries=filterMgmtcontroller.getCountry(request);
							LOGGER.debug(String.format("OUT Country Call[country list length is = %s]", countries!=null?countries.size():0));
							
							return countries;	
					}
					
				});
			
			buildingListFuture= executor.submit(new Callable<List<LBSLocationBuildingType>>() {
				public List<LBSLocationBuildingType> call() throws Exception {
					List<LBSLocationBuildingType> buildingType=filterMgmtcontroller.getBuildingType(request);
					LOGGER.debug(String.format("OUT Country Call[building list length is = %s]", buildingType!=null?buildingType.size():0));
					
					return buildingType;	
			}
			
		});
			
			localDBCall=executor.submit(new Callable<Boolean>(){
				public Boolean call()throws Exception{
					//DB Retrieval of Fields to be displayed
					UserFieldsViewSetting fieldSetting=preferenceMgmtController.retrievePopupSettings(request);

					//Db retireval for the values that are already saved in the database.
					String preferences=preferenceMgmtController.retrievePreferencesForOPS(request);
					form.setFilterPreferences(StringEscapeUtils.escapeJavaScript(preferences));
					
					//For canned queries
					form.setCannedQueries(filterMgmtcontroller.retrieveCannedQueriesList(request));
					
					if(fieldSetting!=null){
						LOGGER.debug("field Setting "+fieldSetting.getFieldsDisplayed());
						form.setDefaultFieldsView(fieldSetting.getFieldsDisplayed());
					}
					
					retrieveDropdownValues(request,model);
					
					return new Boolean(true);
				}
			});
			
			chkL5User=executor.submit(new Callable<Boolean>(){
				public Boolean call()throws Exception{
					checkL5User(request,form);
					return new Boolean(true);
				}
			});
			
			
			form.setCountryString(HTMLOutputGenerator.convertToOptions(countryListFuture.get(), LexmarkConstants.COUNTRY));
			form.setBuildingTypes(HTMLOutputGenerator.generateBuildingTypes(buildingListFuture.get()));
			LOGGER.debug(" chkL5User ="+chkL5User.get());
			LOGGER.debug(" localDBCall ="+localDBCall.get());
			LOGGER.debug("time taken==" +(System.currentTimeMillis()-time1));
			return true;
			}catch(Exception e){
				LOGGER.debug("Exception"+e.getMessage());
				return false;
			}finally{
				
			}
	}
	
	/**
	 * @param request 
	 * @param form 
	 */
	private void setURlUtils(PortletRequest request,FleetManagementForm form){
		//This method is for generating supports and download url
		//This is for Control panel url
		Locale locale=request.getLocale();
		String urlHost = PropertiesMessageUtil.getPropertyMessage(
				"com.lexmark.services.resources.hostConfig",
				"markDirectHost", Locale.US);
		String URLPart2 = "&lang=" + locale.getLanguage() + "&country=" +
                locale.getLanguage() + "_" + locale.getCountry() + "&partnumber=";
		form.setSupportDwnldURL(urlHost + "support" + URLPart2);
		
	}
	
	/**
	 * @param deviceIds 
	 * @param response 
	 */
	@ResourceMapping("retrieveDeviceURL")
	public void getImage(@RequestParam(value="deviceId") String deviceIds,ResourceResponse response){
		
		String[] splitDeviceID=deviceIds.split(",");
		StringBuffer imgUrls=new StringBuffer("[");
		for(String device:splitDeviceID){
			String partImage="";
			if(StringUtils.isNotBlank(device)){
				try {
					 partImage= URLImageUtil.getPartImageFromLocal(device);
				} catch (Exception e) {
					LOGGER.debug("Exception"+e.getMessage());
					LOGGER.error("[Exception occured while retireving the url ]");				
				} 
			}else{
				partImage="Not found";
			}
			
				imgUrls.append("\"");
				imgUrls.append(partImage);
				imgUrls.append("\",");
			
			
		}
		imgUrls.deleteCharAt(imgUrls.length()-1);
		imgUrls.append("]");
		writeResonse(response,imgUrls.toString());
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
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("retrieveDeviceBookmarked")
	public void getDeviceList(ResourceRequest request, ResourceResponse response){
		AssetListContract contract = ContractFactory.getAssetListContract(request);
		contract.setFavoriteFlag(true);
		contract.setLoadAllFlag(true);
		String DATEFORMAT = "MM/dd/yyyy" ;
	    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    final String utcTime = sdf.format(new Date());
		contract.setEntitlementEndDate(utcTime);
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));

		contract.setSessionHandle(crmSessionHandle);
		
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract,LOGGER);
		LOGGER.info("end printing lex loggger");
		AssetListResult deviceListResult=null;
		try{
			deviceListResult= orderSuppliesAssetService.retrieveAllDeviceList(contract);
		}catch(Exception e){
			LOGGER.debug("Exception"+e.getMessage());
			deviceListResult=new AssetListResult();
			LOGGER.error("[Exception occured while retrieving ASset]");
		}
		/*finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}*/
		List<Asset> assets=deviceListResult.getAssets();
		writeResonse(response,HTMLOutputGenerator.convertAsset(assets));
		
	}
	
	/**
	 * @param favoriteAssetId 
	 * @param favoriteFlag 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("updateUserFavoriteAsset")
	public void updateUserFavoriteAsset(@RequestParam("favoriteAssetId") String favoriteAssetId,
			@RequestParam("favoriteFlag") boolean favoriteFlag,
			ResourceRequest request, ResourceResponse response) throws Exception {
		sharedPortletController.updateUserFavoriteAsset(favoriteAssetId, favoriteFlag, request, response);
	}
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getAssetInformation")
	public void getAssetInformation(ResourceRequest request,ResourceResponse response){
		
		LBSAssetListContract contract = ContractFactory.getLBSAssetListContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));

		contract.setSessionHandle(crmSessionHandle);
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract,LOGGER);
		LOGGER.info("end printing lex loggger");
		LBSFloorPlanListResult result=null;

		try{
			result= lBSFloorPlanService.retrieveLBSFloorPlanAssetList(contract);
		}catch(Exception e){
			LOGGER.debug("Exception"+e.getMessage());
			result=new LBSFloorPlanListResult();
			LOGGER.error("[Exception occured while retrieving ASset]");
		}
		/*finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}*/
		
		String jsonAssetDetails=HTMLOutputGenerator.convertAssetDetails(result.getAssetList(),request.getLocale());		
		writeResonse(response, jsonAssetDetails);
		
	}
	/**
	 * @param request 
	 * @param form 
	 */
	private void getAccessForCreateRequest(PortletRequest request,FleetManagementForm form){
		HashMap<?,?> hMap= (HashMap<?,?>)request.getPortletSession().getAttribute("userAccessMapForSr" , PortletSession.APPLICATION_SCOPE);
		if(hMap==null){
			return;
		}
		String createChangeMgmtReq = (String)hMap.get("CREATE CHANGE MGMT REQUEST");
		String createSuppliesReq = (String)hMap.get("CREATE SUPPLIES REQUEST");
		String createServiceReq = (String)hMap.get("CREATE SERVICE REQUEST");
		String createHardwareReq = (String)hMap.get("CREATE HARDWARE REQUEST");
		
		if(createChangeMgmtReq !=null && "True".equalsIgnoreCase(createChangeMgmtReq)){
			form.setShowChangeMgmt(true);
		}
		if(createSuppliesReq !=null && "True".equalsIgnoreCase(createSuppliesReq)){
			form.setShowSupplies(true);
		}
		if(createServiceReq !=null && "True".equalsIgnoreCase(createServiceReq)){
			form.setShowService(true);
		}
		if(createHardwareReq !=null && "True".equalsIgnoreCase(createHardwareReq)){
			form.setShowHardware(true);
		}
		 
	}
	
	/**
	 * @param request 
	 * @param model 
	 */
	public void retrieveDropdownValues(PortletRequest request,Model model){
		
		
		
		
		Map<String, String> productSeries=null;
		//Map<String, String> productOwnership=null;
		Map<String, String> productType=null;
		Map<String, String> srType=null;
		Map<String, String> srStatus=null;
		Map<String, String> srArea=null;
		Map<String, String> srSubArea=null;
		Map<String, String> meterReadType=null;
		Map<String, String>assetLifeCycle=null;
		Map<String, String>devicePhase=null;
		Map<String, String>hwStatus=null;
		Map<String, String>srOPSStatus=null;
		Map<String, String>srSubStatus=null;
		Map<String, String>srSource=null;
		Map<String, String> alertCodes=null;
		Map<String, String> expiration=null;
		try {
			
			
			productSeries = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_PRODUCT_SERIES.getValue(), request.getLocale());
			//productOwnership = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_PRODUCT_OWNERSHIP.getValue(), request.getLocale());
			srType = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_SR_TYPE.getValue(), request.getLocale());
			srStatus = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), request.getLocale());
			srArea = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(), request.getLocale());
			srSubArea = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_SUB_AREA.getValue(), request.getLocale());
			
			srOPSStatus = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.OPS_SR_STATUS.getValue(), request.getLocale());
			srSubStatus = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.OPS_SR_SUBSTATUS.getValue(), request.getLocale());
			srSource = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.OPS_SR_SOURCE.getValue(), request.getLocale());
			
			meterReadType = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_METER_READ.getValue(), request.getLocale());
			productType=commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_PRODUCT_TYPE.getValue(), request.getLocale());
			
			//Added for ops
			assetLifeCycle=commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_ASSET_LIFECYCLE.getValue(), request.getLocale());
			devicePhase=commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_DEVICE_PHASE.getValue(), request.getLocale());
			hwStatus=commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_HARDWARE_STATUS.getValue(), request.getLocale());
			expiration = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_DEVICE_STATUS_EXPIRATION.getValue(), request.getLocale());
			alertCodes = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.LBS_DEVICE_STATUS_ALERTS.getValue(), request.getLocale());
			
		}catch (LGSDBException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Exception"+e.getMessage());
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
		}
	
		model.addAttribute("productSeries",productSeries);
		//model.addAttribute("productOwnership",productOwnership);
		model.addAttribute("srType",srType);
		model.addAttribute("srStatus",srStatus);
		model.addAttribute("srArea",srArea);
		model.addAttribute("srSubArea",srSubArea);
		model.addAttribute("meterReads",meterReadType);
		model.addAttribute("productType",productType);
		
		model.addAttribute("assetLifeCycle",assetLifeCycle);
		model.addAttribute("devicePhase",devicePhase);
		model.addAttribute("hwStatus",hwStatus);
		
		model.addAttribute("srOPSStatus",srOPSStatus);
		model.addAttribute("srSubStatus",srSubStatus);
		model.addAttribute("srSource",srSource);
		model.addAttribute("alertCodes",alertCodes);
		model.addAttribute("expiration",expiration);
		
		 	
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
			response.setContentType("text/javascript");
			out.write(val);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
	}
}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 * @throws LGSDBException 
	 */
	@RequestMapping(params = "action=viewDeviceHistoryPopup")
	public String viewDeviceHistoryPopup(Model model, RenderRequest request,
			RenderResponse response) throws Exception, LGSDBException {
		String METHOD_NAME = "viewDeviceHistoryPopup";
		LOGGER.debug(METHOD_NAME);
		String requestTypeStr=request.getParameter(ChangeMgmtConstant.REQUESTTYPESTR);
		if(request.getParameter("isTwoYearHistoryFlag").equalsIgnoreCase("true")){
			LOGGER.debug("insideif--->>>"+request.getParameter("isTwoYearHistoryFlag"));
			model.addAttribute("isTwoYearHistoryFlag", "true");
		}
		LOGGER.debug("isTwoYearHistoryFlag--->>>"+request.getParameter("isTwoYearHistoryFlag"));
		String gridType=null;
		String gridCreationId=null;
		if(requestTypeStr!=null && requestTypeStr!="")
		{
			if(requestTypeStr.equalsIgnoreCase(ChangeMgmtConstant.ALL_REQUESTS)){
				gridType=ChangeMgmtConstant.ALL_REQUESTS;
				gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[0];
			}else if(requestTypeStr.equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
				gridType=ChangeMgmtConstant.CHANGE_REQUESTS;
				gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[1];
			}else if(requestTypeStr.equalsIgnoreCase(ChangeMgmtConstant.SUPPLY_REQUESTS)){
				gridType=ChangeMgmtConstant.SUPPLY_REQUESTS;
				gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[2];
			}else if(requestTypeStr.equalsIgnoreCase(ChangeMgmtConstant.SERVICE_REQUESTS)){
				gridType=ChangeMgmtConstant.SERVICE_REQUESTS;
				gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[3];
			}
			//Hardware changes
			else if(requestTypeStr.equalsIgnoreCase(ChangeMgmtConstant.HARDWARE_REQUESTS)){
				gridType=ChangeMgmtConstant.HARDWARE_REQUESTS;
				gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[4];
			}
		}
		retrieveGridSetting(gridCreationId, request, response, model);
		 Map<String, String> requestStatusLOVMap =null;
			try {
				requestStatusLOVMap =  commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), request.getLocale());						
			}catch(LGSDBException e){
				LOGGER.debug("Exception"+e.getMessage());
				LOGGER.error("Unable to retrieve Status map");
				requestStatusLOVMap=new HashMap<String,String>();
			}
		model.addAttribute(ChangeMgmtConstant.GRIDTYPE, gridType);
		model.addAttribute("requestStatusLOVMap", requestStatusLOVMap);
		 //For MPS Phase 2.1
		return "fleetmanagement/deviceHistoryPopup";
		
		
		
	}
	
	
	/**
	 * @param request 
	 * @param form 
	 */
	private void checkL5User(PortletRequest request,FleetManagementForm form){
		PortletSession session=request.getPortletSession();
		form.setMdmId(PortalSessionUtil.getMdmId(session));
		String mdmLevel=PortalSessionUtil.getMdmLevel(session);
		form.setMdmLevel(mdmLevel);
		
		if(PortalSessionUtil.getChlNodeId(session)!=null){
			form.setMdmLevel("Siebel");//This needs to be hardcoded because its L5 user according to this value the json param will change.
			LBSCHLContract contract=ContractFactory.getLBSCHLContract(request);
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			try{
				LBSCHLListResult result=lBSCHLMappingService.retrieveCHLtList(contract);
				LBSCHLMapping chl=result.getChlAccount().get(0);
				
				form.setMdmId(chl.getL5AccoountId());
				form.setL5HeirarchyParentChain(chl.getHeirarchyParentChain());
				
				for(LBSCHLMapping mapping:result.getChlAccount()){
					LOGGER.debug("printing mapping details" +mapping.getL5AccoountId() + "  jjj "+ mapping.getHeirarchyParentChain());					
				}
			}catch(Exception exception){
				LOGGER.debug("Exception"+exception.getMessage());
				LOGGER.debug("[ Exception occured in chl service]");
			}
			
		}
		
		
		
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

	/*Changes for CR 17284*/
	/**
	 * Set selected Zoom level info to session
	 * @param resourceRequest 
	 * @param resourceResponse 
	 */
	@ResourceMapping(value="setZoomLevelToSession")
	public void setZoomLevelToSession(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		PortletSession portletSession = resourceRequest.getPortletSession();
		String zoomLevelInfo=resourceRequest.getParameter("zoomLevelInfo");
		LOGGER.debug("Inside set Zoom Level");
		LOGGER.debug("zoomLevelInfo is "+zoomLevelInfo);
		portletSession.setAttribute("zoomLevelInfo", zoomLevelInfo ,PortletSession.APPLICATION_SCOPE);
		String zoomInfo = (String)portletSession.getAttribute("zoomLevelInfo",PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("zoomLevelInfo from session is "+zoomInfo);
		try{
			PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setContentType("text/plain");
			out.print("success");
			out.flush();
			out.close();
		}catch(IOException ie){
			LOGGER.debug("Exception"+ie.getMessage());
			LOGGER.error("IOException occured");
		}
	}
	/*End Change for CR 17284*/
	
	
	
	
	
	
}
