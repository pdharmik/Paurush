/**
 * ConsumablesServiceRequestData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class ConsumablesServiceRequestData  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestType;

    private java.lang.String serviceRequestSource;

    private java.lang.String serviceRequestStatus;

    private java.lang.String requestedService;

    private java.lang.String requestedServiceAction;

    private java.lang.String serviceRequestDate;

    private java.lang.String serviceRequestDescription;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact requester;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact primaryContact;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact secondaryContact;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAccount accountInformation;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress shipToAddress;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.AssetInformation2 assetInformation;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.MaterialDetails[] materialDetails;

    private java.lang.String customerReferenceNumber;

    private java.lang.String vendorName;

    private java.lang.String vendorId;

    private java.lang.String costCenter;

    private java.lang.String installationOnlyFlag;

    private java.lang.String serviceRequestComments;

    private java.lang.String relatedServiceRequestNumber;

    private java.lang.String billingModel;

    private java.lang.String attachmentNotes;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OrderInformation orderInformation;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentDetails paymentInformation;

    public ConsumablesServiceRequestData() {
    }

    public ConsumablesServiceRequestData(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestType,
           java.lang.String serviceRequestSource,
           java.lang.String serviceRequestStatus,
           java.lang.String requestedService,
           java.lang.String requestedServiceAction,
           java.lang.String serviceRequestDate,
           java.lang.String serviceRequestDescription,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact requester,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact primaryContact,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact secondaryContact,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAccount accountInformation,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress shipToAddress,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.AssetInformation2 assetInformation,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.MaterialDetails[] materialDetails,
           java.lang.String customerReferenceNumber,
           java.lang.String vendorName,
           java.lang.String vendorId,
           java.lang.String costCenter,
           java.lang.String installationOnlyFlag,
           java.lang.String serviceRequestComments,
           java.lang.String relatedServiceRequestNumber,
           java.lang.String billingModel,
           java.lang.String attachmentNotes,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OrderInformation orderInformation,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentDetails paymentInformation) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestType = serviceRequestType;
           this.serviceRequestSource = serviceRequestSource;
           this.serviceRequestStatus = serviceRequestStatus;
           this.requestedService = requestedService;
           this.requestedServiceAction = requestedServiceAction;
           this.serviceRequestDate = serviceRequestDate;
           this.serviceRequestDescription = serviceRequestDescription;
           this.requester = requester;
           this.primaryContact = primaryContact;
           this.secondaryContact = secondaryContact;
           this.accountInformation = accountInformation;
           this.shipToAddress = shipToAddress;
           this.assetInformation = assetInformation;
           this.materialDetails = materialDetails;
           this.customerReferenceNumber = customerReferenceNumber;
           this.vendorName = vendorName;
           this.vendorId = vendorId;
           this.costCenter = costCenter;
           this.installationOnlyFlag = installationOnlyFlag;
           this.serviceRequestComments = serviceRequestComments;
           this.relatedServiceRequestNumber = relatedServiceRequestNumber;
           this.billingModel = billingModel;
           this.attachmentNotes = attachmentNotes;
           this.orderInformation = orderInformation;
           this.paymentInformation = paymentInformation;
    }


    /**
     * Gets the serviceRequestNumber value for this ConsumablesServiceRequestData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this ConsumablesServiceRequestData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestType value for this ConsumablesServiceRequestData.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this ConsumablesServiceRequestData.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the serviceRequestSource value for this ConsumablesServiceRequestData.
     * 
     * @return serviceRequestSource
     */
    public java.lang.String getServiceRequestSource() {
        return serviceRequestSource;
    }


    /**
     * Sets the serviceRequestSource value for this ConsumablesServiceRequestData.
     * 
     * @param serviceRequestSource
     */
    public void setServiceRequestSource(java.lang.String serviceRequestSource) {
        this.serviceRequestSource = serviceRequestSource;
    }


    /**
     * Gets the serviceRequestStatus value for this ConsumablesServiceRequestData.
     * 
     * @return serviceRequestStatus
     */
    public java.lang.String getServiceRequestStatus() {
        return serviceRequestStatus;
    }


    /**
     * Sets the serviceRequestStatus value for this ConsumablesServiceRequestData.
     * 
     * @param serviceRequestStatus
     */
    public void setServiceRequestStatus(java.lang.String serviceRequestStatus) {
        this.serviceRequestStatus = serviceRequestStatus;
    }


    /**
     * Gets the requestedService value for this ConsumablesServiceRequestData.
     * 
     * @return requestedService
     */
    public java.lang.String getRequestedService() {
        return requestedService;
    }


    /**
     * Sets the requestedService value for this ConsumablesServiceRequestData.
     * 
     * @param requestedService
     */
    public void setRequestedService(java.lang.String requestedService) {
        this.requestedService = requestedService;
    }


    /**
     * Gets the requestedServiceAction value for this ConsumablesServiceRequestData.
     * 
     * @return requestedServiceAction
     */
    public java.lang.String getRequestedServiceAction() {
        return requestedServiceAction;
    }


    /**
     * Sets the requestedServiceAction value for this ConsumablesServiceRequestData.
     * 
     * @param requestedServiceAction
     */
    public void setRequestedServiceAction(java.lang.String requestedServiceAction) {
        this.requestedServiceAction = requestedServiceAction;
    }


    /**
     * Gets the serviceRequestDate value for this ConsumablesServiceRequestData.
     * 
     * @return serviceRequestDate
     */
    public java.lang.String getServiceRequestDate() {
        return serviceRequestDate;
    }


    /**
     * Sets the serviceRequestDate value for this ConsumablesServiceRequestData.
     * 
     * @param serviceRequestDate
     */
    public void setServiceRequestDate(java.lang.String serviceRequestDate) {
        this.serviceRequestDate = serviceRequestDate;
    }


    /**
     * Gets the serviceRequestDescription value for this ConsumablesServiceRequestData.
     * 
     * @return serviceRequestDescription
     */
    public java.lang.String getServiceRequestDescription() {
        return serviceRequestDescription;
    }


    /**
     * Sets the serviceRequestDescription value for this ConsumablesServiceRequestData.
     * 
     * @param serviceRequestDescription
     */
    public void setServiceRequestDescription(java.lang.String serviceRequestDescription) {
        this.serviceRequestDescription = serviceRequestDescription;
    }


    /**
     * Gets the requester value for this ConsumablesServiceRequestData.
     * 
     * @return requester
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ConsumablesServiceRequestData.
     * 
     * @param requester
     */
    public void setRequester(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact requester) {
        this.requester = requester;
    }


    /**
     * Gets the primaryContact value for this ConsumablesServiceRequestData.
     * 
     * @return primaryContact
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this ConsumablesServiceRequestData.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the secondaryContact value for this ConsumablesServiceRequestData.
     * 
     * @return secondaryContact
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getSecondaryContact() {
        return secondaryContact;
    }


    /**
     * Sets the secondaryContact value for this ConsumablesServiceRequestData.
     * 
     * @param secondaryContact
     */
    public void setSecondaryContact(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact secondaryContact) {
        this.secondaryContact = secondaryContact;
    }


    /**
     * Gets the accountInformation value for this ConsumablesServiceRequestData.
     * 
     * @return accountInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAccount getAccountInformation() {
        return accountInformation;
    }


    /**
     * Sets the accountInformation value for this ConsumablesServiceRequestData.
     * 
     * @param accountInformation
     */
    public void setAccountInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAccount accountInformation) {
        this.accountInformation = accountInformation;
    }


    /**
     * Gets the shipToAddress value for this ConsumablesServiceRequestData.
     * 
     * @return shipToAddress
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress getShipToAddress() {
        return shipToAddress;
    }


    /**
     * Sets the shipToAddress value for this ConsumablesServiceRequestData.
     * 
     * @param shipToAddress
     */
    public void setShipToAddress(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress shipToAddress) {
        this.shipToAddress = shipToAddress;
    }


    /**
     * Gets the assetInformation value for this ConsumablesServiceRequestData.
     * 
     * @return assetInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.AssetInformation2 getAssetInformation() {
        return assetInformation;
    }


    /**
     * Sets the assetInformation value for this ConsumablesServiceRequestData.
     * 
     * @param assetInformation
     */
    public void setAssetInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.AssetInformation2 assetInformation) {
        this.assetInformation = assetInformation;
    }


    /**
     * Gets the materialDetails value for this ConsumablesServiceRequestData.
     * 
     * @return materialDetails
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.MaterialDetails[] getMaterialDetails() {
        return materialDetails;
    }


    /**
     * Sets the materialDetails value for this ConsumablesServiceRequestData.
     * 
     * @param materialDetails
     */
    public void setMaterialDetails(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.MaterialDetails[] materialDetails) {
        this.materialDetails = materialDetails;
    }


    /**
     * Gets the customerReferenceNumber value for this ConsumablesServiceRequestData.
     * 
     * @return customerReferenceNumber
     */
    public java.lang.String getCustomerReferenceNumber() {
        return customerReferenceNumber;
    }


    /**
     * Sets the customerReferenceNumber value for this ConsumablesServiceRequestData.
     * 
     * @param customerReferenceNumber
     */
    public void setCustomerReferenceNumber(java.lang.String customerReferenceNumber) {
        this.customerReferenceNumber = customerReferenceNumber;
    }


    /**
     * Gets the vendorName value for this ConsumablesServiceRequestData.
     * 
     * @return vendorName
     */
    public java.lang.String getVendorName() {
        return vendorName;
    }


    /**
     * Sets the vendorName value for this ConsumablesServiceRequestData.
     * 
     * @param vendorName
     */
    public void setVendorName(java.lang.String vendorName) {
        this.vendorName = vendorName;
    }


    /**
     * Gets the vendorId value for this ConsumablesServiceRequestData.
     * 
     * @return vendorId
     */
    public java.lang.String getVendorId() {
        return vendorId;
    }


    /**
     * Sets the vendorId value for this ConsumablesServiceRequestData.
     * 
     * @param vendorId
     */
    public void setVendorId(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }


    /**
     * Gets the costCenter value for this ConsumablesServiceRequestData.
     * 
     * @return costCenter
     */
    public java.lang.String getCostCenter() {
        return costCenter;
    }


    /**
     * Sets the costCenter value for this ConsumablesServiceRequestData.
     * 
     * @param costCenter
     */
    public void setCostCenter(java.lang.String costCenter) {
        this.costCenter = costCenter;
    }


    /**
     * Gets the installationOnlyFlag value for this ConsumablesServiceRequestData.
     * 
     * @return installationOnlyFlag
     */
    public java.lang.String getInstallationOnlyFlag() {
        return installationOnlyFlag;
    }


    /**
     * Sets the installationOnlyFlag value for this ConsumablesServiceRequestData.
     * 
     * @param installationOnlyFlag
     */
    public void setInstallationOnlyFlag(java.lang.String installationOnlyFlag) {
        this.installationOnlyFlag = installationOnlyFlag;
    }


    /**
     * Gets the serviceRequestComments value for this ConsumablesServiceRequestData.
     * 
     * @return serviceRequestComments
     */
    public java.lang.String getServiceRequestComments() {
        return serviceRequestComments;
    }


    /**
     * Sets the serviceRequestComments value for this ConsumablesServiceRequestData.
     * 
     * @param serviceRequestComments
     */
    public void setServiceRequestComments(java.lang.String serviceRequestComments) {
        this.serviceRequestComments = serviceRequestComments;
    }


    /**
     * Gets the relatedServiceRequestNumber value for this ConsumablesServiceRequestData.
     * 
     * @return relatedServiceRequestNumber
     */
    public java.lang.String getRelatedServiceRequestNumber() {
        return relatedServiceRequestNumber;
    }


    /**
     * Sets the relatedServiceRequestNumber value for this ConsumablesServiceRequestData.
     * 
     * @param relatedServiceRequestNumber
     */
    public void setRelatedServiceRequestNumber(java.lang.String relatedServiceRequestNumber) {
        this.relatedServiceRequestNumber = relatedServiceRequestNumber;
    }


    /**
     * Gets the billingModel value for this ConsumablesServiceRequestData.
     * 
     * @return billingModel
     */
    public java.lang.String getBillingModel() {
        return billingModel;
    }


    /**
     * Sets the billingModel value for this ConsumablesServiceRequestData.
     * 
     * @param billingModel
     */
    public void setBillingModel(java.lang.String billingModel) {
        this.billingModel = billingModel;
    }


    /**
     * Gets the attachmentNotes value for this ConsumablesServiceRequestData.
     * 
     * @return attachmentNotes
     */
    public java.lang.String getAttachmentNotes() {
        return attachmentNotes;
    }


    /**
     * Sets the attachmentNotes value for this ConsumablesServiceRequestData.
     * 
     * @param attachmentNotes
     */
    public void setAttachmentNotes(java.lang.String attachmentNotes) {
        this.attachmentNotes = attachmentNotes;
    }


    /**
     * Gets the orderInformation value for this ConsumablesServiceRequestData.
     * 
     * @return orderInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OrderInformation getOrderInformation() {
        return orderInformation;
    }


    /**
     * Sets the orderInformation value for this ConsumablesServiceRequestData.
     * 
     * @param orderInformation
     */
    public void setOrderInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OrderInformation orderInformation) {
        this.orderInformation = orderInformation;
    }


    /**
     * Gets the paymentInformation value for this ConsumablesServiceRequestData.
     * 
     * @return paymentInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentDetails getPaymentInformation() {
        return paymentInformation;
    }


    /**
     * Sets the paymentInformation value for this ConsumablesServiceRequestData.
     * 
     * @param paymentInformation
     */
    public void setPaymentInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentDetails paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsumablesServiceRequestData)) return false;
        ConsumablesServiceRequestData other = (ConsumablesServiceRequestData) obj;
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
            ((this.serviceRequestStatus==null && other.getServiceRequestStatus()==null) || 
             (this.serviceRequestStatus!=null &&
              this.serviceRequestStatus.equals(other.getServiceRequestStatus()))) &&
            ((this.requestedService==null && other.getRequestedService()==null) || 
             (this.requestedService!=null &&
              this.requestedService.equals(other.getRequestedService()))) &&
            ((this.requestedServiceAction==null && other.getRequestedServiceAction()==null) || 
             (this.requestedServiceAction!=null &&
              this.requestedServiceAction.equals(other.getRequestedServiceAction()))) &&
            ((this.serviceRequestDate==null && other.getServiceRequestDate()==null) || 
             (this.serviceRequestDate!=null &&
              this.serviceRequestDate.equals(other.getServiceRequestDate()))) &&
            ((this.serviceRequestDescription==null && other.getServiceRequestDescription()==null) || 
             (this.serviceRequestDescription!=null &&
              this.serviceRequestDescription.equals(other.getServiceRequestDescription()))) &&
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.primaryContact==null && other.getPrimaryContact()==null) || 
             (this.primaryContact!=null &&
              this.primaryContact.equals(other.getPrimaryContact()))) &&
            ((this.secondaryContact==null && other.getSecondaryContact()==null) || 
             (this.secondaryContact!=null &&
              this.secondaryContact.equals(other.getSecondaryContact()))) &&
            ((this.accountInformation==null && other.getAccountInformation()==null) || 
             (this.accountInformation!=null &&
              this.accountInformation.equals(other.getAccountInformation()))) &&
            ((this.shipToAddress==null && other.getShipToAddress()==null) || 
             (this.shipToAddress!=null &&
              this.shipToAddress.equals(other.getShipToAddress()))) &&
            ((this.assetInformation==null && other.getAssetInformation()==null) || 
             (this.assetInformation!=null &&
              this.assetInformation.equals(other.getAssetInformation()))) &&
            ((this.materialDetails==null && other.getMaterialDetails()==null) || 
             (this.materialDetails!=null &&
              java.util.Arrays.equals(this.materialDetails, other.getMaterialDetails()))) &&
            ((this.customerReferenceNumber==null && other.getCustomerReferenceNumber()==null) || 
             (this.customerReferenceNumber!=null &&
              this.customerReferenceNumber.equals(other.getCustomerReferenceNumber()))) &&
            ((this.vendorName==null && other.getVendorName()==null) || 
             (this.vendorName!=null &&
              this.vendorName.equals(other.getVendorName()))) &&
            ((this.vendorId==null && other.getVendorId()==null) || 
             (this.vendorId!=null &&
              this.vendorId.equals(other.getVendorId()))) &&
            ((this.costCenter==null && other.getCostCenter()==null) || 
             (this.costCenter!=null &&
              this.costCenter.equals(other.getCostCenter()))) &&
            ((this.installationOnlyFlag==null && other.getInstallationOnlyFlag()==null) || 
             (this.installationOnlyFlag!=null &&
              this.installationOnlyFlag.equals(other.getInstallationOnlyFlag()))) &&
            ((this.serviceRequestComments==null && other.getServiceRequestComments()==null) || 
             (this.serviceRequestComments!=null &&
              this.serviceRequestComments.equals(other.getServiceRequestComments()))) &&
            ((this.relatedServiceRequestNumber==null && other.getRelatedServiceRequestNumber()==null) || 
             (this.relatedServiceRequestNumber!=null &&
              this.relatedServiceRequestNumber.equals(other.getRelatedServiceRequestNumber()))) &&
            ((this.billingModel==null && other.getBillingModel()==null) || 
             (this.billingModel!=null &&
              this.billingModel.equals(other.getBillingModel()))) &&
            ((this.attachmentNotes==null && other.getAttachmentNotes()==null) || 
             (this.attachmentNotes!=null &&
              this.attachmentNotes.equals(other.getAttachmentNotes()))) &&
            ((this.orderInformation==null && other.getOrderInformation()==null) || 
             (this.orderInformation!=null &&
              this.orderInformation.equals(other.getOrderInformation()))) &&
            ((this.paymentInformation==null && other.getPaymentInformation()==null) || 
             (this.paymentInformation!=null &&
              this.paymentInformation.equals(other.getPaymentInformation())));
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
        if (getServiceRequestStatus() != null) {
            _hashCode += getServiceRequestStatus().hashCode();
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
        if (getServiceRequestDescription() != null) {
            _hashCode += getServiceRequestDescription().hashCode();
        }
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
        }
        if (getPrimaryContact() != null) {
            _hashCode += getPrimaryContact().hashCode();
        }
        if (getSecondaryContact() != null) {
            _hashCode += getSecondaryContact().hashCode();
        }
        if (getAccountInformation() != null) {
            _hashCode += getAccountInformation().hashCode();
        }
        if (getShipToAddress() != null) {
            _hashCode += getShipToAddress().hashCode();
        }
        if (getAssetInformation() != null) {
            _hashCode += getAssetInformation().hashCode();
        }
        if (getMaterialDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMaterialDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMaterialDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCustomerReferenceNumber() != null) {
            _hashCode += getCustomerReferenceNumber().hashCode();
        }
        if (getVendorName() != null) {
            _hashCode += getVendorName().hashCode();
        }
        if (getVendorId() != null) {
            _hashCode += getVendorId().hashCode();
        }
        if (getCostCenter() != null) {
            _hashCode += getCostCenter().hashCode();
        }
        if (getInstallationOnlyFlag() != null) {
            _hashCode += getInstallationOnlyFlag().hashCode();
        }
        if (getServiceRequestComments() != null) {
            _hashCode += getServiceRequestComments().hashCode();
        }
        if (getRelatedServiceRequestNumber() != null) {
            _hashCode += getRelatedServiceRequestNumber().hashCode();
        }
        if (getBillingModel() != null) {
            _hashCode += getBillingModel().hashCode();
        }
        if (getAttachmentNotes() != null) {
            _hashCode += getAttachmentNotes().hashCode();
        }
        if (getOrderInformation() != null) {
            _hashCode += getOrderInformation().hashCode();
        }
        if (getPaymentInformation() != null) {
            _hashCode += getPaymentInformation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsumablesServiceRequestData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestData"));
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
        elemField.setFieldName("serviceRequestStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestStatus"));
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
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
        elemField.setFieldName("accountInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAccount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipToAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipToAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetInformation2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("materialDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MaterialDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "MaterialDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfMaterialDetailsItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vendorName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VendorName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vendorId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VendorId"));
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
        elemField.setFieldName("installationOnlyFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstallationOnlyFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestComments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestComments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedServiceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelatedServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingModel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BillingModel"));
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
        elemField.setFieldName("orderInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrderInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OrderInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaymentInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelPaymentDetails"));
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
