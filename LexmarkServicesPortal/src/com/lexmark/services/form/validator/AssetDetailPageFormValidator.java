package com.lexmark.services.form.validator;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.services.form.AssetDetailPageForm;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class AssetDetailPageFormValidator implements Validator {
	private static final Logger LOGGER = LogManager.getLogger(AssetDetailPageFormValidator.class);
	private static final String NUMBER_PATTERN = "[1-9]+";
	/**
	 * 
	 * @param type 
	 * @return boolean 
	 */
	public boolean supports(Class<?> type) {
		// TODO Auto-generated method stub
		return AssetDetailPageForm.class.equals(type);
	}
/**
 * 
 * @param obj 
 * @param errors 
 */
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
			LOGGER.debug("Inside the validate method");
			AssetDetailPageForm assetDetailPageForm = (AssetDetailPageForm)obj;
			if(assetDetailPageForm.getAsset().getInstallationOnlyFlag()==false){
				qunatityValidator(assetDetailPageForm, errors);
			}
	}
	/**
	 * 
	 * @param form 
	 * @param errors 
	 */
	private void qunatityValidator(AssetDetailPageForm form, Errors errors){
		LOGGER.debug("About to write down the error ");
		//errors.reject("assetDetail.quantity.empty"); //Message from properties file
		boolean hasQuantityValue = false;
		boolean needEmptyCheck = true;
		int maxNoOfParts = Integer.parseInt(form.getMaxPartsToBeOrdered());
		LOGGER.debug("Max part to be ordered is "+form.getMaxPartsToBeOrdered());
		for (int i=0;i<form.getNoOfPart();i++){
			
			// started validation for max order qty 
			LOGGER.debug("form.getNoOfPart() "+form.getNoOfPart());
			LOGGER.debug("Part no of no of max qty "+form.getAssetPartList().get(i).getPartNumber());
			if(null != form.getAssetPartList().get(i).getMaxQuantity()){
				LOGGER.debug("in if getMaxQuantity not null ----------- "+form.getAssetPartList().get(i).getMaxQuantity());
				
				if("".equals(form.getAssetPartList().get(i).getMaxQuantity().trim())){
					LOGGER.debug("in if getMaxQuantity is blank ----------- "+form.getAssetPartList().get(i).getMaxQuantity());
					LOGGER.debug("Max Qty value is blank");
					LOGGER.debug("Setting the value to five");
					// if getMaxQuantity is blank, set it to 5 for default
					form.getAssetPartList().get(i).setMaxQuantity("5");
				}
			LOGGER.debug("max qty ----------- "+form.getAssetPartList().get(i).getMaxQuantity());
			maxNoOfParts = Integer.parseInt(form.getAssetPartList().get(i).getMaxQuantity());
			}
			else{
				// if MaxQuantity is null, set it to 5 for default
				form.getAssetPartList().get(i).setMaxQuantity("5");
				LOGGER.debug("in else Max parts is --------------- "+form.getAssetPartList().get(i).getMaxQuantity());
				maxNoOfParts = Integer.parseInt(form.getAssetPartList().get(i).getMaxQuantity());
			}
			
			// ended validation for max order qty 
			
			
			LOGGER.debug("Entered the for loop for quantity validation. let see the order quanitity form.getAssetPartList().get(i).getOrderQuantity() "+form.getAssetPartList().get(i).getOrderQuantity());
			LOGGER.debug("condition 1 "+form.getAssetPartList().get(i).getOrderQuantity());
			
			if((form.getAssetPartList().get(i).getOrderQuantity()==null)||(form.getAssetPartList().get(i).getOrderQuantity().length()==0)||
					(Integer.valueOf(form.getAssetPartList().get(i).getOrderQuantity())==0)){
				//we do not need to check the number format validation..
			}//else{
				/*if(!(form.getAssetPartList().get(i).getOrderQuantity().matches(NUMBER_PATTERN))){
					errors.reject("assetDetail.quantity.number");
					hasQuantityValue = true;
					break;
				}*/
			//}
			
			
			if((form.getAssetPartList().get(i).getOrderQuantity()==null)||(form.getAssetPartList().get(i).getOrderQuantity().length()==0)||((Integer.valueOf(form.getAssetPartList().get(i).getOrderQuantity())==0))){
				LOGGER.debug("Order quantity is empty");
				if(needEmptyCheck){//We dont need empty check if the control reached to else block for at least once
					hasQuantityValue=false;
				}
			}else{
				LOGGER.debug("At least one order quantity is not empty");
				if(Integer.parseInt(form.getAssetPartList().get(i).getOrderQuantity())>maxNoOfParts){
					//String[] maxNoOfPartsObject = {Integer.toString(maxNoOfParts)};
					errors.reject("requestInfo.label.validation.extra");
					hasQuantityValue = true;
					break;
				}
				hasQuantityValue = true;
				needEmptyCheck=false;
				//break;
			}
		}
		if(!hasQuantityValue){
			LOGGER.debug("So does not have any part quantity");
			errors.reject("assetDetail.quantity.empty");
		}
		for(int j=0;j<errors.getAllErrors().size();j++){
			LOGGER.debug("Errors are "+errors.getAllErrors().get(j).getCode().toString());
		}
	}
}
