/**
 * BackOrderedQuantity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.source.updateServiceOrders.client;

public class BackOrderedQuantity  implements java.io.Serializable {
    private java.lang.String backOrderedQuantityUnitOfMeasure;

    private java.lang.String backOrderedQuantity;

    public BackOrderedQuantity() {
    }

    public BackOrderedQuantity(
           java.lang.String backOrderedQuantityUnitOfMeasure,
           java.lang.String backOrderedQuantity) {
           this.backOrderedQuantityUnitOfMeasure = backOrderedQuantityUnitOfMeasure;
           this.backOrderedQuantity = backOrderedQuantity;
    }


    /**
     * Gets the backOrderedQuantityUnitOfMeasure value for this BackOrderedQuantity.
     * 
     * @return backOrderedQuantityUnitOfMeasure
     */
    public java.lang.String getBackOrderedQuantityUnitOfMeasure() {
        return backOrderedQuantityUnitOfMeasure;
    }


    /**
     * Sets the backOrderedQuantityUnitOfMeasure value for this BackOrderedQuantity.
     * 
     * @param backOrderedQuantityUnitOfMeasure
     */
    public void setBackOrderedQuantityUnitOfMeasure(java.lang.String backOrderedQuantityUnitOfMeasure) {
        this.backOrderedQuantityUnitOfMeasure = backOrderedQuantityUnitOfMeasure;
    }


    /**
     * Gets the backOrderedQuantity value for this BackOrderedQuantity.
     * 
     * @return backOrderedQuantity
     */
    public java.lang.String getBackOrderedQuantity() {
        return backOrderedQuantity;
    }


    /**
     * Sets the backOrderedQuantity value for this BackOrderedQuantity.
     * 
     * @param backOrderedQuantity
     */
    public void setBackOrderedQuantity(java.lang.String backOrderedQuantity) {
        this.backOrderedQuantity = backOrderedQuantity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BackOrderedQuantity)) return false;
        BackOrderedQuantity other = (BackOrderedQuantity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.backOrderedQuantityUnitOfMeasure==null && other.getBackOrderedQuantityUnitOfMeasure()==null) || 
             (this.backOrderedQuantityUnitOfMeasure!=null &&
              this.backOrderedQuantityUnitOfMeasure.equals(other.getBackOrderedQuantityUnitOfMeasure()))) &&
            ((this.backOrderedQuantity==null && other.getBackOrderedQuantity()==null) || 
             (this.backOrderedQuantity!=null &&
              this.backOrderedQuantity.equals(other.getBackOrderedQuantity())));
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
        if (getBackOrderedQuantityUnitOfMeasure() != null) {
            _hashCode += getBackOrderedQuantityUnitOfMeasure().hashCode();
        }
        if (getBackOrderedQuantity() != null) {
            _hashCode += getBackOrderedQuantity().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BackOrderedQuantity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "BackOrderedQuantity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("backOrderedQuantityUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BackOrderedQuantityUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("backOrderedQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BackOrderedQuantity"));
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
