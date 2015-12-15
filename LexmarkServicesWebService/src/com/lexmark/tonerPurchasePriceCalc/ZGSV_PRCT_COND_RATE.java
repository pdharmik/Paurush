/**
 * ZGSV_PRCT_COND_RATE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.tonerPurchasePriceCalc;

public class ZGSV_PRCT_COND_RATE  implements java.io.Serializable {
    private java.lang.String PRODUCT_ID;

    private java.lang.String RATE;

    private java.lang.String CURRENCY;

    public ZGSV_PRCT_COND_RATE() {
    }

    public ZGSV_PRCT_COND_RATE(
           java.lang.String PRODUCT_ID,
           java.lang.String RATE,
           java.lang.String CURRENCY) {
           this.PRODUCT_ID = PRODUCT_ID;
           this.RATE = RATE;
           this.CURRENCY = CURRENCY;
    }


    /**
     * Gets the PRODUCT_ID value for this ZGSV_PRCT_COND_RATE.
     * 
     * @return PRODUCT_ID
     */
    public java.lang.String getPRODUCT_ID() {
        return PRODUCT_ID;
    }


    /**
     * Sets the PRODUCT_ID value for this ZGSV_PRCT_COND_RATE.
     * 
     * @param PRODUCT_ID
     */
    public void setPRODUCT_ID(java.lang.String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }


    /**
     * Gets the RATE value for this ZGSV_PRCT_COND_RATE.
     * 
     * @return RATE
     */
    public java.lang.String getRATE() {
        return RATE;
    }


    /**
     * Sets the RATE value for this ZGSV_PRCT_COND_RATE.
     * 
     * @param RATE
     */
    public void setRATE(java.lang.String RATE) {
        this.RATE = RATE;
    }


    /**
     * Gets the CURRENCY value for this ZGSV_PRCT_COND_RATE.
     * 
     * @return CURRENCY
     */
    public java.lang.String getCURRENCY() {
        return CURRENCY;
    }


    /**
     * Sets the CURRENCY value for this ZGSV_PRCT_COND_RATE.
     * 
     * @param CURRENCY
     */
    public void setCURRENCY(java.lang.String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ZGSV_PRCT_COND_RATE)) return false;
        ZGSV_PRCT_COND_RATE other = (ZGSV_PRCT_COND_RATE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PRODUCT_ID==null && other.getPRODUCT_ID()==null) || 
             (this.PRODUCT_ID!=null &&
              this.PRODUCT_ID.equals(other.getPRODUCT_ID()))) &&
            ((this.RATE==null && other.getRATE()==null) || 
             (this.RATE!=null &&
              this.RATE.equals(other.getRATE()))) &&
            ((this.CURRENCY==null && other.getCURRENCY()==null) || 
             (this.CURRENCY!=null &&
              this.CURRENCY.equals(other.getCURRENCY())));
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
        if (getPRODUCT_ID() != null) {
            _hashCode += getPRODUCT_ID().hashCode();
        }
        if (getRATE() != null) {
            _hashCode += getRATE().hashCode();
        }
        if (getCURRENCY() != null) {
            _hashCode += getCURRENCY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZGSV_PRCT_COND_RATE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZGSV_PRCT_COND_RATE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRODUCT_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PRODUCT_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CURRENCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CURRENCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
