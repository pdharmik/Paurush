package com.lexmark.services.form;

import java.util.Date;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class ShipmentForm {
	
	private int shipmentProgress;
	private String shipmentXML;
	private String trackingNumber;
	private String carrier;
	private String carrierLink;
	private boolean hasProperUrl;
	private String ETA;
	private Date actualDeliveryDate;
	private Date actualShipDate;
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public int getShipmentProgress() {
		return shipmentProgress;
	}
	public void setShipmentProgress(int shipmentProgress) {
		this.shipmentProgress = shipmentProgress;
	}
	public String getShipmentXML() {
		return shipmentXML;
	}
	public void setShipmentXML(String shipmentXML) {
		this.shipmentXML = shipmentXML;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getCarrierLink() {
		return carrierLink;
	}
	public void setCarrierLink(String carrierLink) {
		this.carrierLink = carrierLink;
	}
	public boolean isHasProperUrl() {
		return hasProperUrl;
	}
	public void setHasProperUrl(boolean hasProperUrl) {
		this.hasProperUrl = hasProperUrl;
	}
	public String getETA() {
		return ETA;
	}
	public void setETA(String eTA) {
		ETA = eTA;
	}
	public Date getActualDeliveryDate() {
		return actualDeliveryDate;
	}
	public void setActualDeliveryDate(Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}
	/**
	 * @param actualShipDate the actualShipDate to set
	 */
	public void setActualShipDate(Date actualShipDate) {
		this.actualShipDate = actualShipDate;
	}
	/**
	 * @return the actualShipDate
	 */
	public Date getActualShipDate() {
		return actualShipDate;
	}
	
	
}
