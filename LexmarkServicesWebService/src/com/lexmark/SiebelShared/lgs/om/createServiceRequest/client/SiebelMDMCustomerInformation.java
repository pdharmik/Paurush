/**
 * SiebelMDMCustomerInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class SiebelMDMCustomerInformation  implements java.io.Serializable {
    private java.lang.String MDMId;

    private java.lang.String MDMLevel;

    public SiebelMDMCustomerInformation() {
    }

    public SiebelMDMCustomerInformation(
           java.lang.String MDMId,
           java.lang.String MDMLevel) {
           this.MDMId = MDMId;
           this.MDMLevel = MDMLevel;
    }


    /**
     * Gets the MDMId value for this SiebelMDMCustomerInformation.
     * 
     * @return MDMId
     */
    public java.lang.String getMDMId() {
        return MDMId;
    }


    /**
     * Sets the MDMId value for this SiebelMDMCustomerInformation.
     * 
     * @param MDMId
     */
    public void setMDMId(java.lang.String MDMId) {
        this.MDMId = MDMId;
    }


    /**
     * Gets the MDMLevel value for this SiebelMDMCustomerInformation.
     * 
     * @return MDMLevel
     */
    public java.lang.String getMDMLevel() {
        return MDMLevel;
    }


    /**
     * Sets the MDMLevel value for this SiebelMDMCustomerInformation.
     * 
     * @param MDMLevel
     */
    public void setMDMLevel(java.lang.String MDMLevel) {
        this.MDMLevel = MDMLevel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelMDMCustomerInformation)) return false;
        SiebelMDMCustomerInformation other = (SiebelMDMCustomerInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.MDMId==null && other.getMDMId()==null) || 
             (this.MDMId!=null &&
              this.MDMId.equals(other.getMDMId()))) &&
            ((this.MDMLevel==null && other.getMDMLevel()==null) || 
             (this.MDMLevel!=null &&
              this.MDMLevel.equals(other.getMDMLevel())));
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
        if (getMDMId() != null) {
            _hashCode += getMDMId().hashCode();
        }
        if (getMDMLevel() != null) {
            _hashCode += getMDMLevel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelMDMCustomerInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelMDMCustomerInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MDMId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MDMId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MDMLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MDMLevel"));
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
