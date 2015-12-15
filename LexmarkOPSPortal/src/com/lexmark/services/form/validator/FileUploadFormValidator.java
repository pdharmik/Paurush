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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.services.form.FileUploadForm;

/**
 * @author wipro
 * @version 2.1
 *
 */

public class FileUploadFormValidator implements Validator {

	private static final LEXLogger LOGGER = LEXLogger
			.getLEXLogger(FileUploadFormValidator.class);
	private static String CLASS_NAME = "FileUploadFormValidator" ;
	private String maxFileCount;
	private String maxFileSize;
	private String fileTypeList;
	private String tempMaxCount;
	private String acceptMaxCount;
	private String acceptMaxSize;
	private String fileTypeListMaps;
	

	public boolean supports(Class<?> type) {
		return FileUploadForm.class.equals(type);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		String METHOD_NAME="validate";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		FileUploadForm fileUploadForm = (FileUploadForm) obj;
		String pageType = fileUploadForm.getPageType();
		LOGGER.debug("Inside FileUploadFormValidator pageType :: " + pageType);
		if (fileUploadForm.getFileData() != null) {
			if (pageType != null) {
				if ("Template".equalsIgnoreCase(pageType)) {
					fileZeroSizeValidator(fileUploadForm, errors);
					fileSizeValidator(fileUploadForm, errors);
					fileCountValidatorForTemplate(fileUploadForm, errors);
					csvFileValidator(fileUploadForm, errors);
				}
				if("Acceptance".equalsIgnoreCase(pageType)) {
					fileZeroSizeValidator(fileUploadForm, errors);
					acceptFileSizeValidator(fileUploadForm, errors);
					fileCountValidatorForAcceptance(fileUploadForm, errors);
					
				}
				if("mapRequest".equalsIgnoreCase(pageType)) {
					fileTypeValidatorForMaps(fileUploadForm, errors);
					
				}
			} else {
				fileZeroSizeValidator(fileUploadForm, errors);
				fileSizeValidator(fileUploadForm, errors);
				fileCountValidator(fileUploadForm, errors);
				fileTypeValidator(fileUploadForm, errors);
			}
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	private void fileZeroSizeValidator(FileUploadForm fileUploadForm,
			Errors errors) {
		String METHOD_NAME="fileZeroSizeValidator";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		CommonsMultipartFile file = fileUploadForm.getFileData();
		if (file.getSize() <= 0) {
			errors.reject("validation.fileUpload.fileSizeEmpty.errorMsg"); // Message
																			// from
																			// properties
																			// file
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	private void fileSizeValidator(FileUploadForm fileUploadForm, Errors errors) {
		String METHOD_NAME="fileSizeValidator";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		CommonsMultipartFile file = fileUploadForm.getFileData();
		if (file.getSize() > Long.valueOf(maxFileSize)) {
			errors.reject("validation.fileUpload.maxSizeExceeds.errorMsg"); // Message
																			// from
																			// properties
																			// file
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	private void fileCountValidator(FileUploadForm fileUploadForm, Errors errors) {
		String METHOD_NAME="fileCountValidator";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		int fileCount = fileUploadForm.getFileCount();
		if (fileCount == Integer.valueOf(maxFileCount)) {
			errors.reject("validation.fileUpload.fileCountExceeds.errorMsg"); // Message
																				// from
																				// properties
																				// file
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	private void fileCountValidatorForTemplate(FileUploadForm fileUploadForm,
			Errors errors) {
		String METHOD_NAME="fileCountValidatorForTemplate";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		int fileCount = fileUploadForm.getFileCount();
		LOGGER.debug("in fileCountValidatorForTemplate fileCount " + fileCount);
		if (fileCount == Integer.valueOf(tempMaxCount)) {
			errors.reject("validation.fileUpload.template.fileCountExceeds.errorMsg"); // Message
																						// from
																						// properties
																						// file
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	
	private void fileCountValidatorForAcceptance(FileUploadForm fileUploadForm,
			Errors errors) {
		String METHOD_NAME="fileCountValidatorForAcceptance";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		int fileCount = fileUploadForm.getFileCount();
		LOGGER.debug("in fileCountValidatorForAcceptance fileCount " + fileCount);
		if (fileCount == Integer.valueOf(acceptMaxCount)) {
			errors.reject("validation.fileUpload.template.fileCountExceeds.errorMsg"); // Message
																						// from
																						// properties
																						// file
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	private void fileTypeValidator(FileUploadForm fileUploadForm, Errors errors) {
		String METHOD_NAME="fileTypeValidator";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		String fileName = fileUploadForm.getFileData().getFileItem().getName();
		String fileType = fileName.substring(fileName.lastIndexOf('.') + 1,
				fileName.length());
		fileType = fileType.toLowerCase();
		LOGGER.debug("in fileCountValidator fileName " + fileName
				+ " fileType " + fileType);
		if (fileTypeList.indexOf(fileType) < 0) {
			errors.reject("validation.fileUpload.fileTypeInvalid.errorMsg"); // Message
																				// from
																				// properties
																				// file
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	private void fileTypeValidatorForMaps(FileUploadForm fileUploadForm, Errors errors) {
		String METHOD_NAME="fileTypeValidator";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		String fileName = fileUploadForm.getFileData().getFileItem().getName();
		String fileType = fileName.substring(fileName.lastIndexOf('.') + 1,
				fileName.length());
		fileType = fileType.toLowerCase();
		LOGGER.debug("in fileCountValidator fileName " + fileName
				+ " fileType " + fileType);
		if (fileTypeListMaps.indexOf(fileType) < 0) {
			errors.reject("validation.fileUpload.fileTypeInvalid.errorMsg"); // Message
																				// from
																				// properties
																				// file
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	private void csvFileValidator(FileUploadForm fileUploadForm, Errors errors) {
		String METHOD_NAME="csvFileValidator";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		String fileName = fileUploadForm.getFileData().getFileItem().getName();
		String fileType = fileName.substring(fileName.lastIndexOf('.') + 1,
				fileName.length());
		LOGGER.debug("in csvFileValidator fileType " + fileType + " fileName "
				+ fileName);
		if (!"xls".equalsIgnoreCase(fileType) && !"xlsx".equalsIgnoreCase(fileType)) {
			errors.reject("validation.fileUpload.template.fileTypeInvalid.errorMsg"); // Message
																						// from
																						// properties
																						// file
		}
		
				LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	
	//MPS Phase 2.1 changes
	
	private void acceptFileSizeValidator(FileUploadForm fileUploadForm, Errors errors) {
		String METHOD_NAME="fileSizeValidator";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		CommonsMultipartFile file = fileUploadForm.getFileData();
		if (file.getSize() > Long.valueOf(acceptMaxSize)) {
			errors.reject("validation.fileUpload.maxSizeExceeds.errorMsg"); // Message
																			// from
																			// properties
																			// file
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	public String getMaxFileCount() {
		return maxFileCount;
	}

	public void setMaxFileCount(String maxFileCount) {
		this.maxFileCount = maxFileCount;
	}

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public String getFileTypeList() {
		return fileTypeList;
	}

	public void setFileTypeList(String fileTypeList) {
		this.fileTypeList = fileTypeList;
	}

	public String getTempMaxCount() {
		return tempMaxCount;
	}

	public void setTempMaxCount(String tempMaxCount) {
		this.tempMaxCount = tempMaxCount;
	}

	public String getAcceptMaxSize() {
		return acceptMaxSize;
	}

	public void setAcceptMaxSize(String acceptMaxSize) {
		this.acceptMaxSize = acceptMaxSize;
	}

	public String getAcceptMaxCount() {
		return acceptMaxCount;
	}

	public void setAcceptMaxCount(String acceptMaxCount) {
		this.acceptMaxCount = acceptMaxCount;
	}

	public String getFileTypeListMaps() {
		return fileTypeListMaps;
	}

	public void setFileTypeListMaps(String fileTypeListMaps) {
		this.fileTypeListMaps = fileTypeListMaps;
	}

}
