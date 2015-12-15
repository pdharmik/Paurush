/**
 * EscalateServiceRequestWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class EscalateServiceRequestWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestWSInput2 escalateServiceRequestWSInput;

    public EscalateServiceRequestWSInput() {
    }

    public EscalateServiceRequestWSInput(
           com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestWSInput2 escalateServiceRequestWSInput) {
           this.escalateServiceRequestWSInput = escalateServiceRequestWSInput;
    }


    /**
     * Gets the escalateServiceRequestWSInput value for this EscalateServiceRequestWSInput.
     * 
     * @return escalateServiceRequestWSInput
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestWSInput2 getEscalateServiceRequestWSInput() {
        return escalateServiceRequestWSInput;
    }


    /**
     * Sets the escalateServiceRequestWSInput value for this EscalateServiceRequestWSInput.
     * 
     * @param escalateServiceRequestWSInput
     */
    public void setEscalateServiceRequestWSInput(com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestWSInput2 escalateServiceRequestWSInput) {
        this.escalateServiceRequestWSInput = escalateServiceRequestWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EscalateServiceRequestWSInput)) return false;
        EscalateServiceRequestWSInput other = (EscalateServiceRequestWSInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.escalateServiceRequestWSInput==null && other.getEscalateServiceRequestWSInput()==null) || 
             (this.escalateServiceRequestWSInput!=null &&
              this.escalateServiceRequestWSInput.equals(other.getEscalateServiceRequestWSInput())));
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
        if (getEscalateServiceRequestWSInput() != null) {
            _hashCode += getEscalateServiceRequestWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EscalateServiceRequestWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("escalateServiceRequestWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "escalateServiceRequestWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestWSInput2"));
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
