package com.lexmark.constants;

public enum ErrorCodeEnum {
	SIEBEL_MAJORMINOR("SIBEL_ERROR_CODE_FOR MAJOR MINOR"), GENERAL_ERROR_WEBMETHODS(
			"WEBMETHODS_ERROR_CODE_FOR CONNECTION ERRORS"), GENERAL_ERROR_SIEBEL("GENERAL SIEBEL ERRORS");
	
	// The values would be appropriately added/changed later on
	//Caused by: com.amind.data.service.SiebelEAIException: [AMS-DS-1220] Error: Invalid Siebel EAI Data Service query request.
	private String errorCode;
	
	ErrorCodeEnum(String errorCode)
	{
		this.errorCode=errorCode;
	}
	
	public String getErrorCode()
	{
		return errorCode; 
	}
}

