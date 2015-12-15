package com.lexmark.enums;

public enum ReportRunFrequencyEnum {
	// higher priority value has higher priority
	ONDEMAND("O", "OnDemand", 3),
	DAILY("D", "Daily", 2),
	WEEKLY("W", "Weekly", 1), 
	MONTHLY("M", "Monthly", 0);
	
	private String code;
	private String displayName;
	private int priority;
	ReportRunFrequencyEnum(String code, String displayName,  int priority) {
		this.code = code;
		this.displayName = displayName;
		this.priority = priority;
	}
	public String getCode() {
		return code;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public static ReportRunFrequencyEnum instance(String code) {
		for(ReportRunFrequencyEnum e: ReportRunFrequencyEnum.values()) {
			if(e.getCode().equalsIgnoreCase(code)) {
				return e;
			}
		}
		return DAILY;
	}
	
}
