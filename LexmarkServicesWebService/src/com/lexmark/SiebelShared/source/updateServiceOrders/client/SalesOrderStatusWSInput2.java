/**
 * SalesOrderStatusWSInput2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.source.updateServiceOrders.client;

public class SalesOrderStatusWSInput2  implements java.io.Serializable {
    private com.lexmark.SiebelShared.source.updateServiceOrders.client.DocumentMetaData documentMetaData;

    private com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusData salesOrderStatusData;

    public SalesOrderStatusWSInput2() {
    }

    public SalesOrderStatusWSInput2(
           com.lexmark.SiebelShared.source.updateServiceOrders.client.DocumentMetaData documentMetaData,
           com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusData salesOrderStatusData) {
           this.documentMetaData = documentMetaData;
           this.salesOrderStatusData = salesOrderStatusData;
    }


    /**
     * Gets the documentMetaData value for this SalesOrderStatusWSInput2.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.source.updateServiceOrders.client.DocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this SalesOrderStatusWSInput2.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.source.updateServiceOrders.client.DocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the salesOrderStatusData value for this SalesOrderStatusWSInput2.
     * 
     * @return salesOrderStatusData
     */
    public com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusData getSalesOrderStatusData() {
        return salesOrderStatusData;
    }


    /**
     * Sets the salesOrderStatusData value for this SalesOrderStatusWSInput2.
     * 
     * @param salesOrderStatusData
     */
    public void setSalesOrderStatusData(com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusData salesOrderStatusData) {
        this.salesOrderStatusData = salesOrderStatusData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderStatusWSInput2)) return false;
        SalesOrderStatusWSInput2 other = (SalesOrderStatusWSInput2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentMetaData==null && other.getDocumentMetaData()==null) || 
             (this.documentMetaData!=null &&
              this.documentMetaData.equals(other.getDocumentMetaData()))) &&
            ((this.salesOrderStatusData==null && other.getSalesOrderStatusData()==null) || 
             (this.salesOrderStatusData!=null &&
              this.salesOrderStatusData.equals(other.getSalesOrderStatusData())));
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
        if (getDocumentMetaData() != null) {
            _hashCode += getDocumentMetaData().hashCode();
        }
        if (getSalesOrderStatusData() != null) {
            _hashCode += getSalesOrderStatusData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SalesOrderStatusWSInput2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "SalesOrderStatusWSInput2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "DocumentMetaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesOrderStatusData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SalesOrderStatusData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "SalesOrderStatusData"));
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
