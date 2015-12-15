/**
 * Copyright@ 2012. This document has been created & written by 
 * Wipro technologies for Lexmark and this is copyright to Lexmark 
 *
 * File         	: DeviceFinderController 
 * Package     		: com.lexmark.services.portlet 
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments 
 * ---------------------------------------------------------------------
 * Sourav 						 		             Modified/Added Methods for MPS 
 *
 */
package com.lexmark.services.portlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.service.api.ContactService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.DeviceDetailForm;
import com.lexmark.services.form.ServiceRequestConfirmationForm;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ControllerUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ResourceResponseUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.services.util.URLImageUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.DownloadFileUtil;
import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.StringUtil;
import com.lexmark.util.report.PdfPReportGenerator;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Wipro 
 * @version 1.0 
 *
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes("serviceRequestConfirmationForm")
public class DeviceFinderController extends BaseController {
	private static final String IMAGE_NOT_FOUND = "Not found";
	private static LEXLogger logger = LEXLogger.getLEXLogger(DeviceFinderController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static final String METH_SHOWDEVICELISTPAGE ="showDeviceListPage";
	private static final String METH_OPENPAGECOUNTPOPUP ="openPageCountPopUp";
	private static final String METH_UPDATEPAGECOUNTS ="updatePageCounts";
	private static final String METH_RETRIEVEHISTORYLISTBYASSETID ="retrieveHistoryListByAssetId";
	private static final String METH_GETLTPCVALUES ="getLtpcValues";
	private static final String METH_RETRIEVEDEVCELIST = "retriveDeviceList";
	private static final String METH_SHOWDEVICEDTLPG = "showDeviceDetailPage";
	private static final String METH_GETLOVMAP = "getLovAsMap";
	private static final String METH_SHOWPRINTLSTPG = "showPrintDeviceListPage";
	private static final String METH_SHOWPRNTDEVICEPG = "showPrintDevicePage";
	private static final String METH_DEVICELOCTREEURL = "retrieveDeviceLocationTreeXML";
	@Autowired
	private ContactService contactService;
	@Autowired
	private SharedPortletController sharedPortletController;
	@Autowired
	private ServiceRequestService serviceRequestService;	
    @Autowired
    private GlobalService  globalService;
    @Autowired
    private ProductImageService  productImageService;
	@Autowired
	private RequestTypeService requestTypeService;
	@Autowired
	private CommonController commonController;
	@Autowired
	private OrderSuppliesAssetService orderSuppliesAssetService;
	//brmandal added for page count pop up
	@Autowired
	private PageCountsController pageCountsController;
	@Autowired
    private ServiceRequestLocale serviceRequestLocaleService;
	
	
	
	/**
	 * This method returns the device finder page, retrieves the grid settings, checks the roles for MPS
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showDeviceListPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		logger.enter(this.getClass().getSimpleName(), METH_SHOWDEVICELISTPAGE);
		
		//Changes for Back From Detail History page
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		if(httpReq.getParameter(ChangeMgmtConstant.BACKFROMHISTORY)!=null && ChangeMgmtConstant.TRUE.equalsIgnoreCase(httpReq.getParameter(ChangeMgmtConstant.BACKFROMHISTORY))){
			return showDeviceDetailPage(request, response,model);
		}
		
		//Ends	

		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID(ChangeMgmtConstant.DWNLD_DEVICELIST_URL);
		
		PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
		PortletSession portletSession = portletRequest.getPortletSession();
        if(portletSession.getAttribute(ChangeMgmtConstant.SEARCH_VALUE, PortletSession.APPLICATION_SCOPE) != null){
        	model.addAttribute(ChangeMgmtConstant.SEARCH_NAME, String.valueOf(portletSession.getAttribute(ChangeMgmtConstant.SEARCH_NAME, PortletSession.APPLICATION_SCOPE)));
            model.addAttribute(ChangeMgmtConstant.SEARCH_VALUE, String.valueOf(portletSession.getAttribute(ChangeMgmtConstant.SEARCH_VALUE, PortletSession.APPLICATION_SCOPE)));
		}
        
		model.addAttribute(ChangeMgmtConstant.DWNLD_DEVICELIST_URL, resURL.toString());
		
		retrieveGridSetting(ChangeMgmtConstant.DEVICELIST_GRID,request,response,model);
		
		//Add Device Location tree
		model.addAttribute(ChangeMgmtConstant.DEVICELOCTREE_URL, sharedPortletController.getDeviceLocationXMLURL(response));
		//Add CHL node tree
		model.addAttribute(ChangeMgmtConstant.CHLTREE_URL, sharedPortletController.getCHLTreeXMLURL(response));
		
		/******** Below section is added for role based access in grid - create new request link *********/
		//try{
		//Changes for Create a request Link Asset Entitlement / Catalog Entitlement
	//	commonController.retrieveReqHistoryAccess4Portal(request,response);
		//Ends
		//commonController.checkUserRoles(request.getPortletSession(), request);
		//}catch(Exception ex)
		//{
			//String errorMessage = ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.servicerequest.retrieveDeviceList", null, request.getLocale());			
			//throw new Exception(errorMessage+"^"+ex.getMessage());
		//}
		
		//Changes for Back to deviceDetail
		if(request.getParameter("backToList")!=null && "true".equalsIgnoreCase(request.getParameter("backToList"))){
			model.addAttribute("backToList", "true");
			request.removeAttribute("backToList");
		}
		
		 /*Changes for CR 17284*/
		PortletSession session  = request.getPortletSession();
		if(session.getAttribute("zoomLevelInfo",PortletSession.APPLICATION_SCOPE)!=null){
			String zoomLevelInfo = (String)session.getAttribute("zoomLevelInfo",PortletSession.APPLICATION_SCOPE);
			model.addAttribute("zoomLevelInfo",zoomLevelInfo);
		}
		/*End Change for CR 17284*/
		logger.exit(this.getClass().getSimpleName(), METH_SHOWDEVICELISTPAGE);
		return "deviceFinder/deviceFinder";
	}
	
	/**
	 * Resource Mapped method, retrieves Grid Data for MPS to be shown in Device Finder page
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping(value="retrieveDeviceListinDM")
	public void retriveDeviceList(ResourceRequest request, ResourceResponse response) throws Exception{
		logger.enter(this.getClass().getSimpleName(), METH_RETRIEVEDEVCELIST);
		AssetListContract contract = ContractFactory.getAssetListContract(request);
		String lbsFilterCriteriaFlag=contract.getFilterCriteria().get("lbsAddresFlagValue")!=null?contract.getFilterCriteria().get("lbsAddresFlagValue").toString():"";
		if(!lbsFilterCriteriaFlag.equalsIgnoreCase("")){
			contract.setLbsFilterCriteriaFlag(lbsFilterCriteriaFlag);
			contract.getFilterCriteria().remove("lbsAddresFlagValue");
		}
		ContractFactory.printContractDetail(contract);
				
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try
		{
			contract.setSessionHandle(crmSessionHandle);

			String viewType = request.getParameter(ChangeMgmtConstant.VIEWPARAM);
			if (ChangeMgmtConstant.BOOKMARKED.equalsIgnoreCase(viewType)){
				contract.setFavoriteFlag(true);
			} else{
				contract.setFavoriteFlag(false);
			}
			
			if (LexmarkConstants.VIEWTYPE_MANAGED_DEVICES.equalsIgnoreCase(viewType)){
				contract.setAssetType(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES);
			}
			else if(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES.equalsIgnoreCase(viewType)){
				contract.setAssetType(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES);
			} 
			
			//Always set this so that favorites can be identified
			String contactId = PortalSessionUtil.getContactId(request.getPortletSession());
			contract.setContactId(contactId);			
			loadFilterCriteria(request, contract);
			// Prepare downLoadContract into session for downloading
			session.setAttribute("downLoadContract", contract);

			PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
			PortletSession portletSession = portletRequest.getPortletSession();
			portletSession.removeAttribute(ChangeMgmtConstant.SEARCH_NAME,PortletSession.APPLICATION_SCOPE);
			portletSession.removeAttribute(ChangeMgmtConstant.SEARCH_VALUE,PortletSession.APPLICATION_SCOPE);
		   
			String DATEFORMAT = "MM/dd/yyyy" ;
		    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		    final String utcTime = sdf.format(new Date());
				contract.setEntitlementEndDate(utcTime);
			contract.setEntitlementEndDate(utcTime);
			logger.debug("The flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			logger.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,logger);
			logger.info("end printing lex loggger");
			
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveDeviceList", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			Long startTime = System.currentTimeMillis();
			
			AssetListResult deviceListResult = orderSuppliesAssetService.retrieveAllDeviceList(contract);
			logger.logTime("** MPS PERFORMANCE TESTING RETRIEVE ALL DEVICE LIST FOR DEVICE FINDER ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEALLDEVICELIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			PerformanceTracker.endTracking(lexmarkTran);
			List<Asset>  deviceList = deviceListResult.getAssets();	
			String partImage;
			Long startTimeImage = System.currentTimeMillis();
			for (Asset device : deviceList) {
				
				
				partImage="";
				
				// Performance changes for retreving image file from local in device finder
				
				partImage = URLImageUtil.getPartImageFromLocal(device.getProductTLI());
				
				device.setProductImageURL(partImage);
				sharedPortletController.assembleDevice(device,
						request.getLocale());
			}
			logger.logTime("** MPS PERFORMANCE TESTING RETRIEVE IMAGE RETRIVAL FOR DEVICE FINDER ==>: " + (System.currentTimeMillis()- startTimeImage)/1000.0);
			String content = getXmlOutputGenerator(
					request.getLocale()).convertAssetToXML(request,deviceList,
					deviceListResult.getTotalCount(),
					contract.getStartRecordNumber(),
					request.getContextPath(),
					PortalSessionUtil.getContactId(session),
			"");

			PrintWriter out = response.getWriter();
			response.setContentType(ChangeMgmtConstant.CONTENTTYPEXML);
			out.print(content);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.exit(this.getClass().getSimpleName(), METH_RETRIEVEDEVCELIST);
	}

	/**
	 * Show advanced search page. 
	 * @param request 
	 * @param model 
	 * @return targeted page 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showDeviceAdvancedSearchPage")
	public String showDeviceAdvancedSearchOptionPage(RenderRequest request, Model model) throws Exception{
		sharedPortletController.retrieveAdvanceSearchData(request, model);
		return "deviceAdvancedSearch";
		
	}
	/**
	 * This method let us navigates to the Device Details page for MPS
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=viewDeviceDetail")
	public String showDeviceDetailPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		logger.enter(this.getClass().getSimpleName(), METH_SHOWDEVICEDTLPG);
		try{
			AssetContract contract = ContractFactory.getAssetContract(request);
			
			
			//Changes for Back From Detail History page
			String assetId=null;
		    String isDeviceBookmarked=null;
			//Resource URL created for CI BRD 14-02-01
		    ResourceURL resURL = response.createResourceURL();
			resURL.setResourceID("downloadRequestListURL");
			//Resource URL created for CI BRD 14-02-01 ends
			
			HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
			if(httpReq.getParameter(ChangeMgmtConstant.BACKFROMHISTORY)!=null && ChangeMgmtConstant.TRUE.equalsIgnoreCase(httpReq.getParameter(ChangeMgmtConstant.BACKFROMHISTORY))){
				assetId = httpReq.getParameter(ChangeMgmtConstant.ASSETROWID);
				isDeviceBookmarked = httpReq.getParameter(ChangeMgmtConstant.ISDEVICE_BOOKMARKED);
				contract.setAssetId(assetId);
				model.addAttribute(ChangeMgmtConstant.BACKFROMHISTORY,httpReq.getParameter(ChangeMgmtConstant.BACKFROMHISTORY));
				model.addAttribute(ChangeMgmtConstant.LINKCLICKED,httpReq.getParameter(ChangeMgmtConstant.LINKCLICKED));					
			}else{
				assetId = request.getParameter(ChangeMgmtConstant.ASSETROWID);
				isDeviceBookmarked = request.getParameter(ChangeMgmtConstant.ISDEVICE_BOOKMARKED);				
			}
			//Ends
			contract.setPageName(ChangeMgmtConstant.DEVICE_DETAIL);
			logger.info("start printing lex logger");
			ObjectDebugUtil.printObjectContentForDM(contract, logger);
			logger.info("end printing lex loggger");			
			if (assetId == null) {
				throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.deviceFinder.assetIdException", null, request.getLocale()));
			}
			PortletSession session = request.getPortletSession();
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveDeviceDetail", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			Long startTime = System.currentTimeMillis();
		
			AssetResult deviceResult = orderSuppliesAssetService.retrieveDeviceDetail(contract);
			logger.logTime("** MPS PERFORMANCE TESTING RETRIEVE Device DETAIL ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEDEVICEDETAIL, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			PerformanceTracker.endTracking(lexmarkTran);
			
			ProductImageContract productImageContract = new ProductImageContract();
			productImageContract.setPartNumber(deviceResult.getAsset().getProductTLI());
			long timeBeforeCall=System.currentTimeMillis();
			ProductImageResult productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEPRODUCTIMAGEURL, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_PORTALIMAGEDB,productImageContract);
			logger.info("start printing lex logger");
			
			logger.info("end printing lex loggger");

			deviceResult.getAsset().setProductImageURL(productImageResult.getProductImageUrl());
			
			if(!StringUtil.isEmpty(isDeviceBookmarked)){				
			deviceResult.getAsset().setUserFavoriteFlag(Boolean.valueOf(isDeviceBookmarked));
			}
	
			sharedPortletController.assembleDevice(deviceResult.getAsset(), request.getLocale());
			DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
			deviceDetailForm.setDevice(deviceResult.getAsset());
			if (deviceResult.getAsset().isConsumableAssetFlag()!=null) {
				deviceDetailForm.setConsumableAssetFlag(deviceResult.getAsset().isConsumableAssetFlag());
			}
			//Added for CI BRD 14-02-06 -- STARTS
			if(deviceResult.getAsset().getInstallDate()!=null){
				deviceDetailForm.setInstallDate(DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(deviceResult.getAsset().getInstallDate()));
			}
			//Added for CI BRD 14-02-06 -- ENDS
			String createServiceRequestFlag = (String) request.getPortletSession().getAttribute(ChangeMgmtConstant.CREATESERVICEREQFLAG);
			if("true".equals(createServiceRequestFlag)){
				deviceDetailForm.setCreateServiceRequestFlag(true);
			}
			
			//------------------- 7189 Start Independent--------------------	
			Map<String, String> requestAccessMap= (Map<String, String>)session.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);
			String createServiceReq = getEntitlementStatus(request, "CREATE SERVICE REQUEST");
			if(createServiceReq != null && createServiceReq.equalsIgnoreCase("False")){
				if (requestAccessMap == null || requestAccessMap.isEmpty()){
					requestAccessMap = new HashMap<String, String>();
					requestAccessMap.put("CREATE SERVICE REQUEST", "False");
				}else{
					if(requestAccessMap .get("CREATE SERVICE REQUEST")!=null && !(requestAccessMap .get("CREATE SERVICE REQUEST").equalsIgnoreCase("True"))){			//modified as per defect #14967			
						requestAccessMap.put("CREATE SERVICE REQUEST", "False");
					}
				}
			}				
			session.setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, requestAccessMap, PortletSession.APPLICATION_SCOPE);
			//------------------- 7189 End--------------------
			
			// Retrieve Grid Setting			
			retrieveGridSettingForDMHistory("gridContainerDiv_all_request_types",request,response,model);
			retrieveGridSettingForDMHistory("gridContainerDiv_supplies_requests",request,response,model);
			retrieveGridSettingForDMHistory("gridContainerDiv_change_requests",request,response,model);
			retrieveGridSettingForDMHistory("gridContainerDiv_service_requests",request,response,model);
						
			model.addAttribute("deviceDetailForm", deviceDetailForm);
			
			/******Below section is for retrieving siebel lov list for request type and status *****/
			long timeBeforeServiceCall=System.currentTimeMillis();
			SiebelLOVListResult requestTypeLOVList = globalService.retrieveSiebelLOVList(ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.REQUEST_TYPE.getValue(), null,null));
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVESIEBELLOVLIST, timeBeforeServiceCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.REQUEST_TYPE.getValue(), null,null));
			logger.info("start printing lex logger");
			logger.logTime("** MPS PERFORMANCE TESTING RETRIEVE SIEBEL LOV LIST ==>: " + (System.currentTimeMillis()- timeBeforeServiceCall)/1000.0);
			logger.info("end printing lex loggger");
			
			if(requestTypeLOVList != null){
		
				Map<String, String> requestTypeLOVMap = getLovAsMap(requestTypeLOVList);

				model.addAttribute("requestTypeLOVMap", requestTypeLOVMap);
			}
			//LEX:AIR00072240 :to Fetching data from DB
		 
         Map<String, String> requestStatusLOVMap = ControllerUtil.retrieveLocalizedLOVMap(
					SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), null, serviceRequestLocaleService,
					request.getLocale());
	     model.addAttribute("requestStatusLOVMap", requestStatusLOVMap);
         //END ***LEX:AIR00072240
			/******End of the section for retrieving siebel lov list for request type and status *****/
			/******Added for role based access ********/
			boolean hidePgCnts = true;
			List<String> userRoleList = PortalSessionUtil.getUserRoles(request.getPortletSession());
			
			if(userRoleList!=null && (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_SUPPORT))){			
				hidePgCnts = false;
			}
			//Defect 9929 :: Added for change in Amind mapping for page counts data 
			for(PageCounts pageCount : deviceDetailForm.getDevice().getPageCounts()){
				if("ltpc".equalsIgnoreCase(pageCount.getName())){
					deviceDetailForm.getDevice().setLastPageCount(pageCount.getCount());
					
					if(pageCount.getDate() != null){
						deviceDetailForm.getDevice().setLastPageReadDate(new Date(pageCount.getDate()));
					}
				}else if("color".equalsIgnoreCase(pageCount.getName())){
					deviceDetailForm.getDevice().setColorCapableFlag(true);
					deviceDetailForm.getDevice().setLastColorPageCount(pageCount.getCount());
					
					if(pageCount.getDate() != null){
						deviceDetailForm.getDevice().setLastColorPageReadDate(new Date(pageCount.getDate()));
					}
										
				}
			}
			
			// added for pagecounts start
			Map<String, String> pageCountsMap=null;
			try {
				pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), request.getLocale());
				logger.debug("pageCountsMap = "+ pageCountsMap);
				
			} catch (LGSDBException e) {
				logger.debug("catch"+ e.getMessage());
			}
			String pageCountsString = getXmlOutputGenerator(request
					.getLocale()).pageCountsXML(deviceDetailForm.getDevice().getPageCounts(),deviceDetailForm.getDevice().getPageCounts().size(),0,pageCountsMap);
			pageCountsString = pageCountsString.replace("\n", "");
			model.addAttribute("pageCountsString", pageCountsString);
			
			// added for pagecounts end
			
			
			model.addAttribute("hidePgCnts", hidePgCnts);
			//Resource URL Added for CI BRD 14-02-01
			model.addAttribute("downloadRequestListURL", resURL.toString());
			logger.exit(this.getClass().getSimpleName(), METH_SHOWDEVICEDTLPG);
			return ChangeMgmtConstant.DEVICEDETAILS_PATH;  
			
		}catch(Exception e){
			logStackTrace(e);
			String errorMessage = ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.unableToRetrieveDeviceDetail", null, request.getLocale());			
			request.setAttribute("errorAttribute", errorMessage);
			logger.exit(this.getClass().getSimpleName(), METH_SHOWDEVICEDTLPG);
			/***If error, return to the deviceFinder jsp***/
			return ChangeMgmtConstant.DEVICEFINDER_PATH;
		}
	}
	
	/**
	 * This is done to get lov as map in device mgmt history
	 * @param requestStatusLOVList 
	 * @return Map 
	 */
	private Map<String, String> getLovAsMap(
			SiebelLOVListResult requestStatusLOVList) {
		logger.enter(this.getClass().getSimpleName(), METH_GETLOVMAP);
		if(requestStatusLOVList == null || requestStatusLOVList.getLovList() == null || requestStatusLOVList.getLovList().size()==0){
			logger.warn("SiebelLOVListResult object is null or No LOV is populated in the object !");
			return null;
		}
		Map<String, String> lovMap = new LinkedHashMap<String, String>();
		for(ListOfValues lovObj : requestStatusLOVList.getLovList()){
			lovMap.put(lovObj.getName(), lovObj.getValue());
		}
		logger.exit(this.getClass().getSimpleName(), METH_GETLOVMAP);
		return lovMap;
	}

	/**
	 * This method let us navigates to the Service Request DrillDown page for MPS
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=serviceRequestDrillDown")
	public String showServiceRequestDetailPage(RenderRequest request, RenderResponse response, Model model)
		throws Exception{
		sharedPortletController.showServiceRequestDrillDownPage(
				request, response, model);
		return "serviceRequest/serviceRequestDrillDown";
	}

	/**
	 * This method let us navigates to the Service Request DrillDown Print page for MPS
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showServiceRequestDrillDownPrintPage")
	public String showServiceRequestDrillDownPrintPage() throws Exception{
		return "serviceRequest/serviceRequestDrillDownPrint";
	}
	
	/**this 6 mapping is for Request Service
	 * @return String 
	 * @throws Exception  
	 */
	@RequestMapping(params = "action=showServiceRequestConfirmation")
	public String showServiceRequestConfirmationPage()throws Exception{
		
		return "serviceRequest/thankYou";		
	}
	
	/**
	 * This method let us navigates to the Service Request Thank You Email page for MPS
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=thankYouEmail")
	public String showThankYouEmailPage()throws Exception{
		return "serviceRequest/thankYouEmail";		
	}
	
	/**
	 * This method let us navigates to the Service Request Thank You Print page for MPS
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=thankYouPrint")
	public String showThankYouPrintPage() throws Exception{
		return "serviceRequest/thankYouPrint";
	}
	
	/**
	 * This method let us navigates to the Service Request Page2 for MPS
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param assetId 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping (params = "action=showServiceRequestPage2")
	public String showServiceRequestSubmitPage2(RenderRequest request,
			RenderResponse response,@RequestParam("assetId") String assetId,
				Model model) throws Exception {
		sharedPortletController.showServiceRequestSubmitPage2(request, response, assetId, model);
		
		// the asset is not entitled, process the situation like not my printer
		if (model.containsAttribute("notEntitledFlag")) {
			sharedPortletController.showServiceRequestSubmitPage2FromDeviceNotFoundPage(
					request, response, assetId, null, null, null, model);
		}
		return "serviceRequest/serviceRequestPage2";
	}
	
	/**
	 * This method let us navigates to the Service Request Create New Contact/Address Page for MPS
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param gridName 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showCreateNewPage")
	public String showCreateNewPage(RenderRequest request,
			RenderResponse response, @RequestParam("gridName") String gridName,
			Model model)throws Exception {
		return sharedPortletController.showCreateNewPage(request, response, gridName, model);
	}
	
	/**
	 * This method let us navigates to the Service Request Create New Contact/ServiceAddress Page for MPS
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param gridName 
	 * @param contactId 
	 * @param firstName 
	 * @param workPhone 
	 * @param emailAddress 
	 * @param lastName 
	 * @return String 
	 */
	@RequestMapping(params = "action=showEditPage")
	public String showEditContactPage(RenderRequest request,
			RenderResponse response, @RequestParam("gridName") String gridName, @RequestParam("contactId") String contactId,
			@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName, 
			@RequestParam("workPhone") String workPhone, @RequestParam("emailAddress") String emailAddress,
			Model model) {
		String returnPage = sharedPortletController.showEditContactPage(request, response, gridName, contactId, lastName, firstName, workPhone, emailAddress, model);
		return returnPage;
	}
	
	/**
	 * This method let us submit a Service Request
	 * @param request 
	 * @param response 
	 * @param serviceRequestConfirmationForm 
	 * @param result 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping (params = "action=submitServiceRequest")
	public void submitServiceRequest(ActionRequest request, ActionResponse response,
			@ModelAttribute("serviceRequestConfirmationForm") ServiceRequestConfirmationForm
			serviceRequestConfirmationForm, BindingResult result,
			Model model) throws Exception {	
		model.addAttribute("serviceRequestType", "deviceFinderServiceRequest");
		sharedPortletController.submitServiceRequest(request, response, serviceRequestConfirmationForm, result, model);
		response.setRenderParameter("action", "showServiceRequestConfirmation");	    
	}
	
	/**
	 * This method let us retrieve Primary Contact List URL
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="contactListURL")
	public void retrievePrimaryContactListURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{			
		sharedPortletController.retrievePrimaryContactListURL(request, response, model);
	}
	
	/**
	 * This method let us Retrieve Service AddressList URL
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="serviceAddressListURL")
	public void retrieveServiceAddressListURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		sharedPortletController.retrieveServiceAddressListURL(request, response, model);
	}
	
	/**
	 * This method let us Retrieve Secondary Contact List URL
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="secContactListURL")
	public void retrieveSecondaryContactListURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{			
		sharedPortletController.retrieveSecondaryContactListURL(request, response, model);
	}
	
	/**
	 * This method let us navigate to Service Request Confirmation Print Page
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showServiceRequestConfirmationPrintPage")
	public String showServiceRequestConfirmationPrintPage() throws Exception{
		return "serviceRequest/serviceRequestConfirmationPrint";
	}
	
	/**
	 * This method let us retrieve Associated Service Ticket List
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="retrieveAssociatedServiceTicketList")
	public void retrieveAssociatedServiceTicketList(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{
		sharedPortletController.retrieveAssociatedServiceTicketList(request, response, model);
	}
	
	/**
	 * This method let us retrieve Service Request History List
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="retrieveServiceRequestHistoryList")
	public void retrieveServiceRequestHistoryList(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{
		sharedPortletController.retrieveServiceRequestHistoryList(request, response, model);
	}
	
	/**
	 * This method let us get State
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("getState")
	public void getState(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		sharedPortletController.getState(request, response, model);
	}
	
	/**
	 * Modified For Device Management Print Functionality for MPS ---- Device Finder Grid
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printDeviceList")
	public String showPrintDeviceListPage() throws Exception{
		logger.enter(this.getClass().getSimpleName(), METH_SHOWPRINTLSTPG);
		logger.exit(this.getClass().getSimpleName(), METH_SHOWPRINTLSTPG);
		return ChangeMgmtConstant.DEVICE_LIST_PRNT_PATH;
	}
	
	/**
	 * Added For Device Management Print Functionality for MPS ------ Details Page
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printDeviceDetailList")
	public String showPrintDevicePage() throws Exception{
		logger.enter(this.getClass().getSimpleName(), METH_SHOWPRNTDEVICEPG);
		logger.exit(this.getClass().getSimpleName(), METH_SHOWPRNTDEVICEPG);
		return ChangeMgmtConstant.DEVICE_DTLS_PRNT_PG;
	}
	//Added for 14-02-01
	/**
	 * Added For Device Management Print Functionality for MPS ------ Details Page
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printDeviceDetailReqList")
	public String showPrintDeviceReqPage() throws Exception{
		logger.enter(this.getClass().getSimpleName(), METH_SHOWPRNTDEVICEPG);
		logger.exit(this.getClass().getSimpleName(), METH_SHOWPRNTDEVICEPG);
		return ChangeMgmtConstant.DEVICE_DTLS_REQ_PRNT_PG;
	}
	
	/**
	 * This method let us navigate to the Control Panel Page for MPS
	 * @param request 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=gotoControlPanel")
	public String gotoControlPanel(RenderRequest request,
			Model model) throws Exception {
		model.addAttribute("controlPanelURL",request.getParameter("controlPanelURL"));
		model.addAttribute("pageName", "Device Finder");
		return "controlPanelPage";
	}

	/**
	 *  retrieve Device List and down load it csv and pdf format.
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("downloadDeviceListURL")
	public void downloadDeviceListURL(ResourceRequest request,
			ResourceResponse response) throws Exception{
		String downloadType = request.getParameter("downloadType");
		String gVPage =request.getParameter("gVPage");
		logger.debug("gVPage"+gVPage);
		Locale locale = request.getLocale();
		PortletSession session = request.getPortletSession();
		AssetListContract contract =  (AssetListContract) session.getAttribute("downLoadContract");
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			String DATEFORMAT = "MM/dd/yyyy" ;
		    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		    final String utcTime = sdf.format(new Date());
				contract.setEntitlementEndDate(utcTime);
		
			contract.setSessionHandle(crmSessionHandle);
			//Setting flag as true for PDF Download
			contract.setDownloadCall(true);
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveDeviceList", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			logger.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, logger);
			logger.info("end printing lex loggger");
			Long startTime = System.currentTimeMillis();
			logger.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			AssetListResult deviceListResult = orderSuppliesAssetService.retrieveAllDeviceList(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEALLDEVICELIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			logger.logTime("** MPS PERFORMANCE TESTING RETRIEVE all device list ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			List<Asset> deviceList = deviceListResult.getAssets();
			
			PerformanceTracker.endTracking(lexmarkTran);
			if("csv".equals(downloadType)){
				downloadDeviceListCSV(response,deviceList,locale,gVPage);
			}else if("pdf".equals(downloadType)){
				
				downloadDeviceListPDF(response,deviceList,locale,gVPage);
				logger.debug("downloadDeviceListPDF");
			}else{
				
				throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.portlet.downloadException", null, request.getLocale()));
			}
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
			logger.debug("-------------download device list end---------");//Added for 14-02-01
		}
	}
	//Added for CI BRD 14-02-01 --STARTS
		/**
		 *  retrieve Request History List and down load it csv and pdf format.
		 * @param request
		 * @throws Exception
		 */
		@ResourceMapping("downloadRequestListURL")
		public void downloadRequestListURL(ResourceRequest request,
				ResourceResponse response, Model model) throws Exception{
			logger.enter(this.getClass().getSimpleName(), "downloadRequestListURL");
			String downloadType = request.getParameter("downloadType");
			String requestType = request.getParameter("pageType");
			Locale locale = request.getLocale();
			PortletSession session = request.getPortletSession();
			RequestListContract contract =  (RequestListContract) session.getAttribute("downloadRequestListContract");
			//Added clone session for repeat download
			session.setAttribute("downloadRequestListContract", getContractClone(contract));
			contract.setStartRecordNumber(0);
			contract.setIncrement(Integer.valueOf(MINUES_ONE));
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			try {
				String DATEFORMAT = "MM/dd/yyyy" ;
			    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
			    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			    final String utcTime = sdf.format(new Date());
					contract.setEntitlementEndDate(utcTime);
				contract.setSessionHandle(crmSessionHandle);
				logger.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
				contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
				logger.info("start printing lex logger");
				ObjectDebugUtil.printObjectContent(contract, logger);
				logger.info("end printing lex loggger");
				LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveDeviceList", 
						PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
		
				Long startTime = System.currentTimeMillis();
				RequestListResult serviceRequestListResult=requestTypeService.retrieveRequestList(contract);
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEREQUESTLIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
				logger.logTime("** MPS PERFORMANCE TESTING RETRIEVE all request list ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
				List<ServiceRequest> serviceRequestList = serviceRequestListResult.getRequestList();
				logger.debug(" request list size in download is ---------> " +serviceRequestList.size());
				float timezoneOffset = 0;
				if(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)!= null){
					 timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
				}
				PerformanceTracker.endTracking(lexmarkTran);
				
				if("csv".equals(downloadType)){
					downloadRequestListCSV(response,serviceRequestList,locale,requestType,timezoneOffset);
				}else if("pdf".equals(downloadType)){
					downloadRequestListPDF(response,serviceRequestList,locale,requestType);
				}else{
					
					throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.portlet.downloadException", null, request.getLocale()));
				}
				
			}finally {
				globalService.releaseSessionHandle(crmSessionHandle);
			}
			
			logger.exit(this.getClass().getSimpleName(), "downloadRequestListURL");
		}
		private void downloadRequestListCSV(ResourceResponse response, List<ServiceRequest> requestList,
				Locale locale, String requestType,float timeZoneOffset) throws IOException {
			String fileName = null;
			String csvFileHeader = "";
			logger.debug("downloadRequestListCSV timeZoneOffset-->>"+timeZoneOffset);
			if("gridContainerDiv_all_request_types".equals(requestType))
			{
				fileName = DownloadFileLocalizationUtil
						.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
								ChangeMgmtConstant.ALLHISTORYDOWNLOADFILENAME,locale);
						csvFileHeader=  ChangeMgmtConstant.allHistoryCSVHeader;
			}
			else if("gridContainerDiv_change_requests".equals(requestType))
			{
				fileName = DownloadFileLocalizationUtil
						.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
								ChangeMgmtConstant.CMHISTORYDOWNLOADFILENAME,locale);
						csvFileHeader=  ChangeMgmtConstant.cmHistoryCSVHeader;
			}
			else if("gridContainerDiv_supplies_requests".equals(requestType))
			{
				fileName = DownloadFileLocalizationUtil
				.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
						ChangeMgmtConstant.SUPPLYHISTORYDOWNLOADFILENAME,locale);
				csvFileHeader=  ChangeMgmtConstant.supplyHistoryCSVHeader;
				
			}
			else if("gridContainerDiv_service_requests".equals(requestType))
			{
				fileName = DownloadFileLocalizationUtil
						.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
								ChangeMgmtConstant.SERVICEREQHISTORYDOWNLOADFILENAME,locale);
						csvFileHeader=  ChangeMgmtConstant.serviceReqHistoryCSVHeader;
			}
			else{
				fileName = DownloadFileLocalizationUtil
						.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
								"Default.Download.FileName",locale);
						csvFileHeader=  ChangeMgmtConstant.DefaultHistoryCSVHeader;
			}
				
			response.setProperty("Content-disposition", "attachment; filename="
					+ fileName);
			response.setContentType("text/csv");
			PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
			
			out.print(DownloadFileUtil.createRequestListCSV(requestList, locale, csvFileHeader,requestType,timeZoneOffset));
			out.flush();
			out.close();
		}
		private void downloadRequestListPDF(ResourceResponse response, List<ServiceRequest> serviceRequestList,
				Locale locale, String requestType) throws IOException {
			String[] cmHistoryListHeader = null;
			String fileName = "";
			PdfPReportGenerator generator = null;
			if("gridContainerDiv_all_request_types".equals(requestType))
			{
				fileName = DownloadFileLocalizationUtil
						.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
								ChangeMgmtConstant.ALLHISTORYDOWNLOADFILENAME,locale);
				cmHistoryListHeader=DownloadFileLocalizationUtil
						.getLocalizedFileHeader(ChangeMgmtConstant.allHistoryPDFHeader,
								locale).split(",");
				generator = new PdfPReportGenerator(
						cmHistoryListHeader, ChangeMgmtConstant.ALLHISTORYCOLUMNS,
						serviceRequestList);
			}
			else if("gridContainerDiv_change_requests".equals(requestType))
			{
				fileName = DownloadFileLocalizationUtil
						.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
								ChangeMgmtConstant.CMHISTORYDOWNLOADFILENAME,locale);
				cmHistoryListHeader=DownloadFileLocalizationUtil
						.getLocalizedFileHeader(ChangeMgmtConstant.cmHistoryPDFHeader,
								locale).split(",");
				generator = new PdfPReportGenerator(
						cmHistoryListHeader, ChangeMgmtConstant.CHANGEHISTORYPDFCOLUMNS,
						serviceRequestList);
			}
			else if("gridContainerDiv_supplies_requests".equals(requestType))
			{
				fileName = DownloadFileLocalizationUtil
						.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
								ChangeMgmtConstant.SUPPLYHISTORYDOWNLOADFILENAME,locale);
				cmHistoryListHeader=DownloadFileLocalizationUtil
						.getLocalizedFileHeader(ChangeMgmtConstant.supplyHistoryPDFHeader,
								locale).split(",");
				generator = new PdfPReportGenerator(
						cmHistoryListHeader, ChangeMgmtConstant.SUPPLYHISTORYPDFCOLUMNS,
						serviceRequestList);
			}
			else if("gridContainerDiv_service_requests".equals(requestType))
			{
				fileName = DownloadFileLocalizationUtil
						.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
								ChangeMgmtConstant.SERVICEREQHISTORYDOWNLOADFILENAME,locale);
				cmHistoryListHeader=DownloadFileLocalizationUtil
						.getLocalizedFileHeader(ChangeMgmtConstant.serviceReqHistoryPDFHeader,
								locale).split(",");
				generator = new PdfPReportGenerator(
						cmHistoryListHeader, ChangeMgmtConstant.SERVICEHISTORYPDFCOLUMNS,
						serviceRequestList);
				
			}
			else{
				fileName = DownloadFileLocalizationUtil
						.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
								"Default.Download.FileName",locale);
			}
			
			
			response.setProperty("Content-disposition", "attachment; filename="+ fileName);
			
			response.setContentType("application/pdf");
			
			OutputStream responseOut = response.getPortletOutputStream();
			
			try {
				generator.generate(responseOut);
				responseOut.flush();
			}
			finally {
				if(responseOut != null) {
					try {
						responseOut.close();
					} catch (IOException ignored) {
						throw new IOException(ignored.getLocalizedMessage());
					}
				}
			}
		}
		
		//Added for CI BRD 14-02-01 --ENDS
	
	/**
	 * This method let us navigate to the Device Detail Print Page for MPS
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showDeviceDetailPrintPage")
	public String showDeviceDetailPrintPage() throws Exception{
		return "deviceFinder/deviceDetailPrint";
	}
	
	/**
	 * This method let us navigate to the Service Request History Page for MPS
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showPastServiceRequestHistoryPage")
	public String showPastServiceRequestHistoryPage(RenderResponse response, Model model) throws Exception{
		ResourceURL srHistoryURL = response.createResourceURL();
		srHistoryURL.setResourceID("retrieveServiceRequestListByAssetId");
		DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
		deviceDetailForm.setServiceRequestsXML(srHistoryURL.toString());
		model.addAttribute("deviceDetailForm", deviceDetailForm);
		return "serviceRequest/serviceRequestHistoryPage";
	}
	
	/**
	 * This method let us navigate to the Service Request DrillDown LightBox Page for MPS
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=serviceRequestDrillDownLightBox")
	public String showServiceRequestDrillDownLightBox(RenderRequest request, RenderResponse response, Model model) throws Exception{
		logger.debug("-------------serviceRequestDrillDownLightBox started---------");
		sharedPortletController.showServiceRequestDrillDownPage(request, response, model);
		return "serviceRequest/serviceRequestDrillDownLightBox";
	}
	
	/**
	 * This method let us retrieve ServiceRequest list
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="retrieveServiceRequestListByAssetId")
	public void retrieveServiceRequestListByAssetId(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{	
		sharedPortletController.retrieveServiceRequestHistoryListDrillDown(request, response, model);

	}
	
	/**
	 * This method let us retrieve CHL TreeXML 
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("chlTreeXMLURL")
	public void retrieveCHLTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		sharedPortletController.retrieveCHLTreeXML(request, response, model);
	}
	/**
	 * This method is modified for MPS, retrieves the data for device location popup
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("deviceLocationXMLURL")
	public void retrieveDeviceLocationTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		logger.enter(this.getClass().getSimpleName(),METH_DEVICELOCTREEURL );
		String pageFrom="";
		sharedPortletController.retrieveDeviceLocationTreeXML(request, response, model,pageFrom);
		logger.exit(this.getClass().getSimpleName(), METH_DEVICELOCTREEURL);
	}
	
	/**
	 * This method let us download DeviceList CSV
	 * @param response 
	 * @param deviceList 
	 * @param locale 
	 * @throws IOException 
	 */
	private void downloadDeviceListCSV(ResourceResponse response, List<Asset> deviceList,
			Locale locale, String gVPage) throws IOException {
		String fileName = null;
		fileName = DownloadFileLocalizationUtil.getDeviceListFileName(locale) + ".csv";
		//Change done for CI Defect#10242
		response.setProperty("Content-disposition", "attachment; filename=\""
				+ fileName+"\"");
		//Changes done for CI Defect #10242
		response.setContentType("text/csv;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
		/******Changed for device mgmt csv writing*****/
		out.print(DownloadFileUtil.createDeviceListCSV(deviceList, locale,gVPage));
		out.flush();
		out.close();
	}
	
	
	/**
	 * @param response 
	 * @param deviceList 
	 * @param locale 
	 * @throws IOException 
	 */
	private void downloadDeviceListPDF(ResourceResponse response, List<Asset> deviceList,
			Locale locale, String gVPage) throws IOException {
		logger.debug("Inside downloadDeviceListPDF");
		String fileName = DownloadFileLocalizationUtil.getDeviceListFileName(locale) + ".pdf";		
		String[] headers =null;
		String[] generatorPatterns = null;
		
		if (deviceList != null) {
			for (int i = 0; i < deviceList.size(); i++) {
				if(deviceList.get(i).isLbsAddressFlag()){
					deviceList.get(i).setLbsAddresFlagValue("Yes");
				}else{
					deviceList.get(i).setLbsAddresFlagValue("No");
				}
			}
		}
		
		if(gVPage.equalsIgnoreCase("true")){
			
			logger.debug("Inside downloadDeviceListPDF gVPage");
			 headers = DownloadFileLocalizationUtil
			.getDeviceListGridViewCSVFileHeader(locale).split(",");
			 
//				String[] generatorPatterns = new String[]{"serialNumber", "deviceTag", "productLine", 
			 generatorPatterns = new String[]{"serialNumber", "deviceTag", "productLine",
					"assetTag", "hostName", "ipAddress", "account.accountName", "assetType", "devicePhase","assetCostCenter", "installAddress.addressName", 
					"installAddress.addressLine1","installAddress.officeNumber", "installAddress.city", "installAddress.state",
					"installAddress.province","installAddress.county","installAddress.district", "installAddress.country", "installAddress.postalCode",
					"assetContact.firstName", "assetContact.lastName", "assetContact.emailAddress",
					"assetContact.workPhone","lbsAddresFlagValue"};
			 logger.debug("Inside downloadDeviceListPDF gVPage if");
		}
		
		else{
			 headers = DownloadFileLocalizationUtil
			.getDeviceListCSVFileHeader(locale).split(",");
			 
			 generatorPatterns = new String[]{"serialNumber", "deviceTag", "productLine", 
						"assetTag", "hostName", "ipAddress", "account.accountName", "assetType", "devicePhase","assetCostCenter", "installAddress.addressName", 
						"installAddress.addressLine1","installAddress.officeNumber", "installAddress.city", "installAddress.state",
						"installAddress.province","installAddress.county","installAddress.district", "installAddress.country", "installAddress.postalCode",
						"assetContact.firstName", "assetContact.lastName", "assetContact.emailAddress",
						"assetContact.workPhone"};
			 logger.debug("Inside downloadDeviceListPDF gVPage else");
		}
				
		
		//Changes done for Export to PDF under CI Defect #7768--ENDS
		PdfPReportGenerator generator = new PdfPReportGenerator(headers, generatorPatterns, deviceList);
		//Change done for CI Defect#10242
		response.setProperty("Content-disposition", "attachment; filename=\""
		+ fileName+"\"");
		//Changes done for CI Defect #10242
		response.setContentType("application/pdf;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		OutputStream responseOut = response.getPortletOutputStream();
		logger.debug("Inside downloadDeviceListPDF gVPage opstream");
		try {
			generator.generate(responseOut);
			responseOut.flush();
			logger.debug("Inside downloadDeviceListPDF gVPage try");
		}
		finally {
			if(responseOut != null) {
				try {
					responseOut.close();
					logger.debug("Inside downloadDeviceListPDF gVPage try close");
				} catch (IOException ignored) {
					logger.debug("Inside downloadDeviceListPDF gVPage catch");
					throw new IOException(ignored.getLocalizedMessage());
					
				}
			}
		}
	}
	
	
	/**
	 * @param request 
	 * @param contract 
	 */
	private void loadFilterCriteria(ResourceRequest request, AssetListContract contract){
		/* Added by sankha for LEX:AIR00073162 start */
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		/* End for LEX:AIR00073162 */
		if (request.getParameter("country") != null) {
			contract.getFilterCriteria().put("installAddress.country", request.getParameter("country"));
		}
		if (request.getParameter("province") != null) {
			contract.getFilterCriteria().put("installAddress.province", request.getParameter("province"));
		}
		if (request.getParameter("state") != null) {
			contract.getFilterCriteria().put("installAddress.state", request.getParameter("state"));
		}
		if (request.getParameter("city") != null) {
			contract.getFilterCriteria().put("installAddress.city", request.getParameter("city"));
		}
		if (request.getParameter("chlNodeId") != null) {
			contract.setChlNodeId(request.getParameter("chlNodeId"));
		}
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
	 * @param contactId 
	 * @param favoriteContactId 
	 * @param flagStatus 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("setContactFavouriteFlag")
	public void setContactFavouriteFlag(@RequestParam("contactId") String contactId,
			@RequestParam("favoriteContactId") String favoriteContactId,
			@RequestParam("flagStatus") boolean flagStatus,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		try {		
			//@Autowired doesn't work!!!		
			FavoriteContract favoriteContract = ContractFactory.getFavoriteContract(request);
			
			FavoriteResult favoriteResult = null;
			favoriteContract.setFavoriteContactId(favoriteContactId);
			favoriteContract.setContactId(contactId);
			favoriteContract.setFavoriteFlag(!flagStatus);
			
			PortletSession session = request.getPortletSession();
			
			logger.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(favoriteContract,logger);
			logger.info("end printing lex loggger");
			
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "updateUserFavoriteContact", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			long timeBeforeCall=System.currentTimeMillis();
			favoriteResult = contactService.updateUserFavoriteContact(favoriteContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_UPDATEUSERFAVORITECONTACT, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,favoriteContract);
			logger.info("start printing lex logger");
			logger.logTime("** MPS PERFORMANCE TESTING UPDATE USER FAVOURITE CONTRACT ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			logger.info("end printing lex loggger");
			PerformanceTracker.endTracking(lexmarkTran);
			success = favoriteResult.isResult();
		} catch (Exception e) {
		logger.error(e.getMessage());
			success = false;
		}
		String errorCode = success?"message.servicerequest.setContactFavouriteFlag"
				:"exception.servicerequest.setContactFavouriteFlag";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteContactId+"\"");
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
		boolean success = false;
		try {				
			FavoriteAddressContract favoriteAddressContract	=  ContractFactory.getFavoriteAddressContract();
			FavoriteResult favoriteResult = null;
			favoriteAddressContract.setFavoriteAddressId(favoriteAddressId);
			favoriteAddressContract.setContactId(contactId);
			favoriteAddressContract.setFavoriteFlag(!flagStatus);
			
			PortletSession session = request.getPortletSession();

			logger.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(favoriteAddressContract,logger);
			logger.info("end printing lex loggger");
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "updateUserFavoriteAddress", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			long timeBeforeCall=System.currentTimeMillis();
			favoriteResult = serviceRequestService.updateUserFavoriteAddress(favoriteAddressContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_UPDATEUSERFAVORITEADDRESS, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,favoriteAddressContract);
			logger.info("start printing lex logger");
			logger.logTime("** MPS PERFORMANCE TESTING UPDATE USER FAVOURITE ADDRESS ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			logger.info("end printing lex loggger");
			PerformanceTracker.endTracking(lexmarkTran);
			success = favoriteResult.isResult();
		} catch (Exception e) {
		logger.error(e.getMessage());
			success = false;
		}
		String errorCode = success?"message.servicerequest.setServiceAddressFavouriteFlag"
				:"exception.servicerequest.setServiceAddressFavouriteFlag";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteAddressId+"\"");
	}
	
	/**.
	 * This method renders the popup for updating page count/meter reads for MPS
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 * @author Sagar Sarkar
	 */
	@RequestMapping (params = "action=updatePageCountPopUp")
	public String openPageCountPopUp() throws LGSCheckedException, LGSRuntimeException {
		logger.enter(this.getClass().getSimpleName(), METH_OPENPAGECOUNTPOPUP);
		logger.exit(this.getClass().getSimpleName(), METH_OPENPAGECOUNTPOPUP);
		return ChangeMgmtConstant.DEVICE_PGCNTPOP_PATH;
	}
	
	/**
	 * This method invokes the aMind service to update the page counts/meter reads for MPS
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping(value = "updatePageCounts")
	public void updatePageCounts(Model model, final ResourceRequest request, ResourceResponse response)
								throws Exception {
	logger.enter(this.getClass().getSimpleName(), METH_UPDATEPAGECOUNTS);
	//brmandal changed for page count pop up - MPS phase 2.1
	pageCountsController.updatePageCounts(model, request, response);
	
	
}	
/**
 * Retrieve history list for device management for MPS
 * @param request 
 * @param response 
 */
	@ResourceMapping(value="retrieveHistoryListByAssetId")
	public void retrieveHistoryListByAssetId(ResourceRequest request, ResourceResponse response)
	{	
		logger.enter(this.getClass().getSimpleName(),METH_RETRIEVEHISTORYLISTBYASSETID);
		
		final String assetId = request.getParameter(ChangeMgmtConstant.ASSET_ROWID);		
		final String historySrType = request.getParameter(ChangeMgmtConstant.SRHISTORY_TYPE); 
		String gridColumns[]=null;
		RequestListResult serviceRequestListResult=null;
		boolean breakFixFlag=false;
		
		if(!StringUtil.isEmpty(historySrType))
		{
			if(historySrType.equalsIgnoreCase(ChangeMgmtConstant.ALL_REQUESTS))
			{
				gridColumns = ChangeMgmtConstant.ALLHISTORYCOLUMNS;
			}
			else if(historySrType.equalsIgnoreCase(ChangeMgmtConstant.SUPPLY_REQUESTS)){
				gridColumns = ChangeMgmtConstant.SUPPLYHISTORYCOLUMNS;
			}else if(historySrType.equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
				gridColumns = ChangeMgmtConstant.CHANGEHISTORYCOLUMNS;
			}else if(historySrType.equalsIgnoreCase(ChangeMgmtConstant.SERVICEREQ_LIST))
			{
				gridColumns = ChangeMgmtConstant.BREAKFIXHISTCOLUMNS;
			}
		}
		
		String timezoneOffset=request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		
		float timezoneOffSetinFloat=0;
		
		if(!StringUtil.isEmpty(timezoneOffset))
		{
			timezoneOffSetinFloat = Float.valueOf(timezoneOffset);
		}
		
		RequestListContract contract = ContractFactory.getHistoryListContract(request,historySrType,gridColumns);
		
		//Setting the asset Id as filter criteria
		contract.getFilterCriteria().put(ChangeMgmtConstant.ASSETID, assetId);
		//start ---added by Tanya for LEX:AIR00072573
		contract.getFilterCriteria().remove("serviceRequest.startDate");
		contract.getFilterCriteria().remove("serviceRequest.endDate");
		//End-----------
		logger.info("start printing lex logger");
		ObjectDebugUtil.printObjectContentForDM(contract, logger);
		logger.info("end printing lex loggger");
		
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		//Changed for CI Defect 10242
		String[] generatorPatterns = new String[] { "serviceRequestNumber", "serviceRequestStatusDate", 
				"asset.serialNumber", "asset.modelNumber", "problemDescription", "resolutionCode", "serviceRequestStatus","accountName",
				"serviceAddress.addressName", "serviceAddress.officeNumber", "serviceAddress.city", "serviceAddress.state", 
				"serviceAddress.province", "serviceAddress.county", "serviceAddress.district", "serviceAddress.postalCode", "serviceAddress.country", 
				"custRefNumber", "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", 
				"primaryContact.workPhone", "contractType", "asset.deviceTag", "asset.hostName", "costCenter"};
				//Added for CI BRD14-02-01--STARTS
				session.setAttribute("downloadRequestListContract", getContractClone(contract));
				RequestListContract contractreq =  (RequestListContract)session.getAttribute("downloadRequestListContract");
				logger.info("start printing lex logger");
				ObjectDebugUtil.printObjectContentForDM(contractreq, logger);
				logger.info("end printing lex loggger");
				//Added for CI BRD 14-02-01--ENDS
		try {
			contract.setSessionHandle(crmSessionHandle);
			logger.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			logger.debug("Contract for Request History on this asset");
			ObjectDebugUtil.printObjectContent(contract, logger);
			logger.debug("Contract for Request History on this asset Ends");
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL,
					"retrieveServiceRequestHistoryList", PortalSessionUtil
					.getMdmId(session), PortalSessionUtil.getServiceUser(
							session).getEmailAddress());
			
			if(historySrType.equalsIgnoreCase(ChangeMgmtConstant.SERVICEREQ_LIST))
			{
				breakFixFlag=true;				
				sharedPortletController.retrieveServiceRequestList(contract, request, response, generatorPatterns,true);
			}
			else {	
				long timeBeforeCall=System.currentTimeMillis();
				serviceRequestListResult = requestTypeService.retrieveRequestList(contract);
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEREQUESTLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
				logger.info("start printing lex logger");
				logger.logTime("** MPS PERFORMANCE TESTING RETRIEVE REQUEST LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
				logger.info("end printing lex loggger");
			}
			
			PerformanceTracker.endTracking(lexmarkTran);
			
			if(serviceRequestListResult!=null && !breakFixFlag)
			{
			List<ServiceRequest> serviceRequestList = serviceRequestListResult.getRequestList();
		
			int totalCount = serviceRequestListResult.getTotalCount();
			
			String xmlContent = null;
			
			if(historySrType.equalsIgnoreCase(ChangeMgmtConstant.ALL_REQUESTS)){
				xmlContent = getXmlOutputGeneratorUtil(request.getLocale())
				.generateXMLForAllRequestDM(serviceRequestList,
						totalCount, contract.getStartRecordNumber(), timezoneOffSetinFloat);
			}else if(historySrType.equalsIgnoreCase(ChangeMgmtConstant.SUPPLY_REQUESTS)){
				xmlContent = getXmlOutputGeneratorUtil(request.getLocale())
				.generateXMLForSupplyRequestDM(serviceRequestList,
						totalCount, contract.getStartRecordNumber(), timezoneOffSetinFloat);
			}else if(historySrType.equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
				xmlContent = getXmlOutputGeneratorUtil(request.getLocale())
				.generateXMLForChangeRequestDM(serviceRequestList,
						totalCount, contract.getStartRecordNumber(), timezoneOffSetinFloat);
			}
			
			PrintWriter out = response.getWriter();
			response.setContentType(ChangeMgmtConstant.CONTENTTYPEXML);
			out.print(xmlContent);
			out.flush();
			out.close();
			}
		}catch (Exception e) {
			logger.error("Exception 1:"+e.getMessage());
			logStackTrace(e);
		}
		finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.exit(this.getClass().getSimpleName(),METH_RETRIEVEHISTORYLISTBYASSETID);
	}

	/**
	 * This method is responsible for the device detail call and extract 
	 * the ltpc values out of it, need it for update page counts link in the grid for MPS
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	 //brmandal changed for page count pop up - mps phase 2.1
	@ResourceMapping (value = "getPageCountPopUp")
	public void getLtpcValues(ResourceRequest request, ResourceResponse response, Model model) throws Exception
	{
		//brmandal
		pageCountsController.getPageCountPopUp(request, response, model);
	}
	
	//Added for CI 14-02-01-STARTS
	//Clone Contract
	private RequestListContract getContractClone(RequestListContract contract){	
		
			RequestListContract newContract = new RequestListContract();
			// ActivityListContract
			newContract.getFilterCriteria().putAll(contract.getFilterCriteria());
			newContract.getSearchCriteria().putAll(contract.getSearchCriteria());
			newContract.getSortCriteria().putAll(contract.getSortCriteria());
			newContract.setStatus(contract.getStatus());
			newContract.setChlNodeId(contract.getChlNodeId());
			newContract.setContactId(contract.getContactId());
			newContract.setAssetType(contract.getAssetType());
			newContract.setServiceRequestNumber(contract.getServiceRequestNumber());
			newContract.setShowAllFlag(contract.isShowAllFlag());
			newContract.setAssetFavoriteFlag(contract.isAssetFavoriteFlag());
			newContract.setVendorFlag(contract.isVendorFlag());
			newContract.setVendorAccountId(contract.getVendorAccountId());
			newContract.setChangeRequestFlag(contract.isChangeRequestFlag());
			newContract.setHardwareRequestFlag(contract.isHardwareRequestFlag());
			newContract.setEntitlementEndDate(contract.getEntitlementEndDate());
			newContract.setMdmId(contract.getMdmId());
			newContract.setMdmLevel(contract.getMdmLevel());
			newContract.setStartRecordNumber(contract.getStartRecordNumber());
			newContract.setIncrement(contract.getIncrement());
			newContract.setNewQueryIndicator(contract.isNewQueryIndicator());
			
			return newContract;		
		
	}
	//Added for CI 14-02-01-ENDS
}