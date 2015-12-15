package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-partneractivityshipmentdata-mapping.xml"
 */

public class PartnerActivityShipmentDataDo extends BaseEntity {

	private String shipmentCarrier;
	private String shipmentTrackingNumber;
	private String shipmentTrackingStatus;
	private String shipmentDate;
	
	public String getShipmentCarrier() {
		return shipmentCarrier;
	}
	public void setShipmentCarrier(String shipmentCarrier) {
		this.shipmentCarrier = shipmentCarrier;
	}
	public String getShipmentTrackingNumber() {
		return shipmentTrackingNumber;
	}
	public void setShipmentTrackingNumber(String shipmentTrackingNumber) {
		this.shipmentTrackingNumber = shipmentTrackingNumber;
	}

	public String getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getShipmentTrackingStatus() {
		return shipmentTrackingStatus;
	}
	public void setShipmentTrackingStatus(String shipmentTrackingStatus) {
		this.shipmentTrackingStatus = shipmentTrackingStatus;
	}
}
