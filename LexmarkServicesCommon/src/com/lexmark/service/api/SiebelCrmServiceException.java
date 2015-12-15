package com.lexmark.service.api;

public class SiebelCrmServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    

    public SiebelCrmServiceException() {
		super();
	}

	public SiebelCrmServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SiebelCrmServiceException(String arg0) {
		super(arg0);
	}

	public SiebelCrmServiceException(Throwable arg0) {
		super(arg0);
	}

}
