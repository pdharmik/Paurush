/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CommonController
 * Package     		: com.lexmark.services.portlet.common
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
package com.lexmark.services.portlet.common;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
//import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.AttachmentContract;

import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountAccess;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestDTO;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.process.LGSProcessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AccountFlagResult;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.GeographyListResult;
import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.SiebelAccountListResult;
//import com.lexmark.service.api.AddressCleansingService;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
//import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.GeographyService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.impl.real.jdbc.GeographyServiceImpl;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import com.lexmark.service.impl.real.jdbc.ServiceRequestLocaleImpl;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.AssetAcceptanceForm;
import com.lexmark.services.form.AssetDetailPageForm;
import com.lexmark.services.form.CMTemplateRquestForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.ManageAddressForm;
import com.lexmark.services.form.ManageAssetForm;
import com.lexmark.services.form.ManageCHLOthersForm;
import com.lexmark.services.form.ManageContactForm;
import com.lexmark.services.form.ManageReturnsForm;
import com.lexmark.services.form.MapsRequestForm;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.services.util.PerformanceUtil; //Added for performance logging
import com.lexmark.util.DateUtil;
import com.lexmark.util.FileWriteUtility;
import com.lexmark.util.LOVComparator;
import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.PropsFileLoadUtility;
import com.lexmark.util.StringUtil;
import com.liferay.portal.kernel.util.JavaConstants;
//import com.liferay.portal.util.PortalUtil;

/**
 * @author Wipro 
 * @version 1.0 
 *
 */

@SuppressWarnings("unused")
@Controller
@RequestMapping("VIEW")
@SessionAttributes(value="newContactPopupError")

public class CommonController extends BaseController{
	
	@Autowired
	private GlobalService globalService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private SharedPortletController sharedPortletController;
	@Autowired
	private ServiceRequestService serviceRequestService;
	@Autowired 
	private GeographyService geographyService; 
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ExecutorService executorService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService; 
	@Resource
	private List<GeographyListResult> allCountryList;
	@Autowired
	private OrderSuppliesAssetService orderSuppliesAssetService;
	@Autowired
	private ServiceRequestLocale  serviceRequestLocale;
	
	@Autowired
	private ContactController contactController;
	//Done for BRD13-10-02 (CI)
	/**
	 * 
	 */
	@Autowired
	public RequestTypeService requestTypeService;
	
	/**. Instance variable of wrapper logger class **/
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(CommonController.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	
	private static final String CREATE_SERVICE_REQUEST = "CREATE SERVICE REQUEST";
	private static final String CREATE_SUPPLIES_REQUEST ="CREATE SUPPLIES REQUEST";
	private static final String CREATE_CHANGE_MGMT_REQUEST ="CREATE CHANGE MGMT REQUEST";
	private static final String SHOW_SERVICE_REQUEST = "SHOW SERVICE REQUEST";
	private static final String SHOW_SUPPLIES_REQUEST ="SHOW SUPPLIES REQUEST";
	private static final String SHOW_CHANGE_MGMT_REQUEST ="SHOW CHANGE MGMT REQUEST";
	private static final String SHOW_ALL_REQUEST = "SHOW ALL REQUEST" ;	
	private static final String METH_RETRIEVEASSETLIST ="retrieveAssetList";
	private static final String METH_RETRIEVEADDRESSLIST ="retrieveAddressList";
	private static final String METH_SETSERVICEADDRESSFAVOURITEFLAG ="setServiceAddressFavouriteFlag";
	private static final String METH_SHOWADDRESSLISTPOPUP ="showAddressListPopUp";
	private static final String METH_SHOWCHLTREEPOPUP ="showCHLTreePopUp";
	private static final String METH_CREATENEWREQUESTPOPUP ="createNewRequestPopUp";
	private static final String METH_CHECKUSERROLES ="checkUserRoles";
	private static final String METH_LOADFILTERCRITERIA ="loadFilterCriteria";
	private static final String METH_GETCONTACTINFORMATION ="getContactInformation";
	private static final String METH_RETRIEVEASSETDETAIL ="retrieveAssetDetail";
	private static final String METH_GETASSETCONTRACT ="getAssetContract";
	private static final String METH_GETSERVICEREQCONTRACT ="getServiceReqContract";
	private static final String METH_FORWARDTOJSP ="forwardToJsp";
	private static final String METH_SUBMITDOCUMENTLIST ="submitDocumentList";
	private static final String METH_RETRIEVECHLTREEXML ="retrieveCHLTreeXML";
	private static final String METH_RETRIEVEDEVICELOCATIONTREEXML ="retrieveDeviceLocationTreeXML";
	private static final String METH_UPDATEUSERFAVOURITEASSET ="updateUserFavoriteAsset";
	private static final String METH_FILLDTO ="fillDTO";
	private static final String METH_CREATEFUTURETASK ="createFutureTask";
	private static final String METH_WRITECSV ="writeCSV";
	private static final String METH_RENAMEATTACHMENT ="renameAttachment";
	private static final String METH_POPULATECOUNTRYLIST ="populateCountryList";
	private static final String METH_POPULATESTATELIST ="populateStateList";
	private static final String METH_NEWADDRESSVALIDATEFROMPOPUP ="newAddressValidateFromPopup";
	private static final String METH_RETRIEVEACCOUNTLIST ="retrieveAccountList";
	private static final String METH_SETCURRENTACCOUNT ="setCurrentAccount";
	private static final String METH_RETRIEVELOCALIZEDLOVMAP ="retrieveLocalizedLOVMap";
	private static final String METH_PRINTCONFIRMATIONPAGE ="printConfirmationPage";
	private static final String METH_EMAILCONFIRMATIONPAGE ="emailConfirmationPage";
	private static final String METH_PRINTACCOUNTDETAIL ="printAccountDetail";
	private static final String METH_VERIFYREQUESTACCESSFLAG4ACCT ="verifyRequestAccessFlag4Acct";
	private static final String METH_RETRIEVEREQHISTORYACCESS4PORTAL ="retrieveReqHistoryAccess4Portal";
	private static final String GET_SIEBEL_LOCALISED_VALUE ="getLocalizeSiebelValue";
	private static final String GRID_QUERY_PARAMS = "gridQueryParams_";
	
	//Added for Hardware Request in MPS2.1
	private static final String CREATE_HARDWARE_REQUEST = "CREATE HARDWARE REQUEST";
	private static final String SHOW_HARDWARE_REQUEST = "SHOW HARDWARE REQUEST";
	
	//Added for LBS Map SR creation flow
	private static final String CREATE_MAP_REQUEST = "CREATE MAP REQUEST";
	//Added for LBS FleetManage flag
	private static final String FLEET_MANAGER_FLAG = "FLEET MANAGER FLAG";

	/**
	 * Common Method for asset list population shown in CM assetlist page(customized)
	 * @param resReq 
	 * @param resResp 
	 */
	@ResourceMapping(value = "deviceListPopulate")
	public void retrieveAssetList(ResourceRequest resReq,
			ResourceResponse resResp) {
		
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVEASSETLIST);				
		LOGGER.debug("------------- Step 1---retriveDeviceList started---------["
				+ System.nanoTime() + "]");
		AssetListContract contract = ContractFactory.getAssetListContractForCM(resReq);
		
		//This is added for printing contract details for asset
		ContractFactory.printContractDetail(contract);
		
		PortletSession session = resReq.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(resReq));
		try {
			contract.setSessionHandle(crmSessionHandle);

			String viewType = resReq.getParameter(ChangeMgmtConstant.VIEWPARAM);
			
			LOGGER.debug("view type is " + viewType);

			if (ChangeMgmtConstant.BOOKMARKED.equalsIgnoreCase(viewType)) {
				contract.setFavoriteFlag(true);
			} else {
				contract.setFavoriteFlag(false);
			}

			if (LexmarkConstants.VIEWTYPE_MANAGED_DEVICES
					.equalsIgnoreCase(viewType)) {
				contract.setAssetType(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES);
			} else if (LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES
					.equalsIgnoreCase(viewType)) {
				contract.setAssetType(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES);
			}

			// Always set this so that favorites can be identified
			String contactId = PortalSessionUtil.getContactId(resReq.getPortletSession());
			contract.setContactId(contactId);
			loadFilterCriteria(resReq, contract);
			
			PortletRequest portletRequest = (PortletRequest) resReq.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
			PortletSession portletSession = portletRequest.getPortletSession();
			portletSession.removeAttribute("searchName",PortletSession.APPLICATION_SCOPE);
			portletSession.removeAttribute("searchValue",PortletSession.APPLICATION_SCOPE);
			
			String DATEFORMAT = "MM/dd/yyyy" ;
		    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		    final String utcTime = sdf.format(new Date());
				contract.setEntitlementEndDate(utcTime);
			
			LOGGER.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			LOGGER.info("start printing lex LOGGER");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			LOGGER.info("end printing lex loggger");
			
			Long startTime = System.currentTimeMillis();
			AssetListResult deviceListResult = orderSuppliesAssetService.retrieveAllDeviceList(contract);
			Long endTime = System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_RETRIEVEALLDEVICELIST, startTime,endTime, PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			List<Asset> deviceList = deviceListResult.getAssets();

			LOGGER.debug("List size is " + deviceList.size());

			for (Asset device : deviceList) {
				ProductImageContract productImageContract = new ProductImageContract();
				productImageContract.setPartNumber(device.getProductTLI());
				ProductImageResult productImageResult = productImageService
						.retrieveProductImageUrl(productImageContract);
				device.setProductImageURL(productImageResult
						.getProductImageUrl());
				sharedPortletController.assembleDevice(device,
						resReq.getLocale());
			}
			String content = getXmlOutputGeneratorUtil(resReq.getLocale())
					.convertAssetToXML(resReq, deviceList,
							deviceListResult.getTotalCount(),
							contract.getStartRecordNumber(),
							resReq.getContextPath(),
							PortalSessionUtil.getContactId(session),
							LexmarkSPConstants.ASSET_LIST_TYPE);

			PrintWriter out = resResp.getWriter();
			resResp.setContentType(ChangeMgmtConstant.CONTENTTYPEXML);
			out.print(content);
			out.flush();
			out.close();
		} catch (Exception ex) {

			LOGGER.debug("Exception "+ex.getMessage());

		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		LOGGER.debug("------------- Step 1---retriveDeviceList end---------["
				+ System.nanoTime() + "]");		
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVEASSETLIST);
	}
	
	/**
	 * Common Method for address list population to be shown in popups grid and 
	 * in addresslist page  
	 * @param resReq  
	 * @param resResp 
	 */
	@ResourceMapping(value="addressListPopulate")
	public void retrieveAddressList(ResourceRequest resReq, ResourceResponse resResp)
	{	
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVEADDRESSLIST);
		Boolean isAddrPopUpVal=Boolean.valueOf(resReq.getParameter(ChangeMgmtConstant.ISPOPUP));		
		LOGGER.debug("Value is ====>" + isAddrPopUpVal);		
		LOGGER.debug("-------------serviceAddressListURL started---------");
		String contextPath = resReq.getContextPath();
		LOGGER.debug("the contextPath is" + contextPath);
		AddressListContract addressListContract = ContractFactory.getAddressListContract(resReq);
		
		/*****Added By Sourav*****/
		PortletSession session = resReq.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(
		PortalSessionUtil.getSiebelCrmSessionHandle(resReq));
		try {
			addressListContract.setSessionHandle(crmSessionHandle);
		} catch (Exception e) {
			LOGGER.debug("Exception message" + e.getMessage());
		}
		/**************** END ***/

		String isFavorite = resReq.getParameter("isFavorite");
		if (isFavorite != null && isFavorite.equalsIgnoreCase("true")) {
			LOGGER.debug("***********the searchCriterias is not null******************");
			
			addressListContract.setFavoriteFlag(true);
		} else {
			addressListContract.setFavoriteFlag(false);
		}
		
		/*********Added for LBS*********///
		if(("true".equalsIgnoreCase(resReq.getParameter("lbsFlag")))){
			addressListContract.setLbsFlag(true);
		}
		
		String addressCleansedFlag = resReq.getParameter("addressCleansedFlag");
		if(StringUtils.isNotBlank(addressCleansedFlag)&&addressCleansedFlag.equalsIgnoreCase("true")){
			addressListContract.setCleansedAddress(true);
		}
		addressListContract.setContactId(PortalSessionUtil.getContactId(session));
		//This is added for printing contract details for address
		LOGGER.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContent(addressListContract, LOGGER);
		LOGGER.info("end printing lex loggger");
		try {
			
			Long startTime = System.currentTimeMillis();
			AddressListResult addressListResult = serviceRequestService.retrieveAddressList(addressListContract);
			Long endTime = System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_RETRIEVEADDRESSLIST, startTime,endTime, PerformanceConstant.SYSTEM_SIEBEL,addressListContract);
			LOGGER.debug("List Total Count is "+ addressListResult.getTotalCount());
			LOGGER.debug("List Total Count is "+ addressListResult.getAddressList().size());
			
		String content = getXmlOutputGeneratorUtil(resReq.getLocale())
				.convertAddressListToXML(addressListResult.getAddressList(),
						addressListResult.getTotalCount(),addressListContract.getStartRecordNumber(),
						contextPath,isAddrPopUpVal);
		
		PrintWriter out = resResp.getWriter();
		resResp.setContentType(ChangeMgmtConstant.CONTENTTYPEXML);
		out.print(content);
		out.flush();
		out.close();
		LOGGER.debug("-------------serviceAddressListURL end---------");
		}catch(Exception ex)
		{
			LOGGER.debug("Exception "+ex.getMessage());
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVEADDRESSLIST);
	}
	/**
	 * Method to bookmark address 
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
		LOGGER.enter(this.getClass().getSimpleName(),METH_SETSERVICEADDRESSFAVOURITEFLAG);
		boolean success = false;
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		//flatStatus true represents to Unbookmark and false means Bookmark
		try {
			LOGGER.debug("-------------setAddressFavourite started---------");				
			FavoriteAddressContract favoriteAddressContract	=  ContractFactory.getFavoriteAddressContract();
			FavoriteResult favoriteResult=null;
			
			favoriteAddressContract.setSessionHandle(crmSessionHandle);
			favoriteAddressContract.setFavoriteAddressId(favoriteAddressId);
			
			favoriteAddressContract.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
			favoriteAddressContract.setFavoriteFlag(!flagStatus);
			
			LOGGER.debug("****************Starting to Print the FAVORITE ADDRESS CONTRACT Data******************");

			LOGGER.debug("Favourite Address ID ----------------- " + favoriteAddressContract.getFavoriteAddressId());
			LOGGER.debug("Favourite contact ID ----------------- " + favoriteAddressContract.getContactId());
			LOGGER.debug("Favorite Address FLAG ----------------- " + favoriteAddressContract.isFavoriteFlag());
			
			LOGGER.debug("****************Ending Printing of the FAVORITE ADDRESS CONTRACT Data******************");

			LOGGER.debug("-------------before setting contract favourite---------");		
			
			LOGGER.info("start printing lex LOGGER");
			ObjectDebugUtil.printObjectContent(favoriteAddressContract, LOGGER);
			LOGGER.info("end printing lex loggger");
			Long startTime = System.currentTimeMillis();
			
			favoriteResult = serviceRequestService.updateUserFavoriteAddress(favoriteAddressContract);
			Long endTime = System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_UPDATEUSERFAVORITEADDRESS, startTime,endTime, PerformanceConstant.SYSTEM_AMIND,favoriteAddressContract);
			
			LOGGER.debug("-------------setContactFavourite end---------");
			success = favoriteResult.isResult();
		} catch (Exception e) {
			success = false;
			e.getMessage();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		LOGGER.debug("success: "+success);
		String errorCode = success? flagStatus ? "message.servicerequest.unBookmarkAddressSuccessfully" : "message.servicerequest.bookmarkAddressSuccessfully"
				:"exception.servicerequest.setServiceAddressFavouriteFlag";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteAddressId+"\"");
		
		LOGGER.exit(this.getClass().getSimpleName(),METH_SETSERVICEADDRESSFAVOURITEFLAG);
	}

	/**
	 * Method to show the addresslist popup 
	 * @param req 
	 * @param resp 
	 * @param model 
	 * @return String 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "action=showAddressListPopUp")
	public String showAddressListPopUp(RenderRequest req, RenderResponse resp, Model model) {
		LOGGER.enter(this.getClass().getSimpleName(), METH_SHOWADDRESSLISTPOPUP);
		LOGGER.debug("PortalSessionUtil.getContactId(req.getPortletSession()) is "+PortalSessionUtil.getContactId(req.getPortletSession()));
		/* This will get the Account Name from the session */
		PortletSession session=req.getPortletSession();
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		if(accDetails != null){
			String accountName=accDetails.get("accountName");
			LOGGER.debug("Account Name ::::   "+accountName);
			model.addAttribute("accountName", accountName);	
		}
		String addressCleanseFlag="false";
		if(req.getParameter("addressCleansedFlag")!=null){
			addressCleanseFlag = req.getParameter("addressCleansedFlag");
		}
		if(req.getParameter("g")!=null){
			LOGGER.debug("include Grid==="+req.getParameter("g"));
			model.addAttribute("includeGrid", req.getParameter("g"));
		}
		
		
		model.addAttribute("addressCleansedFlag", addressCleanseFlag);
		retrieveGridSetting("serviceAddressGridbox", req, resp, model);
		LOGGER.exit(this.getClass().getSimpleName(), METH_SHOWADDRESSLISTPOPUP);
		resp.setContentType("UTF-8");
		return ChangeMgmtConstant.ADDRLISTPOPUPPATH;
	}

	/**
	 * Method to show the CHL Tree popup
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping(params = "action=showCHLTreePopUp")
	public String showCHLTreePopUp(RenderRequest request,
			RenderResponse response) {
		LOGGER.enter(this.getClass().getSimpleName(), METH_SHOWCHLTREEPOPUP);
		LOGGER.exit(this.getClass().getSimpleName(), METH_SHOWCHLTREEPOPUP);
		response.setContentType("UTF-8");
		return ChangeMgmtConstant.CHLTREEPATH;
		
	}

	/**
	 * Method to open the create new request popup 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */		
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "action=createNewRequestPopUp")
	public String createNewRequestPopUp(Model model, RenderRequest request,
			RenderResponse response) {
		LOGGER.enter(this.getClass().getSimpleName(), METH_CREATENEWREQUESTPOPUP);
		try{
			PortletSession portletSession = request.getPortletSession();
			Boolean isEmployeeFlag=false;
			if(LexmarkConstants.USER_SEGMENT_EMPLOYEE.equalsIgnoreCase(PortalSessionUtil.getUserSegment(request.getPortletSession()))) {
				isEmployeeFlag  = true;
			}
			LOGGER.debug(" is Lexmark Employee flag is +" + isEmployeeFlag);
			portletSession.setAttribute(ChangeMgmtConstant.ISLEXEMPLOYEE,isEmployeeFlag ,PortletSession.APPLICATION_SCOPE);
			
			LOGGER.debug(" MDM Level is ----"+ PortalSessionUtil.getMdmLevel(portletSession));
			model.addAttribute("mdmLevel", PortalSessionUtil.getMdmLevel(portletSession));
			
			
			//Added for Role Based Access
			/*Below Commented
			 * This is not required as it is 
			 * called from HistoryController as 
			 * well as from DeviceController.
			 * */
			/*Added by sankha for LEX:AIR00077400 */
			checkUserRoles(portletSession, request); 
			//lets call the new method here
			PortletSession portltSession = request.getPortletSession();
			Map<String, String> requestAcessMap=(Map<String, String>)portltSession.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);
			LOGGER.debug("### request access map is "+ requestAcessMap);
			if(requestAcessMap.get(CREATE_SERVICE_REQUEST).equalsIgnoreCase("true")){
				model.addAttribute("createServiceRequestFlag", "true");
			}
			/*End LEX:AIR00077400 */
			final String chkConsElgFlag=request.getParameter(ChangeMgmtConstant.CHECKCONSELGFLAG);
			if(chkConsElgFlag!=null){
				request.setAttribute(ChangeMgmtConstant.CHECKCONSELGFLAG,chkConsElgFlag.trim());
			}
			/*
			 * chkConsElgFlag
			 * The above flag needs to be true to show the orderSupplies link or not
			 * so if its flase or null we don't need to call the AgreementList
			 * to get the entitlement flags. 
			 * */
			
			LOGGER.debug(" Before calling checkAccountFlag method");
			
			String accountId = request.getParameter("accountId");
			LOGGER.debug("account id is ="+accountId);
			model.addAttribute("accountId", accountId);
			/*
			 * account id is null means the request is from 
			 * Requests tab and not from Device Finder
			 * */
			LOGGER.debug("is consumable asset = "+chkConsElgFlag);
			String currURL=response.createRenderURL().toString();
			LOGGER.debug("currURL==="+currURL);
			boolean isPartnerRequest=currURL.indexOf(ChangeMgmtConstant.ISPARTNERPORTAL)!=-1;
			LOGGER.debug("isPartnerRequest==="+isPartnerRequest);
			
			request.setAttribute("isPartnerRequest", isPartnerRequest);
			
			if(accountId!=null && chkConsElgFlag!=null && chkConsElgFlag.equalsIgnoreCase("true")){
				LOGGER.debug("is consumable asset flag = "+chkConsElgFlag+"Going for agreement call to get Entitlement/expedite flags");
				checkEntitlementAndExpediteAccountFlag(request);
			}else{
				/*The request is from Requests Tab
				 * Need to check whether Entitlement flags are already there
				 * or not in session.
				 * */
				Map<String, String> requestAccessMap=(Map<String, String>)portletSession.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);
				LOGGER.debug("request access map is "+ requestAccessMap + "is catalog key present="+requestAccessMap.containsKey(ChangeMgmtConstant.CATALOG_ENTITLEMENT_FLAG)
						+"is asset key present="+requestAccessMap.containsKey(ChangeMgmtConstant.ASSET_ENTITLEMENT_FLAG));
				Map<String,String> accountCurrentDetailsPartner=(Map<String, String>)portletSession.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, PortletSession.APPLICATION_SCOPE);
				
				portletSession.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILSPARTNER, accountCurrentDetailsPartner,PortletSession.APPLICATION_SCOPE);
				
				if(requestAccessMap!= null && !requestAccessMap.containsKey(ChangeMgmtConstant.CATALOG_ENTITLEMENT_FLAG) &&
						!requestAccessMap.containsKey(ChangeMgmtConstant.ASSET_ENTITLEMENT_FLAG)){
					LOGGER.debug("About to call the method checkEntitlementAndExpediteAccountFlag");
					checkEntitlementAndExpediteAccountFlag(request);
				}
				
				
			}
			
			final String assetId=request.getParameter(ChangeMgmtConstant.ASSETID);
			if(assetId!=null)
			{
				LOGGER.debug("Again reset the assetId in request to forward to consumables details page");
				request.setAttribute(ChangeMgmtConstant.ASSETID, assetId);
			}
			
			final String createSrFlag=request.getParameter(ChangeMgmtConstant.CREATESERVICEREQFLAG);
			if(createSrFlag!=null){
				request.setAttribute(ChangeMgmtConstant.CREATESERVICEREQFLAG,createSrFlag.trim());
			}
			final String pageType = request.getParameter("pageType");
			LOGGER.debug("Page Type is "+pageType);
			model.addAttribute("pageType", pageType);
			String accountId1 = request.getParameter("accountId");
			LOGGER.debug("accountId1 id is ="+accountId1);
			model.addAttribute("accountId1", accountId1);

			String fleetManageDropFlag  = (String)portletSession.getAttribute("fleetManageDropFlag", portletSession.APPLICATION_SCOPE);			
			    
			LOGGER.debug("Setting LBS Flag to true common"+ fleetManageDropFlag);
			if(fleetManageDropFlag != null && fleetManageDropFlag.equalsIgnoreCase("true")){
			
				LOGGER.debug("Setting fleetManageDropFlag to true ");
				model.addAttribute("fleetManageDropFlag", "true");
			}
			else{
				model.addAttribute("fleetManageDropFlag","false");
				LOGGER.debug("Setting fleetManageDropFlag to false common");
			}
			
			}catch(Exception ex)
			{
				LOGGER.debug("Exception "+ex.getMessage());
				request.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);//Allow a message to be shown on popup
			}
		LOGGER.exit(this.getClass().getSimpleName(), METH_CREATENEWREQUESTPOPUP);
		response.setContentType("UTF-8");
		//Changes MPS 2.1
		LOGGER.debug("include Grid==="+request.getParameter("g"));
		model.addAttribute("includeGrid", request.getParameter("g"));
		
		
		
		//Ends Changes MPS 2.1
			return ChangeMgmtConstant.CRREQPOPUPPATH;
	}

	
	/**
	 * @param portletSession 
	 * @param request 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void checkUserRoles(PortletSession portletSession,
			RenderRequest request) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), METH_CHECKUSERROLES);
		Map<String, String> requestAccessMap= (Map<String, String>)portletSession.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("request acccess map check user roles " + requestAccessMap);
		
		if (requestAccessMap == null || requestAccessMap.isEmpty()){
			LOGGER.debug("req access map is empty in session, so creating a new one");
			requestAccessMap = new HashMap<String, String>();
		}

		if(requestAccessMap.size()<=5){
			LOGGER.debug("Req access map size contains only history flags");
		List<String> userRoleList = PortalSessionUtil.getUserRoles(portletSession);
		
		LOGGER.debug("CommonController ---> userRoleList-->"+userRoleList);
		
		 String userType = PortalSessionUtil.getUserType(request.getPortletSession());
		 AccountAccess accountReqAccess = null;
		 String createServiceReq ="";
		 String createSuppliesReq ="";
		 String createAcctMgmtReq ="";
		 String createHardwareReq ="";
		 String createMapSRFlag ="";
		
		//This logic has been put temporarylly, need to verify later based on access role
		if(userType != null && userType.equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE)){
			accountReqAccess = (AccountAccess)verifyRequestAccessFlag4Acct(request);
			
		}else if(userType != null && userType.equalsIgnoreCase(LexmarkConstants.PORTAL_CUSTOMER)){
		  //This logic is for customer
			LOGGER.debug("*****User is a Customer*****");
			
			createServiceReq = getEntitlementStatus( request, CREATE_SERVICE_REQUEST);
			createSuppliesReq = getEntitlementStatus( request, CREATE_SUPPLIES_REQUEST);
			createAcctMgmtReq = getEntitlementStatus( request, CREATE_CHANGE_MGMT_REQUEST);
			createHardwareReq = getEntitlementStatus( request, CREATE_HARDWARE_REQUEST); // Added for Hardware Request in MPS2.1
			createMapSRFlag = getEntitlementStatus( request, CREATE_MAP_REQUEST);
			
			LOGGER.debug("createServiceReq from db " + createServiceReq);
			LOGGER.debug("createSuppliesReq from db " + createSuppliesReq);
			LOGGER.debug("createAcctMgmtReq from db " + createAcctMgmtReq);
			LOGGER.debug("createHardwareReq from db " + createHardwareReq);
			LOGGER.debug("createMapSRFlag from db " + createMapSRFlag);

			if(createServiceReq != null && createServiceReq.equalsIgnoreCase("False")){
					LOGGER.debug("***Create Service req is false*****");
					requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
				}
				
				if(createSuppliesReq != null && createSuppliesReq.equalsIgnoreCase("False"))
				{
					LOGGER.debug("***createSuppliesReq is false*****");
					requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
				}
				
				if(createAcctMgmtReq != null && createAcctMgmtReq.equalsIgnoreCase("False"))
				{
					LOGGER.debug("***createAcctMgmtReq is false*****");
					requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
				}
				
				if(createHardwareReq != null && createHardwareReq.equalsIgnoreCase("False"))
				{
					LOGGER.debug("***createHardwareReq is false*****");
					requestAccessMap.put(CREATE_HARDWARE_REQUEST, "False");
				}
				
				if ((createServiceReq != null && createServiceReq.equalsIgnoreCase("True")) ||
						(createSuppliesReq != null && createSuppliesReq.equalsIgnoreCase("True")) || 
						(createAcctMgmtReq != null && createAcctMgmtReq.equalsIgnoreCase("True")) ||
						(createHardwareReq != null && createHardwareReq.equalsIgnoreCase("True"))){
					LOGGER.debug("***Before calling account access*****");
					accountReqAccess = (AccountAccess)verifyRequestAccessFlag4Acct(request);				
				}
			
		}
		
		if(userType != null && (userType.equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE) || userType.equalsIgnoreCase(ChangeMgmtConstant.VENDORREQTYPE))){
			if (accountReqAccess != null){
				LOGGER.debug("***Setting the CREATE access flags*****");
	
				if(requestAccessMap.containsKey(CREATE_SUPPLIES_REQUEST))
					{
						LOGGER.debug("***Map contains supplies req*****");
						requestAccessMap.put(CREATE_SERVICE_REQUEST, accountReqAccess.getCreateServiceReqFlag());
						requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, accountReqAccess.getCreateAccountMgmtReqFlag());
					}
					else if(requestAccessMap.containsKey(CREATE_CHANGE_MGMT_REQUEST))
					{
						LOGGER.debug("***map contains cm req*****");
						requestAccessMap.put(CREATE_SERVICE_REQUEST, accountReqAccess.getCreateServiceReqFlag());
						requestAccessMap.put(CREATE_SUPPLIES_REQUEST, accountReqAccess.getCreateSuppliesReqFlag());
					}
					else if(requestAccessMap.containsKey(CREATE_SERVICE_REQUEST))
					{
						LOGGER.debug("***map contains service req*****");
						requestAccessMap.put(CREATE_SUPPLIES_REQUEST, accountReqAccess.getCreateSuppliesReqFlag());
						requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, accountReqAccess.getCreateAccountMgmtReqFlag());
					}
					else{
						requestAccessMap.put(CREATE_SERVICE_REQUEST, accountReqAccess.getCreateServiceReqFlag());
						requestAccessMap.put(CREATE_SUPPLIES_REQUEST, accountReqAccess.getCreateSuppliesReqFlag());
						requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, accountReqAccess.getCreateAccountMgmtReqFlag());
					}
				requestAccessMap.put(CREATE_HARDWARE_REQUEST, accountReqAccess.getCreateHardwareReqFlag());// Added for Hardware Request in MPS2.1
				requestAccessMap.put(CREATE_MAP_REQUEST, accountReqAccess.getCreateMapSRFlag());//LBS MapSR Flag
				requestAccessMap.put(FLEET_MANAGER_FLAG, accountReqAccess.getFleetManagerFlag());//LBS MapSR Flag
			}else{
				LOGGER.debug("***Customer does not have Create access*****");
				requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
				requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "False");
				requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
				requestAccessMap.put(CREATE_HARDWARE_REQUEST, "False");// Added for Hardware Request in MPS2.1
				requestAccessMap.put(CREATE_MAP_REQUEST, "False");//LBS MapSR Flag
				requestAccessMap.put(FLEET_MANAGER_FLAG, "False");//Fleet Manager Flag
			}
		}
		
		if(userType != null && userType.equalsIgnoreCase(LexmarkConstants.PORTAL_CUSTOMER)){
			if (accountReqAccess != null){
				LOGGER.debug("***Setting the CREATE access flags*****");
	
				if(requestAccessMap.containsKey(CREATE_SUPPLIES_REQUEST))
					{
						LOGGER.debug("***Map contains supplies req*****");
						if(createServiceReq != null && createServiceReq.equalsIgnoreCase("true") && accountReqAccess.getCreateServiceReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(CREATE_SERVICE_REQUEST, "true");
						}else{
							requestAccessMap.put(CREATE_SERVICE_REQUEST, "false");
						}
						
						if(createAcctMgmtReq != null && createAcctMgmtReq.equalsIgnoreCase("true") && accountReqAccess.getCreateAccountMgmtReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "true");
						}else{
							requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "false");
						}
					}
					else if(requestAccessMap.containsKey(CREATE_CHANGE_MGMT_REQUEST))
					{
						LOGGER.debug("***map contains cm req*****");
						
						if(createServiceReq != null && createServiceReq.equalsIgnoreCase("true") && accountReqAccess.getCreateServiceReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(CREATE_SERVICE_REQUEST, "true");
						}else{
							requestAccessMap.put(CREATE_SERVICE_REQUEST, "false");
						}
						
						if(createSuppliesReq != null && createSuppliesReq.equalsIgnoreCase("true") && accountReqAccess.getCreateSuppliesReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "true");
						}else{
							requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "false");
						}
					}
					else if(requestAccessMap.containsKey(CREATE_SERVICE_REQUEST))
					{
						LOGGER.debug("***map contains service req*****");
						
						if(createAcctMgmtReq != null && createAcctMgmtReq.equalsIgnoreCase("true") && accountReqAccess.getCreateAccountMgmtReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "true");
						}else{
							requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "false");
						}
						
						if(createSuppliesReq != null && createSuppliesReq.equalsIgnoreCase("true") && accountReqAccess.getCreateSuppliesReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "true");
						}else{
							requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "false");
						}
					}
					else{
						requestAccessMap.put(CREATE_SERVICE_REQUEST, accountReqAccess.getCreateServiceReqFlag());
						requestAccessMap.put(CREATE_SUPPLIES_REQUEST, accountReqAccess.getCreateSuppliesReqFlag());
						requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, accountReqAccess.getCreateAccountMgmtReqFlag());
					}
				
					if(createHardwareReq != null && createHardwareReq.equalsIgnoreCase("true") && accountReqAccess.getCreateHardwareReqFlag().equalsIgnoreCase("true")){
						requestAccessMap.put(CREATE_HARDWARE_REQUEST, "true");
					}else{
						requestAccessMap.put(CREATE_HARDWARE_REQUEST, "false");
					}// Added for Hardware Request in MPS2.1
					
					//LBS MapSR Flag
					if(createMapSRFlag != null && createMapSRFlag.equalsIgnoreCase("true") && accountReqAccess.getCreateMapSRFlag().equalsIgnoreCase("true")){
						requestAccessMap.put(CREATE_MAP_REQUEST, "true");
					}else{
						requestAccessMap.put(CREATE_MAP_REQUEST, "false");
					}
					
					//Fleet manager Flag
					if(accountReqAccess.getFleetManagerFlag().equalsIgnoreCase("true")){
						requestAccessMap.put(FLEET_MANAGER_FLAG, "true");
					}else{
						requestAccessMap.put(FLEET_MANAGER_FLAG, "false");
					}
			}else{
				LOGGER.debug("***Customer does not have Create access*****");
				requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
				requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "False");
				requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
				requestAccessMap.put(CREATE_HARDWARE_REQUEST, "False");// Added for Hardware Request in MPS2.1
				requestAccessMap.put(CREATE_MAP_REQUEST, "False");//LBS MapSR Flag
				requestAccessMap.put(FLEET_MANAGER_FLAG, "False");//Fleet Manager Flag
			}
		}
		
		if(userType != null && userType.equalsIgnoreCase(ChangeMgmtConstant.VENDORREQTYPE))
		{
			LOGGER.debug("User is a partner----->");
			
			if(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_VIEW) 
			&& !userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN)
			&& !userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)
			&& !userRoleList.contains(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR)){
				
				LOGGER.debug("Vendor has role service view ");
				requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
				requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "False");
				requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
				requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "True");
			}
			else{
				LOGGER.debug("***Vendor with Create access for Supplies Request and Service Request*****");
				requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
				requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "True");
				requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
				requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "True");
			}
		}
		
		/*
		 * MPS 2.1 Changes below section is for Showing Asset Acceptance
		 * Link in create new request popup
		 * */
		if( userRoleList.contains(ChangeMgmtConstant.ACCOUNT_MANAGEMENT)){
			requestAccessMap.put(ChangeMgmtConstant.SHOW_ASSET_ACCEPTANCE, ChangeMgmtConstant.TRUE);
			
			if(userRoleList.size()==1){
				requestAccessMap.put(ChangeMgmtConstant.ONLY_ASSET_ACCEPTANCE, ChangeMgmtConstant.TRUE);
				requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
				requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "False");
				requestAccessMap.put(CREATE_HARDWARE_REQUEST, "False");
			}
		}
		/*
		 * Changes Ends MPS 2.1*/
		
		
		
		if(requestAccessMap!=null) {
			LOGGER.debug("serviceRequestsAccessMap size is " + requestAccessMap.size());
		}
		LOGGER.debug("requestAccessMap in chk user roles is -->" + requestAccessMap);
		portletSession.setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, requestAccessMap,
				PortletSession.APPLICATION_SCOPE);
		
		if(requestAccessMap!= null && requestAccessMap.containsKey(FLEET_MANAGER_FLAG)){
			LOGGER.debug("FLEET_MANAGER_FLAG is there" + requestAccessMap.get(FLEET_MANAGER_FLAG));
			portletSession.setAttribute("fleetManageDropFlag", requestAccessMap.get(FLEET_MANAGER_FLAG), PortletSession.APPLICATION_SCOPE);
		}
		}
		else
		{
			LOGGER.debug("Map already in session, so no need to make calls to Siebel or DB");
			LOGGER.debug("serviceRequestsAccessMap size is " + requestAccessMap.size());
			LOGGER.debug("requestAccessMap in chk user roles is -->" + requestAccessMap);
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_CHECKUSERROLES);
	}
	
	/**
	 * @param request 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void checkEntitlementAndExpediteAccountFlag(PortletRequest request) throws Exception {
		
		LOGGER.enter(this.getClass().getSimpleName(), "checkEntitlementAndExpediteAccountFlag");
		LOGGER.debug("Enter to the checkEntitlementAndExpediteAccountFlag method ");
		
		boolean catalogEntitlementFlag = false;
		boolean assetEntitlementFlag = false;
		boolean singleAccount = false;
		List<Account> accountListSingle = new ArrayList<Account>();
				
		SiebelAccountListContract siebelAccountListContract = ContractFactory.getSiebelAccountListContract(request);
		/*
		 * Below Section means the request from 
		 * Device Finder*/
		String accountId=request.getParameter("accountId");
		LOGGER.debug("Account Id coming as "+accountId);
		if(accountId!=null){
			siebelAccountListContract.setAccountId(accountId);
		}
		
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		
		
		PortletSession portletSession=request.getPortletSession();
		AccountFlagResult accountFlagResult;
		try {
			siebelAccountListContract.setSessionHandle(crmSessionHandle);
			long timeBeforeCall=System.currentTimeMillis();	
		LOGGER.debug("isPartnerRequest flags---->"+request.getAttribute("isPartnerRequest"));
			if((Boolean)request.getAttribute("isPartnerRequest")){
				siebelAccountListContract.setPartnerPortal(true);
			}else{
				siebelAccountListContract.setPartnerPortal(false);
			}
			ObjectDebugUtil.printObjectContent(siebelAccountListContract, LOGGER);
			accountFlagResult = globalService.retrieveEntitelmentFlags(siebelAccountListContract);
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_RETRIEVEENTITELMENTFLAGS, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,siebelAccountListContract);
			List<Account> accountList = accountFlagResult.getAccountList();
			if(accountList!=null){
			LOGGER.debug("accountList.size()= "+accountList.size());
			String servicePartQuantity = "";
			String suppliesPartQuantity = "";
			for(int i=0;i<accountList.size();i++){
				LOGGER.debug("account name is ==================== "+accountList.get(i).getAccountName()+" and account ID is ==================== "+accountList.get(i).getAccountId());
				
				LOGGER.debug("mps Quantity is ==================== "+accountList.get(i).getMpsQuantity());
				LOGGER.debug("service part qty is ==================== "+accountList.get(i).getQuantityServices());
				LOGGER.debug("supplies part qty is ==================== "+accountList.get(i).getQuantitySupplies());
				if(null != accountList.get(i).getQuantityServices()){
					servicePartQuantity = accountList.get(i).getQuantityServices();
				}				
				if(null != accountList.get(i).getQuantitySupplies()){
					suppliesPartQuantity = accountList.get(i).getQuantitySupplies();
				}
			}
			if(null == portletSession.getAttribute("servicePartQuantity",portletSession.APPLICATION_SCOPE)){
				LOGGER.debug("service part qty is null");
				portletSession.setAttribute("servicePartQuantity", servicePartQuantity ,PortletSession.APPLICATION_SCOPE);
			}
			else{
				LOGGER.debug("service part qty is not null");
			}
			if(null == portletSession.getAttribute("suppliesPartQuantity",portletSession.APPLICATION_SCOPE)){
				LOGGER.debug("supplies part qty is null");
				portletSession.setAttribute("suppliesPartQuantity", suppliesPartQuantity ,PortletSession.APPLICATION_SCOPE);
			}
			else{
				LOGGER.debug("supplies part qty is not null");
			}
			
			LOGGER.debug("servicePartQuantity set in session =================== "+servicePartQuantity);
			LOGGER.debug("suppliesPartQuantity set in session =================== "+suppliesPartQuantity);
			if(accountList.size()==1) {
				LOGGER.debug("accountList size is 1");
				singleAccount = true;
				accountListSingle.add(0, accountList.get(0));
				LOGGER.debug("account agreement Name="+accountList.get(0).getAgreementName());
				
			}
			}
			
			LOGGER.debug("ACCOUNT FLAGS--::CATALOG ENTITLEMENT="+accountFlagResult.isCatalogEntitlementFlag()+
					"::ASSET ENTITLEMENT="+accountFlagResult.isAssetEntitlementFlag());
			if(accountFlagResult.isCatalogEntitlementFlag()){
			catalogEntitlementFlag=true;
			}
			
			if(accountFlagResult.isAssetEntitlementFlag()){
			assetEntitlementFlag=true;
			}
			
		LOGGER.debug("after ending for");
		}catch(Exception e){
			LOGGER.debug("Exception message" + e.getMessage());
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		
		Map<String, String> requestAccessMap=(Map<String, String>)portletSession.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("requestAccessMap read here is "+requestAccessMap);
		requestAccessMap.put(ChangeMgmtConstant.CATALOG_ENTITLEMENT_FLAG,String.valueOf(catalogEntitlementFlag));
		requestAccessMap.put(ChangeMgmtConstant.ASSET_ENTITLEMENT_FLAG,String.valueOf(assetEntitlementFlag));
		
		
		Map<String, List> accountListMap=new HashMap<String, List>();
		accountListMap.put(ChangeMgmtConstant.ACCOUNTLIST,accountListSingle);
		if(singleAccount) {
			portletSession.setAttribute(ChangeMgmtConstant.ACCOUNTLISTMAP, accountListMap ,PortletSession.APPLICATION_SCOPE);
		}
				
		/*
		 * This portion is for Catalog Expedite
		 * */
		LOGGER.exit(this.getClass().getSimpleName(), "checkEntitlementAndExpediteAccountFlag");
	}

	/**
	 * Method to load the filter criteria 
	 * @param request 
	 * @param contract 
	 */
	private void loadFilterCriteria(ResourceRequest request,
			AssetListContract contract) {
		LOGGER.enter(this.getClass().getSimpleName(), METH_LOADFILTERCRITERIA);
		if (request.getParameter(ChangeMgmtConstant.COUNTRY) != null) {
			contract.getFilterCriteria().put("installAddress.country",
					request.getParameter(ChangeMgmtConstant.COUNTRY));
		}
		if (request.getParameter(ChangeMgmtConstant.PROVINCE) != null) {
			contract.getFilterCriteria().put("installAddress.province",
					request.getParameter(ChangeMgmtConstant.PROVINCE));
		}
		if (request.getParameter(ChangeMgmtConstant.STATE) != null) {
			contract.getFilterCriteria().put("installAddress.state",
					request.getParameter(ChangeMgmtConstant.STATE));
		}
		if (request.getParameter(ChangeMgmtConstant.CITY) != null) {
			contract.getFilterCriteria().put("installAddress.city",
					request.getParameter(ChangeMgmtConstant.CITY));
		}
		if (request.getParameter(ChangeMgmtConstant.CHLNODEID) != null) {
			contract.setChlNodeId(request.getParameter(ChangeMgmtConstant.CHLNODEID));
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_LOADFILTERCRITERIA);
	}

	/**
	 * This method is used to get the contact information
	 * @param request 
	 * @param response 
	 * @return AccountContact 
	 * @throws LGSRuntimeException 
	 */
	public AccountContact getContactInformation(PortletRequest request,
			PortletResponse response) throws LGSRuntimeException{
		LOGGER.enter(this.getClass().getSimpleName(), METH_GETCONTACTINFORMATION);
		
		/* This method has been changed for MPS 2.1
		 * Now calling getContactInfo from ContactController */
		
		AccountContact accountContact=null;
		try{
			accountContact = contactController.getContactInfo(request,response);
			}catch (RuntimeException e) {
				throw new LGSRuntimeException(e.getMessage());
			}
			catch(Exception ex)
			{
				throw new LGSRuntimeException(ex.getMessage());
			}
		
		LOGGER.exit(this.getClass().getSimpleName(), METH_GETCONTACTINFORMATION);
		return accountContact;
	}
	
	/**
	 * Method to retrieve device detail 
	 * @param contract 
	 * @return AssetResult 
	 * @throws LGSRuntimeException  
	 */
	public AssetResult retrieveAssetDetail(AssetContract contract)
			throws LGSRuntimeException {
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVEASSETDETAIL);
		AssetResult assetResult=null;
		try{
			Long startTime = System.currentTimeMillis();
			
		assetResult=orderSuppliesAssetService.retrieveDeviceDetail(contract);
		Long endTime = System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_RETRIEVEDEVICEDETAIL, startTime,endTime, PerformanceConstant.SYSTEM_SIEBEL,contract);
		
		}catch(Exception ex)
		{
			throw new LGSRuntimeException(ex.getMessage(), ex);
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVEASSETDETAIL);
		return assetResult;		
	}

	/**
	 * Method to set asset id for asset contract 
	 * @param assetId 
	 * @param request 
	 * @return AssetContract 
	 */
	public AssetContract getAssetContract(String assetId, RenderRequest request) {
		LOGGER.enter(this.getClass().getSimpleName(), METH_GETASSETCONTRACT);
		AssetContract contract = new AssetContract();
		PortletSession session = request.getPortletSession();
		if (assetId != null) {			
			contract.setAssetId(assetId);
			contract.setContactId(PortalSessionUtil.getContactId(session));
		} else
		{
			contract.setAssetId("");
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_GETASSETCONTRACT);
		return contract;
	}

	/**
	 * Common Method to set ServiceRequestContract object with Service Request Data from the form bean
	 * @param formData 
	 * @param request 
	 * @return CreateServiceRequestContract 
	 */
	public CreateServiceRequestContract getServiceReqContract(Object formData,
			ActionRequest request) {
		LOGGER.enter(this.getClass().getSimpleName(), METH_GETSERVICEREQCONTRACT);
		CreateServiceRequestContract serviceReqContract = new CreateServiceRequestContract();
		serviceReqContract.setMdmId(PortalSessionUtil.getMdmId(request
				.getPortletSession()));
		serviceReqContract.setMdmLevel(PortalSessionUtil.getMdmLevel(request
				.getPortletSession()));
		serviceReqContract.setUserType(PortalSessionUtil.getUserType(request.getPortletSession()));
		LOGGER.debug("user type is "+serviceReqContract.getUserType());
		AccountContact accContact=this.getContactInformation(request, null);
		
		if (formData instanceof ManageAssetForm) {
			LOGGER.debug("formdata is of masform====> ");
			serviceReqContract.setServiceRequest(((ManageAssetForm) formData)
					.getServiceRequest());
			serviceReqContract.getServiceRequest().setAsset(((ManageAssetForm) formData)
					.getAssetDetail()); // Added for MPS2.1 wave4
			
			//changes for page counts
			List<PageCounts> pageCountList = ((ManageAssetForm) formData).getAssetDetail().getPageCounts();
			List<PageCounts> modifiedPageCountList = new ArrayList<PageCounts>();
			
			for(int i=0;i<pageCountList.size();i++){
				String convertedDate=pageCountList.get(i).getDate();
				PageCounts modifiedPageCount = new PageCounts();
				modifiedPageCount.setName(pageCountList.get(i).getName());
				modifiedPageCount.setCount(pageCountList.get(i).getCount());
				
				if(pageCountList.get(i).getDate()!= null && !"".equals(pageCountList.get(i).getDate().trim())){
					String formatString = DateUtil.getDateFormatByLang(request.getLocale().getLanguage());
					formatString= formatString+" HH:mm:ss";
					DateFormat formatter = null;
			        formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
			        try {
						convertedDate=formatter.format(new SimpleDateFormat(formatString).parse(convertedDate));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						LOGGER.debug("Exception message" + e.getMessage());
					}
					LOGGER.debug("convertedDate--->>>"+convertedDate);					
									
					try {
						LOGGER.debug("The default date is to be shown--->>>"+DateUtil.converDateToGMTString((Date) formatter.parse(convertedDate),Float.parseFloat(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET))));
						modifiedPageCount.setDate (DateUtil.converDateToGMTString((Date) formatter.parse(convertedDate),Float.parseFloat(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET))));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						LOGGER.debug("Exception message" + e.getMessage());
					}								
				}else{
					modifiedPageCount.setDate ("");	
				}
				modifiedPageCountList.add(modifiedPageCount);
			}
			
			
			((ManageAssetForm) formData).getAssetDetail().setPageCounts(modifiedPageCountList);
			
			if(((ManageAssetForm) formData).getFleetManagementFlag()!=null && ((ManageAssetForm) formData).getFleetManagementFlag()!=""&&((ManageAssetForm) formData).getFleetManagementFlag().equalsIgnoreCase("true")) {
				LOGGER.debug("Setting LBS Flag to true in commoncontroller");
				serviceReqContract.setFleetManagementFlag(((ManageAssetForm) formData).getFleetManagementFlag());
			}
			else{
				LOGGER.debug("Setting LBS Flag to false commoncontroller");
				serviceReqContract.setFleetManagementFlag("false");
			}
			
			if(((ManageAssetForm) formData).getMoveType()!=null && ((ManageAssetForm) formData).getMoveType()!="") {
				serviceReqContract.setMoveType(((ManageAssetForm) formData).getMoveType());
			}
			if(((ManageAssetForm) formData).getNewAssetInfo()!=null) {
			if(((ManageAssetForm) formData).getNewAssetInfo().getIpAddress()!=null && ((ManageAssetForm) formData).getNewAssetInfo().getIpAddress()!="") {
				serviceReqContract.getServiceRequest().getAsset().setIpAddress(((ManageAssetForm) formData).getNewAssetInfo().getIpAddress());
			}
			if(((ManageAssetForm) formData).getNewAssetInfo().getHostName()!=null && ((ManageAssetForm) formData).getNewAssetInfo().getHostName()!="") {
				serviceReqContract.getServiceRequest().getAsset().setHostName(((ManageAssetForm) formData).getNewAssetInfo().getHostName());
			}
			if(((ManageAssetForm) formData).getNewAssetInfo().getDeviceTag()!=null && ((ManageAssetForm) formData).getNewAssetInfo().getDeviceTag()!="") {
				serviceReqContract.getServiceRequest().getAsset().setDeviceTag(((ManageAssetForm) formData).getNewAssetInfo().getDeviceTag());
			}
			if(((ManageAssetForm) formData).getNewAssetInfo().getAssetCostCenter()!=null && ((ManageAssetForm) formData).getNewAssetInfo().getAssetCostCenter()!="") {
				serviceReqContract.getServiceRequest().getAsset().setAssetCostCenter(((ManageAssetForm) formData).getNewAssetInfo().getAssetCostCenter());
			}
			if(((ManageAssetForm) formData).getNewAssetInfo().getChlNodeId()!=null && ((ManageAssetForm) formData).getNewAssetInfo().getChlNodeId()!="") {
				serviceReqContract.getServiceRequest().getAsset().setChlNodeId(((ManageAssetForm) formData).getNewAssetInfo().getChlNodeId());
			}
			if(((ManageAssetForm) formData).getNewAssetInfo().getChlNodeValue()!=null && ((ManageAssetForm) formData).getNewAssetInfo().getChlNodeValue()!="") {
				serviceReqContract.getServiceRequest().getAsset().setChlNodeValue(((ManageAssetForm) formData).getNewAssetInfo().getChlNodeValue());
			}
			
			}
			
			
			/* String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
			if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
				LOGGER.debug("Setting LBS Flag to true ");
				//request.setAttribute("fleetManagementFlag", "true");
				//serviceReqContract.setFleetManagementFlag(((ManageAssetForm) formData).getFleetManagementFlag());
				serviceReqContract.setFleetManagementFlag(fleetManagementFlag);
			}
			else{
				LOGGER.debug("Setting LBS Flag to false");
				serviceReqContract.setFleetManagementFlag("false");
			}*/
			
			serviceReqContract.getServiceRequest().setRequestor(accContact);
			serviceReqContract.setPrevSrNo(((ManageAssetForm)formData).getPrevSrNo());
			
			LOGGER.debug("In getService req contract prev SR no-----> " + ((ManageAssetForm)formData).getPrevSrNo());
			populateAreaSubArea(((ManageAssetForm)formData).getArea(),((ManageAssetForm)formData).getSubArea(),
					serviceReqContract);
			
		} else if (formData instanceof ManageAddressForm) {
			LOGGER.debug("form data is of maddressform============> ");
			serviceReqContract.setServiceRequest(((ManageAddressForm) formData)
					.getServiceRequest());
			
			serviceReqContract.setPrevSrNo(((ManageAddressForm)formData).getPrevSrNo());
			
			LOGGER.debug("area is " + ((ManageAddressForm)formData).getArea());
			
			LOGGER.debug("Sub area is " + ((ManageAddressForm)formData).getSubArea());
			populateAreaSubArea(((ManageAddressForm)formData).getArea(),((ManageAddressForm)formData).getSubArea(),
					serviceReqContract);
			serviceReqContract.getServiceRequest().setRequestor(accContact);
			//Added for CI 14-02-03
			serviceReqContract.getServiceRequest().setNotes(((ManageAddressForm)formData).getAttachmentDescription());
			
			
		} else if (formData instanceof ManageContactForm) {
			LOGGER.debug("form data is of mContact form==========> ");
			serviceReqContract.setServiceRequest(((ManageContactForm) formData)
					.getServiceRequest());
			
			serviceReqContract.setPrevSrNo(((ManageContactForm)formData).getPrevSRNumber());
			
			populateAreaSubArea(((ManageContactForm)formData).getArea(),((ManageContactForm)formData).getSubArea(),
					serviceReqContract);
			serviceReqContract.getServiceRequest().setRequestor(accContact);
			//Added for CI 14-02-03
			serviceReqContract.getServiceRequest().setNotes(((ManageContactForm)formData).getAttachmentDescription());
			LOGGER.debug("contact notes-->"+((ManageContactForm)formData).getAttachmentDescription());
		}
		else if (formData instanceof ManageCHLOthersForm) {
			LOGGER.debug("form data is of ManageCHLOthersForm ==========> ");
			serviceReqContract.setServiceRequest(((ManageCHLOthersForm) formData)
					.getServiceRequest());
			serviceReqContract.setPrevSrNo(((ManageCHLOthersForm)formData).getPrevSRNumber());
			serviceReqContract.getServiceRequest().setRequestor(accContact);			
			
		}else if (formData instanceof MapsRequestForm) {
			LOGGER.debug("form data is of MapsRequestForm ==========> ");
			serviceReqContract.setServiceRequest(((MapsRequestForm) formData)
					.getServiceRequest());
			serviceReqContract.setPrevSrNo(((MapsRequestForm)formData).getPrevSRNumber());
			serviceReqContract.getServiceRequest().setRequestor(accContact);		
			serviceReqContract.getServiceRequest().setNotes(((MapsRequestForm)formData).getNotesOrComments());
			
			
			//Added for #17607
			serviceReqContract.setMoveAssetFlag(((MapsRequestForm)formData).isMoveAsset());		
			serviceReqContract.setMoveContactSelectFlag(((MapsRequestForm)formData).isMoveContactSelect());
			//Changes End #17607
		}
		else if (formData instanceof CMTemplateRquestForm) {
			LOGGER.debug("form data is of ManageCHLOthersForm ==========> ");
			serviceReqContract.setServiceRequest(((CMTemplateRquestForm) formData)
					.getServiceRequest());
			serviceReqContract.setPrevSrNo(((CMTemplateRquestForm)formData).getPrevSRNumber());
			serviceReqContract.getServiceRequest().setRequestor(accContact);
		}else if(formData instanceof ManageReturnsForm){
			serviceReqContract.setServiceRequest(((CMTemplateRquestForm) formData)
					.getServiceRequest());
			serviceReqContract.getServiceRequest().setRequestor(accContact);
		}
		else if (formData instanceof AssetAcceptanceForm) {
			LOGGER.debug("form data is of AssetAcceptanceForm ==========> ");
			String cmArea=((AssetAcceptanceForm)formData).getCmArea();
			ServiceRequest sr=((AssetAcceptanceForm)formData).getServiceRequest();
			
			ListOfValues subarea=new ListOfValues();
			subarea.setValue(cmArea);
			sr.setSubArea(subarea);
			
			
			LOGGER.debug("Sub area is " + ((AssetAcceptanceForm)formData).getCmAreaValue());
			serviceReqContract.setServiceRequest(((AssetAcceptanceForm) formData)
					.getServiceRequest());
			serviceReqContract.setPrevSrNo(((AssetAcceptanceForm)formData).getPrevSRNumber());
			serviceReqContract.getServiceRequest().setRequestor(accContact);			
			
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_GETSERVICEREQCONTRACT);
		return serviceReqContract;
	}
	/**
	 * @param area 
	 * @param subArea 
	 * @param serviceReqContract 
	 */
	private void populateAreaSubArea(final String area, final String subArea,
			CreateServiceRequestContract serviceReqContract) {
		
		ListOfValues lovForArea = new ListOfValues();
		ListOfValues lovForSubArea = new ListOfValues();
		
		if(area!=null && subArea!=null)
		{
			if(serviceReqContract.getServiceRequest()!=null)
			{
				lovForArea.setValue(area);
				lovForSubArea.setValue(subArea);
				serviceReqContract.getServiceRequest().setArea(lovForArea);
				serviceReqContract.getServiceRequest().setSubArea(lovForSubArea);
			}
		}
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=forwardToattchmentJSP")
	public String forwardToJsp(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), METH_FORWARDTOJSP);
		PortletSession session = request.getPortletSession();
		Properties props = PropsFileLoadUtility.getConfigurationFile(ChangeMgmtConstant.CONFIG_FILE_PATH);
		String maxFileSize = props.getProperty(ChangeMgmtConstant.ATTACHMENT_MAX_SIZE);
		String maxFileCount = props.getProperty(ChangeMgmtConstant.ATTACHMENT_MAX_COUNT);
		String acceptMaxCount = props.getProperty(ChangeMgmtConstant.ATTACHMENT_MAX_COUNT_ACCEPT);
		String acceptMaxSize = props.getProperty(ChangeMgmtConstant.ATTACHMENT_MAX_SIZE_ACCEPT);

		session.removeAttribute("fileList1");

		FileUploadForm fileUploadForm = new FileUploadForm();

		model.addAttribute("maxFileSize", maxFileSize);
		model.addAttribute("maxFileCount", maxFileCount);
		model.addAttribute("acceptMaxCount", acceptMaxCount);
		model.addAttribute("acceptMaxSize", acceptMaxSize);
		model.addAttribute("fileUploadForm", fileUploadForm);
		LOGGER.exit(this.getClass().getSimpleName(), METH_FORWARDTOJSP);
		return ChangeMgmtConstant.ATTACHMENTPATH;
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param fileUploadForm 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=submitDocumentList")
	public String submitDocumentList(Model model, RenderRequest request,
			RenderResponse response,
			@ModelAttribute("fileUploadForm") FileUploadForm fileUploadForm)
			throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), METH_SUBMITDOCUMENTLIST);
		PortletSession session = request.getPortletSession();
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute("fileList1");
		List<File> fileList = new ArrayList<File>();
		Set<String> fileNames = fileMap.keySet();

		Iterator<String> it = fileNames.iterator();
        while (it.hasNext()) {
        		String fileName =  it.next();
                fileList.add(new File("/Attachment/" + fileName));
        }
		model.addAttribute("fileUploadForm", fileUploadForm);
		model.addAttribute("confirmation", "Files Uploaded...");
		LOGGER.exit(this.getClass().getSimpleName(), METH_SUBMITDOCUMENTLIST);
		return ChangeMgmtConstant.ATTACHMENT_FILE;
	}
	// Create the resource URL in the displayCHLTree.jsp so that the content can
	// be opened up in the overlay
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("chlTreeXMLURL")
	public void retrieveCHLTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVECHLTREEXML);
		sharedPortletController.retrieveCHLTreeXML(request, response, model);
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVECHLTREEXML);
	}

	// Create the resource URL in the jsp so that the content can be opened up
	// in the overlay
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("deviceLocationXMLURL")
	public void retrieveDeviceLocationTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(),METH_RETRIEVEDEVICELOCATIONTREEXML);
		
		sharedPortletController.retrieveRequestListDeviceLocationTreeXML(request,response, model);
		LOGGER.exit(this.getClass().getSimpleName(),METH_RETRIEVEDEVICELOCATIONTREEXML);
	}
	/**
	 * This method is used to bookmark the asset (star sign)
	 * @param favoriteAssetId 
	 * @param favoriteFlag 
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("updateUserFavoriteAsset")
	public void updateUserFavoriteAsset(
			@RequestParam("favoriteAssetId") String favoriteAssetId,
			@RequestParam("favoriteFlag") boolean favoriteFlag,
			ResourceRequest request, ResourceResponse response)
			{
		LOGGER.enter(this.getClass().getSimpleName(), METH_UPDATEUSERFAVOURITEASSET);
		try{
			sharedPortletController.updateUserFavoriteAsset(favoriteAssetId,favoriteFlag, request, response);
		}catch(Exception ex)
		{
			LOGGER.debug("Exception caught " + ex.getMessage());
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_UPDATEUSERFAVOURITEASSET);
	}
	
	/**
	 * Method to fill dto to write the CSV
	 * @param serviceReqDTO 
	 * @param formBean 
	 * @param request 
	 * @return FutureTask<String> 
	 * @throws LGSCheckedException 
	 */
	public  FutureTask<String> fillDTO(final ServiceRequestDTO serviceReqDTO, Object formBean, ActionRequest request)
	throws LGSCheckedException
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_FILLDTO);
		FutureTask<String> future=null;
		try{
		//This section is for the Manage Assets
		if (formBean instanceof ManageAssetForm) {			
			LOGGER.debug("formdata is of masform====> ");
			
			//Go for the new asset details, new consumables contact, new consumables address
			//new installed address in change asset page			
			if (((ManageAssetForm) formBean).getNewAssetInfo() != null) {				
				LOGGER.debug("It contains the new asset identifiers of change asset ");				
				BeanUtils.copyProperty(serviceReqDTO, "assetDetailForChange",
						((ManageAssetForm) formBean).getNewAssetInfo());
				
				if(((ManageAssetForm) formBean).getNewAssetInfo().getAssetContact().getFirstName()=="")
				{
					LOGGER.debug("Consumables contact on the form - user not changed");
					BeanUtils.copyProperty(serviceReqDTO, "consumablesContact", ((ManageAssetForm) formBean)
							.getOldAssetInfo().getAssetContact());
				}
				else 
				{
					LOGGER.debug("Consumables contact on the form - user changed data");
					BeanUtils.copyProperty(serviceReqDTO, "consumablesContact", ((ManageAssetForm) formBean)			
						.getNewAssetInfo().getAssetContact());
				}
				
				if(((ManageAssetForm) formBean).getNewAssetInfo().getConsumableAddress().getAddressLine1()=="")
				{
					LOGGER.debug("Consumables address on the form - user not changed");
					BeanUtils.copyProperty(serviceReqDTO, "consumablesAddress", ((ManageAssetForm) formBean)
							.getOldAssetInfo().getInstallAddress());
				}
				else 
				{
					LOGGER.debug("Consumables address on the form - user changed data");
					BeanUtils.copyProperty(serviceReqDTO, "consumablesAddress", ((ManageAssetForm) formBean)
						.getNewAssetInfo().getConsumableAddress());
				}
				
				if(((ManageAssetForm) formBean).getNewAssetInfo().getInstallAddress().getAddressLine1()==""){				
					
					LOGGER.debug("Installed address on the form - user not changed");
					BeanUtils.copyProperty(serviceReqDTO, "address", ((ManageAssetForm) formBean).getOldAssetInfo().getInstallAddress());
				}
				else 
				{
					LOGGER.debug("Installed address on the form-user changed data");
					BeanUtils.copyProperty(serviceReqDTO, "address", ((ManageAssetForm) formBean).getNewAssetInfo().getInstallAddress());			
				}
			}
			//Go for the old asset details in change asset page
			if (((ManageAssetForm) formBean).getOldAssetInfo() != null) 
			{
				LOGGER.debug("It contains the old asset identifiers of change asset");
				BeanUtils.copyProperty(serviceReqDTO, "assetDetail",
						((ManageAssetForm) formBean).getOldAssetInfo());
			}
			//Asset information on add asset page
			if(((ManageAssetForm) formBean).getAssetDetail()!=null)
			{	
				BeanUtils.copyProperty(serviceReqDTO, "assetDetail", ((ManageAssetForm) formBean).getAssetDetail());
				if(((ManageAssetForm) formBean).getAssetDetail().getInstallAddress()!=null){
					
					BeanUtils.copyProperty(serviceReqDTO, "address", ((ManageAssetForm) formBean).getAssetDetail().getInstallAddress());
				}
				
				if(((ManageAssetForm) formBean).getAssetDetail().getConsumableAddress()!=null)
				{
					BeanUtils.copyProperty(serviceReqDTO, "consumablesAddress", ((ManageAssetForm) formBean)
							.getAssetDetail().getConsumableAddress());
				}
				
				//This is for the pickup address in decommission asset page
				if(((ManageAssetForm) formBean).getAssetDetail().getPickupAddress()!=null)
				{
					LOGGER.debug("PickUp addr for Decommission ");
					BeanUtils.copyProperty(serviceReqDTO, "pickupAddress", ((ManageAssetForm) formBean).getAssetDetail().getPickupAddress());
				}
			}
			
			if(((ManageAssetForm) formBean).getInstallAssetFlag()!=null)
			{
				LOGGER.debug("Install asset flag");
				BeanUtils.copyProperty(serviceReqDTO, "installAssetFlag", ((ManageAssetForm) formBean).getInstallAssetFlag());
			}
			
			//Done--START
			if(((ManageAssetForm) formBean).getMoveType()!=null)
			{
				LOGGER.debug("MOVE TYPE NOT NULL");
				BeanUtils.copyProperty(serviceReqDTO, "moveType", ((ManageAssetForm) formBean).getMoveType());
			}
			
			//Done--END
			if(((ManageAssetForm) formBean).getDecommAssetFlag()!=null)
			{
				LOGGER.debug("Decommission asset flag");
				BeanUtils.copyProperty(serviceReqDTO, "decommAssetFlag", ((ManageAssetForm) formBean).getDecommAssetFlag());
			}
			
			//This is for the primary site contact in decommission asset page
			if(((ManageAssetForm) formBean).getPrimarySiteContact()!=null)
			{
				LOGGER.debug("Primary Site Contact for decommission ");
				BeanUtils.copyProperty(serviceReqDTO, "primarySiteContact", ((ManageAssetForm) formBean).getPrimarySiteContact());
			}
			
			//Addtnl dock details in decommission asset page
			if(((ManageAssetForm) formBean).getAddtnDockDetails()!=null)
			{
				LOGGER.debug("Addtional dock detail for decom ");
				BeanUtils.copyProperty(serviceReqDTO, "addtnDockDetails", ((ManageAssetForm) formBean).getAddtnDockDetails());
			}
			LOGGER.debug("Before csv call ");	
			LOGGER.info("Before csv call ");	
			try{				
				future=createFutureTask(serviceReqDTO, ChangeMgmtConstant.CSVTYPEMANAGEASSET);
			
			}catch(Exception ex)
			{
				logStackTrace(ex);
				throw new LGSProcessException("Error in creating CSV file");
			}
			LOGGER.debug("After csv call");
			LOGGER.info("After csv call");
		}

		//This is for the Manage Address
		if (formBean instanceof ManageAddressForm) {
			LOGGER.debug("formdata is of maddrform=======> ");
			
			if(((ManageAddressForm) formBean).getGenericAddress()!=null)
			{
				BeanUtils.copyProperty(serviceReqDTO, "address", ((ManageAddressForm) formBean).getGenericAddress());
			}
			try{
				future=createFutureTask(serviceReqDTO, ChangeMgmtConstant.CSVTYPEMANAGEADDRESS);
			}catch(Exception ex)
			{
				logStackTrace(ex);
				throw new LGSProcessException("Error in creating CSV file");
			}
			LOGGER.debug("After csv call");
		}
		//This is for the Manage Contact
		if (formBean instanceof ManageContactForm) {
			LOGGER.debug("formdata is of mcform============>");
			
			//This contains the new contact information
			if(((ManageContactForm) formBean).getAccountContact()!=null)
			{
				BeanUtils.copyProperty(serviceReqDTO, "contact", ((ManageContactForm) formBean).getAccountContact());
				BeanUtils.copyProperty(serviceReqDTO, "pageNameForContact", ((ManageContactForm) formBean).getPageName());
			}
			try{
				future=createFutureTask(serviceReqDTO, ChangeMgmtConstant.CSVTYPEMANAGECONTACT);
			}catch(Exception ex)
			{
				logStackTrace(ex);
				throw new LGSProcessException("Error in creating CSV file");//caught at the invoking pt
			}
			LOGGER.debug("After csv call");
		}
		
		}catch(IllegalAccessException ex)
		{
			logStackTrace(ex);
			throw new LGSCheckedException("Error while copying");//caught at the invoking pt
		}
		catch(InvocationTargetException ex)
		{
			logStackTrace(ex);
			throw new LGSCheckedException("Error while copying");//caught at the invoking pt
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_FILLDTO);
		return future;
	}
	
	/**
	 * @param serviceReqDTO 
	 * @param csvFileNm 
	 * @return FutureTask<String> 
	 */
	private FutureTask<String> createFutureTask(final ServiceRequestDTO serviceReqDTO, final String csvFileNm) {
		
		LOGGER.enter(this.getClass().getSimpleName(), METH_CREATEFUTURETASK);
		FutureTask<String> future=null;
		future = new FutureTask<String>(
                new Callable<String>()
                {
                    public String call()
                    {
                        return writeCSV(serviceReqDTO, csvFileNm);
                    }
                });
		executorService.execute(future);		
		if(future.isDone())
		{
			LOGGER.debug("Now i will close the executor service");
			LOGGER.info("Now i will close the executor service its done");
			executorService.shutdown();
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_CREATEFUTURETASK);
		return future;
	}

	/**
	 * Method to invoke the singleton instance and create CSV with form data
	 * @param serviceReqDTO 
	 * @param requestType 
	 * @return String 
	 */
	private String writeCSV(ServiceRequestDTO serviceReqDTO, final String requestType)	
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_WRITECSV);
		String responseText=null;		
		responseText=FileWriteUtility.getInstance().createCSVFile(serviceReqDTO, requestType.trim());
		LOGGER.exit(this.getClass().getSimpleName(), METH_WRITECSV);
		return responseText;
	}
	
	/**
	 * Method to rename the CSV with the SR Row Id 
	 * @param fileName 
	 * @param request 
	 * @param serviceReqRowId  
	 * @throws Exception 
	 */
	public void renameAttachment(String fileName, ActionRequest request, String serviceReqRowId) throws Exception
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_RENAMEATTACHMENT);
		try{
		LOGGER.debug("Inside uploadAttachment " + fileName);
		LOGGER.info("Inside uploadAttachment " + fileName);
		String fName = "";
		if(fileName.contains("\\")){
			fName = fileName.substring(fileName.lastIndexOf("\\")+1, fileName.length());
		}else if(fileName.contains("/")){
			fName=fileName.substring(fileName.lastIndexOf("/")+1,fileName.length());
		}else{
			fName = fileName;
		}

		LOGGER.debug("File name after extract ---->" + fName);
		LOGGER.info("File name after extract ---->" + fName);
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachmentDoc = new Attachment();
		
		attachmentDoc.setAttachmentName(fName);
		attachmentDoc.setVisibility(PortalSessionUtil.getUserType(request.getPortletSession()));
		LOGGER.debug("Attachment Visibility ---->" + attachmentDoc.getVisibility());
		LOGGER.info("Attachment Visibility ---->" + attachmentDoc.getVisibility());
		
		attachmentList.add(attachmentDoc);
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle
				(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		AttachmentContract attachmentContract = new AttachmentContract();
		attachmentContract.setSessionHandle(crmSessionHandle);
		attachmentContract.setIdentifier(serviceReqRowId);
		attachmentContract.setRequestType(ChangeMgmtConstant.ATTACHMENTREQTYPE);
		attachmentContract.setAttachments(attachmentList);
		
		LOGGER.debug("-------------------------- Calling attachment service ----------------------");
		LOGGER.info("-------------------------- Calling attachment service ----------------------");
		
		LOGGER.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContent(attachmentContract,LOGGER);
		LOGGER.info("end printing lex loggger");
		long timeBeforeCall=System.currentTimeMillis();
		attachmentService.uploadAttachments(attachmentContract);
		long timeAfterCall=System.currentTimeMillis();
		LOGGER.info("start printing lex LOGGER");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_AMIND,attachmentContract);
		LOGGER.info("end printing lex loggger");
		}catch(Exception ex)
		{
			LOGGER.debug("Exception "+ex.getMessage());
			LOGGER.info("Attachment upload Fail");
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_RENAMEATTACHMENT);
	}
	
	
	/* Country List Drop Down Population
	 * 
	 * */
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=countryDropDownPopulate")
	public void populateCountryList(RenderRequest resourceRequest,RenderResponse resourceResponse)throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), METH_POPULATECOUNTRYLIST);
		List<GeographyCountryContract> countries =null;
		PrintWriter out =null;
		String htmlContentCountryList=null;
		try{
			
		if(allCountryList.get(0)!=null)
		{
			LOGGER.debug("Allcountry list size is "+allCountryList.get(0).getCountryList().size());
			
			countries = allCountryList.get(0).getCountryList();
			LOGGER.debug("================country list has been populated===============");
			htmlContentCountryList = getXmlOutputGeneratorUtil(resourceRequest.getLocale()).convertCountriesToHTML(countries);
			LOGGER.debug("=============country list has been converted to xml============");
		}
		}catch(Exception ex)
		{	
			logStackTrace(ex);
			LOGGER.debug("There has been an error in fetching country details");
			GeographyListResult countryListResult = geographyService.getCountryDetails();
			countries = countryListResult.getCountryList();
			htmlContentCountryList = getXmlOutputGeneratorUtil(resourceRequest.getLocale()).convertCountriesToHTML(countries);
			
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
		LOGGER.exit(this.getClass().getSimpleName(), METH_POPULATECOUNTRYLIST);
	}
	/* Country List Drop Down Population
	 * 
	 * */
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @throws Exception 
	 */ 
	@RequestMapping(params = "action=stateDropDownPopulate")
	public void populateStateList(RenderRequest resourceRequest,RenderResponse resourceResponse)throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), METH_POPULATESTATELIST);
		String countryCodeVal=resourceRequest.getParameter(ChangeMgmtConstant.COUNTRYCODE);
		LOGGER.debug("countryCodeVal " + countryCodeVal);
		GeographyListResult stateListResult= new GeographyListResult();
		List<GeographyStateContract> stateList= null;
		String htmlContentStateList = null;
		PrintWriter out = null;
		try{
		if(GeographyServiceImpl.allStateMap!=null)
		{
			LOGGER.debug("Retrieving data from state map");			
			stateListResult.setStateList(GeographyServiceImpl.allStateMap.get(countryCodeVal.trim()));
		}
		else{
			LOGGER.debug("State map cache is empty, go for the db call");
		stateListResult = geographyService.getStateDetails(countryCodeVal);
		}
		
		stateList = stateListResult.getStateList();
		LOGGER.debug("================state list has been populated===============" + stateList.size());
		htmlContentStateList=getXmlOutputGeneratorUtil(resourceRequest.getLocale()).convertStateToHTML(stateList);
		LOGGER.debug("=============state list has been converted to xml============");
		}catch(Exception ex)
		{
			logStackTrace(ex);
			stateListResult = geographyService.getStateDetails(countryCodeVal);
			stateList = stateListResult.getStateList();
			htmlContentStateList=getXmlOutputGeneratorUtil(resourceRequest.getLocale()).convertStateToHTML(stateList);
		}
		finally{
			out = resourceResponse.getWriter();
			resourceResponse.setContentType(ChangeMgmtConstant.CONTENTTYPEHTML);
			out.print(countryCodeVal+htmlContentStateList);
			out.flush();
			out.close();	
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_POPULATESTATELIST);
	}
	
	
	/**
	 * @param model 
	 * @param resourceRequest 
	 * @param resourceResponse 
	 */
	@ResourceMapping(value="populateAccountList")
	public void retrieveAccountList(Model model,ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVEACCOUNTLIST);
		String isCatalogPage =null;
		
		/* Added for MPS Phase 2 Hardware Order*/
		String isHardwarePage =null;
		/*End Add*/
		PortletSession portletSession = resourceRequest.getPortletSession();
		SiebelAccountListContract siebelAccountListContract = ContractFactory
															  .getSiebelAccountListContract(resourceRequest);
		if(resourceRequest.getParameter("isCatalogPage")!=null){
			isCatalogPage = resourceRequest.getParameter("isCatalogPage");
			LOGGER.debug("isCatalogPage---------->"+isCatalogPage);
			if(isCatalogPage.equalsIgnoreCase("true")){
				siebelAccountListContract.setAgreementFlag(true);
			}
		}
		
		/* Added for MPS Phase 2 Hardware Order*/
		if(resourceRequest.getParameter("isHardwarePage")!=null){
			isHardwarePage = resourceRequest.getParameter("isHardwarePage");
			LOGGER.debug("isHardwarePage---------->"+isHardwarePage);
			if(isHardwarePage.equalsIgnoreCase("true")){
				siebelAccountListContract.setAgreementFlag(true);
				siebelAccountListContract.setHardwareFlag(true);
			}
		}
		/*End Add*/
		//Added for LBS
		String lbsflag=resourceRequest.getParameter("isLbs");
		LOGGER.debug("lbsflag---------->"+lbsflag);
		String mapReq=resourceRequest.getParameter("mapReq");
		if(StringUtils.isNotBlank(lbsflag) && "true".equalsIgnoreCase(lbsflag) || (StringUtils.isNotBlank(mapReq)&& "true".equalsIgnoreCase(mapReq)) ){
			siebelAccountListContract.setLbsFlag(true);
		}
		
		
		CrmSessionHandle crmSessionHandle = globalService
											.initCrmSessionHandle(PortalSessionUtil
											.getSiebelCrmSessionHandle(resourceRequest));
		siebelAccountListContract.setSessionHandle(crmSessionHandle);
		LOGGER.debug("-------------retrieveSiebelAccountList started---------");
		
		LOGGER.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContent(siebelAccountListContract, LOGGER);
		LOGGER.info("end printing lex loggger");
		
		
		SiebelAccountListResult siebelAccountListResult=null;
		try{
			/*
			 * This section will execute if
			 * request is from cAtalog page
			 * */
		if((isCatalogPage!=null && isCatalogPage.equalsIgnoreCase("true")) || (isHardwarePage!=null && isHardwarePage.equalsIgnoreCase("true"))){
			long timeBeforeCall=System.currentTimeMillis();
			
			siebelAccountListResult= globalService.retrieveCatalogAgreementList(siebelAccountListContract);
			long timeAfterCall=System.currentTimeMillis();
			/* End commenting */
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_RETRIEVECATALOGAGREEMENTLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,siebelAccountListContract);
			
		}
		else{	
			long timeBeforeCall=System.currentTimeMillis();
			siebelAccountListResult= serviceRequestService.retrieveSiebelAccountList(siebelAccountListContract);
			List<Account> accountList = siebelAccountListResult.getAccountList();
			portletSession.setAttribute("IS_ALLIANCE_PARTNER", false, PortletSession.APPLICATION_SCOPE);
			for (Account account : accountList) {
				LOGGER.debug("alliance flag 1 --------->>>>>>> "+account.isAlliancePartner());
				if(account.isAlliancePartner()){
					LOGGER.debug("account.isAlliancePartner() in if "+account.isAlliancePartner());
					portletSession.setAttribute("IS_ALLIANCE_PARTNER", true, PortletSession.APPLICATION_SCOPE);
				}				
			}
			
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_RETRIEVESIEBELACCOUNTLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,siebelAccountListContract);
			
		}
		}catch(Exception e){
			LOGGER.info("Error occured while retrieving Account List when request from isCatalogPage="+isCatalogPage);
			e.getMessage();
		}
		finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		//Added By MPS Offshore Team for adding Vendor Flag
		List<String> userRoleList = PortalSessionUtil.getUserRoles(portletSession);		
		boolean isVendorFlag = false;
		if(userRoleList != null && !userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			isVendorFlag = true;
		}
		
		List<Account> accountList = siebelAccountListResult.getAccountList();
		LOGGER.debug("no of account "+accountList.size());
		LOGGER.debug("-------------retrieveSiebelAccountList Ended---------");
		LOGGER.debug("-------------Coverting Account List to XML---------");
		String content = getXmlOutputGeneratorUtil(resourceRequest.getLocale())
		.convertAccountListToXML(accountList,
				accountList.size(), 0,isCatalogPage,isHardwarePage,isVendorFlag);
		LOGGER.debug("-------------Coverting Account List to XML ENDS---------");
		globalService.releaseSessionHandle(crmSessionHandle);
		try{
		PrintWriter out = resourceResponse.getWriter();
		resourceResponse.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		}catch(IOException e){
			LOGGER.debug("Exception occured while writing the content in retrieveAccountList");
			e.getMessage();
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVEACCOUNTLIST);
	}
	
	/**
	 * @param accountListMapSingle 
	 * @return SiebelAccountListResult 
	 */
	public SiebelAccountListResult returnSingleAccount(List<Account> accountListMapSingle) {
		SiebelAccountListResult result = new SiebelAccountListResult();
		result.setAccountList(accountListMapSingle);
		return result;
	}
	
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 */
	@ResourceMapping(value="setCurrentAccountValuesToSession")
	public void setCurrentAccount(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		LOGGER.enter(this.getClass().getSimpleName(), METH_SETCURRENTACCOUNT);
		Enumeration<String> paramNames=resourceRequest.getParameterNames();
		Map<String,String> accountDetailsValues=new HashMap<String,String>();
		while(paramNames.hasMoreElements()){
			String paramName=paramNames.nextElement();
			LOGGER.debug("session:"+paramName+ " " +resourceRequest.getParameter(paramName) );
			accountDetailsValues.put(paramName, resourceRequest.getParameter(paramName));
		}
		PortletSession portletSession = resourceRequest.getPortletSession();
		
		
		
		/* 03-29-2013
		 * This is for PARTNER PORTAL changes for PERFORMANCE ISSUE
		 * Amind will fetch entitlement flags and expedite flags on account selection
		 * for partner portal customer requests. So again we have to call retrieve Siebel Agreement list.
		 * */
		//This portion is for parnter portal customer requests
		String currURL=resourceResponse.createRenderURL().toString();
		LOGGER.debug("currURL==="+currURL);
		boolean isPartnerRequest=currURL.indexOf(ChangeMgmtConstant.ISPARTNERPORTAL)!=-1;
		LOGGER.debug("isPartnerRequest==="+isPartnerRequest);
		if(isPartnerRequest){
			try{
				SiebelAccountListContract siebelAccountListContract = ContractFactory.getSiebelAccountListContract(resourceRequest);
				
				
				//These values are to be sent to Amind for Second call
					siebelAccountListContract.setMdmId(accountDetailsValues.get("accountId"));
					siebelAccountListContract.setMdmLevel("Siebel");
					siebelAccountListContract.setVendorFlag(false);
				//ENDS These values are to be sent to Amind for Second call
					
				CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(resourceRequest));
				siebelAccountListContract.setSessionHandle(crmSessionHandle);
				
				LOGGER.info("start printing lex LOGGER This call is for Partner portal getting Entitlement flags and catalog expedite flag");
				ObjectDebugUtil.printObjectContent(siebelAccountListContract, LOGGER);
				LOGGER.info("end printing lex loggger");
				
				
				long timeBeforeCall=System.currentTimeMillis();
				SiebelAccountListResult siebelAccountListResult = globalService.retrieveSiebelAgreementList(siebelAccountListContract);
				long timeAfterCall=System.currentTimeMillis();
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_RETRIEVECATALOGAGREEMENTLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,siebelAccountListContract);
								
				List<Account> siebelAccountList=siebelAccountListResult.getAccountList();
				LOGGER.debug("siebel account list size="+siebelAccountList.size());
				//As there would be one Account
				String servicePartQuantity = "";
				String suppliesPartQuantity = "";
				if(siebelAccountList.get(0)!=null){
					accountDetailsValues.put(ChangeMgmtConstant.CATALOG_ENTITLEMENT_FLAG,String.valueOf(siebelAccountList.get(0).isCatalogEntitlementFlag()));
					accountDetailsValues.put(ChangeMgmtConstant.ASSET_ENTITLEMENT_FLAG,String.valueOf(siebelAccountList.get(0).isAssetEntitlementFlag()));
					accountDetailsValues.put(ChangeMgmtConstant.REQUESTEXPEDITE,String.valueOf(siebelAccountList.get(0).isCatalogExpediteFlag()));
					LOGGER.debug("Catalog entitlement flag "+siebelAccountList.get(0).isCatalogEntitlementFlag());
					LOGGER.debug("Asset entitlement flag "+siebelAccountList.get(0).isAssetEntitlementFlag());
					LOGGER.debug("Service qty is === "+siebelAccountList.get(0).getQuantityServices());
					LOGGER.debug("Supplies qty is === "+siebelAccountList.get(0).getQuantitySupplies());
					if(null != siebelAccountList.get(0).getQuantityServices()){
						servicePartQuantity = siebelAccountList.get(0).getQuantityServices();
					}				
					if(null != siebelAccountList.get(0).getQuantitySupplies()){
						suppliesPartQuantity = siebelAccountList.get(0).getQuantitySupplies();
					}
				
				portletSession.setAttribute("servicePartQuantity", servicePartQuantity ,PortletSession.APPLICATION_SCOPE);
				portletSession.setAttribute("suppliesPartQuantity", suppliesPartQuantity ,PortletSession.APPLICATION_SCOPE);
				LOGGER.debug("Partner Portal servicePartQuantity set in session =================== "+servicePartQuantity);
				LOGGER.debug("Partner Portal suppliesPartQuantity set in session =================== "+suppliesPartQuantity);
					
					
				}else{
					LOGGER.debug("Siebel accout list is nulll ...");
					
					accountDetailsValues.put(ChangeMgmtConstant.CATALOG_ENTITLEMENT_FLAG,"false");
					accountDetailsValues.put(ChangeMgmtConstant.ASSET_ENTITLEMENT_FLAG,"false");
					accountDetailsValues.put(ChangeMgmtConstant.REQUESTEXPEDITE,"false");
				}	
				
			}catch(Exception e){
				LOGGER.debug("Exception occured while retrieving account flags for partner portal");
				accountDetailsValues.put(ChangeMgmtConstant.CATALOG_ENTITLEMENT_FLAG,"false");
				accountDetailsValues.put(ChangeMgmtConstant.ASSET_ENTITLEMENT_FLAG,"false");
				accountDetailsValues.put(ChangeMgmtConstant.REQUESTEXPEDITE,"false");
				e.getMessage();
			}
		}
		
		//This is for PARTNER PORTAL changes for PERFORMANCE ISSUE ENDS..
		portletSession.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accountDetailsValues ,PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("request values"+accountDetailsValues.toString());
		
		try{
			PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setContentType("text/plain");
			out.print("success");
			out.flush();
			out.close();
		}catch(IOException ie){
			LOGGER.debug("IO Exception message" + ie.getMessage());
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_SETCURRENTACCOUNT);
	}
	
	
	/**
	 * Retrieves a map of localized LOV from database for the Area and Subarea drop downs.
	 * It will be used in list page to fill selection filter, and to localize LOV string in Area and Subarea drop downs.
	 * @param lovType 
	 * @param locale 
	 * @return Map<String, String> 
	 * @throws LGSDBException 
	 * @throws Exception 
	 */
	public Map<String, String> retrieveLocalizedLOVMap(String lovType, Locale locale) throws LGSDBException, Exception{
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVELOCALIZEDLOVMAP);
		LOGGER.debug("Inside CommonController >> retrieveLocalizedLOVMap...");
		Map<String, String> lovMap = new LinkedHashMap<String, String>();
		LocalizedSiebelLOVListResult result = new LocalizedSiebelLOVListResult();
		
		LocalizedSiebelLOVListContract contract = ContractFactory.createLocalizedSiebelLOVListContract(lovType, null, locale);
		LOGGER.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		LOGGER.info("end printing lex loggger");
		try {
			Long startTime = System.currentTimeMillis();
			
			result = serviceRequestLocaleService.retrieveLocalizedSiebelLOVList(contract);
			LOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Siebel LOV LIst ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
		} catch (InfrastructureException ex) {
			result.setLocalizedSiebelLOVList(new ArrayList<ListOfValues>(0));
			LOGGER.error("Exception occured while retrieving Localized Siebel LOV List", ex);
			throw new LGSDBException("hiberanateexception",ex);
		}
		List<ListOfValues> valuesList = result.getLocalizedSiebelLOVList();//new ArrayList<ListOfValues>(0);
		
		if(valuesList.isEmpty()){
			LOGGER.error(" LOV  "+lovType+" not found in Portal Database OR, \n No value of LOV "
					+lovType+" found for the Local "+locale.getLanguage()+"\n Returning empty list");
			valuesList = new ArrayList<ListOfValues>(0); 
			
		}
		
		Collections.sort(valuesList, new LOVComparator(locale));
		for(ListOfValues lov : valuesList){
			lovMap.put(lov.getValue(), lov.getName());
		}
		LOGGER.debug("Retrieved lovMap :: "+ lovMap);
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVELOCALIZEDLOVMAP);
		return lovMap;		
	}
	

	/**.
	 * This method renders the popup page for printing the 
	 * change management confirmation page.
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printConfirmationPage")
	public String printConfirmationPage(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), METH_PRINTCONFIRMATIONPAGE);
		LOGGER.exit(this.getClass().getSimpleName(), METH_PRINTCONFIRMATIONPAGE);
		return "common/printConfirmationPage";
	}
	
	/**.
	 * This method renders the popup page to email the 
	 * change management confirmation page.
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=emailConfirmationPage")
	public String emailConfirmationPage(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), METH_EMAILCONFIRMATIONPAGE);
		LOGGER.exit(this.getClass().getSimpleName(), METH_EMAILCONFIRMATIONPAGE);
		return "common/emailConfirmationPage";
	}
	
	/**
	 * @param accDetailsMap 
	 */
	public void printAccountDetail(Map<String, String> accDetailsMap)
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_PRINTACCOUNTDETAIL);
		LOGGER.debug("Account id ::::   "+accDetailsMap.get("accountId"));
		LOGGER.debug("Account Name ::::   "+accDetailsMap.get("accountName"));
		LOGGER.debug("Account Organization ::::   "+accDetailsMap.get("accountOrganization"));
		LOGGER.debug("Agreement Id::::   "+accDetailsMap.get("agreementId"));
		LOGGER.debug("Agreement Name ::::   "+accDetailsMap.get("agreementName"));
		LOGGER.debug("Country ::::   "+accDetailsMap.get("country"));
		LOGGER.exit(this.getClass().getSimpleName(), METH_PRINTACCOUNTDETAIL);
	}
	
	
	/**. Verifies create SR flag
	 * 
	 * @param request 
	 * @return AccountAccess 
	 * @throws Exception  
	 * @author Sandeep P 
	 */
	public AccountAccess verifyRequestAccessFlag4Acct(RenderRequest request) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(),METH_VERIFYREQUESTACCESSFLAG4ACCT);
		PortletSession session = request.getPortletSession();
		AccountAccess acctRequestAccess = (AccountAccess) session.getAttribute("ACCOUNT_REQUEST_ACCESS_FLAGS",PortletSession.APPLICATION_SCOPE);
		
		
		String createSuppliesReqFlag = "false";
		String createServiceReqFlag = "false";
		String createAcctMgmtReqFlag = "false";
		String showSuppliesReqFlag = "false";
		String showServiceReqFlag = "false";
		String showAcctMgmtReqFlag = "false";
		
		/* Added for Hardware Request in MPS2.1 */
		String showHardwareReqFlag = "false";
		String createHardwareReqFlag = "false";
		String createMapSRFlag = "false";
		String fleetManagerFlag = "false";
			
		if (acctRequestAccess == null){
	    	
	    	acctRequestAccess = new AccountAccess();
			
			SiebelAccountListContract siebelAccountListContract = ContractFactory.getSiebelAccountListContract(request);
			ObjectDebugUtil.printObjectContent(siebelAccountListContract, LOGGER);
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			try {
				
				siebelAccountListContract.setSessionHandle(crmSessionHandle);
				LOGGER.debug("-------------retrieveSiebelAccountList started---------");
				
				//Performance Testing
				Long startTime = System.currentTimeMillis();
				SiebelAccountListResult siebelAccountListResult = serviceRequestService.retrieveSiebelAccountList(siebelAccountListContract);
				LOGGER.logTime("Time taken to retrieveSiebelAccountList----->>"+(System.currentTimeMillis()-startTime)/1000.0+"SECS");
				
				List<Account> accountList = siebelAccountListResult.getAccountList();
				LOGGER.debug("-------------retrieveSiebelAccountList ended---------accountList Size:"+accountList.size());
				for (Account account : accountList) {
					LOGGER.debug("ACCOUNT FLAGS--::SERVICE REQUEST="+account.getCreateServiceRequest()+
							"::SUPPLIES REQUEST="+account.getUserConsumables()+"::ACCOUNT MANAGEMENT="+
							account.getAccountMgmtRequest()+ "::HARDWARE"+account.isHardwareRequestFlag());
					//Verify for Create Service Request
					if (account.getCreateServiceRequest()!=null && account.getCreateServiceRequest().equalsIgnoreCase("update")) {
						createServiceReqFlag = "true";
						showServiceReqFlag = "true";
					}else if (account.getCreateServiceRequest()!=null && account.getCreateServiceRequest().equalsIgnoreCase("show") ) {
						showServiceReqFlag = "true";
					}
					//Verify for Create supplies Request
					if (account.getUserConsumables()!=null && account.getUserConsumables().equalsIgnoreCase("update")) {
						createSuppliesReqFlag = "true";
						showSuppliesReqFlag = "true";
					}else if (account.getUserConsumables()!=null && account.getUserConsumables().equalsIgnoreCase("show")) {
						showSuppliesReqFlag = "true";
					}
					
					//Verify for Account Management Request
					LOGGER.debug("account.getAccountMgmtRequest()="+account.getAccountMgmtRequest());
					if (account.getAccountMgmtRequest()!=null && account.getAccountMgmtRequest().equalsIgnoreCase("update")) {
						createAcctMgmtReqFlag = "true";
						showAcctMgmtReqFlag = "true";
					}else if (account.getAccountMgmtRequest()!=null && account.getAccountMgmtRequest().equalsIgnoreCase("show")) {
						showAcctMgmtReqFlag = "true";
					} 
					
					//Verify for Hardware Request
					if (account.isHardwareRequestFlag()!=null && account.isHardwareRequestFlag().equalsIgnoreCase("update")) {
						createHardwareReqFlag = "true";
						showHardwareReqFlag = "true";
					}else if (account.isHardwareRequestFlag()!=null && account.isHardwareRequestFlag().equalsIgnoreCase("show")) {
						showHardwareReqFlag = "true";
					} 
					
					//Ends for JAN RELEASE catalog entitlement 
					
					//LBS Flags
					if (account.isLbsDisplayWeb()) {
						fleetManagerFlag = "true";
						createMapSRFlag = "true";
					}
				}
				acctRequestAccess.setCreateSuppliesReqFlag(createSuppliesReqFlag);
				acctRequestAccess.setCreateServiceReqFlag(createServiceReqFlag);
				acctRequestAccess.setCreateAccountMgmtReqFlag(createAcctMgmtReqFlag);
				
				acctRequestAccess.setShowSuppliesReqFlag(showSuppliesReqFlag);
				acctRequestAccess.setShowServiceReqFlag(showServiceReqFlag);
				acctRequestAccess.setShowAccountMgmtReqFlag(showAcctMgmtReqFlag);
				
				//Added for Hardware Request on MPS2.1
				acctRequestAccess.setCreateHardwareReqFlag(createHardwareReqFlag);
				acctRequestAccess.setShowHardwareReqFlag(showHardwareReqFlag);
				acctRequestAccess.setFleetManagerFlag(fleetManagerFlag);
				acctRequestAccess.setCreateMapSRFlag(createMapSRFlag);
				
				
				LOGGER.debug("MPS*** Verify SHOW Account Level Flags Starts"); 
				LOGGER.debug(
						"	SHOW Supplies Req:"+acctRequestAccess.getShowSuppliesReqFlag()+
						"	SHOW Service Req:"+acctRequestAccess.getCreateServiceReqFlag()+
						"	SHOW Account Management Req:"+acctRequestAccess.getCreateAccountMgmtReqFlag());
				LOGGER.debug("NPS*** Verify Create Account Level Flags Ends");
				
				LOGGER.debug("NPS*** Verify Create Account Level Flags Starts"); 
				LOGGER.debug(
						"	CREATE Supplies Req:"+acctRequestAccess.getCreateSuppliesReqFlag()+
						"	CREATE Service Req:"+acctRequestAccess.getCreateServiceReqFlag()+
						"	CREATE Account Management Req:"+acctRequestAccess.getCreateAccountMgmtReqFlag()+
						"	CREATE Map SR Flag :"+acctRequestAccess.getCreateMapSRFlag()+
						"	Fleet Manager Flag :"+acctRequestAccess.getFleetManagerFlag());
				LOGGER.debug("NPS*** Verify Create Account Level Flags Ends");
				
				session.setAttribute("ACCOUNT_REQUEST_ACCESS_FLAGS",acctRequestAccess,PortletSession.APPLICATION_SCOPE);
			} finally {
				globalService.releaseSessionHandle(crmSessionHandle);
			}
	    }// end of if
	    LOGGER.exit(this.getClass().getSimpleName(),METH_VERIFYREQUESTACCESSFLAG4ACCT);
		return acctRequestAccess;
	}

	
	/**.
	 * This method retrieves the assess flag for the request types to be displayed.
	 * @param request 
	 * @param response 
	 */
	@SuppressWarnings("unchecked")
	public void retrieveReqHistoryAccess4Portal(RenderRequest request,	RenderResponse response) {
		LOGGER.enter(this.getClass().getSimpleName(),METH_RETRIEVEREQHISTORYACCESS4PORTAL);
	try{
		PortletSession portletSession = request.getPortletSession();
		
		Map<String, String> requestAccessMap= (Map<String, String>)portletSession.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("requestAccessMap--->"+requestAccessMap);
		if (requestAccessMap == null || requestAccessMap.isEmpty()){
			requestAccessMap = new HashMap<String, String>();
		}
		List<String> userRoleList = PortalSessionUtil.getUserRoles(portletSession);
		LOGGER.debug("CommonController ---> userRoleList-->"+userRoleList);
		
		String userType = PortalSessionUtil.getUserType(request.getPortletSession());
		AccountAccess accountReqAccess = null;
		String showServiceReq ="";
		String showSuppliesReq ="";
		String showAcctMgmtReq ="";
		String showHardwareReq ="";
		
		LOGGER.debug("CommonController ---> userType-->"+userType);
		//This logic has been put temporarylly, need to verify later based on access role
		if(userType != null && (userType.equalsIgnoreCase("Employee"))){
			LOGGER.info("inside check for employee");
			accountReqAccess = (AccountAccess)verifyRequestAccessFlag4Acct(request);
			
		}else if(userType != null && userType.equalsIgnoreCase("Customer")){
		  //This logic is for customer
			LOGGER.debug("*****User is a Customer*****");
			
			showServiceReq = getEntitlementStatus( request, SHOW_SERVICE_REQUEST);
			showSuppliesReq = getEntitlementStatus( request, SHOW_SUPPLIES_REQUEST);
			showAcctMgmtReq = getEntitlementStatus( request, SHOW_CHANGE_MGMT_REQUEST);
			showHardwareReq = getEntitlementStatus( request, SHOW_HARDWARE_REQUEST); // Added for Hardware Request in MPS2.1
			LOGGER.debug("showServiceReq--->"+showServiceReq);
			LOGGER.debug("showSuppliesReq--->"+showSuppliesReq);
			LOGGER.debug("showAcctMgmtReq--->"+showAcctMgmtReq);
			LOGGER.debug("showHardwareReq--->"+showHardwareReq);

			
			
			if ((showServiceReq != null && showServiceReq.equalsIgnoreCase("true")) ||
				(showSuppliesReq != null && showSuppliesReq.equalsIgnoreCase("true")) || 
				(showAcctMgmtReq != null && showAcctMgmtReq.equalsIgnoreCase("true")) ||
				(showHardwareReq != null && showHardwareReq.equalsIgnoreCase("true"))){
					LOGGER.debug("***Before calling account access*****");
					accountReqAccess = (AccountAccess)verifyRequestAccessFlag4Acct(request);
				
			}
		
		}
		
		if (accountReqAccess != null){
			LOGGER.debug("***Setting the Request History flags*****");

			LOGGER.debug("Show Service Req Flag---->"+accountReqAccess.getShowServiceReqFlag());
			LOGGER.debug("Show Supply Req Flag---->"+accountReqAccess.getShowSuppliesReqFlag());
			LOGGER.debug("Show Acc mngmt Flag---->"+accountReqAccess.getShowAccountMgmtReqFlag());
			LOGGER.debug("Show Hardware Req Flag---->"+accountReqAccess.getShowHardwareReqFlag());// Added for Hardware Request in MPS2.1
			if(userType != null && (userType.equalsIgnoreCase("Employee"))){
				requestAccessMap.put(SHOW_SERVICE_REQUEST, accountReqAccess.getShowServiceReqFlag());
				requestAccessMap.put(SHOW_SUPPLIES_REQUEST, accountReqAccess.getShowSuppliesReqFlag());
				requestAccessMap.put(SHOW_CHANGE_MGMT_REQUEST, accountReqAccess.getShowAccountMgmtReqFlag());
				requestAccessMap.put(SHOW_HARDWARE_REQUEST, accountReqAccess.getShowHardwareReqFlag());// Added for Hardware Request in MPS2.1
				
			}else if(userType != null && userType.equalsIgnoreCase("Customer")){
				if((showServiceReq != null && showServiceReq.equalsIgnoreCase("true")) && accountReqAccess.getShowServiceReqFlag().equalsIgnoreCase("true")){
					requestAccessMap.put(SHOW_SERVICE_REQUEST, "true");
				}else{
					requestAccessMap.put(SHOW_SERVICE_REQUEST, "false");
				}
				
				if((showSuppliesReq != null && showSuppliesReq.equalsIgnoreCase("true")) && accountReqAccess.getShowSuppliesReqFlag().equalsIgnoreCase("true")){
					requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "true");
				}else{
					requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "false");
				}
				
				if((showAcctMgmtReq != null && showAcctMgmtReq.equalsIgnoreCase("true")) && accountReqAccess.getShowAccountMgmtReqFlag().equalsIgnoreCase("true")){
					requestAccessMap.put(SHOW_CHANGE_MGMT_REQUEST, "true");
				}else{
					requestAccessMap.put(SHOW_CHANGE_MGMT_REQUEST, "false");
				}
				
				if((showHardwareReq != null && showHardwareReq.equalsIgnoreCase("true")) && accountReqAccess.getShowHardwareReqFlag().equalsIgnoreCase("true")){
					requestAccessMap.put(SHOW_HARDWARE_REQUEST, "true");
				}else{
					requestAccessMap.put(SHOW_HARDWARE_REQUEST, "false");
				}
			}
		}else{
			LOGGER.debug("***Customer does not have Request History access*****");
			requestAccessMap.put(SHOW_SERVICE_REQUEST, "false");
			requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "false");
			requestAccessMap.put(SHOW_CHANGE_MGMT_REQUEST, "false");
			requestAccessMap.put(SHOW_HARDWARE_REQUEST, "false");// Added for Hardware Request in MPS2.1
			
		}
		if(requestAccessMap!=null){
			
		
		Iterator<String> itr = requestAccessMap.keySet().iterator();
		int count = 0;
		while(itr.hasNext()){
			String key = itr.next();
			if((key.equals(SHOW_SERVICE_REQUEST) || key.equals(SHOW_SUPPLIES_REQUEST) || key.equals(SHOW_CHANGE_MGMT_REQUEST) || key.equals(SHOW_HARDWARE_REQUEST))
				&& requestAccessMap.get(key).equalsIgnoreCase("true")){
					count ++;
			}
			
		}
		if(count>=1){
			requestAccessMap.put(SHOW_ALL_REQUEST, "true");
		}else{
			requestAccessMap.put(SHOW_ALL_REQUEST, "false");
		}
		LOGGER.debug("requestAccessMap is -->" + requestAccessMap);
		portletSession.setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, requestAccessMap,
				PortletSession.APPLICATION_SCOPE);
		
		}
		}catch(Exception ex)
		{
			LOGGER.debug("Exception "+ex.getMessage());
		}
		LOGGER.exit(this.getClass().getSimpleName(),METH_RETRIEVEREQHISTORYACCESS4PORTAL);
	}
	/**
	 * @return ServiceRequestLocale 
	 */
	public ServiceRequestLocale getServiceRequestLocale() {
    	if(serviceRequestLocale == null) {
    		serviceRequestLocale = new ServiceRequestLocaleImpl();
    	}
		return serviceRequestLocale;
	}
	
	/**
	 * This method retrieves the localized SR type from portal DB
	 * @param siebelValue 
	 * @param LOVType 
	 * @param locale 
	 * @return String 
	 */
	public String getLocalizeSiebelValue(String siebelValue, String LOVType, Locale locale){
		LOGGER.enter(this.getClass().getSimpleName(),GET_SIEBEL_LOCALISED_VALUE);
		LOGGER.debug("getLocalizeSiebelValue.LOVType-->["+LOVType+"] siebelValue-->["+siebelValue+"]"+" local-->"+locale.getLanguage());
		LocalizedSiebelValueContract contract = ContractFactory.createLocalizedSiebelValueContract(LOVType,siebelValue,locale);
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		ServiceRequestLocale srlocale = getServiceRequestLocale();
		try {
			result = srlocale.retrieveLocalizedSiebelValue(contract);
		} catch (InfrastructureException ex) {
			
			throw new LGSRuntimeException("Exception while retrieving localized SR_TYPE LOV list from DB",ex);
		}
		ListOfValues localLovValue = result.getLovValue();
		
		if(localLovValue == null || localLovValue.getName()== null || localLovValue.getName().isEmpty()){
			return siebelValue;
		}
		LOGGER.exit(this.getClass().getSimpleName(),GET_SIEBEL_LOCALISED_VALUE);
		return localLovValue.getName();
	}
	/**
	 * This method saves the filtervalues of grid,date range, option selected to session
	 * Added for JAN release 
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("saveGridParamsForCM")
	public void saveGridParamsForCM(ResourceRequest request,ResourceResponse response){
		
		String queryParams = request.getParameter("queryParams");
		String gridId = request.getParameter("gridId");
		response.setContentType("text/plain");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter()," + e.getMessage());
			return;
		}
		if (StringUtils.isEmpty(queryParams)) {
			out.write("{error:\"queryParams required\"}");
			return;
		}
		if (StringUtils.isEmpty(gridId)) {
			out.write("{error:\"gridId required\"}");
			return;
		}
		String sessionGridKey = GRID_QUERY_PARAMS.concat(gridId);
		PortletSession session = request.getPortletSession();
		if("null".equalsIgnoreCase(queryParams)){
			session.removeAttribute(sessionGridKey);
		}
		else{
			session.setAttribute(sessionGridKey, queryParams);
		}	
			
		out.write("success");
		out.flush();
		out.close();
		LOGGER.info("Grid query params of sessionKey[" + sessionGridKey
				+ "] was successfully saved");
		
		
	}
	/**
	 * This method retrieves the filtervalues of grid,date range, option selected to session
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("retrieveGridParamsForCM")
	public void retireveGridParamsForCM(ResourceRequest request,ResourceResponse response){
			LOGGER.debug("enter retireveGridParamsForCM");
		String gridId = request.getParameter("gridId");
		String sessionGridKey = GRID_QUERY_PARAMS.concat(gridId);
		String queryParams=null;
		if(request.getPortletSession().getAttribute(sessionGridKey)!=null){
			queryParams = (String)request.getPortletSession().getAttribute(sessionGridKey);
		}
		StringBuilder json = new StringBuilder();
		if(queryParams==null) {
			json.append("{\"status\":\"blank\"");
		}else{
			json.append("{\"status\":\"available\",");
			json.append(queryParams);
		}
		json.append("}");
		try {
			final PrintWriter out = response.getWriter();
			response.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			response.setProperty("Expires", "max-age=0,no-cache,no-store");
			LOGGER.debug(json.toString());
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter()," + e.getMessage());
		}
		
		LOGGER.debug("exit retireveGridParamsForCM");
	}

	//Added for Defect 3924 -Jan Release------START
	/**
	 * @param device 
	 * @param locale 
	 */
	public void assembleDevice(Asset device, Locale locale) {
		// construct supportURL and downloadsURL
		String urlHost = PropertiesMessageUtil.getPropertyMessage(
				"com.lexmark.services.resources.hostConfig",
				"markDirectHost", Locale.US);
		String URLPart2 = "&lang=" + locale.getLanguage() + "&country=" +
                locale.getLanguage() + "_" + locale.getCountry() + "&partnumber=" +
                device.getProductTLI();
		device.setSupportUrl(urlHost + "support" + URLPart2);
		device.setDownloadsUrl(urlHost + "downloads" + URLPart2);
		
		// populate control panel URL
		if (!StringUtil.isStringEmpty(device.getHostName())) {
			device.setControlPanelURL("http://" + device.getHostName());
		} else if (!StringUtil.isStringEmpty(device.getIpAddress())) {
			device.setControlPanelURL("http://" + device.getIpAddress());
		}
	//Added for Defect 3924 -Jan Release------END
	}
	// added
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @param assetId 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@ResourceMapping(value="assetRequestHistory")
	public String getAssetRequestHistory(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse,@RequestParam("assetId") String assetId,Model model)throws Exception{
		LOGGER.debug("-------------- Entering to getAssetRequestHistory controller method -------------------");		
		model.addAttribute("assetId", assetId);
		resourceResponse.setContentType("UTF-8");
		LOGGER.debug("assetRequestHistory Sl no :"+resourceRequest.getParameter("slno"));
		LOGGER.debug("-------------- Exiting from  getAssetRequestHistory controller method -------------------");
		return "orderSuppliesAssetOrder/recentOrderHistory";
	}
	
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @param assetId 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="retrieveOrderHistory")
	public void getRetrieveOrderHistory(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse,@RequestParam("assetId") String assetId,Model model)throws Exception{
		LOGGER.info("-------------- Entering in getRetrieveOrdderHistory controller method -------------------");
		AssetContract contract = ContractFactory.getAssetOrderDetailContract();
		contract.setAssetId(assetId);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = null;
		currentDate = dateFormat.format(new Date());		
		Date currentDateObj = DateUtil.getStringToLocalizedDate(currentDate, false, resourceRequest.getLocale());
		DateFormat dateFormatObj = new SimpleDateFormat("MM/dd/yyyy");
		String current_Date = dateFormatObj.format(currentDateObj);
		Date final_Date  = DATE_FORMAT.parse(current_Date);
		contract.setCurrentDate(final_Date);
		LOGGER.info("Final date :"+current_Date);
		AssetResult result = orderSuppliesAssetService.retrieveDeviceDetail(contract);
		LOGGER.info("In the controller----" + assetId);
		AssetDetailPageForm assetDetailPageForm = new AssetDetailPageForm();
		Asset asset = new Asset();
		if(result.getAsset().getModelNumber()!=null){	
			LOGGER.info(" MODEL NO: "+result.getAsset().getModelNumber());
			asset.setModelNumber(result.getAsset().getModelNumber());
			asset.setSerialNumber(result.getAsset().getSerialNumber());
			asset.setAssetTag(result.getAsset().getAssetTag());			
			asset.setDeviceTag(result.getAsset().getDeviceTag());
			asset.setIpAddress(result.getAsset().getIpAddress());			
			assetDetailPageForm.setAsset(asset);
			LOGGER.info("asset ipaddress and serial no :"+asset.getIpAddress()+":"+ asset.getSerialNumber());
			List<Part>  assetPartList = result.getAsset().getLastOrderPartList();						
			LOGGER.debug("assetPartList size"+assetPartList.size());
			assetDetailPageForm.setAssetPartList(assetPartList);
		}
		String content = getXmlOutputGenerator(resourceRequest.getLocale()).convertOrderHistoryPopup(assetDetailPageForm,
				assetDetailPageForm.getAssetPartList().size(),0,resourceRequest.getLocale());
		PrintWriter out = resourceResponse.getWriter();
		resourceResponse.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		
	}
	
	//Common Method for Services Portal to fetch Request History on Asset for BRD13-10-02 (CI)
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 */ 
	public void retrieveHistoryListByAssetId(ResourceRequest request, ResourceResponse response, Model model)
	{	
		LOGGER.enter(this.getClass().getSimpleName(),"retrieveHistoryListByAssetId");
		final String assetId = request.getParameter(ChangeMgmtConstant.ASSET_ROWID);		
		final String historySrType = request.getParameter(ChangeMgmtConstant.SRHISTORY_TYPE); 
		String gridColumns[]=null;
		RequestListResult serviceRequestListResult=null;
		boolean breakFixFlag=false;
		
		PortletSession session = request.getPortletSession();
		gridColumns = ChangeMgmtConstant.ALLHISTORYCOLUMNS;
		
		LOGGER.debug("timezone offset is " + request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		String timezoneOffset=request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		
		float timezoneOffSetinFloat=0;
		
		if(!StringUtil.isEmpty(timezoneOffset))
		{
			timezoneOffSetinFloat = Float.valueOf(timezoneOffset);
			LOGGER.debug("timezone offset in float is " + timezoneOffSetinFloat);
		}
		
		RequestListContract contract = ContractFactory.getHistoryListContract(request,historySrType,gridColumns);
		
		//Setting the asset Id as filter criteria
		contract.getFilterCriteria().put(ChangeMgmtConstant.ASSETID, assetId);
		contract.getFilterCriteria().remove("serviceRequest.startDate");
		contract.getFilterCriteria().remove("serviceRequest.endDate");
		//End-----------
		LOGGER.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
		contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
		LOGGER.info("start printing lex LOGGER");
		ObjectDebugUtil.printObjectContentForDM(contract, LOGGER);
		LOGGER.info("end printing lex loggger");
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		
		String[] generatorPatterns = new String[] { "serviceRequestNumber", "serviceRequestStatusDate", 
				"asset.serialNumber", "asset.productLine", "problemDescription", "resolutionCode", "serviceRequestStatus",
				"serviceAddress.addressName", "serviceAddress.officeNumber", "serviceAddress.city", "serviceAddress.state", 
				"serviceAddress.province", "serviceAddress.county", "serviceAddress.district", "serviceAddress.postalCode", "serviceAddress.country", 
				"custRefNumber", "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", 
				"primaryContact.workPhone", "contractType", "asset.deviceTag", "asset.hostName", "costCenter","accountName"};
		try {
			contract.setSessionHandle(crmSessionHandle);
			LOGGER.debug("Inside Device finder controller -------------------->>>>");
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL,
					"retrieveServiceRequestHistoryList", PortalSessionUtil
					.getMdmId(session), PortalSessionUtil.getServiceUser(
							session).getEmailAddress());
			
			
				serviceRequestListResult = requestTypeService.retrieveRequestList(contract);
			
			PerformanceTracker.endTracking(lexmarkTran);
			
			if(serviceRequestListResult!=null && !breakFixFlag)
			{
				LOGGER.debug("going for xml generation other than break fix");
			List<ServiceRequest> serviceRequestList = serviceRequestListResult.getRequestList();
			
			LOGGER.debug("Hisotry List size is " + serviceRequestList.size());
			int totalCount = serviceRequestListResult.getTotalCount();
			
			String xmlContent = null;
			for(ServiceRequest sr:serviceRequestList){
				if(sr.getArea()!=null){
						String area = getLocalizeSiebelValue(sr.getArea().getValue(),SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(),request.getLocale());
						sr.getArea().setName(area);
				}
			}
			xmlContent = getXmlOutputGeneratorUtil(request.getLocale())
					.generateXMLForAllRequestOnAnAsset(serviceRequestList,
							totalCount, contract.getStartRecordNumber(), timezoneOffSetinFloat);
			
			PrintWriter out = response.getWriter();
			response.setContentType(ChangeMgmtConstant.CONTENTTYPEXML);
			out.print(xmlContent);
			out.flush();
			out.close();
			}
		}catch (Exception e) {
			LOGGER.error("Exception 1:"+e.getMessage());
			logStackTrace(e);
		}
		finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		LOGGER.exit(this.getClass().getSimpleName(),"retrieveHistoryListByAssetId");
	}
	// end
	
	//Common Method uploading attachment 
	/**
	 * @param attachmentList 
	 * @param srRowId 
	 * @throws Exception 
	 */
	public void uploadAttachment(List <Attachment> attachmentList, String srRowId) throws Exception	{
		AttachmentContract contract = new AttachmentContract();
		List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
		if(attachmentList!=null && !attachmentList.isEmpty()){
			for (int i=0;i<attachmentList.size();i++){
				if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
					createSRAttachmentList.add(attachmentList.get(i));
					LOGGER.debug("Display Attachment Name: "+ attachmentList.get(i).getDisplayAttachmentName());
					LOGGER.debug("Attachment Name: "+ attachmentList.get(i).getAttachmentName());
					LOGGER.debug("Actual File Name: "+ attachmentList.get(i).getActualFileName());
					LOGGER.debug("File Name: "+ attachmentList.get(i).getFileName());
				}
			}
			if(createSRAttachmentList!=null && !createSRAttachmentList.isEmpty()){
				contract.setAttachments(createSRAttachmentList);
				contract.setRequestType("Service Request");
				contract.setIdentifier(srRowId);					
				ObjectDebugUtil.printObjectContent(contract, LOGGER);
				long timeBeforeCall=System.currentTimeMillis();
				attachmentService.uploadAttachments(contract);
				long timeAfterCall=System.currentTimeMillis();
				LOGGER.info("start printing lex logger");
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_UPLOADATTACHMENTS, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_AMIND,contract);
				LOGGER.info("end printing lex loggger");
			}
			
		}			
	}
	
}