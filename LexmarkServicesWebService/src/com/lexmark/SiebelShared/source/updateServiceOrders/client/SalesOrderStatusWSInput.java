/**
 * SalesOrderStatusWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.source.updateServiceOrders.client;

public class SalesOrderStatusWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusWSInput2 salesOrderStatusWSInput;

    public SalesOrderStatusWSInput() {
    }

    public SalesOrderStatusWSInput(
           com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusWSInput2 salesOrderStatusWSInput) {
           this.salesOrderStatusWSInput = salesOrderStatusWSInput;
    }


    /**
     * Gets the salesOrderStatusWSInput value for this SalesOrderStatusWSInput.
     * 
     * @return salesOrderStatusWSInput
     */
    public com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusWSInput2 getSalesOrderStatusWSInput() {
        return salesOrderStatusWSInput;
    }


    /**
     * Sets the salesOrderStatusWSInput value for this SalesOrderStatusWSInput.
     * 
     * @param salesOrderStatusWSInput
     */
    public void setSalesOrderStatusWSInput(com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusWSInput2 salesOrderStatusWSInput) {
        this.salesOrderStatusWSInput = salesOrderStatusWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderStatusWSInput)) return false;
        SalesOrderStatusWSInput other = (SalesOrderStatusWSInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.salesOrderStatusWSInput==null && other.getSalesOrderStatusWSInput()==null) || 
             (this.salesOrderStatusWSInput!=null &&
              this.salesOrderStatusWSInput.equals(other.getSalesOrderStatusWSInput())));
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
        if (getSalesOrderStatusWSInput() != null) {
            _hashCode += getSalesOrderStatusWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesOrderStatusWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "SalesOrderStatusWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesOrderStatusWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SalesOrderStatusWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "SalesOrderStatusWSInput2"));
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
