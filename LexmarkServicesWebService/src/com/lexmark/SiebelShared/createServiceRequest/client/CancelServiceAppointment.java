/**
 * CancelServiceAppointment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class CancelServiceAppointment  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentWSInput cancelServiceAppointmentWSInput;

    public CancelServiceAppointment() {
    }

    public CancelServiceAppointment(
           com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentWSInput cancelServiceAppointmentWSInput) {
           this.cancelServiceAppointmentWSInput = cancelServiceAppointmentWSInput;
    }


    /**
     * Gets the cancelServiceAppointmentWSInput value for this CancelServiceAppointment.
     * 
     * @return cancelServiceAppointmentWSInput
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentWSInput getCancelServiceAppointmentWSInput() {
        return cancelServiceAppointmentWSInput;
    }


    /**
     * Sets the cancelServiceAppointmentWSInput value for this CancelServiceAppointment.
     * 
     * @param cancelServiceAppointmentWSInput
     */
    public void setCancelServiceAppointmentWSInput(com.lexmark.SiebelShared.createServiceRequest.client.CancelServiceAppointmentWSInput cancelServiceAppointmentWSInput) {
        this.cancelServiceAppointmentWSInput = cancelServiceAppointmentWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelServiceAppointment)) return false;
        CancelServiceAppointment other = (CancelServiceAppointment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cancelServiceAppointmentWSInput==null && other.getCancelServiceAppointmentWSInput()==null) || 
             (this.cancelServiceAppointmentWSInput!=null &&
              this.cancelServiceAppointmentWSInput.equals(other.getCancelServiceAppointmentWSInput())));
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
        if (getCancelServiceAppointmentWSInput() != null) {
            _hashCode += getCancelServiceAppointmentWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelServiceAppointment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelServiceAppointmentWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cancelServiceAppointmentWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentWSInput"));
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
