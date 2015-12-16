/**
 * StatusServiceRequestData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class StatusServiceRequestData  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestType;

    private java.lang.String serviceRequestStatus;

    private java.lang.String statusDate;

    private java.lang.String customerId;

    private java.lang.String customerName;

    private java.lang.String customerReferenceNumber;

    private java.lang.String serialNumber;

    private com.lexmark.SiebelShared.createServiceRequest.client.ShipmentInformation[] shipmentInformation;

    private com.lexmark.SiebelShared.createServiceRequest.client.DebriefInformation debriefInformation;

    public StatusServiceRequestData() {
    }

    public StatusServiceRequestData(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestType,
           java.lang.String serviceRequestStatus,
           java.lang.String statusDate,
           java.lang.String customerId,
           java.lang.String customerName,
           java.lang.String customerReferenceNumber,
           java.lang.String serialNumber,
           com.lexmark.SiebelShared.createServiceRequest.client.ShipmentInformation[] shipmentInformation,
           com.lexmark.SiebelShared.createServiceRequest.client.DebriefInformation debriefInformation) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestType = serviceRequestType;
           this.serviceRequestStatus = serviceRequestStatus;
           this.statusDate = statusDate;
           this.customerId = customerId;
           this.customerName = customerName;
           this.customerReferenceNumber = customerReferenceNumber;
           this.serialNumber = serialNumber;
           this.shipmentInformation = shipmentInformation;
           this.debriefInformation = debriefInformation;
    }


    /**
     * Gets the serviceRequestNumber value for this StatusServiceRequestData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this StatusServiceRequestData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestType value for this StatusServiceRequestData.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this StatusServiceRequestData.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the serviceRequestStatus value for this StatusServiceRequestData.
     * 
     * @return serviceRequestStatus
     */
    public java.lang.String getServiceRequestStatus() {
        return serviceRequestStatus;
    }


    /**
     * Sets the serviceRequestStatus value for this StatusServiceRequestData.
     * 
     * @param serviceRequestStatus
     */
    public void setServiceRequestStatus(java.lang.String serviceRequestStatus) {
        this.serviceRequestStatus = serviceRequestStatus;
    }


    /**
     * Gets the statusDate value for this StatusServiceRequestData.
     * 
     * @return statusDate
     */
    public java.lang.String getStatusDate() {
        return statusDate;
    }


    /**
     * Sets the statusDate value for this StatusServiceRequestData.
     * 
     * @param statusDate
     */
    public void setStatusDate(java.lang.String statusDate) {
        this.statusDate = statusDate;
    }


    /**
     * Gets the customerId value for this StatusServiceRequestData.
     * 
     * @return customerId
     */
    public java.lang.String getCustomerId() {
        return customerId;
    }


    /**
     * Sets the customerId value for this StatusServiceRequestData.
     * 
     * @param customerId
     */
    public void setCustomerId(java.lang.String customerId) {
        this.customerId = customerId;
    }


    /**
     * Gets the customerName value for this StatusServiceRequestData.
     * 
     * @return customerName
     */
    public java.lang.String getCustomerName() {
        return customerName;
    }


    /**
     * Sets the customerName value for this StatusServiceRequestData.
     * 
     * @param customerName
     */
    public void setCustomerName(java.lang.String customerName) {
        this.customerName = customerName;
    }


    /**
     * Gets the customerReferenceNumber value for this StatusServiceRequestData.
     * 
     * @return customerReferenceNumber
     */
    public java.lang.String getCustomerReferenceNumber() {
        return customerReferenceNumber;
    }


    /**
     * Sets the customerReferenceNumber value for this StatusServiceRequestData.
     * 
     * @param customerReferenceNumber
     */
    public void setCustomerReferenceNumber(java.lang.String customerReferenceNumber) {
        this.customerReferenceNumber = customerReferenceNumber;
    }


    /**
     * Gets the serialNumber value for this StatusServiceRequestData.
     * 
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this StatusServiceRequestData.
     * 
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }


    /**
     * Gets the shipmentInformation value for this StatusServiceRequestData.
     * 
     * @return shipmentInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.ShipmentInformation[] getShipmentInformation() {
        return shipmentInformation;
    }


    /**
     * Sets the shipmentInformation value for this StatusServiceRequestData.
     * 
     * @param shipmentInformation
     */
    public void setShipmentInformation(com.lexmark.SiebelShared.createServiceRequest.client.ShipmentInformation[] shipmentInformation) {
        this.shipmentInformation = shipmentInformation;
    }


    /**
     * Gets the debriefInformation value for this StatusServiceRequestData.
     * 
     * @return debriefInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.DebriefInformation getDebriefInformation() {
        return debriefInformation;
    }


    /**
     * Sets the debriefInformation value for this StatusServiceRequestData.
     * 
     * @param debriefInformation
     */
    public void setDebriefInformation(com.lexmark.SiebelShared.createServiceRequest.client.DebriefInformation debriefInformation) {
        this.debriefInformation = debriefInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusServiceRequestData)) return false;
        StatusServiceRequestData other = (StatusServiceRequestData) obj;
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
            ((this.statusDate==null && other.getStatusDate()==null) || 
             (this.statusDate!=null &&
              this.statusDate.equals(other.getStatusDate()))) &&
            ((this.customerId==null && other.getCustomerId()==null) || 
             (this.customerId!=null &&
              this.customerId.equals(other.getCustomerId()))) &&
            ((this.customerName==null && other.getCustomerName()==null) || 
             (this.customerName!=null &&
              this.customerName.equals(other.getCustomerName()))) &&
            ((this.customerReferenceNumber==null && other.getCustomerReferenceNumber()==null) || 
             (this.customerReferenceNumber!=null &&
              this.customerReferenceNumber.equals(other.getCustomerReferenceNumber()))) &&
            ((this.serialNumber==null && other.getSerialNumber()==null) || 
             (this.serialNumber!=null &&
              this.serialNumber.equals(other.getSerialNumber()))) &&
            ((this.shipmentInformation==null && other.getShipmentInformation()==null) || 
             (this.shipmentInformation!=null &&
              java.util.Arrays.equals(this.shipmentInformation, other.getShipmentInformation()))) &&
            ((this.debriefInformation==null && other.getDebriefInformation()==null) || 
             (this.debriefInformation!=null &&
              this.debriefInformation.equals(other.getDebriefInformation())));
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
        if (getStatusDate() != null) {
            _hashCode += getStatusDate().hashCode();
        }
        if (getCustomerId() != null) {
            _hashCode += getCustomerId().hashCode();
        }
        if (getCustomerName() != null) {
            _hashCode += getCustomerName().hashCode();
        }
        if (getCustomerReferenceNumber() != null) {
            _hashCode += getCustomerReferenceNumber().hashCode();
        }
        if (getSerialNumber() != null) {
            _hashCode += getSerialNumber().hashCode();
        }
        if (getShipmentInformation() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getShipmentInformation());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getShipmentInformation(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDebriefInformation() != null) {
            _hashCode += getDebriefInformation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusServiceRequestData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "StatusServiceRequestData"));
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StatusDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("serialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SerialNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipmentInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipmentInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ShipmentInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfShipmentInformationItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debriefInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DebriefInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DebriefInformation"));
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
