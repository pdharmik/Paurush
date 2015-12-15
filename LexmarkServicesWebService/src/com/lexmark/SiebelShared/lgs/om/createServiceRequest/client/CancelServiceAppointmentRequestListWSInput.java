/**
 * CancelServiceAppointmentRequestListWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class CancelServiceAppointmentRequestListWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentData2 cancelServiceAppointmentData;

    public CancelServiceAppointmentRequestListWSInput() {
    }

    public CancelServiceAppointmentRequestListWSInput(
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentData2 cancelServiceAppointmentData) {
           this.documentMetaData = documentMetaData;
           this.cancelServiceAppointmentData = cancelServiceAppointmentData;
    }


    /**
     * Gets the documentMetaData value for this CancelServiceAppointmentRequestListWSInput.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this CancelServiceAppointmentRequestListWSInput.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the cancelServiceAppointmentData value for this CancelServiceAppointmentRequestListWSInput.
     * 
     * @return cancelServiceAppointmentData
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentData2 getCancelServiceAppointmentData() {
        return cancelServiceAppointmentData;
    }


    /**
     * Sets the cancelServiceAppointmentData value for this CancelServiceAppointmentRequestListWSInput.
     * 
     * @param cancelServiceAppointmentData
     */
    public void setCancelServiceAppointmentData(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentData2 cancelServiceAppointmentData) {
        this.cancelServiceAppointmentData = cancelServiceAppointmentData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelServiceAppointmentRequestListWSInput)) return false;
        CancelServiceAppointmentRequestListWSInput other = (CancelServiceAppointmentRequestListWSInput) obj;
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
            ((this.cancelServiceAppointmentData==null && other.getCancelServiceAppointmentData()==null) || 
             (this.cancelServiceAppointmentData!=null &&
              this.cancelServiceAppointmentData.equals(other.getCancelServiceAppointmentData())));
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
        if (getCancelServiceAppointmentData() != null) {
            _hashCode += getCancelServiceAppointmentData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelServiceAppointmentRequestListWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRequestListWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelServiceAppointmentData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cancelServiceAppointmentData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentData2"));
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
