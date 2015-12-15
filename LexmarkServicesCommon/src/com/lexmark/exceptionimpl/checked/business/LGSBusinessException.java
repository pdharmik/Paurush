package com.lexmark.exceptionimpl.checked.business;

import com.lexmark.exceptionimpl.checked.LGSCheckedException;

public class LGSBusinessException extends LGSCheckedException{

	/**
	 * Serialization Version Variable.
	 */
	private static final long serialVersionUID = 7443968275681434166L;

	/**
	 * 
	 */
	public LGSBusinessException() {
		super();
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LGSBusinessException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 */
	public LGSBusinessException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public LGSBusinessException(Throwable cause) {
		super(cause);
		
	}

}
