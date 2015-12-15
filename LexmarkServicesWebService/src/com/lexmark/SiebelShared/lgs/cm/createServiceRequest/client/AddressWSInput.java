/**
 * AddressWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class AddressWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData11 documentMetaData;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressData addressData;

    public AddressWSInput() {
    }

    public AddressWSInput(
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData11 documentMetaData,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressData addressData) {
           this.documentMetaData = documentMetaData;
           this.addressData = addressData;
    }


    /**
     * Gets the documentMetaData value for this AddressWSInput.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData11 getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this AddressWSInput.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.DocumentMetaData11 documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the addressData value for this AddressWSInput.
     * 
     * @return addressData
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressData getAddressData() {
        return addressData;
    }


    /**
     * Sets the addressData value for this AddressWSInput.
     * 
     * @param addressData
     */
    public void setAddressData(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressData addressData) {
        this.addressData = addressData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressWSInput)) return false;
        AddressWSInput other = (AddressWSInput) obj;
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
            ((this.addressData==null && other.getAddressData()==null) || 
             (this.addressData!=null &&
              this.addressData.equals(other.getAddressData())));
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
        if (getAddressData() != null) {
            _hashCode += getAddressData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DocumentMetaData11"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AddressData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressData"));
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
