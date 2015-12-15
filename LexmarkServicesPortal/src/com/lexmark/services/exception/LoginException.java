package com.lexmark.services.exception;

import java.io.Serializable;

public class LoginException extends RuntimeException implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7950570134872080030L;
	public static final String ERROR_MESSAGE = "Not Logged in";
	  /**
	   * Constructs an exception with the given description.
	   * 
	   * @param s the exception's description.
	   */
	  public LoginException() {
		  
	    super(ERROR_MESSAGE, null);
	  }

	  /**
	   * Constructs an exception with the given description and cause.
	   * 
	   * @param s the exception's description.
	   * @param cause the exception's cause.
	   */
	  public LoginException(Throwable cause) {
	    super(ERROR_MESSAGE, cause);
	  }
}
