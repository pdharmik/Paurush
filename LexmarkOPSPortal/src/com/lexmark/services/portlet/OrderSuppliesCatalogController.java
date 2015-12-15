/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: OrderSuppliesCatalogController.java
 * Package     		: com.lexmark.services.portlet
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		2.1             Initial Version
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
import com.documentum.fc.impl.util.DateFormatUtil;
import org.apache.commons.fileupload.FileItem;
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
import com.lexmark.contract.CreateConsumableServiceRequestContract;
import com.lexmark.contract.PaymentListContract;
import com.lexmark.contract.PriceContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.TaxContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.CatalogListResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.PaymentTypeListResult;
import com.lexmark.result.PriceResult;
import com.lexmark.result.RequestResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.result.TaxResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderSuppliesCatalogService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.api.CreateConsumableRequest;
import com.lexmark.services.api.om.RetrievePriceService;
import com.lexmark.services.api.om.RetrieveTaxService;
import com.lexmark.services.api.om.RetrieveTonerPriceService;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.BaseForm;
import com.lexmark.services.form.CartViewForm;
import com.lexmark.services.form.CatalogDetailPageForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.validator.CatalogDetailPageFormValidator;
import com.lexmark.services.form.validator.FileUploadFormValidator;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PaymentUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.XMLEncodeUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * This controller class is used for catalog order section in order supplies
 * @author wipro
 * @version 2.1
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={"catalogDetailPageForm", "reviewCatalogOrderForm"})
public class OrderSuppliesCatalogController extends BaseController {
	
	/**. Instance variable of wrapper logger class **/
	private static Logger LOGGER = LogManager.getLogger(OrderSuppliesCatalogController.class);	
	private static LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(OrderSuppliesCatalogController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	
	/** holds the reference of AmindOrderSuppliesCatalogService bean **/
	@Autowired
    private OrderSuppliesCatalogService orderSuppliesCatalogService;
	
	/**. Holds the reference of CommonController bean **/
	@Autowired
	private CommonController commonController;
	
	/**. Holds AmindAttachmentService bean reference **/
	@Autowired
	private AttachmentService attachmentService;	 
    
	/**. This variable holds AmindGlobalService bean reference **/
	@Autowired
    private GlobalService  globalService;
    
	/**. Holds the reference of CreateConsumableRequestImpl bean **/
	@Autowired
	private CreateConsumableRequest createConsumableRequest;
	
	/** Holds the reference of FileUploadFormValidator bean **/
	@Autowired
    public FileUploadFormValidator fileUploadFormValidator;
	
	/** Holds the reference of CatalogDetailPageFormValidator bean **/
	@Autowired
	private CatalogDetailPageFormValidator catalogDetailPageFormValidator;
	
	/** holds AmindContractedServiceRequestService bean reference **/
	@Autowired
	private ServiceRequestService serviceRequestService;
	
	/** holds AmindRequestTypeService bean reference **/
	@Autowired
	public RequestTypeService requestTypeService;
	
	/**. Holds the reference of RetrievePriceServiceImpl bean **/
	/*Added for MPS 2.1 Wave 1 Consumables price call*/
	@Autowired 
	private RetrievePriceService retrievePriceService;
	
	@Autowired 
	private RetrieveTonerPriceService retrieveTonerPriceService;
	
	/**. Holds the reference of RetrieveTaxServiceImpl bean **/
	@Autowired 
	private RetrieveTaxService retrieveTaxService;
	/*End Add*/
	
	private String maxPartsToBeOrdered;
	private String listOfFileTypes;
	private String attMaxCount;	
	
	/**
	 * Show Part Catalog List Form from an Account.
	 * @param binder
	 * @param locale
	 */
	@InitBinder(value={"fileUploadForm","catalogDetailPageForm"})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));		
		
		if(binder.getTarget() instanceof FileUploadForm){
			
			binder.setValidator(fileUploadFormValidator);
		}else if(binder.getTarget() instanceof CatalogDetailPageForm){
			
			binder.setValidator(catalogDetailPageFormValidator);
		}
	}

	/**
	 * Show the catalog order landing page and show the list of the Product Type
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	
	@RequestMapping
	public String showCatalogOrderList( RenderRequest request, RenderResponse response,Model model) throws Exception{
		LOGGER.debug(" [IN] showCatalogOrderList");
		PortletSession session = request.getPortletSession();
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		LOGGER.debug("requestNumber--->"+httpReq.getParameter("requestNumber") + 
				"Status--->"+httpReq.getParameter("reqStatus"));
		
		if(httpReq.getParameter("requestNumber")!= null && 
				httpReq.getParameter("reqStatus")!=null &&
				httpReq.getParameter("reqStatus").equalsIgnoreCase("Draft")){
			
			return redirectCatalogOrderDetailsPage( model,  request,  response);
		}
		String relatedServiceRequestNumber = request.getParameter("relatedServiceRequestNumber");
		if(relatedServiceRequestNumber == null || "".equals(relatedServiceRequestNumber)){
			//Lets remove the request number from session	
			session.setAttribute("catalogDetailPageFormSession", null);
			session.removeAttribute("draftSrNumber");
			session.removeAttribute("draftSrID");
		}
		//Call the retrieveCatalogFieldList method with the agreement Id		
		
		String accountName;
		String pageFrom = request.getParameter("pageFrom");	
		
		/*Commented the previous implementation for L5 acount as part of MPS 2.1 Wave 1 Changes for consumables, 
		 * as currently everything will setup based on the account selection only. 
		 * The same needs to be updated for the CommonController Account Selection
		 */
		
		// added defect #7697
		
		String currURL=PortalUtil.getCurrentURL(request);
		boolean isPartnerRequest=currURL.indexOf(ChangeMgmtConstant.ISPARTNERPORTAL)!=-1;
		Map<String,String> accDetails;
		if(isPartnerRequest){
		accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetailsPartner",PortletSession.APPLICATION_SCOPE);
		}
		else
		{
		accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		}
		 
		//ends here
			
		String soldTo = "";
		String splitterFlag = "false";
		if(accDetails != null){
			accountName = accDetails.get("accountName");
			model.addAttribute("accountName",accountName);
			
			if(accDetails.get("splitterFlag")!=null){
				splitterFlag = accDetails.get("splitterFlag");
			}
		}	
		
		
		/*Added for MPS 2.1 Wave 1 Consumables for Splitter Logic*/		
		
		if(splitterFlag.equalsIgnoreCase("false")){
			CatalogListContract catalogListContract = new CatalogListContract();
			catalogListContract.setAgreementId(accDetails.get("agreementId"));
			LOGGER.debug("Agreement Id passed :: "+ catalogListContract.getAgreementId());
			catalogListContract.setEffectiveDate(new Date());
			
			long timeBeforeCall=System.currentTimeMillis();
			CatalogListResult result =  orderSuppliesCatalogService.retrieveCatalogFieldList(catalogListContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVECATALOGFIELDLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,catalogListContract);
			LOGGER.info("start printing lex logger");
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIVE CATALOG FIELD LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
			
			
			List <String> productTypeList = new ArrayList<String>();
			if(result.getLovList()!=null){
				
				for(int i=0;i<result.getLovList().size();i++){
					LOGGER.debug("values received from siebel is "+result.getLovList().get(i).getValue());
					productTypeList.add(result.getLovList().get(i).getValue());
				}
			}
			String productTypeData = getXmlContent(productTypeList,"productType",request.getLocale());
			model.addAttribute("productTypeData", productTypeData);
		}else if(splitterFlag.equalsIgnoreCase("true")){
			AddressListContract addressListContract = new AddressListContract();
			
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			addressListContract.setSessionHandle(crmSessionHandle);
			
			
			if(accDetails.get("soldToList")!=null){
				soldTo = (String) accDetails.get("soldToList");
				List<String> soldToList = Arrays.asList(soldTo.split(","));
				LOGGER.debug("Contract value portal is sending is soldToList "+soldToList.toString());
				addressListContract.setSoldToNumbers(soldToList);
			}
			ObjectDebugUtil.printObjectContent(addressListContract, LEXLOGGER);
			long timeBeforeCall=System.currentTimeMillis();
			AddressListResult result =  globalService.retrieveBillToAddressList(addressListContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEBILLTOADDRESSLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,addressListContract);
			LOGGER.info("start printing lex logger");
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIVE BILL ADDRESS LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
			
			List<GenericAddress> billToList = result.getAddressList();
			
			if(billToList!=null && billToList.size()==1){
				session.setAttribute("selectedBillToAddress", billToList.get(0) ,PortletSession.APPLICATION_SCOPE);
				model.addAttribute("singleBillToAddress",billToList.get(0));
			}else{		
				String billToAddressList = getBillToOption(billToList,request);
				
				model.addAttribute("billToAddressList", billToAddressList);
			}
			
			String productTypeData = "<select id=\"\"><option value=\"\">"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.select", request.getLocale())+"</option></select>";
			model.addAttribute("productTypeData", productTypeData);		
		}
		/*End Add*/
		
		session.removeAttribute("agreementId");
		session.setAttribute("agreementId", accDetails.get("agreementId"));
		String partTypeData = "<select id=\"\"><option value=\"\">"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.select", request.getLocale())+"</option></select>";
		model.addAttribute("partTypeData", partTypeData);
		if(!("cancelAction".equalsIgnoreCase(pageFrom))){			
			session.removeAttribute("catalogOrderListToSession");			
			session.removeAttribute("catalogCurrentDetails",PortletSession.APPLICATION_SCOPE);			
		}
		List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
		if(catalogOrderListToSession==null){
			catalogOrderListToSession = new ArrayList<OrderPart>();
		}
		if(("cancelAction".equalsIgnoreCase(pageFrom))){	
			if(catalogOrderListToSession.size() > 0){
				model.addAttribute("orderedCurrentcy", catalogOrderListToSession.get(0).getCurrency());
			}
		}
		model.addAttribute("cartQuantity", catalogOrderListToSession.size());		
		
		model.addAttribute("mdmLevelForAssetDetails", PortalSessionUtil.getMdmLevel(session));
		
		LOGGER.debug("--------------------- [OUT] showCatalogOrderList method-------------------");
		return "ordermanagement/catalogOrder/viewCatalogOrderList";
	}
	
	
	/**
	 * This renders the catalog details page with draft SR data populated.
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws LGSBusinessException
	 */
	private String redirectCatalogOrderDetailsPage( Model model,RenderRequest request, RenderResponse response) throws LGSBusinessException{
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
		List<String> exceptionList = new ArrayList<String>();
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		
		RequestResult requestResult = new RequestResult();
		try {
			long timeBeforeCall=System.currentTimeMillis();
			requestResult = requestTypeService.retrieveSupplyRequestDetail(contract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVESUPPLYREQUESTDETAIL, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			LOGGER.info("start printing lex logger");
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIVE SUPPLIES REQUEST DETAILS ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
			
		} catch (Exception e) {
			exceptionList.add(e.getMessage());
			
		}
		CatalogDetailPageForm catalogDetailPageForm = new CatalogDetailPageForm();
		
		PortletSession session = request.getPortletSession();
		session.setAttribute("draftSrNumber", httpReq.getParameter("requestNumber"));
		ServiceRequest serviceRequest = requestResult.getServiceRequest();
		if(serviceRequest== null){
			throw new LGSBusinessException("This draft request details is not retrieved due to internal system error." +
					"\n Please contact administrator.");
		}		
		
		LOGGER.debug("sreviceRequest===>"+serviceRequest + "sreviceRequest.AccountID===>"+serviceRequest.getAccountId() + 
		"sreviceRequest.AccountName===>"+serviceRequest.getAccountName() + 
		"Requestor contact id "+serviceRequest.getRequestor().getContactId() + 
		"Primary contact id "+serviceRequest.getPrimaryContact().getContactId() + 
		"Address id "+serviceRequest.getServiceAddress().getAddressId());
		
		CatalogListContract catalogListContract = new CatalogListContract();
		
		Map<String,String> accDetails = new HashMap<String, String>();
		if(session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE)!=null ){
			accDetails =(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		}else{
			SiebelAccountListContract siebelAccountListContract = ContractFactory
			  .getSiebelAccountListContract(request);
			siebelAccountListContract.setSessionHandle(crmSessionHandle);
			
			ObjectDebugUtil.printObjectContent(siebelAccountListContract, LEXLOGGER);			
			
			long timeBeforeCall=System.currentTimeMillis();
			SiebelAccountListResult siebelAccountListResult = serviceRequestService
															  .retrieveSiebelAccountList(siebelAccountListContract);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVESIEBELACCOUNTLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,siebelAccountListContract);
			LOGGER.info("start printing lex logger");
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE SIEBEL ACCOUNT LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
			
			List<Account> accountList = siebelAccountListResult.getAccountList();
			for(Account acc:accountList ){
				if(acc.getAccountId().equalsIgnoreCase(serviceRequest.getAccountId())){
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
					accDetails.put("accountName", acc.getAccountName());
					accDetails.put("agreementId", acc.getAgreementId());
					accDetails.put("agreementName", acc.getAgreementName());
					accDetails.put("country", acc.getAddress().getCountry());
					accDetails.put("splitterFlag", String.valueOf(acc.isAccountSplitterFlag()));
                    accDetails.put("requestExpedite", String.valueOf(acc.isAssetExpediteFlag()));
					session.setAttribute("accountCurrentDetails",accDetails,PortletSession.APPLICATION_SCOPE);
				}
			}
		}
		if(accDetails != null && !accDetails.isEmpty()){
			LOGGER.debug("Account id:"+accDetails.get("accountId") + "Account Name:"+accDetails.get("accountName") + 
			"Account Organization:"+accDetails.get("accountOrganization") + "Agreement Id:"+accDetails.get("agreementId") + 
			"Agreement Name:"+accDetails.get("agreementName") + "Country:"+accDetails.get("country"));
		}
		catalogListContract.setAgreementId(accDetails.get("agreementId"));		
		
		if(serviceRequest.getSecondaryContact()!= null){
			LOGGER.debug("Secondary Contact Name-->"+serviceRequest.getSecondaryContact().getFirstName());
		}
		String customerReferenceNumber = serviceRequest.getCustomerReferenceNumber();
		
		serviceRequest.setCustomerReferenceId(customerReferenceNumber);
		
		catalogDetailPageForm.setPoNumber(serviceRequest.getPoNumber());
		String dateString = "";
		if(serviceRequest.getRequestedEffectiveDate()!=null ){
			dateString = DateUtil.convertDateToGMTString(serviceRequest.getRequestedEffectiveDate());
			if(dateString.length()>10){
				dateString = dateString.substring(0,10);
			}
		}
		
		catalogDetailPageForm.setRequestedDeliveryDate(dateString);		
		
		List<OrderPart> catalogPartList = new ArrayList<OrderPart>();
		List<Part> partList = serviceRequest.getParts();		
		
		if (partList!=null && !partList.isEmpty()){
			
				for(Part part : partList){
					OrderPart orderPart = new OrderPart();
					orderPart.setPartNumber(part.getPartNumber());
					orderPart.setPartDesc(part.getDescription());
					orderPart.setPartType(part.getAssetUsageType());
					orderPart.setYield(part.getYield());
					orderPart.setModel(part.getModel());
					orderPart.setOrderQuantity(part.getOrderQuantity());
					orderPart.setCatalogId(part.getCatalogId());
					orderPart.setConsumableType(part.getConsumableType());
					orderPart.setProviderOrderNumber(part.getProviderOrderNumber());
					orderPart.setProductId(part.getProductId());
					orderPart.setCategory(part.getCategory());
					orderPart.setSupplyId(part.getSupplyId());
					orderPart.setSupplyType(part.getSupplyType());
					orderPart.setPartQuantity(part.getOrderQuantity());
					if(part.getPrice()!=null){
						orderPart.setUnitPrice(part.getPrice().toString());
						orderPart.setCurrency(part.getCurrency());
					}
					catalogPartList.add(orderPart);
				}
			catalogDetailPageForm.setCatalogPartList(catalogPartList);
		}
		session.setAttribute("catalogOrderListToSession", catalogPartList);
		catalogDetailPageForm.setServiceRequest(serviceRequest);
		GenericAddress shipToAddress = serviceRequest.getServiceAddress();
		
		catalogDetailPageForm.setShipToAddress(shipToAddress);
		
		catalogDetailPageForm.setSpecialInstruction(serviceRequest.getSpecialInstructions());
		
		catalogDetailPageForm.setDefaultSpecialInstruction(serviceRequest.getDefaultSpecialInstructions());
		
		catalogDetailPageForm.setAttachmentDescription(serviceRequest.getNotes());
		AttachmentForm attachForm = new AttachmentForm();
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		attachmentList = serviceRequest.getAttachments();
		catalogDetailPageForm.setListOfFileTypes(listOfFileTypes);
		catalogDetailPageForm.setAttMaxCount(attMaxCount);
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
		
		catalogDetailPageForm.setMaxPartsToBeOrdered(getMaxPartsToBeOrdered());
		catalogDetailPageForm.setRelatedServiceRequestedNumber(serviceRequest.getServiceRequestNumber());
		if(serviceRequest.getId() !=null){
			catalogDetailPageForm.setRelatedServiceRequestedRowId(serviceRequest.getId());
		}else{
			catalogDetailPageForm.setRelatedServiceRequestedRowId("");
		}		
		session.setAttribute("draftSrID",serviceRequest.getId());
		model.addAttribute("draftPage",true);
		model.addAttribute("catalogDetailPageForm", catalogDetailPageForm);
		session.setAttribute("catalogDetailPageFormSession", catalogDetailPageForm);
		model.addAttribute("attachmentFormDisplay", catalogDetailPageForm);
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		catalogDetailPageForm.setFileCount(modifiedAttachmentList.size());
		fileUploadForm.setFileCount(modifiedAttachmentList.size());
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID("displayAttachment");
		model.addAttribute("displayAttachment", resURL1.toString());
		LOGGER.debug("--------------------- [OUT] redirectCatalogOrderDetailsPage method-------------------");
		return "ordermanagement/catalogOrder/catalogDetail";
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
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",
				PortletSession.APPLICATION_SCOPE);
		String splitterFlag = "false";
		CatalogListContract catalogListContract = new CatalogListContract();	
		String agreementId = (String) session.getAttribute("agreementId");
		catalogListContract.setAgreementId(agreementId);
		
		if(accDetails.get("splitterFlag")!=null){
			splitterFlag = accDetails.get("splitterFlag");
		}
		
		if(splitterFlag.equalsIgnoreCase("true")){
			String soldToNumber = "";
			String paymentType = "";
			Map<String,String> catalogDetails=(Map<String,String>)session.getAttribute("catalogCurrentDetails", 
					PortletSession.APPLICATION_SCOPE);
			if(catalogDetails != null){
				soldToNumber = catalogDetails.get("soldToNumber");
				paymentType = catalogDetails.get("paymentType");
				
				LOGGER.debug("Bill To Address ::::   "+catalogDetails.get("billToAddress"));
				
			}
			catalogListContract.setPaymentType(paymentType);
			catalogListContract.setSoldToNumber(soldToNumber);
			catalogListContract.setContractNumber(accDetails.get("contractNumber"));
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle 
					(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			catalogListContract.setSessionHandle(crmSessionHandle);
			catalogListContract.setHardwareFlag(false);
			
		}else{
			LOGGER.debug("Contract value portal is sending is agreementId "+agreementId);
		}
		catalogListContract.setEffectiveDate(new Date());
		ObjectDebugUtil.printObjectContent(catalogListContract, LEXLOGGER);
		long timeBeforeCall=System.currentTimeMillis();
		CatalogListResult result =  orderSuppliesCatalogService.retrieveCatalogFieldList(catalogListContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_ORDERSUPPLIESCATALOGSERVICE, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,catalogListContract);
		LOGGER.info("start printing lex logger");
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE CATALOG FIELD LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		LOGGER.info("end printing lex loggger");
		List <String> productTypeList = new ArrayList<String>();
		if(result.getLovList()!=null){
			
			for(int i=0;i<result.getLovList().size();i++){
				
				if(result.getLovList().get(i).getValue()!=null){
					productTypeList.add(result.getLovList().get(i).getValue());
				}
			}
		}
		Collections.sort(productTypeList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(getXmlContent(productTypeList,"productType",request.getLocale()));
		out.flush();
		out.close();
	}
	
	/**
	 * This method is used to retrieve the ProductModel
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	
	@ResourceMapping("getProductModel")
	public void getProductModel(ResourceRequest request, ResourceResponse response) throws Exception{
		LOGGER.debug("---------------- [IN] getProductModel value is ---"+request.getParameter("productType"));
		PortletSession session = request.getPortletSession();
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",
				PortletSession.APPLICATION_SCOPE);
		String splitterFlag = "false";
		String productType = request.getParameter("productType");
		CatalogListContract catalogListContract = new CatalogListContract();	
		String agreementId = (String) session.getAttribute("agreementId");
		catalogListContract.setAgreementId(agreementId);
		catalogListContract.setProductType(productType);
		catalogListContract.setEffectiveDate(new Date());
		
		if(accDetails.get("splitterFlag")!=null){
			splitterFlag = accDetails.get("splitterFlag");
		}
		
		if(splitterFlag.equalsIgnoreCase("true")){
			String soldToNumber = "";
			String paymentType = "";
			Map<String,String> catalogDetails=(Map<String,String>)session.getAttribute("catalogCurrentDetails",
					PortletSession.APPLICATION_SCOPE);
			if(catalogDetails != null){
				soldToNumber = catalogDetails.get("soldToNumber");
				paymentType = catalogDetails.get("paymentType");
				
			}
			catalogListContract.setPaymentType(paymentType);
			catalogListContract.setSoldToNumber(soldToNumber);
			catalogListContract.setContractNumber(accDetails.get("contractNumber"));
			catalogListContract.setHardwareFlag(false);
			ObjectDebugUtil.printObjectContent(catalogListContract, LOGGER);
			
		}else{
			LOGGER.debug("Contract value portal is sending is agreementId "+agreementId+" productType "+productType);
		}
		ObjectDebugUtil.printObjectContent(catalogListContract, LOGGER);
		long timeBeforeCall=System.currentTimeMillis();
		CatalogListResult result =  orderSuppliesCatalogService.retrieveCatalogFieldList(catalogListContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVECATALOGFIELDLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,catalogListContract);
		LOGGER.info("start printing lex logger");
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE CATALOG FIELD LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		LOGGER.info("end printing lex loggger");
		List <String> productModelList = new ArrayList<String>();
		if(result.getLovList()!=null){
			
			for(int i=0;i<result.getLovList().size();i++){
				
				if(result.getLovList().get(i).getValue()!=null){
					productModelList.add(result.getLovList().get(i).getValue());
				}
			}
		}
		Collections.sort(productModelList);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(getXmlContent(productModelList,"productModel",request.getLocale()));
		out.flush();
		out.close();
	}
	
	/**
	 * This method is used to retrieve the PartType
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	
	@ResourceMapping("getPartType")
	public void getPartType(ResourceRequest request, ResourceResponse response) throws Exception{
		LOGGER.debug("---------------- [IN] getPartType value is --------------------"+request.getParameter("productType"));
		
		PortletSession session = request.getPortletSession();
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",
				PortletSession.APPLICATION_SCOPE);
		String splitterFlag = "false";
		String productType = request.getParameter("productType");
		String productModel = request.getParameter("productModel");
		CatalogListContract catalogListContract = new CatalogListContract();	
		String agreementId = (String) session.getAttribute("agreementId");
		catalogListContract.setAgreementId(agreementId);
		catalogListContract.setProductType(productType);
		catalogListContract.setProductModel(productModel);
		catalogListContract.setEffectiveDate(new Date());
		
		if(accDetails.get("splitterFlag")!=null){
			splitterFlag = accDetails.get("splitterFlag");
		}
		
		if(splitterFlag.equalsIgnoreCase("true")){
			String soldToNumber = "";
			String paymentType = "";
			Map<String,String> catalogDetails=(Map<String,String>)session.getAttribute("catalogCurrentDetails",
					PortletSession.APPLICATION_SCOPE);
			if(catalogDetails != null){
				soldToNumber = catalogDetails.get("soldToNumber");
				paymentType = catalogDetails.get("paymentType");
				
			}
			catalogListContract.setPaymentType(paymentType);
			catalogListContract.setSoldToNumber(soldToNumber);
			catalogListContract.setContractNumber(accDetails.get("contractNumber"));
			catalogListContract.setHardwareFlag(false);
			
		}else{
			LOGGER.debug("Contract value portal is sending is agreementId "+agreementId+" productType "+productType+
					" productModel "+productModel);
		}
		ObjectDebugUtil.printObjectContent(catalogListContract, LOGGER);
		long timeBeforeCall=System.currentTimeMillis();
		CatalogListResult result =  orderSuppliesCatalogService.retrieveCatalogFieldList(catalogListContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVECATALOGFIELDLIST_PT, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,catalogListContract);
		LOGGER.info("start printing lex logger");
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE CATALOG FIELD LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		LOGGER.info("end printing lex loggger");
		List <String> productModelList = new ArrayList<String>();
		if(result.getLovList()!=null){
			
			for(int i=0;i<result.getLovList().size();i++){
				
				if(result.getLovList().get(i).getValue()!=null){
					productModelList.add(result.getLovList().get(i).getValue());
				}
			}
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(getXmlContent(productModelList,"partType",request.getLocale()));
		out.flush();
		out.close();
	}
	/**
	 * THis method is used to convert the list of productType,productModel and partType to the xml
	 * @param productTypeList 
	 * @param type 
	 * @return String
	 */
	
	private String getXmlContent(List<String> productTypeList, String type, Locale local) {
		StringBuilder sb = new StringBuilder();
		if(productTypeList.size()!=0){
		if(type.equalsIgnoreCase("productType")){
			sb.append("<select id=\"productType\" onChange=\"getProductModel();\">");
		}else if(type.equalsIgnoreCase("productModel")){
			sb.append("<select id=\"productModel\" onChange=\"getPartType();\">");
		}else if(type.equalsIgnoreCase("partType")){
			sb.append("<select id=\"partType\" onChange=\"removeErrorMesssage();\">");
		}else if(type.equalsIgnoreCase("paymentType")){
			sb.append("<select id=\"paymentType\">");
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
				sb.append("<select id=\"productType\"><option value=\"\">"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.noProductAvailable", local)+"</option></select>");
			}else if(type.equalsIgnoreCase("productModel")){
				sb.append("<select id=\"productModel\"><option value=\"\">"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.noProductAvailable", local)+"</option></select>");
			}else if(type.equalsIgnoreCase("partType")){
				sb.append("<select id=\"partType\"><option value=\"\">"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.noProductAvailable", local)+"</option></select>");
			}else if(type.equalsIgnoreCase("partType")){
				sb.append("<select id=\"paymentType\"><option value=\"\">"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"paymentType.notAvl", local)+"</option></select>");
			}
		}
		return sb.toString();
	}
	/**
	 * This method is used to display the list of parts based on the search
	 * You can search based on either product type, model, part type OR part number
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String
	 * @throws Exception 
	 */
	
	@RequestMapping(params = "action=displayCatalogSelection")
	public String showCatalogSelectionPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		LOGGER.debug("-------------showCatalogSelectionPage started---------");
		ResourceURL url = response.createResourceURL();
		url.setResourceID("retriveCatalogPartListURL");
		model.addAttribute("retriveCatalogPartListURL", url.toString());	
		
		AccountContact accountContact = new AccountContact();
		accountContact.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		LOGGER.debug("-------------showCatalogSelectionPage ended---------");
		return "ordermanagement/catalogOrder/viewCatalogOrderList";
	}
	/**
	 * ResourceMapping for the retrieve part list
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	
	@ResourceMapping("retriveCatalogPartListURL")
	public void retriveCatalogList(ResourceRequest request, ResourceResponse response) throws Exception
	{
		LOGGER.debug("-----------------------[IN]retriveCatalogList----------------------");
		String soldToNumber = "";
		String paymentType = "";
		CatalogListContract contract = ContractFactory.getCatalogPartRequestListContract(request);		
		PortletSession session = request.getPortletSession();
		Map<String,String> catalogDetails=(Map<String,String>)session.getAttribute("catalogCurrentDetails",
				PortletSession.APPLICATION_SCOPE);
		if(catalogDetails != null){
			soldToNumber = catalogDetails.get("soldToNumber");
			paymentType = catalogDetails.get("paymentType");
			
		}
		String agreementId = (String) session.getAttribute("agreementId");
		contract.setAgreementId(agreementId);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			Map<String,String> accDetails =(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,
					PortletSession.APPLICATION_SCOPE);
			String splitterFlag = "false";
			splitterFlag = accDetails.get("splitterFlag");
			contract.setSessionHandle(crmSessionHandle);
			contract.setPartNumber(request.getParameter("partNumber"));
			contract.setPartType(request.getParameter("partType"));
			contract.setProductModel(request.getParameter("productModel"));
			contract.setProductType(request.getParameter("productType"));
			contract.setEffectiveDate(new Date());
			contract.setCatalogFlag(true);
			if(splitterFlag.equalsIgnoreCase("true")){
				contract.setPaymentType(paymentType);
				contract.setSoldToNumber(soldToNumber);
				contract.setContractNumber(accDetails.get("contractNumber"));
			}
			
			LOGGER.debug("agreement id"+agreementId + "Part number"+request.getParameter("partNumber") + "partType"+
					request.getParameter("partType") + "productModel"+request.getParameter("productModel") + "producttype"+
					request.getParameter("productType"));	
			
			Map<String, String> quantityServicesMap;
			Map<String, String> quantitySuppliesMap;
			if(null != session.getAttribute("quantityServicesMap",PortletSession.APPLICATION_SCOPE)){
				quantityServicesMap = (Map<String,String>)session.getAttribute("quantityServicesMap",PortletSession.APPLICATION_SCOPE);
				LOGGER.debug("quantityServicesMap in catalog controller = "+quantityServicesMap);
			}
			if(null != session.getAttribute("quantitySuppliesMap",PortletSession.APPLICATION_SCOPE)){
				quantitySuppliesMap = (Map<String,String>)session.getAttribute("quantitySuppliesMap",PortletSession.APPLICATION_SCOPE);
				LOGGER.debug("quantitySuppliesMap in catalog controller = "+quantitySuppliesMap);
			}
			
			ObjectDebugUtil.printObjectContent(contract,LEXLOGGER);			
			
			CatalogListResult catalogListResult = new CatalogListResult();
			if(splitterFlag.equalsIgnoreCase("true")){
				long timeBeforeCall=System.currentTimeMillis();
				catalogListResult = orderSuppliesCatalogService.retrieveCatalogListWithContractNumber(contract);
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVECATALOGLISTWITHCONTRACTNUMBER, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
				LOGGER.info("start printing lex logger");
				LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE CATALOG LIST WITH CONTRACT NO ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
				LOGGER.info("end printing lex loggger");
			}else{
				long timeBeforeCall=System.currentTimeMillis();
				catalogListResult = orderSuppliesCatalogService.retrieveCatalogList(contract);
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVECATALOGLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
				LOGGER.info("start printing lex logger");
				LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE CATALOG LIST WITH CONTRACT NO ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
				LOGGER.info("end printing lex loggger");
			}			
			
			if (catalogListResult.getPartsList()!=null){
				LOGGER.debug("No of parts returned from siebel is "+catalogListResult.getPartsList().size());
			}
			List<OrderPart> catalogPartList = catalogListResult.getPartsList();
			for(OrderPart catalogPart:catalogPartList){
				LOGGER.debug("Contract Number for part "+catalogPart.getPartNumber()+" is "+catalogPart.getContractNo());
			}
			PriceResult priceResult = null;
			String content="";
			if(catalogPartList!=null && catalogPartList.get(0)!=null){			
				
				splitterFlag = accDetails.get("splitterFlag");
				if(splitterFlag.equalsIgnoreCase("true")){
					
					Map<String,Boolean> catalogFinalFlags=(Map<String,Boolean>)session.getAttribute
							(ChangeMgmtConstant.CATFINALFLAGS,PortletSession.APPLICATION_SCOPE);
					boolean showPriceFlag = catalogFinalFlags.get("finalShowPriceFlag");
					
					if(showPriceFlag == true){						
						Map<String,String> catDetails =(Map<String,String>)session.getAttribute(ChangeMgmtConstant.CATCURRDETAILS,
								PortletSession.APPLICATION_SCOPE);
						String selectedPaymentType = catDetails.get("paymentType");
						if(selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.CONSUMABLE_PAYMENT_TYPE_PAY_LATER)){
							PriceContract tonerPriceContract = ContractFactory.getTonerPriceContract(catalogPartList,session);
							ObjectDebugUtil.printObjectContent(tonerPriceContract, LOGGER);
							long timeBeforeCall=System.currentTimeMillis();
							priceResult = retrieveTonerPriceService.retrieveTonerPriceList(tonerPriceContract);
							
							PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVETONERPRICELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,tonerPriceContract);
							LOGGER.info("start printing lex logger");
							LEXLOGGER.logTime("** MPS PERFORMANCE TESTING SAP CALL RETRIEVE TONER PRICE LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
							LOGGER.info("end printing lex loggger");
							content = getXmlOutputGenerator(request.getLocale()).catalogToDataViewXml(catalogPartList,
									catalogListResult.getTotalCount(), contract.getStartRecordNumber(), session,priceResult, paymentType,true);
						}else if(selectedPaymentType.equalsIgnoreCase(ChangeMgmtConstant.PAYMENT_TYPE_PAY_NOW)){
							String contractNo = catalogPartList.get(0).getContractNo()!= null ?catalogPartList.get(0).getContractNo():"";
							PriceContract priceContract = ContractFactory.getCatalogPriceContract(catalogPartList,session,contractNo);
							ObjectDebugUtil.printObjectContent(priceContract, LOGGER);
							long timeBeforeCall=System.currentTimeMillis();
							priceResult = retrievePriceService.retrievePriceList(priceContract);
							
							PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEPRICELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SAP,priceContract);
							LOGGER.info("start printing lex logger");
							LEXLOGGER.logTime("** MPS PERFORMANCE TESTING SAP CALL RETRIEVE PRICE LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
							LOGGER.info("end printing lex loggger");
							content = getXmlOutputGenerator(request.getLocale()).catalogToDataViewXml(catalogPartList,
									catalogListResult.getTotalCount(), contract.getStartRecordNumber(), session,priceResult, paymentType,false);
						}
					}else{
						content = getXmlOutputGenerator(request.getLocale()).catalogToDataViewXml(catalogPartList,
								catalogListResult.getTotalCount(), contract.getStartRecordNumber(), session,priceResult, paymentType,false);
					}
				}else{
					content = getXmlOutputGenerator(request.getLocale()).catalogToDataViewXml(catalogPartList,
							catalogListResult.getTotalCount(), contract.getStartRecordNumber(), session,priceResult, paymentType,false);
				}
			}
			
			PrintWriter out =  response.getWriter();
			response.setContentType("text/xml");
			out.print(content);
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
	public void addToCart(ResourceRequest request, ResourceResponse response, Model model) throws Exception
	{
		LOGGER.debug("-------------[IN]addToCart -------------");
		PortletSession session = request.getPortletSession();
		String partNumber = request.getParameter("partNumber");
		String partDesc = request.getParameter("partDesc");
		String partType = request.getParameter("partType");
		String yield = request.getParameter("yield");
		String partQty = request.getParameter("partQty");
		String catalogId = request.getParameter("catalogId");
		String consumableType = request.getParameter("consumableType");
		String lineId = request.getParameter("lineId");
		String supplyId = request.getParameter("supplyId");
		String productId = request.getParameter("productId");
		String proModel = request.getParameter("proModel");
		
		String maxQuantity=request.getParameter("maxQuantity");
		LOGGER.debug("Max Qty in Add to Cart = " + maxQuantity);
		
		String price = "";
		String currency = request.getParameter("currency");
		String salesOrg = request.getParameter("salesOrg");
		String providerOrderNumber = request.getParameter("providerOrderNumber");
		partDesc = URLDecoder.decode(partDesc, "UTF-8");
		partNumber = URLDecoder.decode(partNumber, "UTF-8");
		if(salesOrg!=null && !salesOrg.equals("")){
			salesOrg = URLDecoder.decode(salesOrg, "UTF-8");
		}
		
		List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
		if(catalogOrderListToSession==null){
			catalogOrderListToSession = new ArrayList<OrderPart>();
		}
		//Here please check if the catalogid is there the session list. If it is there in the list update that otherwise add that.
		
		if (catalogOrderListToSession!=null){			
			for(int i=0;i<catalogOrderListToSession.size();i++){
				if(catalogId.equalsIgnoreCase(catalogOrderListToSession.get(i).getCatalogId())){
					//Update is required
					catalogOrderListToSession.remove(i);
					break;
				}
			}
		}
		
		Map<String,String> catalogPriceMap= new HashMap<String,String>();
		if(session.getAttribute(ChangeMgmtConstant.CATPRICEMAP,PortletSession.APPLICATION_SCOPE)!=null){
			catalogPriceMap = (Map<String,String>)session.getAttribute(ChangeMgmtConstant.CATPRICEMAP,
					PortletSession.APPLICATION_SCOPE);
			if(catalogPriceMap.get(catalogId)!=null){
				price = catalogPriceMap.get(catalogId);
			}
		}		
		
		OrderPart orderPart = new OrderPart();
		orderPart.setPartNumber(partNumber);
		orderPart.setPartDesc(partDesc);
		orderPart.setPartType(partType);
		orderPart.setYield(yield);
		orderPart.setPartQuantity(partQty);
		orderPart.setCatalogId(catalogId);
		orderPart.setConsumableType(consumableType);
		orderPart.setSupplyId(supplyId);
		orderPart.setProductId(productId);
		orderPart.setContractLineItemId(lineId);
		orderPart.setSalesOrg(salesOrg);
		orderPart.setModel(proModel);
		orderPart.setProviderOrderNumber(providerOrderNumber);
		orderPart.setMpsQuantity(maxQuantity);
		if(price!=null && price!="")
		{
			orderPart.setUnitPrice(price);
		}else{
			orderPart.setUnitPrice(null);
		}
		orderPart.setCurrency(currency);
		catalogOrderListToSession.add(orderPart);
		model.addAttribute("catalogOrderNumber", catalogOrderListToSession.size());	
		
		session.setAttribute("catalogOrderListToSession", catalogOrderListToSession);
		
		response.setProperty("catalogOrderNumber", String.valueOf(catalogOrderListToSession.size()));
		
		printPartFromSession(session);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(String.valueOf(catalogOrderListToSession.size()));
		out.flush();
		out.close();
		LOGGER.debug("-------------[OUT]addToCart ----------------");
	}
	/**
	 * This method is used to print part from session
	 * @param session 
	 */
	private void printPartFromSession(PortletSession session){
		LOGGER.debug("------------------- [IN] Printing the part details from the session --------------------------------");
		List<OrderPart> catalogOrderListToSession = new ArrayList<OrderPart>();
		catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
		if (catalogOrderListToSession!=null){
			for(int i=0;i<catalogOrderListToSession.size();i++){				
				if(catalogOrderListToSession.get(i).getUnitPrice()!=null){					
					LOGGER.debug("Unit price is "+catalogOrderListToSession.get(i).getUnitPrice());
				}				
			}
		}
		LOGGER.debug("------------------- [OUT] Printing the part details from the session --------------------------------");
	}
	/**
	 * This method is used to show service request printer selection page
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=passingSearchList")
	public void showServiceRequestPrinterSelectionPage(ActionRequest request, ActionResponse response, Model model) 
			throws Exception{
		LOGGER.debug("------ACTION: gotoSRSelectCatalog Started----------");
		response.setRenderParameter("action", "displayCatalogSelection");
		LOGGER.debug("------ACTION: gotoSRSelectCatalog End----------");
	}
	
	/**
	 * This method is used to show the order detail page	
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	
	@RequestMapping(params = "action=showCatalogDetailPage")
	public String showCatalogDetailPage (RenderRequest request, RenderResponse response, Model model) throws Exception{
		LOGGER.debug("---------------------- [IN] showCatalogDetailPage --------------------------------");
		PortletSession session = request.getPortletSession();
		ServiceRequest serviceRequest=new ServiceRequest();
		AccountContact accContact=commonController.getContactInformation(request, response);
		serviceRequest.setRequestor(accContact);
		
		CatalogDetailPageForm catalogDetailPageForm=(CatalogDetailPageForm)session.getAttribute("catalogDetailPageFormSession");
		if(catalogDetailPageForm == null){
		catalogDetailPageForm = new CatalogDetailPageForm();
		}
		List<OrderPart> catalogOrderListToSession = new ArrayList<OrderPart>();
		catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
		//Part details added to the cart
		if (catalogOrderListToSession!=null){
			List<OrderPart> catalogPartList = new ArrayList<OrderPart>();
				for(OrderPart part : catalogOrderListToSession){
					OrderPart orderPart = new OrderPart();
					orderPart.setPartNumber(part.getPartNumber());
					orderPart.setPartDesc(part.getPartDesc());
					orderPart.setPartType(part.getPartType());
					orderPart.setYield(part.getYield());
					orderPart.setModel(part.getModel());
					orderPart.setContractLineItemId(part.getContractLineItemId());
					orderPart.setOrderQuantity(part.getPartQuantity());
					
					
					orderPart.setMpsQuantity(part.getMpsQuantity());
					LOGGER.debug("Part Qty = " + part.getPartQuantity());
					LOGGER.debug("Max Qty = " + part.getMpsQuantity());
					
					orderPart.setCatalogId(part.getCatalogId());
					orderPart.setConsumableType(part.getConsumableType());
					orderPart.setProviderOrderNumber(part.getProviderOrderNumber());
					orderPart.setSupplyId(part.getSupplyId());
					orderPart.setProductId(part.getProductId());
					if(part.getUnitPrice()!=null && part.getUnitPrice()!=""){
						orderPart.setUnitPrice(part.getUnitPrice());
					}
					if(part.getCurrency()!=null && part.getCurrency()!=""){
						orderPart.setCurrency(part.getCurrency());
					}
					
					catalogPartList.add(orderPart);
				}
			catalogDetailPageForm.setCatalogPartList(catalogPartList);
		}
		catalogDetailPageForm.setServiceRequest(serviceRequest);
		
		catalogDetailPageForm.setMaxPartsToBeOrdered(getMaxPartsToBeOrdered());
		
		//Call to get default special instruction for this account
		//Changed for MPS 2.1
		String mdmLevel = PortalSessionUtil.getMdmLevel(session);//LexmarkConstants.MDM_LEVEL_SIEBEL;
		String mdmId=PortalSessionUtil.getMdmId(session);
		Map<String,String> accDetails =(Map<String,String>)session.getAttribute("accountCurrentDetails",
				PortletSession.APPLICATION_SCOPE);
		List<String> userRoleList = PortalSessionUtil.getUserRoles(session);
		if(accDetails != null){
			LOGGER.debug("Account id:"+accDetails.get("accountId") + "Account Name:"+accDetails.get("accountName") + 
			"Account Organization:"+accDetails.get("accountOrganization") + "Agreement Id:"+accDetails.get("agreementId") + 
			"Agreement Name:"+accDetails.get("agreementName") + "Country:"+accDetails.get("country"));
		}
		
		String accountIdForAddressFlag=accDetails.get("accountId");
		LOGGER.debug("accountIdForAddressFlag"+accountIdForAddressFlag);
	
		/* Added for JAN release for Request Expedite Order*/
		//-----------------START---- 8133 -------------------------------
		//If customer Raised the request
		
			catalogDetailPageForm.setExpediteOrderAllowed(accDetails.get("requestExpedite").equals("true")?true:false);
		
		//----------------END------ 8133 ------------------------------	
		
		
		//Lets call amind to get the default special instructioncatalogOrderListToSession.get(i).getPrice()
		PortletSession portletSession = request.getPortletSession();
		SiebelAccountListContract siebelAccountListContract = new SiebelAccountListContract();
		siebelAccountListContract.setMdmId(mdmId);
		siebelAccountListContract.setMdmLevel(mdmLevel);
		
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || 
				(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			siebelAccountListContract.setVendorFlag(true);
		}else {
			siebelAccountListContract.setVendorFlag(false);
		}
		siebelAccountListContract.setNewQueryIndicator(false);
		
		LOGGER.debug("mdmid "+siebelAccountListContract.getMdmId()+" mdmlevel "+siebelAccountListContract.getMdmLevel()+
				" vendor flag "+siebelAccountListContract.isVendorFlag()+
				" new query indicator "+siebelAccountListContract.isNewQueryIndicator());
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		siebelAccountListContract.setSessionHandle(crmSessionHandle);
		LOGGER.debug("-------------retrieveSiebelAccountList started---------");			
		
		ObjectDebugUtil.printObjectContent(siebelAccountListContract, LEXLOGGER);		
		
		long timeBeforeCall=System.currentTimeMillis();
		SiebelAccountListResult siebelAccountListResult = serviceRequestService
														  .retrieveSiebelAccountList(siebelAccountListContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVESIEBELACCOUNTLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,siebelAccountListContract);
		LOGGER.info("start printing lex logger");
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE SIEBEL ACCOUNT LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		LOGGER.info("end printing lex loggger");
		
		LOGGER.debug("-------------retrieveSiebelAccountList ended---------");
		if(siebelAccountListResult.getAccountList()!=null){
			LOGGER.debug("No of accounts portal is receiving"+siebelAccountListResult.getAccountList().size());
			/**
			 * Added for MPS 2.1 
			 * */
			List<Account> accounts=siebelAccountListResult.getAccountList();
				
			for(Account account:accounts){
				if(StringUtils.isNotBlank(account.getAccountId())&& accountIdForAddressFlag.equalsIgnoreCase(account.getAccountId())){
					
					String defaultSpecialInstruction = account.getSpecialHandlingInstruction();				
					catalogDetailPageForm.setAddressFlag(account.isAddressFlag());
					catalogDetailPageForm.setDefaultSpecialInstruction(defaultSpecialInstruction);
					break;
				}
			}
			
		}
		catalogDetailPageForm.setListOfFileTypes(listOfFileTypes);
		catalogDetailPageForm.setAttMaxCount(attMaxCount);
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID("displayAttachment");
		model.addAttribute("displayAttachment", resURL1.toString());
		//Setting the related service request number for draft SR
		String draftSrNumber = (String)session.getAttribute("draftSrNumber");
		String draftSrID=(String)session.getAttribute("draftSrID");
		if(!("".equals(draftSrNumber)||draftSrNumber==null)){
			
			catalogDetailPageForm.setRelatedServiceRequestedNumber(draftSrNumber);
			if(draftSrID !=null){
				catalogDetailPageForm.setRelatedServiceRequestedRowId(draftSrID);
			}else{
				catalogDetailPageForm.setRelatedServiceRequestedRowId("");
			}
		}else{
			
			session.removeAttribute("attachmentList");
		}
		//If it is draft SR, need to add attachment to the page
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
		model.addAttribute("catalogDetailPageForm", catalogDetailPageForm);
		model.addAttribute("attachmentFormDisplay", catalogDetailPageForm);
		//Initializes the form for file upload
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		LOGGER.debug("---------------------- [OUT] showCatalogDetailPage --------------------------------");
		return "ordermanagement/catalogOrder/catalogDetail";
	}
	
	/**
	 * This method is used to show the review order page
	 * @param request
	 * @param response
	 * @param catalogDetailPageForm
	 * @param bindingResult
	 * @param model
	 * @return String
	 * @throws Exception
	 */
	@RenderMapping(params="action=submitCatalogOrder")
	public String submitCatalogOrder(
			RenderRequest request, 
			RenderResponse response, 
			@ModelAttribute("catalogDetailPageForm") @Valid CatalogDetailPageForm catalogDetailPageForm,			
			BindingResult bindingResult,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,
			Model model) throws Exception{
		
		LOGGER.debug("---------------------- [In] submitCatalogOrder for confirm order page ----------------------");
		String pageSubmitType = catalogDetailPageForm.getPageSubmitType();
		String returnPage = "";
		String exError = null;
		String creditCurrency="USD";
		PortletSession session = request.getPortletSession();
		String attachmentDescription= request.getParameter("attachmentDescriptionID");
		LOGGER.debug("attachment description-->> "+attachmentDescription);
		commonController.getContactInformation(request, response);
		if("confirmOrderRequest".equalsIgnoreCase(pageSubmitType)){
			CatalogDetailPageForm reviewCatalogOrderForm = new CatalogDetailPageForm();
				if(bindingResult.hasErrors()){
					
					for(int j=0;j<bindingResult.getAllErrors().size();j++){
						LOGGER.debug("Errors are in controller "+bindingResult.getAllErrors().get(j).getCode().toString());
					}
					//Display part list after validation
					String paramPrefix = null;
					String orderQuantity = null;
					String catalogId = "";
					List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute
							("catalogOrderListToSession");
					Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",
							PortletSession.APPLICATION_SCOPE);
					String splitterFlag = "false";
					if(accDetails.get("splitterFlag")!=null){
						splitterFlag = accDetails.get("splitterFlag");
					}
					List<OrderPart> modifiedCatalogPartList = new ArrayList<OrderPart>();
					List<OrderPart> modifiedCatalogPartListForCatalogForm = new ArrayList<OrderPart>();
					if(catalogDetailPageForm.getCatalogPartList()!=null){
						LOGGER.debug("The size of the catalog part list in the form is "+
					catalogDetailPageForm.getCatalogPartList().size());
					}
					if(catalogOrderListToSession!=null){
						LOGGER.debug("The size of the catalogOrderListToSession is "+catalogOrderListToSession.size());
					}
					for (int i=0;i<catalogDetailPageForm.getCatalogPartList().size();i++){
						
						paramPrefix = "catalogPartList[" + i + "].";
						orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
						catalogId = request.getParameter("catalogId["+i+"]");
						
						if(catalogId!=null){
							for(int j=0;j<catalogOrderListToSession.size();j++){
								if(catalogId.equalsIgnoreCase(catalogOrderListToSession.get(j).getCatalogId())){
									if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals
											(Integer.toString(0))) {
										if("".equals(orderQuantity)){LOGGER.debug("Order quanityt is blank");}
										if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
										if(orderQuantity!=null){
											OrderPart orderPart = new OrderPart();
											orderPart.setPartNumber(catalogOrderListToSession.get(j).getPartNumber());
											orderPart.setPartDesc(catalogOrderListToSession.get(j).getPartDesc());
											orderPart.setConsumableType(catalogOrderListToSession.get(j).getConsumableType());
											orderPart.setYield(catalogOrderListToSession.get(j).getYield());											
											orderPart.setModel(catalogOrderListToSession.get(j).getModel());
											orderPart.setPartType(catalogOrderListToSession.get(j).getPartType());
											orderPart.setProviderOrderNumber(catalogOrderListToSession.get(j).getProviderOrderNumber());
											orderPart.setOrderQuantity(orderQuantity);
											orderPart.setCatalogId(catalogId);
											orderPart.setSupplyId(catalogOrderListToSession.get(j).getSupplyId());
											orderPart.setProductId(catalogOrderListToSession.get(j).getProductId());
											
											orderPart.setMpsQuantity(catalogOrderListToSession.get(j).getMpsQuantity());
											
											if(splitterFlag.equalsIgnoreCase("true")){
												orderPart.setContractLineItemId(catalogOrderListToSession.get(j).getContractLineItemId());
												orderPart.setSalesOrg(catalogOrderListToSession.get(j).getSalesOrg());
												if(catalogOrderListToSession.get(j).getUnitPrice()!=null){
													orderPart.setUnitPrice(catalogOrderListToSession.get(j).getUnitPrice());
												}
												if(catalogOrderListToSession.get(j).getCurrency()!=null){
													orderPart.setCurrency(catalogOrderListToSession.get(j).getCurrency());
													creditCurrency = catalogOrderListToSession.get(j).getCurrency();
												}
											}
											modifiedCatalogPartListForCatalogForm.add(orderPart);
										}
									}else{
										OrderPart orderPart = new OrderPart();
										orderPart.setPartNumber(catalogOrderListToSession.get(j).getPartNumber());
										orderPart.setPartDesc(catalogOrderListToSession.get(j).getPartDesc());
										orderPart.setConsumableType(catalogOrderListToSession.get(j).getConsumableType());
										orderPart.setPartType(catalogOrderListToSession.get(j).getPartType());
										orderPart.setOrderQuantity(orderQuantity);
										orderPart.setCatalogId(catalogOrderListToSession.get(j).getCatalogId());
										orderPart.setYield(catalogOrderListToSession.get(j).getYield());
										orderPart.setModel(catalogOrderListToSession.get(j).getModel());
										orderPart.setSupplyId(catalogOrderListToSession.get(j).getSupplyId());
										orderPart.setProviderOrderNumber(catalogOrderListToSession.get(j).getProviderOrderNumber());
										orderPart.setProductId(catalogOrderListToSession.get(j).getProductId());
										
										orderPart.setMpsQuantity(catalogOrderListToSession.get(j).getMpsQuantity());
										
										if(splitterFlag.equalsIgnoreCase("true")){
											orderPart.setContractLineItemId(catalogOrderListToSession.get(j).getContractLineItemId());
											orderPart.setSalesOrg(catalogOrderListToSession.get(j).getSalesOrg());
											if(catalogOrderListToSession.get(j).getUnitPrice()!=null){
												orderPart.setUnitPrice(catalogOrderListToSession.get(j).getUnitPrice());
											}
											if(catalogOrderListToSession.get(j).getCurrency()!=null){
												orderPart.setCurrency(catalogOrderListToSession.get(j).getCurrency());
												creditCurrency = catalogOrderListToSession.get(j).getCurrency();
											}
										}
										modifiedCatalogPartList.add(orderPart);
										LOGGER.debug("part added for catalog id "+orderPart.getCatalogId()+
												" and quantity "+orderPart.getOrderQuantity());
										modifiedCatalogPartListForCatalogForm.add(orderPart);
									}
									LOGGER.debug("After setting order quantity for "+i+" number part is "+
									catalogOrderListToSession.get(j).getOrderQuantity());
									break;
								}
							}
						}
					}//outer for loop
					catalogDetailPageForm.setCatalogPartList(modifiedCatalogPartListForCatalogForm);
					reviewCatalogOrderForm.setCatalogPartList(modifiedCatalogPartList);
					List <Attachment> attachmentList = new ArrayList<Attachment>();
					attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
					catalogDetailPageForm.setAttachmentList(attachmentList);
					attachForm.setAttachmentList(attachmentList);
					attachForm.setListOfFileTypes(listOfFileTypes);
					attachForm.setAttMaxCount(attMaxCount);
					catalogDetailPageForm.setAttachmentDescription(attachmentDescription);
					attachForm.setAttachmentDescription(attachmentDescription);
					FileUploadForm fileUploadForm = new FileUploadForm();
					if(attachForm.getAttachmentList() !=null){
						fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
						}
					//End display part list after validation
					model.addAttribute("catalogDetailPageForm", catalogDetailPageForm);
					model.addAttribute("reviewCatalogOrderForm", reviewCatalogOrderForm);
					model.addAttribute("creditCurrency",creditCurrency);
					model.addAttribute("fileUploadForm", fileUploadForm);
					
					returnPage = "ordermanagement/catalogOrder/catalogDetail";
				} else {
				try {				
				String paramPrefix = null;
				String orderQuantity = null;
				String catalogId = "";
				List<OrderPart> modifiedCatalogPartList = new ArrayList<OrderPart>();
				List<OrderPart> modifiedCatalogPartListForCatalogForm = new ArrayList<OrderPart>();
				List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
				Map<String,Boolean> catalogFinalFlags=(Map<String,Boolean>)session.getAttribute
						(ChangeMgmtConstant.CATFINALFLAGS,PortletSession.APPLICATION_SCOPE);
				Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",
						PortletSession.APPLICATION_SCOPE);
				String splitterFlag = "false";
				if(accDetails.get("splitterFlag")!=null){
					splitterFlag = accDetails.get("splitterFlag");
				}
				if(catalogDetailPageForm.getCatalogPartList()!=null){
					LOGGER.debug("The size of the catalog part list in the form is "+
				catalogDetailPageForm.getCatalogPartList().size());
				}
				if(catalogOrderListToSession!=null){
					LOGGER.debug("The size of the catalogOrderListToSession is "+catalogOrderListToSession.size());
				}
				for (int i=0;i<catalogDetailPageForm.getCatalogPartList().size();i++){
					
					paramPrefix = "catalogPartList[" + i + "].";
					orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
					catalogId = request.getParameter("catalogId["+i+"]");
					
					if(catalogId!=null){
						for(int j=0;j<catalogOrderListToSession.size();j++){
							if(catalogId.equalsIgnoreCase(catalogOrderListToSession.get(j).getCatalogId())){
								if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.toString(0))) {
									if("".equals(orderQuantity)){LOGGER.debug("Order quanityt is blank");}
									if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
									if(orderQuantity!=null){
										OrderPart orderPart = new OrderPart();
										orderPart.setPartNumber(catalogOrderListToSession.get(j).getPartNumber());
										orderPart.setPartDesc(catalogOrderListToSession.get(j).getPartDesc());
										orderPart.setConsumableType(catalogOrderListToSession.get(j).getConsumableType());
										orderPart.setYield(catalogOrderListToSession.get(j).getYield());
										orderPart.setModel(catalogOrderListToSession.get(j).getModel());
										orderPart.setPartType(catalogOrderListToSession.get(j).getPartType());
										orderPart.setProviderOrderNumber(catalogOrderListToSession.get(j).getProviderOrderNumber());
										orderPart.setOrderQuantity(orderQuantity);
										orderPart.setCatalogId(catalogId);
										orderPart.setSupplyId(catalogOrderListToSession.get(j).getSupplyId());
										orderPart.setProductId(catalogOrderListToSession.get(j).getProductId());
										
										orderPart.setMpsQuantity(catalogOrderListToSession.get(j).getMpsQuantity());
										
										/*MPS 2.1 Changes. Removing Implicit Flag*/
										
										/*Ends*/
										if(splitterFlag.equalsIgnoreCase("true")){
											orderPart.setContractLineItemId(catalogOrderListToSession.get(j).getContractLineItemId());
											orderPart.setSalesOrg(catalogOrderListToSession.get(j).getSalesOrg());
											
											if(catalogFinalFlags.get("finalShowPriceFlag") == true && 
													catalogOrderListToSession.get(j).getUnitPrice()!=null){
												orderPart.setUnitPrice(catalogOrderListToSession.get(j).getUnitPrice());
												if(catalogOrderListToSession.get(j).getUnitPrice()!=null && 
														catalogOrderListToSession.get(j).getUnitPrice()!=""){
													orderPart.setTotal(PaymentUtil.calculateTotalPrice
															(catalogOrderListToSession.get(j).getUnitPrice(),orderQuantity));
												}
											}
											if(catalogOrderListToSession.get(j).getCurrency()!=null){
												orderPart.setCurrency(catalogOrderListToSession.get(j).getCurrency());
												creditCurrency = catalogOrderListToSession.get(j).getCurrency();
											}
										}
										modifiedCatalogPartListForCatalogForm.add(orderPart);
									}
								}else{
									OrderPart orderPart = new OrderPart();
									orderPart.setPartNumber(catalogOrderListToSession.get(j).getPartNumber());
									orderPart.setPartDesc(catalogOrderListToSession.get(j).getPartDesc());
									orderPart.setConsumableType(catalogOrderListToSession.get(j).getConsumableType());
									orderPart.setPartType(catalogOrderListToSession.get(j).getPartType());
									orderPart.setOrderQuantity(orderQuantity);
									orderPart.setCatalogId(catalogOrderListToSession.get(j).getCatalogId());
									orderPart.setYield(catalogOrderListToSession.get(j).getYield());
									orderPart.setProviderOrderNumber(catalogOrderListToSession.get(j).getProviderOrderNumber());
									orderPart.setModel(catalogOrderListToSession.get(j).getModel());
									orderPart.setSupplyId(catalogOrderListToSession.get(j).getSupplyId());
									orderPart.setProductId(catalogOrderListToSession.get(j).getProductId());
									
									orderPart.setMpsQuantity(catalogOrderListToSession.get(j).getMpsQuantity());
									/*MPS 2.1 Changes. Removing Implicit Flag*/
									
									/*End*/
									if(splitterFlag.equalsIgnoreCase("true")){
										if(catalogOrderListToSession.get(j).getUnitPrice()!= null){
											if(catalogOrderListToSession.get(j).getUnitPrice() != "0" || catalogOrderListToSession.get(j).getUnitPrice() != ""){
												orderPart.setContractLineItemId(catalogOrderListToSession.get(j).getContractLineItemId());
												orderPart.setSalesOrg(catalogOrderListToSession.get(j).getSalesOrg());
												if(catalogFinalFlags.get("finalShowPriceFlag") == true && 
														catalogOrderListToSession.get(j).getUnitPrice()!=null){
													orderPart.setUnitPrice(catalogOrderListToSession.get(j).getUnitPrice());
													if(catalogOrderListToSession.get(j).getUnitPrice()!=null && 
															catalogOrderListToSession.get(j).getUnitPrice()!=""){
														orderPart.setTotal(PaymentUtil.calculateTotalPrice
																(catalogOrderListToSession.get(j).getUnitPrice(),orderQuantity));
													}
												}
												if(catalogOrderListToSession.get(j).getCurrency()!=null){
													orderPart.setCurrency(catalogOrderListToSession.get(j).getCurrency());
													creditCurrency = catalogOrderListToSession.get(j).getCurrency();
												}
											}
										}
										
									}
									modifiedCatalogPartList.add(orderPart);
									
									modifiedCatalogPartListForCatalogForm.add(orderPart);
								}
								
								break;
							}
						}
					}
				}//outer for loop
				reviewCatalogOrderForm.setCatalogPartList(modifiedCatalogPartList);
				catalogDetailPageForm.setCatalogPartList(modifiedCatalogPartListForCatalogForm);
				if(splitterFlag.equalsIgnoreCase("true")){
					
					if(catalogFinalFlags.get("finalTaxCalcFlag") == true){
						
						//Tax Call 					
						TaxContract taxContract = ContractFactory.getOrderTaxContract(modifiedCatalogPartList, 
								catalogDetailPageForm.getShipToAddress(), session, false);
						ObjectDebugUtil.printObjectContent(taxContract, LEXLOGGER);
						if(taxContract.getLineInformationList().size() != 0){
							long timeBeforeCall=System.currentTimeMillis();
							TaxResult taxresult = retrieveTaxService.retrieveTaxList(taxContract);
							
							PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVETAXLIST, timeBeforeCall,System.currentTimeMillis(), 
									PerformanceConstant.SYSTEM_SAP,taxContract);
							Map<String, String> priceMap = PaymentUtil.calculateTotalPriceWithTax
									(new ArrayList<Object>(modifiedCatalogPartList), taxresult.getLineInformationList());
							
		                    catalogDetailPageForm.setTotalAmt(priceMap.get("grandTotal"));
		                    catalogDetailPageForm.setSubTotal(priceMap.get("totalPrice"));
		                    catalogDetailPageForm.setTax(priceMap.get("totalTax"));
		                    
		                  //Populate Taxes in the part list
							for(Price price1 : taxresult.getLineInformationList()){
								for(OrderPart orderPart : modifiedCatalogPartList){
									if(price1.getSourceReferenceLineId().equalsIgnoreCase(orderPart.getCatalogId())){
										if(!price1.getTax().equalsIgnoreCase("Unavailable")){
											orderPart.setTax(price1.getTax());
										}
									}
								}
							}
						}
						
					}
					if(catalogFinalFlags.get("finalCreditFlag") == true){
						
						GenericAddress selBillTo = (GenericAddress)session.getAttribute("selectedBillToAddress", 
								PortletSession.APPLICATION_SCOPE);
						
						
						if(selBillTo!=null){
							catalogDetailPageForm.setBillToAddress(selBillTo);
							model.addAttribute("billTo",selBillTo);
						}
					}
					
				}				
				List <Attachment> attachmentList = new ArrayList<Attachment>();
				attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
				
				
				if(attachmentList!=null && attachmentList.size()>0){
					for (int i=0;i<attachmentList.size();i++){
						LOGGER.debug("attachment names are "+attachmentList.get(i).getAttachmentName() + 
								"attachment display names are "+attachmentList.get(i).getDisplayAttachmentName());						
					}
				}			
				
				catalogDetailPageForm.setAttachmentList(attachmentList);
				//changes for email page				
				
				if(catalogDetailPageForm.getPoNumber()!=null){
					
					reviewCatalogOrderForm.setPoNumber(catalogDetailPageForm.getPoNumber());
				}
				reviewCatalogOrderForm.setAttachmentList(attachmentList);
				
				ResourceURL resURL1 = response.createResourceURL();
				resURL1.setResourceID("displayAttachment");
				model.addAttribute("displayAttachment", resURL1.toString());
				catalogDetailPageForm.refreshSubmitToken(request);
				catalogDetailPageForm.setAttachmentDescription(attachmentDescription);
				attachForm.setAttachmentDescription(attachmentDescription);
				session.setAttribute("catalogDetailPageForm", catalogDetailPageForm);
				model.addAttribute("creditCurrency",creditCurrency);
				model.addAttribute("catalogDetailPageForm", catalogDetailPageForm);
				model.addAttribute("reviewCatalogOrderForm", reviewCatalogOrderForm);
			LOGGER.debug("---------------------- [Out] submitCatalogOrder for confirm order page ----------------------");
			returnPage = "ordermanagement/catalogOrder/reviewCatalogOrder";
			
			}catch(Exception e){
					
					exError = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
							"tax.validation.error", request.getLocale());
										
					model.addAttribute("Error", exError);
					model.addAttribute("catalogDetailPageForm", catalogDetailPageForm);
					FileUploadForm fileUploadForm = new FileUploadForm();
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
					
					returnPage = "ordermanagement/catalogOrder/catalogDetail";
				
			}
			}
		}else if("draftOrderRequest".equalsIgnoreCase(pageSubmitType)){
			LOGGER.debug("---------------------- [In] submitAssetOrder for draft order page ----------------------");
			CatalogDetailPageForm reviewCatalogOrderForm = new CatalogDetailPageForm();
			if(bindingResult.hasErrors()){
				List <Attachment> attachmentList = new ArrayList<Attachment>();
				attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
				catalogDetailPageForm.setAttachmentList(attachmentList);
				attachForm.setAttachmentList(attachmentList);
				attachForm.setListOfFileTypes(listOfFileTypes);
				attachForm.setAttMaxCount(attMaxCount);
				catalogDetailPageForm.setAttachmentDescription(attachmentDescription);
				attachForm.setAttachmentDescription(attachmentDescription);
				FileUploadForm fileUploadForm = new FileUploadForm();
				if(attachForm.getAttachmentList() !=null){
					fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
					}
				
				for(int j=0;j<bindingResult.getAllErrors().size();j++){
					LOGGER.debug("Errors are in controller "+bindingResult.getAllErrors().get(j).getCode().toString());
				}
				//Display part list after validation				
				String paramPrefix = null;
				String orderQuantity = null;
				String catalogId = "";
				List<OrderPart> modifiedCatalogPartList = new ArrayList<OrderPart>();
				List<OrderPart> modifiedCatalogPartListForCatalogForm = new ArrayList<OrderPart>();
				
				List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
				Map<String,Boolean> catalogFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.CATFINALFLAGS,
						PortletSession.APPLICATION_SCOPE);
				Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",
						PortletSession.APPLICATION_SCOPE);
				String splitterFlag = "false";
				if(accDetails.get("splitterFlag")!=null){
					splitterFlag = accDetails.get("splitterFlag");
				}
				if(catalogDetailPageForm.getCatalogPartList()!=null){
					LOGGER.debug("The size of the catalog part list in the form is "+catalogDetailPageForm.getCatalogPartList().size());
				}
				if(catalogOrderListToSession!=null){
					LOGGER.debug("The size of the catalogOrderListToSession is "+catalogOrderListToSession.size());
				}
				for (int i=0;i<catalogDetailPageForm.getCatalogPartList().size();i++){
					
					paramPrefix = "catalogPartList[" + i + "].";
					orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
					catalogId = request.getParameter("catalogId["+i+"]");
					
					if(catalogId!=null){
						for(int j=0;j<catalogOrderListToSession.size();j++){
							if(catalogId.equalsIgnoreCase(catalogOrderListToSession.get(j).getCatalogId())){
								if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.toString(0))) {
									if("".equals(orderQuantity)){LOGGER.debug("Order quanityt is blank");}
									if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
									if(orderQuantity!=null){
										OrderPart orderPart = new OrderPart();
										orderPart.setPartNumber(catalogOrderListToSession.get(j).getPartNumber());
										orderPart.setPartDesc(catalogOrderListToSession.get(j).getPartDesc());
										orderPart.setConsumableType(catalogOrderListToSession.get(j).getConsumableType());
										orderPart.setYield(catalogOrderListToSession.get(j).getYield());
										orderPart.setModel(catalogOrderListToSession.get(j).getModel());
										orderPart.setPartType(catalogOrderListToSession.get(j).getPartType());
										orderPart.setProviderOrderNumber(catalogOrderListToSession.get(j).getProviderOrderNumber());
										orderPart.setOrderQuantity(orderQuantity);
										orderPart.setCatalogId(catalogId);
										orderPart.setSupplyId(catalogOrderListToSession.get(j).getSupplyId());
										orderPart.setProductId(catalogOrderListToSession.get(j).getProductId());
										
										orderPart.setMpsQuantity(catalogOrderListToSession.get(j).getMpsQuantity());
										
										if(splitterFlag.equalsIgnoreCase("true")){
											orderPart.setContractLineItemId(catalogOrderListToSession.get(j).getContractLineItemId());
											if(catalogOrderListToSession.get(j).getUnitPrice()!=null){
												orderPart.setUnitPrice(catalogOrderListToSession.get(j).getUnitPrice());
											}
											if(catalogOrderListToSession.get(j).getCurrency()!=null){
												orderPart.setCurrency(catalogOrderListToSession.get(j).getCurrency());
											}
										}
										modifiedCatalogPartListForCatalogForm.add(orderPart);
									}
								}else{
									OrderPart orderPart = new OrderPart();
									orderPart.setPartNumber(catalogOrderListToSession.get(j).getPartNumber());
									orderPart.setPartDesc(catalogOrderListToSession.get(j).getPartDesc());
									orderPart.setConsumableType(catalogOrderListToSession.get(j).getConsumableType());
									orderPart.setPartType(catalogOrderListToSession.get(j).getPartType());
									orderPart.setOrderQuantity(orderQuantity);
									orderPart.setCatalogId(catalogOrderListToSession.get(j).getCatalogId());
									orderPart.setProviderOrderNumber(catalogOrderListToSession.get(j).getProviderOrderNumber());
									orderPart.setYield(catalogOrderListToSession.get(j).getYield());
									orderPart.setModel(catalogOrderListToSession.get(j).getModel());
									orderPart.setSupplyId(catalogOrderListToSession.get(j).getSupplyId());
									orderPart.setProductId(catalogOrderListToSession.get(j).getProductId());
									
									orderPart.setMpsQuantity(catalogOrderListToSession.get(j).getMpsQuantity());
									
									if(splitterFlag.equalsIgnoreCase("true")){
										orderPart.setContractLineItemId(catalogOrderListToSession.get(j).getContractLineItemId());
										if(catalogOrderListToSession.get(j).getUnitPrice()!=null){
											orderPart.setUnitPrice(catalogOrderListToSession.get(j).getUnitPrice());										
										}
										if(catalogOrderListToSession.get(j).getCurrency()!=null){
											orderPart.setCurrency(catalogOrderListToSession.get(j).getCurrency());
										}
									}
									modifiedCatalogPartList.add(orderPart);
									
									modifiedCatalogPartListForCatalogForm.add(orderPart);
								}
								
								break;
							}
						}
					}
				}//outer for loop
				
				catalogDetailPageForm.setCatalogPartList(modifiedCatalogPartList);
				//End Display part list after validation
				model.addAttribute("catalogDetailPageForm", catalogDetailPageForm);
				model.addAttribute("reviewCatalogOrderForm", reviewCatalogOrderForm);
				model.addAttribute("attachmentForm", attachForm);
				model.addAttribute("fileUploadForm", fileUploadForm);
				
				returnPage = "ordermanagement/catalogOrder/catalogDetail";
			} else {
			String paramPrefix = null;
			String orderQuantity = null;
			String catalogId = "";
			List<OrderPart> modifiedCatalogPartList = new ArrayList<OrderPart>();
			List<OrderPart> modifiedCatalogPartListForCatalogForm = new ArrayList<OrderPart>();
			Map<String,Boolean> catalogFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.CATFINALFLAGS,
					PortletSession.APPLICATION_SCOPE);
			Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",
					PortletSession.APPLICATION_SCOPE);
			String splitterFlag = "false";
			if(accDetails.get("splitterFlag")!=null){
				splitterFlag = accDetails.get("splitterFlag");
			}
			if(accDetails != null){
				LOGGER.debug("Account id:"+accDetails.get("accountId") + "Account Name:"+accDetails.get("accountName") + 
				"Account Organization:"+accDetails.get("accountOrganization") + "Agreement Id:"+accDetails.get("agreementId") + 
				"Agreement Name:"+accDetails.get("agreementName") + "Country:"+accDetails.get("country"));
			}

			List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
			if(catalogDetailPageForm.getCatalogPartList()!=null){
				LOGGER.debug("The size of the catalog part list in the form is "+catalogDetailPageForm.getCatalogPartList().size());
			}
			if(catalogOrderListToSession!=null){
				LOGGER.debug("The size of the catalogOrderListToSession is "+catalogOrderListToSession.size());
			}
			for (int i=0;i<catalogDetailPageForm.getCatalogPartList().size();i++){
				
				paramPrefix = "catalogPartList[" + i + "].";
				orderQuantity = request.getParameter(paramPrefix + "orderQuantity");
				catalogId = request.getParameter("catalogId["+i+"]");
				
				if(catalogId!=null){
					for(int j=0;j<catalogOrderListToSession.size();j++){
						if(catalogId.equalsIgnoreCase(catalogOrderListToSession.get(j).getCatalogId())){
							if ("".equals(orderQuantity)||orderQuantity ==null||orderQuantity.equals(Integer.toString(0))) {
								if("".equals(orderQuantity)){LOGGER.debug("Order quanityt is blank");}
								if(orderQuantity ==null){LOGGER.debug("Order quantity is null");}
								if(orderQuantity!=null){
									OrderPart orderPart = new OrderPart();
									orderPart.setPartNumber(catalogOrderListToSession.get(j).getPartNumber());
									orderPart.setPartDesc(catalogOrderListToSession.get(j).getPartDesc());
									orderPart.setConsumableType(catalogOrderListToSession.get(j).getConsumableType());
									orderPart.setYield(catalogOrderListToSession.get(j).getYield());
									orderPart.setModel(catalogOrderListToSession.get(j).getModel());
									orderPart.setPartType(catalogOrderListToSession.get(j).getPartType());
									orderPart.setProviderOrderNumber(catalogOrderListToSession.get(j).getProviderOrderNumber());
									orderPart.setOrderQuantity(orderQuantity);
									orderPart.setCatalogId(catalogId);
									orderPart.setSupplyId(catalogOrderListToSession.get(j).getSupplyId());
									orderPart.setProductId(catalogOrderListToSession.get(j).getProductId());
									
									orderPart.setMpsQuantity(catalogOrderListToSession.get(j).getMpsQuantity());
									/*MPS 2.1 Changes. Removing Implicit Flag*/
									
									/*Ends*/
									
									if(splitterFlag.equalsIgnoreCase("true")){
										orderPart.setContractLineItemId(catalogOrderListToSession.get(j).getContractLineItemId());
										if(catalogFinalFlags.get("finalShowPriceFlag") == true && catalogOrderListToSession.get(j).getUnitPrice()!=null){
											if(catalogOrderListToSession.get(j).getUnitPrice()!=null){
												orderPart.setUnitPrice(catalogOrderListToSession.get(j).getUnitPrice());
											}
											if(catalogOrderListToSession.get(j).getCurrency()!=null){
												orderPart.setCurrency(catalogOrderListToSession.get(j).getCurrency());
											}
										}
									}
									modifiedCatalogPartListForCatalogForm.add(orderPart);
								}
							}else{
								OrderPart orderPart = new OrderPart();
								orderPart.setPartNumber(catalogOrderListToSession.get(j).getPartNumber());
								orderPart.setPartDesc(catalogOrderListToSession.get(j).getPartDesc());
								orderPart.setConsumableType(catalogOrderListToSession.get(j).getConsumableType());
								orderPart.setPartType(catalogOrderListToSession.get(j).getPartType());
								orderPart.setOrderQuantity(orderQuantity);
								orderPart.setCatalogId(catalogOrderListToSession.get(j).getCatalogId());
								orderPart.setProviderOrderNumber(catalogOrderListToSession.get(j).getProviderOrderNumber());
								orderPart.setYield(catalogOrderListToSession.get(j).getYield());
								orderPart.setModel(catalogOrderListToSession.get(j).getModel());
								orderPart.setSupplyId(catalogOrderListToSession.get(j).getSupplyId());
								orderPart.setProductId(catalogOrderListToSession.get(j).getProductId());
								
								orderPart.setMpsQuantity(catalogOrderListToSession.get(j).getMpsQuantity());
								
								if(splitterFlag.equalsIgnoreCase("true")){
									if(catalogFinalFlags.get("finalShowPriceFlag") == true && catalogOrderListToSession.get(j).getUnitPrice()!=null){
										orderPart.setContractLineItemId(catalogOrderListToSession.get(j).getContractLineItemId());
										if(catalogOrderListToSession.get(j).getUnitPrice()!=null){
											orderPart.setUnitPrice(catalogOrderListToSession.get(j).getUnitPrice());
										}
										if(catalogOrderListToSession.get(j).getCurrency()!=null){
											orderPart.setCurrency(catalogOrderListToSession.get(j).getCurrency());
										}
									}
								}
								modifiedCatalogPartList.add(orderPart);
								
								modifiedCatalogPartListForCatalogForm.add(orderPart);
							}							
							break;
						}
					}
				}
			}//outer for loop
			catalogDetailPageForm.setCatalogPartList(modifiedCatalogPartList);
			
			List <Attachment> attachmentList = new ArrayList<Attachment>();
			attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
			//Adding correct data for the attachment
			if(attachmentList!=null && attachmentList.size()>0){
				
				for(int i=0;i<attachmentList.size();i++){
					LOGGER.debug("attachment names are "+attachmentList.get(i).getAttachmentName());
				}
			}
			
			Map<String,String> catDetails =(Map<String,String>)session.getAttribute(ChangeMgmtConstant.CATCURRDETAILS,
					PortletSession.APPLICATION_SCOPE);
			if(catDetails !=null){
			if(catDetails.get("paymentType") != null){
				catalogDetailPageForm.setSelectedPaymentType(catDetails.get("paymentType"));
			}else{
				catalogDetailPageForm.setSelectedPaymentType("");
			}
			}
						
			catalogDetailPageForm.setAttachmentDescription(attachmentDescription);
			attachForm.setAttachmentDescription(attachmentDescription);
			CreateConsumableServiceRequestContract createServiceReqContract = 
				ContractFactory.getCatalogDraftReqContract(catalogDetailPageForm, request);
			
//			Updated By MPS Offshore Team for create Vendor Order
			List<String> userRoleList = PortalSessionUtil.getUserRoles(session);		
			boolean isVendorFlag = false;
			if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || 
					(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
			{
				isVendorFlag = true;
			}			
			
			long timeBeforeCall=System.currentTimeMillis();
			CreateServiceRequestResult result = createConsumableRequest.createConsumableServiceRequest
					(createServiceReqContract,accDetails,isVendorFlag);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_CREATECONSUMABLESERVICEREQUEST_SR, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,createServiceReqContract);
			LOGGER.info("start printing lex logger");
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING CONSUMABLES SERVICE REQUEST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
			
			String serviceRequestNumber = result.getServiceRequestNumber();
			String srRowId = result.getServiceRequestRowId();
			//Received the servicerequestnumber from webmethods. Lets call aMind
			AttachmentContract contract = new AttachmentContract();
			List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
			if(attachmentList!=null && attachmentList.size()>0){
				
				for (int i=0;i<attachmentList.size();i++){
					if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
						
						createSRAttachmentList.add(attachmentList.get(i));
					}
					
				}
				if(createSRAttachmentList!=null && createSRAttachmentList.size()>0){
					for(int i=0;i<createSRAttachmentList.size();i++){
						LOGGER.debug("Create SR will be called for following attachments "+
					createSRAttachmentList.get(i).getAttachmentName());
					}
					contract.setAttachments(createSRAttachmentList);
					contract.setRequestType("Service Request");
					contract.setIdentifier(srRowId);		
					
					ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);					
					long timeBeforeCallAttachment=System.currentTimeMillis();				
					attachmentService.uploadAttachments(contract);
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeCallAttachment,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
				}
			}		
			
			catalogDetailPageForm.setMaxPartsToBeOrdered(getMaxPartsToBeOrdered());
			model.addAttribute("serviceRequestNumber", serviceRequestNumber);
			session.setAttribute("catalogDetailPageForm", catalogDetailPageForm);
			model.addAttribute("catalogDetailPageForm", catalogDetailPageForm);
			model.addAttribute("srnumber",serviceRequestNumber);
			model.addAttribute("draftConfirmation","draftConfirmation");
			FileUploadForm fileUploadForm = new FileUploadForm();
			model.addAttribute("fileUploadForm", fileUploadForm);
			returnPage = "ordermanagement/catalogOrder/catalogDetail";
			LOGGER.debug("---------------------- [Out] submitAssetOrder for draft order page ----------------------");
		}
		
		}
		return returnPage;
	}
	/**
	 * This method is used to confirm catalog order page
	 * @param request 
	 * @param response 
	 * @param catalogDetailPageForm 
	 * @param reviewCatalogOrderForm 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	//Here you have to call webmethod webservice and also you have to call amind attachment service
	@RenderMapping(params="action=confirmCatalogOrder")
	public String confirmCatalogOrder(
			RenderRequest request, 
			RenderResponse response,  
			@ModelAttribute("catalogDetailPageForm") CatalogDetailPageForm catalogDetailPageForm,
			@ModelAttribute("reviewCatalogOrderForm") CatalogDetailPageForm reviewCatalogOrderForm,
			Model model){
		LOGGER.debug("---------------------- [In] confirmCatalogOrder method ----------------------");
		boolean srCreationFlag = true;
		if (PortalSessionUtil.isDuplicatedSubmit(request,catalogDetailPageForm)) {	
			List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
			List <Attachment> attachmentList = new ArrayList<Attachment>();
			attachmentList = reviewCatalogOrderForm.getAttachmentList();
			if(attachmentList!=null && attachmentList.size()>0){
				List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
				for(int i=0;i<reviewCatalogOrderForm.getAttachmentList().size();i++){
					AttachmentFile attachmentFile = new AttachmentFile();
					attachmentFile.setFileName(attachmentList.get(i).getDisplayAttachmentName());
					attachmentFile.setFileSize(attachmentList.get(i).getSize());
					displayAttachmentList.add(attachmentFile);
					if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
						createSRAttachmentList.add(attachmentList.get(i));
					}
					
				}
				model.addAttribute("displayAttachmentList", displayAttachmentList);
				catalogDetailPageForm.setDisplayAttachmentList(displayAttachmentList);
				}
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, 
					"exception.serviceRequest.duplicateSubmission", null, request.getLocale()));
		}else{
		PortletSession session = request.getPortletSession();		
		
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",
				PortletSession.APPLICATION_SCOPE);
		if(accDetails != null){
			LOGGER.debug("Account id:"+accDetails.get("accountId") + 
			"Account Name:"+accDetails.get("accountName") + 
			"Account Organization:"+accDetails.get("accountOrganization") + 
			"Agreement Id:"+accDetails.get("agreementId") + 
			"Agreement Name:"+accDetails.get("agreementName") + 
			"Country:"+accDetails.get("country"));
		}		
		
		for (int i=0;i<catalogDetailPageForm.getCatalogPartList().size();i++){
			LOGGER.debug("After setting order quantity for "+i+" number part is "+
					catalogDetailPageForm.getCatalogPartList().get(i).getOrderQuantity()+" for consumable part type "+
					catalogDetailPageForm.getCatalogPartList().get(i).getConsumableType() + 
			"price is "+catalogDetailPageForm.getCatalogPartList().get(i).getUnitPrice() + 
			"Total is "+catalogDetailPageForm.getCatalogPartList().get(i).getTotal());
		}		
		
		if(catalogDetailPageForm.getCreditCardToken()!=null){
			LOGGER.debug("Credit Card token retrieved from form is "+catalogDetailPageForm.getCreditCardToken() + 
			"Credit Card Number "+catalogDetailPageForm.getCreditCardEncryptedNo() + 
			"Credit Card expiration date "+catalogDetailPageForm.getCreditCardExpirationDate() + 
			"Credit Card Type "+catalogDetailPageForm.getCreditCardType() + 
			"Card Holder Name "+catalogDetailPageForm.getCardHoldername());
		}
		String creditFlag = "false";
		if(catalogDetailPageForm.getCreditFlag()!=null && catalogDetailPageForm.getCreditFlag().equalsIgnoreCase("true")){
			creditFlag = catalogDetailPageForm.getCreditFlag();
		}		
		
		Map<String,String> catDetails =(Map<String,String>)session.getAttribute(ChangeMgmtConstant.CATCURRDETAILS,
				PortletSession.APPLICATION_SCOPE);
		if(catDetails!= null){
			catalogDetailPageForm.setSelectedPaymentType(catDetails.get("paymentType"));
		}
				
		//This is the time to call webmethods webservice
		CreateConsumableServiceRequestContract createServiceReqContract = 
			ContractFactory.getCatalogServiceReqContract(catalogDetailPageForm, reviewCatalogOrderForm, request);
				
//		Updated By MPS Offshore Team for create Vendor Order
		List<String> userRoleList = PortalSessionUtil.getUserRoles(session);		
		boolean isVendorFlag = false;
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || 
				(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			isVendorFlag = true;
		}		
		
		
		CreateServiceRequestResult result = null;
		try{
			long timeBeforeCall=System.currentTimeMillis();
			result = createConsumableRequest.createConsumableServiceRequest
					(createServiceReqContract,accDetails,isVendorFlag);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_CREATECONSUMABLESERVICEREQUEST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,createServiceReqContract);
			LOGGER.info("start printing lex logger");
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING WEBMETHODS CALL CREATE CONSUMABLE SERVICE REQUEST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
			LOGGER.info("end printing lex loggger");
		}catch (Exception e) {
			LOGGER.info("Exception in SR creation.");
			srCreationFlag = false;
		}
		if(result!= null){
			String serviceRequestNumber = result.getServiceRequestNumber();
			String srRowId = result.getServiceRequestRowId();
			AttachmentContract contract = new AttachmentContract();
			List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
			List <Attachment> attachmentList = new ArrayList<Attachment>();
			attachmentList = reviewCatalogOrderForm.getAttachmentList();
			if(attachmentList!=null && attachmentList.size()>0){
				
				
				try {
					commonController.uploadAttachment(reviewCatalogOrderForm.getAttachmentList(), srRowId);
					List<AttachmentFile> displayAttachmentList = new ArrayList<AttachmentFile>();
					for(int i=0;i<attachmentList.size();i++){
						AttachmentFile attachmentFile = new AttachmentFile();
						attachmentFile.setFileName(attachmentList.get(i).getDisplayAttachmentName());
						attachmentFile.setFileSize(attachmentList.get(i).getSize());
						displayAttachmentList.add(attachmentFile);
						
					}
					model.addAttribute("displayAttachmentList", displayAttachmentList);
					catalogDetailPageForm.setDisplayAttachmentList(displayAttachmentList);
				} catch (Exception e) {
					LOGGER.info("Exception in Attachment upload.");
					model.addAttribute("attachmentException", "attachfailed");
				}
				
			}
			model.addAttribute("serviceRequestNumber", serviceRequestNumber);	
		}
		
		
		//this is to enable re-submit of SR form on submit/draft exception
		Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, session.PORTLET_SCOPE);
		BaseForm baseForm = (BaseForm)catalogDetailPageForm;
		baseForm.setSubmitToken(tokenInSession);
		//Received the servicerequestnumber from webmethods. Lets call aMind
			
		model.addAttribute("creditFlag", creditFlag);
		model.addAttribute("catalogDetailPageForm", catalogDetailPageForm);
		model.addAttribute("reviewAssetOrderForm", reviewCatalogOrderForm);
		session.removeAttribute("draftSrNumber");
		session.removeAttribute("attachmentList");
		session.removeAttribute("catalogDetailPageForm");
		session.removeAttribute("servicePartQuantity");
		session.removeAttribute("suppliesPartQuantity");
		session.removeAttribute("quantityServicesMap");
		session.removeAttribute("quantitySuppliesMap");
		}
		LOGGER.debug("----------------------- [OUT] confirmCatalogOrder method ----------------------");
		if(srCreationFlag){
			return "ordermanagement/catalogOrder/confirmCatalogOrder";
		}else{
			String srCreationValidation = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"exception.sr.save", request.getLocale());
			model.addAttribute("srCreationError",srCreationValidation);
			return "ordermanagement/catalogOrder/reviewCatalogOrder";
		}
		
	}
	/**
	 * This method is used to show cart view page popup
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showCartViewPage")
	public String showCartViewPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		LOGGER.debug("-------------showCartViewPage started---------");
		PortletSession session = request.getPortletSession();
		CartViewForm cartViewForm = new CartViewForm();
		session.removeAttribute("cartViewCatalogOrderListToSession");
		List<OrderPart> cartViewCatalogOrderListToSession = new ArrayList<OrderPart>();
		List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
		if (catalogOrderListToSession!=null){
			List<OrderPart> catalogPartList = new ArrayList<OrderPart>();
				for(OrderPart part : catalogOrderListToSession){
					OrderPart orderPart = new OrderPart();
					orderPart.setPartNumber(part.getPartNumber());
					orderPart.setPartDesc(part.getPartDesc());
					orderPart.setPartType(part.getPartType());
					orderPart.setYield(part.getYield());
					orderPart.setPartQuantity(part.getPartQuantity());
					orderPart.setContractLineItemId(part.getContractLineItemId());
					orderPart.setProviderOrderNumber(part.getProviderOrderNumber());
					orderPart.setCatalogId(part.getCatalogId());
					orderPart.setConsumableType(part.getConsumableType());
					orderPart.setSupplyId(part.getSupplyId());
					orderPart.setProductId(part.getProductId());
					orderPart.setSalesOrg(part.getSalesOrg());
					orderPart.setModel(part.getModel());
					
					orderPart.setMpsQuantity(part.getMpsQuantity());
					
					if(part.getUnitPrice()!=null && part.getUnitPrice()!=""){
						orderPart.setUnitPrice(part.getUnitPrice());
					}					
					if(part.getCurrency()!=null && part.getCurrency()!=""){
						orderPart.setCurrency(part.getCurrency());
					}
					LOGGER.debug("Part details number "+part.getPartNumber()+" part desc "
							+part.getPartDesc()+" part type "+part.getPartType()+" yield "+part.getYield()+" quantity "+
							part.getPartQuantity()+" catalogid "+part.getCatalogId()+" consumbale part tye "+part.getConsumableType()+
							" supply id "+part.getSupplyId()+" product id "+part.getProductId()+" model is "+part.getModel()+" provider Order Number is "+part.getProviderOrderNumber());
					catalogPartList.add(orderPart);
					cartViewCatalogOrderListToSession.add(orderPart);
				}
				cartViewForm.setCatalogPartList(catalogPartList);
				session.setAttribute("cartViewCatalogOrderListToSession", cartViewCatalogOrderListToSession);//This will be used for cart view modification
		}
		model.addAttribute("cartViewForm", cartViewForm);
		return "ordermanagement/catalogOrder/cartView";
	}
	/**
	 * This method is used to update cart view url
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("updateCartView")
	public void updateCartViewURL(ResourceRequest request, ResourceResponse response) throws Exception
	{
		LOGGER.debug("---------------------------------- [IN]updateCartViewURL-------------------");
		PortletSession session = request.getPortletSession();
		String orderQuantity= request.getParameter("orderQuantity");
		String catalogId = request.getParameter("catalogId");
		String jobType = request.getParameter("jobType");
		
		List<OrderPart> cartViewCatalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute
				("cartViewCatalogOrderListToSession");
		//Changes for Cart Deletion MPS 2.1
		List<?> catalogOrderListToSession = (ArrayList<?>) session.getAttribute("catalogOrderListToSession");

		//Ends Changes for Cart Deletion MPS 2.1
		if (cartViewCatalogOrderListToSession!=null){
			
			for(int i=0;i<cartViewCatalogOrderListToSession.size();i++){
				if(catalogId.equalsIgnoreCase(cartViewCatalogOrderListToSession.get(i).getCatalogId())){
					//Update is required
					if("update".equalsIgnoreCase(jobType)){
						
						OrderPart orderPart = new OrderPart();
						orderPart.setPartNumber(cartViewCatalogOrderListToSession.get(i).getPartNumber());
						orderPart.setPartDesc(cartViewCatalogOrderListToSession.get(i).getPartDesc());
						orderPart.setPartType(cartViewCatalogOrderListToSession.get(i).getPartType());
						orderPart.setYield(cartViewCatalogOrderListToSession.get(i).getYield());
						orderPart.setModel(cartViewCatalogOrderListToSession.get(i).getModel());
						orderPart.setSupplyId(cartViewCatalogOrderListToSession.get(i).getSupplyId());
						orderPart.setProductId(cartViewCatalogOrderListToSession.get(i).getProductId());
						orderPart.setProviderOrderNumber(cartViewCatalogOrderListToSession.get(i).getProviderOrderNumber());
						orderPart.setSalesOrg(cartViewCatalogOrderListToSession.get(i).getSalesOrg());
						orderPart.setContractLineItemId(cartViewCatalogOrderListToSession.get(i).getContractLineItemId());
						
						orderPart.setMpsQuantity(cartViewCatalogOrderListToSession.get(i).getMpsQuantity());
						
						if(cartViewCatalogOrderListToSession.get(i).getUnitPrice()!=null){
							orderPart.setUnitPrice(cartViewCatalogOrderListToSession.get(i).getUnitPrice());
						}
						if(cartViewCatalogOrderListToSession.get(i).getCurrency()!=null){
							orderPart.setCurrency(cartViewCatalogOrderListToSession.get(i).getCurrency());
						}
						orderPart.setPartQuantity(orderQuantity);
						orderPart.setCatalogId(catalogId);
						orderPart.setConsumableType(cartViewCatalogOrderListToSession.get(i).getConsumableType());
						cartViewCatalogOrderListToSession.remove(i);
						//if orderQuantity is zero dont add the part to the session
						//else remove the quantity from the cart but we cant do that. We need to handle that if any error comes
						cartViewCatalogOrderListToSession.add(orderPart);
					}else{						
						//Changes for Cart Deletion MPS 2.1
						catalogOrderListToSession.remove(i);
						//Ends Changes for Cart Deletion MPS 2.1
						cartViewCatalogOrderListToSession.remove(i);
					}
					session.removeAttribute("cartViewCatalogOrderListToSession");
					//Changes for Cart Deletion MPS 2.1
					session.removeAttribute("catalogOrderListToSession");
					//Ends Changes for Cart Deletion MPS 2.1
					session.setAttribute("cartViewCatalogOrderListToSession", cartViewCatalogOrderListToSession);
					//Changes for Cart Deletion MPS 2.1
					session.setAttribute("catalogOrderListToSession",catalogOrderListToSession);
					//Ends Changes for Cart Deletion MPS 2.1
					break;
				}
			}
		}
		
		//Printing the values
		List<OrderPart> printCartViewCatalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute
				("cartViewCatalogOrderListToSession");
		
		if (printCartViewCatalogOrderListToSession!=null){
			
			for(int i=0;i<printCartViewCatalogOrderListToSession.size();i++){
				LOGGER.debug("Part type "+printCartViewCatalogOrderListToSession.get(i).getPartType() + 
				"Quantity "+printCartViewCatalogOrderListToSession.get(i).getPartQuantity());
			}
		}
		//complete printing the values
		LOGGER.debug("---------------------------------- [OUT]updateCartViewURL-------------------");
	}
	
	/**
	 * This method is used to update cart view
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("updateCompleteCartView")
	public void updateCompleteCartView(ResourceRequest request, ResourceResponse response) throws Exception
	{
		LOGGER.debug("------------------------- [IN]updateCompleteCartView -------------------------");
		PortletSession session = request.getPortletSession();
		List<OrderPart> catalogOrderListToSession = new ArrayList<OrderPart>();
		List<OrderPart> cartViewCatalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("cartViewCatalogOrderListToSession");
		Map<String,Boolean> catalogFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.CATFINALFLAGS,
				PortletSession.APPLICATION_SCOPE);
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",
				PortletSession.APPLICATION_SCOPE);
		String splitterFlag = "false";
		if(accDetails.get("splitterFlag")!=null){
			splitterFlag = accDetails.get("splitterFlag");
		}
		session.removeAttribute("catalogOrderListToSession");
		for(int i=0;i<cartViewCatalogOrderListToSession.size();i++){
			String orderQuantity = cartViewCatalogOrderListToSession.get(i).getPartQuantity();
			
			if(!("".equals(orderQuantity)||Integer.toString(0).equals(orderQuantity))){
				
				OrderPart orderPart = new OrderPart();
				orderPart.setPartNumber(cartViewCatalogOrderListToSession.get(i).getPartNumber());
				orderPart.setPartDesc(cartViewCatalogOrderListToSession.get(i).getPartDesc());
				orderPart.setPartType(cartViewCatalogOrderListToSession.get(i).getPartType());
				orderPart.setYield(cartViewCatalogOrderListToSession.get(i).getYield());
				orderPart.setModel(cartViewCatalogOrderListToSession.get(i).getModel());
				orderPart.setSupplyId(cartViewCatalogOrderListToSession.get(i).getSupplyId());
				orderPart.setProviderOrderNumber(cartViewCatalogOrderListToSession.get(i).getProviderOrderNumber());
				orderPart.setProductId(cartViewCatalogOrderListToSession.get(i).getProductId());
				orderPart.setSalesOrg(cartViewCatalogOrderListToSession.get(i).getSalesOrg());
				orderPart.setPartQuantity(cartViewCatalogOrderListToSession.get(i).getPartQuantity());
				orderPart.setCatalogId(cartViewCatalogOrderListToSession.get(i).getCatalogId());
				orderPart.setConsumableType(cartViewCatalogOrderListToSession.get(i).getConsumableType());
				
				orderPart.setMpsQuantity(cartViewCatalogOrderListToSession.get(i).getMpsQuantity());
				
				if(splitterFlag.equalsIgnoreCase("true")){
					orderPart.setContractLineItemId(cartViewCatalogOrderListToSession.get(i).getContractLineItemId());
					if(catalogFinalFlags.get("finalShowPriceFlag") == true && cartViewCatalogOrderListToSession.get(i).getUnitPrice()!=null){
						if(cartViewCatalogOrderListToSession.get(i).getUnitPrice()!=null){
							orderPart.setUnitPrice(cartViewCatalogOrderListToSession.get(i).getUnitPrice());						
						}
						if(cartViewCatalogOrderListToSession.get(i).getCurrency()!=null){
							orderPart.setCurrency(cartViewCatalogOrderListToSession.get(i).getCurrency());
						}
					}
				}
				catalogOrderListToSession.add(orderPart);
			}
		}
		session.setAttribute("catalogOrderListToSession", catalogOrderListToSession);
		//String successMessage = "Your cart has been successfully updated";
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
	 * This method is used to show catalog order page
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showCatalogOrderPage")
	public void showCatalogOrderPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
	
	/**
	 * Method responsible for back button in add CATALOG review page
	 * @param req 
	 * @param resp 
	 * @param catalogDetailPageForm 
	 * @param model 
	 * @return String
	 */
	@RenderMapping(params = "action=goToCatalogDetail")
	public String goToAddCatalog(RenderRequest req, RenderResponse resp, @ModelAttribute("catalogDetailPageForm") 
			CatalogDetailPageForm catalogDetailPageForm,@ModelAttribute ("attachmentForm") AttachmentForm attachForm, Model model)	
	{
		PortletSession session = req.getPortletSession();
		LOGGER.debug("primary firstname="+catalogDetailPageForm.getServiceRequest().getPrimaryContact().getFirstName() + 
		"primary lastname="+catalogDetailPageForm.getServiceRequest().getPrimaryContact().getLastName() + 
		"secondary firstname="+catalogDetailPageForm.getServiceRequest().getSecondaryContact().getFirstName() + 
		"secondary lastname="+catalogDetailPageForm.getServiceRequest().getSecondaryContact().getLastName());
		
		commonController.getContactInformation(req, resp);
		FileUploadForm fileUploadForm = new FileUploadForm();
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
		model.addAttribute("catalogDetailPageForm", catalogDetailPageForm);
		return "ordermanagement/catalogOrder/catalogDetail";
	}
	
	/**
	 * This method is used to delete the part from the catalog detail page
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("deleteFromCatalogDetail")
	public void deleteFromCatalogDetail(ResourceRequest request, ResourceResponse response) throws Exception
	{
		LOGGER.debug("---------------------------------- [IN]deleteFromCatalogDetail-------------------");
		PortletSession session = request.getPortletSession();
		String catalogId = request.getParameter("catalogId");
		
		List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
		
		if (catalogOrderListToSession!=null){
			
			for(int i=0;i<catalogOrderListToSession.size();i++){
				if(catalogId.equalsIgnoreCase(catalogOrderListToSession.get(i).getCatalogId())){
					
					catalogOrderListToSession.remove(i);
					session.removeAttribute("cartViewCatalogOrderListToSession");
					session.setAttribute("catalogOrderListToSession", catalogOrderListToSession);
					
					break;
				}
			}
		}
		//Printing the values
		List<OrderPart> printCatalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
		if (printCatalogOrderListToSession!=null){
			
			for(int i=0;i<printCatalogOrderListToSession.size();i++){
				LOGGER.debug("Part type "+printCatalogOrderListToSession.get(i).getPartType() + 
				"Quantity "+printCatalogOrderListToSession.get(i).getPartQuantity() + 
				"Description "+printCatalogOrderListToSession.get(i).getDescription() + 
				"Part Description "+printCatalogOrderListToSession.get(i).getPartDesc());
			}
		}
		//complete printing the values
		LOGGER.debug("---------------------------------- [OUT]deleteFromCatalogDetail-------------------");
	}
	
		
	
			
	/**
	 * This metho is used to print the catalog order confirmation page
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=catalogOrderConfirmPrint")
	public String showOrderConfirmPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "ordermanagement/catalogOrder/catalogOrderConfirmPrint";
	}
	/**
	 * This method is used to email the order confirmation page
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=catalogOrderConfirmEmail")
	public String showOrderConfirmEmailPage(RenderRequest request, RenderResponse response, Model model)throws Exception{
		return "ordermanagement/catalogOrder/catalogOrderConfirmEmail";		
	}
	
	
	/**
	 * 
	 * @return String
	 */
	public String getMaxPartsToBeOrdered() {
		return maxPartsToBeOrdered;
	}
	/**
	 * 
	 * @param maxPartsToBeOrdered 
	 */
	public void setMaxPartsToBeOrdered(String maxPartsToBeOrdered) {
		this.maxPartsToBeOrdered = maxPartsToBeOrdered;
	}
	/**
	 * 
	 * @return String
	 */
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}
	/**
	 * 
	 * @param listOfFileTypes 
	 */
	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}
	/**
	 * 
	 * @return String
	 */
	public String getAttMaxCount() {
		return attMaxCount;
	}
	/**
	 * 
	 * @param attMaxCount 
	 */
	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}
	
	/**
	 * Renders the email confirmation page for Catalog order SR
	 * @param req 
	 * @param resp 
	 * @param model 
	 * @return String
	 */
	@RequestMapping(params = "action=catalogOrderEmailConfirmationPage")
	public String renderCatalogOrderEmailConfirmationPage(RenderRequest req, RenderResponse resp, Model model) {		
		return "ordermanagement/catalogOrder/catalogOrderEmailPage";
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
		return "common/emailConfirmationPage";
	}
	
	/**
	 * This method is used to display attachment
	 * @param request  
	 * @param response  
	 * @throws LGSRuntimeException  
	 */
	@ResourceMapping(value="displayAttachment")
	public void displayAttachment(ResourceRequest request,ResourceResponse response) throws LGSRuntimeException{			
		
		LOGGER.debug("Inside displayAttachment...");
		
		String fileName = request.getParameter("fileName");
		
		AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
		
		openAttachment(request,response, fileName, fileProperties.getFileUploadDestination() + fileName);
		
		LOGGER.debug("End of displayAttachment...");
		
	}
	
		/**
		 * This  method used to open any attachment
		 * @param request  
		 * @param response  
		 * @param fileName  
		 * @param fullPath  
		 */
		private void openAttachment(ResourceRequest request, ResourceResponse response,String fileName, String fullPath){
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String userAgent = httpReq.getHeader("User-Agent");
	    if (userAgent.contains("MSIE 7.0")) {
	    	fileName = fileName.replace(" ", "%20");   
	    }   
	    response.setProperty("Content-disposition", "attachment; filename=\"" + fileName +"\"");
		
		if(fileName.indexOf("csv")>0){
			
        	response.setContentType("application/vnd.ms-excel");
        }else if(fileName.indexOf("pdf") > 0) {
        	
        	response.setContentType("application/pdf");
        }else if(fileName.indexOf("xls")>0){
        	
        	response.setContentType("application/vnd.ms-excel");
        }else if(fileName.indexOf("doc")>0){
        	
        	response.setContentType("application/msword");
        }else if(fileName.indexOf("zip") > 0) {
        	response.setContentType("application/zip");
        }else if(fileName.indexOf("xlsx")>0){
        	response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }else if(fileName.indexOf("docx")>0){
        	response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
        else if(fileName.indexOf("ppt")>0){
        	response.setContentType("application/vnd.ms-powerpoint");
        }
        else if(fileName.indexOf("pptx")>0){
        	response.setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
        }
		List<String> exceptionList = new ArrayList<String>();
		InputStream inputStream = null;
		OutputStream out = null;
		try {
			
			  inputStream= new FileInputStream(fullPath);
			  
			  out = response.getPortletOutputStream();
			  byte buf[]=new byte[1024];
			  int len;
			  while((len=inputStream.read(buf))>0){
			  out.write(buf,0,len);
			  }
			  out.close();
			  inputStream.close();			
		} catch (IOException e) {
			exceptionList.add(e.getMessage());
		}finally{
			if(inputStream != null ){
				try {
					inputStream.close();
				} catch (IOException e) {
					LOGGER.debug("Exception in closing resource");
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					LOGGER.debug("Exception in closing resource");
				}
			}
			
		}
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
		 * This method is used for attachment timestamp
		 * @param fName  
		 * @return String 
		 * @throws LGSBusinessException  
		 */
		private String setTimestampInAttachment(String fName) throws LGSBusinessException{
	
			int index = fName.lastIndexOf(".");
	
			String fExtension = fName.substring(index);	
	
			fName = fName.substring(0, index);	
	
			String fNameFinal = fName + "_" + System.currentTimeMillis()+ fExtension;	
	
			return fNameFinal;
		}
	
		/**
		 * This method is used to retrieve the List of Bill To Address and set them in a select Box
		 * @param billToList 
		 * @param request  
		 * @return 
		 */
	private String getBillToOption(List<GenericAddress> billToList,RenderRequest request) {
		int loopCount = 0; //Add this variable for alternate row color on 11June
		StringBuilder sb = new StringBuilder();
		Map<String, GenericAddress> billToMap = new HashMap<String, GenericAddress>();
		String SELECT_STR = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, 
				"requestInfo.option.select", request.getLocale());
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
						sb.append("<br/>&nbsp;");
					}
				}
				if(billTo.getAddressLine2()!=null && !billTo.getAddressLine2().equals("")){
					sb.append(replaceNullWithBlankString(StringEscapeUtils.escapeJavaScript(billTo.getAddressLine2())));
					sb.append("<br/>&nbsp;");
				}
				
				if(billTo.getCity()!=null && !billTo.getCity().equals("")){
					sb.append(replaceNullWithBlankString(billTo.getCity()));
					if((billTo.getState()!=null && !billTo.getState().equals("")) || (billTo.getCountry()!=null && 
							!billTo.getCountry().equals(""))){
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
						sb.append("<br/>&nbsp;");
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
			
			//Adding Bill To List to Session
			request.getPortletSession().setAttribute("billToAddressMap", billToMap ,PortletSession.APPLICATION_SCOPE);
		} 
		return sb.toString();
	}

	/**
	 * This method is used to retrieve the Payment Type/Billing Model
	 * @param request  
	 * @param response  
	 * @throws Exception  
	 */	
	@ResourceMapping("getPaymentType")
	public void getPaymentType(ResourceRequest request, ResourceResponse response) throws Exception{
		LOGGER.debug("---------------- [IN] getPaymentType--------------------------");			
		
		//Retrieve Map from session and set the Bill To in the session
		PortletSession session = request.getPortletSession();
		GenericAddress billToAddress=new GenericAddress();
		if(request.getParameter("soldTo")!=null && request.getParameter("soldTo").equalsIgnoreCase("singleAddress")){
			
			billToAddress=(GenericAddress)session.getAttribute("selectedBillToAddress",PortletSession.APPLICATION_SCOPE);
		}else{
			Map<String, GenericAddress> billToMap = (Map<String, GenericAddress>) session.getAttribute("billToAddressMap",
					PortletSession.APPLICATION_SCOPE);
			billToAddress = billToMap.get(request.getParameter("soldTo"));
			session.removeAttribute("selectedBillToAddress",PortletSession.APPLICATION_SCOPE);
			session.setAttribute("selectedBillToAddress", billToAddress ,PortletSession.APPLICATION_SCOPE);			
		}
		Map<String,String> accDetails =(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,
				PortletSession.APPLICATION_SCOPE);
		/***start changes for Siebel Localization LOV***/
		PaymentListContract paymentContract = ContractFactory.getPaymentListContract(request, false);
		paymentContract.setSoldToNumber(billToAddress.getSoldToNumber());
		paymentContract.setContractNumber(accDetails.get("contractNumber"));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		paymentContract.setSessionHandle(crmSessionHandle);
		ObjectDebugUtil.printObjectContent(paymentContract, LEXLOGGER);
		List<String> exceptionList = new ArrayList<String>();
		long timeBeforeCall=System.currentTimeMillis();
		PaymentTypeListResult result = globalService.retrievePaymentTypeList(paymentContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERHARDWARE_MSG_RETRIEVEPAYMENTTYPELIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,paymentContract);
		LOGGER.info("start printing lex logger");
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE PAYMENT TYPE LIST ==>: " + (System.currentTimeMillis()- timeBeforeCall)/1000.0);
		LOGGER.info("end printing lex loggger");
		
		Map<String, String> requestTypeLOVMap= new TreeMap<String, String>();
		ArrayList<String> paymentType = result.getPaymentType();
		List<String> paymentTypeValueList=new ArrayList<String>();
		List<String> paymentTypeKeyList=new ArrayList<String>();
		PrintWriter out = response.getWriter();
		try {
			requestTypeLOVMap = commonController.retrieveLocalizedLOVMap
					(SiebelLocalizationOptionEnum.CONSUMABLE_PAYMENT_TYPE.getValue(), request.getLocale());
			
		} catch (LGSDBException e) {
			// TODO Auto-generated catch block
			exceptionList.add(e.getMessage());
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
		if(paymentTypeKeyList!=null && paymentTypeKeyList.size() == 1 && paymentTypeValueList!=null && 
				paymentTypeValueList.size() == 1){			
			Map<String,String> catalogDetailsValues=new HashMap<String,String>();
			catalogDetailsValues.put("paymentType", paymentTypeKeyList.get(0));
			catalogDetailsValues.put("paymentTypeText", paymentTypeValueList.get(0));			
			
			catalogDetailsValues.put("soldToNumber", billToAddress.getSoldToNumber());
			catalogDetailsValues.put("billToNumber", billToAddress.getBillToNumber());
			session.setAttribute(ChangeMgmtConstant.CATCURRDETAILS, catalogDetailsValues ,PortletSession.APPLICATION_SCOPE);
						
			Map<String,String> catDetails =(Map<String,String>)session.getAttribute(ChangeMgmtConstant.CATCURRDETAILS,
					PortletSession.APPLICATION_SCOPE);
			String showPriceFlag = accDetails.get("showPrice");
			String creditFlag = accDetails.get("creditCardFlag");
			String poFlag = accDetails.get("poFlag");
			String selectedPaymentType = catDetails.get("paymentType");
			PaymentUtil.setFinalCatalogFlags(session,showPriceFlag, creditFlag, poFlag, selectedPaymentType,"Catalog");
			responseBody.append("\"paymentTypeHtml\":\""+paymentTypeValueList.get(0)+"\"");
			responseBody.append(","+"\"singlePaymentType\":\"true\"");					
		}else{						
			responseBody.append("\"paymentTypeHtml\":\""+getXmlContentForPaymentType(paymentTypeKeyList,paymentTypeValueList,request.getLocale())+"\"");
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
	 * THis method is used to convert the list of Payment Type to the xml
	 * @param paymentTypeKeyList 
	 * @param paymentTypeValueList 
	 * @return 
	 */
	private String getXmlContentForPaymentType(List<String> paymentTypeKeyList,List<String> paymentTypeValueList,Locale locale) {
		StringBuilder sb = new StringBuilder();
		String noPaymentType = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"paymentType.notAvl", locale);
		if(paymentTypeKeyList.size()!=0){
			sb.append("<select id=\'paymentType\' name=\'paymentType\'>");
			sb.append("<option value=\'\'>"+PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.option.select", locale)+"</option>");
			for(int i=0;i<paymentTypeKeyList.size();i++) {
				sb.append("<option value=\'"+ 	XMLEncodeUtil.escapeXML(paymentTypeKeyList.get(i).toString().replaceAll(" ", "%20"))  + "\'>");
				sb.append(paymentTypeValueList.get(i).toString());
				sb.append("</option>");
			}
			sb.append("</select>");
		}else{
				sb.append("<select id=\'paymentType\'><option value=\'\'>"+noPaymentType+"</option></select>");
		}
		return sb.toString();
	}
	
	/**
	 * This method is used to set Catalog specific values to session along with all the Catalog specific Maps
	 * @param request  
	 * @param response  
	 * @return void  
	 * @throws Exception  
	 */
	@ResourceMapping(value="setCatalogValuesToSession")
	public void setCatalogValuesToSession(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		PortletSession portletSession = resourceRequest.getPortletSession();
		Enumeration<String> paramNames=resourceRequest.getParameterNames();
		Map<String,String> catalogDetailsValues=new HashMap<String,String>();
		while(paramNames.hasMoreElements()){
			String paramName=paramNames.nextElement();
			
			catalogDetailsValues.put(paramName, resourceRequest.getParameter(paramName));
		}
		GenericAddress selBillTo = (GenericAddress)portletSession.getAttribute("selectedBillToAddress", PortletSession.APPLICATION_SCOPE);
		
		
		catalogDetailsValues.put("soldToNumber", selBillTo.getSoldToNumber());
		catalogDetailsValues.put("billToNumber", selBillTo.getBillToNumber());
		
		portletSession.setAttribute(ChangeMgmtConstant.CATCURRDETAILS, catalogDetailsValues ,PortletSession.APPLICATION_SCOPE);
		
		Map<String,String> accDetails =(Map<String,String>)portletSession.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,
				PortletSession.APPLICATION_SCOPE);
		Map<String,String> catDetails =(Map<String,String>)portletSession.getAttribute(ChangeMgmtConstant.CATCURRDETAILS,
				PortletSession.APPLICATION_SCOPE);
		String showPriceFlag = accDetails.get("showPrice");
		String creditFlag = accDetails.get("creditCardFlag");
		String poFlag = accDetails.get("poFlag");
		String selectedPaymentType = catDetails.get("paymentType");
		PaymentUtil.setFinalCatalogFlags(portletSession,showPriceFlag, creditFlag, poFlag, selectedPaymentType,"Catalog");
		try{
			PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setContentType("text/plain");
			out.print("success");
			out.flush();
			out.close();
		}catch(IOException ie){
			LOGGER.debug("IO Exception occured.");
		}
	}	
	
	/**
	 * This method is used to remove Catalog specific values from session along with all the Catalog specific Maps
	 * @param resourceRequest 
	 * @param resourceResponse 
	 */
	@ResourceMapping(value="removeCatalogValuesFromSession")
	public void removeCatalogValuesFromSession(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		
		PortletSession session = resourceRequest.getPortletSession();
		Map<String,String> catalogDetails=(Map<String,String>)session.getAttribute(ChangeMgmtConstant.CATCURRDETAILS,
				PortletSession.APPLICATION_SCOPE);
		GenericAddress selBillTo=(GenericAddress)session.getAttribute("selectedBillToAddress",PortletSession.APPLICATION_SCOPE);
		Map<String,Boolean> catalogFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.CATFINALFLAGS,
				PortletSession.APPLICATION_SCOPE);
		if(catalogDetails != null){
			session.removeAttribute(ChangeMgmtConstant.CATCURRDETAILS,PortletSession.APPLICATION_SCOPE);			
		}
		if(catalogFinalFlags != null){
			session.removeAttribute(ChangeMgmtConstant.CATFINALFLAGS,PortletSession.APPLICATION_SCOPE);
		}
		List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
		if(catalogOrderListToSession!=null){
			session.removeAttribute("catalogOrderListToSession");
		}
		if(selBillTo != null){
			session.removeAttribute("selectedBillToAddress", PortletSession.APPLICATION_SCOPE);
		}
		
		
		try{
			PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setContentType("text/plain");
			out.print("success");
			out.flush();
			out.close();
		}catch(IOException ie){
			LOGGER.debug("IO Exception occured.");
		}
	}
	
	/**
	 * This method replaces null with a blank string
	 * @param value 
	 * @return 
	 */
	private Object replaceNullWithBlankString( Object value){
		if(value == null){
			return "";
		}
		return value;
	}

}


