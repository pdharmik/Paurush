package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * @author David Tsamalashvili
 * 
 * The mapping file: do-supplyeequestorderedpartsdo-mapping.xml
 */
public class SupplyRequestOrderedPartsDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String carrierCode;
    private String expediateShippingFlg;
    private String partDisposition;
    private String shippedDate;
    private String trackingNumber;
    private String partNumber;
    private String product;
    private String quantityRequested;
    private String serialNumber;
    private String status;
    private String parentPropductId;
    private String productId;
    //OPS
    private String sourceInventoryLocation;
    private String destinationInventoryLocation;
    
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	public String getExpediateShippingFlg() {
		return expediateShippingFlg;
	}
	public void setExpediateShippingFlg(String expediateShippingFlg) {
		this.expediateShippingFlg = expediateShippingFlg;
	}
	public String getPartDisposition() {
		return partDisposition;
	}
	public void setPartDisposition(String partDisposition) {
		this.partDisposition = partDisposition;
	}
	public String getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(String shippedDate) {
		this.shippedDate = shippedDate;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getQuantityRequested() {
		return quantityRequested;
	}
	public void setQuantityRequested(String quantityRequested) {
		this.quantityRequested = quantityRequested;
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
	public String getParentPropductId() {
		return parentPropductId;
	}
	public void setParentPropductId(String parentPropductId) {
		this.parentPropductId = parentPropductId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSourceInventoryLocation() {
		return sourceInventoryLocation;
	}
	public void setSourceInventoryLocation(String sourceInventoryLocation) {
		this.sourceInventoryLocation = sourceInventoryLocation;
	}
	public String getDestinationInventoryLocation() {
		return destinationInventoryLocation;
	}
	public void setDestinationInventoryLocation(String destinationInventoryLocation) {
		this.destinationInventoryLocation = destinationInventoryLocation;
	}

}
