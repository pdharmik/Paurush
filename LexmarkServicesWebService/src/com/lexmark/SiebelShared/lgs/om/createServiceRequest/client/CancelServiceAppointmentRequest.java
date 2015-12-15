/**
 * CancelServiceAppointmentRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class CancelServiceAppointmentRequest  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentRequestListWSInput cancelServiceAppointmentRequestListWSInput;

    public CancelServiceAppointmentRequest() {
    }

    public CancelServiceAppointmentRequest(
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentRequestListWSInput cancelServiceAppointmentRequestListWSInput) {
           this.cancelServiceAppointmentRequestListWSInput = cancelServiceAppointmentRequestListWSInput;
    }


    /**
     * Gets the cancelServiceAppointmentRequestListWSInput value for this CancelServiceAppointmentRequest.
     * 
     * @return cancelServiceAppointmentRequestListWSInput
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentRequestListWSInput getCancelServiceAppointmentRequestListWSInput() {
        return cancelServiceAppointmentRequestListWSInput;
    }


    /**
     * Sets the cancelServiceAppointmentRequestListWSInput value for this CancelServiceAppointmentRequest.
     * 
     * @param cancelServiceAppointmentRequestListWSInput
     */
    public void setCancelServiceAppointmentRequestListWSInput(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentRequestListWSInput cancelServiceAppointmentRequestListWSInput) {
        this.cancelServiceAppointmentRequestListWSInput = cancelServiceAppointmentRequestListWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelServiceAppointmentRequest)) return false;
        CancelServiceAppointmentRequest other = (CancelServiceAppointmentRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cancelServiceAppointmentRequestListWSInput==null && other.getCancelServiceAppointmentRequestListWSInput()==null) || 
             (this.cancelServiceAppointmentRequestListWSInput!=null &&
              this.cancelServiceAppointmentRequestListWSInput.equals(other.getCancelServiceAppointmentRequestListWSInput())));
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
        if (getCancelServiceAppointmentRequestListWSInput() != null) {
            _hashCode += getCancelServiceAppointmentRequestListWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelServiceAppointmentRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelServiceAppointmentRequestListWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cancelServiceAppointmentRequestListWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequestListWSInput"));
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
