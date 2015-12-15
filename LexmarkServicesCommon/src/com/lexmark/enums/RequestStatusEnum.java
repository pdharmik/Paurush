package com.lexmark.enums;

/**
 * Enum for Request Status
 */
public enum RequestStatusEnum {

	OPEN("Open"),
	COMPLETED("Completed"),
	IN_PROGRESS("In Progress"),
	ACCEPTED("Accepted"),
	DISPATCHED_TO_SP("Dispatched to SP"),
	CLAIM_SUBMITTED("Claim Submitted");
	
	private String value;
	
	RequestStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
