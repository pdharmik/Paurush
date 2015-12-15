package com.lexmark.enums;

/**
 * Enum for Carrier
 */
public enum CarrierEnum {

	FEDEX("Federal Express"),
	UPS("UPS"),
	DHL("Airborne"),
	YELLOW_FREIGHT("Yellow Freight"),
	SA_SA("SA , SA");
	
	private String value;
	CarrierEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
