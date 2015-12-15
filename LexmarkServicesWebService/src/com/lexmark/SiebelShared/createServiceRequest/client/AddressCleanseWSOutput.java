/**
 * AddressCleanseWSOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class AddressCleanseWSOutput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.AddressWSOutput addressWSOutput;

    public AddressCleanseWSOutput() {
    }

    public AddressCleanseWSOutput(
           com.lexmark.SiebelShared.createServiceRequest.client.AddressWSOutput addressWSOutput) {
           this.addressWSOutput = addressWSOutput;
    }


    /**
     * Gets the addressWSOutput value for this AddressCleanseWSOutput.
     * 
     * @return addressWSOutput
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.AddressWSOutput getAddressWSOutput() {
        return addressWSOutput;
    }


    /**
     * Sets the addressWSOutput value for this AddressCleanseWSOutput.
     * 
     * @param addressWSOutput
     */
    public void setAddressWSOutput(com.lexmark.SiebelShared.createServiceRequest.client.AddressWSOutput addressWSOutput) {
        this.addressWSOutput = addressWSOutput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressCleanseWSOutput)) return false;
        AddressCleanseWSOutput other = (AddressCleanseWSOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addressWSOutput==null && other.getAddressWSOutput()==null) || 
             (this.addressWSOutput!=null &&
              this.addressWSOutput.equals(other.getAddressWSOutput())));
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
        if (getAddressWSOutput() != null) {
            _hashCode += getAddressWSOutput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressCleanseWSOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressCleanseWSOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressWSOutput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AddressWSOutput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AddressWSOutput"));
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
