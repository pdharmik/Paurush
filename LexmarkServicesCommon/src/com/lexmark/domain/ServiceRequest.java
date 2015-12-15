package com.lexmark.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lexmark.service.impl.real.domain.SupplyRequestOrderedPartsDo;
import com.lexmark.service.impl.real.domain.SupplyRequestRecommendedPartsDo;

public class ServiceRequest implements Serializable {
	private static final long serialVersionUID = -5879728134501611176L;

	private String customerReferenceId;
	private String orderSource;
	private Date requestedEffectiveDate;
	private String costCenter;
	private Boolean expediteOrder;
	private ListOfValues area;
	private ListOfValues subArea;
	private String notes;//siebel ?
	private String poNumber;
	private String requestType;
	private String serviceRequestNumber;
	private Date serviceRequestDate;
	private Date serviceRequestEndDate;
	private Asset asset;
	private String serviceRequestStatus;
	private Date serviceRequestStatusDate;
	private AccountContact requestor;
	private String otherRequestedService;
	private List<EntitlementServiceDetail> selectedServiceDetails;
	private AccountContact primaryContact;
	private AccountContact secondaryContact;
	public AccountContact getMapsRequestContact() {
		return mapsRequestContact;
	}

	public void setMapsRequestContact(AccountContact mapsRequestContact) {
		this.mapsRequestContact = mapsRequestContact;
	}

	private AccountContact mapsRequestContact;
	private GenericAddress serviceAddress;
	private String problemDescription;
	private String referenceNumber;
	private String optionExchangeOtherDescription;
	private Asset assetShipped;
	private String id;
	private List<ServiceRequestActivity> activitywebUpdateActivities;
	private List<ServiceRequestActivity> emailActivities;
	private List<ServiceRequestActivity> servicewebUpdateActivities;
	private List<ServiceRequestOrderLineItem> shipmentOrderLines;
	private List<ServiceRequestOrderLineItem> returnOrderLines;
	private List<ServiceRequestOrderLineItem> pendingShipments;
	private String serviceActivityStatus;
	private ListOfValues serviceRequestType;
	private ListOfValues statusType;
	private String serviceRequestor;
	private String customerReferenceNumber;
	private String contractType;
	private List<Part> parts;
	private List<Part> cancelledParts;
	private String addtnlDescription;
 	private String eta;
	private String helpdeskReferenceNumber;
    private String resolutionCode;	// added by nelson for CI5
    private String accountName;
    private List<Attachment> attachments; // added 21.05.2012
	private List<PageCounts> pageCounts;
	// new fields to be added
	private GenericAddress installAddress;
	private AccountContact massUploadContact;
	
	private String massUploadDebriefStatus;
	private Integer massUploadserviceProviderReferenceNum;
	private String massUploadTechnicianName;
	private String webOrderNumber;
	private String serialNumber;
	private String shipToDefault;
	private String fccCode;
	private String descriptionLocalLang;
	private List<Part> recommendedParts;
	private List<Part> orderedParts;
	
	//MADC requests
	private Date dateCreated;
	private String customerDeviceTag;
	private String productModel;
	private String addressName;
	private String addressLine1;
	private String houseNumber;
	private Date serviceRequestDateETA;
	
	
	public AccountContact getMassUploadContact() {
		return massUploadContact;
	}

	public void setMassUploadContact(AccountContact massUploadContact) {
		this.massUploadContact = massUploadContact;
	}

	private String coveredService;


	

	/*****MPS Offshore team comments*********/
	/******customer reference number field named custRefNumber is already used by SW1*/
		
	private String custRefNumber;
	
	private String serviceRequestETA;
	private String serviceRequestSLA;
	// dwijen added End

	private String defaultSpecialInstructions;
	private String specialInstructions;
	private String accountId;
	private String accountCountry;
	private BigDecimal itemSubTotalBeforeTax;
	private BigDecimal tax;
	private BigDecimal totalAmount;
	private String creditCardToken;
	private String creditCardType;
	
	private String billingModel;
    private boolean poNumberFlag;
    private boolean creditNumberFlag;
    private String soldToNumber;
    
    
    //Added for PartnerPortal Hardware debrief
    private String statusDetail;
	private AccountContact technicianContact;
	private String responseMetric;
	private Date customerRequestedResponseDate;
	private AccountContact primarySuppliesContact;
	private AccountContact secondarySuppliesContact;

	private String respondWithin;
	private Date resolveWithin;
	private Date actualStartDate;
	private Date actualEndDate;
	private String comments;
	private String travelDistance;
	private String travelDistanceUnitOfMeasure;
	private String travelDuration;
	private List<AccountContact> contactInfoForDevice;
	private String projectName;
	private String projectPhase;
	private boolean project;
	//Ends addition for Hardware debrief
	


	//OPS
	private String agreementName;
	public boolean isProject() {
		return project;
	}

	public void setProject(boolean project) {
		this.project = project;
	}

	private String agreementNumber;
	private String customerRequestDateTime;
	private String committedDateTime;
	private String requestStatus;
	private String subStatus;
	private String severity;
	private List<Order> orders;
	
	private String currency;
	private String lbsDeviceinfo;
	private String lbsLexmarkInstall;
	
	public String getLbsDeviceinfo() {
		return lbsDeviceinfo;
	}

	public void setLbsDeviceinfo(String lbsDeviceinfo) {
		this.lbsDeviceinfo = lbsDeviceinfo;
	}

	public String getLbsLexmarkInstall() {
		return lbsLexmarkInstall;
	}

	public void setLbsLexmarkInstall(String lbsLexmarkInstall) {
		this.lbsLexmarkInstall = lbsLexmarkInstall;
	}
	
	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getHelpdeskReferenceNumber() {
		return helpdeskReferenceNumber;
	}

	public void setHelpdeskReferenceNumber(String helpdeskReferenceNumber) {
		this.helpdeskReferenceNumber = helpdeskReferenceNumber;
	}

	public String getServiceRequestSLA() {

		return serviceRequestSLA;
	}

	public void setServiceRequestETA(String serviceRequestETA) {
		this.serviceRequestETA = serviceRequestETA;
	}

	public void setServiceRequestSLA(String serviceRequestSLA) {
		this.serviceRequestSLA = serviceRequestSLA;
	}

	public String getServiceRequestETA() {
		return serviceRequestETA;
	}

	public void setCustRefNumber(String custRefNumber) {
		this.custRefNumber = custRefNumber;
	}

	public String getCustRefNumber() {
		return custRefNumber;
	}
	public String getServiceActivityStatus() {
		return serviceActivityStatus;
	}

	public void setServiceActivityStatus(String serviceActivityStatus) {
		this.serviceActivityStatus = serviceActivityStatus;
	}

	public Date getServiceRequestStatusDate() {
		return serviceRequestStatusDate;
	}

	public void setServiceRequestStatusDate(Date serviceRequestStatusDate) {
		this.serviceRequestStatusDate = serviceRequestStatusDate;
	}

	public List<ServiceRequestActivity> getActivitywebUpdateActivities() {
		return activitywebUpdateActivities;
	}

	public void setActivitywebUpdateActivities(List<ServiceRequestActivity> activitywebUpdateActivities) {

		this.activitywebUpdateActivities = activitywebUpdateActivities;
	}

	public List<ServiceRequestActivity> getServicewebUpdateActivities() {
		return servicewebUpdateActivities;
	}

	public void setServicewebUpdateActivities(List<ServiceRequestActivity> servicewebUpdateActivities) {

		this.servicewebUpdateActivities = servicewebUpdateActivities;
	}

	public List<ServiceRequestOrderLineItem> getShipmentOrderLines() {
		return shipmentOrderLines;
	}

	public void setShipmentOrderLines(List<ServiceRequestOrderLineItem> shipmentOrderLines) {

		this.shipmentOrderLines = shipmentOrderLines;
	}

	public List<ServiceRequestOrderLineItem> getReturnOrderLines() {
		return returnOrderLines;
	}

	public void setReturnOrderLines(List<ServiceRequestOrderLineItem> returnOrderLines) {

		this.returnOrderLines = returnOrderLines;
	}
	
	public List<ServiceRequestOrderLineItem> getPendingShipments() {
        return pendingShipments;
    }

    public void setPendingShipments(List<ServiceRequestOrderLineItem> pendingShipments) {
        this.pendingShipments = pendingShipments;
    }

    public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}

	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}

	public void setServiceRequestDate(Date serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}

	public Date getServiceRequestDate() {
		return serviceRequestDate;
	}

	public Date getServiceRequestEndDate() {
		return serviceRequestEndDate;
	}

	public void setServiceRequestEndDate(Date serviceRequestEndDate) {
		this.serviceRequestEndDate = serviceRequestEndDate;

	}

	public String getServiceRequestStatus() {
		return serviceRequestStatus;
	}

	public void setServiceRequestStatus(String serviceRequestStatus) {
		this.serviceRequestStatus = serviceRequestStatus;
	}

	public String getOtherRequestedService() {
		return otherRequestedService;
	}

	public void setOtherRequestedService(String otherRequestedService) {
		this.otherRequestedService = otherRequestedService;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getOptionExchangeOtherDescription() {
		return optionExchangeOtherDescription;
	}

	public void setOptionExchangeOtherDescription(String optionExchangeOtherDescription) {

		this.optionExchangeOtherDescription = optionExchangeOtherDescription;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public AccountContact getRequestor() {
		return requestor;
	}

	public void setRequestor(AccountContact requestor) {
		this.requestor = requestor;
	}

	public List<EntitlementServiceDetail> getSelectedServiceDetails() {
		return selectedServiceDetails;
	}

	public void setSelectedServiceDetails(List<EntitlementServiceDetail> selectedServiceDetails) {

		this.selectedServiceDetails = selectedServiceDetails;
	}

	public AccountContact getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(AccountContact primaryContact) {
		this.primaryContact = primaryContact;
	}

	public List<ServiceRequestActivity> getEmailActivities() {
		return emailActivities;
	}

	public void setEmailActivities(List<ServiceRequestActivity> emailActivities) {
		this.emailActivities = emailActivities;
	}

	public AccountContact getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(AccountContact secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public GenericAddress getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(GenericAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public Asset getAssetShipped() {
		return assetShipped;
	}

	public void setAssetShipped(Asset assetShipped) {
		this.assetShipped = assetShipped;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public ListOfValues getServiceRequestType() {
		return serviceRequestType;
	}

	public void setServiceRequestType(ListOfValues serviceRequestType) {
		this.serviceRequestType = serviceRequestType;
	}

	public String getServiceRequestor() {
		return serviceRequestor;
	}

	public void setServiceRequestor(String serviceRequestor) {
		this.serviceRequestor = serviceRequestor;
	}

	public void setCustomerReferenceNumber(String customerReferenceNumber) {
		this.customerReferenceNumber = customerReferenceNumber;
	}

	public String getCustomerReferenceNumber() {
		return customerReferenceNumber;
	}

//    added by nelson for CI5
	public void setResolutionCode(String resolutionCode) {
		this.resolutionCode = resolutionCode;
	}
	public String getPoNumber() {
		return poNumber;
	}




	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getResolutionCode() {
		if(resolutionCode == null || "".equals(resolutionCode)) {
			return "";
		} else {
			return resolutionCode;
		}
	}
//	end of addition by nelosn for CI5

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getAddtnlDescription() {
		return addtnlDescription;
	}

	public void setAddtnlDescription(String addtnlDescription) {
		this.addtnlDescription = addtnlDescription;
	}

	public String getCustomerReferenceId() {
		return customerReferenceId;
	}

	public void setCustomerReferenceId(String customerReferenceId) {
		this.customerReferenceId = customerReferenceId;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractType() {
		return contractType;
	}

	public ListOfValues getStatusType() {
		return statusType;
	}

	public void setStatusType(ListOfValues statusType) {
		this.statusType = statusType;
	}

	
	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public void setArea(ListOfValues area) {
		this.area = area;
	}

	public ListOfValues getArea()
	{
		return area;
	}
	public void setSubArea(ListOfValues subArea) {
		this.subArea = subArea;
	}
	
	public ListOfValues getSubArea()
	{
		return subArea;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setRequestedEffectiveDate(Date requestedEffectiveDate) {
		this.requestedEffectiveDate = requestedEffectiveDate;
	}

	public Date getRequestedEffectiveDate() {
		return requestedEffectiveDate;
	}

	public Boolean getExpediteOrder() {
		return expediteOrder;
	}

	public void setExpediteOrder(Boolean expediteOrder) {
		this.expediteOrder = expediteOrder;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setPageCounts(List<PageCounts> pageCounts) {
		this.pageCounts = pageCounts;
	}

	public List<PageCounts> getPageCounts() {
		return pageCounts;
	}

	public String getDefaultSpecialInstructions() {
		return defaultSpecialInstructions;
	}

	public void setDefaultSpecialInstructions(String defaultSpecialInstructions) {
		this.defaultSpecialInstructions = defaultSpecialInstructions;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setCancelledParts(List<Part> cancelledParts) {
		this.cancelledParts = cancelledParts;
	}

	public List<Part> getCancelledParts() {
		return cancelledParts;
	}

	public void setAccountCountry(String accountCountry) {
		this.accountCountry = accountCountry;
	}

	public String getAccountCountry() {
		return accountCountry;
	}

	public BigDecimal getItemSubTotalBeforeTax() {
		return itemSubTotalBeforeTax;
	}

	public void setItemSubTotalBeforeTax(BigDecimal itemSubTotalBeforeTax) {
		this.itemSubTotalBeforeTax = itemSubTotalBeforeTax;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCreditCardToken() {
		return creditCardToken;
	}

	public void setCreditCardToken(String creditCardToken) {
		this.creditCardToken = creditCardToken;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getBillingModel() {
		return billingModel;
	}

	public void setBillingModel(String billingModel) {
		this.billingModel = billingModel;
	}

	public boolean isPoNumberFlag() {
		return poNumberFlag;
	}

	public void setPoNumberFlag(boolean poNumberFlag) {
		this.poNumberFlag = poNumberFlag;
	}

	public boolean isCreditNumberFlag() {
		return creditNumberFlag;
	}

	public void setCreditNumberFlag(boolean creditNumberFlag) {
		this.creditNumberFlag = creditNumberFlag;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setTechnicianContact(AccountContact technicianContact) {
		this.technicianContact = technicianContact;
	}

	public AccountContact getTechnicianContact() {
		return technicianContact;
	}

	public void setResponseMetric(String responseMetric) {
		this.responseMetric = responseMetric;
	}

	public String getResponseMetric() {
		return responseMetric;
	}

	public void setCustomerRequestedResponseDate(
			Date customerRequestedResponseDate) {
		this.customerRequestedResponseDate = customerRequestedResponseDate;
	}

	public Date getCustomerRequestedResponseDate() {
		return customerRequestedResponseDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setRespondWithin(String respondWithin) {
		this.respondWithin = respondWithin;
	}

	public String getRespondWithin() {
		return respondWithin;
	}

	public void setResolveWithin(Date resolveWithin) {
		this.resolveWithin = resolveWithin;
	}

	public Date getResolveWithin() {
		return resolveWithin;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return comments;
	}

	public void setPrimarySuppliesContact(AccountContact primarySuppliesContact) {
		this.primarySuppliesContact = primarySuppliesContact;
	}

	public AccountContact getPrimarySuppliesContact() {
		return primarySuppliesContact;
	}

	public void setSecondarySuppliesContact(AccountContact secondarySuppliesContact) {
		this.secondarySuppliesContact = secondarySuppliesContact;
	}

	public AccountContact getSecondarySuppliesContact() {
		return secondarySuppliesContact;
	}

	/**
	 * New field to be added
	 */
	public GenericAddress getInstallAddress() {
		return installAddress;
	}

	public void setInstallAddress(GenericAddress installAddress) {
		this.installAddress = installAddress;
	}

	public void setTravelDistance(String travelDistance) {
		this.travelDistance = travelDistance;
	}

	public String getTravelDistance() {
		return travelDistance;
	}

	public void setTravelDistanceUnitOfMeasure(
			String travelDistanceUnitOfMeasure) {
		this.travelDistanceUnitOfMeasure = travelDistanceUnitOfMeasure;
	}

	public String getTravelDistanceUnitOfMeasure() {
		return travelDistanceUnitOfMeasure;
	}

	public void setTravelDuration(String travelDuration) {
		this.travelDuration = travelDuration;
	}

	public String getTravelDuration() {
		return travelDuration;
	}

	public void setContactInfoForDevice(List<AccountContact> contactInfoForDevice) {
		this.contactInfoForDevice = contactInfoForDevice;
	}

	public List<AccountContact> getContactInfoForDevice() {
		return contactInfoForDevice;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectPhase(String projectPhase) {
		this.projectPhase = projectPhase;
	}

	public String getProjectPhase() {
		return projectPhase;
	}

	public String getCoveredService() {
		return coveredService;
	}

	public void setCoveredService(String coveredService) {
		this.coveredService = coveredService;
	}
	
	public String getMassUploadDebriefStatus() {
		return massUploadDebriefStatus;
	}

	public void setMassUploadDebriefStatus(String massUploadDebriefStatus) {
		this.massUploadDebriefStatus = massUploadDebriefStatus;
	}

	public Integer getMassUploadserviceProviderReferenceNum() {
		return massUploadserviceProviderReferenceNum;
	}

	public void setMassUploadserviceProviderReferenceNum(
			Integer massUploadserviceProviderReferenceNum) {
		this.massUploadserviceProviderReferenceNum = massUploadserviceProviderReferenceNum;
	}

	public String getMassUploadTechnicianName() {
		return massUploadTechnicianName;
	}

	public void setMassUploadTechnicianName(String massUploadTechnicianName) {
		this.massUploadTechnicianName = massUploadTechnicianName;
	}

	public String getWebOrderNumber() {
		return webOrderNumber;
	}

	public void setWebOrderNumber(String webOrderNumber) {
		this.webOrderNumber = webOrderNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getShipToDefault() {
		return shipToDefault;
	}

	public void setShipToDefault(String shipToDefault) {
		this.shipToDefault = shipToDefault;
	}

	public String getFccCode() {
		return fccCode;
	}

	public void setFccCode(String fccCode) {
		this.fccCode = fccCode;
	}

	public String getDescriptionLocalLang() {
		return descriptionLocalLang;
	}

	public void setDescriptionLocalLang(String descriptionLocalLang) {
		this.descriptionLocalLang = descriptionLocalLang;
	}

	public List<Part> getRecommendedParts() {
		return recommendedParts;
	}

	public void setRecommendedParts(
			List<Part> recommendedParts) {
		this.recommendedParts = recommendedParts;
	}
	

	public List<Part> getOrderedParts() {
		return orderedParts;
	}

	public void setOrderedParts(List<Part> orderedParts) {
		this.orderedParts = orderedParts;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getCustomerDeviceTag() {
		return customerDeviceTag;
	}

	public void setCustomerDeviceTag(String customerDeviceTag) {
		this.customerDeviceTag = customerDeviceTag;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public String getCustomerRequestDateTime() {
		return customerRequestDateTime;
	}

	public void setCustomerRequestDateTime(String customerRequestDateTime) {
		this.customerRequestDateTime = customerRequestDateTime;
	}

	public String getCommittedDateTime() {
		return committedDateTime;
	}

	public void setCommittedDateTime(String committedDateTime) {
		this.committedDateTime = committedDateTime;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * @return the serviceRequestDateETA
	 */
	public Date getServiceRequestDateETA() {
		return serviceRequestDateETA;
	}

	/**
	 * @param serviceRequestDateETA the serviceRequestDateETA to set
	 */
	public void setServiceRequestDateETA(Date serviceRequestDateETA) {
		this.serviceRequestDateETA = serviceRequestDateETA;
	}

}
