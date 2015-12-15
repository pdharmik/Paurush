/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ClaimRequestConfirmationForm
 * Package     		: com.lexmark.form
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.form;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.Account;
import com.lexmark.domain.Activity;
import com.lexmark.domain.Asset;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.Attachment;

public class ClaimRequestConfirmationForm extends BaseForm implements Serializable{

	private static final long serialVersionUID = -4714435115841009238L;
	
	private Activity activity;
	private Asset asset;
	private boolean notMyPrinterFlag;
	private Map<String, String> problemCodeList;
	private Map<String, String> partStatusList;
	private Map<String, String> errorCode1List;
	private Map<String, String> carriers;
	private Map<String, String> resolutionCodeList;
	private Map<String, String> paymentTypes;
	private Map<String, String> workingConditionList;
	private String problemCode;
	private String resolutionCode;
	private String serviceStartDate;
	private String serviceEndDate;
	private List<Account> partnerIndirectAccountList;
	private String activityNoteListXML;
	private String requestedPartsListXML;
	private String additionalPaymentRequestListXML;
	private String requestedPartsListNOXML;
	private String partnerAddressListURL;
	private String problemCodeLocalized;
	private String technicianFullName;
	private String newAccountName;
	private String resolutionCodeLocalized;
	private float timezoneOffset;
	private CommonsMultipartFile fileData;		// for CI5 attachment feature
	private int fileCount;//Attachment
	private String totalFileSize;//Attachment
	private String attachmentFileXML;		// for CI5 attachment feature
	private Map<String, AttachmentFile> fileMap = new LinkedHashMap<String, AttachmentFile>();		// for CI5 attachment feature
	private Map<String, FileObject> attachmentFileMap;//Attachment
	/*Added by sankha for AIR65413 start*/
	private boolean showAdditionalPaymentRequestListFlag;
	private String pageSubmitType; //Added for CI BRD13-10-01
	private String serviceRequestedDate; //Added for CI BRD13-10-07
	
	public boolean isShowAdditionalPaymentRequestListFlag() {
		return showAdditionalPaymentRequestListFlag;
	}
	public void setShowAdditionalPaymentRequestListFlag(
			boolean showAdditionalPaymentRequestListFlag) {
		this.showAdditionalPaymentRequestListFlag = showAdditionalPaymentRequestListFlag;
	}
	/*Added by sankha for AIR65413 end*/
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	private List<Attachment> attachmentList;
	
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public String getTotalFileSize() {
		return totalFileSize;
	}
	public void setTotalFileSize(String totalFileSize) {
		this.totalFileSize = totalFileSize;
	}
	public float getTimezoneOffset() {
		return timezoneOffset;
	}
	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	public String getResolutionCodeLocalized() {
		return resolutionCodeLocalized;
	}
	public void setResolutionCodeLocalized(String resolutionCodeLocalized) {
		this.resolutionCodeLocalized = resolutionCodeLocalized;
	}
	public String getProblemCodeLocalized() {
		return problemCodeLocalized;
	}
	public void setProblemCodeLocalized(String problemCodeLocalized) {
		this.problemCodeLocalized = problemCodeLocalized;
	}
	public String getRequestedPartsListNOXML() {
		return requestedPartsListNOXML;
	}
	public void setRequestedPartsListNOXML(String requestedPartsListNOXML) {
		this.requestedPartsListNOXML = requestedPartsListNOXML;
	}
	
	public String getActivityNoteListXML() {
		return activityNoteListXML;
	}
	public void setActivityNoteListXML(String activityNoteListXML) {
		this.activityNoteListXML = activityNoteListXML;
	}
	public String getRequestedPartsListXML() {
		return requestedPartsListXML;
	}
	public void setRequestedPartsListXML(String requestedPartsListXML) {
		this.requestedPartsListXML = requestedPartsListXML;
	}
	public String getAdditionalPaymentRequestListXML() {
		return additionalPaymentRequestListXML;
	}
	public void setAdditionalPaymentRequestListXML(
			String additionalPaymentRequestListXML) {
		this.additionalPaymentRequestListXML = additionalPaymentRequestListXML;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public boolean isNotMyPrinterFlag() {
		return notMyPrinterFlag;
	}
	public void setNotMyPrinterFlag(boolean notMyPrinterFlag) {
		this.notMyPrinterFlag = notMyPrinterFlag;
	}
	public Map<String, String> getProblemCodeList() {
		return problemCodeList;
	}
	public void setProblemCodeList(Map<String, String> problemCodeList) {
		this.problemCodeList = problemCodeList;
	}
	public Map<String, String> getPartStatusList() {
		return partStatusList;
	}
	public void setPartStatusList(Map<String, String> partStatusList) {
		this.partStatusList = partStatusList;
	}
	public Map<String, String> getErrorCode1List() {
		return errorCode1List;
	}
	public void setErrorCode1List(Map<String, String> errorCode1List) {
		this.errorCode1List = errorCode1List;
	}
	public Map<String, String> getCarriers() {
		return carriers;
	}
	public void setCarriers(Map<String, String> carriers) {
		this.carriers = carriers;
	}
	public Map<String, String> getResolutionCodeList() {
		return resolutionCodeList;
	}
	public void setResolutionCodeList(Map<String, String> resolutionCodeList) {
		this.resolutionCodeList = resolutionCodeList;
	}
	public String getProblemCode() {
		return problemCode;
	}
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}
	public String getResolutionCode() {
		return resolutionCode;
	}
	public void setResolutionCode(String resolutionCode) {
		this.resolutionCode = resolutionCode;
	}
	public List<Account> getPartnerIndirectAccountList() {
		return partnerIndirectAccountList;
	}
	public void setPartnerIndirectAccountList(
			List<Account> partnerIndirectAccountList) {
		this.partnerIndirectAccountList = partnerIndirectAccountList;
	}
	public String getPartnerAddressListURL() {
		return partnerAddressListURL;
	}
	public void setPartnerAddressListURL(String partnerAddressListURL) {
		this.partnerAddressListURL = partnerAddressListURL;
	}
	public String getServiceStartDate() {
		return serviceStartDate;
	}
	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	public String getServiceEndDate() {
		return serviceEndDate;
	}
	public void setServiceEndDate(String serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}
	public Map<String, String> getPaymentTypes() {
		return paymentTypes;
	}
	public void setPaymentTypes(Map<String, String> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	public String getTechnicianFullName() {
		return technicianFullName;
	}
	public void setTechnicianFullName(String technicianFullName) {
		this.technicianFullName = technicianFullName;
	}
	public String getNewAccountName() {
		return newAccountName;
	}
	public void setNewAccountName(String newAccountName) {
		this.newAccountName = newAccountName;
	}
	public Map<String, String> getWorkingConditionList() {
		return workingConditionList;
	}
	public void setWorkingConditionList(Map<String, String> workingConditionList) {
		this.workingConditionList = workingConditionList;
	}
	
// for attachment feature CI5

	public void setAttachmentFileXML(String attachmentFileXML) {
		this.attachmentFileXML = attachmentFileXML;
	}
	
	public String getAttachmentFileXML() {
		return attachmentFileXML;
	}
	
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	
	public void setFileMap(Map<String, AttachmentFile> fileMap) {
		this.fileMap = fileMap;
	}
	
	public Map<String, AttachmentFile> getFileMap() {
		return fileMap;
	}
	public Map<String, FileObject> getAttachmentFileMap() {
		return attachmentFileMap;
	}
	public void setAttachmentFileMap(Map<String, FileObject> attachmentFileMap) {
		this.attachmentFileMap = attachmentFileMap;
	}
	public String getPageSubmitType() {
		return pageSubmitType;
	}
	public void setPageSubmitType(String pageSubmitType) {
		this.pageSubmitType = pageSubmitType;
	}
	public String getServiceRequestedDate() {
		return serviceRequestedDate;
	}
	public void setServiceRequestedDate(String serviceRequestedDate) {
		this.serviceRequestedDate = serviceRequestedDate;
	}
	
//	 for CI5 attachment feature
}
