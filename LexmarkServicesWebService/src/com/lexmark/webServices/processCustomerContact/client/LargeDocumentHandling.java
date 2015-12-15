/**
 * LargeDocumentHandling.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.processCustomerContact.client;

public class LargeDocumentHandling  implements java.io.Serializable {
    private com.lexmark.webServices.processCustomerContact.client.StorageType storageType;

    public LargeDocumentHandling() {
    }

    public LargeDocumentHandling(
           com.lexmark.webServices.processCustomerContact.client.StorageType storageType) {
           this.storageType = storageType;
    }


    /**
     * Gets the storageType value for this LargeDocumentHandling.
     * 
     * @return storageType
     */
    public com.lexmark.webServices.processCustomerContact.client.StorageType getStorageType() {
        return storageType;
    }


    /**
     * Sets the storageType value for this LargeDocumentHandling.
     * 
     * @param storageType
     */
    public void setStorageType(com.lexmark.webServices.processCustomerContact.client.StorageType storageType) {
        this.storageType = storageType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LargeDocumentHandling)) return false;
        LargeDocumentHandling other = (LargeDocumentHandling) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.storageType==null && other.getStorageType()==null) || 
             (this.storageType!=null &&
              this.storageType.equals(other.getStorageType())));
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
        if (getStorageType() != null) {
            _hashCode += getStorageType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LargeDocumentHandling.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "LargeDocumentHandling"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storageType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StorageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "StorageType"));
        elemField.setMinOccurs(0);
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
