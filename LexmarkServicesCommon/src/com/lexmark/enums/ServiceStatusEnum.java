package com.lexmark.enums;

/**
 * Enum for service status.
 */
public enum ServiceStatusEnum {

	IN_PROCESS("Inprocess"),
	COMPLETED("Completed"),
	SHIPPED("Shipped"),
	SUBMITTED("Submitted"),
	ADDITIONAL_SERVICE_REQUIRED("Additional Service Required"),
	DELIVERED("Delivered");
	
	private String value;
	ServiceStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
