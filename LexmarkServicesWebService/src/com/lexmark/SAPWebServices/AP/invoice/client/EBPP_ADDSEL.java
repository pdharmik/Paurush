/**
 * EBPP_ADDSEL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_ADDSEL  implements java.io.Serializable {
    private java.lang.String ORGTYPE;

    private java.lang.String ORGKEY;

    private java.lang.String ADDSELTYPE;

    private java.lang.String ADDSELKEY;

    public EBPP_ADDSEL() {
    }

    public EBPP_ADDSEL(
           java.lang.String ORGTYPE,
           java.lang.String ORGKEY,
           java.lang.String ADDSELTYPE,
           java.lang.String ADDSELKEY) {
           this.ORGTYPE = ORGTYPE;
           this.ORGKEY = ORGKEY;
           this.ADDSELTYPE = ADDSELTYPE;
           this.ADDSELKEY = ADDSELKEY;
    }


    /**
     * Gets the ORGTYPE value for this EBPP_ADDSEL.
     * 
     * @return ORGTYPE
     */
    public java.lang.String getORGTYPE() {
        return ORGTYPE;
    }


    /**
     * Sets the ORGTYPE value for this EBPP_ADDSEL.
     * 
     * @param ORGTYPE
     */
    public void setORGTYPE(java.lang.String ORGTYPE) {
        this.ORGTYPE = ORGTYPE;
    }


    /**
     * Gets the ORGKEY value for this EBPP_ADDSEL.
     * 
     * @return ORGKEY
     */
    public java.lang.String getORGKEY() {
        return ORGKEY;
    }


    /**
     * Sets the ORGKEY value for this EBPP_ADDSEL.
     * 
     * @param ORGKEY
     */
    public void setORGKEY(java.lang.String ORGKEY) {
        this.ORGKEY = ORGKEY;
    }


    /**
     * Gets the ADDSELTYPE value for this EBPP_ADDSEL.
     * 
     * @return ADDSELTYPE
     */
    public java.lang.String getADDSELTYPE() {
        return ADDSELTYPE;
    }


    /**
     * Sets the ADDSELTYPE value for this EBPP_ADDSEL.
     * 
     * @param ADDSELTYPE
     */
    public void setADDSELTYPE(java.lang.String ADDSELTYPE) {
        this.ADDSELTYPE = ADDSELTYPE;
    }


    /**
     * Gets the ADDSELKEY value for this EBPP_ADDSEL.
     * 
     * @return ADDSELKEY
     */
    public java.lang.String getADDSELKEY() {
        return ADDSELKEY;
    }


    /**
     * Sets the ADDSELKEY value for this EBPP_ADDSEL.
     * 
     * @param ADDSELKEY
     */
    public void setADDSELKEY(java.lang.String ADDSELKEY) {
        this.ADDSELKEY = ADDSELKEY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_ADDSEL)) return false;
        EBPP_ADDSEL other = (EBPP_ADDSEL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ORGTYPE==null && other.getORGTYPE()==null) || 
             (this.ORGTYPE!=null &&
              this.ORGTYPE.equals(other.getORGTYPE()))) &&
            ((this.ORGKEY==null && other.getORGKEY()==null) || 
             (this.ORGKEY!=null &&
              this.ORGKEY.equals(other.getORGKEY()))) &&
            ((this.ADDSELTYPE==null && other.getADDSELTYPE()==null) || 
             (this.ADDSELTYPE!=null &&
              this.ADDSELTYPE.equals(other.getADDSELTYPE()))) &&
            ((this.ADDSELKEY==null && other.getADDSELKEY()==null) || 
             (this.ADDSELKEY!=null &&
              this.ADDSELKEY.equals(other.getADDSELKEY())));
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
        if (getORGTYPE() != null) {
            _hashCode += getORGTYPE().hashCode();
        }
        if (getORGKEY() != null) {
            _hashCode += getORGKEY().hashCode();
        }
        if (getADDSELTYPE() != null) {
            _hashCode += getADDSELTYPE().hashCode();
        }
        if (getADDSELKEY() != null) {
            _hashCode += getADDSELKEY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_ADDSEL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_ADDSEL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGTYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ORGTYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORGKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ORGKEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ADDSELTYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ADDSELTYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ADDSELKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ADDSELKEY"));
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
