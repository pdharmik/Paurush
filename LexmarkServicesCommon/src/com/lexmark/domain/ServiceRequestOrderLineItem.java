package com.lexmark.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.amind.common.domain.BaseEntity;

public class ServiceRequestOrderLineItem extends BaseEntity implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7231708309504429182L;
    private String carrier;
    private String trackingNumber;
    private String serviceRequestId;
    private String assetshippedModelNumber;
    private String orderLineType;
    private String productTLI;
    private String productDescription;
    private String serialNumber;
    private String status;
    private Date statusDate;
    private String quantity;
    private String eta;
    private Date actualDeliveryDate;
    private String vendorProduct;
    private String contactMethod;
    private String partnumber;
    private int backOrderQuantity;
    private int shippedQuantity;
    private int pendingQuantity;
    private Date orderedDate;
    private String partName;
    private Date actualShipDate;
    private List<String> serialNumbers;
    private String vendorId;
    private String partType;
    private String orderReferenceNumber;
    private String orderFultillmentStatus;
    private String portalFulfillmentStatus;    
    private String fulfillmentType;
    private String deviceType;
    private BigDecimal price;
    private String supplyType;
    private BigDecimal netPrice;
    
    
    private int quantityRequested;
    private int remainingQuantity;
    private int backOrderQty;

    private Date shippedDate;
    private Date backOrderedDate;
    private Date deliveryDate;
    private String customerReqItemId;
    
    private String tracking;

    private String shipmentStatus;
    
    private String lineNumber;
    //Added by portal team
    private String currency;
	private String fulfillmentStatus;
	
	private String subStatus;
	private String severity;
	
	private String serviceProviderName;
	//For OPS portal
	private String authorizationCode;
	private String authorizationReason;
	private int shipmentProgress;
	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	
    
    public String getAuthorizationReason() {
		return authorizationReason;
	}

	public void setAuthorizationReason(String authorizationReason) {
		this.authorizationReason = authorizationReason;
	}

	public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductTLI() {
        return productTLI;
    }

    public void setProductTLI(String productTLI) {
        this.productTLI = productTLI;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getOrderLineType() {
        return orderLineType;
    }

    public void setOrderLineType(String orderLineType) {
        this.orderLineType = orderLineType;
    }

    public String getAssetshippedModelNumber() {
        return assetshippedModelNumber;
    }

    public void setAssetshippedModelNumber(String assetshipedModelNumber) {
        this.assetshippedModelNumber = assetshipedModelNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getVendorProduct() {
        return vendorProduct;
    }

    public void setVendorProduct(String vendorProduct) {
        this.vendorProduct = vendorProduct;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

	public void setSerialNumbers(List<String> serialNumbers) {
		this.serialNumbers = serialNumbers;
	}

	public List<String> getSerialNumbers() {
		return serialNumbers;
	}

	public String getPartnumber() {
		return partnumber;
	}

	public void setPartnumber(String partnumber) {
		this.partnumber = partnumber;
	}

	public int getBackOrderQuantity() {
		return backOrderQuantity;
	}

	public void setBackOrderQuantity(int backOrderQuantity) {
		this.backOrderQuantity = backOrderQuantity;
	}

	public int getShippedQuantity() {
		return shippedQuantity;
	}

	public void setShippedQuantity(int shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
	}

	public int getPendingQuantity() {
        return pendingQuantity;
    }

    public void setPendingQuantity(int pendingQuantity) {
        this.pendingQuantity = pendingQuantity;
    }

    public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Date getActualShipDate() {
		return actualShipDate;
	}

	public void setActualShipDate(Date actualShipDate) {
		this.actualShipDate = actualShipDate;
	}

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
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
		return fulfillmentType;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public BigDecimal getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	public int getQuantityRequested() {
		return quantityRequested;
	}

	public void setQuantityRequested(int quantityRequested) {
		this.quantityRequested = quantityRequested;
	}

	public int getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public int getBackOrderQty() {
		return backOrderQty;
	}

	public void setBackOrderQty(int backOrderQty) {
		this.backOrderQty = backOrderQty;
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

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	public String getFulfillmentStatus() {
		return fulfillmentStatus;
	}

	public void setFulfillmentStatus(String fulfillmentStatus) {
		this.fulfillmentStatus = fulfillmentStatus;
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

	public String getServiceProviderName() {
		return serviceProviderName;
	}

	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	public int getShipmentProgress() {
		return shipmentProgress;
	}

	public void setShipmentProgress(int shipmentProgress) {
		this.shipmentProgress = shipmentProgress;
	}

}
