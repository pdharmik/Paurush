package com.lexmark.exception;

public interface LGSException {

	public void errorCodeCheck(Throwable ex);
	
	public void logException(String message, Throwable th);
	
}
