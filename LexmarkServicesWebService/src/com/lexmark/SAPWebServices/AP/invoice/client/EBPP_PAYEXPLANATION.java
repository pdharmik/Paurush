/**
 * EBPP_PAYEXPLANATION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_PAYEXPLANATION  implements java.io.Serializable {
    private java.lang.String REFID;

    private java.lang.String TYPE;

    private java.lang.String CURRENCY;

    private java.math.BigDecimal AMOUNT;

    private java.lang.String DOCT1;

    public EBPP_PAYEXPLANATION() {
    }

    public EBPP_PAYEXPLANATION(
           java.lang.String REFID,
           java.lang.String TYPE,
           java.lang.String CURRENCY,
           java.math.BigDecimal AMOUNT,
           java.lang.String DOCT1) {
           this.REFID = REFID;
           this.TYPE = TYPE;
           this.CURRENCY = CURRENCY;
           this.AMOUNT = AMOUNT;
           this.DOCT1 = DOCT1;
    }


    /**
     * Gets the REFID value for this EBPP_PAYEXPLANATION.
     * 
     * @return REFID
     */
    public java.lang.String getREFID() {
        return REFID;
    }


    /**
     * Sets the REFID value for this EBPP_PAYEXPLANATION.
     * 
     * @param REFID
     */
    public void setREFID(java.lang.String REFID) {
        this.REFID = REFID;
    }


    /**
     * Gets the TYPE value for this EBPP_PAYEXPLANATION.
     * 
     * @return TYPE
     */
    public java.lang.String getTYPE() {
        return TYPE;
    }


    /**
     * Sets the TYPE value for this EBPP_PAYEXPLANATION.
     * 
     * @param TYPE
     */
    public void setTYPE(java.lang.String TYPE) {
        this.TYPE = TYPE;
    }


    /**
     * Gets the CURRENCY value for this EBPP_PAYEXPLANATION.
     * 
     * @return CURRENCY
     */
    public java.lang.String getCURRENCY() {
        return CURRENCY;
    }


    /**
     * Sets the CURRENCY value for this EBPP_PAYEXPLANATION.
     * 
     * @param CURRENCY
     */
    public void setCURRENCY(java.lang.String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }


    /**
     * Gets the AMOUNT value for this EBPP_PAYEXPLANATION.
     * 
     * @return AMOUNT
     */
    public java.math.BigDecimal getAMOUNT() {
        return AMOUNT;
    }


    /**
     * Sets the AMOUNT value for this EBPP_PAYEXPLANATION.
     * 
     * @param AMOUNT
     */
    public void setAMOUNT(java.math.BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }


    /**
     * Gets the DOCT1 value for this EBPP_PAYEXPLANATION.
     * 
     * @return DOCT1
     */
    public java.lang.String getDOCT1() {
        return DOCT1;
    }


    /**
     * Sets the DOCT1 value for this EBPP_PAYEXPLANATION.
     * 
     * @param DOCT1
     */
    public void setDOCT1(java.lang.String DOCT1) {
        this.DOCT1 = DOCT1;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_PAYEXPLANATION)) return false;
        EBPP_PAYEXPLANATION other = (EBPP_PAYEXPLANATION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.REFID==null && other.getREFID()==null) || 
             (this.REFID!=null &&
              this.REFID.equals(other.getREFID()))) &&
            ((this.TYPE==null && other.getTYPE()==null) || 
             (this.TYPE!=null &&
              this.TYPE.equals(other.getTYPE()))) &&
            ((this.CURRENCY==null && other.getCURRENCY()==null) || 
             (this.CURRENCY!=null &&
              this.CURRENCY.equals(other.getCURRENCY()))) &&
            ((this.AMOUNT==null && other.getAMOUNT()==null) || 
             (this.AMOUNT!=null &&
              this.AMOUNT.equals(other.getAMOUNT()))) &&
            ((this.DOCT1==null && other.getDOCT1()==null) || 
             (this.DOCT1!=null &&
              this.DOCT1.equals(other.getDOCT1())));
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
        if (getREFID() != null) {
            _hashCode += getREFID().hashCode();
        }
        if (getTYPE() != null) {
            _hashCode += getTYPE().hashCode();
        }
        if (getCURRENCY() != null) {
            _hashCode += getCURRENCY().hashCode();
        }
        if (getAMOUNT() != null) {
            _hashCode += getAMOUNT().hashCode();
        }
        if (getDOCT1() != null) {
            _hashCode += getDOCT1().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_PAYEXPLANATION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PAYEXPLANATION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REFID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REFID"));
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
        elemField.setFieldName("DOCT1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOCT1"));
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
