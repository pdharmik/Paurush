package com.lexmark.enums;

public enum DebriefStatusEnum {
	
	FAILED_VALIDATION("Failed Validation"),
	VALIDATED("Validated");
	
	private String value;
	
	DebriefStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
