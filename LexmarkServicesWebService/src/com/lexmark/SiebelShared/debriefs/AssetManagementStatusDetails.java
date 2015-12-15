/**
 * AssetManagementStatusDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;

public class AssetManagementStatusDetails  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String activityID;

    private java.lang.String accountName;

    private java.lang.String storeFrontName;

    private java.lang.String serviceRequestStatusOverall;

    private java.lang.String serviceRequestStatusDetail;

    private java.lang.String statusDateTime;

    private java.lang.String reasonForStatus;

    private java.lang.String estimatedTimeOfArrivalDate;

    private java.lang.String deferredAppointmentDate;

    private java.lang.String estimatedCallbackDate;

    private java.lang.String shipmentTrackingNumber;

    private java.lang.String serviceProviderName;

    private java.lang.String serviceRequestType;

    private java.lang.String requestedService;

    private java.lang.String customerReferenceNumber;

    private java.lang.String servicePartnerReferenceNumber;

    private java.lang.String projectName;

    private java.lang.String projectPhase;

    private java.lang.String comments;

    private com.lexmark.SiebelShared.debriefs.Attachments[] attachments;

    private com.lexmark.SiebelShared.debriefs.ActivityDetails[] activityDetails;

    private com.lexmark.SiebelShared.debriefs.SiebelContact primaryContact;

    private com.lexmark.SiebelShared.debriefs.SiebelContact secondaryContact;

    private com.lexmark.SiebelShared.debriefs.AdditionalContacts[] additionalContacts;

    private com.lexmark.SiebelShared.debriefs.AdditionalAddresses[] additionalAddresses;

    private com.lexmark.SiebelShared.debriefs.SiebelActivityNotes[] activityNotes;

    private com.lexmark.SiebelShared.debriefs.DebriefDetails debriefDetails;

    public AssetManagementStatusDetails() {
    }

    public AssetManagementStatusDetails(
           java.lang.String serviceRequestNumber,
           java.lang.String activityID,
           java.lang.String accountName,
           java.lang.String storeFrontName,
           java.lang.String serviceRequestStatusOverall,
           java.lang.String serviceRequestStatusDetail,
           java.lang.String statusDateTime,
           java.lang.String reasonForStatus,
           java.lang.String estimatedTimeOfArrivalDate,
           java.lang.String deferredAppointmentDate,
           java.lang.String estimatedCallbackDate,
           java.lang.String shipmentTrackingNumber,
           java.lang.String serviceProviderName,
           java.lang.String serviceRequestType,
           java.lang.String requestedService,
           java.lang.String customerReferenceNumber,
           java.lang.String servicePartnerReferenceNumber,
           java.lang.String projectName,
           java.lang.String projectPhase,
           java.lang.String comments,
           com.lexmark.SiebelShared.debriefs.Attachments[] attachments,
           com.lexmark.SiebelShared.debriefs.ActivityDetails[] activityDetails,
           com.lexmark.SiebelShared.debriefs.SiebelContact primaryContact,
           com.lexmark.SiebelShared.debriefs.SiebelContact secondaryContact,
           com.lexmark.SiebelShared.debriefs.AdditionalContacts[] additionalContacts,
           com.lexmark.SiebelShared.debriefs.AdditionalAddresses[] additionalAddresses,
           com.lexmark.SiebelShared.debriefs.SiebelActivityNotes[] activityNotes,
           com.lexmark.SiebelShared.debriefs.DebriefDetails debriefDetails) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.activityID = activityID;
           this.accountName = accountName;
           this.storeFrontName = storeFrontName;
           this.serviceRequestStatusOverall = serviceRequestStatusOverall;
           this.serviceRequestStatusDetail = serviceRequestStatusDetail;
           this.statusDateTime = statusDateTime;
           this.reasonForStatus = reasonForStatus;
           this.estimatedTimeOfArrivalDate = estimatedTimeOfArrivalDate;
           this.deferredAppointmentDate = deferredAppointmentDate;
           this.estimatedCallbackDate = estimatedCallbackDate;
           this.shipmentTrackingNumber = shipmentTrackingNumber;
           this.serviceProviderName = serviceProviderName;
           this.serviceRequestType = serviceRequestType;
           this.requestedService = requestedService;
           this.customerReferenceNumber = customerReferenceNumber;
           this.servicePartnerReferenceNumber = servicePartnerReferenceNumber;
           this.projectName = projectName;
           this.projectPhase = projectPhase;
           this.comments = comments;
           this.attachments = attachments;
           this.activityDetails = activityDetails;
           this.primaryContact = primaryContact;
           this.secondaryContact = secondaryContact;
           this.additionalContacts = additionalContacts;
           this.additionalAddresses = additionalAddresses;
           this.activityNotes = activityNotes;
           this.debriefDetails = debriefDetails;
    }


    /**
     * Gets the serviceRequestNumber value for this AssetManagementStatusDetails.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this AssetManagementStatusDetails.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the activityID value for this AssetManagementStatusDetails.
     * 
     * @return activityID
     */
    public java.lang.String getActivityID() {
        return activityID;
    }


    /**
     * Sets the activityID value for this AssetManagementStatusDetails.
     * 
     * @param activityID
     */
    public void setActivityID(java.lang.String activityID) {
        this.activityID = activityID;
    }


    /**
     * Gets the accountName value for this AssetManagementStatusDetails.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this AssetManagementStatusDetails.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the storeFrontName value for this AssetManagementStatusDetails.
     * 
     * @return storeFrontName
     */
    public java.lang.String getStoreFrontName() {
        return storeFrontName;
    }


    /**
     * Sets the storeFrontName value for this AssetManagementStatusDetails.
     * 
     * @param storeFrontName
     */
    public void setStoreFrontName(java.lang.String storeFrontName) {
        this.storeFrontName = storeFrontName;
    }


    /**
     * Gets the serviceRequestStatusOverall value for this AssetManagementStatusDetails.
     * 
     * @return serviceRequestStatusOverall
     */
    public java.lang.String getServiceRequestStatusOverall() {
        return serviceRequestStatusOverall;
    }


    /**
     * Sets the serviceRequestStatusOverall value for this AssetManagementStatusDetails.
     * 
     * @param serviceRequestStatusOverall
     */
    public void setServiceRequestStatusOverall(java.lang.String serviceRequestStatusOverall) {
        this.serviceRequestStatusOverall = serviceRequestStatusOverall;
    }


    /**
     * Gets the serviceRequestStatusDetail value for this AssetManagementStatusDetails.
     * 
     * @return serviceRequestStatusDetail
     */
    public java.lang.String getServiceRequestStatusDetail() {
        return serviceRequestStatusDetail;
    }


    /**
     * Sets the serviceRequestStatusDetail value for this AssetManagementStatusDetails.
     * 
     * @param serviceRequestStatusDetail
     */
    public void setServiceRequestStatusDetail(java.lang.String serviceRequestStatusDetail) {
        this.serviceRequestStatusDetail = serviceRequestStatusDetail;
    }


    /**
     * Gets the statusDateTime value for this AssetManagementStatusDetails.
     * 
     * @return statusDateTime
     */
    public java.lang.String getStatusDateTime() {
        return statusDateTime;
    }


    /**
     * Sets the statusDateTime value for this AssetManagementStatusDetails.
     * 
     * @param statusDateTime
     */
    public void setStatusDateTime(java.lang.String statusDateTime) {
        this.statusDateTime = statusDateTime;
    }


    /**
     * Gets the reasonForStatus value for this AssetManagementStatusDetails.
     * 
     * @return reasonForStatus
     */
    public java.lang.String getReasonForStatus() {
        return reasonForStatus;
    }


    /**
     * Sets the reasonForStatus value for this AssetManagementStatusDetails.
     * 
     * @param reasonForStatus
     */
    public void setReasonForStatus(java.lang.String reasonForStatus) {
        this.reasonForStatus = reasonForStatus;
    }


    /**
     * Gets the estimatedTimeOfArrivalDate value for this AssetManagementStatusDetails.
     * 
     * @return estimatedTimeOfArrivalDate
     */
    public java.lang.String getEstimatedTimeOfArrivalDate() {
        return estimatedTimeOfArrivalDate;
    }


    /**
     * Sets the estimatedTimeOfArrivalDate value for this AssetManagementStatusDetails.
     * 
     * @param estimatedTimeOfArrivalDate
     */
    public void setEstimatedTimeOfArrivalDate(java.lang.String estimatedTimeOfArrivalDate) {
        this.estimatedTimeOfArrivalDate = estimatedTimeOfArrivalDate;
    }


    /**
     * Gets the deferredAppointmentDate value for this AssetManagementStatusDetails.
     * 
     * @return deferredAppointmentDate
     */
    public java.lang.String getDeferredAppointmentDate() {
        return deferredAppointmentDate;
    }


    /**
     * Sets the deferredAppointmentDate value for this AssetManagementStatusDetails.
     * 
     * @param deferredAppointmentDate
     */
    public void setDeferredAppointmentDate(java.lang.String deferredAppointmentDate) {
        this.deferredAppointmentDate = deferredAppointmentDate;
    }


    /**
     * Gets the estimatedCallbackDate value for this AssetManagementStatusDetails.
     * 
     * @return estimatedCallbackDate
     */
    public java.lang.String getEstimatedCallbackDate() {
        return estimatedCallbackDate;
    }


    /**
     * Sets the estimatedCallbackDate value for this AssetManagementStatusDetails.
     * 
     * @param estimatedCallbackDate
     */
    public void setEstimatedCallbackDate(java.lang.String estimatedCallbackDate) {
        this.estimatedCallbackDate = estimatedCallbackDate;
    }


    /**
     * Gets the shipmentTrackingNumber value for this AssetManagementStatusDetails.
     * 
     * @return shipmentTrackingNumber
     */
    public java.lang.String getShipmentTrackingNumber() {
        return shipmentTrackingNumber;
    }


    /**
     * Sets the shipmentTrackingNumber value for this AssetManagementStatusDetails.
     * 
     * @param shipmentTrackingNumber
     */
    public void setShipmentTrackingNumber(java.lang.String shipmentTrackingNumber) {
        this.shipmentTrackingNumber = shipmentTrackingNumber;
    }


    /**
     * Gets the serviceProviderName value for this AssetManagementStatusDetails.
     * 
     * @return serviceProviderName
     */
    public java.lang.String getServiceProviderName() {
        return serviceProviderName;
    }


    /**
     * Sets the serviceProviderName value for this AssetManagementStatusDetails.
     * 
     * @param serviceProviderName
     */
    public void setServiceProviderName(java.lang.String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }


    /**
     * Gets the serviceRequestType value for this AssetManagementStatusDetails.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this AssetManagementStatusDetails.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the requestedService value for this AssetManagementStatusDetails.
     * 
     * @return requestedService
     */
    public java.lang.String getRequestedService() {
        return requestedService;
    }


    /**
     * Sets the requestedService value for this AssetManagementStatusDetails.
     * 
     * @param requestedService
     */
    public void setRequestedService(java.lang.String requestedService) {
        this.requestedService = requestedService;
    }


    /**
     * Gets the customerReferenceNumber value for this AssetManagementStatusDetails.
     * 
     * @return customerReferenceNumber
     */
    public java.lang.String getCustomerReferenceNumber() {
        return customerReferenceNumber;
    }


    /**
     * Sets the customerReferenceNumber value for this AssetManagementStatusDetails.
     * 
     * @param customerReferenceNumber
     */
    public void setCustomerReferenceNumber(java.lang.String customerReferenceNumber) {
        this.customerReferenceNumber = customerReferenceNumber;
    }


    /**
     * Gets the servicePartnerReferenceNumber value for this AssetManagementStatusDetails.
     * 
     * @return servicePartnerReferenceNumber
     */
    public java.lang.String getServicePartnerReferenceNumber() {
        return servicePartnerReferenceNumber;
    }


    /**
     * Sets the servicePartnerReferenceNumber value for this AssetManagementStatusDetails.
     * 
     * @param servicePartnerReferenceNumber
     */
    public void setServicePartnerReferenceNumber(java.lang.String servicePartnerReferenceNumber) {
        this.servicePartnerReferenceNumber = servicePartnerReferenceNumber;
    }


    /**
     * Gets the projectName value for this AssetManagementStatusDetails.
     * 
     * @return projectName
     */
    public java.lang.String getProjectName() {
        return projectName;
    }


    /**
     * Sets the projectName value for this AssetManagementStatusDetails.
     * 
     * @param projectName
     */
    public void setProjectName(java.lang.String projectName) {
        this.projectName = projectName;
    }


    /**
     * Gets the projectPhase value for this AssetManagementStatusDetails.
     * 
     * @return projectPhase
     */
    public java.lang.String getProjectPhase() {
        return projectPhase;
    }


    /**
     * Sets the projectPhase value for this AssetManagementStatusDetails.
     * 
     * @param projectPhase
     */
    public void setProjectPhase(java.lang.String projectPhase) {
        this.projectPhase = projectPhase;
    }


    /**
     * Gets the comments value for this AssetManagementStatusDetails.
     * 
     * @return comments
     */
    public java.lang.String getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this AssetManagementStatusDetails.
     * 
     * @param comments
     */
    public void setComments(java.lang.String comments) {
        this.comments = comments;
    }


    /**
     * Gets the attachments value for this AssetManagementStatusDetails.
     * 
     * @return attachments
     */
    public com.lexmark.SiebelShared.debriefs.Attachments[] getAttachments() {
        return attachments;
    }


    /**
     * Sets the attachments value for this AssetManagementStatusDetails.
     * 
     * @param attachments
     */
    public void setAttachments(com.lexmark.SiebelShared.debriefs.Attachments[] attachments) {
        this.attachments = attachments;
    }

    public com.lexmark.SiebelShared.debriefs.Attachments getAttachments(int i) {
        return this.attachments[i];
    }

    public void setAttachments(int i, com.lexmark.SiebelShared.debriefs.Attachments _value) {
        this.attachments[i] = _value;
    }


    /**
     * Gets the activityDetails value for this AssetManagementStatusDetails.
     * 
     * @return activityDetails
     */
    public com.lexmark.SiebelShared.debriefs.ActivityDetails[] getActivityDetails() {
        return activityDetails;
    }


    /**
     * Sets the activityDetails value for this AssetManagementStatusDetails.
     * 
     * @param activityDetails
     */
    public void setActivityDetails(com.lexmark.SiebelShared.debriefs.ActivityDetails[] activityDetails) {
        this.activityDetails = activityDetails;
    }

    public com.lexmark.SiebelShared.debriefs.ActivityDetails getActivityDetails(int i) {
        return this.activityDetails[i];
    }

    public void setActivityDetails(int i, com.lexmark.SiebelShared.debriefs.ActivityDetails _value) {
        this.activityDetails[i] = _value;
    }


    /**
     * Gets the primaryContact value for this AssetManagementStatusDetails.
     * 
     * @return primaryContact
     */
    public com.lexmark.SiebelShared.debriefs.SiebelContact getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this AssetManagementStatusDetails.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.SiebelShared.debriefs.SiebelContact primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the secondaryContact value for this AssetManagementStatusDetails.
     * 
     * @return secondaryContact
     */
    public com.lexmark.SiebelShared.debriefs.SiebelContact getSecondaryContact() {
        return secondaryContact;
    }


    /**
     * Sets the secondaryContact value for this AssetManagementStatusDetails.
     * 
     * @param secondaryContact
     */
    public void setSecondaryContact(com.lexmark.SiebelShared.debriefs.SiebelContact secondaryContact) {
        this.secondaryContact = secondaryContact;
    }


    /**
     * Gets the additionalContacts value for this AssetManagementStatusDetails.
     * 
     * @return additionalContacts
     */
    public com.lexmark.SiebelShared.debriefs.AdditionalContacts[] getAdditionalContacts() {
        return additionalContacts;
    }


    /**
     * Sets the additionalContacts value for this AssetManagementStatusDetails.
     * 
     * @param additionalContacts
     */
    public void setAdditionalContacts(com.lexmark.SiebelShared.debriefs.AdditionalContacts[] additionalContacts) {
        this.additionalContacts = additionalContacts;
    }

    public com.lexmark.SiebelShared.debriefs.AdditionalContacts getAdditionalContacts(int i) {
        return this.additionalContacts[i];
    }

    public void setAdditionalContacts(int i, com.lexmark.SiebelShared.debriefs.AdditionalContacts _value) {
        this.additionalContacts[i] = _value;
    }


    /**
     * Gets the additionalAddresses value for this AssetManagementStatusDetails.
     * 
     * @return additionalAddresses
     */
    public com.lexmark.SiebelShared.debriefs.AdditionalAddresses[] getAdditionalAddresses() {
        return additionalAddresses;
    }


    /**
     * Sets the additionalAddresses value for this AssetManagementStatusDetails.
     * 
     * @param additionalAddresses
     */
    public void setAdditionalAddresses(com.lexmark.SiebelShared.debriefs.AdditionalAddresses[] additionalAddresses) {
        this.additionalAddresses = additionalAddresses;
    }

    public com.lexmark.SiebelShared.debriefs.AdditionalAddresses getAdditionalAddresses(int i) {
        return this.additionalAddresses[i];
    }

    public void setAdditionalAddresses(int i, com.lexmark.SiebelShared.debriefs.AdditionalAddresses _value) {
        this.additionalAddresses[i] = _value;
    }


    /**
     * Gets the activityNotes value for this AssetManagementStatusDetails.
     * 
     * @return activityNotes
     */
    public com.lexmark.SiebelShared.debriefs.SiebelActivityNotes[] getActivityNotes() {
        return activityNotes;
    }


    /**
     * Sets the activityNotes value for this AssetManagementStatusDetails.
     * 
     * @param activityNotes
     */
    public void setActivityNotes(com.lexmark.SiebelShared.debriefs.SiebelActivityNotes[] activityNotes) {
        this.activityNotes = activityNotes;
    }

    public com.lexmark.SiebelShared.debriefs.SiebelActivityNotes getActivityNotes(int i) {
        return this.activityNotes[i];
    }

    public void setActivityNotes(int i, com.lexmark.SiebelShared.debriefs.SiebelActivityNotes _value) {
        this.activityNotes[i] = _value;
    }


    /**
     * Gets the debriefDetails value for this AssetManagementStatusDetails.
     * 
     * @return debriefDetails
     */
    public com.lexmark.SiebelShared.debriefs.DebriefDetails getDebriefDetails() {
        return debriefDetails;
    }


    /**
     * Sets the debriefDetails value for this AssetManagementStatusDetails.
     * 
     * @param debriefDetails
     */
    public void setDebriefDetails(com.lexmark.SiebelShared.debriefs.DebriefDetails debriefDetails) {
        this.debriefDetails = debriefDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetManagementStatusDetails)) return false;
        AssetManagementStatusDetails other = (AssetManagementStatusDetails) obj;
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
            ((this.activityID==null && other.getActivityID()==null) || 
             (this.activityID!=null &&
              this.activityID.equals(other.getActivityID()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            ((this.storeFrontName==null && other.getStoreFrontName()==null) || 
             (this.storeFrontName!=null &&
              this.storeFrontName.equals(other.getStoreFrontName()))) &&
            ((this.serviceRequestStatusOverall==null && other.getServiceRequestStatusOverall()==null) || 
             (this.serviceRequestStatusOverall!=null &&
              this.serviceRequestStatusOverall.equals(other.getServiceRequestStatusOverall()))) &&
            ((this.serviceRequestStatusDetail==null && other.getServiceRequestStatusDetail()==null) || 
             (this.serviceRequestStatusDetail!=null &&
              this.serviceRequestStatusDetail.equals(other.getServiceRequestStatusDetail()))) &&
            ((this.statusDateTime==null && other.getStatusDateTime()==null) || 
             (this.statusDateTime!=null &&
              this.statusDateTime.equals(other.getStatusDateTime()))) &&
            ((this.reasonForStatus==null && other.getReasonForStatus()==null) || 
             (this.reasonForStatus!=null &&
              this.reasonForStatus.equals(other.getReasonForStatus()))) &&
            ((this.estimatedTimeOfArrivalDate==null && other.getEstimatedTimeOfArrivalDate()==null) || 
             (this.estimatedTimeOfArrivalDate!=null &&
              this.estimatedTimeOfArrivalDate.equals(other.getEstimatedTimeOfArrivalDate()))) &&
            ((this.deferredAppointmentDate==null && other.getDeferredAppointmentDate()==null) || 
             (this.deferredAppointmentDate!=null &&
              this.deferredAppointmentDate.equals(other.getDeferredAppointmentDate()))) &&
            ((this.estimatedCallbackDate==null && other.getEstimatedCallbackDate()==null) || 
             (this.estimatedCallbackDate!=null &&
              this.estimatedCallbackDate.equals(other.getEstimatedCallbackDate()))) &&
            ((this.shipmentTrackingNumber==null && other.getShipmentTrackingNumber()==null) || 
             (this.shipmentTrackingNumber!=null &&
              this.shipmentTrackingNumber.equals(other.getShipmentTrackingNumber()))) &&
            ((this.serviceProviderName==null && other.getServiceProviderName()==null) || 
             (this.serviceProviderName!=null &&
              this.serviceProviderName.equals(other.getServiceProviderName()))) &&
            ((this.serviceRequestType==null && other.getServiceRequestType()==null) || 
             (this.serviceRequestType!=null &&
              this.serviceRequestType.equals(other.getServiceRequestType()))) &&
            ((this.requestedService==null && other.getRequestedService()==null) || 
             (this.requestedService!=null &&
              this.requestedService.equals(other.getRequestedService()))) &&
            ((this.customerReferenceNumber==null && other.getCustomerReferenceNumber()==null) || 
             (this.customerReferenceNumber!=null &&
              this.customerReferenceNumber.equals(other.getCustomerReferenceNumber()))) &&
            ((this.servicePartnerReferenceNumber==null && other.getServicePartnerReferenceNumber()==null) || 
             (this.servicePartnerReferenceNumber!=null &&
              this.servicePartnerReferenceNumber.equals(other.getServicePartnerReferenceNumber()))) &&
            ((this.projectName==null && other.getProjectName()==null) || 
             (this.projectName!=null &&
              this.projectName.equals(other.getProjectName()))) &&
            ((this.projectPhase==null && other.getProjectPhase()==null) || 
             (this.projectPhase!=null &&
              this.projectPhase.equals(other.getProjectPhase()))) &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              this.comments.equals(other.getComments()))) &&
            ((this.attachments==null && other.getAttachments()==null) || 
             (this.attachments!=null &&
              java.util.Arrays.equals(this.attachments, other.getAttachments()))) &&
            ((this.activityDetails==null && other.getActivityDetails()==null) || 
             (this.activityDetails!=null &&
              java.util.Arrays.equals(this.activityDetails, other.getActivityDetails()))) &&
            ((this.primaryContact==null && other.getPrimaryContact()==null) || 
             (this.primaryContact!=null &&
              this.primaryContact.equals(other.getPrimaryContact()))) &&
            ((this.secondaryContact==null && other.getSecondaryContact()==null) || 
             (this.secondaryContact!=null &&
              this.secondaryContact.equals(other.getSecondaryContact()))) &&
            ((this.additionalContacts==null && other.getAdditionalContacts()==null) || 
             (this.additionalContacts!=null &&
              java.util.Arrays.equals(this.additionalContacts, other.getAdditionalContacts()))) &&
            ((this.additionalAddresses==null && other.getAdditionalAddresses()==null) || 
             (this.additionalAddresses!=null &&
              java.util.Arrays.equals(this.additionalAddresses, other.getAdditionalAddresses()))) &&
            ((this.activityNotes==null && other.getActivityNotes()==null) || 
             (this.activityNotes!=null &&
              java.util.Arrays.equals(this.activityNotes, other.getActivityNotes()))) &&
            ((this.debriefDetails==null && other.getDebriefDetails()==null) || 
             (this.debriefDetails!=null &&
              this.debriefDetails.equals(other.getDebriefDetails())));
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
        if (getActivityID() != null) {
            _hashCode += getActivityID().hashCode();
        }
        if (getAccountName() != null) {
            _hashCode += getAccountName().hashCode();
        }
        if (getStoreFrontName() != null) {
            _hashCode += getStoreFrontName().hashCode();
        }
        if (getServiceRequestStatusOverall() != null) {
            _hashCode += getServiceRequestStatusOverall().hashCode();
        }
        if (getServiceRequestStatusDetail() != null) {
            _hashCode += getServiceRequestStatusDetail().hashCode();
        }
        if (getStatusDateTime() != null) {
            _hashCode += getStatusDateTime().hashCode();
        }
        if (getReasonForStatus() != null) {
            _hashCode += getReasonForStatus().hashCode();
        }
        if (getEstimatedTimeOfArrivalDate() != null) {
            _hashCode += getEstimatedTimeOfArrivalDate().hashCode();
        }
        if (getDeferredAppointmentDate() != null) {
            _hashCode += getDeferredAppointmentDate().hashCode();
        }
        if (getEstimatedCallbackDate() != null) {
            _hashCode += getEstimatedCallbackDate().hashCode();
        }
        if (getShipmentTrackingNumber() != null) {
            _hashCode += getShipmentTrackingNumber().hashCode();
        }
        if (getServiceProviderName() != null) {
            _hashCode += getServiceProviderName().hashCode();
        }
        if (getServiceRequestType() != null) {
            _hashCode += getServiceRequestType().hashCode();
        }
        if (getRequestedService() != null) {
            _hashCode += getRequestedService().hashCode();
        }
        if (getCustomerReferenceNumber() != null) {
            _hashCode += getCustomerReferenceNumber().hashCode();
        }
        if (getServicePartnerReferenceNumber() != null) {
            _hashCode += getServicePartnerReferenceNumber().hashCode();
        }
        if (getProjectName() != null) {
            _hashCode += getProjectName().hashCode();
        }
        if (getProjectPhase() != null) {
            _hashCode += getProjectPhase().hashCode();
        }
        if (getComments() != null) {
            _hashCode += getComments().hashCode();
        }
        if (getAttachments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttachments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttachments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getActivityDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActivityDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getActivityDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPrimaryContact() != null) {
            _hashCode += getPrimaryContact().hashCode();
        }
        if (getSecondaryContact() != null) {
            _hashCode += getSecondaryContact().hashCode();
        }
        if (getAdditionalContacts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalContacts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalContacts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAdditionalAddresses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalAddresses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalAddresses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getActivityNotes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActivityNotes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getActivityNotes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDebriefDetails() != null) {
            _hashCode += getDebriefDetails().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssetManagementStatusDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AssetManagementStatusDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storeFrontName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StoreFrontName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestStatusOverall");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestStatusOverall"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestStatusDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestStatusDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StatusDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reasonForStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReasonForStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estimatedTimeOfArrivalDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EstimatedTimeOfArrivalDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deferredAppointmentDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeferredAppointmentDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estimatedCallbackDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EstimatedCallbackDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipmentTrackingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipmentTrackingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedService");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestedService"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servicePartnerReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServicePartnerReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProjectName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectPhase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProjectPhase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Attachments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "Attachments"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "ActivityDetails"));
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrimaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "SiebelContact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SecondaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "SiebelContact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalContacts");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AdditionalContacts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AdditionalContacts"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalAddresses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AdditionalAddresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AdditionalAddresses"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityNotes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityNotes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "SiebelActivityNotes"));
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debriefDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DebriefDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "DebriefDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
