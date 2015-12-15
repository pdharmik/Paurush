package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;

public class MassUploadTemplateShipment implements Serializable{

	private static final long serialVersionUID = 1L;

	private int shippedQuantity;
	private Date shippedDate;
	private String trackingNumber;
	private String carrier;
	private String shipmentStatus;
	private Date deliveryDate;
	
	public int getShippedQuantity() {
		return shippedQuantity;
	}
	public void setShippedQuantity(int shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
	}
	public Date getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(Date shippedDate) {
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
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
}
