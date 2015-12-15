/**
 * ChangeManagementServiceRequestData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ChangeManagementServiceRequestData  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestType;

    private java.lang.String serviceRequestSource;

    private java.lang.String requestedService;

    private java.lang.String requestedServiceAction;

    private java.lang.String serviceRequestDate;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountInformation accountInformation;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester requester;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact primaryContact;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact secondaryContact;

    private java.lang.String serviceRequestDescription;

    private java.lang.String customerReferenceNumber;

    private java.lang.String costCenter;

    private java.lang.String relatedServiceRequestNumber;

    private java.lang.String attachmentNotes;

    public ChangeManagementServiceRequestData() {
    }

    public ChangeManagementServiceRequestData(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestType,
           java.lang.String serviceRequestSource,
           java.lang.String requestedService,
           java.lang.String requestedServiceAction,
           java.lang.String serviceRequestDate,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountInformation accountInformation,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester requester,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact primaryContact,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact secondaryContact,
           java.lang.String serviceRequestDescription,
           java.lang.String customerReferenceNumber,
           java.lang.String costCenter,
           java.lang.String relatedServiceRequestNumber,
           java.lang.String attachmentNotes) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestType = serviceRequestType;
           this.serviceRequestSource = serviceRequestSource;
           this.requestedService = requestedService;
           this.requestedServiceAction = requestedServiceAction;
           this.serviceRequestDate = serviceRequestDate;
           this.accountInformation = accountInformation;
           this.requester = requester;
           this.primaryContact = primaryContact;
           this.secondaryContact = secondaryContact;
           this.serviceRequestDescription = serviceRequestDescription;
           this.customerReferenceNumber = customerReferenceNumber;
           this.costCenter = costCenter;
           this.relatedServiceRequestNumber = relatedServiceRequestNumber;
           this.attachmentNotes = attachmentNotes;
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
     * Gets the accountInformation value for this ChangeManagementServiceRequestData.
     * 
     * @return accountInformation
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountInformation getAccountInformation() {
        return accountInformation;
    }


    /**
     * Sets the accountInformation value for this ChangeManagementServiceRequestData.
     * 
     * @param accountInformation
     */
    public void setAccountInformation(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AccountInformation accountInformation) {
        this.accountInformation = accountInformation;
    }


    /**
     * Gets the requester value for this ChangeManagementServiceRequestData.
     * 
     * @return requester
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ChangeManagementServiceRequestData.
     * 
     * @param requester
     */
    public void setRequester(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester requester) {
        this.requester = requester;
    }


    /**
     * Gets the primaryContact value for this ChangeManagementServiceRequestData.
     * 
     * @return primaryContact
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this ChangeManagementServiceRequestData.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the secondaryContact value for this ChangeManagementServiceRequestData.
     * 
     * @return secondaryContact
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact getSecondaryContact() {
        return secondaryContact;
    }


    /**
     * Sets the secondaryContact value for this ChangeManagementServiceRequestData.
     * 
     * @param secondaryContact
     */
    public void setSecondaryContact(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact secondaryContact) {
        this.secondaryContact = secondaryContact;
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
            ((this.serviceRequestDescription==null && other.getServiceRequestDescription()==null) || 
             (this.serviceRequestDescription!=null &&
              this.serviceRequestDescription.equals(other.getServiceRequestDescription()))) &&
            ((this.customerReferenceNumber==null && other.getCustomerReferenceNumber()==null) || 
             (this.customerReferenceNumber!=null &&
              this.customerReferenceNumber.equals(other.getCustomerReferenceNumber()))) &&
            ((this.costCenter==null && other.getCostCenter()==null) || 
             (this.costCenter!=null &&
              this.costCenter.equals(other.getCostCenter()))) &&
            ((this.relatedServiceRequestNumber==null && other.getRelatedServiceRequestNumber()==null) || 
             (this.relatedServiceRequestNumber!=null &&
              this.relatedServiceRequestNumber.equals(other.getRelatedServiceRequestNumber()))) &&
            ((this.attachmentNotes==null && other.getAttachmentNotes()==null) || 
             (this.attachmentNotes!=null &&
              this.attachmentNotes.equals(other.getAttachmentNotes())));
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
        if (getServiceRequestDescription() != null) {
            _hashCode += getServiceRequestDescription().hashCode();
        }
        if (getCustomerReferenceNumber() != null) {
            _hashCode += getCustomerReferenceNumber().hashCode();
        }
        if (getCostCenter() != null) {
            _hashCode += getCostCenter().hashCode();
        }
        if (getRelatedServiceRequestNumber() != null) {
            _hashCode += getRelatedServiceRequestNumber().hashCode();
        }
        if (getAttachmentNotes() != null) {
            _hashCode += getAttachmentNotes().hashCode();
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
        elemField.setFieldName("accountInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Requester"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrimaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PrimaryContact"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SecondaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SecondaryContact"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
        elemField.setFieldName("relatedServiceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelatedServiceRequestNumber"));
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
