package com.lexmark.services.exception;

import java.io.Serializable;

public class OPSCompanyNotAssociatedException extends RuntimeException implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8424190479196504409L;
	/**
	 * 
	 */
	public static final String ERROR_MESSAGE = "Company not associated";
	  /**
	   * Constructs an exception with the given description.
	   * 
	   * @param s the exception's description.
	   */
	  public OPSCompanyNotAssociatedException() {
		  
	    super(ERROR_MESSAGE, null);
	  }

	  /**
	   * Constructs an exception with the given description and cause.
	   * 
	   * @param s the exception's description.
	   * @param cause the exception's cause.
	   */
	  public OPSCompanyNotAssociatedException(Throwable cause) {
	    super(ERROR_MESSAGE, cause);
	  }
}
