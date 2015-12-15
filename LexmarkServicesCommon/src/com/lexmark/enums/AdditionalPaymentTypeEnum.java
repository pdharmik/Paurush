package com.lexmark.enums;

/**
 * Enum for additional payment type.
 */
public enum AdditionalPaymentTypeEnum {

	ADDITIONAL_PAYMENT_TYPE_ONE("Payment Type One"),
	ADDITIONAL_PAYMENT_TYPE_TWO("Payment Type Two");
	
	private String value;
	
	AdditionalPaymentTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
