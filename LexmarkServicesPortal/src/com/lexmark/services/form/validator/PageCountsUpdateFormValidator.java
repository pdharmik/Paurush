/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: PageCountsUpdateFormValidator.java
 * Package     		: com.lexmark.services.form.validator
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


package com.lexmark.services.form.validator;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.services.form.PageCountsUpdateForm;

/**
 * @author wipro
 * @version 2.1
 * this will validate the pagecount data on page counts update pop-up
 */
public class PageCountsUpdateFormValidator implements Validator {
	private static Logger logger = LogManager.getLogger(PageCountsUpdateFormValidator.class);

	/**
	 * 
	 * @param type 
	 * @return boolean 
	 */
	public boolean supports(Class<?> type) {
		return PageCountsUpdateForm.class.equals(type);
	}
	/**
	 * 
	 * @param object  
	 * @param error  
	 */
	public void validate(Object object, Errors error) {
		logger.debug("Inside PageCountsUpdateFormValidator.");
		PageCountsUpdateForm pageCountsUpdateForm = (PageCountsUpdateForm)object;
		checkForPageCounts(pageCountsUpdateForm, error);
		//		can have a check for Date as well
	}
	
	/**
	 * This method is used to check the page counts count 
	 * @param form 
	 * @param error  
	 */
	private void checkForPageCounts(PageCountsUpdateForm form, Errors error) {
		
		String count = form.getNewPageCount();
		String colorCount = form.getNewColorPageCount();
		if(isNullValue(count)) {
			error.reject("page count should be more than 0");
		} else {
			if(isNonNumberedValue(count.trim())) {
				error.reject("incorrect page count");
			}
			else {
				if(isLessThanZero(count.trim())) {
					error.reject("page count should be more than 0");
				}
			}
		
		}
		boolean colorPageCountFlag = form.getColorCapableFlag();
		if(colorPageCountFlag) {
			if(isNullValue(colorCount)) {
				error.reject("color page count should be more than 0");
			} else {
				if(isNonNumberedValue(colorCount.trim())) {
					error.reject("incorrect color page count");
				} else {
					if(isLessThanZero(colorCount.trim())) {
						error.reject("color page count should be more than 0");
					}
				}
			}
		}
	}
	
	/**
	 * This method is used to return null value in boolean
	 * @param value  
	 * @return Boolean 
	 */
	private boolean isNullValue(String value) {
		boolean isNull = false;
		if(value == null) {
			isNull = true;
		}
		return isNull;
	}
	
	/**
	 * This method is used to return non number in boolean 
	 * @param value  
	 * @return Boolean  
	 */
	private boolean isNonNumberedValue(String value) {
		boolean isNonNumbered = false;
		try {
			Long.parseLong(value);
		} catch (Exception e) {
			e.getMessage();
			isNonNumbered = true;
		}
		return isNonNumbered;
	}
	
	/**
	 * This method is used to return less than zero value in boolean
	 * @param value 
	 * @return Boolean 
	 */
	private boolean isLessThanZero(String value) {
		boolean isLess = false;
		Long count = Long.parseLong(value);
		if(count <= 0 ) {
			isLess = true;
		}
		return isLess;
	}
}
