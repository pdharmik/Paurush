package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class AssetDetailPageForm extends BaseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9186616551474163709L;
	private List<Part> assetPartList;
	private AccountContact accountContact;
	private GenericAddress serviceAddress;
	private Asset asset;
	private CommonsMultipartFile fileData;
	private ServiceRequest serviceRequest;
	private GenericAddress shipToAddress;
	private GenericAddress physicalLocationAddress;
	private String defaultSpecialInstruction;
	private String specialInstruction;
	private boolean requestExpediteOrder;
	private String requestedDeliveryDate;
	private String poNumber;
	private PageCounts pageCounts;
	private List<Attachment> attachmentList;
	private List<AttachmentFile> displayAttachmentList;
	private List<PageCounts> pageCountsList;
	private String attachmentDescription;
	private int fileCount;
	private String pageSubmitType;
	private String maxPartsToBeOrdered;
	private boolean addressFlag;
	private String relatedServiceRequestedNumber;
	private String relatedServiceRequestedRowId;
	private int noOfPart;
	//added 
	private String listOfFileTypes;
	private String attMaxCount;
	private String installDate;
	
	
	/*Added for JAn release for Request Expedite order
	 * This field is set if Expedite order is allowed for 
	 * this request or not on this basis request expedite
	 * checkbox will be shown or not
	 * */
	private boolean expediteOrderAllowed;
	
	/*************MPS Phase 2.1 changes **************/
	
	private GenericAddress billToAddress;
	private String creditCardToken;
	private String creditCardEncryptedNo;
	private String creditCardExpirationDate;
	private String creditCardType;
	private String cardHoldername;
	private String transactionId;
	private String totalPrice;
	private String subTotalPrice;
	private String totalTax;
	private String creditFlag;
	private Map<String, String> paymentTypes;
	private String selectedPaymentType;
	private String selectedPaymentTypeId;
	
	private String showPriceFlag;
	private boolean poNumberFlag;
	private boolean creditNumberFlag;
	
	private boolean finalShowPriceFlag;
	private boolean finalCreditFlag;
	private boolean finalPOFlag;
	private boolean finalTaxCalcFlag;
	private boolean splitterFlag;
	private String salesOrganisation;
	private String agreementId;
	private String contractNumber;
	private boolean dsplPaymentTypeFlag;
	private String paymentMethod;
	private String fleetManagementFlag;
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
	 * @return String 
	 */
	public String getAgreementId() {
		return agreementId;
	}
	/**
	 * 
	 * @param agreementId 
	 */
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getContractNumber() {
		return contractNumber;
	}
	/**
	 * 
	 * @param contractNumber 
	 */
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
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
	 * @return PageCounts 
	 */
	public PageCounts getPageCounts() {
		return pageCounts;
	}
	/**
	 * 
	 * @param pageCounts 
	 */
	public void setPageCounts(PageCounts pageCounts) {
		this.pageCounts = pageCounts;
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
	public List<Part> getAssetPartList() {
		return assetPartList;
	}
	/**
	 * 
	 * @param assetPartList 
	 */
	public void setAssetPartList(List<Part> assetPartList) {
		this.assetPartList = assetPartList;
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
	 * @return GerenicAddress 
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
	 * @return CommonsMultipartFile 
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
	 * @return getFileCount 
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
	 * @return List 
	 */
	public List<PageCounts> getPageCountsList() {
		return pageCountsList;
	}
	/**
	 * 
	 * @param pageCountsList 
	 */
	public void setPageCountsList(List<PageCounts> pageCountsList) {
		this.pageCountsList = pageCountsList;
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
	 * @return GenericAddress
	 */
	public GenericAddress getPhysicalLocationAddress() {
		return physicalLocationAddress;
	}
	/**
	 * 
	 * @param physicalLocationAddress 
	 */
	public void setPhysicalLocationAddress(GenericAddress physicalLocationAddress) {
		this.physicalLocationAddress = physicalLocationAddress;
	}

	/**
	 * @return the relatedServiceRequestedNumber
	 */
	public String getRelatedServiceRequestedNumber() {
		return relatedServiceRequestedNumber;
	}
	/**
	 * @param relatedServiceRequestedNumber the relatedServiceRequestedNumber to set
	 */
	public void setRelatedServiceRequestedNumber(
			String relatedServiceRequestedNumber) {
		this.relatedServiceRequestedNumber = relatedServiceRequestedNumber;
	}
	/**
	 * @return the relatedServiceRequestedRowId
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
	 * @return int 
	 */
	public int getNoOfPart() {
		return noOfPart;
	}
	/**
	 * 
	 * @param noOfPart 
	 */
	public void setNoOfPart(int noOfPart) {
		this.noOfPart = noOfPart;
	}
	/**
	 * 
	 * @return boolean 
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
	public String getInstallDate() {
		return installDate;
	}
	/**
	 * 
	 * @param installDate 
	 */
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
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
	 * @return boolean 
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
	public String getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 
	 * @param totalPrice 
	 */
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSubTotalPrice() {
		return subTotalPrice;
	}
	/**
	 * 
	 * @param subTotalPrice 
	 */
	public void setSubTotalPrice(String subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getTotalTax() {
		return totalTax;
	}
	/**
	 * 
	 * @param totalTax 
	 */
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
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
	
	public String getShowPriceFlag() {
		return showPriceFlag;
	}
	/**
	 * 
	 * @param showPriceFlag 
	 */
	public void setShowPriceFlag(String showPriceFlag) {
		this.showPriceFlag = showPriceFlag;
	}
	/**
	 * 
	 * @return boolean 
	 */
	public boolean isPoNumberFlag() {
		return poNumberFlag;
	}
	/**
	 * 
	 * @param poNumberFlag 
	 */
	public void setPoNumberFlag(boolean poNumberFlag) {
		this.poNumberFlag = poNumberFlag;
	}
	/**
	 * 
	 * @return boolean 
	 */
	public boolean isCreditNumberFlag() {
		return creditNumberFlag;
	}
	/**
	 * 
	 * @param creditNumberFlag 
	 */
	public void setCreditNumberFlag(boolean creditNumberFlag) {
		this.creditNumberFlag = creditNumberFlag;
	}
	/**
	 * 
	 * @return boolean 
	 */
	public boolean isFinalShowPriceFlag() {
		return finalShowPriceFlag;
	}
	/**
	 * 
	 * @param finalShowPriceFlag 
	 */
	public void setFinalShowPriceFlag(boolean finalShowPriceFlag) {
		this.finalShowPriceFlag = finalShowPriceFlag;
	}
	/**
	 * 
	 * @return boolean 
	 */
	public boolean isFinalCreditFlag() {
		return finalCreditFlag;
	}
	/**
	 * 
	 * @param finalCreditFlag 
	 */
	public void setFinalCreditFlag(boolean finalCreditFlag) {
		this.finalCreditFlag = finalCreditFlag;
	}
	/**
	 * 
	 * @return boolean 
	 */
	public boolean isFinalPOFlag() {
		return finalPOFlag;
	}
	/**
	 * 
	 * @param finalPOFlag 
	 */
	public void setFinalPOFlag(boolean finalPOFlag) {
		this.finalPOFlag = finalPOFlag;
	}
	/**
	 * 
	 * @return boolean 
	 */
	public boolean isFinalTaxCalcFlag() {
		return finalTaxCalcFlag;
	}
	/**
	 * 
	 * @param finalTaxCalcFlag 
	 */
	public void setFinalTaxCalcFlag(boolean finalTaxCalcFlag) {
		this.finalTaxCalcFlag = finalTaxCalcFlag;
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
	 * @return boolean 
	 */
	public boolean isSplitterFlag() {
		return splitterFlag;
	}
	/**
	 * 
	 * @param splitterFlag 
	 */
	public void setSplitterFlag(boolean splitterFlag) {
		this.splitterFlag = splitterFlag;
	}
	/**
	 * 
	 * @return Map 
	 */
	public Map<String, String> getPaymentTypes() {
		return paymentTypes;
	}
	/**
	 * 
	 * @param paymentTypes 
	 */
	public void setPaymentTypes(Map<String, String> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSelectedPaymentTypeId() {
		return selectedPaymentTypeId;
	}
	/**
	 * 
	 * @param selectedPaymentTypeId 
	 */
	public void setSelectedPaymentTypeId(String selectedPaymentTypeId) {
		this.selectedPaymentTypeId = selectedPaymentTypeId;
	}
	/**
	 *  
	 * @return String 
	 */
	public String getSalesOrganisation() {
		return salesOrganisation;
	}
	/**
	 * 
	 * @param salesOrganisation 
	 */
	public void setSalesOrganisation(String salesOrganisation) {
		this.salesOrganisation = salesOrganisation;
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
	 * @return boolean 
	 */
	public boolean isDsplPaymentTypeFlag() {
		return dsplPaymentTypeFlag;
	}
	/**
	 * 
	 * @param dsplPaymentTypeFlag 
	 */
	public void setDsplPaymentTypeFlag(boolean dsplPaymentTypeFlag) {
		this.dsplPaymentTypeFlag = dsplPaymentTypeFlag;
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
