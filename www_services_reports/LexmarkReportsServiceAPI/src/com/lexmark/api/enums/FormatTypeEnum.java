package com.lexmark.api.enums;

public enum FormatTypeEnum {
	EXCEL("XLS"), PDF("PDF");

	private String value;

	FormatTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
