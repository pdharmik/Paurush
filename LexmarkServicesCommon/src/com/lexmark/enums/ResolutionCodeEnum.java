package com.lexmark.enums;

/**
 * Enum for Resolution Code.
 */
public enum ResolutionCodeEnum {

	RESOLUTION_CODE_ONE("12345678"),
	RESOLUTION_CODE_TWO("837629");
	
	private String value;
	
	ResolutionCodeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
