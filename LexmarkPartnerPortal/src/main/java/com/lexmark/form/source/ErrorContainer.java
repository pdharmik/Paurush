package com.lexmark.form.source;

public class ErrorContainer {
	private String partNumber;
	private String errorMessage;
	private int lineNumber;
	public ErrorContainer(String x,String y,int z) {
		partNumber=x;
		errorMessage=y;
		lineNumber=z;
	}
	public String getpartNumber() {
		return partNumber;
	}

	public void setpartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String geterrorMessage() {
		return errorMessage;
	}

	public void seterrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getlineNumber() {
		return lineNumber;
	}

	public void setlineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
}
