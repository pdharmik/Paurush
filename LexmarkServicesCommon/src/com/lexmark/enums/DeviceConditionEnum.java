package com.lexmark.enums;

/**
 * Enum for device condition.
 *
 */
public enum DeviceConditionEnum {

	WORKING("Working"),
	NOT_WORKING("Not Working");
	
	private String value;
	
	DeviceConditionEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
