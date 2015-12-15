/**
 * ConfirmServiceAppointment2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ConfirmServiceAppointment2  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointmentWSInput confirmServiceAppointmentWSInput;

    public ConfirmServiceAppointment2() {
    }

    public ConfirmServiceAppointment2(
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointmentWSInput confirmServiceAppointmentWSInput) {
           this.confirmServiceAppointmentWSInput = confirmServiceAppointmentWSInput;
    }


    /**
     * Gets the confirmServiceAppointmentWSInput value for this ConfirmServiceAppointment2.
     * 
     * @return confirmServiceAppointmentWSInput
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointmentWSInput getConfirmServiceAppointmentWSInput() {
        return confirmServiceAppointmentWSInput;
    }


    /**
     * Sets the confirmServiceAppointmentWSInput value for this ConfirmServiceAppointment2.
     * 
     * @param confirmServiceAppointmentWSInput
     */
    public void setConfirmServiceAppointmentWSInput(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ConfirmServiceAppointmentWSInput confirmServiceAppointmentWSInput) {
        this.confirmServiceAppointmentWSInput = confirmServiceAppointmentWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConfirmServiceAppointment2)) return false;
        ConfirmServiceAppointment2 other = (ConfirmServiceAppointment2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.confirmServiceAppointmentWSInput==null && other.getConfirmServiceAppointmentWSInput()==null) || 
             (this.confirmServiceAppointmentWSInput!=null &&
              this.confirmServiceAppointmentWSInput.equals(other.getConfirmServiceAppointmentWSInput())));
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
        if (getConfirmServiceAppointmentWSInput() != null) {
            _hashCode += getConfirmServiceAppointmentWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConfirmServiceAppointment2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointment2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirmServiceAppointmentWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confirmServiceAppointmentWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointmentWSInput"));
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
