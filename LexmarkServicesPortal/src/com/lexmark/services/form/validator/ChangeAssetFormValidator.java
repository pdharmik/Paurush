package com.lexmark.services.form.validator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.services.form.ManageAssetForm;

public class ChangeAssetFormValidator implements Validator{

	private static Logger LOGGER = LogManager.getLogger(ChangeAssetFormValidator.class);
	/**
	 * 
	 * @param type 
	 * @return boolean   
	 */
	public boolean supports(Class<?> type) {
		
		return ManageAssetForm.class.equals(type);
		
	}
	/**
	 * 
	 * @param obj 
	 * @param errors 
	 */
	public void validate(Object obj, Errors errors) {
		LOGGER.info("Inside validate method of manage asset validator------>");
		ManageAssetForm manageAssetForm=(ManageAssetForm)obj;
		
		if(manageAssetForm.getAssetDetail()!=null){
			
			/*Serial No, Product, Install Date, IP Address, Host Name, Customer Asset tag
			 * Asset cost center, customer ref id, cost center, description, effective date of change			 * 
			 * 
			 */
			
		//Validation rule for asset serial no
		assetSerialNoValidator(manageAssetForm, errors);
		
		//Validation rule for product no
		assetProductNoValidator(manageAssetForm, errors);
		
		//Validation rule for product no
		assetInstallDateValidator(manageAssetForm, errors);
		
		//Validation rule for asset IP address, Gateway, Mask
		assetIPAddressValidator(manageAssetForm, errors);
		
		//Validation rule for asset host name
		assetHostNameValidator(manageAssetForm, errors);
		
		//validation rule for customer asset tag
		assetCustomerAssetTagValidator(manageAssetForm, errors);
		}
		
		//validation rule for cost center
		costCenterValidator(manageAssetForm, errors);
		//validation rule for customer reference id
		assetCustRefIdValidator(manageAssetForm, errors);
		
		//validation rule for asset cost center
		assetCostCenterValidator(manageAssetForm, errors);
		
		assetEffectiveDateOfChangeValidator(manageAssetForm, errors);
		
	}
/**
 * 
 * @param manageAssetForm 
 * @param errors 
 */
	private void assetEffectiveDateOfChangeValidator(
			ManageAssetForm manageAssetForm, Errors errors) {
		
		//Write the validation rule for effective date of change
	}
/**
 * 
 * @param manageAssetForm 
 * @param errors 
 */
	private void costCenterValidator(ManageAssetForm manageAssetForm,
			Errors errors) {
		
		//Write the validation rule for cost center
	}
/**
 * 
 * @param manageAssetForm 
 * @param errors 
 */
	private void assetInstallDateValidator(ManageAssetForm manageAssetForm,
			Errors errors) {
		
		//Write the validation rule for effective install date
	}
/**
 * 
 * @param manageAssetForm 
 * @param errors 
 */
	private void assetCostCenterValidator(ManageAssetForm manageAssetForm,
			Errors errors) {
		
		//Write the validation rule for cost center
	}
/**
 * 
 * @param manageAssetForm 
 * @param errors 
 */
	private void assetCustRefIdValidator(ManageAssetForm manageAssetForm,
			Errors errors) {
	//Write the validation rule for customer ref id
	}
/**
 * 
 * @param manageAssetForm 
 * @param errors 
 */
	private void assetCustomerAssetTagValidator(
			ManageAssetForm manageAssetForm, Errors errors) {
		//Write the validation rule for customer asset tag
		
	}
/**
 * 
 * @param manageAssetForm 
 * @param errors 
 */
	private void assetHostNameValidator(ManageAssetForm manageAssetForm,
			Errors errors) {
		//write the validation rule for host name
		
	}
	/**
	 * 
	 * @param manageAssetForm 
	 * @param errors 
	 */
	private void assetProductNoValidator(ManageAssetForm manageAssetForm, Errors errors)
	{
		//write the validation rule for product no
	}
	
	
	/**
	 * 
	 * @param manageAssetForm 
	 * @param errors 
	 */
	private void assetIPAddressValidator(ManageAssetForm manageAssetForm, Errors errors) {
		
		String patrn = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		
		if(!(manageAssetForm.getAssetDetail().getIpAddress().isEmpty()))
		{
			if(!(manageAssetForm.getAssetDetail().getIpAddress().matches(patrn)))
			{
				errors.reject("asset.ip.address.format");//Message from properties file
			}
		
			//else ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assetDetail.ipAddress","asset.ip.address.empty");
		}else{
			return;}//IP Address is allowed to be null
		
	}

	
	
	/********************Annotations used in the Asset bean**************/
	/**
	 * 
	 * @param manageAssetForm 
	 * @param errors 
	 */
	private void assetSerialNoValidator(ManageAssetForm manageAssetForm, Errors errors)
	{	
		if(manageAssetForm.getAssetDetail().getSerialNumber()!=null)
		{
		if(!(manageAssetForm.getAssetDetail().getSerialNumber().isEmpty()))
		{
			//This block only gets executed once the serial no field is not empty.
			
			String patrn="^[0-9]{7}$";//Checks for only digits and allowed a length of 7
			
			if(!(manageAssetForm.getAssetDetail().getSerialNumber().matches(patrn)))
			{	
				errors.reject("asset.serialno.format"); //Message from properties file
			}
		}
		//else ValidationUtils.rejectIfEmptyOrWhitespace(errors, "assetDetail.serialNumber","asset.serialno.empty");
		else {return;}
		}
	}
}

