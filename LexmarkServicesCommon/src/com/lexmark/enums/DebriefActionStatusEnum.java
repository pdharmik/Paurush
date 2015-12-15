package com.lexmark.enums;

public enum DebriefActionStatusEnum {
	
	INCOMPLETE("Incomplete"),
	COMPLETE("Complete");
	
	private String value;
	
	DebriefActionStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
