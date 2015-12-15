/**
 * ServiceAppointmentTimeSlots.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ServiceAppointmentTimeSlots  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.AddedPropSet addedPropSet;

    private java.lang.String version;  // attribute

    private java.lang.String encoding;  // attribute

    public ServiceAppointmentTimeSlots() {
    }

    public ServiceAppointmentTimeSlots(
           com.lexmark.SiebelShared.createServiceRequest.client.AddedPropSet addedPropSet,
           java.lang.String version,
           java.lang.String encoding) {
           this.addedPropSet = addedPropSet;
           this.version = version;
           this.encoding = encoding;
    }


    /**
     * Gets the addedPropSet value for this ServiceAppointmentTimeSlots.
     * 
     * @return addedPropSet
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.AddedPropSet getAddedPropSet() {
        return addedPropSet;
    }


    /**
     * Sets the addedPropSet value for this ServiceAppointmentTimeSlots.
     * 
     * @param addedPropSet
     */
    public void setAddedPropSet(com.lexmark.SiebelShared.createServiceRequest.client.AddedPropSet addedPropSet) {
        this.addedPropSet = addedPropSet;
    }


    /**
     * Gets the version value for this ServiceAppointmentTimeSlots.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this ServiceAppointmentTimeSlots.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }


    /**
     * Gets the encoding value for this ServiceAppointmentTimeSlots.
     * 
     * @return encoding
     */
    public java.lang.String getEncoding() {
        return encoding;
    }


    /**
     * Sets the encoding value for this ServiceAppointmentTimeSlots.
     * 
     * @param encoding
     */
    public void setEncoding(java.lang.String encoding) {
        this.encoding = encoding;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceAppointmentTimeSlots)) return false;
        ServiceAppointmentTimeSlots other = (ServiceAppointmentTimeSlots) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addedPropSet==null && other.getAddedPropSet()==null) || 
             (this.addedPropSet!=null &&
              this.addedPropSet.equals(other.getAddedPropSet()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
            ((this.encoding==null && other.getEncoding()==null) || 
             (this.encoding!=null &&
              this.encoding.equals(other.getEncoding())));
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
        if (getAddedPropSet() != null) {
            _hashCode += getAddedPropSet().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        if (getEncoding() != null) {
            _hashCode += getEncoding().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceAppointmentTimeSlots.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "serviceAppointmentTimeSlots"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("version");
        attrField.setXmlName(new javax.xml.namespace.QName("", "version"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("encoding");
        attrField.setXmlName(new javax.xml.namespace.QName("", "encoding"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addedPropSet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addedPropSet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "addedPropSet"));
        elemField.setNillable(true);
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
