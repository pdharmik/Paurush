/**
 * StatusServiceRequestWSOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class StatusServiceRequestWSOutput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput2 statusServiceRequestWSOutput;

    public StatusServiceRequestWSOutput() {
    }

    public StatusServiceRequestWSOutput(
           com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput2 statusServiceRequestWSOutput) {
           this.statusServiceRequestWSOutput = statusServiceRequestWSOutput;
    }


    /**
     * Gets the statusServiceRequestWSOutput value for this StatusServiceRequestWSOutput.
     * 
     * @return statusServiceRequestWSOutput
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput2 getStatusServiceRequestWSOutput() {
        return statusServiceRequestWSOutput;
    }


    /**
     * Sets the statusServiceRequestWSOutput value for this StatusServiceRequestWSOutput.
     * 
     * @param statusServiceRequestWSOutput
     */
    public void setStatusServiceRequestWSOutput(com.lexmark.SiebelShared.createServiceRequest.client.StatusServiceRequestWSOutput2 statusServiceRequestWSOutput) {
        this.statusServiceRequestWSOutput = statusServiceRequestWSOutput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusServiceRequestWSOutput)) return false;
        StatusServiceRequestWSOutput other = (StatusServiceRequestWSOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.statusServiceRequestWSOutput==null && other.getStatusServiceRequestWSOutput()==null) || 
             (this.statusServiceRequestWSOutput!=null &&
              this.statusServiceRequestWSOutput.equals(other.getStatusServiceRequestWSOutput())));
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
        if (getStatusServiceRequestWSOutput() != null) {
            _hashCode += getStatusServiceRequestWSOutput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusServiceRequestWSOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "statusServiceRequestWSOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusServiceRequestWSOutput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusServiceRequestWSOutput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "statusServiceRequestWSOutput2"));
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
