/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: HistoryController.java
 * Package     		: com.lexmark.services.portlet.common
 * Creation Date 	: 1st April 2012
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro			1st April 2012 		1.0             Initial Version
 * Wipro	10th May 2012		1.1				Added method for details page
 *
 */

package com.lexmark.services.portlet.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.LocalizedPageCountNameContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.contract.SRAdministrationListContract;
import com.lexmark.contract.ServiceRequestAssociatedListContract;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.SiebelLocalization;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.domain.UserDetails;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AttachmentResult;
import com.lexmark.result.LocalizedPageCountNameResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestResult;
import com.lexmark.result.SRAdministrationListResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.RequestDetailForm;
import com.lexmark.services.form.ShipmentForm;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ResourceResponseUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.util.BusinessObjectUtil;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.DownloadFileUtil;
import com.lexmark.util.LangUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.report.PdfPReportGenerator;
import com.liferay.portal.util.PortalUtil;

/**.
 * This controller serves the requests for change management history related 
 * functionalities like retrieving, searching/filtering history data, 
 * details page etc.
 * @author sagar
 * 
 */
@Controller
@RequestMapping("VIEW")
public class HistoryController extends BaseController{
	
	/**. This variable holds AmindGlobalService bean reference **/
	@Autowired
    public GlobalService  globalService;
	
	/**. This map holds the request types and respective grid page name.
	 *  Declared in allRequestHistory.xml 
	**/
	@Resource
	private Map<String, String> historyPageMap ;	
	
	/**. This variable holds AmindRequestTypeService bean reference **/
	@Autowired
	public RequestTypeService requestTypeService;
	
	/**. Holds the reference of CommonController bean **/
	@Autowired
	private CommonController commonController;
	
	/**. Holds ServiceRequestLocaleImpl bean reference  **/
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	
	/**. Holds AmindAttachmentService bean reference **/
	@Autowired
    public AttachmentService attachmentService;
	
	/**. Holds AmindContractedServiceRequestService bean reference **/
	@Autowired
    public ServiceRequestService serviceRequestService;
	
	/**. Holds the reference of SharedPortletController bean **/
	@Autowired
    public SharedPortletController sharedPortletController;
	
	/**. Holds the reference of ProductImageServiceImpl bean **/
	@Autowired
    private ProductImageService  productImageService;
	
	/**. Instance variable of wrapper logger class **/
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(HistoryController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	/**. Name of this Class **/
	private static String CLASS_NAME = "HistoryController.java" ;
	
	private static final String SHIPMENT = "shipment";
	private static final String ASSET = "Asset";
	private static final String CONTACT = "Contact";
	private static final String ADDRESS = "Address";
	private static final String CHL = "CHL";
	private static final String CUSTOMERHIERARCHY="Customer Hierarchy";
	private static final String OTHERS = "Others";
	private static final String PRODUCT_TLI = "productTLI";
	private static final String PROD_DESCRIPTN = "productDescription";
	private static final String QUANTITY = "quantity";
	private static final String STATUS = "status";
	private static final String PARTNO = "partnumber";
	private static final String PART_TYPE = "partType";

	//added for MPS Phase 2
	private static final String PRICE = "price";
	private static final String DEVICE_TYPE = "deviceType";

	private static final String PENDING_QUANTITY = "quantity";
	private static final String SNO = "serialNumber";
	private static final String VENDORPRODUCT = "vendorProduct";
	private static final String SHIPPED_QUANTITY = "shippedQuantity";
	private static final String CHL_OTHERS_VIEW = "CHLOthers";
	private static final String NORMAL_VIEW = "Normal";
	private static final String FILE_DELIMITER = ".";
	private static final String GRID_QUERY_PARAMS = "gridQueryParams_";
	/**.
	 * 
	 * This method renders the change management history page.
	 * 
	 * @param model Model object
	 * @param request RenderRequest object
	 * @param response RenderResponse object
	 * @return String The view page name
	 * @throws Exception Exception
	 * @author Sagar Sarkar
	 * @throws LGSDBException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping
	public String showCMHistoryPage(Model model, RenderRequest request,
			RenderResponse response) throws Exception, LGSDBException {
		String METHOD_NAME = "showCMHistoryPage" ;
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		// Added for MPS Phase 2.1
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("downloadHistoryList");
		model.addAttribute("downloadHistoryList", resURL.toString());
		
		String currURL=resURL.toString();
		LOGGER.debug("currURL==="+currURL);
		boolean isPartnerRequest=currURL.indexOf(ChangeMgmtConstant.ISPARTNERPORTAL)!=-1;
		LOGGER.debug("isPartnerRequest==="+isPartnerRequest);
		
		//if(!isPartnerRequest){
			//commonController.retrieveReqHistoryAccess4Portal(request,response);
		//}
		//This section is for hiding the subtab if the user does not have any access		
		//commonController.checkUserRoles(request.getPortletSession(), request);		
		//For MPS Phase 2.1
		
		//Below Section is for forwarding the requests to Details Page coming from Device Mgmt		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		
		String serviceRequestNumber=httpReq.getParameter("serviceRequestNumber");
		
		if(serviceRequestNumber !=null)
		{
			LOGGER.exit(CLASS_NAME, METHOD_NAME );
			return showRequestDetails(model, request, response);
		}
		//End of the section
		
		//For MPS Phase 2.1
		
			String requestTypeStr=request.getParameter(ChangeMgmtConstant.REQUESTTYPESTR);
			
			if(requestTypeStr==null) {
				// - requestTypeStr added for back button in asset details (MPS Phase 2)
				requestTypeStr=httpReq.getParameter(ChangeMgmtConstant.REQUESTTYPESTR);
			}
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
			//B2B Changes
				else if(requestTypeStr.equalsIgnoreCase(ChangeMgmtConstant.B2B_REQUESTS)){
					gridType=ChangeMgmtConstant.B2B_REQUESTS;
					gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[5];
				}
			model.addAttribute(ChangeMgmtConstant.BACKFROMHISTORY,ChangeMgmtConstant.TRUE);		
				
			}else{
			
				/*
				 * Default Page and grid settings to be 
				 * retrieved according to the access
				 * */
				if(!isPartnerRequest){
					PortletSession portletSession=request.getPortletSession();
					Map<?,?> requestAccessMap=(Map<?,?>)portletSession.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);
					
					if(requestAccessMap.get("SHOW ALL REQUEST").equals("true")){
						gridType=ChangeMgmtConstant.ALL_REQUESTS;
						gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[0];
					}			
					else if(requestAccessMap.get("SHOW CHANGE MGMT REQUEST").equals("true")){
						gridType=ChangeMgmtConstant.CHANGE_REQUESTS;
						gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[1];
					}
					else if(requestAccessMap.get("SHOW SUPPLIES REQUEST").equals("true")){
						gridType=ChangeMgmtConstant.SUPPLY_REQUESTS;
						gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[2];
					}
					else if(requestAccessMap.get("SHOW SERVICE REQUEST").equals("true")){
						gridType=ChangeMgmtConstant.SERVICE_REQUESTS;
						gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[3];
					}
				}else{
					/*
					 * This section is from partnet Portal request
					 * Partner will see only Supply requests
					 * */
					gridType=ChangeMgmtConstant.SUPPLY_REQUESTS;
					gridCreationId=ChangeMgmtConstant.GRID_CREATION_IDS_FOR_HISTORY[2];
				}
			}
			retrieveGridSetting(gridCreationId, request, response, model);
			model.addAttribute(ChangeMgmtConstant.GRIDTYPE, gridType);
			LOGGER.debug("grid type is "+gridType);
			 Map<String, String> requestStatusLOVMap =null;
				try {
					requestStatusLOVMap =  commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), request.getLocale());						
				}catch(LGSDBException e){
					LOGGER.error("Unable to retrieve Status map");
					requestStatusLOVMap=new HashMap<String,String>();
					e.getMessage();
				}
			
			model.addAttribute("requestStatusLOVMap", requestStatusLOVMap);
			
			LOGGER.exit(CLASS_NAME, METHOD_NAME );
			return "lgsHistoryDetails/allRequestHistoryHome";
	
	}
	//For MPS Phase 2.1
	/**
	 * 
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @param model 
	 * @param gridId 
	 */
	@ResourceMapping("getGridSettingsByAjax")
	public void getGridSetting(ResourceRequest resourceRequest,ResourceResponse resourceResponse,Model model,@RequestParam("gridId") String gridId){
	
		LOGGER.debug("grid ID ="+gridId);
		
		//Get Grid settings for particular Grid id
		UserGridSettingResult result =retrieveGridSettingForHistory(gridId, resourceRequest);
		StringBuffer json=new StringBuffer("{");
			

		boolean flagIsSessionKeyAvail=false;
		if(result==null){
			appendToJSON(LexmarkConstants.JSON_STATUS,LexmarkConstants.JSON_STATUS_FAILURE,json);
			
		}else{
			appendToJSON(LexmarkConstants.JSON_STATUS,LexmarkConstants.JSON_STATUS_AVAILABLE,json);
			for(String gridParamFromDB:LexmarkConstants.gridSavingParams){
				
				String columnValue = BusinessObjectUtil.formatColumn(result, gridParamFromDB, resourceRequest.getLocale());
				LOGGER.debug("field name "+gridParamFromDB+" result "+columnValue);
				appendToJSON(gridParamFromDB,columnValue==null?"":columnValue,json);
				
			}
			String sessionGridKey = GRID_QUERY_PARAMS.concat(gridId);
			String queryParams=null;
			if(resourceRequest.getPortletSession().getAttribute(sessionGridKey)!=null){
				queryParams = (String)resourceRequest.getPortletSession().getAttribute(sessionGridKey);
			}
			
			if(queryParams!=null) {
				flagIsSessionKeyAvail=true;
				json.append(queryParams);
			}
			
		}
		if(!flagIsSessionKeyAvail){
			json.deleteCharAt(json.length()-1);
		}
		
		json.append("}");
		LOGGER.debug("json uptil is "+json);
		
		try {
			final PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			resourceResponse.setProperty("Expires", "max-age=0,no-cache,no-store");
			resourceResponse.setContentType("UTF-8");
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
		
	}
	
	
	
	//For MPS Phase 2.1
	/**
	 * 
	 * @param key  
	 * @param value  
	 * @param stringBuffer 
	 */
	private void appendToJSON(String key,String value,StringBuffer stringBuffer){
		
			stringBuffer.append("\""+key+"\":\""+value+"\",");
		
	}
	

	/**.
	 * Display the grid according to the request type link present in 
	 * left hand navigation link.
	 * @param model Model
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @return String Grid page
	 * @throws Exception 
	 * @author Sagar Sarkar
	 */
	@RequestMapping(params = "action=showHistoryGrid")
	public String showHistoryGrid(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		String METHOD_NAME = "showHistoryGrid" ;
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		String historyPageName = ChangeMgmtConstant.CMHISTORY_DEFAULTPAGE; 
		
		if(historyPageMap.isEmpty()){
			LOGGER.warn("historyPageMap is empty.Default History page will be displayed!");
		}
		String historyPageType = request.getParameter("requestTypePage");
		LOGGER.debug("HistoryController.showCMHistoryPage.requestTypePage---->"+historyPageType);
		
		if( historyPageType != null
				&& historyPageMap.containsKey(historyPageType.trim() )){
			historyPageName = historyPageMap.get(historyPageType.trim());
		}
		LOGGER.debug("HistoryController.showCMHistoryPage.historyPage---->"+historyPageName);
		model.addAttribute("GRIDTYPE", historyPageType);
		
		ResourceURL url = response.createResourceURL();
		if(historyPageName.equalsIgnoreCase("SERVICE_REQUESTS")){
			url.setResourceID("retrieveBreakfixSRList");
			model.addAttribute("retrieveCMHistoryList", url.toString());
			retrieveGridSetting("serviceRequestListGrid",request,response,model);
			return "serviceRequest/serviceRequestListPage";
		}else{
		url.setResourceID("retrieveCMHistoryList");
		}
		model.addAttribute("retrieveCMHistoryList", url.toString());
		
		Map<String, String> requestTypeLOVMap = new HashMap<String, String>();
		 try {
			 requestTypeLOVMap = commonController.retrieveLocalizedLOVMap(ChangeMgmtConstant.LOV_SR_TYPE, request.getLocale());
			 model.addAttribute("requestTypeLOVMap", requestTypeLOVMap);
			 LOGGER.debug("HistoryController.showCMHistoryPage.requestTypeLOVMap-->"+requestTypeLOVMap);
			 
			 Map<String, String> requestStatusLOVMap = 
				 commonController.retrieveLocalizedLOVMap(
						 SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), request.getLocale());
			 LOGGER.debug("requestStatusLOVMap-->"+requestStatusLOVMap);
				model.addAttribute("requestStatusLOVMap", requestStatusLOVMap);		
			
			retrieveGridSetting(historyPageName,request,response,model);
		 } catch (LGSDBException dbExcption) {
			 LOGGER.error("LGSBusinessException occurred ::: "+ dbExcption.getMessage());
		 }
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		response.setContentType("UTF-8");
		return "lgsHistoryDetails/"+historyPageName;
		
	}
	
	/**.
	 * This utility method converts the LOV into a Map.
	 * @param lovResultObj 
	 * @return Map<String, String> 
	 * @author Sagar Sarkar 
	 */
	private Map<String, String> getLovAsMap(SiebelLOVListResult lovResultObj){
		String METHOD_NAME="getLovAsMap";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		if(lovResultObj == null || lovResultObj.getLovList() == null || lovResultObj.getLovList().size()==0){
			LOGGER.warn("SiebelLOVListResult object is null or No LOV is populated in the object !");
			
			new HashMap<String, String>();
		}
		Map<String, String> lovMap = new LinkedHashMap<String, String>();
		for(ListOfValues lovObj : lovResultObj.getLovList()){
			lovMap.put(lovObj.getName(), lovObj.getValue());
		}
		lovMap.remove("Claims");
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		return lovMap;
	}
	
	/**.
	 * This method retrieves the change management history records, flushes the
	 * data as XML string resource to response object.
	 * 
	 * @param request ResourceRequest object
	 * @param response ResourceResponse object
	 * @throws Exception Exception
	 * @author Sagar Sarkar
	 */
	@ResourceMapping("retrieveCMHistoryList")
	public void retrieveCMHistoryList(ResourceRequest request,
			ResourceResponse response) throws Exception {
		String METHOD_NAME = "retrieveCMHistoryList";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		String requestedGridType = request.getParameter("gridType");
		LOGGER.debug("HistoryController.retrieveCMHistoryList.requestResourceType--->"+requestedGridType);
		String fromFleetManager=request.getParameter("fromFleetManager")!=null?request.getParameter("fromFleetManager"):"";
		LOGGER.debug("fromFleetManager--->"+fromFleetManager);
		String gridColumns[] = null;
		
		if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.ALL_REQUESTS)){
			gridColumns = ChangeMgmtConstant.ALLHISTORYCOLUMNS;
		}else if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.SUPPLY_REQUESTS)){
			gridColumns = ChangeMgmtConstant.SUPPLYHISTORYCOLUMNS;
		}else if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
			gridColumns = ChangeMgmtConstant.CHANGEHISTORYCOLUMNS;
		}else if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.SERVICE_REQUESTS)){ //CI-6
			gridColumns = ChangeMgmtConstant.SERVICEREQUESTSCOLUMNS;//CI-6
		}
		// CHANGES FOR HARDWARE For MPS Phase 2.1
		else if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.HARDWARE_REQUESTS)){ 
			
			gridColumns = ChangeMgmtConstant.HARDWAREQUESTSCOLUMNS;
			LOGGER.debug("griddd>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + gridColumns);
		}
		// For B2B Requests
		else if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.B2B_REQUESTS)){ 
			
			gridColumns = ChangeMgmtConstant.B2BREQUESTSCOLUMNS;
			LOGGER.debug("griddd>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + gridColumns);
		}
		
		//end
		LOGGER.debug("HistoryController.retrieveCMHistoryList.gridColumns--->"+Arrays.asList(gridColumns));
		
		RequestListContract contract = ContractFactory
				.getHistoryListContract(request, requestedGridType,
						gridColumns);

		loadFilterCriteria(request, contract);

		PortletSession session = request.getPortletSession();		
		UserDetails userDetails = (UserDetails) session.getAttribute("LOGINUSERDETAILS",PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("The userDetails.isViewAllCustomerOrderFlag() ---->>>"+userDetails.isViewAllCustomerOrderFlag());
		//The below session map is set in common Controller, The map is only valid for partner portal here
		Map<String,String> accountDetailsValues=(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,session.APPLICATION_SCOPE); //Added for BRD #14-07-09		
		if(accountDetailsValues !=null){			
				LOGGER.debug("The account id is------------>>>>>"+accountDetailsValues.get("accountId"));
				contract.setAccountId(accountDetailsValues.get("accountId"));
		}
		
		session.setAttribute("downLoadContract", contract.clone());		

		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));

		try {
			contract.setViewAllCustomerOrder(userDetails.isViewAllCustomerOrderFlag()); //if isViewAllCustomerOrderFlag=true (new search spec) else old searchspec in aMind BRD #14-07-09
			contract.setSessionHandle(crmSessionHandle);
			LOGGER.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			RequestListResult serviceRequestListResult;
			if(fromFleetManager.equals("true")&&requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
				//contract.setAssetId(request.getParameter("assetId"));
				//Below assetId changes done for defect 18256
				contract.getFilterCriteria().put("assetId", request.getParameter("assetId"));
				//Ends assetId changes done for defect 18256
				LOGGER.info("Calling NEW API");
				LOGGER.info("start printing lex logger");
				ObjectDebugUtil.printObjectContent(contract,LOGGER);
				LOGGER.info("end printing lex loggger");
				serviceRequestListResult=requestTypeService.retrieveMadcRequestList(contract);
				LOGGER.info("Calling NEW API");
				
			}
			else
			{
				LOGGER.info("start printing lex logger");
				ObjectDebugUtil.printObjectContent(contract,LOGGER);
				LOGGER.info("end printing lex loggger");
				serviceRequestListResult = requestTypeService.retrieveRequestList(contract);
			}
			
			LOGGER.debug("HistoryController.retrieveCMHistoryList.contract.isAssetFavoriteFlag-->"+contract.isAssetFavoriteFlag());
			long timeBeforeCall=System.currentTimeMillis();
			// Commented for MPS 2.1 Mock call
			
			LOGGER.debug("fromFleetManager"+fromFleetManager);
			LOGGER.debug("requestedGridType"+requestedGridType.trim());
			
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			

			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_RETRIEVEREQUESTLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
		
			
			LOGGER.info("end printing lex loggger");
			
			// Commented for MPS 2.1 Mock call
			List<ServiceRequest> serviceRequestList = serviceRequestListResult.getRequestList();
			int totalCount = serviceRequestListResult.getTotalCount();
			
					
			float timezoneOffset = 0;
			if(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)!= null){
				 timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
			}
			
			LOGGER.debug("timezoneOffset --->"+timezoneOffset);
			String xmlContent = "<Data></Data>" ;
			
			if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.ALL_REQUESTS)){
				xmlContent = getXmlOutputGenerator(request.getLocale())
				.buildXMLForAllRequestType(serviceRequestList,
						totalCount, contract.getStartRecordNumber(), timezoneOffset);
			}else if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.SUPPLY_REQUESTS)){
				xmlContent = getXmlOutputGenerator(request.getLocale())
				.buildXMLForSupplyRequestType(serviceRequestList,
						totalCount, contract.getStartRecordNumber(), timezoneOffset, request.getContextPath());
			}else if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
				xmlContent = getXmlOutputGenerator(request.getLocale())
				.buildXMLForChangeRequestType(serviceRequestList,
						totalCount, contract.getStartRecordNumber(), timezoneOffset);
			}else if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.HARDWARE_REQUESTS)){ //For MPS Phase 2.1
				LOGGER.debug("in hardware >>>>>>>>>>>>>>>>>>>>>>"+requestedGridType);
				xmlContent = getXmlOutputGenerator(request.getLocale())
				.buildXMLForHardwareRequestType(serviceRequestList,
						totalCount, contract.getStartRecordNumber(), timezoneOffset, request.getContextPath());
			}
			else if(requestedGridType.trim().equalsIgnoreCase(ChangeMgmtConstant.B2B_REQUESTS)){ //For MPS Phase 2.1
				LOGGER.debug("in hardware >>>>>>>>>>>>>>>>>>>>>>"+requestedGridType);
				xmlContent = getXmlOutputGenerator(request.getLocale())
				.buildXMLForB2BRequestType(serviceRequestList,
						totalCount, contract.getStartRecordNumber(), timezoneOffset, request.getContextPath());
			}
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(xmlContent);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("Exception :"+e.getMessage());
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
	}
	
	
	
	/**.
	 * This method adds the filter criteria to the contract.
	 * @param request ResourceRequest
	 * @param contract ServiceRequestListContract
	 * @author Sagar Sarkar
	 */
	private void loadFilterCriteria(ResourceRequest request,
			RequestListContract contract) {
		String METHOD_NAME = "loadFilterCriteria";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		if (request.getParameter("serviceRequestNumber") != null) {
			contract.getFilterCriteria().put("requestNumber",
					request.getParameter("serviceRequestNumber"));
			LOGGER.debug("contract.getFilterCriteria()---1"+contract.getFilterCriteria());
		}
		if(request.getParameter(ChangeMgmtConstant.VALUE_AREA) != null){
			contract.getFilterCriteria().put(ChangeMgmtConstant.VALUE_AREA,request.getParameter(ChangeMgmtConstant.VALUE_AREA));
			LOGGER.debug("contract.getFilterCriteria()---2"+contract.getFilterCriteria());
		}
		if (request.getParameter(ChangeMgmtConstant.VALUE_SUBAREA) != null) {
			contract.getFilterCriteria().put(ChangeMgmtConstant.VALUE_SUBAREA,	request.getParameter(ChangeMgmtConstant.VALUE_SUBAREA));
			LOGGER.debug("contract.getFilterCriteria()---3"+contract.getFilterCriteria());
		}
		if (request.getParameter(ChangeMgmtConstant.COUNTRY) != null) {
			contract.getFilterCriteria().put("serviceAddress.country", request.getParameter(ChangeMgmtConstant.COUNTRY));
			LOGGER.debug("contract.getFilterCriteria()---4"+contract.getFilterCriteria());
		}
		if (request.getParameter(ChangeMgmtConstant.STATE) != null) {
			contract.getFilterCriteria().put("serviceAddress.state", request.getParameter(ChangeMgmtConstant.STATE));
			LOGGER.debug("contract.getFilterCriteria()---5"+contract.getFilterCriteria());
		}
		if (request.getParameter(ChangeMgmtConstant.PROVINCE) != null) {
			contract.getFilterCriteria().put("serviceAddress.province", request.getParameter(ChangeMgmtConstant.PROVINCE));
			LOGGER.debug("contract.getFilterCriteria()---5"+contract.getFilterCriteria());
		}
		if (request.getParameter(ChangeMgmtConstant.CITY) != null) {
			contract.getFilterCriteria().put("serviceAddress.city", request.getParameter(ChangeMgmtConstant.CITY));
			LOGGER.debug("contract.getFilterCriteria()---6"+contract.getFilterCriteria());
		}
		
		if (request.getParameter(ChangeMgmtConstant.CHLNODEID) != null) {
			contract.setChlNodeId(request.getParameter(ChangeMgmtConstant.CHLNODEID));
			LOGGER.debug("contract.getFilterCriteria()---7"+contract.getFilterCriteria());
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
	}
	
	
	/**.
	 * Retrieve change management history list and download it csv and pdf format.
	 * 
	 * @param request ResourceRequest 
	 * @param response ResourceResponse 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("downloadHistoryList")
	public void downloadHistoryList(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		String METHOD_NAME = "downloadHistoryList";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		String downloadType = request.getParameter("downloadType");
		String pageType = request.getParameter("pageType");
		LOGGER.debug("HistoryController.downloadHistoryList downloadType : "+downloadType);
		Locale locale = request.getLocale();
		LOGGER.debug("locale :--->"+locale.getCountry());
		
		PortletSession session = request.getPortletSession();
		RequestListContract contract = (RequestListContract) session
				.getAttribute("downLoadContract");
		session.setAttribute("downLoadContract", contract.clone());
		
		
		/*
		 * Commented as not required anymore..
		 * 11838
		 * */
		
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			LOGGER.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LOGGER);
			LOGGER.info("end printing lex loggger");
			
			LOGGER.debug("HistoryController.downloadHistoryList.contract.isAssetFavoriteFlag-->"+contract.isAssetFavoriteFlag());
			long timeBeforeCall=System.currentTimeMillis();
			RequestListResult serviceRequestListResult = requestTypeService.retrieveRequestList(contract);
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_DOWNLOADHISTORYLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
			LOGGER.info("end printing lex loggger");
			List<ServiceRequest> serviceRequests = serviceRequestListResult
					.getRequestList();
			LOGGER.debug("serviceRequestListResult.size-->"+serviceRequestListResult.getTotalCount());
			float timezoneOffset = 0;
			if(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)!= null){
				 timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
			}
			
			serviceRequests = requestType(serviceRequests, locale);			
			
			LOGGER.debug("timezoneOffset-->"+timezoneOffset);
			if (ChangeMgmtConstant.CSV_FILETYPE_EXTN.equals(downloadType)) {
				downloadCMHistoryListToCSV(response, serviceRequests, locale,pageType, timezoneOffset);
			} else if (ChangeMgmtConstant.PDF_FILETYPE_EXTN.equals(downloadType)) {
				downloadPDF(response, serviceRequests, locale,pageType);
			} else {
				throw new Exception(
						ServiceStatusErrorMessageUtil.getErrorMessage(
								LexmarkSPConstants.ERROR_MESSAGE_BUNDLE,
								"exception.portlet.downloadException", null,
								request.getLocale()));
			}
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}

		LOGGER.exit(CLASS_NAME, METHOD_NAME );
	}
	
	
	/**.
	 * This utility method populates the request type filter criteria in the contract.
	 * @param pageType String
	 * @param contract RequestListContract
	 * @param request ResourceRequest
	 */
	@SuppressWarnings("unchecked")
	private void populateRequestTypes(String pageType, RequestListContract contract, ResourceRequest request){
		String METHOD_NAME = "populateRequestTypes";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		List<String> reqTypeValues = new ArrayList<String>();
		if(pageType.equalsIgnoreCase(ChangeMgmtConstant.ALL_REQUESTS) ){
			if(contract.getFilterCriteria().get("requestType") != null){
				String reqType = (String) contract.getFilterCriteria().get("requestType");
				reqTypeValues.add(reqType);
				contract.getFilterCriteria().put("requestType", reqTypeValues);
			}else{
			
				Map<String,String> accessMap = (Map<String, String>) request.getPortletSession().getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR,PortletSession.APPLICATION_SCOPE);
				LOGGER.debug("ContractFactory.getHistoryListContract.accessMap--->"+accessMap);
				if(!accessMap.isEmpty()){
					
					if(accessMap.containsKey("SHOW SUPPLIES REQUEST") && accessMap.get("SHOW SUPPLIES REQUEST").toString().equalsIgnoreCase("true")){
						reqTypeValues.add(ChangeMgmtConstant.SRTYPE_SUPPLYMANAGEMENT);
					}if(accessMap.containsKey("SHOW CHANGE MGMT REQUEST") && accessMap.get("SHOW CHANGE MGMT REQUEST").toString().equalsIgnoreCase("true")){
						reqTypeValues.add(ChangeMgmtConstant.SRTYPE_CHANGEMANAGEMENT);
					}if(accessMap.containsKey("SHOW SERVICE REQUEST") && accessMap.get("SHOW SERVICE REQUEST").toString().equalsIgnoreCase("true")){
						reqTypeValues.add(ChangeMgmtConstant.SRTYPE_BREAKFIX);
					}
					
					contract.getFilterCriteria().put(ChangeMgmtConstant.REQUESTTYPE, reqTypeValues);
				}
			}
		}else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.SUPPLY_REQUESTS)){
			reqTypeValues.add(ChangeMgmtConstant.SRTYPE_SUPPLYMANAGEMENT);
			contract.getFilterCriteria().put(ChangeMgmtConstant.REQUESTTYPE, reqTypeValues);
		}else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
			reqTypeValues.add(ChangeMgmtConstant.SRTYPE_CHANGEMANAGEMENT);
			contract.getFilterCriteria().put(ChangeMgmtConstant.REQUESTTYPE, reqTypeValues);
		}else if(pageType.equalsIgnoreCase("ServiceRequestList")){
			reqTypeValues.add(ChangeMgmtConstant.SRTYPE_BREAKFIX);
			contract.getFilterCriteria().put(ChangeMgmtConstant.REQUESTTYPE, reqTypeValues);
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
	}
	

	/**
	 * 
	 * @param response 
	 * @param serviceRequestList  
	 * @param locale 
	 * @param pageType 
	 * @param timeZoneOffset 
	 * @throws IOException 
	 */
	private void downloadCMHistoryListToCSV(ResourceResponse response,
			List<ServiceRequest> serviceRequestList, Locale locale,String pageType, float timeZoneOffset)
			throws IOException {
		String METHOD_NAME = "downloadCMHistoryListToCSV";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		LOGGER.debug("HistoryController.downloadCMHistoryListToCSV Enter :pageType--> "+pageType);
		LOGGER.debug("HistoryController.downloadCMHistoryListToCSV Enter :timeZoneOffset--> "+timeZoneOffset);
		String csvFileName="";
		String csvFileHeader = "";
		if(pageType.isEmpty()){
			csvFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
					"Default.Download.FileName",locale);
			csvFileHeader=  ChangeMgmtConstant.DefaultHistoryCSVHeader;

		}else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.ALL_REQUESTS)){
			csvFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
					ChangeMgmtConstant.ALLHISTORYDOWNLOADFILENAME,locale);
			csvFileHeader=  ChangeMgmtConstant.allHistoryCSVHeader;
			
		}else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.SUPPLY_REQUESTS)){
			csvFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
					ChangeMgmtConstant.SUPPLYHISTORYDOWNLOADFILENAME,locale);
			csvFileHeader=  ChangeMgmtConstant.supplyHistoryCSVHeader;
			
		}else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
			csvFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
					ChangeMgmtConstant.CMHISTORYDOWNLOADFILENAME,locale);
			csvFileHeader=  ChangeMgmtConstant.cmHistoryCSVHeader;
			
		}
		// changes for hardware Mps 2.1
		else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.HARDWARE_REQUESTS)){
			csvFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.CSV_FILETYPE_EXTN,
					ChangeMgmtConstant.HARDWAREDOWNLOADFILENAME,locale);
			csvFileHeader=  ChangeMgmtConstant.cmHardwareheader;
			
		}
		
		 
		LOGGER.info("HistoryController.downloadCMHistoryListToCSV.csvFileName-->"+csvFileName);
		response.setProperty("Content-disposition", "attachment; filename="
				+ csvFileName);

		response.setContentType("text/csv");
		PrintWriter out = ResourceResponseUtil
				.getUTF8PrintWrtierWithBOM(response);
		out.print(DownloadFileUtil.assembleCMHistoryToCSV(serviceRequestList,
				csvFileHeader, locale, pageType, timeZoneOffset));
		out.flush();
		out.close();
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
	}
	


	/**.
	 * Builds the pdf file with change management history data and flushes to
	 * get downloaded in client side.  
	 * @param response ResourceResponse 
	 * @param serviceRequestList List<ServiceRequest> 
	 * @param pageType  
	 * @param locale Locale 
	 * @throws IOException 
	 */
	private void downloadPDF(ResourceResponse response,
			List<ServiceRequest> serviceRequestList, Locale locale,String pageType)
			throws IOException {
		String METHOD_NAME = "downloadPDF";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		String pdfFileName= "";
		String[] cmHistoryListHeader = null;
		PdfPReportGenerator generator = null;
		
		if(pageType.isEmpty()){
			pdfFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
					"Default.Download.FileName",locale);
			cmHistoryListHeader=DownloadFileLocalizationUtil
			.getLocalizedFileHeader(ChangeMgmtConstant.DefaultHistoryPDFHeader,
					locale).split(",");

		}else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.ALL_REQUESTS)){
			pdfFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
					ChangeMgmtConstant.ALLHISTORYDOWNLOADFILENAME,locale);
			cmHistoryListHeader=DownloadFileLocalizationUtil
			.getLocalizedFileHeader(ChangeMgmtConstant.allHistoryPDFHeader,
					locale).split(",");
			
			generator = new PdfPReportGenerator(
					cmHistoryListHeader, ChangeMgmtConstant.ALLHISTORYCOLUMNS,
					serviceRequestList);
			
		}else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.SUPPLY_REQUESTS)){
			pdfFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
					ChangeMgmtConstant.SUPPLYHISTORYDOWNLOADFILENAME,locale);
			cmHistoryListHeader=DownloadFileLocalizationUtil
			.getLocalizedFileHeader(ChangeMgmtConstant.supplyHistoryPDFHeader,
					locale).split(",");
			
			generator = new PdfPReportGenerator(
					cmHistoryListHeader, ChangeMgmtConstant.SUPPLYHISTORYPDFCOLUMNS,
					serviceRequestList);
			
		}else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.CHANGE_REQUESTS)){
			pdfFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
					ChangeMgmtConstant.CMHISTORYDOWNLOADFILENAME,locale);
			cmHistoryListHeader=DownloadFileLocalizationUtil
			.getLocalizedFileHeader(ChangeMgmtConstant.cmHistoryPDFHeader,
					locale).split(",");
			
			generator = new PdfPReportGenerator(
					cmHistoryListHeader, ChangeMgmtConstant.CHANGEHISTORYCOLUMNS,
					serviceRequestList);
			
		}
		//changes for hardware pdf Mps 2.1
		else if(pageType.equalsIgnoreCase(ChangeMgmtConstant.HARDWARE_REQUESTS)){
			pdfFileName = DownloadFileLocalizationUtil
			.getLocalizedFileName(ChangeMgmtConstant.PDF_FILETYPE_EXTN,
					ChangeMgmtConstant.HARDWAREDOWNLOADFILENAME,locale);
			cmHistoryListHeader=DownloadFileLocalizationUtil
			.getLocalizedFileHeader(ChangeMgmtConstant.cmHardwareheaderpdf,
					locale).split(",");
			
			generator = new PdfPReportGenerator(
					cmHistoryListHeader, ChangeMgmtConstant.HARDWAREQUESTSCOLUMNSPDF,
					serviceRequestList);
			
		}

		response.setProperty("Content-disposition", "attachment; filename="
				+ pdfFileName);
		response.setContentType("application/pdf");
		
		OutputStream responseOut = response.getPortletOutputStream();
		try {
			generator.generate(responseOut);
			responseOut.flush();
		} finally {
			if (responseOut != null) {
				try {
					responseOut.close();
				} catch (IOException ignored) {
					LOGGER.error("Excepton occured while closing responseOut", ignored);
					throw new IOException(ignored.getLocalizedMessage());
				}
			}
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
	}
	
	
	
	/**.
	 * Display the details of the Service Requests
	 * @param model Model 
	 * @param request RenderRequest  
	 * @param response RenderResponse 
	 * @return String Request details page 
	 * @throws Exception  
	 * @throws LGSDBException  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "action=showRequestDetails")
	public String showRequestDetails(Model model, RenderRequest request,
			RenderResponse response) throws Exception, LGSDBException {
		String METHOD_NAME = "showRequestDetails";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		RequestDetailForm requestForm = new RequestDetailForm();
		String toJSP = ChangeMgmtConstant.CHANGEREQUESTDETAILS;
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		
		String serviceRequestNumber = httpReq.getParameter(ChangeMgmtConstant.SERVICEREQNO);
		requestForm.setSrNumber(serviceRequestNumber);
		
		String timeZoneOffset = httpReq.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		
		String requestType =httpReq.getParameter(ChangeMgmtConstant.REQUESTTYPE);
		String srArea = "";
		String srSubArea = "";
		Boolean assetFlag = false;
		String coveredService = "";
		/*added for CR#12054 -starts*/
		boolean standardUser=false;
		PortletSession portletSession = request.getPortletSession();
		standardUser=PortalSessionUtil.isUserRoleStandardAccess(portletSession);
		portletSession.setAttribute("isStandardUser", standardUser,PortletSession.APPLICATION_SCOPE);
		
		/*added for CR#12054 -ends*/
		LOGGER.debug("STANDARD USER=======>>>"+standardUser);
		if(httpReq.getParameter(ChangeMgmtConstant.SRAREA)!=null){
			srArea = httpReq.getParameter(ChangeMgmtConstant.SRAREA);
		}
		if(httpReq.getParameter("srSubArea")!=null){
			srSubArea = httpReq.getParameter("srSubArea");
		}
		if(httpReq.getParameter("coveredService")!=null){
			coveredService = httpReq.getParameter("coveredService");
		}
		
		LOGGER.debug("srArea---->"+srArea);
		LOGGER.debug("coveredService---->"+coveredService);
		
		
		if(ChangeMgmtConstant.SERVICEREQTYPE.equalsIgnoreCase(requestType)){
			if((ChangeMgmtConstant.AREA_ADDASSET_YES.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_ADDASSET_YES.equalsIgnoreCase(srSubArea))
				||(ChangeMgmtConstant.AREA_ADDASSET_NO.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_ADDASSET_NO.equalsIgnoreCase(srSubArea))
				||(ChangeMgmtConstant.AREA_CHANGEASSET_YES.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_CHANGEASSET_YES.equalsIgnoreCase(srSubArea))
				||(ChangeMgmtConstant.AREA_CHANGEASSET_NO.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_CHANGEASSET_NO.equalsIgnoreCase(srSubArea))
				||(ChangeMgmtConstant.AREA_DECOMMASSET_YES.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_DECOMMASSET_YES.equalsIgnoreCase(srSubArea))
				||(ChangeMgmtConstant.AREA_DECOMMASSET_NO.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_DECOMMASSET_NO.equalsIgnoreCase(srSubArea))||
				(ChangeMgmtConstant.AREA_DECOMMASSET_YES.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_INSTALLDEINSTALL.equalsIgnoreCase(srSubArea))){
				assetFlag=true;
				toJSP = "lgsHistoryDetails/assetChangeRequest";
				if(coveredService != null){
					model.addAttribute("coveredService",coveredService);
				}
				
			}else{
				if("Install".equalsIgnoreCase(srArea) && "BAU".equalsIgnoreCase(srSubArea)){
					toJSP = "lgsHistoryDetails/installReqDetails";
				}else{
					toJSP = "lgsHistoryDetails/changeRequestDetails";
				}
				
				assetFlag=false;
			}
		}else if(ChangeMgmtConstant.SRTYPE_SUPPLYMANAGEMENT.equalsIgnoreCase(requestType)){
			Map<String, String> requestTypeLOVMap=null;
			try {
				requestTypeLOVMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.REQUEST_TYPE.getValue(), request.getLocale());
			
				LOGGER.debug("requestTypeLOVMap --->"+requestTypeLOVMap);
				model.addAttribute("requestTypeLOVMap", requestTypeLOVMap);
				
				Map<String, String> requestAreaLOVMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(), request.getLocale());
				LOGGER.debug("requestAreaLOVMap --->"+requestAreaLOVMap);
				model.addAttribute("requestAreaLOVMap", requestAreaLOVMap);
			} catch (LGSDBException dbExcption) {
				LOGGER.error("LGSBusinessException occurred ::: "+ dbExcption.getMessage());
			}
			
			SiebelLOVListResult requestStatusLOVList = globalService.retrieveSiebelLOVList(ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), null,null));
			/*LOGGER.debug("requestStatusLOVList-->"+requestStatusLOVList);*/
			if(requestStatusLOVList != null){
				LOGGER.debug("request Status LOV list-->"+requestStatusLOVList.getLovList());
				Map<String, String> requestStatusLOVMap = getLovAsMap(requestStatusLOVList);
				LOGGER.debug("requestStatusLOVMap-->"+requestStatusLOVMap);
				model.addAttribute("requestStatusLOVMap", requestStatusLOVMap);
			}
			if(ChangeMgmtConstant.INQUERYAREA.equalsIgnoreCase(srArea) && ((ChangeMgmtConstant.UPLOADSUPPLIESREQUESTS.equalsIgnoreCase(srSubArea))
					||(ChangeMgmtConstant.UPLOADCONSUMABLESRSREQUEST.equalsIgnoreCase(srSubArea))
					||(ChangeMgmtConstant.UPLOADEDCONSUMABLEREQUEST.equalsIgnoreCase(srSubArea)))){				
				toJSP = "lgsHistoryDetails/changeRequestDetails";
			}else{
				toJSP = ChangeMgmtConstant.SUPPLYREQUESTDETAILS;
			}
			
			
			
		}
		//List of exceptions to be displayed to user
		List<String> exceptionList = new ArrayList<String>();
		
		String sourcePage =httpReq.getParameter(ChangeMgmtConstant.SOURCEPAGE);
		requestForm.setSourcePage(sourcePage);
		
		//Clearing the attributes from the request
		httpReq.removeAttribute(ChangeMgmtConstant.SERVICEREQNO);
		httpReq.removeAttribute(ChangeMgmtConstant.TIMEZNOFFSET);
		httpReq.removeAttribute(ChangeMgmtConstant.REQUESTTYPE);
		httpReq.removeAttribute(ChangeMgmtConstant.SOURCEPAGE);
		httpReq.removeAttribute(ChangeMgmtConstant.ASSETROWID);

		LOGGER.debug("requestType---->"+requestType);
		if(ChangeMgmtConstant.SRTYPE_SUPPLYMANAGEMENT.equalsIgnoreCase(requestType)){
			populateSupplyRequestDetails(request, response, model, requestForm, exceptionList, "", timeZoneOffset);
		}
		else if(ChangeMgmtConstant.SERVICEREQTYPE.equalsIgnoreCase(requestType) ){
			LOGGER.debug("assetFlag---->"+assetFlag);
			populateChangeRequestDetails(request, model, requestForm, exceptionList, "",assetFlag,timeZoneOffset);
		}
		if(ChangeMgmtConstant.INQUERYAREA.equalsIgnoreCase(srArea) && ((ChangeMgmtConstant.UPLOADSUPPLIESREQUESTS.equalsIgnoreCase(srSubArea))
				||(ChangeMgmtConstant.UPLOADCONSUMABLESRSREQUEST.equalsIgnoreCase(srSubArea))
				||(ChangeMgmtConstant.UPLOADEDCONSUMABLEREQUEST.equalsIgnoreCase(srSubArea)))){
			if(requestForm.getServiceRequest().getServiceRequestStatus()!= null){
				requestForm.setReqProgress(checkProgress(requestForm.getServiceRequest().getServiceRequestStatus()));//To be changed
				String view = decideCMView(requestForm.getServiceRequest().getArea(), requestForm.getServiceRequest().getSubArea()) ;
				model.addAttribute(ChangeMgmtConstant.PAGEVIEW, view);
			}
			
			
			// Retrieve Service Request Notification List
						if (requestForm.getServiceRequest().getEmailActivities() != null
								&& requestForm.getServiceRequest()
										.getEmailActivities().size() > 0) {
							String srNotifyXML = getXmlOutputGenerator(request.getLocale()).convertServiceRequestNotificationToXML(requestForm.getServiceRequest().getEmailActivities());
							srNotifyXML = srNotifyXML
									.replace("\n", "");
							requestForm.setSrNotifyXML(srNotifyXML);
						}else{
							requestForm.setSrNotifyXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
						}
		}
		//Added For CR 10483 and 13824 June relase start
		if("Install".equalsIgnoreCase(srArea) && "BAU".equalsIgnoreCase(srSubArea)){
			model.addAttribute("coveredService",coveredService);
			String[] activityGeneratorPattern = new String[] {
					"partNumber", "description", "orderQuantity"}; 
			if(requestForm.getServiceRequest()!=null && requestForm.getServiceRequest().getActivitywebUpdateActivities() != null && 
					requestForm.getServiceRequest().getActivitywebUpdateActivities().size() > 0){
				
				List<ServiceRequestActivity> sActivityList = requestForm.getServiceRequest().getActivitywebUpdateActivities();
				LOGGER.debug("sActivityList" +sActivityList);
				String serviceRequestTechnicianXML = getXmlOutputGenerator(request
						.getLocale()).getActivityDetails(sActivityList,
								sActivityList.size(), 0,
								activityGeneratorPattern);
				LOGGER.debug("serviceRequestTechnicianXML" + serviceRequestTechnicianXML);
				serviceRequestTechnicianXML = serviceRequestTechnicianXML.replaceAll("\n", "");
				requestForm.setServiceRequestTechnicianXML(serviceRequestTechnicianXML);
				model.addAttribute("SRTechnicianXMLFlag",true);
			}
			else  {
				
				requestForm.setServiceRequestTechnicianXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
				model.addAttribute("SRTechnicianXMLFlag",false);
			}
		}
		//Added For CR 10483 and 13824 June relase end
		//Translated values for Area
		if(requestForm.getServiceRequest().getArea()!=null){
		String area = commonController.getLocalizeSiebelValue(requestForm.getServiceRequest().getArea().getValue(),SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(),request.getLocale());
		requestForm.getServiceRequest().getArea().setName(requestForm.getServiceRequest().getArea().getValue());
		requestForm.getServiceRequest().getArea().setValue(area);
		}
		//Translated values for Sub Area
		if(requestForm.getServiceRequest().getSubArea()!=null){
		String subArea = commonController.getLocalizeSiebelValue(requestForm.getServiceRequest().getSubArea().getValue(),SiebelLocalizationOptionEnum.REQUEST_SUBAREA.getValue(),request.getLocale());
		requestForm.getServiceRequest().getSubArea().setName(requestForm.getServiceRequest().getSubArea().getValue());
		requestForm.getServiceRequest().getSubArea().setValue(subArea);
		}
		//Translated values for Status
		String status = commonController.getLocalizeSiebelValue(requestForm.getServiceRequest().getServiceRequestStatus(),SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(),request.getLocale());
		requestForm.getServiceRequest().setServiceRequestStatus(status);
		
		if(requestForm.getServiceRequest().getServiceRequestDate()!= null){			
			String creationDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(requestForm.getServiceRequest().getServiceRequestDate(), Float.parseFloat(timeZoneOffset)), true, request.getLocale());
			LOGGER.debug("creationDate date ---->"+creationDate);
			requestForm.setCreationDate(creationDate);
			
		}
		
		if(requestForm.getServiceRequest()!= null && requestForm.getServiceRequest().getArea()!=null 
				&& requestForm.getServiceRequest().getArea().getValue().equalsIgnoreCase("Consumables Return")){
			
			
			if(requestForm.getServiceRequest().getServiceRequestStatus()!= null){
				requestForm.setReqProgress(checkProgress(requestForm.getServiceRequest().getServiceRequestStatus()));//To be changed
			}
			LOGGER.debug("Its a returns request and should be redirected to returnsRequestDetails.jsp");
			
			
			toJSP = ChangeMgmtConstant.RETURNSREQUESTDETAILS;
		}
		
		
		/**--Setting account details to session --Start--**/
		
		//Changes for defect 
		
		Map<String,String> accountDetailsValues =(Map<String,String>)portletSession.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		if(accountDetailsValues==null){
			accountDetailsValues=new HashMap<String, String>();
		}
		
		LOGGER.debug(ChangeMgmtConstant.ACCOUNTID + " : "+ requestForm.getServiceRequest().getAccountId() + " " + ChangeMgmtConstant.ACCOUNTNAME+ " : "+ requestForm.getServiceRequest().getAccountName() + " " + ChangeMgmtConstant.COUNTRY + " : " + requestForm.getServiceRequest().getAccountCountry());
		accountDetailsValues.put(ChangeMgmtConstant.ACCOUNTID, requestForm.getServiceRequest().getAccountId());
		accountDetailsValues.put(ChangeMgmtConstant.ACCOUNTNAME, requestForm.getServiceRequest().getAccountName());
		accountDetailsValues.put(ChangeMgmtConstant.COUNTRY, requestForm.getServiceRequest().getAccountCountry());
				
		portletSession.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accountDetailsValues ,PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("request values " + accountDetailsValues.toString());
		/**Setting account details to session --End--**/
		
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID(ChangeMgmtConstant.DOWNLOADATTACHMENT);
		model.addAttribute(ChangeMgmtConstant.DOWNLOADATTACHMENT, resURL.toString());

		model.addAttribute(ChangeMgmtConstant.REQUESTFORM, requestForm);
		model.addAttribute(ChangeMgmtConstant.TIMEZNOFFSET, timeZoneOffset);
		
		
		LOGGER.debug(" MDM Level is ----"+ PortalSessionUtil.getMdmLevel(portletSession));
		model.addAttribute(ChangeMgmtConstant.MDMLEVELFROMDETAILS, PortalSessionUtil.getMdmLevel(portletSession));
		LOGGER.debug("HistoryController.showRequestDetails Exit : toJSP--->"+toJSP);
		//Changes for back page from DeviceFinder
		if(httpReq.getParameter(ChangeMgmtConstant.ASSET_ROWID)!=null && httpReq.getParameter(ChangeMgmtConstant.ASSET_ROWID)!=""
			&& httpReq.getParameter(ChangeMgmtConstant.ISDEVICE_BOOKMARKED)!=null && httpReq.getParameter(ChangeMgmtConstant.ISDEVICE_BOOKMARKED)!=""){
			
			model.addAttribute(ChangeMgmtConstant.ASSET_ROWID,httpReq.getParameter(ChangeMgmtConstant.ASSET_ROWID) );
			model.addAttribute(ChangeMgmtConstant.ISDEVICE_BOOKMARKED,httpReq.getParameter(ChangeMgmtConstant.ISDEVICE_BOOKMARKED));
			model.addAttribute(ChangeMgmtConstant.MODELLINKCLICKED, httpReq.getParameter(ChangeMgmtConstant.LINKCLICKED));
			httpReq.removeAttribute(ChangeMgmtConstant.ASSET_ROWID);
			httpReq.removeAttribute(ChangeMgmtConstant.ISDEVICE_BOOKMARKED);
			httpReq.removeAttribute(ChangeMgmtConstant.LINKCLICKED);
		}
		//Ends
		if(toJSP.equalsIgnoreCase("lgsHistoryDetails/changeRequestDetails") || toJSP.equalsIgnoreCase("lgsHistoryDetails/assetChangeRequest")){
			if(requestForm.getServiceRequest().getServiceRequestStatus()!= null){
							requestForm.setReqProgress(checkProgressForChangeManagement(requestForm.getServiceRequest().getServiceRequestStatus()));//To be changed---ShippedStatus
							LOGGER.debug("ServiceRequestStatus---->"+requestForm.getServiceRequest().getServiceRequestStatus());
						}
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		
		return toJSP;
		
	}
	
	/**.
	 * Redirects back to history page
	 * @param model Model 
	 * @param request RenderRequest  
	 * @param response RenderResponse 
	 * @return String History page name 
	 * @throws Exception  
	 * @throws LGSDBException   
	 */
	@RequestMapping(params = "action=backToHistory")
	public String backToHistory(Model model, RenderRequest request,
			RenderResponse response) throws Exception, LGSDBException {
		String METHOD_NAME = "backToHistory";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		String toJSP = ChangeMgmtConstant.VIEWALLREQUESTHISTORY;
		
		String requestTypeStr = request.getParameter(ChangeMgmtConstant.REQUESTTYPESTR);
		model.addAttribute(ChangeMgmtConstant.REQUESTTYPESTR, requestTypeStr);
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		 //For MPS Phase 2.1
		return showCMHistoryPage(model, request, response);
		
		
		
	}
	
	/**
	 *   
	 * @param request 
	 * @param model 
	 * @param requestForm 
	 * @param exceptionList 
	 * @param source 
	 * @param assetFlag 
	 * @param timeZoneOffset 
	 * @throws Exception 
	 * @throws LGSDBException 
	 */
	private void populateChangeRequestDetails(RenderRequest request, Model model, 
			RequestDetailForm requestForm , List<String> exceptionList, String source, Boolean assetFlag,String timeZoneOffset) throws Exception, LGSDBException{
		String METHOD_NAME = "populateChangeRequestDetails";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		String userSegment = PortalSessionUtil.getUserType(request.getPortletSession());
		/*LOGGER.debug("User Type ---->"+userSegment);*/
		
		RequestContract contract = new RequestContract();
		contract.setMadcServiceRequestFlag(true);//Added for defct 10529
		contract.setServiceRequestNumber(requestForm.getSrNumber());
		contract.setSessionHandle(crmSessionHandle);
		if(userSegment.equalsIgnoreCase(ChangeMgmtConstant.VENDORREQTYPE)){
			contract.setVisibilityRole(ChangeMgmtConstant.USERTYPE_PARTNER);
		}
		else{
			contract.setVisibilityRole(userSegment);
		}
		
		LOGGER.debug("SrNumber ::: "+ requestForm.getSrNumber());
		
		RequestResult requestResult = new RequestResult();
		try{
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LOGGER);
			LOGGER.info("end printing lex loggger");
			long timeBeforeCall=System.currentTimeMillis();
			requestResult = requestTypeService.retrieveSupplyRequestDetail(contract);
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_RETRIEVECMDETAIL, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
			LOGGER.info("end printing lex loggger");
		
		}catch(Exception e){
			LOGGER.error("Exception occurred ::: "+ e.getMessage());
			requestResult.setServiceRequest(new ServiceRequest());
			exceptionList.add(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.historyDetails.exception", request.getLocale())+" "+ requestForm.getSrNumber());
			
		}
		Map<String, String> pageCountsMap=null;
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), request.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		requestForm.setServiceRequest(requestResult.getServiceRequest());
		if(assetFlag){
			LOGGER.debug("assetFlag ::: "+ assetFlag);
			//added for page counts
			String pageCountsString="";
			if(requestForm.getServiceRequest().getPageCounts() != null){
			if(requestForm.getServiceRequest().getPageCounts().size()>0){
			 pageCountsString = getXmlOutputGenerator(request
					.getLocale()).pageCountsXMLForAsset(requestForm.getServiceRequest().getPageCounts(),requestForm.getServiceRequest().getPageCounts().size(),0,pageCountsMap,timeZoneOffset,request.getLocale());
			pageCountsString = pageCountsString.replace("\n", "");
			}else{
				pageCountsString=getXmlOutputGenerator(request.getLocale()).generateEmptyXML();
			}}
			model.addAttribute("pageCountsString", pageCountsString);
			//page counts end
			
			// for move to address
			
			//Added for CR 16731 LBS 
			  String moveToAddressGrouped = requestForm.getServiceRequest().getAsset().getMoveToAddressGrouped();
			  LOGGER.debug("address grouped == "+moveToAddressGrouped);
			  if(StringUtils.isNotBlank(moveToAddressGrouped)){
				  requestForm.getServiceRequest().getAsset().setInstallAddress(createAddressfromString(moveToAddressGrouped));				  
			  }
			  //Ends Added for CR 16731 LBS 
			  
			  		
			// retrieve Technician List
				String[] technicianGeneratorPatterns = new String[] { ChangeMgmtConstant.ACTIVITYDATE,
						ChangeMgmtConstant.ACTIVITYSTATUS,ChangeMgmtConstant.ACTIVITYDESCRIPTION,ChangeMgmtConstant.ACTIVITYSERIALNUMBER};
				if (requestResult.getServiceRequest()
						.getActivitywebUpdateActivities() != null
						&& requestResult.getServiceRequest()
								.getActivitywebUpdateActivities().size() > 0) {
					/*LOGGER.debug("!!!!!!!!!!!!!!!That means activity web update activity value is not null");*/
					List<ServiceRequestActivity> sActivityList = assembleServiceRequestActivity(requestResult.getServiceRequest().getActivitywebUpdateActivities());
					String serviceRequestTechnicianXML = getXmlOutputGenerator(request
							.getLocale()).generate(sActivityList,
									requestResult.getServiceRequest()
									.getActivitywebUpdateActivities().size(), 0,
							technicianGeneratorPatterns);
					serviceRequestTechnicianXML = serviceRequestTechnicianXML.replace(
							"\n", "");
					requestForm
							.setServiceRequestTechnicianXML(serviceRequestTechnicianXML);
					
		
				}
				if(requestResult.getServiceRequest().getServiceActivityStatus()!= null){
					requestForm.setServiceRequestTechnicianProgress(checkTechnicianProgress(requestResult.getServiceRequest().getServiceActivityStatus()));
					LOGGER.debug("Activity status:: "+requestResult.getServiceRequest().getServiceActivityStatus());
				}
				Map<String, String> deviceContactTypeMap=null;
				try{
					deviceContactTypeMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONTACT_TYPE_FOR_ASSET.getValue(), request.getLocale());
					LOGGER.debug("deviceContactTypeMap = "+ deviceContactTypeMap);
				}catch (Exception e) {
					LOGGER.debug("catch"+ e.getMessage());
				}
				model.addAttribute("deviceContactTypeList", deviceContactTypeMap);
		}		
		if(requestForm.getServiceRequest().getServiceRequestStatus()!= null){
			requestForm.setReqProgress(checkProgress(requestForm.getServiceRequest().getServiceRequestStatus()));//To be changed
			
			
		}
		
		
			// Retrieve Service Request Notification List
			if (requestResult.getServiceRequest().getEmailActivities() != null
					&& requestResult.getServiceRequest()
							.getEmailActivities().size() > 0) {
				String srNotifyXML = getXmlOutputGenerator(request.getLocale()).convertServiceRequestNotificationToXML(requestResult.getServiceRequest().getEmailActivities());
				srNotifyXML = srNotifyXML
						.replace("\n", "");
				requestForm.setSrNotifyXML(srNotifyXML);
			}else{
				requestForm.setSrNotifyXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
			}
			
		if(!source.equalsIgnoreCase(ChangeMgmtConstant.DETAIL_VIEW_POPUP)){
			// Retrieve Service Request Status history List
			String[] statusGeneratorPatterns = new String[] { ChangeMgmtConstant.ACTIVITYDATE,
					ChangeMgmtConstant.ACTIVITYSTATUS, ChangeMgmtConstant.ACTIVITYDESCRIPTION };
			if (requestResult.getServiceRequest()
					.getServicewebUpdateActivities() != null
					&& requestResult.getServiceRequest()
							.getServicewebUpdateActivities().size() > 0) {
				List<ServiceRequestActivity> activityList = assembleServiceRequestActivity(requestResult.getServiceRequest().getServicewebUpdateActivities());
				String srStausXML = getXmlOutputGenerator(request
						.getLocale()).generate(activityList,
								requestResult.getServiceRequest()
								.getServicewebUpdateActivities().size(), 0,
						statusGeneratorPatterns);
				srStausXML = srStausXML.replace("\n", "");
				requestForm
						.setSrStausXML(srStausXML);
			}else{
				requestForm.setSrStausXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
			}
		  }
			String view = decideCMView(requestForm.getServiceRequest().getArea(), requestForm.getServiceRequest().getSubArea()) ;
			model.addAttribute(ChangeMgmtConstant.PAGEVIEW, view);
			String updateType ="";
			if(requestForm.getServiceRequest().getSubArea()!= null){
				String subArea1 = requestForm.getServiceRequest().getSubArea().getValue();
				if(subArea1.indexOf(ASSET)>-1){
					updateType = ASSET;
				}
				else if(subArea1.indexOf(CONTACT)>-1){
					updateType = CONTACT;
				}
				else if(subArea1.indexOf(ADDRESS)>-1){
					updateType = ADDRESS;
				}
				else if(subArea1.indexOf(CHL)>-1){
					updateType = CHL;
				}
				//added for new chl area/sub-area
				else if(subArea1.indexOf(CUSTOMERHIERARCHY)>-1){
					updateType = CHL;
				}
				//Added defect #7853
				else if(subArea1.indexOf(ChangeMgmtConstant.CHANGEASSETSUBAREA_MOVETOSTORAGE)>-1){
					updateType = ChangeMgmtConstant.CHANGEASSETSUBAREA_MOVETOSTORAGE;
				}
				else if(subArea1.indexOf(ChangeMgmtConstant.CHANGEASSETSUBAREA_INSTALLFROMSTORAGE)>-1){
					updateType = ChangeMgmtConstant.CHANGEASSETSUBAREA_INSTALLFROMSTORAGE;
				}
				else if(subArea1.indexOf(ChangeMgmtConstant.CHANGEASSETSUBAREA_MOVEINSTALLLOCATIONS)>-1){
					updateType = ChangeMgmtConstant.CHANGEASSETSUBAREA_MOVEINSTALLLOCATIONS;
				}
				else if(subArea1.indexOf(ChangeMgmtConstant.MOVEPHYSICAL)>-1){
					updateType = ChangeMgmtConstant.MOVEPHYSICAL;
				}
				else if(subArea1.indexOf(ChangeMgmtConstant.CHANGEASSETSUBAREA_MOVESTORAGELOCATIONS)>-1){
					updateType = ChangeMgmtConstant.CHANGEASSETSUBAREA_MOVESTORAGELOCATIONS;
				}	
				//ends here
				else{
					updateType = OTHERS;
				}
				model.addAttribute(ChangeMgmtConstant.UPDATETYPEFLAG, updateType);
			}
		
		List<Attachment> attList = requestForm.getServiceRequest().getAttachments();
		try{
		if(attList != null){
			LOGGER.debug("attList size ---->"+attList.size());
			for(Attachment file : attList){
				String fileName = file.getDisplayAttachmentName();
				int lastIndex = fileName.lastIndexOf("_");
				if( lastIndex != -1){
					String timestamp = fileName.substring(lastIndex + 1, fileName.length());
					LOGGER.debug("Time stamp string ---->"+ timestamp);
					LOGGER.debug("Time stamp string length ---->"+ timestamp.length());
					if(timestamp.length() == 13){
							fileName = fileName.substring(0, lastIndex);
							file.setDisplayAttachmentName(fileName);
							LOGGER.debug("After the new file is set in the attachment bean");
					}
				}
			}
		}
		}catch(Exception ex)
			{
				logStackTrace(ex);
			}
		requestForm.setAttachList(attList);

		if(!exceptionList.isEmpty()){
			model.addAttribute("exceptionList", exceptionList);	
		}	
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
	}
	
	/**.
	 * Populates data for supply request details page.
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @param model Model
	 * @param requestForm RequestDetailForm
	 * @param exceptionList List<String>
	 * @param source String
	 * @param timeZoneOffset String
	 * @throws Exception 
	 */
	private void populateSupplyRequestDetails(RenderRequest request, RenderResponse response,
			Model model, RequestDetailForm requestForm, List<String> exceptionList, String source, String timeZoneOffset) throws Exception{
		String METHOD_NAME = "populateSupplyRequestDetails";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		String userSegment = PortalSessionUtil.getUserType(request.getPortletSession());
		model.addAttribute(ChangeMgmtConstant.USERSEGMENT, userSegment);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		RequestContract contract = new RequestContract();
		contract.setServiceRequestNumber(requestForm.getSrNumber());
		contract.setMadcServiceRequestFlag(false);//Added for defct 10529
		if(userSegment.equalsIgnoreCase(ChangeMgmtConstant.VENDORREQTYPE)){
			contract.setVisibilityRole(ChangeMgmtConstant.USERTYPE_PARTNER);
		}
		else{
			contract.setVisibilityRole(userSegment);
		}
		
		
		contract.setSessionHandle(crmSessionHandle);
		RequestResult requestResult = new RequestResult();
		try{
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LOGGER);
			LOGGER.info("end printing lex loggger");
			long timeBeforeCall=System.currentTimeMillis();
			requestResult = requestTypeService.retrieveSupplyRequestDetail(contract);
			boolean showUserMsg = false;
			if("Consumable SVC Parts Request".equalsIgnoreCase(requestResult.getServiceRequest().getArea().getValue())){
				showUserMsg = true;
			}
			LOGGER.debug("SHIP TO DEFAULT VALUE IS ----------------->>>>>>>>>> "+requestResult.getServiceRequest().getShipToDefault());
			String shipToDefault = requestResult.getServiceRequest().getShipToDefault();
			if(null != shipToDefault && ("partner to provide".equalsIgnoreCase(shipToDefault) || "Ship to Service Partner".equalsIgnoreCase(shipToDefault)) && showUserMsg){
				model.addAttribute("showUserMsg", true);
			}
			else{
				model.addAttribute("showUserMsg", false);
			}
			//Done for Defect 3924- Jan Release--START
			sharedPortletController.assembleDevice(requestResult.getServiceRequest().getAsset(), request.getLocale());
			//Done for Defect 3924- Jan Release--END
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_RETRIEVESUPPLYREQUESTDETAIL, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
			LOGGER.info("end printing lex loggger");
		}catch(Exception e){
			LOGGER.error("Exception occurred in Printing supply request details ::: "+ e.getMessage());
			requestResult.setServiceRequest(new ServiceRequest());
			exceptionList.add(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.historyDetails.exception", request.getLocale())+" "+ requestForm.getSrNumber());
		}
		requestForm.setServiceRequest(requestResult.getServiceRequest());
		
		LOGGER.debug("In populateSupplyRequestDetails before list");
			// Retrieve Service Request Status history List
			String[] statusGeneratorPatterns = new String[] { "activityDate",
					"activityStatus", "activityDescription" };
			if (requestResult.getServiceRequest()
					.getServicewebUpdateActivities() != null
					&& requestResult.getServiceRequest()
							.getServicewebUpdateActivities().size() > 0) {
				List<ServiceRequestActivity> activityList = assembleServiceRequestActivity(requestResult.getServiceRequest().getServicewebUpdateActivities());
				String srStausXML = getXmlOutputGenerator(request
						.getLocale()).generate(activityList,
								requestResult.getServiceRequest()
								.getServicewebUpdateActivities().size(), 0,
						statusGeneratorPatterns);
				srStausXML = srStausXML.replace("\n", "");
				requestForm
						.setSrStausXML(srStausXML);
			}else{
				requestForm.setSrStausXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
			}
		
			// retrieveServiceRequestHistoryList
			ResourceURL srHistoryURL = response.createResourceURL();
			srHistoryURL.setResourceID(ChangeMgmtConstant.RETRIEVESRHISTORYLIST);
			ResourceURL associatedRequestHistoryURL = response.createResourceURL();
			associatedRequestHistoryURL.setResourceID(ChangeMgmtConstant.RETRIEVEASSOC_SRHISTORYLIST);
			if(requestResult.getServiceRequest().getAsset() != null){
			if(requestResult.getServiceRequest().getAsset().getAssetId() != null){
				String accountRowId =requestResult.getServiceRequest().getAccountId(); 
				String assetRowId = requestResult.getServiceRequest().getAsset().getAssetId();
				
				requestForm.setSrHistoryListXML(srHistoryURL
						.toString()
						+ "&assetRowId="
						+ assetRowId
						+ "&accountRowId="
						+ accountRowId
						+ "&serviceRequestNumber="
						+ requestResult.getServiceRequest().getServiceRequestNumber()
						+ "&timeZoneOffset="
						+ timeZoneOffset);
				
				// populate control panel URL
				if (!StringUtil.isStringEmpty(requestResult.getServiceRequest().getAsset().getHostName())) {
					requestResult.getServiceRequest().getAsset().setControlPanelURL("http://" + requestResult.getServiceRequest().getAsset().getHostName());
				} else if (!StringUtil.isStringEmpty(requestResult.getServiceRequest().getAsset().getIpAddress())) {
					requestResult.getServiceRequest().getAsset().setControlPanelURL("http://" + requestResult.getServiceRequest().getAsset().getIpAddress());
				}
				
				ProductImageContract productImageContract = new ProductImageContract();
				productImageContract.setPartNumber(requestResult.getServiceRequest().getAsset().getProductTLI());
				ProductImageResult productImageResult = new ProductImageResult();
				try {
					productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
				} catch (Exception e) {
					LOGGER.error("Exception occurred in productImageResult= "+e.getMessage());
				}
				requestResult.getServiceRequest().getAsset().setProductImageURL(productImageResult
						.getProductImageUrl());
				
			}
			}
			LOGGER.debug("url is "+associatedRequestHistoryURL);
			
			requestForm.setAssociatedRequestHistoryListXML(associatedRequestHistoryURL
					.toString()
					+"&serviceRequestNumber="
					+ requestResult.getServiceRequest().getServiceRequestNumber()
					+ "&timeZoneOffset="
					+ timeZoneOffset);
			
			Map shipments = this.getServiceRequestShipments(requestResult
					.getServiceRequest().getShipmentOrderLines(), request
					.getLocale());
	
			List<ShipmentForm> serviceRequestShipments = (List<ShipmentForm>) shipments.get(ChangeMgmtConstant.SHIPMENTFORM);
			if (serviceRequestShipments != null
					&& serviceRequestShipments.size() > 0) {
				requestForm.setServiceRequestShipments(serviceRequestShipments);
			}
					
			//Populate pending shipment grid
			if(requestResult.getServiceRequest().getPendingShipments() != null && !requestResult.getServiceRequest().getPendingShipments().isEmpty()){
				LOGGER.debug("PendingShipments section starts ");
				this.setPendingQty(requestResult.getServiceRequest().getPendingShipments());
				requestForm.setPendingRequest(getPendingShipmentData(requestResult.getServiceRequest().getPendingShipments(), request.getLocale()));
			}
			else{
				List<ServiceRequestOrderLineItem> orderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
				for (Part part : LangUtil.notNull(requestResult.getServiceRequest().getParts())) {
						LOGGER.debug("Part Status :::" + part.getStatus());
						if(!ChangeMgmtConstant.STATUS_ATHORIZED.equalsIgnoreCase(part.getStatus())){
							ServiceRequestOrderLineItem item = new ServiceRequestOrderLineItem();
							item.setPartnumber(part.getPartNumber());
							LOGGER.debug("Implicit flag:: " + part.getImplicitFlag());
							if(part.getImplicitFlag()){
								item.setProductDescription(ChangeMgmtConstant.LEXMARK_RECOMMENTED);
							}
							else{
								item.setProductDescription(part.getDescription());
							}
							
							item.setStatus(part.getStatus());
							item.setPartType(part.getPartType());
							if(part.getOrderQuantity() != null){
								item.setQuantity(part.getOrderQuantity());
							}	
							item.setPrice(part.getPrice());
							item.setCurrency(part.getCurrency());
							orderLineItems.add(item);
						}else{
							ServiceRequestOrderLineItem itemAuthorized = new ServiceRequestOrderLineItem();
							int itemStatus = 0;
							for(ServiceRequestOrderLineItem shippedItems : requestResult.getServiceRequest().getShipmentOrderLines()){
								if(shippedItems.getPartName().equalsIgnoreCase(part.getPartNumber())){
									itemStatus = 1;
								}
							}
							if(itemStatus == 0){
								itemAuthorized.setPartnumber(part.getPartNumber());
								LOGGER.debug("Implicit flag:: " + part.getImplicitFlag());
								if(part.getImplicitFlag()){
									itemAuthorized.setProductDescription(ChangeMgmtConstant.LEXMARK_RECOMMENTED);
								}
								else{
									itemAuthorized.setProductDescription(part.getDescription());
								}
								
								itemAuthorized.setStatus(part.getStatus());
								itemAuthorized.setPartType(part.getPartType());
								if(part.getOrderQuantity() != null){
									itemAuthorized.setQuantity(part.getOrderQuantity());
								}	
								itemAuthorized.setPrice(part.getPrice());
								itemAuthorized.setCurrency(part.getCurrency());
								orderLineItems.add(itemAuthorized);
							}
							
						}
						
					
				}
				/*LOGGER.debug("getPendingShipments is not blank ");*/
				requestForm.setPendingRequest(getPendingShipmentData(orderLineItems, request.getLocale()));
			}
			
			if(requestResult.getServiceRequest().getCancelledParts() != null && !requestResult.getServiceRequest().getCancelledParts().isEmpty()){
				
				List<ServiceRequestOrderLineItem> cancelledItems = new ArrayList<ServiceRequestOrderLineItem>();
				for (Part part : LangUtil.notNull(requestResult.getServiceRequest().getCancelledParts())) {
						
						ServiceRequestOrderLineItem item = new ServiceRequestOrderLineItem();
						item.setProductTLI(part.getPartNumber());
						item.setProductDescription(part.getDescription());
						item.setStatus(part.getStatus());
						item.setQuantity(part.getOrderQuantity());
						cancelledItems.add(item);
					
				}
				 
				String[] cancelledItemsGeneratorPatterns = new String[] {PRODUCT_TLI, PROD_DESCRIPTN, QUANTITY};
						
						String cancelledItemsXML = getXmlOutputGenerator(
								request.getLocale()).generate(cancelledItems, cancelledItems.size(),
								0, cancelledItemsGeneratorPatterns);
						cancelledItemsXML = cancelledItemsXML
								.replace("\n", "");
						requestForm.setCancelledPartsXML(StringUtil.encodeSingleQuote(
								cancelledItemsXML));
			}
			
			// retrieve Technician List
			String[] technicianGeneratorPatterns = new String[] { ChangeMgmtConstant.ACTIVITYDATE,
					ChangeMgmtConstant.ACTIVITYSTATUS, ChangeMgmtConstant.ACTIVITYDESCRIPTION };
			
			if (requestResult.getServiceRequest()
					.getActivitywebUpdateActivities() != null
					&& requestResult.getServiceRequest()
							.getActivitywebUpdateActivities().size() > 0) {
				/*LOGGER.debug("!!!!!!!!!!!!!!!That means activity web update activity value is not null");*/
				List<ServiceRequestActivity> sActivityList = assembleServiceRequestActivity(requestResult.getServiceRequest().getActivitywebUpdateActivities());
				String serviceRequestTechnicianXML = getXmlOutputGenerator(request
						.getLocale()).generate(sActivityList,
								requestResult.getServiceRequest()
								.getActivitywebUpdateActivities().size(), 0,
						technicianGeneratorPatterns);
				serviceRequestTechnicianXML = serviceRequestTechnicianXML.replace(
						"\n", "");
				requestForm
						.setServiceRequestTechnicianXML(serviceRequestTechnicianXML);
				
	
			}
			if(requestResult.getServiceRequest().getServiceActivityStatus()!= null){
				requestForm.setServiceRequestTechnicianProgress(checkTechnicianProgress(requestResult.getServiceRequest().getServiceActivityStatus()));
				LOGGER.debug("Activity status:: "+requestResult.getServiceRequest().getServiceActivityStatus());
			}
					//}
		
		//set Attachment list
		List<Attachment> attList = requestForm.getServiceRequest().getAttachments();
		
		try{
		if(attList != null){
			LOGGER.debug("attList size ---->"+attList.size());
			
			for(Attachment file : attList){
				String fileName = file.getDisplayAttachmentName();
				int lastIndex = fileName.lastIndexOf("_");
				if( lastIndex != -1){
					String timestamp = fileName.substring(lastIndex + 1, fileName.length());
					LOGGER.debug("Time stamp string ---->"+ timestamp);
					LOGGER.debug("Time stamp string length ---->"+ timestamp.length());
					if(timestamp.length() == 13){
							fileName = fileName.substring(0, lastIndex);
							file.setDisplayAttachmentName(fileName);
							LOGGER.debug("After the new file is set in the attachment bean");
					}
				}
			}
		}
		}catch(Exception ex)
		{
			logStackTrace(ex);
		}
			requestForm.setAttachList(attList);
			
		if(!exceptionList.isEmpty()){
			model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION, exceptionList);	
		}
		/********   added for return details page**********/
		// Retrieve Service Request Notification List
		if (requestResult.getServiceRequest().getEmailActivities() != null
				&& requestResult.getServiceRequest()
						.getEmailActivities().size() > 0) {
			String srNotifyXML = getXmlOutputGenerator(request.getLocale()).convertServiceRequestNotificationToXML(requestResult.getServiceRequest().getEmailActivities());
			srNotifyXML = srNotifyXML
					.replace("\n", "");
			requestForm.setSrNotifyXML(srNotifyXML);
		}else{
			requestForm.setSrNotifyXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
		}
		Map<String, String> pageCountsMap=null;
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), request.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		List<PageCounts> pageCountList = requestForm.getServiceRequest().getPageCounts();
		
		if(pageCountList != null){
			String pageCountsString = getXmlOutputGenerator(request
					.getLocale()).pageCountsXMLForAsset(pageCountList,pageCountList.size(),0,pageCountsMap,timeZoneOffset,request.getLocale());
			pageCountsString = pageCountsString.replace("\n", "");
			model.addAttribute("pageCountsString", pageCountsString);
			
			}
	}
	
	/**. Gets input stream for download attachment in details page.
	 * 
	 * @param attchmntName String 
	 * @param identifier String 
	 * @param crmSessionHandle CrmSessionHandle  
	 * @return InputStream 
	 */
	private InputStream getInputStreamForAttachment(String attchmntName, String identifier,
			CrmSessionHandle crmSessionHandle){
		String METHOD_NAME = "getInputStreamForAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		try{
				LOGGER.debug("AttchmntName : " + attchmntName + " Identifier : " +identifier);
				
				AttachmentContract attContract = new AttachmentContract();
				attContract.setUserFileName(attchmntName);
				attContract.setRequestType(ChangeMgmtConstant.ATTACHMENTREQTYPE);
				attContract.setIdentifier(identifier);
				attContract.setSessionHandle(crmSessionHandle);
				LOGGER.debug("AttachmentContract >> UserFileName:: "+attContract.getUserFileName()
				+"AttachmentContract >> RequestType:: "+attContract.getRequestType()
				+"AttachmentContract >> Identifier:: "+attContract.getIdentifier());
									
				LOGGER.debug("attchmntName while setting in session:: "+attchmntName);
				LOGGER.info("start printing lex logger");
				ObjectDebugUtil.printObjectContent(attContract,LOGGER);
				LOGGER.info("end printing lex loggger");
				long timeBeforeCall=System.currentTimeMillis();
				AttachmentResult downldReslt = attachmentService.downloadAttachment(attContract);
				long timeAfterCall=System.currentTimeMillis();
				LOGGER.info("start printing lex logger");
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_DOWNLOADATTACHMENT, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,attContract);
				LOGGER.info("end printing lex loggger");
				LOGGER.debug("downldReslt.getFileStream() : " + downldReslt.getFileStream()
				+ " downldReslt.getFileName() : " + downldReslt.getFileName());
				if(downldReslt.getFileStream() == null){
					throw new Exception();
				}
				else{
					LOGGER.exit(CLASS_NAME, METHOD_NAME );
					return downldReslt.getFileStream();
				}
			
		}catch (Exception e) {
			LOGGER.debug("Exception occurred in attachment retrieval ::: "+ e.getMessage());
			return null;
		}
		
	}
	
	/**.
	 * Populated pending shipment data 
	 * @param pendingShipments List<ServiceRequestOrderLineItem> 
	 * @param locale Locale 
	 * @return ShipmentForm 
	 */
	private ShipmentForm getPendingShipmentData(List<ServiceRequestOrderLineItem> pendingShipments, Locale locale){
		String METHOD_NAME = "getPendingShipmentData";
		
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		ShipmentForm shipForm = new ShipmentForm();
		String[] pendingShipmentsGeneratorPatterns = new String[] {
				PARTNO, PROD_DESCRIPTN, PART_TYPE, PENDING_QUANTITY, DEVICE_TYPE, PRICE}; //For MPS Phase 2.1
		        
				String pendingShipmentsXML = getXmlOutputGenerator(
						locale).generateXMLPendingShip(pendingShipments, pendingShipments.size(),
						0, pendingShipmentsGeneratorPatterns);
				if(pendingShipmentsXML != null){
					pendingShipmentsXML = pendingShipmentsXML
					.replace("\n", "");
					shipForm.setShipmentXML(StringUtil.encodeSingleQuote(
							pendingShipmentsXML));
				}else{
					shipForm.setShipmentXML(pendingShipmentsXML);
				}
				
				LOGGER.debug("getPendingShipmentData ShipmentXML= "+shipForm.getShipmentXML());
				LOGGER.exit(CLASS_NAME, METHOD_NAME );
		return shipForm;
		
	}
	
	/**
	 * Calculates the pending quantity considering the actual pending amount and the back ordered qty
	 * @param pendingShipments 
	 */
	private void setPendingQty(List<ServiceRequestOrderLineItem> pendingShipments){
		String METHOD_NAME = "setPendingQty";
		
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		for(ServiceRequestOrderLineItem lineItem : pendingShipments){
			int pendingQty = lineItem.getPendingQuantity();
			int backOrderQty = lineItem.getBackOrderQuantity();
			int actualQty = 0;
			
			if(pendingQty != 0 && backOrderQty != 0){
				actualQty = pendingQty + backOrderQty;
			}
			else if(pendingQty == 0){
				actualQty = backOrderQty;
			}
			else{
				actualQty = pendingQty;
			}
			LOGGER.debug("Pending Qty::"+ pendingQty + " :: Back Order Qty::"+ backOrderQty + " :: Actual Pending Qty::"+ actualQty);
			lineItem.setQuantity(String.valueOf(actualQty));			
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );		
	}
	
	/**. Populated shipment data
	 * 
	 * @param shipmentOrderLines List<ServiceRequestOrderLineItem> 
	 * @param locale Locale 
	 * @return Map 
	 */
	private Map getServiceRequestShipments(
			List<ServiceRequestOrderLineItem> shipmentOrderLines, Locale locale) {
		String METHOD_NAME = "getServiceRequestShipments";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		Map shipment = new HashMap();
		SharedPortletController shared = new SharedPortletController();
		if (shipmentOrderLines != null) {
			List<List<ServiceRequestOrderLineItem>> returnShipments = shared.listShipments(shipmentOrderLines);
			List<ShipmentForm> shipmentForms = new ArrayList<ShipmentForm>();
			ShipmentForm inProcessForm = new ShipmentForm();
			for (int i = 0; i < returnShipments.size(); i++) {
				ShipmentForm shipmentForm = new ShipmentForm();
				List<ServiceRequestOrderLineItem> orderLineItems = returnShipments
						.get(i);
				String[] requestShipmentGeneratorPatterns = new String[] {
				SNO,PARTNO, PROD_DESCRIPTN,PART_TYPE, VENDORPRODUCT,SHIPPED_QUANTITY,PRICE};//For MPS Phase 2.1
				
				String requestShipmentNotificationsXML = getXmlOutputGenerator(
						locale).generate(orderLineItems, orderLineItems.size(),
						0, requestShipmentGeneratorPatterns,SHIPMENT);
				requestShipmentNotificationsXML = requestShipmentNotificationsXML
						.replace("\n", "");
				shipmentForm.setShipmentXML(StringUtil.encodeSingleQuote(
						requestShipmentNotificationsXML));
				shipmentForm.setTrackingNumber(orderLineItems.get(0)
						.getTrackingNumber());
				shipmentForm.setCarrier(orderLineItems.get(0).getCarrier());
				shipmentForm.setActualShipDate(orderLineItems.get(0).getShippedDate());
				//Get the carrier link here
				String carrier = orderLineItems.get(0).getCarrier();
				String trackingNumber = orderLineItems.get(0).getTrackingNumber();
				String trackingUrl="";
				boolean hasProperUrl=false;
				/*LOGGER.debug("Carrier name is "+carrier+" and trackingNumber is "+trackingNumber);*/
				//Get the carrier link from property file
				if(trackingNumber!= null){
					trackingUrl = shared.getTrackingUrl(carrier, trackingNumber);
					shipmentForm.setCarrierLink(trackingUrl);
					if(!"".equals(trackingUrl)){
						hasProperUrl = true;
					}
					
				}
				
				shipmentForm.setHasProperUrl(hasProperUrl);
				//Complete getting carrier link here
				shipmentForm.setShipmentProgress(shared.checkProgress(orderLineItems
						.get(0).getStatus(), SHIPMENT));
				
				shipmentForm.setActualDeliveryDate(orderLineItems.get(0).getActualDeliveryDate());
				shipmentForm.setETA(orderLineItems.get(0).getEta());
				
				if (shipmentForm.getTrackingNumber() != null) {
					shipmentForms.add(shipmentForm);
				} else {
					inProcessForm = shipmentForm;
				}
			}
			shipment.put(ChangeMgmtConstant.SHIPMENTFORM, shipmentForms);
			shipment.put(ChangeMgmtConstant.INPROCESSFORM, inProcessForm);

		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		
		return shipment;
	}
	
	/**. Downloads attachment in details page.
	 * 
	 * @param request ResourceRequest 
	 * @param response ResourceResponse 
	 * @param model Model 
	 * @throws Exception 
	 */
	@ResourceMapping("downloadAttachment")
	public void downloadAttachment(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		String METHOD_NAME = "downloadAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );	
		request.setCharacterEncoding("UTF-8");
		StringBuffer dispAttchmntName = new StringBuffer(request.getParameter(ChangeMgmtConstant.DISP_ATTACHMENT_NAME));
		StringBuffer attchmntName = new StringBuffer(request.getParameter(ChangeMgmtConstant.ATTACHMENT_NAME));
		StringBuffer fileExtension = new StringBuffer(request.getParameter(ChangeMgmtConstant.FILE_EXTENSION));
		String identifier = request.getParameter(ChangeMgmtConstant.IDENTIFIER);
		
		LOGGER.debug("HistoryController.downloadAttachment attchmntName : " + attchmntName
		+"fileExtension : " + fileExtension
		+"identifier : " + identifier);
		
		if(!fileExtension.toString().equalsIgnoreCase("URL")){
			attchmntName.append(FILE_DELIMITER);
			attchmntName.append(fileExtension);
			
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			InputStream fileStream = getInputStreamForAttachment(attchmntName.toString(), identifier, crmSessionHandle); 
			try{
				if(fileStream!=null){
					// localizedFileName added to prevent special character attachment name from getting distorted
					String localizedFileName = DownloadFileLocalizationUtil.encodeDecodeFileName(dispAttchmntName.toString());
					response.setProperty("Content-disposition", "attachment; filename=\"" + localizedFileName + FILE_DELIMITER + fileExtension +"\"");
					response.setCharacterEncoding("utf-8");
					if(attchmntName.indexOf("jpg") > 0) {
						response.setContentType("image/jpg");
			        }else if(attchmntName.indexOf("gif") > 0) {
			        	response.setContentType("image/gif");
			        }else if(attchmntName.indexOf("pdf") > 0) {
			        	response.setContentType("application/pdf");
			        	
			        }else if(attchmntName.indexOf("html") > 0) {
			        	response.setContentType("text/html");
			        }else if(attchmntName.indexOf("zip") > 0) {
			        	response.setContentType("application/zip");
			        }else if(attchmntName.indexOf("txt") > 0 ){
			        	response.setContentType("text/plain");
			        }else if(attchmntName.indexOf("xls")>0){
			        	response.setContentType("application/vnd.ms-excel");
			        }else if(attchmntName.indexOf("pdf")>0){
			        	response.setContentType("application/pdf");
			        }else if(attchmntName.indexOf("doc")>0){
			        	response.setContentType("application/msword");
			        }else if(attchmntName.indexOf("xlsx")>0){
			        	response.setContentType("application/vnd.ms-excel");
			        }else if(attchmntName.indexOf("docx")>0){
			        	response.setContentType("application/msword");
			        }else if(attchmntName.indexOf("csv")>0){
			        	response.setContentType("application/vnd.ms-excel");
			        }else if(attchmntName.indexOf("vsd")>0){
			        	response.setContentType("application/x-visio");
			        }
					
					 InputStream inputStream = fileStream;
					 OutputStream out = response.getPortletOutputStream();
					 byte buf[]=new byte[1024];
					 int inputStreamBufferlen=inputStream.read(buf);
					 while(inputStreamBufferlen>0){
						 out.write(buf,0,inputStreamBufferlen);
						 inputStreamBufferlen=inputStream.read(buf);
					 }
					 out.close();
					 inputStream.close();
					 LOGGER.debug("\nFile is created...................................");
				}
				else{
					response.setProperty("Content-disposition", "attachment; filename=Error_in_file_download.doc");
					response.setContentType("application/msword");
					OutputStream out = response.getPortletOutputStream();
					byte buf[]=("The file '"+ dispAttchmntName + FILE_DELIMITER + fileExtension + "' could not downloaded.").getBytes();
					out.write(buf);
					out.flush();
					out.close();
					LOGGER.debug("\nFile is created...................................");
				}

					
			}catch (IOException e){
				LOGGER.debug("HistoryController.showRequestDetails exception : " + e.getMessage());
			}
			catch(Exception exception){
				LOGGER.debug("HistoryController.showRequestDetails exception : " + exception.getMessage());
			}
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );

	}
	
	/**. Assembles service request activity data.
	 * 
	 * @param list List<ServiceRequestActivity>
	 * @return List<ServiceRequestActivity>
	 */
	private List<ServiceRequestActivity> assembleServiceRequestActivity (List<ServiceRequestActivity> list){
		String METHOD_NAME = "assembleServiceRequestActivity";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		SRAdministrationListContract contract = ContractFactory.getSRAdministrationListContract();
		
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract,LOGGER);
		LOGGER.info("end printing lex loggger");
		
		SRAdministrationListResult result = serviceRequestLocaleService.retrieveSRAdministrationList(contract);
		Map<Object, Object> orderMap = new HashMap<Object, Object>();
		if (result.isSucceed()) {
			List<SiebelLocalization> siebelLocalizations = result.getSiebelLocalizations();
			for (SiebelLocalization siebelLocal : siebelLocalizations){
				orderMap.put(siebelLocal.getSiebelValue(), siebelLocal.getStatusOrder());
			}
		}
		for(ServiceRequestActivity serviceRequestActivity : list){
			serviceRequestActivity.setStatusOrder((Integer)orderMap.get(serviceRequestActivity.getActivityStatus()));
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		return list;
	}
	
	/**.
	 * Retrieves the data for the requests with same asset serial number list
	 * @param request ResourceRequest 
	 * @param response ResourceResponse 
	 * @param model Model 
	 * @throws Exception  
	 */
	@ResourceMapping(value="retrieveSRHistoryListXML")
	public void retrieveServiceRequestHistoryList(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		String METHOD_NAME = "retrieveServiceRequestHistoryList";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );

		String assetId = request.getParameter(ChangeMgmtConstant.ASSETROWID);
		String accountId = request.getParameter(ChangeMgmtConstant.ACCOUNTROWID);
		String serviceRequestNumber = request.getParameter(ChangeMgmtConstant.SERVICEREQNO);
		String timeZoneOffset = request.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);

		ServiceRequestHistoryListContract contract = ContractFactory.getServiceRequestHistoryListContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			contract.setAssetId(assetId);
			if (accountId != null){
				contract.setAccountId(accountId);
				LOGGER.debug("XXXXXXXXX accountId: " + accountId);
			}
			if (serviceRequestNumber != null){
				contract.setServiceRequestNumber(serviceRequestNumber);
				LOGGER.debug("XXXXXXXXX serviceRequestNumber: " + serviceRequestNumber);
			}
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LOGGER);
			LOGGER.info("end printing lex loggger");
			long timeBeforeCall=System.currentTimeMillis();
			ServiceRequestListResult serviceRequestListResult = serviceRequestService.retrieveServiceRequestHistoryList(contract);
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_SERVICEREQUESTSERVICE, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
			LOGGER.info("end printing lex loggger");
			List<ServiceRequest> serviceRequestList = serviceRequestListResult.getServiceRequests();
			
			int totalCount = serviceRequestListResult.getTotalCount();
			String xmlContent = getXmlOutputGeneratorUtil(request.getLocale())
			.convertRequestHistoryToXML(serviceRequestList, totalCount,
					contract.getStartRecordNumber(), 
					false, Float.parseFloat(timeZoneOffset));
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml"); 
			out.print(xmlContent);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
	}
	
	/**.
	 * Retrieves the data for the associated requests history list
	 * @param request ResourceRequest 
	 * @param response ResourceResponse 
	 * @param model Model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="retrieveAssociatedSRHistoryListXML")
	public void retrieveServiceAssociatedRequestHistoryList(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		String METHOD_NAME = "retrieveServiceAssociatedRequestHistoryList";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		String timeZoneOffset = request.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		
		String serviceRequestNumber = request
				.getParameter(ChangeMgmtConstant.SERVICEREQNO);		
		ServiceRequestAssociatedListContract contract = ContractFactory
				.getAssociatedServiceRequestListContract(request);		
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));
		try {
			PortletSession session = request.getPortletSession();
			contract.setSessionHandle(crmSessionHandle);
			contract.setServiceRequestNumber(serviceRequestNumber);	
			LOGGER.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LOGGER);
			LOGGER.info("end printing lex loggger");
			long timeBeforeCall=System.currentTimeMillis();
			ServiceRequestListResult serviceRequestListResult = serviceRequestService
			.retrieveAssociatedServiceRequestList(contract);
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_RETRIEVEASSOCIATEDSERVICEREQUESTLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
			LOGGER.info("end printing lex loggger");			
			
			List<ServiceRequest> serviceRequestList = serviceRequestListResult.getServiceRequests();
			int totalCount = serviceRequestListResult.getTotalCount();
			LOGGER.debug("total count"+totalCount);
			for(ServiceRequest sr:serviceRequestList){
				if(sr.getArea()!=null){
						String area = commonController.getLocalizeSiebelValue(sr.getArea().getValue(),SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(),request.getLocale());
						sr.getArea().setName(area);
				}
			}
			String xmlContent = getXmlOutputGeneratorUtil(request.getLocale())
			.convertAssociatedRequestToXML(serviceRequestList, totalCount,contract.getStartRecordNumber(), 
					false, Float.parseFloat(timeZoneOffset));
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(xmlContent);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}

		LOGGER.exit(CLASS_NAME, METHOD_NAME );

	}
	
	/**.
	 * 
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @param model Model 
	 * @return String 
	 * @throws Exception 
	 */
	/*Method gotoControlPanel added for Defect 3924- Jan Release*/
	@RequestMapping(params = "action=gotoControlPanel")
	public String gotoControlPanel(RenderRequest request, RenderResponse response,
			Model model) throws Exception {
		model.addAttribute(ChangeMgmtConstant.CONTROLPANELURL,request.getParameter(ChangeMgmtConstant.CONTROLPANELURL));
		model.addAttribute(ChangeMgmtConstant.PAGENAME, ChangeMgmtConstant.REQUESTDETAILS);
		return ChangeMgmtConstant.CONTROLPANELPAGE;
	}
	
	
	/**.
	 * 
	 * @param status String
	 * @return double
	 */
	private double checkProgressForChangeManagement(String status) {
		
		double progress = 0;
		
			if (status.equalsIgnoreCase(ChangeMgmtConstant.SUBMITTED)) {
				progress = 25.0;
			} else if (status.equalsIgnoreCase(ChangeMgmtConstant.INPROGRESS) || status.equalsIgnoreCase(ChangeMgmtConstant.INPROCESS)) {
				progress = 50.0;
			} else if (status.equalsIgnoreCase(ChangeMgmtConstant.SHIPPED)) {
				progress = 75.0;
			} else if (status.equalsIgnoreCase(ChangeMgmtConstant.COMPLETED) || "Solution Provided".equalsIgnoreCase(status)){
				progress = 100;
			}else if (LexmarkConstants.TECHNICIANSTATUS_CANCELLED.equals(status)){
				progress = 0;
			} else {
				progress = 33;
			}
			LOGGER.debug("Progress-- "+progress);
		return progress;
	}
	
	/**.
	 * 
	 * @param status String
	 * @return int
	 */
	private int checkTechnicianProgress(String status) {
		if (LexmarkConstants.TECHNICIANSTATUS_ASSIGNED.equals(status)) {
			return 66;
		} else if (LexmarkConstants.TECHNICIANSTATUS_COMPLETE.equals(status)) {
			return 100;
		} else if (LexmarkConstants.TECHNICIANSTATUS_CANCELLED.equals(status)){
			return 0;
		} else {
			return 33;
		}
	}
	
	/**.
	 * 
	 * @param area ListOfValues
	 * @param subArea ListOfValues
	 * @return String
	 */
	private String decideCMView(ListOfValues area, ListOfValues subArea) {
		
		String view = null;
		String areaStr = "";
		String subAreaStr = "";
		
		if(area != null){
			areaStr = area.getValue(); 
		}
		if(subArea != null){
			subAreaStr = subArea.getValue(); 
		}
		if(ChangeMgmtConstant.SIEBEL_VALUE_CHL_SUBAREA_ADD.equalsIgnoreCase(subAreaStr) || ChangeMgmtConstant.SIEBEL_VALUE_CHL_SUBAREA_CHANGE.equalsIgnoreCase(subAreaStr) || ChangeMgmtConstant.SIEBEL_VALUE_CHL_SUBAREA_DECOMMISSION.equalsIgnoreCase(subAreaStr)){
			view = CHL_OTHERS_VIEW;
		}
		else if(ChangeMgmtConstant.SRTYPE_CANCEL_REQUEST.equalsIgnoreCase(areaStr)){
			view = CHL_OTHERS_VIEW;
		}
		
		if(view == null){
			view = NORMAL_VIEW;
		}
		LOGGER.debug("View for details :: " +  view );
		return view;
	}
	
	/**.
	 * Renders the print preview page 
	 * @param request RenderRequest 
	 * @param response RenderResponse 
	 * @param model Model 
	 * @return String  
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printSRHistoryList")
	public String showRequestHistoryPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return ChangeMgmtConstant.SERVICEREQUESTSPRINT;
	}

	/**.
	 * Method is invoked when SR# is clicked from any of the details grids
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @param model Model
	 * @return String 
	 * @throws Exception 
	 * @throws LGSDBException 
	 * 
	 */
	@RenderMapping(params="action=showRequestDetailsPopup")
	public String showRequestDetailsPopup(Model model, RenderRequest request,
			RenderResponse response) throws Exception, LGSDBException {
		String METHOD_NAME = "showRequestDetailsPopup";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		
		RequestDetailForm requestFormPopup = new RequestDetailForm();
		String toJSP = ChangeMgmtConstant.CHANGEREQUESTDETAILSPOPUP;
		List<String> exceptionList = new ArrayList<String>();
		String serviceRequestNumber = request.getParameter(ChangeMgmtConstant.SERVICEREQNO);
		
	try {
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));		
		requestFormPopup.setSrNumber(serviceRequestNumber);
		String timeZoneOffset = httpReq.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		if (timeZoneOffset!=null) {
			model.addAttribute(ChangeMgmtConstant.TIMEZONEOFFSET_POPUP, timeZoneOffset);
		}
		
		String srArea = "";
		Boolean assetFlag=false;
		if(httpReq.getParameter(ChangeMgmtConstant.SRAREA)!=null){
			srArea = httpReq.getParameter(ChangeMgmtConstant.SRAREA);
		}
		
		String requestType =request.getParameter(ChangeMgmtConstant.REQUESTTYPE);
		String srSubArea="";
		String coveredService = "";
		if(httpReq.getParameter(ChangeMgmtConstant.SRAREA)!=null){
			srArea = httpReq.getParameter(ChangeMgmtConstant.SRAREA);
		}
		if(httpReq.getParameter("srSubArea")!=null){
			srSubArea = httpReq.getParameter("srSubArea");
		}
		if(httpReq.getParameter("coveredService")!=null){
			coveredService = httpReq.getParameter("coveredService");
		}
		if("Fleet_Management".equalsIgnoreCase(requestType)){
			if((ChangeMgmtConstant.AREA_ADDASSET_YES.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_ADDASSET_YES.equalsIgnoreCase(srSubArea))
					||(ChangeMgmtConstant.AREA_ADDASSET_NO.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_ADDASSET_NO.equalsIgnoreCase(srSubArea))
					||(ChangeMgmtConstant.AREA_CHANGEASSET_YES.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_CHANGEASSET_YES.equalsIgnoreCase(srSubArea))
					||(ChangeMgmtConstant.AREA_CHANGEASSET_NO.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_CHANGEASSET_NO.equalsIgnoreCase(srSubArea))
					||(ChangeMgmtConstant.AREA_DECOMMASSET_YES.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_DECOMMASSET_YES.equalsIgnoreCase(srSubArea))
					||(ChangeMgmtConstant.AREA_DECOMMASSET_NO.equalsIgnoreCase(srArea) && ChangeMgmtConstant.SUBAREA_DECOMMASSET_NO.equalsIgnoreCase(srSubArea))){
				toJSP = "lgsHistoryDetails/assetChangeRequestPopup";
				assetFlag=true;
				if(coveredService != null){
					model.addAttribute("coveredService",coveredService);
				}
			}else{
				if("Install".equalsIgnoreCase(srArea) && "BAU".equalsIgnoreCase(srSubArea)){
					toJSP = "lgsHistoryDetails/installReqDetailsPopup";
					
				}else{
					toJSP = ChangeMgmtConstant.CHANGEREQUESTDETAILSPOPUP;
				}
			
			}
		}else if("Consumables_Management".equalsIgnoreCase(requestType)){
			if(ChangeMgmtConstant.INQUERYAREA.equalsIgnoreCase(srArea)){
				toJSP = ChangeMgmtConstant.CHANGEREQUESTDETAILSPOPUP;
			}else{
				toJSP = ChangeMgmtConstant.SUPPLYREQUESTDETAILSPOPUP;
			}
			
		}
		
		httpReq.removeAttribute(ChangeMgmtConstant.TIMEZNOFFSET);
		
		if(ChangeMgmtConstant.CONSUMABLESMGMT.equalsIgnoreCase(requestType)){
			populateSupplyRequestDetails(request, response, model, requestFormPopup, exceptionList, ChangeMgmtConstant.DETAIL_VIEW_POPUP, timeZoneOffset);
		}
		else if(ChangeMgmtConstant.FLEETMGMT.equalsIgnoreCase(requestType)) {
			populateChangeRequestDetails(request, model, requestFormPopup, exceptionList, ChangeMgmtConstant.DETAIL_VIEW_POPUP,assetFlag,timeZoneOffset);
		}
		if(ChangeMgmtConstant.INQUERYAREA.equalsIgnoreCase(srArea)){
			if(requestFormPopup.getServiceRequest().getServiceRequestStatus()!= null){
				requestFormPopup.setReqProgress(checkProgress(requestFormPopup.getServiceRequest().getServiceRequestStatus()));//To be changed
				String view = decideCMView(requestFormPopup.getServiceRequest().getArea(), requestFormPopup.getServiceRequest().getSubArea()) ;
				model.addAttribute(ChangeMgmtConstant.PAGEVIEW, view);
			}
			// Retrieve Service Request Notification List
						if (requestFormPopup.getServiceRequest().getEmailActivities() != null
								&& requestFormPopup.getServiceRequest()
										.getEmailActivities().size() > 0) {
							String srNotifyXML = getXmlOutputGenerator(request.getLocale()).convertServiceRequestNotificationToXML(requestFormPopup.getServiceRequest().getEmailActivities());
							srNotifyXML = srNotifyXML
									.replace("\n", "");
							requestFormPopup.setSrNotifyXML(srNotifyXML);
						}else{
							requestFormPopup.setSrNotifyXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
						}
		}
		//Added For CR 10483 and 13824 June relase start
		if("Install".equalsIgnoreCase(srArea) && "BAU".equalsIgnoreCase(srSubArea)){
			model.addAttribute("coveredService",coveredService);
			String[] activityGeneratorPattern = new String[] {
					"partNumber", "description", "orderQuantity"}; 
			if(requestFormPopup.getServiceRequest()!=null && requestFormPopup.getServiceRequest().getActivitywebUpdateActivities() != null && 
					requestFormPopup.getServiceRequest().getActivitywebUpdateActivities().size() > 0){
				
				List<ServiceRequestActivity> sActivityList = requestFormPopup.getServiceRequest().getActivitywebUpdateActivities();
				LOGGER.debug("sActivityList" +sActivityList);
				String serviceRequestTechnicianXML = getXmlOutputGenerator(request
						.getLocale()).getActivityDetails(sActivityList,
								sActivityList.size(), 0,
								activityGeneratorPattern);
				LOGGER.debug("serviceRequestTechnicianXML" + serviceRequestTechnicianXML);
				serviceRequestTechnicianXML = serviceRequestTechnicianXML.replaceAll("\n", "");
				requestFormPopup.setServiceRequestTechnicianXML(serviceRequestTechnicianXML);
				model.addAttribute("SRTechnicianXMLFlagPopup",true);
			}
			else  {
				
				requestFormPopup.setServiceRequestTechnicianXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
				model.addAttribute("SRTechnicianXMLFlagPopup",false);
			}
		}
		String creationDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(requestFormPopup.getServiceRequest().getServiceRequestDate(), Float.parseFloat(timeZoneOffset)), true, request.getLocale());
		LOGGER.debug("Created date ---->"+creationDate);
		requestFormPopup.setCreationDate(creationDate);
				
		model.addAttribute(ChangeMgmtConstant.REQUESTFORM_POPUP, requestFormPopup);
				
		LOGGER.debug("HistoryController.showRequestDetailsPopup Exit : toJSP--->"+toJSP);
		if(toJSP.equalsIgnoreCase("lgsHistoryDetails/changeRequestDetailsPopup") || toJSP.equalsIgnoreCase("lgsHistoryDetails/assetChangeRequestPopup")){
			if(requestFormPopup.getServiceRequest().getServiceRequestStatus()!= null){
							requestFormPopup.setReqProgress(checkProgressForChangeManagement(requestFormPopup.getServiceRequest().getServiceRequestStatus()));//To be changed---ShippedStatus
							LOGGER.debug("ServiceRequestStatusPopup---->"+requestFormPopup.getServiceRequest().getServiceRequestStatus());
						}
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		return toJSP;
	} catch (Exception ex) {
		exceptionList.clear();
		exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE,
				"exception.retrieve.Detail", null, request.getLocale())+ " "+ serviceRequestNumber);
		model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION, exceptionList);		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		ex.getMessage();
		return toJSP;
		
	}
		
	}
	
	/**.
	 * This method Retrieves the data for devicelocation tree in request history page.
	 * @param request ResourceRequest 
	 * @param response ResourceResponse 
	 * @param model Model 
	 * @throws Exception 
	 * @author Sagar Sarkar
	 */
	@ResourceMapping("deviceLocationXMLURLforRequest")
	public void retrieveDeviceLocationTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		LOGGER.enter(CLASS_NAME, "retrieveDeviceLocationTreeXML");
		sharedPortletController.retrieveRequestListDeviceLocationTreeXML(request,
				response, model);
		LOGGER.exit(CLASS_NAME, "retrieveDeviceLocationTreeXML");
	}
	
	/**.
	 * Renders the notification description pop up page
	 * @param request RenderRequest 
	 * @param response RenderResponse 
	 * @param model Model 
	 * @return String 
	 * @throws Exception  
	 */
	@RequestMapping(params = "action=showNotificationDescDetail")
	public String showNotificationDescDetail(RenderRequest request, RenderResponse response, Model model)
			throws Exception { 

		return "serviceRequest/notificationDescriptionDetail";
	}
		
//		Changes For Email and Print
/**	
 * 
 * @return String 
 */
	@RequestMapping(params = "action=printSupplyHistory")
	public String showSupplyRequestPrint(){
		String METHOD_NAME = "showSupplyRequestPrint";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "lgsHistoryDetails/supplyRequestDetailsPrint";
	}
	/**
	 * 
	 * @return String 
	 */
	@RequestMapping(params = "action=emailSupplyHistory")
	public String showSupplyRequestEmail(){
		String METHOD_NAME = "showSupplyRequestEmail";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);		
		return "lgsHistoryDetails/supplyRequestDetailsEmail";
	}

//Added for MPS Phase 2.1 Change Request Print
	/**
	 *  Renders the print page for change request. 
	 * @return String 
	 */
	 
	@RequestMapping(params = "action=printChangeRequest")
	public String showPrintChangeRequest(){
		String METHOD_NAME="showPrintChangeRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("------------- Inside Show Print ---------");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "lgsHistoryDetails/printChangeRequest";
	}
	
	/**
	 * Renders the email page for change request 
	 * @return String 
	 */
	@RequestMapping(params = "action=emailChangeRequest")
	public String showEmailChangeRequest(){
		String METHOD_NAME="showEmailChangeRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("-------------showEmail started---------");
		return "lgsHistoryDetails/emailChangeRequest";
	}
	/**.
	 * Renders the print page for change request.
	 * @return String 
	 */
	@RequestMapping(params = "action=printAssetRequest")
	public String showPrintAssetRequest(){
		String METHOD_NAME="showprintAssetRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("------------- Inside Show Print ---------");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "lgsHistoryDetails/printAssetDetails";
	}
	
	/**
	 * Renders the email page for change request
	 * @return String 
	 */
	@RequestMapping(params = "action=emailAssetRequest")
	public String showEmailAssetRequest(){
		String METHOD_NAME="showemailAssetRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("-------------showEmail started---------");
		return "lgsHistoryDetails/emailAssetDetails";
	}
	
	//Added for CI BRD13-10-02
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 */
	@ResourceMapping(value="retrieveHistoryList")
	public void retrieveHistoryList(ResourceRequest request, ResourceResponse response, Model model)
	{
		commonController.retrieveHistoryListByAssetId(request, response, model);
	}
	
	

	/**
	 * 
	 * @param locale 
	 * @return Map 
	 * @throws Exception 
	 */
	public Map<String,String> getRequestType(Locale locale) throws Exception{
		
		 Map<String, String> requestTypeLOVMap =null;
			try {
				requestTypeLOVMap =  commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.REQUEST_TYPE.getValue(),locale);						
			}catch(LGSDBException e){
				LOGGER.error("Unable to retrieve Status map");
				requestTypeLOVMap=new HashMap<String,String>();
				e.getMessage();
			}
			return requestTypeLOVMap;
		
	}

	
	/**
	 * Set SiebelLocalized Values for Request Type
	 * @param locale  
	 * @param serviceRequests  
	 * @return List 
	 */
	public List<ServiceRequest> requestType(List<ServiceRequest> serviceRequests, Locale locale){
		
		Map<String,String> requestStatusLOVMap=null;
		try {
			requestStatusLOVMap=getRequestType(locale);
			LOGGER.debug("requestStatusLOVMap -->> "+requestStatusLOVMap);
		} catch (Exception e) {
			requestStatusLOVMap=null;
			LOGGER.debug("Unable to retrieve RequestType Map");
			e.getMessage();
		}
		final String REQUESTTYPEHARDWARE = "Hardware Request";
		for(ServiceRequest sr:serviceRequests){
			 ListOfValues reqTypeLOV = sr.getServiceRequestType();
			 ListOfValues areaLOV = sr.getArea();
				ListOfValues subAreaLOV = sr.getSubArea();
			if(reqTypeLOV.getValue().equalsIgnoreCase("Fleet Management")&& (areaLOV.getValue().equalsIgnoreCase("HW Order")|| (areaLOV.getValue().equalsIgnoreCase("Hardware-Ship and Install") && subAreaLOV.getValue().equalsIgnoreCase("BAU")))){
				LOGGER.debug("areaLOV.getValue()-->> "+areaLOV.getValue());
				sr.getServiceRequestType().setValue(requestStatusLOVMap.get(REQUESTTYPEHARDWARE));
			}else{
			sr.getServiceRequestType().setValue(requestStatusLOVMap.get(sr.getServiceRequestType().getValue()));
			}
		}
		return serviceRequests;
	}
	
	/**Checks Service Status
	 * @param status
	 * @return
	 */
	private double checkProgress(String status) {
		
		double progress = 0;
		
			if (status.equalsIgnoreCase(ChangeMgmtConstant.SUBMITTED)) {
				progress = 33.3;
			} else if (status.equalsIgnoreCase(ChangeMgmtConstant.INPROGRESS) || status.equalsIgnoreCase(ChangeMgmtConstant.INPROCESS)) {
				progress = 66.6;
			} else if (status.equalsIgnoreCase(ChangeMgmtConstant.COMPLETED) || "Solution Provided".equalsIgnoreCase(status)){
				progress = 100;
			}else if (LexmarkConstants.TECHNICIANSTATUS_CANCELLED.equals(status)){
				progress = 0;
			} else {
				progress = 33;
			}
		
		return progress;
	}
	/**
	 * @param addressString
	 * @return
	 */
	private GenericAddress createAddressfromString(String addressString){
		GenericAddress address=new GenericAddress();
		try{
		
		
		String[] concatFields={"addressId","addressName","addressLine1","addressLine2",
				"officeNumber","city","stateCode",
                "province","district","county","region","country","countryISOCode","postalCode","campusName","campusId",
                "physicalLocation1","buildingId","physicalLocation2","floorId","physicalLocation3",
                "stateFullName","zoneName","zoneId","latitude","longitude","coordinatesXPreDebriefRFV","coordinatesYPreDebriefRFV",
                "isAddressCleansed","savedErrorMessage","newAddressFlag"};


	
	
			String [] moveToAddressSplitArray = addressString.split("\\|");			  
			if(moveToAddressSplitArray.length >1){
				for(int i=0;i<concatFields.length;i++){
					LOGGER.debug("concatFields "+concatFields[i]);
					try {
						if(i>=moveToAddressSplitArray.length){
							break;
						}
						if("isAddressCleansed".equals(concatFields[i])){
							if(StringUtils.isNotBlank(moveToAddressSplitArray[i])&& "true".equalsIgnoreCase(moveToAddressSplitArray[i])){                                                        
								address.setIsAddressCleansed(true);
							}
						}else{
							PropertyUtils.setProperty(address,concatFields[i], moveToAddressSplitArray[i]);        
						}
						
					
					} catch (IllegalAccessException e) {
						LOGGER.error(String.format("[ Field error %s  exception message %s]",concatFields[i],e.getMessage()));
					} catch (InvocationTargetException e) {
						LOGGER.error(String.format("[ Field error %s  exception message %s]",concatFields[i],e.getMessage()));
					} catch (NoSuchMethodException e) {
						LOGGER.error(String.format("[ Field error %s  exception message %s]",concatFields[i],e.getMessage()));
					}
				}
			}	
				

			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			return address;
		}
	}

}