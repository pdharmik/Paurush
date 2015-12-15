package com.lexmark.api.enums;

public enum ScheduleEnum {
	RUNNOW("RUNNOW"), 
	SCHEDULE("SCHEDULE"), 
	DAILY("DAILY"), 
	WEEKLY("WEEKLY"), 
	MONTHLY("MONTHLY");

	private String value;

	ScheduleEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
