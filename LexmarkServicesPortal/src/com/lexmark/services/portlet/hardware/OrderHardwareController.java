package com.lexmark.services.portlet.hardware;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

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

//import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.CatalogListContract;
import com.lexmark.contract.CreateHardwareRequestContract;
import com.lexmark.contract.HardwareCatalogContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.TaxContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.BundlePart;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
//import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.CatalogListResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.HardwareCatalogResult;
import com.lexmark.result.PaymentTypeListResult;
import com.lexmark.result.PriceResult;
import com.lexmark.result.RequestResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.result.TaxResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderHardwareService;
import com.lexmark.service.api.OrderSuppliesCatalogService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestService;
//import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.api.CreateHardwareRequest;
import com.lexmark.services.api.om.RetrievePriceService;
import com.lexmark.services.api.om.RetrieveTaxService;
import com.lexmark.services.exporter.ExcelDataExporter;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.BaseForm;
import com.lexmark.services.form.CartViewForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.HardwareDetailPageForm;
import com.lexmark.services.form.validator.FileUploadFormValidator;
import com.lexmark.services.form.validator.HardwareDetailPageFormValidator;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PaymentUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.URLImageUtil;
import com.lexmark.services.util.XMLEncodeUtil;
import com.lexmark.services.util.PerformanceUtil; //Added for performance logging
import com.lexmark.util.DateUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * 
 * This controller handles the requests for Hardware
 * @author Wipro
 * @version 2.1
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={"hardwareDetailPageForm"})
public class OrderHardwareController extends BaseController {	
	
	private static Logger LOGGER = LogManager.getLogger(OrderHardwareController.class);	
	private static LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(OrderHardwareController.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	private static final String IMAGE_NOT_FOUND = "Not found";
	
	@Autowired
    private OrderSuppliesCatalogService orderSuppliesCatalogService;
    @Autowired
    private OrderHardwareService orderHardwareService;
	@Autowired
	private CommonController commonController;
	@Autowired
	private AttachmentService attachmentService;	 
    @Autowired
    private GlobalService  globalService;   
    @Autowired
    private CreateHardwareRequest createHardwareRequest;
	@Autowired
    private FileUploadFormValidator fileUploadFormValidator;
	@Autowired
	private HardwareDetailPageFormValidator hardwareDetailPageFormValidator;	
	@Autowired
	private ServiceRequestService serviceRequestService;
	@Autowired
	private RequestTypeService requestTypeService;	
	@Autowired 
	private RetrievePriceService retrievePriceService;
	@Autowired 	
	private RetrieveTaxService retrieveTaxService;	
	
	private String maxPartsToBeOrdered;
	private String listOfFileTypes;
	private String attMaxCount;
	private String templateFileCount;
	private String templateFileLOV;
	private String templateFileName;
	
	/**
	 * Init Binder for validation and initialization
	 * @param binder 
	 * @param locale 
	 * 
	 */
	
	@InitBinder(value={"fileUploadForm","hardwareDetailPageForm"})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));		
		
		if(binder.getTarget() instanceof FileUploadForm){
			binder.setValidator(fileUploadFormValidator);
		}else if(binder.getTarget() instanceof HardwareDetailPageForm){
			binder.setValidator(hardwareDetailPageFormValidator);
		}
	}

	/**
	 * Show the hardware order landing page and show the list of the Billing Addresses
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return pageName 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showHardwareOrderList(RenderRequest request, RenderResponse response,Model model) throws Exception{
		LOGGER.debug("-------------[IN] showHardwareOrderList Controller method---------");
		PortletSession session = request.getPortletSession();
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		LOGGER.debug("requestNumber --------------->"+httpReq.getParameter("requestNumber")+" Status --------------->"+httpReq.getParameter("reqStatus"));
		if(httpReq.getParameter("requestNumber")!= null && 
				httpReq.getParameter("reqStatus")!=null &&
				httpReq.getParameter("reqStatus").equalsIgnoreCase("Draft")){
			
			return redirectHardwareOrderDetailsPage( model,  request,  response);
		}
		String relatedServiceRequestNumber = request.getParameter("relatedServiceRequestNumber");
		if(relatedServiceRequestNumber== null || "".equals(relatedServiceRequestNumber)){
			//remove the request number from session			
			session.setAttribute("hardwareDetailPageFormSession", null);
		
			session.setAttribute("hardwareOrderListToSession", null);
			session.removeAttribute("draftSrNumber");
			session.setAttribute("draftSrNumber", null);
			session.setAttribute("draftSrID", null);
			
		}
		
		
		String accountName=null;		
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(accDetails != null){
			accountName = accDetails.get("accountName");
			model.addAttribute("accountName",accountName);
			LOGGER.debug(" Account id ::::   "+accDetails.get("accountId")+" Account Name ::::   "+accDetails.get("accountName")+" Account Organization ::::   "+accDetails.get("accountOrganization")+" Agreement Id::::   "+accDetails.get("agreementId")+" Agreement Name ::::   "+accDetails.get("agreementName")+" Country ::::   "+accDetails.get("country"));			
			
		}
		
		session.removeAttribute("agreementId");
		session.setAttribute("agreementId", accDetails.get("agreementId"));
		
		/* Getting list of Bill To addresses based on Sold To number list retrieved from selected Agreement*/
		String soldTo = "";
		AddressListContract addressListContract = new AddressListContract();
		if(accDetails.get("soldToList")!=null){
			soldTo = (String) accDetails.get("soldToList");
			List<String> soldToList = Arrays.asList(soldTo.split(","));
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
			LOGGER.debug("Contract value portal is sending is soldToList "+soldToListFormated.toString());
			addressListContract.setSoldToNumbers(soldToListFormated);
			//modified for 10 digit sold to number end 
		}
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		addressListContract.setSessionHandle(crmSessionHandle);
		long timeBeforeCall=System.currentTimeMillis();
		LEXLOGGER.info("start printing lex logger");
		
		ObjectDebugUtil.printObjectContent(addressListContract, LEXLOGGER);
		AddressListResult result =  globalService.retrieveBillToAddressList(addressListContract);
		long timeAfterCall=System.currentTimeMillis();
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVEBILLTOADDRESSLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,addressListContract);
		
		LEXLOGGER.info("end printing lex loggger");
		List<GenericAddress> billToList = result.getAddressList();
		
		if(billToList!=null && billToList.size()==1){
			session.setAttribute("selectedHWBillToAddress", billToList.get(0) ,PortletSession.APPLICATION_SCOPE);
			model.addAttribute("singleBillToAddress",billToList.get(0));
		}else{		
			String billToAddressList = getBillToOption(billToList,request);
			model.addAttribute("billToAddressList", billToAddressList);
		}
		String productTypeData = "<select id=\"\"><option value=\"\">Select</option></select>";
		model.addAttribute("productTypeData", productTypeData);		
		
		String productModelData = "<select id=\"\"><option value=\"\">Select</option></select>";
		model.addAttribute("productModelData", productModelData);
		String pageFrom = request.getParameter("pageFrom");
		if("cancelAction".equalsIgnoreCase(pageFrom)){
			LOGGER.debug("This action is coming from cancel action");
		}else{
			session.removeAttribute("hardwareOrderListToSession");
			session.removeAttribute(ChangeMgmtConstant.HWCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		}
		List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
		if(hardwareOrderListToSession==null){
			hardwareOrderListToSession = new ArrayList<OrderPart>();
		}
		if(("cancelAction".equalsIgnoreCase(pageFrom))){	
			if(!hardwareOrderListToSession.isEmpty()){
				model.addAttribute("orderedCurrency", hardwareOrderListToSession.get(0).getCurrency());
			}
		}
		model.addAttribute("cartQuantity", hardwareOrderListToSession.size());
		model.addAttribute("mdmLevelForAssetDetails", PortalSessionUtil.getMdmLevel(session));
		
		LOGGER.debug("--------------------- [OUT] showHardwareOrderList method-------------------");
		return "ordermanagement/hardwareOrder/viewHardwareOrderList";
	}
	
	
	/**
	 * This method is used to retrieve the ProductModel List
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	
	@ResourceMapping("getProductModel")
	public void getProductModel(ResourceRequest request, ResourceResponse response) throws Exception{
		LOGGER.debug("---------------- [IN] getProductModel value is --------------------"+request.getParameter("productType"));
		String productType = request.getParameter("productType");
		CatalogListContract hardwareCatalogContract = new CatalogListContract();
		PortletSession session = request.getPortletSession();
		
		String agreementId = (String) session.getAttribute("agreementId");
		hardwareCatalogContract.setAgreementId(agreementId);//agreementId we can set in session and before setting we have to remove that from session scope
		hardwareCatalogContract.setProductType(productType);
		hardwareCatalogContract.setEffectiveDate(new Date());
		String soldToNumber = "";
		String paymentType = "";
		
		/*Retrieving the Hardware and Account details map to receive SoldTo Number and Payment Type*/
		Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute("hardwareCurrentDetails",PortletSession.APPLICATION_SCOPE);
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(hardwareDetails != null){
			soldToNumber = hardwareDetails.get("soldToNumber");
			paymentType = hardwareDetails.get("paymentType");
			LOGGER.debug("Sold To ::::   "+hardwareDetails.get("soldToNumber")+" Bill To Address ::::   "+hardwareDetails.get("billToAddress")+" paymentType ::::   "+hardwareDetails.get("paymentType"));
		}
		hardwareCatalogContract.setPaymentType(paymentType);
		LOGGER.debug("contractNumber "+accDetails.get("contractNumber"));
		hardwareCatalogContract.setContractNumber(accDetails.get("contractNumber"));
		hardwareCatalogContract.setHardwareFlag(true);
		ObjectDebugUtil.printObjectContent(hardwareCatalogContract, LOGGER);
		LOGGER.debug("Contract value portal is sending is agreementId "+agreementId+" productType "+productType+" paymentType "+paymentType+" soldToNumber "+soldToNumber);
		
		long timeBeforeCall=System.currentTimeMillis();
		CatalogListResult result =  orderSuppliesCatalogService.retrieveCatalogFieldList(hardwareCatalogContract);
		long timeAfterCall=System.currentTimeMillis();
		LOGGER.info("start printing lex logger");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVECATALOGFIELDLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,hardwareCatalogContract);
		
		LOGGER.info("end printing lex loggger");
		List <String> productModelList = new ArrayList<String>();
		if(result.getLovList()!=null){
			LOGGER.debug("total no of list received from siebel is "+result.getLovList().size());
			for(int i=0;i<result.getLovList().size();i++){
				LOGGER.debug("values received from siebel is "+result.getLovList().get(i).getValue());
				if(result.getLovList().get(i).getValue()!=null){
					productModelList.add(result.getLovList().get(i).getValue());
				}
			}
		}
		Collections.sort(productModelList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(getXmlContent(productModelList,"productModel", request.getLocale()));
		out.flush();
		out.close();
	}
	
	
	/**
	 * @param productTypeList 
	 * @param type 
	 * @param local 
	 * @return dropDownValue 
	 */
	private String getXmlContent(List<String> productTypeList, String type, Locale local) {
		StringBuilder sb = new StringBuilder();
		if(!(productTypeList.isEmpty())){
		if(type.equalsIgnoreCase("productType")){
			sb.append("<select id=\"productType\" onChange=\"getProductModel();\" onmousedown=\"jQuery(this).removeClass('errorColor')\">");
		}else if(type.equalsIgnoreCase("productModel")){
			sb.append("<select id=\"productModel\">");
		}
		sb.append("<option value=\"\">"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.select", local)+"</option>");
		for(int i=0;i<productTypeList.size();i++) {
			sb.append("<option value=\""+ 	XMLEncodeUtil.escapeXML(productTypeList.get(i).toString().replaceAll(" ", "%20"))  + "\">");
			sb.append(productTypeList.get(i).toString());
			sb.append("</option>");
		}
		sb.append("</select>");
		}else{
			if(type.equalsIgnoreCase("productType")){
				sb.append("<select id=\"productType\"><option value=\"\">No Product Available</option></select>");
			}else if(type.equalsIgnoreCase("productModel")){
				sb.append("<select id=\"productModel\"><option value=\"\">No Product Available</option></select>");
			}
		}
		return sb.toString();
	}	
	
	/**
	 * ResourceMapping for the retrieve Bundle list
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("retriveHardwarePartListURL")
	public void retrieveHardwareList(ResourceRequest request, ResourceResponse response) throws Exception
	{
		LOGGER.debug("-----------------------[IN]retrieveHardwareList----------------------");
		HardwareCatalogContract contract = new HardwareCatalogContract();
		PortletSession session = request.getPortletSession();
		String soldToNumber = "";
		String paymentType = "";
		String agreementId = (String) session.getAttribute("agreementId");
		contract.setAgreementId(agreementId);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		
		/*Retrieving the Hardware and Account details map to receive SoldTo Number and Payment Type*/
		Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute("hardwareCurrentDetails",PortletSession.APPLICATION_SCOPE);
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(hardwareDetails != null){
			soldToNumber = hardwareDetails.get("soldToNumber");
			paymentType = hardwareDetails.get("paymentType");
		}
		try {
			contract.setSessionHandle(crmSessionHandle);
			contract.setPartNumber(request.getParameter("partNumber"));
			contract.setProductModel(request.getParameter("productModel"));
			contract.setProductType(request.getParameter("productType"));
			contract.setContractNumber(accDetails.get("contractNumber"));
			contract.setPaymentType(paymentType);
			contract.setEffectiveDate(new Date());
			LOGGER.debug("----- Part number "+request.getParameter("partNumber")+" productModel "+request.getParameter("productModel")+" producttype "+
					request.getParameter("productType"));
			
			LEXLOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LEXLOGGER);
			LEXLOGGER.info("end printing lex logger");
			long timeBeforeCall=System.currentTimeMillis();
			HardwareCatalogResult hardwareCatalogResult = orderHardwareService.retrieveHardwareList(contract);
			
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVEHARDWARELIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			LOGGER.info("end printing lex loggger");			
			
			/*Retrieving the Hardware Related flag information from session as retrieved based on the Agreement selection to determine price call*/
			Map<String,Boolean> hardwareFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.HWFINALFLAGS,PortletSession.APPLICATION_SCOPE);
			boolean finalShowPriceFlag = hardwareFinalFlags.get("finalShowPriceFlag");
			
			PriceResult bundlePriceResult = null;
			if(finalShowPriceFlag == true){
				
				List<Bundle> bundleList = hardwareCatalogResult.getHardwareCatalog().getBundleList();
				
				if(bundleList!=null && !(bundleList.isEmpty()) && bundleList.get(0)!=null){
					for(Bundle bundle:bundleList){
						LOGGER.debug("Bundle Contract No "+bundle.getContractNumber());
					}
					
					/*As contract number is same for all the parts retrieve Contract number from the 1st part in the list to pass in the price call to SAP*/
					String contractNo = bundleList.get(0).getContractNumber()!= null ?bundleList.get(0).getContractNumber():"";
					PriceContract priceContract = ContractFactory.getHardwareBundlePriceContract(bundleList,session,contractNo);
					try{
						long timeBeforeCalling=System.currentTimeMillis();
						/*Price call to SAP start*/
						bundlePriceResult = retrievePriceService.retrievePriceList(priceContract);
						long timeAfterCalling=System.currentTimeMillis();
						LOGGER.info("start printing lex logger");
						
						PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVEPRICELIST, timeBeforeCalling,timeAfterCalling, PerformanceConstant.SYSTEM_SAP,priceContract);
						
						LOGGER.info("end printing lex loggger");
					}catch(Exception e){
						LOGGER.error("Eception occured");
						e.getMessage();
					}
				}				
			}
			
			String productModel = "";
			String productDesc = "";
			String deviceType = "";
			String color_mono = "";
			String partImage = "";
			if(hardwareCatalogResult.getHardwareCatalog().getProductModel()!=null){
				productModel = hardwareCatalogResult.getHardwareCatalog().getProductModel();
				if (productModel.length()>3){
					partImage = URLImageUtil.getPartImage(productModel);
				}
				if(!partImage.equals(IMAGE_NOT_FOUND)&& StringUtils.isNotBlank(partImage)){
					partImage = "<img src='"+partImage+"' alt=\'Change\' width=\'100\' height=\'100\'/>";
				}else{
					partImage = "<img src=\'/LexmarkServicesPortal/images/part_na_color.png\' width=\'100\' height=\'100\'/>";
				}
			}
			if(hardwareCatalogResult.getHardwareCatalog().getDescription()!=null){
				productDesc = hardwareCatalogResult.getHardwareCatalog().getDescription();
			}
			if(hardwareCatalogResult.getHardwareCatalog().getDeviceType()!=null){
				deviceType = hardwareCatalogResult.getHardwareCatalog().getDeviceType();
			}
			if(hardwareCatalogResult.getHardwareCatalog().getColor_mono()!=null){
				color_mono = hardwareCatalogResult.getHardwareCatalog().getColor_mono();
			}
			int recordCount = 0;
			String bundleXML = getXmlOutputGenerator(request.getLocale()).bundleViewXml(hardwareCatalogResult, session, bundlePriceResult, paymentType);
			if(hardwareCatalogResult.getHardwareCatalog() == null){
				recordCount = 0;
			}else{
				recordCount = hardwareCatalogResult.getHardwareCatalog().getBundleList().size();
			}
			
			
			
			bundleXML = bundleXML.replaceAll("\\r|\\n", "");
			StringBuffer responseBody=new StringBuffer();
			responseBody.append("\"productModel\":\""+productModel+"\"");
			responseBody.append(","+"\"productDesc\":\""+productDesc+"\"");
			responseBody.append(","+"\"deviceType\":\""+deviceType+"\"");
			responseBody.append(","+"\"color_mono\":\""+color_mono+"\"");
			responseBody.append(","+"\"partImage\":\""+partImage+"\"");
			responseBody.append(","+"\"recordCount\":\""+recordCount+"\"");
			responseBody.append(","+"\"bundleXML\":\""+bundleXML+"\"");
			responseBody.insert(0, "{");
			responseBody.insert(responseBody.length(), "}");
			LOGGER.debug("response body finally is " + responseBody.toString());
			PrintWriter out =  response.getWriter();
			response.setContentType("text/html");
			out.print(responseBody.toString());
			out.flush();
			out.close();
			responseBody.delete(0, responseBody.length());
		} catch (Exception e) {
			LOGGER.error("exception occured");
			e.getMessage();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
	}	
	
	/**
	 * Retrieve Accessories list from Siebel
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("retriveAccessoriesPartListURL")
	public void retrieveAccessoriesList(ResourceRequest request, ResourceResponse response) throws Exception
	{
		LOGGER.debug("-----------------------[IN]retrieveAccessoriesList----------------------");
		CatalogListContract contract = ContractFactory.getCatalogPartRequestListContract(request);	
		String soldToNumber = "";
		String paymentType = "";
		PortletSession session = request.getPortletSession();
		String agreementId = (String) session.getAttribute("agreementId");
		contract.setAgreementId(agreementId);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute("hardwareCurrentDetails",PortletSession.APPLICATION_SCOPE);
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(hardwareDetails != null){
			soldToNumber = hardwareDetails.get("soldToNumber");
			paymentType = hardwareDetails.get("paymentType");
			LOGGER.debug("Sold To ::::   "+hardwareDetails.get("soldToNumber"));
			LOGGER.debug("paymentType ::::   "+hardwareDetails.get("paymentType"));
		}		
		try {
			contract.setSessionHandle(crmSessionHandle);
			contract.setPaymentType(paymentType);
			contract.setContractNumber(accDetails.get("contractNumber"));
			contract.setHardwareFlag(true);
			contract.setHardwareAccessoriesFlag(true);
			LOGGER.debug("----- Part number "+request.getParameter("partNumber")+" productModel "+request.getParameter("productModel")+" producttype "+
					request.getParameter("productType"));
			
			LEXLOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LEXLOGGER);
			LEXLOGGER.info("end printing lex logger");
			long timeBeforeCall=System.currentTimeMillis();
			CatalogListResult catalogListResult = orderSuppliesCatalogService.retrieveCatalogListWithContractNumber(contract);
			LOGGER.debug("accessories List ----------------------->>> " + catalogListResult.getAccessoriesList().size());
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVEHARDWARELIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			LOGGER.info("end printing lex loggger");			
			
			Map<String,Boolean> hardwareFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.HWFINALFLAGS,PortletSession.APPLICATION_SCOPE);
			boolean finalShowPriceFlag = hardwareFinalFlags.get("finalShowPriceFlag");
			PriceResult accessoriesPriceResult = null;
			int recordCount = 0;
			List<OrderPart> accessoriesList = catalogListResult.getAccessoriesList();
			if(finalShowPriceFlag == true){							
				if(accessoriesList!=null && !(accessoriesList.isEmpty()) && accessoriesList.get(0)!=null){
					recordCount = accessoriesList.size();
					String contractNo = accessoriesList.get(0).getContractNo()!= null ?accessoriesList.get(0).getContractNo():"";
					PriceContract priceContract = ContractFactory.getCatalogPriceContract(accessoriesList,session,contractNo);
					try{
						long timeBeforeCalling=System.currentTimeMillis();
						accessoriesPriceResult = retrievePriceService.retrievePriceList(priceContract);
						long timeAfterCalling=System.currentTimeMillis();
						LOGGER.info("start printing lex logger");
						
						PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVEPRICELIST, timeBeforeCalling,timeAfterCalling, PerformanceConstant.SYSTEM_SAP,priceContract);
						
						LOGGER.info("end printing lex loggger");
					}catch(Exception e){
						LOGGER.error("Exception occured");
						e.getMessage();
					}
				}
			}		
			
					
				
			String accessoriesXML = getXmlOutputGenerator(request.getLocale()).accessoriesViewXml(accessoriesList, session, accessoriesPriceResult, paymentType,catalogListResult.getTotalCount(),contract.getStartRecordNumber());
			accessoriesXML = accessoriesXML.replaceAll("\\r|\\n", "");
			
			PrintWriter out =  response.getWriter();
			response.setContentType("text/xml");
			out.print(accessoriesXML);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
	}
		
	/**
	 * This method is used when user tried add some quantity to the cart
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("addToCartURL")
	public void addToCart(@RequestParam("partDesc") String partDescription, 
			ResourceRequest request, ResourceResponse response, Model model) throws Exception
	{
		LOGGER.debug("-------------[IN]addToCart -------------");
		PortletSession session = request.getPortletSession();
		
		String partNumber = request.getParameter("partNumber");
		String partDesc = request.getParameter("partDesc");
		String localDesc = request.getParameter("localDesc");
		String partType = request.getParameter("partType");
		String partQty = request.getParameter("partQty");
		String catalogId = request.getParameter("catalogId");
		String supplyId = request.getParameter("supplyId");
		String proModel = request.getParameter("proModel");
		String hardwareType = request.getParameter("hardwareType");
		String lineId = request.getParameter("lineId");
		String currency = request.getParameter("currency");
		String salesOrg = request.getParameter("salesOrg");
		String partImg = request.getParameter("imagePath");
		String price = "";
		
		String descArr = request.getParameter("descArr");
		String qtyArr = request.getParameter("qtyArr");
		partNumber = URLDecoder.decode(partNumber, "UTF-8");
		partDesc = URLDecoder.decode(partDesc, "UTF-8");
		partType = URLDecoder.decode(partType, "UTF-8");
		proModel = URLDecoder.decode(proModel, "UTF-8");
		salesOrg = URLDecoder.decode(salesOrg, "UTF-8");
		
		descArr = URLDecoder.decode(descArr, "UTF-8");
		qtyArr = URLDecoder.decode(qtyArr, "UTF-8");
		
		List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
		if(hardwareOrderListToSession==null){
			hardwareOrderListToSession = new ArrayList<OrderPart>();
		}
		//Here please check if the catalogid is there the session list. If it is there in the list update that otherwise add that.
		if (hardwareOrderListToSession!=null){
			for(int i=0;i<hardwareOrderListToSession.size();i++){
				if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(i).getCatalogId())){
					//Update is required
					hardwareOrderListToSession.remove(i);
					break;
				}
			}
		}
		
		Map<String,String> hardwarePriceMap= new HashMap<String,String>();
		if(session.getAttribute(ChangeMgmtConstant.HWPRICEMAP,PortletSession.APPLICATION_SCOPE)!=null){
			hardwarePriceMap = (Map<String,String>)session.getAttribute(ChangeMgmtConstant.HWPRICEMAP,PortletSession.APPLICATION_SCOPE);
			if(hardwarePriceMap.get(catalogId)!=null){
				price = hardwarePriceMap.get(catalogId);
			}
		}		
		
		OrderPart orderPart = new OrderPart();
				
		if(session.getAttribute("bundlePartListMap",PortletSession.APPLICATION_SCOPE)!=null){
			Map<String, List<Part>> bundlePartMap = (Map<String, List<Part>>) session.getAttribute("bundlePartListMap",PortletSession.APPLICATION_SCOPE);
			if(bundlePartMap.get(catalogId)!=null){
				List<BundlePart> bundlePartList = new ArrayList<BundlePart>();
				List<Part> selectedBundlePartList = bundlePartMap.get(catalogId);
				for(Part selectBundlePart:selectedBundlePartList){
					BundlePart bundlePart = new BundlePart();
					bundlePart.setPartNumber(selectBundlePart.getPartNumber());
					bundlePart.setDescription(selectBundlePart.getDescription());
					bundlePart.setQty(selectBundlePart.getOrderQuantity());
					bundlePart.setSupplyId(selectBundlePart.getSupplyId());
					bundlePart.setCatalogId(selectBundlePart.getCatalogId());
					bundlePart.setParentLineId(lineId);
					bundlePart.setLineId(selectBundlePart.getParentLineItemNumber());
					bundlePartList.add(bundlePart);
				}
				orderPart.setBundlePartList(bundlePartList);
			}
		}
		
		orderPart.setPartNumber(partNumber);
		orderPart.setPartDesc(partDesc);
		orderPart.setLocalDesc(localDesc);
		orderPart.setPartType(partType);		
		orderPart.setPartQuantity(partQty);		
		orderPart.setCatalogId(catalogId);		
		orderPart.setSupplyId(supplyId);		
		orderPart.setModel(proModel);		
		orderPart.setHardwareType(hardwareType);
		orderPart.setContractLineItemId(lineId);
		hardwareOrderListToSession.add(orderPart);		
		
		if(price!=null && StringUtils.isNotBlank(price))
		{
			orderPart.setUnitPrice(price);
		}else{
			orderPart.setUnitPrice(null);
		}
		orderPart.setSalesOrg(salesOrg);
		orderPart.setCurrency(currency);
		orderPart.setPartImg(partImg);
		model.addAttribute("hardwareOrderSize", hardwareOrderListToSession.size());			
		session.setAttribute("hardwareOrderListToSession", hardwareOrderListToSession);		
		response.setProperty("hardwareOrderSize", String.valueOf(hardwareOrderListToSession.size()));
		LOGGER.debug(" Cart Size is "+String.valueOf(hardwareOrderListToSession.size()));
		
		printPartFromSession(session);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(String.valueOf(hardwareOrderListToSession.size()));
		out.flush();
		out.close();
		LOGGER.debug("-------------[OUT]addToCart ----------------");
	}
	
	/**
	 * @param session 
	 */
	private void printPartFromSession(PortletSession session){
		LOGGER.debug("------------------- [IN] Printing the part details from the session --------------------------------");
		List<OrderPart> hardwareOrderListToSession = new ArrayList<OrderPart>();
		hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
		if (hardwareOrderListToSession!=null){
			for(int i=0;i<hardwareOrderListToSession.size();i++){
				LOGGER.debug("Part number is "+hardwareOrderListToSession.get(i).getPartNumber()+" Model is "+hardwareOrderListToSession.get(i).getModel()+" Part desc is "+hardwareOrderListToSession.get(i).getPartDesc()+" Part quantity is "+hardwareOrderListToSession.get(i).getPartQuantity());		
			}
		}
		LOGGER.debug("------------------- [OUT] Printing the part details from the session --------------------------------");
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=passingSearchList")
	public void showServiceRequestPrinterSelectionPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		LOGGER.debug("------ACTION: gotoSRSelectCatalog Started----------");
		response.setRenderParameter("action", "displayCatalogSelection");
		LOGGER.debug("------ACTION: gotoSRSelectCatalog End----------");
	}
	
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
	 * This method is used to show the Hardware detail page	
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return pageName 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showHardwareDetailPage")
	public String showHardwareDetailPage (RenderRequest request, RenderResponse response, Model model) throws Exception{
		LOGGER.debug("---------------------- [IN] showHardwareDetailPage --------------------------------");
		PortletSession session = request.getPortletSession();
		ServiceRequest serviceRequest=new ServiceRequest();
		AccountContact accContact=commonController.getContactInformation(request, response);
		serviceRequest.setRequestor(accContact);
		HardwareDetailPageForm hardwareDetailPageForm=(HardwareDetailPageForm)session.getAttribute("hardwareDetailPageFormSession");
		if(hardwareDetailPageForm==null){
		hardwareDetailPageForm = new HardwareDetailPageForm();
		}
	
		List<OrderPart> hardwareOrderListToSession = new ArrayList<OrderPart>();
		hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
		//Part details added to the cart
		if (hardwareOrderListToSession!=null){
			List<OrderPart> bundleList = new ArrayList<OrderPart>();
			List<OrderPart> accessoriesList = new ArrayList<OrderPart>();
				for(OrderPart part : hardwareOrderListToSession){
					OrderPart orderPart = new OrderPart();
					orderPart.setPartNumber(part.getPartNumber());
					orderPart.setPartDesc(part.getPartDesc());
					orderPart.setLocalDesc(part.getLocalDesc());
					orderPart.setPartType(part.getPartType());
					orderPart.setModel(part.getModel());
					orderPart.setOrderQuantity(part.getPartQuantity());
					orderPart.setHardwareType(part.getHardwareType());
					orderPart.setContractLineItemId(part.getContractLineItemId());
					orderPart.setCatalogId(part.getCatalogId());
					orderPart.setProductId(part.getProductId());
					orderPart.setPartImg(part.getPartImg());
					if(part.getUnitPrice()!=null && part.getUnitPrice()!=""){
						orderPart.setUnitPrice(part.getUnitPrice());
					}
					if(part.getCurrency()!=null && part.getCurrency()!=""){
						orderPart.setCurrency(part.getCurrency());
					}
					if(part.getBundlePartList()!=null){
						orderPart.setBundlePartList(part.getBundlePartList());
					}
					
					LOGGER.debug("@@@@@@@@@@@@@@ Part details number "+part.getPartNumber()+" part desc "
							+part.getPartDesc()+" part type "+part.getPartType()+" part.getModel() "+part.getModel()+" quantity "+
							part.getPartQuantity()+" catalogid "+part.getCatalogId()+
							" product id "+part.getProductId());
					if(part.getHardwareType()!=null){
						if(part.getHardwareType().equalsIgnoreCase("bundle")){
							bundleList.add(orderPart);
						}else if(part.getHardwareType().equalsIgnoreCase("accessories")){
							accessoriesList.add(orderPart);
						}
					}
					
				}
				
				if(hardwareDetailPageForm.getBundleList()!=null){
					bundleList.addAll(hardwareDetailPageForm.getBundleList());
				}
				
				if(hardwareDetailPageForm.getAccessoriesList()!=null){
					accessoriesList.addAll(hardwareDetailPageForm.getAccessoriesList());	
				}
				
				hardwareDetailPageForm.setBundleList(bundleList);
				hardwareDetailPageForm.setAccessoriesList(accessoriesList);
		}
		hardwareDetailPageForm.setServiceRequest(serviceRequest);
		hardwareDetailPageForm.setMaxPartsToBeOrdered(getMaxPartsToBeOrdered());
		String mdmLevel = LexmarkConstants.MDM_LEVEL_SIEBEL;
		String mdmId=null;
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);	
			
		if(accDetails != null){
			LOGGER.debug("Account id ::::   "+accDetails.get("accountId"));			
		}
		mdmId = accDetails.get("accountId");		
		
		PortletSession portletSession = request.getPortletSession();
		SiebelAccountListContract siebelAccountListContract = new SiebelAccountListContract();
		siebelAccountListContract.setMdmId(mdmId);
		siebelAccountListContract.setMdmLevel(mdmLevel);
		
		siebelAccountListContract.setNewQueryIndicator(false);
		LOGGER.debug("********************** Contract data we are sending Start *************************");
		LOGGER.debug("mdmid "+siebelAccountListContract.getMdmId()+" mdmlevel "+siebelAccountListContract.getMdmLevel()+
				" vendor flag "+siebelAccountListContract.isVendorFlag()+" new query indicator "+siebelAccountListContract.isNewQueryIndicator());
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		siebelAccountListContract.setSessionHandle(crmSessionHandle);
		LOGGER.debug("-------------retrieveSiebelAccountList started---------");
				
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(siebelAccountListContract, LEXLOGGER);
		LEXLOGGER.info("end printing lex logger");
		long timeBeforeCall=System.currentTimeMillis();
		SiebelAccountListResult siebelAccountListResult = serviceRequestService
														  .retrieveSiebelAccountList(siebelAccountListContract);
		
		long timeAfterCall=System.currentTimeMillis();
		LEXLOGGER.info("start printing lex logger");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVESIEBELACCOUNTLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,siebelAccountListContract);
		
		LEXLOGGER.info("end printing lex loggger");
		LOGGER.debug("-------------retrieveSiebelAccountList ended---------");
		if(siebelAccountListResult.getAccountList()!=null){
			LOGGER.debug("No of accounts portal is receiving is "+siebelAccountListResult.getAccountList().size());
			if(!(siebelAccountListResult.getAccountList().isEmpty())){
				String defaultSpecialInstruction = siebelAccountListResult.getAccountList().get(0).getSpecialHandlingInstruction();
				hardwareDetailPageForm.setAddressFlag(siebelAccountListResult.getAccountList().get(0).isAddressFlag());
				hardwareDetailPageForm.setDefaultSpecialInstruction(defaultSpecialInstruction);
			}
		}
		hardwareDetailPageForm.setListOfFileTypes(listOfFileTypes);
		hardwareDetailPageForm.setAttMaxCount(attMaxCount);
		//template flow
		hardwareDetailPageForm.setTemplateFileLOV(templateFileLOV);
		hardwareDetailPageForm.setTemplateFileCount(templateFileCount);
		hardwareDetailPageForm.setTemplateFileName(templateFileName);
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID("displayAttachment");
		model.addAttribute("displayAttachment", resURL1.toString());
		//Setting the related service request number for draft SR
		String draftSrNumber = (String)session.getAttribute("draftSrNumber");
		LOGGER.debug("draftSrNumber is coming as "+draftSrNumber);
		String draftSrID=(String)session.getAttribute("draftSrID");
		if(!("".equals(draftSrNumber)||draftSrNumber==null)){
			LOGGER.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! THIS IS DRAFT SR SO SETTING THE DRAFT SR NUMBER TO THE FORM");
			hardwareDetailPageForm.setRelatedServiceRequestedNumber(draftSrNumber);
			if(draftSrID !=null){
				hardwareDetailPageForm.setRelatedServiceRequestedRowId(draftSrID);
			}else{
				hardwareDetailPageForm.setRelatedServiceRequestedRowId("");
			}
			
		}else{
			LOGGER.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! THIS IS NOT A DRAFT SR SO REMOVING THE ATTACHMENT LIST FROM SR");
			session.removeAttribute("attachmentList");
		}
		//If it is draft SR, need to add attachment to the page
		AttachmentForm attachForm = new AttachmentForm();
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
		attachForm.setAttachmentList(modifiedAttachmentList);
		request.setAttribute("attachmentForm",attachForm);
		model.addAttribute("attachmentForm",attachForm);
		hardwareDetailPageForm.setAttachmentList(modifiedAttachmentList);
		model.addAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
		model.addAttribute("attachmentFormDisplay", hardwareDetailPageForm);
		//Initializes the form for file upload
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		LOGGER.debug("---------------------- [OUT] showHardwareDetailPage --------------------------------");
		return "ordermanagement/hardwareOrder/hardwareDetail";
	}
	
	/**
	 * @param resourceRequest  
	 * @param resourceResponse 
	 * */
	@ResourceMapping("downloadXLSFile")
	public void downloadTemplate(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		LOGGER.debug("------------Enter downloadXLSFile-----------");
		
		String exportColumnPatterns[]=null;
		
		String fileName=null;
		
		List<?> dataList=null;
		
		ExcelDataExporter exporter = new ExcelDataExporter();
		exporter.setLocale(resourceRequest.getLocale());
		fileName="hardwareInstall";
	
		exportColumnPatterns=LexmarkSPConstants.PATTERNS_HARDWARE_INSTALL_EXPORT;
		
		dataList= getOrderHardwareTemplate(resourceRequest,resourceResponse);
		exporter.exportHWInstallExcel(resourceResponse, fileName, exportColumnPatterns, dataList);	
	}
	/**
	 * 
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @return List 
	 */
	
	private List<?> getOrderHardwareTemplate(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) {
		LOGGER.debug("------------Enter getOrderHardwareTemplate-----------");
		// TODO Auto-generated method stub
		PortletSession session = resourceRequest.getPortletSession();
		List<OrderPart> hardwareOrderListToSession = new ArrayList<OrderPart>();
		if(session.getAttribute("hardwareOrderListToSession")!=null){
			hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
		}
		return hardwareOrderListToSession;
	}

	/**
	 * This method is used to show the review order page and also creating the draft order 
	 * @param request 
	 * @param response 
	 * @param hardwareDetailPageForm 
	 * @param bindingResult 
	 * @param model 
	 * @param attachForm 
	 * @return String
	 * @throws Exception 
	 */
	@RenderMapping(params="action=submitHardwareOrder")
	public String submitHardwareOrder(
			RenderRequest request, 
			RenderResponse response, 
			@ModelAttribute("hardwareDetailPageForm") @Valid HardwareDetailPageForm hardwareDetailPageForm, 
			BindingResult bindingResult,@ModelAttribute ("attachmentForm") AttachmentForm attachForm,
			Model model) throws Exception{
		
		LOGGER.debug("---------------------- [In] submitAssetOrder for confirm order page ----------------------");
		String pageSubmitType = hardwareDetailPageForm.getPageSubmitType();
		String attachmentDescription= request.getParameter("attachmentDescriptionID");
		String returnPage = "";
		String creditCurrency="USD";
		String exError = null;
		PortletSession session = request.getPortletSession();
		commonController.getContactInformation(request, response);
		
		if("confirmOrderRequest".equalsIgnoreCase(pageSubmitType)){
				if(bindingResult.hasErrors()){
					LOGGER.debug("%%%%%%%%%%%%%% Error is there in the form ");
					for(int j=0;j<bindingResult.getAllErrors().size();j++){
						LOGGER.debug("Errors are in controller "+bindingResult.getAllErrors().get(j).getCode().toString());
					}
					//Display part list after validation
					String paramPrefix = null;
					String orderQuantity = null;
					String catalogId = "";
					List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
					List<OrderPart> modifiedBundleList = new ArrayList<OrderPart>();
					List<OrderPart> modifiedAccessoriesList = new ArrayList<OrderPart>();
					
					if(hardwareOrderListToSession!=null){
						LOGGER.debug("The size of the hardwareOrderListToSession is "+hardwareOrderListToSession.size());
					}
					
					if(hardwareDetailPageForm.getBundleList()!=null){
						for (int i=0;i<hardwareDetailPageForm.getBundleList().size();i++){
							LOGGER.debug("Order quantity for "+i+" number part is "+hardwareDetailPageForm.getBundleList().get(i).getOrderQuantity());
							paramPrefix = "bundleList[" + i + "].";
							orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
							catalogId = request.getParameter("bundleId["+i+"]");
							LOGGER.debug("!!!!!!!!!!!! for bundleID "+catalogId+" order quantity is "+orderQuantity);
							if(catalogId!=null){
								for(int j=0;j<hardwareOrderListToSession.size();j++){
									if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(j).getCatalogId())){
										if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.valueOf(0))) {
											if("".equals(orderQuantity)){LOGGER.debug("Order quanityt is blank");}
											if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
										}else{
											OrderPart orderPart = new OrderPart();
											orderPart.setPartNumber(hardwareOrderListToSession.get(j).getPartNumber());
											orderPart.setPartDesc(hardwareOrderListToSession.get(j).getPartDesc());
											orderPart.setLocalDesc(hardwareOrderListToSession.get(j).getLocalDesc());
											orderPart.setPartImg(hardwareOrderListToSession.get(j).getPartImg());
											orderPart.setOrderQuantity(orderQuantity);
											orderPart.setCatalogId(hardwareOrderListToSession.get(j).getCatalogId());
											orderPart.setContractLineItemId(hardwareOrderListToSession.get(j).getContractLineItemId());
											orderPart.setSupplyId(hardwareOrderListToSession.get(j).getSupplyId());
											orderPart.setSalesOrg(hardwareOrderListToSession.get(j).getSalesOrg());
											orderPart.setBundlePartList(hardwareOrderListToSession.get(j).getBundlePartList());
											if(hardwareOrderListToSession.get(j).getUnitPrice()!=null){
												orderPart.setUnitPrice(hardwareOrderListToSession.get(j).getUnitPrice());
											}
											if(hardwareOrderListToSession.get(j).getCurrency()!=null){
												orderPart.setCurrency(hardwareOrderListToSession.get(j).getCurrency());
												creditCurrency = hardwareOrderListToSession.get(j).getCurrency();
											}
											modifiedBundleList.add(orderPart);											
										}
										LOGGER.debug("After setting order quantity for "+i+" number part is "+hardwareOrderListToSession.get(j).getOrderQuantity());
										break;
									}
								}
							}
						}
					}
					
					if(hardwareDetailPageForm.getAccessoriesList()!=null){
						for (int i=0;i<hardwareDetailPageForm.getAccessoriesList().size();i++){
							LOGGER.debug("Order quantity for "+i+" number part is "+hardwareDetailPageForm.getAccessoriesList().get(i).getOrderQuantity());
							paramPrefix = "accessoriesList[" + i + "].";
							orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
							catalogId = request.getParameter("accessoriesCatalogId["+i+"]");
							LOGGER.debug("!!!!!!!!!!!! for catalogID "+catalogId+" order quantity is "+orderQuantity);
							if(catalogId!=null){
								for(int j=0;j<hardwareOrderListToSession.size();j++){
									if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(j).getCatalogId())){
										if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.valueOf(0))) {
											if("".equals(orderQuantity)){LOGGER.debug("Order quantity is blank");}
											if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
										}else{
											OrderPart orderPart = new OrderPart();
											orderPart.setPartNumber(hardwareOrderListToSession.get(j).getPartNumber());
											orderPart.setPartDesc(hardwareOrderListToSession.get(j).getPartDesc());
											orderPart.setLocalDesc(hardwareOrderListToSession.get(j).getLocalDesc());
											orderPart.setPartType(hardwareOrderListToSession.get(j).getPartType());
											orderPart.setModel(hardwareOrderListToSession.get(j).getModel());
											orderPart.setPartImg(hardwareOrderListToSession.get(j).getPartImg());
											orderPart.setOrderQuantity(orderQuantity);
											orderPart.setCatalogId(hardwareOrderListToSession.get(j).getCatalogId());
											orderPart.setContractLineItemId(hardwareOrderListToSession.get(j).getContractLineItemId());
											orderPart.setSupplyId(hardwareOrderListToSession.get(j).getSupplyId());
											orderPart.setSalesOrg(hardwareOrderListToSession.get(j).getSalesOrg());
											if(hardwareOrderListToSession.get(j).getUnitPrice()!=null){
												orderPart.setUnitPrice(hardwareOrderListToSession.get(j).getUnitPrice());
											}
											if(hardwareOrderListToSession.get(j).getCurrency()!=null){
												orderPart.setCurrency(hardwareOrderListToSession.get(j).getCurrency());
												creditCurrency = hardwareOrderListToSession.get(j).getCurrency();
											}
											modifiedAccessoriesList.add(orderPart);
											LOGGER.debug("part added for catalog id "+orderPart.getCatalogId()+" and quantity "+orderPart.getOrderQuantity());
										}
										LOGGER.debug("After setting order quantity for "+i+" number part is "+hardwareOrderListToSession.get(j).getOrderQuantity());
										break;
									}
								}
							}
						}
					}
					
										
					if(modifiedBundleList!=null && !(modifiedBundleList.isEmpty())){
						hardwareDetailPageForm.setBundleList(modifiedBundleList);
					}
					if(modifiedAccessoriesList!=null && !(modifiedAccessoriesList.isEmpty())){
						hardwareDetailPageForm.setAccessoriesList(modifiedAccessoriesList);
					}					
					//End display part list after validation
					List <Attachment> attachmentList = new ArrayList<Attachment>();
					attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
					hardwareDetailPageForm.setAttachmentList(attachmentList);
					attachForm.setAttachmentList(attachmentList);
					attachForm.setListOfFileTypes(listOfFileTypes);
					attachForm.setAttMaxCount(attMaxCount);
					hardwareDetailPageForm.setAttachmentDescription(attachmentDescription);
					attachForm.setAttachmentDescription(attachmentDescription);
					model.addAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
					FileUploadForm fileUploadForm = new FileUploadForm();
					model.addAttribute("fileUploadForm", fileUploadForm);
					model.addAttribute("creditCurrency",creditCurrency);
					
					/* Return to Hardware Details page as there is exception/validation issues*/
					returnPage = "ordermanagement/hardwareOrder/hardwareDetail";
				} else {
				try {
				String paramPrefix = null;
				String orderQuantity = null;
				String catalogId = "";
				List<OrderPart> modifiedBundleList = new ArrayList<OrderPart>();
				List<OrderPart> modifiedAccessoriesList = new ArrayList<OrderPart>();
				List<OrderPart> modifiedHardwareList = new ArrayList<OrderPart>();
				List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
				
				if(hardwareOrderListToSession!=null){
					LOGGER.debug("The size of the catalogOrderListToSession is "+hardwareOrderListToSession.size());
				}
				if(hardwareDetailPageForm.getBundleList()!=null){
					for (int i=0;i<hardwareDetailPageForm.getBundleList().size();i++){
						LOGGER.debug("Order quantity for "+i+" number part is "+hardwareDetailPageForm.getBundleList().get(i).getOrderQuantity());
						paramPrefix = "bundleList[" + i + "].";
						orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
						catalogId = request.getParameter("bundleId["+i+"]");
						LOGGER.debug("!!!!!!!!!!!! for bundleID "+catalogId+" order quantity is "+orderQuantity);
						if(catalogId!=null){
							for(int j=0;j<hardwareOrderListToSession.size();j++){
								if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(j).getCatalogId())){
									if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.valueOf(0))) {
										if("".equals(orderQuantity)){LOGGER.debug("Order quanityt is blank");}
										if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
									}else{
										OrderPart orderPart = new OrderPart();
										orderPart.setPartNumber(hardwareOrderListToSession.get(j).getPartNumber());
										orderPart.setPartDesc(hardwareOrderListToSession.get(j).getPartDesc());
										orderPart.setLocalDesc(hardwareOrderListToSession.get(j).getLocalDesc());
										orderPart.setOrderQuantity(orderQuantity);
										orderPart.setCatalogId(hardwareOrderListToSession.get(j).getCatalogId());
										orderPart.setSupplyId(hardwareOrderListToSession.get(j).getSupplyId());
										orderPart.setContractLineItemId(hardwareOrderListToSession.get(j).getContractLineItemId());
										orderPart.setSalesOrg(hardwareOrderListToSession.get(j).getSalesOrg());
										orderPart.setPartImg(hardwareOrderListToSession.get(j).getPartImg());
										orderPart.setBundlePartList(hardwareOrderListToSession.get(j).getBundlePartList());
										if(hardwareOrderListToSession.get(j).getUnitPrice()!=null){
											orderPart.setUnitPrice(hardwareOrderListToSession.get(j).getUnitPrice());
											if(hardwareOrderListToSession.get(j).getUnitPrice()!=null && hardwareOrderListToSession.get(j).getUnitPrice()!=""){
												orderPart.setTotal(PaymentUtil.calculateTotalPrice(hardwareOrderListToSession.get(j).getUnitPrice(),orderQuantity));
											}
										}
										if(hardwareOrderListToSession.get(j).getCurrency()!=null){
											orderPart.setCurrency(hardwareOrderListToSession.get(j).getCurrency());
											creditCurrency = hardwareOrderListToSession.get(j).getCurrency();
										}
										modifiedBundleList.add(orderPart);
										modifiedHardwareList.add(orderPart);
										LOGGER.debug("part added for catalog id "+orderPart.getCatalogId()+" and quantity "+orderPart.getOrderQuantity());
									}
									LOGGER.debug("After setting order quantity for "+i+" number part is "+hardwareOrderListToSession.get(j).getOrderQuantity());
									break;
								}
							}
						}
					}
				}
				
				if(hardwareDetailPageForm.getAccessoriesList()!=null){
					for (int i=0;i<hardwareDetailPageForm.getAccessoriesList().size();i++){
						LOGGER.debug("Order quantity for "+i+" number part is "+hardwareDetailPageForm.getAccessoriesList().get(i).getOrderQuantity());
						paramPrefix = "accessoriesList[" + i + "].";
						orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
						catalogId = request.getParameter("accessoriesCatalogId["+i+"]");
						LOGGER.debug("!!!!!!!!!!!! for catalogID "+catalogId+" order quantity is "+orderQuantity);
						if(catalogId!=null){
							for(int j=0;j<hardwareOrderListToSession.size();j++){
								if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(j).getCatalogId())){
									if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.valueOf(0))) {
										if("".equals(orderQuantity)){LOGGER.debug("Order quantity is blank");}
										if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
									}else{
										OrderPart orderPart = new OrderPart();
										orderPart.setPartNumber(hardwareOrderListToSession.get(j).getPartNumber());
										orderPart.setPartDesc(hardwareOrderListToSession.get(j).getPartDesc());
										orderPart.setLocalDesc(hardwareOrderListToSession.get(j).getLocalDesc());
										orderPart.setPartType(hardwareOrderListToSession.get(j).getPartType());
										orderPart.setModel(hardwareOrderListToSession.get(j).getModel());
										orderPart.setPartImg(hardwareOrderListToSession.get(j).getPartImg());
										orderPart.setOrderQuantity(orderQuantity);
										orderPart.setCatalogId(hardwareOrderListToSession.get(j).getCatalogId());
										orderPart.setContractLineItemId(hardwareOrderListToSession.get(j).getContractLineItemId());
										orderPart.setSupplyId(hardwareOrderListToSession.get(j).getSupplyId());
										orderPart.setSalesOrg(hardwareOrderListToSession.get(j).getSalesOrg());
										if(hardwareOrderListToSession.get(j).getUnitPrice()!=null){
											orderPart.setUnitPrice(hardwareOrderListToSession.get(j).getUnitPrice());
											if(hardwareOrderListToSession.get(j).getUnitPrice()!=null && hardwareOrderListToSession.get(j).getUnitPrice()!=""){
												orderPart.setTotal(PaymentUtil.calculateTotalPrice(hardwareOrderListToSession.get(j).getUnitPrice(),orderQuantity));
											}
										}
										if(hardwareOrderListToSession.get(j).getCurrency()!=null){
											orderPart.setCurrency(hardwareOrderListToSession.get(j).getCurrency());
											creditCurrency = hardwareOrderListToSession.get(j).getCurrency();
										}
										modifiedAccessoriesList.add(orderPart);
										modifiedHardwareList.add(orderPart);
										LOGGER.debug("part added for catalog id "+orderPart.getCatalogId()+" and quantity "+orderPart.getOrderQuantity());
									}
									LOGGER.debug("After setting order quantity for "+i+" number part is "+hardwareOrderListToSession.get(j).getOrderQuantity());
									break;
								}
							}
						}
					}
				}
				
				/* Retrieve Hardware Flag information to determine whether to call for Tax*/				
				Map<String,Boolean> hardwareFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.HWFINALFLAGS,PortletSession.APPLICATION_SCOPE);
				
				if(hardwareFinalFlags.get("finalTaxCalcFlag")== true){

                   	Map<String, String> priceMap = new HashMap<String, String>();
                    
                    if(modifiedHardwareList!=null && !(modifiedHardwareList.isEmpty())){
                    	/*Retrieve Tax fro Webmethods/SAP*/
    					TaxContract contract = ContractFactory.getOrderTaxContract(modifiedHardwareList, hardwareDetailPageForm.getShipToAddress(), session, true);    					
    					long timeBeforeCall=System.currentTimeMillis();
    					TaxResult taxresult = retrieveTaxService.retrieveTaxList(contract);
    					long timeAfterCall=System.currentTimeMillis();
						LOGGER.info("start printing lex logger");
						
						PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVETAXLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SAP,contract);
    					
    					LOGGER.info("end printing lex loggger");
    					priceMap = PaymentUtil.calculateTotalPriceWithTax(new ArrayList<Object>(modifiedHardwareList), taxresult.getLineInformationList());
    					
    					//Populate Taxes in the hardware list
						for(Price price1 : taxresult.getLineInformationList()){
							for(OrderPart orderPart : modifiedBundleList){
								if(price1.getSourceReferenceLineId().equalsIgnoreCase(orderPart.getCatalogId())){
									/* Set Tax to part if Tax call has not failed, otherwise Tax should be Unavailable*/
									if(!price1.getTax().equalsIgnoreCase("Unavailable")){
										orderPart.setTax(price1.getTax());
									}
								}
							}for(OrderPart orderPart : modifiedAccessoriesList){
								if(price1.getSourceReferenceLineId().equalsIgnoreCase(orderPart.getCatalogId())){
									if(!price1.getTax().equalsIgnoreCase("Unavailable")){
										orderPart.setTax(price1.getTax());
									}
								}
							}				
							
						}
						
                    }else{
                    	BigDecimal priceTotal= new BigDecimal("0");
                	    BigDecimal taxTotal = new BigDecimal("0");
                	    BigDecimal grandTotal= new BigDecimal("0");
                	    priceMap.put("totalPrice", priceTotal.toString());
                	    priceMap.put("totalTax", taxTotal.toString());
                	    priceMap.put("grandTotal", grandTotal.toString());
                    }                   
    
                    hardwareDetailPageForm.setTotalAmt(priceMap.get("grandTotal"));
                    hardwareDetailPageForm.setSubTotal(priceMap.get("totalPrice"));
                    hardwareDetailPageForm.setTax(priceMap.get("totalTax"));
				}
				
				if(modifiedBundleList!=null && !(modifiedBundleList.isEmpty())){
					hardwareDetailPageForm.setBundleList(modifiedBundleList);
				}
				if(modifiedAccessoriesList!=null && !(modifiedAccessoriesList.isEmpty())){
					hardwareDetailPageForm.setAccessoriesList(modifiedAccessoriesList);
				}
				
				String paymentType = "";
				Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute("hardwareCurrentDetails",PortletSession.APPLICATION_SCOPE);
				if(hardwareDetails != null){
					paymentType = hardwareDetails.get("paymentType");
				}
				if(hardwareFinalFlags.get("finalCreditFlag")== true && hardwareFinalFlags.get("finalShowPriceFlag")== true && "Ship and Bill".equals(paymentType)){				
					GenericAddress selBillTo = (GenericAddress)session.getAttribute("selectedHWBillToAddress", PortletSession.APPLICATION_SCOPE);				
					if(selBillTo!=null){
						hardwareDetailPageForm.setBillToAddress(selBillTo);
						model.addAttribute("billTo",selBillTo);
					}
				}
				
				LOGGER.debug("Entering attachment block");
				List <Attachment> attachmentList = new ArrayList<Attachment>();
				attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
				
				LOGGER.debug("@@@@@@@@@@@@@@@@@@@@ chcek how many attachment is there ");
				if(attachmentList!=null && !(attachmentList.isEmpty())){
					for (int i=0;i<attachmentList.size();i++){
						LOGGER.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! attachment names are "+attachmentList.get(i).getAttachmentName()+" attachment display names are"+attachmentList.get(i).getDisplayAttachmentName());
					}
				}
				attachForm.setAttachmentDescription(attachmentDescription);
				hardwareDetailPageForm.setAttachmentDescription(attachmentDescription);
				hardwareDetailPageForm.setAttachmentList(attachmentList);
				ResourceURL resURL1 = response.createResourceURL();
				resURL1.setResourceID("displayAttachment");
				model.addAttribute("displayAttachment", resURL1.toString());
				hardwareDetailPageForm.refreshSubmitToken(request);
				session.setAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
				model.addAttribute("creditCurrency",creditCurrency);
				model.addAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
			LOGGER.debug("---------------------- [Out] submitHardwareOrder for confirm order page ----------------------");
			returnPage = "ordermanagement/hardwareOrder/reviewHardwareOrder";
				
				}catch(Exception e){
					
					exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
							"tax.validation.error", request.getLocale());					
					model.addAttribute("Error", exError);
					
					commonController.getContactInformation(request, response);
					List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
					if (attachmentList != null){
						hardwareDetailPageForm.setAttachmentList(attachmentList);
					}
					hardwareDetailPageForm.setAttachmentDescription(attachmentDescription);
					attachForm.setAttachmentDescription(attachmentDescription);
					model.addAttribute("catalogDetailAttachmentForm", hardwareDetailPageForm);
					model.addAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
					attachForm.setListOfFileTypes(listOfFileTypes);
					attachForm.setAttMaxCount(attMaxCount);
					FileUploadForm fileUploadForm = new FileUploadForm();
					//changes for mps2.1
					if(hardwareDetailPageForm.getAttachmentList() !=null){
						fileUploadForm.setFileCount(hardwareDetailPageForm.getAttachmentList().size());
						}
						//changes for mps2.1
					model.addAttribute("attachmentForm", attachForm);
					model.addAttribute("fileUploadForm", fileUploadForm);
					LOGGER.error("exception occured "+e.getMessage());					
					returnPage = "ordermanagement/hardwareOrder/hardwareDetail";				
			}
			}
		}else if("draftOrderRequest".equalsIgnoreCase(pageSubmitType)){
			LOGGER.debug("---------------------- [In] submitHardwareOrder for draft order page ----------------------");
			
			if(bindingResult.hasErrors()){
				LOGGER.debug("%%%%%%%%%%%%%% Error is there in the form ");
				for(int j=0;j<bindingResult.getAllErrors().size();j++){
					LOGGER.debug("Errors are in controller "+bindingResult.getAllErrors().get(j).getCode().toString());
				}
				//Display part list after validation				
				String paramPrefix = null;
				String orderQuantity = null;
				String catalogId = "";
				List<OrderPart> modifiedBundleList = new ArrayList<OrderPart>();
				List<OrderPart> modifiedAccessoriesList = new ArrayList<OrderPart>();
				List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
				
				if(hardwareOrderListToSession!=null){
					LOGGER.debug("The size of the catalogOrderListToSession is "+hardwareOrderListToSession.size());
				}
				if(hardwareDetailPageForm.getBundleList()!=null){
					for (int i=0;i<hardwareDetailPageForm.getBundleList().size();i++){
						LOGGER.debug("Order quantity for "+i+" number part is "+hardwareDetailPageForm.getBundleList().get(i).getOrderQuantity());
						paramPrefix = "bundleList[" + i + "].";
						orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
						catalogId = request.getParameter("bundleId["+i+"]");
						LOGGER.debug("!!!!!!!!!!!! for bundleID "+catalogId+" order quantity is "+orderQuantity);
						if(catalogId!=null){
							for(int j=0;j<hardwareOrderListToSession.size();j++){
								if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(j).getCatalogId())){
									if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.valueOf(0))) {
										if("".equals(orderQuantity)){LOGGER.debug("Order quanityt is blank");}
										if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
									}else{
										OrderPart orderPart = new OrderPart();
										orderPart.setPartNumber(hardwareOrderListToSession.get(j).getPartNumber());
										orderPart.setPartDesc(hardwareOrderListToSession.get(j).getPartDesc());
										orderPart.setLocalDesc(hardwareOrderListToSession.get(j).getLocalDesc());
										orderPart.setOrderQuantity(orderQuantity);
										orderPart.setCatalogId(hardwareOrderListToSession.get(j).getCatalogId());
										orderPart.setSupplyId(hardwareOrderListToSession.get(j).getSupplyId());
										orderPart.setContractLineItemId(hardwareOrderListToSession.get(j).getContractLineItemId());
										orderPart.setSalesOrg(hardwareOrderListToSession.get(j).getSalesOrg());
										orderPart.setPartImg(hardwareOrderListToSession.get(j).getPartImg());
										orderPart.setBundlePartList(hardwareOrderListToSession.get(j).getBundlePartList());
										if(hardwareOrderListToSession.get(j).getUnitPrice()!=null){
											orderPart.setUnitPrice(hardwareOrderListToSession.get(j).getUnitPrice());
										}
										if(hardwareOrderListToSession.get(j).getCurrency()!=null){
											orderPart.setCurrency(hardwareOrderListToSession.get(j).getCurrency());
										}
										modifiedBundleList.add(orderPart);
										LOGGER.debug("part added for catalog id "+orderPart.getCatalogId()+" and quantity "+orderPart.getOrderQuantity());
									}
									LOGGER.debug("After setting order quantity for "+i+" number part is "+hardwareOrderListToSession.get(j).getOrderQuantity());
									break;
								}
							}
						}
					}
				}
				
				if(hardwareDetailPageForm.getAccessoriesList()!=null){
					for (int i=0;i<hardwareDetailPageForm.getAccessoriesList().size();i++){
						LOGGER.debug("Order quantity for "+i+" number part is "+hardwareDetailPageForm.getAccessoriesList().get(i).getOrderQuantity());
						paramPrefix = "accessoriesList[" + i + "].";
						orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
						catalogId = request.getParameter("accessoriesCatalogId["+i+"]");
						LOGGER.debug("!!!!!!!!!!!! for catalogID "+catalogId+" order quantity is "+orderQuantity);
						if(catalogId!=null){
							for(int j=0;j<hardwareOrderListToSession.size();j++){
								if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(j).getCatalogId())){
									if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.valueOf(0))) {
										if("".equals(orderQuantity)){LOGGER.debug("Order quantity is blank");}
										if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
									}else{
										OrderPart orderPart = new OrderPart();
										orderPart.setPartNumber(hardwareOrderListToSession.get(j).getPartNumber());
										orderPart.setPartDesc(hardwareOrderListToSession.get(j).getPartDesc());
										orderPart.setLocalDesc(hardwareOrderListToSession.get(j).getLocalDesc());
										orderPart.setPartType(hardwareOrderListToSession.get(j).getPartType());
										orderPart.setModel(hardwareOrderListToSession.get(j).getModel());
										orderPart.setOrderQuantity(orderQuantity);
										orderPart.setCatalogId(hardwareOrderListToSession.get(j).getCatalogId());
										orderPart.setSupplyId(hardwareOrderListToSession.get(j).getSupplyId());
										orderPart.setContractLineItemId(hardwareOrderListToSession.get(j).getContractLineItemId());
										orderPart.setSalesOrg(hardwareOrderListToSession.get(j).getSalesOrg());
										orderPart.setPartImg(hardwareOrderListToSession.get(j).getPartImg());
										if(hardwareOrderListToSession.get(j).getUnitPrice()!=null){
											orderPart.setUnitPrice(hardwareOrderListToSession.get(j).getUnitPrice());
										}
										if(hardwareOrderListToSession.get(j).getCurrency()!=null){
											orderPart.setCurrency(hardwareOrderListToSession.get(j).getCurrency());
										}
										modifiedAccessoriesList.add(orderPart);
										LOGGER.debug("part added for catalog id "+orderPart.getCatalogId()+" and quantity "+orderPart.getOrderQuantity());
									}
									LOGGER.debug("After setting order quantity for "+i+" number part is "+hardwareOrderListToSession.get(j).getOrderQuantity());
									break;
								}
							}
						}
					}
				}		
				List <Attachment> attachmentList = new ArrayList<Attachment>();
				attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
				hardwareDetailPageForm.setAttachmentDescription(attachmentDescription);
				attachForm.setAttachmentDescription(attachmentDescription);
				attachForm.setListOfFileTypes(listOfFileTypes);
				attachForm.setAttMaxCount(attMaxCount);
				hardwareDetailPageForm.setAttachmentList(attachmentList);
				attachForm.setAttachmentList(attachmentList);			
				hardwareDetailPageForm.setBundleList(modifiedBundleList);
				hardwareDetailPageForm.setAccessoriesList(modifiedAccessoriesList);
				GenericAddress selBillTo = (GenericAddress)session.getAttribute("selectedHWBillToAddress", PortletSession.APPLICATION_SCOPE);
				if(selBillTo!=null){
					hardwareDetailPageForm.setBillToAddress(selBillTo);
					model.addAttribute("billTo",selBillTo);
				}
				//End Display part list after validation
				model.addAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
				model.addAttribute("attachmentForm", attachForm);
				FileUploadForm fileUploadForm = new FileUploadForm();
				model.addAttribute("fileUploadForm", fileUploadForm);
				
				returnPage = "ordermanagement/hardwareOrder/hardwareDetail";
			} else {
			String paramPrefix = null;
			String orderQuantity = null;
			String catalogId = "";
			List<OrderPart> modifiedBundleList = new ArrayList<OrderPart>();
			List<OrderPart> modifiedAccessoriesList = new ArrayList<OrderPart>();
			List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
			
			if(hardwareOrderListToSession!=null){
				LOGGER.debug("The size of the catalogOrderListToSession is "+hardwareOrderListToSession.size());
			}
			if(hardwareDetailPageForm.getBundleList()!=null){
				for (int i=0;i<hardwareDetailPageForm.getBundleList().size();i++){
					LOGGER.debug("Order quantity for "+i+" number part is "+hardwareDetailPageForm.getBundleList().get(i).getOrderQuantity());
					paramPrefix = "bundleList[" + i + "].";
					orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
					catalogId = request.getParameter("bundleId["+i+"]");
					LOGGER.debug("!!!!!!!!!!!! for bundleID "+catalogId+" order quantity is "+orderQuantity);
					if(catalogId!=null){
						for(int j=0;j<hardwareOrderListToSession.size();j++){
							if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(j).getCatalogId())){
								if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.valueOf(0))) {
									if("".equals(orderQuantity)){LOGGER.debug("Order quanityt is blank");}
									if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
								}else{
									OrderPart orderPart = new OrderPart();
									orderPart.setPartNumber(hardwareOrderListToSession.get(j).getPartNumber());
									orderPart.setPartDesc(hardwareOrderListToSession.get(j).getPartDesc());
									orderPart.setLocalDesc(hardwareOrderListToSession.get(j).getLocalDesc());
									orderPart.setOrderQuantity(orderQuantity);
									orderPart.setContractLineItemId(hardwareOrderListToSession.get(j).getContractLineItemId());
									orderPart.setCatalogId(hardwareOrderListToSession.get(j).getCatalogId());
									orderPart.setSalesOrg(hardwareOrderListToSession.get(j).getSalesOrg());
									orderPart.setSupplyId(hardwareOrderListToSession.get(j).getSupplyId());
									orderPart.setPartImg(hardwareOrderListToSession.get(j).getPartImg());
									orderPart.setBundlePartList(hardwareOrderListToSession.get(j).getBundlePartList());
									if(hardwareOrderListToSession.get(j).getUnitPrice()!=null){
										orderPart.setUnitPrice(hardwareOrderListToSession.get(j).getUnitPrice());
									}
									if(hardwareOrderListToSession.get(j).getCurrency()!=null){
										orderPart.setCurrency(hardwareOrderListToSession.get(j).getCurrency());
									}
									modifiedBundleList.add(orderPart);
									LOGGER.debug("part added for catalog id "+orderPart.getCatalogId()+" and quantity "+orderPart.getOrderQuantity());
								}
								LOGGER.debug("After setting order quantity for "+i+" number part is "+hardwareOrderListToSession.get(j).getOrderQuantity());
								break;
							}
						}
					}
				}
			}
			
			if(hardwareDetailPageForm.getAccessoriesList()!=null){
				for (int i=0;i<hardwareDetailPageForm.getAccessoriesList().size();i++){
					LOGGER.debug("Order quantity for "+i+" number part is "+hardwareDetailPageForm.getAccessoriesList().get(i).getOrderQuantity());
					paramPrefix = "accessoriesList[" + i + "].";
					orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
					catalogId = request.getParameter("accessoriesCatalogId["+i+"]");
					LOGGER.debug("!!!!!!!!!!!! for catalogID "+catalogId+" order quantity is "+orderQuantity);
					if(catalogId!=null){
						for(int j=0;j<hardwareOrderListToSession.size();j++){
							if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(j).getCatalogId())){
								if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.valueOf(0))) {
									if("".equals(orderQuantity)){LOGGER.debug("Order quantity is blank");}
									if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
								}else{
									OrderPart orderPart = new OrderPart();
									orderPart.setPartNumber(hardwareOrderListToSession.get(j).getPartNumber());
									orderPart.setPartDesc(hardwareOrderListToSession.get(j).getPartDesc());
									orderPart.setLocalDesc(hardwareOrderListToSession.get(j).getLocalDesc());
									orderPart.setPartType(hardwareOrderListToSession.get(j).getPartType());
									orderPart.setModel(hardwareOrderListToSession.get(j).getModel());
									orderPart.setPartImg(hardwareOrderListToSession.get(j).getPartImg());
									orderPart.setOrderQuantity(orderQuantity);
									orderPart.setCatalogId(hardwareOrderListToSession.get(j).getCatalogId());
									orderPart.setContractLineItemId(hardwareOrderListToSession.get(j).getContractLineItemId());
									orderPart.setSupplyId(hardwareOrderListToSession.get(j).getSupplyId());
									orderPart.setSalesOrg(hardwareOrderListToSession.get(j).getSalesOrg());
									if(hardwareOrderListToSession.get(j).getUnitPrice()!=null){
										orderPart.setUnitPrice(hardwareOrderListToSession.get(j).getUnitPrice());
									}
									if(hardwareOrderListToSession.get(j).getCurrency()!=null){
										orderPart.setCurrency(hardwareOrderListToSession.get(j).getCurrency());
									}
									modifiedAccessoriesList.add(orderPart);
									LOGGER.debug("part added for catalog id "+orderPart.getCatalogId()+" and quantity "+orderPart.getOrderQuantity());
								}
								LOGGER.debug("After setting order quantity for "+i+" number part is "+hardwareOrderListToSession.get(j).getOrderQuantity());
								break;
							}
						}
					}
				}
			}
						
			hardwareDetailPageForm.setBundleList(modifiedBundleList);
			hardwareDetailPageForm.setAccessoriesList(modifiedAccessoriesList);
			LOGGER.debug("Entering attachment block");
			List <Attachment> attachmentList = new ArrayList<Attachment>();
			attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
			//Adding correct data for the attachment
			if(attachmentList!=null && !(attachmentList.isEmpty())){
				LOGGER.debug("Attachment size is more than one");
				for(int i=0;i<attachmentList.size();i++){
					LOGGER.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! attachment names are "+attachmentList.get(i).getAttachmentName());
				}
			}
			GenericAddress selBillTo = (GenericAddress)session.getAttribute("selectedHWBillToAddress", PortletSession.APPLICATION_SCOPE);
			
			if(selBillTo!=null){
				LOGGER.debug("selBillTo Address Id" + selBillTo.getSoldToNumber());
				LOGGER.debug("selBillTo Sold To" + selBillTo.getSoldToNumber());
				LOGGER.debug("selBillTo Address Line 1" + selBillTo.getAddressLine1());
				hardwareDetailPageForm.setBillToAddress(selBillTo);
				model.addAttribute("billTo",selBillTo);
			}
			hardwareDetailPageForm.setAttachmentDescription(attachmentDescription);
			attachForm.setAttachmentDescription(attachmentDescription);
			attachForm.setListOfFileTypes(listOfFileTypes);
			attachForm.setAttMaxCount(attMaxCount);
			hardwareDetailPageForm.setAttachmentList(attachmentList);
			attachForm.setAttachmentList(attachmentList);
			CreateHardwareRequestContract createHardwareReqContract = 
				ContractFactory.getHardwareDraftReqContract(hardwareDetailPageForm, request);
			
			Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		
			long timeBeforeCall=System.currentTimeMillis();
			CreateServiceRequestResult result = createHardwareRequest.createHardwareRequest(createHardwareReqContract,accDetails);
			long timeAfterCall=System.currentTimeMillis();
			LEXLOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_CREATEHARDWAREREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,createHardwareReqContract);
			
			LEXLOGGER.info("end printing lex loggger");
			LOGGER.debug("ServiceRequest number received from webmethod is "+result.getServiceRequestNumber());
			LOGGER.debug("ServiceRequest row id received from webmethod is "+result.getServiceRequestRowId());
			String serviceRequestNumber = result.getServiceRequestNumber();
			String srRowId = result.getServiceRequestRowId();
			//Received the servicerequestnumber from webmethods. Lets call aMind
			AttachmentContract contract = new AttachmentContract();
			List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
			if(attachmentList!=null && !(attachmentList.isEmpty())){
				LOGGER.debug("No of attachment in the reviewAssetOrderForm "+attachmentList.size());
				for (int i=0;i<attachmentList.size();i++){
					if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
						LOGGER.debug("Attachment id is either null or blank");
						createSRAttachmentList.add(attachmentList.get(i));
					}
					LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$ Attachment ban is "+attachmentList.size());
				}
				if(createSRAttachmentList!=null && !(createSRAttachmentList.isEmpty())){
					for(int i=0;i<createSRAttachmentList.size();i++){
						LOGGER.debug("Create SR will be called for following attachments "+createSRAttachmentList.get(i).getAttachmentName());
					}
					contract.setAttachments(createSRAttachmentList);
					contract.setRequestType("Service Request");
					contract.setIdentifier(srRowId);
					
					LEXLOGGER.info("start printing lex logger");
					ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
					LEXLOGGER.info("end printing lex logger");
					long timeBeforeCallAttachment=System.currentTimeMillis();					
					attachmentService.uploadAttachments(contract);
					long timeAfterCallAttachment=System.currentTimeMillis();
					LEXLOGGER.info("start printing lex logger");
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeCallAttachment,timeAfterCallAttachment, PerformanceConstant.SYSTEM_AMIND,contract);					
					
					LEXLOGGER.info("end printing lex loggger");
				}
			}
			
			hardwareDetailPageForm.setMaxPartsToBeOrdered(getMaxPartsToBeOrdered());
			model.addAttribute("serviceRequestNumber", serviceRequestNumber);
			session.setAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
			model.addAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
			model.addAttribute("srnumber",serviceRequestNumber);
			model.addAttribute("draftConfirmation","draftConfirmation");
			FileUploadForm fileUploadForm = new FileUploadForm();
			model.addAttribute("fileUploadForm", fileUploadForm);
			model.addAttribute("attachmentForm", attachForm);
			returnPage = "ordermanagement/hardwareOrder/hardwareDetail";
			LOGGER.debug("---------------------- [Out] submitAssetOrder for draft order page ----------------------");
		}
		
		}
		return returnPage;
	}
	
	
	/**
	 * This method is used to call WM webservice and also aMind attachment service for create Hardware Order
	 * @param request 
	 * @param response 
	 * @param hardwareDetailPageForm 
	 * @param model 
	 * @return pageName
	 * @throws Exception 
	 */
	@RenderMapping(params="action=confirmHardwareOrder")
	public String confirmHardwareOrder(
			RenderRequest request, 
			RenderResponse response,  
			@ModelAttribute("hardwareDetailPageForm") HardwareDetailPageForm hardwareDetailPageForm,
			Model model) throws Exception{
		LOGGER.debug("---------------------- [In] confirmHardwareOrder method ----------------------");
		if (PortalSessionUtil.isDuplicatedSubmit(request,hardwareDetailPageForm)) {
			List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
			List <Attachment> attachmentList = new ArrayList<Attachment>();
			attachmentList = hardwareDetailPageForm.getAttachmentList();
			if(attachmentList!=null && attachmentList.size()>0){
				List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
				for(int i=0;i<hardwareDetailPageForm.getAttachmentList().size();i++){
					AttachmentFile attachmentFile = new AttachmentFile();
					attachmentFile.setFileName(attachmentList.get(i).getDisplayAttachmentName());
					attachmentFile.setFileSize(attachmentList.get(i).getSize());
					displayAttachmentList.add(attachmentFile);
					if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
						createSRAttachmentList.add(attachmentList.get(i));
					}
					
				}
				model.addAttribute("displayAttachmentList", displayAttachmentList);
				hardwareDetailPageForm.setDisplayAttachmentList(displayAttachmentList);
				}
			LOGGER.debug("OrderHardwareController.submitTemplateSRForm.duplicated submit, do nothing!");		
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "exception.serviceRequest.duplicateSubmission", null, request.getLocale()));
		}else{
		PortletSession session = request.getPortletSession();
		
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(accDetails != null){
			LOGGER.debug("Account id ::::   "+accDetails.get("accountId")+" Account Name :::: "+accDetails.get("accountName")+" Account Organization :::: "+accDetails.get("accountOrganization")+" Agreement Id:::: "+accDetails.get("agreementId")+" Agreement Name :::: "+accDetails.get("agreementName")+" Country :::: "+accDetails.get("country"));
		}		
		
		String creditFlag = "false";
		if(hardwareDetailPageForm.getCreditFlag()!=null && hardwareDetailPageForm.getCreditFlag().equalsIgnoreCase("true")){
			creditFlag = hardwareDetailPageForm.getCreditFlag();
		}		
		//Creating WM call Contract
		CreateHardwareRequestContract createHardwareContract = ContractFactory.getHardwareContract(hardwareDetailPageForm, request);		
		long timeBeforeCall=System.currentTimeMillis();
		CreateServiceRequestResult result = createHardwareRequest.createHardwareRequest(createHardwareContract,accDetails);
		long timeAfterCall=System.currentTimeMillis();
		LEXLOGGER.info("start printing lex logger");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_CREATEHARDWAREREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,createHardwareContract);
		
		LEXLOGGER.info("end printing lex loggger");
		LOGGER.debug("ServiceRequest number received from webmethod is "+result.getServiceRequestNumber());
		LOGGER.debug("ServiceRequest row id received from webmethod is "+result.getServiceRequestRowId());
		String serviceRequestNumber = result.getServiceRequestNumber();
		String srRowId = result.getServiceRequestRowId();
		AttachmentContract contract = new AttachmentContract();
		List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
		List <Attachment> attachmentList = new ArrayList<Attachment>();
		attachmentList = hardwareDetailPageForm.getAttachmentList();
		if(attachmentList!=null && !(attachmentList.isEmpty())){
			List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
			for(int i=0;i<attachmentList.size();i++){
				AttachmentFile attachmentFile = new AttachmentFile();
				attachmentFile.setFileName(attachmentList.get(i).getDisplayAttachmentName());
				attachmentFile.setFileSize(attachmentList.get(i).getSize());
				displayAttachmentList.add(attachmentFile);
				if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
					createSRAttachmentList.add(attachmentList.get(i));
				}
			}
			LOGGER.debug("No of attachment in the reviewAssetOrderForm "+attachmentList.size());
			if(createSRAttachmentList!=null && !(createSRAttachmentList.isEmpty())){
				for(int i=0;i<createSRAttachmentList.size();i++){
					LOGGER.debug("Create SR will be called for following attachments "+createSRAttachmentList.get(i).getAttachmentName());
				}
				contract.setAttachments(createSRAttachmentList);
				contract.setRequestType("Service Request");
				contract.setIdentifier(srRowId);
				try{
				attachmentService.uploadAttachments(contract);
				}
				catch (Exception exception) {
					displayAttachmentList = null;
					model.addAttribute("displayAttachmentList", displayAttachmentList);
					hardwareDetailPageForm.setDisplayAttachmentList(displayAttachmentList);
					hardwareDetailPageForm.setAttachmentList(null);
					LOGGER.info("Exception in Attachment upload.");
					model.addAttribute("attachmentException", "attachfailed");
					exception.getMessage();
				}
			}
			model.addAttribute("displayAttachmentList", displayAttachmentList);
			hardwareDetailPageForm.setDisplayAttachmentList(displayAttachmentList);
		}
		LOGGER.debug("Let find oyt the server name "+request.getServerName());
		LOGGER.debug("Lets find oyt the server port "+request.getServerPort());
		//this is to enable re-submit of SR form on submit/draft exception
		Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, session.PORTLET_SCOPE);
		BaseForm baseForm = (BaseForm)hardwareDetailPageForm;
		baseForm.setSubmitToken(tokenInSession);
		model.addAttribute("serviceRequestNumber", serviceRequestNumber);	
		model.addAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
		model.addAttribute("creditFlag", creditFlag);
		session.removeAttribute("draftSrNumber");
		session.removeAttribute("attachmentList");
		session.removeAttribute("catalogDetailPageForm");
		}
		LOGGER.debug("----------------------- [OUT] confirmHardwareOrder method ----------------------");
		return "ordermanagement/hardwareOrder/confirmHardwareOrder";
	}
	
	
	/**
	 * This method is used to show the Cart Page when user clicks on the link displaying product quantity
	 * @param request  
	 * @param response  
	 * @param model  
	 * @return pageName  
	 * @throws Exception  
	 */
	@RequestMapping(params = "action=showCartViewPage")
	public String showCartViewPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		LOGGER.debug("-------------showCartViewPage started---------");
		PortletSession session = request.getPortletSession();
		CartViewForm cartViewForm = new CartViewForm();
		session.removeAttribute("cartViewHardwareOrderListToSession");
		List<OrderPart> cartViewHardwareOrderListToSession = new ArrayList<OrderPart>();
		List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
		if (hardwareOrderListToSession!=null){
			List<OrderPart> hardwarePartList = new ArrayList<OrderPart>();
				for(OrderPart part : hardwareOrderListToSession){
					OrderPart orderPart = new OrderPart();
					orderPart.setPartNumber(part.getPartNumber());
					orderPart.setPartDesc(part.getPartDesc());
					orderPart.setLocalDesc(part.getLocalDesc());
					orderPart.setPartType(part.getPartType());
					orderPart.setPartQuantity(part.getPartQuantity());
					orderPart.setCatalogId(part.getCatalogId());
					orderPart.setContractLineItemId(part.getContractLineItemId());
					orderPart.setSupplyId(part.getSupplyId());
					orderPart.setModel(part.getModel());
					orderPart.setPartImg(part.getPartImg());
					orderPart.setSalesOrg(part.getSalesOrg());
					orderPart.setHardwareType(part.getHardwareType());
					if(part.getBundlePartList()!=null){
						orderPart.setBundlePartList(part.getBundlePartList());
					}
					if(part.getUnitPrice()!=null && part.getUnitPrice()!=""){
						orderPart.setUnitPrice(part.getUnitPrice());
					}
					if(part.getCurrency()!=null && part.getCurrency()!=""){
						orderPart.setCurrency(part.getCurrency());
					}
					LOGGER.debug("@@@@@@@@@@@@@@ Part details number "+part.getPartNumber()+" part desc "
							+part.getPartDesc()+" part type "+part.getPartType()+" quantity "+
							part.getPartQuantity()+" catalogid "+part.getCatalogId()+" model is "+part.getModel());
					hardwarePartList.add(orderPart);
					cartViewHardwareOrderListToSession.add(orderPart);
				}
				cartViewForm.setCatalogPartList(hardwarePartList);
				session.setAttribute("cartViewHardwareOrderListToSession", cartViewHardwareOrderListToSession);//This will be used for cart view modification
		}
		LOGGER.debug("-------------after setting the values in cartViewHardwareOrderListToSession---------");
		model.addAttribute("cartViewForm", cartViewForm);
		return "ordermanagement/hardwareOrder/cartView";
	}
	
	/**
	 * This method is used to update the cart
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return 
	 * @throws Exception 
	 */
	@ResourceMapping("updateCartView")
	public void updateCartViewURL(ResourceRequest request, ResourceResponse response, Model model) throws Exception
	{
		LOGGER.debug("---------------------------------- [IN]updateCartViewURL-------------------");
		PortletSession session = request.getPortletSession();
		String orderQuantity= request.getParameter("orderQuantity");
		String catalogId = request.getParameter("catalogId");
		String jobType = request.getParameter("jobType");
		LOGGER.debug("action value is "+jobType);
		List<OrderPart> cartViewHardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("cartViewHardwareOrderListToSession");
		//Changes for Direct part deletion MPS 2.1
		List<?> hardwareOrderListToSession = (ArrayList<?>) session.getAttribute("hardwareOrderListToSession");
		//Ends Changes for Direct part deletion MPS 2.1
		if (cartViewHardwareOrderListToSession!=null){
			for(int i=0;i<cartViewHardwareOrderListToSession.size();i++){
				if(catalogId.equalsIgnoreCase(cartViewHardwareOrderListToSession.get(i).getCatalogId())){
					//Update is required
					if("update".equalsIgnoreCase(jobType)){
						LOGGER.debug("orderType value is" +cartViewHardwareOrderListToSession.get(i).getHardwareType());
						OrderPart orderPart = new OrderPart();
						orderPart.setPartNumber(cartViewHardwareOrderListToSession.get(i).getPartNumber());
						orderPart.setPartDesc(cartViewHardwareOrderListToSession.get(i).getPartDesc());
						orderPart.setLocalDesc(cartViewHardwareOrderListToSession.get(i).getLocalDesc());
						orderPart.setPartType(cartViewHardwareOrderListToSession.get(i).getPartType());
						orderPart.setModel(cartViewHardwareOrderListToSession.get(i).getModel());
						orderPart.setPartImg(cartViewHardwareOrderListToSession.get(i).getPartImg());
						orderPart.setContractLineItemId(cartViewHardwareOrderListToSession.get(i).getContractLineItemId());
						orderPart.setHardwareType(cartViewHardwareOrderListToSession.get(i).getHardwareType());
						orderPart.setSalesOrg(cartViewHardwareOrderListToSession.get(i).getSalesOrg());
						orderPart.setPartQuantity(orderQuantity);
						if(cartViewHardwareOrderListToSession.get(i).getUnitPrice()!=null && cartViewHardwareOrderListToSession.get(i).getUnitPrice()!=""){
							orderPart.setUnitPrice(cartViewHardwareOrderListToSession.get(i).getUnitPrice());
						}
						if(cartViewHardwareOrderListToSession.get(i).getCurrency()!=null && cartViewHardwareOrderListToSession.get(i).getCurrency()!=""){
							orderPart.setCurrency(cartViewHardwareOrderListToSession.get(i).getCurrency());
						}
						if(cartViewHardwareOrderListToSession.get(i).getBundlePartList()!=null){
							orderPart.setBundlePartList(cartViewHardwareOrderListToSession.get(i).getBundlePartList());
						}
						orderPart.setCatalogId(catalogId);
						orderPart.setSupplyId(cartViewHardwareOrderListToSession.get(i).getSupplyId());
						cartViewHardwareOrderListToSession.remove(i);
						//if orderQuantity is zero dont add the part to the session
						//else remove the quantity from the cart but we cant do that. We need to handle that if any error comes
						cartViewHardwareOrderListToSession.add(orderPart);
					}else{
						//Changes for Direct part deletion MPS 2.1
						hardwareOrderListToSession.remove(i);
						//Ends Changes for Direct part deletion MPS 2.1
						cartViewHardwareOrderListToSession.remove(i);
					}
					session.removeAttribute("cartViewHardwareOrderListToSession");
					//Changes for Direct part deletion MPS 2.1
					session.removeAttribute("hardwareOrderListToSession");
					session.setAttribute("hardwareOrderListToSession", hardwareOrderListToSession);
					//Ends Changes for Direct part deletion MPS 2.1
					session.setAttribute("cartViewHardwareOrderListToSession", cartViewHardwareOrderListToSession);
					break;
				}
			}
		}
		
		//Printing the values
		List<OrderPart> printCartViewHardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("cartViewHardwareOrderListToSession");
		
		if (printCartViewHardwareOrderListToSession!=null){
			for(int i=0;i<printCartViewHardwareOrderListToSession.size();i++){
				LOGGER.debug("Part type "+printCartViewHardwareOrderListToSession.get(i).getPartType()+" Quantity "+printCartViewHardwareOrderListToSession.get(i).getPartQuantity());
			}
		}
		//complete printing the values
		LOGGER.debug("---------------------------------- [OUT]updateCartViewURL-------------------");
	}
	
	
	/**
	 * This method is used to update the complete cart
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return 
	 * @throws Exception 
	 */
	@ResourceMapping("updateCompleteCartView")
	public void updateCompleteCartView(ResourceRequest request, ResourceResponse response, Model model) throws Exception
	{
		LOGGER.debug("------------------------- [IN]updateCompleteCartView -------------------------");
		PortletSession session = request.getPortletSession();
		
		String orderQuantity= request.getParameter("orderQuantity");
		String catalogId = request.getParameter("catalogId");
		List<String> orderQuantityList = Arrays.asList(orderQuantity.split(","));
		List<String> catalogIdList = Arrays.asList(catalogId.split(","));
		
		List<OrderPart> hardwareOrderListToSession = new ArrayList<OrderPart>();
		List<OrderPart> cartViewHardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("cartViewHardwareOrderListToSession");
		session.removeAttribute("hardwareOrderListToSession");
		for(int i=0;i<cartViewHardwareOrderListToSession.size();i++){
			String orderQuantitySession = cartViewHardwareOrderListToSession.get(i).getPartQuantity();
			LOGGER.debug("Order quantity value for "+i+" no part is "+orderQuantitySession);
			LOGGER.debug("orderQuantity="+orderQuantityList.get(i)+" catalogId="+catalogIdList.get(i)+" catalogId session="+cartViewHardwareOrderListToSession.get(i).getCatalogId());
			if(!("".equals(orderQuantitySession)||Integer.toString(0).equals(orderQuantitySession)) && (catalogIdList.get(i).equalsIgnoreCase(cartViewHardwareOrderListToSession.get(i).getCatalogId()))){
				LOGGER.debug("orderType value is" +cartViewHardwareOrderListToSession.get(i).getHardwareType());
				OrderPart orderPart = new OrderPart();
				orderPart.setPartNumber(cartViewHardwareOrderListToSession.get(i).getPartNumber());
				orderPart.setPartDesc(cartViewHardwareOrderListToSession.get(i).getPartDesc());
				orderPart.setLocalDesc(cartViewHardwareOrderListToSession.get(i).getLocalDesc());
				orderPart.setPartType(cartViewHardwareOrderListToSession.get(i).getPartType());
				orderPart.setModel(cartViewHardwareOrderListToSession.get(i).getModel());
				orderPart.setPartImg(cartViewHardwareOrderListToSession.get(i).getPartImg());
				orderPart.setPartQuantity(orderQuantityList.get(i));
				orderPart.setCatalogId(cartViewHardwareOrderListToSession.get(i).getCatalogId());
				orderPart.setSupplyId(cartViewHardwareOrderListToSession.get(i).getSupplyId());
				orderPart.setContractLineItemId(cartViewHardwareOrderListToSession.get(i).getContractLineItemId());
				orderPart.setSalesOrg(cartViewHardwareOrderListToSession.get(i).getSalesOrg());
				orderPart.setHardwareType(cartViewHardwareOrderListToSession.get(i).getHardwareType());
				if(cartViewHardwareOrderListToSession.get(i).getUnitPrice()!=null && cartViewHardwareOrderListToSession.get(i).getUnitPrice()!=""){
					orderPart.setUnitPrice(cartViewHardwareOrderListToSession.get(i).getUnitPrice());
				}
				if(cartViewHardwareOrderListToSession.get(i).getCurrency()!=null && cartViewHardwareOrderListToSession.get(i).getCurrency()!=""){
					orderPart.setCurrency(cartViewHardwareOrderListToSession.get(i).getCurrency());
				}
				if(cartViewHardwareOrderListToSession.get(i).getBundlePartList()!=null){
					orderPart.setBundlePartList(cartViewHardwareOrderListToSession.get(i).getBundlePartList());
				}				
				cartViewHardwareOrderListToSession.remove(i);
				cartViewHardwareOrderListToSession.add(i, orderPart);
				hardwareOrderListToSession.add(orderPart);
			}
		}
		session.setAttribute("hardwareOrderListToSession", hardwareOrderListToSession);
		session.removeAttribute("cartViewHardwareOrderListToSession");
		session.setAttribute("cartViewHardwareOrderListToSession", cartViewHardwareOrderListToSession);
		
		String successMessage = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"cartUpdate.success.msg", request.getLocale());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(successMessage);
		out.flush();
		out.close();
		LOGGER.debug("------------------------  [OUT]updateCompleteCartView -------------------------");
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showCatalogOrderPage")
	public void showCatalogOrderPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		LOGGER.debug("------------------ RequestMapping of the showCatalogOrderPage -----------------------");
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
	

	

	/**
	 * @param req 
	 * @param resp 
	 * @param hardwareDetailPageForm 
	 * @param model 
	 * @param attachForm 
	 * @return pageName 
	 */
	@RenderMapping(params = "action=goToHardwareDetail")
	public String goToAddCatalog(RenderRequest req, RenderResponse resp, @ModelAttribute("hardwareDetailPageForm") 
			HardwareDetailPageForm hardwareDetailPageForm,@ModelAttribute ("attachmentForm") AttachmentForm attachForm, Model model)	
	{
		PortletSession session = req.getPortletSession();
		LOGGER.debug("primary firstname="+hardwareDetailPageForm.getServiceRequest().getPrimaryContact().getFirstName()+" primary lastname="+hardwareDetailPageForm.getServiceRequest().getPrimaryContact().getLastName()+" primary workphone="+hardwareDetailPageForm.getServiceRequest().getPrimaryContact().getWorkPhone()+" primary emailaddress="+hardwareDetailPageForm.getServiceRequest().getPrimaryContact().getEmailAddress()+" primary contactid="+hardwareDetailPageForm.getServiceRequest().getPrimaryContact().getContactId()+" secondary firstname="+hardwareDetailPageForm.getServiceRequest().getSecondaryContact().getFirstName()+" secondary lastname="+hardwareDetailPageForm.getServiceRequest().getSecondaryContact().getLastName()+" secondary work phone="+hardwareDetailPageForm.getServiceRequest().getSecondaryContact().getWorkPhone()+" secondary email="+hardwareDetailPageForm.getServiceRequest().getSecondaryContact().getEmailAddress()+" secondary contactid="+hardwareDetailPageForm.getServiceRequest().getSecondaryContact().getContactId());
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		commonController.getContactInformation(req, resp);
		FileUploadForm fileUploadForm = new FileUploadForm();
		//changes for mps2.1
		if(hardwareDetailPageForm.getAttachmentList() !=null){
		fileUploadForm.setFileCount(hardwareDetailPageForm.getAttachmentList().size());
		}
		if (attachmentList != null){
			attachForm.setAttachmentList(attachmentList);
		}
		attachForm.setListOfFileTypes(listOfFileTypes);
		attachForm.setAttMaxCount(attMaxCount);
		model.addAttribute("attachmentForm", attachForm);
		model.addAttribute("fileUploadForm", fileUploadForm);
		model.addAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
		model.addAttribute("catalogDetailAttachmentForm", hardwareDetailPageForm);
		//changes for mps2.1
		return "ordermanagement/hardwareOrder/hardwareDetail";
	}
	
	
			
	/**
	 * This method is used to print the hardware order confirmation page
	 * @param request 
	 * @param response 
	 * @param model  
	 * @return pageName 
	 */
	@RequestMapping(params = "action=hardwareOrderConfirmPrint")
	public String showOrderConfirmPrintPage(RenderRequest request, RenderResponse response, Model model){
		return "ordermanagement/hardwareOrder/hardwareOrderConfirmPrint";
	}
	/**
	 * This method is used to email the order confirmation page
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return pageName 
	 */
	@RequestMapping(params = "action=hardwareOrderConfirmEmail")
	public String showOrderConfirmEmailPage(RenderRequest request, RenderResponse response, Model model){
		return "ordermanagement/hardwareOrder/hardwareOrderConfirmEmail";		
	}

	/**
	 * @return maxPartsOrdered  
	 * */
	public String getMaxPartsToBeOrdered() {
		return maxPartsToBeOrdered;
	}
	/**
	 * @param maxPartsToBeOrdered  
	 * */
	public void setMaxPartsToBeOrdered(String maxPartsToBeOrdered) {
		this.maxPartsToBeOrdered = maxPartsToBeOrdered;
	}
	/**
	 * @return listOfFileTypes  
	 * */
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}
	/**
	 * @param listOfFileTypes  
	 * */
	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}
	/**
	 * @return attMaxCount  
	 * */
	public String getAttMaxCount() {
		return attMaxCount;
	}
	/**
	 * @param attMaxCount  
	 * */
	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}
	
	/**
	 * Renders the email confirmation page for Catalog order SR
	 * @param req 
	 * @param resp 
	 * @param model 
	 * @return pageName 
	 */
	@RequestMapping(params = "action=hardwareOrderEmailConfirmationPage")
	public String renderCatalogOrderEmailConfirmationPage(RenderRequest req, RenderResponse resp, Model model) {
		
		return "ordermanagement/hardwareOrder/hardwareOrderConfirmEmail";
	}
	
	
	/**.
	 * This method renders the popup page to email the 
	 * change management confirmation page.
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return pageName 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=emailConfirmationPage")
	public String emailConfirmationPage(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		
		return "common/emailConfirmationPage";
	}
	
	
	
	
	/**
	 * This method is used to retrieve the List of Bill To Address and set them in a select Box
	 * @param billToList  
	 * @param request 
	 * @return dropdownValue   
	 */
	private String getBillToOption(List<GenericAddress> billToList,RenderRequest request) {
		int loopCount = 0; //Add this variable for alternate row color on 11June
		StringBuilder sb = new StringBuilder();
		Map<String, GenericAddress> billToMap = new HashMap<String, GenericAddress>();
		String SELECT_STR = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "requestInfo.option.select", request.getLocale());
		if (billToList == null) {
			sb.append("<complete>");
			sb.append("<option value=\"\" selected=\"selected\">-" +SELECT_STR+ "-</option>");
			sb.append("</complete>");
		} else{
			sb.append("<complete>");
			sb.append("<option value=\"\" selected=\"selected\">-" +SELECT_STR+ "-</option>");
			
			for (GenericAddress billTo:billToList) {
				billToMap.put(String.valueOf(loopCount), billTo);
				
				sb.append("<option value=\"" + loopCount+ "\">");
				
				sb.append("<![CDATA[");
				if(loopCount%2==0) {
					sb.append("<div class=\"comboColor\">");
				} else {
					sb.append("<div class=\"comboAlterColor\">");
				}
				if(billTo.getAddressLine1()!=null && !billTo.getAddressLine1().equals("")){
					sb.append(replaceNullWithBlankString(StringEscapeUtils.escapeJavaScript(billTo.getAddressLine1())));
					if(billTo.getAddressLine2()!=null && !billTo.getAddressLine2().equals("")){
						sb.append(",");
					}else{
						sb.append("<br/>");
					}
				}
				if(billTo.getAddressLine2()!=null && !billTo.getAddressLine2().equals("")){
					sb.append(replaceNullWithBlankString(StringEscapeUtils.escapeJavaScript(billTo.getAddressLine2())));
					sb.append("<br/>");
				}
				
				if(billTo.getCity()!=null && !billTo.getCity().equals("")){
					sb.append(replaceNullWithBlankString(StringEscapeUtils.escapeJavaScript(billTo.getCity())));
					if((billTo.getState()!=null && !billTo.getState().equals("")) || (billTo.getCountry()!=null && !billTo.getCountry().equals(""))){
						sb.append(",");
					}
				}
				
				if(billTo.getState()!=null && !billTo.getState().equals("")){
					sb.append(replaceNullWithBlankString(StringEscapeUtils.escapeJavaScript(billTo.getState())));
					if(billTo.getCountry()!=null && !billTo.getCountry().equals("")){
						sb.append(",");
					}
				}
				
				if(billTo.getCountry()!=null && !billTo.getCountry().equals("")){
					sb.append(replaceNullWithBlankString(StringEscapeUtils.escapeJavaScript(billTo.getCountry())));
					if(billTo.getPostalCode()!=null && !billTo.getPostalCode().equals("")){
						sb.append("<br/>");
					}
				}					
				if(billTo.getPostalCode()!=null && !billTo.getPostalCode().equals("")){
					sb.append(replaceNullWithBlankString(StringEscapeUtils.escapeJavaScript(billTo.getPostalCode())));
				}
				
				sb.append("</div>");
				sb.append("]]>");
				sb.append("</option>");
				loopCount++;
			}
			sb.append("</complete>");
		} 
		//Adding Bill To List to Session
		request.getPortletSession().setAttribute("billToAddressMap", billToMap ,PortletSession.APPLICATION_SCOPE);
		
		return sb.toString();
	}

	/**
	 * This method is used to retrieve the Payment Type/Billing Model
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */	
	@ResourceMapping("getPaymentType")
	public void getPaymentType(ResourceRequest request, ResourceResponse response) throws Exception{
		LOGGER.debug("---------------- [IN] getPaymentType--------------------------");	
		//Retrieve Map from session and set the Bill To in the session
		PortletSession session = request.getPortletSession();
		GenericAddress billToAddress=new GenericAddress();
		if(request.getParameter("soldTo")!=null && request.getParameter("soldTo").equalsIgnoreCase("singleAddress")){
			billToAddress=(GenericAddress)session.getAttribute("selectedHWBillToAddress",PortletSession.APPLICATION_SCOPE);
		}else{
			Map<String, GenericAddress> billToMap = (Map<String, GenericAddress>) session.getAttribute("billToAddressMap",PortletSession.APPLICATION_SCOPE);
			billToAddress = billToMap.get(request.getParameter("soldTo"));
			session.removeAttribute("selectedHWBillToAddress",PortletSession.APPLICATION_SCOPE);
			session.setAttribute("selectedHWBillToAddress", billToAddress ,PortletSession.APPLICATION_SCOPE);
		}
		
		
		/***start changes for Siebel Localization LOV***/		
		PaymentListContract paymentContract = ContractFactory.getPaymentListContract(request, true);
		Map<String,String> accDetails =(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		paymentContract.setContractNumber(accDetails.get("contractNumber"));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		paymentContract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(paymentContract, LOGGER);
		long timeBeforeCall=System.currentTimeMillis();
		PaymentTypeListResult result = globalService.retrievePaymentTypeList(paymentContract);
		long timeAfterCall=System.currentTimeMillis();
		LOGGER.info("start printing lex logger");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVEPAYMENTTYPELIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,paymentContract);
		
		LOGGER.info("end printing lex loggger");
		String paymentTypeAccessories=getPaymentListAccessories(request);
		Map<String, String> requestTypeLOVMap=new TreeMap<String, String>();
		ArrayList<String> paymentType = result.getPaymentType();
		LOGGER.debug("payment type ---------->>"+ paymentType.contains("Ship and Bill"));
		if(!paymentType.contains("Ship and Bill") && !"".equalsIgnoreCase(paymentTypeAccessories)){
			paymentType.add(paymentTypeAccessories);
		}
		LOGGER.debug("payment Types "+paymentType.toString());
		List<String> paymentTypeValueList=new ArrayList<String>();
		List<String> paymentTypeKeyList=new ArrayList<String>();
		PrintWriter out = response.getWriter();
		try {
			requestTypeLOVMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.HARDWARE_PAYMENT_TYPE.getValue(), request.getLocale());
			
		} catch (LGSDBException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Database exception occured");
			e.getMessage();
		}
		ListIterator it = paymentType.listIterator();
		while (it.hasNext()) {
			Object value= it.next();
			Iterator itmap = requestTypeLOVMap.entrySet().iterator();
			while (itmap.hasNext()) {
				Map.Entry valuemap = (Map.Entry)itmap.next();
				if(value.toString().equalsIgnoreCase(valuemap.getKey().toString())){
					paymentTypeValueList.add(valuemap.getValue().toString());
					paymentTypeKeyList.add(valuemap.getKey().toString());
				}
			}
		}
	
		/***end changes for Siebel Localization LOV***/
		StringBuffer responseBody=new StringBuffer();
		
		response.setContentType("text/html");
		if(paymentTypeKeyList!=null && paymentTypeKeyList.size() == 1 && paymentTypeValueList!=null && paymentTypeValueList.size() == 1){			
			Map<String,String> hardwareDetailsValues=new HashMap<String,String>();
			hardwareDetailsValues.put("paymentType", paymentTypeKeyList.get(0));
			hardwareDetailsValues.put("paymentTypeText", paymentTypeValueList.get(0));			
			hardwareDetailsValues.put("soldToNumber", billToAddress.getSoldToNumber());
			hardwareDetailsValues.put("billToNumber", billToAddress.getBillToNumber());
			session.setAttribute(ChangeMgmtConstant.HWCURRDETAILS, hardwareDetailsValues ,PortletSession.APPLICATION_SCOPE);
			Map<String,String> catDetails =(Map<String,String>)session.getAttribute(ChangeMgmtConstant.HWCURRDETAILS,PortletSession.APPLICATION_SCOPE);
			String showPriceFlag = accDetails.get("showPrice");
			String creditFlag = accDetails.get("creditCardFlag");
			String poFlag = accDetails.get("poFlag");
			String selectedPaymentType = catDetails.get("paymentType");
			PaymentUtil.setFinalCatalogFlags(session,showPriceFlag, creditFlag, poFlag, selectedPaymentType,"Hardware");
			responseBody.append("\"paymentTypeHtml\":\""+paymentTypeValueList.get(0)+"\"");
			responseBody.append(","+"\"singlePaymentType\":\"true\"");					
		}else{						
			responseBody.append("\"paymentTypeHtml\":\""+getXmlContentForPaymentType(paymentTypeKeyList,paymentTypeValueList, request.getLocale())+"\"");
			responseBody.append(","+"\"singlePaymentType\":\"false\"");
		}
		responseBody.insert(0, "{");
		responseBody.insert(responseBody.length(), "}");
		LOGGER.debug("response body finally is " + responseBody.toString());
		out.print(responseBody.toString());
		out.flush();
		out.close();
	}
	
	/**
	 * Added for MPS 2.1 Wave 1 Consumables for getting product type list
	 * This method is used to retrieve the ProductType
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */	
	@ResourceMapping("getProductType")
	public void getProductType(ResourceRequest request, ResourceResponse response) throws Exception{
		LOGGER.debug("---------------- [IN] getProductType --------------------");
		PortletSession session = request.getPortletSession();		
		CatalogListContract hardwareListContract = new CatalogListContract();	
		String agreementId = (String) session.getAttribute("agreementId");
		hardwareListContract.setAgreementId(agreementId);
		String soldToNumber = "";
		String paymentType = "";
		Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute("hardwareCurrentDetails",PortletSession.APPLICATION_SCOPE);
		Map<String,String> accDetails =(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(hardwareDetails != null){
			soldToNumber = hardwareDetails.get("soldToNumber");
			paymentType = hardwareDetails.get("paymentType");
		}
		hardwareListContract.setPaymentType(paymentType);
		hardwareListContract.setContractNumber(accDetails.get("contractNumber"));
		hardwareListContract.setHardwareFlag(true);
		hardwareListContract.setEffectiveDate(new Date());
		ObjectDebugUtil.printObjectContent(hardwareListContract, LOGGER);
		LOGGER.debug("Contract value portal is sending is agreementId "+agreementId+" paymentType "+paymentType+" SoldToNumber "+soldToNumber);
		
		
		CatalogListResult result =  orderSuppliesCatalogService.retrieveCatalogFieldList(hardwareListContract);
		List <String> productTypeList = new ArrayList<String>();
		if(result.getLovList()!=null){
			LOGGER.debug("total no of list received from siebel is "+result.getLovList().size());
			for(int i=0;i<result.getLovList().size();i++){
				LOGGER.debug("values received from siebel is "+result.getLovList().get(i).getValue());
				if(result.getLovList().get(i).getValue()!=null){
					productTypeList.add(result.getLovList().get(i).getValue());
				}
			}
		}
		Collections.sort(productTypeList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(getXmlContent(productTypeList,"productType", request.getLocale()));
		out.flush();
		out.close();
	}
	
	/**
	 * THis method is used to convert the list of Payment Type to the xml
	 * @param paymentTypeKeyList 
	 * @param local  
	 * @param paymentTypeValueList 
	 * @return dropdownValue 
	 */
	private String getXmlContentForPaymentType(List<String> paymentTypeKeyList,List<String> paymentTypeValueList, Locale local) {
		StringBuilder sb = new StringBuilder();
		String noPaymentType = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"paymentType.notAvl", local);
		if(!(paymentTypeKeyList.isEmpty())){
			sb.append("<select id=\'paymentType\' name=\'paymentType\'>");
			sb.append("<option value=\'\'>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"requestInfo.option.select", local)+"</option>");
			for(int i=0;i<paymentTypeKeyList.size();i++) {
				sb.append("<option value=\'"+ 	XMLEncodeUtil.escapeXML(paymentTypeKeyList.get(i).toString().replaceAll(" ", "%20"))  + "\'>");
				sb.append(paymentTypeValueList.get(i).toString());
				sb.append("</option>");
			}
			sb.append("</select>");
		}else{
				sb.append("<select id=\'paymentType\'><option value=\'\'>"+noPaymentType+"</option></select>");
		}
		LOGGER.debug("xml content is "+sb.toString());
		return sb.toString();
	}
	
	
	/**
	 * Set selected Hardware values like Payment Type, SoldTo Number and Bill To Number to session
	 * @param resourceRequest 
	 * @param resourceResponse 
	 */
	@ResourceMapping(value="setHardwareValuesToSession")
	public void setHardwareValuesToSession(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		PortletSession portletSession = resourceRequest.getPortletSession();
		Enumeration<String> paramNames=resourceRequest.getParameterNames();
		Map<String,String> hardwareDetailsValues=new HashMap<String,String>();
		while(paramNames.hasMoreElements()){
			String paramName=paramNames.nextElement();
			LOGGER.debug("session:"+paramName+ " " +resourceRequest.getParameter(paramName) );
			hardwareDetailsValues.put(paramName, resourceRequest.getParameter(paramName));
		}
		GenericAddress selBillTo = (GenericAddress)portletSession.getAttribute("selectedHWBillToAddress", PortletSession.APPLICATION_SCOPE);
		hardwareDetailsValues.put("soldToNumber", selBillTo.getSoldToNumber());
		hardwareDetailsValues.put("billToNumber", selBillTo.getBillToNumber());
		portletSession.setAttribute(ChangeMgmtConstant.HWCURRDETAILS, hardwareDetailsValues ,PortletSession.APPLICATION_SCOPE);
		Map<String,String> accDetails =(Map<String,String>)portletSession.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		Map<String,String> catDetails =(Map<String,String>)portletSession.getAttribute(ChangeMgmtConstant.HWCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		String showPriceFlag = accDetails.get("showPrice");
		String creditFlag = accDetails.get("creditCardFlag");
		String poFlag = accDetails.get("poFlag");
		String selectedPaymentType = catDetails.get("paymentType");
		PaymentUtil.setFinalCatalogFlags(portletSession,showPriceFlag, creditFlag, poFlag, selectedPaymentType,"Hardware");
		try{
			PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setContentType("text/plain");
			out.print("success");
			out.flush();
			out.close();
		}catch(IOException ie){
			LOGGER.error("IOException occured");
			ie.getMessage();
		}
	}
	
	/**
	 * This method is used to remove Hardware specific values from session along with all the Hardware specific Maps
	 * @param resourceRequest 
	 * @param resourceResponse 
	 */
	@ResourceMapping(value="removeHardwareValuesFromSession")
	public void removeHardwareValuesFromSession(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		
		PortletSession session = resourceRequest.getPortletSession();
		Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute(ChangeMgmtConstant.HWCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		GenericAddress selBillTo=(GenericAddress)session.getAttribute("selectedHWBillToAddress",PortletSession.APPLICATION_SCOPE);
		Map<String,Boolean> hardwareFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.HWFINALFLAGS,PortletSession.APPLICATION_SCOPE);
		if(hardwareDetails != null){
			session.removeAttribute(ChangeMgmtConstant.HWCURRDETAILS,PortletSession.APPLICATION_SCOPE);			
		}
		if(hardwareFinalFlags != null){
			session.removeAttribute(ChangeMgmtConstant.HWFINALFLAGS,PortletSession.APPLICATION_SCOPE);
		}
		List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
		if(hardwareOrderListToSession!=null){
			session.removeAttribute("hardwareOrderListToSession");
		}
		if(selBillTo != null){
			session.removeAttribute("selectedHWBillToAddress", PortletSession.APPLICATION_SCOPE);
		}
		LOGGER.debug("removed hardware details from session");
		
		try{
			PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setContentType("text/plain");
			out.print("success");
			out.flush();
			out.close();
		}catch(IOException ie){
			LOGGER.error("IOException occured");
			ie.getMessage();
		}
	}
	
	/**.
	 * This renders the hardware details page with draft SR data populated.
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return pageName 
	 * @throws LGSBusinessException  
	 */
	private String redirectHardwareOrderDetailsPage( Model model,RenderRequest request, RenderResponse response) throws LGSBusinessException{
		LOGGER.debug("--------------------- [IN] redirectCatalogOrderDetailsPage method-------------------");
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String userSegment = PortalSessionUtil.getUserType(request.getPortletSession());
		/**
		 * Changes for 9993 MPS 2.1
		 * */
		AccountContact accContact=commonController.getContactInformation(request, response);
		/**
		 * Ends Changes for 9993 MPS 2.1
		 * */
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		RequestContract contract = new RequestContract();
		contract.setServiceRequestNumber(httpReq.getParameter("requestNumber"));
		contract.setVisibilityRole(userSegment);
		contract.setSessionHandle(crmSessionHandle);
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		LEXLOGGER.info("end printing lex logger");
		RequestResult requestResult = new RequestResult();
		try {
			Long timeBeforeCall = System.currentTimeMillis();
			requestResult = requestTypeService.retrieveSupplyRequestDetail(contract);
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVESUPPLYREQUESTDETAIL, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			LEXLOGGER.info("end printing lex loggger");
		} catch (Exception e) {
			LOGGER.debug("Exception occured in retrieveSupplyRequestDetail service call ...");
			e.getMessage();
		}
		HardwareDetailPageForm hardwareDetailPageForm = new HardwareDetailPageForm();
		
		PortletSession session = request.getPortletSession();
		session.setAttribute("draftSrNumber", httpReq.getParameter("requestNumber"));
		ServiceRequest serviceRequest = requestResult.getServiceRequest();
		if(serviceRequest== null){
			throw new LGSBusinessException("This draft request details is not retrieved due to internal system error.\n Please contact administrator.");
		}
		LOGGER.debug("sreviceRequest===>"+serviceRequest+" sreviceRequest.AccountID===>"+serviceRequest.getAccountId()+" sreviceRequest.AccountName===>"+serviceRequest.getAccountName()+" Requestor contact id is "+serviceRequest.getRequestor().getContactId()+" Primary contact id is "+serviceRequest.getPrimaryContact().getContactId()+" Address id is "+serviceRequest.getServiceAddress().getAddressId()+" Billing Model is "+serviceRequest.getBillingModel());		
		
		Map<String,String> accDetails = new HashMap<String, String>();
		if(session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE)!=null ){
			accDetails =(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		}else{
			String agreementId="";
			List<Part> srPartList = serviceRequest.getParts();
			if(srPartList!=null && !srPartList.isEmpty() && srPartList.get(0)!=null){
				agreementId = srPartList.get(0).getAgreementId();
			}
			SiebelAccountListContract siebelAccountListContract = ContractFactory
			  .getSiebelAccountListContract(request);
			siebelAccountListContract.setSessionHandle(crmSessionHandle);
			siebelAccountListContract.setAgreementFlag(true);
			siebelAccountListContract.setHardwareFlag(true);
			SiebelAccountListResult siebelAccountListResult=null;
			try{
				LEXLOGGER.info("start printing lex logger");
				ObjectDebugUtil.printObjectContent(siebelAccountListContract, LEXLOGGER);
				LEXLOGGER.info("end printing lex logger");
				long timeBeforeCall=System.currentTimeMillis();				
				siebelAccountListResult = globalService.retrieveCatalogAgreementList(siebelAccountListContract);
				long timeAfterCall=System.currentTimeMillis();
				LEXLOGGER.info("start printing lex logger");
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVECATALOGAGREEMENTLIST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,siebelAccountListContract);
				
				LEXLOGGER.info("end printing lex loggger");
			}catch(Exception e){
				LOGGER.info("Error occured while retrieving Account List when request from Hardware Draft Request");
				e.getMessage();
			}
			finally{
				globalService.releaseSessionHandle(crmSessionHandle);
			}
			List<Account> accountList = siebelAccountListResult.getAccountList();
			for(Account acc:accountList ){
				if(acc.getAgreementId().equalsIgnoreCase(agreementId)){
					accDetails.put("accountId", acc.getAccountId());
					accDetails.put("vendorAccountID", acc.getVendorAccountId());
					if(acc.isCreateClaimFlag()){
						accDetails.put("isCreateClaimFlag","true");
					}else{
						accDetails.put("isCreateClaimFlag","false");
					}
					if(acc.isViewOrderFlag()){
						accDetails.put("isViewOrderFlag", "true");
					}else{
						accDetails.put("isViewOrderFlag", "false");
					}
					String priceFlag = acc.getShowPriceFlag();
					String poFlag = acc.isPoNumberFlag() ? "true":"false";
					String creditFlag = acc.isCreditNumberFlag() ? "true":"false";
					String accountSplitterFlag = acc.isAccountSplitterFlag() ? "true":"false";
					String contractNumber = acc.getContractNumber() != null ?acc.getContractNumber():"";
					accDetails.put("accountName", acc.getAccountName());
					accDetails.put("agreementId", acc.getAgreementId());
					accDetails.put("agreementName", acc.getAgreementName());
					accDetails.put("country", acc.getAddress().getCountry());
					accDetails.put("showPrice", priceFlag);
					accDetails.put("poFlag", poFlag);
					accDetails.put("creditCardFlag", creditFlag);
					accDetails.put("contractNumber", contractNumber);
					String soldToList = "";
					if(acc.getSoldToNumbers()!=null){
						List<String> soldTo = acc.getSoldToNumbers();
						StringBuilder idArrayBuilder = new StringBuilder();
						for( String id : soldTo ) {
						  if( idArrayBuilder.length() > 0 ) {
						    idArrayBuilder.append(",");
						  }
			
						  idArrayBuilder.append(id);
						}
						soldToList = idArrayBuilder.toString();
						if(soldToList.endsWith(",")){
							soldToList = soldToList.substring(0, soldToList.length()-1);
						}
					}else{
						soldToList = "";
					}
					accDetails.put("splitterFlag", accountSplitterFlag);
					accDetails.put("soldToList", soldToList);
					session.setAttribute("accountCurrentDetails",accDetails,PortletSession.APPLICATION_SCOPE);
					PaymentUtil.setFinalCatalogFlags(session,priceFlag, creditFlag, poFlag, serviceRequest.getBillingModel(),"Hardware");
				}
			}
		}
		if(accDetails != null && !accDetails.isEmpty()){
			LOGGER.debug("Account id ::::   "+accDetails.get("accountId")+" Account Name ::::  "+accDetails.get("accountName")+" Account Organization ::::  "+accDetails.get("accountOrganization")+" Agreement Id::::   "+accDetails.get("agreementId")+" Agreement Name ::::   "+accDetails.get("agreementName")+" Country :::: "+accDetails.get("country"));	
		}
		
		LOGGER.debug("Secondary Contact--> "+serviceRequest.getSecondaryContact());
		if(serviceRequest.getSecondaryContact()!= null){
			LOGGER.debug("Secondary Contact Name-->"+serviceRequest.getSecondaryContact().getFirstName());
		}
		String customerReferenceNumber = serviceRequest.getCustomerReferenceNumber();
		LOGGER.debug("Setting customerReferenceId with the valu of customerReferenceNumber["+customerReferenceNumber+"]");
		serviceRequest.setCustomerReferenceId(customerReferenceNumber);
		if(serviceRequest.getPoNumber()!=null){
			hardwareDetailPageForm.setPoNumber(serviceRequest.getPoNumber());
		}		
		
		List<OrderPart> bundleList = new ArrayList<OrderPart>();
		List<OrderPart> accessoriesList = new ArrayList<OrderPart>();
		List<OrderPart> hardwareList = new ArrayList<OrderPart>();
		List<Part> partList = serviceRequest.getParts();
		int priceCounter = 0;
		LOGGER.debug("partList===>"+partList);
		if (partList!=null && !partList.isEmpty()){
			
				for(Part part : partList){
					OrderPart orderPart = new OrderPart();
					orderPart.setPartNumber(part.getPartNumber());
					orderPart.setPartDesc(part.getDescription());
					orderPart.setPartType(part.getAssetUsageType());
					orderPart.setModel(part.getModel());
					orderPart.setOrderQuantity(part.getOrderQuantity());
					orderPart.setHardwareType(part.getCatalogType());
					orderPart.setCatalogId(part.getCatalogId());
					orderPart.setSupplyId(part.getSupplyId());
					orderPart.setSalesOrg(part.getSalesOrg());
					if(part.getPrice()!=null){
						orderPart.setUnitPrice(part.getPrice().toString());
						priceCounter++;
					}
					if(part.getCurrency()!=null && part.getCurrency()!=""){
						orderPart.setCurrency(part.getCurrency());
					}					
					orderPart.setPartQuantity(part.getOrderQuantity());
					String partImage = "";
					if(part.getPartNumber()!=null){
						partImage = URLImageUtil.getPartImage(part.getPartNumber());
					}
					orderPart.setPartImg(partImage);
					LOGGER.debug("Hardware Type -->"+part.getCatalogType()+" Part Type "+part.getConsumableType()+" Material Line "+part.getMaterialLine()+" Parent Line Id "+part.getBundleParentLineId());
					if(part.getCatalogType()!=null){
						if(part.getCatalogType().equalsIgnoreCase("Hardware Bundles")){
							if(part.getBundleParentLineId()==null || part.getBundleParentLineId().equals("")){
								List<BundlePart> bundlePartList = new ArrayList<BundlePart>();
								for(Part childPart : partList){
									if(part.getCatalogId()!=null && childPart.getBundleParentLineId()!=null && part.getCatalogId().equalsIgnoreCase(childPart.getBundleParentLineId())){
										BundlePart bundlePart = new BundlePart();
										bundlePart.setCatalogId(childPart.getCatalogId());
										bundlePart.setDescription(childPart.getDescription());
										bundlePart.setPartNumber(childPart.getPartNumber());
										int quantity =Integer.parseInt(childPart.getOrderQuantity()) / Integer.parseInt(part.getOrderQuantity());
										bundlePart.setQty(new Integer(quantity).toString());
										bundlePart.setSupplyId(childPart.getSupplyId());
										bundlePartList.add(bundlePart);
									}
								}
								orderPart.setBundlePartList(bundlePartList);
								bundleList.add(orderPart);
								hardwareList.add(orderPart);
							}							
						}else if(part.getCatalogType().equalsIgnoreCase("Supply Components")){
							if(part.getMaterialLine()!=null && !part.getMaterialLine().equalsIgnoreCase("Supplies")){
								accessoriesList.add(orderPart);
								hardwareList.add(orderPart);
							}							
						}
					}
					
				}
				if(bundleList!=null && bundleList.size()>0){
					hardwareDetailPageForm.setBundleList(bundleList);
				}
				if(accessoriesList!=null && accessoriesList.size()>0){
					hardwareDetailPageForm.setAccessoriesList(accessoriesList);
				}
								
		}
		session.setAttribute("hardwareOrderListToSession", hardwareList);
		
		hardwareDetailPageForm.setServiceRequest(serviceRequest);
		GenericAddress shipToAddress = serviceRequest.getServiceAddress();		
		hardwareDetailPageForm.setShipToAddress(shipToAddress);		
		hardwareDetailPageForm.setSpecialInstruction(serviceRequest.getSpecialInstructions());		
		hardwareDetailPageForm.setDefaultSpecialInstruction(serviceRequest.getDefaultSpecialInstructions());		
		hardwareDetailPageForm.setAttachmentDescription(serviceRequest.getNotes());
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
				LOGGER.debug("Attachment name : "+attachment.getAttachmentName()+" Attachment ActivityId : "+attachment.getActivityId()+" Attachment Extension : "+attachment.getExtension()+" Attachment Size : "+attachment.getSize()+" Attachment Status : "+attachment.getStatus()+" Attachment Visibility : "+attachment.getVisibility()+" Attachment CompletedOn : "+attachment.getCompletedOn());
				modifiedAttachment.setAttachmentName(attachment.getAttachmentName());
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
				LOGGER.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
				modifiedAttachment.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
				//start doing the manipulation for display name
				String attachName = attachment.getAttachmentName();
				String fileNameWithTimestamp = attachName.substring(attachName.indexOf('@')+1, attachName.length());
				displayAttachment = fileNameWithTimestamp.substring(0,fileNameWithTimestamp.lastIndexOf('_'));
				displayAttachment = displayAttachment+"."+attachment.getExtension();
				LOGGER.debug("Final displayFileName is "+displayAttachment);
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
		
		hardwareDetailPageForm.setAttachmentList(modifiedAttachmentList);
		hardwareDetailPageForm.setMaxPartsToBeOrdered(getMaxPartsToBeOrdered());
		hardwareDetailPageForm.setRelatedServiceRequestedNumber(serviceRequest.getServiceRequestNumber());
		hardwareDetailPageForm.setRelatedServiceRequestedRowId(serviceRequest.getId());
		session.setAttribute("draftSrID",serviceRequest.getId());
		hardwareDetailPageForm.setListOfFileTypes(listOfFileTypes);
		hardwareDetailPageForm.setAttMaxCount(attMaxCount);
		//template flow
		hardwareDetailPageForm.setTemplateFileLOV(templateFileLOV);
		hardwareDetailPageForm.setTemplateFileCount(templateFileCount);
		hardwareDetailPageForm.setTemplateFileName(templateFileName);
		model.addAttribute("hardwareDetailPageForm", hardwareDetailPageForm);
		session.setAttribute("hardwareDetailPageFormSession", hardwareDetailPageForm);	
		model.addAttribute("attachmentFormDisplay", hardwareDetailPageForm);
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		hardwareDetailPageForm.setFileCount(modifiedAttachmentList.size());
		fileUploadForm.setFileCount(modifiedAttachmentList.size());
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID("displayAttachment");
		model.addAttribute("displayAttachment", resURL1.toString());
		LOGGER.debug("--------------------- [OUT] redirectHardwareOrderDetailsPage method-------------------");
		return "ordermanagement/hardwareOrder/hardwareDetail";
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
	 * @param templateFileCount the templateFileCount to set
	 */
	public void setTemplateFileCount(String templateFileCount) {
		this.templateFileCount = templateFileCount;
	}

	/**
	 * @return the templateFileCount
	 */
	public String getTemplateFileCount() {
		return templateFileCount;
	}

	/**
	 * @param templateFileLOV the templateFileLOV to set
	 */
	public void setTemplateFileLOV(String templateFileLOV) {
		this.templateFileLOV = templateFileLOV;
	}

	/**
	 * @return the templateFileLOV
	 */
	public String getTemplateFileLOV() {
		return templateFileLOV;
	}

	/**
	 * @param templateFileName the templateFileName to set
	 */
	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	/**
	 * @return the templateFileName
	 */
	public String getTemplateFileName() {
		return templateFileName;
	}
	
	/**
	 * 
	 * @param request 
	 * @return String 
	 */
	public String getPaymentListAccessories(ResourceRequest request){
		CatalogListContract contract = new CatalogListContract();	
		String soldToNumber = "";
		String paymentTypeAccessories = "";		
		PortletSession session = request.getPortletSession();
		String agreementId = (String) session.getAttribute("agreementId");
		contract.setAgreementId(agreementId);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		Map<String,String> hardwareDetails=(Map<String,String>)session.getAttribute("hardwareCurrentDetails",PortletSession.APPLICATION_SCOPE);
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(hardwareDetails != null){
			soldToNumber = hardwareDetails.get("soldToNumber");
			LOGGER.debug("Sold To ::::   "+hardwareDetails.get("soldToNumber"));
			LOGGER.debug("paymentType ::::   "+hardwareDetails.get("paymentType"));
		}		
		try {
			contract.setSessionHandle(crmSessionHandle);
			contract.setPaymentType("Ship and Bill");
			contract.setContractNumber(accDetails.get("contractNumber"));
			contract.setHardwareFlag(true);
			contract.setHardwareAccessoriesFlag(true);
			contract.setNewQueryIndicator(true);
			
			LEXLOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LEXLOGGER);
			LEXLOGGER.info("end printing lex logger");
			long timeBeforeCall=System.currentTimeMillis();
			CatalogListResult catalogListResult = orderSuppliesCatalogService.retrieveCatalogListWithContractNumber(contract);
			if(catalogListResult != null && catalogListResult.getAccessoriesList()!=null && catalogListResult.getAccessoriesList().size() >0){
				paymentTypeAccessories="Ship and Bill";
			}
		}catch(Exception e){
			e.getMessage();
		}
		
		return paymentTypeAccessories;
	}
}


