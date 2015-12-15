package com.lexmark.reportScheduler.exception;

public class ReportGenerateException extends RuntimeException {
	private static final long serialVersionUID = 5974783768426596174L;
	
	public ReportGenerateException(Throwable ex) {
		super(ex);
	}
	
	public ReportGenerateException(String message) {
		super(message);
	}
}
