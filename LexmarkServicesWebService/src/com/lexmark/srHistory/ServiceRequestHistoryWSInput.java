/**
 * ServiceRequestHistoryWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class ServiceRequestHistoryWSInput  implements java.io.Serializable {
    private com.lexmark.srHistory.ServiceRequestHistoryWSInput2 serviceRequestHistoryWSInput;

    public ServiceRequestHistoryWSInput() {
    }

    public ServiceRequestHistoryWSInput(
           com.lexmark.srHistory.ServiceRequestHistoryWSInput2 serviceRequestHistoryWSInput) {
           this.serviceRequestHistoryWSInput = serviceRequestHistoryWSInput;
    }


    /**
     * Gets the serviceRequestHistoryWSInput value for this ServiceRequestHistoryWSInput.
     * 
     * @return serviceRequestHistoryWSInput
     */
    public com.lexmark.srHistory.ServiceRequestHistoryWSInput2 getServiceRequestHistoryWSInput() {
        return serviceRequestHistoryWSInput;
    }


    /**
     * Sets the serviceRequestHistoryWSInput value for this ServiceRequestHistoryWSInput.
     * 
     * @param serviceRequestHistoryWSInput
     */
    public void setServiceRequestHistoryWSInput(com.lexmark.srHistory.ServiceRequestHistoryWSInput2 serviceRequestHistoryWSInput) {
        this.serviceRequestHistoryWSInput = serviceRequestHistoryWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestHistoryWSInput)) return false;
        ServiceRequestHistoryWSInput other = (ServiceRequestHistoryWSInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceRequestHistoryWSInput==null && other.getServiceRequestHistoryWSInput()==null) || 
             (this.serviceRequestHistoryWSInput!=null &&
              this.serviceRequestHistoryWSInput.equals(other.getServiceRequestHistoryWSInput())));
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
        if (getServiceRequestHistoryWSInput() != null) {
            _hashCode += getServiceRequestHistoryWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestHistoryWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequestHistoryWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestHistoryWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestHistoryWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequestHistoryWSInput2"));
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
