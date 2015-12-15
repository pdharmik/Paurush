package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-servicerequestorderlineItems-mapping.xml"
 */
public class ServiceRequestOrderLineItem extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 7231708309504429182L;
	
	private String carrier;
	private String trackingNumber;
	private String serviceRequestId;
	private String assetshippedModelNumber;
	private String orderLineType;
	private String productTLI;
	private String description;
	private String serialNumber;
	private String status;
	private Date statusDate;
	private String modelNumber;
	private String orderType;

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
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

}
