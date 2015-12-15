package com.lexmark.form.source;

public class ProcessedPartGridError {
	private String errorCode;
	private String Message;
	public ProcessedPartGridError(String errorCode,String Message)
	{
		this.errorCode=errorCode;
		this.Message=Message;
	}
	public String geterrorCode() {
		return errorCode;
	}

	public void seterrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String geterrorMessage() {
		return Message;
	}

	public void seterrorMessage(String Message) {
		this.Message = Message;
	}

}
