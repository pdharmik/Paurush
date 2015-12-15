package com.lexmark.contract;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import com.lexmark.contract.api.ContractBase;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;

public class CreateHardwareRequestContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = -1222866826788365616L;
	private ServiceRequest serviceRequest; 
	private String contactId;
	private Locale locale;
	private String mdmId;
	private String mdmLevel;
	private String srType;
	private String srArea;
	private String srSubArea;
	private String assetSerialNumber;
	private String printerProductNumber;
	private List<PageCounts> pageCountList;
	private List<Part> assetPartList;
	private List<OrderPart> bundleList;
	private List<OrderPart> accessoriesList;
	private List<OrderPart> suppliesList;
	private GenericAddress shipToAddress;
	private String specialInstruction;
	private String requestedDeliveryDate;
	private String poNumber;
	private String AttachmentNotes;
	private String SRStatus;
	private String userType;
	private String updatedSRNumber;
	private String vendorName;
	private String consumableOrderType;
	private String relatedServiceRequestedNumber; 
	private String relatedServiceRequestedRowId;
	private String creditCardToken;
	private String creditCardEncryptedNo;
	private String creditCardExpirationDate;
	private String creditCardType;
	private String cardHolderName;
	private String transactionId;
	private String installationOnlyFlag;
	private String partsToBeInstalledDescription;
	private String soldToNumber;
	private String billToNumber;
	private String paymentType;
	private String paymentMethod;
	
	
	public String getCreditCardEncryptedNo() {
		return creditCardEncryptedNo;
	}
	public void setCreditCardEncryptedNo(String creditCardEncryptedNo) {
		this.creditCardEncryptedNo = creditCardEncryptedNo;
	}
	public String getCreditCardExpirationDate() {
		return creditCardExpirationDate;
	}
	public void setCreditCardExpirationDate(String creditCardExpirationDate) {
		this.creditCardExpirationDate = creditCardExpirationDate;
	}
	public String getCreditCardType() {
		return creditCardType;
	}
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getSoldToNumber() {
		return soldToNumber;
	}
	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}
	private GenericAddress billToAddress;
	
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getSrType() {
		return srType;
	}
	public void setSrType(String srType) {
		this.srType = srType;
	}
	public String getSrArea() {
		return srArea;
	}
	public void setSrArea(String srArea) {
		this.srArea = srArea;
	}
	public String getSrSubArea() {
		return srSubArea;
	}
	public void setSrSubArea(String srSubArea) {
		this.srSubArea = srSubArea;
	}
	public String getAssetSerialNumber() {
		return assetSerialNumber;
	}
	public void setAssetSerialNumber(String assetSerialNumber) {
		this.assetSerialNumber = assetSerialNumber;
	}
	public String getPrinterProductNumber() {
		return printerProductNumber;
	}
	public void setPrinterProductNumber(String printerProductNumber) {
		this.printerProductNumber = printerProductNumber;
	}
	public List<PageCounts> getPageCountList() {
		return pageCountList;
	}
	public void setPageCountList(List<PageCounts> pageCountList) {
		this.pageCountList = pageCountList;
	}
	public List<Part> getAssetPartList() {
		return assetPartList;
	}
	public void setAssetPartList(List<Part> assetPartList) {
		this.assetPartList = assetPartList;
	}
	
	public List<OrderPart> getBundleList() {
		return bundleList;
	}
	public void setBundleList(List<OrderPart> bundleList) {
		this.bundleList = bundleList;
	}
	public List<OrderPart> getAccessoriesList() {
		return accessoriesList;
	}
	public void setAccessoriesList(List<OrderPart> accessoriesList) {
		this.accessoriesList = accessoriesList;
	}
	public List<OrderPart> getSuppliesList() {
		return suppliesList;
	}
	public void setSuppliesList(List<OrderPart> suppliesList) {
		this.suppliesList = suppliesList;
	}
	public GenericAddress getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(GenericAddress shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	
	public String getRequestedDeliveryDate() {
		return requestedDeliveryDate;
	}
	public void setRequestedDeliveryDate(String requestedDeliveryDate) {
		this.requestedDeliveryDate = requestedDeliveryDate;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getAttachmentNotes() {
		return AttachmentNotes;
	}
	public void setAttachmentNotes(String attachmentNotes) {
		AttachmentNotes = attachmentNotes;
	}
	public String getSRStatus() {
		return SRStatus;
	}
	public void setSRStatus(String sRStatus) {
		SRStatus = sRStatus;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUpdatedSRNumber() {
		return updatedSRNumber;
	}
	public void setUpdatedSRNumber(String updatedSRNumber) {
		this.updatedSRNumber = updatedSRNumber;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getConsumableOrderType() {
		return consumableOrderType;
	}
	public void setConsumableOrderType(String consumableOrderType) {
		this.consumableOrderType = consumableOrderType;
	}
	public String getRelatedServiceRequestedNumber() {
		return relatedServiceRequestedNumber;
	}
	public void setRelatedServiceRequestedNumber(
			String relatedServiceRequestedNumber) {
		this.relatedServiceRequestedNumber = relatedServiceRequestedNumber;
	}
	public String getRelatedServiceRequestedRowId() {
		return relatedServiceRequestedRowId;
	}
	public void setRelatedServiceRequestedRowId(String relatedServiceRequestedRowId) {
		this.relatedServiceRequestedRowId = relatedServiceRequestedRowId;
	}
	public String getInstallationOnlyFlag() {
		return installationOnlyFlag;
	}
	public void setInstallationOnlyFlag(String installationOnlyFlag) {
		this.installationOnlyFlag = installationOnlyFlag;
	}
	public String getPartsToBeInstalledDescription() {
		return partsToBeInstalledDescription;
	}
	public void setPartsToBeInstalledDescription(
			String partsToBeInstalledDescription) {
		this.partsToBeInstalledDescription = partsToBeInstalledDescription;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCreditCardToken() {
		return creditCardToken;
	}
	public void setCreditCardToken(String creditCardToken) {
		this.creditCardToken = creditCardToken;
	}
	public GenericAddress getBillToAddress() {
		return billToAddress;
	}
	public void setBillToAddress(GenericAddress billToAddress) {
		this.billToAddress = billToAddress;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getBillToNumber() {
		return billToNumber;
	}
	public void setBillToNumber(String billToNumber) {
		this.billToNumber = billToNumber;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
}
