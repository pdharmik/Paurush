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
	public String getBackToMap() {
		return backToMap;
	}

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
	public String getFleetManagementFlag() {
		return fleetManagementFlag;
	}

	public void setFleetManagementFlag(String fleetManagementFlag) {
		this.fleetManagementFlag = fleetManagementFlag;
	}

	public AccountContact getLoginAccountContact() {
		return loginAccountContact;
	}

	public void setLoginAccountContact(AccountContact loginAccountContact) {
		this.loginAccountContact = loginAccountContact;
	}

	public String getHostingPortletName() {
		return hostingPortletName;
	}

	public void setHostingPortletName(String hostingPortletName) {
		this.hostingPortletName = hostingPortletName;
	}

	public String getOnsiteRepair() {
		return onsiteRepair;
	}

	public void setOnsiteRepair(String onsiteRepair) {
		this.onsiteRepair = onsiteRepair;
	}

	public String getOnsiteExchangeOfDevice() {
		return onsiteExchangeOfDevice;
	}

	public void setOnsiteExchangeOfDevice(String onsiteExchangeOfDevice) {
		this.onsiteExchangeOfDevice = onsiteExchangeOfDevice;
	}

	public String getExchangeOfDevice() {
		return exchangeOfDevice;
	}

	public void setExchangeOfDevice(String exchangeOfDevice) {
		this.exchangeOfDevice = exchangeOfDevice;
	}

	public String getExchangeOfOption() {
		return exchangeOfOption;
	}

	public void setExchangeOfOption(String exchangeOfOption) {
		this.exchangeOfOption = exchangeOfOption;
	}

	public String getServiceAddressListURL() {
		return serviceAddressListURL;
	}

	public void setServiceAddressListURL(String serviceAddressListURL) {
		this.serviceAddressListURL = serviceAddressListURL;
	}

	public String getContactListURL() {
		return contactListURL;
	}

	public void setContactListURL(String contactListURL) {
		this.contactListURL = contactListURL;
	}
	
	public String getSecContactListURL() {
		return secContactListURL;
	}

	public void setSecContactListURL(String secContactListURL) {
		this.secContactListURL = secContactListURL;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public String[] getSelectedServiceDetails() {
		return selectedServiceDetails;
	}

	public void setSelectedServiceDetails(String[] selectedServiceDetails) {
		this.selectedServiceDetails = selectedServiceDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
//	added for breakfix MPS
	
	public boolean isDraftFlag() {
		return draftFlag;
	}
	
	public void setDraftFlag(boolean draftFlag) {
		this.draftFlag = draftFlag;
	}
	
	public boolean setDraftFlag() {
		return isDraftFlag();
	}
	
	public void setAttachmentFileMap(Map<String, FileObject> attachmentFileMap) {
		this.attachmentFileMap = attachmentFileMap;
	}
	
	public Map<String, FileObject> getAttachmentFileMap() {
		return attachmentFileMap;
	}
//	end of addition for breakfix MPS
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}

	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}

	public String getAttMaxCount() {
		return attMaxCount;
	}

	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}

	public String getAttachmentDescription() {
		return attachmentDescription;
	}

	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}

	public void setPrinterErrorCode(String printerErrorCode) {
		this.printerErrorCode = printerErrorCode;
	}

	public String getPrinterErrorCode() {
		return printerErrorCode;
	}
}
