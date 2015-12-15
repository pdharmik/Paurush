package com.lexmark.enums;

public enum RunLogStatusEnum {
	SELECTED("SELECTED"),   
	BUILDSUBMIT("BUILDSUBMIT"),
	BUILDOK("BUILDOK"),    
	BUILDERROR("BUILDERROR"), 
	LOADSUBMIT("LOADSUBMIT"), 
	LOADOK("LOADOK"),     
	LOADERROR("LOADERROR"),  
	WAITFORPUB("WAITFORPUB"), 
	DEFERRED("DEFERRED"),   
	PUBLISHOK("PUBLISHOK");  
	
	private String statusCode;
	RunLogStatusEnum(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getStatusCode() {
		return this.statusCode;
	}
}
