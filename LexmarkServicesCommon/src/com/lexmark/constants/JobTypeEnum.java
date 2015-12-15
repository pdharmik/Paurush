package com.lexmark.constants;


public enum JobTypeEnum {
	 SUBMITNOW("SUBMITNOW"),
     SCHEDULED("SCHEDULED");
	private String type;
	JobTypeEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public static JobTypeEnum instance(String type) {
		for(JobTypeEnum e: JobTypeEnum.values()) {
			if(e.getType().equalsIgnoreCase(type)) {
				return e;
			}
		}
		return SCHEDULED;
	}
}
