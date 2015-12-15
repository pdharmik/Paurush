/**
 * ServiceRequestWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class ServiceRequestWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequestData3 serviceRequestData;

    public ServiceRequestWSInput() {
    }

    public ServiceRequestWSInput(
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequestData3 serviceRequestData) {
           this.documentMetaData = documentMetaData;
           this.serviceRequestData = serviceRequestData;
    }


    /**
     * Gets the documentMetaData value for this ServiceRequestWSInput.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this ServiceRequestWSInput.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the serviceRequestData value for this ServiceRequestWSInput.
     * 
     * @return serviceRequestData
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequestData3 getServiceRequestData() {
        return serviceRequestData;
    }


    /**
     * Sets the serviceRequestData value for this ServiceRequestWSInput.
     * 
     * @param serviceRequestData
     */
    public void setServiceRequestData(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ServiceRequestData3 serviceRequestData) {
        this.serviceRequestData = serviceRequestData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestWSInput)) return false;
        ServiceRequestWSInput other = (ServiceRequestWSInput) obj;
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
            ((this.serviceRequestData==null && other.getServiceRequestData()==null) || 
             (this.serviceRequestData!=null &&
              this.serviceRequestData.equals(other.getServiceRequestData())));
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
        if (getServiceRequestData() != null) {
            _hashCode += getServiceRequestData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData3"));
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
