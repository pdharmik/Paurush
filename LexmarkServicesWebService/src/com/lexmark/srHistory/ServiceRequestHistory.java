/**
 * ServiceRequestHistory.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class ServiceRequestHistory  implements java.io.Serializable {
    private com.lexmark.srHistory.ServiceRequestHistory2 serviceRequestHistory;

    public ServiceRequestHistory() {
    }

    public ServiceRequestHistory(
           com.lexmark.srHistory.ServiceRequestHistory2 serviceRequestHistory) {
           this.serviceRequestHistory = serviceRequestHistory;
    }


    /**
     * Gets the serviceRequestHistory value for this ServiceRequestHistory.
     * 
     * @return serviceRequestHistory
     */
    public com.lexmark.srHistory.ServiceRequestHistory2 getServiceRequestHistory() {
        return serviceRequestHistory;
    }


    /**
     * Sets the serviceRequestHistory value for this ServiceRequestHistory.
     * 
     * @param serviceRequestHistory
     */
    public void setServiceRequestHistory(com.lexmark.srHistory.ServiceRequestHistory2 serviceRequestHistory) {
        this.serviceRequestHistory = serviceRequestHistory;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestHistory)) return false;
        ServiceRequestHistory other = (ServiceRequestHistory) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceRequestHistory==null && other.getServiceRequestHistory()==null) || 
             (this.serviceRequestHistory!=null &&
              this.serviceRequestHistory.equals(other.getServiceRequestHistory())));
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
        if (getServiceRequestHistory() != null) {
            _hashCode += getServiceRequestHistory().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestHistory.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequestHistory"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequestHistory2"));
        elemField.setNillable(true);
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
