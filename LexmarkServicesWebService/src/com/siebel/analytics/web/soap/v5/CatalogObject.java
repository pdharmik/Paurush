/**
 * CatalogObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public class CatalogObject  implements java.io.Serializable {
    private java.lang.String catalogObject;

    private com.siebel.analytics.web.soap.v5.ItemInfo itemInfo;

    private com.siebel.analytics.web.soap.v5.ErrorInfo errorInfo;

    public CatalogObject() {
    }

    public CatalogObject(
           java.lang.String catalogObject,
           com.siebel.analytics.web.soap.v5.ItemInfo itemInfo,
           com.siebel.analytics.web.soap.v5.ErrorInfo errorInfo) {
           this.catalogObject = catalogObject;
           this.itemInfo = itemInfo;
           this.errorInfo = errorInfo;
    }


    /**
     * Gets the catalogObject value for this CatalogObject.
     * 
     * @return catalogObject
     */
    public java.lang.String getCatalogObject() {
        return catalogObject;
    }


    /**
     * Sets the catalogObject value for this CatalogObject.
     * 
     * @param catalogObject
     */
    public void setCatalogObject(java.lang.String catalogObject) {
        this.catalogObject = catalogObject;
    }


    /**
     * Gets the itemInfo value for this CatalogObject.
     * 
     * @return itemInfo
     */
    public com.siebel.analytics.web.soap.v5.ItemInfo getItemInfo() {
        return itemInfo;
    }


    /**
     * Sets the itemInfo value for this CatalogObject.
     * 
     * @param itemInfo
     */
    public void setItemInfo(com.siebel.analytics.web.soap.v5.ItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }


    /**
     * Gets the errorInfo value for this CatalogObject.
     * 
     * @return errorInfo
     */
    public com.siebel.analytics.web.soap.v5.ErrorInfo getErrorInfo() {
        return errorInfo;
    }


    /**
     * Sets the errorInfo value for this CatalogObject.
     * 
     * @param errorInfo
     */
    public void setErrorInfo(com.siebel.analytics.web.soap.v5.ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CatalogObject)) return false;
        CatalogObject other = (CatalogObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.catalogObject==null && other.getCatalogObject()==null) || 
             (this.catalogObject!=null &&
              this.catalogObject.equals(other.getCatalogObject()))) &&
            ((this.itemInfo==null && other.getItemInfo()==null) || 
             (this.itemInfo!=null &&
              this.itemInfo.equals(other.getItemInfo()))) &&
            ((this.errorInfo==null && other.getErrorInfo()==null) || 
             (this.errorInfo!=null &&
              this.errorInfo.equals(other.getErrorInfo())));
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
        if (getCatalogObject() != null) {
            _hashCode += getCatalogObject().hashCode();
        }
        if (getItemInfo() != null) {
            _hashCode += getItemInfo().hashCode();
        }
        if (getErrorInfo() != null) {
            _hashCode += getErrorInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CatalogObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "CatalogObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("catalogObject");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "catalogObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itemInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "itemInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ItemInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "errorInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ErrorInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
