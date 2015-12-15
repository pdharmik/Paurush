package com.lexmark.enums;

/**
 * Enum for service type.
 */
public enum ServiceTypeEnum {

	INSTALLATION("Installation"),
	INSTALLATION_DELINSTALLATION("Installation / Deinstallation"),
	ONSITE_EXCHANGE("Onsite Exchange"),
	MECHANICAL_REPLACEMENT_WITH_TECHNICIAN("Mech Replacement with Tech");
	
	
	private String value;
	ServiceTypeEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
