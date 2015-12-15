package com.lexmark.exceptionimpl.checked.process;

import com.lexmark.exceptionimpl.checked.LGSCheckedException;

public class LGSProcessException extends LGSCheckedException {

	/**
	 * Default Constructor for Custom MPSProcessException 
	 */
	public LGSProcessException() {
		super();
	}

	/**
	 * Default Constructor for Custom MPSProcessException
	 * @param message
	 * @param cause
	 */
	public LGSProcessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Default Constructor for Custom MPSProcessException
	 * @param message
	 */
	public LGSProcessException(String message) {
		super(message);
	}

	/**
	 * Default Constructor for Custom MPSProcessException
	 * @param cause
	 */
	public LGSProcessException(Throwable cause) {
		super(cause);
	}

	/**
	 * Serialization Version Variable.
	 */
	private static final long serialVersionUID = 6398130454342963098L;

}
