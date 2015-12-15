/**
 * APAR_EBPP_CARDTYPES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class APAR_EBPP_CARDTYPES  implements java.io.Serializable {
    private java.lang.String INSTITUTE;

    private java.lang.String TEXT;

    public APAR_EBPP_CARDTYPES() {
    }

    public APAR_EBPP_CARDTYPES(
           java.lang.String INSTITUTE,
           java.lang.String TEXT) {
           this.INSTITUTE = INSTITUTE;
           this.TEXT = TEXT;
    }


    /**
     * Gets the INSTITUTE value for this APAR_EBPP_CARDTYPES.
     * 
     * @return INSTITUTE
     */
    public java.lang.String getINSTITUTE() {
        return INSTITUTE;
    }


    /**
     * Sets the INSTITUTE value for this APAR_EBPP_CARDTYPES.
     * 
     * @param INSTITUTE
     */
    public void setINSTITUTE(java.lang.String INSTITUTE) {
        this.INSTITUTE = INSTITUTE;
    }


    /**
     * Gets the TEXT value for this APAR_EBPP_CARDTYPES.
     * 
     * @return TEXT
     */
    public java.lang.String getTEXT() {
        return TEXT;
    }


    /**
     * Sets the TEXT value for this APAR_EBPP_CARDTYPES.
     * 
     * @param TEXT
     */
    public void setTEXT(java.lang.String TEXT) {
        this.TEXT = TEXT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof APAR_EBPP_CARDTYPES)) return false;
        APAR_EBPP_CARDTYPES other = (APAR_EBPP_CARDTYPES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.INSTITUTE==null && other.getINSTITUTE()==null) || 
             (this.INSTITUTE!=null &&
              this.INSTITUTE.equals(other.getINSTITUTE()))) &&
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
        if (getINSTITUTE() != null) {
            _hashCode += getINSTITUTE().hashCode();
        }
        if (getTEXT() != null) {
            _hashCode += getTEXT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(APAR_EBPP_CARDTYPES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "APAR_EBPP_CARDTYPES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTITUTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTITUTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
