package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-supplyrequestdetailshipment-mapping.xml
 * 
 */
public class SupplyRequestDetailShipmentDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private ArrayList<SupplyRequestDetailShipmentItemDo>  shipmentLineItems;
    private String vendorId;
    private String orderReferenceNumber;
    private String orderLineType;
    private String portalFulfillmentStatus;
    private String fulfillmentType;
    private String supplyType;
    private String netPrice;
    private String trackingNumber;
    private String status;
    private String carrier;
    private String typeIdentifier;

    public ArrayList<SupplyRequestDetailShipmentItemDo> getShipmentLineItems() {
        return shipmentLineItems;
    }

    public void setShipmentLineItems(ArrayList<SupplyRequestDetailShipmentItemDo> shipmentLineItems) {
        this.shipmentLineItems = shipmentLineItems;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

	public void setOrderReferenceNumber(String orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}

	public String getOrderReferenceNumber() {
		return orderReferenceNumber;
	}

    public String getOrderLineType() {
        return orderLineType;
    }

    public void setOrderLineType(String orderLineType) {
        this.orderLineType = orderLineType;
    }

    public String getPortalFulfillmentStatus() {
        return portalFulfillmentStatus;
    }

    public void setPortalFulfillmentStatus(String portalFulfillmentStatus) {
        this.portalFulfillmentStatus = portalFulfillmentStatus;
    }

    public String getFulfillmentType() {
        return fulfillmentType;
    }

    public void setFulfillmentType(String fulfillmentType) {
        this.fulfillmentType = fulfillmentType;
    }

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getNetPrice() {
		if(netPrice==null || netPrice.isEmpty()) return "0";
		return netPrice;
	}

	public void setNetPrice(String netPrice) {
		this.netPrice = netPrice;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getTypeIdentifier() {
		return typeIdentifier;
	}

	public void setTypeIdentifier(String typeIdentifier) {
		this.typeIdentifier = typeIdentifier;
	}
}
