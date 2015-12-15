/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CatalogDetailPageForm.java
 * Package     		: com.lexmark.services.form
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro		2013 		2.1             Initial Version
 * 
 */

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
 * This class is used for catalog section for OrderSuppliesCatalogController
 * @version 2.1
 * @author wipro
 *
 */
public class CatalogDetailPageForm extends BaseForm implements Serializable {

	/**
	 * variable declaration
	 */
	private static final long serialVersionUID = 9186616551474163709L;
	/**
	 * variable declaration
	 */
	private List<OrderPart> catalogPartList;
	/**
	 * variable declaration
	 */
	private AccountContact accountContact;
	/**
	 * variable declaration
	 */
	private GenericAddress serviceAddress;
	/**
	 * variable declaration
	 */
	private CommonsMultipartFile fileData;
	/**
	 * variable declaration
	 */
	private ServiceRequest serviceRequest;
	/**
	 * variable declaration
	 */
	private GenericAddress shipToAddress;
	/**
	 * variable declaration
	 */
	private String defaultSpecialInstruction;
	/**
	 * variable declaration
	 */
	private String specialInstruction;
	/**
	 * variable declaration
	 */
	private boolean requestExpediteOrder;
	/**
	 * variable declaration
	 */
	private String requestedDeliveryDate;
	/**
	 * variable declaration
	 */
	private String poNumber;
	/**
	 * variable declaration
	 */
	private List<Attachment> attachmentList;
	/**
	 * variable declaration
	 */
	private List<AttachmentFile> displayAttachmentList;
	/**
	 * variable declaration
	 */
	private String attachmentDescription;
	/**
	 * variable declaration
	 */
	private int fileCount;
	/**
	 * variable declaration
	 */
	private String pageSubmitType;
	/**
	 * variable declaration
	 */
	private String maxPartsToBeOrdered;
	/**
	 * variable declaration
	 */
	private boolean addressFlag;
	/**
	 * variable declaration
	 */
	private String relatedServiceRequestedNumber;
	/**
	 * variable declaration
	 */
	private String relatedServiceRequestedRowId;
	/**
	 * variable declaration
	 */
	private String listOfFileTypes;
	/**
	 * variable declaration
	 */
	private String attMaxCount;
	
	/*Added for JAn release for Request Expedite order
	 * This field is set if Expedite order is allowed for 
	 * this request or not on this basis request expedite
	 * checkbox will be shown or not
	 * */
	/**
	 * variable declaration
	 */
	private boolean expediteOrderAllowed;
	/*Added for MPS 2.1 Wave 1 changes*/
	/**
	 * variable declaration
	 */
	private String subTotal;
	/**
	 * variable declaration
	 */
	private String tax;
	/**
	 * variable declaration
	 */
	private String totalAmt;
	/**
	 * variable declaration
	 */
	private GenericAddress billToAddress;
	/**
	 * variable declaration
	 */
	private String creditCardToken;
	/**
	 * variable declaration
	 */
	private String creditCardEncryptedNo;
	/**
	 * variable declaration
	 */
	private String creditCardExpirationDate;
	/**
	 * variable declaration
	 */
	private String creditCardType;
	/**
	 * variable declaration
	 */
	private String cardHoldername;
	/**
	 * variable declaration
	 */
	private String creditFlag;
	/**
	 * variable declaration
	 */
	private String transactionId;
	/**
	 * variable declaration
	 */
	private String selectedPaymentType;
	/**
	 * variable declaration
	 */
	private String paymentMethod;
	/*End Add*/
	

	/**
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
	 * @return ServiceRequest 
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
	 * @return List 
	 */
	public List<OrderPart> getCatalogPartList() {
		return catalogPartList;
	}
	/**
	 * 
	 * @param catalogPartList 
	 */
	public void setCatalogPartList(List<OrderPart> catalogPartList) {
		this.catalogPartList = catalogPartList;
	}
	/**
	 * 
	 * @return AccountContact 
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
	 * @return GenericAddress 
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
	 * @return GenericAddress 
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
	 * @return CommonMultipartFile 
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
	 * @return String 
	 */
	public String getRelatedServiceRequestedNumber() {
		return relatedServiceRequestedNumber;
	}
	/**
	 * @param relatedServiceRequestedNumber the relatedServiceRequestedNumber to set 
	 */
	public void setRelatedServiceRequestedNumber(String relatedServiceRequestedNumber) {
		this.relatedServiceRequestedNumber = relatedServiceRequestedNumber;
	}
	/**
	 * @return String 
	 */
	public String getRelatedServiceRequestedRowId() {
		return relatedServiceRequestedRowId;
	}
	/**
	 * @param relatedServiceRequestedRowId the relatedServiceRequestedRowId to set 
	 */
	public void setRelatedServiceRequestedRowId(String relatedServiceRequestedRowId) {
		this.relatedServiceRequestedRowId = relatedServiceRequestedRowId;
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
	 * @param expediteOrderAllowed 
	 */
	public void setExpediteOrderAllowed(boolean expediteOrderAllowed) {
		this.expediteOrderAllowed = expediteOrderAllowed;
	}
	/**
	 * 
	 * @return Boolean  
	 */
	public boolean isExpediteOrderAllowed() {
		return expediteOrderAllowed;
	}
	/**
	 * 
	 * @return GenericAddress 
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
	 * 
	 * @return String 
	 */
	public String getSelectedPaymentType() {
		return selectedPaymentType;
	}
	/**
	 * 
	 * @param selectedPaymentType  
	 */
	public void setSelectedPaymentType(String selectedPaymentType) {
		this.selectedPaymentType = selectedPaymentType;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * 
	 * @param paymentMethod  
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	
	
}
