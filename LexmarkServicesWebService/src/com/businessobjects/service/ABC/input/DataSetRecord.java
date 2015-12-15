/**
 * DataSetRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.service.ABC.input;

public class DataSetRecord  implements java.io.Serializable {
    private java.lang.String city;

    private java.lang.String business_spcAddressCountry;

    private java.lang.String postalCode;

    private java.lang.String business_spcAddressState;

    private java.lang.String streetAddress;

    private java.lang.String streetAddress2;

    public DataSetRecord() {
    }

    public DataSetRecord(
           java.lang.String city,
           java.lang.String business_spcAddressCountry,
           java.lang.String postalCode,
           java.lang.String business_spcAddressState,
           java.lang.String streetAddress,
           java.lang.String streetAddress2) {
           this.city = city;
           this.business_spcAddressCountry = business_spcAddressCountry;
           this.postalCode = postalCode;
           this.business_spcAddressState = business_spcAddressState;
           this.streetAddress = streetAddress;
           this.streetAddress2 = streetAddress2;
    }


    /**
     * Gets the city value for this DataSetRecord.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this DataSetRecord.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the business_spcAddressCountry value for this DataSetRecord.
     * 
     * @return business_spcAddressCountry
     */
    public java.lang.String getBusiness_spcAddressCountry() {
        return business_spcAddressCountry;
    }


    /**
     * Sets the business_spcAddressCountry value for this DataSetRecord.
     * 
     * @param business_spcAddressCountry
     */
    public void setBusiness_spcAddressCountry(java.lang.String business_spcAddressCountry) {
        this.business_spcAddressCountry = business_spcAddressCountry;
    }


    /**
     * Gets the postalCode value for this DataSetRecord.
     * 
     * @return postalCode
     */
    public java.lang.String getPostalCode() {
        return postalCode;
    }


    /**
     * Sets the postalCode value for this DataSetRecord.
     * 
     * @param postalCode
     */
    public void setPostalCode(java.lang.String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * Gets the business_spcAddressState value for this DataSetRecord.
     * 
     * @return business_spcAddressState
     */
    public java.lang.String getBusiness_spcAddressState() {
        return business_spcAddressState;
    }


    /**
     * Sets the business_spcAddressState value for this DataSetRecord.
     * 
     * @param business_spcAddressState
     */
    public void setBusiness_spcAddressState(java.lang.String business_spcAddressState) {
        this.business_spcAddressState = business_spcAddressState;
    }


    /**
     * Gets the streetAddress value for this DataSetRecord.
     * 
     * @return streetAddress
     */
    public java.lang.String getStreetAddress() {
        return streetAddress;
    }


    /**
     * Sets the streetAddress value for this DataSetRecord.
     * 
     * @param streetAddress
     */
    public void setStreetAddress(java.lang.String streetAddress) {
        this.streetAddress = streetAddress;
    }


    /**
     * Gets the streetAddress2 value for this DataSetRecord.
     * 
     * @return streetAddress2
     */
    public java.lang.String getStreetAddress2() {
        return streetAddress2;
    }


    /**
     * Sets the streetAddress2 value for this DataSetRecord.
     * 
     * @param streetAddress2
     */
    public void setStreetAddress2(java.lang.String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataSetRecord)) return false;
        DataSetRecord other = (DataSetRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.business_spcAddressCountry==null && other.getBusiness_spcAddressCountry()==null) || 
             (this.business_spcAddressCountry!=null &&
              this.business_spcAddressCountry.equals(other.getBusiness_spcAddressCountry()))) &&
            ((this.postalCode==null && other.getPostalCode()==null) || 
             (this.postalCode!=null &&
              this.postalCode.equals(other.getPostalCode()))) &&
            ((this.business_spcAddressState==null && other.getBusiness_spcAddressState()==null) || 
             (this.business_spcAddressState!=null &&
              this.business_spcAddressState.equals(other.getBusiness_spcAddressState()))) &&
            ((this.streetAddress==null && other.getStreetAddress()==null) || 
             (this.streetAddress!=null &&
              this.streetAddress.equals(other.getStreetAddress()))) &&
            ((this.streetAddress2==null && other.getStreetAddress2()==null) || 
             (this.streetAddress2!=null &&
              this.streetAddress2.equals(other.getStreetAddress2())));
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
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getBusiness_spcAddressCountry() != null) {
            _hashCode += getBusiness_spcAddressCountry().hashCode();
        }
        if (getPostalCode() != null) {
            _hashCode += getPostalCode().hashCode();
        }
        if (getBusiness_spcAddressState() != null) {
            _hashCode += getBusiness_spcAddressState().hashCode();
        }
        if (getStreetAddress() != null) {
            _hashCode += getStreetAddress().hashCode();
        }
        if (getStreetAddress2() != null) {
            _hashCode += getStreetAddress2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataSetRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", ">>DataSet>Record"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", "City"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("business_spcAddressCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", "Business_spcAddress.Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", "PostalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("business_spcAddressState");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", "Business_spcAddress.State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streetAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", "StreetAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streetAddress2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/ABC/input", "StreetAddress2"));
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
