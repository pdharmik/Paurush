package com.lexmark.services.portlet.fleetmanagement;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.CheckedEntitlementServiceDetailContract;
import com.lexmark.contract.LocalizedEntitlementServiceListContract;
import com.lexmark.contract.LocalizedPageCountNameContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.UserGridSettingContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.CheckedEntitlementServiceDetailResult;
import com.lexmark.result.LocalizedEntitlementServiceListResult;
import com.lexmark.result.LocalizedPageCountNameResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.impl.real.jdbc.UserGridSettingServiceImpl;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.AssetDetailPageForm;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.FleetManagementForm;
import com.lexmark.services.form.ServiceRequestConfirmationForm;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.PaginationUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.XmlOutputGenerator;
import com.lexmark.util.DateUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.liferay.portal.util.PortalUtil;
import com.lexmark.services.util.ObjectDebugUtil;

/**
 * @author wipro 
 *
 */

@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={"assetDetailPageForm", "serviceRequestConfirmationForm"})
public class SuppliesAndServiceController{
	
	private String listOfFileTypes;
	private String attMaxCount;
	private String maxPartsToBeOrdered;
	
	@Autowired
	private CommonController commonController;
	@Autowired
	private OrderSuppliesAssetService orderSuppliesAssetService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private ProductImageService productImageService;	
	@Autowired
	private SharedPortletController sharedPortletController;
	
	private static Logger LOGGER = LogManager.getLogger(SuppliesAndServiceController.class);
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * @param request 
	 * @param response 
	 * @param fleetMgmtForm 
	 * @param model 
	 * @throws Exception 
	 */
	@ActionMapping(params="action=createSuppliesRequest")
	public void createSuppliesRequest(ActionRequest request, 
			ActionResponse response, @ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, Model model) throws Exception {
		AssetDetailPageForm assetDetailPageForm = new AssetDetailPageForm();
		String exError = null;
		
		try {
		LOGGER.debug("Entering createSuppliesRequest method");
		//String addressJson = fleetMgmtForm.getAddressJson();
		request.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
		LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
		request.setAttribute("fleetManagementFlag", "true");
		model.addAttribute("fleetManagementFlag", "true");
		//Call asset details call
		//AssetDetailPageForm assetDetailPageForm = new AssetDetailPageForm();
		
		
			AssetContract contract = ContractFactory.getAssetOrderDetailContract();
			contract.setAssetId(fleetMgmtForm.getAssetId()); //("1-BBBV-2098");
			Date parsedCurrentDate  = this.getCurrentDate(request);
			contract.setCurrentDate(parsedCurrentDate); 
			contract.setPageName(ChangeMgmtConstant.CONSUMABLE_DEVICE_DETAIL);
			contract.setEffectiveDate(new Date());
			
			AssetResult result = orderSuppliesAssetService.retrieveDeviceDetail(contract);	
			if(result.getAsset() != null){
				LOGGER.debug("======== Populating Asset ===========");		
				populateSuppliesReuestForm(assetDetailPageForm,result,request, response, model);
				LOGGER.debug("======== Completed Populating Asset ===========");
			}else{
				throw new Exception("Asset is null");
			}
			
			assetDetailPageForm.setFleetManagementFlag("true");
			
			FileUploadForm fileUploadForm = new FileUploadForm();
			model.addAttribute("fileUploadForm", fileUploadForm);
			model.addAttribute("assetDetailPageForm", assetDetailPageForm);
			/*	if(assetDetailPageForm.getPageCountsList()!=null){
				pageCountListSize = assetDetailPageForm.getPageCountsList().size();
			}*/
			model.addAttribute("pageCountListSize", 0);
			
			commonController.getContactInformation(request, response);	
		}catch(Exception e){
			LOGGER.debug("Exception Occured...  " + e.getMessage());
			exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"asset.data.validation.error", request.getLocale());
			model.addAttribute("AssetNotValid", exError);
			LOGGER.debug("LGSBusinessException in asset details call-----------> ");
			
			//return "orderSuppliesAssetOrder/assetDetail";
		}
			model.addAttribute("assetDetailPageForm", assetDetailPageForm);	
			model.addAttribute("attachmentForm", new AttachmentForm());
		LOGGER.debug("Exiting createSuppliesRequest method  ");
		response.setRenderParameter("action", "showSuppliesRequestPage");
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String  
	 * @throws Exception 
	 */
	@RequestMapping(params="action=showSuppliesRequestPage")
	public String showSuppliesRequestPage(Model model, 
			RenderRequest request, 
			RenderResponse response
			) throws Exception {
		
		//String toJsp = orderSuppliesAssetOrderController.showAssetDetailPage(request, response, "1-BBBV-2098", "1234", model);
		LOGGER.debug("Entering showSuppliesRequestPage method  ");
		try{
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID("displayAttachment");
		model.addAttribute("displayAttachment", resURL1.toString());
		model.addAttribute("fleetManagementFlag", "true");
		request.setAttribute("fleetManagementFlag", "true");
		LOGGER.debug("Exiting showSuppliesRequestPage method  ");
		
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			
			
		}
		return "orderSuppliesAssetOrder/assetDetail";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param fleetMgmtForm 
	 * @param model 
	 * @throws Exception 
	 */
	@ActionMapping(params="action=createServiceRequest")
	public void createServiceRequest(ActionRequest request, 
			ActionResponse response, @ModelAttribute ("fleetMgmtForm") FleetManagementForm fleetMgmtForm, Model model) throws Exception {
		
		LOGGER.debug("Entering createServiceRequest method");
		//String exError=null;
		//ServiceRequestConfirmationForm serviceRequestConfirmationForm = new ServiceRequestConfirmationForm();
		//List<String> errorList = new ArrayList<String>();
		//boolean hasExceptions = false;
		
		//String addressJson = fleetMgmtForm.getAddressJson();
		//LOGGER.debug("addressJson :: "+ addressJson);
		try{
		String assetId = fleetMgmtForm.getAssetId(); //"1-BBBV-2098";
		model.addAttribute("fleetManagementFlag", "true");
		request.setAttribute("fleetManagementFlag", "true");
		request.setAttribute("backJSON", StringEscapeUtils.escapeHtml(fleetMgmtForm.getBackInfo()));
		LOGGER.debug("back json= " +fleetMgmtForm.getBackInfo());
		this.showServiceRequestSubmitPage2(request, response, assetId, model);
		retrieveGridSetting("gridContainerDiv_all_request_types",request,response,model);//Loading data from db-CI BRD13-10-02
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		if (model.containsAttribute("notEntitledFlag")) {
			this.showServiceRequestSubmitPage2FromDeviceNotFoundPage(
					request, response, assetId, null, null, null, model);
		}
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			
			//exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"Could not create request", request.getLocale());
			String errorMessage = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"asset.data.validation.error", request.getLocale());
			model.addAttribute("errors", errorMessage);
			request.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
		}
		model.addAttribute("serviceRequestConfirmationForm", new ServiceRequestConfirmationForm());
		model.addAttribute("attachmentForm", new AttachmentForm());
	
		LOGGER.debug("Exiting createServiceRequest method");
		response.setRenderParameter("action", "showServiceRequestPage");
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=showServiceRequestPage")
	public String showServiceRequestPage(Model model, 
			RenderRequest request, 
			RenderResponse response
			) throws Exception {
		
		LOGGER.debug("Entering showServiceRequestPage method");
		
		
		
		try{
			ServiceRequestConfirmationForm serviceRequestConfirmationForm = (ServiceRequestConfirmationForm)request.getPortletSession().getAttribute("serviceRequestConfirmationForm",PortletSession.APPLICATION_SCOPE);
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("serviceAddressListURL");
		LOGGER.debug("the serviceAddressListURL is " + resURL.toString());
		serviceRequestConfirmationForm.setServiceAddressListURL(resURL
				.toString());

		ResourceURL resURL2 = response.createResourceURL();
		resURL2.setResourceID("contactListURL");
		LOGGER.debug("the contactListURL is " + resURL2.toString());
		serviceRequestConfirmationForm.setContactListURL(resURL2.toString());
		
		ResourceURL resURL3 = response.createResourceURL();
		resURL3.setResourceID("secContactListURL");
		LOGGER.debug("the secContactListURL is " + resURL3.toString());
		serviceRequestConfirmationForm.setSecContactListURL(resURL3.toString());
		
		serviceRequestConfirmationForm.setFleetManagementFlag("true");
		
		model.addAttribute("serviceRequestConfirmationForm", serviceRequestConfirmationForm);
		request.getPortletSession().removeAttribute("serviceRequestConfirmationForm",PortletSession.APPLICATION_SCOPE);
		
		}catch (Exception e) {
			LOGGER.debug("Exception"+e.getMessage());
			String errorMessage = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"asset.data.validation.error", request.getLocale());
			model.addAttribute("errors", errorMessage);
			request.setAttribute(ChangeMgmtConstant.EXCEPTNATTR,ChangeMgmtConstant.TRUEATTR);
		}
		
		
		String toJsp = "serviceRequest/serviceRequestPage2";//serviceRequestController.showSRSubmitPage2(request, response, assetId, model);
		//model.addAttribute("serviceRequestConfirmationForm", serviceRequestConfirmationForm);
		//model.addAttribute("attachmentForm", new AttachmentForm());
		model.addAttribute("fleetManagementFlag", "true");
		request.setAttribute("fleetManagementFlag", "true");
		LOGGER.debug("Exiting showServiceRequestPage method");
		return toJsp;
	}
	
	
	/**
	 * @param assetDetailPageForm 
	 * @param result 
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	private void populateSuppliesReuestForm(AssetDetailPageForm assetDetailPageForm, AssetResult result, ActionRequest request,ActionResponse response, Model model) throws Exception{
		try{
		LOGGER.debug("==== Entering Util method populateSuppliesReuestForm =====");
		PortletSession portletSession = request.getPortletSession();
		boolean dsplPaymentTypeFlag = false;
		String assetValidation = null;
		int pageCountListSize = 0;
		
		ServiceRequest serviceRequest=new ServiceRequest();
		AccountContact accContact=commonController.getContactInformation(request, response);
		serviceRequest.setRequestor(accContact);
		
		/* Changes for adding Bill to address*/
		String agreementId = "";
		if(result.getAsset().getAgreementId()!=null){
			agreementId = result.getAsset().getAgreementId();				
		}
		
		LOGGER.debug("Asset Agreement ID "+agreementId);
		
		assetDetailPageForm.setAgreementId(agreementId);
		assetDetailPageForm.setContractNumber(result.getAsset().getContractNumber());
		LOGGER.debug("Asset Contract Number "+ result.getAsset().getContractNumber());
		
		if(result.getAsset().getAssetContact()!=null){
			serviceRequest.setPrimaryContact(result.getAsset().getAssetContact());
		}
		/*
		 * This portion added for setting Account Name and Account Account Id,
		 * so that when address pop up opens from CommonController the values are available
		 * from session.*/
		if(result.getAsset()!=null  && result.getAsset().getAccount()!=null ){
			Map<String,String> accDetails = (Map<String,String>)portletSession.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
			if(accDetails == null)
			{
				accDetails=new HashMap<String, String>();
			}
			accDetails.put(ChangeMgmtConstant.ACCOUNTNAME, result.getAsset().getAccount().getAccountName());
			accDetails.put(ChangeMgmtConstant.ACCOUNTID, result.getAsset().getAccount().getAccountId());
			portletSession.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accDetails ,PortletSession.APPLICATION_SCOPE);
			
			assetDetailPageForm.setExpediteOrderAllowed(result.getAsset().getAccount().isAssetExpediteFlag());
			assetDetailPageForm.setSplitterFlag(result.getAsset().getAccount().isAccountSplitterFlag());

			assetDetailPageForm.setShowPriceFlag(result.getAsset().getAccount().getShowPriceFlag());//Show Price flag is true				
			
			if(assetDetailPageForm.isSplitterFlag()){
				assetDetailPageForm.setShowPriceFlag(result.getAsset().getAccount().getShowPriceFlag());
				
				assetDetailPageForm.setPoNumberFlag(result.getAsset().getAccount().isPoNumberFlag());			
				assetDetailPageForm.setCreditNumberFlag(result.getAsset().getAccount().isCreditNumberFlag());								
				assetDetailPageForm.setSalesOrganisation(result.getAsset().getAccount().getOrganizationName());
			}				
			
		}
			
		/*START--Added for Defect 3924-Jan Release*/
		commonController.assembleDevice(result.getAsset(), request.getLocale());
		/*END--Added for Defect 3924-Jan Release*/
		
		
		serviceRequest.setCostCenter(result.getAsset().getAssetCostCenter());
		assetDetailPageForm.setServiceRequest(serviceRequest);
		
		//Product Image
		ProductImageContract productImageContract = new ProductImageContract();
		productImageContract.setPartNumber(result.getAsset().getProductTLI());
		//Long retreveImageTime = System.currentTimeMillis();
		ProductImageResult productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
		
		//PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEPRODUCTIMAGEURL, retreveImageTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_PORTALIMAGEDB,productImageContract);
		LOGGER.info("start printing lex logger");
		//LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE PRODUCT IMAGE URL ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		LOGGER.info("end printing lex loggger");
		result.getAsset().setProductImageURL(productImageResult.getProductImageUrl());
		
		
					
		/***MPS 2.1 changes***/
		//for MPS 1.0 Customer there will be no payment types
		
		
		//removing part list from session before populating new part list
		portletSession.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST);
		portletSession.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);

		if(result.getAsset().getPartList()!=null){

			Set<String> distinctPaymentTypes = new HashSet<String>();
			List <Part> partList = new ArrayList<Part>();
			List<Part>  assetPartList = result.getAsset().getPartList();
			Map<String, String> requestTypeLOVMap=null;
			Map<String, String> paymentTypeList=new TreeMap<String, String>();
			for (Part list : assetPartList) {
				Part partListForm = new Part();
				partListForm.setPartNumber(list.getPartNumber());
				partListForm.setVendorPartNumber(list.getVendorPartNumber());
				partListForm.setDescription(list.getDescription());
				partListForm.setPartType(list.getPartType());//display
				partListForm.setCatalogId(list.getCatalogId());
				partListForm.setSupplyType(list.getConsumableType());
				partListForm.setSupplyId(list.getSupplyId());
				partListForm.setProductId(list.getProductId());
				partListForm.setPaymentTypes(list.getPaymentTypes()); //MPS 2.1 changes
				partListForm.setContractLineItemId(list.getContractLineItemId());
				partListForm.setContractNo(list.getContractNo());
				partListForm.setSoldToNumbers(list.getSoldToNumbers());
				
                partListForm.setPrefferedPartFlag(list.isPrefferedPartFlag());
                partListForm.setSalesOrg(list.getSalesOrg());
				
				LOGGER.debug("serialNumber="+result.getAsset().getSerialNumber()); 
				LOGGER.debug("@@@@@@@@@@@@@@ Part details number "+list.getPartNumber()+" part type "
						+list.getPartType()+" category "+list.getCategory()+" supplyType "+list.getSupplyType()+" consumbaleType "+
						list.getConsumableType()+" AssetUsageType "+list.getAssetUsageType()+" catalogid "+list.getCatalogId()+
						"list.getSupplyId() "+list.getSupplyId()+" list.getProductId() "+list.getProductId()+" Description "+list.getDescription());
				partList.add(partListForm);
				
				/***MPS 2.1 changes***/
				//for MPS 2.1 Customer there will be payment types. If not preset error would be displayed
				if(assetDetailPageForm.isSplitterFlag()){
					if(list.getPaymentTypes() != null){
						for(String paymentType: list.getPaymentTypes()){
							if(paymentType != null){
								if(!"".equals(paymentType)){
									distinctPaymentTypes.add(paymentType);
								
								}
							}
						}
					}else{
						assetDetailPageForm.setAssetPartList(partList);
						portletSession.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);// removing part																									// lis
						portletSession.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED,assetDetailPageForm.getAssetPartList());
					}
				
					
				}
			}
			
			if(assetDetailPageForm.isSplitterFlag()){
				/***start changes for Siebel Localization LOV***/
				try {
					requestTypeLOVMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONSUMABLE_PAYMENT_TYPE.getValue(), request.getLocale());
					
					
				} catch (LGSDBException e) {
					LOGGER.debug("Exception"+e.getMessage());
					LOGGER.debug("Database read Exception occured. ");
				}
				Iterator<String> it= distinctPaymentTypes.iterator();
		        while(it.hasNext()){
		        	Object value= it.next();
		        	Iterator itmap = requestTypeLOVMap.entrySet().iterator();
		        	while (itmap.hasNext()) {
		        		Map.Entry valuemap = (Map.Entry)itmap.next();
		        		
		        		if(value.toString().equalsIgnoreCase(valuemap.getKey().toString())){
							paymentTypeList.put(valuemap.getKey().toString(), valuemap.getValue().toString());
						}
		        	}
		        }
					
		        /***end changes for Siebel Localization LOV***/
				/***MPS 2.1 changes***/
				if(paymentTypeList.size()>0){
					if(paymentTypeList.size() == 1){
						Iterator itmapPT = paymentTypeList.entrySet().iterator();
			        	while (itmapPT.hasNext()) {
			        		Map.Entry valuemap = (Map.Entry)itmapPT.next();
			        		if(valuemap.getKey().toString().equalsIgnoreCase(ChangeMgmtConstant.PAYMENT_TYPE_PAY_NOW)){
			        			dsplPaymentTypeFlag = true;
			        	
			        		}
			        		assetDetailPageForm.setSelectedPaymentType(valuemap.getValue().toString());
			        		assetDetailPageForm.setSelectedPaymentTypeId(valuemap.getKey().toString());
			        	}
						
					}
					else{
						dsplPaymentTypeFlag = true;
						assetDetailPageForm.setPaymentTypes(paymentTypeList);
						
						
					}
				}
				
				assetDetailPageForm.setAssetPartList(partList);
				portletSession.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST);//removing part lis from session
				portletSession.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST, assetDetailPageForm.getAssetPartList());
			}
			else{
				assetDetailPageForm.setAssetPartList(partList);
				portletSession.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);//removing part lis from session
				portletSession.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED, assetDetailPageForm.getAssetPartList());
			}
			
			assetDetailPageForm.setDsplPaymentTypeFlag(dsplPaymentTypeFlag);
			
		}else{
			if(assetDetailPageForm.isSplitterFlag()){
				assetValidation = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
						"asset.data.validation.error", request.getLocale());
			}else{
				assetValidation = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
						"asset.data.validation.error.splitterFalse", request.getLocale());
			}
		}

		
		//check the page count part
		if(result.getAsset().getPageCounts()!=null){
			List<PageCounts> pageCountList = result.getAsset().getPageCounts();
			pageCountListSize = pageCountList.size();
			
			List<PageCounts> modifiedPageCountList = new ArrayList<PageCounts>();
			for(int i=0;i<pageCountList.size();i++){
				
				LOGGER.debug("!!!!!!!!!! page count value received from siebel is name "+pageCountList.get(i).getName()+
						" count "+pageCountList.get(i).getCount()+" date "+pageCountList.get(i).getDate());
				LocalizedPageCountNameResult pageCountResult = new LocalizedPageCountNameResult();
				LocalizedPageCountNameContract pageCountContract = new LocalizedPageCountNameContract();
				pageCountContract.setSiebelValue(pageCountList.get(i).getName());
				
				pageCountResult = serviceRequestLocaleService.retrieveLocalizedPageCountName(pageCountContract);
				
				PageCounts modifiedPageCount = new PageCounts();
				modifiedPageCount.setName(pageCountResult.getLocalizedValue());
				modifiedPageCount.setCount(pageCountList.get(i).getCount());
				
				// Added :The below code is to transfer the default date for Page count in the locale specific format 
				if(pageCountList.get(i).getDate()!= null && pageCountList.get(i).getDate()!=""){
					DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
					Date  defaultDateINGMT = parser.parse(pageCountList.get(i).getDate());
					LOGGER.debug("defaultDateINGMT order--->>>"+defaultDateINGMT.toString());					
					LOGGER.debug("The default date is to be shown--->>>"+DateUtil.convertToLocaleSpecificDateTime(defaultDateINGMT, request.getLocale()));					
					modifiedPageCount.setDate(DateUtil.convertToLocaleSpecificDateTime(defaultDateINGMT, request.getLocale()));							
				}
				//ends here
				modifiedPageCount.setSiebelName(pageCountList.get(i).getName());
				//Lets check if both the values are blank, we need to set the flag as true so that we can differentiate while sending the details to WM
				if((pageCountList.get(i).getCount()==""||pageCountList.get(i).getCount()==null)
						&&(pageCountList.get(i).getDate()==""||pageCountList.get(i).getDate()==null)){
					modifiedPageCount.setBothValueBlank(true);
				}
				modifiedPageCountList.add(modifiedPageCount);
				LOGGER.debug("Exit from the for loop");
			}
			assetDetailPageForm.setPageCountsList(modifiedPageCountList);
		}
		
		assetDetailPageForm.setAddressFlag(result.getAsset().isAddressFlag());		
		//page count end
		assetDetailPageForm.setAsset(result.getAsset());
		AccountContact accountContact = new AccountContact();
		accountContact = result.getAsset().getAssetContact();
		assetDetailPageForm.setAccountContact(accountContact);
		LOGGER.debug("Install address is "+result.getAsset().getInstallAddress().getAddressId()+
				" "+result.getAsset().getInstallAddress().getAddressLine1()+" "+result.getAsset().getInstallAddress().getCity()+
				" "+result.getAsset().getInstallAddress().getCountry());
		GenericAddress serviceAddress = new GenericAddress();
		result.getAsset().getInstallAddress();
		String addressId = result.getAsset().getInstallAddress().getAddressId();
		String storeFrontName = result.getAsset().getInstallAddress().getStoreFrontName();
		String addressLine1 = result.getAsset().getInstallAddress().getAddressLine1();
		String addressLine2 = result.getAsset().getInstallAddress().getAddressLine2();
		String city = result.getAsset().getInstallAddress().getCity();
		String state = result.getAsset().getInstallAddress().getState();
		String province = result.getAsset().getInstallAddress().getProvince();
		String postcode = result.getAsset().getInstallAddress().getPostalCode();
		String country = result.getAsset().getInstallAddress().getCountry();


		serviceAddress.setAddressId(addressId);
		serviceAddress.setStoreFrontName(storeFrontName);
		serviceAddress.setAddressLine1(addressLine1);
		serviceAddress.setAddressLine2(addressLine2);
		serviceAddress.setCity(city);
		serviceAddress.setState(state);
		serviceAddress.setProvince(province);
		serviceAddress.setPostalCode(postcode);
		serviceAddress.setCountry(country);
		assetDetailPageForm.setServiceAddress(serviceAddress);
		//assetDetailPageForm.setServiceAddress(addressFromMap);
		GenericAddress shipToAddress = new GenericAddress();
		shipToAddress = result.getAsset().getInstallAddress();
		shipToAddress.setIsAddressCleansed(true);//Added for MPS 2.1
		
		//Added by BRD #14-06-08
		LOGGER.debug("The asset physical Address are ---->>"+result.getAsset().getPhysicalLocationAddress().getPhysicalLocation1()+"----"+result.getAsset().getPhysicalLocationAddress().getPhysicalLocation2()+
				"-----"+result.getAsset().getPhysicalLocationAddress().getPhysicalLocation3());
		shipToAddress.setPhysicalLocation1(result.getAsset().getPhysicalLocationAddress().getPhysicalLocation1());
		shipToAddress.setPhysicalLocation2(result.getAsset().getPhysicalLocationAddress().getPhysicalLocation2());
		shipToAddress.setPhysicalLocation3(result.getAsset().getPhysicalLocationAddress().getPhysicalLocation3());
		assetDetailPageForm.setShipToAddress(shipToAddress);
		LOGGER.debug("Phisical Location 1------->>>"+assetDetailPageForm.getShipToAddress().getPhysicalLocation1());
		LOGGER.debug("Phisical Location 2------->>>"+assetDetailPageForm.getShipToAddress().getPhysicalLocation2());
		LOGGER.debug("Phisical Location 3------->>>"+assetDetailPageForm.getShipToAddress().getPhysicalLocation3());
		//Ends here*/
		
		assetDetailPageForm.setPhysicalLocationAddress(result.getAsset().getPhysicalLocationAddress());
		assetDetailPageForm.setDefaultSpecialInstruction(result.getAsset().getDefaultSpecialInstruction());
		
		assetDetailPageForm.setMaxPartsToBeOrdered(getMaxPartsToBeOrdered());
		//install date
		if(result.getAsset().getInstallDate()!=null){			
			assetDetailPageForm.setInstallDate(DateUtil.convertToLocaleSpecificDate(result.getAsset().getInstallDate(), request.getLocale())); //added
		}			 
		
		assetDetailPageForm.setListOfFileTypes(listOfFileTypes);
		assetDetailPageForm.setAttMaxCount(attMaxCount);
		model.addAttribute("pageCountListSize", pageCountListSize);
		model.addAttribute("assetDetailPageForm", assetDetailPageForm);
		model.addAttribute("attachmentFormDisplay", assetDetailPageForm);
		//Initializes the form for file upload
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		model.addAttribute("AssetNotValid", assetValidation);
		model.addAttribute("attachmentForm", new AttachmentForm());
		//Removes the Attachment Map if any exists from previous flows
		model.addAttribute("fleetManagementFlag", "true");
		
		portletSession.removeAttribute("attachmentList");
		portletSession.setAttribute("AssetResult", null,PortletSession.APPLICATION_SCOPE); // Removing this result from session, 
		LOGGER.debug("==== Exiting Util method populateSuppliesReuestForm =====");
	}catch (Exception e) {
		LOGGER.debug("Exception"+e.getMessage());
	}
	}
	
	/**
	 * @param request 
	 * @return Date 
	 * @throws Exception 
	 */
	private Date getCurrentDate(PortletRequest request) throws Exception{
		
		String dateFormatter = DateUtil.getDateFormatByLang(request.getLocale().getLanguage());
		DateFormat dateFormat = new SimpleDateFormat(dateFormatter);
		String currentDate = null;
		currentDate = dateFormat.format(new Date());		
		Date currentDateObj = DateUtil.getStringToLocalizedDate(currentDate, false, request.getLocale());
		String currentDateGMT = DateUtil.convertDateToGMTString(currentDateObj);
		
		Date parsedCurrentDate  = DATE_FORMAT.parse(currentDateGMT);
		
		return parsedCurrentDate;
	}
	
	
	/**
	 * @param request 
	 * @param response 
	 * @param assetId 
	 * @param model 
	 * @throws Exception 
	 */
	public void showServiceRequestSubmitPage2(ActionRequest request,
			ActionResponse response, String assetId, Model model)
			throws Exception {
		LOGGER.debug(assetId);
		ServiceRequestConfirmationForm serviceRequestConfirmationForm = new ServiceRequestConfirmationForm();
		ServiceRequest serviceRequest = new ServiceRequest();
		List<EntitlementServiceDetail> selectedServiceDetails = new ArrayList<EntitlementServiceDetail>();
		serviceRequest.setSelectedServiceDetails(selectedServiceDetails);
		AccountContact primaryContact = new AccountContact();
		serviceRequest.setPrimaryContact(primaryContact);
		LOGGER.debug(serviceRequest.getPrimaryContact().getContactId());
		AccountContact secondaryContact = new AccountContact();
		serviceRequest.setSecondaryContact(secondaryContact);
		LOGGER.debug(serviceRequest.getSecondaryContact().getContactId());
		GenericAddress serviceAddress = new GenericAddress();
		serviceRequest.setServiceAddress(serviceAddress);
		serviceRequestConfirmationForm.setServiceRequest(serviceRequest);
		model.addAttribute("fleetManagementFlag", "true");
		AssetContract contract = new AssetContract();
		contract.setAssetId(assetId);
		contract.setPageName(ChangeMgmtConstant.BREAKFIX_DEVICE_DETAIL);
		try{
			//PortletSession session = request.getPortletSession();
			
			//LEXLOGGER.info("start printing lex logger");
			//ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
			//LEXLOGGER.info("end printing lex loggger");
			// retrieve device by contract
			//Long startTime = System.currentTimeMillis();
			
			AssetResult assetResult = orderSuppliesAssetService.retrieveDeviceDetail(contract);		//  test mock call
			//LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Device DETAIL ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
			//PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEDEVICEDETAIL, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			if (assetResult != null && assetResult.getAsset() != null) {
				LOGGER.debug("the asset is not null!!!!!!!!!!!!!!!!!!!!!!!!!!![IN SHAREDPORTLETCONTROLLER]");
				Asset asset = assetResult.getAsset();
				
				assembleDevice(asset, request.getLocale()); /*Done for Defect 3924 -Jan Release*/
				// if the asset is not entitled, process the situation like not my printer
				if (asset.getEntitlement() == null || asset.getEntitlement().getServiceDetails() == null || asset.getEntitlement().getServiceDetails().size() == 0) {
					model.addAttribute("notEntitledFlag", true);
					return;
				}
				LocalizedEntitlementServiceListContract localizedEntitlementServiceListContract = new LocalizedEntitlementServiceListContract();
				localizedEntitlementServiceListContract
						.setEntitlementServiceDetails(asset.getEntitlement()
								.getServiceDetails());
				localizedEntitlementServiceListContract.setLocale(request
						.getLocale());
				LocalizedEntitlementServiceListResult result = serviceRequestLocaleService
						.retrieveLocalizedEntitlementServiceList(localizedEntitlementServiceListContract);
				asset.getEntitlement().setServiceDetails(
						result.getEntitlementServiceDetails());
				String imageUrl = retrieveProductImageUrl(asset.getProductTLI());
			    if(LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL.equalsIgnoreCase(imageUrl)) {
			    	imageUrl = getServerURLPrefix(request) + imageUrl;
			    }
			    asset.setProductImageURL(imageUrl);
				
				//remove work phone "\n" character if any
				String workPhone = asset.getAssetContact().getWorkPhone();
				LOGGER.debug("the work phone is ******************************" + workPhone);			
				if(workPhone !=null && workPhone != "") {
					int idx = workPhone.indexOf("\n");
					if(idx!=-1){					
						LOGGER.debug("workphone number has return character, and we will replace that with space");
						workPhone = workPhone.substring(0, idx) + " " + workPhone.substring(idx+1);
						LOGGER.debug("now the work phone is ******************************" + workPhone);
						asset.getAssetContact().setWorkPhone(workPhone);
					}
				}
				serviceRequestConfirmationForm.setAsset(asset);
				LOGGER.debug("IP fetched from form--------------------->>"+serviceRequestConfirmationForm.getAsset().getControlPanelURL());
				
			} else {
				LOGGER.debug("the asset is null!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				serviceRequestConfirmationForm.setAsset(new Asset());
			}
			
			LOGGER.debug("the name space of this portlet is "
					+ response.getNamespace());
			if (response.getNamespace().indexOf(
					LexmarkConstants.SERVICE_REQUEST_PORTLET_NAME) > -1) {
				LOGGER.debug("SERVICE_REQUEST_PORTLET_NAME FOUND");
				serviceRequestConfirmationForm
						.setHostingPortletName(LexmarkConstants.SERVICE_REQUEST_PORTLET_NAME);
			}
			if (response.getNamespace().indexOf(
					LexmarkConstants.DEVICE_FINDER_PORTLET_NAME) > -1) {
				LOGGER.debug("DEVICE_FINDER_PORTLET_NAME FOUND");
				serviceRequestConfirmationForm
						.setHostingPortletName(LexmarkConstants.DEVICE_FINDER_PORTLET_NAME);
			}
			
			
			// set login user contact information
			
			AccountContact accountContact = new AccountContact();
			accountContact.setContactId(PortalSessionUtil.getContactId(request
					.getPortletSession()));
			accountContact.setFirstName(PortalSessionUtil.getFirstName(request
					.getPortletSession()));
			accountContact.setLastName(PortalSessionUtil.getLastName(request
					.getPortletSession()));
			accountContact.setWorkPhone(PortalSessionUtil.getWorkPhone(request
					.getPortletSession()));
			accountContact.setEmailAddress(PortalSessionUtil
					.getEmailAddress(request.getPortletSession()));
			
			LOGGER.debug("Account contact ID"+accountContact.getContactId());
			LOGGER.debug("Account f name"+accountContact.getFirstName());
			serviceRequestConfirmationForm.setLoginAccountContact(accountContact);
			LOGGER.debug("CONTACT ID"+serviceRequestConfirmationForm.getLoginAccountContact().getContactId());
			
			serviceRequestConfirmationForm.getServiceRequest().setServiceAddress(serviceRequestConfirmationForm.getAsset().getInstallAddress());
			serviceRequestConfirmationForm.getServiceRequest().setPrimaryContact(serviceRequestConfirmationForm.getAsset().getAssetContact());
			
			model.addAttribute("serviceRequestConfirmationForm", serviceRequestConfirmationForm);
			serviceRequestConfirmationForm.refreshSubmitToken(request);
			model.addAttribute("attachmentFormDisplay", getAttachmentDetails());	// added for MPS breakfix
			LOGGER.exit("Exiting showServiceRequestSubmitPage2");
		}catch(Exception e){
			LOGGER.debug("Exception"+e.getMessage());
			String errorMessage = ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.unableToRetrieveDeviceDetail", null, request.getLocale());
			//throw new Exception(errorMessage+"^"+e.getMessage());
		}
	}
	
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
	}
	/**
	 * @param productTLI 
	 * @return String 
	 * @throws Exception 
	 */
	private String retrieveProductImageUrl(String productTLI) throws Exception {
		ProductImageContract productImageContract = new ProductImageContract();
		productImageContract.setPartNumber(productTLI);
		ProductImageResult productImageResult = productImageService
				.retrieveProductImageUrl(productImageContract);
		return productImageResult.getProductImageUrl();
	}
	
	/**
	 * @param request 
	 * @return String 
	 */
	private String getServerURLPrefix(ActionRequest request) {
		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);   
		String url = httpReq.getRequestURL().toString();
		String subUrl = getSubStringFromZeroToNthPattern(url , "/" , 3);
		return subUrl;
	}
	
	/**
	 * @param str 
	 * @param pattern 
	 * @param index 
	 * @return String  
	 */
	private String getSubStringFromZeroToNthPattern(String str , String pattern , int index){
		if(str==null)
			{return "";}
		int start = -1;
		int i = 0;
		int last = 0;
		int size = str.length();
		for (i = 0 ;i<index ;i++){
			if(size>start+1)
				{start = str.indexOf(pattern, (start+1));}
			else{
				start = -1;
				break;
				}
			if(start ==-1)
				{break;}
			
			last = start;
		}
		if(start!= -1)
			{return str.substring(0, last);}
		if(start==-1&& i==(index-1))
			{return str;}
		else
			{return "";}
	}
	
	/**
	 * @return FileUploadForm 
	 */
	private FileUploadForm getAttachmentDetails() {
		 FileUploadForm f = new FileUploadForm();
		 f.setAttachmentFileMap(new LinkedHashMap<String, FileObject>());
		 return f;
	 }
	
	/**
	 * @param gridId  
	 * @param request 
	 * @param response 
	 * @param model 
	 */
	public void retrieveGridSetting(String gridId, ActionRequest request, ActionResponse response,Model model) {
		boolean success = true;
		UserGridSettingContract contract = new UserGridSettingContract();
		contract.setGridId(gridId);
		UserGridSettingResult result = new UserGridSettingResult();
		try {
			LOGGER.debug("-------------retrieveCustomizedGridSetting---------");
			LOGGER.debug("-------gridId="+contract.getGridId());	
			contract.setUserNumber(LexmarkUserUtil.getUserId(request));
			UserGridSettingServiceImpl userGridSettingService = new UserGridSettingServiceImpl();
			result = userGridSettingService.retrieveUserGridSettings(contract);
			LOGGER.debug("==========gridId"+result.getGridId());
			LOGGER.debug("==========colsFilter"+result.getColsFilter());
			LOGGER.debug("==========colsHidden"+result.getColsHidden());
			LOGGER.debug("==========colsOrder"+result.getColsOrder());
			LOGGER.debug("==========colsWidth"+result.getColsWidth());
			LOGGER.debug("==========userNumber"+result.getUserNumber());
			success = true;
		} catch (Exception e) {
			success = false;
			LOGGER.debug("Error ------------------------"+e.getMessage());
		}
		finally{
			LOGGER.debug("success: " + success);
			model.addAttribute("gridSettings",result);
		}
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param assetId 
	 * @param serialNumber 
	 * @param machineType 
	 * @param productTLI 
	 * @param model 
	 * @throws Exception 
	 */ 
	public void showServiceRequestSubmitPage2FromDeviceNotFoundPage(
			ActionRequest request, ActionResponse response, String assetId,
			String serialNumber, String machineType, String productTLI, Model model)
			throws Exception {
		try{
		ServiceRequestConfirmationForm serviceRequestConfirmationForm = new ServiceRequestConfirmationForm();
		ServiceRequest serviceRequest = new ServiceRequest();
		List<EntitlementServiceDetail> selectedServiceDetails = new ArrayList<EntitlementServiceDetail>();
		serviceRequest.setSelectedServiceDetails(selectedServiceDetails);
		AccountContact primaryContact = new AccountContact();
		serviceRequest.setPrimaryContact(primaryContact);
		LOGGER.debug(serviceRequest.getPrimaryContact().getContactId());
		AccountContact secondaryContact = new AccountContact();
		serviceRequest.setSecondaryContact(secondaryContact);
		LOGGER.debug(serviceRequest.getSecondaryContact().getContactId());
		GenericAddress serviceAddress = new GenericAddress();
		serviceRequest.setServiceAddress(serviceAddress);
		serviceRequestConfirmationForm.setServiceRequest(serviceRequest);
		model.addAttribute("fleetManagementFlag", "true");
		
			AssetContract contract = new AssetContract();
			if (assetId != null && !assetId.equals("")) {
				contract.setAssetId(assetId);
				contract.setPageName(ChangeMgmtConstant.BREAKFIX_DEVICE_DETAIL);
				PortletSession session = request.getPortletSession();
				
				AssetResult assetResult = orderSuppliesAssetService.retrieveDeviceDetail(contract);
				
				if (assetResult != null && assetResult.getAsset() != null) {
					Asset asset = assetResult.getAsset();					
					assembleDevice(asset, request.getLocale());/*Done for Defect 3924-Jan Release*/
					LOGGER.debug("the asset is not null!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					CheckedEntitlementServiceDetailContract checkedEntitlementServiceDetailContract = new CheckedEntitlementServiceDetailContract();
					checkedEntitlementServiceDetailContract.setLocale(request
							.getLocale());
					CheckedEntitlementServiceDetailResult checkedEntitlementServiceDetailResult = serviceRequestLocaleService
							.retrieveCheckedEntitlementServiceDetail(checkedEntitlementServiceDetailContract);
					Entitlement e = new Entitlement();
					e.setServiceDetails(checkedEntitlementServiceDetailResult
							.getEntitlementServiceDetails());
					asset.setEntitlement(e);
					asset.setNotMyPrinter(true);
					LOGGER.debug("not my printer flag is *******************"
							+ asset.isNotMyPrinter());
					asset.setProductImageURL(retrieveProductImageUrl(asset.getProductTLI()));
					//added to remove the new line charecter from the phone no
					asset.getAssetContact().setWorkPhone(asset.getAssetContact().getWorkPhone().replace(" ", "").replace("\n", ""));
					LOGGER.debug("------correct-------->>>"+asset.getAssetContact().getWorkPhone());
					serviceRequestConfirmationForm.setAsset(asset);
				} else {
					LOGGER.debug("the asset is nulll!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					Asset a = new Asset();
					a.setNotMyPrinter(true);
					serviceRequestConfirmationForm.setAsset(a);
				}
			} else {
				Asset newAsset = new Asset();
				newAsset.setSerialNumber(serialNumber);
				CheckedEntitlementServiceDetailContract checkedEntitlementServiceDetailContract = new CheckedEntitlementServiceDetailContract();
				checkedEntitlementServiceDetailContract.setLocale(request
						.getLocale());
				CheckedEntitlementServiceDetailResult checkedEntitlementServiceDetailResult = serviceRequestLocaleService
						.retrieveCheckedEntitlementServiceDetail(checkedEntitlementServiceDetailContract);
				Entitlement e = new Entitlement();
				e.setServiceDetails(checkedEntitlementServiceDetailResult
						.getEntitlementServiceDetails());
				newAsset.setEntitlement(e);
				if (machineType != null && !machineType.equals(""))
					{newAsset.setModelNumber(machineType);}
				if (productTLI != null && !productTLI.equals("")) {
					newAsset.setProductTLI(productTLI);
				}
				newAsset.setProductImageURL(retrieveProductImageUrl(productTLI));
				newAsset.setNotMyPrinter(true);
				LOGGER.debug("machineType is ***********************"
						+ newAsset.getModelNumber());
				LOGGER.debug("productTLI is ***********************"
						+ newAsset.getProductTLI());
				LOGGER.debug("not my printer flag is *******************"
						+ newAsset.isNotMyPrinter());
				serviceRequestConfirmationForm.setAsset(newAsset);
			}
	
			/*ResourceURL resURL = response.createResourceURL();
			resURL.setResourceID("serviceAddressListURL");
			LOGGER.debug("the serviceAddressListURL is " + resURL.toString());
			serviceRequestConfirmationForm.setServiceAddressListURL(resURL
					.toString());
	
			ResourceURL resURL2 = response.createResourceURL();
			resURL2.setResourceID("contactListURL");
			LOGGER.debug("the contactListURL is " + resURL2.toString());
			serviceRequestConfirmationForm.setContactListURL(resURL2.toString());
	
			ResourceURL resURL3 = response.createResourceURL();
			resURL3.setResourceID("secContactListURL");
			LOGGER.debug("the secContactListURL is " + resURL3.toString());
			serviceRequestConfirmationForm.setSecContactListURL(resURL3.toString());*/
			LOGGER.debug("the name space of this portlet is "
					+ response.getNamespace());
			if (response.getNamespace().indexOf(
					LexmarkConstants.SERVICE_REQUEST_PORTLET_NAME) > -1) {
				LOGGER.debug("SERVICE_REQUEST_PORTLET_NAME FOUND");
				serviceRequestConfirmationForm
						.setHostingPortletName(LexmarkConstants.SERVICE_REQUEST_PORTLET_NAME);
			}
			if (response.getNamespace().indexOf(
					LexmarkConstants.DEVICE_FINDER_PORTLET_NAME) > -1) {
				LOGGER.debug("DEVICE_FINDER_PORTLET_NAME FOUND");
				serviceRequestConfirmationForm
						.setHostingPortletName(LexmarkConstants.DEVICE_FINDER_PORTLET_NAME);
			}
	
			// set login user contact information
			AccountContact accountContact = new AccountContact();
			accountContact.setContactId(PortalSessionUtil.getContactId(request
					.getPortletSession()));
			accountContact.setFirstName(PortalSessionUtil.getFirstName(request
					.getPortletSession()));
			accountContact.setLastName(PortalSessionUtil.getLastName(request
					.getPortletSession()));
			accountContact.setWorkPhone(PortalSessionUtil.getWorkPhone(request
					.getPortletSession()));
			accountContact.setEmailAddress(PortalSessionUtil
					.getEmailAddress(request.getPortletSession()));
			
			LOGGER.debug("Account contact ID"+accountContact.getContactId());
			LOGGER.debug("Account f name"+accountContact.getFirstName());
			serviceRequestConfirmationForm.setLoginAccountContact(accountContact);	
			
			LOGGER.debug("CONTACT ID"+serviceRequestConfirmationForm.getLoginAccountContact().getContactId());
			
			serviceRequestConfirmationForm.getServiceRequest().setServiceAddress(serviceRequestConfirmationForm.getAsset().getInstallAddress());
			serviceRequestConfirmationForm.getServiceRequest().setPrimaryContact(serviceRequestConfirmationForm.getAsset().getAssetContact());
			
			model.addAttribute("serviceRequestConfirmationForm", serviceRequestConfirmationForm);
			serviceRequestConfirmationForm.refreshSubmitToken(request);
			model.addAttribute("attachmentFormDisplay", getAttachmentDetails());	// added for MPS breakfix
			
			request.getPortletSession().setAttribute("serviceRequestConfirmationForm",serviceRequestConfirmationForm, PortletSession.APPLICATION_SCOPE);
			LOGGER.exit("Exiting showServiceRequestSubmitPage2FromDeviceNotFoundPage");
		}catch(Exception e){
			LOGGER.debug("Exception"+e.getMessage());
			String errorMessage = ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.unableToRetrieveDeviceDetail", null, request.getLocale());
			//throw new Exception(errorMessage+"^"+e.getMessage());
		}
	}

	/**
	 * @return String 
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
	 * @return String 
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
	 * @return String 
	 */
	public String getMaxPartsToBeOrdered() {
		return maxPartsToBeOrdered;
	}

	/**
	 * @param maxPartsToBeOrdered 
	 */
	public void setMaxPartsToBeOrdered(String maxPartsToBeOrdered) {
		this.maxPartsToBeOrdered = maxPartsToBeOrdered;
	}
	
}
