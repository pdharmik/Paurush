package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.ServiceRequest;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class HardwareDetailPageForm extends BaseForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<OrderPart> accessoriesList;
	private List<OrderPart> suppliesList;
	private List<OrderPart> bundleList;
	private AccountContact accountContact;
	private GenericAddress serviceAddress;
	private CommonsMultipartFile fileData;
	private ServiceRequest serviceRequest;
	private GenericAddress shipToAddress;
	private String defaultSpecialInstruction;
	private String specialInstruction;
	private boolean requestExpediteOrder;
	private String requestedDeliveryDate;
	private String poNumber;
	private String creditCardToken;
	private List<Attachment> attachmentList;
	private List<AttachmentFile> displayAttachmentList;
	private String attachmentDescription;
	private int fileCount;
	private String pageSubmitType;
	private String maxPartsToBeOrdered;
	private boolean addressFlag;
	private String relatedServiceRequestedNumber;
	private String relatedServiceRequestedRowId;
	private String listOfFileTypes;
	private String attMaxCount;
	private String totalAmt;
	private String subTotal;
	private String tax;
	private String creditCardEncryptedNo;
	private String creditCardExpirationDate;
	private String creditCardType;
	private String cardHoldername;
	private String creditFlag;
	private String transactionId;
	private GenericAddress billToAddress;
	private boolean installationOnlyFlag;
	private String paymentMethod;
	//for template flow
	private String templateFileCount;
	private String templateFileLOV;
	private String templateFileName;
	/**
	 * 
	 * @return String 
	 */
	public String getCreditCardEncryptedNo() {
		return creditCardEncryptedNo;
	}
	/**
	 * 
	 * @param creditCardEncryptedNo 
	 */
	public void setCreditCardEncryptedNo(String creditCardEncryptedNo) {
		this.creditCardEncryptedNo = creditCardEncryptedNo;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCreditCardExpirationDate() {
		return creditCardExpirationDate;
	}
	/**
	 * 
	 * @param creditCardExpirationDate 
	 */
	public void setCreditCardExpirationDate(String creditCardExpirationDate) {
		this.creditCardExpirationDate = creditCardExpirationDate;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCreditCardType() {
		return creditCardType;
	}
	/**
	 * 
	 * @param creditCardType 
	 */
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCardHoldername() {
		return cardHoldername;
	}
	/**
	 * 
	 * @param cardHoldername 
	 */
	public void setCardHoldername(String cardHoldername) {
		this.cardHoldername = cardHoldername;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getTotalAmt() {
		return totalAmt;
	}
	/**
	 * 
	 * @param totalAmt 
	 */
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSubTotal() {
		return subTotal;
	}
	/**
	 * 
	 * @param subTotal 
	 */
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getTax() {
		return tax;
	}
	/**
	 * 
	 * @param tax 
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<OrderPart> getAccessoriesList() {
		return accessoriesList;
	}
	/**
	 * 
	 * @param accessoriesList 
	 */
	public void setAccessoriesList(List<OrderPart> accessoriesList) {
		this.accessoriesList = accessoriesList;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<OrderPart> getSuppliesList() {
		return suppliesList;
	}
	/**
	 * 
	 * @param suppliesList 
	 */
	public void setSuppliesList(List<OrderPart> suppliesList) {
		this.suppliesList = suppliesList;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<OrderPart> getBundleList() {
		return bundleList;
	}
	/**
	 * 
	 * @param bundleList 
	 */
	public void setBundleList(List<OrderPart> bundleList) {
		this.bundleList = bundleList;
	}
	/**
	 * 
	 * @return accountContact 
	 */
	public AccountContact getAccountContact() {
		return accountContact;
	}
	/**
	 * 
	 * @param accountContact 
	 */
	public void setAccountContact(AccountContact accountContact) {
		this.accountContact = accountContact;
	}
	/**
	 * 
	 * @return accountContact 
	 */
	public GenericAddress getServiceAddress() {
		return serviceAddress;
	}
	/**
	 * 
	 * @param serviceAddress 
	 */
	public void setServiceAddress(GenericAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	/**
	 * 
	 * @return fileData 
	 */
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	/**
	 * 
	 * @param fileData 
	 */
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
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
	 * @return shipToAddress 
	 */
	public GenericAddress getShipToAddress() {
		return shipToAddress;
	}
	/**
	 * 
	 * @param shipToAddress 
	 */
	public void setShipToAddress(GenericAddress shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getDefaultSpecialInstruction() {
		return defaultSpecialInstruction;
	}
	/**
	 * 
	 * @param defaultSpecialInstruction 
	 */
	public void setDefaultSpecialInstruction(String defaultSpecialInstruction) {
		this.defaultSpecialInstruction = defaultSpecialInstruction;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	/**
	 * 
	 * @param specialInstruction 
	 */
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	/**
	 * 
	 * @return Boolean  
	 */
	public boolean isRequestExpediteOrder() {
		return requestExpediteOrder;
	}
	/**
	 * 
	 * @param requestExpediteOrder 
	 */
	public void setRequestExpediteOrder(boolean requestExpediteOrder) {
		this.requestExpediteOrder = requestExpediteOrder;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getRequestedDeliveryDate() {
		return requestedDeliveryDate;
	}
	/**
	 * 
	 * @param requestedDeliveryDate 
	 */
	
	public void setRequestedDeliveryDate(String requestedDeliveryDate) {
		this.requestedDeliveryDate = requestedDeliveryDate;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getPoNumber() {
		return poNumber;
	}
	/**
	 * 
	 * @param poNumber 
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	/**
	 * 
	 * @param attachmentList 
	 */
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<AttachmentFile> getDisplayAttachmentList() {
		return displayAttachmentList;
	}
	/**
	 * 
	 * @param displayAttachmentList 
	 */
	public void setDisplayAttachmentList(List<AttachmentFile> displayAttachmentList) {
		this.displayAttachmentList = displayAttachmentList;
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
	 * @return Integer 
	 */
	public int getFileCount() {
		return fileCount;
	}
	/**
	 * 
	 * @param fileCount 
	 */
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getPageSubmitType() {
		return pageSubmitType;
	}
	/**
	 * 
	 * @param pageSubmitType 
	 */
	public void setPageSubmitType(String pageSubmitType) {
		this.pageSubmitType = pageSubmitType;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getMaxPartsToBeOrdered() {
		return maxPartsToBeOrdered;
	}
	/**
	 * 
	 * @param maxPartsToBeOrdered 
	 */ 
	public void setMaxPartsToBeOrdered(String maxPartsToBeOrdered) {
		this.maxPartsToBeOrdered = maxPartsToBeOrdered;
	}
	/**
	 * 
	 * @return Boolean 
	 */
	public boolean isAddressFlag() {
		return addressFlag;
	}
	/**
	 * 
	 * @param addressFlag 
	 */
	public void setAddressFlag(boolean addressFlag) {
		this.addressFlag = addressFlag;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getRelatedServiceRequestedNumber() {
		return relatedServiceRequestedNumber;
	}
	/**
	 * 
	 * @param relatedServiceRequestedNumber 
	 */
	public void setRelatedServiceRequestedNumber(
			String relatedServiceRequestedNumber) {
		this.relatedServiceRequestedNumber = relatedServiceRequestedNumber;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getRelatedServiceRequestedRowId() {
		return relatedServiceRequestedRowId;
	}
	/**
	 * 
	 * @param relatedServiceRequestedRowId 
	 */
	public void setRelatedServiceRequestedRowId(String relatedServiceRequestedRowId) {
		this.relatedServiceRequestedRowId = relatedServiceRequestedRowId;
	}
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
	public String getCreditCardToken() {
		return creditCardToken;
	}
	/**
	 * 
	 * @param creditCardToken 
	 */
	public void setCreditCardToken(String creditCardToken) {
		this.creditCardToken = creditCardToken;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCreditFlag() {
		return creditFlag;
	}
	/**
	 * 
	 * @param creditFlag 
	 */
	public void setCreditFlag(String creditFlag) {
		this.creditFlag = creditFlag;
	}
	/**
	 * 
	 * @return billToAddress 
	 */
	public GenericAddress getBillToAddress() {
		return billToAddress;
	}
	/**
	 * 
	 * @param billToAddress 
	 */
	public void setBillToAddress(GenericAddress billToAddress) {
		this.billToAddress = billToAddress;
	}
	/**
	 * 
	 * @return Boolean 
	 */
	public boolean isInstallationOnlyFlag() {
		return installationOnlyFlag;
	}
	/**
	 * 
	 * @param installationOnlyFlag 
	 */
	public void setInstallationOnlyFlag(boolean installationOnlyFlag) {
		this.installationOnlyFlag = installationOnlyFlag;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * 
	 * @param transactionId 
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @param templateFileCount the templateFileCount to set
	 */
	public void setTemplateFileCount(String templateFileCount) {
		this.templateFileCount = templateFileCount;
	}
	/**
	 * @return the templateFileCount
	 */
	public String getTemplateFileCount() {
		return templateFileCount;
	}
	/**
	 * @param templateFileLOV the templateFileLOV to set
	 */
	public void setTemplateFileLOV(String templateFileLOV) {
		this.templateFileLOV = templateFileLOV;
	}
	/**
	 * @return the templateFileLOV
	 */
	public String getTemplateFileLOV() {
		return templateFileLOV;
	}
	/**
	 * @param templateFileName the templateFileName to set
	 */
	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}
	/**
	 * @return the templateFileName
	 */
	public String getTemplateFileName() {
		return templateFileName;
	}
	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
