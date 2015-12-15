/**
 * EBPP_DM_ALLOCATION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_DM_ALLOCATION  implements java.io.Serializable {
    private java.lang.String CASE_GUID;

    private java.lang.String LINCT;

    public EBPP_DM_ALLOCATION() {
    }

    public EBPP_DM_ALLOCATION(
           java.lang.String CASE_GUID,
           java.lang.String LINCT) {
           this.CASE_GUID = CASE_GUID;
           this.LINCT = LINCT;
    }


    /**
     * Gets the CASE_GUID value for this EBPP_DM_ALLOCATION.
     * 
     * @return CASE_GUID
     */
    public java.lang.String getCASE_GUID() {
        return CASE_GUID;
    }


    /**
     * Sets the CASE_GUID value for this EBPP_DM_ALLOCATION.
     * 
     * @param CASE_GUID
     */
    public void setCASE_GUID(java.lang.String CASE_GUID) {
        this.CASE_GUID = CASE_GUID;
    }


    /**
     * Gets the LINCT value for this EBPP_DM_ALLOCATION.
     * 
     * @return LINCT
     */
    public java.lang.String getLINCT() {
        return LINCT;
    }


    /**
     * Sets the LINCT value for this EBPP_DM_ALLOCATION.
     * 
     * @param LINCT
     */
    public void setLINCT(java.lang.String LINCT) {
        this.LINCT = LINCT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_DM_ALLOCATION)) return false;
        EBPP_DM_ALLOCATION other = (EBPP_DM_ALLOCATION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CASE_GUID==null && other.getCASE_GUID()==null) || 
             (this.CASE_GUID!=null &&
              this.CASE_GUID.equals(other.getCASE_GUID()))) &&
            ((this.LINCT==null && other.getLINCT()==null) || 
             (this.LINCT!=null &&
              this.LINCT.equals(other.getLINCT())));
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
        if (getCASE_GUID() != null) {
            _hashCode += getCASE_GUID().hashCode();
        }
        if (getLINCT() != null) {
            _hashCode += getLINCT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_DM_ALLOCATION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_DM_ALLOCATION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CASE_GUID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CASE_GUID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LINCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LINCT"));
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
