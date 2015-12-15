package com.lexmark.form;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.amind.order.domain.Asset;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.Attachment;

public class ClaimDebriefForm extends BaseForm{

	private static final long serialVersionUID = 282042329121932800L;
	private Activity activity;
	private Map<String, String> problemCodeMap;
	private List<AccountContact>  technicianList;
	private Map<String, String> resolutionCodeMap;
	private Map<String, String> workingConditionMap;
	private Map<String, String> partStatusList;
	private Map<String, String> errorCode1List;
	private Map<String, String> paymentTypes;
	private Map<String, String> carrierList;
	private String problemCodeLocalized;
	private String resolutionCodeLocalized;
	private String serviceStartDate;
	private String serviceEndDate;
	private String dateServicedRequested;
	

	//private List<Account> partnerIndirectAccountList;
	private String activityNoteListXML;
	private String partsAndToolsListXML;
	private String additionalPaymentRequestListXML;
	private boolean allowAdditionalPaymentRequestFlag;
	private String partDebriefListXML;
	private String contactId;
	private boolean showAdditionalPaymentRequestListFlag;
	private float timezoneOffset;
	private String fromPage;
	private List<AdditionalPaymentRequest> newAdditionalPaymentRequestList = new ArrayList<AdditionalPaymentRequest>();
	
	//private String requestedPartsListNOXML;
	//private String partnerAddressListURL;
	
	private CommonsMultipartFile fileData;		//  for CI5 attachment feature
	private Map<String, AttachmentFile> fileMap = new LinkedHashMap<String, AttachmentFile>();		//  for CI5 attachment feature
	//for 2098
	// CI5 Attachment
	private List<Attachment> attachmentList;
	private String attachmentFileXML;	
	
	// CI6 Attachment Error Message
	
	private String errorMessage;
	
	//Added BRD 13-10-01
	private String formattedServiceStartDate;
	private String formattedServiceEndDate;
	private String formattedServiceRequestedDate;
	//ends here
	
	public String getDateServicedRequested() {
		return dateServicedRequested;
	}
	public void setDateServicedRequested(String dateServicedRequested) {
		this.dateServicedRequested = dateServicedRequested;
	}	
	public String getErrorMessage() {
		return errorMessage;
	}
	public String getFormattedServiceRequestedDate() {
		return formattedServiceRequestedDate;
	}
	public void setFormattedServiceRequestedDate(
			String formattedServiceRequestedDate) {
		this.formattedServiceRequestedDate = formattedServiceRequestedDate;
	}
	public String getFormattedServiceStartDate() {
		return formattedServiceStartDate;
	}
	public void setFormattedServiceStartDate(String formattedServiceStartDate) {
		this.formattedServiceStartDate = formattedServiceStartDate;
	}
	public String getFormattedServiceEndDate() {
		return formattedServiceEndDate;
	}
	public void setFormattedServiceEndDate(String formattedServiceEndDate) {
		this.formattedServiceEndDate = formattedServiceEndDate;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getAttachmentFileXML() {
		return attachmentFileXML;
	}

	public void setAttachmentFileXML(String attachmentFileXML) {
		this.attachmentFileXML = attachmentFileXML;
	}

	private int fileCount;//Attachment
	public int getFileCount() {
		return fileCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	private String totalFileSize;//Attachment
	// Attachments
	private Map<String, String> travelUnitOfMeasureMap;
	private boolean removeDeviceSectionFlag;
	private boolean installedDeviceSectionFlag;
	private boolean networkConnectedFlag;
	private Map<String, String> workingConditionaMap;
	public Map<String, String> getWorkingConditionaMap() {
		return workingConditionaMap;
	}

	public void setWorkingConditionaMap(Map<String, String> workingConditionaMap) {
		this.workingConditionaMap = workingConditionaMap;
	}

	private List<Asset> assetList;
	
	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	public boolean isNetworkConnectedFlag() {
		return networkConnectedFlag;
	}

	public void setNetworkConnectedFlag(boolean networkConnectedFlag) {
		this.networkConnectedFlag = networkConnectedFlag;
	}

	public Map<String, String> getTravelUnitOfMeasureMap() {
		return travelUnitOfMeasureMap;
	}

	public void setTravelUnitOfMeasureMap(Map<String, String> travelUnitOfMeasureMap) {
		this.travelUnitOfMeasureMap = travelUnitOfMeasureMap;
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
	//end
	
	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public Activity getActivity() {
		return activity;
	}

	public Map<String, String> getCarrierList() {
		return carrierList;
	}

	public void setCarrierList(Map<String, String> carrierList) {
		this.carrierList = carrierList;
	}

	public boolean isShowAdditionalPaymentRequestListFlag() {
		return showAdditionalPaymentRequestListFlag;
	}

	public void setShowAdditionalPaymentRequestListFlag(
			boolean showAdditionalPaymentRequestListFlag) {
		this.showAdditionalPaymentRequestListFlag = showAdditionalPaymentRequestListFlag;
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

	public Map<String, String> getResolutionCodeMap() {
		return resolutionCodeMap;
	}

	public void setResolutionCodeMap(Map<String, String> resolutionCodeMap) {
		this.resolutionCodeMap = resolutionCodeMap;
	}

	public Map<String, String> getWorkingConditionMap() {
		return workingConditionMap;
	}

	public void setWorkingConditionMap(Map<String, String> workingConditionMap) {
		this.workingConditionMap = workingConditionMap;
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

	public String getProblemCodeLocalized() {
		return problemCodeLocalized;
	}

	public void setProblemCodeLocalized(String problemCodeLocalized) {
		this.problemCodeLocalized = problemCodeLocalized;
	}

	public String getResolutionCodeLocalized() {
		return resolutionCodeLocalized;
	}

	public void setResolutionCodeLocalized(String resolutionCodeLocalized) {
		this.resolutionCodeLocalized = resolutionCodeLocalized;
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

	public Map<String, String> getProblemCodeMap() {
		return problemCodeMap;
	}

	public void setProblemCodeMap(Map<String, String> problemCodeMap) {
		this.problemCodeMap = problemCodeMap;
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

	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	public float getTimezoneOffset() {
		return timezoneOffset;
	}

	public List<AdditionalPaymentRequest> getNewAdditionalPaymentRequestList() {
		return newAdditionalPaymentRequestList;
	}

	public void setNewAdditionalPaymentRequestList(
			List<AdditionalPaymentRequest> newAdditionalPaymentRequestList) {
		this.newAdditionalPaymentRequestList = newAdditionalPaymentRequestList;
	}
	
// for attachment feature CI5

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
//	end of addition for CI5 attachment feature
	
}
