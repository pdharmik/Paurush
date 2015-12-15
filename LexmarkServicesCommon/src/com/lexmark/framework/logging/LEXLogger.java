/**
 * 
 */
package com.lexmark.framework.logging;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * @author spapudesi
 *
 */
public class LEXLogger extends LogManager{
	private org.apache.logging.log4j.Logger mLoggerInstance;  

	/**
     * This method will return an instance of the Lexmark Logger which has an appropriate
     * log4j logger wrapped inside it
     * @param 		aLoggerIdentifier
     * 				Identifier of type Object(Class/String are valid types) which will be used to retrieve logger instance
     * @return		Instance of LEXLogger
     */
	public static LEXLogger getLEXLogger(Object aLoggerIdentifier) {
		return new LEXLogger(aLoggerIdentifier);
	}
	
	/**
	 * Private constructor which takes identifier of type Class to retrieve Logger instance
	 * @param 	aLoggerIdentifier
	 */
	@SuppressWarnings("unchecked")
	private LEXLogger(Object aLoggerIdentifier) {
		if(aLoggerIdentifier instanceof Class) {
			mLoggerInstance = LogManager.getLogger((Class)aLoggerIdentifier);
		}
		else if(aLoggerIdentifier instanceof String) {
			mLoggerInstance = LogManager.getLogger((String)aLoggerIdentifier);			
		}
		else {
			mLoggerInstance = LogManager.getLogger(LEXLogger.class);
		}
	}
	
	
	
	
	/**
	 * This method will be used for logging messages at LEVEL=INFO
	 * @param 	aMessage
	 */	
	public void info(Object aMessage) {
		if (isInfoEnabled()){
			mLoggerInstance.info(aMessage);
		}
	}

	/**
	 * This method will be used for logging messages at LEVEL=INFO
	 * @param 	aMessage
	 */	
	public void info(Object aMessage, Throwable aThrowable) {
		mLoggerInstance.info(aMessage, aThrowable);
	}
	
	
	
	/**
	 * This method will be used for logging messages at LEVEL=DEBUG
	 * @param 	aMessage
	 */
	public void debug(Object aMessage) {
		if (isDebugEnabled()){
			mLoggerInstance.debug(aMessage);
		}
	}

	/**
	 * This method will be used for logging time
	 * @param 	aMessage
	 */
	public void logTime(Object aMessage) {
		if (isDebugEnabled()){
			mLoggerInstance.debug(aMessage);
		}else if (isInfoEnabled()){
			mLoggerInstance.info(aMessage);
		} else{
			mLoggerInstance.error(aMessage);
		}
	}
	
	/**
	 * This method will be used for logging Contract
	 * @param 	aMessage
	 */
	public void logContract(Object aMessage) {
		if (isDebugEnabled()){
			mLoggerInstance.debug(aMessage);
		}else if (isInfoEnabled()){
			mLoggerInstance.info(aMessage);
		} else{
			mLoggerInstance.error(aMessage);
		}
	}
	/**
	 * This method will be used for logging messages at LEVEL=DEBUG
	 * @param 	aMessage
	 */	
	public void debug(Object aMessage, Throwable aThrowable) {
		mLoggerInstance.debug(aMessage, aThrowable);
	}
	
	/**
	 * This method will be used for logging messages at LEVEL=WARN
	 * @param 	aMessage
	 */	
	public void warn(Object aMessage) {
		mLoggerInstance.warn(aMessage);
	}

	/**
	 * This method will be used for logging messages at LEVEL=WARN
	 * @param 	aMessage
	 */	
	public void warn(Object aMessage, Throwable aThrowable) {
		mLoggerInstance.warn(aMessage, aThrowable);
	}
	
	/**
	 * This method will be used for logging messages at LEVEL=ERROR
	 * @param 	aMessage
	 */	
	public void error(Object aMessage) {
		mLoggerInstance.error(aMessage);
	}

	/**
	 * This method will be used for logging messages at LEVEL=ERROR
	 * @param 	aMessage
	 */	
	public void error(Object aMessage, Throwable aThrowable) {
		mLoggerInstance.error(aMessage, aThrowable);
	}
	
	/**
	 * This method will be used for logging messages at LEVEL=FATAL
	 * @param 	aMessage
	 */	
	public void fatal(Object aMessage) {
		mLoggerInstance.fatal(aMessage);
	}

	/**
	 * This method will be used for logging messages at LEVEL=FATAL
	 * @param 	aMessage
	 */	
	public void fatal(Object aMessage, Throwable aThrowable) {
		mLoggerInstance.fatal(aMessage, aThrowable);
	}
	
	

	/**
	 * This method will return boolean specifying whether INFO level is
	 * enabled with the associated Logger Instance
	 * @return		boolean specifying whether Info is enabled
	 */
	public boolean isInfoEnabled() {
		return mLoggerInstance.isInfoEnabled();
	}
	
	/**
	 * This method will return boolean specifying whether DEBUG level is
	 * enabled with the associated Logger Instance
	 * @return		boolean specifying whether Debug is enabled
	 */
	public boolean isDebugEnabled() {
		return mLoggerInstance.isDebugEnabled();
	}	
	
	
	/**
	 * This logs the method entry.
	 * 
	 * @param className String - Name of the class
	 * @param methodName String - Name of the method
	 */
	public void enter(String className, String methodName) 
	{
		if (isDebugEnabled()) { 
			mLoggerInstance.debug("Entering method : " + methodName + " of class : " + className); 
		}
	}
	
	/**
	 * This logs the method exit.
	 * 
	 * @param className String - Name of the class
	 * @param methodName String - Name of the method
	 */
	public void exit(String className, String methodName) 
	{
		if (isDebugEnabled()) { mLoggerInstance.debug("Exiting method : " + methodName + " of class : " + className); };
	}
}
