/**
 * EscalateServiceRequestData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class EscalateServiceRequestData  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String customerId;

    private java.lang.String customerRelatedDocumentNumber;

    private java.lang.String escalateReason;

    private java.lang.String description;

    public EscalateServiceRequestData() {
    }

    public EscalateServiceRequestData(
           java.lang.String serviceRequestNumber,
           java.lang.String customerId,
           java.lang.String customerRelatedDocumentNumber,
           java.lang.String escalateReason,
           java.lang.String description) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.customerId = customerId;
           this.customerRelatedDocumentNumber = customerRelatedDocumentNumber;
           this.escalateReason = escalateReason;
           this.description = description;
    }


    /**
     * Gets the serviceRequestNumber value for this EscalateServiceRequestData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this EscalateServiceRequestData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the customerId value for this EscalateServiceRequestData.
     * 
     * @return customerId
     */
    public java.lang.String getCustomerId() {
        return customerId;
    }


    /**
     * Sets the customerId value for this EscalateServiceRequestData.
     * 
     * @param customerId
     */
    public void setCustomerId(java.lang.String customerId) {
        this.customerId = customerId;
    }


    /**
     * Gets the customerRelatedDocumentNumber value for this EscalateServiceRequestData.
     * 
     * @return customerRelatedDocumentNumber
     */
    public java.lang.String getCustomerRelatedDocumentNumber() {
        return customerRelatedDocumentNumber;
    }


    /**
     * Sets the customerRelatedDocumentNumber value for this EscalateServiceRequestData.
     * 
     * @param customerRelatedDocumentNumber
     */
    public void setCustomerRelatedDocumentNumber(java.lang.String customerRelatedDocumentNumber) {
        this.customerRelatedDocumentNumber = customerRelatedDocumentNumber;
    }


    /**
     * Gets the escalateReason value for this EscalateServiceRequestData.
     * 
     * @return escalateReason
     */
    public java.lang.String getEscalateReason() {
        return escalateReason;
    }


    /**
     * Sets the escalateReason value for this EscalateServiceRequestData.
     * 
     * @param escalateReason
     */
    public void setEscalateReason(java.lang.String escalateReason) {
        this.escalateReason = escalateReason;
    }


    /**
     * Gets the description value for this EscalateServiceRequestData.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this EscalateServiceRequestData.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EscalateServiceRequestData)) return false;
        EscalateServiceRequestData other = (EscalateServiceRequestData) obj;
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
            ((this.customerId==null && other.getCustomerId()==null) || 
             (this.customerId!=null &&
              this.customerId.equals(other.getCustomerId()))) &&
            ((this.customerRelatedDocumentNumber==null && other.getCustomerRelatedDocumentNumber()==null) || 
             (this.customerRelatedDocumentNumber!=null &&
              this.customerRelatedDocumentNumber.equals(other.getCustomerRelatedDocumentNumber()))) &&
            ((this.escalateReason==null && other.getEscalateReason()==null) || 
             (this.escalateReason!=null &&
              this.escalateReason.equals(other.getEscalateReason()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription())));
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
        if (getCustomerId() != null) {
            _hashCode += getCustomerId().hashCode();
        }
        if (getCustomerRelatedDocumentNumber() != null) {
            _hashCode += getCustomerRelatedDocumentNumber().hashCode();
        }
        if (getEscalateReason() != null) {
            _hashCode += getEscalateReason().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EscalateServiceRequestData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("customerRelatedDocumentNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerRelatedDocumentNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("escalateReason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EscalateReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Description"));
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
