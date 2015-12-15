/**
 * ChangeManagementServiceRequestWSInput2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ChangeManagementServiceRequestWSInput2  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestData changeManagementServiceRequestData;

    public ChangeManagementServiceRequestWSInput2() {
    }

    public ChangeManagementServiceRequestWSInput2(
           com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestData changeManagementServiceRequestData) {
           this.documentMetaData = documentMetaData;
           this.changeManagementServiceRequestData = changeManagementServiceRequestData;
    }


    /**
     * Gets the documentMetaData value for this ChangeManagementServiceRequestWSInput2.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this ChangeManagementServiceRequestWSInput2.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the changeManagementServiceRequestData value for this ChangeManagementServiceRequestWSInput2.
     * 
     * @return changeManagementServiceRequestData
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestData getChangeManagementServiceRequestData() {
        return changeManagementServiceRequestData;
    }


    /**
     * Sets the changeManagementServiceRequestData value for this ChangeManagementServiceRequestWSInput2.
     * 
     * @param changeManagementServiceRequestData
     */
    public void setChangeManagementServiceRequestData(com.lexmark.SiebelShared.createServiceRequest.client.ChangeManagementServiceRequestData changeManagementServiceRequestData) {
        this.changeManagementServiceRequestData = changeManagementServiceRequestData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChangeManagementServiceRequestWSInput2)) return false;
        ChangeManagementServiceRequestWSInput2 other = (ChangeManagementServiceRequestWSInput2) obj;
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
            ((this.changeManagementServiceRequestData==null && other.getChangeManagementServiceRequestData()==null) || 
             (this.changeManagementServiceRequestData!=null &&
              this.changeManagementServiceRequestData.equals(other.getChangeManagementServiceRequestData())));
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
        if (getChangeManagementServiceRequestData() != null) {
            _hashCode += getChangeManagementServiceRequestData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ChangeManagementServiceRequestWSInput2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestWSInput2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeManagementServiceRequestData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ChangeManagementServiceRequestData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ChangeManagementServiceRequestData"));
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
