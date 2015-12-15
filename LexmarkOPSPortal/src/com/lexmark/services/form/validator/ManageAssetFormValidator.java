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
 * Sourav						 		1.0             Initial Version
 *
 */
package com.lexmark.services.form.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.services.form.ManageAssetForm;
import com.lexmark.util.StringUtil;

/**
 * @author Wipro
 * @version 2.1
 */

public class ManageAssetFormValidator implements Validator{

	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageAssetFormValidator.class);
	private static final String datePatrn = "(0?[1-9]|[12][0-9]|3[01])";
	private static final String monthPatrn = "(0?[1-9]|1[012])";
	private static final String yearPatrn = "((19|20)\\d\\d)";
	public boolean supports(Class<?> type) {
		
		return ManageAssetForm.class.equals(type);
		
	}

	public void validate(Object obj, Errors errors) {
		LOGGER.debug("Inside validate method of manage asset validator------>");
		ManageAssetForm manageAssetForm=(ManageAssetForm)obj;
		
		validateForAddAsset(manageAssetForm, errors);
		validateForChangeAsset(manageAssetForm, errors);
	}

	private void validateForChangeAsset(ManageAssetForm manageAssetForm, Errors errors) {
		//Validation rule for asset serial no
		if(manageAssetForm.getNewAssetInfo()!=null)
		{			
			//Validation rule for asset IP address
			assetIPAddressValidator(manageAssetForm.getNewAssetInfo().getIpAddress(), errors);
		}
	}

	private void validateForAddAsset(ManageAssetForm manageAssetForm, Errors errors) {
		if(manageAssetForm.getAssetDetail()!=null)
		{		
			//Validation rule for asset serial no			
			assetSerialNoValidator(manageAssetForm.getAssetDetail().getSerialNumber(), errors);
			//Validation rule for Install Date			
			assetInstallDateValidator(manageAssetForm.getAssetDetail().getInstallDate(), manageAssetForm.getDateFormat(), errors);
			//Validation rule for asset IP address
			assetIPAddressValidator(manageAssetForm.getAssetDetail().getIpAddress(), errors);
			assetAddtnDescValidator(manageAssetForm.getServiceRequest().getAddtnlDescription(),errors);
		}
	}
	/**
	 * Validator for Additional Description field
	 * @param addtnlDescription
	 * @param errors
	 */
	private void assetAddtnDescValidator(String addtnlDescription, Errors errors) {
		LOGGER.enter(this.getClass().getSimpleName(), "assetAddtnDescValidator");
		if(!StringUtil.isEmpty(addtnlDescription))
		{
			if(addtnlDescription.trim().length()>2000)
			{
				errors.reject("validation.description.length.errorMsg");
			}
		}
		LOGGER.exit(this.getClass().getSimpleName(), "assetAddtnDescValidator");
	}
	/**
	 * Validator for Install Date
	 * @param installDate
	 * @param dateFmt 
	 * @param errors
	 */
	private void assetInstallDateValidator(Date installDate,String dateFmt, Errors errors) {
		LOGGER.enter(this.getClass().getSimpleName(),"assetInstallDateValidator");		 
		LOGGER.debug("Date format is " + dateFmt);		
		final StringBuffer patrn = new StringBuffer();
		final char seperator = dateFmt.charAt(2); 
		LOGGER.debug("Seperator is --------> " + seperator);
		
		if(dateFmt.startsWith("dd")){
			LOGGER.debug("starts with dd");
			patrn.append(datePatrn + seperator + monthPatrn + seperator + yearPatrn);
		}
		else if(dateFmt.startsWith("mm")|| dateFmt.startsWith("MM"))
		{
			LOGGER.debug("starts with MM or mm");
			patrn.append(monthPatrn + seperator + datePatrn + seperator + yearPatrn);	
		}
		else{
			patrn.append(yearPatrn + seperator + datePatrn + seperator + monthPatrn);
		}
		LOGGER.debug("The patrn is  -------> " + patrn.toString());
		final DateFormat simpleDateFmt=new SimpleDateFormat(dateFmt);		
		LOGGER.debug("install date is " + installDate);		
		if(installDate!=null)				
		{	
			final String fmtInstallDt = simpleDateFmt.format(installDate);
			LOGGER.debug("install date after fmt " + fmtInstallDt);
			
			if(!(fmtInstallDt.trim().matches(patrn.toString())))
			{
				LOGGER.debug("contains errors");
				errors.reject("validation.Asset.installDate.format.errorMsg");
			}
		}
		patrn.delete(0, patrn.length());
		LOGGER.exit(this.getClass().getSimpleName(),"assetInstallDateValidator");
	}
	/**
	 * Validator for IP Address
	 * @param IPAddress
	 * @param errors
	 */
	private void assetIPAddressValidator(String IPAddress,Errors errors) {
		LOGGER.enter(this.getClass().getSimpleName(), "assetIPAddressValidator");
		//Covers both IPv6 and IPv4 pattern	
		final String patrn = "^(?:(?:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){7})|(?:(?!(?:.*[a-f0-9](?::|$)){7,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?)))|(?:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){5}:)|(?:(?!(?:.*[a-f0-9]:){5,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3}:)?))?(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))(?:\\.(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))){3}))$";
		
		if(!(IPAddress.isEmpty()))
		{
			if(!(IPAddress.matches(patrn)))
			{
				errors.reject("validation.Asset.ipadd.format.errorMsg");//Message from properties file
			}
		}
		LOGGER.exit(this.getClass().getSimpleName(), "assetIPAddressValidator");
	}
	/**
	 * Validator for Serial No Field
	 * @param serialNo
	 * @param errors
	 */
	private void assetSerialNoValidator(String serialNo, Errors errors)
	{
		LOGGER.enter(this.getClass().getSimpleName(), "assetSerialNoValidator");
		LOGGER.debug("Serial no is in the validator " + serialNo);		
		if(!(serialNo.isEmpty()))
		{	
			final String patrn="^[0-9a-zA-Z]{1,}$";//Checks for alphanumeric & the length validation is removed as per defect #12420
			
			if(!(serialNo.trim().matches(patrn)))
			{	
				errors.reject("validation.Asset.serialNo.format.errorMsg"); //Message from properties file
			}
		}
		LOGGER.exit(this.getClass().getSimpleName(), "assetSerialNoValidator");
	}
}
