/**
 * CancelServiceRequestWSInput2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class CancelServiceRequestWSInput2  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData12 documentMetaData;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestData cancelServiceRequestData;

    public CancelServiceRequestWSInput2() {
    }

    public CancelServiceRequestWSInput2(
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData12 documentMetaData,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestData cancelServiceRequestData) {
           this.documentMetaData = documentMetaData;
           this.cancelServiceRequestData = cancelServiceRequestData;
    }


    /**
     * Gets the documentMetaData value for this CancelServiceRequestWSInput2.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData12 getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this CancelServiceRequestWSInput2.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData12 documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the cancelServiceRequestData value for this CancelServiceRequestWSInput2.
     * 
     * @return cancelServiceRequestData
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestData getCancelServiceRequestData() {
        return cancelServiceRequestData;
    }


    /**
     * Sets the cancelServiceRequestData value for this CancelServiceRequestWSInput2.
     * 
     * @param cancelServiceRequestData
     */
    public void setCancelServiceRequestData(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CancelServiceRequestData cancelServiceRequestData) {
        this.cancelServiceRequestData = cancelServiceRequestData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelServiceRequestWSInput2)) return false;
        CancelServiceRequestWSInput2 other = (CancelServiceRequestWSInput2) obj;
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
            ((this.cancelServiceRequestData==null && other.getCancelServiceRequestData()==null) || 
             (this.cancelServiceRequestData!=null &&
              this.cancelServiceRequestData.equals(other.getCancelServiceRequestData())));
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
        if (getCancelServiceRequestData() != null) {
            _hashCode += getCancelServiceRequestData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelServiceRequestWSInput2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestWSInput2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData12"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelServiceRequestData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cancelServiceRequestData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceRequestData"));
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
