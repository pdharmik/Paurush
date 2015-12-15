/**
 * SellableItemServiceRequestWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class SellableItemServiceRequestWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.SellableItemsServiceRequestWSInput sellableItemsServiceRequestWSInput;

    public SellableItemServiceRequestWSInput() {
    }

    public SellableItemServiceRequestWSInput(
           com.lexmark.SiebelShared.createServiceRequest.client.SellableItemsServiceRequestWSInput sellableItemsServiceRequestWSInput) {
           this.sellableItemsServiceRequestWSInput = sellableItemsServiceRequestWSInput;
    }


    /**
     * Gets the sellableItemsServiceRequestWSInput value for this SellableItemServiceRequestWSInput.
     * 
     * @return sellableItemsServiceRequestWSInput
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SellableItemsServiceRequestWSInput getSellableItemsServiceRequestWSInput() {
        return sellableItemsServiceRequestWSInput;
    }


    /**
     * Sets the sellableItemsServiceRequestWSInput value for this SellableItemServiceRequestWSInput.
     * 
     * @param sellableItemsServiceRequestWSInput
     */
    public void setSellableItemsServiceRequestWSInput(com.lexmark.SiebelShared.createServiceRequest.client.SellableItemsServiceRequestWSInput sellableItemsServiceRequestWSInput) {
        this.sellableItemsServiceRequestWSInput = sellableItemsServiceRequestWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SellableItemServiceRequestWSInput)) return false;
        SellableItemServiceRequestWSInput other = (SellableItemServiceRequestWSInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sellableItemsServiceRequestWSInput==null && other.getSellableItemsServiceRequestWSInput()==null) || 
             (this.sellableItemsServiceRequestWSInput!=null &&
              this.sellableItemsServiceRequestWSInput.equals(other.getSellableItemsServiceRequestWSInput())));
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
        if (getSellableItemsServiceRequestWSInput() != null) {
            _hashCode += getSellableItemsServiceRequestWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SellableItemServiceRequestWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SellableItemServiceRequestWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sellableItemsServiceRequestWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SellableItemsServiceRequestWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SellableItemsServiceRequestWSInput"));
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
