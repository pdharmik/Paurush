/**
 * 
 */
package com.lexmark.services.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.ServiceRequest;


/**
 * @author wipro
 *
 */
public class CMTemplateRquestForm extends BaseForm{
	
	private ServiceRequest serviceRequest;
	private String requestType;
	private String requestSubType;
	private String prevSRNumber ;
	private String fileName;
	private String fileSize;
	private String fileSizeInByte;
	private CommonsMultipartFile fileData;
  
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	
	public String getRequestSubType() {
		return requestSubType;
	}
	public void setRequestSubType(String requestSubType) {
		this.requestSubType = requestSubType;
	}
	/**
	public String getEffectiveDateOfChange() {
		return effectiveDateOfChange;
	}
	public void setEffectiveDateOfChange(String effectiveDateOfChange) {
		this.effectiveDateOfChange = effectiveDateOfChange;
	}
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public void setCustomerReferenceId(String customerReferenceId) {
		this.customerReferenceId = customerReferenceId;
	}
	public String getCustomerReferenceId() {
		return customerReferenceId;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the fileData
	 */
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	/**
	 * @param fileData the fileData to set
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	/**
	 * @return the serviceRequest
	 */
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	/**
	 * @param serviceRequest the serviceRequest to set
	 */
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	/**
	 * @return the fileSizeInByte
	 */
	public String getFileSizeInByte() {
		return fileSizeInByte;
	}
	/**
	 * @param fileSizeInByte the fileSizeInByte to set
	 */
	public void setFileSizeInByte(String fileSizeInByte) {
		this.fileSizeInByte = fileSizeInByte;
	}
	/**
	 * @return the prevSRNumber
	 */
	public String getPrevSRNumber() {
		return prevSRNumber;
	}
	/**
	 * @param prevSRNumber the prevSRNumber to set
	 */
	public void setPrevSRNumber(String prevSRNumber) {
		this.prevSRNumber = prevSRNumber;
	}
	
	
}
