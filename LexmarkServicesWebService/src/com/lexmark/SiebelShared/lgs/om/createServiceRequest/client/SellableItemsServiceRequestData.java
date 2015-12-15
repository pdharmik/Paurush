/**
 * SellableItemsServiceRequestData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class SellableItemsServiceRequestData  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestType;

    private java.lang.String serviceRequestSource;

    private java.lang.String serviceRequestStatus;

    private java.lang.String requestedService;

    private java.lang.String requestedServiceAction;

    private java.lang.String serviceRequestDate;

    private java.lang.String serviceRequestDescription;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAccount accountInformation;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact requester;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact primaryContact;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact secondaryContact;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress shipToAddress;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ProductDetails[] productDetails;

    private java.lang.String customerReferenceNumber;

    private java.lang.String costCenter;

    private java.lang.String installationOnlyFlag;

    private java.lang.String serviceRequestComments;

    private java.lang.String relatedServiceRequestNumber;

    private java.lang.String relatedAgreementNumber;

    private java.lang.String billingModel;

    private java.lang.String attachmentNotes;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OrderInformation2 orderInformation;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentDetails paymentInformation;

    public SellableItemsServiceRequestData() {
    }

    public SellableItemsServiceRequestData(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestType,
           java.lang.String serviceRequestSource,
           java.lang.String serviceRequestStatus,
           java.lang.String requestedService,
           java.lang.String requestedServiceAction,
           java.lang.String serviceRequestDate,
           java.lang.String serviceRequestDescription,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAccount accountInformation,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact requester,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact primaryContact,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact secondaryContact,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress shipToAddress,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ProductDetails[] productDetails,
           java.lang.String customerReferenceNumber,
           java.lang.String costCenter,
           java.lang.String installationOnlyFlag,
           java.lang.String serviceRequestComments,
           java.lang.String relatedServiceRequestNumber,
           java.lang.String relatedAgreementNumber,
           java.lang.String billingModel,
           java.lang.String attachmentNotes,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OrderInformation2 orderInformation,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentDetails paymentInformation) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestType = serviceRequestType;
           this.serviceRequestSource = serviceRequestSource;
           this.serviceRequestStatus = serviceRequestStatus;
           this.requestedService = requestedService;
           this.requestedServiceAction = requestedServiceAction;
           this.serviceRequestDate = serviceRequestDate;
           this.serviceRequestDescription = serviceRequestDescription;
           this.accountInformation = accountInformation;
           this.requester = requester;
           this.primaryContact = primaryContact;
           this.secondaryContact = secondaryContact;
           this.shipToAddress = shipToAddress;
           this.productDetails = productDetails;
           this.customerReferenceNumber = customerReferenceNumber;
           this.costCenter = costCenter;
           this.installationOnlyFlag = installationOnlyFlag;
           this.serviceRequestComments = serviceRequestComments;
           this.relatedServiceRequestNumber = relatedServiceRequestNumber;
           this.relatedAgreementNumber = relatedAgreementNumber;
           this.billingModel = billingModel;
           this.attachmentNotes = attachmentNotes;
           this.orderInformation = orderInformation;
           this.paymentInformation = paymentInformation;
    }


    /**
     * Gets the serviceRequestNumber value for this SellableItemsServiceRequestData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this SellableItemsServiceRequestData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestType value for this SellableItemsServiceRequestData.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this SellableItemsServiceRequestData.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the serviceRequestSource value for this SellableItemsServiceRequestData.
     * 
     * @return serviceRequestSource
     */
    public java.lang.String getServiceRequestSource() {
        return serviceRequestSource;
    }


    /**
     * Sets the serviceRequestSource value for this SellableItemsServiceRequestData.
     * 
     * @param serviceRequestSource
     */
    public void setServiceRequestSource(java.lang.String serviceRequestSource) {
        this.serviceRequestSource = serviceRequestSource;
    }


    /**
     * Gets the serviceRequestStatus value for this SellableItemsServiceRequestData.
     * 
     * @return serviceRequestStatus
     */
    public java.lang.String getServiceRequestStatus() {
        return serviceRequestStatus;
    }


    /**
     * Sets the serviceRequestStatus value for this SellableItemsServiceRequestData.
     * 
     * @param serviceRequestStatus
     */
    public void setServiceRequestStatus(java.lang.String serviceRequestStatus) {
        this.serviceRequestStatus = serviceRequestStatus;
    }


    /**
     * Gets the requestedService value for this SellableItemsServiceRequestData.
     * 
     * @return requestedService
     */
    public java.lang.String getRequestedService() {
        return requestedService;
    }


    /**
     * Sets the requestedService value for this SellableItemsServiceRequestData.
     * 
     * @param requestedService
     */
    public void setRequestedService(java.lang.String requestedService) {
        this.requestedService = requestedService;
    }


    /**
     * Gets the requestedServiceAction value for this SellableItemsServiceRequestData.
     * 
     * @return requestedServiceAction
     */
    public java.lang.String getRequestedServiceAction() {
        return requestedServiceAction;
    }


    /**
     * Sets the requestedServiceAction value for this SellableItemsServiceRequestData.
     * 
     * @param requestedServiceAction
     */
    public void setRequestedServiceAction(java.lang.String requestedServiceAction) {
        this.requestedServiceAction = requestedServiceAction;
    }


    /**
     * Gets the serviceRequestDate value for this SellableItemsServiceRequestData.
     * 
     * @return serviceRequestDate
     */
    public java.lang.String getServiceRequestDate() {
        return serviceRequestDate;
    }


    /**
     * Sets the serviceRequestDate value for this SellableItemsServiceRequestData.
     * 
     * @param serviceRequestDate
     */
    public void setServiceRequestDate(java.lang.String serviceRequestDate) {
        this.serviceRequestDate = serviceRequestDate;
    }


    /**
     * Gets the serviceRequestDescription value for this SellableItemsServiceRequestData.
     * 
     * @return serviceRequestDescription
     */
    public java.lang.String getServiceRequestDescription() {
        return serviceRequestDescription;
    }


    /**
     * Sets the serviceRequestDescription value for this SellableItemsServiceRequestData.
     * 
     * @param serviceRequestDescription
     */
    public void setServiceRequestDescription(java.lang.String serviceRequestDescription) {
        this.serviceRequestDescription = serviceRequestDescription;
    }


    /**
     * Gets the accountInformation value for this SellableItemsServiceRequestData.
     * 
     * @return accountInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAccount getAccountInformation() {
        return accountInformation;
    }


    /**
     * Sets the accountInformation value for this SellableItemsServiceRequestData.
     * 
     * @param accountInformation
     */
    public void setAccountInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAccount accountInformation) {
        this.accountInformation = accountInformation;
    }


    /**
     * Gets the requester value for this SellableItemsServiceRequestData.
     * 
     * @return requester
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this SellableItemsServiceRequestData.
     * 
     * @param requester
     */
    public void setRequester(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact requester) {
        this.requester = requester;
    }


    /**
     * Gets the primaryContact value for this SellableItemsServiceRequestData.
     * 
     * @return primaryContact
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this SellableItemsServiceRequestData.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the secondaryContact value for this SellableItemsServiceRequestData.
     * 
     * @return secondaryContact
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getSecondaryContact() {
        return secondaryContact;
    }


    /**
     * Sets the secondaryContact value for this SellableItemsServiceRequestData.
     * 
     * @param secondaryContact
     */
    public void setSecondaryContact(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact secondaryContact) {
        this.secondaryContact = secondaryContact;
    }


    /**
     * Gets the shipToAddress value for this SellableItemsServiceRequestData.
     * 
     * @return shipToAddress
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress getShipToAddress() {
        return shipToAddress;
    }


    /**
     * Sets the shipToAddress value for this SellableItemsServiceRequestData.
     * 
     * @param shipToAddress
     */
    public void setShipToAddress(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress shipToAddress) {
        this.shipToAddress = shipToAddress;
    }


    /**
     * Gets the productDetails value for this SellableItemsServiceRequestData.
     * 
     * @return productDetails
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ProductDetails[] getProductDetails() {
        return productDetails;
    }


    /**
     * Sets the productDetails value for this SellableItemsServiceRequestData.
     * 
     * @param productDetails
     */
    public void setProductDetails(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ProductDetails[] productDetails) {
        this.productDetails = productDetails;
    }


    /**
     * Gets the customerReferenceNumber value for this SellableItemsServiceRequestData.
     * 
     * @return customerReferenceNumber
     */
    public java.lang.String getCustomerReferenceNumber() {
        return customerReferenceNumber;
    }


    /**
     * Sets the customerReferenceNumber value for this SellableItemsServiceRequestData.
     * 
     * @param customerReferenceNumber
     */
    public void setCustomerReferenceNumber(java.lang.String customerReferenceNumber) {
        this.customerReferenceNumber = customerReferenceNumber;
    }


    /**
     * Gets the costCenter value for this SellableItemsServiceRequestData.
     * 
     * @return costCenter
     */
    public java.lang.String getCostCenter() {
        return costCenter;
    }


    /**
     * Sets the costCenter value for this SellableItemsServiceRequestData.
     * 
     * @param costCenter
     */
    public void setCostCenter(java.lang.String costCenter) {
        this.costCenter = costCenter;
    }


    /**
     * Gets the installationOnlyFlag value for this SellableItemsServiceRequestData.
     * 
     * @return installationOnlyFlag
     */
    public java.lang.String getInstallationOnlyFlag() {
        return installationOnlyFlag;
    }


    /**
     * Sets the installationOnlyFlag value for this SellableItemsServiceRequestData.
     * 
     * @param installationOnlyFlag
     */
    public void setInstallationOnlyFlag(java.lang.String installationOnlyFlag) {
        this.installationOnlyFlag = installationOnlyFlag;
    }


    /**
     * Gets the serviceRequestComments value for this SellableItemsServiceRequestData.
     * 
     * @return serviceRequestComments
     */
    public java.lang.String getServiceRequestComments() {
        return serviceRequestComments;
    }


    /**
     * Sets the serviceRequestComments value for this SellableItemsServiceRequestData.
     * 
     * @param serviceRequestComments
     */
    public void setServiceRequestComments(java.lang.String serviceRequestComments) {
        this.serviceRequestComments = serviceRequestComments;
    }


    /**
     * Gets the relatedServiceRequestNumber value for this SellableItemsServiceRequestData.
     * 
     * @return relatedServiceRequestNumber
     */
    public java.lang.String getRelatedServiceRequestNumber() {
        return relatedServiceRequestNumber;
    }


    /**
     * Sets the relatedServiceRequestNumber value for this SellableItemsServiceRequestData.
     * 
     * @param relatedServiceRequestNumber
     */
    public void setRelatedServiceRequestNumber(java.lang.String relatedServiceRequestNumber) {
        this.relatedServiceRequestNumber = relatedServiceRequestNumber;
    }


    /**
     * Gets the relatedAgreementNumber value for this SellableItemsServiceRequestData.
     * 
     * @return relatedAgreementNumber
     */
    public java.lang.String getRelatedAgreementNumber() {
        return relatedAgreementNumber;
    }


    /**
     * Sets the relatedAgreementNumber value for this SellableItemsServiceRequestData.
     * 
     * @param relatedAgreementNumber
     */
    public void setRelatedAgreementNumber(java.lang.String relatedAgreementNumber) {
        this.relatedAgreementNumber = relatedAgreementNumber;
    }


    /**
     * Gets the billingModel value for this SellableItemsServiceRequestData.
     * 
     * @return billingModel
     */
    public java.lang.String getBillingModel() {
        return billingModel;
    }


    /**
     * Sets the billingModel value for this SellableItemsServiceRequestData.
     * 
     * @param billingModel
     */
    public void setBillingModel(java.lang.String billingModel) {
        this.billingModel = billingModel;
    }


    /**
     * Gets the attachmentNotes value for this SellableItemsServiceRequestData.
     * 
     * @return attachmentNotes
     */
    public java.lang.String getAttachmentNotes() {
        return attachmentNotes;
    }


    /**
     * Sets the attachmentNotes value for this SellableItemsServiceRequestData.
     * 
     * @param attachmentNotes
     */
    public void setAttachmentNotes(java.lang.String attachmentNotes) {
        this.attachmentNotes = attachmentNotes;
    }


    /**
     * Gets the orderInformation value for this SellableItemsServiceRequestData.
     * 
     * @return orderInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OrderInformation2 getOrderInformation() {
        return orderInformation;
    }


    /**
     * Sets the orderInformation value for this SellableItemsServiceRequestData.
     * 
     * @param orderInformation
     */
    public void setOrderInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OrderInformation2 orderInformation) {
        this.orderInformation = orderInformation;
    }


    /**
     * Gets the paymentInformation value for this SellableItemsServiceRequestData.
     * 
     * @return paymentInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentDetails getPaymentInformation() {
        return paymentInformation;
    }


    /**
     * Sets the paymentInformation value for this SellableItemsServiceRequestData.
     * 
     * @param paymentInformation
     */
    public void setPaymentInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentDetails paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SellableItemsServiceRequestData)) return false;
        SellableItemsServiceRequestData other = (SellableItemsServiceRequestData) obj;
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
            ((this.accountInformation==null && other.getAccountInformation()==null) || 
             (this.accountInformation!=null &&
              this.accountInformation.equals(other.getAccountInformation()))) &&
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.primaryContact==null && other.getPrimaryContact()==null) || 
             (this.primaryContact!=null &&
              this.primaryContact.equals(other.getPrimaryContact()))) &&
            ((this.secondaryContact==null && other.getSecondaryContact()==null) || 
             (this.secondaryContact!=null &&
              this.secondaryContact.equals(other.getSecondaryContact()))) &&
            ((this.shipToAddress==null && other.getShipToAddress()==null) || 
             (this.shipToAddress!=null &&
              this.shipToAddress.equals(other.getShipToAddress()))) &&
            ((this.productDetails==null && other.getProductDetails()==null) || 
             (this.productDetails!=null &&
              java.util.Arrays.equals(this.productDetails, other.getProductDetails()))) &&
            ((this.customerReferenceNumber==null && other.getCustomerReferenceNumber()==null) || 
             (this.customerReferenceNumber!=null &&
              this.customerReferenceNumber.equals(other.getCustomerReferenceNumber()))) &&
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
            ((this.relatedAgreementNumber==null && other.getRelatedAgreementNumber()==null) || 
             (this.relatedAgreementNumber!=null &&
              this.relatedAgreementNumber.equals(other.getRelatedAgreementNumber()))) &&
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
        if (getAccountInformation() != null) {
            _hashCode += getAccountInformation().hashCode();
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
        if (getShipToAddress() != null) {
            _hashCode += getShipToAddress().hashCode();
        }
        if (getProductDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProductDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProductDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCustomerReferenceNumber() != null) {
            _hashCode += getCustomerReferenceNumber().hashCode();
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
        if (getRelatedAgreementNumber() != null) {
            _hashCode += getRelatedAgreementNumber().hashCode();
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
        new org.apache.axis.description.TypeDesc(SellableItemsServiceRequestData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SellableItemsServiceRequestData"));
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
        elemField.setFieldName("accountInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAccount"));
        elemField.setNillable(true);
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
        elemField.setFieldName("shipToAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipToAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ProductDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfProductDetailsItem"));
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
        elemField.setFieldName("relatedAgreementNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelatedAgreementNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingModel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BillingModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OrderInformation2"));
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
