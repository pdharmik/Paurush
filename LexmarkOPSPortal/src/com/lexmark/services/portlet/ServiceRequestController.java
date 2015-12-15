/**
 * Copyright@ 2012. This document has been created & written by 
 * Wipro technologies for Lexmark and this is copyright to Lexmark 
 *
 * File         	: ServiceRequestController 
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


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.contract.GlobalAssetListContract;
import com.lexmark.contract.ManualAssetContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.AttachmentResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.ManualAssetResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.result.UpdateAssetMeterReadResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.ContactService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.MeterReadService;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindOrderSuppliesAssetService;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.BaseForm;
import com.lexmark.services.form.DeviceDetailForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.ServiceRequestConfirmationForm;
import com.lexmark.services.form.ServiceRequestListForm;
import com.lexmark.services.form.validator.AssetDetailPageFormValidator;
import com.lexmark.services.form.validator.FileUploadFormValidator;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ResourceResponseUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.DownloadFileUtil;
import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
@SessionAttributes("serviceRequestConfirmationForm")
public class ServiceRequestController extends BaseController {
	private static Logger logger = LogManager.getLogger(ServiceRequestController.class);
	private static String FILENAME_CSV;
	private static String[] SERVICEREQUEST_HEADER;
	
	@Autowired
	private SharedPortletController sharedPortletController;
	@Autowired
	private CommonController commonController; //Done CI6
	@Autowired
	private ServiceRequestService serviceRequestService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private OrderSuppliesAssetService orderSuppliesAssetService;
	@Autowired
	private ContactService contactService;
    @Autowired
    private GlobalService  globalService;
    @Autowired
    private ProductImageService  productImageService;
    @Autowired
    private FileUploadFormValidator fileUploadFormValidator;
    @Autowired
    private AssetDetailPageFormValidator assetDetailPageFormValidator;
    //Added for update Page Counts popup
    @Autowired
	private MeterReadService meterReadService;
    // Added for Printer Error Code
    @Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
    //Ends
	
    private static final String[] serviceRequestColumns = new String[] { "serviceRequestNumber", "serviceRequestNumber", "serviceRequestStatusDate", "asset.serialNumber", "asset.modelNumber","problemDescription","serviceAddress.addressName", "serviceRequestStatus", "serviceAddress.city", "serviceAddress.state","serviceAddress.province", "serviceAddress.postalCode", "serviceAddress.country", "custRefNumber", "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone"};
    
    private static final Logger LOGGER = LogManager.getLogger(ServiceRequestController.class);//added CI6
    private static Logger perfLogger = LogManager.getLogger("performance");
	
    @Autowired
    private AttachmentService attachmentService;
    
	@RequestMapping(params="action=showBreakFixSRList")//RequestMapping params added by sagar
	public String showServiceRequestListPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		boolean createServiceRequestFlag = sharedPortletController.isCreateServiceRequestFlag(request);
		sharedPortletController.checkAccountTypeFlagsInSession(request);
		PortletSession session = request.getPortletSession();
		String accountTypeMPSFlag = (String) session.getAttribute(SharedPortletController.ACCOUNT_TYPE_MPS_FLAG);
		String accountTypeCSSFlag = (String) session.getAttribute(SharedPortletController.ACCOUNT_TYPE_CSS_FLAG);
		model.addAttribute(SharedPortletController.ACCOUNT_TYPE_MPS_FLAG, accountTypeMPSFlag);
		model.addAttribute(SharedPortletController.ACCOUNT_TYPE_CSS_FLAG, accountTypeCSSFlag);

		
		ServiceRequestListForm serviceRequestListForm = new ServiceRequestListForm();
		
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("downloadServiceRequestsURL");
		model.addAttribute("downloadServiceRequestsURL", resURL.toString());
		serviceRequestListForm.setCreateServiceRequestFlag(createServiceRequestFlag);
		//Add Device Location tree
		serviceRequestListForm.setDeviceLocationTreeXMLURL(sharedPortletController.getDeviceLocationXMLURL(response));
		//Add CHL node tree
		serviceRequestListForm.setChlTreeXMLURL(sharedPortletController.getCHLTreeXMLURL(response));
		// Retrieve Grid Setting
		retrieveGridSetting("serviceRequestListGrid",request,response,model);
		model.addAttribute("serviceRequestListForm", serviceRequestListForm);
		return "serviceRequest/serviceRequestListPage";
	}
	
	
	@RequestMapping
	public String showServiceRequestPrinterSelectionPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		logger.debug("-------------showAssetSelectionPage started---------");
		
		ResourceURL url = response.createResourceURL();
		url.setResourceID("retriveDeviceList");
		model.addAttribute("retriveDeviceList", url.toString());	
		ResourceURL url2 = response.createResourceURL();
		url2.setResourceID("showGlobalDeviceResultsURL");
		model.addAttribute("showGlobalDeviceResultsURL", url2.toString());		
		ServiceRequestConfirmationForm serviceRequestConfirmationForm = new ServiceRequestConfirmationForm();
		String loginUserContactId = PortalSessionUtil.getContactId(request.getPortletSession());
		AccountContact accountContact = new AccountContact();
		accountContact.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		serviceRequestConfirmationForm.setLoginAccountContact(accountContact);	
		// Retrieve Grid Setting
		retrieveGridSetting("serviceDeviceListGrid",request,response,model);

		Locale locale = request.getLocale();
		String URLPart = "&locale=" + locale.getLanguage() + "&userlocale=" +
		locale.getLanguage() + "_" + locale.getCountry();

		String helpURL = PropertiesMessageUtil.getPropertyMessage(
				"com.lexmark.services.resources.hostConfig",
				"printerSelectionHelpHost", Locale.US) + URLPart;
		
		model.addAttribute("serviceRequestConfirmationForm", serviceRequestConfirmationForm);
		model.addAttribute("helpURL" , helpURL);
		logger.debug("-------------showAssetSelectionPage ended---------");
		
		/*********** Below Section is for forwarding the requests to Create Page coming from Device Mgmt *******/
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		
		final String assetId=httpReq.getParameter("assetId");
		
		if(assetId !=null)
		{
			logger.debug("Go for forwarding the request to Create page");
			return showSRSubmitPage2(request, response,assetId, model);
		}
		/****************End of the section ********************/
		
		else
		{		
			return "serviceRequest/serviceRequestSelectAsset";
		}
		
	}
	
	/**
	 * This method is for redirecting the user to create SR's with proper access
	 * @param request
	 * @param response
	 * @param assetId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (params = "action=showServiceRequestPage2")
	public String showSRSubmitPage2(RenderRequest request,
			RenderResponse response,@RequestParam("assetId") String assetId,
				Model model) throws Exception {
		sharedPortletController.showServiceRequestSubmitPage2(request, response, assetId, model);
		retrieveGridSetting("gridContainerDiv_all_request_types",request,response,model);//Loading data from db-CI BRD13-10-02
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		if (model.containsAttribute("notEntitledFlag")) {
			sharedPortletController.showServiceRequestSubmitPage2FromDeviceNotFoundPage(
					request, response, assetId, null, null, null, model);
		}
		return "serviceRequest/serviceRequestPage2";
	}
	
	@RequestMapping(params = "action=checkAccountSRPrivilege")
	public void showServiceRequestPrinterSelectionPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		logger.debug("------ACTION: gotoSRSelectAsset Started----------");
		response.setRenderParameter("action", "displayAssetSelection");
		logger.debug("------ACTION: gotoSRSelectAsset End----------");
	}
	
	
	@RequestMapping(params = "action=serviceRequestDrillDown")
	public String showServiceRequestDrillDownPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		logger.debug("-------------serviceRequestDrillDown started---------");
		sharedPortletController.showServiceRequestDrillDownPage(request, response, model);
		// Retrieve Grid Setting
		retrieveGridSetting("servicesHistoryGrid",request,response,model);
		return "serviceRequest/serviceRequestDrillDown";
	}

	@RequestMapping(params = "action=serviceRequestDrillDownLightBox")
	public String showServiceRequestDrillDownLightBox(RenderRequest request, RenderResponse response, Model model) throws Exception{
		logger.debug("-------------serviceRequestDrillDownLightBox started---------");
		sharedPortletController.showServiceRequestDrillDownPage(request, response, model);
		return "serviceRequest/serviceRequestDrillDownLightBox";
	}
	
	@RequestMapping(params = "action=printServiceRequests")
	public String showServiceRequestPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "serviceRequest/serviceRequestsPrint";
	}

	@RequestMapping(params = "action=showServiceRequestDrillDownPrintPage")
	public String showServiceRequestDrillDownPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "serviceRequest/serviceRequestDrillDownPrint";
	}
	
	@ResourceMapping("updateUserFavoriteAsset")
	public void updateUserFavoriteAsset(@RequestParam("favoriteAssetId") String favoriteAssetId,
			@RequestParam("favoriteFlag") boolean favoriteFlag,
			ResourceRequest request, ResourceResponse response) throws Exception {

		
		commonController.updateUserFavoriteAsset(favoriteAssetId, favoriteFlag, request, response);
		}
	
	@RequestMapping(params = "action=thankYouPrint")
	public String showThankYouPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "serviceRequest/thankYouPrint";
	}
	
	@RequestMapping(params = "action=showServiceRequestConfirmationPrintPage")
	public String showServiceRequestConfirmationPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "serviceRequest/serviceRequestConfirmationPrint";
	}
	

	@RequestMapping(params = "action=showServiceRequestConfirmation")
	public String showServiceRequestConfirmationPage(RenderRequest request, RenderResponse response, Model model)throws Exception{
		return "serviceRequest/thankYou";		
	}
	
	@RequestMapping(params = "action=showWaitPage")
	public String showWaitPage(RenderRequest request, RenderResponse response, Model model)throws Exception{		
		return "serviceRequest/wait";
	}
	
	@RequestMapping(params = "action=thankYouEmail")
	public String showThankYouEmailPage(RenderRequest request, RenderResponse response, Model model)throws Exception{
		return "serviceRequest/thankYouEmail";		
	}

	@RequestMapping (params = "action=showServiceRequestPage2_brkfix")
	public String showServiceRequestSubmitPage2(RenderRequest request,
			RenderResponse response,@RequestParam("assetId") String assetId,
			@RequestParam("notMyPrinterFlag") String notMyPrinterFlag,
			@RequestParam("serialNumber") String serialNumber,
			@RequestParam("machineType") String machineType,
			@RequestParam("productTLI") String productTLI,
				Model model) throws Exception {
		try
		{
			ServiceRequestConfirmationForm serviceRequestConfirmationForm = new ServiceRequestConfirmationForm();
			FileUploadForm fileUploadForm = new FileUploadForm();
			model.addAttribute("fileUploadForm", fileUploadForm);
			retrieveGridSetting("gridContainerDiv_all_request_types",request,response,model);//Loading data from db-CI BRD13-10-02
		}
		catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
		}
		
		if (!"true".equals(notMyPrinterFlag)) {
			sharedPortletController.showServiceRequestSubmitPage2(request, response, assetId, model);
		}
		if("true".equals(notMyPrinterFlag) || !"true".equals(notMyPrinterFlag) &&
				model.containsAttribute("notEntitledFlag")) {
			sharedPortletController.showServiceRequestSubmitPage2FromDeviceNotFoundPage(
					request, response, assetId, serialNumber, machineType, productTLI, model);


		}
		
//		added for MPS breakfix
//		this will remove any attachments from session during new SR create
		PortletSession session = request.getPortletSession();
		session.removeAttribute("attachmentsMap");		
//		end of addition for MPS breakfix 

		/* you can still add model attribute here using "model.addAttribute",
		 * which allow you add need extra information in the page,

		 * like dynamic tree list URL in device finder
		 */
		
		// Added For Printer Error Code for CI 13.10.17
		Map<String, String> requestTypeLOVMap=null;
		try {
			requestTypeLOVMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PRINTER_ERROR.getValue(), request.getLocale());
			
		} catch (LGSDBException e) {
			logger.debug("LGS DB Exception "+e.getMessage());
		}
		model.addAttribute("assetID",assetId);
		model.addAttribute("requestTypeLOVMap", requestTypeLOVMap);
		return "serviceRequest/serviceRequestPage2";
	}
	
	@RequestMapping (params = "action=submitServiceRequest")
	public void submitServiceRequest(ActionRequest request, ActionResponse response,
			@ModelAttribute("serviceRequestConfirmationForm") ServiceRequestConfirmationForm
			serviceRequestConfirmationForm, @ModelAttribute ("fleetManagementFlag") String fleetManagementFlag,@ModelAttribute("fileUploadForm") FileUploadForm   // FileUploadForm Added Printer Error Code  for CI 13.10.17
			fileUploadForm, BindingResult result,
			Model model) throws Exception {
		PortletSession session = request.getPortletSession();
		List<String> errorList = new ArrayList<String>();
		boolean hasExceptions = false;
		// If the asset with service request is manually added
		if (StringUtil.isStringEmpty(serviceRequestConfirmationForm.getAsset().getAssetId())) {
			AccountContact primaryContact = serviceRequestConfirmationForm.getServiceRequest().getPrimaryContact();
			serviceRequestConfirmationForm.getAsset().setAssetContact(primaryContact);
		}
		
		//LBS
		//String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			request.setAttribute("fleetManagementFlag", "true");
			model.addAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			request.setAttribute("fleetManagementFlag","false");
			model.addAttribute("fleetManagementFlag", "false");
		}
		
		model.addAttribute("serviceRequestType", "normalServiceRequest");
		model.addAttribute("fileUploadForm", fileUploadForm); 								    // FileUploadForm Added Printer Error Code  for CI 13.10.17
		model.addAttribute("errorcode", serviceRequestConfirmationForm.getPrinterErrorCode());  // Added for Printer Error Code for CI 13.10.17
		try {
			sharedPortletController.submitServiceRequest(request, response, serviceRequestConfirmationForm, result, model);
		} catch (SiebelCrmServiceException e) {
			// TODO: handle exception
			errorList.add(e.getMessage());
		} 
		catch (LGSBusinessException e) {
			// TODO: handle exception
			e.getMessage();
			errorList.add(e.getMessage());
		}
		catch (Exception e) {
			// TODO: handle exception
			errorList.add(e.getMessage());
		}
		
		if(!errorList.isEmpty()){
			hasExceptions = true;
			for(String error : errorList) {
				logger.debug("\nError: " + error);
			}
			model.addAttribute("exceptionList", errorList);	
		}
		
		if(hasExceptions) {
//			this is to enable re-submit of SR form on submit/draft exception
			Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, session.PORTLET_SCOPE);
			BaseForm baseForm = (BaseForm)serviceRequestConfirmationForm;
			baseForm.setSubmitToken(tokenInSession);
			
			model.addAttribute(serviceRequestConfirmationForm);
			response.setRenderParameter("action", "showSRPageWithExceptions");
		} else {
			logger.debug("no exceptions - redirecting to confirmation page");
			response.setRenderParameter("action", "showServiceRequestConfirmation");
		}
//		end of addition for breakfix MPS
		
	}
	
	@RequestMapping(params = "action=showCreateNewPage")
	public String showCreateNewPage(RenderRequest request,
			RenderResponse response, @RequestParam("gridName") String gridName,
			Model model)throws Exception {

		return sharedPortletController.showCreateNewPage(request, response, gridName, model);
	}
	
	
	
	@RequestMapping(params = "action=showEditPage")
	public String showEditContactPage(RenderRequest request,
			RenderResponse response, @RequestParam("gridName") String gridName, @RequestParam("contactId") String contactId,
			@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName, 
			@RequestParam("workPhone") String workPhone, @RequestParam("emailAddress") String emailAddress,
			Model model) {


		String returnPage = sharedPortletController.showEditContactPage(request, response, gridName, contactId, lastName, firstName, workPhone, emailAddress, model);
		return returnPage;
	}

	@RequestMapping(params = "action=showDeviceAdvancedSearchPage")
	public String showDeviceAdvancedSearchOptionPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		sharedPortletController.retrieveAdvanceSearchData(request, model);
		return "deviceAdvancedSearch";
		
	}

	@RequestMapping(params = "action=showSRListAdvancedSearchPage")
	public String showSRListAdvancedSearchOptionPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		sharedPortletController.retrieveAdvanceSearchData(request, model);
		return "serviceRequest/serviceRequestAdvancedSearch";
		
	} 

	@RequestMapping(params = "action=showSRDrillDownEmailSendingPage")
	public String showEmailNotificationSendingPage(RenderRequest request, RenderResponse response, Model model)
			throws Exception {

		return "serviceRequest/serviceRequestDrillDownEmail";
	}
	
	@RequestMapping(params = "action=showNotificationDescriptionDetail")
	public String showNotificationDescriptionDetail(RenderRequest request, RenderResponse response, Model model)
			throws Exception { 

		return "serviceRequest/notificationDescriptionDetail";
	}


	//*********************** START OF RESOURCE MAPPING ***************************//
	@ResourceMapping(value="showDeviceResultsURL")
	public void showDeviceResults(ResourceRequest request, ResourceResponse response) throws Exception{
		AssetListContract contract = ContractFactory.getAssetListContract(request);
		
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);

			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveDeviceList", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			ObjectDebugUtil.printObjectContent(contract, logger);
			long timeBeforeCall=System.currentTimeMillis();
			AssetListResult assetListResult = orderSuppliesAssetService.retrieveDeviceList(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEDEVICELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			PerformanceTracker.endTracking(lexmarkTran);

			List<Asset> assetList = assetListResult.getAssets();
			String content = getXmlOutputGenerator(request.getLocale()).assetsToDataViewXml(assetList,assetListResult.getTotalCount(), contract.getStartRecordNumber());
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(content);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
	}
	
	//for global asset retrieving
	@ResourceMapping(value="showGlobalDeviceResultsURL")
	public void showGlobalDeviceResults(ResourceRequest request, ResourceResponse response) throws Exception{
		GlobalAssetListContract contract = ContractFactory.getGlobalAssetListContract(request);
		PortletSession session = request.getPortletSession();
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveGlobalAssetList", 
				PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
		ObjectDebugUtil.printObjectContent(contract, logger);
		long timeBeforeCall=System.currentTimeMillis();
		AssetListResult assetListResult = deviceService.retrieveGlobalAssetList(contract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SERVICEREQUEST_MSG_RETRIEVEGLOBALASSETLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		PerformanceTracker.endTracking(lexmarkTran);
		
		List<Asset> assetList = assetListResult.getAssets();
		
		for (Asset device : assetList) {
			ProductImageContract productImageContract = new ProductImageContract();
			productImageContract.setPartNumber(device.getProductTLI());
			long timeBeforeServiceCall=System.currentTimeMillis();
			ProductImageResult productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEPRODUCTIMAGEURL, timeBeforeServiceCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_PORTALIMAGEDB,productImageContract);
			device.setProductImageURL(productImageResult.getProductImageUrl());
			assembleDevice(device, request.getLocale());
		}

		String content = getXmlOutputGenerator(request.getLocale()).assetsToDataViewXml(assetList,assetListResult.getTotalCount(), 0);
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
	}
	
	@ResourceMapping("retrieveBreakfixSRList")
	public void retrieveServiceRequestList(ResourceRequest request, ResourceResponse response) throws Exception{
		logger.debug("------------- Step 1---retrieveServiceRequestList started---------["+System.nanoTime()+"]");
		
		String pageType = request.getParameter("pageName");
		
		String contactId = PortalSessionUtil.getContactId(request.getPortletSession());
// Added by Dwijen
	
			String[] generatorPatterns = new String[] { "serviceRequestNumber", "serviceRequestStatusDate", 
					"asset.serialNumber", "asset.modelNumber", "problemDescription", "serviceAddress.addressName", 
					"serviceRequestStatus", "serviceAddress.city", "serviceAddress.state", 
					"serviceAddress.province",	"serviceAddress.postalCode", "serviceAddress.country", 
					"custRefNumber", "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", 
					"primaryContact.workPhone"};
	// end
		RequestListContract contract = ContractFactory
		.getHistoryListContract(request, "ServiceRequestList",
				serviceRequestColumns);
		
		loadFilterCriteria(request,contract);
		
		PortletSession session = request.getPortletSession();

		if(pageType != null) {
				if((LexmarkConstants.VIEWTYPE_MANAGED_DEVICES).equalsIgnoreCase(pageType)) {
				contract.setAssetType(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES);
			} else if((LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES).equalsIgnoreCase(pageType)) {
				contract.setAssetType(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES);
			}
		}
		session.setAttribute("downLoadContract", contract);
		ObjectDebugUtil.printObjectContent(contract,logger);
		
		sharedPortletController.retrieveServiceRequestList(contract, request, response, generatorPatterns,true);

		logger.debug("------------- Step 1---retrieveServiceRequestList end---------["+System.nanoTime()+"]");
	}
	
	@ResourceMapping(value="retrieveAssociatedServiceTicketList")
	public void retrieveAssociatedServiceTicketList(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{

		sharedPortletController.retrieveAssociatedServiceTicketList(request, response, model);
	}
	
	@ResourceMapping(value="retrieveServiceRequestHistoryList")
	public void retrieveServiceRequestHistoryList(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{

		sharedPortletController.retrieveServiceRequestHistoryList(request, response, model);
	}
	
	@ResourceMapping(value="retrieveServiceRequestHistoryListDrillDown")
	public void retrieveServiceRequestHistoryListDrillDown(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{

		sharedPortletController.retrieveServiceRequestHistoryListDrillDown(request, response, model);
	}

	@ResourceMapping(value="contactListURL")
	public void retrievePrimaryContactListURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{			

		sharedPortletController.retrievePrimaryContactListURL(request, response, model);
	}
	
	@ResourceMapping(value="secContactListURL")
	public void retrieveSecondaryContactListURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{			

		sharedPortletController.retrieveSecondaryContactListURL(request, response, model);
	}
	
	@ResourceMapping(value="serviceAddressListURL")
	public void retrieveServiceAddressListURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{

		sharedPortletController.retrieveServiceAddressListURL(request, response, model);
	}
	
	
	/**
	 *  retrieve Services Requests and down load it csv and pdf format.

	 * @param request
	 * @throws Exception
	 */
	@ResourceMapping("downloadServiceRequestsURL")
	public void downloadServiceRequestsURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		String downloadType = request.getParameter("downloadType");
		Locale locale = request.getLocale();
		PortletSession session = request.getPortletSession();
		ServiceRequestListContract contract =  (ServiceRequestListContract) session.getAttribute("downLoadContract");
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);

			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveServiceRequestList", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			long timeBeforeCall=System.currentTimeMillis();
			ServiceRequestListResult serviceRequestListResult = serviceRequestService.retrieveServiceRequestList(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_RETRIEVEASSOCIATEDSERVICEREQUESTLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			PerformanceTracker.endTracking(lexmarkTran);		
			List<ServiceRequest> serviceRequests = serviceRequestListResult.getServiceRequests();
			if("csv".equals(downloadType)){
				downloadServiceRequestCSV(response,serviceRequests,locale);
			}else if("pdf".equals(downloadType)){
				downloadPDF(response,serviceRequests,locale);
			}else{
				throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.portlet.downloadException", null, request.getLocale()));
			}
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
	}
	
	@ResourceMapping("chlTreeXMLURL")
	public void retrieveCHLTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{

		sharedPortletController.retrieveCHLTreeXML(request, response, model);
	}

	@ResourceMapping("deviceLocationXMLURL")
	public void retrieveDeviceLocationTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		String pageFrom="";
		sharedPortletController.retrieveDeviceLocationTreeXML(request, response, model,pageFrom);
	}

	@ResourceMapping("getState")
	public void getState(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{

		logger.debug("Request value inside the controller "+request.getParameter("countryCode"));
		sharedPortletController.getState(request, response, model);
	}
	//*********************** END OF RESOURCE MAPPING ***************************//
	



	private void downloadServiceRequestCSV(ResourceResponse response, List<ServiceRequest> serviceRequestList,
			Locale locale) throws IOException {

		FILENAME_CSV = DownloadFileLocalizationUtil.getServiceRequestsCSVFileName(locale);
		response.setProperty("Content-disposition", "attachment; filename="
				+ FILENAME_CSV);

		response.setContentType("text/csv");
		PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
		out.print(DownloadFileUtil.assembleToServiceRequestCSV(serviceRequestList, locale));
		out.flush();
		out.close();
	}
	


	private void downloadPDF(ResourceResponse response, List<ServiceRequest> serviceRequestList,
			Locale locale) throws IOException {

		String fileName = DownloadFileLocalizationUtil.getServiceRequestsPDFFileName(locale);
		SERVICEREQUEST_HEADER = DownloadFileLocalizationUtil.getServiceRequestsPDFHeaders(locale);
		//TODO figure out where to configure generatorPatterns
		String[] generatorPatterns = new String[]{"serviceRequestNumber","serviceRequestStatusDate",
				"asset.serialNumber", "asset.modelNumber","problemDescription","asset.physicalLocation1",
				"serviceRequestStatus","serviceAddress.city","serviceAddress.state",
				"serviceAddress.province","serviceAddress.postalCode","serviceAddress.country",
				"custRefNumber","primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress",
				"primaryContact.workPhone"};
		
		sharedPortletController.downloadPDF(response, serviceRequestList, locale, SERVICEREQUEST_HEADER, generatorPatterns, fileName);

	}
	
	private void setBreadCrumbToSRListForm(ServiceRequestListForm serviceRequestListForm , String pageType)
	{

		if((LexmarkConstants.PAGETYPE_RECENTSRLIST).equals(pageType))
			{serviceRequestListForm.setBreadCrumb(LexmarkConstants.SERVICE_REQUEST_BREADCRUMB_RECENT);}
		else if((LexmarkConstants.PAGETYPE_MYSRLIST).equals(pageType))
			{serviceRequestListForm.setBreadCrumb(LexmarkConstants.SERVICE_REQUEST_BREADCRUMB_MY);}
		else if("branch".equals(pageType))
			{serviceRequestListForm.setBreadCrumb("branch");}
		else 
			{serviceRequestListForm.setBreadCrumb("");}


	}

	
	@ResourceMapping("setContactFavouriteFlag")
	public void setContactFavouriteFlag(@RequestParam("contactId") String contactId,
			@RequestParam("favoriteContactId") String favoriteContactId,
			@RequestParam("flagStatus") boolean flagStatus,
			ResourceRequest request, ResourceResponse response) throws Exception {

		boolean success = false;
		try {
			FavoriteContract favoriteContract = ContractFactory.getFavoriteContract(request);
			FavoriteResult favoriteResult;
			favoriteContract.setFavoriteContactId(favoriteContactId);
			favoriteContract.setContactId(contactId);
			favoriteContract.setFavoriteFlag(!flagStatus);
			
			PortletSession session = request.getPortletSession();		
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "updateUserFavoriteContact", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			long timeBeforeCall=System.currentTimeMillis();
			favoriteResult = contactService.updateUserFavoriteContact(favoriteContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_UPDATEUSERFAVORITECONTACT, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,favoriteContract);
			PerformanceTracker.endTracking(lexmarkTran);
			success = favoriteResult.isResult();
		} catch (Exception e) {
			success = false;
		}
		String errorCode = success?"message.servicerequest.setContactFavouriteFlag"
				:"exception.servicerequest.setContactFavouriteFlag";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteContactId+"\"");
	}
	
	@ResourceMapping("setServiceAddressFavouriteFlag")
	public void setServiceAddressFavouriteFlag(@RequestParam("favoriteAddressId") String favoriteAddressId,
			@RequestParam("contactId") String contactId,
			@RequestParam("flagStatus") boolean flagStatus,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		//Changes for CI BRD 13-10-08 --STARTS
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {		
			FavoriteAddressContract favoriteAddressContract	=  ContractFactory.getFavoriteAddressContract();
			FavoriteResult favoriteResult;
			favoriteAddressContract.setSessionHandle(crmSessionHandle);
			favoriteAddressContract.setFavoriteAddressId(favoriteAddressId);
			favoriteAddressContract.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
			favoriteAddressContract.setFavoriteFlag(!flagStatus);
			
			PortletSession session = request.getPortletSession();

			logger.debug("-------------before setting contract favourite---------");		
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "updateUserFavoriteAddress", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			LOGGER.info("start printing lex LOGGER");
			ObjectDebugUtil.printObjectContent(favoriteAddressContract, LOGGER);
			LOGGER.info("end printing lex loggger");
			//Changes for CI BRD 13-10-08 --ENDS
			long timeBeforeCall=System.currentTimeMillis();
			favoriteResult = serviceRequestService.updateUserFavoriteAddress(favoriteAddressContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_UPDATEUSERFAVORITEADDRESS, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,favoriteAddressContract);
			PerformanceTracker.endTracking(lexmarkTran);
			logger.debug("-------------setContactFavourite end---------");
			success = favoriteResult.isResult();
		} catch (Exception e) {
			success = false;
		}
		logger.debug("success: "+success);
		String errorCode = success?"message.servicerequest.setServiceAddressFavouriteFlag"
				:"exception.servicerequest.setServiceAddressFavouriteFlag";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteAddressId+"\"");
	}
	
	//validate manual asset
	@ResourceMapping("validateManualAsset")
	public void validateManualAsset(@RequestParam("machineType") String machineType,
			@RequestParam("productTLI") String productTLI,
			ResourceRequest request, ResourceResponse response) throws Exception {

		boolean success = false;
		try {
			logger.debug("-------------validateManualAsset started---------");
			ManualAssetContract contract = new ManualAssetContract();
			logger.debug("the machine type is " + machineType);
			logger.debug("the product TLI is " + productTLI);
			if(machineType!=null)
				{contract.setModelNumber(machineType);}
			if(productTLI!=null)
				{contract.setProductTLI(productTLI);}
			PortletSession session = request.getPortletSession();
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "validateManualAsset", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			long timeBeforeCall=System.currentTimeMillis();
			ManualAssetResult mar = serviceRequestService.validateManualAsset(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SERVICEREQUEST_MSG_VALIDATEMANUALASSET, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
			PerformanceTracker.endTracking(lexmarkTran);
			logger.debug("-------------validateManualAsset end---------");
			success = mar.getResult();
		} catch (Exception e) {
			success = false;
		}
		logger.debug("success: "+success);
		//note: following msg error is never used, but they are need for getting the 
		//response call working
		String errorCode = success?"message.servicerequest.validateManualAsset"
				:"exception.servicerequest.validateManualAsset";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
	}
	
	private void loadFilterCriteria(ResourceRequest request, RequestListContract contract){
		if (request.getParameter("country") != null) {
			contract.getFilterCriteria().put("serviceAddress.country", request.getParameter("country"));
		}
		if (request.getParameter("province") != null) {
			contract.getFilterCriteria().put("serviceAddress.province", request.getParameter("province"));
		}
		if (request.getParameter("state") != null) {
			contract.getFilterCriteria().put("serviceAddress.state", request.getParameter("state"));
		}
		if (request.getParameter("city") != null) {
			contract.getFilterCriteria().put("serviceAddress.city", request.getParameter("city"));
		}
		if (request.getParameter("chlNodeId") != null) {
			contract.setChlNodeId(request.getParameter("chlNodeId"));
		}
	}

	@ResourceMapping ("retriveDeviceList")
	public void retriveDeviceList(ResourceRequest request, ResourceResponse response) throws Exception{
		logger.debug("------------- Step 1---ServiceRequestController.retriveDeviceList started---------["+System.nanoTime()+"]");
		//changes done to remove the viewmore link from grid
		request.setAttribute("showLinksMore", "false");
		//Ends
		AssetListContract contract = ContractFactory.getAssetListContract(request);
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			loadFilterCriteria(request, contract);
			// Prepare downLoadContract into session for downloading
			session.setAttribute("downLoadContract", contract);
			PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
			PortletSession portletSession = portletRequest.getPortletSession();
			portletSession.removeAttribute("searchName",portletSession.APPLICATION_SCOPE);
			portletSession.removeAttribute("searchValue",portletSession.APPLICATION_SCOPE);

			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retriveDeviceList", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			String DATEFORMAT = "MM/dd/yyyy" ;
		    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		    final String utcTime = sdf.format(new Date());
			contract.setEntitlementEndDate(utcTime);
			logger.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			logger.debug("---->>> before the contract-->>>");
			ObjectDebugUtil.printObjectContent(contract, logger);
			logger.debug("---->>>> after the contract------>>>>");
			
			long timeBeforeCall=System.currentTimeMillis();
			AssetListResult deviceListResult = orderSuppliesAssetService.retrieveAllDeviceList(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.COMMON_MSG_RETRIEVEALLDEVICELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			PerformanceTracker.endTracking(lexmarkTran);

			List<Asset>  deviceList = deviceListResult.getAssets();


			for (Asset device : deviceList) {
				assembleDevice(device, request.getLocale());
			}
			String content = getXmlOutputGenerator(request.getLocale()).convertAssetToXML(request,deviceList,
					deviceListResult.getTotalCount(),
					contract.getStartRecordNumber(),
					request.getContextPath(),
					PortalSessionUtil.getContactId(session),
					LexmarkSPConstants.ASSET_LIST_TYPE);


			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(content);
			out.flush();
			out.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			logger.debug("Exception "+e.getMessage());
		}
		finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("------------- Step 1---ServiceRequestController.retriveDeviceList end---------["+System.nanoTime()+"]");
	}
	
	
	private void loadFilterCriteria(ResourceRequest request, AssetListContract contract){
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

	private void assembleDevice(Asset device, Locale locale) {
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
	 * @author wipro
	 * common action for redirecting to SR form after adding/deleting attachment file
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=srCreatePage")
	public String gotoSRCreatePage(Model model, RenderRequest request,
	RenderResponse response) throws Exception {
		retrieveGridSetting("gridContainerDiv_all_request_types",request,response,model);//Loading data from db-CI BRD13-10-02
		return "serviceRequest/serviceRequestPage2";
	}
	
	@InitBinder(value={"fileUploadForm","serviceRequestConfirmationForm"})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		if(binder.getTarget() instanceof FileUploadForm){
			logger.debug("Setting FileUploadForm validator");
			binder.setValidator(fileUploadFormValidator);
		}else if(binder.getTarget() instanceof ServiceRequestConfirmationForm){
			logger.debug("Setting AssetDetailPageForm validator");
		}
	}
	
	@RequestMapping(params = "action=showSRPageWithExceptions")
	public String showSRPageWithExceptions(RenderRequest request, RenderResponse response, Model model)throws Exception{
		retrieveGridSetting("gridContainerDiv_all_request_types",request,response,model);//Loading data from db-CI BRD13-10-02
		return "serviceRequest/serviceRequestPage2";
	}
//	end of addition by
	
	
	/*------------------------- START : CI-6 Req : 0.3  ---------------------*/
	@ActionMapping(params="action=addAttachmentsCreateService")
	public void addAttachmentsCreate(Model model, ActionRequest request, ActionResponse response,
			@ModelAttribute("serviceRequestConfirmationForm") ServiceRequestConfirmationForm serviceRequestConfirmationForm,
			@ModelAttribute("fileUploadForm") @Valid FileUploadForm 
			fileUploadForm, BindingResult result, Object path) throws LGSCheckedException, LGSRuntimeException {

		PortletSession session = request.getPortletSession();
		Map<String, FileObject> fileMap = (Map<String, FileObject>)session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		logger.debug("-----------  (File Map)--------- : "+fileMap);
		if (result.hasErrors()) {
			List<ObjectError> exceptionList = result.getAllErrors();
			if(!exceptionList.isEmpty()){
				model.addAttribute("exceptionList", exceptionList);	
			}
			fileUploadForm.setFileMap(fileMap);
				session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP, fileMap);
				request.setAttribute("fileUploadForm",fileUploadForm);
				model.addAttribute("fileUploadForm",fileUploadForm);
				
				request.setAttribute("serviceRequestConfirmationForm",serviceRequestConfirmationForm);
				model.addAttribute("serviceRequestConfirmationForm",serviceRequestConfirmationForm);
				response.setRenderParameter("action", "addAttachmentsCreateBreakFix");
		} else{
			List<String> exceptionList = new ArrayList<String>();
		    try
		    {
		    	FileObject fileObject = new FileObject();
		    	if(fileMap == null) {
		    		fileMap = new LinkedHashMap<String, FileObject>();
				}
		    	double fileSize=(int) fileUploadForm.getFileData().getSize();
		    	fileSize=fileSize/1024;
		    	
		    	// File Size
		    	double fileSizeDisplay=fileUploadForm.getFileData().getSize();
		    	logger.debug("File Size is " + fileSizeDisplay);
		    	fileSizeDisplay=fileSizeDisplay/1024;
		    	BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
		    	roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
		    	logger.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
		    	
		    	FileItem fileItem	=	fileUploadForm.getFileData().getFileItem();
		    	String 	 fileName	=	returnFileName(fileItem.getName());
		    	int index = fileName.indexOf("\\");
				if(index != 0){
					int index1 = fileName.lastIndexOf("\\");
					fileName = fileName.substring(index1+1);
				}
				String fileNameWithTimestamp = setTimestampInAttachment(fileName);
					
				fileObject.setFileName(fileNameWithTimestamp);
				
					// File Name for display
					String displayAttachment = fileNameWithTimestamp.substring(0,fileNameWithTimestamp.lastIndexOf('_'));

					String fileExt = fileNameWithTimestamp.substring(fileNameWithTimestamp.lastIndexOf(".")+1, fileNameWithTimestamp.length()).toLowerCase();

					String displayAttachmentName = displayAttachment+"."+fileExt;
					
					fileObject.setDisplayFileName(displayAttachmentName);
				fileObject.setFileSize(String.valueOf(roundedFileSizeDisplay));
				fileObject.setFileSizeInBytes(String.valueOf(fileUploadForm.getFileData().getSize()));
				
				AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
				fileItem.write(new File(fileProperties.getFileUploadDestination() + fileNameWithTimestamp));
				
				fileMap.put(fileNameWithTimestamp,fileObject);
					fileUploadForm.setFileMap(fileMap);
					fileUploadForm.setFileCount(fileMap.size());
				session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP, fileMap);
		    }
		    catch (LGSRuntimeException e) {
				exceptionList.add(e.getMessage());
			}
		    catch (Exception e) {
				exceptionList.add(e.getMessage());
			}
		    if(!exceptionList.isEmpty()){
				model.addAttribute("exceptionList", exceptionList);	
			}
		    request.setAttribute("fileUploadForm",fileUploadForm);
			model.addAttribute("fileUploadForm",fileUploadForm);
			request.setAttribute("serviceRequestConfirmationForm",serviceRequestConfirmationForm);
			model.addAttribute("serviceRequestConfirmationForm",serviceRequestConfirmationForm);
			response.setRenderParameter("action", "addAttachmentsCreateBreakFix");
		}
	}
	
	
	/*------------------------- END : CI-6 Req : 0.3  ---------------------*/
	
	@RequestMapping(params = "action=addAttachmentsCreateBreakFix")
	public String addAttachmentsCreateBreakFix(Model model, RenderRequest request,RenderResponse response) throws Exception {
		retrieveGridSetting("gridContainerDiv_all_request_types",request,response,model);//Loading data from db-CI BRD13-10-02
		LOGGER.debug("Inside ServiceRequestController >> addAttachmentsCreateBreakFix render method");
		model.addAttribute("source","attachment");
		return "serviceRequest/serviceRequestPage2";
	}	
	
	@ActionMapping(params = "action=removeAttachment")
	public void removeAttachment(Model model,ActionRequest request, ActionResponse response, @ModelAttribute("serviceRequestConfirmationForm") ServiceRequestConfirmationForm
			serviceRequestConfirmationForm, @ModelAttribute("fileUploadForm") FileUploadForm 
			fileUploadForm,  @RequestParam(value="fileName") String fileName) throws LGSCheckedException, LGSRuntimeException {

		LOGGER.debug("Inside ServiceRequestController >> removeAttachment method");
		PortletSession session = request.getPortletSession();
		List<String> exceptionList = new ArrayList<String>();
		try{
			Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			
			if(fileMap == null){
				throw new LGSRuntimeException("There is no file to be removed.");
			}
			
			fileMap.remove(fileName);
			AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
			File removableFile = new File(fileProperties.getFileUploadDestination() + fileName);
			boolean delStatus = removableFile.delete();
			LOGGER.debug("delStatus "+delStatus);
			
			if(delStatus == false){
				throw new LGSRuntimeException("The attachment could not be removed.");
			}
			
			if(fileMap.isEmpty()){
				fileUploadForm.setFileMap(null);
				fileUploadForm.setFileCount(0);
				session.removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			}
			else{
				fileUploadForm.setFileMap(fileMap);
				fileUploadForm.setFileCount(fileMap.size());
				
			}
		}catch (LGSRuntimeException e) {
			LOGGER.debug("LGSRuntimeException occurred ::: "+ e.getMessage());
			exceptionList.add(e.getMessage());
		}
		
		if(!exceptionList.isEmpty()){
			model.addAttribute("exceptionList", exceptionList);	
		}
		request.setAttribute("fileUploadForm",fileUploadForm);
		model.addAttribute("fileUploadForm",fileUploadForm);
		session.setAttribute("fileUploadForm",fileUploadForm);
		
		
		request.setAttribute("serviceRequestConfirmationForm",serviceRequestConfirmationForm);
		model.addAttribute("serviceRequestConfirmationForm",serviceRequestConfirmationForm);
		
		
		response.setRenderParameter("action", "removeAttachmentBreakFix");
		
	}
	@RequestMapping(params = "action=removeAttachmentBreakFix")
	public String removeAttachmentBreakFix(Model model, RenderRequest request,RenderResponse response) throws Exception {
		LOGGER.debug("Inside ServiceRequestController >> removeAttachmentBreakFix render method");
		retrieveGridSetting("gridContainerDiv_all_request_types",request,response,model);//Loading data from db-CI BRD13-10-02
		model.addAttribute("source","attachment");
		return "serviceRequest/serviceRequestPage2";
	}
	
	//Added for update pageCounts
	/**
	 * This method is responsible for the device detail call and extract the ltpc values out of it
	 * @param request
	 * @param response
	 * @param model
	 */
	@ResourceMapping (value = "getLtpcValues")
	public void getLtpcValues(ResourceRequest request, ResourceResponse response, Model model) throws Exception
	{
		StringBuffer responseBody=new StringBuffer();
		try{
		AssetContract contract = ContractFactory.getAssetContract(request);
		
		PortletSession session = request.getPortletSession();
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveDeviceDetail", 
				PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
		long timeBeforeCall=System.currentTimeMillis();
		AssetResult deviceResult = orderSuppliesAssetService.retrieveDeviceDetail(contract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEDEVICELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);

		PerformanceTracker.endTracking(lexmarkTran);
		
		final String ltpcVal=deviceResult.getAsset().getLastPageCount()==null?"":deviceResult.getAsset().getLastPageCount();
		final String colorVal=deviceResult.getAsset().getLastColorPageCount()==null?"":deviceResult.getAsset().getLastColorPageCount();
		final Date ltpcDt=deviceResult.getAsset().getLastPageReadDate();
		
		final Date colorDt=deviceResult.getAsset().getLastColorPageReadDate();
		
		
		if(deviceResult!=null)
		{
			logger.debug("Generating the json response...");
			
			responseBody.append("\"ltpcSuccess\":\"Success\"");
			responseBody.append(","+"\"ltpcvalue\":\""+ltpcVal+"\"");
			responseBody.append(","+"\"colorvalue\":\""+colorVal+"\"");
			if(ltpcDt!=null)
			{
				Calendar calForLtpc=new GregorianCalendar();
				calForLtpc.setTime(ltpcDt);
				logger.debug(calForLtpc.getTimeInMillis());
				
				responseBody.append(","+"\"ltpcdate\":\""+calForLtpc.getTimeInMillis()+"\"");	
			}
			else{
				responseBody.append(","+"\"ltpcdate\":\""+ltpcDt+"\"");
			}
			
			if(colorDt!=null)
			{
				Calendar calForColor=new GregorianCalendar();
				calForColor.setTime(colorDt);
				logger.debug(calForColor.getTimeInMillis());
				
				responseBody.append(","+"\"colordate\":\""+calForColor.getTimeInMillis()+"\"");	
			}
			else{
				responseBody.append(","+"\"colordate\":\""+colorDt+"\"");
			}
		}
		
		}catch(Exception ex)
		{
			logger.debug("Exception " + ex.getMessage());
			responseBody.append("\"error:\""+
			PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"meterRead.label.meterReadError", request.getLocale())+"\"");
			
			request.setAttribute("errorAttribute", "true");//This is done for the time being
		}
		finally{
			responseBody.insert(0, "{");
			responseBody.insert(responseBody.length(), "}");
			logger.debug("response body finally is " + responseBody.toString());
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(responseBody);
			out.flush();
			out.close();
			responseBody.delete(0, responseBody.length());
		}
	}
	/**
	 * This is the main call which runs on the 1st Thread
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResourceMapping(value = "updatePageCounts")
	public void updatePageCounts(Model model, final ResourceRequest request, ResourceResponse response)
								throws Exception {
	final String newPageCount=request.getParameter("newPageCount");//This is for LTPC
	final String newColorPageCount=request.getParameter("newColorPageCount");//This is for color 
	final String newPageReadDt=request.getParameter("newPageReadDate");
	final String newColorPageReadDt=request.getParameter("newColorPageReadDate");
	final String selectedAssetId=request.getParameter("selectedAssetId");
	if(logger.isDebugEnabled())
	{
	logger.debug("new ltpc count is " + newPageCount);
	logger.debug("new color count is " + newColorPageCount);
	logger.debug("new page read dt is " + newColorPageReadDt);
	logger.debug("new color page read dt is " + newPageReadDt);
	logger.debug("asset id is " + selectedAssetId);
	}
	PortletSession session = request.getPortletSession();
	
	final Asset asset = new Asset();			
	asset.setAssetId(selectedAssetId);
	asset.setNewPageCount(newPageCount);
	asset.setNewColorPageCount(newColorPageCount);
	
	StringBuffer responseBody=new StringBuffer();	
	
	/*****Continue with the current processing***********/
	CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
	
	try{
		
		if(newPageReadDt=="")
		{
			asset.setMeterReadDate(DateUtil.getStringToLocalizedDate(DateUtil.localizeDateTime(new Date(), true, request.getLocale()), false, request.getLocale()));	
		}
		else 
		{
			asset.setMeterReadDate(DateUtil.getStringToLocalizedDate(newPageReadDt, false, request.getLocale()));
		}
		
		UpdateAssetMeterReadContract contract = ContractFactory.getUpdateAssetMeterReadContract(request);
		
		contract.setSessionHandle(crmSessionHandle);
		contract.setAsset(asset);
		
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "updateAssetMeterRead", 
				PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
		UpdateAssetMeterReadResult meterReadResult = meterReadService.updateAssetMeterRead(contract);
		PerformanceTracker.endTracking(lexmarkTran);	
		
		globalService.releaseSessionHandle(crmSessionHandle);
		
		if(meterReadResult.getResult())
		{
			
			responseBody.append("\"success\":\"<strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"devicemgmt.meterreads.update.success.mesg", request.getLocale())+"</strong>\"");
			
			if(newColorPageReadDt!="")
			{
				asset.setMeterReadDate(DateUtil.getStringToLocalizedDate(newColorPageReadDt, false, request.getLocale()));
				
				LexmarkTransaction lexmarkTranS = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "updateAssetMeterRead", 
						PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
				meterReadResult = meterReadService.updateAssetMeterRead(contract);
				PerformanceTracker.endTracking(lexmarkTranS);
			}
		}	
	
	}catch (Exception e) {
		logger.debug("Exception "+e.getMessage());
			
			request.setAttribute("errorAttribute", "true");//This is done for the time being
			
			if(responseBody.length()>0)
			{
			responseBody.append(","+"\"error\":\"<strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
			"devicemgmt.meterreads.update.failure.mesg", request.getLocale())+"</strong>\"");
			}
			else{
			responseBody.append("\"error\":\"<strong>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"devicemgmt.meterreads.update.failure.mesg", request.getLocale())+"</strong>\"");
			}
		}
	
	finally{
		
		globalService.releaseSessionHandle(crmSessionHandle);
		responseBody.insert(0, "{");
		responseBody.insert(responseBody.length(), "}");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(responseBody);
		out.flush();
		out.close();
		responseBody.delete(0, responseBody.length());
		}
	}
	
	@RequestMapping(params = "action=viewDeviceDetail")
	public String showDeviceDetailPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		try{
			AssetContract contract = ContractFactory.getAssetContract(request);
			String assetId = request.getParameter("assetRowId");
			if (assetId == null) {
				throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.deviceFinder.assetIdException", null, request.getLocale()));
			}
			PortletSession session = request.getPortletSession();
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveDeviceDetail", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			long timeBeforeCall=System.currentTimeMillis();
			AssetResult deviceResult = orderSuppliesAssetService.retrieveDeviceDetail(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEDEVICEDETAIL, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			PerformanceTracker.endTracking(lexmarkTran);
			
			ProductImageContract productImageContract = new ProductImageContract();
			productImageContract.setPartNumber(deviceResult.getAsset().getProductTLI());
			long timeBeforeServiceCall=System.currentTimeMillis();
			ProductImageResult productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEPRODUCTIMAGEURL, timeBeforeServiceCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_PORTALIMAGEDB,productImageContract);
			deviceResult.getAsset().setProductImageURL(productImageResult.getProductImageUrl());
			
			String accountRowId = deviceResult.getAsset().getAccount().getAccountId();
			logger.debug("###########accountRowId: " + accountRowId);
	
			sharedPortletController.assembleDevice(deviceResult.getAsset(), request.getLocale());
			DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
			deviceDetailForm.setDevice(deviceResult.getAsset());
			deviceDetailForm.setConsumableAssetFlag(deviceResult.getAsset().isConsumableAssetFlag());
			

			String createServiceRequestFlag = (String) request.getPortletSession().getAttribute("createServiceRequestFlag");
			if("true".equals(createServiceRequestFlag)){
				deviceDetailForm.setCreateServiceRequestFlag(true);
			}
			// Retrieve Grid Setting			
			retrieveGridSettingForDMHistory("gridContainerDiv_all_request_types",request,response,model);
			retrieveGridSettingForDMHistory("gridContainerDiv_supplies_requests",request,response,model);
			retrieveGridSettingForDMHistory("gridContainerDiv_change_requests",request,response,model);
			retrieveGridSettingForDMHistory("gridContainerDiv_service_requests",request,response,model);
						
			model.addAttribute("deviceDetailForm", deviceDetailForm);
			
			
			/******Below section is for retrieving siebel lov list for request type and status *****/
			long timeBeforeCallReqTpye=System.currentTimeMillis();
			SiebelLOVListResult requestTypeLOVList = globalService.retrieveSiebelLOVList(ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.REQUEST_TYPE.getValue(), null,null));
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SERVICEREQUEST_MSG_RETRIEVESIEBELLOVLISTRT, timeBeforeCallReqTpye,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.REQUEST_TYPE.getValue(), null,null));
			logger.debug("requestTypeLOVList-->"+requestTypeLOVList);
			if(requestTypeLOVList != null){
				logger.debug("request Type LOV list-->"+requestTypeLOVList.getLovList());
				Map<String, String> requestTypeLOVMap = getLovAsMap(requestTypeLOVList);
				logger.debug("requestTypeLOVMap-->"+requestTypeLOVMap);

				model.addAttribute("requestTypeLOVMap", requestTypeLOVMap);
			}
			long timeBeforeCallReqStatus=System.currentTimeMillis();
			SiebelLOVListResult requestStatusLOVList = globalService.retrieveSiebelLOVList(ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), null,null));
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SERVICEREQUEST_MSG_RETRIEVESIEBELLOVLISTSTATUS, timeBeforeCallReqStatus,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), null,null));
			logger.debug("requestStatusLOVList-->"+requestStatusLOVList);
			if(requestStatusLOVList != null){
				logger.debug("request Status LOV list-->"+requestStatusLOVList.getLovList());
				Map<String, String> requestStatusLOVMap = getLovAsMap(requestStatusLOVList);
				logger.debug("requestStatusLOVMap-->"+requestStatusLOVMap);
				model.addAttribute("requestStatusLOVMap", requestStatusLOVMap);
			}
			/******End of the section for retrieving siebel lov list for request type and status *****/
			
			return "deviceFinder/deviceInfoPage";  
			
		}catch(Exception e){
			String errorMessage = ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.unableToRetrieveDeviceDetail", null, request.getLocale());
			
			request.setAttribute("errorAttribute", errorMessage);
			
			/***If error, return to the deviceFinder jsp***/
			return "deviceFinder/deviceFinder";
			
		}
	}
	
	@RequestMapping(params = "action=gotoControlPanel")
	public String gotoControlPanel(RenderRequest request, RenderResponse response,
			Model model) throws Exception {
		model.addAttribute("controlPanelURL",request.getParameter("controlPanelURL"));
		model.addAttribute("pageName", "Device Finder");
		return "controlPanelPage";
	}
	/**
	 * This is done to get lov as map in device mgmt history
	 * @param requestStatusLOVList
	 * @return
	 */
	private Map<String, String> getLovAsMap(
			SiebelLOVListResult requestStatusLOVList) {
		
		if(requestStatusLOVList == null || requestStatusLOVList.getLovList() == null || requestStatusLOVList.getLovList().size()==0){
			logger.warn("SiebelLOVListResult object is null or No LOV is populated in the object !");
			return null;
		}
		Map<String, String> lovMap = new LinkedHashMap<String, String>();
		for(ListOfValues lovObj : requestStatusLOVList.getLovList()){
			lovMap.put(lovObj.getName(), lovObj.getValue());
		}
		
		return lovMap;
	}
	//Ends
	
	
	private String returnFileName(String inputStr){
		if(inputStr.indexOf("\\")!=-1){
    		int i=inputStr.lastIndexOf("\\");
    		logger.debug("Index is "+i);
    		String str_Return=inputStr.substring(i+1,inputStr.length());
    		logger.debug("Final return string is "+str_Return);
    		return str_Return;
    		
	}   	
	else{
		return inputStr;
		
	}
	}//end of returnFileName
	
	private String composeFilePath(String path, String fileName)
	{
		StringBuilder filePathBuilder = new StringBuilder();
		filePathBuilder.append(path);
		filePathBuilder.append(fileName);
		return filePathBuilder.toString();
	}
	private String setTimestampInAttachment(String fName) throws LGSBusinessException{
		
		int index = fName.lastIndexOf(".");
		
		String fExtension = fName.substring(index);
		LOGGER.debug("fExtension ----> "+fExtension);
		
		fName = fName.substring(0, index);
		LOGGER.debug("fName----> "+fName);
		
		String fNameFinal = fName + "_" + System.currentTimeMillis()+ fExtension;
		LOGGER.debug("fNameFinal----> "+fNameFinal);
		
		return fNameFinal;
	}

	/*-------------------------Addition for Attachment Feature-END-----------------------*/
	/**
	 * Download File Attachment for CI6
	 */
	
	@ResourceMapping(value="displayAttachmentBreakFix")
	
	public void displayAttachment(ResourceRequest request, ResourceResponse response, Model model)throws Exception{
		
		AttachmentContract contract = new AttachmentContract();

		String displayAttachmentName=request.getParameter("displayAttachmentName");
		
		String attachmentName=request.getParameter("attachmentName");
		
		contract.setUserFileName(request.getParameter("attachmentName"));
		contract.setIdentifier(request.getParameter("activityId"));
		
		// CI6 Download Attachment
		contract.setRequestType("Service Request");
		logger.debug("Contract value we are sending actualFileName "+request.getParameter("attachmentName")+" and activity id is "+request.getParameter("activityId"));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		
		
		try {
			AttachmentResult attachmentResult = attachmentService.downloadAttachment(contract);
			String fileName = attachmentResult.getFileName();
			InputStream fileStream = attachmentResult.getFileStream();
					if(fileStream!=null){
					response.setProperty("Content-disposition", "attachment; filename=\"" + displayAttachmentName +"\"");
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
			        } 
					 InputStream inputStream = fileStream;
					
					  OutputStream out = response.getPortletOutputStream();
					  byte buf[]=new byte[1024];
					  int len;
					  while((len=inputStream.read(buf))>0)
					  {out.write(buf,0,len);}
					  out.close();
					  inputStream.close();
				}
				else{
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
				OutputStream out = response.getPortletOutputStream();
				
			}
	}
	
	//Added for CI BRD13-10-02
	@ResourceMapping(value="retrieveHistoryList")
	public void retrieveHistoryList(ResourceRequest request, ResourceResponse response, Model model)
	{
		commonController.retrieveHistoryListByAssetId(request, response, model);
	}
	
	
	}	//End of Main Class
