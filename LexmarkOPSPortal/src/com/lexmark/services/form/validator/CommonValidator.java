package com.lexmark.services.form.validator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.services.form.ManageContactForm;
import com.lexmark.services.util.ChangeMgmtConstant;

/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CommonValidator
 * Package     		: com.lexmark.services.form.validator
 */
@SuppressWarnings(value = { "unchecked" })
public class CommonValidator implements Validator {

	private  List<String> errorList;

	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(CommonValidator.class);

	public boolean supports(Class<?> type) {

		if ("manageContactform".equalsIgnoreCase(type.getSimpleName())){
			return true;
		}
		return ManageContactForm.class.equals(type);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		LOGGER.enter(this.getClass().getSimpleName(), "validate");
		
		/*
		 * Validate the Manage Contact Form
		 */
		if (obj instanceof ManageContactForm) {
			try {
				ManageContactForm manageContactForm = (ManageContactForm) obj;

				if (manageContactForm.getAccountContact() != null) {
						/*firstNameValidator(manageContactForm
								.getAccountContact().getFirstName(), errors);
						middleNameValidator(manageContactForm
								.getAccountContact().getMiddleName(), errors);
						lastNameValidator(manageContactForm
								.getAccountContact().getLastName(), errors);*/
						workPhoneValidator(manageContactForm
								.getAccountContact().getWorkPhone(), errors);
						alternatePhoneValidator(manageContactForm
								.getAccountContact().getAlternatePhone(),errors);
						emailAddressValidator(manageContactForm
								.getAccountContact().getEmailAddress(), errors);
				}
			} catch (Exception ex) {
				logStackTrace(ex);
				throw new LGSRuntimeException("Error while validating fields");
			}
			
		}
		/*
		 * Validate the contact popup
		 */
		else if (obj instanceof List<?>) {
			
			LOGGER.debug("in popup validation");
			List<Object> popUpList = (List<Object>) obj;
			Map<String,String[]> popUpValuesMap = (Map<String,String[]>)popUpList.get(0);
			List<String> popUpErrorList = (List<String>)popUpList.get(1);
			errorList=popUpErrorList;
			if(popUpValuesMap.containsKey("firstName")){
				LOGGER.debug("checking for contact"+ popUpValuesMap.get("firstName")[0]);
				firstNameValidator(popUpValuesMap.get("firstName")[0],errors);
				lastNameValidator(popUpValuesMap.get("lastName")[0],errors);
				workPhoneValidator(popUpValuesMap.get("workPhone")[0],errors);
				alternatePhoneValidator(popUpValuesMap.get("alternatePhone")[0],errors);
				emailAddressValidator(popUpValuesMap.get("emailId")[0],errors);
				LOGGER.debug("[OUT] contact popup validate");
			}
		}
		LOGGER.exit(this.getClass().getSimpleName(), "validate");
	}

	/******************** Annotations used in the Asset bean **************/
	/**
	 * This method is used to validate the First Name
	 * 
	 * @param firstName
	 * @param errors
	 */
	private void firstNameValidator(final String firstName, Errors errors) {
		LOGGER.enter(this.getClass().getSimpleName(), "firstNameValidator");
		LOGGER.debug("firstNameValidator value="+firstName);
			/*
			 * This block only gets executed once the firstname field is not
			 * empty.
			 */

			if (firstName!=null && firstName!="") {
			  if((firstName.matches(ChangeMgmtConstant.NAMEPATTERN))) {
				if (errorList==null) {
					errors.reject("validation.contact.firstname.format.errorMsg");
				} else {
					errorList.add("validation.contact.firstname.format.errorMsg");
		
				}
			  }		
			}
		LOGGER.exit(this.getClass().getSimpleName(), "firstNameValidator");
		}


	/**
	 * This method is used to validate the Middle Name
	 * 
	 * @param middleName
	 * @param errors
	 */
	private void middleNameValidator(String middleName, Errors errors) {
		LOGGER.enter(this.getClass().getSimpleName(), "middleNameValidator");
		LOGGER.debug("middleNameValidator value="+middleName);
		if (middleName != null && middleName != "") {
			/*
			 * This block only gets executed once the middlename field is not
			 * empty.
			 */

			if ((middleName.matches(ChangeMgmtConstant.NAMEPATTERN))) {
				errors.reject("validation.contact.middlename.format.errorMsg");
			}
		}
		LOGGER.exit(this.getClass().getSimpleName(), "middleNameValidator");
	}

	/**
	 * This method is used to validate the Last Name
	 * 
	 * @param lastName
	 * @param errors
	 */
	private void lastNameValidator(String lastName, Errors errors) {
		LOGGER.enter(this.getClass().getSimpleName(), "lastNameValidator");
		LOGGER.debug("lastNameValidator value="+lastName);
			
			/*
			 * This block only gets executed once the lastname field is not
			 * empty.
			 */

			if (lastName!=null && lastName!="") {
			  if((lastName.matches(ChangeMgmtConstant.NAMEPATTERN))) {
				if (errorList==null) {
					errors.reject("validation.contact.lastname.format.errorMsg");
				} else {
					errorList.add("validation.contact.lastname.format.errorMsg");
				}
			  }
			}
		LOGGER.exit(this.getClass().getSimpleName(), "lastNameValidator");
		
	}

	/**
	 * This method is used to validate the Work Phone
	 * 
	 * @param workPhone
	 * @param errors
	 * @param pageName
	 */
	private void workPhoneValidator(String workPhone, Errors errors) {
		LOGGER.enter(this.getClass().getSimpleName(), "workPhoneValidator");
		LOGGER.debug("workPhoneValidator value="+workPhone);
			/*
			 * This block only gets executed once the workphone field is not
			 * empty.
			 */

			if (workPhone!=null && workPhone!="") {
			  if(!(workPhone.matches(ChangeMgmtConstant.PHONEPATTERN))) {
				if (errorList==null) {
					errors.reject("validation.contact.workphone.format.errorMsg");
				} else {
					errorList.add("validation.contact.workphone.format.errorMsg");
				}
			  }
			}
			LOGGER.exit(this.getClass().getSimpleName(), "workPhoneValidator");
		}
		

	/**
	 * This method is used to validate the Alternate Phone
	 * 
	 * @param alternatePhone
	 * @param errors
	 * @param pageName
	 */
	private void alternatePhoneValidator(String alternatePhone, Errors errors) {
		
		LOGGER.enter(this.getClass().getSimpleName(),"alternatePhoneValidator");
		LOGGER.debug("alternatePhoneValidator value="+alternatePhone);
			/*
			 * This block only gets executed once the alternate phone field is
			 * not empty.
			 */

			if (alternatePhone!=null && alternatePhone!="") {
			  if(!(alternatePhone.matches(ChangeMgmtConstant.PHONEPATTERN))) {
				if (errorList==null) {
					errors.reject("validation.contact.alternatephone.format.errorMsg");
				} else {
					errorList.add("validation.contact.alternatephone.format.errorMsg");
				}
			  }
			}
			LOGGER.exit(this.getClass().getSimpleName(),"alternatePhoneValidator");
		}
	

	/**
	 * This method is used to validate the Email Address
	 * 
	 * @param emailAddress
	 * @param errors
	 * @param pageName
	 */
	private void emailAddressValidator(String emailAddress, Errors errors) {
		LOGGER.enter(this.getClass().getSimpleName(), "emailAddressValidator");
		LOGGER.debug("emailAddressValidator value="+emailAddress); 
		
			/*
			 * This block only gets executed once the emailaddress field is not
			 * empty.
			 */

			if (emailAddress!=null && emailAddress!="") {
			  if(!(emailAddress.matches(ChangeMgmtConstant.EMAILPATTERN))) {
				if (errorList==null) {
					errors.reject("validation.contact.emailaddress.format.errorMsg");
				} else {
					errorList.add("validation.contact.emailaddress.format.errorMsg");
				}			  
			  }
			}
			LOGGER.exit(this.getClass().getSimpleName(),"emailAddressValidator");
		}
		
	/**
	 * This method is used to print the log
	 * @param throwable
	 */
	private void logStackTrace(Throwable throwable)
	{
   		LOGGER.debug("Inside logstack trace");
		StringWriter writer = new StringWriter();
		throwable.printStackTrace(new PrintWriter(writer));
		LOGGER.debug(writer.getBuffer().toString());
	}	

}
	
