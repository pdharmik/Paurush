package com.lexmark.exception;

import java.io.Serializable;

public class AccountNotAssociatedException extends RuntimeException implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8424190479196504409L;
	/**
	 * 
	 */
	public static final String ERROR_MESSAGE = "Account not associated";
	  /**
	   * Constructs an exception with the given description.
	   * 
	   * @param s the exception's description.
	   */
	  public AccountNotAssociatedException() {
		  
	    super(ERROR_MESSAGE, null);
	  }

	  /**
	   * Constructs an exception with the given description and cause.
	   * 
	   * @param s the exception's description.
	   * @param cause the exception's cause.
	   */
	  public AccountNotAssociatedException(Throwable cause) {
	    super(ERROR_MESSAGE, cause);
	  }
}
