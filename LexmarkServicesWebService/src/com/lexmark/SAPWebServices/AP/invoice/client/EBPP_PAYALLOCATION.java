/**
 * EBPP_PAYALLOCATION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_PAYALLOCATION  implements java.io.Serializable {
    private java.lang.String INVID;

    private java.lang.String REFID;

    private java.math.BigDecimal AMOUNT;

    private java.lang.String CURRENCY;

    private java.lang.String ALLOCATED_INVID;

    private java.math.BigDecimal ALLOCATED_AMOUNT;

    private java.lang.String LINCT;

    private java.lang.String ALLOCATED_LINCT;

    private java.lang.String TYPE;

    private java.math.BigDecimal DISCOUNT;

    public EBPP_PAYALLOCATION() {
    }

    public EBPP_PAYALLOCATION(
           java.lang.String INVID,
           java.lang.String REFID,
           java.math.BigDecimal AMOUNT,
           java.lang.String CURRENCY,
           java.lang.String ALLOCATED_INVID,
           java.math.BigDecimal ALLOCATED_AMOUNT,
           java.lang.String LINCT,
           java.lang.String ALLOCATED_LINCT,
           java.lang.String TYPE,
           java.math.BigDecimal DISCOUNT) {
           this.INVID = INVID;
           this.REFID = REFID;
           this.AMOUNT = AMOUNT;
           this.CURRENCY = CURRENCY;
           this.ALLOCATED_INVID = ALLOCATED_INVID;
           this.ALLOCATED_AMOUNT = ALLOCATED_AMOUNT;
           this.LINCT = LINCT;
           this.ALLOCATED_LINCT = ALLOCATED_LINCT;
           this.TYPE = TYPE;
           this.DISCOUNT = DISCOUNT;
    }


    /**
     * Gets the INVID value for this EBPP_PAYALLOCATION.
     * 
     * @return INVID
     */
    public java.lang.String getINVID() {
        return INVID;
    }


    /**
     * Sets the INVID value for this EBPP_PAYALLOCATION.
     * 
     * @param INVID
     */
    public void setINVID(java.lang.String INVID) {
        this.INVID = INVID;
    }


    /**
     * Gets the REFID value for this EBPP_PAYALLOCATION.
     * 
     * @return REFID
     */
    public java.lang.String getREFID() {
        return REFID;
    }


    /**
     * Sets the REFID value for this EBPP_PAYALLOCATION.
     * 
     * @param REFID
     */
    public void setREFID(java.lang.String REFID) {
        this.REFID = REFID;
    }


    /**
     * Gets the AMOUNT value for this EBPP_PAYALLOCATION.
     * 
     * @return AMOUNT
     */
    public java.math.BigDecimal getAMOUNT() {
        return AMOUNT;
    }


    /**
     * Sets the AMOUNT value for this EBPP_PAYALLOCATION.
     * 
     * @param AMOUNT
     */
    public void setAMOUNT(java.math.BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }


    /**
     * Gets the CURRENCY value for this EBPP_PAYALLOCATION.
     * 
     * @return CURRENCY
     */
    public java.lang.String getCURRENCY() {
        return CURRENCY;
    }


    /**
     * Sets the CURRENCY value for this EBPP_PAYALLOCATION.
     * 
     * @param CURRENCY
     */
    public void setCURRENCY(java.lang.String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }


    /**
     * Gets the ALLOCATED_INVID value for this EBPP_PAYALLOCATION.
     * 
     * @return ALLOCATED_INVID
     */
    public java.lang.String getALLOCATED_INVID() {
        return ALLOCATED_INVID;
    }


    /**
     * Sets the ALLOCATED_INVID value for this EBPP_PAYALLOCATION.
     * 
     * @param ALLOCATED_INVID
     */
    public void setALLOCATED_INVID(java.lang.String ALLOCATED_INVID) {
        this.ALLOCATED_INVID = ALLOCATED_INVID;
    }


    /**
     * Gets the ALLOCATED_AMOUNT value for this EBPP_PAYALLOCATION.
     * 
     * @return ALLOCATED_AMOUNT
     */
    public java.math.BigDecimal getALLOCATED_AMOUNT() {
        return ALLOCATED_AMOUNT;
    }


    /**
     * Sets the ALLOCATED_AMOUNT value for this EBPP_PAYALLOCATION.
     * 
     * @param ALLOCATED_AMOUNT
     */
    public void setALLOCATED_AMOUNT(java.math.BigDecimal ALLOCATED_AMOUNT) {
        this.ALLOCATED_AMOUNT = ALLOCATED_AMOUNT;
    }


    /**
     * Gets the LINCT value for this EBPP_PAYALLOCATION.
     * 
     * @return LINCT
     */
    public java.lang.String getLINCT() {
        return LINCT;
    }


    /**
     * Sets the LINCT value for this EBPP_PAYALLOCATION.
     * 
     * @param LINCT
     */
    public void setLINCT(java.lang.String LINCT) {
        this.LINCT = LINCT;
    }


    /**
     * Gets the ALLOCATED_LINCT value for this EBPP_PAYALLOCATION.
     * 
     * @return ALLOCATED_LINCT
     */
    public java.lang.String getALLOCATED_LINCT() {
        return ALLOCATED_LINCT;
    }


    /**
     * Sets the ALLOCATED_LINCT value for this EBPP_PAYALLOCATION.
     * 
     * @param ALLOCATED_LINCT
     */
    public void setALLOCATED_LINCT(java.lang.String ALLOCATED_LINCT) {
        this.ALLOCATED_LINCT = ALLOCATED_LINCT;
    }


    /**
     * Gets the TYPE value for this EBPP_PAYALLOCATION.
     * 
     * @return TYPE
     */
    public java.lang.String getTYPE() {
        return TYPE;
    }


    /**
     * Sets the TYPE value for this EBPP_PAYALLOCATION.
     * 
     * @param TYPE
     */
    public void setTYPE(java.lang.String TYPE) {
        this.TYPE = TYPE;
    }


    /**
     * Gets the DISCOUNT value for this EBPP_PAYALLOCATION.
     * 
     * @return DISCOUNT
     */
    public java.math.BigDecimal getDISCOUNT() {
        return DISCOUNT;
    }


    /**
     * Sets the DISCOUNT value for this EBPP_PAYALLOCATION.
     * 
     * @param DISCOUNT
     */
    public void setDISCOUNT(java.math.BigDecimal DISCOUNT) {
        this.DISCOUNT = DISCOUNT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_PAYALLOCATION)) return false;
        EBPP_PAYALLOCATION other = (EBPP_PAYALLOCATION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INVID==null && other.getINVID()==null) || 
             (this.INVID!=null &&
              this.INVID.equals(other.getINVID()))) &&
            ((this.REFID==null && other.getREFID()==null) || 
             (this.REFID!=null &&
              this.REFID.equals(other.getREFID()))) &&
            ((this.AMOUNT==null && other.getAMOUNT()==null) || 
             (this.AMOUNT!=null &&
              this.AMOUNT.equals(other.getAMOUNT()))) &&
            ((this.CURRENCY==null && other.getCURRENCY()==null) || 
             (this.CURRENCY!=null &&
              this.CURRENCY.equals(other.getCURRENCY()))) &&
            ((this.ALLOCATED_INVID==null && other.getALLOCATED_INVID()==null) || 
             (this.ALLOCATED_INVID!=null &&
              this.ALLOCATED_INVID.equals(other.getALLOCATED_INVID()))) &&
            ((this.ALLOCATED_AMOUNT==null && other.getALLOCATED_AMOUNT()==null) || 
             (this.ALLOCATED_AMOUNT!=null &&
              this.ALLOCATED_AMOUNT.equals(other.getALLOCATED_AMOUNT()))) &&
            ((this.LINCT==null && other.getLINCT()==null) || 
             (this.LINCT!=null &&
              this.LINCT.equals(other.getLINCT()))) &&
            ((this.ALLOCATED_LINCT==null && other.getALLOCATED_LINCT()==null) || 
             (this.ALLOCATED_LINCT!=null &&
              this.ALLOCATED_LINCT.equals(other.getALLOCATED_LINCT()))) &&
            ((this.TYPE==null && other.getTYPE()==null) || 
             (this.TYPE!=null &&
              this.TYPE.equals(other.getTYPE()))) &&
            ((this.DISCOUNT==null && other.getDISCOUNT()==null) || 
             (this.DISCOUNT!=null &&
              this.DISCOUNT.equals(other.getDISCOUNT())));
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
        if (getINVID() != null) {
            _hashCode += getINVID().hashCode();
        }
        if (getREFID() != null) {
            _hashCode += getREFID().hashCode();
        }
        if (getAMOUNT() != null) {
            _hashCode += getAMOUNT().hashCode();
        }
        if (getCURRENCY() != null) {
            _hashCode += getCURRENCY().hashCode();
        }
        if (getALLOCATED_INVID() != null) {
            _hashCode += getALLOCATED_INVID().hashCode();
        }
        if (getALLOCATED_AMOUNT() != null) {
            _hashCode += getALLOCATED_AMOUNT().hashCode();
        }
        if (getLINCT() != null) {
            _hashCode += getLINCT().hashCode();
        }
        if (getALLOCATED_LINCT() != null) {
            _hashCode += getALLOCATED_LINCT().hashCode();
        }
        if (getTYPE() != null) {
            _hashCode += getTYPE().hashCode();
        }
        if (getDISCOUNT() != null) {
            _hashCode += getDISCOUNT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_PAYALLOCATION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PAYALLOCATION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INVID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INVID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REFID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REFID"));
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
        elemField.setFieldName("CURRENCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CURRENCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALLOCATED_INVID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ALLOCATED_INVID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALLOCATED_AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ALLOCATED_AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LINCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LINCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ALLOCATED_LINCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ALLOCATED_LINCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISCOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DISCOUNT"));
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
