/**
 * ServiceRequestInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ServiceRequestInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSInput serviceRequestWSInput;

    public ServiceRequestInput() {
    }

    public ServiceRequestInput(
           com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSInput serviceRequestWSInput) {
           this.serviceRequestWSInput = serviceRequestWSInput;
    }


    /**
     * Gets the serviceRequestWSInput value for this ServiceRequestInput.
     * 
     * @return serviceRequestWSInput
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSInput getServiceRequestWSInput() {
        return serviceRequestWSInput;
    }


    /**
     * Sets the serviceRequestWSInput value for this ServiceRequestInput.
     * 
     * @param serviceRequestWSInput
     */
    public void setServiceRequestWSInput(com.lexmark.SiebelShared.createServiceRequest.client.ServiceRequestWSInput serviceRequestWSInput) {
        this.serviceRequestWSInput = serviceRequestWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestInput)) return false;
        ServiceRequestInput other = (ServiceRequestInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceRequestWSInput==null && other.getServiceRequestWSInput()==null) || 
             (this.serviceRequestWSInput!=null &&
              this.serviceRequestWSInput.equals(other.getServiceRequestWSInput())));
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
        if (getServiceRequestWSInput() != null) {
            _hashCode += getServiceRequestWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestWSInput"));
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
