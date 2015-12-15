/**
 * @author Sourav
 * 
 */
package com.lexmark.exceptionimpl.runtime;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.ErrorCodeEnum;
import com.lexmark.exception.LGSException;


public class LGSRuntimeException extends RuntimeException implements
		LGSException {	
	private static Logger LOGGER = LogManager.getLogger(LGSRuntimeException.class);
	/**
	 * Serialization Version Variable.
	 */
	private static final long serialVersionUID = 3791076501860009388L;

	/**
	 * Default Constructor for Custom MPSRuntimeException Exception
	 */
	public LGSRuntimeException() {
		super();

	}

	/**
	 * Constructor for Custom MPSRuntimeException Exception
	 * 
	 * @param message
	 * @param cause
	 */
	public LGSRuntimeException(String message, Throwable cause) {
		super(message, cause);
		errorCodeCheck(cause);
	}

	/**
	 * Constructor for Custom MPSRuntimeException Exception
	 * 
	 * @param message
	 */
	public LGSRuntimeException(String message) {
		super(message);

	}

	/**
	 * Constructor for Custom MPSRuntimeException Exception
	 * 
	 * @param cause
	 */
	public LGSRuntimeException(Throwable cause) {
		super(cause);
		errorCodeCheck(cause);

	}

	/**
	 * This is a custom method
	 */
	@Override
	public void errorCodeCheck(Throwable th) {

		LOGGER.info("This is a custom method---->");
		
		// Here we can check the error code with the values inside enum
		//Extract the error code from the throwable
		ErrorCodeEnum.GENERAL_ERROR_SIEBEL.getErrorCode(); //would return the error code
	}

	/**
	 * Write the mesage as well as the stacktrace to the log file
	 */
	@Override
	public void logException(String message, Throwable th) {

		LOGGER.info("Inside logException method----->");
		th.printStackTrace();
		// logger.info(message);
	}

	public void logException(String message) {
		// logger.info(message);

	}

}
