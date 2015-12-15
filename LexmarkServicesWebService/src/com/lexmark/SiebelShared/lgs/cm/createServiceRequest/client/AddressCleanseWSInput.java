/**
 * AddressCleanseWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class AddressCleanseWSInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressWSInput addressWSInput;

    public AddressCleanseWSInput() {
    }

    public AddressCleanseWSInput(
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressWSInput addressWSInput) {
           this.addressWSInput = addressWSInput;
    }


    /**
     * Gets the addressWSInput value for this AddressCleanseWSInput.
     * 
     * @return addressWSInput
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressWSInput getAddressWSInput() {
        return addressWSInput;
    }


    /**
     * Sets the addressWSInput value for this AddressCleanseWSInput.
     * 
     * @param addressWSInput
     */
    public void setAddressWSInput(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AddressWSInput addressWSInput) {
        this.addressWSInput = addressWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressCleanseWSInput)) return false;
        AddressCleanseWSInput other = (AddressCleanseWSInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addressWSInput==null && other.getAddressWSInput()==null) || 
             (this.addressWSInput!=null &&
              this.addressWSInput.equals(other.getAddressWSInput())));
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
        if (getAddressWSInput() != null) {
            _hashCode += getAddressWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressCleanseWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AddressWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressWSInput"));
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
