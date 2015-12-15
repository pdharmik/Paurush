package com.lexmark.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;

/**
 * Form used to display close out request page.
 *
 */
public class RequestsDebriefForm extends BaseForm{
	private static final long serialVersionUID = 775226580216562067L;
	private Activity activity;
	private String technicianFullName;
	private Map<String, String> travelUnitOfMeasureMap;
	private Map<String, String> problemCodeMap;
	private List<AccountContact>  technicianList;
	private Map<String, String> resolutionCodeMap;
	private Map<String, String> workingConditionaMap;
	private Map<String, String> partStatusList;
	private Map<String, String> errorCode1List;
	private Map<String, String> paymentTypes;
	private Map<String, String> carrierList;
	private Map<String, String> sourceList;
	private List<String> addressStatusList = new ArrayList<String>();
	private String serviceStartDate;
	private String serviceEndDate;
	private String activityNoteListXML;
	private String partsAndToolsListXML;
	private String additionalPaymentRequestListXML;
	private boolean allowAdditionalPaymentRequestFlag;
	private String partDebriefListXML;
	private String contactId;
	private String partnerAddressListURL;
	private boolean showAdditionalPaymentRequestListFlag;
	private boolean removeDeviceSectionFlag;
	private boolean installedDeviceSectionFlag;
	private boolean networkConnectedFlag;
	private float timezoneOffset;
	private String fromPage;
	private List<Asset> assetList;
	private List<AdditionalPaymentRequest> newAdditionalPaymentRequestList = new ArrayList<AdditionalPaymentRequest>();
	
	private CommonsMultipartFile fileData;		// added for CI5 attachment feature
	private int fileCount;//Attachment
	private String totalFileSize;//Attachment
	private String attachmentFileXML;		
	private List<Attachment> attachmentList;
	
// CI6 Attachment Error Message
	
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	public List<String> getAddressStatusList() {
		return addressStatusList;
	}
	public void setAddressStatusList(List<String> addressStatusList) {
		this.addressStatusList = addressStatusList;
	}
	public String getPartnerAddressListURL() {
		return partnerAddressListURL;
	}
	public void setPartnerAddressListURL(String partnerAddressListURL) {
		this.partnerAddressListURL = partnerAddressListURL;
	}
	public String getTechnicianFullName() {
		return technicianFullName;
	}
	public void setTechnicianFullName(String technicianFullName) {
		this.technicianFullName = technicianFullName;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public List<AccountContact> getTechnicianList() {
		return technicianList;
	}
	public void setTechnicianList(List<AccountContact> technicianList) {
		this.technicianList = technicianList;
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
	public Map<String, String> getPaymentTypes() {
		return paymentTypes;
	}
	public void setPaymentTypes(Map<String, String> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	public Map<String, String> getCarrierList() {
		return carrierList;
	}
	public void setCarrierList(Map<String, String> carrierList) {
		this.carrierList = carrierList;
	}
	public Map<String, String> getSourceList() {
		return sourceList;
	}
	public void setSourceList(Map<String, String> sourceList) {
		this.sourceList = sourceList;
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
	public String getActivityNoteListXML() {
		return activityNoteListXML;
	}
	public void setActivityNoteListXML(String activityNoteListXML) {
		this.activityNoteListXML = activityNoteListXML;
	}
	public String getPartsAndToolsListXML() {
		return partsAndToolsListXML;
	}
	public void setPartsAndToolsListXML(String partsAndToolsListXML) {
		this.partsAndToolsListXML = partsAndToolsListXML;
	}
	public String getAdditionalPaymentRequestListXML() {
		return additionalPaymentRequestListXML;
	}
	public void setAdditionalPaymentRequestListXML(
			String additionalPaymentRequestListXML) {
		this.additionalPaymentRequestListXML = additionalPaymentRequestListXML;
	}
	public boolean isAllowAdditionalPaymentRequestFlag() {
		return allowAdditionalPaymentRequestFlag;
	}
	public void setAllowAdditionalPaymentRequestFlag(
			boolean allowAdditionalPaymentRequestFlag) {
		this.allowAdditionalPaymentRequestFlag = allowAdditionalPaymentRequestFlag;
	}
	public String getPartDebriefListXML() {
		return partDebriefListXML;
	}
	public void setPartDebriefListXML(String partDebriefListXML) {
		this.partDebriefListXML = partDebriefListXML;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public boolean isShowAdditionalPaymentRequestListFlag() {
		return showAdditionalPaymentRequestListFlag;
	}
	public void setShowAdditionalPaymentRequestListFlag(
			boolean showAdditionalPaymentRequestListFlag) {
		this.showAdditionalPaymentRequestListFlag = showAdditionalPaymentRequestListFlag;
	}
	public boolean isRemoveDeviceSectionFlag() {
		return removeDeviceSectionFlag;
	}
	public void setRemoveDeviceSectionFlag(boolean removeDeviceSectionFlag) {
		this.removeDeviceSectionFlag = removeDeviceSectionFlag;
	}
	public boolean isInstalledDeviceSectionFlag() {
		return installedDeviceSectionFlag;
	}
	public void setInstalledDeviceSectionFlag(boolean installedDeviceSectionFlag) {
		this.installedDeviceSectionFlag = installedDeviceSectionFlag;
	}
	public boolean isNetworkConnectedFlag() {
		return networkConnectedFlag;
	}
	public void setNetworkConnectedFlag(boolean networkConnectedFlag) {
		this.networkConnectedFlag = networkConnectedFlag;
	}
	public String getFromPage() {
		return fromPage;
	}
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
	public Map<String, String> getTravelUnitOfMeasureMap() {
		return travelUnitOfMeasureMap;
	}
	public void setTravelUnitOfMeasureMap(Map<String, String> travelUnitOfMeasureMap) {
		this.travelUnitOfMeasureMap = travelUnitOfMeasureMap;
	}
	public Map<String, String> getProblemCodeMap() {
		return problemCodeMap;
	}
	public void setProblemCodeMap(Map<String, String> problemCodeMap) {
		this.problemCodeMap = problemCodeMap;
	}
	public Map<String, String> getResolutionCodeMap() {
		return resolutionCodeMap;
	}
	public void setResolutionCodeMap(Map<String, String> resolutionCodeMap) {
		this.resolutionCodeMap = resolutionCodeMap;
	}
	public Map<String, String> getWorkingConditionaMap() {
		return workingConditionaMap;
	}
	public void setWorkingConditionaMap(Map<String, String> workingConditionaMap) {
		this.workingConditionaMap = workingConditionaMap;
	}
	public float getTimezoneOffset() {
		return timezoneOffset;
	}
	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	public List<Asset> getAssetList() {
		return assetList;
	}
	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}
	public List<AdditionalPaymentRequest> getNewAdditionalPaymentRequestList() {
		return newAdditionalPaymentRequestList;
	}
	public void setNewAdditionalPaymentRequestList(
			List<AdditionalPaymentRequest> newAdditionalPaymentRequestList) {
		this.newAdditionalPaymentRequestList = newAdditionalPaymentRequestList;
	}

}
