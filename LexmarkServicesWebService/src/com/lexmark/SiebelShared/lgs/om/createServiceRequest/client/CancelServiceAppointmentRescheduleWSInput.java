/**
 * CancelServiceAppointmentRescheduleWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class CancelServiceAppointmentRescheduleWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentRescheduleData cancelServiceAppointmentRescheduleData;

    public CancelServiceAppointmentRescheduleWSInput() {
    }

    public CancelServiceAppointmentRescheduleWSInput(
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentRescheduleData cancelServiceAppointmentRescheduleData) {
           this.documentMetaData = documentMetaData;
           this.cancelServiceAppointmentRescheduleData = cancelServiceAppointmentRescheduleData;
    }


    /**
     * Gets the documentMetaData value for this CancelServiceAppointmentRescheduleWSInput.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this CancelServiceAppointmentRescheduleWSInput.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the cancelServiceAppointmentRescheduleData value for this CancelServiceAppointmentRescheduleWSInput.
     * 
     * @return cancelServiceAppointmentRescheduleData
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentRescheduleData getCancelServiceAppointmentRescheduleData() {
        return cancelServiceAppointmentRescheduleData;
    }


    /**
     * Sets the cancelServiceAppointmentRescheduleData value for this CancelServiceAppointmentRescheduleWSInput.
     * 
     * @param cancelServiceAppointmentRescheduleData
     */
    public void setCancelServiceAppointmentRescheduleData(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.CancelServiceAppointmentRescheduleData cancelServiceAppointmentRescheduleData) {
        this.cancelServiceAppointmentRescheduleData = cancelServiceAppointmentRescheduleData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelServiceAppointmentRescheduleWSInput)) return false;
        CancelServiceAppointmentRescheduleWSInput other = (CancelServiceAppointmentRescheduleWSInput) obj;
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
            ((this.cancelServiceAppointmentRescheduleData==null && other.getCancelServiceAppointmentRescheduleData()==null) || 
             (this.cancelServiceAppointmentRescheduleData!=null &&
              this.cancelServiceAppointmentRescheduleData.equals(other.getCancelServiceAppointmentRescheduleData())));
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
        if (getCancelServiceAppointmentRescheduleData() != null) {
            _hashCode += getCancelServiceAppointmentRescheduleData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelServiceAppointmentRescheduleWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRescheduleWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelServiceAppointmentRescheduleData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cancelServiceAppointmentRescheduleData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentRescheduleData"));
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
