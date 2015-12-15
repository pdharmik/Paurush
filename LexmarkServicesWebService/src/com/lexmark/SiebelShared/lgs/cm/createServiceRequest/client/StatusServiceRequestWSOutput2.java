/**
 * StatusServiceRequestWSOutput2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class StatusServiceRequestWSOutput2  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData10 documentMetaData;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestData statusServiceRequestData;

    public StatusServiceRequestWSOutput2() {
    }

    public StatusServiceRequestWSOutput2(
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData10 documentMetaData,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestData statusServiceRequestData) {
           this.documentMetaData = documentMetaData;
           this.statusServiceRequestData = statusServiceRequestData;
    }


    /**
     * Gets the documentMetaData value for this StatusServiceRequestWSOutput2.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData10 getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this StatusServiceRequestWSOutput2.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData10 documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the statusServiceRequestData value for this StatusServiceRequestWSOutput2.
     * 
     * @return statusServiceRequestData
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestData getStatusServiceRequestData() {
        return statusServiceRequestData;
    }


    /**
     * Sets the statusServiceRequestData value for this StatusServiceRequestWSOutput2.
     * 
     * @param statusServiceRequestData
     */
    public void setStatusServiceRequestData(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.StatusServiceRequestData statusServiceRequestData) {
        this.statusServiceRequestData = statusServiceRequestData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatusServiceRequestWSOutput2)) return false;
        StatusServiceRequestWSOutput2 other = (StatusServiceRequestWSOutput2) obj;
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
            ((this.statusServiceRequestData==null && other.getStatusServiceRequestData()==null) || 
             (this.statusServiceRequestData!=null &&
              this.statusServiceRequestData.equals(other.getStatusServiceRequestData())));
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
        if (getStatusServiceRequestData() != null) {
            _hashCode += getStatusServiceRequestData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatusServiceRequestWSOutput2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "statusServiceRequestWSOutput2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData10"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusServiceRequestData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "StatusServiceRequestData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "StatusServiceRequestData"));
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
