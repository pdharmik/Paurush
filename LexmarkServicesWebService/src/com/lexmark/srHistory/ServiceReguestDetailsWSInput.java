/**
 * ServiceReguestDetailsWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class ServiceReguestDetailsWSInput  implements java.io.Serializable {
    private com.lexmark.srHistory.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.srHistory.ServiceRequestDetailsData serviceRequestDetailsData;

    public ServiceReguestDetailsWSInput() {
    }

    public ServiceReguestDetailsWSInput(
           com.lexmark.srHistory.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.srHistory.ServiceRequestDetailsData serviceRequestDetailsData) {
           this.documentMetaData = documentMetaData;
           this.serviceRequestDetailsData = serviceRequestDetailsData;
    }


    /**
     * Gets the documentMetaData value for this ServiceReguestDetailsWSInput.
     * 
     * @return documentMetaData
     */
    public com.lexmark.srHistory.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this ServiceReguestDetailsWSInput.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.srHistory.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the serviceRequestDetailsData value for this ServiceReguestDetailsWSInput.
     * 
     * @return serviceRequestDetailsData
     */
    public com.lexmark.srHistory.ServiceRequestDetailsData getServiceRequestDetailsData() {
        return serviceRequestDetailsData;
    }


    /**
     * Sets the serviceRequestDetailsData value for this ServiceReguestDetailsWSInput.
     * 
     * @param serviceRequestDetailsData
     */
    public void setServiceRequestDetailsData(com.lexmark.srHistory.ServiceRequestDetailsData serviceRequestDetailsData) {
        this.serviceRequestDetailsData = serviceRequestDetailsData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceReguestDetailsWSInput)) return false;
        ServiceReguestDetailsWSInput other = (ServiceReguestDetailsWSInput) obj;
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
            ((this.serviceRequestDetailsData==null && other.getServiceRequestDetailsData()==null) || 
             (this.serviceRequestDetailsData!=null &&
              this.serviceRequestDetailsData.equals(other.getServiceRequestDetailsData())));
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
        if (getServiceRequestDetailsData() != null) {
            _hashCode += getServiceRequestDetailsData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceReguestDetailsWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceReguestDetailsWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestDetailsData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestDetailsData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequestDetailsData"));
        elemField.setMinOccurs(0);
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
