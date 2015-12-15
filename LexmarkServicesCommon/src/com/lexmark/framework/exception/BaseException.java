 /**
 * Copyright@ 2012. This document has been created & written by 
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *  
 * File         	: BaseException.java
 * Package      	: com.lexmark.fwk.exception
 * Creation Date    : 11-Sep-2012
 * 
 * Modification History:  
 * 
 * ---------------------------------------------------------------------
 * Author      				 Date        Version          Comments
 * --------------------------------------------------------------------- 
 * Sandeep Papuesi        11-Sep-2012     1.0             Initial Version
 * 
 */

package com.lexmark.framework.exception;


import java.util.Map;


/**
 * This is a base class for the all the exception classes created 
 * within this application. This BaseException class extended the 
 * Exception class to add the error code and the parameterized message 
 * to be passed along with the actual error message.
 * 
 * @author Wipro
 * 
 */
public class BaseException extends Throwable {
    
    /**
     * for serialVersionUID.
     */
    private static final long serialVersionUID = 6516270093353647788L;
    /**
     * for EMPTY_MSG_ID.
     */
    public static final String EMPTY_MSG_ID = "";    
    /**
     * for parameters.
     */
    private transient Object[] parameters = null;    
    /**
     * for additionalInfoMap.
     */
    private transient Map<Object,Object> additionalInfoMap = null;
    /**
     * for error Code.
     */
    private String errorCode;

    /**
     * @param errorCode String
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    /**
     * Empty constructor for BaseException and this calls the super class and
     * also this set the empty string for the error code.
     * 
     */
    public BaseException() {
        super();
        setErrorCode(EMPTY_MSG_ID);
    }
    /**
     * This creates the BaseException with an associated error code.
     * 
     * @param errorCode
     *            String
     */
    public BaseException(String errorCode) {
        super();
        setErrorCode(errorCode);
        
    }
    /**
     * this creates a BaseException associated with error code and the
     * parameters stored locally into an array object.
     * 
     * @param errorCode
     *            String
     * @param params
     *            Object[]
     */
    public BaseException(String errorCode, Object[] params) {
        super();
        setErrorCode(errorCode);

        // stores the parameters into array locally
        storeParameters(params);
    }
    /**
     * this creates a BaseException associated with the error code and error
     * message.
     * 
     * @param errorCode
     *            String
     * @param message
     *            String
     */
    public BaseException(String errorCode, String message) {
        super(message);
        setErrorCode(errorCode);
    }
    /**
     * this creates a BaseException along with the error code and error message
     * parameters.
     * 
     * @param errorCode
     *            String
     * @param message
     *            String
     * @param params
     *            Object[]
     */
    public BaseException(String errorCode, String message, Object[] params) {
        super(message);
        setErrorCode(errorCode);

        // stores the parameters into array locally
        storeParameters(params);
    }
    /**
     * This creates BaseException with error code, error message with 
     * additional parameters passed.
     * 
     * @param errorCode
     *            String
     * @param message
     *            String
     * @param additionalInfo
     *            Map
     */
    public BaseException(
            String errorCode, String message, Map<Object,Object> additionalInfo) {
        super(message);
        setErrorCode(errorCode);

        // stores the parameters into array locally
        storeParameters(additionalInfo);
    }    
    /**
     * this creates BaseException associated with error code and the throwable
     * cause.
     * @param errorCode String
     * @param cause Throwable
     */
    public BaseException(String errorCode, Throwable cause) {
        super(errorCode,cause);
        setErrorCode(errorCode);       
    }    
    /**
     * this creates BaseException associated with error code and the throwable
     * cause.
     * 
     * @param errorCode
     *            String
     * @param message
     *            String
     * @param cause
     *            Throwable
     */
    public BaseException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        setErrorCode(errorCode);
    }
    /**
     * this creates BaseException associated with error code and the throwable
     * cause. Parameters stored locally to append with the message
     * 
     * @param errorCode
     *            String
     * @param message
     *            String
     * @param cause
     *            String
     * @param params
     *            Object[]
     */
    public BaseException(String errorCode, String message, Throwable cause,
            Object[] params) {
        super(message, cause);
        setErrorCode(errorCode);

        // stores locally into array
        storeParameters(params);
    }
    /**
     * this creates BaseException associated with error code and the throwable
     * cause. Parameters stored locally to append with the message
     * 
     * @param errorCode
     *            String
     * @param cause
     *            String
     * @param params
     *            Object[]
     */
    public BaseException(String errorCode, Throwable cause, Object[] params) {
        super(cause);
        setErrorCode(errorCode);

        // stores locally into array
        storeParameters(params);
    }
    /**
     * In case of unknown exceptions, this method can be called, if the
     * exception is the BaseException and throws the BaseException or it
     * converts the exception into BaseException and throws back to the caller.
     * 
     * @param currentEx
     *            Throwable
     * @throws BaseException currentEx
     */
    public static void mapAndThrow(Throwable currentEx) throws BaseException {

        if (currentEx != null) {

            if (currentEx instanceof BaseException) {
                throw (BaseException) currentEx;
            }
            throw new BaseException(EMPTY_MSG_ID, currentEx, null);
        }
    }
    /**
     * returns the error code
     * 
     * @return String
     */
    public final String getErrorCode() {
        return this.errorCode;
    }
    /**
     * returns the parameters stored locally
     * 
     * @return Object[]
     */
    public final Object[] getParameters() {
        return this.parameters;
    }
    /**
     * This method stores the application passed parameters into an array
     * locally
     * 
     * @param params
     *            Object[]
     */
    private void storeParameters(Object[] params) {

        if (params != null) {
            parameters = new Object[params.length];
            System.arraycopy(params, 0, parameters, 0, params.length);
        }
    }
    /**
     * This method is used to store the parameters passed as part of the
     * methods/constructors.
     * 
     * @param additionalInfo
     *            Map
     */
    private void storeParameters(Map<Object,Object> additionalInfo) {
        additionalInfoMap.clear();
        additionalInfoMap.putAll(additionalInfo);
    }
}
