/**
 * ServiceProviderInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ServiceProviderInformation  implements java.io.Serializable {
    private java.lang.String serviceProviderName;

    private java.lang.String serviceProviderInstructions;

    private java.lang.String serviceProviderReferenceNumber;

    private java.lang.String serviceProviderTimeOfArrival;

    public ServiceProviderInformation() {
    }

    public ServiceProviderInformation(
           java.lang.String serviceProviderName,
           java.lang.String serviceProviderInstructions,
           java.lang.String serviceProviderReferenceNumber,
           java.lang.String serviceProviderTimeOfArrival) {
           this.serviceProviderName = serviceProviderName;
           this.serviceProviderInstructions = serviceProviderInstructions;
           this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
           this.serviceProviderTimeOfArrival = serviceProviderTimeOfArrival;
    }


    /**
     * Gets the serviceProviderName value for this ServiceProviderInformation.
     * 
     * @return serviceProviderName
     */
    public java.lang.String getServiceProviderName() {
        return serviceProviderName;
    }


    /**
     * Sets the serviceProviderName value for this ServiceProviderInformation.
     * 
     * @param serviceProviderName
     */
    public void setServiceProviderName(java.lang.String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }


    /**
     * Gets the serviceProviderInstructions value for this ServiceProviderInformation.
     * 
     * @return serviceProviderInstructions
     */
    public java.lang.String getServiceProviderInstructions() {
        return serviceProviderInstructions;
    }


    /**
     * Sets the serviceProviderInstructions value for this ServiceProviderInformation.
     * 
     * @param serviceProviderInstructions
     */
    public void setServiceProviderInstructions(java.lang.String serviceProviderInstructions) {
        this.serviceProviderInstructions = serviceProviderInstructions;
    }


    /**
     * Gets the serviceProviderReferenceNumber value for this ServiceProviderInformation.
     * 
     * @return serviceProviderReferenceNumber
     */
    public java.lang.String getServiceProviderReferenceNumber() {
        return serviceProviderReferenceNumber;
    }


    /**
     * Sets the serviceProviderReferenceNumber value for this ServiceProviderInformation.
     * 
     * @param serviceProviderReferenceNumber
     */
    public void setServiceProviderReferenceNumber(java.lang.String serviceProviderReferenceNumber) {
        this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
    }


    /**
     * Gets the serviceProviderTimeOfArrival value for this ServiceProviderInformation.
     * 
     * @return serviceProviderTimeOfArrival
     */
    public java.lang.String getServiceProviderTimeOfArrival() {
        return serviceProviderTimeOfArrival;
    }


    /**
     * Sets the serviceProviderTimeOfArrival value for this ServiceProviderInformation.
     * 
     * @param serviceProviderTimeOfArrival
     */
    public void setServiceProviderTimeOfArrival(java.lang.String serviceProviderTimeOfArrival) {
        this.serviceProviderTimeOfArrival = serviceProviderTimeOfArrival;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceProviderInformation)) return false;
        ServiceProviderInformation other = (ServiceProviderInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceProviderName==null && other.getServiceProviderName()==null) || 
             (this.serviceProviderName!=null &&
              this.serviceProviderName.equals(other.getServiceProviderName()))) &&
            ((this.serviceProviderInstructions==null && other.getServiceProviderInstructions()==null) || 
             (this.serviceProviderInstructions!=null &&
              this.serviceProviderInstructions.equals(other.getServiceProviderInstructions()))) &&
            ((this.serviceProviderReferenceNumber==null && other.getServiceProviderReferenceNumber()==null) || 
             (this.serviceProviderReferenceNumber!=null &&
              this.serviceProviderReferenceNumber.equals(other.getServiceProviderReferenceNumber()))) &&
            ((this.serviceProviderTimeOfArrival==null && other.getServiceProviderTimeOfArrival()==null) || 
             (this.serviceProviderTimeOfArrival!=null &&
              this.serviceProviderTimeOfArrival.equals(other.getServiceProviderTimeOfArrival())));
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
        if (getServiceProviderName() != null) {
            _hashCode += getServiceProviderName().hashCode();
        }
        if (getServiceProviderInstructions() != null) {
            _hashCode += getServiceProviderInstructions().hashCode();
        }
        if (getServiceProviderReferenceNumber() != null) {
            _hashCode += getServiceProviderReferenceNumber().hashCode();
        }
        if (getServiceProviderTimeOfArrival() != null) {
            _hashCode += getServiceProviderTimeOfArrival().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceProviderInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceProviderInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderInstructions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderInstructions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderTimeOfArrival");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderTimeOfArrival"));
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
