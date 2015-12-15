package com.lexmark.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.Activity;
import com.lexmark.domain.Attachment;

public class ServiceRequestDetailForm  extends BaseForm{

	private static final long serialVersionUID = -7708068333372970624L;
	private Activity activity = null;
	private String technicianInformationListXML;
	private String orderPartsXML;
	private String returnPartsXML;
	private String recommendedPartsXML;
	private String additionalPaymentRequestsXML;
	private String notesXML;
	private String problemCodeLocalized;
	private String partnerAddressListURL;
	private String technicianFullName;
	private List<String> addressStatusList = new ArrayList<String>();
	private Map<String, String> activityStatusList ;
	private String contactId;
	private String firstName;
	private String lastName;
	private float timezoneOffset;
	private String customerRequestedResponseStr;
	private String estimatedTimeofArrivalStr;
	private String statusAsOfStr;
	private String requestFromPage;
	private String serviceProviderReferenceNumber;
	private CommonsMultipartFile fileData;		// added for CI5 attachment feature
	private int fileCount;//Attachment
	private String totalFileSize;//Attachment
	private String attachmentFileXML;		
	private List<Attachment> attachmentList;
	private String childSRStatus;


	public String getChildSRStatus() {
		return childSRStatus;
	}
	public void setChildSRStatus(String childSRStatus) {
		this.childSRStatus = childSRStatus;
	}
	
	// CI6 Attachment Error Message
	
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getAttachmentFileXML() {
		return attachmentFileXML;
	}

	public void setAttachmentFileXML(String attachmentFileXML) {
		this.attachmentFileXML = attachmentFileXML;
	}

	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
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

	private boolean allowAdditionalPaymentRequestFlag;

	
	public String getTechnicianFullName() {
		return technicianFullName;
	}

	public void setTechnicianFullName(String technicianFullName) {
		this.technicianFullName = technicianFullName;
	}

	public Map<String, String> getActivityStatusList() {
		return activityStatusList;
	}

	public void setActivityStatusList(Map<String, String> activityStatusList) {
		this.activityStatusList = activityStatusList;
	}

	public boolean isAllowAdditionalPaymentRequestFlag() {
		return allowAdditionalPaymentRequestFlag;
	}

	public void setAllowAdditionalPaymentRequestFlag(boolean allowAdditionalPaymentRequestFlag) {
		this.allowAdditionalPaymentRequestFlag = allowAdditionalPaymentRequestFlag;
	}

	public String getTechnicianInformationListXML() {
		return technicianInformationListXML;
	}

	public void setTechnicianInformationListXML(String technicianInformationListXML) {
		this.technicianInformationListXML = technicianInformationListXML;
	}

	public String getProblemCodeLocalized() {
		return problemCodeLocalized;
	}

	public void setProblemCodeLocalized(String problemCodeLocalized) {
		this.problemCodeLocalized = problemCodeLocalized;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity serviceRequest) {
		this.activity = serviceRequest;
	}

	public String getOrderPartsXML() {
		return orderPartsXML;
	}

	public void setOrderPartsXML(String orderPartsXML) {
		this.orderPartsXML = orderPartsXML;
	}

	public String getReturnPartsXML() {
		return returnPartsXML;
	}

	public void setReturnPartsXML(String returnPartsXML) {
		this.returnPartsXML = returnPartsXML;
	}

	public String getRecommendedPartsXML() {
		return recommendedPartsXML;
	}

	public void setRecommendedPartsXML(String recommendedPartsXML) {
		this.recommendedPartsXML = recommendedPartsXML;
	}

	public String getAdditionalPaymentRequestsXML() {
		return additionalPaymentRequestsXML;
	}

	public void setAdditionalPaymentRequestsXML(String additionalPaymentRequestsXML) {
		this.additionalPaymentRequestsXML = additionalPaymentRequestsXML;
	}

	public String getNotesXML() {
		return notesXML;
	}

	public void setNotesXML(String notesXML) {
		this.notesXML = notesXML;
	}

	public String getPartnerAddressListURL() {
		return partnerAddressListURL;
	}

	public void setPartnerAddressListURL(String partnerAddressListURL) {
		this.partnerAddressListURL = partnerAddressListURL;
	}

	public List<String> getAddressStatusList() {
		return addressStatusList;
	}

	public void setAddressStatusList(List<String> addressStatusList) {
		this.addressStatusList = addressStatusList;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public float getTimezoneOffset() {
		return timezoneOffset;
	}

	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	public String getCustomerRequestedResponseStr() {
		return customerRequestedResponseStr;
	}

	public void setCustomerRequestedResponseStr(String customerRequestedResponseStr) {
		this.customerRequestedResponseStr = customerRequestedResponseStr;
	}

	public String getEstimatedTimeofArrivalStr() {
		return estimatedTimeofArrivalStr;
	}

	public void setEstimatedTimeofArrivalStr(String estimatedTimeofArrivalStr) {
		this.estimatedTimeofArrivalStr = estimatedTimeofArrivalStr;
	}

	public String getStatusAsOfStr() {
		return statusAsOfStr;
	}

	public void setStatusAsOfStr(String statusAsOfStr) {
		this.statusAsOfStr = statusAsOfStr;
	}

	public String getRequestFromPage() {
		return requestFromPage;
	}

	public void setRequestFromPage(String requestFromPage) {
		this.requestFromPage = requestFromPage;
	}

	public String getServiceProviderReferenceNumber() {
		return serviceProviderReferenceNumber;
	}

	public void setServiceProviderReferenceNumber(String serviceProviderReferenceNumber) {
		this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
	}
}
