/**
 * EBPP_CREDITS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_CREDITS  implements java.io.Serializable {
    private java.math.BigDecimal TOTAL_AMOUNT;

    private java.math.BigDecimal CREDIT_AMOUNT;

    private java.math.BigDecimal NET_AMOUNT;

    private java.lang.String CURRENCY;

    private java.math.BigDecimal OPEN_DEBITS;

    private java.math.BigDecimal OPEN_CREDITS;

    private java.math.BigDecimal OPEN_NET;

    private java.math.BigDecimal PREVIOUS_BALANCE_NET;

    private java.math.BigDecimal OPEN_COLLECTABLE;

    public EBPP_CREDITS() {
    }

    public EBPP_CREDITS(
           java.math.BigDecimal TOTAL_AMOUNT,
           java.math.BigDecimal CREDIT_AMOUNT,
           java.math.BigDecimal NET_AMOUNT,
           java.lang.String CURRENCY,
           java.math.BigDecimal OPEN_DEBITS,
           java.math.BigDecimal OPEN_CREDITS,
           java.math.BigDecimal OPEN_NET,
           java.math.BigDecimal PREVIOUS_BALANCE_NET,
           java.math.BigDecimal OPEN_COLLECTABLE) {
           this.TOTAL_AMOUNT = TOTAL_AMOUNT;
           this.CREDIT_AMOUNT = CREDIT_AMOUNT;
           this.NET_AMOUNT = NET_AMOUNT;
           this.CURRENCY = CURRENCY;
           this.OPEN_DEBITS = OPEN_DEBITS;
           this.OPEN_CREDITS = OPEN_CREDITS;
           this.OPEN_NET = OPEN_NET;
           this.PREVIOUS_BALANCE_NET = PREVIOUS_BALANCE_NET;
           this.OPEN_COLLECTABLE = OPEN_COLLECTABLE;
    }


    /**
     * Gets the TOTAL_AMOUNT value for this EBPP_CREDITS.
     * 
     * @return TOTAL_AMOUNT
     */
    public java.math.BigDecimal getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }


    /**
     * Sets the TOTAL_AMOUNT value for this EBPP_CREDITS.
     * 
     * @param TOTAL_AMOUNT
     */
    public void setTOTAL_AMOUNT(java.math.BigDecimal TOTAL_AMOUNT) {
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
    }


    /**
     * Gets the CREDIT_AMOUNT value for this EBPP_CREDITS.
     * 
     * @return CREDIT_AMOUNT
     */
    public java.math.BigDecimal getCREDIT_AMOUNT() {
        return CREDIT_AMOUNT;
    }


    /**
     * Sets the CREDIT_AMOUNT value for this EBPP_CREDITS.
     * 
     * @param CREDIT_AMOUNT
     */
    public void setCREDIT_AMOUNT(java.math.BigDecimal CREDIT_AMOUNT) {
        this.CREDIT_AMOUNT = CREDIT_AMOUNT;
    }


    /**
     * Gets the NET_AMOUNT value for this EBPP_CREDITS.
     * 
     * @return NET_AMOUNT
     */
    public java.math.BigDecimal getNET_AMOUNT() {
        return NET_AMOUNT;
    }


    /**
     * Sets the NET_AMOUNT value for this EBPP_CREDITS.
     * 
     * @param NET_AMOUNT
     */
    public void setNET_AMOUNT(java.math.BigDecimal NET_AMOUNT) {
        this.NET_AMOUNT = NET_AMOUNT;
    }


    /**
     * Gets the CURRENCY value for this EBPP_CREDITS.
     * 
     * @return CURRENCY
     */
    public java.lang.String getCURRENCY() {
        return CURRENCY;
    }


    /**
     * Sets the CURRENCY value for this EBPP_CREDITS.
     * 
     * @param CURRENCY
     */
    public void setCURRENCY(java.lang.String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }


    /**
     * Gets the OPEN_DEBITS value for this EBPP_CREDITS.
     * 
     * @return OPEN_DEBITS
     */
    public java.math.BigDecimal getOPEN_DEBITS() {
        return OPEN_DEBITS;
    }


    /**
     * Sets the OPEN_DEBITS value for this EBPP_CREDITS.
     * 
     * @param OPEN_DEBITS
     */
    public void setOPEN_DEBITS(java.math.BigDecimal OPEN_DEBITS) {
        this.OPEN_DEBITS = OPEN_DEBITS;
    }


    /**
     * Gets the OPEN_CREDITS value for this EBPP_CREDITS.
     * 
     * @return OPEN_CREDITS
     */
    public java.math.BigDecimal getOPEN_CREDITS() {
        return OPEN_CREDITS;
    }


    /**
     * Sets the OPEN_CREDITS value for this EBPP_CREDITS.
     * 
     * @param OPEN_CREDITS
     */
    public void setOPEN_CREDITS(java.math.BigDecimal OPEN_CREDITS) {
        this.OPEN_CREDITS = OPEN_CREDITS;
    }


    /**
     * Gets the OPEN_NET value for this EBPP_CREDITS.
     * 
     * @return OPEN_NET
     */
    public java.math.BigDecimal getOPEN_NET() {
        return OPEN_NET;
    }


    /**
     * Sets the OPEN_NET value for this EBPP_CREDITS.
     * 
     * @param OPEN_NET
     */
    public void setOPEN_NET(java.math.BigDecimal OPEN_NET) {
        this.OPEN_NET = OPEN_NET;
    }


    /**
     * Gets the PREVIOUS_BALANCE_NET value for this EBPP_CREDITS.
     * 
     * @return PREVIOUS_BALANCE_NET
     */
    public java.math.BigDecimal getPREVIOUS_BALANCE_NET() {
        return PREVIOUS_BALANCE_NET;
    }


    /**
     * Sets the PREVIOUS_BALANCE_NET value for this EBPP_CREDITS.
     * 
     * @param PREVIOUS_BALANCE_NET
     */
    public void setPREVIOUS_BALANCE_NET(java.math.BigDecimal PREVIOUS_BALANCE_NET) {
        this.PREVIOUS_BALANCE_NET = PREVIOUS_BALANCE_NET;
    }


    /**
     * Gets the OPEN_COLLECTABLE value for this EBPP_CREDITS.
     * 
     * @return OPEN_COLLECTABLE
     */
    public java.math.BigDecimal getOPEN_COLLECTABLE() {
        return OPEN_COLLECTABLE;
    }


    /**
     * Sets the OPEN_COLLECTABLE value for this EBPP_CREDITS.
     * 
     * @param OPEN_COLLECTABLE
     */
    public void setOPEN_COLLECTABLE(java.math.BigDecimal OPEN_COLLECTABLE) {
        this.OPEN_COLLECTABLE = OPEN_COLLECTABLE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_CREDITS)) return false;
        EBPP_CREDITS other = (EBPP_CREDITS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.TOTAL_AMOUNT==null && other.getTOTAL_AMOUNT()==null) || 
             (this.TOTAL_AMOUNT!=null &&
              this.TOTAL_AMOUNT.equals(other.getTOTAL_AMOUNT()))) &&
            ((this.CREDIT_AMOUNT==null && other.getCREDIT_AMOUNT()==null) || 
             (this.CREDIT_AMOUNT!=null &&
              this.CREDIT_AMOUNT.equals(other.getCREDIT_AMOUNT()))) &&
            ((this.NET_AMOUNT==null && other.getNET_AMOUNT()==null) || 
             (this.NET_AMOUNT!=null &&
              this.NET_AMOUNT.equals(other.getNET_AMOUNT()))) &&
            ((this.CURRENCY==null && other.getCURRENCY()==null) || 
             (this.CURRENCY!=null &&
              this.CURRENCY.equals(other.getCURRENCY()))) &&
            ((this.OPEN_DEBITS==null && other.getOPEN_DEBITS()==null) || 
             (this.OPEN_DEBITS!=null &&
              this.OPEN_DEBITS.equals(other.getOPEN_DEBITS()))) &&
            ((this.OPEN_CREDITS==null && other.getOPEN_CREDITS()==null) || 
             (this.OPEN_CREDITS!=null &&
              this.OPEN_CREDITS.equals(other.getOPEN_CREDITS()))) &&
            ((this.OPEN_NET==null && other.getOPEN_NET()==null) || 
             (this.OPEN_NET!=null &&
              this.OPEN_NET.equals(other.getOPEN_NET()))) &&
            ((this.PREVIOUS_BALANCE_NET==null && other.getPREVIOUS_BALANCE_NET()==null) || 
             (this.PREVIOUS_BALANCE_NET!=null &&
              this.PREVIOUS_BALANCE_NET.equals(other.getPREVIOUS_BALANCE_NET()))) &&
            ((this.OPEN_COLLECTABLE==null && other.getOPEN_COLLECTABLE()==null) || 
             (this.OPEN_COLLECTABLE!=null &&
              this.OPEN_COLLECTABLE.equals(other.getOPEN_COLLECTABLE())));
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
        if (getTOTAL_AMOUNT() != null) {
            _hashCode += getTOTAL_AMOUNT().hashCode();
        }
        if (getCREDIT_AMOUNT() != null) {
            _hashCode += getCREDIT_AMOUNT().hashCode();
        }
        if (getNET_AMOUNT() != null) {
            _hashCode += getNET_AMOUNT().hashCode();
        }
        if (getCURRENCY() != null) {
            _hashCode += getCURRENCY().hashCode();
        }
        if (getOPEN_DEBITS() != null) {
            _hashCode += getOPEN_DEBITS().hashCode();
        }
        if (getOPEN_CREDITS() != null) {
            _hashCode += getOPEN_CREDITS().hashCode();
        }
        if (getOPEN_NET() != null) {
            _hashCode += getOPEN_NET().hashCode();
        }
        if (getPREVIOUS_BALANCE_NET() != null) {
            _hashCode += getPREVIOUS_BALANCE_NET().hashCode();
        }
        if (getOPEN_COLLECTABLE() != null) {
            _hashCode += getOPEN_COLLECTABLE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_CREDITS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_CREDITS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOTAL_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TOTAL_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CREDIT_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CREDIT_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NET_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NET_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CURRENCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CURRENCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPEN_DEBITS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN_DEBITS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPEN_CREDITS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN_CREDITS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPEN_NET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN_NET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PREVIOUS_BALANCE_NET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PREVIOUS_BALANCE_NET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPEN_COLLECTABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN_COLLECTABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
