package com.lexmark.enums;

/**
 * Enum for Payment Status.
 */
public enum PaymentStatusEnum {

	PENDING("Pending"),
	REVIEWED("Reviewed"),
	APPROVED("Approved"),
	INVOICED("Invoiced"),
	NOT_PAID("Not Paid"),
	PAID("Paid");
	
	private String value;
	
	PaymentStatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
