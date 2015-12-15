package com.lexmark.service.impl.real.requestService;

import static com.lexmark.util.LangUtil.toInt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Order;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.service.impl.real.domain.ActivityPartListDo;
import com.lexmark.service.impl.real.domain.RequestTypeActivitiesDo;
import com.lexmark.service.impl.real.domain.RequestTypeDo;
import com.lexmark.service.impl.real.domain.RequestTypeFavoriteAssetDo;
import com.lexmark.service.impl.real.domain.RequestTypePartBaseDo;
import com.lexmark.service.impl.real.domain.ServiceRequestActivitiesAttachmentDo;
import com.lexmark.service.impl.real.domain.ServiceRequestDetailContactDo;
import com.lexmark.service.impl.real.domain.SupplyAccountContactDetailDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailActivityDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailAttachmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailCustomerOrderItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailOrderItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailOrderLineShipmentItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailPageCountDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailPendingShipmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentItemDo;
import com.lexmark.service.impl.real.domain.SupplyRequestDetailShipmentItemOrderEntryDo;
import com.lexmark.service.impl.real.domain.SupplyRequestLbsLineItemsDo;
import com.lexmark.service.impl.real.domain.SupplyRequestOrderedPartsDo;
import com.lexmark.service.impl.real.domain.SupplyRequestRecommendedPartsDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import com.lexmark.util.StringUtil;

public class AmindRequestTypeConversionUtil {
    
   // private static final Log logger = LogFactory.getLog(AmindRequestTypeConversionUtil.class);
	 private static final Logger logger = LogManager.getLogger(AmindRequestTypeConversionUtil.class);
    private static final String BUILDING = "Building";
    private static final String OFFICE = "Office";
    private static final String FLOOR = "Floor";
	
	public  static <T extends RequestTypeDo> List<com.lexmark.domain.ServiceRequest> convertRequestTypeList(
			List<T> requestDoList) {
		
		List<ServiceRequest> requestList = new ArrayList<com.lexmark.domain.ServiceRequest>();

		if (requestDoList == null) {
			return requestList;
		}

		for (T requestDo : requestDoList) {
			ServiceRequest request = new ServiceRequest();
			
			request.setServiceRequestNumber(requestDo.getRequestNumber());
			request.setCostCenter(requestDo.getCostCenter());
			request.setServiceRequestType(createListOfValues(requestDo.getServiceRequestType()));
			request.setServiceRequestStatus(requestDo.getServiceRequestStatus());  // Changes Done by Arunava for LEX:AIR00070769
			request.setServiceRequestDate(requestDo.getServiceRequestDate());
			request.setStatusType(createListOfValues(requestDo.getStatusType()));
			request.setAccountName(requestDo.getAccountName());
			request.setResolutionCode(requestDo.getResolutionCode());
			request.setProblemDescription(requestDo.getProblemDescription());
			request.setWebOrderNumber(requestDo.getWebOrderNumber());
			request.setSerialNumber(requestDo.getSerialNumber());
			
			if (requestDo.getServiceRequestETA() == null
					|| "".equals(requestDo.getServiceRequestETA())) {
				request.setServiceRequestETA(requestDo.getServiceRequestSLA());
			} else {
				request.setServiceRequestETA(requestDo.getServiceRequestETA());
			}
			
			// Asset
			request.setAsset(populateAsset(requestDo));
			request.setProblemDescription(requestDo.getProblemDescription());
			request.setExpediteOrder(requestDo.getExpediteOrder());
			request.setArea(createListOfValues(requestDo.getArea()));
			request.setSubArea(createListOfValues(requestDo.getSubArea()));
			request.setHelpdeskReferenceNumber(requestDo.getHelpDeskReferenceNumber());
			request.setEta(requestDo.getEta());
			request.setPoNumber(requestDo.getPoNumber());
			request.setContractType(requestDo.getContractType());
			request.setPrimaryContact(populatePrimaryContact(requestDo));
			request.setRequestor(populateRequestorContact(requestDo));
			request.setServiceAddress(populateServiceAddress(requestDo));
			
			//OPS
			request.setRequestStatus(requestDo.getServiceRequestStatus());
			request.setSubStatus(requestDo.getSubStatus()); 
			request.setSeverity(requestDo.getSeverity());
			request.setProjectName(requestDo.getProjectName());
			request.setProjectPhase(requestDo.getProjectPhase());
			request.setAgreementName(requestDo.getAgreementName());
			request.setAgreementNumber(requestDo.getAgreementNumber());
			request.setParts(populateParts(requestDo));
			
			if (requestDo.getServiceOverrideType() == null || "".equals(requestDo.getServiceOverrideType())) {
				request.setCoveredService(requestDo.getCoveredServiceType());
			} else {
				request.setCoveredService(requestDo.getServiceOverrideType());
			}
		
			requestList.add(request);
		}
		
		return requestList;
	}
	
	 public static com.lexmark.domain.ServiceRequest convertAccountContactTypeDetail(SupplyAccountContactDetailDo accountContactDetailDo, List<SupplyAccountContactDetailDo> detailList) {
	        com.lexmark.domain.ServiceRequest request = new com.lexmark.domain.ServiceRequest();
	        if (accountContactDetailDo == null) {
	        	
	        	return request;
	        }
	       
	        populateAccountContact(request, detailList);
	        
	        
	        return request;
	 }/**/
	 
	 
    public static com.lexmark.domain.ServiceRequest convertRequestTypeDetail(SupplyRequestDetailDo requestDetailDo, boolean madcServiceRequestFlag) {
        com.lexmark.domain.ServiceRequest request = new com.lexmark.domain.ServiceRequest();

        if (requestDetailDo == null) {
            return request;
        }
         
		request.setOtherRequestedService(LangUtil.isBlank(requestDetailDo.getServiceOverrideType()) ? requestDetailDo.getRequestedService(): requestDetailDo.getServiceOverrideType());
		request.setId(requestDetailDo.getId());
        request.setServiceRequestNumber(requestDetailDo.getServiceRequestNumber());
        request.setServiceRequestDate(requestDetailDo.getServiceRequestDate());
        request.setServiceRequestStatusDate(requestDetailDo.getStatusDate());
        request.setCustomerReferenceNumber(requestDetailDo.getCustomerReferenceId());
        request.setExpediteOrder(populateRequestExpediteOrder(requestDetailDo));
        request.setServiceRequestStatus(requestDetailDo.getStatus());
        request.setCostCenter(requestDetailDo.getMadcAssetCostCenter());
        request.setAddtnlDescription(requestDetailDo.getAdditionalDetails());
        request.setPoNumber(requestDetailDo.getCustomerPONumber());
        request.setNotes(requestDetailDo.getAttachmentNotes());
        request.setRequestedEffectiveDate(requestDetailDo.getRequestedEffectiveDate());
        request.setSpecialInstructions(requestDetailDo.getPortalSpecialInstruction());
        request.setDefaultSpecialInstructions(requestDetailDo.getDefaultSpecialInstruction());
        request.setAccountId(requestDetailDo.getAccountId());
        request.setAccountName(requestDetailDo.getAccoutName());
        request.setAccountCountry(requestDetailDo.getAccountCountry());
        request.setResolutionCode(requestDetailDo.getResolutionCode());
        request.setArea(createListOfValues(requestDetailDo.getArea()));
        request.setSubArea(createListOfValues(requestDetailDo.getSubArea()));
        request.setOrderSource(requestDetailDo.getOrderSource());
        request.setServiceAddress(populateServiceAddress(requestDetailDo));  
        request.setRequestor(populateRequestorContact(requestDetailDo));   
        request.setSecondaryContact(populateSecondaryContact(requestDetailDo));  
        request.setPrimaryContact(populatePrimaryContact(requestDetailDo));  
        request.setAsset(populateAsset(requestDetailDo, madcServiceRequestFlag));  
        request.setInstallAddress(populateInstallAddress(requestDetailDo, madcServiceRequestFlag));
        request.setSoldToNumber(requestDetailDo.getSoldToNumber());
        request.setFccCode(requestDetailDo.getFccc());
        //OPS
        request.setAgreementName(requestDetailDo.getAgreementName());
        request.setAgreementNumber(requestDetailDo.getAgreementNumber());
        request.setCustomerRequestDateTime(requestDetailDo.getCustomerRequestDateTime());
        request.setCommittedDateTime(requestDetailDo.getCommittedDateTime());
        request.setRequestStatus(requestDetailDo.getRequestStatus());
        request.setSubStatus(requestDetailDo.getSubStatus());
        request.setSeverity(requestDetailDo.getSeverity());
        request.setProjectName(requestDetailDo.getProjectName());
        request.setProjectPhase(requestDetailDo.getProjectPhase());
        request.setOrders(populateOrders(requestDetailDo));
        populateLangDescription(request,requestDetailDo);				//Changes for 13403
        populateServiceActivityStatus(request, requestDetailDo);
        populateAttachments(request, requestDetailDo);
        populateServerRequestActivities(request, requestDetailDo, madcServiceRequestFlag);
        processPendingShipments(request, requestDetailDo);
        processShippedItems(request, requestDetailDo);
        populatePageCounts(request, requestDetailDo);
        populateParts(request, requestDetailDo);
        request.setCurrency(pupulateCurrency(AmindServiceUtil.removeNulls(LangUtil.notNull(requestDetailDo.getCustomerOrderLineItems()))));
        request.setProblemDescription(requestDetailDo.getProblemDescription());
        //fields added as per email "Map Change Request1.ppt" 
        request.setLbsDeviceinfo(requestDetailDo.getLbsDeviceinfo());				
        request.setLbsLexmarkInstall(requestDetailDo.getLbsLexmarkInstall());
        
       // request.setItemSubTotalBeforeTax(requestDetailDo.getItemSubTotalBeforeTax());
        //TODO: process customer order Item to get Tax and total and for Sub total : Subtract tax from total
/*        request.setTax(requestDetailDo.getTax());
        request.setTotalAmount(requestDetailDo.getTotal());*/
        populatePaymentDetails(request, requestDetailDo);
        AmindServiceUtil.populatePaymentMethod(request,requestDetailDo.getPaymentMethod());
        request.setBillingModel(requestDetailDo.getBillingModel());
        
        request.setHelpdeskReferenceNumber(requestDetailDo.getHelpdeskReferenceNumber());
        
        if(LangUtil.isNotEmpty(requestDetailDo.getActions())){
        request.setRecommendedParts(populateRecommendedParts(requestDetailDo.getActions()));
        }
        
        if(LangUtil.isNotEmpty(requestDetailDo.getOrderItems())){
            request.setOrderedParts(populateOrderedParts(requestDetailDo.getOrderItems()));
        }
        
        
        return request;
    }
    
	private static List<Order> populateOrders(SupplyRequestDetailDo requestDetailDo) {
		List<Order> orders = new ArrayList<Order>();
		List<SupplyRequestDetailOrderItemDo> orderItems = requestDetailDo.getOrderItems();
		for (SupplyRequestDetailOrderItemDo orderItemDo : LangUtil.notNull(orderItems)) {
			
			Order order = new Order();
			order.setSubStatus(orderItemDo.getSubStatus());
			order.setSeverity(orderItemDo.getSeverity());
			order.setOrderNumber(orderItemDo.getOrderNumber());
			order.setOrderType(orderItemDo.getOrderType());
			order.setOrderLineItems(populateOrderLineItems(orderItemDo));
			orders.add(order);
		}
		
		return orders;
	}

	private static List<ServiceRequestOrderLineItem> populateOrderLineItems(SupplyRequestDetailOrderItemDo orderItemDo) {
		List<SupplyRequestLbsLineItemsDo> orderedParts = orderItemDo.getLbsOrderLineItems();
		List<ServiceRequestOrderLineItem> orderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
		for (SupplyRequestLbsLineItemsDo orderedPartsDo : LangUtil.notNull(orderedParts)) {
			ServiceRequestOrderLineItem orderLineItem = new ServiceRequestOrderLineItem();
			orderLineItem.setStatus(orderedPartsDo.getStatus());
			
			if (orderedPartsDo.getSourceInventoryLocation() == null) {
				orderLineItem.setServiceProviderName(orderedPartsDo.getDestinationInventoryLocation());
			} else {
				orderLineItem.setServiceProviderName(orderedPartsDo.getSourceInventoryLocation());
			}
			orderLineItems.add(orderLineItem);
		}	
		
		return orderLineItems;
	}

	private static List<Part> populateOrderedParts(ArrayList<SupplyRequestDetailOrderItemDo> ordered) {
		List<SupplyRequestOrderedPartsDo> orderedParts = new ArrayList<SupplyRequestOrderedPartsDo>();
		List<Part> orderedPartsList = new ArrayList<Part>();
    	
		
		for (SupplyRequestDetailOrderItemDo supplyRequestOrderedPartsDo : ordered) {
			
			if(LangUtil.isNotEmpty(supplyRequestOrderedPartsDo.getOrderedParts())){
				
				for (SupplyRequestOrderedPartsDo orderedPrts : supplyRequestOrderedPartsDo.getOrderedParts()) {
					//SupplyRequestOrderedPartsDo orderedPartsConverted = new SupplyRequestOrderedPartsDo();
					Part orderedPartsConverted = new Part();
					
					orderedPartsConverted.setOrderedCarrierCode(orderedPrts.getCarrierCode());
					orderedPartsConverted.setOrderedExpediateShippingFlg(orderedPrts.getExpediateShippingFlg());
					orderedPartsConverted.setOrderedPartDisposition(orderedPrts.getPartDisposition());
					orderedPartsConverted.setOrderedShippedDate(orderedPrts.getShippedDate());
					orderedPartsConverted.setOrderedTrackingNumber(orderedPrts.getTrackingNumber());
					orderedPartsConverted.setOrderedPartNumber(orderedPrts.getPartNumber());
					orderedPartsConverted.setOrderedProduct(orderedPrts.getProduct());
					orderedPartsConverted.setOrderedQuantityRequested(orderedPrts.getQuantityRequested());
					orderedPartsConverted.setOrderedSerialNumber(orderedPrts.getQuantityRequested());
					orderedPartsConverted.setOrderedStatus(orderedPrts.getStatus());
					orderedPartsConverted.setOrderedProductId(orderedPrts.getProductId());
					orderedPartsConverted.setOrderedParentPropductId(orderedPrts.getParentPropductId());					
					
					orderedPartsList.add(orderedPartsConverted);
				}
			}
		}
		
		return orderedPartsList;
	}

	private static List<Part> populateRecommendedParts(ArrayList<SupplyRequestDetailActivityDo> actions) {
		List<SupplyRequestRecommendedPartsDo> parts = new ArrayList<SupplyRequestRecommendedPartsDo>();	
		List<Part> recommendedPartsList = new ArrayList<Part>();
    	
		for (SupplyRequestDetailActivityDo supplyRequestRecommendedPartsDo : actions) {
			
			if(LangUtil.isNotEmpty(supplyRequestRecommendedPartsDo.getRecommendedParts())){
				
				for (SupplyRequestRecommendedPartsDo recomParts : supplyRequestRecommendedPartsDo.getRecommendedParts()) {
					//SupplyRequestRecommendedPartsDo partsConverted = new SupplyRequestRecommendedPartsDo();
					Part partsConverted = new Part();
					
					partsConverted.setRecommendedQuantity(recomParts.getRecommendedQuantity());
					partsConverted.setRecommendedProductName(recomParts.getProductName());
					partsConverted.setRecommendedPartNumber(recomParts.getPartNumber());
					partsConverted.setRecommendedPartDisposition(recomParts.getPartDisposition());
					partsConverted.setRecommendedReturnIfDefective(recomParts.getReturnIfDefective());
					partsConverted.setRecommendedProductId(recomParts.getProductId());
					partsConverted.setRecommendedActivityId(recomParts.getActivityId());
					partsConverted.setRecommendedActivitySRId(recomParts.getActivitySRId());
					partsConverted.setRecommendedRelationType(recomParts.getRelationType());
					partsConverted.setRecommendedPrimaryOrderId(recomParts.getPrimaryOrderId());
					partsConverted.setRecommendedPrimaryOrderItemId(recomParts.getPrimaryOrderItemId());
					partsConverted.setRecommendedRequiredFlag(recomParts.getRecommendedReturnedFlag());
					recommendedPartsList.add(partsConverted);
					
				}
			}
		}
		
		return recommendedPartsList;
	}

	private static GenericAddress populateInstallAddress(SupplyRequestDetailDo requestDo,	boolean madcServiceRequestFlag) {
		GenericAddress installAddress = new GenericAddress();
		// Install address
		if (!madcServiceRequestFlag) {
			// madcServiceRequestFlag
			installAddress.setAddressLine1(requestDo.getInstallAddress1());
			installAddress.setAddressLine2(requestDo.getInstallAddress2());
			installAddress.setAddressLine3(requestDo.getInstallAddress3());
			installAddress.setCity(requestDo.getInstallCity());
			installAddress.setCountry(requestDo.getInstallCountry());
			installAddress.setPostalCode(requestDo.getInstallPostalCode());
			installAddress.setState(requestDo.getInstallState());
			installAddress.setCounty(requestDo.getCounty());
			installAddress.setDistrict(requestDo.getDistrict());
			installAddress.setOfficeNumber(requestDo.getOfficeNumber());
			installAddress.setProvince(requestDo.getInstallProvince());
			installAddress.setAddressName(requestDo.getInstallAddressName());
			installAddress.setAddressId(requestDo.getInstallAddressId());
			installAddress.setStoreFrontName(requestDo.getStoreFrontName());
			installAddress.setCountryISOCode(requestDo.getCountryIsoCode());
			installAddress.setRegion(requestDo.getRegion());
			installAddress.setPhysicalLocation1(requestDo.getInstallPhysicalLocation1());
			installAddress.setPhysicalLocation2(requestDo.getInstallPhysicalLocation2());
			installAddress.setPhysicalLocation3(requestDo.getInstallPhysicalLocation3());
		} else {
			installAddress.setAddressLine1(requestDo.getServiceAddress1());
			installAddress.setAddressLine2(requestDo.getServiceAddress2());
			installAddress.setAddressLine3(requestDo.getServiceAddress3());
			installAddress.setCity(requestDo.getServiceCity());
			installAddress.setCountry(requestDo.getServiceCountry());
			installAddress.setPostalCode(requestDo.getServicePostalCode());
			installAddress.setState(requestDo.getServiceState());
			installAddress.setCounty(requestDo.getCounty());
			installAddress.setDistrict(requestDo.getDistrict());
			installAddress.setOfficeNumber(requestDo.getOfficeNumber());
			installAddress.setProvince(requestDo.getServiceProvince());
			installAddress.setAddressName(requestDo.getServiceAddressName());
			installAddress.setAddressId(requestDo.getServiceAddressId());
			installAddress.setStoreFrontName(requestDo.getStoreFrontName());
			installAddress.setCountryISOCode(requestDo.getCountryIsoCode());
			installAddress.setRegion(requestDo.getRegion());
			if (LangUtil.isNotEmpty(requestDo.getServiceLocation())) {
				if (LangUtil.isNotBlank(requestDo.getOrderSource())	&& "Web".equalsIgnoreCase(requestDo.getOrderSource())) {
					populatePhysicalLocationContiansPrefix(requestDo, installAddress);
				} else {
					populatePhysicalLocationWOPrefix(requestDo, installAddress);
				}
			}
		}
		return installAddress;
	}

	private static String populateCostCenter(SupplyRequestDetailDo requestDetailDo, boolean madcServiceRequestFlag) {
		return madcServiceRequestFlag ? requestDetailDo.getMadcAssetCostCenter() : requestDetailDo.getAssetCostCenter();
	}

	private static boolean populateRequestExpediteOrder(SupplyRequestDetailDo requestDetailDo) {
		
		if(requestDetailDo.isRequestExpediteOrder() == null || !requestDetailDo.isRequestExpediteOrder()) {
			return false;
		}
 		return true;
	}

	public static ServiceRequest convertRequestTypeActivitiesDotoRequestTypeActivities(List<RequestTypeActivitiesDo> requestTypeActivitiesList) {
		ServiceRequest serviceRequest = new ServiceRequest();
		List<ServiceRequestActivity> serviceRequestActivityResult = new ArrayList<ServiceRequestActivity>();
		List<Asset> assets = new ArrayList<Asset>();
		
		for (RequestTypeActivitiesDo requestTypeActivitiesDo : LangUtil.notNull(requestTypeActivitiesList)) {
			ServiceRequestActivity serviceRequestActivity = new ServiceRequestActivity();
			Asset asset = new Asset();
			asset.setSerialNumber(requestTypeActivitiesDo.getSerialNumber());
			asset.setDeviceType(requestTypeActivitiesDo.getDeviceType());
			asset.setActivityNumber(requestTypeActivitiesDo.getActivityNumber());
			asset.setStatusDetail(requestTypeActivitiesDo.getStatusDetail());
			asset.setBuilding(requestTypeActivitiesDo.getBuilding());
			asset.setFloor(requestTypeActivitiesDo.getFloor());
			asset.setOffice(requestTypeActivitiesDo.getOffice());
			asset.setDescription(requestTypeActivitiesDo.getDescription());
			asset.setDeviceTag(requestTypeActivitiesDo.getDeviceTag());
			asset.setInstallDate(requestTypeActivitiesDo.getInstallDate());
			asset.setIpAddress(requestTypeActivitiesDo.getIpAddress());
			asset.setHostName(requestTypeActivitiesDo.getHostName());
			asset.setPartList(popupatePartList(requestTypeActivitiesDo));
			asset.setInstallAddress(populateInstallAddress(requestTypeActivitiesDo));
			assets.add(asset);
			serviceRequest.setArea(createListOfValues(requestTypeActivitiesDo.getArea()));
			serviceRequest.setSubArea(createListOfValues(requestTypeActivitiesDo.getSubArea()));
			serviceRequest.setOrderSource(requestTypeActivitiesDo.getOrderSource());
			serviceRequest.setServiceRequestStatus(requestTypeActivitiesDo.getServiceRequestStatus());
			serviceRequest.setRequestor(populateRequestor(requestTypeActivitiesDo));
			serviceRequest.setAccountName(requestTypeActivitiesDo.getAccountName());
			serviceRequest.setPrimaryContact(populatePrimaryContact(requestTypeActivitiesDo));
			serviceRequest.setSecondaryContact(populateSecondaryContact(requestTypeActivitiesDo));
			serviceRequest.setCustomerReferenceNumber(requestTypeActivitiesDo.getCustomerReferenceNumber());
			serviceRequest.setCostCenter(requestTypeActivitiesDo.getCostCenter());
			serviceRequest.setAddtnlDescription(requestTypeActivitiesDo.getAddtnlDescription());
			serviceRequest.setNotes(requestTypeActivitiesDo.getNotes());
			serviceRequest.setAttachments(populateServiceRequestActivitiesAttachment(requestTypeActivitiesDo));
				
			serviceRequestActivity.setAssetDetails(assets);
			serviceRequestActivity.setActivityStatus(requestTypeActivitiesDo.getStatus());
			serviceRequestActivityResult.add(serviceRequestActivity);			
		}
		serviceRequest.setActivitywebUpdateActivities(serviceRequestActivityResult);
		
		return serviceRequest;
	}

	private static List<Attachment> populateServiceRequestActivitiesAttachment(RequestTypeActivitiesDo requestTypeActivitiesDo) {
		List<Attachment> attachments = new ArrayList<Attachment>();
		for (ServiceRequestActivitiesAttachmentDo srAttachment : LangUtil.notNull(requestTypeActivitiesDo.getServiceRequestActivitiesAttachmentDo())) {
			Attachment attachment = new Attachment();
			attachment.setAttachmentName(srAttachment.getAttachmentName());
			attachments.add(attachment);
		}
		return attachments;
	}

	private static AccountContact populateSecondaryContact(RequestTypeActivitiesDo requestTypeActivitiesDo) {
		AccountContact secondaryContact = new AccountContact();
		secondaryContact.setFirstName(requestTypeActivitiesDo.getSecondaryContactFirstName());
		secondaryContact.setLastName(requestTypeActivitiesDo.getSecondaryContactLastName());
		secondaryContact.setWorkPhone(requestTypeActivitiesDo.getSecondaryContactWorkPhone());
		secondaryContact.setEmailAddress(requestTypeActivitiesDo.getSecondaryContactEmail());
		return secondaryContact;
	}

	private static AccountContact populatePrimaryContact(RequestTypeActivitiesDo requestTypeActivitiesDo) {
		AccountContact primaryContact = new AccountContact();
		primaryContact.setFirstName(requestTypeActivitiesDo.getPrimaryContactFirstName());
		primaryContact.setLastName(requestTypeActivitiesDo.getPrimaryContactLastName());
		primaryContact.setWorkPhone(requestTypeActivitiesDo.getPrimaryContactWorkPhone());
		primaryContact.setEmailAddress(requestTypeActivitiesDo.getPrimaryContactEmail());
		return primaryContact;
	}

	private static AccountContact populateRequestor(RequestTypeActivitiesDo requestTypeActivitiesDo) {
		AccountContact accountContact = new AccountContact();
		accountContact.setFirstName(requestTypeActivitiesDo.getRequestorFirstName());
		accountContact.setLastName(requestTypeActivitiesDo.getRequestorLastName());
		return accountContact;
	}

	private static GenericAddress populateInstallAddress(RequestTypeActivitiesDo requestTypeActivitiesDo) {
		GenericAddress address = new GenericAddress();
		address.setDistrict(requestTypeActivitiesDo.getDistrict());
		address.setAddressLine1(requestTypeActivitiesDo.getAddressLine1());
		address.setAddressLine2(requestTypeActivitiesDo.getAddressLine2());
		address.setAddressLine3(requestTypeActivitiesDo.getAddressLine3());
		address.setCity(requestTypeActivitiesDo.getCity());
		address.setCountry(requestTypeActivitiesDo.getCountry());
		address.setCountryISOCode(requestTypeActivitiesDo.getCountryCode());
		address.setCounty(requestTypeActivitiesDo.getCounty());
		address.setErrorMsgForCleansing(requestTypeActivitiesDo.getErrorMessage());
		address.setLatitude(requestTypeActivitiesDo.getLatitude());
		address.setLongitude(requestTypeActivitiesDo.getLongitude());
		address.setPostalCode(requestTypeActivitiesDo.getPostalCode());
		address.setProvince(requestTypeActivitiesDo.getProvince());
		address.setRegion(requestTypeActivitiesDo.getRegion());
		address.setState(requestTypeActivitiesDo.getState());
		address.setStateFullName(requestTypeActivitiesDo.getStateFullName());
		return address;
	}

	private static List<Part> popupatePartList(RequestTypeActivitiesDo requestTypeActivitiesDo) {
		List<Part> parts = new ArrayList<Part>();
		if(LangUtil.isNotEmpty(requestTypeActivitiesDo.getActivityPartList())) {
			for (ActivityPartListDo activityPartListDo : requestTypeActivitiesDo.getActivityPartList()) {
				Part part = new Part();
				part.setPartNumber(activityPartListDo.getPartNumber());
				part.setDescription(activityPartListDo.getDescription());
				part.setOrderQuantity(activityPartListDo.getOrderQuantity());
				parts.add(part);
			}
		}
		
		return parts;
	}


	private static void populatePaymentDetails(ServiceRequest request,SupplyRequestDetailDo requestDetailDo) {
		if(requestDetailDo.getPaymentDetails()!=null && requestDetailDo.getPaymentDetails().get(0)!=null) {
			request.setCreditCardToken(requestDetailDo.getPaymentDetails().get(0).getCreditCardToken());
			request.setCreditCardType(requestDetailDo.getPaymentDetails().get(0).getCreditCardType());
		}
	}

	private static void populateServiceActivityStatus(com.lexmark.domain.ServiceRequest request, SupplyRequestDetailDo requestDetailDo) {
    	if(requestDetailDo.getActions() !=  null) {
        	for(SupplyRequestDetailActivityDo activity : requestDetailDo.getActions()) {
        		if (activity.isServiceActivityFlag()) {
        			request.setShipToDefault(activity.getShipToDefault());
        			request.setServiceActivityStatus(AmindRequestDataConversionUtil.getActivityStatus(activity.getStatus()));
    	    	}
        	}	
    	}

    }

    private static void populateParts(ServiceRequest request, SupplyRequestDetailDo requestDetailDo){
    	List<Part> partsList = new ArrayList<Part>();
    	List<Part> cancelledParts = new ArrayList<Part>();
    	
    	for(SupplyRequestDetailCustomerOrderItemDo itemDo: LangUtil.notNull(requestDetailDo.getCustomerOrderLineItems())){			
    		List<ServiceRequestOrderLineItem> shipmentOrderLines = request.getShipmentOrderLines();    	
	    	List<ServiceRequestOrderLineItem> pendingShipments = request.getPendingShipments();
	    	
	    	Part part = new Part();
    		part.setPartId(itemDo.getId());
    		part.setCatalogId(itemDo.getCatalogId());
    		part.setDescription(itemDo.getPartDescriptionExplicit());
    		part.setProductId(itemDo.getProductId());
    		part.setSupplyId(itemDo.getSupplyId());
    		part.setPartNumber(LangUtil.isBlank(itemDo.getVendorPartNumber()) ? itemDo.getPartNumber() : itemDo.getVendorPartNumber());
    		part.setPartName(itemDo.getProductName());
    		part.setPrinterPartNumber(itemDo.getPrinterMaterial());
    		part.setOrderQuantity(itemDo.getQuantity());
    		part.setPartType(itemDo.getPartType());
    		part.setConsumableType(itemDo.getConsumableType());
    		part.setImplicitFlag(itemDo.isImplicitFlag());
    		part.setYield(itemDo.getYield());
    		part.setAgreementId(itemDo.getAgreementId());
    		part.setModel(itemDo.getProductModel());
    		part.setStatus(itemDo.getStatus());
    		part.setCatalogType(itemDo.getCatalogType());
    		part.setPrice(new BigDecimal(itemDo.getUnitPrice()));
    		part.setTotalPrice(itemDo.getTotalPrice());
    		part.setTotalLinePrice(itemDo.getTotalLinePrice());
    		part.setTaxAmount(itemDo.getTax());
    		part.setCurrency(itemDo.getCurrency());
    		part.setDeviceType(itemDo.getDeviceType());
    		part.setMaterialLine(itemDo.getMaterialLine());
    		part.setBundleParentLineId(itemDo.getSapParentLineNumber());
    		//added for deinstall parts 
    		part.setInstallSerialNumber(itemDo.getInstallSerialNumber());
    		part.setInstallProduct(itemDo.getPartNumber());
    		part.setDeinstallSerialNumber(itemDo.getDeinstallSerialNumber());
    		part.setDeinstallProduct(itemDo.getDeinstallProduct());
    		part.setDeinstallModel(itemDo.getDeinstallModel());
    		part.setDeinstallIPAddr(itemDo.getDeinstallIPAddr());
    		part.setDeinstallAssetTag(itemDo.getDeinstallAssetTag());
    		part.setDeinstallBrand(itemDo.getDeinstallBrand());
    		part.setDeinstallComments(itemDo.getDeinstallComments());
    		part.setDeinstallHostName(itemDo.getDeinstallHostName());
    		
    		//OPS
    		part.setAuthorizationCode(itemDo.getStatus());
    		part.setAuthorizationReason(itemDo.getCancellationReason());
    		
			if (!isNotShowOnPortal(itemDo.getStatus(), itemDo.getCancellationReason())) {
				if (isCancelledPartsStatus(itemDo.getStatus())) {
					part.setCancelledQuantity(itemDo.getQuantity());
					cancelledParts.add(part);
				} else if (isHardwareRequest(requestDetailDo)) {
					partsList.add(part);
				} else if (!partMatchesShipmentsOrPending(shipmentOrderLines, pendingShipments, itemDo)) {
					partsList.add(part);
				}
			}
		}
    	
    	double total = 0;
    	double tax = 0;
    	double subTotal = 0;
    	
    	for (Part listPart : partsList) {
    		subTotal += LangUtil.convertStringToDouble(listPart.getTotalPrice());
			tax += LangUtil.convertStringToDouble(listPart.getTaxAmount());
		}
    	
    	total = subTotal + tax;
    	
    	request.setTotalAmount(new BigDecimal(total));
    	request.setTax(new BigDecimal(tax));
    	request.setItemSubTotalBeforeTax(new BigDecimal(subTotal));
    	
    	request.setParts(partsList);
    	appendCancelledParts(request, cancelledParts);
    }
    
    private static boolean isHardwareRequest(SupplyRequestDetailDo requestDetailDo) {
    	if (("HW Order".equalsIgnoreCase(requestDetailDo.getArea()) || "Hardware-Ship and Install".equalsIgnoreCase(requestDetailDo.getArea())) 
    			&& "BAU".equalsIgnoreCase(requestDetailDo.getSubArea())) {
			return true;
		}
		return false;
	}

	private static boolean partMatchesShipmentsOrPending(List<ServiceRequestOrderLineItem> shipmentOrderLines,	List<ServiceRequestOrderLineItem> pendingShipments,	SupplyRequestDetailCustomerOrderItemDo itemDo) {
    	for (ServiceRequestOrderLineItem serviceRequestOrderLineItem : LangUtil.notNull(pendingShipments)) {
    		if (StringUtil.isStringEmpty(serviceRequestOrderLineItem.getPartnumber())) {
    			continue;
    		}
			if (serviceRequestOrderLineItem.getPartnumber().equalsIgnoreCase(itemDo.getPartNumber())) {
				return true;
			}
		}
    	for (ServiceRequestOrderLineItem serviceRequestOrderLineItem : LangUtil.notNull(shipmentOrderLines)) {
    		if (StringUtil.isStringEmpty(serviceRequestOrderLineItem.getPartnumber())) {
    			continue;
    		}  
			if (serviceRequestOrderLineItem.getPartnumber().equalsIgnoreCase(itemDo.getPartNumber())) {
				return true;
			}
		}
		return false;
	}

	static boolean isNotShowOnPortal(String status, String reason) {
        return LangUtil.startsWith(status, "Cancelled") && "Vendor cannot fulfill".equalsIgnoreCase(reason);
    }
    
    static boolean isShipPendingStatus (String status) {
    	return "Ship Pending".equalsIgnoreCase(status);
    }
    
    static boolean isCancelledPartsStatus(String status) {
    	return LangUtil.startsWith(status, "Cancelled Customer");
    }

    private static void populatePageCounts(ServiceRequest request, SupplyRequestDetailDo requestDetailDo){
    	List<PageCounts> pageCountsList = new ArrayList<PageCounts>();
    	
    	for(SupplyRequestDetailPageCountDo supplyPageCount: LangUtil.notNull(requestDetailDo.getPageCounts())){
    		PageCounts pageCounts = new PageCounts(supplyPageCount.getName(),supplyPageCount.getCount(), supplyPageCount.getDate());
    		if(!"MONO".equalsIgnoreCase(supplyPageCount.getName()))
    		{
    			pageCountsList.add(pageCounts);
    		}
    		
    	}
    	
    	request.setPageCounts(pageCountsList);
    }
	
    private static void processShippedItems(ServiceRequest request, SupplyRequestDetailDo requestDetailDo) {
        List<ServiceRequestOrderLineItem> orderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
    	for (SupplyRequestDetailOrderItemDo orderItem : LangUtil.notNull(requestDetailDo.getOrderItems())) {
    		for(SupplyRequestDetailShipmentDo shippedItemDo : LangUtil.notNull(orderItem.getShippedItems())) {
                if (isPricingOrderLineType(shippedItemDo.getOrderLineType())) {
                    continue;
                }
			    
                List<ServiceRequestOrderLineItem> items = toServiceRequestOrderLineItem(shippedItemDo, requestDetailDo);
                orderLineItems.addAll(items);
            }        		
    	}
        
    	appendShipmentOrderLines(request, orderLineItems);
    }

    /**
     * For Shipment Order Lines.
     */ 
    static List<ServiceRequestOrderLineItem> toServiceRequestOrderLineItem(SupplyRequestDetailShipmentDo shipmentItemDo, SupplyRequestDetailDo requestDetailDo)
    {
        List<ServiceRequestOrderLineItem> result = new ArrayList<ServiceRequestOrderLineItem>();
        boolean breakfix = isBreakFix(requestDetailDo);
        
        for (SupplyRequestDetailShipmentItemDo itemDo : LangUtil.notNull(shipmentItemDo.getShipmentLineItems())) {
        	ServiceRequestOrderLineItem item = new ServiceRequestOrderLineItem();
        	item.setEta(itemDo.getEta());
        	item.setActualDeliveryDate(itemDo.getActualDeliveryDate());
        	item.setProductTLI(itemDo.getProductTLI());
        	item.setProductDescription(itemDo.getPartName());
        	item.setVendorProduct(itemDo.getVendorProduct());
        	item.setQuantity(itemDo.getQuantity());
        	item.setVendorId(shipmentItemDo.getVendorId());
        	item.setPartName(itemDo.getPartName());
         	item.setProductDescription(itemDo.getDescription());
        	item.setPartnumber(itemDo.getPartNumber());
        	item.setShippedQuantity(toInt(itemDo.getShippedQuantity()));
        	item.setActualShipDate(itemDo.getActualShipDate());
        	item.setPartType(itemDo.getPartType());
        	item.setFulfillmentType(shipmentItemDo.getFulfillmentType());
        	item.setShippedDate(itemDo.getShipmentDate());
        	
			if (breakfix) {
				item.setCarrier(shipmentItemDo.getCarrier());
				item.setTrackingNumber(shipmentItemDo.getTrackingNumber());
				item.setStatus(shipmentItemDo.getStatus());
			} else {
				item.setCarrier(itemDo.getCarrier());
				item.setTrackingNumber(itemDo.getTrackingNumber());
				item.setStatus(itemDo.getStatus());
			}
        	
        	item.setSupplyType(shipmentItemDo.getSupplyType());
        	item.setNetPrice(new BigDecimal(shipmentItemDo.getNetPrice()));

        	List<String> serialNumbers = new ArrayList<String>();
        	for (SupplyRequestDetailShipmentItemOrderEntryDo itemOrderEntryDo : LangUtil.notNull(itemDo.getShipmentOrderEntryItems())) {
        		serialNumbers.add(itemOrderEntryDo.getSerialNumber());
        	}
        	item.setSerialNumbers(serialNumbers);

        	result.add(item);
        }
        
        return result;
    }
    
    private static boolean isBreakFix(SupplyRequestDetailDo requestDetailDo) {
    	if ("MPS".equalsIgnoreCase(requestDetailDo.getTypeIdentifier())) {
    		return false;
    	}
    	return true;
    }

	/**
     * For Shipment Order Lines.
     *  
     * @see com.lexmark.service.impl.real.util.AmindRequestTypeConversionUtil#toServiceRequestOrderLineItem(SupplyRequestDetailShipmentDo)
     */
    private static ServiceRequestOrderLineItem toServiceRequestOrderLineItem(SupplyRequestDetailOrderLineShipmentItemDo shipmentItemDo,
            SupplyRequestDetailPendingShipmentDo itemDo)
    {
        ServiceRequestOrderLineItem result = new ServiceRequestOrderLineItem();
        if (shipmentItemDo == null) {
            return result;
        }
        result.setCarrier(shipmentItemDo.getCarrier());
        result.setEta(shipmentItemDo.getEta());
        result.setActualDeliveryDate(shipmentItemDo.getActualDeliveryDate());
        result.setProductTLI(shipmentItemDo.getProductTLI());
        result.setProductDescription(shipmentItemDo.getPartName());
        result.setVendorProduct(shipmentItemDo.getVendorProduct());
        result.setQuantity(shipmentItemDo.getQuantity());
//        result.setVendorId(vendorId);
        result.setVendorId(itemDo.getVendorId());
        result.setTrackingNumber(shipmentItemDo.getTrackingNumber());
        result.setPartName(shipmentItemDo.getPartName());
        result.setPartnumber(LangUtil.isBlank(itemDo.getVendorPartNumber()) ? itemDo.getPartNumber() : itemDo.getVendorPartNumber());
        result.setShippedQuantity(toInt(shipmentItemDo.getShippedQuantity()));
        result.setActualShipDate(shipmentItemDo.getActualShipDate());
        result.setPartType(shipmentItemDo.getPartType());
        result.setStatus(shipmentItemDo.getStatus());
        result.setFulfillmentType(itemDo.getFulfillmentType());

        List<String> serialNumbers = new ArrayList<String>();
        for (SupplyRequestDetailShipmentItemOrderEntryDo itemOrderEntryDo : LangUtil.notNull(shipmentItemDo.getShipmentOrderEntryItems())) {
            serialNumbers.add(itemOrderEntryDo.getSerialNumber());
        }
        result.setSerialNumbers(serialNumbers);

        return result;        
    }
    
    /** For Pending Shipments.
     */ 
    static ServiceRequestOrderLineItem toServiceRequestOrderLineItem(SupplyRequestDetailPendingShipmentDo itemDo) {
        ServiceRequestOrderLineItem item = new ServiceRequestOrderLineItem();
    
        item.setProductDescription(itemDo.getPartName());
        item.setStatus(itemDo.getStatus());
        item.setQuantity(itemDo.getPendingQuantity());
        item.setVendorId(itemDo.getVendorId());
        item.setPartName(itemDo.getPartName());
        item.setPartType(itemDo.getPartType());
        item.setPartnumber(LangUtil.isBlank(itemDo.getVendorPartNumber()) ? itemDo.getPartNumber() : itemDo.getVendorPartNumber());
        item.setEta(itemDo.getExpectedShipDate());
        item.setOrderReferenceNumber(itemDo.getOrderReferenceNumber());
        item.setBackOrderQuantity(toInt(itemDo.getBackOrderQuantity()));
        item.setOrderedDate(itemDo.getOrderedDate());
        item.setFulfillmentType(itemDo.getFulfillmentType());
        int requestedQuantity = toInt(itemDo.getRequestedQuantity());
        int backOrderQuantity = toInt(itemDo.getBackOrderQuantity());
        int shipmentQuantity = toInt(itemDo.getShippedQuantity());
        int pendingQuantity = requestedQuantity - backOrderQuantity - shipmentQuantity;
        if (pendingQuantity < 0) {
//            throw new AssertionError(String.format("pendingQuantity=%s < 0 (context: partNumber=%s, partName=%s, requestedQuantity=%s, backOrderQuantity=%s, shipmentQuantity=%s)",
//                    pendingQuantity, itemDo.getPartNumber(), itemDo.getPartName(), requestedQuantity, backOrderQuantity, shipmentQuantity));
            logger.warn(String.format("pendingQuantity=%s < 0 (context: partNumber=%s, partName=%s, requestedQuantity=%s, backOrderQuantity=%s, shipmentQuantity=%s)",
                    pendingQuantity, itemDo.getPartNumber(), itemDo.getPartName(), requestedQuantity, backOrderQuantity, shipmentQuantity));
            
        }
        item.setQuantity(itemDo.getRequestedQuantity());
        item.setPendingQuantity(pendingQuantity);
        return item;
    }
    
    /**
     * Populating pandingShipments and cancelledParts. 
     */
    static void processPendingShipments(ServiceRequest request, SupplyRequestDetailDo requestDetailDo) {
		List<ServiceRequestOrderLineItem> pendingShipments = new ArrayList<ServiceRequestOrderLineItem>();
		List<Part> cancelledParts = new ArrayList<Part>();
		List<ServiceRequestOrderLineItem> shipmentOrderLines = new ArrayList<ServiceRequestOrderLineItem>();
		
		for (SupplyRequestDetailOrderItemDo orderItemDo : LangUtil.notNull(requestDetailDo.getOrderItems())) {
			for (SupplyRequestDetailPendingShipmentDo itemDo : LangUtil.notNull(orderItemDo.getPendingShipments())) {
			    if (isPricingOrderLineType(itemDo.getOrderLineType()) 
			    		|| isNotShowOnPortal(itemDo.getStatus(),itemDo.getCancellationReason()) 
			    		|| isShipPendingStatus(itemDo.getStatus())) {
	        	       continue;
			    }
			    if ("Cancelled".equalsIgnoreCase(itemDo.getStatus())) {
				  	cancelledParts.add(toPart(itemDo));
				} else {
				    pendingShipments.add(toServiceRequestOrderLineItem(itemDo));
				}
				
				// shipmentOrderLines processing
				for (SupplyRequestDetailOrderLineShipmentItemDo shipmentItemDo : LangUtil.notNull(itemDo.getPendingShipmentItems())) {
				    shipmentOrderLines.add(toServiceRequestOrderLineItem(shipmentItemDo, itemDo));
				}
			}
		}
		
		request.setPendingShipments(pendingShipments);
		appendCancelledParts(request, cancelledParts);
		appendShipmentOrderLines(request, shipmentOrderLines);
    }
    
    
    /**
     *  Defect #2348: filtering out "Pricing" orderLineType.
     */
    private static boolean isPricingOrderLineType(String orderLineType) {
        return "Pricing".equalsIgnoreCase(orderLineType); 
    }

    private static void appendCancelledParts(ServiceRequest request, List<Part> inputCancelledParts) {
        List<Part> cancelledParts = request.getCancelledParts();
        if (LangUtil.isEmpty(cancelledParts)) {
            cancelledParts = new ArrayList<Part>();
        } 
        cancelledParts.addAll(inputCancelledParts);
        request.setCancelledParts(cancelledParts);
    }
    
    private static void appendShipmentOrderLines(ServiceRequest request, List<ServiceRequestOrderLineItem> inputOrderLineItems) {
        List<ServiceRequestOrderLineItem> orderLineItems = request.getShipmentOrderLines();
        if (LangUtil.isEmpty(orderLineItems)) {
            orderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
        } 
        orderLineItems.addAll(inputOrderLineItems);
        request.setShipmentOrderLines(orderLineItems);
    }

    private static Part toPart(SupplyRequestDetailPendingShipmentDo itemDo) {
        Part part = new Part();
        part.setPartNumber(LangUtil.isBlank(itemDo.getVendorPartNumber()) ? itemDo.getPartNumber() : itemDo.getVendorPartNumber());
        part.setDescription(itemDo.getPartName());
        part.setOrderQuantity(itemDo.getRequestedQuantity());
        part.setCancelledQuantity(itemDo.getCancelledQuantity());
        part.setStatus(itemDo.getStatus());
        return part;
    }
   

    static void populateServerRequestActivities(ServiceRequest request, SupplyRequestDetailDo requestDetailDo, boolean madcServiceRequestFlag) {
//    		if(madcServiceRequestFlag){
    			request.setActivitywebUpdateActivities(AmindRequestDataConversionUtil.populateActivityWebUpdateActivities(requestDetailDo));
//    		}
//    		else {
    			request.setServicewebUpdateActivities(AmindRequestDataConversionUtil.populateServiceWebUpdateActivities(requestDetailDo, madcServiceRequestFlag));
//    		}
	       request.setEmailActivities(AmindRequestDataConversionUtil.populateEmailActivities(request, requestDetailDo.getActions()));
    }

    private static void populateAttachments(ServiceRequest request, SupplyRequestDetailDo requestDetail) {
	    List<Attachment> attachments = new ArrayList<Attachment>(); 
	    for (SupplyRequestDetailAttachmentDo attachDo :  LangUtil.notNull(requestDetail.getAttachments())) {
	       Attachment attach = new Attachment();
	       attach.setAttachmentName(attachDo.getFileName());
	       attach.setExtension(attachDo.getFileExt());
	       attach.setVisibility(attachDo.getVisibilityRole());
	       attach.setDescription(attachDo.getComments());
	       attach.setActivityId(attachDo.getIdentifier());
	       attach.setId(attachDo.getId());
	       attach.setSize(Integer.parseInt(attachDo.getSize().replace(",", "")));
	       attach.setDisplayAttachmentName(attachDo.getDisplayFileName());
	       attachments.add(attach); 
	    }
	    request.setAttachments(attachments);
	    
	    
    }
 	private static void populateLangDescription(
			com.lexmark.domain.ServiceRequest request,
			SupplyRequestDetailDo requestDetailDo) {
		String tempLangDesc = "";
		if (LangUtil.isNotEmpty(requestDetailDo.getCustomerOrderLineItems())) {
			tempLangDesc = requestDetailDo.getCustomerOrderLineItems().get(0)
					.getDescriptionLocalLang();
		}
		if (LangUtil.isBlank(tempLangDesc)
				&& LangUtil.isBlank(requestDetailDo.getCustomerReportingName())) {
			request.setDescriptionLocalLang(requestDetailDo.getModelNumber());
		} else {
			request.setDescriptionLocalLang(requestDetailDo
					.getCustomerReportingName());
			if (LangUtil.isNotBlank(tempLangDesc)) {
				request.setDescriptionLocalLang(tempLangDesc);
			}
		}
	}
    
    private static void populateAccountContact(ServiceRequest request, List<SupplyAccountContactDetailDo> requestDetail) {
	    List<AccountContact> acList = new ArrayList<AccountContact>(); 
	    for (SupplyAccountContactDetailDo accountContactDo :  requestDetail) {
	       AccountContact ac = new AccountContact();
	       ac.setWorkPhone(accountContactDo.getWorkPhone());
	       ac.setAlternatePhone(accountContactDo.getHomePhone());
	       ac.setLastName(accountContactDo.getLastName());
	       ac.setEmailAddress(accountContactDo.getEmailAddress());
	       acList.add(ac); 
	    }
	    request.setContactInfoForDevice(acList);
	}
/**/


    private static Asset populateAsset(RequestTypeDo requestDo) {
    	Asset asset = new Asset();
		if (LangUtil.isNotEmpty(requestDo.getAssetId())) {
			asset.setSerialNumber(requestDo.getSerialNumber());
			asset.setModelNumber(requestDo.getProductModel());
			asset.setProblemDescription(requestDo.getProblemDescription());
			asset.setDeviceTag(requestDo.getDeviceTag());
			asset.setHostName(requestDo.getHostName());
			asset.setAssetCostCenter(requestDo.getAssetCostCenter());
			asset.setCustomerReportingName(requestDo.getCustomerReportingName());
			asset.setInstallDate(requestDo.getInstallDate());
			asset.setIpAddress(requestDo.getIpAddress());
			asset.setProductTLI(requestDo.getProductTLI());
		} else {
			asset.setSerialNumber(requestDo.getMpsSerialNumber());
			asset.setDeviceTag(requestDo.getMpsDeviceTag());
			asset.setHostName(requestDo.getMpsHostName());
			asset.setModelNumber(requestDo.getProductModel());
			asset.setProblemDescription(requestDo.getProblemDescription());
			asset.setAssetCostCenter(requestDo.getMadcCostCenter());
			asset.setCustomerReportingName(requestDo.getCustomerReportingName());
			asset.setInstallDate(requestDo.getMadcInstallDate());
			asset.setIpAddress(requestDo.getMadcIPAddress());
			asset.setProductTLI(requestDo.getMadcProductTLI());
		}
		return asset;
	}
    
	private static GenericAddress populateMoveFromAddress(SupplyRequestDetailDo requestDo) {
		GenericAddress moveFromAddress = new GenericAddress();
		moveFromAddress.setAddressId(requestDo.getMoveFromMadcAddressID());
		moveFromAddress.setAddressLine1(requestDo.getMoveFromMadcAddressLine1());
		moveFromAddress.setAddressLine2(requestDo.getMoveFromMadcAddressLine2());
		moveFromAddress.setCity(requestDo.getMoveFromMadcCity());
		moveFromAddress.setCountry(requestDo.getMoveFromMadcCountry());
		moveFromAddress.setCountryISOCode(requestDo.getMoveFromMadcISOCountryCode());
		moveFromAddress.setCounty(requestDo.getMoveFromMadcCounty());
		moveFromAddress.setDistrict(requestDo.getMoveFromMadcDistrict());
		moveFromAddress.setState(requestDo.getMoveFromMadcState());
		moveFromAddress.setStateFullName(requestDo.getMoveFromMadcStateFullName());
		moveFromAddress.setOfficeNumber(requestDo.getMoveFromMadcHouseNum());
		moveFromAddress.setErrorMsgForCleansing(requestDo.getMoveFromMadcFirstLogicErrorMessage());
		moveFromAddress.setLatitude(requestDo.getMoveFromMadcLatitude());
		moveFromAddress.setLongitude(requestDo.getMoveFromMadcLongitude());
		moveFromAddress.setRegion(requestDo.getMoveFromMadcRegion());
		moveFromAddress.setPostalCode(requestDo.getMoveFromMadcPostalCode());
		moveFromAddress.setProvince(requestDo.getMoveFromMadcProvince());
		moveFromAddress.setPhysicalLocation1(requestDo.getMoveFromMadcBuilding());
		moveFromAddress.setPhysicalLocation2(requestDo.getMoveFromMadcFloor());
		moveFromAddress.setPhysicalLocation3(requestDo.getMoveFromMadcOffice());
		moveFromAddress.setAddressName(requestDo.getMadcAddressName());
		//TODO: populate addressName
		
		return moveFromAddress;
	}

    private static Asset populateAsset(SupplyRequestDetailDo requestDo, boolean madcServiceRequestFlag) {
        Asset asset = new Asset();
        asset.setModelNumber(requestDo.getModelNumber()); 
        asset.setProblemDescription(requestDo.getProblemDescription());
		asset.setAssetId(requestDo.getAssetId());
		asset.setProductLine(requestDo.getProductLine());
		asset.setChlNodeValue(requestDo.getChlNodeValue());
		asset.setChlNodeId(requestDo.getChlNodeId());
		asset.setDeviceContact(populateDeviceContacts(requestDo.getDeviceContacts()));
		asset.setCustomerReportingName(requestDo.getCustomerReportingName());
		asset.setDeviceTag(requestDo.getDeviceTag());
		asset.setMoveFromAddress(populateMoveFromAddress(requestDo));	
//		for (SupplyRequestDetailActivityDo activityDo : LangUtil.notNull(requestDo.getActions())) {					// added fields for CR 10483 and 13824
//			asset.setStatusDetail(activityDo.getStatusDetail());
//			asset.setDeviceType(activityDo.getDeviceType());
//		}
		if (madcServiceRequestFlag) {
			asset.setAssetTag(requestDo.getAssetTag());
			asset.setProductTLI(requestDo.getMadcProductTLI());
			asset.setAssetCostCenter(requestDo.getAssetCostCenter());
			asset.setSerialNumber(requestDo.getMadcSerialNumber());
			asset.setIpAddress(requestDo.getIpAddress());
			asset.setInstallDate(requestDo.getInstallDate());
			asset.setHostName(requestDo.getHostName());
		} else {
			asset.setProductTLI(requestDo.getProductTLI());
			asset.setAssetCostCenter(requestDo.getAssetCostCenter());
	        asset.setSerialNumber(requestDo.getSerialNumber());
			asset.setIpAddress(requestDo.getAssetBasedIPAddress());
			asset.setInstallDate(requestDo.getAssetBasedInstallDate());
			asset.setHostName(requestDo.getAssetBasedSRHostName());
		}
		
		//Install address
		if(!madcServiceRequestFlag)
		{
		GenericAddress installAddress = new GenericAddress();
		//madcServiceRequestFlag
		installAddress.setAddressLine1(requestDo.getInstallAddress1());
		installAddress.setAddressLine2(requestDo.getInstallAddress2());
		installAddress.setAddressLine3(requestDo.getInstallAddress3());
		installAddress.setCity(requestDo.getInstallCity());
		installAddress.setCountry(requestDo.getInstallCountry());
		installAddress.setPostalCode(requestDo.getInstallPostalCode());
		installAddress.setState(requestDo.getInstallState());
		installAddress.setCounty(requestDo.getCounty());
		installAddress.setDistrict(requestDo.getDistrict());
		installAddress.setOfficeNumber(requestDo.getOfficeNumber());
		installAddress.setProvince(requestDo.getInstallProvince());
		installAddress.setAddressName(requestDo.getInstallAddressName());
		installAddress.setAddressId(requestDo.getInstallAddressId());
		installAddress.setStoreFrontName(requestDo.getStoreFrontName());
		installAddress.setCountryISOCode(requestDo.getCountryIsoCode());
		installAddress.setRegion(requestDo.getRegion());
		installAddress.setPhysicalLocation1(requestDo.getInstallPhysicalLocation1());
		installAddress.setPhysicalLocation2(requestDo.getInstallPhysicalLocation2());
		installAddress.setPhysicalLocation3(requestDo.getInstallPhysicalLocation3());
		installAddress.setLBSIdentifierFlag(requestDo.getLBSIdentifierFlag());
		asset.setInstallAddress(installAddress);
		}
		else
		{
			GenericAddress installAddress = new GenericAddress();
			installAddress.setAddressLine1(requestDo.getServiceAddress1());
			installAddress.setAddressLine2(requestDo.getServiceAddress2());
			installAddress.setAddressLine3(requestDo.getServiceAddress3());
			installAddress.setCity(requestDo.getServiceCity());
			installAddress.setCountry(requestDo.getServiceCountry());
			installAddress.setPostalCode(requestDo.getServicePostalCode());
			installAddress.setState(requestDo.getServiceState());
			installAddress.setCounty(requestDo.getCounty());
			installAddress.setDistrict(requestDo.getDistrict());
			installAddress.setOfficeNumber(requestDo.getOfficeNumber());
			installAddress.setProvince(requestDo.getServiceProvince());
			installAddress.setAddressName(requestDo.getServiceAddressName());
			installAddress.setAddressId(requestDo.getServiceAddressId());
			installAddress.setStoreFrontName(requestDo.getStoreFrontName());
			installAddress.setCountryISOCode(requestDo.getCountryIsoCode());
			installAddress.setRegion(requestDo.getRegion());
			if (LangUtil.isNotEmpty(requestDo.getServiceLocation())) {
				if (LangUtil.isNotBlank(requestDo.getOrderSource())	&& "Web".equalsIgnoreCase(requestDo.getOrderSource())) {
					populatePhysicalLocationContiansPrefix(requestDo, installAddress);
				} else {
					populatePhysicalLocationWOPrefix(requestDo, installAddress);
				}
			}
			installAddress.setLBSIdentifierFlag(requestDo.getLBSIdentifierFlag());
			installAddress.setCoordinatesXPreDebriefRFV(requestDo.getCoordinatesX());
			installAddress.setCoordinatesYPreDebriefRFV(requestDo.getCoordinatesY());
			asset.setInstallAddress(installAddress);
		}
		//Move to address
//		asset.setMoveToAddressGrouped(requestDo.getAssetMoveToAddressGrouped());
		asset.setMoveToAddressGrouped(requestDo.getAssetMoveToAddressGroupedMADC());
		return asset;
    }
	
	private static AccountContact populatePrimaryContact(RequestTypeDo requestDo) {
		AccountContact primary = new AccountContact();
		primary.setFirstName(requestDo.getPrimaryContactFirstName());
		primary.setLastName(requestDo.getPrimaryContactLastName());
		primary.setEmailAddress(requestDo.getPrimaryContactEmailAddress());
		primary.setWorkPhone(requestDo.getPrimaryContactWorkPhone());
		return primary;
	}
	
	private static AccountContact populatePrimaryContact(SupplyRequestDetailDo requestDo) {
		AccountContact primary = new AccountContact();
		primary.setFirstName(requestDo.getPrimaryContactFirstName());
		primary.setLastName(requestDo.getPrimaryContactLastName());
		primary.setEmailAddress(requestDo.getPrimaryContactEmailAddress());
		primary.setWorkPhone(requestDo.getPrimaryContactWorkPhone());
		primary.setContactId(requestDo.getPrimaryContactId());
		primary.setDepartment(requestDo.getPrimaryContactDepartment());
		return primary;
	}
	
	private static AccountContact populateRequestorContact(RequestTypeDo requestDo) {
		AccountContact requestor = new AccountContact();
		requestor.setFirstName(requestDo.getRequestorContactFirstName());
		requestor.setLastName(requestDo.getRequestorContactLastName());
		requestor.setEmailAddress(requestDo.getRequestorContactEmailAddress());
		requestor.setWorkPhone(requestDo.getRequestorContactWorkPhone());
		return requestor;
	}
	
	private static AccountContact populateRequestorContact(SupplyRequestDetailDo requestDo) {
		AccountContact requestor = new AccountContact();
		requestor.setFirstName(requestDo.getRequestorContactFirstName());
		requestor.setLastName(requestDo.getRequestorContactLastName());
		requestor.setEmailAddress(requestDo.getRequestorContactEmailAddress());
		requestor.setWorkPhone(requestDo.getRequestorContactWorkPhone());
		requestor.setContactId(requestDo.getRequestorContactId());
		return requestor;
	}


	private static GenericAddress populateServiceAddress(RequestTypeDo requestDo) {
		GenericAddress serviceAddress = new GenericAddress();
		serviceAddress.setAddressLine1(requestDo.getServiceAddressLine1());
		serviceAddress.setAddressLine2(requestDo.getServiceAddressLine2());
		serviceAddress.setAddressLine3(requestDo.getServiceAddressLine3());
		serviceAddress.setCity(requestDo.getServiceCity());
		serviceAddress.setState(requestDo.getServiceState());
		serviceAddress.setPostalCode(requestDo.getServicePostalCode());
		serviceAddress.setProvince(requestDo.getServiceProvince());
		serviceAddress.setCountry(requestDo.getServiceCountry());
		serviceAddress.setAddressName(requestDo.getServiceAddressName());
		serviceAddress.setCounty(requestDo.getCounty());
		serviceAddress.setDistrict(requestDo.getDistrict());
		serviceAddress.setOfficeNumber(requestDo.getOfficeNumber());
		serviceAddress.setStoreFrontName(requestDo.getStoreFrontName());
		return serviceAddress;
	}
	
	
    private static GenericAddress populateServiceAddress(SupplyRequestDetailDo requestDo) {
        GenericAddress serviceAddress = new GenericAddress();
        serviceAddress.setAddressLine1(requestDo.getServiceAddress1()); 
        serviceAddress.setAddressLine2(requestDo.getServiceAddress2()); 
        serviceAddress.setAddressLine3(requestDo.getServiceAddress3());
        serviceAddress.setCity(requestDo.getServiceCity());
        serviceAddress.setState(requestDo.getServiceState());
        serviceAddress.setPostalCode(requestDo.getServicePostalCode());
        serviceAddress.setProvince(requestDo.getServiceProvince());
        serviceAddress.setCountry(requestDo.getServiceCountry());
        serviceAddress.setAddressName(requestDo.getServiceAddressName());
        serviceAddress.setAddressId(requestDo.getServiceAddressId());
        serviceAddress.setStoreFrontName(requestDo.getStoreFrontName());
		serviceAddress.setCounty(requestDo.getServiceCounty());
		serviceAddress.setDistrict(requestDo.getDistrict());
		serviceAddress.setOfficeNumber(requestDo.getOfficeNumber());
		//OPS
//		serviceAddress.setAddressName(requestDo.getAddressName());
		serviceAddress.setLBSIdentifierFlag(requestDo.getLBSIdentifierFlag());
		/*
		 * populate physical location 1, location 2, location 3 with comma
		 * separated values
		 */
		if (LangUtil.isNotEmpty(requestDo.getServiceLocation())) {

			if (LangUtil.isNotBlank(requestDo.getOrderSource())
					&& "Web".equalsIgnoreCase(requestDo.getOrderSource())) {
				populatePhysicalLocationContiansPrefix(requestDo,
						serviceAddress);
			} else {
				populatePhysicalLocationWOPrefix(requestDo, serviceAddress);
			}
		}

        return serviceAddress;
    }	
	private static void populatePhysicalLocationWOPrefix (SupplyRequestDetailDo requestDo, GenericAddress serviceAddress) {
		String[] locations = requestDo.getServiceLocation().split(",");
		for(int i = 0 ; i < locations.length ; i++){
			 switch(i){
			 case 0: 
       			 serviceAddress.setPhysicalLocation1(locations[i]);
        		 break;
        	 case 1:
        		 serviceAddress.setPhysicalLocation2(locations[i]);
        		 break;
        	 case 2:
        		 serviceAddress.setPhysicalLocation3(locations[i]);
        		 break;
			 }
		}
	}
    private static void populatePhysicalLocationContiansPrefix (SupplyRequestDetailDo requestDo, GenericAddress serviceAddress) {
    	String[] locations = requestDo.getServiceLocation().split(",");
    	LocationEnum locationEnum = null;
    	for(int i = 0 ; i < locations.length ; i++){
	    	List<String> physicalLocation = Arrays.asList(locations[i].split(" "));
	    	if(LangUtil.isNotEmpty(physicalLocation)) {
	    		try{
	    			locationEnum = LocationEnum.valueOf(physicalLocation.get(0));
	    		}catch (IllegalArgumentException exception){
	    			//squash
	    		}
	    	}
	    	if(locationEnum != null) {
	    		switch(locationEnum)
	    		{
	    		case Building: 
	    			locations[i] = locations[i].replaceFirst(LocationEnum.Building.toString(), "");
	    			serviceAddress.setPhysicalLocation1(locations[i].trim());
	    			break;
	    		case Floor:
	    			locations[i] = locations[i].replaceFirst(LocationEnum.Floor.toString(), "");
	    			serviceAddress.setPhysicalLocation2(locations[i].trim());
	    			break;
	    		case Office:
	    			locations[i] = locations[i].replaceFirst(LocationEnum.Office.toString(), "");
	    			serviceAddress.setPhysicalLocation3(locations[i].trim());
	    			break;
	
	    		}
	    	}
    	}
    }
	private static<T extends RequestTypeDo> List<Part> populateParts(T requestDo) {
		List<? extends RequestTypePartBaseDo> partsDo = null;
		
		if(requestDo instanceof RequestTypeFavoriteAssetDo){
			partsDo = ((RequestTypeFavoriteAssetDo)requestDo).getAssetParts();			
		}else if(requestDo instanceof RequestTypeDo) {
			partsDo = requestDo.getParts();
		}

		List<Part> parts = new ArrayList<Part>();
		if (partsDo == null) {
			return parts;
		}
		
		for (RequestTypePartBaseDo partDo: partsDo) {
			Part part = new Part();
			part.setPartNumber(LangUtil.isEmpty(partDo.getVendorPartNumber()) ? partDo.getPartNumber() : partDo.getVendorPartNumber());
			part.setDescription(partDo.getPartDescription());
			part.setPartType(partDo.getPartType());
			part.setOrderQuantity(partDo.getPartOrderQuantity());
			part.setImplicitFlag(partDo.isImplicitFlag());
			parts.add(part);
		}
		
		return parts;
	}

	private static AccountContact populateSecondaryContact(SupplyRequestDetailDo requestDetail) {
		AccountContact secondary = new AccountContact();
		secondary.setFirstName(requestDetail.getSecondaryContactFirstName());
		secondary.setLastName(requestDetail.getSecondaryContactLastName());
		secondary.setEmailAddress(requestDetail.getSecondaryContactEmailAddress());
		secondary.setWorkPhone(requestDetail.getSecondaryContactWorkPhone());
		secondary.setContactId(requestDetail.getSecondaryContactId());
		secondary.setDepartment(requestDetail.getSecondaryContactDepartment());
		return secondary;
	}

	private static ListOfValues createListOfValues(String value) {
		ListOfValues lov = new ListOfValues();
		lov.setValue(value);
		return lov;
	}
	
	private AmindRequestTypeConversionUtil() {
	}
	
	
	
    private static String pupulateCurrency(List<SupplyRequestDetailCustomerOrderItemDo> partDOs) {
    	
    	for (SupplyRequestDetailCustomerOrderItemDo partDO : partDOs) {
			if(LangUtil.isNotEmpty(partDO.getCurrency())) {
				return partDO.getCurrency();
			}
		}
    	
		return null;
	}
    
    private static List<AccountContact> populateDeviceContacts(ArrayList<ServiceRequestDetailContactDo> deviceContacts) {

		List<AccountContact> contacts = new ArrayList<AccountContact>();
		
		if(LangUtil.isNotEmpty(deviceContacts)) {
			for (ServiceRequestDetailContactDo contact : deviceContacts) {
				AccountContact accountContact = new AccountContact();
				
				accountContact.setDeviceContactType(contact.getDeviceContactType());
				accountContact.setFirstName(contact.getFirstName());
				accountContact.setLastName(contact.getLastName());
				accountContact.setWorkPhone(contact.getWorkPhone());
				accountContact.setEmailAddress(contact.getEmailAddress());
				accountContact.setHomePhone(contact.getHomePhone());
				
				GenericAddress contactAddress = new GenericAddress();
				contactAddress.setAddressLine1(contact.getAddressLine1());
				contactAddress.setAddressLine2(contact.getAddressLine2());
				contactAddress.setAddressLine3(contact.getAddressLine3());
				contactAddress.setCity(contact.getCity());
				contactAddress.setState(contact.getState());
				contactAddress.setPostalCode(contact.getPostalCode());
				contactAddress.setProvince(contact.getProvince());
				contactAddress.setCountry(contact.getCountry());
				contactAddress.setAddressName(contact.getAddressName());
				contactAddress.setAddressId(contact.getAddressId());
				contactAddress.setStoreFrontName(contact.getStoreFrontName());
				contactAddress.setCounty(contact.getCounty());
				contactAddress.setDistrict(contact.getDistrict());
				contactAddress.setOfficeNumber(contact.getOfficeNumber());
				contactAddress.setCountryISOCode(contact.getCountryIsoCode());
				contactAddress.setRegion(contact.getRegion());
				contactAddress.setPhysicalLocation1(contact.getBuilding());
				contactAddress.setPhysicalLocation2(contact.getFloor());
				contactAddress.setPhysicalLocation3(contact.getOffice());

				accountContact.setAddress(contactAddress);
				
				contacts.add(accountContact);
			}
		}
		
		return contacts;
	}
}
