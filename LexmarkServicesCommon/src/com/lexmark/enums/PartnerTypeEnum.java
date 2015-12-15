package com.lexmark.enums;

/**
 * Enum for partner type.
 */
public enum PartnerTypeEnum {

	DIRECT("DIRECT"),
	INDIRECT("INDIRECT"),
	BOTH("BOTH");
	
	private String value;
	
	PartnerTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
