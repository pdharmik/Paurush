package com.lexmark.constants;

public enum ReportSelectionFlagEnum {
	UNRUN("UNRUN"),
	FAILED("FAILED"),
	NORMAL("NORMAL"),
	FORCE("FORCE"),
	SUBMITNOW("SUBMITNOW");
	
	private String type;
	ReportSelectionFlagEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
