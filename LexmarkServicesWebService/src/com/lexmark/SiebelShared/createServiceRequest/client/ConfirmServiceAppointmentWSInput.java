/**
 * ConfirmServiceAppointmentWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ConfirmServiceAppointmentWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointmentData confirmServiceAppointmentData;

    public ConfirmServiceAppointmentWSInput() {
    }

    public ConfirmServiceAppointmentWSInput(
           com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointmentData confirmServiceAppointmentData) {
           this.documentMetaData = documentMetaData;
           this.confirmServiceAppointmentData = confirmServiceAppointmentData;
    }


    /**
     * Gets the documentMetaData value for this ConfirmServiceAppointmentWSInput.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this ConfirmServiceAppointmentWSInput.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.createServiceRequest.client.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the confirmServiceAppointmentData value for this ConfirmServiceAppointmentWSInput.
     * 
     * @return confirmServiceAppointmentData
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointmentData getConfirmServiceAppointmentData() {
        return confirmServiceAppointmentData;
    }


    /**
     * Sets the confirmServiceAppointmentData value for this ConfirmServiceAppointmentWSInput.
     * 
     * @param confirmServiceAppointmentData
     */
    public void setConfirmServiceAppointmentData(com.lexmark.SiebelShared.createServiceRequest.client.ConfirmServiceAppointmentData confirmServiceAppointmentData) {
        this.confirmServiceAppointmentData = confirmServiceAppointmentData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConfirmServiceAppointmentWSInput)) return false;
        ConfirmServiceAppointmentWSInput other = (ConfirmServiceAppointmentWSInput) obj;
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
            ((this.confirmServiceAppointmentData==null && other.getConfirmServiceAppointmentData()==null) || 
             (this.confirmServiceAppointmentData!=null &&
              this.confirmServiceAppointmentData.equals(other.getConfirmServiceAppointmentData())));
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
        if (getConfirmServiceAppointmentData() != null) {
            _hashCode += getConfirmServiceAppointmentData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConfirmServiceAppointmentWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointmentWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirmServiceAppointmentData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confirmServiceAppointmentData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointmentData"));
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
