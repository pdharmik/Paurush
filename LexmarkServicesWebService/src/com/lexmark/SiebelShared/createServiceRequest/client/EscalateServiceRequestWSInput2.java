/**
 * EscalateServiceRequestWSInput2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class EscalateServiceRequestWSInput2  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestData escalateServiceRequestData;

    public EscalateServiceRequestWSInput2() {
    }

    public EscalateServiceRequestWSInput2(
           com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestData escalateServiceRequestData) {
           this.documentMetaData = documentMetaData;
           this.escalateServiceRequestData = escalateServiceRequestData;
    }


    /**
     * Gets the documentMetaData value for this EscalateServiceRequestWSInput2.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this EscalateServiceRequestWSInput2.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the escalateServiceRequestData value for this EscalateServiceRequestWSInput2.
     * 
     * @return escalateServiceRequestData
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestData getEscalateServiceRequestData() {
        return escalateServiceRequestData;
    }


    /**
     * Sets the escalateServiceRequestData value for this EscalateServiceRequestWSInput2.
     * 
     * @param escalateServiceRequestData
     */
    public void setEscalateServiceRequestData(com.lexmark.SiebelShared.createServiceRequest.client.EscalateServiceRequestData escalateServiceRequestData) {
        this.escalateServiceRequestData = escalateServiceRequestData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EscalateServiceRequestWSInput2)) return false;
        EscalateServiceRequestWSInput2 other = (EscalateServiceRequestWSInput2) obj;
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
            ((this.escalateServiceRequestData==null && other.getEscalateServiceRequestData()==null) || 
             (this.escalateServiceRequestData!=null &&
              this.escalateServiceRequestData.equals(other.getEscalateServiceRequestData())));
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
        if (getEscalateServiceRequestData() != null) {
            _hashCode += getEscalateServiceRequestData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EscalateServiceRequestWSInput2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestWSInput2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("escalateServiceRequestData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "escalateServiceRequestData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "escalateServiceRequestData"));
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
