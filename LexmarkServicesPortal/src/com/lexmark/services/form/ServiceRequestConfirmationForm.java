package com.lexmark.services.form;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.ServiceRequest;

public class ServiceRequestConfirmationForm extends BaseForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4073631073632041616L;
	private ServiceRequest serviceRequest;	
	private Asset asset;
	private String serviceAddressListURL;
	private String contactListURL;
	private String secContactListURL;
	private String onsiteRepair;
	private String onsiteExchangeOfDevice;
	private String exchangeOfDevice;
	private String exchangeOfOption;
	private String hostingPortletName;
	private String backToMap;
	/**
	 * 
	 * @return String 
	 */
	public String getBackToMap() {
		return backToMap;
	}
/**
 * 
 * @param backToMap 
 */
	public void setBackToMap(String backToMap) {
		this.backToMap = backToMap;
	}

	// Printer Error Code Added for CI 13.10.17
	private String printerErrorCode;
//	added for breakfix MPS
	
	private boolean draftFlag;
	private Map<String, FileObject> attachmentFileMap = new LinkedHashMap<String, FileObject>();
//	end of addition for breakfix MPS
	
	private AccountContact loginAccountContact;
	private String[] selectedServiceDetails;
	//Added for Attachments--START
	private String listOfFileTypes;
	private String attMaxCount;
	private String attachmentDescription;
	//Added for Attachments--END
	
	private String fleetManagementFlag;
	/**
	 * 
	 * @return String 
	 */
	public String getFleetManagementFlag() {
		return fleetManagementFlag;
	}
/**
 * 
 * @param fleetManagementFlag 
 */
	public void setFleetManagementFlag(String fleetManagementFlag) {
		this.fleetManagementFlag = fleetManagementFlag;
	}
/**
 * 
 * @return loginAccountContact 
 */
	public AccountContact getLoginAccountContact() {
		return loginAccountContact;
	}
/**
 * 
 * @param loginAccountContact 
 */
	public void setLoginAccountContact(AccountContact loginAccountContact) {
		this.loginAccountContact = loginAccountContact;
	}
/**
 * 
 * @return String 
 */
	public String getHostingPortletName() {
		return hostingPortletName;
	}
/**
 *  
 * @param hostingPortletName 
 */
	public void setHostingPortletName(String hostingPortletName) {
		this.hostingPortletName = hostingPortletName;
	}
/**
 * 
 * @return String 
 */
	public String getOnsiteRepair() {
		return onsiteRepair;
	}
/**
 * 
 * @param onsiteRepair 
 */
	public void setOnsiteRepair(String onsiteRepair) {
		this.onsiteRepair = onsiteRepair;
	}
/**
 * 
 * @return String 
 */
	public String getOnsiteExchangeOfDevice() {
		return onsiteExchangeOfDevice;
	}
/**
 * 
 * @param onsiteExchangeOfDevice 
 */
	public void setOnsiteExchangeOfDevice(String onsiteExchangeOfDevice) {
		this.onsiteExchangeOfDevice = onsiteExchangeOfDevice;
	}
/**
 * 
 * @return String 
 */
	public String getExchangeOfDevice() {
		return exchangeOfDevice;
	}
/**
 * 
 * @param exchangeOfDevice 
 */
	public void setExchangeOfDevice(String exchangeOfDevice) {
		this.exchangeOfDevice = exchangeOfDevice;
	}
/**
 * 
 * @return String 
 */
	public String getExchangeOfOption() {
		return exchangeOfOption;
	}
/**
 * 
 * @param exchangeOfOption 
 */
	public void setExchangeOfOption(String exchangeOfOption) {
		this.exchangeOfOption = exchangeOfOption;
	}
/**
 * 
 * @return String 
 */
	public String getServiceAddressListURL() {
		return serviceAddressListURL;
	}
/**
 * 
 * @param serviceAddressListURL 
 */
	public void setServiceAddressListURL(String serviceAddressListURL) {
		this.serviceAddressListURL = serviceAddressListURL;
	}
/**
 * 
 * @return String 
 */
	public String getContactListURL() {
		return contactListURL;
	}
/**
 * 
 * @param contactListURL 
 */
	public void setContactListURL(String contactListURL) {
		this.contactListURL = contactListURL;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSecContactListURL() {
		return secContactListURL;
	}
/**
 * 
 * @param secContactListURL 
 */
	public void setSecContactListURL(String secContactListURL) {
		this.secContactListURL = secContactListURL;
	}
/**
 * 
 * @return Asset 
 */
	public Asset getAsset() {
		return asset;
	}
/**
 * 
 * @param asset 
 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
/**
 * 
 * @return serviceRequest 
 */
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	/**
	 * 
	 * @param serviceRequest 
	 */
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
/**
 * 
 * @return String 
 */
	public String[] getSelectedServiceDetails() {
		return selectedServiceDetails;
	}
/**
 * 
 * @param selectedServiceDetails 
 */
	public void setSelectedServiceDetails(String[] selectedServiceDetails) {
		this.selectedServiceDetails = selectedServiceDetails;
	}
/**
 * 
 * @return Long 
 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
//	added for breakfix MPS
	/**
	 * 
	 * @return Boolean 
	 */
	public boolean isDraftFlag() {
		return draftFlag;
	}
	/**
	 * 
	 * @param draftFlag 
	 */
	public void setDraftFlag(boolean draftFlag) {
		this.draftFlag = draftFlag;
	}
	/**
	 * 
	 * @return Boolean 
	 */
	public boolean setDraftFlag() {
		return isDraftFlag();
	}
	/**
	 * 
	 * @param attachmentFileMap 
	 */
	public void setAttachmentFileMap(Map<String, FileObject> attachmentFileMap) {
		this.attachmentFileMap = attachmentFileMap;
	}
	/**
	 * 
	 * @return Map 
	 */
	public Map<String, FileObject> getAttachmentFileMap() {
		return attachmentFileMap;
	}
//	end of addition for breakfix MPS
	/**
	 * 
	 * @return String 
	 */
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}
/**
 * 
 * @param listOfFileTypes 
 */
	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}
/**
 * 
 * @return String 
 */
	public String getAttMaxCount() {
		return attMaxCount;
	}
/**
 * 
 * @param attMaxCount 
 */
	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}
/**
 * 
 * @return String 
 */
	public String getAttachmentDescription() {
		return attachmentDescription;
	}
/**
 * 
 * @param attachmentDescription 
 */
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
/**
 * 
 * @param printerErrorCode 
 */
	public void setPrinterErrorCode(String printerErrorCode) {
		this.printerErrorCode = printerErrorCode;
	}
/**
 * 
 * @return String  
 */
	public String getPrinterErrorCode() {
		return printerErrorCode;
	}
}
