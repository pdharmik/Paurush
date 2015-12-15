/**
 * ServiceRequestDetailsData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class ServiceRequestDetailsData  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestId;

    public ServiceRequestDetailsData() {
    }

    public ServiceRequestDetailsData(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestId) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestId = serviceRequestId;
    }


    /**
     * Gets the serviceRequestNumber value for this ServiceRequestDetailsData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this ServiceRequestDetailsData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestId value for this ServiceRequestDetailsData.
     * 
     * @return serviceRequestId
     */
    public java.lang.String getServiceRequestId() {
        return serviceRequestId;
    }


    /**
     * Sets the serviceRequestId value for this ServiceRequestDetailsData.
     * 
     * @param serviceRequestId
     */
    public void setServiceRequestId(java.lang.String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestDetailsData)) return false;
        ServiceRequestDetailsData other = (ServiceRequestDetailsData) obj;
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
            ((this.serviceRequestId==null && other.getServiceRequestId()==null) || 
             (this.serviceRequestId!=null &&
              this.serviceRequestId.equals(other.getServiceRequestId())));
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
        if (getServiceRequestId() != null) {
            _hashCode += getServiceRequestId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestDetailsData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequestDetailsData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestId"));
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
