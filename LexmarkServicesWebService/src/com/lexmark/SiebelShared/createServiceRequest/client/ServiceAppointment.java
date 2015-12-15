/**
 * ServiceAppointment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ServiceAppointment  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.GetServiceAppointmentWSInput getServiceAppointmentWSInput;

    public ServiceAppointment() {
    }

    public ServiceAppointment(
           com.lexmark.SiebelShared.createServiceRequest.client.GetServiceAppointmentWSInput getServiceAppointmentWSInput) {
           this.getServiceAppointmentWSInput = getServiceAppointmentWSInput;
    }


    /**
     * Gets the getServiceAppointmentWSInput value for this ServiceAppointment.
     * 
     * @return getServiceAppointmentWSInput
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.GetServiceAppointmentWSInput getGetServiceAppointmentWSInput() {
        return getServiceAppointmentWSInput;
    }


    /**
     * Sets the getServiceAppointmentWSInput value for this ServiceAppointment.
     * 
     * @param getServiceAppointmentWSInput
     */
    public void setGetServiceAppointmentWSInput(com.lexmark.SiebelShared.createServiceRequest.client.GetServiceAppointmentWSInput getServiceAppointmentWSInput) {
        this.getServiceAppointmentWSInput = getServiceAppointmentWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceAppointment)) return false;
        ServiceAppointment other = (ServiceAppointment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceAppointmentWSInput==null && other.getGetServiceAppointmentWSInput()==null) || 
             (this.getServiceAppointmentWSInput!=null &&
              this.getServiceAppointmentWSInput.equals(other.getGetServiceAppointmentWSInput())));
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
        if (getGetServiceAppointmentWSInput() != null) {
            _hashCode += getGetServiceAppointmentWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceAppointment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceAppointmentWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "getServiceAppointmentWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceAppointmentWSInput"));
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
