package com.lexmark.enums;

/**
 * Enum for travel unit of measure.
 */
public enum TravelUnitOfMeasureEnum {
	
	MILES("Miles");
	
	private String value;
	
	TravelUnitOfMeasureEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
