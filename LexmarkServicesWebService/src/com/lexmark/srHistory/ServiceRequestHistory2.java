/**
 * ServiceRequestHistory2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class ServiceRequestHistory2  implements java.io.Serializable {
    private com.lexmark.srHistory.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.srHistory.ServiceRequest serviceRequest;

    public ServiceRequestHistory2() {
    }

    public ServiceRequestHistory2(
           com.lexmark.srHistory.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.srHistory.ServiceRequest serviceRequest) {
           this.documentMetaData = documentMetaData;
           this.serviceRequest = serviceRequest;
    }


    /**
     * Gets the documentMetaData value for this ServiceRequestHistory2.
     * 
     * @return documentMetaData
     */
    public com.lexmark.srHistory.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this ServiceRequestHistory2.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.srHistory.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the serviceRequest value for this ServiceRequestHistory2.
     * 
     * @return serviceRequest
     */
    public com.lexmark.srHistory.ServiceRequest getServiceRequest() {
        return serviceRequest;
    }


    /**
     * Sets the serviceRequest value for this ServiceRequestHistory2.
     * 
     * @param serviceRequest
     */
    public void setServiceRequest(com.lexmark.srHistory.ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestHistory2)) return false;
        ServiceRequestHistory2 other = (ServiceRequestHistory2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentMetaData==null && other.getDocumentMetaData()==null) || 
             (this.documentMetaData!=null &&
              this.documentMetaData.equals(other.getDocumentMetaData()))) &&
            ((this.serviceRequest==null && other.getServiceRequest()==null) || 
             (this.serviceRequest!=null &&
              this.serviceRequest.equals(other.getServiceRequest())));
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
        if (getDocumentMetaData() != null) {
            _hashCode += getDocumentMetaData().hashCode();
        }
        if (getServiceRequest() != null) {
            _hashCode += getServiceRequest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestHistory2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequestHistory2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequest"));
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
