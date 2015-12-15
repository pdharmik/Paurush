/**
 * ServiceRequestData3.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ServiceRequestData3  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestDate;

    private java.lang.String serviceRequestStatus;

    private java.lang.String serviceRequestSource;

    private java.lang.String serviceRequestType;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact requester;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelMDMCustomerInformation customerInformation;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact primaryContact;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact secondaryContact;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation assetInformation;

    private java.lang.String problemDescription;

    private java.lang.String problemResolution;

    private java.lang.String requestedService;

    private java.lang.String serviceSummary;

    private java.lang.String accountPIN;

    private java.lang.String newProblemFlag;

    private java.lang.String onDemandDataSentFlag;

    private java.lang.String firstCallCompletionFlag;

    private com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails2[] selectedServiceDetails;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress serviceAddress;

    private java.lang.String trackingNumber;

    private java.lang.String carrier;

    private java.lang.String shipDate;

    private java.lang.String referenceNumber;

    private java.lang.String returnTrackingNumber;

    private java.lang.String optionExchangeAdditionalDescription;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation returnedAssetInformation;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityDetails webUpdateActivities;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityDetails emailActivities;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelServiceProviderInformation serviceProviderInformation;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelRecommendedPartsList[] recommendedPartsList;

    public ServiceRequestData3() {
    }

    public ServiceRequestData3(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestDate,
           java.lang.String serviceRequestStatus,
           java.lang.String serviceRequestSource,
           java.lang.String serviceRequestType,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact requester,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelMDMCustomerInformation customerInformation,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact primaryContact,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact secondaryContact,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation assetInformation,
           java.lang.String problemDescription,
           java.lang.String problemResolution,
           java.lang.String requestedService,
           java.lang.String serviceSummary,
           java.lang.String accountPIN,
           java.lang.String newProblemFlag,
           java.lang.String onDemandDataSentFlag,
           java.lang.String firstCallCompletionFlag,
           com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails2[] selectedServiceDetails,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress serviceAddress,
           java.lang.String trackingNumber,
           java.lang.String carrier,
           java.lang.String shipDate,
           java.lang.String referenceNumber,
           java.lang.String returnTrackingNumber,
           java.lang.String optionExchangeAdditionalDescription,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation returnedAssetInformation,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityDetails webUpdateActivities,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityDetails emailActivities,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelServiceProviderInformation serviceProviderInformation,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelRecommendedPartsList[] recommendedPartsList) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestDate = serviceRequestDate;
           this.serviceRequestStatus = serviceRequestStatus;
           this.serviceRequestSource = serviceRequestSource;
           this.serviceRequestType = serviceRequestType;
           this.requester = requester;
           this.customerInformation = customerInformation;
           this.primaryContact = primaryContact;
           this.secondaryContact = secondaryContact;
           this.assetInformation = assetInformation;
           this.problemDescription = problemDescription;
           this.problemResolution = problemResolution;
           this.requestedService = requestedService;
           this.serviceSummary = serviceSummary;
           this.accountPIN = accountPIN;
           this.newProblemFlag = newProblemFlag;
           this.onDemandDataSentFlag = onDemandDataSentFlag;
           this.firstCallCompletionFlag = firstCallCompletionFlag;
           this.selectedServiceDetails = selectedServiceDetails;
           this.serviceAddress = serviceAddress;
           this.trackingNumber = trackingNumber;
           this.carrier = carrier;
           this.shipDate = shipDate;
           this.referenceNumber = referenceNumber;
           this.returnTrackingNumber = returnTrackingNumber;
           this.optionExchangeAdditionalDescription = optionExchangeAdditionalDescription;
           this.returnedAssetInformation = returnedAssetInformation;
           this.webUpdateActivities = webUpdateActivities;
           this.emailActivities = emailActivities;
           this.serviceProviderInformation = serviceProviderInformation;
           this.recommendedPartsList = recommendedPartsList;
    }


    /**
     * Gets the serviceRequestNumber value for this ServiceRequestData3.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this ServiceRequestData3.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestDate value for this ServiceRequestData3.
     * 
     * @return serviceRequestDate
     */
    public java.lang.String getServiceRequestDate() {
        return serviceRequestDate;
    }


    /**
     * Sets the serviceRequestDate value for this ServiceRequestData3.
     * 
     * @param serviceRequestDate
     */
    public void setServiceRequestDate(java.lang.String serviceRequestDate) {
        this.serviceRequestDate = serviceRequestDate;
    }


    /**
     * Gets the serviceRequestStatus value for this ServiceRequestData3.
     * 
     * @return serviceRequestStatus
     */
    public java.lang.String getServiceRequestStatus() {
        return serviceRequestStatus;
    }


    /**
     * Sets the serviceRequestStatus value for this ServiceRequestData3.
     * 
     * @param serviceRequestStatus
     */
    public void setServiceRequestStatus(java.lang.String serviceRequestStatus) {
        this.serviceRequestStatus = serviceRequestStatus;
    }


    /**
     * Gets the serviceRequestSource value for this ServiceRequestData3.
     * 
     * @return serviceRequestSource
     */
    public java.lang.String getServiceRequestSource() {
        return serviceRequestSource;
    }


    /**
     * Sets the serviceRequestSource value for this ServiceRequestData3.
     * 
     * @param serviceRequestSource
     */
    public void setServiceRequestSource(java.lang.String serviceRequestSource) {
        this.serviceRequestSource = serviceRequestSource;
    }


    /**
     * Gets the serviceRequestType value for this ServiceRequestData3.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this ServiceRequestData3.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the requester value for this ServiceRequestData3.
     * 
     * @return requester
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ServiceRequestData3.
     * 
     * @param requester
     */
    public void setRequester(com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact requester) {
        this.requester = requester;
    }


    /**
     * Gets the customerInformation value for this ServiceRequestData3.
     * 
     * @return customerInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelMDMCustomerInformation getCustomerInformation() {
        return customerInformation;
    }


    /**
     * Sets the customerInformation value for this ServiceRequestData3.
     * 
     * @param customerInformation
     */
    public void setCustomerInformation(com.lexmark.SiebelShared.createServiceRequest.client.SiebelMDMCustomerInformation customerInformation) {
        this.customerInformation = customerInformation;
    }


    /**
     * Gets the primaryContact value for this ServiceRequestData3.
     * 
     * @return primaryContact
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this ServiceRequestData3.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the secondaryContact value for this ServiceRequestData3.
     * 
     * @return secondaryContact
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact getSecondaryContact() {
        return secondaryContact;
    }


    /**
     * Sets the secondaryContact value for this ServiceRequestData3.
     * 
     * @param secondaryContact
     */
    public void setSecondaryContact(com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact secondaryContact) {
        this.secondaryContact = secondaryContact;
    }


    /**
     * Gets the assetInformation value for this ServiceRequestData3.
     * 
     * @return assetInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation getAssetInformation() {
        return assetInformation;
    }


    /**
     * Sets the assetInformation value for this ServiceRequestData3.
     * 
     * @param assetInformation
     */
    public void setAssetInformation(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation assetInformation) {
        this.assetInformation = assetInformation;
    }


    /**
     * Gets the problemDescription value for this ServiceRequestData3.
     * 
     * @return problemDescription
     */
    public java.lang.String getProblemDescription() {
        return problemDescription;
    }


    /**
     * Sets the problemDescription value for this ServiceRequestData3.
     * 
     * @param problemDescription
     */
    public void setProblemDescription(java.lang.String problemDescription) {
        this.problemDescription = problemDescription;
    }


    /**
     * Gets the problemResolution value for this ServiceRequestData3.
     * 
     * @return problemResolution
     */
    public java.lang.String getProblemResolution() {
        return problemResolution;
    }


    /**
     * Sets the problemResolution value for this ServiceRequestData3.
     * 
     * @param problemResolution
     */
    public void setProblemResolution(java.lang.String problemResolution) {
        this.problemResolution = problemResolution;
    }


    /**
     * Gets the requestedService value for this ServiceRequestData3.
     * 
     * @return requestedService
     */
    public java.lang.String getRequestedService() {
        return requestedService;
    }


    /**
     * Sets the requestedService value for this ServiceRequestData3.
     * 
     * @param requestedService
     */
    public void setRequestedService(java.lang.String requestedService) {
        this.requestedService = requestedService;
    }


    /**
     * Gets the serviceSummary value for this ServiceRequestData3.
     * 
     * @return serviceSummary
     */
    public java.lang.String getServiceSummary() {
        return serviceSummary;
    }


    /**
     * Sets the serviceSummary value for this ServiceRequestData3.
     * 
     * @param serviceSummary
     */
    public void setServiceSummary(java.lang.String serviceSummary) {
        this.serviceSummary = serviceSummary;
    }


    /**
     * Gets the accountPIN value for this ServiceRequestData3.
     * 
     * @return accountPIN
     */
    public java.lang.String getAccountPIN() {
        return accountPIN;
    }


    /**
     * Sets the accountPIN value for this ServiceRequestData3.
     * 
     * @param accountPIN
     */
    public void setAccountPIN(java.lang.String accountPIN) {
        this.accountPIN = accountPIN;
    }


    /**
     * Gets the newProblemFlag value for this ServiceRequestData3.
     * 
     * @return newProblemFlag
     */
    public java.lang.String getNewProblemFlag() {
        return newProblemFlag;
    }


    /**
     * Sets the newProblemFlag value for this ServiceRequestData3.
     * 
     * @param newProblemFlag
     */
    public void setNewProblemFlag(java.lang.String newProblemFlag) {
        this.newProblemFlag = newProblemFlag;
    }


    /**
     * Gets the onDemandDataSentFlag value for this ServiceRequestData3.
     * 
     * @return onDemandDataSentFlag
     */
    public java.lang.String getOnDemandDataSentFlag() {
        return onDemandDataSentFlag;
    }


    /**
     * Sets the onDemandDataSentFlag value for this ServiceRequestData3.
     * 
     * @param onDemandDataSentFlag
     */
    public void setOnDemandDataSentFlag(java.lang.String onDemandDataSentFlag) {
        this.onDemandDataSentFlag = onDemandDataSentFlag;
    }


    /**
     * Gets the firstCallCompletionFlag value for this ServiceRequestData3.
     * 
     * @return firstCallCompletionFlag
     */
    public java.lang.String getFirstCallCompletionFlag() {
        return firstCallCompletionFlag;
    }


    /**
     * Sets the firstCallCompletionFlag value for this ServiceRequestData3.
     * 
     * @param firstCallCompletionFlag
     */
    public void setFirstCallCompletionFlag(java.lang.String firstCallCompletionFlag) {
        this.firstCallCompletionFlag = firstCallCompletionFlag;
    }


    /**
     * Gets the selectedServiceDetails value for this ServiceRequestData3.
     * 
     * @return selectedServiceDetails
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails2[] getSelectedServiceDetails() {
        return selectedServiceDetails;
    }


    /**
     * Sets the selectedServiceDetails value for this ServiceRequestData3.
     * 
     * @param selectedServiceDetails
     */
    public void setSelectedServiceDetails(com.lexmark.SiebelShared.createServiceRequest.client.SelectedServiceDetails2[] selectedServiceDetails) {
        this.selectedServiceDetails = selectedServiceDetails;
    }


    /**
     * Gets the serviceAddress value for this ServiceRequestData3.
     * 
     * @return serviceAddress
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress getServiceAddress() {
        return serviceAddress;
    }


    /**
     * Sets the serviceAddress value for this ServiceRequestData3.
     * 
     * @param serviceAddress
     */
    public void setServiceAddress(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress serviceAddress) {
        this.serviceAddress = serviceAddress;
    }


    /**
     * Gets the trackingNumber value for this ServiceRequestData3.
     * 
     * @return trackingNumber
     */
    public java.lang.String getTrackingNumber() {
        return trackingNumber;
    }


    /**
     * Sets the trackingNumber value for this ServiceRequestData3.
     * 
     * @param trackingNumber
     */
    public void setTrackingNumber(java.lang.String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }


    /**
     * Gets the carrier value for this ServiceRequestData3.
     * 
     * @return carrier
     */
    public java.lang.String getCarrier() {
        return carrier;
    }


    /**
     * Sets the carrier value for this ServiceRequestData3.
     * 
     * @param carrier
     */
    public void setCarrier(java.lang.String carrier) {
        this.carrier = carrier;
    }


    /**
     * Gets the shipDate value for this ServiceRequestData3.
     * 
     * @return shipDate
     */
    public java.lang.String getShipDate() {
        return shipDate;
    }


    /**
     * Sets the shipDate value for this ServiceRequestData3.
     * 
     * @param shipDate
     */
    public void setShipDate(java.lang.String shipDate) {
        this.shipDate = shipDate;
    }


    /**
     * Gets the referenceNumber value for this ServiceRequestData3.
     * 
     * @return referenceNumber
     */
    public java.lang.String getReferenceNumber() {
        return referenceNumber;
    }


    /**
     * Sets the referenceNumber value for this ServiceRequestData3.
     * 
     * @param referenceNumber
     */
    public void setReferenceNumber(java.lang.String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }


    /**
     * Gets the returnTrackingNumber value for this ServiceRequestData3.
     * 
     * @return returnTrackingNumber
     */
    public java.lang.String getReturnTrackingNumber() {
        return returnTrackingNumber;
    }


    /**
     * Sets the returnTrackingNumber value for this ServiceRequestData3.
     * 
     * @param returnTrackingNumber
     */
    public void setReturnTrackingNumber(java.lang.String returnTrackingNumber) {
        this.returnTrackingNumber = returnTrackingNumber;
    }


    /**
     * Gets the optionExchangeAdditionalDescription value for this ServiceRequestData3.
     * 
     * @return optionExchangeAdditionalDescription
     */
    public java.lang.String getOptionExchangeAdditionalDescription() {
        return optionExchangeAdditionalDescription;
    }


    /**
     * Sets the optionExchangeAdditionalDescription value for this ServiceRequestData3.
     * 
     * @param optionExchangeAdditionalDescription
     */
    public void setOptionExchangeAdditionalDescription(java.lang.String optionExchangeAdditionalDescription) {
        this.optionExchangeAdditionalDescription = optionExchangeAdditionalDescription;
    }


    /**
     * Gets the returnedAssetInformation value for this ServiceRequestData3.
     * 
     * @return returnedAssetInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation getReturnedAssetInformation() {
        return returnedAssetInformation;
    }


    /**
     * Sets the returnedAssetInformation value for this ServiceRequestData3.
     * 
     * @param returnedAssetInformation
     */
    public void setReturnedAssetInformation(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetInformation returnedAssetInformation) {
        this.returnedAssetInformation = returnedAssetInformation;
    }


    /**
     * Gets the webUpdateActivities value for this ServiceRequestData3.
     * 
     * @return webUpdateActivities
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityDetails getWebUpdateActivities() {
        return webUpdateActivities;
    }


    /**
     * Sets the webUpdateActivities value for this ServiceRequestData3.
     * 
     * @param webUpdateActivities
     */
    public void setWebUpdateActivities(com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityDetails webUpdateActivities) {
        this.webUpdateActivities = webUpdateActivities;
    }


    /**
     * Gets the emailActivities value for this ServiceRequestData3.
     * 
     * @return emailActivities
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityDetails getEmailActivities() {
        return emailActivities;
    }


    /**
     * Sets the emailActivities value for this ServiceRequestData3.
     * 
     * @param emailActivities
     */
    public void setEmailActivities(com.lexmark.SiebelShared.createServiceRequest.client.SiebelActivityDetails emailActivities) {
        this.emailActivities = emailActivities;
    }


    /**
     * Gets the serviceProviderInformation value for this ServiceRequestData3.
     * 
     * @return serviceProviderInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelServiceProviderInformation getServiceProviderInformation() {
        return serviceProviderInformation;
    }


    /**
     * Sets the serviceProviderInformation value for this ServiceRequestData3.
     * 
     * @param serviceProviderInformation
     */
    public void setServiceProviderInformation(com.lexmark.SiebelShared.createServiceRequest.client.SiebelServiceProviderInformation serviceProviderInformation) {
        this.serviceProviderInformation = serviceProviderInformation;
    }


    /**
     * Gets the recommendedPartsList value for this ServiceRequestData3.
     * 
     * @return recommendedPartsList
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelRecommendedPartsList[] getRecommendedPartsList() {
        return recommendedPartsList;
    }


    /**
     * Sets the recommendedPartsList value for this ServiceRequestData3.
     * 
     * @param recommendedPartsList
     */
    public void setRecommendedPartsList(com.lexmark.SiebelShared.createServiceRequest.client.SiebelRecommendedPartsList[] recommendedPartsList) {
        this.recommendedPartsList = recommendedPartsList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestData3)) return false;
        ServiceRequestData3 other = (ServiceRequestData3) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceRequestNumber==null && other.getServiceRequestNumber()==null) || 
             (this.serviceRequestNumber!=null &&
              this.serviceRequestNumber.equals(other.getServiceRequestNumber()))) &&
            ((this.serviceRequestDate==null && other.getServiceRequestDate()==null) || 
             (this.serviceRequestDate!=null &&
              this.serviceRequestDate.equals(other.getServiceRequestDate()))) &&
            ((this.serviceRequestStatus==null && other.getServiceRequestStatus()==null) || 
             (this.serviceRequestStatus!=null &&
              this.serviceRequestStatus.equals(other.getServiceRequestStatus()))) &&
            ((this.serviceRequestSource==null && other.getServiceRequestSource()==null) || 
             (this.serviceRequestSource!=null &&
              this.serviceRequestSource.equals(other.getServiceRequestSource()))) &&
            ((this.serviceRequestType==null && other.getServiceRequestType()==null) || 
             (this.serviceRequestType!=null &&
              this.serviceRequestType.equals(other.getServiceRequestType()))) &&
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.customerInformation==null && other.getCustomerInformation()==null) || 
             (this.customerInformation!=null &&
              this.customerInformation.equals(other.getCustomerInformation()))) &&
            ((this.primaryContact==null && other.getPrimaryContact()==null) || 
             (this.primaryContact!=null &&
              this.primaryContact.equals(other.getPrimaryContact()))) &&
            ((this.secondaryContact==null && other.getSecondaryContact()==null) || 
             (this.secondaryContact!=null &&
              this.secondaryContact.equals(other.getSecondaryContact()))) &&
            ((this.assetInformation==null && other.getAssetInformation()==null) || 
             (this.assetInformation!=null &&
              this.assetInformation.equals(other.getAssetInformation()))) &&
            ((this.problemDescription==null && other.getProblemDescription()==null) || 
             (this.problemDescription!=null &&
              this.problemDescription.equals(other.getProblemDescription()))) &&
            ((this.problemResolution==null && other.getProblemResolution()==null) || 
             (this.problemResolution!=null &&
              this.problemResolution.equals(other.getProblemResolution()))) &&
            ((this.requestedService==null && other.getRequestedService()==null) || 
             (this.requestedService!=null &&
              this.requestedService.equals(other.getRequestedService()))) &&
            ((this.serviceSummary==null && other.getServiceSummary()==null) || 
             (this.serviceSummary!=null &&
              this.serviceSummary.equals(other.getServiceSummary()))) &&
            ((this.accountPIN==null && other.getAccountPIN()==null) || 
             (this.accountPIN!=null &&
              this.accountPIN.equals(other.getAccountPIN()))) &&
            ((this.newProblemFlag==null && other.getNewProblemFlag()==null) || 
             (this.newProblemFlag!=null &&
              this.newProblemFlag.equals(other.getNewProblemFlag()))) &&
            ((this.onDemandDataSentFlag==null && other.getOnDemandDataSentFlag()==null) || 
             (this.onDemandDataSentFlag!=null &&
              this.onDemandDataSentFlag.equals(other.getOnDemandDataSentFlag()))) &&
            ((this.firstCallCompletionFlag==null && other.getFirstCallCompletionFlag()==null) || 
             (this.firstCallCompletionFlag!=null &&
              this.firstCallCompletionFlag.equals(other.getFirstCallCompletionFlag()))) &&
            ((this.selectedServiceDetails==null && other.getSelectedServiceDetails()==null) || 
             (this.selectedServiceDetails!=null &&
              java.util.Arrays.equals(this.selectedServiceDetails, other.getSelectedServiceDetails()))) &&
            ((this.serviceAddress==null && other.getServiceAddress()==null) || 
             (this.serviceAddress!=null &&
              this.serviceAddress.equals(other.getServiceAddress()))) &&
            ((this.trackingNumber==null && other.getTrackingNumber()==null) || 
             (this.trackingNumber!=null &&
              this.trackingNumber.equals(other.getTrackingNumber()))) &&
            ((this.carrier==null && other.getCarrier()==null) || 
             (this.carrier!=null &&
              this.carrier.equals(other.getCarrier()))) &&
            ((this.shipDate==null && other.getShipDate()==null) || 
             (this.shipDate!=null &&
              this.shipDate.equals(other.getShipDate()))) &&
            ((this.referenceNumber==null && other.getReferenceNumber()==null) || 
             (this.referenceNumber!=null &&
              this.referenceNumber.equals(other.getReferenceNumber()))) &&
            ((this.returnTrackingNumber==null && other.getReturnTrackingNumber()==null) || 
             (this.returnTrackingNumber!=null &&
              this.returnTrackingNumber.equals(other.getReturnTrackingNumber()))) &&
            ((this.optionExchangeAdditionalDescription==null && other.getOptionExchangeAdditionalDescription()==null) || 
             (this.optionExchangeAdditionalDescription!=null &&
              this.optionExchangeAdditionalDescription.equals(other.getOptionExchangeAdditionalDescription()))) &&
            ((this.returnedAssetInformation==null && other.getReturnedAssetInformation()==null) || 
             (this.returnedAssetInformation!=null &&
              this.returnedAssetInformation.equals(other.getReturnedAssetInformation()))) &&
            ((this.webUpdateActivities==null && other.getWebUpdateActivities()==null) || 
             (this.webUpdateActivities!=null &&
              this.webUpdateActivities.equals(other.getWebUpdateActivities()))) &&
            ((this.emailActivities==null && other.getEmailActivities()==null) || 
             (this.emailActivities!=null &&
              this.emailActivities.equals(other.getEmailActivities()))) &&
            ((this.serviceProviderInformation==null && other.getServiceProviderInformation()==null) || 
             (this.serviceProviderInformation!=null &&
              this.serviceProviderInformation.equals(other.getServiceProviderInformation()))) &&
            ((this.recommendedPartsList==null && other.getRecommendedPartsList()==null) || 
             (this.recommendedPartsList!=null &&
              java.util.Arrays.equals(this.recommendedPartsList, other.getRecommendedPartsList())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getServiceRequestNumber() != null) {
            _hashCode += getServiceRequestNumber().hashCode();
        }
        if (getServiceRequestDate() != null) {
            _hashCode += getServiceRequestDate().hashCode();
        }
        if (getServiceRequestStatus() != null) {
            _hashCode += getServiceRequestStatus().hashCode();
        }
        if (getServiceRequestSource() != null) {
            _hashCode += getServiceRequestSource().hashCode();
        }
        if (getServiceRequestType() != null) {
            _hashCode += getServiceRequestType().hashCode();
        }
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
        }
        if (getCustomerInformation() != null) {
            _hashCode += getCustomerInformation().hashCode();
        }
        if (getPrimaryContact() != null) {
            _hashCode += getPrimaryContact().hashCode();
        }
        if (getSecondaryContact() != null) {
            _hashCode += getSecondaryContact().hashCode();
        }
        if (getAssetInformation() != null) {
            _hashCode += getAssetInformation().hashCode();
        }
        if (getProblemDescription() != null) {
            _hashCode += getProblemDescription().hashCode();
        }
        if (getProblemResolution() != null) {
            _hashCode += getProblemResolution().hashCode();
        }
        if (getRequestedService() != null) {
            _hashCode += getRequestedService().hashCode();
        }
        if (getServiceSummary() != null) {
            _hashCode += getServiceSummary().hashCode();
        }
        if (getAccountPIN() != null) {
            _hashCode += getAccountPIN().hashCode();
        }
        if (getNewProblemFlag() != null) {
            _hashCode += getNewProblemFlag().hashCode();
        }
        if (getOnDemandDataSentFlag() != null) {
            _hashCode += getOnDemandDataSentFlag().hashCode();
        }
        if (getFirstCallCompletionFlag() != null) {
            _hashCode += getFirstCallCompletionFlag().hashCode();
        }
        if (getSelectedServiceDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSelectedServiceDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSelectedServiceDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getServiceAddress() != null) {
            _hashCode += getServiceAddress().hashCode();
        }
        if (getTrackingNumber() != null) {
            _hashCode += getTrackingNumber().hashCode();
        }
        if (getCarrier() != null) {
            _hashCode += getCarrier().hashCode();
        }
        if (getShipDate() != null) {
            _hashCode += getShipDate().hashCode();
        }
        if (getReferenceNumber() != null) {
            _hashCode += getReferenceNumber().hashCode();
        }
        if (getReturnTrackingNumber() != null) {
            _hashCode += getReturnTrackingNumber().hashCode();
        }
        if (getOptionExchangeAdditionalDescription() != null) {
            _hashCode += getOptionExchangeAdditionalDescription().hashCode();
        }
        if (getReturnedAssetInformation() != null) {
            _hashCode += getReturnedAssetInformation().hashCode();
        }
        if (getWebUpdateActivities() != null) {
            _hashCode += getWebUpdateActivities().hashCode();
        }
        if (getEmailActivities() != null) {
            _hashCode += getEmailActivities().hashCode();
        }
        if (getServiceProviderInformation() != null) {
            _hashCode += getServiceProviderInformation().hashCode();
        }
        if (getRecommendedPartsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecommendedPartsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecommendedPartsList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestData3.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData3"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestSource");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelMDMCustomerInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrimaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SecondaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAssetInformation"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problemDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProblemDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problemResolution");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProblemResolution"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedService");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestedService"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceSummary");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceSummary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountPIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newProblemFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NewProblemFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onDemandDataSentFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OnDemandDataSentFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstCallCompletionFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FirstCallCompletionFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectedServiceDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SelectedServiceDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails2"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfSelectedServiceDetails2Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TrackingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carrier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Carrier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnTrackingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnTrackingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("optionExchangeAdditionalDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "optionExchangeAdditionalDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnedAssetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnedAssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAssetInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webUpdateActivities");
        elemField.setXmlName(new javax.xml.namespace.QName("", "webUpdateActivities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelActivityDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailActivities");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EmailActivities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelActivityDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelServiceProviderInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recommendedPartsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RecommendedPartsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelRecommendedPartsList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfSiebelRecommendedPartsListItem"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
