/**
 * EBPP_TOTALS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_TOTALS  implements java.io.Serializable {
    private java.lang.String INVGROUP;

    private java.lang.String CURRENCY;

    private java.math.BigDecimal AMOUNT;

    private java.lang.String TEXT;

    public EBPP_TOTALS() {
    }

    public EBPP_TOTALS(
           java.lang.String INVGROUP,
           java.lang.String CURRENCY,
           java.math.BigDecimal AMOUNT,
           java.lang.String TEXT) {
           this.INVGROUP = INVGROUP;
           this.CURRENCY = CURRENCY;
           this.AMOUNT = AMOUNT;
           this.TEXT = TEXT;
    }


    /**
     * Gets the INVGROUP value for this EBPP_TOTALS.
     * 
     * @return INVGROUP
     */
    public java.lang.String getINVGROUP() {
        return INVGROUP;
    }


    /**
     * Sets the INVGROUP value for this EBPP_TOTALS.
     * 
     * @param INVGROUP
     */
    public void setINVGROUP(java.lang.String INVGROUP) {
        this.INVGROUP = INVGROUP;
    }


    /**
     * Gets the CURRENCY value for this EBPP_TOTALS.
     * 
     * @return CURRENCY
     */
    public java.lang.String getCURRENCY() {
        return CURRENCY;
    }


    /**
     * Sets the CURRENCY value for this EBPP_TOTALS.
     * 
     * @param CURRENCY
     */
    public void setCURRENCY(java.lang.String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }


    /**
     * Gets the AMOUNT value for this EBPP_TOTALS.
     * 
     * @return AMOUNT
     */
    public java.math.BigDecimal getAMOUNT() {
        return AMOUNT;
    }


    /**
     * Sets the AMOUNT value for this EBPP_TOTALS.
     * 
     * @param AMOUNT
     */
    public void setAMOUNT(java.math.BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }


    /**
     * Gets the TEXT value for this EBPP_TOTALS.
     * 
     * @return TEXT
     */
    public java.lang.String getTEXT() {
        return TEXT;
    }


    /**
     * Sets the TEXT value for this EBPP_TOTALS.
     * 
     * @param TEXT
     */
    public void setTEXT(java.lang.String TEXT) {
        this.TEXT = TEXT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_TOTALS)) return false;
        EBPP_TOTALS other = (EBPP_TOTALS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INVGROUP==null && other.getINVGROUP()==null) || 
             (this.INVGROUP!=null &&
              this.INVGROUP.equals(other.getINVGROUP()))) &&
            ((this.CURRENCY==null && other.getCURRENCY()==null) || 
             (this.CURRENCY!=null &&
              this.CURRENCY.equals(other.getCURRENCY()))) &&
            ((this.AMOUNT==null && other.getAMOUNT()==null) || 
             (this.AMOUNT!=null &&
              this.AMOUNT.equals(other.getAMOUNT()))) &&
            ((this.TEXT==null && other.getTEXT()==null) || 
             (this.TEXT!=null &&
              this.TEXT.equals(other.getTEXT())));
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
        if (getINVGROUP() != null) {
            _hashCode += getINVGROUP().hashCode();
        }
        if (getCURRENCY() != null) {
            _hashCode += getCURRENCY().hashCode();
        }
        if (getAMOUNT() != null) {
            _hashCode += getAMOUNT().hashCode();
        }
        if (getTEXT() != null) {
            _hashCode += getTEXT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_TOTALS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_TOTALS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INVGROUP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INVGROUP"));
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
        elemField.setFieldName("AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TEXT"));
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
