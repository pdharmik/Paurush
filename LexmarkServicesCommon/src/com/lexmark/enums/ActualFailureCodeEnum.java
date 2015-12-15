package com.lexmark.enums;

/**
 * Enum for actual failure code.
 */
public enum ActualFailureCodeEnum {

	NO_COMMUNICATION("No Communication"),
	PAPER_JAM("Paper jam"),
	
	// TODO: needs to be removed in future
	CODE_TREE("Code 3");
	
	private String value;
	
	ActualFailureCodeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
