/**
 * Copyright@ 2012. This document has been created & written by 
 * Wipro technologies for Lexmark and this is copyright to Lexmark 
 *
 * File         	: OrderSuppliesAssetOrderController 
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

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AgreementSoldToNumberContract;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.CreateConsumableServiceRequestContract;
import com.lexmark.contract.LocalizedPageCountNameContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.TaxContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.AgreementSoldToNumberResult;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.LocalizedPageCountNameResult;
import com.lexmark.result.PriceResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.RequestResult;
import com.lexmark.result.TaxResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.api.CreateConsumableRequest;
import com.lexmark.services.api.om.RetrievePriceService;
import com.lexmark.services.api.om.RetrieveTaxService;
import com.lexmark.services.api.om.RetrieveTonerPriceService;
import com.lexmark.services.form.AssetDetailPageForm;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.BaseForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.validator.AssetDetailPageFormValidator;
import com.lexmark.services.form.validator.FileUploadFormValidator;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PaymentUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ResourceResponseUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.XMLEncodeUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.DownloadFileUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.report.PdfPReportGenerator;
import com.liferay.portal.util.PortalUtil;
import com.lexmark.services.util.URLImageUtil;
/**
 * OrderSuppliesAssetOrderController
 * @author wipro
 * @version 2.1
 * 
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={"assetDetailPageForm", "reviewAssetOrderForm"})
public class OrderSuppliesAssetOrderController extends BaseController {
	
	private static Logger LOGGER = LogManager.getLogger(OrderSuppliesAssetOrderController.class);
	private static LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(OrderSuppliesAssetOrderController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	
	/**
	 * sharedPortletController
	 */
	@Autowired
	private SharedPortletController sharedPortletController;
	/**
	 * OrderSuppliesAssetService
	 */
	@Autowired
	private OrderSuppliesAssetService orderSuppliesAssetService;
	/**
	 * ProductImageService
	 */
	@Autowired
	private ProductImageService productImageService;
	/**
	 * CommonController
	 */
	@Autowired
	private CommonController commonController;
	/**
	 * AttachmentService
	 */
	@Autowired
	private AttachmentService attachmentService;
	/**
	 * GlobalService
	 */
	@Autowired
    private GlobalService  globalService;
	/**
	 * CreateConsumableRequest
	 */
	@Autowired
	private CreateConsumableRequest createConsumableRequest;
	/**
	 * FileUploadFormValidator
	 */
	@Autowired
    public FileUploadFormValidator fileUploadFormValidator;
	/**
	 * AssetDetailPageFormValidator
	 */
	@Autowired
	private AssetDetailPageFormValidator assetDetailPageFormValidator;
	/**
	 * ServiceRequestLocale
	 */
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	
	/**
	 * RequestTypeService
	 */
	@Autowired
	public RequestTypeService requestTypeService;
	
	/**
	 * RetrieveTaxService
	 */
	@Autowired
	public RetrieveTaxService retrieveTaxService;
	
	/**
	 * RetrievePriceService
	 */
	@Autowired
	public RetrievePriceService retrievePriceService;
	
	@Autowired 
	private RetrieveTonerPriceService retrieveTonerPriceService;
	
	/**
	 * maxPartsToBeOrdered
	 */
	private String maxPartsToBeOrdered;
	/**
	 * listOfFileTypes
	 */
	private String listOfFileTypes;
	/**
	 * attMaxCount
	 */
	private String attMaxCount;
	/**
	 * Method to bind the form for validation. 
	 * @param binder 
	 * @param locale 
	 */
	@InitBinder(value={"fileUploadForm","assetDetailPageForm"})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		if(binder.getTarget() instanceof FileUploadForm){
			binder.setValidator(fileUploadFormValidator);
		}else if(binder.getTarget() instanceof AssetDetailPageForm){
			binder.setValidator(assetDetailPageFormValidator);
		}
	}
	/**
	 * This method is used to show the asset list page
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return jsp name as string 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showAssetListPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		LOGGER.debug("-------------------- showAssetListPage started --------------------");		
		PortletSession portletSession=request.getPortletSession();
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
	
		portletSession.setAttribute("selectedAssetBillToAddress", null ,PortletSession.APPLICATION_SCOPE);
		
		if(httpReq.getParameter("requestNumber")!= null && 
				httpReq.getParameter("reqStatus")!=null &&
				httpReq.getParameter("reqStatus").equalsIgnoreCase("Draft")){
			
			commonController.getContactInformation(request, response);
			// - sourcePage and history added for back button in asset details (MPS Phase 2)
			model.addAttribute("gridType", httpReq.getParameter("sourcePage"));
			model.addAttribute("history", httpReq.getParameter("history"));
			return redirectAssetDetailsPage( model,  request,  response);
		}
				
		//here retrieve the usergridsettings value from the database.
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("downloadAssetListURL");
		model.addAttribute("downloadAssetListURL", resURL.toString());
		retrieveGridSetting("consumableAssetlistGrid",request,response,model);
		//Add Device Location tree
		
		model.addAttribute("deviceLocationTreeXMLURL", getDeviceLocationXMLURL(response));
		//Add CHL node tree
		model.addAttribute("chlTreeXMLURL", sharedPortletController.getCHLTreeXMLURL(response));
		
		portletSession.removeAttribute("selectedAssetBillToAddress");
		model.addAttribute("mdmLevelForAssetDetails", PortalSessionUtil.getMdmLevel(portletSession));
		LOGGER.debug("-------------------- showAssetListPage ended ----------------------");
		
		PortletURL query = response.createRenderURL(); 
		String queryString = query.toString();
		
		if(queryString.indexOf("/partner-portal") != -1 && queryString.indexOf("consumableorder") == -1)
		{
			return "orderSuppliesAssetOrder/selectAccountPopUp";
		}
		
		/*********** Below Section is for forwarding the requests to Details Page coming from Device Mgmt *******/
		
		final String assetId=httpReq.getParameter("assetId");
		String contractNumber = "";
		if(httpReq.getParameter("contractNumber")!=null){
			contractNumber=httpReq.getParameter("contractNumber");
		}
		
		if(assetId !=null)
		{
			
			return showAssetDetailPage(request, response,assetId,contractNumber, model);
		}
		/****************End of the section ********************/
		
		else
		{		
			return "orderSuppliesAssetOrder/assetList";
		}	

	}
	
	
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("deviceLocationXMLURLForOrderList")
	public void getDeviceLocationXML(ResourceRequest resourceRequest,ResourceResponse resourceResponse,Model model)throws Exception{
		
		String pageFrom="Order Supplies";
		sharedPortletController.retrieveDeviceLocationTreeXML(resourceRequest,resourceResponse,model,pageFrom);
	}
	
	
	/**
	 * @param response 
	 * @return String 
	 */
	private String getDeviceLocationXMLURL(RenderResponse response) {
		// TODO Auto-generated method stub
		ResourceURL url = response.createResourceURL();
		url.setResourceID("deviceLocationXMLURLForOrderList");
		return  url.toString();
	
	}
	/**.
	 * This method renders the asset details page to populate a draft consumable request data.
	 * @param model Model
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @return assetDetails page
	 * @author Sagar sarkar
	 */
	private String redirectAssetDetailsPage(Model model, RenderRequest request, RenderResponse response){
		LOGGER.debug("OrderSupplyAssetOrderController.redirectAssetDetailsPage.Enter :");
		PortletSession session = request.getPortletSession();
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String userSegment = PortalSessionUtil.getUserType(request.getPortletSession());
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		RequestContract contract = new RequestContract();
		contract.setServiceRequestNumber(httpReq.getParameter("requestNumber"));
		contract.setVisibilityRole(userSegment);
		contract.setSessionHandle(crmSessionHandle);
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		LOGGER.info("end printing lex loggger");
		
		//LBS
		String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			model.addAttribute("fleetManagementFlag","false");
		}
		
		RequestResult requestResult = new RequestResult();
		try {
			Long startTime = System.currentTimeMillis();
			requestResult = requestTypeService.retrieveSupplyRequestDetail(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVESUPPLYREQUESTDETAIL, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Supply request DETAIL ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
		} catch (Exception e) {
			LOGGER.debug("Exception occured in retrieveSupplyRequestDetail service call ...");
		}
		if(requestResult.getServiceRequest()!=null){
			
			ServiceRequest sr = requestResult.getServiceRequest();
			
			
			List<PageCounts> pageCount = sr.getPageCounts();
			if(pageCount !=null && !pageCount.isEmpty()){
				LOGGER.debug("pageCount size-->"+pageCount.size());
				
			}
			
		}
		
		LOGGER.debug("Service call done for SR number -->"+contract.getServiceRequestNumber());
		AssetDetailPageForm assetDetailPageForm = new AssetDetailPageForm();
		ServiceRequest serviceRequest=requestResult.getServiceRequest();
		String customerReferenceNumber = serviceRequest.getCustomerReferenceNumber();
		
		serviceRequest.setCustomerReferenceId(customerReferenceNumber);
		assetDetailPageForm.setSpecialInstruction(serviceRequest.getSpecialInstructions());
		assetDetailPageForm.setDefaultSpecialInstruction(serviceRequest.getDefaultSpecialInstructions());
		Asset asset = serviceRequest.getAsset();
		
		assetDetailPageForm.setServiceRequest(serviceRequest) ;
		LOGGER.debug("Asset.ID--->"+asset.getAssetId() + "asset.getAssetTag()" + asset.getAssetTag() + "asset.getSerialNumber()"+asset.getSerialNumber());
		
		asset.setPoNumber(serviceRequest.getPoNumber());
		asset.setDeviceTag(asset.getAssetTag());
		ProductImageContract productImageContract = new ProductImageContract();
		productImageContract.setPartNumber(serviceRequest.getAsset().getProductTLI());//result.getAsset().getProductTLI()
		ProductImageResult productImageResult = null;
		try {
			long timeBeforeCall=System.currentTimeMillis();
			productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEPRODUCTIMAGEURL, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_PORTALIMAGEDB,productImageContract);
			LOGGER.info("start printing lex logger");
			//LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE PRODUCT IMAGE URL ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
		} catch (Exception e) {
			LOGGER.debug("Exception occured in retrieveProductImageUrl service call ...");
		}
		asset.setProductImageURL(productImageResult!= null?productImageResult.getProductImageUrl():"");
		
		assetDetailPageForm.setAsset(asset);
		if(asset.getInstallDate()!=null){
			assetDetailPageForm.setInstallDate(DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(asset.getInstallDate()));
		}
		GenericAddress physicalLocationAddress = new GenericAddress();
		physicalLocationAddress.setPhysicalLocation1(serviceRequest.getAsset().getInstallAddress().getPhysicalLocation1());
		physicalLocationAddress.setPhysicalLocation2(serviceRequest.getAsset().getInstallAddress().getPhysicalLocation2());
		physicalLocationAddress.setPhysicalLocation3(serviceRequest.getAsset().getInstallAddress().getPhysicalLocation3());
		assetDetailPageForm.setPhysicalLocationAddress(physicalLocationAddress);
		
		AssetContract assetContract = new AssetContract();
		assetContract.setAssetId(asset.getAssetId());
		
		List<Part>  assetPartList = new ArrayList<Part>();
		List <Part> displayPartList = new ArrayList<Part>();
		LEXLOGGER.info("start printing lex logger");
		assetContract.setPageName(ChangeMgmtConstant.CONSUMABLE_DEVICE_DETAIL);
		assetContract.setEffectiveDate(new Date());
		assetContract.setCurrentDate(new Date());
		ObjectDebugUtil.printObjectContent(assetContract, LEXLOGGER);
		LEXLOGGER.info("end printing lex loggger");
		Long startTime = System.currentTimeMillis();
		
		AssetResult result = orderSuppliesAssetService.retrieveDeviceDetail(assetContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEDEVICEDETAIL, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,assetContract);
		LOGGER.info("start printing lex logger");
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Device DETAIL ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
		LOGGER.info("end printing lex loggger");
		
		/*
		 * This portion added for setting Account Name and Account Account Id,
		 * so that when address popup opens from CommonCtroller the values are available
		 * from session.*/
		if(result.getAsset()!=null  && result.getAsset().getAccount()!=null ){			
			Map<String,String> accDetails= null;
			accDetails = (Map<String,String>)request.getPortletSession().getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
			if(accDetails == null)
			{
				accDetails=new HashMap<String, String>();
			}
			accDetails.put(ChangeMgmtConstant.ACCOUNTNAME,result.getAsset().getAccount().getAccountName());
			accDetails.put(ChangeMgmtConstant.ACCOUNTID,result.getAsset().getAccount().getAccountId());
			PortletSession portletSession = request.getPortletSession();
			portletSession.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accDetails ,PortletSession.APPLICATION_SCOPE);
			
			assetDetailPageForm.setExpediteOrderAllowed(result.getAsset().getAccount().isAssetExpediteFlag());			
				
		}
					
		/*Changes Ends*/		
		
		if(result.getAsset().getPartList()!=null){
			
			assetPartList = result.getAsset().getPartList();
			for (Part list : assetPartList) {
				Part partListForm = new Part();
				partListForm.setPartNumber(list.getPartNumber());
				partListForm.setDescription(list.getDescription());
				partListForm.setPartType(list.getPartType());//display
				partListForm.setCatalogId(list.getCatalogId());
				partListForm.setSupplyType(list.getConsumableType());
				partListForm.setImplicitFlag(list.getImplicitFlag()); //Added by Arup
				partListForm.setSupplyId(list.getSupplyId());
				partListForm.setProductId(list.getProductId());
				LOGGER.debug("serialNumber="+result.getAsset().getSerialNumber()+" Implicitflag="+list.getImplicitFlag()); //Added by Arup
				LOGGER.debug("@@@@@@@@@@@@@@ Part details number "+list.getPartNumber()+" part type "
						+list.getPartType()+" category "+list.getCategory()+" supplyType "+list.getSupplyType()+" consumbaleType "+
						list.getConsumableType()+" AssetUsageType "+list.getAssetUsageType()+" catalogid "+list.getCatalogId()+
						"list.getSupplyId() "+list.getSupplyId()+" list.getProductId() "+list.getProductId()+" Description "+list.getDescription());
				displayPartList.add(partListForm);
			}
		}
		if(serviceRequest.getParts()!=null){
			for(Part p:serviceRequest.getParts()){
				for(int i=0;i<displayPartList.size();i++){
					if(p.getCatalogId().equalsIgnoreCase(displayPartList.get(i).getCatalogId())){
						displayPartList.get(i).setOrderQuantity(p.getOrderQuantity());
						break;
					}
				}
			}
		}
		
		assetDetailPageForm.setAssetPartList(displayPartList);
		assetDetailPageForm.setNoOfPart(displayPartList.size());//This parameter is required in the assetDetail page validation.
		session.removeAttribute("selectedAssetBillToAddress");
		session.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST);
		session.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);
		
		session.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED, displayPartList);
		
		if(serviceRequest.getPageCounts()!=null){
			List<PageCounts> pageCountList = serviceRequest.getPageCounts();
			
			List<PageCounts> modifiedPageCountList = new ArrayList<PageCounts>();
			for(int i=0;i<pageCountList.size();i++){
				
				LOGGER.debug("page count value received from siebel is name "+pageCountList.get(i).getName()+
						" count "+pageCountList.get(i).getCount()+" date "+pageCountList.get(i).getDate());
				LocalizedPageCountNameResult pageCountResult = new LocalizedPageCountNameResult();
				LocalizedPageCountNameContract pageCountContract = new LocalizedPageCountNameContract();
				pageCountContract.setSiebelValue(pageCountList.get(i).getName());
				//long timeBeforeCall=System.currentTimeMillis();
				pageCountResult = serviceRequestLocaleService.retrieveLocalizedPageCountName(pageCountContract);
				/*LOGGER.info("start printing lex logger");
				LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE LOCALIZED PAGECOUNT NAME ==>: " + (timeBeforeCall- startTime)/1000.0);
				LOGGER.info("end printing lex loggger");*/
				
				PageCounts modifiedPageCount = new PageCounts();
				modifiedPageCount.setName(pageCountResult.getLocalizedValue());
				modifiedPageCount.setCount(pageCountList.get(i).getCount());
				modifiedPageCount.setDate(pageCountList.get(i).getDate());
				modifiedPageCount.setSiebelName(pageCountList.get(i).getName());
				modifiedPageCountList.add(modifiedPageCount);
				
			}
			List<PageCounts> pageCountListOthers = result.getAsset().getPageCounts();
			if(pageCountListOthers !=null){
				
				for(int j=0;j<modifiedPageCountList.size();j++){
				for(int i=0;i<pageCountListOthers.size();i++){
					if((pageCountListOthers.get(i).getName()).equalsIgnoreCase(modifiedPageCountList.get(j).getSiebelName())){
						
						LocalizedPageCountNameResult pageCountResult = new LocalizedPageCountNameResult();
						LocalizedPageCountNameContract pageCountContract = new LocalizedPageCountNameContract();
						pageCountContract.setSiebelValue(modifiedPageCountList.get(j).getName());
						pageCountResult = serviceRequestLocaleService.retrieveLocalizedPageCountName(pageCountContract);
						
						pageCountListOthers.get(i).setName(pageCountResult.getLocalizedValue());
						pageCountListOthers.get(i).setCount(modifiedPageCountList.get(j).getCount());
						pageCountListOthers.get(i).setDate(modifiedPageCountList.get(j).getDate());
						pageCountListOthers.get(i).setSiebelName(modifiedPageCountList.get(j).getName());
						
					}
				}
				}
			
			}
			
			List<PageCounts> modifiedPageCountListNew = new ArrayList<PageCounts>();
			for(int i=0;i<pageCountListOthers.size();i++){
				PageCounts modifiedPageCount = new PageCounts();
				LocalizedPageCountNameResult pageCountResult = new LocalizedPageCountNameResult();
				LocalizedPageCountNameContract pageCountContract = new LocalizedPageCountNameContract();
				pageCountContract.setSiebelValue(pageCountListOthers.get(i).getName());
				pageCountResult = serviceRequestLocaleService.retrieveLocalizedPageCountName(pageCountContract);
				
				modifiedPageCount.setName(pageCountResult.getLocalizedValue());
				modifiedPageCount.setCount(pageCountListOthers.get(i).getCount());
				modifiedPageCount.setSiebelName(pageCountListOthers.get(i).getName());
				if(pageCountListOthers.get(i).getDate()!= null && pageCountListOthers.get(i).getDate()!=""){
					DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
					Date defaultDateINGMT = null;
					try {
						defaultDateINGMT = parser.parse(pageCountListOthers.get(i).getDate());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						LOGGER.debug("Parse Exception "+e.getMessage());
					}
					LOGGER.debug("defaultDateINGMT order--->>>"+defaultDateINGMT.toString());					
					LOGGER.debug("The default date is to be shown--->>>"+DateUtil.convertToLocaleSpecificDateTime(defaultDateINGMT, request.getLocale()));					
					modifiedPageCount.setDate(DateUtil.convertToLocaleSpecificDateTime(defaultDateINGMT, request.getLocale()));							
				}
				modifiedPageCountListNew.add(modifiedPageCount);
			}
			LOGGER.debug("pageCountListOthers size" + modifiedPageCountListNew.size());
			
			
			assetDetailPageForm.setPageCountsList(modifiedPageCountListNew);
		}
		assetDetailPageForm.setMaxPartsToBeOrdered(getMaxPartsToBeOrdered());
		
		assetDetailPageForm.setListOfFileTypes(listOfFileTypes);
		assetDetailPageForm.setAttMaxCount(attMaxCount);		
				
		GenericAddress serviceAddress = serviceRequest.getAsset().getInstallAddress();
		
		assetDetailPageForm.setServiceAddress(serviceAddress);
		
		GenericAddress shipToAddress = serviceRequest.getServiceAddress();
		
		assetDetailPageForm.setShipToAddress(shipToAddress);
		
		assetDetailPageForm.setPoNumber(serviceRequest.getPoNumber());
		String dateString = "";
		if(serviceRequest.getRequestedEffectiveDate()!=null ){
			dateString = DateUtil.convertDateToGMTString(serviceRequest.getRequestedEffectiveDate());
			if(dateString.length()>10){
				dateString = dateString.substring(0,10);
			}
		}
		
		assetDetailPageForm.setRequestedDeliveryDate(dateString);
		
		assetDetailPageForm.setAttachmentDescription(serviceRequest.getNotes());
		AttachmentForm attachForm = new AttachmentForm();
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		attachmentList = serviceRequest.getAttachments();
		attachForm.setListOfFileTypes(listOfFileTypes);
		attachForm.setAttMaxCount(attMaxCount);
		List<Attachment> modifiedAttachmentList = new ArrayList<Attachment>();
		if(attachmentList!=null){
			for(Attachment attachment:attachmentList){
				Attachment modifiedAttachment = new Attachment();
				String displayAttachment = "";
				
				modifiedAttachment.setAttachmentName(attachment.getAttachmentName()+"."+attachment.getExtension());
				modifiedAttachment.setActivityId(attachment.getActivityId());
				modifiedAttachment.setExtension(attachment.getExtension());
				modifiedAttachment.setSize(attachment.getSize());
				modifiedAttachment.setStatus(attachment.getStatus());
				modifiedAttachment.setVisibility(attachment.getVisibility());
				modifiedAttachment.setCompletedOn(attachment.getCompletedOn());
				modifiedAttachment.setId(attachment.getId());
				double fileSizeDisplay=attachment.getSize();
				
				fileSizeDisplay=fileSizeDisplay/1024;
				BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
				roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
				
				modifiedAttachment.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
				//start doing the manipulation for display name
				String attachName = attachment.getAttachmentName();
				String fileNameWithTimestamp = attachName.substring(attachName.indexOf('@')+1, attachName.length());
				
				displayAttachment = fileNameWithTimestamp.substring(0,fileNameWithTimestamp.lastIndexOf('_'));
				displayAttachment = displayAttachment+"."+attachment.getExtension();
				
				//end completing the manipulation for display name
				modifiedAttachment.setDisplayAttachmentName(displayAttachment);
				modifiedAttachmentList.add(modifiedAttachment);
			}
		}
		session.setAttribute("attachmentList", modifiedAttachmentList);
		attachForm.setAttachmentList(modifiedAttachmentList);
		attachForm.setAttachmentDescription(serviceRequest.getNotes());
		request.setAttribute("attachmentForm",attachForm);
		model.addAttribute("attachmentForm",attachForm);
		assetDetailPageForm.setRelatedServiceRequestedNumber(serviceRequest.getServiceRequestNumber());
		model.addAttribute("assetDetailPageForm", assetDetailPageForm);
		model.addAttribute("attachmentFormDisplay", assetDetailPageForm);
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		assetDetailPageForm.setFileCount(modifiedAttachmentList.size());
		fileUploadForm.setFileCount(modifiedAttachmentList.size());
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID("displayAttachment");
		model.addAttribute("displayAttachment", resURL1.toString());
		
		LOGGER.debug("OrderSupplyAssetOrderController.redirectAssetDetailsPage.Exit :");
		return "orderSuppliesAssetOrder/assetDetail"; 
		
	}
	
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showAssetListPageForPartner")
	public String showAssetListPageForPartner(Model model, RenderRequest request, RenderResponse response) throws Exception{
		LOGGER.debug("-------------------- showAssetListPageForPartner started --------------------");
		
		//here retrieve the usergridsettings value from the database.
		//Grid is 
		
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("downloadAssetListURL");
		model.addAttribute("downloadAssetListURL", resURL.toString());
		retrieveGridSetting("consumableAssetlistGrid",request,response,model);
		//Add Device Location tree
		model.addAttribute("deviceLocationTreeXMLURL", sharedPortletController.getDeviceLocationXMLURL(response));
		//Add CHL node tree
		model.addAttribute("chlTreeXMLURL", sharedPortletController.getCHLTreeXMLURL(response));
		PortletSession portletSession=request.getPortletSession();
		LOGGER.debug("mdmlevel is "+PortalSessionUtil.getMdmLevel(portletSession) );
		model.addAttribute("mdmLevelForAssetDetails", PortalSessionUtil.getMdmLevel(portletSession));
		LOGGER.debug("-------------------- showAssetListPage ended ----------------------");		
		return "orderSuppliesAssetOrder/assetList";
	}
	/**
	 * This is a resource mapping call and responsible for data in the consumable asset list grid 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping(value="assetLiseLandingPageURL")
	public void retrieveContractedDeviceList(ResourceRequest request, ResourceResponse response) throws Exception{
		LOGGER.debug("-------------- Entering to retrieveDeviceList controller method -------------------");
		try{
		AssetListContract contract = ContractFactory.getConsumableAssetListContract(request);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		PortletSession session = request.getPortletSession();
		contract.setSessionHandle(crmSessionHandle);
		String viewType = request.getParameter("view");
		if ("bookmarked".equalsIgnoreCase(viewType)){
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
		LOGGER.debug("---------------- Before calling orderSuppliesAssetService method ------------------");
		loadFilterCriteria(request, contract);
		
		/* Added by sankha for LEX:AIR00075005 start */
		String DATEFORMAT = "MM/dd/yyyy" ;
	    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    final String utcTime = sdf.format(new Date());
		contract.setEntitlementEndDate(utcTime);
		LOGGER.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
		contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
		/* End */
		
		LOGGER.debug("Lets see the contract values mdmId:"+contract.getMdmId()+" ,mdmLevel:"
				+contract.getMdmLevel()+" ,contactId:"+contract.getContactId()+" ,chl node id:"+contract.getChlNodeId());
		LOGGER.info("start printing lex logger");
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract,LEXLOGGER);
		LOGGER.info("end printing lex loggger");
		LEXLOGGER.info("end printing lex loggger");
		Long startTime = System.currentTimeMillis();
		AssetListResult assetListResult = orderSuppliesAssetService.retrieveDeviceList(contract);//Mock call To be changed
		Long endTime = System.currentTimeMillis();
		Long timeTaken = endTime - startTime;
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEDEVICELIST, startTime,endTime, PerformanceConstant.SYSTEM_SIEBEL,contract);
		LEXLOGGER.logTime("Time taken to retrieve the consumable assetList "+timeTaken);
		List<Asset> deviceList = assetListResult.getAssets();
		String partImage;
		for (Asset device : deviceList) {
			partImage="";
			//long timeBeforeCall=System.currentTimeMillis();
			partImage = URLImageUtil.getPartImageFromLocal(device.getProductTLI());
			device.setProductImageURL(partImage);
			sharedPortletController.assembleDevice(device, request.getLocale());
		}
		String content = getXmlOutputGenerator(request.getLocale()).convertContractedAssetToXML(request,deviceList,
				assetListResult.getTotalCount(),contract.getStartRecordNumber(),request.getContextPath(),"");
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		LOGGER.debug("----------------------- Exiting from the retrieveDeviceList controller method ----------------------");
	} finally {
		//Release the session handle when mansi will implement that.
	}
	}
	
	/**
	 * @param request 
	 * @param contract 
	 */
	private void loadFilterCriteria(ResourceRequest request, AssetListContract contract) {
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
		long startTime = System.currentTimeMillis();
		LOGGER.debug("-------------- Entering to getAssetRequestHistory controller method -------------------");
		
		model.addAttribute("assetId", assetId);
		resourceResponse.setContentType("UTF-8");
		
		LOGGER.debug("-------------- Exiting from  getAssetRequestHistory controller method -------------------");
		long endTime = System.currentTimeMillis();
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING getAssetRequestHistory takes ==>: " + (endTime - startTime)/1000.0);
		return "orderSuppliesAssetOrder/recentOrderHistory";
		
	}

	/**
	 * @param resourceRequest
	 * @param resourceResponse
	 * @param assetId
	 * @param model
	 * @return
	 * @throws Exception
	 * 
	 * Onload of the popup(of device history) page this
	 * methode will get the request by ajax call
	 * 
	 */
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
			ResourceResponse resourceResponse,@RequestParam("assetId") String assetId)throws Exception{
		long startTime = System.currentTimeMillis();
		LOGGER.info("-------------- Entering in getRetrieveOrdderHistory controller method -------------------");
		AssetContract contract = ContractFactory.getAssetOrderDetailContract();
		contract.setAssetId(assetId);
		
		String dateFormatter = DateUtil.getDateFormatByLang(resourceRequest.getLocale().getLanguage());
		DateFormat dateFormat = new SimpleDateFormat(dateFormatter);
		String currentDate = null;
		currentDate = dateFormat.format(new Date());		
		Date currentDateObj = DateUtil.getStringToLocalizedDate(currentDate, false, resourceRequest.getLocale());
		String current_Date = DateUtil.convertDateToGMTString(currentDateObj);
		
		Date final_Date  = DATE_FORMAT.parse(current_Date);
		contract.setCurrentDate(final_Date);
		contract.setPageName(ChangeMgmtConstant.CONSUMABLE_DEVICE_DETAIL);
		contract.setEffectiveDate(new Date());
		ObjectDebugUtil.printObjectContent(contract,LEXLOGGER);
		long timeBeforeCall=System.currentTimeMillis();
		AssetResult result = orderSuppliesAssetService.retrieveDeviceDetail(contract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEORDERHISTORY, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Device DETAIL ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		resourceRequest.getPortletSession().setAttribute("AssetResult", result,PortletSession.APPLICATION_SCOPE); // newly added to get the detail
																					// from the session in the details page
		
		AssetDetailPageForm assetDetailPageForm = new AssetDetailPageForm();
		Asset asset = new Asset();
		
		if(result.getAsset().getModelNumber()!=null){	
			
			asset.setModelNumber(result.getAsset().getModelNumber());
			asset.setSerialNumber(result.getAsset().getSerialNumber());
			asset.setAssetTag(result.getAsset().getAssetTag());			
			asset.setDeviceTag(result.getAsset().getDeviceTag());
			asset.setIpAddress(result.getAsset().getIpAddress());			
			assetDetailPageForm.setAsset(asset);
			LOGGER.info("asset ipaddress and serial no :"+asset.getIpAddress()+":"+ asset.getSerialNumber());
			List<Part>  assetPartList = result.getAsset().getLastOrderPartList();						
			assetDetailPageForm.setAssetPartList(assetPartList);
		}
		String content = getXmlOutputGenerator(resourceRequest.getLocale()).convertOrderHistoryPopup(assetDetailPageForm,
				assetDetailPageForm.getAssetPartList().size(),0,resourceRequest.getLocale());
		PrintWriter out = resourceResponse.getWriter();
		resourceResponse.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		long endTime = System.currentTimeMillis();
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING getRetrieveOrderHistory takes ==>: " + (endTime - startTime)/1000.0);
		
	}
	//end
	
	/**
	 * This method is used to redirect to the Credit Card Page	
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return pageName 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showCreditCardPage")
	public String showCreditCardPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "creditCard/generateToken";
	}
	
	/**
	 * This method is used to render the details of the asset to be ordered 
	 * @param request 
	 * @param response 
	 * @param assetId 
	 * @param model 
	 * @return jsp name as string 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showAssetDetailPage")
	public String showAssetDetailPage (RenderRequest request, 
			RenderResponse response,@RequestParam("assetId") String assetId,@RequestParam("contractNumber") String contractNumber,Model model) throws Exception{
		LOGGER.debug("THE ASSETID IS ************************************" + assetId);
		LOGGER.debug("THE CONTRACT NUMBER IS ************************************" + contractNumber);
		model.addAttribute("assetId", assetId);
		AssetContract contract = ContractFactory.getAssetOrderDetailContract(request);
		PortletSession session = request.getPortletSession();
		
		//LBS
		String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			model.addAttribute("fleetManagementFlag","false");
		}
		
		int pageCountListSize = 0;
		boolean dsplPaymentTypeFlag = false;
		AssetDetailPageForm assetDetailPageForm = new AssetDetailPageForm();
		String assetValidation = null;
		/* Changes added for LEX:AIR00077250 Started */
		String dateFormatter = DateUtil.getDateFormatByLang(request.getLocale().getLanguage());
		DateFormat dateFormat = new SimpleDateFormat(dateFormatter);
		String currentDate = null;
		currentDate = dateFormat.format(new Date());		
		Date currentDateObj = DateUtil.getStringToLocalizedDate(currentDate, false, request.getLocale());
		String current_Date = DateUtil.convertDateToGMTString(currentDateObj);
		Date final_Date  = DATE_FORMAT.parse(current_Date);
		AttachmentForm attachForm = new AttachmentForm();
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
		
		attachForm.setAttachmentList(modifiedAttachmentList);
		request.setAttribute("attachmentForm",attachForm);
		model.addAttribute("attachmentForm",attachForm);
		/* Changes added for LEX:AIR00077250 Ended */
		try {
			contract.setAssetId(assetId);
			contract.setCurrentDate(final_Date); // for LEX:AIR00077250
			contract.setPageName(ChangeMgmtConstant.CONSUMABLE_DEVICE_DETAIL);
			contract.setEffectiveDate(new Date());
			long timeBeforeCall=System.currentTimeMillis();
			
			LOGGER.debug("start printing lex logger debug");
			LOGGER.info("start printing lex logger");
			
			
			// getting value from session if exists else make a new call to AMind
			AssetResult result = null;
			result = (AssetResult)session.getAttribute("AssetResult",PortletSession.APPLICATION_SCOPE);
			if(result == null){
				 
				
				 ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
				 Long startTime = System.currentTimeMillis();
				 result = orderSuppliesAssetService.retrieveDeviceDetail(contract);//Mock call To be changed
				 
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEDEVICEDETAIL, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
				 LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Device DETAIL ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			}
			
			LEXLOGGER.info("start printing lex logger");
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE CONSUMABLE ASSET DETAIL ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0+" for asset id "+assetId);
			
			
			
			ServiceRequest serviceRequest=new ServiceRequest();
			AccountContact accContact=commonController.getContactInformation(request, response);
			if(result.getAsset()==null){
				throw new Exception("Asset List is null");
			}
			
			/* Changes for adding Bill to address*/
			String agreementId = "";
			if(result.getAsset().getAgreementId()!=null){
				agreementId = result.getAsset().getAgreementId();				
			}
			
			LOGGER.debug("Asset Agreement ID "+agreementId);
			LOGGER.debug("Asset Contract Number "+contractNumber);
			assetDetailPageForm.setAgreementId(agreementId);
			assetDetailPageForm.setContractNumber(contractNumber);
			
			if(result.getAsset().getAssetContact()!=null){
				serviceRequest.setPrimaryContact(result.getAsset().getAssetContact());
			}
			/*
			 * This portion added for setting Account Name and Account Account Id,
			 * so that when address popup opens from CommonCtroller the values are available
			 * from session.*/
			if(result.getAsset()!=null  && result.getAsset().getAccount()!=null ){
				Map<String,String> accDetails= null;
				accDetails = (Map<String,String>)request.getPortletSession().getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
				if(accDetails == null)
				{
					accDetails=new HashMap<String, String>();
				}
				accDetails.put(ChangeMgmtConstant.ACCOUNTNAME,result.getAsset().getAccount().getAccountName());
				accDetails.put(ChangeMgmtConstant.ACCOUNTID,result.getAsset().getAccount().getAccountId());
				PortletSession portletSession = request.getPortletSession();
				portletSession.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accDetails ,PortletSession.APPLICATION_SCOPE);
				
				assetDetailPageForm.setExpediteOrderAllowed(result.getAsset().getAccount().isAssetExpediteFlag());
				
				//Changes for MPS 2.1 
				assetDetailPageForm.setSplitterFlag(result.getAsset().getAccount().isAccountSplitterFlag());
				
				assetDetailPageForm.setShowPriceFlag(result.getAsset().getAccount().getShowPriceFlag());//Show Price flag is true				
				
				if(assetDetailPageForm.isSplitterFlag()){
					assetDetailPageForm.setShowPriceFlag(result.getAsset().getAccount().getShowPriceFlag());//Show Price flag is true					
					
					assetDetailPageForm.setPoNumberFlag(result.getAsset().getAccount().isPoNumberFlag());//Payment method flag is either Credit card, PO or Both
					
					assetDetailPageForm.setCreditNumberFlag(result.getAsset().getAccount().isCreditNumberFlag());					
									
					assetDetailPageForm.setSalesOrganisation(result.getAsset().getAccount().getOrganizationName());
				}				
				
			}
						
			/*Changes Ends*/
			/*START--Added for Defect 3924-Jan Release*/
			commonController.assembleDevice(result.getAsset(), request.getLocale());
			/*END--Added for Defect 3924-Jan Release*/
			
			serviceRequest.setRequestor(accContact);
			
			serviceRequest.setCostCenter(result.getAsset().getAssetCostCenter());
			assetDetailPageForm.setServiceRequest(serviceRequest);
			
			//Product Image
			ProductImageContract productImageContract = new ProductImageContract();
			productImageContract.setPartNumber(result.getAsset().getProductTLI());
			Long retreveImageTime = System.currentTimeMillis();
			ProductImageResult productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.DEVICEFINDER_MSG_RETRIEVEPRODUCTIMAGEURL, retreveImageTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_PORTALIMAGEDB,productImageContract);
			LOGGER.info("start printing lex logger");
			//LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE PRODUCT IMAGE URL ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
			result.getAsset().setProductImageURL(productImageResult.getProductImageUrl());
			
			
						
			/***MPS 2.1 changes***/
			//for MPS 1.0 Customer there will be no payment types
			
			
			//removing part list from session before populating new part list
			session.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST);
			session.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);
			
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
                    
                    // Order Quantity Validation start.
                    partListForm.setMaxQuantity(list.getMaxQuantity());
					
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
							session.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);// removing part																									// lis
							session.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED,assetDetailPageForm.getAssetPartList());
						}
					
						
					}
				}
				
				if(assetDetailPageForm.isSplitterFlag()){
					/***start changes for Siebel Localization LOV***/
					try {
						requestTypeLOVMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CONSUMABLE_PAYMENT_TYPE.getValue(), request.getLocale());
						
						
					} catch (LGSDBException e) {
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
					if(!paymentTypeList.isEmpty()){
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
					session.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST);//removing part lis from session
					session.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST, assetDetailPageForm.getAssetPartList());
				}
				else{
					assetDetailPageForm.setAssetPartList(partList);
					session.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);//removing part lis from session
					session.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED, assetDetailPageForm.getAssetPartList());
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
					
					try{
						if(pageCountList.get(i).getDate()!= null && pageCountList.get(i).getDate()!=""){
							DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
							Date  defaultDateINGMT = parser.parse(pageCountList.get(i).getDate());
							LOGGER.debug("defaultDateINGMT order--->>>"+defaultDateINGMT.toString());					
							LOGGER.debug("The default date is to be shown--->>>"+DateUtil.convertToLocaleSpecificDateTime(defaultDateINGMT, request.getLocale()));					
							modifiedPageCount.setDate(DateUtil.convertToLocaleSpecificDateTime(defaultDateINGMT, request.getLocale()));							
						}
						}
						catch(Exception e)
						{
							LOGGER.debug("Exception occured in page counts date formatting");
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
			//Ends here
			
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
			
			//Removes the Attachment Map if any exists from previous flows
			
			session.removeAttribute("attachmentList");
			
			ResourceURL resURL1 = response.createResourceURL();
			resURL1.setResourceID("displayAttachment");
			model.addAttribute("displayAttachment", resURL1.toString());
			session.setAttribute("AssetResult", null,PortletSession.APPLICATION_SCOPE); // Removing this result from session, 
			}catch (LGSBusinessException e) {
				String exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
						e.getMessage(), request.getLocale());
				LOGGER.debug("LGSBusinessException in asset details call-----------> ");
				FileUploadForm fileUploadForm = new FileUploadForm();
				model.addAttribute("fileUploadForm", fileUploadForm);
				model.addAttribute("assetDetailPageForm", assetDetailPageForm);
				if(assetDetailPageForm.getPageCountsList()!=null){
					pageCountListSize = assetDetailPageForm.getPageCountsList().size();
				}
				model.addAttribute("pageCountListSize", pageCountListSize);
				model.addAttribute("AssetNotValid", exError);
				commonController.getContactInformation(request, response);
				return "orderSuppliesAssetOrder/assetDetail";				
			}
			
		return "orderSuppliesAssetOrder/assetDetail";
	}
	/**
	 * This method is used to render the catalogorder landing page from asset list page
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */

	@RequestMapping(params = "action=showCatalogOrderPage")
	public void showCatalogOrderPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		LOGGER.debug("------------------ RequestMapping of the showCatalogOrderPage -----------------------");
		PortletSession session = request.getPortletSession();
		session.removeAttribute("draftSrNumber");		
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
	
	/**
	 * This method is used to update the favorite flag for the asset 
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
	 * This is action mapping call for submitting the asset order detail page for draft 
	 * as well as taking the control to summary page  
	 * @param request   
	 * @param response   
	 * @param assetDetailPageForm   
	 * @param bindingResult   
	 * @param model   
	 * @throws Exception   
	 */
	
	@ActionMapping(params="action=submitAssetOrder")
	public void submitAssetOrder(
			ActionRequest request, 
			ActionResponse response, 
			@ModelAttribute("assetDetailPageForm") @Valid AssetDetailPageForm assetDetailPageForm, 
			BindingResult bindingResult,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,@ModelAttribute ("fleetManagementFlag") String fleetManagementFlag,			 
			Model model) throws Exception {
		LOGGER.debug("---------------------- [In] submitAssetOrder method ----------------------");
		String pageSubmitType = assetDetailPageForm.getPageSubmitType();
		String returnPage = "";
		String exError = null;
		String creditCurrency="USD";
		String attachmentDescription= request.getParameter("attachmentDescriptionID");
		LOGGER.debug("attachment description-->> "+attachmentDescription);
		commonController.getContactInformation(request, response);
		
		//LBS
		//String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			request.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			model.addAttribute("fleetManagementFlag","false");
			request.setAttribute("fleetManagementFlag", "false");
		}
		//added for page counts 		
				String pageCountType= request.getParameter("pageCountTypeID");
				String pageCountsDate= request.getParameter("pageCountsDateID");
				String pageCountValue= request.getParameter("pageCountValueID");
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
				assetDetailPageForm.setPageCountsList(pageCounts);
				
				model.addAttribute("pageCountsOriginalval", request.getParameter("pageCountValue"));
				model.addAttribute("pageCountsOriginaldate", request.getParameter("pageCountDateValid"));
				
				//page counts End
		if("confirmOrderRequest".equalsIgnoreCase(pageSubmitType)){
			LOGGER.debug("---------------------- [In] submitAssetOrder for confirm order page ----------------------");
			AssetDetailPageForm reviewAssetOrderForm = new AssetDetailPageForm();
			if(bindingResult.hasErrors()){
				
				for(int j=0;j<bindingResult.getAllErrors().size();j++){
					LOGGER.debug("Errors are in controller "+bindingResult.getAllErrors().get(j).getCode().toString());
				}
				
				List <Attachment> attachmentList = new ArrayList<Attachment>();
				PortletSession session = request.getPortletSession();
				attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
				assetDetailPageForm.setAttachmentList(attachmentList);
				attachForm.setAttachmentList(attachmentList);
				attachForm.setListOfFileTypes(listOfFileTypes);
				attachForm.setAttMaxCount(attMaxCount);
				assetDetailPageForm.setAttachmentDescription(attachmentDescription);
				attachForm.setAttachmentDescription(attachmentDescription);
				FileUploadForm fileUploadForm = new FileUploadForm();
				model.addAttribute("assetDetailPageForm", assetDetailPageForm);
				model.addAttribute("reviewAssetOrderForm", reviewAssetOrderForm);
				model.addAttribute("fileUploadForm", fileUploadForm);
				returnPage = "orderSuppliesAssetOrder/assetDetail";
			} else {
				Map<String, String> pageCountsMap=null;
				try {
					pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), request.getLocale());
					LOGGER.debug("pageCountsMap = "+ pageCountsMap);
					
				} catch (LGSDBException e) {
					LOGGER.debug("catch"+ e.getMessage());
				}
				
				String pageCountsString = getXmlOutputGenerator(request
							.getLocale()).pageCountsXML(assetDetailPageForm.getPageCountsList(),assetDetailPageForm.getPageCountsList().size(),0,pageCountsMap);
					pageCountsString = pageCountsString.replace("\n", "");	
				
				
				LOGGER.debug("pageCountsString" + pageCountsString);
				model.addAttribute("pageCountsString", pageCountsString);
			try{
				PortletSession session = request.getPortletSession();
				int pageCountListSize = 0;
				String paramPrefix = null;
				String orderQuantity = null;
				List<Part> modifiedAssetList = new ArrayList<Part>();
				List<Part> modifiedAssetListForSession = new ArrayList<Part>();
				
				/*************MPS Phase 2.1 changes **************/				
				
				List<Part> partList =  (List<Part>)session.getAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);				
				
				/*************MPS Phase 2.1 changes **************/
				for (int i=0;i<partList.size();i++){
					LOGGER.debug("Order quantity for "+i+" number part is "+partList.get(i).getOrderQuantity());
					paramPrefix = "assetPartList[" + i + "].";
					orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
					String unitPrice = partList.get(i).getUnitPrice();
					
					if ((orderQuantity !="")) {
							Part assetPartForm = new Part();
							//assetPartForm.setPartNumber(partList.get(i).getPartNumber());
							assetPartForm.setPartNumber(partList.get(i).getVendorPartNumber());
							assetPartForm.setDescription(partList.get(i).getDescription());
							assetPartForm.setPartType(partList.get(i).getPartType());
							assetPartForm.setSupplyType(partList.get(i).getSupplyType());								
							assetPartForm.setOrderQuantity(orderQuantity);
							assetPartForm.setCatalogId(partList.get(i).getCatalogId());
							assetPartForm.setSupplyId(partList.get(i).getSupplyId());
							assetPartForm.setProductId(partList.get(i).getProductId());
							assetPartForm.setSoldToNumbers(partList.get(i).getSoldToNumbers());
							assetPartForm.setSalesOrg(partList.get(i).getSalesOrg());							
                            assetPartForm.setPrefferedPartFlag(partList.get(i).isPrefferedPartFlag());
							if(assetDetailPageForm.isSplitterFlag()){
								if(assetDetailPageForm.isFinalShowPriceFlag()){
									if(unitPrice != null){
										if( (!"0".equals(unitPrice)) && (!"".equals(unitPrice))){
											
											assetPartForm.setUnitPrice(partList.get(i).getUnitPrice());
											if( (!"0".equals(orderQuantity)) && (!"".equals(orderQuantity))){
												assetPartForm.setTotalPrice(PaymentUtil.calculateTotalPrice(unitPrice, orderQuantity)) ;
											}
											assetPartForm.setCurrency(partList.get(i).getCurrency());
											creditCurrency = partList.get(i).getCurrency();
										}
									}
									
								}
							}		
							assetPartForm.setMaxQuantity(partList.get(i).getMaxQuantity());
							modifiedAssetList.add(assetPartForm);	
							modifiedAssetListForSession.add(assetPartForm);
					}
					else{

						Part assetPartForm = new Part();
						//assetPartForm.setPartNumber(partList.get(i).getPartNumber());
						assetPartForm.setPartNumber(partList.get(i).getVendorPartNumber());
						assetPartForm.setDescription(partList.get(i).getDescription());
						assetPartForm.setPartType(partList.get(i).getPartType());
						assetPartForm.setSupplyType(partList.get(i).getSupplyType());								
						assetPartForm.setOrderQuantity("");
						assetPartForm.setCatalogId(partList.get(i).getCatalogId());
						assetPartForm.setSupplyId(partList.get(i).getSupplyId());
						assetPartForm.setProductId(partList.get(i).getProductId());
						assetPartForm.setSoldToNumbers(partList.get(i).getSoldToNumbers());
						assetPartForm.setSalesOrg(partList.get(i).getSalesOrg());							
                        assetPartForm.setPrefferedPartFlag(partList.get(i).isPrefferedPartFlag());
						if(assetDetailPageForm.isSplitterFlag()){
							if(assetDetailPageForm.isFinalShowPriceFlag()){
								if(unitPrice != null){
									if( (!"0".equals(unitPrice)) && (!"".equals(unitPrice))){
										
										assetPartForm.setUnitPrice(partList.get(i).getUnitPrice());
										if( (!"0".equals(orderQuantity)) && (!"".equals(orderQuantity))){
											assetPartForm.setTotalPrice(PaymentUtil.calculateTotalPrice(unitPrice, orderQuantity)) ;
										}
										assetPartForm.setCurrency(partList.get(i).getCurrency());
										creditCurrency = partList.get(i).getCurrency();
									}
								}
								
							}
						}		
						assetPartForm.setMaxQuantity(partList.get(i).getMaxQuantity());
						//modifiedAssetList.add(assetPartForm);	
						modifiedAssetListForSession.add(assetPartForm);
				
					}
					
				}
				//Updating Part List in session
				session.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);
				//session.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED, modifiedAssetList);
				session.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED, modifiedAssetListForSession);
				if(assetDetailPageForm.isSplitterFlag()){
					if(assetDetailPageForm.isFinalTaxCalcFlag()){
						TaxContract taxContract = ContractFactory.getAssetOrderTaxContract(modifiedAssetList, assetDetailPageForm);
						ObjectDebugUtil.printObjectContent(taxContract, LEXLOGGER);
						if(taxContract.getLineInformationList().size() != 0){
							long timeBeforeCall=System.currentTimeMillis();
							TaxResult taxresult = retrieveTaxService.retrieveTaxList(taxContract);
							
							PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVETAXLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,taxContract);
							LOGGER.info("start printing lex logger");
							LEXLOGGER.logTime("** MPS PERFORMANCE TESTING WEBMETHODS CALL RETRIEVE TAX LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
							LOGGER.info("end printing lex loggger");
							
							Map<String, String> paymentDetails = PaymentUtil.calculateTotalPriceWithTax(new ArrayList<Object>(modifiedAssetList), taxresult.getLineInformationList());
							assetDetailPageForm.setTotalPrice(paymentDetails.get("grandTotal"));
							assetDetailPageForm.setSubTotalPrice(paymentDetails.get("totalPrice"));
							assetDetailPageForm.setTotalTax(paymentDetails.get("totalTax"));
							
							//Populate Taxes in the part list
							for(Price price1 : taxresult.getLineInformationList()){
								for(Part part : modifiedAssetList){
									if(price1.getSourceReferenceLineId().equalsIgnoreCase(part.getCatalogId())){
										if(!price1.getTax().equalsIgnoreCase("Unavailable")){
											part.setTaxAmount(price1.getTax());
										}
									}
								}
							}
						}
						
					}
				}				
				
				
				if (assetDetailPageForm.getPageCountsList()!=null){
					reviewAssetOrderForm.setPageCountsList(pageCounts);
					assetDetailPageForm.setPageCountsList(pageCounts);
				}
				
				List <Attachment> attachmentList = new ArrayList<Attachment>();
				attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");				
				
				if(attachmentList!=null && !attachmentList.isEmpty()){
					for (int i=0;i<attachmentList.size();i++){
						LOGGER.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! attachment names are "+attachmentList.get(i).getAttachmentName());
					}
				}
				/*START--Added for Defect 3924-Jan Release*/
				commonController.assembleDevice(assetDetailPageForm.getAsset(), request.getLocale());
				/*END--Added for Defect 3924-Jan Release*/
				assetDetailPageForm.setAttachmentList(attachmentList);
				GenericAddress shipToAddress = assetDetailPageForm.getShipToAddress();
				shipToAddress.setCity(XMLEncodeUtil.escapeXML(shipToAddress.getCity()));
				shipToAddress.setAddressName(XMLEncodeUtil.escapeXML(shipToAddress.getAddressName()));
				assetDetailPageForm.setShipToAddress(shipToAddress);				
				reviewAssetOrderForm.setPoNumber(assetDetailPageForm.getAsset().getPoNumber());
				reviewAssetOrderForm.setAttachmentList(attachmentList);
				
				GenericAddress billToAddress = (GenericAddress)session.getAttribute("selectedAssetBillToAddress", PortletSession.APPLICATION_SCOPE);
				
				assetDetailPageForm.setBillToAddress(billToAddress);
				
				reviewAssetOrderForm.setAssetPartList(modifiedAssetList);
				assetDetailPageForm.setAttachmentDescription(attachmentDescription);
				attachForm.setAttachmentDescription(attachmentDescription);
				
				assetDetailPageForm.refreshSubmitToken(request);
				session.setAttribute("assetDetailPageForm", assetDetailPageForm);
				model.addAttribute("assetDetailPageForm", assetDetailPageForm);
				model.addAttribute("reviewAssetOrderForm", reviewAssetOrderForm);
				model.addAttribute("pageCountListSize", pageCountListSize);
				model.addAttribute("creditCurrency",creditCurrency);
				returnPage = "orderSuppliesAssetOrder/reviewAssetOrder";
			}catch(Exception e){
				PortletSession session = request.getPortletSession();
					exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
							"tax.validation.error", request.getLocale());
					LOGGER.debug("Going back exception in tax call-----------> ");
					FileUploadForm fileUploadForm = new FileUploadForm();
					int pageCountListSize = 0;
					List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
					if (attachmentList != null){
						attachForm.setAttachmentList(attachmentList);
					}
					LOGGER.debug("listOfFileTypes  = "+ listOfFileTypes + attMaxCount);
					attachForm.setListOfFileTypes(listOfFileTypes);
					attachForm.setAttMaxCount(attMaxCount);
					
					//changes for mps2.1
					if(attachForm.getAttachmentList() !=null){
						fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
						}
						//changes for mps2.1
					model.addAttribute("attachmentForm", attachForm);
					model.addAttribute("fileUploadForm", fileUploadForm);
					model.addAttribute("assetDetailPageForm", assetDetailPageForm);
					model.addAttribute("flowType", "fromReviewPage");//Changes for MPS 2.1
					if(assetDetailPageForm.getPageCountsList()!=null){
						pageCountListSize = assetDetailPageForm.getPageCountsList().size();
					}
					model.addAttribute("pageCountListSize", pageCountListSize);					
					model.addAttribute("Error", exError);					
					commonController.getContactInformation(request, response);					
					returnPage="orderSuppliesAssetOrder/assetDetail";				
			}
			}
			LOGGER.debug("---------------------- [Out] submitAssetOrder for confirm order page ----------------------");
			
		}else if("draftOrderRequest".equalsIgnoreCase(pageSubmitType)){
			LOGGER.debug("---------------------- [In] submitAssetOrder for draft order page ----------------------");
			AssetDetailPageForm reviewAssetOrderForm = new AssetDetailPageForm();
			if(bindingResult.hasErrors()){
				
				for(int j=0;j<bindingResult.getAllErrors().size();j++){
					LOGGER.debug("Errors are in controller "+bindingResult.getAllErrors().get(j).getCode().toString());
				}
				model.addAttribute("assetDetailPageForm", assetDetailPageForm);
				model.addAttribute("reviewAssetOrderForm", reviewAssetOrderForm);
				FileUploadForm fileUploadForm = new FileUploadForm();
				model.addAttribute("fileUploadForm", fileUploadForm);				
				returnPage = "orderSuppliesAssetOrder/assetDetail";
			} else {
			String paramPrefix = null;
			String orderQuantity = null;
			PortletSession session = request.getPortletSession();
			List<Part> modifiedAssetList = new ArrayList<Part>();
			for (int i=0;i<assetDetailPageForm.getAssetPartList().size();i++){
				LOGGER.debug("Order quantity for "+i+" number part is "+assetDetailPageForm.getAssetPartList().get(i).getOrderQuantity());
				paramPrefix = "assetPartList[" + i + "].";
				orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
				String unitPrice = assetDetailPageForm.getAssetPartList().get(i).getUnitPrice();
				if (orderQuantity !=null) {
					if ((!"".equals(unitPrice)) || (unitPrice !=null)) {
						Part assetPartForm = new  Part();
						assetPartForm.setPartNumber(assetDetailPageForm.getAssetPartList().get(i).getPartNumber());
						assetPartForm.setDescription(assetDetailPageForm.getAssetPartList().get(i).getDescription());
						assetPartForm.setPartType(assetDetailPageForm.getAssetPartList().get(i).getPartType());
						assetPartForm.setOrderQuantity(assetDetailPageForm.getAssetPartList().get(i).getOrderQuantity());
						assetPartForm.setCatalogId(assetDetailPageForm.getAssetPartList().get(i).getCatalogId());
						assetPartForm.setSupplyType(assetDetailPageForm.getAssetPartList().get(i).getSupplyType());						
						assetPartForm.setSupplyId(assetDetailPageForm.getAssetPartList().get(i).getSupplyId());
						assetPartForm.setProductId(assetDetailPageForm.getAssetPartList().get(i).getProductId());
						assetPartForm.setPrefferedPartFlag(assetDetailPageForm.getAssetPartList().get(i).isPrefferedPartFlag()); //added
						
						if(assetDetailPageForm.isFinalShowPriceFlag()){
							assetPartForm.setUnitPrice(assetDetailPageForm.getAssetPartList().get(i).getUnitPrice());							
							assetPartForm.setCurrency(assetDetailPageForm.getAssetPartList().get(i).getCurrency());
						}						
						modifiedAssetList.add(assetPartForm);
					}
				}
				assetDetailPageForm.getAssetPartList().get(i).setOrderQuantity(orderQuantity);
				LOGGER.debug("After setting order quantity for "+i+" number part is "+assetDetailPageForm.getAssetPartList().get(i).getOrderQuantity());
			}
			reviewAssetOrderForm.setAssetPartList(modifiedAssetList);
			
			//Updating Part List in session
			session.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);
			session.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED, modifiedAssetList);
			
			
			if (assetDetailPageForm.getPageCountsList()!=null){
				reviewAssetOrderForm.setPageCountsList(pageCounts);
				assetDetailPageForm.setPageCountsList(pageCounts);
				}
			List <Attachment> attachmentList = new ArrayList<Attachment>();
			attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
			assetDetailPageForm.setAttachmentList(attachmentList);
			reviewAssetOrderForm.setBillToAddress(assetDetailPageForm.getBillToAddress());			
			assetDetailPageForm.setAttachmentDescription(attachmentDescription);
			CreateConsumableServiceRequestContract createServiceReqContract = 
				ContractFactory.getConsumableDraftReqContract(assetDetailPageForm, reviewAssetOrderForm, request);
			
			//Updated By MPS Offshore Team for create Vendor Order
			List<String> userRoleList = PortalSessionUtil.getUserRoles(session);		
			boolean isVendorFlag = false;
			if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
			{
				isVendorFlag = true;
			}
			Map<String,String> userDetailsMap=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
			LEXLOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(createServiceReqContract, LEXLOGGER);
			LEXLOGGER.info("end printing lex loggger");
			Long startTime = System.currentTimeMillis();			

			CreateServiceRequestResult result = createConsumableRequest.createConsumableServiceRequest(createServiceReqContract,userDetailsMap,isVendorFlag);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_CREATEDRAFTSR, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,createServiceReqContract);
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING WEBMETHODS CALL createConsumableServiceRequest ==>: " + (System.currentTimeMillis()- startTime)/1000.0+" SR NUMBER IS "+result.getServiceRequestNumber());
			LOGGER.debug("ServiceRequest number received from webmethod is "+result.getServiceRequestNumber());
			
			String serviceRequestNumber = result.getServiceRequestNumber();
			String srRowId = result.getServiceRequestRowId();
			//Received the servicerequestnumber from webmethods. Lets call aMind
			AttachmentContract contract = new AttachmentContract();
			List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
			if(attachmentList!=null && !attachmentList.isEmpty()){
				for (int i=0;i<attachmentList.size();i++){
					if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
						createSRAttachmentList.add(attachmentList.get(i));
					}
				}
				if(createSRAttachmentList!=null && !createSRAttachmentList.isEmpty()){
					contract.setAttachments(createSRAttachmentList);
					contract.setRequestType("Service Request");
					contract.setIdentifier(srRowId);					
					ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
					long timeBeforeCall=System.currentTimeMillis();
					attachmentService.uploadAttachments(contract);
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_UPLOADATTACHMENTS, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
					LOGGER.info("start printing lex logger");
					LEXLOGGER.logTime("** MPS PERFORMANCE TESTING UPLOAD ATTACHMENT ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
					LOGGER.info("end printing lex loggger");
				}
				
			}			
			
			assetDetailPageForm.setMaxPartsToBeOrdered(getMaxPartsToBeOrdered());
			assetDetailPageForm.setRelatedServiceRequestedNumber(assetDetailPageForm.getRelatedServiceRequestedNumber());
			attachForm.setAttachmentList(attachmentList);
			attachForm.setListOfFileTypes(listOfFileTypes);
			attachForm.setAttMaxCount(attMaxCount);
			FileUploadForm fileUploadForm = new FileUploadForm();
			if(attachForm.getAttachmentList() !=null){
				fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
				}
				//changes for mps2.1
			model.addAttribute("attachmentForm", attachForm);
			model.addAttribute("serviceRequestNumber", serviceRequestNumber);
			session.setAttribute("assetDetailPageForm", assetDetailPageForm);
			model.addAttribute("assetDetailPageForm", assetDetailPageForm);
			model.addAttribute("srnumber",serviceRequestNumber);
			model.addAttribute("draftConfirmation","draftConfirmation");
					

		
					
			model.addAttribute("fileUploadForm", fileUploadForm);
			model.addAttribute("flowType", "afterSaveAsDraft");//Changes for MPS 2.1
			returnPage = "orderSuppliesAssetOrder/assetDetail";
			LOGGER.debug("---------------------- [Out] submitAssetOrder for draft order page ----------------------");
		}
		}		
			
		request.setAttribute("returnPage", returnPage);
		
		response.setRenderParameter("action", "submitAssetOrderRender");
	}
	
	/**
	 * This method is used to render the jsp based on the flow. 
	 * It might be assetDetail.jsp for draft order OR summary page for normal create SR. 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return page to be rendered 
	 * @throws Exception 
	 */
	@RequestMapping(params="action=submitAssetOrderRender")
	public String submitAssetOrderRender(Model model, 
			RenderRequest request, 
			RenderResponse response
			) throws Exception {
		LOGGER.debug("Entering submitAssetOrderRender");
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID("displayAttachment");
		model.addAttribute("displayAttachment", resURL1.toString());
		//LBS
		String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			request.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			model.addAttribute("fleetManagementFlag","false");
			request.setAttribute("fleetManagementFlag", "false");
		}
		String returnPage = (String)request.getAttribute("returnPage");	
		LOGGER.debug("Exiting submitAssetOrderRender");
		return returnPage;
	}
	
	/**
	 * This method is used to create order. 
	 * In this method webmethods webservice is called as well as amind attachment 
	 * service is called if the order is having any attachment.. 
	 * @param request 
	 * @param response 
	 * @param assetDetailPageForm 
	 * @param reviewAssetOrderForm 
	 * @param model 
	 * @return confirmation jsp page 
	 * @throws Exception 
	 * @throws Exception 
	 */
	
	//Here you have to call webmethod webservice and also you have to call amind attachment service
	@RenderMapping(params="action=confirmAssetOrder")
	public String confirmAssetOrder(
			RenderRequest request, 
			RenderResponse response,  
			@ModelAttribute("assetDetailPageForm") AssetDetailPageForm assetDetailPageForm,
			@ModelAttribute("reviewAssetOrderForm") AssetDetailPageForm reviewAssetOrderForm,@ModelAttribute ("fleetManagementFlag") String fleetManagementFlag,
			Model model) throws Exception{
		LOGGER.debug("---------------------- [In] confirmAssetOrder method ----------------------");
		boolean srCreationFlag = true;
		if (PortalSessionUtil.isDuplicatedSubmit(request,assetDetailPageForm)) {
					
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, 
					"exception.serviceRequest.duplicateSubmission", null, request.getLocale()));
			List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
			List <Attachment> attachmentList = new ArrayList<Attachment>();
			attachmentList = reviewAssetOrderForm.getAttachmentList();
			if(attachmentList!=null && !attachmentList.isEmpty()){
				List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
				for(int i=0;i<reviewAssetOrderForm.getAttachmentList().size();i++){
					AttachmentFile attachmentFile = new AttachmentFile();
					attachmentFile.setFileName(attachmentList.get(i).getDisplayAttachmentName());
					attachmentFile.setFileSize(attachmentList.get(i).getSize());
					displayAttachmentList.add(attachmentFile);
					if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
						createSRAttachmentList.add(attachmentList.get(i));
					}
					LOGGER.debug("File name before service "+reviewAssetOrderForm.getAttachmentList().get(i).getAttachmentName());
				}
				model.addAttribute("displayAttachmentList", displayAttachmentList);
				assetDetailPageForm.setDisplayAttachmentList(displayAttachmentList);
				}
			
			
		}else{
		PortletSession session = request.getPortletSession();
		
		int pageCountListSize = 0;
		LOGGER.debug("assetDetailPageForm no of "+assetDetailPageForm.getAssetPartList().size());
		for (int i=0;i<assetDetailPageForm.getAssetPartList().size();i++){
			LOGGER.debug("After setting order quantity for "+i+" number part is "+assetDetailPageForm.getAssetPartList().get(i).getOrderQuantity());
		}
		
		if(assetDetailPageForm.getCreditCardToken()!=null){
			LOGGER.debug("Credit Card token retrieved from form is "+assetDetailPageForm.getCreditCardToken());
		}
		String creditFlag = "false";
		if(assetDetailPageForm.getCreditFlag()!=null && assetDetailPageForm.getCreditFlag().equalsIgnoreCase("true")){
			creditFlag = assetDetailPageForm.getCreditFlag();
		}
		
		//LBS
		//String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			request.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			model.addAttribute("fleetManagementFlag","false");
			request.setAttribute("fleetManagementFlag", "false");
		}
		
		//This is the time to call webmethods webservice
		CreateConsumableServiceRequestContract createServiceReqContract = 
			ContractFactory.getConsumableServiceReqContract(assetDetailPageForm, reviewAssetOrderForm, request);
		
		/* Added by sankha for LEX:AIR00075446 start */
		String date = assetDetailPageForm.getRequestedDeliveryDate();
		
		if(date !=null  && !("".equals(date))){
			String dateFormatterLocaleSpecific = DateUtil.getDateFormatByLang(request.getLocale().getLanguage());
			String dateFormatter = "MM/dd/yyyy";
			String dateFormatterForLocaleSpecific = dateFormatterLocaleSpecific.replace("mm", "MM");
			SimpleDateFormat genericDateFormat = new SimpleDateFormat(dateFormatter);
			SimpleDateFormat localeSpecificDateFormat = new SimpleDateFormat(dateFormatterForLocaleSpecific);
			Date DeliveryDate = new Date();
			try {
				DeliveryDate = localeSpecificDateFormat.parse(date);
			} catch (ParseException e) {
				LOGGER.info("Exception occured.");
			}
			String requestedDeliveryDate = genericDateFormat.format(DeliveryDate);
			
			createServiceReqContract.setRequestedDeliveryDate(requestedDeliveryDate);
		}
		
		/* End */
		
		//Updated By MPS Offshore Team for create Vendor Order
		List<String> userRoleList = PortalSessionUtil.getUserRoles(session);		
		boolean isVendorFlag = false;
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			isVendorFlag = true;
		}
		Map<String, String> pageCountsMap=null;
		try {
			pageCountsMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_FOR_ASSET.getValue(), request.getLocale());
			LOGGER.debug("pageCountsMap = "+ pageCountsMap);
			
		} catch (LGSDBException e) {
			LOGGER.debug("catch"+ e.getMessage());
		}
		String pageCountsString = getXmlOutputGenerator(request
				.getLocale()).pageCountsXML(reviewAssetOrderForm.getPageCountsList(),reviewAssetOrderForm.getPageCountsList().size(),0,pageCountsMap);
		pageCountsString = pageCountsString.replace("\n", "");
		model.addAttribute("pageCountsString", pageCountsString);
		
		/*START--Added for Defect 3924-Jan Release*/
		commonController.assembleDevice(assetDetailPageForm.getAsset(), request.getLocale());
		/*END--Added for Defect 3924-Jan Release*/
		
		Map<String,String> userDetailsMap=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("Related SR number ---->"+assetDetailPageForm.getRelatedServiceRequestedNumber());
		
		CreateServiceRequestResult result = null;
		try{
			long timeBeforeCall=System.currentTimeMillis();
			result = createConsumableRequest.createConsumableServiceRequest(createServiceReqContract,userDetailsMap,isVendorFlag);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_CREATEASSETORDER, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,createServiceReqContract);
			LOGGER.info("start printing lex logger");
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING WEBMETHODS CALL CREATE CONSUMABLES SERVICE REQUEST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
		}catch (Exception e) {
			LOGGER.info("Exception in SR creation.");
			srCreationFlag = false;
		}
		
		
		if(result != null){
			LOGGER.debug("ServiceRequest number received from webmethod is "+result.getServiceRequestNumber());
			
			String serviceRequestNumber = result.getServiceRequestNumber();
			String srRowId = result.getServiceRequestRowId();
			
			List <Attachment> attachmentList = reviewAssetOrderForm.getAttachmentList();
			if(attachmentList!=null && !attachmentList.isEmpty()){
			
				try{
					commonController.uploadAttachment(reviewAssetOrderForm.getAttachmentList(), srRowId);
					List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
					for(int i=0;i<reviewAssetOrderForm.getAttachmentList().size();i++){
						AttachmentFile attachmentFile = new AttachmentFile();
						attachmentFile.setFileName(attachmentList.get(i).getDisplayAttachmentName());
						attachmentFile.setFileSize(attachmentList.get(i).getSize());
						displayAttachmentList.add(attachmentFile);
						
						LOGGER.debug("File name before service "+reviewAssetOrderForm.getAttachmentList().get(i).getAttachmentName());
					}
					
					model.addAttribute("displayAttachmentList", displayAttachmentList);
					assetDetailPageForm.setDisplayAttachmentList(displayAttachmentList);
				}catch (Exception e) {
					LOGGER.info("Exception in Attachment upload.");
					model.addAttribute("attachmentException", "attachfailed");
				}
			
				
			}
			model.addAttribute("serviceRequestNumber", serviceRequestNumber);	
		}
		
		//this is to enable re-submit of SR form on submit/draft exception
		Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, session.PORTLET_SCOPE);
		BaseForm baseForm = (BaseForm)assetDetailPageForm;
		baseForm.setSubmitToken(tokenInSession);
		model.addAttribute("pageCountListSize", pageCountListSize);
		
		model.addAttribute("creditFlag", creditFlag);
		model.addAttribute("assetDetailPageForm", assetDetailPageForm);
		model.addAttribute("reviewAssetOrderForm", reviewAssetOrderForm);
		session.removeAttribute("attachmentList");
		session.removeAttribute("assetDetailPageForm");
		}
		LOGGER.debug("----------------------- [OUT] confirmAssetOrder method ----------------------");
		if(srCreationFlag){
			return "orderSuppliesAssetOrder/confirmAssetOrder";
		}else{
			String srCreationValidation = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"exception.sr.save", request.getLocale());
			model.addAttribute("srCreationError",srCreationValidation);
			return "orderSuppliesAssetOrder/reviewAssetOrder";
		}
		
	}
		
	
	/**
	 * This method is used to print the assetlist grid 
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return assetListPrint.jsp to show the print page
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printAssetList")
	public String showPrintAssetListPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		LOGGER.debug("-------------showPrintAssetListPage started---------");		
		return "orderSuppliesAssetOrder/assetListPrint";
	}
	/**
	 * This method is used to print the order confirmation page 
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return orderConfirmPrint.jsp 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=orderConfirmPrint")
	public String showOrderConfirmPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "orderSuppliesAssetOrder/orderConfirmPrint";
	}
	/**
	 * This method is used to email the order confirmation page 
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return orderEmail.jsp 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=orderAssetEmail")
	public String showOrderAssetEmail(RenderRequest request, RenderResponse response, Model model) throws Exception{
		LOGGER.debug("-------------showEmail started---------");		
		return "orderSuppliesAssetOrder/orderEmail";	
	}
	
	
	/**
	 * @param request  
	 * @param response  
	 * @param model  
	 * @throws Exception  
	 */
	@ResourceMapping("downloadAssetListURL")
	public void downloadAssetListURL(ResourceRequest request,
			ResourceResponse response) throws Exception{
		LOGGER.debug("-------------download asset list started---------");
		String downloadType = request.getParameter("downloadType");
		Locale locale = request.getLocale();
		PortletSession session = request.getPortletSession();
		AssetListContract contract =  (AssetListContract) session.getAttribute("downLoadContract");
		LOGGER.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
		contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));
		try {
			Long startTime = System.currentTimeMillis();
			AssetListResult assetListResult = orderSuppliesAssetService.retrieveDeviceList(contract);//amind service method
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEDEVICELIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE DeviceList ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			List<Asset> assetList = assetListResult.getAssets();
			if("csv".equals(downloadType)){
				downloadAssetListCSV(response,assetList,locale);
			}else if("pdf".equals(downloadType)){
				downloadAssetListPDF(response,assetList,locale);
			}else{				
				throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.portlet.downloadException", null, request.getLocale()));
			}
		} finally {
			//Release the handle once it is used by aMind
		}
		LOGGER.debug("-------------download device list end---------");
	}
	
	/**
	 * Method responsible for back button in add asset review page 
	 * @param req 
	 * @param resp 
	 * @param assetDetailPageForm 
	 * @param model  
	 * @return String 
	 */

	@RenderMapping(params = "action=goToAssetDetail")
	public String goToAddAsset(RenderRequest req, RenderResponse resp, @ModelAttribute("assetDetailPageForm") 
			AssetDetailPageForm assetDetailPageForm,@ModelAttribute ("attachmentForm") AttachmentForm attachForm,@ModelAttribute ("fleetManagementFlag") String fleetManagementFlag, Model model)	
	{
		model.addAttribute("pageCountsOriginalval", req.getParameter("pageCountValue"));
		model.addAttribute("pageCountsOriginaldate", req.getParameter("pageCountDateValid"));
		//LBS
		//String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
		if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
			LOGGER.debug("Setting LBS Flag to true ");
			model.addAttribute("fleetManagementFlag", "true");
			req.setAttribute("fleetManagementFlag", "true");
		}
		else{
			LOGGER.debug("Setting LBS Flag to false");
			model.addAttribute("fleetManagementFlag","false");
			req.setAttribute("fleetManagementFlag", "false");
		}
		PortletSession session = req.getPortletSession();
		FileUploadForm fileUploadForm = new FileUploadForm();
		int pageCountListSize = 0;		
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		if (attachmentList != null){
			attachForm.setAttachmentList(attachmentList);
		}
		LOGGER.debug("listOfFileTypes  = "+ listOfFileTypes + attMaxCount);
		attachForm.setListOfFileTypes(listOfFileTypes);
		attachForm.setAttMaxCount(attMaxCount);
		
		//changes for mps2.1
		if(attachForm.getAttachmentList() !=null){
			fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
			}
			//changes for mps2.1
		model.addAttribute("attachmentForm", attachForm);
		model.addAttribute("fileUploadForm", fileUploadForm);
		model.addAttribute("assetDetailPageForm", assetDetailPageForm);
		model.addAttribute("flowType", "fromReviewPage");//Changes for MPS 2.1
		if(assetDetailPageForm.getPageCountsList()!=null){
			pageCountListSize = assetDetailPageForm.getPageCountsList().size();
		}
		model.addAttribute("pageCountListSize", pageCountListSize);
		commonController.getContactInformation(req, resp);		
		return "orderSuppliesAssetOrder/assetDetail";
	}
	/*Method gotoControlPanel added for Defect 3924- Jan Release*/
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=gotoControlPanel")
	public String gotoControlPanel(RenderRequest request, RenderResponse response,
			Model model) throws Exception {
		model.addAttribute("controlPanelURL",request.getParameter("controlPanelURL"));
		model.addAttribute("pageName", "Consumable Order");
		return "controlPanelPage";
	}
	
	
	/**
	 * This method is used to download asset list in CSV 
	 * @param response 
	 * @param assetList 
	 * @param locale 
	 * @throws IOException 
	 */
	private void downloadAssetListCSV(ResourceResponse response, List<Asset> assetList, Locale locale) throws IOException {
		String fileName = null;
		fileName = DownloadFileLocalizationUtil.getAssetListFileName(locale) + ".csv";
		response.setProperty("Content-disposition", "attachment; filename="	+ fileName);
		response.setContentType("text/csv");
		PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
		out.print(DownloadFileUtil.assembleToAssetListCSV(assetList, locale));
		out.flush();
		out.close();
	}
	/**
	 * This method is used to download asset list in pdf 
	 * @param response 
	 * @param assetList 
	 * @param locale 
	 * @throws IOException 
	 */
	private void downloadAssetListPDF(ResourceResponse response, List<Asset> assetList,
			Locale locale) throws IOException {
		String fileName = DownloadFileLocalizationUtil.getAssetListFileName(locale) + ".pdf";
		response.setProperty("Content-disposition", "attachment; filename="	+ fileName);
		response.setContentType("application/pdf");
		String[] headers = new String[]{"Serial Number", "Product Model" , "Asset Tag",
				"Device Hostname", "IP Address", "Address Name", "Contract Type", "Device Phase" , "Address Line1", "City","State", "Province", "Country",
				"ZIP / Postal Code", "Primary Contact First Name", "Primary Contact Last Name", "Primary Contact Email", "Primary Contact Phone"};
		String[] generatorPatterns = new String[]{"serialNumber","productLine","assetTag",
				"hostName", "ipAddress", "installAddress.addressName","agreementType","devicePhase","installAddress.addressLine1",
				"installAddress.city","installAddress.state","installAddress.province","installAddress.country",
				"installAddress.postalCode","assetContact.firstName","assetContact.lastName","assetContact.emailAddress","assetContact.workPhone"};

		PdfPReportGenerator generator = new PdfPReportGenerator(headers, generatorPatterns, assetList);
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
					LOGGER.error("IO Exception occured");
				}
			}
		}
	}
	/**
	 * @param inputStr 
	 * @return String 
	 */
	private String returnFileName(String inputStr){
		if(inputStr.indexOf("\\")!=-1){
    		int i=inputStr.lastIndexOf("\\");
    		String str_Return=inputStr.substring(i+1,inputStr.length());
    		return str_Return;
    		
	}   	
	else{
		return inputStr;
		
	}
	}
	
		
	/**
	 * ResourceMapping for the retrieve Price List for Parts 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("retriveretrievePriceURL")
	public void retrievePriceForParts(ResourceRequest request, ResourceResponse response) throws Exception
	{
		LOGGER.debug("-----------------------[IN]retrievePriceForParts----------------------");
		LOGGER.debug("Payment Type Selected By User:: "+request.getParameter("paymentType"));
		LOGGER.debug("Show Price Flag:: "+request.getParameter("showPriceFlag"));
		LOGGER.debug("Credit Flag:: "+request.getParameter("creditFlag"));
		LOGGER.debug("PO Flag:: "+request.getParameter("poFlag"));
		
		PortletSession portletSession = request.getPortletSession();
		List<Part> finalPartList = new ArrayList<Part>();
		String selectedPaymentType = request.getParameter("paymentType");
		Map<String,Part> tempPartMap = new HashMap<String, Part>();
		
		PaymentUtil.setFinalCatalogFlags(portletSession, request.getParameter("showPriceFlag"), request.getParameter("creditFlag"), request.getParameter("poFlag"), request.getParameter("paymentType"),"Asset");
		
		Map<String,Boolean> paymentFinalFlags = (Map<String,Boolean>)portletSession.getAttribute(ChangeMgmtConstant.CATFINALFLAGS,PortletSession.APPLICATION_SCOPE);
		LOGGER.debug("paymentFinalFlags :: "+paymentFinalFlags);
		boolean finalShowPriceFlag = paymentFinalFlags.get("finalShowPriceFlag");
		boolean finalTaxCalcFlag = paymentFinalFlags.get("finalTaxCalcFlag");
		boolean finalCreditFlag = paymentFinalFlags.get("finalCreditFlag");
		boolean finalPOFlag = paymentFinalFlags.get("finalPOFlag");
		LOGGER.debug("Payment Type Selected :: "+request.getParameter("paymentType"));
		String soldToNumber = null;   //Sold to number for Pay Later flow
		
		List<Part> assetPartList = (List<Part>)portletSession.getAttribute(ChangeMgmtConstant.ASSET_PART_LIST);
		LOGGER.debug("Part list size before call :: "+assetPartList.size());
		if(selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.PAYMENT_TYPE_PAY_NOW)){//Only Parts with Pay Now value will appear in the List
			LOGGER.debug("selectedPaymentType :: "+selectedPaymentType);
			for(Part part : assetPartList){
				LOGGER.debug("part :: "+part.getPaymentTypes());
				if(part.getPaymentTypes() != null){
					for(String paymentType : part.getPaymentTypes()){
						if(paymentType.equalsIgnoreCase(selectedPaymentType)){
							finalPartList.add(part);
							tempPartMap.put(part.getPartNumber(), part);
							LOGGER.debug("Part Sold To nos :: "+part.getSoldToNumbers());
							
						}
					}	
				}
				
			}
			
		}else if(selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.CONSUMABLE_PAYMENT_TYPE_PAY_LATER) || selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.CONSUMABLE_PAYMENT_TYPE_UBB)){//Parts with Pay Later or Lease value will appear in the List also blank values
			for(Part part : assetPartList){
				if(part.getPaymentTypes() != null){
					for(String paymentType : part.getPaymentTypes()){
						if(paymentType.equalsIgnoreCase(selectedPaymentType) || paymentType.equalsIgnoreCase("")){
							finalPartList.add(part);
							tempPartMap.put(part.getContractLineItemId(), part);
							LOGGER.debug("Sold To list :::"+part.getSoldToNumbers());
							if(soldToNumber == null){
								if(part.getSoldToNumbers() != null && part.getSoldToNumbers().size()>0){
									soldToNumber = part.getSoldToNumbers().get(0);
									LOGGER.debug("Sold to for pay later call :::" + soldToNumber);
									portletSession.removeAttribute("SoldToNumber",PortletSession.APPLICATION_SCOPE);
									portletSession.setAttribute(ChangeMgmtConstant.PAY_LATER_SOLDTONUMBER,soldToNumber,PortletSession.APPLICATION_SCOPE);
								}
								
							}
							
						}
					}
				}
			}
		}
		if(finalShowPriceFlag){
			
			if(selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.CONSUMABLE_PAYMENT_TYPE_PAY_LATER)){				
				PriceContract priceContract = new PriceContract();
				List<Price> priceInputList = new ArrayList<Price>();		
				for(Part part:finalPartList){
					part.setUnitPrice("");
					part.setCurrency("");
					Price price = new Price();
					String partNumber = part.getPartNumber();
					price.setPartNumber(partNumber);
					priceInputList.add(price);
				}
				String contractNo = finalPartList.get(0).getContractNo()!= null ?finalPartList.get(0).getContractNo():"";
				priceContract.setContractNumber(contractNo);
				priceContract.setPriceList(priceInputList);
				PriceResult result=null;
				long timeBeforeCall=System.currentTimeMillis();
				try{
					result = retrieveTonerPriceService.retrieveTonerPriceList(priceContract);
				}catch (Exception e) {
					LOGGER.debug("exception in pay latter price call");

				}
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVETONERPRICELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,priceContract);
				LOGGER.info("start printing lex logger");
				LEXLOGGER.logTime("** MPS PERFORMANCE TESTING SAP CALL RETRIVE TONER PRICE LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
				LOGGER.info("end printing lex loggger");
				LOGGER.debug("result :: "+result);
				if(result !=null){
				if((result.getPriceOutputList() != null) && (result.getPriceOutputList().size()!=0)){
					for(Price price:result.getPriceOutputList()){
						for(Part tempPart :  finalPartList){
							if(tempPart.getPartNumber().equalsIgnoreCase(price.getPartNumber())){
								tempPart.setUnitPrice(price.getPrice());
								tempPart.setCurrency(price.getCurrency());
							}
							
						}
						
					}
				}
				}
			}else if(selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.PAYMENT_TYPE_PAY_NOW)){			
				PriceContract priceContract = new PriceContract();
				List<Price> priceInputList = new ArrayList<Price>();		
				for(Part part:finalPartList){
					part.setUnitPrice("");
					part.setCurrency("");
					Price price = new Price();
					String contractLineItemId = part.getContractLineItemId();
					price.setContractLineItemId(contractLineItemId);
					priceInputList.add(price);
				}
				String contractNo = finalPartList.get(0).getContractNo()!= null ?finalPartList.get(0).getContractNo():"";
				priceContract.setContractNumber(contractNo);
				priceContract.setPriceList(priceInputList);
				
				long timeBeforeCall=System.currentTimeMillis();
				PriceResult result=null;
				try{
					result = retrievePriceService.retrievePriceList(priceContract);
				}catch (Exception e) {
					LOGGER.debug("exception in pay now price call");
				}
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEPRICELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,priceContract);
				LOGGER.info("start printing lex logger");
				LEXLOGGER.logTime("** MPS PERFORMANCE TESTING CREATE CM ASSET SR ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
				LOGGER.info("end printing lex loggger");
				//Tobe uncommented later when Price call is ready
				LOGGER.debug("result :: "+result);
				if(result != null){
				if((result.getPriceOutputList() != null) && (result.getPriceOutputList().size()!=0)){
					for(Price price:result.getPriceOutputList()){
						for(Part tempPart :  finalPartList){
							if(tempPart.getContractLineItemId().equalsIgnoreCase(price.getContractLineItemId())){
								tempPart.setUnitPrice(price.getPrice());
								tempPart.setCurrency(price.getCurrency());
							}
							
						}
					}
				}
				}
			}
			
			
		}
		String partListTableContent = getXmlOutputGenerator(request.getLocale()).createPartListXML(finalPartList, finalShowPriceFlag, selectedPaymentType);
		portletSession.removeAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);//removing part lis from session
		portletSession.setAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED, finalPartList);		
		StringBuffer responseBody=new StringBuffer();
		responseBody.append("\"finalTaxCalcFlag\":\""+finalTaxCalcFlag+"\"");
		responseBody.append(","+"\"finalShowPriceFlag\":\""+finalShowPriceFlag+"\"");
		responseBody.append(","+"\"finalCreditFlag\":\""+finalCreditFlag+"\"");
		responseBody.append(","+"\"finalPOFlag\":\""+finalPOFlag+"\"");
		responseBody.append(","+"\"noOfPart\":\""+finalPartList.size()+"\"");
		responseBody.append(","+"\"partListTableContent\":\""+partListTableContent+"\"");
		responseBody.insert(0, "{");
		responseBody.insert(responseBody.length(), "}");
		PrintWriter out =  response.getWriter();
		response.setContentType("text/html");
		out.print(responseBody.toString());
		out.flush();
		out.close();
		responseBody.delete(0, responseBody.length());	
	}
	
	/**
	 * This method is used to retrieve the List of Bill To Address and set them in a select Box
	 * @param billToList   
	 * @param request   
	 * @return dropdownValue     
	 */
	@ResourceMapping(value="getBillToAddressList")
	private void getBillToAddressList(ResourceRequest request,ResourceResponse response) throws Exception{
		String contractNumber = "";
		String agreementId = "";
		if(request.getParameter("contractNumber")!=null){
			contractNumber = request.getParameter("contractNumber");
		}
		PortletSession session = request.getPortletSession();
		GenericAddress billToAddressSelected = (GenericAddress)session.getAttribute("selectedAssetBillToAddress", PortletSession.APPLICATION_SCOPE);
		if(request.getParameter("agreementId")!=null){
			agreementId = request.getParameter("agreementId");
		}
		
		AgreementSoldToNumberContract contract = new AgreementSoldToNumberContract();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contract.setAgreementId(agreementId);
		contract.setContractNumber(contractNumber);
		contract.setSessionHandle(crmSessionHandle);
		
		long timeBeforeCall=System.currentTimeMillis();
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		AgreementSoldToNumberResult result = globalService.retrieveSoldToList(contract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVESOLDTOLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE SOLDTO LIST FOR ASSET ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		LEXLOGGER.info("end printing lex loggger");
		
		
		List<String> soldToList = result.getSoldToNumbers();
		AddressListContract addressListContract = new AddressListContract();
		if(soldToList!=null){
			//modified for 10 digit sold to number
			List<String> soldToListFormated=new ArrayList<String>();
			for(int k=0;k<soldToList.size();k++){
				StringBuffer sb=new StringBuffer();
				int soldToLength=soldToList.get(k).length();
				if(soldToLength<10){
					int diff = 10-soldToLength;
					for(int j=0;j<diff;j++){
						sb=sb.append("0");
					}
				}
				sb=sb.append(soldToList.get(k));
				soldToListFormated.add(sb.toString());
			}
			addressListContract.setSoldToNumbers(soldToListFormated);
			//modified for 10 digit sold to number end 
		}
		addressListContract.setSessionHandle(crmSessionHandle);
		timeBeforeCall=System.currentTimeMillis();
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(addressListContract, LEXLOGGER);
		AddressListResult addressResult =  globalService.retrieveBillToAddressList(addressListContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEBILLTOADDRESSLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,addressListContract);
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE BILL TO ADDRESS LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		LEXLOGGER.info("end printing lex loggger");
		List<GenericAddress> billToList = addressResult.getAddressList();
		
		
		int loopCount = 0;
		StringBuilder sb = new StringBuilder();
		Map<String, GenericAddress> billToMapAsset = new HashMap<String, GenericAddress>();
		String SELECT_STR = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "requestInfo.option.select.actual", request.getLocale());
		if (billToList == null) {
			sb.append("<complete>");
			sb.append("<option value=\"\" selected=\"selected\">-" +SELECT_STR+ "-</option>");
			sb.append("</complete>");
		} else{
			sb.append("<complete>");
			if(billToAddressSelected == null ){
				sb.append("<option selected=\"selected\" value=\"\">-" +SELECT_STR+ "-</option>");
				}
			for (GenericAddress billTo:billToList) {
				billToMapAsset.put(String.valueOf(loopCount), billTo);
				
				if(billToAddressSelected != null && billToAddressSelected.getAddressId().equalsIgnoreCase(billTo.getAddressId())){
					sb.append("<option selected=\"selected\" value=\"" + loopCount+ "\">");
				}else{
					sb.append("<option value=\"" + loopCount+ "\">");
				}
				sb.append("<![CDATA[");
				if(loopCount%2==0) {
					sb.append("<div class=\"comboColor\">");
				} else {
					sb.append("<div class=\"comboAlterColor\">");
				}
				if(billTo.getAddressLine1()!=null && !billTo.getAddressLine1().equals("")){
					sb.append(replaceNullWithBlankString(billTo.getAddressLine1()));
					if(billTo.getAddressLine2()!=null && !billTo.getAddressLine2().equals("")){
						sb.append(",");
					}else{
						sb.append("<br/>");
					}
				}
				if(billTo.getAddressLine2()!=null && !billTo.getAddressLine2().equals("")){
					sb.append(replaceNullWithBlankString(billTo.getAddressLine2()));
					sb.append("<br/>");
				}
				
				if(billTo.getCity()!=null && !billTo.getCity().equals("")){
					sb.append(replaceNullWithBlankString(billTo.getCity()));
					if((billTo.getState()!=null && !billTo.getState().equals("")) || (billTo.getCountry()!=null && !billTo.getCountry().equals(""))){
						sb.append(",");
					}
				}
				
				if(billTo.getState()!=null && !billTo.getState().equals("")){
					sb.append(replaceNullWithBlankString(billTo.getState()));
					if(billTo.getCountry()!=null && !billTo.getCountry().equals("")){
						sb.append(",");
					}
				}
				
				if(billTo.getCountry()!=null && !billTo.getCountry().equals("")){
					sb.append(replaceNullWithBlankString(billTo.getCountry()));
					if(billTo.getPostalCode()!=null && !billTo.getPostalCode().equals("")){
						sb.append("<br/>");
					}
				}					
				if(billTo.getPostalCode()!=null && !billTo.getPostalCode().equals("")){
					sb.append(replaceNullWithBlankString(billTo.getPostalCode()));
				}
				
				sb.append("</div>");
				sb.append("]]>");
				sb.append("</option>");
				loopCount++;
			}
			sb.append("</complete>");
		} 
		//Adding Bill To List to Session
		request.getPortletSession().setAttribute("billToMapAsset", billToMapAsset ,PortletSession.APPLICATION_SCOPE);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(sb.toString());
		out.flush();
		out.close();		
	}
	
	/**
	 * @param value 
	 * @return object  
	 */
	private Object replaceNullWithBlankString( Object value){
		if(value == null){
			return "";
		}
		return value;
	}
	
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 */
	@ResourceMapping(value="storeBillTo")
	public void storeBillTo(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		PortletSession session = resourceRequest.getPortletSession();
		Map<String, GenericAddress> billToMapAsset = (Map<String, GenericAddress>) session.getAttribute("billToMapAsset",PortletSession.APPLICATION_SCOPE);
		GenericAddress billToAddress = billToMapAsset.get(resourceRequest.getParameter("index"));
		String billToNumber = "";
		if(billToAddress.getBillToNumber()!=null){
			billToNumber = billToAddress.getBillToNumber();
		}
		session.setAttribute("selectedAssetBillToAddress", billToAddress ,PortletSession.APPLICATION_SCOPE);
		Map<String,String> assetDetailsValues=new HashMap<String,String>();	
		
		assetDetailsValues.put("soldToNumber", billToAddress.getSoldToNumber());
		assetDetailsValues.put("billToNumber", billToAddress.getBillToNumber());
		
		session.setAttribute(ChangeMgmtConstant.ASSETCURRDETAILS, assetDetailsValues ,PortletSession.APPLICATION_SCOPE);
		session.setAttribute("selectedAssetBillToNumber", billToNumber ,PortletSession.APPLICATION_SCOPE);
	}
	
	/**
	 * ResourceMapping for the Population of the Parts Table when back button is hit from the Review page 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("populatePartTable")
	public void populatePartTable( ResourceRequest request, ResourceResponse response) throws Exception
	{
		LOGGER.debug("-----------------------[IN]populatePartTable----------------------");
				
		PortletSession portletSession = request.getPortletSession();
		String finalShowPriceFlag = request.getParameter("finalShowPriceFlag");
		String selectedPaymentType = request.getParameter("selectedPaymentType");
		
		List<Part> finalPartList = (List<Part>) portletSession.getAttribute(ChangeMgmtConstant.ASSET_PART_LIST_FILTERED);
		
		String partListTableContent = "";
		if(finalShowPriceFlag == null){
			partListTableContent = getXmlOutputGenerator(request.getLocale()).createPartListXML(finalPartList, false, selectedPaymentType);
		}else{
			if(finalShowPriceFlag.equalsIgnoreCase("true")){
				partListTableContent = getXmlOutputGenerator(request.getLocale()).createPartListXML(finalPartList, true, selectedPaymentType);
			}else{
				partListTableContent = getXmlOutputGenerator(request.getLocale()).createPartListXML(finalPartList, false, selectedPaymentType);
			}
		}		
		
		StringBuffer responseBody=new StringBuffer();
		responseBody.append("\"partListTableContent\":\""+partListTableContent+"\"");
		responseBody.append(","+"\"noOfPart\":\""+finalPartList.size()+"\"");
		responseBody.insert(0, "{");
		responseBody.insert(responseBody.length(), "}");
		PrintWriter out =  response.getWriter();
		response.setContentType("text/html");
		out.print(responseBody.toString());
		out.flush();
		out.close();
		responseBody.delete(0, responseBody.length());	
	}

	/**
	 * @return maxPartsToBeOrdered 
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


}	