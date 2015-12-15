package com.lexmark.enums;

/**
 * Enum for Service Request Type
 */
public enum ServiceRequestTypeEnum {

	CLAIM_REQUEST("Claim Request"),
	SERVICE_REQUEST("Service Request"),
	ORDER_REQUEST("Order Request");
	
	private String value;
	
	ServiceRequestTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
