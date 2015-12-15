/**
 * EBPP_INIT_ADD_DATA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_INIT_ADD_DATA  implements java.io.Serializable {
    private java.lang.String ID;

    private java.lang.String DATE_VALUE;

    private java.lang.String CHAR_VALUE;

    private java.math.BigDecimal AMOUNT_VALUE;

    private java.lang.String TEXT;

    public EBPP_INIT_ADD_DATA() {
    }

    public EBPP_INIT_ADD_DATA(
           java.lang.String ID,
           java.lang.String DATE_VALUE,
           java.lang.String CHAR_VALUE,
           java.math.BigDecimal AMOUNT_VALUE,
           java.lang.String TEXT) {
           this.ID = ID;
           this.DATE_VALUE = DATE_VALUE;
           this.CHAR_VALUE = CHAR_VALUE;
           this.AMOUNT_VALUE = AMOUNT_VALUE;
           this.TEXT = TEXT;
    }


    /**
     * Gets the ID value for this EBPP_INIT_ADD_DATA.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this EBPP_INIT_ADD_DATA.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the DATE_VALUE value for this EBPP_INIT_ADD_DATA.
     * 
     * @return DATE_VALUE
     */
    public java.lang.String getDATE_VALUE() {
        return DATE_VALUE;
    }


    /**
     * Sets the DATE_VALUE value for this EBPP_INIT_ADD_DATA.
     * 
     * @param DATE_VALUE
     */
    public void setDATE_VALUE(java.lang.String DATE_VALUE) {
        this.DATE_VALUE = DATE_VALUE;
    }


    /**
     * Gets the CHAR_VALUE value for this EBPP_INIT_ADD_DATA.
     * 
     * @return CHAR_VALUE
     */
    public java.lang.String getCHAR_VALUE() {
        return CHAR_VALUE;
    }


    /**
     * Sets the CHAR_VALUE value for this EBPP_INIT_ADD_DATA.
     * 
     * @param CHAR_VALUE
     */
    public void setCHAR_VALUE(java.lang.String CHAR_VALUE) {
        this.CHAR_VALUE = CHAR_VALUE;
    }


    /**
     * Gets the AMOUNT_VALUE value for this EBPP_INIT_ADD_DATA.
     * 
     * @return AMOUNT_VALUE
     */
    public java.math.BigDecimal getAMOUNT_VALUE() {
        return AMOUNT_VALUE;
    }


    /**
     * Sets the AMOUNT_VALUE value for this EBPP_INIT_ADD_DATA.
     * 
     * @param AMOUNT_VALUE
     */
    public void setAMOUNT_VALUE(java.math.BigDecimal AMOUNT_VALUE) {
        this.AMOUNT_VALUE = AMOUNT_VALUE;
    }


    /**
     * Gets the TEXT value for this EBPP_INIT_ADD_DATA.
     * 
     * @return TEXT
     */
    public java.lang.String getTEXT() {
        return TEXT;
    }


    /**
     * Sets the TEXT value for this EBPP_INIT_ADD_DATA.
     * 
     * @param TEXT
     */
    public void setTEXT(java.lang.String TEXT) {
        this.TEXT = TEXT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_INIT_ADD_DATA)) return false;
        EBPP_INIT_ADD_DATA other = (EBPP_INIT_ADD_DATA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.DATE_VALUE==null && other.getDATE_VALUE()==null) || 
             (this.DATE_VALUE!=null &&
              this.DATE_VALUE.equals(other.getDATE_VALUE()))) &&
            ((this.CHAR_VALUE==null && other.getCHAR_VALUE()==null) || 
             (this.CHAR_VALUE!=null &&
              this.CHAR_VALUE.equals(other.getCHAR_VALUE()))) &&
            ((this.AMOUNT_VALUE==null && other.getAMOUNT_VALUE()==null) || 
             (this.AMOUNT_VALUE!=null &&
              this.AMOUNT_VALUE.equals(other.getAMOUNT_VALUE()))) &&
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
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getDATE_VALUE() != null) {
            _hashCode += getDATE_VALUE().hashCode();
        }
        if (getCHAR_VALUE() != null) {
            _hashCode += getCHAR_VALUE().hashCode();
        }
        if (getAMOUNT_VALUE() != null) {
            _hashCode += getAMOUNT_VALUE().hashCode();
        }
        if (getTEXT() != null) {
            _hashCode += getTEXT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_INIT_ADD_DATA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_INIT_ADD_DATA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATE_VALUE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATE_VALUE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CHAR_VALUE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CHAR_VALUE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AMOUNT_VALUE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AMOUNT_VALUE"));
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
