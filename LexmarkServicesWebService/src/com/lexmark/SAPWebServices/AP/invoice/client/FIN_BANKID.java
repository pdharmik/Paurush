/**
 * FIN_BANKID.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class FIN_BANKID  implements java.io.Serializable {
    private java.lang.String BANKDETAILID;

    private java.lang.String DESCRIP;

    public FIN_BANKID() {
    }

    public FIN_BANKID(
           java.lang.String BANKDETAILID,
           java.lang.String DESCRIP) {
           this.BANKDETAILID = BANKDETAILID;
           this.DESCRIP = DESCRIP;
    }


    /**
     * Gets the BANKDETAILID value for this FIN_BANKID.
     * 
     * @return BANKDETAILID
     */
    public java.lang.String getBANKDETAILID() {
        return BANKDETAILID;
    }


    /**
     * Sets the BANKDETAILID value for this FIN_BANKID.
     * 
     * @param BANKDETAILID
     */
    public void setBANKDETAILID(java.lang.String BANKDETAILID) {
        this.BANKDETAILID = BANKDETAILID;
    }


    /**
     * Gets the DESCRIP value for this FIN_BANKID.
     * 
     * @return DESCRIP
     */
    public java.lang.String getDESCRIP() {
        return DESCRIP;
    }


    /**
     * Sets the DESCRIP value for this FIN_BANKID.
     * 
     * @param DESCRIP
     */
    public void setDESCRIP(java.lang.String DESCRIP) {
        this.DESCRIP = DESCRIP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FIN_BANKID)) return false;
        FIN_BANKID other = (FIN_BANKID) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BANKDETAILID==null && other.getBANKDETAILID()==null) || 
             (this.BANKDETAILID!=null &&
              this.BANKDETAILID.equals(other.getBANKDETAILID()))) &&
            ((this.DESCRIP==null && other.getDESCRIP()==null) || 
             (this.DESCRIP!=null &&
              this.DESCRIP.equals(other.getDESCRIP())));
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
        if (getBANKDETAILID() != null) {
            _hashCode += getBANKDETAILID().hashCode();
        }
        if (getDESCRIP() != null) {
            _hashCode += getDESCRIP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FIN_BANKID.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "FIN_BANKID"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BANKDETAILID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BANKDETAILID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DESCRIP"));
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
