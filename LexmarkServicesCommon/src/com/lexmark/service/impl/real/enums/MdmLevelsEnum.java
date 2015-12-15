package com.lexmark.service.impl.real.enums;

public enum MdmLevelsEnum {
	GLOBAL("Global"), DOMESTIC("Domestic"), LEGAL("Legal"), ACCOUNT("Account"), SIEBEL("Siebel");

	private String mdmLevel;

	MdmLevelsEnum(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	@Override
	public String toString() {
		return mdmLevel;
	}

}
