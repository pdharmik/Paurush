/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CatalogDetailPageFormValidator.java
 * Package     		: com.lexmark.services.form.validator
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.form.validator;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.services.form.CatalogDetailPageForm;

/**
 * This class is used for validation in catalog order section
 * @version 2.1
 * @author wipro
 *
 */
public class CatalogDetailPageFormValidator implements Validator {
	/**. Instance variable of wrapper logger class **/
	private static final Logger LOGGER = LogManager.getLogger(CatalogDetailPageFormValidator.class);
	/**
	 * variable declaration
	 */
	private static final String NUMBER_PATTERN = "\\d{1,9}";//(\d){1,9}
	/**
	 * @return Boolean 
	 * @param type 
	 */
	public boolean supports(Class<?> type) {
		// TODO Auto-generated method stub
		return CatalogDetailPageForm.class.equals(type);
	}
	/**
	 * @param obj 
	 * @param errors 
	 */
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
			CatalogDetailPageForm catalogDetailPageForm = (CatalogDetailPageForm)obj;
			qunatityValidator(catalogDetailPageForm, errors);
			shipToAddressValidator(catalogDetailPageForm, errors);
	}
	
	/**
	 * This method is used to validate the quantity entered in the form
	 * @param form 
	 * @param errors 
	 */
	private void qunatityValidator(CatalogDetailPageForm form, Errors errors){
		LOGGER.debug("About to write down the error ");
		boolean hasQuantityValue = false;
		boolean needEmptyCheck = true;
		
		//int maxNoOfParts = Integer.parseInt(form.getMaxPartsToBeOrdered());
		
		for (int i=0;i<form.getCatalogPartList().size();i++){
			
			if((form.getCatalogPartList().get(i).getOrderQuantity()==null)||(form.getCatalogPartList().get(i).getOrderQuantity().length()==0)||
					(form.getCatalogPartList().get(i).getOrderQuantity().equals(Integer.toString(0)))){
				//we do not need to check the number format validation..
			}else{
				if(!(form.getCatalogPartList().get(i).getOrderQuantity().matches(NUMBER_PATTERN))){
					errors.reject("assetDetail.quantity.number");
					hasQuantityValue = true;
					break;
				}
			}
			
			
			if((form.getCatalogPartList().get(i).getOrderQuantity()==null)||(form.getCatalogPartList().get(i).getOrderQuantity().length()==0)||(form.getCatalogPartList().get(i).getOrderQuantity().equals(Integer.toString(0)))){
				
				if(needEmptyCheck){//We dont need empty check if the control reached to else block for at least once
					hasQuantityValue=false;
				}
			}else{
								
				hasQuantityValue = true;
				needEmptyCheck=false;
				//break;
			}
		}
		if(!hasQuantityValue){
			
			errors.reject("assetDetail.quantity.empty");
		}
		for(int j=0;j<errors.getAllErrors().size();j++){
			LOGGER.debug("Errors are "+errors.getAllErrors().get(j).getCode().toString());
		}
	}
	
	
	/**
	 * This method is used to validate the ship to address. For catalog order ship to address is mandatory
	 * @param catalogDetailPageForm 
	 * @param errors 
	 */
	private void shipToAddressValidator(CatalogDetailPageForm catalogDetailPageForm, Errors errors){
		LOGGER.debug("Entered to the method shipToAddressValidator");
		/**
		 * MPS 2.1
		 * Changes for defect 9959 ShipTo address can be blank
		 * */
		if("draftOrderRequest".equalsIgnoreCase(catalogDetailPageForm.getPageSubmitType())){
			return;
		}
		/*Ends changes defect 9959*/
		String country = catalogDetailPageForm.getShipToAddress().getCountry();
		
		if("".equals(country)||country == null){
			
			errors.reject("catalogDetail.shipToAddress.mandatory");
		}
		LOGGER.debug("Exit from the method shipToAddressValidator");
	}
}