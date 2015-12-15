package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * 
 * @author vshynkarenko
 *         mapping-file: "do-supplyrequestdetailorderitem-mapping.xml"
 */
public class SupplyRequestDetailOrderItemDo extends BaseEntity {

    private String orderNumber; 
    private Date orderDate;
    private String serviceAddress1;
    private String serviceAddress2;
    private String serviceAddress3;
    private String serviceCity;
    private String serviceState;
    private String servicePostalCode;
    private String serviceProvince;
    private String serviceCountry;
    private String serviceAddressName;
    private String secondaryContactFirstName;
    private String secondaryContactLastName;
    private String secondaryContactEmailAddress;
    private String secondaryContactWorkPhone;
    private String primaryContactFirstName;
    private String primaryContactLastName;
    private String primaryContactEmailAddress;
    private String primaryContactWorkPhone;
    private String status;
    private String portalSpecialInstruction;
    private Date requestedDeliveryDate;
    private String attentionTo;
    private String assetIdentifier;
    private String insideLocation;
    private String specialHandlingInstructions;
    private String mustResolveBy;
    private String serviceRequestNumber;
    private String storeFrontName;
    private String serialNumber;
    private String requestType;
    private String orderType;     

    private ArrayList<SupplyRequestDetailPendingShipmentDo> pendingShipments = new ArrayList<SupplyRequestDetailPendingShipmentDo>();
    private ArrayList<SupplyRequestDetailShipmentDo> shippedItems = new ArrayList<SupplyRequestDetailShipmentDo>();
    
	private String county;
	private String district;
	private String officeNumber;
	
	//OPS
	private String subStatus;
	private String severity;
	
	private ArrayList<SupplyRequestOrderedPartsDo> orderedParts;
	private ArrayList<SupplyRequestLbsLineItemsDo> lbsOrderLineItems;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ArrayList<SupplyRequestDetailPendingShipmentDo> getPendingShipments() {
        return pendingShipments;
    }

    public void setPendingShipments(
            ArrayList<SupplyRequestDetailPendingShipmentDo> pendingShipments) {
        this.pendingShipments = pendingShipments;
    }

    public ArrayList<SupplyRequestDetailShipmentDo> getShippedItems() {
        return shippedItems;
    }

    public void setShippedItems(
            ArrayList<SupplyRequestDetailShipmentDo> shippedItems) {
        this.shippedItems = shippedItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getServiceAddress1() {
        return serviceAddress1;
    }

    public void setServiceAddress1(String serviceAddress1) {
        this.serviceAddress1 = serviceAddress1;
    }

    public String getServiceAddress2() {
        return serviceAddress2;
    }

    public void setServiceAddress2(String serviceAddress2) {
        this.serviceAddress2 = serviceAddress2;
    }

    public String getServiceAddress3() {
        return serviceAddress3;
    }

    public void setServiceAddress3(String serviceAddress3) {
        this.serviceAddress3 = serviceAddress3;
    }

    public String getServiceCity() {
        return serviceCity;
    }

    public void setServiceCity(String serviceCity) {
        this.serviceCity = serviceCity;
    }

    public String getServiceState() {
        return serviceState;
    }

    public void setServiceState(String serviceState) {
        this.serviceState = serviceState;
    }

    public String getServicePostalCode() {
        return servicePostalCode;
    }

    public void setServicePostalCode(String servicePostalCode) {
        this.servicePostalCode = servicePostalCode;
    }

    public String getServiceProvince() {
        return serviceProvince;
    }

    public void setServiceProvince(String serviceProvince) {
        this.serviceProvince = serviceProvince;
    }

    public String getServiceCountry() {
        return serviceCountry;
    }

    public void setServiceCountry(String serviceCountry) {
        this.serviceCountry = serviceCountry;
    }

    public String getServiceAddressName() {
        return serviceAddressName;
    }

    public void setServiceAddressName(String serviceAddressName) {
        this.serviceAddressName = serviceAddressName;
    }

    public String getSecondaryContactFirstName() {
        return secondaryContactFirstName;
    }

    public void setSecondaryContactFirstName(String secondaryContactFirstName) {
        this.secondaryContactFirstName = secondaryContactFirstName;
    }

    public String getSecondaryContactLastName() {
        return secondaryContactLastName;
    }

    public void setSecondaryContactLastName(String secondaryContactLastName) {
        this.secondaryContactLastName = secondaryContactLastName;
    }

    public String getSecondaryContactEmailAddress() {
        return secondaryContactEmailAddress;
    }

    public void setSecondaryContactEmailAddress(String secondaryContactEmailAddress) {
        this.secondaryContactEmailAddress = secondaryContactEmailAddress;
    }

    public String getSecondaryContactWorkPhone() {
        return secondaryContactWorkPhone;
    }

    public void setSecondaryContactWorkPhone(String secondaryContactWorkPhone) {
        this.secondaryContactWorkPhone = secondaryContactWorkPhone;
    }

    public String getPrimaryContactFirstName() {
        return primaryContactFirstName;
    }

    public void setPrimaryContactFirstName(String primaryContactFirstName) {
        this.primaryContactFirstName = primaryContactFirstName;
    }

    public String getPrimaryContactLastName() {
        return primaryContactLastName;
    }

    public void setPrimaryContactLastName(String primaryContactLastName) {
        this.primaryContactLastName = primaryContactLastName;
    }

    public String getPrimaryContactEmailAddress() {
        return primaryContactEmailAddress;
    }

    public void setPrimaryContactEmailAddress(String primaryContactEmailAddress) {
        this.primaryContactEmailAddress = primaryContactEmailAddress;
    }

    public String getPrimaryContactWorkPhone() {
        return primaryContactWorkPhone;
    }

    public void setPrimaryContactWorkPhone(String primaryContactWorkPhone) {
        this.primaryContactWorkPhone = primaryContactWorkPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPortalSpecialInstruction() {
        return portalSpecialInstruction;
    }

    public void setPortalSpecialInstruction(String portalSpecialInstruction) {
        this.portalSpecialInstruction = portalSpecialInstruction;
    }

    public Date getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
        this.requestedDeliveryDate = requestedDeliveryDate;
    }

    public String getMustResolveBy() {
        return mustResolveBy;
    }

    public void setMustResolveBy(String mustResolveBy) {
        this.mustResolveBy = mustResolveBy;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

    public String getServiceRequestNumber() {
        return serviceRequestNumber;
    }
    
    public String getStoreFrontName() {
        return storeFrontName;
    }

    public void setStoreFrontName(String storeFrontName) {
        this.storeFrontName = storeFrontName;
    }
    
	public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAttentionTo() {
		return attentionTo;
	}

	public void setAttentionTo(String attentionTo) {
		this.attentionTo = attentionTo;
	}

	public String getAssetIdentifier() {
		return assetIdentifier;
	}

	public void setAssetIdentifier(String assetIdentifier) {
		this.assetIdentifier = assetIdentifier;
	}

	public String getInsideLocation() {
		return insideLocation;
	}

	public void setInsideLocation(String insideLocation) {
		this.insideLocation = insideLocation;
	}

	public String getSpecialHandlingInstructions() {
		return specialHandlingInstructions;
	}

	public void setSpecialHandlingInstructions(String specialHandlingInstructions) {
		this.specialHandlingInstructions = specialHandlingInstructions;
	}

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getCounty() {
    	return county;
    }

    public void setCounty(String county) {
    	this.county = county;
    }

    public String getDistrict() {
    	return district;
    }

    public void setDistrict(String district) {
    	this.district = district;
    }

    public String getOfficeNumber() {
    	return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
    	this.officeNumber = officeNumber;
    }

	public ArrayList<SupplyRequestOrderedPartsDo> getOrderedParts() {
		return orderedParts;
	}

	public void setOrderedParts(ArrayList<SupplyRequestOrderedPartsDo> orderedParts) {
		this.orderedParts = orderedParts;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public ArrayList<SupplyRequestLbsLineItemsDo> getLbsOrderLineItems() {
		return lbsOrderLineItems;
	}

	public void setLbsOrderLineItems(
			ArrayList<SupplyRequestLbsLineItemsDo> lbsOrderLineItems) {
		this.lbsOrderLineItems = lbsOrderLineItems;
	}

 }
