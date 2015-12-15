/**
 * ShippedQuantity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.source.updateServiceOrders.client;

public class ShippedQuantity  implements java.io.Serializable {
    private java.lang.String shippedQuantityUnitOfMeasure;

    private java.lang.String shippedQuantity;

    public ShippedQuantity() {
    }

    public ShippedQuantity(
           java.lang.String shippedQuantityUnitOfMeasure,
           java.lang.String shippedQuantity) {
           this.shippedQuantityUnitOfMeasure = shippedQuantityUnitOfMeasure;
           this.shippedQuantity = shippedQuantity;
    }


    /**
     * Gets the shippedQuantityUnitOfMeasure value for this ShippedQuantity.
     * 
     * @return shippedQuantityUnitOfMeasure
     */
    public java.lang.String getShippedQuantityUnitOfMeasure() {
        return shippedQuantityUnitOfMeasure;
    }


    /**
     * Sets the shippedQuantityUnitOfMeasure value for this ShippedQuantity.
     * 
     * @param shippedQuantityUnitOfMeasure
     */
    public void setShippedQuantityUnitOfMeasure(java.lang.String shippedQuantityUnitOfMeasure) {
        this.shippedQuantityUnitOfMeasure = shippedQuantityUnitOfMeasure;
    }


    /**
     * Gets the shippedQuantity value for this ShippedQuantity.
     * 
     * @return shippedQuantity
     */
    public java.lang.String getShippedQuantity() {
        return shippedQuantity;
    }


    /**
     * Sets the shippedQuantity value for this ShippedQuantity.
     * 
     * @param shippedQuantity
     */
    public void setShippedQuantity(java.lang.String shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ShippedQuantity)) return false;
        ShippedQuantity other = (ShippedQuantity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.shippedQuantityUnitOfMeasure==null && other.getShippedQuantityUnitOfMeasure()==null) || 
             (this.shippedQuantityUnitOfMeasure!=null &&
              this.shippedQuantityUnitOfMeasure.equals(other.getShippedQuantityUnitOfMeasure()))) &&
            ((this.shippedQuantity==null && other.getShippedQuantity()==null) || 
             (this.shippedQuantity!=null &&
              this.shippedQuantity.equals(other.getShippedQuantity())));
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
        if (getShippedQuantityUnitOfMeasure() != null) {
            _hashCode += getShippedQuantityUnitOfMeasure().hashCode();
        }
        if (getShippedQuantity() != null) {
            _hashCode += getShippedQuantity().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ShippedQuantity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "ShippedQuantity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shippedQuantityUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShippedQuantityUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shippedQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShippedQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
