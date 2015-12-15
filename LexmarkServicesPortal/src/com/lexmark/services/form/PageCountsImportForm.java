/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: PageCountsImportForm.java
 * Package     		: com.lexmark.services.form
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.form;

import java.io.Serializable;

/**
 * This class extends BaseForm and implements Serializable
 * @author wipro
 * @version 2.1
 */
public class PageCountsImportForm extends BaseForm implements Serializable{
	private static final long serialVersionUID = 7648799643208961809L;
	private String currentDateStr;
	/**
	 * 
	 * @param currentDateStr 
	 */
	public void setCurrentDateStr(String currentDateStr) {
		
		this.currentDateStr = currentDateStr;
	}
	/**
	 * 
	 * @return currentDateStr 
	 */
	public String getCurrentDateStr() {
		
		return currentDateStr;
	}
}
