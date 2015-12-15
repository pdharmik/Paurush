package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-supplyrequestdetailshipmentitem-mapping.xml
 * 
 */
public class SupplyRequestDetailShipmentItemDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String carrier;
    private String eta;
    private Date actualDeliveryDate;
    private Date actualShipDate;
    private String productTLI;
    private String description;
    private String vendorProduct;
    private String quantity;
    private ArrayList<SupplyRequestDetailShipmentItemOrderEntryDo> shipmentOrderEntryItems;
    private String trackingNumber;
    private String shippedQuantity;
    private String status;
    private String partName;
    private String partType;
    private String partNumber;
    private Date shipmentDate;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
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

    public Date getActualShipDate() {
        return actualShipDate;
    }

    public void setActualShipDate(Date actualShipDate) {
        this.actualShipDate = actualShipDate;
    }

    public String getProductTLI() {
        return productTLI;
    }

    public void setProductTLI(String productTLI) {
        this.productTLI = productTLI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendorProduct() {
        return vendorProduct;
    }

    public void setVendorProduct(String vendorProduct) {
        this.vendorProduct = vendorProduct;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public ArrayList<SupplyRequestDetailShipmentItemOrderEntryDo> getShipmentOrderEntryItems() {
        return shipmentOrderEntryItems;
    }

    public void setShipmentOrderEntryItems(ArrayList<SupplyRequestDetailShipmentItemOrderEntryDo> shipmentOrderEntryItems) {
        this.shipmentOrderEntryItems = shipmentOrderEntryItems;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShippedQuantity() {
        return shippedQuantity;
    }

    public void setShippedQuantity(String shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
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

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

}
