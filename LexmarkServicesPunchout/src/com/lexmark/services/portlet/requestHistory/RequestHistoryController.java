package com.lexmark.services.portlet.requestHistory;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.RequestTypeServiceB2B;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.real.jdbc.ServiceRequestLocaleImpl;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.form.RequestDetailsForm;
import com.lexmark.services.form.ShipmentForm;
import com.lexmark.services.mock.GenerateMockData;
import com.lexmark.services.servlet.LoadAccountInformation;

import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ControllerUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.XmlOutputGenerator;
import com.lexmark.util.LangUtil;
import com.lexmark.util.StringUtil;



/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: RequestHistoryController.java
 * Package     		: com.lexmark.portlet
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */

/**
 * @author wipro
 * @version 1.0
 */
@Controller
@RequestMapping("VIEW")
public class RequestHistoryController {
	
	@Autowired
	private RequestTypeServiceB2B requestTypeB2bService;
	
	@Autowired
	private LoadAccountInformation allAccountInformation;
	
	@Autowired
	private GlobalService globalService;
	
	private static final String PROD_DESCRIPTN = "productDescription";
	private static String CLASS_NAME = "RequestHistoryController.java" ;
	private static final String PARTNO = "partnumber";
	private static final String PART_TYPE = "partType";
	private static final String PRICE = "price";
	private static final String DEVICE_TYPE = "deviceType";

	private static final String PENDING_QUANTITY = "quantity";
	private static final String TYPE_HARDWARE = "Hardware";
	private static final String TYPE_SUPPLIES = "Supplies Request";
	private static Logger LOGGER = LogManager
			.getLogger(RequestHistoryController.class);

	private static final String PATH="requests/history/";
	private ServiceRequestLocale  serviceRequestLocale;
	private static final String SHIPMENT = "shipment";
	private static final String RETURN = "return";
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
	 * @param serviceRequestLocale 
	 */
	public void setServiceRequestLocale(ServiceRequestLocale serviceRequestLocale) {
		this.serviceRequestLocale = serviceRequestLocale;
	}
	/**
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@ResourceMapping("loadHistoryList")
	public String showHistoryList(ResourceRequest request,
			ResourceResponse response) {
		LOGGER.debug(("[ In  showHistoryList ]"));

		return PATH+"gridRequestHistory";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@ResourceMapping("retrieveGrid")
	public String retrieveGrid(ResourceRequest request,ResourceResponse response,Model model) throws Exception{
		
		LOGGER.debug(("[ in retrieveGrid]"));
		PortletSession session = request.getPortletSession();
		String supplierId = ControllerUtil.getSupplierId(session);
		request.setAttribute(PunchoutConstants.PUNCHOUT_ACCOUNT, ControllerUtil.getPunchoutAccount(allAccountInformation.getAllAccountList(supplierId), request));
		
		
		RequestListResult result=null;
		CrmSessionHandle crmSessionHandle=null;
		try{
	RequestListContract contract=ContractFactory.getRequestListContract(request);
		crmSessionHandle= globalService
	.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
	contract.setSessionHandle(crmSessionHandle);
	ObjectDebugUtil.printObjectContent(contract, LOGGER);
		result=requestTypeB2bService.retrieveRequestListB2B(contract);
		
		//GenerateMockData generateMock= new GenerateMockData();
		//result = generateMock.generateRequestListResult();
		}catch(Exception e){
			LOGGER.debug("Exception occured" + e.getMessage());
			LOGGER.error("[Exception occured in retrieving the list ]");
		}finally{			
			if(result==null){
				result=new RequestListResult();
			}
		}
		model.addAttribute(PunchoutConstants.GRID_DATA, result.getRequestList());
		model.addAttribute(PunchoutConstants.TOTAL_COUNT, result.getTotalCount());
		response.setContentType("text/xml");
		LOGGER.debug(("[ out retrieveGrid]"));
		return PATH+"requestGridXml";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 */
	@ResourceMapping("loadRequestDetails")
	public String loadRequestDetails(ResourceRequest request,
			ResourceResponse response ,Model model) {
		LOGGER.debug(("[ In  loadRequestDetails ]"));
		
		RequestResult result=null;
		CrmSessionHandle crmSessionHandle=null;
		ServiceRequest serviceRequest = new ServiceRequest();
		try{
			RequestContract contract=ContractFactory.getRequestDetailsContract(request);
		crmSessionHandle= globalService
		.initCrmSessionHandle(PortalSessionUtil
				.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
				
		ObjectDebugUtil.printObjectContent(contract, LOGGER);
		result=requestTypeB2bService.retrieveSupplyRequestDetailB2B(contract);
		
		serviceRequest= result.getServiceRequest();
		serviceRequest.setItemSubTotalBeforeTax(serviceRequest.getItemSubTotalBeforeTax().setScale(2, RoundingMode.CEILING));
		serviceRequest.setTotalAmount(serviceRequest.getTotalAmount().setScale(2, RoundingMode.CEILING));
		}
		catch (Exception e) {
			LOGGER.debug("Exception occured" + e.getMessage());
		}
		
		RequestDetailsForm requestDetails = new RequestDetailsForm();
		
		requestDetails.setServiceRequest(serviceRequest);
		if(serviceRequest.getArea().getValue().equalsIgnoreCase("HW Order")){
			populateShipmentDetailsForHardware(request,result,requestDetails);
		}
		else{
			populateShipmentDetailsForSupplies(request,result,requestDetails);
		}
		
		List<ServiceRequestOrderLineItem> shipmentList = result.getServiceRequest().getShipmentOrderLines();
		
		Map<String,String> shipmentXmlMap=new HashMap<String,String>();
		for(ServiceRequestOrderLineItem shipment : shipmentList){
			shipment.setShipmentProgress(checkProgress(shipment.getStatus(), SHIPMENT));
			String xml = getXmlOutputGenerator(request.getLocale()).generateServiceRequestShipment(shipment, request);
			shipmentXmlMap.put(shipment.getPartnumber(), xml);
			
		}
		requestDetails.getServiceRequest().setShipmentOrderLines(shipmentList);
		model.addAttribute("shipmentXmlMap", shipmentXmlMap);
		model.addAttribute(PunchoutConstants.REQUEST_DETAILS,requestDetails);
		return PATH+"requestDetails";
	}
	

	

/**.
 * Populated pending shipment data
 * @param pendingShipments List<ServiceRequestOrderLineItem>
 * @param locale Locale
 * @return ShipmentForm
 */
private ShipmentForm getPendingShipmentData(List<ServiceRequestOrderLineItem> pendingShipments, Locale locale){
	String METHOD_NAME = "getPendingShipmentData";
	
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
	
	return shipForm;
	
}
public XmlOutputGenerator getXmlOutputGenerator(Locale locale) {
	XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
	xmlOutputGenerator.setServiceRequestLocale(serviceRequestLocale);
	return xmlOutputGenerator;
}

private void populateShipmentDetailsForSupplies(ResourceRequest request,RequestResult result,RequestDetailsForm requestDetails){
	if(result.getServiceRequest().getPendingShipments() != null && !result.getServiceRequest().getPendingShipments().isEmpty()){
		this.setPendingQty(result.getServiceRequest().getPendingShipments());
		
		
		requestDetails.setPendingRequest(getPendingShipmentData(result.getServiceRequest().getPendingShipments(), request.getLocale()));
	}
	else{
		List<ServiceRequestOrderLineItem> orderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
		for (Part part : LangUtil.notNull(result.getServiceRequest().getParts())) {
				if(!"Authorized".equalsIgnoreCase(part.getStatus())){
					ServiceRequestOrderLineItem item = new ServiceRequestOrderLineItem();
					item.setPartnumber(part.getPartNumber());
					if(part.getImplicitFlag()){
						item.setProductDescription("Lexmark Recommended");
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
					for(ServiceRequestOrderLineItem shippedItems : result.getServiceRequest().getShipmentOrderLines()){
						if(shippedItems.getPartName().equalsIgnoreCase(part.getPartNumber())){
							itemStatus = 1;
						}
					}
					if(itemStatus == 0){
						itemAuthorized.setPartnumber(part.getPartNumber());
						if(part.getImplicitFlag()){
							itemAuthorized.setProductDescription("Lexmark Recommended");
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
		requestDetails.setPendingRequest(getPendingShipmentData(orderLineItems, request.getLocale()));
	}
	
	
	
}

private void populateShipmentDetailsForHardware(ResourceRequest request,RequestResult result,RequestDetailsForm requestDetails){
	
	ShipmentForm shipForm = new ShipmentForm();
	
	String[] pendingShipmentsGeneratorPatterns = new String[] {
			"partNumber", "description", "partType", "orderQuantity","deviceType","price"}; 
	
	String pendingShipmentsXML="";
	if(result.getServiceRequest().getParts()!=null){		
		 pendingShipmentsXML = getXmlOutputGenerator(
				request.getLocale()).generatePendingSHipment(result.getServiceRequest().getParts(),result.getServiceRequest().getParts().size(),0,pendingShipmentsGeneratorPatterns,request);
		if(pendingShipmentsXML != null){
			pendingShipmentsXML = pendingShipmentsXML.replace("\n", "");
		}
	}
	shipForm.setShipmentXML(pendingShipmentsXML);
	requestDetails.setPendingRequest(shipForm);	
	
}

private void setPendingQty(List<ServiceRequestOrderLineItem> pendingShipments){
	String METHOD_NAME = "setPendingQty";
	
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
		lineItem.setQuantity(String.valueOf(actualQty));			
	}
		
}

public int checkProgress(String status, String type) {
	int progress = 0;
	if (SHIPMENT.equals(type)) {
		if (LexmarkConstants.SHIPMENTSTATUS_SHIPPED.equals(status)) {
			progress = 66;
		} else if (LexmarkConstants.SHIPMENTSTATUS_DELIVERED.equals(status)) {
			progress = 100;
		} else if (LexmarkConstants.TECHNICIANSTATUS_CANCELLED.equals(status)){
			return 0;
		} else {
			progress = 33;
		}
	} else if (RETURN.equals(type)) { 
		if (LexmarkConstants.SHIPMENTSTATUS_DELIVERED.equals(status)) {
			progress = 100;
		} else if (LexmarkConstants.TECHNICIANSTATUS_CANCELLED.equals(status)){
			return 1;
		} else {
			progress = 50;
		}
	}
	return progress;
}

}