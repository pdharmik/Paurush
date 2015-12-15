package com.lexmark.service.impl.real.partnerOrderService;


import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;
import static com.lexmark.util.LangUtil.toInt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.source.OrderDetailContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.Order;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailActivityDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailOrderItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailOrderLineShipmentItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailPendingShipmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentItemOrderEntryDo;
import com.lexmark.service.impl.real.partnerOrderService.OrderServiceList;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;

public class AmindPartnerOrderDetailConversionUtil {
    
    //private static final Log logger = LogFactory.getLog(AmindPartnerOrderDetailConversionUtil.class);
	private static final Logger logger = LogManager.getLogger(AmindPartnerOrderDetailConversionUtil.class);

    private AmindPartnerOrderDetailConversionUtil() {
	}


    public static com.lexmark.domain.Order toOrder(SupplyRequestDetailDo detailDo, SupplyRequestDetailOrderItemDo orderItemdo, OrderDetailContract contract) {
        if (detailDo == null) {
            return null;
        }

        List<ServiceRequestOrderLineItem> processedParts = new ArrayList<ServiceRequestOrderLineItem>();
        
        com.lexmark.domain.Order order = new com.lexmark.domain.Order();
        SupplyRequestDetailOrderItemDo orderDo =  orderItemdo;
        order.setId(orderDo.getId());
        order.setOrderNumber(orderDo.getOrderNumber());
        order.setRequestedDeliveryDate(orderDo.getRequestedDeliveryDate());
        order.setServiceRequestNumber(orderDo.getServiceRequestNumber());
        order.setRequestNumber(orderDo.getServiceRequestNumber());
        order.setOrderDate(orderDo.getOrderDate());
        order.setAssetIdentifier(orderDo.getAssetIdentifier());
        order.setAttentionTo(orderDo.getAttentionTo());
        order.setInsideLocation(orderDo.getInsideLocation());
        order.setSpecialHandlingInstructions(orderDo.getSpecialHandlingInstructions());
        
        order.setSpecialInstructions(orderDo.getPortalSpecialInstruction());
        order.setMustResolveBy(orderDo.getMustResolveBy());

        order.setPrimaryContact(populatePrimaryContact(orderDo));
        order.setSecondaryContact(populateSecondaryContact(orderDo));
        order.setShippingAddress(populateServiceAddress(orderDo));
        populatePendingShipments(order, orderDo.getPendingShipments(),contract, processedParts);
        populateProcessedPartsFromShipment(order, orderDo.getShippedItems(),contract, processedParts);
        order.setProcessedParts(processedParts);
        processStatusForOrderLineItems(order);
        processServiceProviderReferenceNumber(order);
        order.setEmailActivities(populateEmailActivities(detailDo));
        
        Asset asset = new Asset();
        asset.setSerialNumber(orderDo.getSerialNumber());
        order.setAsset(asset);
        
        order.setRequestType(orderDo.getRequestType());
        order.setOrderType(orderDo.getOrderType());
        
        return order;
    }
    
    
     private static List<com.lexmark.domain.ServiceRequestActivity> populateEmailActivities(SupplyRequestDetailDo detailDo) {

        List<com.lexmark.domain.ServiceRequestActivity> emailActivities = new ArrayList<com.lexmark.domain.ServiceRequestActivity>();
        List<SupplyRequestDetailActivityDo> actions = detailDo.getActions();

        if (actions == null) {
            return emailActivities;
        }

        for (SupplyRequestDetailActivityDo action : actions) {
        	com.lexmark.domain.ServiceRequestActivity activity = new com.lexmark.domain.ServiceRequestActivity();

        	activity.setActivityId(action.getActivityId());
        	activity.setActivityDate(action.getCreatedDate());

        	if (action.getType().equalsIgnoreCase("Email - Outbound")) {
        		activity = populateEmailActivity(action, activity);
        		if (activity != null) {
        			emailActivities.add(activity);
        		}
        	}

        }

        return emailActivities;
    }
    
    private static com.lexmark.domain.ServiceRequestActivity populateEmailActivity(
            SupplyRequestDetailActivityDo action, com.lexmark.domain.ServiceRequestActivity activity) {

        String emailAddress = action.getEmailAddress();
        String contactEmailAddress = action.getContactEmail();

        if (isEmpty(contactEmailAddress)) {
            return null;
        }
        if (!emailAddress.contains(contactEmailAddress)) {
            return null;
        }

        activity.setRecipientEmail(action.getEmailAddress());
        activity.setActivityStatus(action.getStatus());

        String description = action.getDescription();
        if (isNotEmpty(description)) {
            activity.setActivityDescription(description);
        } else {
            activity.setActivityDescription(action.getOverrideDesc());
        }

        return activity;
    }
    
    static void processServiceProviderReferenceNumber(Order order){
    	if(LangUtil.isNotEmpty(order.getPendingShipments()) && LangUtil.isNotEmpty(order.getPendingShipments().get(0).getOrderReferenceNumber())){
    		order.setServiceProviderReferenceNumber(order.getPendingShipments().get(0).getOrderReferenceNumber());
    	} else if (LangUtil.isNotEmpty(order.getProcessedParts()) && LangUtil.isNotEmpty(order.getProcessedParts().get(0).getOrderReferenceNumber()))
    	{
    		order.setServiceProviderReferenceNumber(order.getProcessedParts().get(0).getOrderReferenceNumber());
    	}
    }
    static void processStatusForOrderLineItems(Order order) {
    	  
        List<ServiceRequestOrderLineItem> allOrderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
        if(LangUtil.isNotEmpty(order.getPendingShipments()) && order.getPendingShipments() != null){
        	 allOrderLineItems.addAll(order.getPendingShipments());
         }
        if(LangUtil.isNotEmpty(order.getProcessedParts()) && order.getProcessedParts() != null){
        		allOrderLineItems.addAll(order.getProcessedParts());
      	}
        order.setStatus(OrderServiceList.totalOrderSatus(allOrderLineItems));
    }
    static void populatePendingShipments(Order order, List<SupplyRequestDetailPendingShipmentDo> pendingShipments,
    		OrderDetailContract contract, List<ServiceRequestOrderLineItem> processedParts) {
        List<ServiceRequestOrderLineItem> orderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
            for (SupplyRequestDetailPendingShipmentDo itemDo : LangUtil.notNull(pendingShipments)) {
            	for(String contractVendorId: LangUtil.notNull(contract.getVendorAccountIds()))
            	{
            		if(contractVendorId.equalsIgnoreCase(itemDo.getVendorId()) && !"Cancelled".startsWith(itemDo.getStatus()))
            		{
            			ServiceRequestOrderLineItem item = new ServiceRequestOrderLineItem();
                        item.setId(itemDo.getId());
                        item.setPartName(itemDo.getPartName());
                        item.setPartType(itemDo.getPartType());
                        item.setPartnumber(itemDo.getPartNumber());
                        item.setEta(itemDo.getExpectedShipDate());
                        
                        item.setProductDescription(itemDo.getDescription());
                        item.setStatus(itemDo.getStatus());
                        item.setVendorId(itemDo.getVendorId());
                       
                        item.setBackOrderQuantity(toInt(itemDo.getBackOrderQuantity()));
                        item.setOrderedDate(itemDo.getOrderedDate());
                        
                        int requestedQuantity = toInt(itemDo.getRequestedQuantity());
                        int backOrderQuantity = toInt(itemDo.getBackOrderQuantity());
                        int shipmentQuantity = toInt(itemDo.getShippedQuantity());
                        int pendingQuantity = requestedQuantity - backOrderQuantity - shipmentQuantity;
                        item.setPendingQuantity(pendingQuantity);
                        item.setQuantity("" + requestedQuantity);
                        item.setOrderReferenceNumber(itemDo.getOrderReferenceNumber());
                        item.setPortalFulfillmentStatus(itemDo.getPortalFulfillmentStatus());
                        populateProcessedPartsFromPendingShipment(order, itemDo.getPendingShipmentItems(),itemDo.getVendorId(), processedParts);
                        orderLineItems.add(item);
            		}
            	}
            
            }
       
        order.setPendingShipments(orderLineItems);
    }
    
    private static ServiceRequestOrderLineItem populateProcessedPart(
    		SupplyRequestDetailShipmentItemDo itemDo, List<? extends SupplyRequestDetailShipmentItemOrderEntryDo> serialNumnerList ) {
		ServiceRequestOrderLineItem orderLineItem = new ServiceRequestOrderLineItem();
		orderLineItem.setId(itemDo.getId());
		orderLineItem.setCarrier(itemDo.getCarrier());
		orderLineItem.setEta(itemDo.getEta());
		orderLineItem.setActualDeliveryDate(itemDo.getActualDeliveryDate());
		orderLineItem.setPartName(itemDo.getDescription());
		orderLineItem.setPartnumber(itemDo.getPartNumber());
		orderLineItem.setVendorProduct(itemDo.getVendorProduct());
		orderLineItem.setShippedQuantity(toInt(itemDo.getShippedQuantity()));
		orderLineItem.setTrackingNumber(itemDo.getTrackingNumber());
		orderLineItem.setActualShipDate(itemDo.getActualShipDate());
		orderLineItem.setPartType(itemDo.getPartType());
		orderLineItem.setStatus(itemDo.getStatus());
		
		List<String> orderSerialNumnbers = new ArrayList<String>();
		for (SupplyRequestDetailShipmentItemOrderEntryDo itemOrderEntryDo : LangUtil.notNull(serialNumnerList)) {
			orderSerialNumnbers.add(toOrderSerialNummber(itemOrderEntryDo));
		}
		orderLineItem.setSerialNumbers(orderSerialNumnbers);
		return orderLineItem;
	}
    
    private static void populateProcessedPartsFromShipment(Order order, ArrayList<SupplyRequestDetailShipmentDo> shipmentItems,
    		OrderDetailContract contract, List<ServiceRequestOrderLineItem> processedParts) {
		for(SupplyRequestDetailShipmentDo shipmentDo : LangUtil.notNull(shipmentItems))
		{
			for (SupplyRequestDetailShipmentItemDo itemDo : LangUtil.notNull(shipmentDo.getShipmentLineItems())) {
				for(String contractVendorId: LangUtil.notNull(contract.getVendorAccountIds()))
            	{
            		if(contractVendorId.equalsIgnoreCase(shipmentDo.getVendorId()))
            		{
            			ServiceRequestOrderLineItem orderLineItem = populateProcessedPart(itemDo, itemDo.getShipmentOrderEntryItems());
        				orderLineItem.setVendorId(shipmentDo.getVendorId());
        				orderLineItem.setOrderReferenceNumber(shipmentDo.getOrderReferenceNumber());
        				orderLineItem.setPortalFulfillmentStatus(shipmentDo.getPortalFulfillmentStatus());
        				orderLineItem.setNetPrice(new BigDecimal(shipmentDo.getNetPrice()));
        				orderLineItem.setSupplyType(shipmentDo.getSupplyType());
        				processedParts.add(orderLineItem);
            		}
            	}
			}
		}
		
	}

    private static void populateProcessedPartsFromPendingShipment(Order order, ArrayList<SupplyRequestDetailOrderLineShipmentItemDo> shipmentItems, String vendorId, List<ServiceRequestOrderLineItem> processedParts) {
		List<ServiceRequestOrderLineItem> orderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
		for (SupplyRequestDetailOrderLineShipmentItemDo itemDo : LangUtil.notNull(shipmentItems)) {
				ServiceRequestOrderLineItem orderLineItem = populateProcessedPart(itemDo, itemDo.getShipmentOrderLineItems());

				orderLineItem.setVendorId(vendorId);
				processedParts.add(orderLineItem);
			}
		
		
	}
    
    private static String toOrderSerialNummber(SupplyRequestDetailShipmentItemOrderEntryDo itemOrderEntryDo) {
        String op = itemOrderEntryDo.getSerialNumber();
        return op;
    }

    static void populateServerRequestActivities(ServiceRequest request, SupplyRequestDetailDo requestDetailDo) {
        List<com.lexmark.domain.ServiceRequestActivity> activities = new ArrayList<com.lexmark.domain.ServiceRequestActivity>();
        for (SupplyRequestDetailActivityDo activityDo : LangUtil.notNull(requestDetailDo.getActions())) {
            com.lexmark.domain.ServiceRequestActivity activity = new com.lexmark.domain.ServiceRequestActivity();
            activity.setActivityId(activityDo.getActivityId());
            activity.setActivityDate(activityDo.getCreatedDate());
            activity.setActivityStatus(activityDo.getStatus());
            activity.setRecipientEmail(activityDo.getEmailAddress());
            activity.setActivityDescription(activityDo.getDescription());
            activities.add(activity);
        }
        request.setActivitywebUpdateActivities(activities);
        request.setServicewebUpdateActivities(activities);
        request.setEmailActivities(activities);
    }

   
    private static GenericAddress populateServiceAddress(SupplyRequestDetailOrderItemDo orderDo) {
        GenericAddress serviceAddress = new GenericAddress();
        serviceAddress.setAddressLine1(orderDo.getServiceAddress1());
        serviceAddress.setAddressLine2(orderDo.getServiceAddress2());
        serviceAddress.setAddressLine3(orderDo.getServiceAddress3());
        serviceAddress.setCity(orderDo.getServiceCity());
        serviceAddress.setCountry(orderDo.getServiceCountry());
        serviceAddress.setState(orderDo.getServiceState());
        serviceAddress.setPostalCode(orderDo.getServicePostalCode());
        serviceAddress.setStoreFrontName(orderDo.getStoreFrontName());
        serviceAddress.setProvince(orderDo.getServiceProvince());
        serviceAddress.setCounty(orderDo.getCounty());
        serviceAddress.setDistrict(orderDo.getDistrict());
        serviceAddress.setOfficeNumber(orderDo.getOfficeNumber());
        return serviceAddress;
    }
    
    private static AccountContact populatePrimaryContact(SupplyRequestDetailOrderItemDo orderDo) {
        AccountContact primaryContact = new AccountContact();
        primaryContact.setFirstName(orderDo.getPrimaryContactFirstName());
        primaryContact.setLastName(orderDo.getPrimaryContactLastName());
        primaryContact.setEmailAddress(orderDo.getPrimaryContactEmailAddress());
        primaryContact.setWorkPhone(orderDo.getPrimaryContactWorkPhone());
    
        return primaryContact;
    }
    
    private static AccountContact populateSecondaryContact(SupplyRequestDetailOrderItemDo orderDo) {
        AccountContact secondaryContact = new AccountContact();
        secondaryContact.setFirstName(orderDo.getSecondaryContactFirstName());
        secondaryContact.setLastName(orderDo.getSecondaryContactLastName());
        secondaryContact.setEmailAddress(orderDo.getSecondaryContactEmailAddress());
        secondaryContact.setWorkPhone(orderDo.getSecondaryContactWorkPhone());
    
        return secondaryContact;
    }

}
