package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Activity implements Serializable {
	private static final long serialVersionUID = 813045940290346604L;
	private ServiceRequest serviceRequest ;
	private ListOfValues activityType;
	private ListOfValues activityStatus;
	private ListOfValues activitySubStatus;
	private String addressStatus;
	private Date activityDate;
	private Debrief debrief;
	private Account partnerAccount;
	private Account customerAccount;
	private AccountContact technician;
	private boolean requestLexmarkReviewFlag;
	private ListOfValues actualFailureCode;
	private String reviewComments;
	private GenericAddress shipToAddress;
	private List<PartLineItem> recommendedPartList;
	private List<PartLineItem> additionalPartList;
	private String requestorFirstName;
	private String requestorLastName;
	private String requestorEmail;
	private String requestorWorkPhone;
	private boolean isChildSR;
	
	public List<PartLineItem> getAdditionalPartList() {
		return additionalPartList;
	}
	public void setAdditionalPartList(List<PartLineItem> additionalPartList) {
		this.additionalPartList = additionalPartList;
	}

	private String accountPriority;
	private String severity;
	private String responseMetric;
	private String resolutionMetric;
	private Date responseDate;
	private Date resolutionDate;
	private String accountSpecialHandling;
	private String assetWarningMessage;
	private String serviceProviderAttemptNumber;
	private String serviceActivityWithin30Days;
	private String trackCodes;
	private List<TechnicianInstruction> serviceInstructionList;
	private List<ActivityNote> activityNoteList;
	private List<AdditionalPaymentRequest> additionalPaymentRequestList;
	private Date statusUpdateDate;
	private Date customerRequestedResponseDate;
	private String servicerComments;
	private String activityId;
	private List<PartLineItem> pendingPartList;
	private List<PartLineItem> orderPartList;
	private List<PartLineItem> returnPartList;
	private Date createdDate;
	
	//Added By MPS Offshore Team
	private List<PartLineItem> pendingShipmentPartList;
	private List<PartLineItem> processedPartList;
	private double suppliesFulfillmentFee;
	//End of Addition
	
	private Date customerRequestedDate;
	
	private Date committedDate;
	
	private OfflineModeAttachment offlineModeAttachment;
	private String countType;
	private String pageCount;
	
	private String debriefStatus;
	private String rfvErrors;
	private List<PartnerActivityCustomerProfile> cpFields;
	private boolean createChildSR;
	private String localizedCreateChildSR;//Added for Partner Portal Request TAb Allow Child SR July Release 2014
	private String agencyContactFirstName;
	private String agencyContactLastName;
	private String agencyContacteMail;
	private String materialLineType;
	private Date dispatchDate;														 
	private String parentSRNum;
	private Date expectedStartInterventionDate;
	
	private List<PartLineItem> partsList;
	private List<PartLineItem> debriefPartsList;
	private String claimCoveredService;
	

	public Date getExpectedStartInterventionDate() {
		return expectedStartInterventionDate;
	}
	public void setExpectedStartInterventionDate(
			Date expectedStartInterventionDate) {
		this.expectedStartInterventionDate = expectedStartInterventionDate;
	}
	public Date getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getParentSRNum() {
		return parentSRNum;
	}
	public void setParentSRNum(String parentSRNum) {
		this.parentSRNum = parentSRNum;
	}
	public List<PartnerActivityCustomerProfile> getCpFields() {
		return cpFields;
	}
	public void setCpFields(List<PartnerActivityCustomerProfile> cpFields) {
		this.cpFields = cpFields;
	}
	public String getCountType() {
		return countType;
	}
	public void setCountType(String countType) {
		this.countType = countType;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	//CI-6 Added Start
	private String expedite;
	
	private Date serviceProviderETA;
	
	public String getExpedite() {
		return expedite;
	}
	public void setExpedite(String expedite) {
		this.expedite = expedite;
	}
	//CI-6 Added End

	private String serviceProviderReferenceNumber;
	private String newCustomerAccountFlag; //changed for CI-5 2098
	private boolean repairCompleteFlag;
	private boolean shipPartsToFlag;
	private String serviceSummary;
	private Date estimatedArrivalTime;
	private String serviceProviderStatus;

	private ListOfValues resolutionCode;//
	private GenericAddress serviceAddress;//
	private String additionalProblemDetails;//
	private String otherTechnicianName;//
	private EntitlementServiceDetails selectedServiceDetails ;//
	
	private String eligibleToPay;
	private String payEligiblityOverride;
	private String partnerAgreementName;
	private double laborPayment;
	private double partsPayment;
	private double additionalPayments;
	private double partnerFee;
	private double totalPayment;
	private String paymentServiceType;
	private Payment payment;
	private String newCustomerAddressCombined;
	private String emailAddress;
	private String activityServiceInstructions;
	private String floorIdPredebrief;
	private String buildingIdPredebrief;
	
	private String xCoordinatePreDebrief;
	private String yCoordinatePreDebrief;
	private String latitudePreDebrief;
	private String longitutdePreDebrief;
	private String siteIdPredDebrief;
	private String sitePredDebrief;
	private String zoneIdPredDebrief;
	private String zonePredDebrief;
	
	

	public String getBuildingIdPredebrief() {
		return buildingIdPredebrief;
	}
	public void setBuildingIdPredebrief(String buildingIdPredebrief) {
		this.buildingIdPredebrief = buildingIdPredebrief;
	}
	public String getFloorIdPredebrief() {
		return floorIdPredebrief;
	}
	public void setFloorIdPredebrief(String floorIdPredebrief) {
		this.floorIdPredebrief = floorIdPredebrief;
	}
	public String getxCoordinatePreDebrief() {
		return xCoordinatePreDebrief;
	}
	public void setxCoordinatePreDebrief(String xCoordinatePreDebrief) {
		this.xCoordinatePreDebrief = xCoordinatePreDebrief;
	}
	public String getyCoordinatePreDebrief() {
		return yCoordinatePreDebrief;
	}
	public void setyCoordinatePreDebrief(String yCoordinatePreDebrief) {
		this.yCoordinatePreDebrief = yCoordinatePreDebrief;
	}
	public String getLatitudePreDebrief() {
		return latitudePreDebrief;
	}
	public void setLatitudePreDebrief(String latitudePreDebrief) {
		this.latitudePreDebrief = latitudePreDebrief;
	}
	public String getLongitutdePreDebrief() {
		return longitutdePreDebrief;
	}
	public void setLongitutdePreDebrief(String longitutdePreDebrief) {
		this.longitutdePreDebrief = longitutdePreDebrief;
	}
	public String getSiteIdPredDebrief() {
		return siteIdPredDebrief;
	}
	public void setSiteIdPredDebrief(String siteIdPredDebrief) {
		this.siteIdPredDebrief = siteIdPredDebrief;
	}
	public String getSitePredDebrief() {
		return sitePredDebrief;
	}
	public void setSitePredDebrief(String sitePredDebrief) {
		this.sitePredDebrief = sitePredDebrief;
	}
	public String getZoneIdPredDebrief() {
		return zoneIdPredDebrief;
	}
	public void setZoneIdPredDebrief(String zoneIdPredDebrief) {
		this.zoneIdPredDebrief = zoneIdPredDebrief;
	}
	public String getZonePredDebrief() {
		return zonePredDebrief;
	}
	public void setZonePredDebrief(String zonePredDebrief) {
		this.zonePredDebrief = zonePredDebrief;
	}

	private List<Attachment> attachmentList;//Attachment
	// Page Count
	 private String pageCountAll;
	 
	 private Date expectedCompletionDate;
	
	public String getPageCountAll() {
		return pageCountAll;
	}
	public void setPageCountAll(String pageCountAll) {
		this.pageCountAll = pageCountAll;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	//added by ragesree - 2098 start
	private boolean exchangeFlag;           
	public boolean isExchangeFlag() {
		return exchangeFlag;
	}
	public void setExchangeFlag(boolean exchangeFlag) {
		this.exchangeFlag = exchangeFlag;
	}
	private String serviceType;
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	//added by ragesree - 2098 end
	public String getActivityServiceInstructions() {
		return activityServiceInstructions;
	}
	public void setActivityServiceInstructions(String activityServiceInstructions) {
		this.activityServiceInstructions = activityServiceInstructions;
	}
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public ListOfValues getActivityType() {
		return activityType;
	}
	public void setActivityType(ListOfValues activityType) {
		this.activityType = activityType;
	}
	public ListOfValues getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(ListOfValues activityStatus) {
		this.activityStatus = activityStatus;
	}
	public ListOfValues getActivitySubStatus() {
		return activitySubStatus;
	}
	public void setActivitySubStatus(ListOfValues activitySubStatus) {
		this.activitySubStatus = activitySubStatus;
	}
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	public Account getPartnerAccount() {
		return partnerAccount;
	}
	public void setPartnerAccount(Account partnerAccount) {
		this.partnerAccount = partnerAccount;
	}
	public Account getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(Account customerAccount) {
		this.customerAccount = customerAccount;
	}
	public AccountContact getTechnician() {
		return technician;
	}
	public void setTechnician(AccountContact technician) {
		this.technician = technician;
	}
	public boolean isRequestLexmarkReviewFlag() {
		return requestLexmarkReviewFlag;
	}
	public void setRequestLexmarkReviewFlag(boolean requestLexmarkReviewFlag) {
		this.requestLexmarkReviewFlag = requestLexmarkReviewFlag;
	}
	public ListOfValues getActualFailureCode() {
		return actualFailureCode;
	}
	public void setActualFailureCode(ListOfValues actualFailureCode) {
		this.actualFailureCode = actualFailureCode;
	}
	public GenericAddress getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(GenericAddress shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	public String getAccountPriority() {
		return accountPriority;
	}
	public void setAccountPriority(String accountPriority) {
		this.accountPriority = accountPriority;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getResponseMetric() {
		return responseMetric;
	}
	public void setResponseMetric(String responseMetric) {
		this.responseMetric = responseMetric;
	}
	public Date getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}
	public Date getCustomerRequestedResponseDate() {
		return customerRequestedResponseDate;
	}
	public void setCustomerRequestedResponseDate(Date customerRequestedResponseDate) {
		this.customerRequestedResponseDate = customerRequestedResponseDate;
	}
	public String getResolutionMetric() {
		return resolutionMetric;
	}
	public void setResolutionMetric(String resolutionMetric) {
		this.resolutionMetric = resolutionMetric;
	}
	public Date getResolutionDate() {
		return resolutionDate;
	}
	public void setResolutionDate(Date resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	public String getAccountSpecialHandling() {
		return accountSpecialHandling;
	}
	public void setAccountSpecialHandling(String accountSpecialHandling) {
		this.accountSpecialHandling = accountSpecialHandling;
	}
	public String getAssetWarningMessage() {
		return assetWarningMessage;
	}
	public void setAssetWarningMessage(String assetWarningMessage) {
		this.assetWarningMessage = assetWarningMessage;
	}
	public String getServiceProviderAttemptNumber() {
		return serviceProviderAttemptNumber;
	}
	public void setServiceProviderAttemptNumber(String serviceProviderAttemptNumber) {
		this.serviceProviderAttemptNumber = serviceProviderAttemptNumber;
	}
	public String getServiceActivityWithin30Days() {
		return serviceActivityWithin30Days;
	}
	public void setServiceActivityWithin30Days(String serviceActivityWithin30Days) {
		this.serviceActivityWithin30Days = serviceActivityWithin30Days;
	}
	public String getTrackCodes() {
		return trackCodes;
	}
	public void setTrackCodes(String trackCodes) {
		this.trackCodes = trackCodes;
	}
	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}
	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}
	public String getServicerComments() {
		return servicerComments;
	}
	public void setServicerComments(String servicerComments) {
		this.servicerComments = servicerComments;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getServiceProviderReferenceNumber() {
		return serviceProviderReferenceNumber;
	}
	public void setServiceProviderReferenceNumber(
			String serviceProviderReferenceNumber) {
		this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
	}
	public String getNewCustomerAccountFlag() {
		return newCustomerAccountFlag;
	}
	public void setNewCustomerAccountFlag(String newCustomerAccountFlag) {
		this.newCustomerAccountFlag = newCustomerAccountFlag;
	}
	public boolean isRepairCompleteFlag() {
		return repairCompleteFlag;
	}
	public void setRepairCompleteFlag(boolean repairCompleteFlag) {
		this.repairCompleteFlag = repairCompleteFlag;
	}
	public boolean isShipPartsToFlag() {
		return shipPartsToFlag;
	}
	public void setShipPartsToFlag(boolean shipPartsToFlag) {
		this.shipPartsToFlag = shipPartsToFlag;
	}
	public void setServiceAddress(GenericAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public GenericAddress getServiceAddress() {
		return serviceAddress;
	}
	public void setSelectedServiceDetails(EntitlementServiceDetails selectedServiceDetails) {
		this.selectedServiceDetails = selectedServiceDetails;
	}
	public EntitlementServiceDetails getSelectedServiceDetails() {
		return selectedServiceDetails;
	}
	public Debrief getDebrief() {
		return debrief;
	}
	public void setDebrief(Debrief debrief) {
		this.debrief = debrief;
	}
	public String getReviewComments() {
		return reviewComments;
	}
	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}
	public List<PartLineItem> getRecommendedPartList() {
		return recommendedPartList;
	}
	public void setRecommendedPartList(List<PartLineItem> recommendedPartList) {
		this.recommendedPartList = recommendedPartList;
	}
	public String getServiceSummary() {
		return serviceSummary;
	}
	public void setServiceSummary(String serviceSummary) {
		this.serviceSummary = serviceSummary;
	}
	public List<TechnicianInstruction> getServiceInstructionList() {
		return serviceInstructionList;
	}
	public void setServiceInstructionList(
			List<TechnicianInstruction> serviceInstructionList) {
		this.serviceInstructionList = serviceInstructionList;
	}
	public List<ActivityNote> getActivityNoteList() {
		return activityNoteList;
	}
	public void setActivityNoteList(List<ActivityNote> activityNoteList) {
		this.activityNoteList = activityNoteList;
	}
	public void setResolutionCode(ListOfValues resolutionCode) {
		this.resolutionCode = resolutionCode;
	}
	public ListOfValues getResolutionCode() {
		return resolutionCode;
	}
	public List<AdditionalPaymentRequest> getAdditionalPaymentRequestList() {
		return additionalPaymentRequestList;
	}
	public void setAdditionalPaymentRequestList(
			List<AdditionalPaymentRequest> additionalPaymentRequestList) {
		this.additionalPaymentRequestList = additionalPaymentRequestList;
	}
	public List<PartLineItem> getPendingPartList() {
		return pendingPartList;
	}
	public void setPendingPartList(List<PartLineItem> pendingPartList) {
		this.pendingPartList = pendingPartList;
	}
	public List<PartLineItem> getOrderPartList() {
		return orderPartList;
	}
	public void setOrderPartList(List<PartLineItem> orderPartList) {
		this.orderPartList = orderPartList;
	}
	public List<PartLineItem> getReturnPartList() {
		return returnPartList;
	}
	public void setReturnPartList(List<PartLineItem> returnPartList) {
		this.returnPartList = returnPartList;
	}
	public String getAdditionalProblemDetails() {
		return additionalProblemDetails;
	}
	public void setAdditionalProblemDetails(String additionalProblemDetails) {
		this.additionalProblemDetails = additionalProblemDetails;
	}
	public String getOtherTechnicianName() {
		return otherTechnicianName;
	}
	public void setOtherTechnicianName(String otherTechnicianName) {
		this.otherTechnicianName = otherTechnicianName;
	}
	public String getAddressStatus() {
		return addressStatus;
	}
	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}
	public void setEstimatedArrivalTime(Date estimatedArrivalTime) {
		this.estimatedArrivalTime = estimatedArrivalTime;
	}
	public Date getEstimatedArrivalTime() {
		return estimatedArrivalTime;
	}
	public void setServiceProviderStatus(String serviceProviderStatus) {
		this.serviceProviderStatus = serviceProviderStatus;
	}
	public String getServiceProviderStatus() {
		return serviceProviderStatus;
	}
	public String getNewCustomerAddressCombined() {
		return newCustomerAddressCombined;
	}
	public void setNewCustomerAddressCombined(String newCustomerAddressCombined) {
		this.newCustomerAddressCombined = newCustomerAddressCombined;
	}
	public String getEligibleToPay() {
		return eligibleToPay;
	}
	public void setEligibleToPay(String eligibleToPay) {
		this.eligibleToPay = eligibleToPay;
	}
	public String getPayEligiblityOverride() {
		return payEligiblityOverride;
	}
	public void setPayEligiblityOverride(String payEligiblityOverride) {
		this.payEligiblityOverride = payEligiblityOverride;
	}
	public String getPartnerAgreementName() {
		return partnerAgreementName;
	}
	public void setPartnerAgreementName(String partnerAgreementName) {
		this.partnerAgreementName = partnerAgreementName;
	}
	public double getLaborPayment() {
		return laborPayment;
	}
	public void setLaborPayment(double laborPayment) {
		this.laborPayment = laborPayment;
	}
	public double getPartsPayment() {
		return partsPayment;
	}
	public void setPartsPayment(double partsPayment) {
		this.partsPayment = partsPayment;
	}
	public double getAdditionalPayments() {
		return additionalPayments;
	}
	public void setAdditionalPayments(double additionalPayments) {
		this.additionalPayments = additionalPayments;
	}
	public double getPartnerFee() {
		return partnerFee;
	}
	public void setPartnerFee(double partnerFee) {
		this.partnerFee = partnerFee;
	}
	public double getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(double totalPayment) {
		this.totalPayment = totalPayment;
	}
	public String getPaymentServiceType() {
		return paymentServiceType;
	}
	public void setPaymentServiceType(String paymentServiceType) {
		this.paymentServiceType = paymentServiceType;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public List<PartLineItem> getPendingShipmentPartList() {
		return pendingShipmentPartList;
	}
	public void setPendingShipmentPartList(List<PartLineItem> pendingShipmentPartList) {
		this.pendingShipmentPartList = pendingShipmentPartList;
	}
	public List<PartLineItem> getProcessedPartList() {
		return processedPartList;
	}
	public void setProcessedPartList(List<PartLineItem> processedPartList) {
		this.processedPartList = processedPartList;
	}
	public double getSuppliesFulfillmentFee() {
		return suppliesFulfillmentFee;
	}
	public void setSuppliesFulfillmentFee(double suppliesFulfillmentFee) {
		this.suppliesFulfillmentFee = suppliesFulfillmentFee;
	}
	public Date getCustomerRequestedDate() {
		return customerRequestedDate;
	}
	public void setCustomerRequestedDate(Date customerRequestedDate) {
		this.customerRequestedDate = customerRequestedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public OfflineModeAttachment getOfflineModeAttachment() {
		return offlineModeAttachment;
	}
	public void setOfflineModeAttachment(OfflineModeAttachment offlineModeAttachment) {
		this.offlineModeAttachment = offlineModeAttachment;
	}
	public Date getServiceProviderETA() {
		return serviceProviderETA;
	}
	public void setServiceProviderETA(Date serviceProviderETA) {
		this.serviceProviderETA = serviceProviderETA;
	}
	public Date getExpectedCompletionDate() {
		return expectedCompletionDate;
	}
	public void setExpectedCompletionDate(Date expectedCompletionDate) {
		this.expectedCompletionDate = expectedCompletionDate;
	}
	public String getDebriefStatus() {
		return debriefStatus;
	}
	public void setDebriefStatus(String debriefStatus) {
		this.debriefStatus = debriefStatus;
	}
	public String getRfvErrors() {
		return rfvErrors;
	}
	public void setRfvErrors(String rfvErrors) {
		this.rfvErrors = rfvErrors;
	}
	public Date getCommittedDate() {
		return committedDate;
	}
	public void setCommittedDate(Date committedDate) {
		this.committedDate = committedDate;
	}
	public boolean isCreateChildSR() {
		return createChildSR;
	}
	public void setCreateChildSR(boolean createChildSR) {
		this.createChildSR = createChildSR;
	}
	public String getAgencyContactFirstName() {
		return agencyContactFirstName;
	}
	public void setAgencyContactFirstName(String agencyContactFirstName) {
		this.agencyContactFirstName = agencyContactFirstName;
	}
	public String getAgencyContactLastName() {
		return agencyContactLastName;
	}
	public void setAgencyContactLastName(String agencyContactLastName) {
		this.agencyContactLastName = agencyContactLastName;
	}
	public String getAgencyContacteMail() {
		return agencyContacteMail;
	}
	public void setAgencyContacteMail(String agencyContacteMail) {
		this.agencyContacteMail = agencyContacteMail;
	}
	public List<PartLineItem> getPartsList() {
		return partsList;
	}
	public void setPartsList(List<PartLineItem> partsList) {
		this.partsList = partsList;
	}
	public List<PartLineItem> getDebriefPartsList() {
		return debriefPartsList;
	}
	public void setDebriefPartsList(List<PartLineItem> debriefPartsList) {
		this.debriefPartsList = debriefPartsList;
	}
	public void setLocalizedCreateChildSR(String localizedCreateChildSR) {
		this.localizedCreateChildSR = localizedCreateChildSR;
	}
	public String getLocalizedCreateChildSR() {
		return localizedCreateChildSR;
	}
	public String getRequestorFirstName() {
		return requestorFirstName;
	}
	public void setRequestorFirstName(String requestorFirstName) {
		this.requestorFirstName = requestorFirstName;
	}
	public String getRequestorLastName() {
		return requestorLastName;
	}
	public void setRequestorLastName(String requestorLastName) {
		this.requestorLastName = requestorLastName;
	}
	public String getRequestorEmail() {
		return requestorEmail;
	}
	public void setRequestorEmail(String requestorEmail) {
		this.requestorEmail = requestorEmail;
	}
	public String getRequestorWorkPhone() {
		return requestorWorkPhone;
	}
	public void setRequestorWorkPhone(String requestorWorkPhone) {
		this.requestorWorkPhone = requestorWorkPhone;
	}
	public String getMaterialLineType() {
		return materialLineType;
	}
	public void setMaterialLineType(String materialLineType) {
		this.materialLineType = materialLineType;
	}
	public boolean isIsChildSR() {
		return isChildSR;
	}
	public void setIsChildSR(boolean isChildSR) {
		this.isChildSR = isChildSR;
	}
	/**
	 * @return the claimCoveredService
	 */
	public String getClaimCoveredService() {
		return claimCoveredService;
	}
	/**
	 * @param claimCoveredService the claimCoveredService to set
	 */
	public void setClaimCoveredService(String claimCoveredService) {
		this.claimCoveredService = claimCoveredService;
	}

}
