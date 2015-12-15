/**
 * ConsumablesServiceRequestWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ConsumablesServiceRequestWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput2 consumablesServiceRequestWSInput;

    public ConsumablesServiceRequestWSInput() {
    }

    public ConsumablesServiceRequestWSInput(
           com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput2 consumablesServiceRequestWSInput) {
           this.consumablesServiceRequestWSInput = consumablesServiceRequestWSInput;
    }


    /**
     * Gets the consumablesServiceRequestWSInput value for this ConsumablesServiceRequestWSInput.
     * 
     * @return consumablesServiceRequestWSInput
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput2 getConsumablesServiceRequestWSInput() {
        return consumablesServiceRequestWSInput;
    }


    /**
     * Sets the consumablesServiceRequestWSInput value for this ConsumablesServiceRequestWSInput.
     * 
     * @param consumablesServiceRequestWSInput
     */
    public void setConsumablesServiceRequestWSInput(com.lexmark.SiebelShared.createServiceRequest.client.ConsumablesServiceRequestWSInput2 consumablesServiceRequestWSInput) {
        this.consumablesServiceRequestWSInput = consumablesServiceRequestWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsumablesServiceRequestWSInput)) return false;
        ConsumablesServiceRequestWSInput other = (ConsumablesServiceRequestWSInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consumablesServiceRequestWSInput==null && other.getConsumablesServiceRequestWSInput()==null) || 
             (this.consumablesServiceRequestWSInput!=null &&
              this.consumablesServiceRequestWSInput.equals(other.getConsumablesServiceRequestWSInput())));
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
        if (getConsumablesServiceRequestWSInput() != null) {
            _hashCode += getConsumablesServiceRequestWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsumablesServiceRequestWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consumablesServiceRequestWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ConsumablesServiceRequestWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ConsumablesServiceRequestWSInput2"));
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
