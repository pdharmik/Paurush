/**
 * ChangeManagementServiceRequestData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ChangeManagementServiceRequestData  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestType;

    private java.lang.String serviceRequestSource;

    private java.lang.String requestedService;

    private java.lang.String requestedServiceAction;

    private java.lang.String serviceRequestDate;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact requester;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount accountInformation;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact primaryContact;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact secondaryContact;

    private com.lexmark.SiebelShared.createServiceRequest.client.AssetContacts[] assetContacts;

    private com.lexmark.SiebelShared.createServiceRequest.client.ActivityDetails2[] activityDetails;

    private java.lang.String serviceRequestDescription;

    private java.lang.String customerReferenceNumber;

    private java.lang.String costCenter;

    private java.lang.String coveredService;

    private java.lang.String projectName;

    private java.lang.String projectPhase;

    private java.lang.String toBeMovedByLexmarkIndicator;

    private java.lang.String changeDeviceSettingsIndicator;

    private java.lang.String relatedServiceRequestNumber;

    private java.lang.String serviceRequestComments;

    private java.lang.String attachmentNotes;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress moveToAddress;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress pickUpAddress;

    public ChangeManagementServiceRequestData() {
    }

    public ChangeManagementServiceRequestData(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestType,
           java.lang.String serviceRequestSource,
           java.lang.String requestedService,
           java.lang.String requestedServiceAction,
           java.lang.String serviceRequestDate,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact requester,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount accountInformation,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact primaryContact,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact secondaryContact,
           com.lexmark.SiebelShared.createServiceRequest.client.AssetContacts[] assetContacts,
           com.lexmark.SiebelShared.createServiceRequest.client.ActivityDetails2[] activityDetails,
           java.lang.String serviceRequestDescription,
           java.lang.String customerReferenceNumber,
           java.lang.String costCenter,
           java.lang.String coveredService,
           java.lang.String projectName,
           java.lang.String projectPhase,
           java.lang.String toBeMovedByLexmarkIndicator,
           java.lang.String changeDeviceSettingsIndicator,
           java.lang.String relatedServiceRequestNumber,
           java.lang.String serviceRequestComments,
           java.lang.String attachmentNotes,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress moveToAddress,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress pickUpAddress) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestType = serviceRequestType;
           this.serviceRequestSource = serviceRequestSource;
           this.requestedService = requestedService;
           this.requestedServiceAction = requestedServiceAction;
           this.serviceRequestDate = serviceRequestDate;
           this.requester = requester;
           this.accountInformation = accountInformation;
           this.primaryContact = primaryContact;
           this.secondaryContact = secondaryContact;
           this.assetContacts = assetContacts;
           this.activityDetails = activityDetails;
           this.serviceRequestDescription = serviceRequestDescription;
           this.customerReferenceNumber = customerReferenceNumber;
           this.costCenter = costCenter;
           this.coveredService = coveredService;
           this.projectName = projectName;
           this.projectPhase = projectPhase;
           this.toBeMovedByLexmarkIndicator = toBeMovedByLexmarkIndicator;
           this.changeDeviceSettingsIndicator = changeDeviceSettingsIndicator;
           this.relatedServiceRequestNumber = relatedServiceRequestNumber;
           this.serviceRequestComments = serviceRequestComments;
           this.attachmentNotes = attachmentNotes;
           this.moveToAddress = moveToAddress;
           this.pickUpAddress = pickUpAddress;
    }


    /**
     * Gets the serviceRequestNumber value for this ChangeManagementServiceRequestData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this ChangeManagementServiceRequestData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestType value for this ChangeManagementServiceRequestData.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this ChangeManagementServiceRequestData.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the serviceRequestSource value for this ChangeManagementServiceRequestData.
     * 
     * @return serviceRequestSource
     */
    public java.lang.String getServiceRequestSource() {
        return serviceRequestSource;
    }


    /**
     * Sets the serviceRequestSource value for this ChangeManagementServiceRequestData.
     * 
     * @param serviceRequestSource
     */
    public void setServiceRequestSource(java.lang.String serviceRequestSource) {
        this.serviceRequestSource = serviceRequestSource;
    }


    /**
     * Gets the requestedService value for this ChangeManagementServiceRequestData.
     * 
     * @return requestedService
     */
    public java.lang.String getRequestedService() {
        return requestedService;
    }


    /**
     * Sets the requestedService value for this ChangeManagementServiceRequestData.
     * 
     * @param requestedService
     */
    public void setRequestedService(java.lang.String requestedService) {
        this.requestedService = requestedService;
    }


    /**
     * Gets the requestedServiceAction value for this ChangeManagementServiceRequestData.
     * 
     * @return requestedServiceAction
     */
    public java.lang.String getRequestedServiceAction() {
        return requestedServiceAction;
    }


    /**
     * Sets the requestedServiceAction value for this ChangeManagementServiceRequestData.
     * 
     * @param requestedServiceAction
     */
    public void setRequestedServiceAction(java.lang.String requestedServiceAction) {
        this.requestedServiceAction = requestedServiceAction;
    }


    /**
     * Gets the serviceRequestDate value for this ChangeManagementServiceRequestData.
     * 
     * @return serviceRequestDate
     */
    public java.lang.String getServiceRequestDate() {
        return serviceRequestDate;
    }


    /**
     * Sets the serviceRequestDate value for this ChangeManagementServiceRequestData.
     * 
     * @param serviceRequestDate
     */
    public void setServiceRequestDate(java.lang.String serviceRequestDate) {
        this.serviceRequestDate = serviceRequestDate;
    }


    /**
     * Gets the requester value for this ChangeManagementServiceRequestData.
     * 
     * @return requester
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ChangeManagementServiceRequestData.
     * 
     * @param requester
     */
    public void setRequester(com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact requester) {
        this.requester = requester;
    }


    /**
     * Gets the accountInformation value for this ChangeManagementServiceRequestData.
     * 
     * @return accountInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount getAccountInformation() {
        return accountInformation;
    }


    /**
     * Sets the accountInformation value for this ChangeManagementServiceRequestData.
     * 
     * @param accountInformation
     */
    public void setAccountInformation(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAccount accountInformation) {
        this.accountInformation = accountInformation;
    }


    /**
     * Gets the primaryContact value for this ChangeManagementServiceRequestData.
     * 
     * @return primaryContact
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this ChangeManagementServiceRequestData.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the secondaryContact value for this ChangeManagementServiceRequestData.
     * 
     * @return secondaryContact
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact getSecondaryContact() {
        return secondaryContact;
    }


    /**
     * Sets the secondaryContact value for this ChangeManagementServiceRequestData.
     * 
     * @param secondaryContact
     */
    public void setSecondaryContact(com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact secondaryContact) {
        this.secondaryContact = secondaryContact;
    }


    /**
     * Gets the assetContacts value for this ChangeManagementServiceRequestData.
     * 
     * @return assetContacts
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.AssetContacts[] getAssetContacts() {
        return assetContacts;
    }


    /**
     * Sets the assetContacts value for this ChangeManagementServiceRequestData.
     * 
     * @param assetContacts
     */
    public void setAssetContacts(com.lexmark.SiebelShared.createServiceRequest.client.AssetContacts[] assetContacts) {
        this.assetContacts = assetContacts;
    }


    /**
     * Gets the activityDetails value for this ChangeManagementServiceRequestData.
     * 
     * @return activityDetails
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.ActivityDetails2[] getActivityDetails() {
        return activityDetails;
    }


    /**
     * Sets the activityDetails value for this ChangeManagementServiceRequestData.
     * 
     * @param activityDetails
     */
    public void setActivityDetails(com.lexmark.SiebelShared.createServiceRequest.client.ActivityDetails2[] activityDetails) {
        this.activityDetails = activityDetails;
    }


    /**
     * Gets the serviceRequestDescription value for this ChangeManagementServiceRequestData.
     * 
     * @return serviceRequestDescription
     */
    public java.lang.String getServiceRequestDescription() {
        return serviceRequestDescription;
    }


    /**
     * Sets the serviceRequestDescription value for this ChangeManagementServiceRequestData.
     * 
     * @param serviceRequestDescription
     */
    public void setServiceRequestDescription(java.lang.String serviceRequestDescription) {
        this.serviceRequestDescription = serviceRequestDescription;
    }


    /**
     * Gets the customerReferenceNumber value for this ChangeManagementServiceRequestData.
     * 
     * @return customerReferenceNumber
     */
    public java.lang.String getCustomerReferenceNumber() {
        return customerReferenceNumber;
    }


    /**
     * Sets the customerReferenceNumber value for this ChangeManagementServiceRequestData.
     * 
     * @param customerReferenceNumber
     */
    public void setCustomerReferenceNumber(java.lang.String customerReferenceNumber) {
        this.customerReferenceNumber = customerReferenceNumber;
    }


    /**
     * Gets the costCenter value for this ChangeManagementServiceRequestData.
     * 
     * @return costCenter
     */
    public java.lang.String getCostCenter() {
        return costCenter;
    }


    /**
     * Sets the costCenter value for this ChangeManagementServiceRequestData.
     * 
     * @param costCenter
     */
    public void setCostCenter(java.lang.String costCenter) {
        this.costCenter = costCenter;
    }


    /**
     * Gets the coveredService value for this ChangeManagementServiceRequestData.
     * 
     * @return coveredService
     */
    public java.lang.String getCoveredService() {
        return coveredService;
    }


    /**
     * Sets the coveredService value for this ChangeManagementServiceRequestData.
     * 
     * @param coveredService
     */
    public void setCoveredService(java.lang.String coveredService) {
        this.coveredService = coveredService;
    }


    /**
     * Gets the projectName value for this ChangeManagementServiceRequestData.
     * 
     * @return projectName
     */
    public java.lang.String getProjectName() {
        return projectName;
    }


    /**
     * Sets the projectName value for this ChangeManagementServiceRequestData.
     * 
     * @param projectName
     */
    public void setProjectName(java.lang.String projectName) {
        this.projectName = projectName;
    }


    /**
     * Gets the projectPhase value for this ChangeManagementServiceRequestData.
     * 
     * @return projectPhase
     */
    public java.lang.String getProjectPhase() {
        return projectPhase;
    }


    /**
     * Sets the projectPhase value for this ChangeManagementServiceRequestData.
     * 
     * @param projectPhase
     */
    public void setProjectPhase(java.lang.String projectPhase) {
        this.projectPhase = projectPhase;
    }


    /**
     * Gets the toBeMovedByLexmarkIndicator value for this ChangeManagementServiceRequestData.
     * 
     * @return toBeMovedByLexmarkIndicator
     */
    public java.lang.String getToBeMovedByLexmarkIndicator() {
        return toBeMovedByLexmarkIndicator;
    }


    /**
     * Sets the toBeMovedByLexmarkIndicator value for this ChangeManagementServiceRequestData.
     * 
     * @param toBeMovedByLexmarkIndicator
     */
    public void setToBeMovedByLexmarkIndicator(java.lang.String toBeMovedByLexmarkIndicator) {
        this.toBeMovedByLexmarkIndicator = toBeMovedByLexmarkIndicator;
    }


    /**
     * Gets the changeDeviceSettingsIndicator value for this ChangeManagementServiceRequestData.
     * 
     * @return changeDeviceSettingsIndicator
     */
    public java.lang.String getChangeDeviceSettingsIndicator() {
        return changeDeviceSettingsIndicator;
    }


    /**
     * Sets the changeDeviceSettingsIndicator value for this ChangeManagementServiceRequestData.
     * 
     * @param changeDeviceSettingsIndicator
     */
    public void setChangeDeviceSettingsIndicator(java.lang.String changeDeviceSettingsIndicator) {
        this.changeDeviceSettingsIndicator = changeDeviceSettingsIndicator;
    }


    /**
     * Gets the relatedServiceRequestNumber value for this ChangeManagementServiceRequestData.
     * 
     * @return relatedServiceRequestNumber
     */
    public java.lang.String getRelatedServiceRequestNumber() {
        return relatedServiceRequestNumber;
    }


    /**
     * Sets the relatedServiceRequestNumber value for this ChangeManagementServiceRequestData.
     * 
     * @param relatedServiceRequestNumber
     */
    public void setRelatedServiceRequestNumber(java.lang.String relatedServiceRequestNumber) {
        this.relatedServiceRequestNumber = relatedServiceRequestNumber;
    }


    /**
     * Gets the serviceRequestComments value for this ChangeManagementServiceRequestData.
     * 
     * @return serviceRequestComments
     */
    public java.lang.String getServiceRequestComments() {
        return serviceRequestComments;
    }


    /**
     * Sets the serviceRequestComments value for this ChangeManagementServiceRequestData.
     * 
     * @param serviceRequestComments
     */
    public void setServiceRequestComments(java.lang.String serviceRequestComments) {
        this.serviceRequestComments = serviceRequestComments;
    }


    /**
     * Gets the attachmentNotes value for this ChangeManagementServiceRequestData.
     * 
     * @return attachmentNotes
     */
    public java.lang.String getAttachmentNotes() {
        return attachmentNotes;
    }


    /**
     * Sets the attachmentNotes value for this ChangeManagementServiceRequestData.
     * 
     * @param attachmentNotes
     */
    public void setAttachmentNotes(java.lang.String attachmentNotes) {
        this.attachmentNotes = attachmentNotes;
    }


    /**
     * Gets the moveToAddress value for this ChangeManagementServiceRequestData.
     * 
     * @return moveToAddress
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress getMoveToAddress() {
        return moveToAddress;
    }


    /**
     * Sets the moveToAddress value for this ChangeManagementServiceRequestData.
     * 
     * @param moveToAddress
     */
    public void setMoveToAddress(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress moveToAddress) {
        this.moveToAddress = moveToAddress;
    }


    /**
     * Gets the pickUpAddress value for this ChangeManagementServiceRequestData.
     * 
     * @return pickUpAddress
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress getPickUpAddress() {
        return pickUpAddress;
    }


    /**
     * Sets the pickUpAddress value for this ChangeManagementServiceRequestData.
     * 
     * @param pickUpAddress
     */
    public void setPickUpAddress(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChangeManagementServiceRequestData)) return false;
        ChangeManagementServiceRequestData other = (ChangeManagementServiceRequestData) obj;
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
            ((this.serviceRequestType==null && other.getServiceRequestType()==null) || 
             (this.serviceRequestType!=null &&
              this.serviceRequestType.equals(other.getServiceRequestType()))) &&
            ((this.serviceRequestSource==null && other.getServiceRequestSource()==null) || 
             (this.serviceRequestSource!=null &&
              this.serviceRequestSource.equals(other.getServiceRequestSource()))) &&
            ((this.requestedService==null && other.getRequestedService()==null) || 
             (this.requestedService!=null &&
              this.requestedService.equals(other.getRequestedService()))) &&
            ((this.requestedServiceAction==null && other.getRequestedServiceAction()==null) || 
             (this.requestedServiceAction!=null &&
              this.requestedServiceAction.equals(other.getRequestedServiceAction()))) &&
            ((this.serviceRequestDate==null && other.getServiceRequestDate()==null) || 
             (this.serviceRequestDate!=null &&
              this.serviceRequestDate.equals(other.getServiceRequestDate()))) &&
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.accountInformation==null && other.getAccountInformation()==null) || 
             (this.accountInformation!=null &&
              this.accountInformation.equals(other.getAccountInformation()))) &&
            ((this.primaryContact==null && other.getPrimaryContact()==null) || 
             (this.primaryContact!=null &&
              this.primaryContact.equals(other.getPrimaryContact()))) &&
            ((this.secondaryContact==null && other.getSecondaryContact()==null) || 
             (this.secondaryContact!=null &&
              this.secondaryContact.equals(other.getSecondaryContact()))) &&
            ((this.assetContacts==null && other.getAssetContacts()==null) || 
             (this.assetContacts!=null &&
              java.util.Arrays.equals(this.assetContacts, other.getAssetContacts()))) &&
            ((this.activityDetails==null && other.getActivityDetails()==null) || 
             (this.activityDetails!=null &&
              java.util.Arrays.equals(this.activityDetails, other.getActivityDetails()))) &&
            ((this.serviceRequestDescription==null && other.getServiceRequestDescription()==null) || 
             (this.serviceRequestDescription!=null &&
              this.serviceRequestDescription.equals(other.getServiceRequestDescription()))) &&
            ((this.customerReferenceNumber==null && other.getCustomerReferenceNumber()==null) || 
             (this.customerReferenceNumber!=null &&
              this.customerReferenceNumber.equals(other.getCustomerReferenceNumber()))) &&
            ((this.costCenter==null && other.getCostCenter()==null) || 
             (this.costCenter!=null &&
              this.costCenter.equals(other.getCostCenter()))) &&
            ((this.coveredService==null && other.getCoveredService()==null) || 
             (this.coveredService!=null &&
              this.coveredService.equals(other.getCoveredService()))) &&
            ((this.projectName==null && other.getProjectName()==null) || 
             (this.projectName!=null &&
              this.projectName.equals(other.getProjectName()))) &&
            ((this.projectPhase==null && other.getProjectPhase()==null) || 
             (this.projectPhase!=null &&
              this.projectPhase.equals(other.getProjectPhase()))) &&
            ((this.toBeMovedByLexmarkIndicator==null && other.getToBeMovedByLexmarkIndicator()==null) || 
             (this.toBeMovedByLexmarkIndicator!=null &&
              this.toBeMovedByLexmarkIndicator.equals(other.getToBeMovedByLexmarkIndicator()))) &&
            ((this.changeDeviceSettingsIndicator==null && other.getChangeDeviceSettingsIndicator()==null) || 
             (this.changeDeviceSettingsIndicator!=null &&
              this.changeDeviceSettingsIndicator.equals(other.getChangeDeviceSettingsIndicator()))) &&
            ((this.relatedServiceRequestNumber==null && other.getRelatedServiceRequestNumber()==null) || 
             (this.relatedServiceRequestNumber!=null &&
              this.relatedServiceRequestNumber.equals(other.getRelatedServiceRequestNumber()))) &&
            ((this.serviceRequestComments==null && other.getServiceRequestComments()==null) || 
             (this.serviceRequestComments!=null &&
              this.serviceRequestComments.equals(other.getServiceRequestComments()))) &&
            ((this.attachmentNotes==null && other.getAttachmentNotes()==null) || 
             (this.attachmentNotes!=null &&
              this.attachmentNotes.equals(other.getAttachmentNotes()))) &&
            ((this.moveToAddress==null && other.getMoveToAddress()==null) || 
             (this.moveToAddress!=null &&
              this.moveToAddress.equals(other.getMoveToAddress()))) &&
            ((this.pickUpAddress==null && other.getPickUpAddress()==null) || 
             (this.pickUpAddress!=null &&
              this.pickUpAddress.equals(other.getPickUpAddress())));
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
        if (getServiceRequestType() != null) {
            _hashCode += getServiceRequestType().hashCode();
        }
        if (getServiceRequestSource() != null) {
            _hashCode += getServiceRequestSource().hashCode();
        }
        if (getRequestedService() != null) {
            _hashCode += getRequestedService().hashCode();
        }
        if (getRequestedServiceAction() != null) {
            _hashCode += getRequestedServiceAction().hashCode();
        }
        if (getServiceRequestDate() != null) {
            _hashCode += getServiceRequestDate().hashCode();
        }
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
        }
        if (getAccountInformation() != null) {
            _hashCode += getAccountInformation().hashCode();
        }
        if (getPrimaryContact() != null) {
            _hashCode += getPrimaryContact().hashCode();
        }
        if (getSecondaryContact() != null) {
            _hashCode += getSecondaryContact().hashCode();
        }
        if (getAssetContacts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAssetContacts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAssetContacts(), i);
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
        if (getServiceRequestDescription() != null) {
            _hashCode += getServiceRequestDescription().hashCode();
        }
        if (getCustomerReferenceNumber() != null) {
            _hashCode += getCustomerReferenceNumber().hashCode();
        }
        if (getCostCenter() != null) {
            _hashCode += getCostCenter().hashCode();
        }
        if (getCoveredService() != null) {
            _hashCode += getCoveredService().hashCode();
        }
        if (getProjectName() != null) {
            _hashCode += getProjectName().hashCode();
        }
        if (getProjectPhase() != null) {
            _hashCode += getProjectPhase().hashCode();
        }
        if (getToBeMovedByLexmarkIndicator() != null) {
            _hashCode += getToBeMovedByLexmarkIndicator().hashCode();
        }
        if (getChangeDeviceSettingsIndicator() != null) {
            _hashCode += getChangeDeviceSettingsIndicator().hashCode();
        }
        if (getRelatedServiceRequestNumber() != null) {
            _hashCode += getRelatedServiceRequestNumber().hashCode();
        }
        if (getServiceRequestComments() != null) {
            _hashCode += getServiceRequestComments().hashCode();
        }
        if (getAttachmentNotes() != null) {
            _hashCode += getAttachmentNotes().hashCode();
        }
        if (getMoveToAddress() != null) {
            _hashCode += getMoveToAddress().hashCode();
        }
        if (getPickUpAddress() != null) {
            _hashCode += getPickUpAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ChangeManagementServiceRequestData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("requestedService");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestedService"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedServiceAction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestedServiceAction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAccount"));
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
        elemField.setFieldName("assetContacts");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetContacts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContacts"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfAssetContactsItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityDetails2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfActivityDetails2Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("costCenter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CostCenter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coveredService");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CoveredService"));
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
        elemField.setFieldName("toBeMovedByLexmarkIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ToBeMovedByLexmarkIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeDeviceSettingsIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ChangeDeviceSettingsIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedServiceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelatedServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestComments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestComments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentNotes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AttachmentNotes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moveToAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MoveToAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pickUpAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PickUpAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress"));
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
