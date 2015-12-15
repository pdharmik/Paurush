package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-supplyrequestdetailpendingshipment-mapping.xml
 * 
 * @see com.lexmark.service.impl.real.domain.SupplyRequestDetailDo
 */
public class SupplyRequestDetailPendingShipmentDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String partNumber;
    private String partName;
    private String partType;
    private String description;
    private String status;
    private String pendingQuantity;
    private String vendorId;
    private String requestedQuantity;
    private String backOrderQuantity;
    private Date orderedDate;
    private String orderReferenceNumber;
    private String expectedShipDate;
    private String shippedQuantity;
    private String cancelledQuantity;
    private String orderLineType;
    private String cancellationReason;
    private String fulfillmentType;
    private ArrayList<SupplyRequestDetailOrderLineShipmentItemDo> pendingShipmentItems;
    private String portalFulfillmentStatus; 
    private String vendorPartNumber;
    

    public String getVendorPartNumber() {
		return vendorPartNumber;
	}

	public void setVendorPartNumber(String vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPendingQuantity() {
        return pendingQuantity;
    }

    public void setPendingQuantity(String pendingQuantity) {
        this.pendingQuantity = pendingQuantity;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(String requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }

    public String getBackOrderQuantity() {
        return backOrderQuantity;
    }

    public void setBackOrderQuantity(String backOrderQuantity) {
        this.backOrderQuantity = backOrderQuantity;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public ArrayList<SupplyRequestDetailOrderLineShipmentItemDo> getPendingShipmentItems() {
        return pendingShipmentItems;
    }

    public void setPendingShipmentItems(ArrayList<SupplyRequestDetailOrderLineShipmentItemDo> pendingShipmentItems) {
        this.pendingShipmentItems = pendingShipmentItems;
    }

    public void setOrderReferenceNumber(String orderReferenceNumber) {
        this.orderReferenceNumber = orderReferenceNumber;
    }

    public String getOrderReferenceNumber() {
        return orderReferenceNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getExpectedShipDate() {
        return expectedShipDate;
    }

    public void setExpectedShipDate(String expectedShipDate) {
        this.expectedShipDate = expectedShipDate;
    }

    public void setShippedQuantity(String shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }

    public String getShippedQuantity() {
        return shippedQuantity;
    }

    public void setCancelledQuantity(String cancelledQuantity) {
        this.cancelledQuantity = cancelledQuantity;
    }

    public String getCancelledQuantity() {
        return cancelledQuantity;
    }
    

    public String getOrderLineType() {
        return orderLineType;
    }
    

    public void setOrderLineType(String orderLineType) {
        this.orderLineType = orderLineType;
    }

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

    public String getPortalFulfillmentStatus() {
        return portalFulfillmentStatus;
    }

    public void setPortalFulfillmentStatus(String portalFulfillmentStatus) {
        this.portalFulfillmentStatus = portalFulfillmentStatus;
    }

	public void setFulfillmentType(String fulfillmentType) {
		this.fulfillmentType = fulfillmentType;
	}

	public String getFulfillmentType() {
		if(fulfillmentType != null && fulfillmentType.equalsIgnoreCase("Notify & Close"))
		{
			return fulfillmentType;
		}
		return null;
	}
}