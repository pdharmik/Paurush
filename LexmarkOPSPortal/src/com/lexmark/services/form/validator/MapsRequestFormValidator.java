/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageAssetFormValidator
 * Package     		: com.lexmark.services.form.validator
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.services.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.framework.logging.LEXLogger;


import com.lexmark.services.form.MapsRequestForm;
import com.lexmark.services.portlet.cm.MapsRequestController;

/**
 * @author wipro
 * @version 2.1
 *
 */

public class MapsRequestFormValidator implements Validator {

	private static final LEXLogger LOGGER = LEXLogger
			.getLEXLogger(MapsRequestController.class);
	private static String CLASS_NAME = "MapsRequestFormValidator" ;

	public boolean supports(Class<?> type) {

		return MapsRequestForm.class.equals(type);
	}

	public void validate(Object obj, Errors errors) {
		LOGGER.enter(CLASS_NAME, "validate");
		MapsRequestForm mapsRequestForm = (MapsRequestForm) obj;

		if (mapsRequestForm.getServiceRequest() != null) {
			notesValidator(mapsRequestForm, errors);
		}
		LOGGER.exit(CLASS_NAME, "validate");
	}

	

	private void notesValidator(MapsRequestForm mapsRequestForm,
			Errors errors) {
		LOGGER.enter(CLASS_NAME, "notesValidator");
		String notes = mapsRequestForm.getNotesOrComments();
		if ("".equals(notes)) {
			errors.reject("requestInfo.label.validation.notesMandatory");
		}
		LOGGER.exit(CLASS_NAME, "notesValidator");
	}

}
