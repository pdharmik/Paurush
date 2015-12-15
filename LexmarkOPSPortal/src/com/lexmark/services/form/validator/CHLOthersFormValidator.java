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
import com.lexmark.services.form.ManageCHLOthersForm;
import com.lexmark.services.portlet.cm.ManageCHLOthersController;

/**
 * @author wipro
 * @version 2.1
 *
 */

public class CHLOthersFormValidator implements Validator {

	private static final LEXLogger LOGGER = LEXLogger
			.getLEXLogger(ManageCHLOthersController.class);
	private static String CLASS_NAME = "CHLOthersFormValidator" ;

	public boolean supports(Class<?> type) {

		return ManageCHLOthersForm.class.equals(type);
	}

	public void validate(Object obj, Errors errors) {
		LOGGER.enter(CLASS_NAME, "validate");
		ManageCHLOthersForm manageCHLOthersForm = (ManageCHLOthersForm) obj;

		if (manageCHLOthersForm.getServiceRequest() != null) {
			areaValidator(manageCHLOthersForm, errors);
			//subAreaValidator(manageCHLOthersForm, errors);
			notesValidator(manageCHLOthersForm, errors);
		}
		LOGGER.exit(CLASS_NAME, "validate");
	}

	private void areaValidator(ManageCHLOthersForm manageCHLOthersForm,
			Errors errors) {
		LOGGER.enter(CLASS_NAME, "areaValidator");
		String area = manageCHLOthersForm.getCmArea();
		if (area == null) {
			errors.reject("requestInfo.label.validation.areaMandatory");
		} 
		LOGGER.exit(CLASS_NAME, "areaValidator");
	}

	private void subAreaValidator(ManageCHLOthersForm manageCHLOthersForm,
			Errors errors) {
		LOGGER.enter(CLASS_NAME, "subAreaValidator");
		String subArea = manageCHLOthersForm.getCmSubArea();
		if (subArea == null) {
			errors.reject("requestInfo.label.validation.subAreaMandatory");
		} 
		LOGGER.exit(CLASS_NAME, "subAreaValidator");
	}

	private void notesValidator(ManageCHLOthersForm manageCHLOthersForm,
			Errors errors) {
		LOGGER.enter(CLASS_NAME, "notesValidator");
		String notes = manageCHLOthersForm.getNotesOrComments();
		if ("".equals(notes)) {
			errors.reject("requestInfo.label.validation.notesMandatory");
		}
		LOGGER.exit(CLASS_NAME, "notesValidator");
	}

}
