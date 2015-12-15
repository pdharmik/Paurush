package com.lexmark.enums;

/**
 * Enum for Part Status.
 */
public enum PartStatusEnum {

	DOA("DOA"),
	USED("Used"),
	NOT_USED("Not Used");
	
	private String value;
	
	PartStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
