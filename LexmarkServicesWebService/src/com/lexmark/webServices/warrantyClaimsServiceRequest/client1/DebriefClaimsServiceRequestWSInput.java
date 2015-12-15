/**
 * DebriefClaimsServiceRequestWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public class DebriefClaimsServiceRequestWSInput  implements java.io.Serializable {
    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData claimsServiceRequestData;

    public DebriefClaimsServiceRequestWSInput() {
    }

    public DebriefClaimsServiceRequestWSInput(
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData claimsServiceRequestData) {
           this.documentMetaData = documentMetaData;
           this.claimsServiceRequestData = claimsServiceRequestData;
    }


    /**
     * Gets the documentMetaData value for this DebriefClaimsServiceRequestWSInput.
     * 
     * @return documentMetaData
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this DebriefClaimsServiceRequestWSInput.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the claimsServiceRequestData value for this DebriefClaimsServiceRequestWSInput.
     * 
     * @return claimsServiceRequestData
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData getClaimsServiceRequestData() {
        return claimsServiceRequestData;
    }


    /**
     * Sets the claimsServiceRequestData value for this DebriefClaimsServiceRequestWSInput.
     * 
     * @param claimsServiceRequestData
     */
    public void setClaimsServiceRequestData(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.ClaimsServiceRequestData claimsServiceRequestData) {
        this.claimsServiceRequestData = claimsServiceRequestData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DebriefClaimsServiceRequestWSInput)) return false;
        DebriefClaimsServiceRequestWSInput other = (DebriefClaimsServiceRequestWSInput) obj;
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
            ((this.claimsServiceRequestData==null && other.getClaimsServiceRequestData()==null) || 
             (this.claimsServiceRequestData!=null &&
              this.claimsServiceRequestData.equals(other.getClaimsServiceRequestData())));
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
        if (getClaimsServiceRequestData() != null) {
            _hashCode += getClaimsServiceRequestData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DebriefClaimsServiceRequestWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "debriefClaimsServiceRequestWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claimsServiceRequestData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ClaimsServiceRequestData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "ClaimsServiceRequestData"));
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
