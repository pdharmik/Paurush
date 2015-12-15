/**
 * CancelServiceRequestWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class CancelServiceRequestWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceRequestWSInput2 cancelServiceRequestWSInput;

    public CancelServiceRequestWSInput() {
    }

    public CancelServiceRequestWSInput(
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceRequestWSInput2 cancelServiceRequestWSInput) {
           this.cancelServiceRequestWSInput = cancelServiceRequestWSInput;
    }


    /**
     * Gets the cancelServiceRequestWSInput value for this CancelServiceRequestWSInput.
     * 
     * @return cancelServiceRequestWSInput
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceRequestWSInput2 getCancelServiceRequestWSInput() {
        return cancelServiceRequestWSInput;
    }


    /**
     * Sets the cancelServiceRequestWSInput value for this CancelServiceRequestWSInput.
     * 
     * @param cancelServiceRequestWSInput
     */
    public void setCancelServiceRequestWSInput(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceRequestWSInput2 cancelServiceRequestWSInput) {
        this.cancelServiceRequestWSInput = cancelServiceRequestWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelServiceRequestWSInput)) return false;
        CancelServiceRequestWSInput other = (CancelServiceRequestWSInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cancelServiceRequestWSInput==null && other.getCancelServiceRequestWSInput()==null) || 
             (this.cancelServiceRequestWSInput!=null &&
              this.cancelServiceRequestWSInput.equals(other.getCancelServiceRequestWSInput())));
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
        if (getCancelServiceRequestWSInput() != null) {
            _hashCode += getCancelServiceRequestWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelServiceRequestWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelServiceRequestWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cancelServiceRequestWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestWSInput2"));
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
