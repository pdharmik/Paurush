/**
 * ZGS_CRM_ORDER_INPUT_ITEM_NO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.priceCalc;

public class ZGS_CRM_ORDER_INPUT_ITEM_NO  implements java.io.Serializable {
    private java.lang.String ITEM_NO;

    public ZGS_CRM_ORDER_INPUT_ITEM_NO() {
    }

    public ZGS_CRM_ORDER_INPUT_ITEM_NO(
           java.lang.String ITEM_NO) {
           this.ITEM_NO = ITEM_NO;
    }


    /**
     * Gets the ITEM_NO value for this ZGS_CRM_ORDER_INPUT_ITEM_NO.
     * 
     * @return ITEM_NO
     */
    public java.lang.String getITEM_NO() {
        return ITEM_NO;
    }


    /**
     * Sets the ITEM_NO value for this ZGS_CRM_ORDER_INPUT_ITEM_NO.
     * 
     * @param ITEM_NO
     */
    public void setITEM_NO(java.lang.String ITEM_NO) {
        this.ITEM_NO = ITEM_NO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ZGS_CRM_ORDER_INPUT_ITEM_NO)) return false;
        ZGS_CRM_ORDER_INPUT_ITEM_NO other = (ZGS_CRM_ORDER_INPUT_ITEM_NO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ITEM_NO==null && other.getITEM_NO()==null) || 
             (this.ITEM_NO!=null &&
              this.ITEM_NO.equals(other.getITEM_NO())));
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
        if (getITEM_NO() != null) {
            _hashCode += getITEM_NO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZGS_CRM_ORDER_INPUT_ITEM_NO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZGS_CRM_ORDER_INPUT_ITEM_NO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ITEM_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ITEM_NO"));
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
