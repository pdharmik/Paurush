/**
 * ServiceRequestAndActivityDetailsInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ServiceRequestAndActivityDetailsInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestAndActivityDetails getServiceRequestAndActivityDetails;

    public ServiceRequestAndActivityDetailsInput() {
    }

    public ServiceRequestAndActivityDetailsInput(
           com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestAndActivityDetails getServiceRequestAndActivityDetails) {
           this.getServiceRequestAndActivityDetails = getServiceRequestAndActivityDetails;
    }


    /**
     * Gets the getServiceRequestAndActivityDetails value for this ServiceRequestAndActivityDetailsInput.
     * 
     * @return getServiceRequestAndActivityDetails
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestAndActivityDetails getGetServiceRequestAndActivityDetails() {
        return getServiceRequestAndActivityDetails;
    }


    /**
     * Sets the getServiceRequestAndActivityDetails value for this ServiceRequestAndActivityDetailsInput.
     * 
     * @param getServiceRequestAndActivityDetails
     */
    public void setGetServiceRequestAndActivityDetails(com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestAndActivityDetails getServiceRequestAndActivityDetails) {
        this.getServiceRequestAndActivityDetails = getServiceRequestAndActivityDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestAndActivityDetailsInput)) return false;
        ServiceRequestAndActivityDetailsInput other = (ServiceRequestAndActivityDetailsInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestAndActivityDetails==null && other.getGetServiceRequestAndActivityDetails()==null) || 
             (this.getServiceRequestAndActivityDetails!=null &&
              this.getServiceRequestAndActivityDetails.equals(other.getGetServiceRequestAndActivityDetails())));
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
        if (getGetServiceRequestAndActivityDetails() != null) {
            _hashCode += getGetServiceRequestAndActivityDetails().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestAndActivityDetailsInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceRequestAndActivityDetailsInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestAndActivityDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "getServiceRequestAndActivityDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestAndActivityDetails"));
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
