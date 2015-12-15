/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ContactFormValidator.java
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
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lexmark.services.form.ManageContactForm;

/**
 * This class validates the contact section
 * @author wipro
 *
 */
@SessionAttributes(value={"errorMap"})
public class ContactFormValidator implements Validator{
	
	@Resource
	private Map<String, String> popupErrorMap ; //Added for popup Error Map
	private static final String namePattern="[a-zA-Z]{1,30}";
	private static final String phonePattern="^[+]{0,1}([0-9]){1,3}[ ]?([-]?(([0-9])|[ ]){1,12})+$";
	private static final String emailPattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*"+"((\\.[A-Za-z]{2,}){1}$)";
	private static final String custRefIdPattern="[a-zA-Z0-9]{1,30}";
	private static final String costCenterPattern="[a-zA-Z0-9]{1,30}";
	String pageName="";
	boolean ispopup = false;
	
	private static Logger logger = LogManager.getLogger(ContactFormValidator.class);
	
	public boolean supports(Class<?> type) {
		return ManageContactForm.class.equals(type);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		if(obj instanceof ManageContactForm)
		{
		  try {						
			ManageContactForm manageContactForm=(ManageContactForm)obj;
			pageName = manageContactForm.getPageName();
			if(manageContactForm.getServiceRequest()!=null){
				custRefIdValidator(manageContactForm, errors);
				costCenterValidator(manageContactForm, errors);
			}
			
			if(manageContactForm.getAccountContact()!=null){
			
			if (pageName != null && pageName.equalsIgnoreCase("addContact")) {
				firstNameValidator(manageContactForm.getAccountContact().getFirstName(), errors);
				middleNameValidator(manageContactForm.getAccountContact().getMiddleName(), errors);
				lastNameValidator(manageContactForm.getAccountContact().getLastName(), errors);
				addrLine1Validator(manageContactForm.getAccountContact().getAddress().getAddressLine1(), errors);
				stateValidator(manageContactForm.getAccountContact().getAddress().getState(), errors);
				zipValidator(manageContactForm.getAccountContact().getAddress().getPostalCode(), errors);
			}
			if (pageName != null && pageName.equalsIgnoreCase("addContact") || pageName.equalsIgnoreCase("changeContact")) {
			workPhoneValidator(manageContactForm.getAccountContact().getWorkPhone(), errors, pageName);
			alternatePhoneValidator(manageContactForm.getAccountContact().getAlternatePhone(), errors, pageName);
			emailAddressValidator(manageContactForm.getAccountContact().getEmailAddress(), errors, pageName);
			}	
			}
		  }
		  catch (NullPointerException ne) {
			  logger.debug("Null values in contact form");
		  }
		}
		else{
		  try{
			pageName = "addContact";
			ispopup = true;
			Map<String,String> contactNewPopup = (Map<String,String>)obj;
			logger.debug("firstname "+contactNewPopup.get("firstName")+"lastname "+contactNewPopup.get("lastName"));
			firstNameValidator(contactNewPopup.get("firstName"), errors);
			lastNameValidator(contactNewPopup.get("lastName"), errors);
			workPhoneValidator(contactNewPopup.get("workPhone"), errors, pageName);
			alternatePhoneValidator(contactNewPopup.get("alternatePhone"), errors, pageName);
			emailAddressValidator(contactNewPopup.get("emailId"), errors, pageName);
		  
		  }catch (NullPointerException ne) {
			  logger.debug("Null values in contact form");
		  }
		}
	}

	/********************Annotations used in the Asset bean**************/
	/**
	 * This method is used to validate the First Name
	 * @param manageContactForm
	 * @param errors
	 */
	private void firstNameValidator(String firstName, Errors errors)
	{	
		if(firstName!=null && firstName!="")
		{
			if(!(firstName.matches(namePattern)))
			{	
				if(!ispopup) {
				errors.reject("contact.firstname.format.error"); //Message from properties file
				}
				else {
				popupErrorMap.put("firstName", "contact.firstname.format.error");
				logger.debug("Firstname pattern not match"+popupErrorMap.get("firstName"));
				}
			}
		}
		else {
			
			if(!ispopup) {
				errors.reject("contact.firstname.empty"); //Message from properties file
			}
			else {
				popupErrorMap.put("firstName", "contact.firstname.empty");
				logger.debug("Firstname empty error is"+popupErrorMap.get("firstName"));
			}
		}
	}
	
	/**
	 * This method is used to validate the Middle Name
	 * @param manageContactForm
	 * @param errors
	 */
	private void middleNameValidator(String middleName, Errors errors)
	{	
		if(middleName!=null && middleName!="")
		{
			if(!(middleName.matches(namePattern)))
			{	
				errors.reject("contact.middlename.format.error"); //Message from properties file
			}
		}
	}
	
	/**
	 * This method is used to validate the Last Name
	 * @param manageContactForm
	 * @param errors
	 */
	private void lastNameValidator(String lastName, Errors errors)
	{	
		if(lastName !=null && lastName !="")
		{
			if(!(lastName.matches(namePattern)))
			{	
				if(!ispopup) {
					errors.reject("contact.lastname.format.error"); //Message from properties file
				}
				else {
					popupErrorMap.put("lastName", "contact.lastname.format.error");
				}
			}
		}
		else {
			if(!ispopup) {
				errors.reject("contact.lastname.empty"); //Message from properties file
			}
			else {
				popupErrorMap.put("lastName", "contact.lastname.empty");
			}
		}
	}
	/**
	 * This method is used to validate the Work Phone
	 * @param manageContactForm
	 * @param errors
	 */
	private void workPhoneValidator(String workPhone, Errors errors, String pageName)
	{	
		if(workPhone!=null && workPhone!="")
		{
			if(!(workPhone.matches(phonePattern)))
			{	
				if(!ispopup) {
					errors.reject("contact.workphone.format.error"); //Message from properties file
				}
				else {
					popupErrorMap.put("workPhone", "contact.workphone.format.error");
				}
			}
		}
		else if(!pageName.equalsIgnoreCase("changeContact")) {
			if(!ispopup) {
				errors.reject("contact.workphone.empty"); //Message from properties file
			}
			else {
				popupErrorMap.put("workPhone", "contact.workphone.empty");
			}
		}
	}
	
	/**
	 * This method is used to validate the Alternate Phone
	 * @param manageContactForm
	 * @param errors
	 */
	private void alternatePhoneValidator(String alternatePhone, Errors errors, String pageName)
	{	
		if(alternatePhone !=null && alternatePhone !="")
		{
		if(!(alternatePhone.matches(phonePattern)))
			{	
				if(!ispopup) {
					errors.reject("contact.alternatephone.format.error"); //Message from properties file
				}
				else {
					popupErrorMap.put("alternatePhone", "contact.alternatephone.format.error");
				}
			}
		}
	}
	/**
	 * This method is used to validate the Email Address
	 * @param manageContactForm
	 * @param errors
	 */
	private void emailAddressValidator(String emailAddress, Errors errors, String pageName)
	{	
		if(emailAddress!=null && emailAddress!="")
		{
			if(!(emailAddress.matches(emailPattern)))
			{	
				if(!ispopup) {
					errors.reject("contact.emailaddress.format.error"); //Message from properties file
				}
				else {
					popupErrorMap.put("email", "contact.emailaddress.format.error");
				}
			}
		}
		else if(!pageName.equalsIgnoreCase("changeContact")) {
			if(!ispopup) {
				errors.reject("contact.emailaddress.empty"); //Message from properties file
			}
			else {
				popupErrorMap.put("email", "contact.emailaddress.empty");
			}
		}
	}
	
	/**
	 * This method is used to validate the Address Line1
	 * @param manageContactForm
	 * @param errors
	 */
	private void addrLine1Validator(String addrLine1, Errors errors)
	{	
		if(addrLine1==null || addrLine1=="")
		{
			errors.reject("contact.addrline1.empty"); //Message from properties file
		}
	}

	/**
	 * This method is used to validate the State / Provience
	 * @param manageContactForm
	 * @param errors
	 */
	private void stateValidator(String state, Errors errors)
	{	
		//String state = manageContactForm.getAccountContact().getAddress().getState();
		if(state==null || state=="")
		{
			errors.reject("contact.state.empty");
		}
	}
	
	/**
	 * This method is used to validate the Zip / Postal Code
	 * @param manageContactForm
	 * @param errors
	 */
	private void zipValidator(String zip, Errors errors)
	{	
		if(zip==null || zip=="")
		{
			errors.reject("contact.zip.empty"); //Message from properties file
		}
	}

	/**
	 * This method is used to validate the Customer Reference Id
	 * @param manageContactForm
	 * @param errors
	 */
	private void custRefIdValidator(ManageContactForm manageContactForm, Errors errors)
	{	
		String custRefId = manageContactForm.getServiceRequest().getCustomerReferenceId();
		if(custRefId!=null && custRefId!="")
		{
			if(!(custRefId.matches(custRefIdPattern)))
			{	
				errors.reject("contact.custrefid.format.error"); //Message from properties file
			}
		}
	}
	/**
	 * This method is used to validate the Cost Center
	 * @param manageContactForm
	 * @param errors
	 */
	private void costCenterValidator(ManageContactForm manageContactForm, Errors errors)
	{	
		String costCenter = manageContactForm.getServiceRequest().getCostCenter();
		if(costCenter!=null && costCenter!="")
		{
			if(!(costCenter.matches(costCenterPattern)))
			{	
				errors.reject("contact.costCenter.format.error"); //Message from properties file
			}
		}
	}
}

