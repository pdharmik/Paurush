/**
 * ServiceRequestDetailsOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ServiceRequestDetailsOutput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWSOutput serviceRequestWSOutput;

    public ServiceRequestDetailsOutput() {
    }

    public ServiceRequestDetailsOutput(
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWSOutput serviceRequestWSOutput) {
           this.serviceRequestWSOutput = serviceRequestWSOutput;
    }


    /**
     * Gets the serviceRequestWSOutput value for this ServiceRequestDetailsOutput.
     * 
     * @return serviceRequestWSOutput
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWSOutput getServiceRequestWSOutput() {
        return serviceRequestWSOutput;
    }


    /**
     * Sets the serviceRequestWSOutput value for this ServiceRequestDetailsOutput.
     * 
     * @param serviceRequestWSOutput
     */
    public void setServiceRequestWSOutput(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceRequestWSOutput serviceRequestWSOutput) {
        this.serviceRequestWSOutput = serviceRequestWSOutput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestDetailsOutput)) return false;
        ServiceRequestDetailsOutput other = (ServiceRequestDetailsOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceRequestWSOutput==null && other.getServiceRequestWSOutput()==null) || 
             (this.serviceRequestWSOutput!=null &&
              this.serviceRequestWSOutput.equals(other.getServiceRequestWSOutput())));
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
        if (getServiceRequestWSOutput() != null) {
            _hashCode += getServiceRequestWSOutput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestDetailsOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestDetailsOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestWSOutput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestWSOutput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestWSOutput"));
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
