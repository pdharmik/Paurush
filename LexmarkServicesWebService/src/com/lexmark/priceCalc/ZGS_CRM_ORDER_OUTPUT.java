/**
 * ZGS_CRM_ORDER_OUTPUT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.priceCalc;

public class ZGS_CRM_ORDER_OUTPUT  implements java.io.Serializable {
    private java.lang.String ITEM_NO;

    private java.lang.String PRODUCT_ID;

    private java.lang.String CURRENCY;

    private java.lang.String RATE;

    public ZGS_CRM_ORDER_OUTPUT() {
    }

    public ZGS_CRM_ORDER_OUTPUT(
           java.lang.String ITEM_NO,
           java.lang.String PRODUCT_ID,
           java.lang.String CURRENCY,
           java.lang.String RATE) {
           this.ITEM_NO = ITEM_NO;
           this.PRODUCT_ID = PRODUCT_ID;
           this.CURRENCY = CURRENCY;
           this.RATE = RATE;
    }


    /**
     * Gets the ITEM_NO value for this ZGS_CRM_ORDER_OUTPUT.
     * 
     * @return ITEM_NO
     */
    public java.lang.String getITEM_NO() {
        return ITEM_NO;
    }


    /**
     * Sets the ITEM_NO value for this ZGS_CRM_ORDER_OUTPUT.
     * 
     * @param ITEM_NO
     */
    public void setITEM_NO(java.lang.String ITEM_NO) {
        this.ITEM_NO = ITEM_NO;
    }


    /**
     * Gets the PRODUCT_ID value for this ZGS_CRM_ORDER_OUTPUT.
     * 
     * @return PRODUCT_ID
     */
    public java.lang.String getPRODUCT_ID() {
        return PRODUCT_ID;
    }


    /**
     * Sets the PRODUCT_ID value for this ZGS_CRM_ORDER_OUTPUT.
     * 
     * @param PRODUCT_ID
     */
    public void setPRODUCT_ID(java.lang.String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }


    /**
     * Gets the CURRENCY value for this ZGS_CRM_ORDER_OUTPUT.
     * 
     * @return CURRENCY
     */
    public java.lang.String getCURRENCY() {
        return CURRENCY;
    }


    /**
     * Sets the CURRENCY value for this ZGS_CRM_ORDER_OUTPUT.
     * 
     * @param CURRENCY
     */
    public void setCURRENCY(java.lang.String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }


    /**
     * Gets the RATE value for this ZGS_CRM_ORDER_OUTPUT.
     * 
     * @return RATE
     */
    public java.lang.String getRATE() {
        return RATE;
    }


    /**
     * Sets the RATE value for this ZGS_CRM_ORDER_OUTPUT.
     * 
     * @param RATE
     */
    public void setRATE(java.lang.String RATE) {
        this.RATE = RATE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ZGS_CRM_ORDER_OUTPUT)) return false;
        ZGS_CRM_ORDER_OUTPUT other = (ZGS_CRM_ORDER_OUTPUT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ITEM_NO==null && other.getITEM_NO()==null) || 
             (this.ITEM_NO!=null &&
              this.ITEM_NO.equals(other.getITEM_NO()))) &&
            ((this.PRODUCT_ID==null && other.getPRODUCT_ID()==null) || 
             (this.PRODUCT_ID!=null &&
              this.PRODUCT_ID.equals(other.getPRODUCT_ID()))) &&
            ((this.CURRENCY==null && other.getCURRENCY()==null) || 
             (this.CURRENCY!=null &&
              this.CURRENCY.equals(other.getCURRENCY()))) &&
            ((this.RATE==null && other.getRATE()==null) || 
             (this.RATE!=null &&
              this.RATE.equals(other.getRATE())));
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
        if (getITEM_NO() != null) {
            _hashCode += getITEM_NO().hashCode();
        }
        if (getPRODUCT_ID() != null) {
            _hashCode += getPRODUCT_ID().hashCode();
        }
        if (getCURRENCY() != null) {
            _hashCode += getCURRENCY().hashCode();
        }
        if (getRATE() != null) {
            _hashCode += getRATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZGS_CRM_ORDER_OUTPUT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZGS_CRM_ORDER_OUTPUT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ITEM_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ITEM_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRODUCT_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PRODUCT_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CURRENCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CURRENCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RATE"));
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
