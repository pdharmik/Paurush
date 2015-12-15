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
	/**
	 * 
	 * @return String 
	 */
	public String getCarrier() {
		return carrier;
	}
	/**
	 * 
	 * @param carrier 
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	/**
	 * 
	 * @return Integer 
	 */
	public int getShipmentProgress() {
		return shipmentProgress;
	}
	/**
	 * 
	 * @param shipmentProgress 
	 */
	public void setShipmentProgress(int shipmentProgress) {
		this.shipmentProgress = shipmentProgress;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getShipmentXML() {
		return shipmentXML;
	}
	/**
	 * 
	 * @param shipmentXML 
	 */
	public void setShipmentXML(String shipmentXML) {
		this.shipmentXML = shipmentXML;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getTrackingNumber() {
		return trackingNumber;
	}
	/**
	 * 
	 * @param trackingNumber 
	 */
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCarrierLink() {
		return carrierLink;
	}
	/**
	 * 
	 * @param carrierLink 
	 */
	public void setCarrierLink(String carrierLink) {
		this.carrierLink = carrierLink;
	}
	/**
	 * 
	 * @return Boolean 
	 */
	public boolean isHasProperUrl() {
		return hasProperUrl;
	}
	/**
	 * 
	 * @param hasProperUrl 
	 */
	public void setHasProperUrl(boolean hasProperUrl) {
		this.hasProperUrl = hasProperUrl;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getETA() {
		return ETA;
	}
	/**
	 * 
	 * @param eTA 
	 */
	public void setETA(String eTA) {
		ETA = eTA;
	}
	/**
	 * 
	 * @return Date 
	 */
	public Date getActualDeliveryDate() {
		return actualDeliveryDate;
	}
	/**
	 * 
	 * @param actualDeliveryDate 
	 */ 
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
