package com.lexmark.services.form.validator;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.services.form.HardwareDetailPageForm;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class HardwareDetailPageFormValidator implements Validator{
	private static final Logger LOGGER = LogManager.getLogger(HardwareDetailPageFormValidator.class);
	/**
	 * variable declaration
	 */
	private static final String NUMBER_PATTERN = "\\d{1,9}";//(\d){1,9}
	/**
	 * 
	 * @param type 
	 * @return boolean 
	 */
	public boolean supports(Class<?> type) {
		return HardwareDetailPageForm.class.equals(type);
	}
	
/**
 * 
 * @param obj 
 * @param errors 
 */
	public void validate(Object obj, Errors errors) {
		HardwareDetailPageForm hardwareDetailPageForm = (HardwareDetailPageForm)obj;
		shipToAddressValidator(hardwareDetailPageForm, errors);
		qunatityValidator(hardwareDetailPageForm, errors);
	}
	/*
	 * This method is used to validate the ship to address.
	 * For catalog order ship to address is mandatory
	 */
	/**
	 * 
	 * @param hardwareDetailPageForm 
	 * @param errors 
	 */
	private void shipToAddressValidator(HardwareDetailPageForm hardwareDetailPageForm, Errors errors){
		LOGGER.debug("Entered to the method shipToAddressValidator");
		String country = hardwareDetailPageForm.getShipToAddress().getCountry();
		LOGGER.debug("Country value is coming as "+country);
		if("".equals(country)||country == null){
			LOGGER.debug("Country value is null so no ship to address is selected.");
			LOGGER.debug("@@@@@@@@@@@@@@@@@ ship to address is not selected so throwing the error");
			errors.reject("requestInfo.hardwareDetail.shipToAddress.mandatory");
		}
		LOGGER.debug("Exit from the method shipToAddressValidator");
	}
	/**
	 * This method is used to validate the quantity entered in the form
	 * @param form 
	 * @param errors 
	 */
	
	private void qunatityValidator(HardwareDetailPageForm form, Errors errors){
		LOGGER.debug("About to write down the error ");
		boolean hasQuantityValue = false;
		boolean needEmptyCheck = true;
		
		//int maxNoOfParts = Integer.parseInt(form.getMaxPartsToBeOrdered());
		//For Bundles
		if(form.getBundleList() !=null){
			int bundleCount=0;
		for (int i=0;i<form.getBundleList().size();i++){	
			
			if((form.getBundleList().get(i).getOrderQuantity()==null)||(form.getBundleList().get(i).getOrderQuantity().length()==0)){
				
			}else{
				
				if(!(form.getBundleList().get(i).getOrderQuantity().matches(NUMBER_PATTERN)) || (("0").equals(form.getBundleList().get(i).getOrderQuantity()))){
					if(bundleCount==0){
					errors.reject("hardwareDetails.quantity.bundle.number");
					}
					hasQuantityValue = true;
					bundleCount++;
					//break;
				}
			}
			
			if((form.getBundleList().get(i).getOrderQuantity()==null)||(form.getBundleList().get(i).getOrderQuantity().length()==0)){
				
				if(needEmptyCheck){//We dont need empty check if the control reached to else block for at least once
					hasQuantityValue=false;
				}
			}else{
						
				hasQuantityValue = true;
				needEmptyCheck=false;
				//break;
			}
		}
		}
		
		//For accessories
		if(form.getAccessoriesList() !=null){
			int accessoriesCount=0;
			for (int i=0;i<form.getAccessoriesList().size();i++){
			
			if((form.getAccessoriesList().get(i).getOrderQuantity()==null)||(form.getAccessoriesList().get(i).getOrderQuantity().length()==0)){
				//we do not need to check the number format validation..
			}else{
				if(!(form.getAccessoriesList().get(i).getOrderQuantity().matches(NUMBER_PATTERN)) || (("0").equals(form.getAccessoriesList().get(i).getOrderQuantity()))){
					if(accessoriesCount ==0){
					errors.reject("hardwareDetails.quantity.accessories.number");
					}
					hasQuantityValue = true;
					//break;
					accessoriesCount++;
				}
			}
			
			
			if((form.getAccessoriesList().get(i).getOrderQuantity()==null)||(form.getAccessoriesList().get(i).getOrderQuantity().length()==0)){
				
				if(needEmptyCheck){//We dont need empty check if the control reached to else block for at least once
					hasQuantityValue=false;
				}
			}else{
								
				hasQuantityValue = true;
				needEmptyCheck=false;
				//break;
			}
		}
		}
		
		if(!hasQuantityValue){
			
			errors.reject("assetDetail.quantity.empty");
		}
		for(int j=0;j<errors.getAllErrors().size();j++){
			LOGGER.debug("Errors are "+errors.getAllErrors().get(j).getCode().toString());
		}
	}
	
	
}