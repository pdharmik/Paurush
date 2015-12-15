/**
 * GetServiceRequestStatusWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class GetServiceRequestStatusWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusData getServiceRequestStatusData;

    public GetServiceRequestStatusWSInput() {
    }

    public GetServiceRequestStatusWSInput(
           com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusData getServiceRequestStatusData) {
           this.documentMetaData = documentMetaData;
           this.getServiceRequestStatusData = getServiceRequestStatusData;
    }


    /**
     * Gets the documentMetaData value for this GetServiceRequestStatusWSInput.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this GetServiceRequestStatusWSInput.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the getServiceRequestStatusData value for this GetServiceRequestStatusWSInput.
     * 
     * @return getServiceRequestStatusData
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusData getGetServiceRequestStatusData() {
        return getServiceRequestStatusData;
    }


    /**
     * Sets the getServiceRequestStatusData value for this GetServiceRequestStatusWSInput.
     * 
     * @param getServiceRequestStatusData
     */
    public void setGetServiceRequestStatusData(com.lexmark.SiebelShared.createServiceRequest.client.GetServiceRequestStatusData getServiceRequestStatusData) {
        this.getServiceRequestStatusData = getServiceRequestStatusData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestStatusWSInput)) return false;
        GetServiceRequestStatusWSInput other = (GetServiceRequestStatusWSInput) obj;
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
            ((this.getServiceRequestStatusData==null && other.getGetServiceRequestStatusData()==null) || 
             (this.getServiceRequestStatusData!=null &&
              this.getServiceRequestStatusData.equals(other.getGetServiceRequestStatusData())));
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
        if (getGetServiceRequestStatusData() != null) {
            _hashCode += getGetServiceRequestStatusData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestStatusWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestStatusData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "getServiceRequestStatusData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusData"));
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
