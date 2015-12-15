/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: HwHistoryController.java
 * Package     		: com.lexmark.services.portlet.common
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		1.0             Initial Version
 * 
 *
 */

package com.lexmark.services.portlet.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.LocalizedPageCountNameContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.SRAdministrationListContract;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.SiebelLocalization;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.LocalizedPageCountNameResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.RequestResult;
import com.lexmark.result.SRAdministrationListResult;
import com.lexmark.result.SiebelLOVListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.RequestDetailForm;
import com.lexmark.services.form.ShipmentForm;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.SharedPortletController;
//import com.lexmark.services.tests.junitTests.CatalogControllerTest;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.PerformanceUtil; //Added for performance logging
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.LangUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author wipro
 * @version 2.1
 *
 */

@Controller
@RequestMapping("VIEW")
public class HwHistoryController extends BaseController{
	/**. Instance variable of wrapper logger class **/
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(HistoryController.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	/**. Name of this Class **/
	private static final   String SHIPMENT = "shipment";
	private static final   String PRODUCT_TLI = "productTLI";
	private static final String PROD_DESCRIPTN = "productDescription";
	private static final   String QUANTITY = "quantity";
	private static  final String STATUS = "status";
	private static  final String PARTNO = "partnumber";
	private static  final String PART_TYPE = "partType";
	private static  final String PENDING_QUANTITY = "quantity";
	private static  final String SNO = "serialNumber";
	private static  final String VENDORPRODUCT = "vendorProduct";
	private static  final String SHIPPED_QUANTITY = "shippedQuantity";
	private static  final String ASSET = "Asset";
	private static  final String CONTACT = "Contact";
	private static  final String ADDRESS = "Address";
	private static  final String CHL = "CHL";
	private static  final String OTHERS = "Others";
	//added for sub row fpr pending shipment
	private static final  String DEVICETYPE ="deviceType";
	private static final   String PRICE ="price";
	private static String CLASS_NAME = "HwHistoryController" ;
	/**. This variable holds AmindGlobalService bean reference **/
	@Autowired
	private GlobalService  globalService;
	
	/**. This variable holds AmindRequestTypeService bean reference **/
	@Autowired
	private RequestTypeService requestTypeService;
	
	/**. Holds the reference of CommonController bean **/
	@Autowired
	private CommonController commonController;
	
	/**. Holds ServiceRequestLocaleImpl bean reference  **/
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	
	/**. Holds AmindAttachmentService bean reference **/
	/*@Autowired
	private AttachmentService attachmentService;*/
	
	/**. Holds AmindContractedServiceRequestService bean reference **/
	/*@Autowired
	private ServiceRequestService serviceRequestService;*/
	
	/**. Holds the reference of SharedPortletController bean **/
	@Autowired
	private SharedPortletController sharedPortletController;
	
	/**. Holds the reference of ProductImageServiceImpl bean **/
	@Autowired
    private ProductImageService  productImageService;
	
	
	
	/**.
	 * Display the details of the Service Requests 
	 * @param model Model 
	 * @param request RenderRequest 
	 * @param response RenderResponse 
	 * @return String Request details page 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "action=showHardwareRequestDetails")
	public String showHardwareRequestDetails(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		String METHOD_NAME = "showHardwareRequestDetails";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		RequestDetailForm requestForm = new RequestDetailForm();
		String toJSP = ChangeMgmtConstant.HARDWAREREQUESTDETAILS;
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		
		String serviceRequestNumber = httpReq.getParameter(ChangeMgmtConstant.SERVICEREQNO);
		requestForm.setSrNumber(serviceRequestNumber);
		
		String timeZoneOffset = httpReq.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		
		String requestType =httpReq.getParameter(ChangeMgmtConstant.REQUESTTYPE);
		
		/*String srArea = "";
		if(httpReq.getParameter(ChangeMgmtConstant.SRAREA)!=null){
			srArea = httpReq.getParameter(ChangeMgmtConstant.SRAREA);
		}*/
		
		 if(ChangeMgmtConstant.HARDWARE.equalsIgnoreCase(requestType)){
			Map<String, String> requestTypeLOVMap=null;
			try {
				requestTypeLOVMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.REQUEST_TYPE.getValue(), request.getLocale());
				
				
				model.addAttribute("requestTypeLOVMap", requestTypeLOVMap);
				
				Map<String, String> requestAreaLOVMap = commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(), request.getLocale());
				
				LOGGER.debug("requestAreaLOVMap --->"+requestAreaLOVMap);
				model.addAttribute("requestAreaLOVMap", requestAreaLOVMap);
			} catch (LGSDBException dbExcption) {
				LOGGER.error("LGSBusinessException occurred ::: "+ dbExcption.getMessage());
			}
			
			SiebelLOVListResult requestStatusLOVList = globalService.retrieveSiebelLOVList(ContractFactory.createSiebelLOVListContract(SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(), null,null));
			
			
			if(requestStatusLOVList != null){
				
				Map<String, String> requestStatusLOVMap = getLovAsMap(requestStatusLOVList);
				
				model.addAttribute("requestStatusLOVMap", requestStatusLOVMap);
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

		
		if(ChangeMgmtConstant.HARDWARE.equalsIgnoreCase(requestType)){
			
			populateHardwareRequestDetails(request, response, model, requestForm, exceptionList, timeZoneOffset);
		}
		LOGGER.debug("return from populate");
		
		//Translated values for Area
	if(requestForm.getServiceRequest().getArea()!=null){
		String area = commonController.getLocalizeSiebelValue(requestForm.getServiceRequest().getArea().getValue(),SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(),request.getLocale());
		requestForm.getServiceRequest().getArea().setValue(area);
	}	
	if(requestForm.getServiceRequest().getSubArea()!=null){
		//Translated values for Sub Area
		String subArea = commonController.getLocalizeSiebelValue(requestForm.getServiceRequest().getSubArea().getValue(),SiebelLocalizationOptionEnum.REQUEST_SUBAREA.getValue(),request.getLocale());
		requestForm.getServiceRequest().getSubArea().setValue(subArea);
	}
		//Translated values for Status
		String status = commonController.getLocalizeSiebelValue(requestForm.getServiceRequest().getServiceRequestStatus(),SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(),request.getLocale());
		requestForm.getServiceRequest().setServiceRequestStatus(status);
	
		if(requestForm.getServiceRequest().getServiceRequestDate()!= null){
			
			String creationDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(requestForm.getServiceRequest().getServiceRequestDate(), Float.parseFloat(timeZoneOffset)), true, request.getLocale());
			
			requestForm.setCreationDate(creationDate);
			
		}
		
		
		PortletSession portletSession = request.getPortletSession();
		
		/**--Setting account details to session --Start--**/
		
		
		Map<String,String> accountDetailsValues =(Map<String,String>)portletSession.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
		if(accountDetailsValues==null){
			accountDetailsValues=new HashMap<String, String>();
		}
		LOGGER.debug(ChangeMgmtConstant.ACCOUNTID + " : "+ requestForm.getServiceRequest().getAccountId() + " " + ChangeMgmtConstant.ACCOUNTNAME+ " : "+ requestForm.getServiceRequest().getAccountName() + " " + ChangeMgmtConstant.COUNTRY + " : " + requestForm.getServiceRequest().getAccountCountry());
		accountDetailsValues.put(ChangeMgmtConstant.ACCOUNTID, requestForm.getServiceRequest().getAccountId());
		accountDetailsValues.put(ChangeMgmtConstant.ACCOUNTNAME, requestForm.getServiceRequest().getAccountName());
		accountDetailsValues.put(ChangeMgmtConstant.COUNTRY, requestForm.getServiceRequest().getAccountCountry());
				
		portletSession.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accountDetailsValues ,PortletSession.APPLICATION_SCOPE);
		
		/**Setting account details to session --End--**/
		
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID(ChangeMgmtConstant.DOWNLOADATTACHMENT);
		model.addAttribute(ChangeMgmtConstant.DOWNLOADATTACHMENT, resURL.toString());

		model.addAttribute(ChangeMgmtConstant.REQUESTFORM, requestForm);
		model.addAttribute(ChangeMgmtConstant.TIMEZNOFFSET, timeZoneOffset);
		
		
		model.addAttribute(ChangeMgmtConstant.MDMLEVELFROMDETAILS, PortalSessionUtil.getMdmLevel(portletSession));
		
		
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		
		return toJSP;
		
	}
	
	/**.
	 * Populates data for supply request details page.
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @param model Model
	 * @param requestForm RequestDetailForm
	 * @param exceptionList List<String>
	 * @param timeZoneOffset String
	 */
	private void populateHardwareRequestDetails(RenderRequest request, RenderResponse response,
			Model model, RequestDetailForm requestForm, List<String> exceptionList,String timeZoneOffset){
		String METHOD_NAME = "populateHardwareRequestDetails";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		String userSegment = PortalSessionUtil.getUserType(request.getPortletSession());
		model.addAttribute(ChangeMgmtConstant.USERSEGMENT, userSegment);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		RequestContract contract = new RequestContract();
		contract.setServiceRequestNumber(requestForm.getSrNumber());
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
			long timeAfterCall=System.currentTimeMillis();
			sharedPortletController.assembleDevice(requestResult.getServiceRequest().getAsset(), request.getLocale());			
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_RETRIEVESUPPLYREQUESTDETAIL, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			LOGGER.info("end printing lex loggger");
		}catch(Exception e){
			LOGGER.error("Exception occurred in Printing supply request details ::: "+ e.getMessage());
			requestResult.setServiceRequest(new ServiceRequest());
			exceptionList.add(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.historyDetails.exception", request.getLocale()) + " "+requestForm.getSrNumber());
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
					&& !serviceRequestShipments.isEmpty()) {
				requestForm.setServiceRequestShipments(serviceRequestShipments);
			}
					
			//Populate pending shipment grid
			
			/*Commented as part of defect 12369*/
			/*if(requestResult.getServiceRequest().getPendingShipments() != null && !requestResult.getServiceRequest().getPendingShipments().isEmpty()){
				LOGGER.debug("Pending Shipment List Not Null");
				this.setPendingQty(requestResult.getServiceRequest().getPendingShipments());
				requestForm.setPendingRequest(getPendingShipmentData(requestResult.getServiceRequest().getPendingShipments(), request.getLocale()));
			model.addAttribute("fromSource", "pendingShipment");
			}
			else{*/
				//added for subrow in pending shipment
			/*End Comment*/
			
			LOGGER.debug("Pending Shipment List Null. Populating from seperate place");
			ShipmentForm shipForm = new ShipmentForm();
			
			String[] pendingShipmentsGeneratorPatterns = new String[] {
					"partNumber", "description", "partType", "orderQuantity","deviceType","price"}; 
			
			String pendingShipmentsXML="";
			if(requestResult.getServiceRequest().getParts()!=null){
				LOGGER.debug(" Part List Size" + requestResult.getServiceRequest().getParts().size());
				 pendingShipmentsXML = getXmlOutputGenerator(
						request.getLocale()).generatePendingSHipment(requestResult.getServiceRequest().getParts(),requestResult.getServiceRequest().getParts().size(),0,pendingShipmentsGeneratorPatterns,request);
				if(pendingShipmentsXML != null){
					pendingShipmentsXML = pendingShipmentsXML.replace("\n", "");
				}
			}
			LOGGER.debug("before exception");
			shipForm.setShipmentXML(pendingShipmentsXML);
			LOGGER.debug("pendingShipmentsXML"+pendingShipmentsXML);
			requestForm.setPendingRequest(shipForm);
			model.addAttribute("fromSource", "parts");
			
			/*Commented as part of defect 12369*/
			/*}*/
			/*End Comment*/
			
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
				
			}
					//}
			String updateType ="";
			if(requestForm.getServiceRequest().getSubArea()!= null){
				String subArea1 = requestForm.getServiceRequest().getSubArea().getValue();
				LOGGER.debug("subArea1 >>>>>>>>>>>>>>>>>>." + subArea1);
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
				else{
					updateType = OTHERS;
				}
				model.addAttribute(ChangeMgmtConstant.UPDATETYPEFLAG, updateType);
			}
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
		
		List<PageCounts> pageCountList = requestForm.getServiceRequest().getPageCounts();
		List<PageCounts> modifiedPageCountList = new ArrayList<PageCounts>();
		if(pageCountList != null){
		for(int i=0;i<pageCountList.size();i++){			
			LOGGER.debug("page count value received from siebel is name "+pageCountList.get(i).getName()+
					" count "+pageCountList.get(i).getCount()+" date "+pageCountList.get(i).getDate());
			LocalizedPageCountNameResult pageCountResult = new LocalizedPageCountNameResult();
			LocalizedPageCountNameContract pageCountContract = new LocalizedPageCountNameContract();
			pageCountContract.setSiebelValue(pageCountList.get(i).getName());
			
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(pageCountContract,LOGGER);
			LOGGER.info("end printing lex loggger");
			
			pageCountResult = serviceRequestLocaleService.retrieveLocalizedPageCountName(pageCountContract);
			LOGGER.debug("Equivalent value received is "+pageCountResult.getLocalizedValue());
			PageCounts modifiedPageCount = new PageCounts();
			modifiedPageCount.setName(pageCountResult.getLocalizedValue());
			modifiedPageCount.setCount(pageCountList.get(i).getCount());
			modifiedPageCount.setDate(pageCountList.get(i).getDate());
			modifiedPageCount.setSiebelName(pageCountList.get(i).getName());
			modifiedPageCountList.add(modifiedPageCount);
			
		}
		}
		requestForm.getServiceRequest().setPageCounts(modifiedPageCountList);
		
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
				SNO,PARTNO, PROD_DESCRIPTN,PART_TYPE, VENDORPRODUCT,SHIPPED_QUANTITY};
				
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
				if(trackingNumber!= null){
					trackingUrl = shared.getTrackingUrl(carrier, trackingNumber);
					shipmentForm.setCarrierLink(trackingUrl);
					if(!("").equals(trackingUrl)){
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
				PARTNO,PARTNO, PROD_DESCRIPTN, PART_TYPE, PENDING_QUANTITY,DEVICETYPE,PRICE}; 
				
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
	 * @return 
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
	 * This utility method converts the LOV into a Map.
	 * @param lovResultObj 
	 * @return Map<String, String> 
	 */
	private Map<String, String> getLovAsMap(SiebelLOVListResult lovResultObj){
		String METHOD_NAME="getLovAsMap";
		LOGGER.enter(CLASS_NAME, METHOD_NAME );
		if(lovResultObj == null || lovResultObj.getLovList() == null || lovResultObj.getLovList().isEmpty()){
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
	 * This utility method to display the progress bar.
	 * @param status 
	 * @return double 
	 */
	private double checkProgress(String status) {
		
		double progress = 0;
		
			if (status.equalsIgnoreCase(ChangeMgmtConstant.SUBMITTED)) {
				progress = 33.3;
			} else if (status.equalsIgnoreCase(ChangeMgmtConstant.INPROGRESS) || status.equalsIgnoreCase(ChangeMgmtConstant.INPROCESS)) {
				progress = 66.6;
			} else if (status.equalsIgnoreCase(ChangeMgmtConstant.COMPLETED)){
				progress = 100;
			} else {
				progress = 33;
			}
		
		return progress;
	}

/*Added for MPS 2.1 for HardwareRequestPopUp START*/
/**.
 * Method is invoked when SR# is clicked from any of the details grids
 * @param request RenderRequest 
 * @param response RenderResponse 
 * @param model Model 
 * @throws Exception 
 * @return String 
 * 
 */
@RenderMapping(params="action=showHardwareDetailsPopup")
public String showHardwareDetailsPopup(Model model, RenderRequest request,
		RenderResponse response) throws Exception {
	String METHOD_NAME = "showHardwareDetailsPopup";
	LOGGER.enter(CLASS_NAME, METHOD_NAME );
	
	RequestDetailForm requestFormPopup = new RequestDetailForm();
	String toJSP = ChangeMgmtConstant.HARDWAREDETAILSPOPUP;
	List<String> exceptionList = new ArrayList<String>();
	HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
	String serviceRequestNumber = httpReq.getParameter(ChangeMgmtConstant.SERVICEREQNO);
	requestFormPopup.setSrNumber(serviceRequestNumber);
try {
	String timeZoneOffset = httpReq.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
	
	if (timeZoneOffset!=null) {
		model.addAttribute(ChangeMgmtConstant.TIMEZONEOFFSET_POPUP, timeZoneOffset);
		
	}
	
	String requestType =request.getParameter(ChangeMgmtConstant.REQUESTTYPE);
	
	httpReq.removeAttribute(ChangeMgmtConstant.TIMEZNOFFSET);
	
	if(ChangeMgmtConstant.FLEETMGMT.equalsIgnoreCase(requestType)){
		populateHardwareRequestDetails(request, response, model, requestFormPopup, exceptionList, timeZoneOffset);
		
	}
	
	String creationDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(requestFormPopup.getServiceRequest().getServiceRequestDate(), Float.parseFloat(timeZoneOffset)), true, request.getLocale());
	
	requestFormPopup.setCreationDate(creationDate);
			
	model.addAttribute(ChangeMgmtConstant.REQUESTFORM_POPUP, requestFormPopup);
			
	
	LOGGER.exit(CLASS_NAME, METHOD_NAME );
	return toJSP;
} catch (Exception ex) {
	logStackTrace(ex);
	exceptionList.clear();
	exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE,
			"exception.retrieve.Detail", null, request.getLocale())+ " "+ serviceRequestNumber);
	model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION, exceptionList);
	LOGGER.exit(CLASS_NAME, METHOD_NAME );
	return toJSP;
}
	
}
/**.
 * This utility method to print the SR.
 * @return String
 */
// Method added for print
@RequestMapping(params = "action=printHwDetails")
public String showHwHistoryPrint(){
	String METHOD_NAME = "showHwHistoryPrint";
	LOGGER.enter(CLASS_NAME, METHOD_NAME);
	LOGGER.exit(CLASS_NAME, METHOD_NAME);
	return "lgsHistoryDetails/hwDetailsPrint";
}
/**.
 * This utility method to email the SR.
 * @return String
 */
@RequestMapping(params = "action=emailHwDetails")
public String showHwHistoryEmail(){
	String METHOD_NAME = "ShowHwHistoryEmail";
	LOGGER.enter(CLASS_NAME, METHOD_NAME);
	LOGGER.exit(CLASS_NAME, METHOD_NAME);
	return "lgsHistoryDetails/hwDetailsEmail";
}

/*END*/

//Added for MPS 2.1 Wave 4 
/**.
* Method is invoked when SR# is clicked from any of the details grids
* @param request RenderRequest 
* @param response RenderResponse 
* @param model Model 
* @throws Exception 
* @return String 
* 
*/


@SuppressWarnings("finally")
@RenderMapping(params="action=showInstallDetailsPopup")
public String showInstallDetailsPopup(Model model, RenderRequest request,
		RenderResponse response) throws Exception {
	String METHOD_NAME = "showInstallDetailsPopup";
	LOGGER.enter(CLASS_NAME, METHOD_NAME );
	RequestDetailForm requestFormPopup = new RequestDetailForm();
	RequestContract contract = new RequestContract();
	HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
	String serviceRequestNumber = httpReq.getParameter(ChangeMgmtConstant.SERVICEREQNO);
	contract.setServiceRequestNumber(serviceRequestNumber);
	String userSegment = PortalSessionUtil.getUserType(request.getPortletSession());
	CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
	if(userSegment.equalsIgnoreCase(ChangeMgmtConstant.VENDORREQTYPE)){
		contract.setVisibilityRole(ChangeMgmtConstant.USERTYPE_PARTNER);
	}
	else{
		contract.setVisibilityRole(userSegment);
	}
	contract.setSessionHandle(crmSessionHandle);
	List<String> exceptionList = new ArrayList<String>();
	LOGGER.debug("serviceRequestNumber:" +serviceRequestNumber);
	RequestResult requestResult = new RequestResult();
try {
			
	requestFormPopup.setSrNumber(serviceRequestNumber);
	
	
	String timeZoneOffset = httpReq.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
	
	if (timeZoneOffset!=null) {
		model.addAttribute(ChangeMgmtConstant.TIMEZONEOFFSET_POPUP, timeZoneOffset);
	}
	ObjectDebugUtil.printObjectContent(contract,LOGGER);
	String requestType =request.getParameter(ChangeMgmtConstant.REQUESTTYPE);
	requestResult = requestTypeService.retrieveSupplyRequestActivities(contract);
	requestFormPopup.setServiceRequest(requestResult.getServiceRequest());

	httpReq.removeAttribute(ChangeMgmtConstant.TIMEZNOFFSET);
	LOGGER.debug("Request Type" +requestType);
	String[] activityGeneratorPattern = new String[] {
			"partNumber", "description", "orderQuantity"}; 
	if(requestResult.getServiceRequest()!=null && requestResult.getServiceRequest().getActivitywebUpdateActivities() != null && 
			requestResult.getServiceRequest().getActivitywebUpdateActivities().size() > 0){
		
		List<ServiceRequestActivity> sActivityList = requestResult.getServiceRequest().getActivitywebUpdateActivities();
		LOGGER.debug("sActivityList" +sActivityList);
		String serviceRequestTechnicianXML = getXmlOutputGenerator(request
				.getLocale()).getActivityDetails(sActivityList,
						sActivityList.size(), 0,
						activityGeneratorPattern);
		LOGGER.debug("serviceRequestTechnicianXML" + serviceRequestTechnicianXML);
		serviceRequestTechnicianXML = serviceRequestTechnicianXML.replaceAll("\n", "");
	requestFormPopup.setServiceRequestTechnicianXML(serviceRequestTechnicianXML);
		
	}
	else  {
		
		requestFormPopup.setServiceRequestTechnicianXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
	}
	if(requestResult.getServiceRequest()!=null && requestResult.getServiceRequest().getServiceActivityStatus()!= null){
		requestFormPopup.setServiceRequestTechnicianProgress(checkTechnicianProgress(requestResult.getServiceRequest().getServiceActivityStatus()));
		LOGGER.debug("Activity status:: "+requestResult.getServiceRequest().getServiceActivityStatus());
	}

	String creationDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(requestFormPopup.getServiceRequest().getServiceRequestDate(), Float.parseFloat(timeZoneOffset)), true, request.getLocale());
	
	requestFormPopup.setCreationDate(creationDate);
			
	model.addAttribute(ChangeMgmtConstant.REQUESTFORM_POPUP, requestFormPopup);
	
	LOGGER.debug(requestFormPopup.getServiceRequestTechnicianXML())	;	
	
	LOGGER.exit(CLASS_NAME, METHOD_NAME );
	
	//return toJSP;

	}catch (Exception ex) {
	
	logStackTrace(ex);
	exceptionList.clear();
	exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE,
			"exception.retrieve.Detail", null, request.getLocale())+ " "+ serviceRequestNumber);
	model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION, exceptionList);
	LOGGER.exit(CLASS_NAME, METHOD_NAME );
	//return toJSP;
	
	}finally{
		globalService.releaseSessionHandle(crmSessionHandle);
		return "lgsHistoryDetails/installReqPopup";	
	}
	
}

/**.
 * This utility method to print the SR.
 * @return String
 */

@RequestMapping(params = "action=printHwInstallDetails")
public String showHwInstallDetailPrint(){
	String METHOD_NAME = "showHwInstallDetailPrint";
	LOGGER.enter(CLASS_NAME, METHOD_NAME);
	LOGGER.exit(CLASS_NAME, METHOD_NAME);
	return "lgsHistoryDetails/installReqDetailsPrint";
}
/**
 * 
 * @return String 
 */
@RequestMapping(params = "action=emailHwInstallDetails")
public String showHwInstallDetailEmail(){
	String METHOD_NAME = "showHwInstallDetailEmail";
	LOGGER.enter(CLASS_NAME, METHOD_NAME);
	LOGGER.exit(CLASS_NAME, METHOD_NAME);
	return "lgsHistoryDetails/installReqDetailsEmail";
}
//END
}
