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
	//added for LBS1.5
	private String pageFlow;
	private String placementId;
	
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
	public String getCardHoldername() {
		return cardHoldername;
	}
	public void setCardHoldername(String cardHoldername) {
		this.cardHoldername = cardHoldername;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
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
	public List<OrderPart> getBundleList() {
		return bundleList;
	}
	public void setBundleList(List<OrderPart> bundleList) {
		this.bundleList = bundleList;
	}
	public AccountContact getAccountContact() {
		return accountContact;
	}
	public void setAccountContact(AccountContact accountContact) {
		this.accountContact = accountContact;
	}
	public GenericAddress getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(GenericAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public GenericAddress getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(GenericAddress shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	public String getDefaultSpecialInstruction() {
		return defaultSpecialInstruction;
	}
	public void setDefaultSpecialInstruction(String defaultSpecialInstruction) {
		this.defaultSpecialInstruction = defaultSpecialInstruction;
	}
	public String getSpecialInstruction() {
		return specialInstruction;
	}
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	public boolean isRequestExpediteOrder() {
		return requestExpediteOrder;
	}
	public void setRequestExpediteOrder(boolean requestExpediteOrder) {
		this.requestExpediteOrder = requestExpediteOrder;
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
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public List<AttachmentFile> getDisplayAttachmentList() {
		return displayAttachmentList;
	}
	public void setDisplayAttachmentList(List<AttachmentFile> displayAttachmentList) {
		this.displayAttachmentList = displayAttachmentList;
	}
	public String getAttachmentDescription() {
		return attachmentDescription;
	}
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public String getPageSubmitType() {
		return pageSubmitType;
	}
	public void setPageSubmitType(String pageSubmitType) {
		this.pageSubmitType = pageSubmitType;
	}
	public String getMaxPartsToBeOrdered() {
		return maxPartsToBeOrdered;
	}
	public void setMaxPartsToBeOrdered(String maxPartsToBeOrdered) {
		this.maxPartsToBeOrdered = maxPartsToBeOrdered;
	}
	public boolean isAddressFlag() {
		return addressFlag;
	}
	public void setAddressFlag(boolean addressFlag) {
		this.addressFlag = addressFlag;
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
	public String getCreditCardToken() {
		return creditCardToken;
	}
	public void setCreditCardToken(String creditCardToken) {
		this.creditCardToken = creditCardToken;
	}
	public String getCreditFlag() {
		return creditFlag;
	}
	public void setCreditFlag(String creditFlag) {
		this.creditFlag = creditFlag;
	}
	public GenericAddress getBillToAddress() {
		return billToAddress;
	}
	public void setBillToAddress(GenericAddress billToAddress) {
		this.billToAddress = billToAddress;
	}
	public boolean isInstallationOnlyFlag() {
		return installationOnlyFlag;
	}
	public void setInstallationOnlyFlag(boolean installationOnlyFlag) {
		this.installationOnlyFlag = installationOnlyFlag;
	}
	public String getTransactionId() {
		return transactionId;
	}
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
	/**
	 * @return the pageFlow
	 */
	public String getPageFlow() {
		return pageFlow;
	}
	/**
	 * @param pageFlow the pageFlow to set
	 */
	public void setPageFlow(String pageFlow) {
		this.pageFlow = pageFlow;
	}
	/**
	 * @return the placementId
	 */
	public String getPlacementId() {
		return placementId;
	}
	/**
	 * @param placementId the placementId to set
	 */
	public void setPlacementId(String placementId) {
		this.placementId = placementId;
	}

}
