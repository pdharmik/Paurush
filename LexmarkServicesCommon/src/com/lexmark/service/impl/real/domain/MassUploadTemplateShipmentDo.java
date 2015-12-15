package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author i.prikhodko
 *
 * do-mapping: do-massuploadtemplateshipmentdo-mapping.xml
 */

public class MassUploadTemplateShipmentDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String shippedQuantity;
	private String shippedDate;
	private String trackingNumber;
	private String carrier;
	private String shipmentStatus;
	private String deliveryDate;
	
	public String getShippedQuantity() {
		return shippedQuantity;
	}
	public void setShippedQuantity(String shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
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
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
