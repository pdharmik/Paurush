package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-orderlineitem-mapping.xml
 * 
 * 
 * @author vpetruchok
 * @version 1.0, 2012-07-18
 */
public class OrderLineItemDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String partNumber;
    private String description;
    private String partType;
    private String orderedQuantity;
    private String status;
    private String vendorId;
    private String contactMethod;
    private String orderHeaderId;
    private String orderReferenceNumber;
    private String orderFultillmentStatus;
    private String mdmLevel1AccountId;
    private String mdmLevel2AccountId;
    private String mdmLevel3AccountId;
    private String mdmLevel4AccountId;
    private String mdmLevel5AccountId;
    private String portalFulfillmentStatus;
    private String readyForBilling;
    
    private String quantityRequested;
    private String remainingQuantity;
    private String backOrderQty;
    private String shippedQuantity;
    
    private Date shippedDate;
    private Date backOrderedDate;
    private Date deliveryDate;
    private String customerReqItemId;
    
    private String tracking;
    private String carrier;
    private String shipmentStatus;
    private String serialNumber;
    
    private String lineNumber;
	private String fulfillmentStatus;
    
    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(String orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

	public String getOrderHeaderId() {
		return orderHeaderId;
	}

	public void setOrderHeaderId(String orderHeaderId) {
		this.orderHeaderId = orderHeaderId;
	}

	public void setOrderReferenceNumber(String orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}

	public String getOrderReferenceNumber() {
		return orderReferenceNumber;
	}

	public void setOrderFultillmentStatus(String orderFultillmentStatus) {
		this.orderFultillmentStatus = orderFultillmentStatus;
	}

	public String getOrderFultillmentStatus() {
		return orderFultillmentStatus;
	}

	public String getMdmLevel1AccountId() {
		return mdmLevel1AccountId;
	}

	public void setMdmLevel1AccountId(String mdmLevel1AccountId) {
		this.mdmLevel1AccountId = mdmLevel1AccountId;
	}

	public String getMdmLevel2AccountId() {
		return mdmLevel2AccountId;
	}

	public void setMdmLevel2AccountId(String mdmLevel2AccountId) {
		this.mdmLevel2AccountId = mdmLevel2AccountId;
	}

	public String getMdmLevel3AccountId() {
		return mdmLevel3AccountId;
	}

	public void setMdmLevel3AccountId(String mdmLevel3AccountId) {
		this.mdmLevel3AccountId = mdmLevel3AccountId;
	}

	public String getMdmLevel4AccountId() {
		return mdmLevel4AccountId;
	}

	public void setMdmLevel4AccountId(String mdmLevel4AccountId) {
		this.mdmLevel4AccountId = mdmLevel4AccountId;
	}

	public String getMdmLevel5AccountId() {
		return mdmLevel5AccountId;
	}

	public void setMdmLevel5AccountId(String mdmLevel5AccountId) {
		this.mdmLevel5AccountId = mdmLevel5AccountId;
	}

    public String getPortalFulfillmentStatus() {
        return portalFulfillmentStatus;
    }

    public void setPortalFulfillmentStatus(String portalFulfillmentStatus) {
        this.portalFulfillmentStatus = portalFulfillmentStatus;
    }

	public void setReadyForBilling(String readyForBilling) {
		this.readyForBilling = readyForBilling;
	}

	public String getReadyForBilling() {
		return readyForBilling;
	}

	public String getQuantityRequested() {
		return quantityRequested;
	}

	public void setQuantityRequested(String quantityRequested) {
		this.quantityRequested = quantityRequested;
	}

	public String getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(String remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public String getBackOrderQty() {
		return backOrderQty;
	}

	public void setBackOrderQty(String backOrderQty) {
		this.backOrderQty = backOrderQty;
	}

	public String getShippedQuantity() {
		return shippedQuantity;
	}

	public void setShippedQuantity(String shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Date getBackOrderedDate() {
		return backOrderedDate;
	}

	public void setBackOrderedDate(Date backOrderedDate) {
		this.backOrderedDate = backOrderedDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getCustomerReqItemId() {
		return customerReqItemId;
	}

	public void setCustomerReqItemId(String customerReqItemId) {
		this.customerReqItemId = customerReqItemId;
	}

	public String getTracking() {
		return tracking;
	}

	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getFulfillmentStatus() {
		return fulfillmentStatus;
	}

	public void setFulfillmentStatus(String fulfillmentStatus) {
		this.fulfillmentStatus = fulfillmentStatus;
	}
	
}
