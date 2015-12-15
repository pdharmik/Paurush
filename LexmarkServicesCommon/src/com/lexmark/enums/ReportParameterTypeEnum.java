package com.lexmark.enums;

public enum ReportParameterTypeEnum {
	BOOLEAN("BOOLEAN", "Boolean"),
	DATE("DATE", "Date"),
	LIST("LIST", "List"),
	NUMERIC("NUMERIC", "Numeric"),
	STRING("STRING", "String");
	
	private String type;
	private String displayName;
	ReportParameterTypeEnum(String type, String displayName) {
		this.type = type;
		this.displayName = displayName;
	}
	public String getType() {
		return type;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public static ReportParameterTypeEnum instance(String type) {
		for(ReportParameterTypeEnum e: ReportParameterTypeEnum.values()) {
			if(e.getType().equalsIgnoreCase(type)) {
				return e;
			}
		}
		return STRING;
	}
}
