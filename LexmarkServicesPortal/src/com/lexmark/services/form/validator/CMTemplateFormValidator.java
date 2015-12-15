/**
 * 
 */
package com.lexmark.services.form.validator;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.services.form.CMTemplateRquestForm;
import com.lexmark.services.form.FileUploadForm;


/**
 * @author gsarkar
 *
 */
public class CMTemplateFormValidator implements Validator{
	
	private static final Logger LOGGER = LogManager.getLogger(CMTemplateFormValidator.class);
	
	private int fileCount;
	private Long fileSize;
	//private List<String> fileExtensions;
	private String customerReferenceIdRegEx;
	private String costCenterRegEx;
	private int addtionalDescMaxLength;
	
/**	
 * 
 * @param givenClass 
 * @return boolean 
 */
	public boolean supports(Class<?> givenClass) {
		if(givenClass.equals(CMTemplateRquestForm.class) || givenClass.equals(FileUploadForm.class)){
		return true;
		}else{
			return false;
		}
	}
/**
 * 
 * @param form 
 * @param errors 
 */
	public void validate(Object form, Errors errors) {
		
		
		
		if (form instanceof FileUploadForm) {
			FileUploadForm fileUploadForm = (FileUploadForm) form;
			LOGGER.debug("fileUploadForm.getFileData().getContentType()--->"+fileUploadForm.getFileData().getContentType());
			
			if(fileUploadForm.getFileData().isEmpty()){
				LOGGER.debug("fileUpload.size.zero-->");
				errors.rejectValue("fileData", "fileUpload.size.zero");//reject("fileUpload.size.zero"); 				
			}else if(fileUploadForm.getFileData().getSize()>fileSize*1024){
				LOGGER.debug("fileUpload.size.exceeds-->");
				errors.rejectValue("fileData", "fileUpload.size.exceeds");//reject("fileUpload.size.exceeds");
			}

		}else if(form instanceof CMTemplateRquestForm){
			
			CMTemplateRquestForm cmTemplateRquestForm = (CMTemplateRquestForm) form;
			
			if(cmTemplateRquestForm.getFileName().isEmpty()){
				LOGGER.debug("fileUpload.file.required-->");
				errors.reject("fileUpload.file.required");
			}
			if(!cmTemplateRquestForm.getServiceRequest().getCustomerReferenceId().isEmpty() &&
					!cmTemplateRquestForm.getServiceRequest().getCustomerReferenceId().matches(customerReferenceIdRegEx)){
				LOGGER.debug("fileUpload.customerReferenceId.invalid-->");
				errors.reject("fileUpload.customerReferenceId.invalid");
			}if(!cmTemplateRquestForm.getServiceRequest().getCostCenter().isEmpty() &&
					!cmTemplateRquestForm.getServiceRequest().getCostCenter().matches(costCenterRegEx)){
				LOGGER.debug("fileUpload.costcenter.invalid-->");
				errors.reject("fileUpload.costcenter.invalid");
			}
			if(!cmTemplateRquestForm.getServiceRequest().getCostCenter().isEmpty() &&
					!cmTemplateRquestForm.getServiceRequest().getCostCenter().matches(costCenterRegEx)){
				LOGGER.debug("fileUpload.costcenter.invalid-->");
				errors.reject("fileUpload.costcenter.invalid");
			}
			if(!cmTemplateRquestForm.getServiceRequest().getAddtnlDescription().isEmpty() &&
					cmTemplateRquestForm.getServiceRequest().getAddtnlDescription().length()>addtionalDescMaxLength){
				LOGGER.debug("fileUpload.addtnlDescription.maxlength.exceeds-->");
				errors.reject("fileUpload.addtnlDescription.maxlength.exceeds");
			}
		}
		
		
	}

	/**
	 * @return the fileCount
	 */
	public int getFileCount() {
		return fileCount;
	}

	/**
	 * @param fileCount the fileCount to set
	 */
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	/**
	 * @return the fileSize
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the fileExtensions
	 *//*
	public List<String> getFileExtensions() {
		return fileExtensions;
	}

	*//**
	 * @param fileExtensions the fileExtensions to set
	 *//*
	public void setFileExtensions(List<String> fileExtensions) {
		this.fileExtensions = fileExtensions;
	}*/

	/**
	 * @return the customerReferenceIdRegEx
	 */
	public String getCustomerReferenceIdRegEx() {
		return customerReferenceIdRegEx;
	}

	/**
	 * @param customerReferenceIdRegEx the customerReferenceIdRegEx to set
	 */
	public void setCustomerReferenceIdRegEx(String customerReferenceIdRegEx) {
		this.customerReferenceIdRegEx = customerReferenceIdRegEx;
	}

	/**
	 * @return the costCenterRegEx
	 */
	public String getCostCenterRegEx() {
		return costCenterRegEx;
	}

	/**
	 * @param costCenterRegEx the costCenterRegEx to set
	 */
	public void setCostCenterRegEx(String costCenterRegEx) {
		this.costCenterRegEx = costCenterRegEx;
	}

	/**
	 * @return the addtionalDescMaxLength
	 */
	public int getAddtionalDescMaxLength() {
		return addtionalDescMaxLength;
	}

	/**
	 * @param addtionalDescMaxLength the addtionalDescMaxLength to set
	 */
	public void setAddtionalDescMaxLength(int addtionalDescMaxLength) {
		this.addtionalDescMaxLength = addtionalDescMaxLength;
	}

}
