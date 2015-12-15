package com.lexmark.enums;

/**
 * Enum for line status.
 */
public enum LineStatusEnum {

	SHIPPED("Shipped"),
	IN_PROCESS("In process"),
	//Added By MPS Offshore Team for status update dropdown
	DELIVERED("Shipped"),
	RETURNED("Returned");
	
	private String value;
	
	LineStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
