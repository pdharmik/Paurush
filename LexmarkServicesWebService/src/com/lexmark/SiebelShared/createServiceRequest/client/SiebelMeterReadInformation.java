/**
 * SiebelMeterReadInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class SiebelMeterReadInformation  implements java.io.Serializable {
    private java.lang.String meterType;

    private java.lang.String meterDateTime;

    private java.lang.String meterValue;

    public SiebelMeterReadInformation() {
    }

    public SiebelMeterReadInformation(
           java.lang.String meterType,
           java.lang.String meterDateTime,
           java.lang.String meterValue) {
           this.meterType = meterType;
           this.meterDateTime = meterDateTime;
           this.meterValue = meterValue;
    }


    /**
     * Gets the meterType value for this SiebelMeterReadInformation.
     * 
     * @return meterType
     */
    public java.lang.String getMeterType() {
        return meterType;
    }


    /**
     * Sets the meterType value for this SiebelMeterReadInformation.
     * 
     * @param meterType
     */
    public void setMeterType(java.lang.String meterType) {
        this.meterType = meterType;
    }


    /**
     * Gets the meterDateTime value for this SiebelMeterReadInformation.
     * 
     * @return meterDateTime
     */
    public java.lang.String getMeterDateTime() {
        return meterDateTime;
    }


    /**
     * Sets the meterDateTime value for this SiebelMeterReadInformation.
     * 
     * @param meterDateTime
     */
    public void setMeterDateTime(java.lang.String meterDateTime) {
        this.meterDateTime = meterDateTime;
    }


    /**
     * Gets the meterValue value for this SiebelMeterReadInformation.
     * 
     * @return meterValue
     */
    public java.lang.String getMeterValue() {
        return meterValue;
    }


    /**
     * Sets the meterValue value for this SiebelMeterReadInformation.
     * 
     * @param meterValue
     */
    public void setMeterValue(java.lang.String meterValue) {
        this.meterValue = meterValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelMeterReadInformation)) return false;
        SiebelMeterReadInformation other = (SiebelMeterReadInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.meterType==null && other.getMeterType()==null) || 
             (this.meterType!=null &&
              this.meterType.equals(other.getMeterType()))) &&
            ((this.meterDateTime==null && other.getMeterDateTime()==null) || 
             (this.meterDateTime!=null &&
              this.meterDateTime.equals(other.getMeterDateTime()))) &&
            ((this.meterValue==null && other.getMeterValue()==null) || 
             (this.meterValue!=null &&
              this.meterValue.equals(other.getMeterValue())));
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
        if (getMeterType() != null) {
            _hashCode += getMeterType().hashCode();
        }
        if (getMeterDateTime() != null) {
            _hashCode += getMeterDateTime().hashCode();
        }
        if (getMeterValue() != null) {
            _hashCode += getMeterValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelMeterReadInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelMeterReadInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meterType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MeterType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meterDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MeterDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meterValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MeterValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
