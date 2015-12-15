/**
 * APAR_EBPP_CARD.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class APAR_EBPP_CARD  implements java.io.Serializable {
    private java.lang.String CARDDETAILID;

    private java.lang.String DESCRIP;

    public APAR_EBPP_CARD() {
    }

    public APAR_EBPP_CARD(
           java.lang.String CARDDETAILID,
           java.lang.String DESCRIP) {
           this.CARDDETAILID = CARDDETAILID;
           this.DESCRIP = DESCRIP;
    }


    /**
     * Gets the CARDDETAILID value for this APAR_EBPP_CARD.
     * 
     * @return CARDDETAILID
     */
    public java.lang.String getCARDDETAILID() {
        return CARDDETAILID;
    }


    /**
     * Sets the CARDDETAILID value for this APAR_EBPP_CARD.
     * 
     * @param CARDDETAILID
     */
    public void setCARDDETAILID(java.lang.String CARDDETAILID) {
        this.CARDDETAILID = CARDDETAILID;
    }


    /**
     * Gets the DESCRIP value for this APAR_EBPP_CARD.
     * 
     * @return DESCRIP
     */
    public java.lang.String getDESCRIP() {
        return DESCRIP;
    }


    /**
     * Sets the DESCRIP value for this APAR_EBPP_CARD.
     * 
     * @param DESCRIP
     */
    public void setDESCRIP(java.lang.String DESCRIP) {
        this.DESCRIP = DESCRIP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof APAR_EBPP_CARD)) return false;
        APAR_EBPP_CARD other = (APAR_EBPP_CARD) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CARDDETAILID==null && other.getCARDDETAILID()==null) || 
             (this.CARDDETAILID!=null &&
              this.CARDDETAILID.equals(other.getCARDDETAILID()))) &&
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
        if (getCARDDETAILID() != null) {
            _hashCode += getCARDDETAILID().hashCode();
        }
        if (getDESCRIP() != null) {
            _hashCode += getDESCRIP().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(APAR_EBPP_CARD.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "APAR_EBPP_CARD"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARDDETAILID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARDDETAILID"));
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
