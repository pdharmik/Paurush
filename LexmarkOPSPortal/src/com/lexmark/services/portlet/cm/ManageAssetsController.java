/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageAssetsController
 * Package     		: com.lexmark.services.portlet.cm
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Sourav						 		1.0             Initial Version
 *
 */
package com.lexmark.services.portlet.cm;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

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
import java.util.concurrent.FutureTask;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.lexmark.contract.LBSAssetListContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestDTO;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AssetResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.LBSFloorPlanListResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.LBSFloorPlanService;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.api.cm.ManageAssetService;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.ManageAssetForm;
import com.lexmark.services.form.validator.ManageAssetFormValidator;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.liferay.portal.util.PortalUtil;
import com.lexmark.services.portlet.common.ContactController;
import com.lexmark.service.api.AttachmentService;

/**
 * @author Wipro 
 * @version 2.1 
 *
 */

@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={ChangeMgmtConstant.ADDASSETFORM, ChangeMgmtConstant.CHANGEASSETFORM, ChangeMgmtConstant.DECOMMASSETFORM})
public class ManageAssetsController extends BaseController {
	
	@Autowired
	private ManageAssetService manageAssetService;	
	@Autowired
	private ManageAssetFormValidator assetFormValidator;
	@Autowired
	private CommonController commonController;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ContactController contactController;
	
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private OrderSuppliesAssetService amindOrderSuppliesAssetService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private LBSFloorPlanService lBSFloorPlanService;
	
	/**
	 * variable Declaration
	 */
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageAssetsController.class);
	
	private static Logger perfLogger = LogManager.getLogger("performance");
	/**
	 * variable Declaration
	 */
	private static final String METH_INITBINDER = "initbinder";
	/**
	 * variable Declaration
	 */
	private static final String METH_REDIRECTTOADDASSET = "redirectToAddAsset";
	/**
	 * variable Declaration
	 */
	private static final String METH_VIEWASSETLIST = "viewAssetList";
	/**
	 * variable Declaration
	 */
	private static final String METH_ADDASSET = "addAsset";
	/**
	 * variable Declaration
	 */
	private static final String METH_DOWEBSERVICECALL = "doWebServiceCall";
	/**
	 * variable Declaration
	 */
	private static final String METH_CHANGEASSET = "changeAsset";
	/**
	 * variable Declaration
	 */
	private static final String METH_SHOWASSETSUMMARY = "showAssetSummary";
	/**
	 * variable Declaration
	 */
	private static final String METH_ADDASSETCONFIRMATN = "showAddAssetConfirmation";
	/**
	 * variable Declaration
	 */
	private static final String METH_CHANGEASSETCONFIRMATN = "showChangeAssetConfirmation";
	/**
	 * variable Declaration
	 */
	private static final String METH_DECOMMASSETCONFIRMATN = "showDecommissionAssetConfirmation";
	/**
	 * variable Declaration
	 */
	private static final String METH_REDIRECTTOCHANGEDECOM = "redirectToChangeDecom";
	/**
	 * variable Declaration
	 */
	private static final String METH_DECOMMISSIONASSET = "decommissionAsset";
	/**
	 * variable Declaration
	 */
	private static final String METH_GETRESOURCEURL = "getResourceURL";
	/**
	 * variable Declaration
	 */
	private static final String METH_CHECKFORMDATA = "checkFormData";
	/**
	 * variable Declaration
	 */
	private static final String METH_GOTOADDASSET = "goToAddAsset";
	/**
	 * variable Declaration
	 */
	private static final String METH_GOTOCHANGEASSET = "goToChangeAsset";
	/**
	 * variable Declaration
	 */
	private static final String METH_GOTODECOMASSET = "goToDecomAsset";
	/**
	 * variable Declaration
	 */
	private static final String METH_PRINTASSET = "printAsset";
	/**
	 * variable Declaration
	 */
	private static final String METH_SENDEMAIL = "sendEmail";
	
	/**
	 * listOfFileTypes
	 */
	private String listOfFileTypes;
	/**
	 * attMaxCount
	 */
	private String attMaxCount;
	private String lbsFormPost;
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
	 * @return lbsFormPost 
	 */
	public String getLbsFormPost() {
		LOGGER.debug("formPost=> "+lbsFormPost);
		return lbsFormPost;
	}
	
	/**
	 * @param lbsFormPost 
	 */
	public void setLbsFormPost(String lbsFormPost) {
		this.lbsFormPost = lbsFormPost;
	}

	
	/**
	 * This method is used to bind the validator for form beans, 
	 * Also custom date editor for LTPC fields are registered here 
	 * @param binder 
	 * @param locale 
	 */
	@InitBinder(value={ChangeMgmtConstant.ADDASSETFORM, ChangeMgmtConstant.CHANGEASSETFORM, ChangeMgmtConstant.DECOMMASSETFORM, "fileUploadForm"})
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
	 * Method for forwarding to the add asset page
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param selectedVal 
	 * @param modelMap 
	 * @return String 
	 * @throws Exception 
	 */
	
	@RenderMapping(params="action=redirectToAddAsset")
	public String redirectToAddAsset(Model model, RenderRequest request,
			RenderResponse response, @RequestParam(ChangeMgmtConstant.SELECTEDVALUE) String selectedVal,  
			ModelMap modelMap) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), METH_REDIRECTTOADDASSET);
		PortletSession session = request.getPortletSession();
		session.removeAttribute("attachmentList");
		session.removeAttribute("attachmentForm");
		AttachmentForm attachForm = new AttachmentForm();
		LOGGER.debug("inside add asset prev srno " + request.getParameter(ChangeMgmtConstant.PREVSRNO) 
				+"update flag " + request.getParameter(ChangeMgmtConstant.ISUPDATEFLAG));		
		AccountContact accContact;
		Boolean isUpdateFlag=false;
		AssetResult assetResult=null;		
		AssetContract contract =null;
		String assetId = (String)request.getAttribute("assetId");
		String backToMap=(String)request.getAttribute("backJSON");
		String placementId=(String)request.getAttribute("placementId");
		LOGGER.debug("back json= " +backToMap);
		
		if(assetId!=null && !assetId.isEmpty())
		{
			LOGGER.info("Asset Id is " + assetId);			
			contract= commonController.getAssetContract(assetId, request);
			contract.setPageName(ChangeMgmtConstant.DEVICE_DETAIL);
			assetResult=commonController.retrieveAssetDetail(contract);
		}
		
		setLbsFormPost(lbsFormPost);
		setModelParams(model, session);
	
		if(!StringUtil.isEmpty(request.getParameter(ChangeMgmtConstant.ISUPDATEFLAG)))
		{	
			isUpdateFlag=Boolean.parseBoolean(request.getParameter(ChangeMgmtConstant.ISUPDATEFLAG));
		}		
		if(!StringUtil.isStringEmpty(selectedVal) && (selectedVal.equalsIgnoreCase(ChangeMgmtConstant.ADDONE)))
		{
				ManageAssetForm masForm=new ManageAssetForm();
				String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
				if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
					LOGGER.debug("Setting Account information from asset details call ");
					Map<String,String> accDetails=new HashMap<String, String>();
					accDetails.put(ChangeMgmtConstant.ACCOUNTNAME,"");
					accDetails.put(ChangeMgmtConstant.ACCOUNTID,(String)request.getAttribute("account.accountId"));
					session.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accDetails ,PortletSession.APPLICATION_SCOPE);
					
				}

				ServiceRequest serviceReq=new ServiceRequest();
				masForm.setServiceRequest(serviceReq);
				masForm.setBackToMap(StringUtils.isNotBlank(backToMap)==true?backToMap:"");
				if(!StringUtil.isEmpty(request.getParameter(ChangeMgmtConstant.PREVSRNO)) && isUpdateFlag)
				{
					masForm.setPrevSrNo(request.getParameter(ChangeMgmtConstant.PREVSRNO));
					masForm.setUpdateSrFlag(isUpdateFlag);
					
				}
				
				try{
									
					accContact=commonController.getContactInformation(request, response);
				}catch(LGSRuntimeException ex)
				{
					LOGGER.error("error is " + ex.getMessage());
					request.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);					
					return ChangeMgmtConstant.ASSETLISTPATH;
				}
				masForm.getServiceRequest().setPrimaryContact(accContact);
				
				model.addAttribute("pageFlow",ChangeMgmtConstant.ADDONE);
				//If it is draft SR, need to add attachment to the page
				List<Attachment> attachmentList = new ArrayList<Attachment>();
				attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
				attachForm.setListOfFileTypes(listOfFileTypes);
				attachForm.setAttMaxCount(attMaxCount);
				List<Attachment> modifiedAttachmentList = new ArrayList<Attachment>();
				if(attachmentList!=null){
					for(Attachment attachment:attachmentList){
						Attachment modifiedAttachment = new Attachment();
						String displayAttachment = "";
						
						modifiedAttachment.setAttachmentName(attachment.getAttachmentName());
						modifiedAttachment.setActivityId(attachment.getActivityId());
						modifiedAttachment.setExtension(attachment.getExtension());
						modifiedAttachment.setSize(attachment.getSize());
						modifiedAttachment.setStatus(attachment.getStatus());
						modifiedAttachment.setVisibility(attachment.getVisibility());
						modifiedAttachment.setCompletedOn(attachment.getCompletedOn());
						modifiedAttachment.setId(attachment.getId());
						double fileSizeDisplay=attachment.getSize();
						LOGGER.debug("File Size is " + fileSizeDisplay);
						fileSizeDisplay=fileSizeDisplay/1024;
						BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
						roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
						
						modifiedAttachment.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
						//start doing the manipulation for display name
						String attachName = attachment.getAttachmentName();
						String fileNameWithTimestamp = attachName.substring(attachName.indexOf('@')+1, attachName.length());
						LOGGER.debug("fileNameWithTimestamp is "+fileNameWithTimestamp);
						displayAttachment = fileNameWithTimestamp.substring(0,fileNameWithTimestamp.lastIndexOf('_'));
						displayAttachment = displayAttachment+"."+attachment.getExtension();
						LOGGER.debug("Final displayFileName is "+displayAttachment);
						//end completing the manipulation for display name
						modifiedAttachment.setDisplayAttachmentName(displayAttachment);
						modifiedAttachmentList.add(modifiedAttachment);
					}
				}
				LOGGER.debug("Setting LBS Flag to false");
				model.addAttribute("fleetManagementFlag", "false");
				masForm.setFleetManagementFlag("false");
				Asset asset = new Asset();
				
				//Added for placement
				asset.setPlacementId(placementId==null?"":placementId);
				GenericAddress shippedAddress= new GenericAddress();
				
				//For LBS
				if (assetResult != null && assetResult.getAsset() != null) {
				    asset = assetResult.getAsset();
				    shippedAddress=asset.getInstallAddress();
					
					/*String isFleetManagement=(String)request.getAttribute("fleetManagementFlag");
					if(StringUtils.isNotBlank(isFleetManagement) && "true".equalsIgnoreCase(isFleetManagement)){
						GenericAddress shippedAddress=asset.getInstallAddress();
						GenericAddress moveToAddress=(GenericAddress)request.getAttribute("installAddress");
						
						shippedAddress.setBuildingId(moveToAddress.getBuildingId());
						shippedAddress.setPhysicalLocation1(moveToAddress.getPhysicalLocation1());
						
						shippedAddress.setFloorId(moveToAddress.getFloorId());
						shippedAddress.setPhysicalLocation2(moveToAddress.getPhysicalLocation2());
						
						shippedAddress.setZoneId(moveToAddress.getZoneId());
						shippedAddress.setZoneName(moveToAddress.getZoneName());
						
						shippedAddress.setLbsAddressFlag(StringUtils.isNotBlank(moveToAddress.getAddressId())==true?true:false);
						
					}/*
					
					
					/* This portion has been added for Asset Image */
					ProductImageContract productImageContract = new ProductImageContract();
					productImageContract.setPartNumber(asset.getProductTLI());
					
					long timeBeforeServiceCall=System.currentTimeMillis();
					ProductImageResult productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
					LOGGER.info("start printing lex logger");
					
					LOGGER.info("end printing lex loggger");
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGEASSETS_MSG_RETRIEVEPRODUCTIMAGEURL, timeBeforeServiceCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_PORTALIMAGEDB,productImageContract);
					asset.setProductImageURL(productImageResult.getProductImageUrl());
					commonController.assembleDevice(asset, request.getLocale());
				
					asset.getAssetContact().setWorkPhone(asset.getAssetContact().getWorkPhone().replace(" ", "").replace("\n", "")); 
					masForm.setPrimarySiteContact(asset.getAssetContact());//This is done bcoz primary site contact is same as asset contact					
					masForm.setAssetDetail(asset);
					
					//LBS
					
					if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
						LOGGER.debug("Setting Account information from asset details call ");
						Map<String,String> accDetails=new HashMap<String, String>();
						accDetails.put(ChangeMgmtConstant.ACCOUNTNAME,asset.getAccount().getAccountName());
						accDetails.put(ChangeMgmtConstant.ACCOUNTID,asset.getAccount().getAccountId());
						session.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accDetails ,PortletSession.APPLICATION_SCOPE);
						
						LOGGER.debug("Setting LBS Flag to true ");
						model.addAttribute("fleetManagementFlag", "true");
						masForm.setFleetManagementFlag(fleetManagementFlag);
						
						
						
					}
				}
				//ends here	
				String isFleetManagement=(String)request.getAttribute("fleetManagementFlag");
				if(StringUtils.isNotBlank(isFleetManagement) && "true".equalsIgnoreCase(isFleetManagement)){
					GenericAddress moveToAddress=(GenericAddress)request.getAttribute("installAddress");
					if(asset.getAssetId()!=null){
						shippedAddress=moveToAddress;
						shippedAddress.setBuildingId(moveToAddress.getBuildingId());
						shippedAddress.setPhysicalLocation1(moveToAddress.getPhysicalLocation1());
						
						shippedAddress.setFloorId(moveToAddress.getFloorId());
						shippedAddress.setPhysicalLocation2(moveToAddress.getPhysicalLocation2());
						
						shippedAddress.setZoneId(moveToAddress.getZoneId());
						shippedAddress.setZoneName(moveToAddress.getZoneName());

						shippedAddress.setPhysicalLocation3(moveToAddress.getPhysicalLocation3());
						
						shippedAddress.setLbsAddressFlag(StringUtils.isNotBlank(moveToAddress.getAddressId())==true?true:false);
						asset.setInstallAddress(shippedAddress);
					}
					else{
						moveToAddress.setLbsAddressFlag(StringUtils.isNotBlank(moveToAddress.getAddressId())==true?true:false);
						asset.setInstallAddress(moveToAddress);
					}
					
					LOGGER.debug("Setting LBS Flag to true ");
					model.addAttribute("fleetManagementFlag", "true");
					masForm.setFleetManagementFlag(isFleetManagement);
					
					masForm.setAssetDetail(asset);
					
				}
				
				
				attachForm.setAttachmentList(modifiedAttachmentList);
				modelMap.addAttribute(ChangeMgmtConstant.ADDASSETFORM, masForm);
				//Initializes the form for file upload
				request.setAttribute("attachmentForm",attachForm);
				model.addAttribute("attachmentForm",attachForm);
				FileUploadForm fileUploadForm = new FileUploadForm();
				model.addAttribute("fileUploadForm", fileUploadForm);
		}
		//added for page counts start
		Map<String, String> pageCountsMap=null;
		List<String> pageCountsList=new ArrayList<String>();
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), request.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch" + e.getMessage());
		}
		Iterator itmap = pageCountsMap.entrySet().iterator();
		while (itmap.hasNext()) {
			Map.Entry valuemap = (Map.Entry)itmap.next();
			pageCountsList.add(valuemap.getValue().toString());
		}
		model.addAttribute("pageCountsList", pageCountsMap);
	
		//end
		
		//added for Device Contact start
		Map<String, String> deviceContactTypeMap=null;
		List<String> deviceContactTypeList=new ArrayList<String>();
		try {
			deviceContactTypeMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONTACT_TYPE_FOR_ASSET.getValue(), request.getLocale());
			LOGGER.debug("deviceContactTypeMap = "+ deviceContactTypeMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage() );
		}
		Iterator itContactmap = deviceContactTypeMap.entrySet().iterator();
		while (itContactmap.hasNext()) {
			Map.Entry valuemapContact = (Map.Entry)itContactmap.next();
			deviceContactTypeList.add(valuemapContact.getValue().toString());
		}
		model.addAttribute("deviceContactTypeList", deviceContactTypeMap);
	
		//end
		
		
		model.addAttribute("placementId", placementId);
		LOGGER.exit(this.getClass().getSimpleName(), METH_REDIRECTTOADDASSET);
		//If no error, go for the add asset page		
		return ChangeMgmtConstant.ADDASSETPATH;
	}

	/**
	 * Default Request Mapped method- redirects to Asset List 
	 * Grid settings retrieved from Database 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String  
	 */
	@RequestMapping
	public String viewAssetList(Model model, RenderRequest request,
			RenderResponse response){
		LOGGER.enter(this.getClass().getSimpleName(), METH_VIEWASSETLIST);
		Boolean isUpdateFlag=false;
		try{
			getResourceURL(response, model);//This is for the device list population
		}catch(LGSCheckedException ex)
		{
			logStackTrace(ex);
			request.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
			return ChangeMgmtConstant.ALLREQHISTPAGENM;
		}
		//This is for the update of sr
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		final String prevSRNoForUpdate = httpReq.getParameter(ChangeMgmtConstant.SERVICEREQNO);		
		if(!StringUtil.isEmpty(httpReq.getParameter(ChangeMgmtConstant.UPDATEFLAG)))
		{
			isUpdateFlag= Boolean.parseBoolean(httpReq.getParameter(ChangeMgmtConstant.UPDATEFLAG));
			LOGGER.debug("Update flag is " + isUpdateFlag);			
			request.setAttribute(ChangeMgmtConstant.ISUPDATEFLAG, isUpdateFlag);
		}
		
		//This is for the update request srno
		if(!StringUtil.isEmpty(prevSRNoForUpdate))
		{
			LOGGER.info("Service req no is " + prevSRNoForUpdate);
			request.setAttribute(ChangeMgmtConstant.SERVICEREQNO, prevSRNoForUpdate);
		}		
		try{			
			retrieveGridSetting("assetListGrid",request,response,model);//Loading data from db
		}catch(Exception ex)
		{
			logStackTrace(ex);
			request.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);			
			return ChangeMgmtConstant.ALLREQHISTPAGENM;
		}
		
			
		LOGGER.exit(this.getClass().getSimpleName(), METH_VIEWASSETLIST);
		return ChangeMgmtConstant.ASSETLISTPATH;		
	}	

	/**
	 * This method is responsible for invoking service calls for adding an asset  
	 * on success redirects to Summary Page 
	 * @param actRequest 
	 * @param actResponse 
	 * @param masForm 
	 * @param attachForm 
	 * @param model 
	 * @param sessionStatus  
	 * @throws Exception 
	 */
	@ActionMapping(params = "action=addAsset")
	public void addAsset(ActionRequest actRequest, ActionResponse actResponse,
			@ModelAttribute(ChangeMgmtConstant.ADDASSETFORM) ManageAssetForm masForm,@ModelAttribute ("attachmentForm") AttachmentForm attachForm, Model model, SessionStatus sessionStatus) throws Exception
		{
		LOGGER.enter(this.getClass().getSimpleName(), METH_ADDASSET);
		CreateServiceRequestResult result=null;
		//LBS
		String fleetManagementFlag = (String)actRequest.getParameter("fleetManagementFlag");
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
		
		commonController.assembleDevice(masForm.getAssetDetail(), actRequest.getLocale());/*Done for Defect 3924-Jan Release*/
		//added for page counts
		Map<String, String> pageCountsMap=null;
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), actRequest.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		String pageCountsString = getXmlOutputGenerator(actRequest
				.getLocale()).pageCountsXML(masForm.getAssetDetail().getPageCounts(),masForm.getAssetDetail().getPageCounts().size(),0,pageCountsMap);
		pageCountsString = pageCountsString.replace("\n", "");
		model.addAttribute("pageCountsString", pageCountsString);
		//page counts end
		PortletSession session = actRequest.getPortletSession();
		model.addAttribute("pageFlow",ChangeMgmtConstant.ADDONE);
		//After the service call, forward to the manage asset summary page	
		AttachmentContract contract = new AttachmentContract();
		List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
		List <Attachment> attachmentList = new ArrayList<Attachment>();
		attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		if(attachmentList!=null && !attachmentList.isEmpty()){
			List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
			for(int i=0;i<attachmentList.size();i++){
				AttachmentFile attachmentFile = new AttachmentFile();
				attachmentFile.setFileName(attachmentList.get(i).getDisplayAttachmentName());
				attachmentFile.setFileSize(attachmentList.get(i).getSize());
				displayAttachmentList.add(attachmentFile);
				if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
					LOGGER.debug("Attachment id is either null or blank");
					createSRAttachmentList.add(attachmentList.get(i));
				}
			}
			LOGGER.debug("No of attachment in the reviewAssetOrderForm "+attachmentList.size());
			
			model.addAttribute("displayAttachmentList", displayAttachmentList);
			attachForm.setDisplayAttachmentList(displayAttachmentList);
		}
		/****Changed the logic for area and subarea *********/
		
		if(masForm.getInstallAssetFlag()!=null && masForm.getInstallAssetFlag().equalsIgnoreCase(ChangeMgmtConstant.YES))
		{
			LOGGER.debug("User choose yes");
			if(masForm.getAssetDetail().getPlacementId()!=null && !"".equalsIgnoreCase(masForm.getAssetDetail().getPlacementId()) && !"".equalsIgnoreCase(masForm.getAssetDetail().getSerialNumber()) && !"".equalsIgnoreCase(masForm.getAssetDetail().getProductLine())){
				Map<String,String> accDetails=(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
				LBSAssetListContract LBSAssetListContract = ContractFactory.getLBSPlacementContract(masForm);
				CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(actRequest));
				LBSAssetListContract.setSessionHandle(crmSessionHandle);
				if(accDetails != null){
				LBSAssetListContract.setAccountId(accDetails.get(ChangeMgmtConstant.ACCOUNTID));
				}
				ObjectDebugUtil.printObjectContent(LBSAssetListContract,LOGGER);
				LOGGER.info("end printing lex loggger");
				LBSFloorPlanListResult res=null;

				try{
					res= lBSFloorPlanService.retrieveLBSFloorPlanAssetList(LBSAssetListContract);
				}catch(Exception e){
					e.printStackTrace();
				}
				if(res != null && res.getAssetList().size() > 0){
				String assetLifecycle=res.getAssetList().get(0).getAssetLifeCycle();
				LOGGER.debug("assetLifecycle-------------->>"+assetLifecycle);
				if("installed".equalsIgnoreCase(assetLifecycle)){
					masForm.getAssetDetail().setPlacementMove(true);
					masForm.setArea(ChangeMgmtConstant.AREA_CHANGEASSET_YES);		
					masForm.setSubArea(ChangeMgmtConstant.SUBAREA_CHANGEASSET_YES);	
				}else{
					masForm.setArea(ChangeMgmtConstant.AREA_ADDASSET_YES);		
					masForm.setSubArea(ChangeMgmtConstant.SUBAREA_ADDASSET_YES);
				}
				}else{					
					masForm.setArea(ChangeMgmtConstant.AREA_ADDASSET_YES);		
					masForm.setSubArea(ChangeMgmtConstant.SUBAREA_ADDASSET_YES);
				
				}
				}else{			
					masForm.setArea(ChangeMgmtConstant.AREA_ADDASSET_YES);		
					masForm.setSubArea(ChangeMgmtConstant.SUBAREA_ADDASSET_YES);
				}
				}
		else{
			LOGGER.debug("User did not choose");
			masForm.setArea(ChangeMgmtConstant.AREA_ADDASSET_NO);		
			masForm.setSubArea(ChangeMgmtConstant.SUBAREA_ADDASSET_NO);		
		}
		
		
		
	
		
		/****************This section has been added to prevent resubmit by browser refresh ***********/
		if (PortalSessionUtil.isDuplicatedSubmit(actRequest,masForm)) {
			LOGGER.debug("ManageAssetsController duplicated submit, do nothing!");		
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.serviceRequest.duplicateSubmission", null, actRequest.getLocale()));
			
			actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_ADD);
			actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ASSETSUMMARY);
			
			model.addAttribute(masForm);
		}
		/**************** Section end to prevent resubmit by browser refresh ***********/
		else{
			
		try{
			try{
				result=doWebServiceCall(masForm,actRequest);
				model.addAttribute(ChangeMgmtConstant.MODELATTRSRNO, result.getServiceRequestNumber());//To be displayed on summary page
				

			}catch(LGSRuntimeException ex)
			{	
				logStackTrace(ex);
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ADDASSETCONFPGNM);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
			}
			catch(LGSBusinessException ex)
			{
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ADDASSETCONFPGNM);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
				logStackTrace(ex);
			}
			try{
				if(createSRAttachmentList!=null && !createSRAttachmentList.isEmpty()){
					for(int i=0;i<createSRAttachmentList.size();i++){
						LOGGER.debug("Create SR will be called for following attachments "+createSRAttachmentList.get(i).getAttachmentName());
					}
					contract.setAttachments(createSRAttachmentList);
					contract.setRequestType("Service Request");
					contract.setIdentifier(result.getServiceRequestRowId());
					long timeBeforeCall=System.currentTimeMillis();
					attachmentService.uploadAttachments(contract);
					LOGGER.info("start printing lex logger");
					LOGGER.logTime("** MPS PERFORMANCE UPLOAD ATTACHMENTS ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
					LOGGER.info("end printing lex loggger");
					LOGGER.debug("After calling upload attachment");
				}
			}catch (Exception e) {
				LOGGER.debug("exception");
				List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
				model.addAttribute("displayAttachmentList", displayAttachmentList);
				attachForm.setDisplayAttachmentList(displayAttachmentList);
				LOGGER.debug("exception in add asset attachment");
				model.addAttribute("attachmentException", "attachfailed");
			}
			//This should happen after the web service call
			LOGGER.debug("After the web service call");
			
			try
			{
				
					if(result.getServiceRequestRowId()!=null)
					{
											
						//No error with rename, go for the summary page
						sessionStatus.setComplete();
						//After the service call, forward to the manage asset summary page						
						actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_ADD);
						actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ASSETSUMMARY);
						
						//this is to enable re-submit of SR form on submit/draft exception
						
						Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, PortletSession.PORTLET_SCOPE);						
						masForm.setSubmitToken(tokenInSession);
						model.addAttribute(masForm);
					}
				//If any error with CSV, it would be wrapped in ExecutionException
			}
			catch (Exception exceptn)//This block would be executed if the upload runnable thread fails
			{
				//if any error do not show the summary page
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ADDASSETCONFPGNM);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
				logStackTrace(exceptn);
			}
		
			
			actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_ADD);
			actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ASSETSUMMARY);
			}catch(Exception ex)
		{
			//If any error do not show the summary page
			actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ADDASSETCONFPGNM);
			actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
			LOGGER.error("Inside catch block add asset "+ex.getMessage());			
			logStackTrace(ex);
		}
		
	  }
		LOGGER.exit(this.getClass().getSimpleName(), METH_ADDASSET);
	}

	/**
	 * Common Method for Add, Change and Decommission Asset to invoke the Webservice call 
	 * @param masForm 
	 * @param request 
	 * @return CreateServiceRequestResult 
	 * @throws Exception 
	 * @throws LGSCheckedException  
	 * @throws Exception 
	 */
	
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
	@RequestMapping(params = "action=changeAsset")
	public void changeAsset(ActionRequest actRequest,
			ActionResponse actResponse, @ModelAttribute(ChangeMgmtConstant.CHANGEASSETFORM) ManageAssetForm masFormForChange,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,Model model, SessionStatus sessionStatus) throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), METH_CHANGEASSET);
		CreateServiceRequestResult result=null;
		//FutureTask<String> future=null;
		//LBS
		String fleetManagementFlag = (String)actRequest.getParameter("fleetManagementFlag");
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
		
		//added for page counts
		Map<String, String> pageCountsMap=null;
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), actRequest.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		String pageCountsString = getXmlOutputGenerator(actRequest
				.getLocale()).pageCountsXML(masFormForChange.getAssetDetail().getPageCounts(),masFormForChange.getAssetDetail().getPageCounts().size(),0,pageCountsMap);
		pageCountsString = pageCountsString.replace("\n", "");
		model.addAttribute("pageCountsString", pageCountsString);
		//end
		AttachmentContract contract = new AttachmentContract();
		List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
		List <Attachment> attachmentList = new ArrayList<Attachment>();
		PortletSession session = actRequest.getPortletSession();
		attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		if(attachmentList!=null && !attachmentList.isEmpty()){
			List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
			for(int i=0;i<attachmentList.size();i++){
				AttachmentFile attachmentFile = new AttachmentFile();
				attachmentFile.setFileName(attachmentList.get(i).getDisplayAttachmentName());
				attachmentFile.setFileSize(attachmentList.get(i).getSize());
				displayAttachmentList.add(attachmentFile);
				if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
					LOGGER.debug("Attachment id is either null or blank");
					createSRAttachmentList.add(attachmentList.get(i));
				}
			}
			LOGGER.debug("No of attachment in the reviewAssetOrderForm "+attachmentList.size());
			model.addAttribute("displayAttachmentList", displayAttachmentList);
			attachForm.setDisplayAttachmentList(displayAttachmentList);
		}
		//Added for CI BRD 13-10-03--STARTS
		String moveType = masFormForChange.getMoveType();
		String installAssetFlag = masFormForChange.getInstallAssetFlag();
		boolean projectFlag= masFormForChange.getServiceRequest().isProject();
		LOGGER.debug("projectFlag::"+projectFlag);
		String pageFlow=(String)actRequest.getParameter("pageFlow");
		if(("No").equals(installAssetFlag))
		{
				LOGGER.debug("fleetmanagemove false");
			masFormForChange.setArea(ChangeMgmtConstant.AREA_CHANGEASSET_NO);		
			masFormForChange.setSubArea(ChangeMgmtConstant.SUBAREA_CHANGEASSET_NO);
		
			
		}
		else if(("Yes").equals(installAssetFlag))
		{
			masFormForChange.setArea(ChangeMgmtConstant.AREA_CHANGEASSET_YES);		
			masFormForChange.setSubArea(ChangeMgmtConstant.SUBAREA_CHANGEASSET_YES);
			}
		//Added for CI BRD 13-10-03--ENDS
		commonController.assembleDevice(masFormForChange.getAssetDetail(), actRequest.getLocale());/*Done for Defect 3924-Jan Release*/
		
		/****************This section has been added to prevent resubmit by browser refresh ***********/
		if (PortalSessionUtil.isDuplicatedSubmit(actRequest,masFormForChange)) {
			LOGGER.debug("ManageAssetsController duplicated submit, do nothing!");		
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.serviceRequest.duplicateSubmission", null, actRequest.getLocale()));
			
			model.addAttribute(masFormForChange);
			
			actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_CHANGE);
			actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ASSETSUMMARY);
		}
		/**************** Section end to prevent resubmit by browser refresh ***********/
		else{
		//Go for the dto fill up and csv creation (future task)
		try{
			
			try{
				
			result=doWebServiceCall(masFormForChange,actRequest);
			//To be displayed on summary page
			model.addAttribute(ChangeMgmtConstant.MODELATTRSRNO, result.getServiceRequestNumber());
			//model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_CHANGE);
			model.addAttribute("pageFlow",pageFlow);
			LOGGER.debug("pageflow"+pageFlow);
			
			}catch(LGSRuntimeException ex)			{
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
			
			
			try{
				if(createSRAttachmentList!=null && !createSRAttachmentList.isEmpty()){
					for(int i=0;i<createSRAttachmentList.size();i++){
						LOGGER.debug("Create SR will be called for following attachments "+createSRAttachmentList.get(i).getAttachmentName());
					}
					contract.setAttachments(createSRAttachmentList);
					contract.setRequestType("Service Request");
					contract.setIdentifier(result.getServiceRequestRowId());
					long timeBeforeCall=System.currentTimeMillis();
					attachmentService.uploadAttachments(contract);
					LOGGER.info("start printing lex logger");
					LOGGER.logTime("** MPS PERFORMANCE UPLOAD ATTACHMENTS ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
					LOGGER.info("end printing lex loggger");
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
					LOGGER.debug("After calling upload attachment");
				}
			}catch (Exception e) {
				LOGGER.debug("exception");
				List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
				model.addAttribute("displayAttachmentList", displayAttachmentList);
				attachForm.setDisplayAttachmentList(displayAttachmentList);
				LOGGER.debug("exception in change asset attachment");
				model.addAttribute("attachmentException", "attachfailed");
			}
			
			try
		        {
			
			
				if(result.getServiceRequestRowId().length()>0)
				{
					//No error with rename, go for the summary page
					sessionStatus.setComplete();					
					//After the service call, forward to the manage asset summary page					
					actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_CHANGE);
					actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ASSETSUMMARY);
					
					//this is to enable re-submit of SR form on submit/draft exception
					
					Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, PortletSession.PORTLET_SCOPE);						
					masFormForChange.setSubmitToken(tokenInSession);
					model.addAttribute(masFormForChange);
					
				}
			
			//This would be executed if any error in CSV Creation
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
	 * @param model 
	 * @return String 
	 * @param srNo  
	 * @throws LGSDBException 
	 */
	@RenderMapping(params = "action=assetSummary")
	public String showAssetSummary(RenderRequest req, Model model, @ModelAttribute(ChangeMgmtConstant.MODELATTRSRNO) String srNo) throws LGSDBException {
		LOGGER.enter(this.getClass().getSimpleName(), METH_SHOWASSETSUMMARY);
		//Forward to the manage asset summary page
		LOGGER.debug("SR No is " + srNo);
		//LBS
		String fleetManagementFlag = (String)req.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			model.addAttribute("fleetManagementFlag","false");
		}
		
		Map<String, String> deviceContactTypeMap=null;
		try{
			deviceContactTypeMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONTACT_TYPE_FOR_ASSET.getValue(), req.getLocale());
			LOGGER.debug("deviceContactTypeMap = "+ deviceContactTypeMap);
		}catch (Exception e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		model.addAttribute("deviceContactTypeList", deviceContactTypeMap);
		model.addAttribute(ChangeMgmtConstant.SRNUMBER,srNo);
		LOGGER.exit(this.getClass().getSimpleName(), METH_SHOWASSETSUMMARY);
		return ChangeMgmtConstant.ASSETSUMMARYPATH;
	}

	/**
	 * This method forwards the request to add asset confirmation page 
	 * @param renderRequest 
	 * @param renderResponse 
	 * @param manageAssetForm 
	 * @param result 
	 * @param model 
	 * @param modelMap 
	 * @return String 
	 * @param attachForm 
	 * @throws Exception 
	 * @throws LGSDBException 
	 * 
	 */
	@RenderMapping(params = "action=addAssetConfirmation")
	public String showAddAssetConfirmation(
			RenderRequest renderRequest,
			RenderResponse renderResponse,
			@ModelAttribute(ChangeMgmtConstant.ADDASSETFORM) @Valid ManageAssetForm manageAssetForm,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,
			BindingResult result, Model model, ModelMap modelMap) throws Exception, LGSDBException {
		LOGGER.enter(this.getClass().getSimpleName(),METH_ADDASSETCONFIRMATN);
		PortletSession session = renderRequest.getPortletSession();
		commonController.assembleDevice(manageAssetForm.getAssetDetail(), renderRequest.getLocale());/*Done for Defect 3924-Jan Release*/
		modelMap.addAttribute(ChangeMgmtConstant.ADDASSETFORM, manageAssetForm);
		LOGGER.debug("request param is " + renderRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		String timezoneOffset=renderRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		//LBS
		String fleetManagementFlag = (String)renderRequest.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			renderRequest.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
			renderRequest.setAttribute("fleetManagementFlag", "true");
		}
		
		//added for page counts 		
		String pageCountType= renderRequest.getParameter("pageCountTypeID");
		String pageCountsDate= renderRequest.getParameter("pageCountsDateID");
		String pageCountValue= renderRequest.getParameter("pageCountValueID");
		String attachmentDescription= renderRequest.getParameter("attachmentDescriptionID");
		List<String> pageCountTypeList=Arrays.asList(pageCountType.split(",",-1));
		List<String> pageCountsDateList=Arrays.asList(pageCountsDate.split(",",-1));
		List<String> pageCountValueList=Arrays.asList(pageCountValue.split(",",-1));
		List<PageCounts> pageCounts = new ArrayList<PageCounts>();
		for(int i=0;i<pageCountTypeList.size();i++){
			PageCounts pc= new PageCounts();
			
			pc.setName(pageCountTypeList.get(i));
			pc.setDate(pageCountsDateList.get(i));
			pc.setCount(pageCountValueList.get(i));
			if(!(i==0 && ("").equals(pageCountTypeList.get(i).trim()) && ("").equals(pageCountsDateList.get(i).trim()) && ("").equals(pageCountValueList.get(i).trim())) ){
				pageCounts.add(pc);
			}
		}
		manageAssetForm.getAssetDetail().setPageCounts(pageCounts);
		
		Map<String, String> pageCountsMap=null;
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), renderRequest.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		String pageCountsString = getXmlOutputGenerator(renderRequest
					.getLocale()).pageCountsXML(manageAssetForm.getAssetDetail().getPageCounts(),manageAssetForm.getAssetDetail().getPageCounts().size(),0,pageCountsMap);
			pageCountsString = pageCountsString.replace("\n", "");	
		
		
		LOGGER.debug("pageCountsString" + pageCountsString);
		//added for page counts end 
		if(manageAssetForm.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			manageAssetForm.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		
		
		List<AccountContact> deviceContactDetailList = contactController.getDeviceContactInfo(renderRequest, renderResponse,model);
		manageAssetForm.getAssetDetail().setDeviceContact(deviceContactDetailList);
		
		/* added for Device Contact Info end */
		
		
			manageAssetForm.getAssetDetail().setNotes(attachmentDescription);
			attachForm.setAttachmentDescription(attachmentDescription);
		
		
		LOGGER.debug("request param is " + renderRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		if (attachmentList != null){
			attachForm.setAttachmentList(attachmentList);
		}
		
		FileUploadForm fileUploadForm = new FileUploadForm();
		//changes for mps2.1
		if(attachForm.getAttachmentList() !=null){
			fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
			}
			//changes for mps2.1
		model.addAttribute("attachmentForm", attachForm);
		model.addAttribute("fileUploadForm", fileUploadForm);
		model.addAttribute("pageCountsString", pageCountsString);
		model.addAttribute("pageFlow",ChangeMgmtConstant.ADDONE);
		if(!StringUtil.isEmpty(timezoneOffset))
		{
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET,timezoneOffset);
		}
		if (result.hasErrors()) {
			LOGGER.debug("Contains errors");			
			//If the binding result contains errors, show them in add asset jsp page itself.
			commonController.getContactInformation(renderRequest, renderResponse);
			LOGGER.exit(this.getClass().getSimpleName(),
					METH_ADDASSETCONFIRMATN);
			return ChangeMgmtConstant.ADDASSETPATH;
			
		} else
		{
			//Move to the asset confirmation page
			manageAssetForm.refreshSubmitToken(renderRequest);			
			LOGGER.exit(this.getClass().getSimpleName(),METH_ADDASSETCONFIRMATN);
			return ChangeMgmtConstant.ADDASSETCONFPATH;
		}
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
	@RenderMapping(params = "action=changeAssetConfirmation")
	public String showChangeAssetConfirmation(
			RenderRequest renderRequest,RenderResponse renderResponse,@ModelAttribute(ChangeMgmtConstant.CHANGEASSETFORM) ManageAssetForm manageAssetFormForChange,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,BindingResult result, Model model, ModelMap modelMap) throws Exception, LGSDBException 
			{	
		LOGGER.enter(this.getClass().getSimpleName(),METH_CHANGEASSETCONFIRMATN);
		LOGGER.debug("request param is " + renderRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		String timezoneOffset=renderRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		String pageFlow=renderRequest.getParameter("pageFlow");
		String attachmentDescription= renderRequest.getParameter("attachmentDescriptionID");
		PortletSession session = renderRequest.getPortletSession();
		//added for page counts 		
		String pageCountType= renderRequest.getParameter("pageCountTypeID");
		String pageCountsDate= renderRequest.getParameter("pageCountsDateID");
		String pageCountValue= renderRequest.getParameter("pageCountValueID");
		List<String> pageCountTypeList=Arrays.asList(pageCountType.split(",",-1));
		List<String> pageCountsDateList=Arrays.asList(pageCountsDate.split(",",-1));
		List<String> pageCountValueList=Arrays.asList(pageCountValue.split(",",-1));
		List<PageCounts> pageCounts = new ArrayList<PageCounts>();
		LOGGER.debug("pageCountTypeList.size()" + pageCountTypeList.size());
		for(int i=0;i<pageCountTypeList.size();i++){
			PageCounts pc= new PageCounts();
			pc.setName(pageCountTypeList.get(i));
			pc.setDate(pageCountsDateList.get(i));
			pc.setCount(pageCountValueList.get(i));
			if(!(i==0 && ("").equals(pageCountTypeList.get(i).trim()) && ("").equals(pageCountsDateList.get(i).trim()) && ("").equals(pageCountValueList.get(i).trim())) ){
				pageCounts.add(pc);
			}
			
		}
		manageAssetFormForChange.getAssetDetail().setPageCounts(pageCounts);
		
		Map<String, String> pageCountsMap=null;
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), renderRequest.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		String pageCountsString = getXmlOutputGenerator(renderRequest
					.getLocale()).pageCountsXML(manageAssetFormForChange.getAssetDetail().getPageCounts(),manageAssetFormForChange.getAssetDetail().getPageCounts().size(),0,pageCountsMap);
			pageCountsString = pageCountsString.replace("\n", "");	
		
		if(manageAssetFormForChange.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			manageAssetFormForChange.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		LOGGER.debug("pageCountsString" + pageCountsString);
		model.addAttribute("pageCountsString", pageCountsString);
		model.addAttribute("pageCountsOriginalval", renderRequest.getParameter("pageCountValue"));
		model.addAttribute("pageCountsOriginaldate", renderRequest.getParameter("pageCountDateValid"));
		//LBS
		String fleetManagementFlag = (String)renderRequest.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			renderRequest.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
			renderRequest.setAttribute("fleetManagementFlag", "false");
			
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
		
		List<AccountContact> deviceContactDetailList = contactController.getDeviceContactInfo(renderRequest, renderResponse,model);
		LOGGER.debug("after for loop deviceContactDetailList size="+deviceContactDetailList.size());
		if(manageAssetFormForChange.getAssetDetail()!=null) {
			LOGGER.debug("getAssetDetail is not null");
			manageAssetFormForChange.getAssetDetail().setDeviceContact(deviceContactDetailList);
		}
		
		//This would fill up the current identifiers column in jsp
		if(modelMap.containsAttribute(ChangeMgmtConstant.CHANGEASSETFORM))		
		{				
			manageAssetFormForChange=(ManageAssetForm)modelMap.get(ChangeMgmtConstant.CHANGEASSETFORM);
			modelMap.addAttribute(ChangeMgmtConstant.CHANGEASSETFORM,manageAssetFormForChange);
		}
		
		
				
		if (result.hasErrors()) {
			LOGGER.debug("Contains errors");
			//Server Side validation errors would be displayed in change asset jsp itself
			commonController.getContactInformation(renderRequest, renderResponse);
			LOGGER.exit(this.getClass().getSimpleName(),
					METH_CHANGEASSETCONFIRMATN);
			return ChangeMgmtConstant.CHANGEASSETPATH;
		} else
		{	
			manageAssetFormForChange.refreshSubmitToken(renderRequest);
			LOGGER.exit(this.getClass().getSimpleName(),METH_CHANGEASSETCONFIRMATN);
			
			
				manageAssetFormForChange.getAssetDetail().setNotes(attachmentDescription);
				attachForm.setAttachmentDescription(attachmentDescription);
			
			
			LOGGER.debug("request param is " + renderRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
			List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
			if (attachmentList != null){
				attachForm.setAttachmentList(attachmentList);
			}
			
			FileUploadForm fileUploadForm = new FileUploadForm();
			//changes for mps2.1
			if(attachForm.getAttachmentList() !=null){
				fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
				}
				//changes for mps2.1
			model.addAttribute("attachmentForm", attachForm);
			model.addAttribute("fileUploadForm", fileUploadForm);
			return ChangeMgmtConstant.CHANGEASSETCONFPATH;
		}
		
	}

	/** 
	 * This method forwards the request to decommission asset confirmation page 
	 * @param renderRequest 
	 * @param renderResponse 
	 * @param manageAssetFormForDecom 
	 * @param result 
	 * @param model 
	 * @return String 
	 * @param attachForm 
	 * @throws Exception 
	 */
	@RenderMapping(params = "action=decommissionAssetConfirmation")
	public String showDecommissionAssetConfirmation(
			RenderRequest renderRequest,RenderResponse renderResponse,@ModelAttribute(ChangeMgmtConstant.DECOMMASSETFORM) ManageAssetForm manageAssetFormForDecom,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,
			BindingResult result, Model model) throws Exception 
		{
		LOGGER.enter(this.getClass().getSimpleName(),METH_DECOMMASSETCONFIRMATN);
		model.addAttribute(ChangeMgmtConstant.DECOMMASSETFORM, manageAssetFormForDecom);
		LOGGER.debug("request param is " + renderRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		String timezoneOffset=renderRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		
		PortletSession session = renderRequest.getPortletSession();
		String attachmentDescription= renderRequest.getParameter("attachmentDescriptionID");
		//added for page counts 		
		String pageCountType= renderRequest.getParameter("pageCountTypeID");
		String pageCountsDate= renderRequest.getParameter("pageCountsDateID");
		String pageCountValue= renderRequest.getParameter("pageCountValueID");
		model.addAttribute("pageCountsOriginalval", renderRequest.getParameter("pageCountValue"));
		model.addAttribute("pageCountsOriginaldate", renderRequest.getParameter("pageCountDateValid"));
		List<String> pageCountTypeList=Arrays.asList(pageCountType.split(",",-1));
		List<String> pageCountsDateList=Arrays.asList(pageCountsDate.split(",",-1));
		List<String> pageCountValueList=Arrays.asList(pageCountValue.split(",",-1));
		List<PageCounts> pageCounts = new ArrayList<PageCounts>();
		
		for(int i=0;i<pageCountTypeList.size();i++){
			PageCounts pc= new PageCounts();
			
			pc.setName(pageCountTypeList.get(i));
			pc.setDate(pageCountsDateList.get(i));
			pc.setCount(pageCountValueList.get(i));
			if(!(i==0 && ("").equals(pageCountTypeList.get(i).trim()) && ("").equals(pageCountsDateList.get(i).trim()) && ("").equals(pageCountValueList.get(i).trim())) ){
				pageCounts.add(pc);
			}
		}
		manageAssetFormForDecom.getAssetDetail().setPageCounts(pageCounts);
		
		Map<String, String> pageCountsMap=null;
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), renderRequest.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		String pageCountsString = getXmlOutputGenerator(renderRequest
					.getLocale()).pageCountsXML(manageAssetFormForDecom.getAssetDetail().getPageCounts(),manageAssetFormForDecom.getAssetDetail().getPageCounts().size(),0,pageCountsMap);
			pageCountsString = pageCountsString.replace("\n", "");	
		
			if(manageAssetFormForDecom.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
				manageAssetFormForDecom.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
			}
		LOGGER.debug("pageCountsString" + pageCountsString);
		model.addAttribute("pageCountsString", pageCountsString);
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		if (attachmentList != null){
			attachForm.setAttachmentList(attachmentList);
		}
		
		FileUploadForm fileUploadForm = new FileUploadForm();
		//changes for mps2.1
		if(attachForm.getAttachmentList() !=null){
			fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
			}
			//changes for mps2.1
		model.addAttribute("attachmentForm", attachForm);
		model.addAttribute("fileUploadForm", fileUploadForm);
		model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_DECOMM);
		
		model.addAttribute(ChangeMgmtConstant.DECOMMASSETFORM, manageAssetFormForDecom);
		model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_DECOMM);
		//LBS
		String fleetManagementFlag = (String)renderRequest.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			renderRequest.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
			renderRequest.setAttribute("fleetManagementFlag", "false");
		}
		if(!StringUtil.isEmpty(timezoneOffset))
		{
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, timezoneOffset);
		}
		if (result.hasErrors()) {
			LOGGER.debug("Contains errors");
			commonController.getContactInformation(renderRequest, renderResponse);
			LOGGER.exit(this.getClass().getSimpleName(),
					METH_DECOMMASSETCONFIRMATN);
			//Server Side validation errors would be displayed in delete asset jsp itself
			return ChangeMgmtConstant.DECOMMASSETPATH;
		} else
		{
			manageAssetFormForDecom.refreshSubmitToken(renderRequest);
			LOGGER.exit(this.getClass().getSimpleName(),METH_DECOMMASSETCONFIRMATN);
			//For no errors move to the confirmation page
		
			
				manageAssetFormForDecom.getAssetDetail().setNotes(attachmentDescription);
				attachForm.setAttachmentDescription(attachmentDescription);
			
			
			LOGGER.debug("request param is " + renderRequest.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
			if (attachmentList != null){
				attachForm.setAttachmentList(attachmentList);
			}
				
			
			
			//changes for mps2.1
			if(attachForm.getAttachmentList() !=null){
				fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
				}
				//changes for mps2.1
			model.addAttribute("attachmentForm", attachForm);
			model.addAttribute("fileUploadForm", fileUploadForm);
			return ChangeMgmtConstant.DECOMMASSETCONFPATH;
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
	@RenderMapping(params="action=forwardtoJSP")
	public String redirectToChangeDecom(RenderRequest req, RenderResponse resp, Model model, 
			@RequestParam(value=ChangeMgmtConstant.ASSETID) String assetId, @RequestParam(value=ChangeMgmtConstant.REQUESTTYPE)String requestType, 
			ModelMap map) throws Exception 
		{
		LOGGER.enter(this.getClass().getSimpleName(), METH_REDIRECTTOCHANGEDECOM);
		AssetResult assetResult=null;		
		AssetContract contract =null;
		ManageAssetForm masFormForChange=null;
		ManageAssetForm masFormForDecomm=null;
		Boolean changeAssetFlag=false;
		AccountContact accContact=null;
		Boolean isUpdateFlag=false;
		AttachmentForm attachForm = new AttachmentForm();
		PortletSession session = req.getPortletSession();
		setLbsFormPost(lbsFormPost);
		LOGGER.debug("Calling Method setModelParams");
		setModelParams(model,session);
		LOGGER.debug("Outside Method setModelParams");
		//added for page counts
		Map<String, String> pageCountsMap=null;
		List<String> pageCountsList=new ArrayList<String>();
		//Added for LBS
		String fleetManagementFlag =  (String)req.getAttribute("fleetManagementFlag");
		String moveAssetFlag =  (String)req.getAttribute("moveAssetFlag");
		String backToMap=(String)req.getAttribute("backJSON");
		LOGGER.debug("back json= " +backToMap);
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), req.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		
		Iterator itmap = pageCountsMap.entrySet().iterator();
		while (itmap.hasNext()) {
			Map.Entry valuemap = (Map.Entry)itmap.next();
			pageCountsList.add(valuemap.getValue().toString());
		}
		model.addAttribute("pageCountsList", pageCountsMap);
		//added for page counts end
		LOGGER.debug("request param is " + req.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		String timezoneOffset=req.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		
		retrieveGridSetting("gridContainerDiv_all_request_types",req,resp,model);//Loading data from db-CI BRD13-10-02
		
		if(!StringUtil.isEmpty(timezoneOffset))
		{
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, timezoneOffset);
		}
		
		if(assetId!=null)
		{
			LOGGER.info("Asset Id is " + assetId);			
			contract= commonController.getAssetContract(assetId, req);
		}

		//it is the information of the requestor
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
			if(requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGEASSETREQTYPE))
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
			else if(requestType.equalsIgnoreCase(ChangeMgmtConstant.DECOMMASSETREQTYPE)) 
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
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		try{
			
			session.removeAttribute("attachmentList");
			session.removeAttribute("attachmentForm");
			long timeBeforeCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LOGGER);
			LOGGER.info("end printing lex loggger");
			assetResult=commonController.retrieveAssetDetail(contract);
			//changes for page counts
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGEASSETS_MSG_RETRIEVEASSETDETAIL, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			List<PageCounts> pageCountList = assetResult.getAsset().getPageCounts();
			List<PageCounts> modifiedPageCountList = new ArrayList<PageCounts>();
			for(int i=0;i<pageCountList.size();i++){
				PageCounts modifiedPageCount = new PageCounts();
				modifiedPageCount.setName(pageCountList.get(i).getName());
				modifiedPageCount.setCount(pageCountList.get(i).getCount());
				if(pageCountList.get(i).getDate()!= null && pageCountList.get(i).getDate()!=""){
					DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
					Date  defaultDateINGMT = parser.parse(pageCountList.get(i).getDate());
					LOGGER.debug("defaultDateINGMT order--->>>"+defaultDateINGMT.toString());					
					LOGGER.debug("The default date is to be shown--->>>"+DateUtil.convertToLocaleSpecificDateTime(defaultDateINGMT, req.getLocale()));					
					modifiedPageCount.setDate(DateUtil.convertToLocaleSpecificDateTime(defaultDateINGMT, req.getLocale()));							
				}
				modifiedPageCountList.add(modifiedPageCount);
			}
			
			
			
			
			LOGGER.debug("** MPS PERFORMANCE TESTING RETRIEVE CHANGE MANAGEMENT ASSET DETAIL ==>: " + (System.currentTimeMillis()- timeBeforeCall));
			if (assetResult != null && assetResult.getAsset() != null) {
				Asset asset = assetResult.getAsset();
				/* This portion has been added for Asset Image */
				ProductImageContract productImageContract = new ProductImageContract();
				productImageContract.setPartNumber(asset.getProductTLI());
				
				long timeBeforeServiceCall=System.currentTimeMillis();
				ProductImageResult productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
				LOGGER.info("start printing lex logger");
				
				LOGGER.info("end printing lex loggger");
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGEASSETS_MSG_RETRIEVEPRODUCTIMAGEURL, timeBeforeServiceCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_PORTALIMAGEDB,productImageContract);
				asset.setProductImageURL(productImageResult.getProductImageUrl());
				commonController.assembleDevice(asset, req.getLocale());/*Done for Defect 3924-Jan Release*/
				asset.getAssetContact().setWorkPhone(asset.getAssetContact().getWorkPhone().replace(" ", "").replace("\n", "")); /*Done to supress the junk charecters*/
				
				//If it is draft SR, need to add attachment to the page
				List<Attachment> attachmentList = new ArrayList<Attachment>();
				attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");

				List<Attachment> modifiedAttachmentList = new ArrayList<Attachment>();
				if(attachmentList!=null){
					for(Attachment attachment:attachmentList){
						Attachment modifiedAttachment = new Attachment();
						String displayAttachment = "";
						
						modifiedAttachment.setAttachmentName(attachment.getAttachmentName());
						modifiedAttachment.setActivityId(attachment.getActivityId());
						modifiedAttachment.setExtension(attachment.getExtension());
						modifiedAttachment.setSize(attachment.getSize());
						modifiedAttachment.setStatus(attachment.getStatus());
						modifiedAttachment.setVisibility(attachment.getVisibility());
						modifiedAttachment.setCompletedOn(attachment.getCompletedOn());
						modifiedAttachment.setId(attachment.getId());
						double fileSizeDisplay=attachment.getSize();
						LOGGER.debug("File Size is " + fileSizeDisplay);
						fileSizeDisplay=fileSizeDisplay/1024;
						BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
						roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
						
						modifiedAttachment.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
						//start doing the manipulation for display name
						String attachName = attachment.getAttachmentName();
						String fileNameWithTimestamp = attachName.substring(attachName.indexOf('@')+1, attachName.length());
						LOGGER.debug("fileNameWithTimestamp is "+fileNameWithTimestamp);
						displayAttachment = fileNameWithTimestamp.substring(0,fileNameWithTimestamp.lastIndexOf('_'));
						displayAttachment = displayAttachment+"."+attachment.getExtension();
						LOGGER.debug("Final displayFileName is "+displayAttachment);
						//end completing the manipulation for display name
						modifiedAttachment.setDisplayAttachmentName(displayAttachment);
						modifiedAttachmentList.add(modifiedAttachment);
					}
				}
				
				//Initializes the form for file upload
				FileUploadForm fileUploadForm = new FileUploadForm();
				model.addAttribute("fileUploadForm", fileUploadForm);
				
				
				if(masFormForChange!=null)
				{
					masFormForChange.setAssetDetail(asset);
					//masFormForChange.getAssetDetail().getInstallAddress().setLbsAddressFlag(true);
					masFormForChange.getAssetDetail().setPageCounts(modifiedPageCountList);
					ServiceRequest serviceReq=new ServiceRequest();
					masFormForChange.setServiceRequest(serviceReq);
					masFormForChange.getServiceRequest().setPrimaryContact(accContact);
					//This would display the current data in the change asset jsp like customer cost code etc...
					
					//Adding LBS changes for Page Flow determination					
					if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){												
						if(moveAssetFlag != null){
							if(moveAssetFlag.equals("true")){
								model.addAttribute("pageFlow","fleetMgmtMove");
								GenericAddress moveAddress = (GenericAddress)session.getAttribute("moveAddress", PortletSession.APPLICATION_SCOPE);
								//masFormForChange.getAssetDetail().getInstallAddress().setPhysicalLocation1(asset.getInstallAddress().getPhysicalLocation1());
								//masFormForChange.getAssetDetail().getInstallAddress().setPhysicalLocation2(asset.getInstallAddress().getPhysicalLocation2());
								//masFormForChange.getAssetDetail().getInstallAddress().setPhysicalLocation3(asset.getInstallAddress().getPhysicalLocation3());
								//masFormForChange.getAssetDetail().getInstallAddress().setZoneId(asset.getInstallAddress().getZoneName());
								masFormForChange.getAssetDetail().setMoveToAddress(moveAddress);
								masFormForChange.setFleetManagementFlag(fleetManagementFlag);
								//to be changed for LBS
								//masFormForChange.getAssetDetail().getMoveToAddress().setLbsAddressFlag(false);
							}
						}else{
							model.addAttribute("pageFlow","fleetMgmtChange");
							//masFormForChange.getAssetDetail().getInstallAddress().setPhysicalLocation1(asset.getInstallAddress().getPhysicalLocation1());
							//masFormForChange.getAssetDetail().getInstallAddress().setPhysicalLocation2(asset.getInstallAddress().getPhysicalLocation2());
							//masFormForChange.getAssetDetail().getInstallAddress().setPhysicalLocation3(asset.getInstallAddress().getPhysicalLocation3());
							//masFormForChange.getAssetDetail().getInstallAddress().setZoneId(asset.getInstallAddress().getZoneName());
							masFormForChange.setFleetManagementFlag(fleetManagementFlag);
							
							
						}						
					}else{
						model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_CHANGE);
						masFormForChange.setFleetManagementFlag("false");
					}					
					
					attachForm.setListOfFileTypes(listOfFileTypes);
					attachForm.setAttMaxCount(attMaxCount);
					
					attachForm.setAttachmentList(modifiedAttachmentList);
					
					//Initializes the form for file upload
					req.setAttribute("attachmentForm",attachForm);
					model.addAttribute("attachmentForm",attachForm);
					
					//added for Device Contact start
					Map<String, String> deviceContactTypeMap=null;
					List<String> deviceContactTypeList=new ArrayList<String>();
					try {
						deviceContactTypeMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONTACT_TYPE_FOR_ASSET.getValue(), req.getLocale());
						LOGGER.debug("deviceContactTypeMap = "+ deviceContactTypeMap);
						
					} catch (LGSDBException e) {
						LOGGER.debug("catch"+ e.getMessage());
					}
					Iterator itContactmap = deviceContactTypeMap.entrySet().iterator();
					while (itContactmap.hasNext()) {
						Map.Entry valuemapContact = (Map.Entry)itContactmap.next();
						deviceContactTypeList.add(valuemapContact.getValue().toString());
					}
					model.addAttribute("deviceContactTypeList", deviceContactTypeMap);
				
					//end
					
					map.addAttribute(ChangeMgmtConstant.ADDASSETFORM, masFormForChange);
					map.addAttribute(ChangeMgmtConstant.CHANGEASSETFORM, masFormForChange);
				}
				else 
				{
					asset.setPickupAddress(asset.getInstallAddress());//This is done bcoz pickup address is same as install address
					masFormForDecomm.setPrimarySiteContact(asset.getAssetContact());//This is done bcoz primary site contact is same as asset contact					
					masFormForDecomm.setAssetDetail(asset);
					//masFormForDecomm.getAssetDetail().getInstallAddress().setLbsAddressFlag(true);
					masFormForDecomm.getAssetDetail().setPageCounts(modifiedPageCountList);

					model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_DECOMM);
					
					attachForm.setListOfFileTypes(listOfFileTypes);
					attachForm.setAttMaxCount(attMaxCount);
					
					attachForm.setAttachmentList(modifiedAttachmentList);
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
					//Initializes the form for file upload
					req.setAttribute("attachmentForm",attachForm);
					model.addAttribute("attachmentForm",attachForm);
					model.addAttribute(ChangeMgmtConstant.ADDASSETFORM, masFormForDecomm);
					model.addAttribute(ChangeMgmtConstant.DECOMMASSETFORM, masFormForDecomm);
				}
				
				//Setting flag for flow from LBS 
				 
				if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
					LOGGER.debug("Setting Account information from asset details call ");
					Map<String,String> accDetails=new HashMap<String, String>();
					accDetails.put(ChangeMgmtConstant.ACCOUNTNAME,asset.getAccount().getAccountName());
					accDetails.put(ChangeMgmtConstant.ACCOUNTID,asset.getAccount().getAccountId());
					session.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accDetails ,PortletSession.APPLICATION_SCOPE);
					
					LOGGER.debug("Setting LBS Flag to true ");
					model.addAttribute("fleetManagementFlag", "true");
					
					
					if(moveAssetFlag != null){
						if(moveAssetFlag.equals("true")){
							model.addAttribute("moveAssetFlag", "true");
						}
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
			
			if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
				if(changeAssetFlag)	//The request is for Change Asset
				{
					map.addAttribute(ChangeMgmtConstant.CHANGEASSETFORM, masFormForChange);
					model.addAttribute("attachmentForm",attachForm);
					req.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
					return ChangeMgmtConstant.CHANGEASSETPATH;
					
				}
				else{
					model.addAttribute(ChangeMgmtConstant.DECOMMASSETFORM, masFormForDecomm);
					model.addAttribute("attachmentForm",attachForm);
					req.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
					return ChangeMgmtConstant.DECOMMASSETPATH;
					
				}
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
			if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
				if(changeAssetFlag)	//The request is for Change Asset
				{
					map.addAttribute(ChangeMgmtConstant.CHANGEASSETFORM, masFormForChange);
					model.addAttribute("attachmentForm",attachForm);
					req.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
					return ChangeMgmtConstant.CHANGEASSETPATH;
				}
				else{
					model.addAttribute(ChangeMgmtConstant.DECOMMASSETFORM, masFormForDecomm);
					model.addAttribute("attachmentForm",attachForm);
					req.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
					return ChangeMgmtConstant.DECOMMASSETPATH;
				}
			}
			else{
			return ChangeMgmtConstant.ASSETLISTPATH;
			}
		}
		if(changeAssetFlag)	//The request is for Change Asset
		{
			LOGGER.debug("returning to change asset=====> ");
			LOGGER.exit(this.getClass().getSimpleName(),METH_REDIRECTTOCHANGEDECOM);
			return ChangeMgmtConstant.CHANGEASSETPATH;
		}
		else{ 
			LOGGER.exit(this.getClass().getSimpleName(),METH_REDIRECTTOCHANGEDECOM);
			//The request is for Decommission Asset			
			return ChangeMgmtConstant.DECOMMASSETPATH;
		}
		
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
	@RequestMapping(params = "action=decommissionAsset")
	public void decommissionAsset(ActionRequest actRequest,
			ActionResponse actResponse, @ModelAttribute(ChangeMgmtConstant.DECOMMASSETFORM) ManageAssetForm masFormForDecomm,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,Model model,  SessionStatus sessionStatus) throws Exception
		{
		LOGGER.enter(this.getClass().getSimpleName(), METH_DECOMMISSIONASSET);
		CreateServiceRequestResult result=null;
		
		//LBS
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
		
		//added for page counts
		Map<String, String> pageCountsMap=null;
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), actRequest.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		String pageCountsString = getXmlOutputGenerator(actRequest
				.getLocale()).pageCountsXML(masFormForDecomm.getAssetDetail().getPageCounts(),masFormForDecomm.getAssetDetail().getPageCounts().size(),0,pageCountsMap);
		pageCountsString = pageCountsString.replace("\n", "");
		model.addAttribute("pageCountsString", pageCountsString);
		//end
		if(masFormForDecomm.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			masFormForDecomm.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		AttachmentContract contract = new AttachmentContract();
		List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
		List <Attachment> attachmentList = new ArrayList<Attachment>();
		PortletSession session = actRequest.getPortletSession();
		attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		if(attachmentList!=null && !attachmentList.isEmpty()){
			List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
			for(int i=0;i<attachmentList.size();i++){
				AttachmentFile attachmentFile = new AttachmentFile();
				attachmentFile.setFileName(attachmentList.get(i).getDisplayAttachmentName());
				attachmentFile.setFileSize(attachmentList.get(i).getSize());
				displayAttachmentList.add(attachmentFile);
				if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
					LOGGER.debug("Attachment id is either null or blank");
					createSRAttachmentList.add(attachmentList.get(i));
				}
			}
			LOGGER.debug("No of attachment in the reviewAssetOrderForm "+attachmentList.size());
			model.addAttribute("displayAttachmentList", displayAttachmentList);
			attachForm.setDisplayAttachmentList(displayAttachmentList);
		}
		commonController.assembleDevice(masFormForDecomm.getAssetDetail(), actRequest.getLocale());/*Done for Defect 3924-Jan Release*/
		
		if(masFormForDecomm.getDecommAssetFlag()!=null && masFormForDecomm.getDecommAssetFlag().equalsIgnoreCase(ChangeMgmtConstant.YES))
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
			LOGGER.debug("ManageAssetsController duplicated submit, do nothing!");		
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.serviceRequest.duplicateSubmission", null, actRequest.getLocale()));			
			model.addAttribute(masFormForDecomm);		
			
			actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_DECOMM);
			actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ASSETSUMMARY);
		}
		/**************** Section end to prevent resubmit by browser refresh ***********/
		else{
		try{
			try{
			result=doWebServiceCall(masFormForDecomm,actRequest);				
			model.addAttribute(ChangeMgmtConstant.MODELATTRSRNO, result.getServiceRequestNumber());//To be displayed on summary page
			
			}catch(LGSRuntimeException ex)
			{	
				logStackTrace(ex);
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.DECOMMASSETCONFPGNM);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
			}
			catch(LGSBusinessException ex)
			{
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.DECOMMASSETCONFPGNM);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);				
				logStackTrace(ex);
			}
			//This should happen after the web service call
			
			try{
				if(createSRAttachmentList!=null && !createSRAttachmentList.isEmpty()){
					for(int i=0;i<createSRAttachmentList.size();i++){
						LOGGER.debug("Create SR will be called for following attachments "+createSRAttachmentList.get(i).getAttachmentName());
					}
					contract.setAttachments(createSRAttachmentList);
					contract.setRequestType("Service Request");
					contract.setIdentifier(result.getServiceRequestRowId());
					long timeBeforeCall=System.currentTimeMillis();
					attachmentService.uploadAttachments(contract);
					LOGGER.info("start printing lex logger");
					LOGGER.logTime("** MPS PERFORMANCE UPLOAD ATTACHMENTS ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
					LOGGER.info("end printing lex loggger");
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
					LOGGER.debug("After calling upload attachment");
				}
			}catch (Exception e) {
				LOGGER.debug("exception");
				List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
				model.addAttribute("displayAttachmentList", displayAttachmentList);
				attachForm.setDisplayAttachmentList(displayAttachmentList);
				LOGGER.debug("exception in decommission asset attachment");
				model.addAttribute("attachmentException", "attachfailed");
			}
			
			
			try
			{
				if(result.getServiceRequestRowId().length()>0)
				{
					//No error with rename, go for the summary page
					sessionStatus.setComplete();
					//After the service call, forward to the manage asset summary page					
					actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_DECOMM);
					actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ASSETSUMMARY);
					
					//this is to enable re-submit of SR form on submit/draft exception
					Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, PortletSession.PORTLET_SCOPE);						
					masFormForDecomm.setSubmitToken(tokenInSession);
					model.addAttribute(masFormForDecomm);
				}
			}catch (Exception exceptn)//This would be executed if error while upload runnable thread fails
			{
				//if any error do not show the summary page
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.DECOMMASSETCONFPGNM);
				actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
				logStackTrace(exceptn);
			}
			
	
		actRequest.setAttribute(ChangeMgmtConstant.TYPEOFREQ, ChangeMgmtConstant.TYPEOFREQ_DECOMM);
		actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ASSETSUMMARY);
		}catch(Exception ex)
		{
			//If any error do not show the summary page
			actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.DECOMMASSETCONFPGNM);
			actRequest.setAttribute(ChangeMgmtConstant.ERRORATTR,ChangeMgmtConstant.TRUEATTR);
			LOGGER.error("Inside catch block add asset "+ex.getMessage());			
			logStackTrace(ex);
		}
	  }
		LOGGER.exit(this.getClass().getSimpleName(), METH_DECOMMISSIONASSET);
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
	 * Private method for checking form data in change asset, to understand the diff between old and new data
	 * accordingly review page would be shown 
	 * @param manageAssetFormForChange 
	 * @param model 
	 * @throws LGSRuntimeException 
	 */
	private void checkFormData(ManageAssetForm manageAssetFormForChange, Model model) throws LGSRuntimeException{
		LOGGER.enter(this.getClass().getSimpleName(), METH_CHECKFORMDATA);
		try{		
			Boolean installedAddressChanged=true;
			Boolean contactChanged=true;
			Boolean consumablesAddrChanged=true;

			if(manageAssetFormForChange.getNewAssetInfo().getInstallAddress()==null ||
					manageAssetFormForChange.getNewAssetInfo().getInstallAddress().getAddressLine1()=="")
			{
				LOGGER.debug("Installed Address not changed by the user=========> ");
				installedAddressChanged=false;
			}
			
			if(manageAssetFormForChange.getNewAssetInfo().getConsumableAddress()==null ||
			manageAssetFormForChange.getNewAssetInfo().getConsumableAddress().getAddressLine1()=="")
			{
				LOGGER.debug("consumables Address not changed by the user=========> ");
				consumablesAddrChanged=false;
			}
			if(manageAssetFormForChange.getNewAssetInfo().getAssetContact()==null || 
					manageAssetFormForChange.getNewAssetInfo().getAssetContact().getFirstName()=="")
			{
				LOGGER.debug("Consumables Contact not changed by the user=========> " + contactChanged);
				contactChanged=false;
			}
			model.addAttribute("addressChanged",installedAddressChanged);
			model.addAttribute("consAddressChanged", consumablesAddrChanged);
			model.addAttribute("contactChanged",contactChanged);
		}catch(Exception ex)
		{	
			logStackTrace(ex);
			throw new LGSRuntimeException("Error occurred while further processing");
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_CHECKFORMDATA);
	}

	/**
	 * Method responsible for back button in add asset review page 
	 * @param req 
	 * @param resp 
	 * @param mAssetForm 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 * @param attachForm  
	 */
	@RenderMapping(params = "action=goToAddAsset")
	public String goToAddAsset(RenderRequest req, RenderResponse resp, @ModelAttribute(ChangeMgmtConstant.ADDASSETFORM)
	ManageAssetForm mAssetForm,@ModelAttribute ("attachmentForm") AttachmentForm attachForm, Model model) throws Exception	
	{		PortletSession session = req.getPortletSession();
		LOGGER.enter(this.getClass().getSimpleName(), METH_GOTOADDASSET);
		
		//LBS
		String fleetManagementFlag = (String)req.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			req.setAttribute("fleetManagementFlag", "true");
			mAssetForm.setFleetManagementFlag(fleetManagementFlag);
			LOGGER.debug("Setting setFleetManagementFlag to true ");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
			req.setAttribute("fleetManagementFlag", "false");
			mAssetForm.setFleetManagementFlag("false");
			LOGGER.debug("Setting setFleetManagementFlag to false ");
		}
		
		//added for page counts
		
		Map<String, String> pageCountsMap=null;
		List<String> pageCountsList=new ArrayList<String>();
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), req.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		Iterator itmap = pageCountsMap.entrySet().iterator();
		while (itmap.hasNext()) {
			Map.Entry valuemap = (Map.Entry)itmap.next();
			pageCountsList.add(valuemap.getValue().toString());
		}
		model.addAttribute("pageCountsList", pageCountsMap);
		LOGGER.debug("attachmentForm desc = "+ mAssetForm.getAttachmentDescription());
		attachForm.setAttachmentDescription(mAssetForm.getAttachmentDescription());
		//added for page counts end
		if(mAssetForm.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			mAssetForm.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		//added for Device Contact start
		LOGGER.debug("in Device Contact section");
		Map<String, String> deviceContactTypeMap=null;
		List<String> deviceContactTypeList=new ArrayList<String>();
		try {
			deviceContactTypeMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONTACT_TYPE_FOR_ASSET.getValue(), req.getLocale());
			LOGGER.debug("deviceContactTypeMap = "+ deviceContactTypeMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		Iterator itContactmap = deviceContactTypeMap.entrySet().iterator();
		while (itContactmap.hasNext()) {
			Map.Entry valuemapContact = (Map.Entry)itContactmap.next();
			deviceContactTypeList.add(valuemapContact.getValue().toString());
		}
		model.addAttribute("deviceContactTypeList", deviceContactTypeMap);
	
		//end
		
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		if (attachmentList != null){
			attachForm.setAttachmentList(attachmentList);
		}
		LOGGER.debug("listOfFileTypes  = "+ listOfFileTypes + attMaxCount);
		attachForm.setListOfFileTypes(listOfFileTypes);
		attachForm.setAttMaxCount(attMaxCount);
		
		FileUploadForm fileUploadForm = new FileUploadForm();
		//changes for mps2.1
		if(attachForm.getAttachmentList() !=null){
			fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
			}
			//changes for mps2.1
		model.addAttribute("attachmentForm", attachForm);
		model.addAttribute("fileUploadForm", fileUploadForm);
		
		model.addAttribute(ChangeMgmtConstant.ADDASSETFORM, mAssetForm);	
		model.addAttribute("pageFlow",ChangeMgmtConstant.ADDONE);
		commonController.getContactInformation(req, resp);	
		LOGGER.exit(this.getClass().getSimpleName(), METH_GOTOADDASSET);
		return ChangeMgmtConstant.ADDASSETPATH;
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
	@RenderMapping(params = "action=goToChangeAsset")
	public String goToChangeAsset(RenderRequest req, RenderResponse resp, @ModelAttribute(ChangeMgmtConstant.CHANGEASSETFORM)
	ManageAssetForm mAssetForm,@ModelAttribute ("attachmentForm") AttachmentForm attachForm, Model model) throws Exception	
	{
		model.addAttribute("pageCountsOriginalval", req.getParameter("pageCountValue"));
		model.addAttribute("pageCountsOriginaldate", req.getParameter("pageCountDateValid"));
		LOGGER.debug("date" + req.getParameter("pageCountDateValid"));
		LOGGER.debug("value" + req.getParameter("pageCountValue"));
		LOGGER.enter(this.getClass().getSimpleName(), METH_GOTOCHANGEASSET);
		retrieveGridSetting("gridContainerDiv_all_request_types",req,resp,model);//Loading data from db-CI BRD13-10-02
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
		
		// added for page counts 
		Map<String, String> pageCountsMap=null;
		List<String> pageCountsList=new ArrayList<String>();
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), req.getLocale());
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		Iterator itmap = pageCountsMap.entrySet().iterator();
		while (itmap.hasNext()) {
			Map.Entry valuemap = (Map.Entry)itmap.next();
			pageCountsList.add(valuemap.getValue().toString());
		}
		model.addAttribute("pageCountsList", pageCountsMap);
		//end
		if(mAssetForm.getAssetDetail().getInstallAddress().getLbsAddressFlag()==null){
			mAssetForm.getAssetDetail().getInstallAddress().setLbsAddressFlag(false);
		}
		//added for Device Contact start
		LOGGER.debug("in Device Contact section");
		Map<String, String> deviceContactTypeMap=null;
		List<String> deviceContactTypeList=new ArrayList<String>();
		try {
			deviceContactTypeMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONTACT_TYPE_FOR_ASSET.getValue(), req.getLocale());
			LOGGER.debug("deviceContactTypeMap = "+ deviceContactTypeMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		} catch (Exception e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		Iterator itContactmap = deviceContactTypeMap.entrySet().iterator();
		while (itContactmap.hasNext()) {
			Map.Entry valuemapContact = (Map.Entry)itContactmap.next();
			deviceContactTypeList.add(valuemapContact.getValue().toString());
		}
		model.addAttribute("deviceContactTypeList", deviceContactTypeMap);
	
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
		LOGGER.exit(this.getClass().getSimpleName(), METH_GOTOCHANGEASSET);
		return ChangeMgmtConstant.CHANGEASSETPATH;
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
	@RenderMapping(params="action=goToDecomAsset")
	public String goToDecomAsset(RenderRequest request, RenderResponse response, @ModelAttribute(ChangeMgmtConstant.DECOMMASSETFORM)
	ManageAssetForm mAssetForm,@ModelAttribute ("attachmentForm") AttachmentForm attachForm,  Model model) throws Exception
	{
		LOGGER.enter(this.getClass().getSimpleName(), METH_GOTODECOMASSET);
		LOGGER.debug("Going back------------> ");
		model.addAttribute("pageCountsOriginalval", request.getParameter("pageCountValue"));
		model.addAttribute("pageCountsOriginaldate", request.getParameter("pageCountDateValid"));
		retrieveGridSetting("gridContainerDiv_all_request_types",request,response,model);//Loading data from db-CI BRD13-10-02
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
		
		//added for page counts 
		Map<String, String> pageCountsMap=null;
		List<String> pageCountsList=new ArrayList<String>();
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), request.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		Iterator itmap = pageCountsMap.entrySet().iterator();
		while (itmap.hasNext()) {
			Map.Entry valuemap = (Map.Entry)itmap.next();
			pageCountsList.add(valuemap.getValue().toString());
		}
		model.addAttribute("pageCountsList", pageCountsMap);
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
		
		model.addAttribute(ChangeMgmtConstant.DECOMMASSETFORM, mAssetForm);
		model.addAttribute("pageFlow",ChangeMgmtConstant.REQ_TYPE_DECOMM);
		commonController.getContactInformation(request, response);
		LOGGER.exit(this.getClass().getSimpleName(), METH_GOTODECOMASSET);
		return ChangeMgmtConstant.DECOMMASSETPATH;
	}
		
	/**
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printAsset")
	public String printAsset() throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), METH_PRINTASSET);
		LOGGER.exit(this.getClass().getSimpleName(), METH_PRINTASSET);
		return ChangeMgmtConstant.PRINTASSETPATH;
	}
	
	/**
	 * @param request 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=AssetEmail")
	public String sendEmail(RenderRequest request, Model model) throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), METH_SENDEMAIL);
		final String typeOfFlow = request.getParameter(ChangeMgmtConstant.TYPEOFREQ);
		model.addAttribute(ChangeMgmtConstant.TYPEOFREQ, typeOfFlow);
		LOGGER.debug("-------------typeOfFlow ---------"+typeOfFlow);
		LOGGER.exit(this.getClass().getSimpleName(), METH_SENDEMAIL);
		return ChangeMgmtConstant.ASSETEMAILPATH;
	}
	/*Method gotoControlPanel added for Defect 3924- Jan Release*/
	/**
	 * @param request 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=gotoControlPanel")
	public String gotoControlPanel(RenderRequest request,
			Model model) throws Exception {
		model.addAttribute("controlPanelURL",request.getParameter("controlPanelURL"));
		model.addAttribute("pageName", "Change Asset");
		return "controlPanelPage";
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
	 * This method is used to retrieve the ProductModel 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	
	@ResourceMapping("getProductTypes")
	public void getProductTypes(ResourceRequest request, ResourceResponse response) throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), "getProductTypes");
		LOGGER.debug("parameter"+ request.getParameter("serialNumber"));
		String serialNumber = request.getParameter("serialNumber");
		//amind call start
		List<Asset> products = new ArrayList<Asset>();
		AssetContract contract = new AssetContract();
		contract.setSerialNumber(serialNumber);
		
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract,LOGGER);
		LOGGER.info("end printing lex loggger");
		PrintWriter out = response.getWriter();
		try{
		long timeBeforeCall=System.currentTimeMillis();
		AssetResult result = amindOrderSuppliesAssetService.retrieveProducts(contract);
		LOGGER.info("start printing lex logger");
		LOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE PRODUCTS ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		LOGGER.info("end printing lex loggger");
		//amind call end
		LOGGER.debug("after retrieveProducts");
		if(result !=null){
		products = result.getAssetlist();
		}
		StringBuffer responseBody=new StringBuffer();
		response.setContentType("text/html");
		responseBody.append(getOptionTypesForProducts(products,request.getLocale()));
		out.print(responseBody.toString());
		out.flush();
		out.close();
		}catch (Exception e) {
			StringBuffer responseBody=new StringBuffer();
			responseBody.append("<select id=\'productType\' onchange=\'showEnterProduct();\'><option selected value=\'\'>-"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.select", request.getLocale())+"-</option>");
			responseBody.append("<option selected value=\'other\'>Others</option></select>");			
			response.setContentType("text/html");
			out.print(responseBody.toString());
			out.flush();
			out.close();
						
		}
		
	}

	/**
	 * @param products 
	 * @return String 
	 */
	private String getOptionTypesForProducts(List<Asset> products, Locale locale) {
		StringBuilder sb = new StringBuilder();
		if(products!=null && !products.isEmpty()){
		sb.append("<select id=\'productType\' onchange=\'showEnterProduct();\' name=\'productType\'>");
		sb.append("<option value=\'\'>-"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.select", locale)+"-</option>");
		for(int i=0;i<products.size();i++) {
			String getProducts=products.get(i).getProductNo();
			
			sb.append("<option value=\'"+ 	getProducts  + "\'>");
			sb.append(getProducts);
			sb.append("</option>");
		}
		sb.append("</select>");
		}else{
			sb.append("<select id=\'productType\' onchange=\'showEnterProduct();\'><option selected value=\'\'>-"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.select", locale)+"-</option>");
			sb.append("<option selected value=\'other\'>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.others", locale)+"</option></select>");
		}
		LOGGER.debug("xml content is "+sb.toString());
		return sb.toString();
	}

/*Added for CI7 BRD14-02-12*/
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showAssetPage")
	public void showAssetPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		LOGGER.debug("------------------ RequestMapping of the showAssetPage -----------------------");
		LOGGER.debug("friendlyURl is"+request.getParameter("friendlyURL"));
		
		//LBS
		String fleetManagementFlag = (String)request.getParameter("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false ");
			model.addAttribute("fleetManagementFlag","false");
		}
		
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
	/*END*/
	
	public void setModelParams(Model model,PortletSession session){
		model.addAttribute("mdmId",PortalSessionUtil.getMdmId(session));
		model.addAttribute("mdmLevel",PortalSessionUtil.getMdmLevel(session));
		model.addAttribute("formPost",getLbsFormPost());
		model.addAttribute("emailId", PortalSessionUtil.getEmailAddress(session));
	}
}
