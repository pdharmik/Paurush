/**
 * ServiceRequest2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class ServiceRequest2  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestType;

    private java.lang.String serviceRequestStatus;

    private java.lang.String serviceRequestDate;

    private java.lang.String serviceRequestSource;

    private java.lang.String requestedService;

    private java.lang.String requestedServiceAction;

    private java.lang.String serviceRequestRegion;

    private java.lang.String accountName;

    private com.lexmark.srHistory.Address accountAddress;

    private java.lang.String referenceNumber;

    private java.lang.String relatedServiceRequest;

    private java.lang.String coveredService;

    private java.lang.String expeditedFlag;

    private com.lexmark.srHistory.Contact primaryContact;

    private com.lexmark.srHistory.Contact secondaryContact;

    private com.lexmark.srHistory.Contact requester;

    private com.lexmark.srHistory.ProductDetails[] productDetails;

    private com.lexmark.srHistory.OrderDetails[] orders;

    public ServiceRequest2() {
    }

    public ServiceRequest2(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestType,
           java.lang.String serviceRequestStatus,
           java.lang.String serviceRequestDate,
           java.lang.String serviceRequestSource,
           java.lang.String requestedService,
           java.lang.String requestedServiceAction,
           java.lang.String serviceRequestRegion,
           java.lang.String accountName,
           com.lexmark.srHistory.Address accountAddress,
           java.lang.String referenceNumber,
           java.lang.String relatedServiceRequest,
           java.lang.String coveredService,
           java.lang.String expeditedFlag,
           com.lexmark.srHistory.Contact primaryContact,
           com.lexmark.srHistory.Contact secondaryContact,
           com.lexmark.srHistory.Contact requester,
           com.lexmark.srHistory.ProductDetails[] productDetails,
           com.lexmark.srHistory.OrderDetails[] orders) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestType = serviceRequestType;
           this.serviceRequestStatus = serviceRequestStatus;
           this.serviceRequestDate = serviceRequestDate;
           this.serviceRequestSource = serviceRequestSource;
           this.requestedService = requestedService;
           this.requestedServiceAction = requestedServiceAction;
           this.serviceRequestRegion = serviceRequestRegion;
           this.accountName = accountName;
           this.accountAddress = accountAddress;
           this.referenceNumber = referenceNumber;
           this.relatedServiceRequest = relatedServiceRequest;
           this.coveredService = coveredService;
           this.expeditedFlag = expeditedFlag;
           this.primaryContact = primaryContact;
           this.secondaryContact = secondaryContact;
           this.requester = requester;
           this.productDetails = productDetails;
           this.orders = orders;
    }


    /**
     * Gets the serviceRequestNumber value for this ServiceRequest2.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this ServiceRequest2.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestType value for this ServiceRequest2.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this ServiceRequest2.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the serviceRequestStatus value for this ServiceRequest2.
     * 
     * @return serviceRequestStatus
     */
    public java.lang.String getServiceRequestStatus() {
        return serviceRequestStatus;
    }


    /**
     * Sets the serviceRequestStatus value for this ServiceRequest2.
     * 
     * @param serviceRequestStatus
     */
    public void setServiceRequestStatus(java.lang.String serviceRequestStatus) {
        this.serviceRequestStatus = serviceRequestStatus;
    }


    /**
     * Gets the serviceRequestDate value for this ServiceRequest2.
     * 
     * @return serviceRequestDate
     */
    public java.lang.String getServiceRequestDate() {
        return serviceRequestDate;
    }


    /**
     * Sets the serviceRequestDate value for this ServiceRequest2.
     * 
     * @param serviceRequestDate
     */
    public void setServiceRequestDate(java.lang.String serviceRequestDate) {
        this.serviceRequestDate = serviceRequestDate;
    }


    /**
     * Gets the serviceRequestSource value for this ServiceRequest2.
     * 
     * @return serviceRequestSource
     */
    public java.lang.String getServiceRequestSource() {
        return serviceRequestSource;
    }


    /**
     * Sets the serviceRequestSource value for this ServiceRequest2.
     * 
     * @param serviceRequestSource
     */
    public void setServiceRequestSource(java.lang.String serviceRequestSource) {
        this.serviceRequestSource = serviceRequestSource;
    }


    /**
     * Gets the requestedService value for this ServiceRequest2.
     * 
     * @return requestedService
     */
    public java.lang.String getRequestedService() {
        return requestedService;
    }


    /**
     * Sets the requestedService value for this ServiceRequest2.
     * 
     * @param requestedService
     */
    public void setRequestedService(java.lang.String requestedService) {
        this.requestedService = requestedService;
    }


    /**
     * Gets the requestedServiceAction value for this ServiceRequest2.
     * 
     * @return requestedServiceAction
     */
    public java.lang.String getRequestedServiceAction() {
        return requestedServiceAction;
    }


    /**
     * Sets the requestedServiceAction value for this ServiceRequest2.
     * 
     * @param requestedServiceAction
     */
    public void setRequestedServiceAction(java.lang.String requestedServiceAction) {
        this.requestedServiceAction = requestedServiceAction;
    }


    /**
     * Gets the serviceRequestRegion value for this ServiceRequest2.
     * 
     * @return serviceRequestRegion
     */
    public java.lang.String getServiceRequestRegion() {
        return serviceRequestRegion;
    }


    /**
     * Sets the serviceRequestRegion value for this ServiceRequest2.
     * 
     * @param serviceRequestRegion
     */
    public void setServiceRequestRegion(java.lang.String serviceRequestRegion) {
        this.serviceRequestRegion = serviceRequestRegion;
    }


    /**
     * Gets the accountName value for this ServiceRequest2.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this ServiceRequest2.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the accountAddress value for this ServiceRequest2.
     * 
     * @return accountAddress
     */
    public com.lexmark.srHistory.Address getAccountAddress() {
        return accountAddress;
    }


    /**
     * Sets the accountAddress value for this ServiceRequest2.
     * 
     * @param accountAddress
     */
    public void setAccountAddress(com.lexmark.srHistory.Address accountAddress) {
        this.accountAddress = accountAddress;
    }


    /**
     * Gets the referenceNumber value for this ServiceRequest2.
     * 
     * @return referenceNumber
     */
    public java.lang.String getReferenceNumber() {
        return referenceNumber;
    }


    /**
     * Sets the referenceNumber value for this ServiceRequest2.
     * 
     * @param referenceNumber
     */
    public void setReferenceNumber(java.lang.String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }


    /**
     * Gets the relatedServiceRequest value for this ServiceRequest2.
     * 
     * @return relatedServiceRequest
     */
    public java.lang.String getRelatedServiceRequest() {
        return relatedServiceRequest;
    }


    /**
     * Sets the relatedServiceRequest value for this ServiceRequest2.
     * 
     * @param relatedServiceRequest
     */
    public void setRelatedServiceRequest(java.lang.String relatedServiceRequest) {
        this.relatedServiceRequest = relatedServiceRequest;
    }


    /**
     * Gets the coveredService value for this ServiceRequest2.
     * 
     * @return coveredService
     */
    public java.lang.String getCoveredService() {
        return coveredService;
    }


    /**
     * Sets the coveredService value for this ServiceRequest2.
     * 
     * @param coveredService
     */
    public void setCoveredService(java.lang.String coveredService) {
        this.coveredService = coveredService;
    }


    /**
     * Gets the expeditedFlag value for this ServiceRequest2.
     * 
     * @return expeditedFlag
     */
    public java.lang.String getExpeditedFlag() {
        return expeditedFlag;
    }


    /**
     * Sets the expeditedFlag value for this ServiceRequest2.
     * 
     * @param expeditedFlag
     */
    public void setExpeditedFlag(java.lang.String expeditedFlag) {
        this.expeditedFlag = expeditedFlag;
    }


    /**
     * Gets the primaryContact value for this ServiceRequest2.
     * 
     * @return primaryContact
     */
    public com.lexmark.srHistory.Contact getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this ServiceRequest2.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.srHistory.Contact primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the secondaryContact value for this ServiceRequest2.
     * 
     * @return secondaryContact
     */
    public com.lexmark.srHistory.Contact getSecondaryContact() {
        return secondaryContact;
    }


    /**
     * Sets the secondaryContact value for this ServiceRequest2.
     * 
     * @param secondaryContact
     */
    public void setSecondaryContact(com.lexmark.srHistory.Contact secondaryContact) {
        this.secondaryContact = secondaryContact;
    }


    /**
     * Gets the requester value for this ServiceRequest2.
     * 
     * @return requester
     */
    public com.lexmark.srHistory.Contact getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ServiceRequest2.
     * 
     * @param requester
     */
    public void setRequester(com.lexmark.srHistory.Contact requester) {
        this.requester = requester;
    }


    /**
     * Gets the productDetails value for this ServiceRequest2.
     * 
     * @return productDetails
     */
    public com.lexmark.srHistory.ProductDetails[] getProductDetails() {
        return productDetails;
    }


    /**
     * Sets the productDetails value for this ServiceRequest2.
     * 
     * @param productDetails
     */
    public void setProductDetails(com.lexmark.srHistory.ProductDetails[] productDetails) {
        this.productDetails = productDetails;
    }

    public com.lexmark.srHistory.ProductDetails getProductDetails(int i) {
        return this.productDetails[i];
    }

    public void setProductDetails(int i, com.lexmark.srHistory.ProductDetails _value) {
        this.productDetails[i] = _value;
    }


    /**
     * Gets the orders value for this ServiceRequest2.
     * 
     * @return orders
     */
    public com.lexmark.srHistory.OrderDetails[] getOrders() {
        return orders;
    }


    /**
     * Sets the orders value for this ServiceRequest2.
     * 
     * @param orders
     */
    public void setOrders(com.lexmark.srHistory.OrderDetails[] orders) {
        this.orders = orders;
    }

    public com.lexmark.srHistory.OrderDetails getOrders(int i) {
        return this.orders[i];
    }

    public void setOrders(int i, com.lexmark.srHistory.OrderDetails _value) {
        this.orders[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequest2)) return false;
        ServiceRequest2 other = (ServiceRequest2) obj;
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
            ((this.serviceRequestStatus==null && other.getServiceRequestStatus()==null) || 
             (this.serviceRequestStatus!=null &&
              this.serviceRequestStatus.equals(other.getServiceRequestStatus()))) &&
            ((this.serviceRequestDate==null && other.getServiceRequestDate()==null) || 
             (this.serviceRequestDate!=null &&
              this.serviceRequestDate.equals(other.getServiceRequestDate()))) &&
            ((this.serviceRequestSource==null && other.getServiceRequestSource()==null) || 
             (this.serviceRequestSource!=null &&
              this.serviceRequestSource.equals(other.getServiceRequestSource()))) &&
            ((this.requestedService==null && other.getRequestedService()==null) || 
             (this.requestedService!=null &&
              this.requestedService.equals(other.getRequestedService()))) &&
            ((this.requestedServiceAction==null && other.getRequestedServiceAction()==null) || 
             (this.requestedServiceAction!=null &&
              this.requestedServiceAction.equals(other.getRequestedServiceAction()))) &&
            ((this.serviceRequestRegion==null && other.getServiceRequestRegion()==null) || 
             (this.serviceRequestRegion!=null &&
              this.serviceRequestRegion.equals(other.getServiceRequestRegion()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            ((this.accountAddress==null && other.getAccountAddress()==null) || 
             (this.accountAddress!=null &&
              this.accountAddress.equals(other.getAccountAddress()))) &&
            ((this.referenceNumber==null && other.getReferenceNumber()==null) || 
             (this.referenceNumber!=null &&
              this.referenceNumber.equals(other.getReferenceNumber()))) &&
            ((this.relatedServiceRequest==null && other.getRelatedServiceRequest()==null) || 
             (this.relatedServiceRequest!=null &&
              this.relatedServiceRequest.equals(other.getRelatedServiceRequest()))) &&
            ((this.coveredService==null && other.getCoveredService()==null) || 
             (this.coveredService!=null &&
              this.coveredService.equals(other.getCoveredService()))) &&
            ((this.expeditedFlag==null && other.getExpeditedFlag()==null) || 
             (this.expeditedFlag!=null &&
              this.expeditedFlag.equals(other.getExpeditedFlag()))) &&
            ((this.primaryContact==null && other.getPrimaryContact()==null) || 
             (this.primaryContact!=null &&
              this.primaryContact.equals(other.getPrimaryContact()))) &&
            ((this.secondaryContact==null && other.getSecondaryContact()==null) || 
             (this.secondaryContact!=null &&
              this.secondaryContact.equals(other.getSecondaryContact()))) &&
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.productDetails==null && other.getProductDetails()==null) || 
             (this.productDetails!=null &&
              java.util.Arrays.equals(this.productDetails, other.getProductDetails()))) &&
            ((this.orders==null && other.getOrders()==null) || 
             (this.orders!=null &&
              java.util.Arrays.equals(this.orders, other.getOrders())));
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
        if (getServiceRequestStatus() != null) {
            _hashCode += getServiceRequestStatus().hashCode();
        }
        if (getServiceRequestDate() != null) {
            _hashCode += getServiceRequestDate().hashCode();
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
        if (getServiceRequestRegion() != null) {
            _hashCode += getServiceRequestRegion().hashCode();
        }
        if (getAccountName() != null) {
            _hashCode += getAccountName().hashCode();
        }
        if (getAccountAddress() != null) {
            _hashCode += getAccountAddress().hashCode();
        }
        if (getReferenceNumber() != null) {
            _hashCode += getReferenceNumber().hashCode();
        }
        if (getRelatedServiceRequest() != null) {
            _hashCode += getRelatedServiceRequest().hashCode();
        }
        if (getCoveredService() != null) {
            _hashCode += getCoveredService().hashCode();
        }
        if (getExpeditedFlag() != null) {
            _hashCode += getExpeditedFlag().hashCode();
        }
        if (getPrimaryContact() != null) {
            _hashCode += getPrimaryContact().hashCode();
        }
        if (getSecondaryContact() != null) {
            _hashCode += getSecondaryContact().hashCode();
        }
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
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
        if (getOrders() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOrders());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOrders(), i);
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
        new org.apache.axis.description.TypeDesc(ServiceRequest2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequest2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("serviceRequestStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestDate"));
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
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestRegion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestRegion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "Address"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedServiceRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelatedServiceRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coveredService");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CoveredService"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expeditedFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExpeditedFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrimaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "Contact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SecondaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "Contact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "Contact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ProductDetails"));
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orders");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Orders"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "OrderDetails"));
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
