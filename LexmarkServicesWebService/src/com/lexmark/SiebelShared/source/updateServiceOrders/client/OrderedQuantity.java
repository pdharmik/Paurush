/**
 * OrderedQuantity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.source.updateServiceOrders.client;

public class OrderedQuantity  implements java.io.Serializable {
    private java.lang.String orderedQuantityUnitOfMeasure;

    private java.lang.String orderedQuantity;

    public OrderedQuantity() {
    }

    public OrderedQuantity(
           java.lang.String orderedQuantityUnitOfMeasure,
           java.lang.String orderedQuantity) {
           this.orderedQuantityUnitOfMeasure = orderedQuantityUnitOfMeasure;
           this.orderedQuantity = orderedQuantity;
    }


    /**
     * Gets the orderedQuantityUnitOfMeasure value for this OrderedQuantity.
     * 
     * @return orderedQuantityUnitOfMeasure
     */
    public java.lang.String getOrderedQuantityUnitOfMeasure() {
        return orderedQuantityUnitOfMeasure;
    }


    /**
     * Sets the orderedQuantityUnitOfMeasure value for this OrderedQuantity.
     * 
     * @param orderedQuantityUnitOfMeasure
     */
    public void setOrderedQuantityUnitOfMeasure(java.lang.String orderedQuantityUnitOfMeasure) {
        this.orderedQuantityUnitOfMeasure = orderedQuantityUnitOfMeasure;
    }


    /**
     * Gets the orderedQuantity value for this OrderedQuantity.
     * 
     * @return orderedQuantity
     */
    public java.lang.String getOrderedQuantity() {
        return orderedQuantity;
    }


    /**
     * Sets the orderedQuantity value for this OrderedQuantity.
     * 
     * @param orderedQuantity
     */
    public void setOrderedQuantity(java.lang.String orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrderedQuantity)) return false;
        OrderedQuantity other = (OrderedQuantity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.orderedQuantityUnitOfMeasure==null && other.getOrderedQuantityUnitOfMeasure()==null) || 
             (this.orderedQuantityUnitOfMeasure!=null &&
              this.orderedQuantityUnitOfMeasure.equals(other.getOrderedQuantityUnitOfMeasure()))) &&
            ((this.orderedQuantity==null && other.getOrderedQuantity()==null) || 
             (this.orderedQuantity!=null &&
              this.orderedQuantity.equals(other.getOrderedQuantity())));
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
        if (getOrderedQuantityUnitOfMeasure() != null) {
            _hashCode += getOrderedQuantityUnitOfMeasure().hashCode();
        }
        if (getOrderedQuantity() != null) {
            _hashCode += getOrderedQuantity().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrderedQuantity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "OrderedQuantity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderedQuantityUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrderedQuantityUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderedQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrderedQuantity"));
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
