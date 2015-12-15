package com.lexmark.services.portlet.cm;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.amind.common.util.StringUtils;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AssetResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.services.api.cm.ManageAssetService;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.FleetManagementForm;
import com.lexmark.services.form.ManageAssetForm;
import com.lexmark.services.form.validator.ManageAssetFormValidator;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.JSONEncryptUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.AssetFileWriteUtility;
import com.lexmark.services.util.URLImageUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.StringUtil;

/**
 * @author Wipro 
 * @version 1.0 
 *
 */

@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={ChangeMgmtConstant.CHANGEASSETFORM, ChangeMgmtConstant.DECOMMASSETFORM})
public class ManageMultiAssetsController extends BaseController {
	
	@Autowired
	private ManageAssetService manageAssetService;
	@Autowired
	private ManageAssetFormValidator assetFormValidator;
	@Autowired
	private CommonController commonController;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ExecutorService executorService;
	
	private String lbsFormPost;
	
	/**
	 * variable Declaration
	 */
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageMultiAssetsController.class);
	private static final String METH_REDIRECTTOCHANGEDECOM = "redirectToChangeMultiAsset";
	private static final String METH_GETRESOURCEURL = "getResourceURL";
	private static final String METH_CHANGEMULTIASSET = "changeMultiAsset";
	private static final String METH_DECOMMULTIASSET = "decommissionMultiAsset";
	private static final String METH_CHANGEASSET = "changeAsset";
	private static final String METH_CHANGEASSETCONFIRMATN = "showChangeAssetConfirmation";
	private static final String METH_DECOMMISIONASSETCONFIRMATN = "showdecommissionAssetConfirmation";
	
	private static final String METH_DOWEBSERVICECALL = "doWebServiceCall";
	private static final String METH_CREATEFUTURETASK ="createFutureTask";
	private static final String METH_WRITECSV ="writeCSV";
	
	private static final String METH_PRINTMULTIASSET = "printMultiAsset";
	private static final String METH_SENDMULTIASSETEMAIL = "sendMultiAssetEmail"; 
	private static final String METH_SHOWMULTIASSETSUMMARY = "showMultiAssetSummary";
	private static final String METH_INITBINDER = "initbinder";
	
	private static final String METH_GOTOCHANGEMULTIASSET = "goToChangeMultiAsset";
	private static final String METH_GOTODECOMMULTIASSET = "goToDecomMultiAsset";
	
	private static Logger perfLogger = LogManager.getLogger("performance");
	
	/**
	 * listOfFileTypes
	 */
	private String listOfFileTypes;
	/**
	 * attMaxCount
	 */
	private String attMaxCount;
	/**
	 * @return listOfFileTypes 
	 */
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}
	/**
	 * @param listOfFileTypes 
	 */
	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}
	/**
	 * @return attMaxCount 
	 */
	public String getAttMaxCount() {
		return attMaxCount;
	}
	/**
	 * @param attMaxCount 
	 */
	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}
	
	/**
	 * This method is used to bind the validator for form beans, 
	 * Also custom date editor for LTPC fields are registered here 
	 * @param binder 
	 * @param locale 
	 */
	@InitBinder(value={ChangeMgmtConstant.ADDASSETFORM, ChangeMgmtConstant.CHANGEASSETFORM, ChangeMgmtConstant.DECOMMASSETFORM})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		LOGGER.enter(this.getClass().getSimpleName(), METH_INITBINDER);
		String masFormInstanceName=binder.getObjectName();		
		LOGGER.info("Binder Object Name " + masFormInstanceName + ", Language is " + locale.getLanguage()
				+ ", country is " + locale.getCountry() + ", format is " + DateUtil.getDateFormatByLang(locale.getLanguage()
				+", Format with timestamp is " + DateUtil.getDateFormatByLang(locale.getLanguage())+ChangeMgmtConstant.HOURFORMAT));
				
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		DateFormat sdfWithTimestamp=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage())+ChangeMgmtConstant.HOURFORMAT);
		sdfWithTimestamp.setLenient(false);		
		binder.registerCustomEditor(Date.class,"assetDetail.lastPageReadDate",new CustomDateEditor(sdfWithTimestamp, true));
		binder.registerCustomEditor(Date.class,"assetDetail.lastColorPageReadDate",new CustomDateEditor(sdfWithTimestamp, true));		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		if (binder.getTarget() instanceof ManageAssetForm) {
			
			((ManageAssetForm)binder.getTarget()).setDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
			((ManageAssetForm)binder.getTarget()).setDateFormatWithTime(DateUtil.getDateFormatByLang(locale.getLanguage()+ChangeMgmtConstant.HOURFORMAT));
			
			if(binder.getObjectName().equalsIgnoreCase(ChangeMgmtConstant.ADDASSETFORM) || 
			binder.getObjectName().equalsIgnoreCase(ChangeMgmtConstant.CHANGEASSETFORM))
			{
				binder.setValidator(assetFormValidator);
			}	
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_INITBINDER);
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
	 * Common method forwards the request to change/decommission asset page 
	 * @param req 
	 * @param resp 
	 * @param model 
	 * @param assetId 
	 * @return String 
	 * @throws Exception 
	 * @param requestType  
	 * @param map  
	 */
	public String redirectToChangeMultiAsset(RenderRequest req, RenderResponse resp, Model model, 
			@RequestParam(value=ChangeMgmtConstant.REQUESTTYPE)String requestType, 
			ModelMap map) throws Exception 
		{
		LOGGER.enter(this.getClass().getSimpleName(), METH_REDIRECTTOCHANGEDECOM);
		List<AssetResult> assetResultList = new ArrayList<AssetResult>();	
		Map<String, Asset> assetMap = new HashMap<String, Asset>();
		List<Asset> assetDetailsList = new ArrayList<Asset>();
		AssetContract contract =null;
		Asset acntAsset = new Asset();
		ManageAssetForm masFormForChange=null;
		ManageAssetForm masFormForDecomm=null;
		
		Boolean changeAssetFlag=false;
		AccountContact accContact=null;
		Boolean isUpdateFlag=false;
		PortletSession session = req.getPortletSession();
		
		//Added for View Grid
		setModelParams(model,session);
		//Ends view Grid
		
		//Added for LBS
		String fleetManagementFlag =  (String)req.getAttribute("fleetManagementFlag");
		String moveAssetFlag =  (String)req.getAttribute("moveAssetFlag");
		String backToMap=(String)req.getAttribute("backJSON");
		LOGGER.debug("back json= " +backToMap);
		LOGGER.debug("request param is " + req.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		String timezoneOffset=req.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		
		if(!StringUtil.isEmpty(timezoneOffset))
		{
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, timezoneOffset);
		}
		
		FleetManagementForm fleetMgmtForm = (FleetManagementForm) req.getAttribute("fltForm");
		LOGGER.debug("inside fleetMgmtForm:::"+fleetMgmtForm);
		String multiAssetList = fleetMgmtForm.getMultiAssetList();
		assetDetailsList = fleetMgmtForm.getAssetDetailsList();
		LOGGER.debug("assetDetailsList:::"+assetDetailsList.size());
		if(assetDetailsList != null && assetDetailsList.size() > 0)
		{
			int assetInd = 0;
			int astSize = assetDetailsList.size();
			for(assetInd = 0; assetInd < astSize; assetInd++)
			{
				Asset newAsset = assetDetailsList.get(assetInd);
				LOGGER.debug("newAsset:::"+newAsset);
				assetMap.put(newAsset.getAssetId(), newAsset);
				LOGGER.debug("assetMap::::"+assetMap);
			}
		}
		//assetMap = fleetMgmtForm.getAssetDetailsMap();
		String[] assetIdArr = multiAssetList.split(",");
		if(assetIdArr.length > 0 && assetIdArr[0].length() > 0)
		{
			LOGGER.info("Asset List is " + assetIdArr[0]);			
			contract= commonController.getAssetContract(assetIdArr[0], req);
			model.addAttribute("multiAssetList", multiAssetList);
		}

		//it is the information of the requester
		try{
			accContact=commonController.getContactInformation(req, resp);
		}
		catch(LGSRuntimeException ex)
		{
			req.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
			logStackTrace(ex);
			try{
				getResourceURL(resp, model);				
			}catch(LGSCheckedException le){				
				logStackTrace(le);
			}
			//For error remain in assetList page
			return ChangeMgmtConstant.ASSETLISTPATH;
		}
		
		//GenericAddress currentInstalledAddess = null;
		 
		LOGGER.debug("after accContact fetched===> ");	
		
		if(!StringUtil.isStringEmpty(requestType))
		{
			if(requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGEMULTIASSETREQTYPE))
			{
				changeAssetFlag=true;				
				masFormForChange=new ManageAssetForm();
				masFormForChange.setBackToMap(StringUtils.isNotBlank(backToMap)==true?backToMap:"");
				LOGGER.debug("inside change asset update flag " + req.getParameter(ChangeMgmtConstant.ISUPDATEFLAG));
				LOGGER.debug("inside change asset prev sr no " + req.getParameter(ChangeMgmtConstant.PREVSRNO));
				
				if(!StringUtil.isEmpty(req.getParameter(ChangeMgmtConstant.ISUPDATEFLAG)))
				{
					//It will be set to true
					isUpdateFlag=Boolean.parseBoolean(req.getParameter(ChangeMgmtConstant.UPDATEFLAG));
				}
				if(!StringUtil.isEmpty(req.getParameter(ChangeMgmtConstant.PREVSRNO)) && isUpdateFlag)
				{
					masFormForChange.setPrevSrNo(req.getParameter(ChangeMgmtConstant.PREVSRNO));
					masFormForChange.setUpdateSrFlag(isUpdateFlag);
				}
				contract.setPageName(ChangeMgmtConstant.CM_DEVICE_DETAIL);
			}
			else if(requestType.equalsIgnoreCase(ChangeMgmtConstant.DECOMMMULTIASSETREQTYPE)) 
			{
				changeAssetFlag=false;
				
				masFormForDecomm=new ManageAssetForm(); //mas form for decomm would be instantiated for decomm request
				masFormForDecomm.setBackToMap(StringUtils.isNotBlank(backToMap)==true?backToMap:"");
				ServiceRequest serviceReq=new ServiceRequest();
				masFormForDecomm.setServiceRequest(serviceReq);
				masFormForDecomm.getServiceRequest().setPrimaryContact(accContact);
				if(!StringUtil.isEmpty(req.getParameter(ChangeMgmtConstant.PREVSRNO)))
				{
					masFormForDecomm.setPrevSrNo(req.getParameter(ChangeMgmtConstant.PREVSRNO));
				}
				contract.setPageName(ChangeMgmtConstant.CM_DECOMMISSION_DEVICE_DETAIL);
			}
		}
		try{
			
			session.removeAttribute("attachmentList");
			session.removeAttribute("attachmentForm");
			long timeBeforeCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LOGGER);
			LOGGER.info("end printing lex loggger");
			//assetResultList=commonController.retrieveAssetDetailsList(contract);
			//changes for page counts
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGEASSETS_MSG_RETRIEVEASSETDETAIL, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			LOGGER.debug("** MPS PERFORMANCE TESTING RETRIEVE CHANGE MANAGEMENT ASSET DETAIL ==>: " + (System.currentTimeMillis()- timeBeforeCall));
			
			if (assetMap != null && assetMap.size() > 0) {
				
				/* to set the general asset information, call the commonController.retrieveAssetDetail with the first assetId in the multiAssetList */
				//AssetResult assetResult=commonController.retrieveAssetDetail(contract);
				//acntAsset = assetResult.getAsset();
				LOGGER.debug("acntAsset::"+acntAsset);
				
				/* For Asset Images will make Ajax call in doc ready - productImageService.retrieveProductImageUrl */
				/* For commonController.assembleDevice - will make Ajax call in doc ready */
				/* Will replace the workPhone in Jsp - setWorkPhone() */
				
				//Initializes the form for file upload
				FileUploadForm fileUploadForm = new FileUploadForm();
				model.addAttribute("fileUploadForm", fileUploadForm);
				LOGGER.debug("fileUploadForm::"+fileUploadForm);
				
				if(masFormForChange!=null)
				{
					LOGGER.debug("assetList1::"+assetMap);
					masFormForChange.setAssetDetailsMap(assetMap);
					masFormForChange.setAssetDetail(acntAsset);
					ServiceRequest serviceReq=new ServiceRequest();
					masFormForChange.setServiceRequest(serviceReq);
					
					LOGGER.debug("accContact::"+accContact);
					masFormForChange.getServiceRequest().setPrimaryContact(accContact);
					LOGGER.debug("masFormForChange::"+masFormForChange);
					//This would display the current data in the change asset jsp like customer cost code etc...
					
					//Adding LBS changes for Page Flow determination					
					if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){												
						if(moveAssetFlag != null && "true".equals(moveAssetFlag)){
								LOGGER.debug("Inside move::pageFlow::fleetMgmtMove");
								model.addAttribute("pageFlow","fleetMgmtMove");
								GenericAddress moveAddress = (GenericAddress)session.getAttribute("moveAddress", PortletSession.APPLICATION_SCOPE);
								LOGGER.debug("Inside changeredirect:::move"+moveAddress);
								GenericAddress installAddress = (GenericAddress)session.getAttribute("installAddress", PortletSession.APPLICATION_SCOPE);
								LOGGER.debug("Inside changeredirect:::install"+installAddress);
								masFormForChange.getAssetDetail().setMoveToAddress(moveAddress);
								masFormForChange.getAssetDetail().setInstallAddress(installAddress);
								LOGGER.debug("installAdd::"+masFormForChange.getAssetDetail().getInstallAddress());
								LOGGER.debug("moveToAdd::"+masFormForChange.getAssetDetail().getMoveToAddress());
								masFormForChange.setFleetManagementFlag(fleetManagementFlag);
						}
						else{
							model.addAttribute("pageFlow","fleetMgmtChange");
							LOGGER.debug("inside change");
							GenericAddress installAddress = (GenericAddress)session.getAttribute("installAddress", PortletSession.APPLICATION_SCOPE);
							
							masFormForChange.getAssetDetail().setInstallAddress(installAddress);
							LOGGER.debug("installAdd::"+masFormForChange.getAssetDetail().getInstallAddress());
							masFormForChange.setFleetManagementFlag(fleetManagementFlag);
						}						
					}else{
						model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_CHANGE);
						masFormForChange.setFleetManagementFlag("false");
					}				
					
					map.addAttribute(ChangeMgmtConstant.CHANGEASSETFORM, masFormForChange);
				}
				else 
				{
					LOGGER.debug("assetList1::"+assetMap);
					masFormForDecomm.setAssetDetailsMap(assetMap);
					masFormForDecomm.setAssetDetail(acntAsset);
					
					GenericAddress installAddress = (GenericAddress)session.getAttribute("installAddress", PortletSession.APPLICATION_SCOPE);
					masFormForDecomm.getAssetDetail().setInstallAddress(installAddress);
					masFormForDecomm.getAssetDetail().setPickupAddress(installAddress);
					
					LOGGER.debug("installAdd::"+masFormForDecomm.getAssetDetail().getInstallAddress());
					LOGGER.debug("getPickupAddress::"+masFormForDecomm.getAssetDetail().getPickupAddress());

					model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_DECOMM);
					
					if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
						LOGGER.debug("Setting LBS Flag to true ");
						model.addAttribute("fleetManagementFlag", "true");
						masFormForDecomm.setFleetManagementFlag(fleetManagementFlag);
					}
					else{
						LOGGER.debug("Setting LBS Flag to false");
						model.addAttribute("fleetManagementFlag", "false");	
						masFormForDecomm.setFleetManagementFlag("false");
					}
					model.addAttribute(ChangeMgmtConstant.DECOMMASSETFORM, masFormForDecomm);
				}
				
				//Setting flag for flow from LBS 				 
				if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
					LOGGER.debug("Setting Account information from asset details call ");
					Map<String,String> accDetails=new HashMap<String, String>();
					String acntName = "";
					if(fleetMgmtForm.getAccount().getAccountName() != null && fleetMgmtForm.getAccount().getAccountName().length() > 0)
					{
						acntName = fleetMgmtForm.getAccount().getAccountName();
						LOGGER.debug("acntName::"+acntName);
					}
					accDetails.put(ChangeMgmtConstant.ACCOUNTNAME, acntName);
					accDetails.put(ChangeMgmtConstant.ACCOUNTID, fleetMgmtForm.getAccount().getAccountId());
					LOGGER.debug("fleetMgmtForm.getAccount().getAccountId()::"+fleetMgmtForm.getAccount().getAccountId());
					session.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accDetails ,PortletSession.APPLICATION_SCOPE);
					LOGGER.debug("accDetails::"+session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, PortletSession.APPLICATION_SCOPE));
					
					LOGGER.debug("Setting LBS Flag to true ");
					model.addAttribute("fleetManagementFlag", "true");					
					
					LOGGER.debug("moveAssetFlag::"+moveAssetFlag);
					if(moveAssetFlag != null && "true".equals(moveAssetFlag)){						
							model.addAttribute("moveAssetFlag", "true");
					}
					
				}else{
					LOGGER.debug("Setting LBS Flag to false");
					model.addAttribute("fleetManagementFlag", "false");	
					
				}
			}
		}catch(LGSRuntimeException ex)
		{	
			LOGGER.error(ex.getMessage());
			logStackTrace(ex);
			req.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
			try{
				getResourceURL(resp, model);
			}catch(LGSCheckedException le){
				LOGGER.debug("exception occured "+le);
				logStackTrace(le);
			}
			//If error return to assetList
			
			if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true") && changeAssetFlag){
				map.addAttribute(ChangeMgmtConstant.CHANGEASSETFORM, masFormForChange);
				req.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
				return ChangeMgmtConstant.CHANGEMULTIASSETPATH;
			}
			else{
				return ChangeMgmtConstant.ASSETLISTPATH;
			}
		}	
		catch(Exception ex)
		{	
			LOGGER.error(ex.getMessage());
			logStackTrace(ex);
			req.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
			try{
				getResourceURL(resp, model);
			}catch(LGSCheckedException le){
				LOGGER.debug("exception occured "+le);
				logStackTrace(le);
			}
			//If error return to assetList
			fleetManagementFlag = (String)req.getAttribute("fleetManagementFlag");
			if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true") && changeAssetFlag){
				map.addAttribute(ChangeMgmtConstant.CHANGEASSETFORM, masFormForChange);
				req.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
				return ChangeMgmtConstant.CHANGEMULTIASSETPATH;
			}
			else{
				return ChangeMgmtConstant.ASSETLISTPATH;
			}
		}
		if(changeAssetFlag)	//The request is for Change Asset
		{
			LOGGER.debug("returning to change multi asset=====> ");
			LOGGER.exit(this.getClass().getSimpleName(),METH_REDIRECTTOCHANGEDECOM);
			return ChangeMgmtConstant.CHANGEMULTIASSETPATH;
		}
		else{ 
			LOGGER.exit(this.getClass().getSimpleName(),METH_REDIRECTTOCHANGEDECOM);
			//The request is for Decommission Asset			
			return ChangeMgmtConstant.DECOMMULTIASSETPATH;
		}
	}
	
	/**
	 * common method for getting the resource url for asset list population 
	 * @param response 
	 * @param model 
	 * @throws LGSCheckedException 
	 */
	private void getResourceURL(RenderResponse response, Model model) throws LGSCheckedException{
		LOGGER.enter(this.getClass().getSimpleName(), METH_GETRESOURCEURL);
		try{
			ResourceURL resURL =response.createResourceURL();
			resURL.setResourceID(ChangeMgmtConstant.DEVICELISTRESURL);//This mapping is present in the common controller			
			model.addAttribute(ChangeMgmtConstant.ASSETRESULTLIST, resURL.toString());
		}catch(Exception ex)
		{
			logStackTrace(ex);
			throw new LGSCheckedException("Error while creating resource URL");
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_GETRESOURCEURL);
	}
	
	/**
	 * This method forwards the request to change asset confirmation page 
	 * @param renderRequest 
	 * @param renderResponse 
	 * @param manageAssetFormForChange 
	 * @param result 
	 * @param model 
	 * @return String 
	 * @param attachForm 
	 * @param modelMap  
	 * @throws Exception 
	 * @throws LGSDBException 
	 */
	@ActionMapping(params = "action=changeMultiAssetConfirmation")
	public void showChangeMultiAssetConfirmation(
			ActionRequest actRequest,ActionResponse actResponse,@ModelAttribute(ChangeMgmtConstant.CHANGEASSETFORM) ManageAssetForm manageAssetFormForChange,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,BindingResult result, Model model, ModelMap modelMap) throws Exception, LGSDBException 
			{	
		
		LOGGER.enter(this.getClass().getSimpleName(),METH_CHANGEASSETCONFIRMATN);
		LOGGER.debug("request param is " + actRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		String timezoneOffset=actRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		String pageFlow=actRequest.getParameter("pageFlow");
		String multiAssetList=actRequest.getParameter("multiAssetList");
		
		LOGGER.debug("Inside showChangeMultiAssetConfirmation::"+ pageFlow);
		FutureTask<Attachment> future = null;
		
		PortletSession session = actRequest.getPortletSession();
		
		if(manageAssetFormForChange.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			manageAssetFormForChange.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		
		String fleetManagementFlag = (String)actRequest.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			actRequest.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
			actRequest.setAttribute("fleetManagementFlag", "false");
			
		}
		//page counts End
		if(!StringUtil.isEmpty(timezoneOffset))
		{
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, timezoneOffset);
		}
		if(!StringUtil.isEmpty(pageFlow))
		{
			model.addAttribute("pageFlow", pageFlow);
		}
		else{
			model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_CHANGE);
		}
		model.addAttribute("multiAssetList", multiAssetList);
		
		//This would fill up the current identifiers column in jsp
		if(modelMap.containsAttribute(ChangeMgmtConstant.CHANGEASSETFORM))		
		{				
			manageAssetFormForChange=(ManageAssetForm)modelMap.get(ChangeMgmtConstant.CHANGEASSETFORM);
			modelMap.addAttribute(ChangeMgmtConstant.CHANGEASSETFORM,manageAssetFormForChange);
		}
		
		if (result.hasErrors()) {
			LOGGER.debug("Contains errors");
			//Server Side validation errors would be displayed in change asset jsp itself
			commonController.getContactInformation(actRequest, actResponse);
			LOGGER.exit(this.getClass().getSimpleName(),
					METH_DECOMMISIONASSETCONFIRMATN);
			actResponse.setRenderParameter("action", "redirectToChangeMultiAsset");
		} 
		else
		{	
			manageAssetFormForChange.refreshSubmitToken(actRequest);
			LOGGER.exit(this.getClass().getSimpleName(),METH_CHANGEASSETCONFIRMATN);
			
			//to create the attachment form
			AttachmentForm attachmForm = new AttachmentForm();
			
			// Creating the future task to create the CSV and upload it
			future = createFutureTask(manageAssetFormForChange.getAssetDetailsMap(), ChangeMgmtConstant.CSVTYPEMANAGEMULTIASSET, pageFlow);
			
			while (!future.isDone()) {
				LOGGER.debug("CSV writing Task is not yet completed======>");
				try {
					// Go to sleep mode if the future task is not completed
					Thread.sleep(500);
				} catch (InterruptedException interruptedException) {
					// if any error do not show the summary page
					logStackTrace(interruptedException);
					//setParametersOnError(actRequest,actResponse);
				}
			}
			
			try {
				LOGGER.debug("Here is the result..." + future.get());
				if (future.get() != null) {
					if (!future.get().getAttachmentName().equalsIgnoreCase(ChangeMgmtConstant.CSVFAILURE)) {
						Attachment attachFile = future.get();
						List<Attachment> attachmentList = new ArrayList<Attachment>();
						//Attachment attachFile = new Attachment();
						
						/*attachFile.setActualFileName(fName);
						attachFile.setAttachmentName(fName);
						//attachFile.setCompletedOn(completedOn);
						attachFile.setDisplayAttachmentName(fName);
						attachFile.setFileName(fName);*/
						
						//double fileSizeDisplay=20000;
						LOGGER.debug("File Size is " + attachFile.getSize());
						double fileSizeDisplay=attachFile.getSize()/1024;
						LOGGER.debug("File Size in Double is " + fileSizeDisplay);
						BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
						LOGGER.debug("File Size in BigDecimal is " + roundedFileSizeDisplay);
						roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
						LOGGER.debug("File Size in BigDecimal ROUND_HALF_UP is " + roundedFileSizeDisplay);
						attachFile.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
						LOGGER.debug("attachFile.getSizeForDisplay::"+attachFile.getSizeForDisplay());
						
						attachmentList.add(attachFile);
						attachForm.setAttachmentList(attachmentList);
						
						session.setAttribute("attachmentList", attachmentList);
						session.setAttribute("attachmentForm", attachForm);
					} else {
						//setParametersOnError(actRequest,actResponse);
					}
				}
				//This would be executed if any error in CSV Creation
			} catch (ExecutionException executionException) {
				logStackTrace(executionException);
			} catch (Exception exception) {
				logStackTrace(exception);
			}
			
			//renderRequest.setAttribute("attachmentForm",attachForm);
			model.addAttribute("attachmentForm", attachForm);
			
			LOGGER.debug("request param is " + actRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
			actResponse.setRenderParameter("action", "redirectToChangeMultiAssetConf");
			
		}
	}
	
	
	@RenderMapping(params="action=redirectToChangeMultiAssetConf")
	public String showChangeMultiAssetConf(RenderRequest req,RenderResponse resp){
		return ChangeMgmtConstant.CHANGEMULTIASSETCONFPATH;
	}
	
	@RenderMapping(params="action=redirectToChangeMultiAsset")
	public String showChangeMultiAsset(RenderRequest req,RenderResponse resp){
		return ChangeMgmtConstant.CHANGEASSETPATH;
	}
	
	
	/**
	 * This method forwards the request to change asset confirmation page 
	 * @param renderRequest 
	 * @param renderResponse 
	 * @param manageAssetFormForDecommission 
	 * @param result 
	 * @param model 
	 * @return String 
	 * @param attachForm 
	 * @param modelMap  
	 * @throws Exception 
	 * @throws LGSDBException 
	 */
	@ActionMapping(params = "action=decommissionMultiAssetConfirmation")
	public void showdecommissionAssetConfirmation(
			ActionRequest actRequest,ActionResponse actResponse,@ModelAttribute(ChangeMgmtConstant.DECOMMASSETFORM) ManageAssetForm manageAssetFormForDecommission,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,BindingResult result, Model model, ModelMap modelMap) throws Exception, LGSDBException 
			{	
		
		LOGGER.enter(this.getClass().getSimpleName(),METH_DECOMMISIONASSETCONFIRMATN);
		LOGGER.debug("request param is " + actRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		String timezoneOffset=actRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		String pageFlow=actRequest.getParameter("pageFlow");
		String multiAssetList=actRequest.getParameter("multiAssetList");
		
		FutureTask<Attachment> future = null;
		
		PortletSession session = actRequest.getPortletSession();
		
		if(manageAssetFormForDecommission.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			manageAssetFormForDecommission.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		
		String fleetManagementFlag = (String)actRequest.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			actRequest.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
			actRequest.setAttribute("fleetManagementFlag", "false");
			
		}
		//page counts End
		if(!StringUtil.isEmpty(timezoneOffset))
		{
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, timezoneOffset);
		}
		if(!StringUtil.isEmpty(pageFlow))
		{
			model.addAttribute("pageFlow", pageFlow);
		}
		else{
			model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_CHANGE);
		}
		model.addAttribute("multiAssetList", multiAssetList);
		
		//This would fill up the current identifiers column in jsp
		if(modelMap.containsAttribute(ChangeMgmtConstant.DECOMMASSETFORM))		
		{				
			manageAssetFormForDecommission=(ManageAssetForm)modelMap.get(ChangeMgmtConstant.DECOMMASSETFORM);
			modelMap.addAttribute(ChangeMgmtConstant.DECOMMASSETFORM,manageAssetFormForDecommission);
		}
		
		if (result.hasErrors()) {
			LOGGER.debug("Contains errors");
			//Server Side validation errors would be displayed in change asset jsp itself
			commonController.getContactInformation(actRequest, actResponse);
			LOGGER.exit(this.getClass().getSimpleName(),
					METH_DECOMMISIONASSETCONFIRMATN);
			actResponse.setRenderParameter("action", "redirectToDecomMultiAsset");
		} 
		else
		{	
			manageAssetFormForDecommission.refreshSubmitToken(actRequest);
			LOGGER.exit(this.getClass().getSimpleName(),METH_DECOMMISIONASSETCONFIRMATN);
			
			//to create the attachment form
			AttachmentForm attachmForm = new AttachmentForm();
			
			// Creating the future task to create the CSV and upload it
			future = createFutureTask(manageAssetFormForDecommission.getAssetDetailsMap(), ChangeMgmtConstant.CSVTYPEMANAGEMULTIASSET, pageFlow);
			
			while (!future.isDone()) {
				LOGGER.debug("CSV writing Task is not yet completed======>");
				try {
					// Go to sleep mode if the future task is not completed
					Thread.sleep(500);
				} catch (InterruptedException interruptedException) {
					// if any error do not show the summary page
					logStackTrace(interruptedException);
					//setParametersOnError(actRequest,actResponse);
				}
			}
			
			try {
				LOGGER.debug("Here is the result..." + future.get());
				if (future.get() != null) {
										
					if (!future.get().getAttachmentName().equalsIgnoreCase(ChangeMgmtConstant.CSVFAILURE)) {
						Attachment attachFile = future.get();
						List<Attachment> attachmentList = new ArrayList<Attachment>();
						
						LOGGER.debug("File Size is " + attachFile.getSize());
						double fileSizeDisplay=attachFile.getSize()/1024;
						LOGGER.debug("File Size in Double is " + fileSizeDisplay);
						BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
						LOGGER.debug("File Size in BigDecimal is " + roundedFileSizeDisplay);
						roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
						LOGGER.debug("File Size in BigDecimal ROUND_HALF_UP is " + roundedFileSizeDisplay);
						attachFile.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
						LOGGER.debug("attachFile.getSizeForDisplay::"+attachFile.getSizeForDisplay());
						
						attachmentList.add(attachFile);
						attachForm.setAttachmentList(attachmentList);
						
						session.setAttribute("attachmentList", attachmentList);
						session.setAttribute("attachmentForm", attachForm);
					} else {
						//setParametersOnError(actRequest,actResponse);
					}
				}
				//This would be executed if any error in CSV Creation
			} catch (ExecutionException executionException) {
				logStackTrace(executionException);
			} catch (Exception exception) {
				logStackTrace(exception);
			}
			
			//renderRequest.setAttribute("attachmentForm",attachForm);
			model.addAttribute("attachmentForm", attachForm);
			
			LOGGER.debug("request param is " + actRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
			actResponse.setRenderParameter("action","redirectToDecomAssetConf");
		}
	}
	
	
	@RenderMapping(params="action=redirectToDecomAssetConf")
	public String showDecomMultiAssetConf(RenderRequest req,RenderResponse resp){
		return ChangeMgmtConstant.DECOMMMULTIASSETCONFPATH;
	}
	
	@RenderMapping(params="action=redirectToDecomMultiAsset")
	public String showDecomMultiAsset(RenderRequest req,RenderResponse resp){
		return ChangeMgmtConstant.DECOMMULTIASSETPATH;
	}
	
	/**
	 * This method is responsible for invoking service calls for decommissioning an asset  
	 * on success redirects to Summary Page 
	 * @param actRequest 
	 * @param actResponse 
	 * @param masFormForDecomm 
	 * @param model 
	 * @param sessionStatus 
	 * @param attachForm 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=decommissionMultiAsset")
	public void decommissionMultiAsset(ActionRequest actRequest,
			ActionResponse actResponse, @ModelAttribute(ChangeMgmtConstant.DECOMMASSETFORM) ManageAssetForm masFormForDecomm,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,Model model,  SessionStatus sessionStatus) throws Exception
		{
		LOGGER.enter(this.getClass().getSimpleName(), METH_DECOMMULTIASSET);
		CreateServiceRequestResult result=null;
		PortletSession session = actRequest.getPortletSession();
		
		//LBS
		String fleetManagementFlag = (String)actRequest.getParameter("fleetManagementFlag");
		String pageFlow=(String)actRequest.getParameter("pageFlow");
		
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			actRequest.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
			actRequest.setAttribute("fleetManagementFlag", "false");
		}
		
		if(masFormForDecomm.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			masFormForDecomm.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		
		commonController.assembleDevice(masFormForDecomm.getAssetDetail(), actRequest.getLocale());/*Done for Defect 3924-Jan Release*/
		
		
			LOGGER.debug("User choose yes");
			if(masFormForDecomm.getDecommAssetFlag()!=null && ChangeMgmtConstant.YES.equalsIgnoreCase(masFormForDecomm.getDecommAssetFlag()))
			{
				LOGGER.debug("User choose yes");
				masFormForDecomm.setArea(ChangeMgmtConstant.AREA_DECOMMASSET_YES);		
				masFormForDecomm.setSubArea(ChangeMgmtConstant.SUBAREA_DECOMMASSET_YES);
			}
			else{
				LOGGER.debug("User did not choose");
				masFormForDecomm.setArea(ChangeMgmtConstant.AREA_DECOMMASSET_NO);		
				masFormForDecomm.setSubArea(ChangeMgmtConstant.SUBAREA_DECOMMASSET_NO);		
			}
		
		
		/****************This section has been added to prevent resubmit by browser refresh ***********/
		if (PortalSessionUtil.isDuplicatedSubmit(actRequest,masFormForDecomm)) {
			LOGGER.debug("ManageMultiAssetsController duplicated submit, do nothing!");		
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.serviceRequest.duplicateSubmission", null, actRequest.getLocale()));			
			model.addAttribute(masFormForDecomm);		
			
			actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_DECOMM);
			actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MULTIASSETSUMMARY);
		}
		/**************** Section end to prevent resubmit by browser refresh ***********/
		else{
		try{
			try{
				// Creating the service request
				result=doWebServiceCall(masFormForDecomm,actRequest);
				// To be displayed on summary page
				model.addAttribute(ChangeMgmtConstant.MODELATTRSRNO, result.getServiceRequestNumber());
				model.addAttribute("pageFlow",pageFlow);
				LOGGER.debug("pageflow"+pageFlow);
			
			}catch(LGSRuntimeException ex)
			{	
				logStackTrace(ex);
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.DECOMMMULTIASSETCONFPATH);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
			}
			catch(LGSBusinessException ex)
			{
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.DECOMMMULTIASSETCONFPATH);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);				
				logStackTrace(ex);
			}
			//This should happen after the web service call
			
			try {
				List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
				LOGGER.debug("attachmentList.size::"+attachmentList.size());
				Attachment attachFile = attachmentList.get(0); 
				LOGGER.debug("attachFile::"+attachFile.getFileName());
				String fName = attachFile.getFileName();
				LOGGER.debug("Here is the result..." + fName);
					if (result.getServiceRequestRowId() != null) {
						
						if(attachmentList!=null && !attachmentList.isEmpty()){
							List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
							
							AttachmentFile attachmentFile = new AttachmentFile();
							attachmentFile.setFileName(attachmentList.get(0).getDisplayAttachmentName());
							attachmentFile.setFileSize(attachmentList.get(0).getSize());
							displayAttachmentList.add(attachmentFile);
							if(attachmentList.get(0).getId()==null || attachmentList.get(0).getId().equalsIgnoreCase("")){//newly created attachments
								LOGGER.debug("Attachment id is either null or blank");
							}
							
							LOGGER.debug("No of attachment in the reviewAssetOrderForm "+attachmentList.size());
							model.addAttribute("displayAttachmentList", displayAttachmentList);
							attachForm.setDisplayAttachmentList(displayAttachmentList);
						}
						commonController.renameAttachment(fName, actRequest,
										result.getServiceRequestRowId());
					} else {
						//setParametersOnError(actRequest,actResponse);
					}
				//This would be executed if any error in CSV Creation
			} catch (ExecutionException executionException) {
				logStackTrace(executionException);
				//setParametersOnError(actRequest,actResponse);
			} catch (Exception exception) {
				LOGGER.debug("exception");
				List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
				model.addAttribute("displayAttachmentList", displayAttachmentList);
				attachForm.setDisplayAttachmentList(displayAttachmentList);
				LOGGER.debug("exception in change asset attachment");
				model.addAttribute("attachmentException", "attachfailed");
				exception.getMessage();
			}
			
			
			try
			{
				if(result.getServiceRequestRowId().length()>0)
				{
					//No error with rename, go for the summary page
					sessionStatus.setComplete();
					//After the service call, forward to the manage asset summary page					
					actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_DECOMM);
					actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MULTIASSETSUMMARY);
					
					//this is to enable re-submit of SR form on submit/draft exception
					Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, PortletSession.PORTLET_SCOPE);						
					masFormForDecomm.setSubmitToken(tokenInSession);
					model.addAttribute(masFormForDecomm);
				}
			}catch (Exception exceptn)//This would be executed if error while upload runnable thread fails
			{
				//if any error do not show the summary page
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.DECOMMMULTIASSETCONFPATH);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
				logStackTrace(exceptn);
			}
			
	
		actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_DECOMM);
		actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MULTIASSETSUMMARY);
		}catch(Exception ex)
		{
			//If any error do not show the summary page
			actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.DECOMMMULTIASSETCONFPATH);
			actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
			LOGGER.error("Inside catch block add asset "+ex.getMessage());			
			logStackTrace(ex);
		}
	  }
		LOGGER.exit(this.getClass().getSimpleName(), METH_DECOMMULTIASSET);
	}
	
	/**
	 * This method is responsible for invoking service calls for changing an asset 
	 * on success redirects to Summary Page 
	 * @param actRequest 
	 * @param actResponse 
	 * @param masFormForChange 
	 * @param attachForm 
	 * @param model 
	 * @param sessionStatus 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=changeMultiAsset")
	public void changeMultiAsset(ActionRequest actRequest,
			ActionResponse actResponse, @ModelAttribute(ChangeMgmtConstant.CHANGEASSETFORM) ManageAssetForm masFormForChange,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,Model model, SessionStatus sessionStatus) throws Exception{
		
		LOGGER.enter(this.getClass().getSimpleName(), METH_CHANGEMULTIASSET);
		CreateServiceRequestResult result=null;
		PortletSession session = actRequest.getPortletSession();
		String fleetManagementFlag = (String)actRequest.getParameter("fleetManagementFlag");
		
		// This parameter is used for detecting the multi-select move/change/decommission request type
		String multiSelectReqType = (String)actRequest.getParameter("multiAssetReqType");
		
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			actRequest.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			model.addAttribute("fleetManagementFlag","false");
			actRequest.setAttribute("fleetManagementFlag", "false");
		}
		
		String installAssetFlag = masFormForChange.getInstallAssetFlag();
		String pageFlow=(String)actRequest.getParameter("pageFlow");
		if(("No").equalsIgnoreCase(installAssetFlag))
		{
				LOGGER.debug("fleetmanagemove false");
			masFormForChange.setArea(ChangeMgmtConstant.AREA_CHANGEASSET_NO);		
			masFormForChange.setSubArea(ChangeMgmtConstant.SUBAREA_CHANGEASSET_NO);
		
			
		}
		else if(("Yes").equalsIgnoreCase(installAssetFlag))
		{
			masFormForChange.setArea(ChangeMgmtConstant.AREA_CHANGEASSET_YES);		
			masFormForChange.setSubArea(ChangeMgmtConstant.SUBAREA_CHANGEASSET_YES);
			}
		/*if(("fleetMgmtChange").equalsIgnoreCase(pageFlow))
		{
			LOGGER.debug("fleetmanagemove false");
			masFormForChange.setArea(ChangeMgmtConstant.DATAMANAGEMENTAREA);		
			masFormForChange.setSubArea(ChangeMgmtConstant.DATAMANAGEMENTSUBAREA);
		}
		else if(("fleetMgmtMove").equalsIgnoreCase(pageFlow))
		{
			masFormForChange.setArea(ChangeMgmtConstant.AREA_CHANGEASSET_YES);		
			masFormForChange.setSubArea(ChangeMgmtConstant.SUBAREA_CHANGEASSET_YES);
		}*/
		commonController.assembleDevice(masFormForChange.getAssetDetail(), actRequest.getLocale());/*Done for Defect 3924-Jan Release*/
		
		/****************This section has been added to prevent resubmit by browser refresh ***********/
		if (PortalSessionUtil.isDuplicatedSubmit(actRequest,masFormForChange)) {
			LOGGER.debug("ManageAssetsController duplicated submit, do nothing!");		
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.serviceRequest.duplicateSubmission", null, actRequest.getLocale()));
			
			model.addAttribute(masFormForChange);
			
			actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_CHANGE);
			actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MULTIASSETSUMMARY);
		}
		/**************** Section end to prevent resubmit by browser refresh ***********/
		else{
			try{
				
				try{
					// Creating the future task to create the CSV and upload it
					//future = createFutureTask(masFormForChange.getAssetDetailsMap(), ChangeMgmtConstant.CSVTYPEMANAGEASSET, multiSelectReqType);
					
					// Creating the service request
					result=doWebServiceCall(masFormForChange,actRequest);
					// To be displayed on summary page
					model.addAttribute(ChangeMgmtConstant.MODELATTRSRNO, result.getServiceRequestNumber());
					model.addAttribute("pageFlow",pageFlow);
					LOGGER.debug("pageflow"+pageFlow);					
				}catch(LGSRuntimeException ex)			
				{
					//if any error do not show the summary page
					actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.CHNGASSETCONFPGNM);
					actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);				
					logStackTrace(ex);
				}
				catch(LGSBusinessException ex)
				{
					actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.CHNGASSETCONFPGNM);
					actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);				
					logStackTrace(ex);
				}
				
				
				try {
					List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
					LOGGER.debug("attachmentList.size::"+attachmentList.size());
					Attachment attachFile = attachmentList.get(0); 
					LOGGER.debug("attachFile::"+attachFile.getFileName());
					String fName = attachFile.getFileName();
					LOGGER.debug("Here is the result..." + fName);
						if (result.getServiceRequestRowId() != null) {
							
							if(attachmentList!=null && !attachmentList.isEmpty()){
								List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
								
								AttachmentFile attachmentFile = new AttachmentFile();
								attachmentFile.setFileName(attachmentList.get(0).getDisplayAttachmentName());
								attachmentFile.setFileSize(attachmentList.get(0).getSize());
								displayAttachmentList.add(attachmentFile);
								if(attachmentList.get(0).getId()==null || attachmentList.get(0).getId().equalsIgnoreCase("")){//newly created attachments
									LOGGER.debug("Attachment id is either null or blank");
								}
								
								LOGGER.debug("No of attachment in the reviewAssetOrderForm "+attachmentList.size());
								model.addAttribute("displayAttachmentList", displayAttachmentList);
								attachForm.setDisplayAttachmentList(displayAttachmentList);
							}
							commonController.renameAttachment(fName, actRequest,
											result.getServiceRequestRowId());
						} else {
							//setParametersOnError(actRequest,actResponse);
						}
					//This would be executed if any error in CSV Creation
				} catch (ExecutionException executionException) {
					logStackTrace(executionException);
					//setParametersOnError(actRequest,actResponse);
				} catch (Exception exception) {
					LOGGER.debug("exception");
					List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
					model.addAttribute("displayAttachmentList", displayAttachmentList);
					attachForm.setDisplayAttachmentList(displayAttachmentList);
					LOGGER.debug("exception in change asset attachment");
					model.addAttribute("attachmentException", "attachfailed");
					exception.getMessage();
				}
				
				try{			
					if(result.getServiceRequestRowId().length()>0)
					{
						LOGGER.debug("No error with rename, go for the summary page");
						sessionStatus.setComplete();					
						//After the service call, forward to the manage asset summary page					
						actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_CHANGE);
						actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MULTIASSETSUMMARY);
						
						//this is to enable re-submit of SR form on submit/draft exception						
						Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, PortletSession.PORTLET_SCOPE);						
						masFormForChange.setSubmitToken(tokenInSession);
						model.addAttribute(masFormForChange);
					}
			   }
			   catch(Exception exceptn)
			   {
					actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.CHNGASSETCONFPGNM);
					actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
					logStackTrace(exceptn);
			   }
			}catch(Exception ex)
			{	
				//If any error do not show the summary page
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.CHNGASSETCONFPGNM);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
				LOGGER.error("Inside catch block change asset "+ex.getMessage());			
				logStackTrace(ex);
			}
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_CHANGEASSET);
	}
	
	/**
	 * This method forwards the request to manage asset summary page 
	 * @param req 
	 * @param model 
	 * @param srNo 
	 * @return String 
	 * @throws LGSDBException 
	 */
	@RenderMapping(params = "action=multiAssetSummary")
	public String showMultiAssetSummary(RenderRequest req, Model model, @ModelAttribute(ChangeMgmtConstant.MODELATTRSRNO) String srNo) throws LGSDBException {
		LOGGER.enter(this.getClass().getSimpleName(), METH_SHOWMULTIASSETSUMMARY);
		//Forward to the manage asset summary page
		LOGGER.debug("showMultiAssetSummary SR No is " + srNo);
		//LBS
		String fleetManagementFlag = (String)req.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug(" showMultiAssetSummary Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("showMultiAssetSummary Setting LBS Flag to false");
			model.addAttribute("fleetManagementFlag","false");
		}
		
		/*Map<String, String> deviceContactTypeMap=null;
		try{
			deviceContactTypeMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONTACT_TYPE_FOR_ASSET.getValue(), req.getLocale());
			LOGGER.debug("deviceContactTypeMap = "+ deviceContactTypeMap);
		}catch (Exception e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		model.addAttribute("deviceContactTypeList", deviceContactTypeMap);*/
		model.addAttribute(ChangeMgmtConstant.SRNUMBER,srNo);
		LOGGER.exit(this.getClass().getSimpleName(), METH_SHOWMULTIASSETSUMMARY);
		return ChangeMgmtConstant.MULTIASSETSUMMARYPATH;
	}
	
	/**
	 * Method responsible for back button in change asset review page 
	 * @param req 
	 * @param resp 
	 * @param mAssetForm 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 * @param attachForm  
	 */
	@RenderMapping(params = "action=goToChangeMultiAsset")
	public String goToChangeMultiAsset(RenderRequest req, RenderResponse resp, @ModelAttribute(ChangeMgmtConstant.CHANGEASSETFORM)
	ManageAssetForm mAssetForm,@ModelAttribute ("attachmentForm") AttachmentForm attachForm, Model model) throws Exception	
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_GOTOCHANGEMULTIASSET);
		PortletSession session = req.getPortletSession();
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		
		//LBS
		String fleetManagementFlag = (String)req.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			req.setAttribute("fleetManagementFlag", "true");
			LOGGER.debug("Setting setFleetManagementFlag to true ");
			mAssetForm.setFleetManagementFlag(fleetManagementFlag);
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
			req.setAttribute("fleetManagementFlag", "false");
			LOGGER.debug("Setting setFleetManagementFlag to false ");
			mAssetForm.setFleetManagementFlag("false");
		}
		
		if(mAssetForm.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			mAssetForm.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		
	
		//end
		LOGGER.debug("attachmentForm desc = "+ mAssetForm.getAttachmentDescription());
		attachForm.setAttachmentDescription(mAssetForm.getAttachmentDescription());
		FileUploadForm fileUploadForm = new FileUploadForm();
		//changes for mps2.1
		if (attachmentList != null){
			attachForm.setAttachmentList(attachmentList);
		}
		LOGGER.debug("listOfFileTypes  = "+ listOfFileTypes + attMaxCount);
		attachForm.setListOfFileTypes(listOfFileTypes);
		attachForm.setAttMaxCount(attMaxCount);
		if(attachForm.getAttachmentList() !=null){
			fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
			}
			//changes for mps2.1
		model.addAttribute("attachmentForm", attachForm);
		model.addAttribute("fileUploadForm", fileUploadForm);
		
		model.addAttribute(ChangeMgmtConstant.CHANGEASSETFORM, mAssetForm);	
		String pageFlow=req.getParameter("pageFlow");
		String multiAssetList=req.getParameter("multiAssetList");
		model.addAttribute("multiAssetList", multiAssetList);
		model.addAttribute("isBackFromConfirm", req.getParameter("isBackFromConfirm"));
		
		LOGGER.debug("pageFlow"+pageFlow);
		if(pageFlow!=null)
		{
			model.addAttribute("pageFlow", pageFlow);
		}
		else{
		model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_CHANGE);
		}
		LOGGER.debug("pageFlow"+pageFlow);
		//model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_CHANGE);
		commonController.getContactInformation(req, resp);
		LOGGER.exit(this.getClass().getSimpleName(), METH_GOTOCHANGEMULTIASSET);
		return ChangeMgmtConstant.CHANGEMULTIASSETPATH;
	}

	/**
	 * Method responsible for back button in decommission asset review page 
	 * @param request 
	 * @param response 
	 * @param mAssetForm 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 * @param attachForm 
	 */
	@RenderMapping(params="action=goToDecomMultiAsset")
	public String goToDecomMultiAsset(RenderRequest request, RenderResponse response, @ModelAttribute(ChangeMgmtConstant.DECOMMASSETFORM)
	ManageAssetForm mAssetForm,@ModelAttribute ("attachmentForm") AttachmentForm attachForm,  Model model) throws Exception
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_GOTODECOMMULTIASSET);
		LOGGER.debug("Going back------------> ");
		PortletSession session = request.getPortletSession();
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		
		//LBS
		String fleetManagementFlag = (String)request.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			request.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
			request.setAttribute("fleetManagementFlag", "false");
		}
		
		//end
		if(mAssetForm.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			mAssetForm.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		FileUploadForm fileUploadForm = new FileUploadForm();
		//changes for mps2.1
		LOGGER.debug("attachmentForm desc = "+ mAssetForm.getAttachmentDescription());
		attachForm.setAttachmentDescription(mAssetForm.getAttachmentDescription());
		if (attachmentList != null){
			attachForm.setAttachmentList(attachmentList);
		}
		if(attachForm.getAttachmentList() !=null){
			fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
			}
		LOGGER.debug("listOfFileTypes  = "+ listOfFileTypes + attMaxCount);
		attachForm.setListOfFileTypes(listOfFileTypes);
		attachForm.setAttMaxCount(attMaxCount);
			//changes for mps2.1
		model.addAttribute("attachmentForm", attachForm);
		model.addAttribute("fileUploadForm", fileUploadForm);
		setModelParams(model,session);
		model.addAttribute(ChangeMgmtConstant.DECOMMASSETFORM, mAssetForm);
		model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_DECOMM);
		String multiAssetList=request.getParameter("multiAssetList");
		model.addAttribute("multiAssetList", multiAssetList);
		model.addAttribute("isBackFromConfirm", request.getParameter("isBackFromConfirm"));
		
		commonController.getContactInformation(request, response);
		LOGGER.exit(this.getClass().getSimpleName(), METH_GOTODECOMMULTIASSET);
		return ChangeMgmtConstant.DECOMMULTIASSETPATH;
	}
	
	private FutureTask<Attachment> createFutureTask(final Map<String, Asset> assetDetailsMap, final String csvFileNm, 
			final String multiSelectReqType) {
		
		LOGGER.enter(this.getClass().getSimpleName(), METH_CREATEFUTURETASK);
		FutureTask<Attachment> future=null;
		future = new FutureTask<Attachment>(
                new Callable<Attachment>()
                {
                    public Attachment call()
                    {
                        return writeCSV(assetDetailsMap, csvFileNm, multiSelectReqType);
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
	 * @param assetDetailsMap 
	 * @param requestType 
	 * @return String 
	 */
	private Attachment writeCSV(Map<String, Asset> assetDetailsMap, final String requestType, 
			final String multiSelectReqType)	
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_WRITECSV);
		String responseText=null;		
		Attachment responseAttach=AssetFileWriteUtility.getInstance().createCSVFile(assetDetailsMap, requestType.trim(), multiSelectReqType);
		LOGGER.exit(this.getClass().getSimpleName(), METH_WRITECSV);
		return responseAttach;
	}
	
	private CreateServiceRequestResult doWebServiceCall(ManageAssetForm masForm, ActionRequest request) throws LGSCheckedException, Exception
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_DOWEBSERVICECALL);
		LOGGER.debug("-------------- getting account information in do webservice call for assets --------------");
		PortletSession session = request.getPortletSession();
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		
		//printing the account information of the user
		if(accDetails!=null)
		{
			commonController.printAccountDetail(accDetails);			
		}
		else{
			LOGGER.debug("acc details map is null, so creating an empty map");
			accDetails=new HashMap<String, String>();//Go for creation of an empty map
		}
		//LBS
		String fleetManagementFlag = (String)request.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			request.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			request.setAttribute("fleetManagementFlag","false");
		}
		
		CreateServiceRequestContract serviceReqContract=commonController.getServiceReqContract(masForm,request);
		
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(serviceReqContract,LOGGER);
		LOGGER.info("end printing lex loggger");
		
		long timeBeforeCall=System.currentTimeMillis();
		final CreateServiceRequestResult result= manageAssetService.createCMRequestService(serviceReqContract,accDetails);
		
		LOGGER.info("start printing lex logger");
		LOGGER.logTime("** MPS PERFORMANCE TESTING WEBMETHODS CALL CREATE CM ASSET SR ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0 +" SR NUMBER IS "+result.getServiceRequestNumber());
		LOGGER.info("end printing lex loggger");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGEASSETS_MSG_CREATECMREQUESTSERVICE, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,serviceReqContract);
		LOGGER.debug("Service req no is "+result.getServiceRequestNumber());//This is the real sr no		
		LOGGER.exit(this.getClass().getSimpleName(), METH_DOWEBSERVICECALL);
		return result;
	}
	
	/**
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printMultiAsset")
	public String printMultiAsset() throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), METH_PRINTMULTIASSET);
		LOGGER.exit(this.getClass().getSimpleName(), METH_PRINTMULTIASSET);
		return ChangeMgmtConstant.PRINTMULTIASSETPATH;
	}
	
	/**
	 * @param request 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=multiAssetEmail")
	public String sendMultiAssetEmail(RenderRequest request, Model model) throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), METH_SENDMULTIASSETEMAIL);
		final String typeOfFlow = request.getParameter(ChangeMgmtConstant.TYPEOFREQ);
		model.addAttribute(ChangeMgmtConstant.TYPEOFREQ, typeOfFlow);
		LOGGER.debug("-------------typeOfFlow ---------"+typeOfFlow);
		LOGGER.exit(this.getClass().getSimpleName(), METH_SENDMULTIASSETEMAIL);
		return ChangeMgmtConstant.MULTIASSETEMAILPATH;
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
	
	private void setModelParams(Model model,PortletSession session){
		model.addAttribute("mdmId",PortalSessionUtil.getMdmId(session));
		model.addAttribute("mdmLevel",PortalSessionUtil.getMdmLevel(session));
		model.addAttribute("formPost",getLbsFormPost());
		model.addAttribute("emailAddress",PortalSessionUtil.getEmailAddress(session));
	}
	/**
	 * @return the lbsFormPost
	 */
	public String getLbsFormPost() {
		return lbsFormPost;
	}
	/**
	 * @param lbsFormPost the lbsFormPost to set
	 */
	public void setLbsFormPost(String lbsFormPost) {
		this.lbsFormPost = lbsFormPost;
	}
}
