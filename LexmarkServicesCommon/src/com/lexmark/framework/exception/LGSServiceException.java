/**
 * Copyright@ 2012. This document has been created & written by 
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *  
 * File         	: LGSServiceException.java
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

import com.lexmark.framework.exception.BaseException;


/**
 * Base exception class for all the PAS Service calls.
 * 
 * @author Wipro
 *
 */
public class LGSServiceException extends BaseException {

    /**
     * for serialVersionUID.
     */
    private static final long serialVersionUID = -4278849260430703835L;

    /**
     * @param errorCode String
     * @param cause Throwable
     */
    public LGSServiceException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
