/**
 * AssetContacts.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class AssetContacts  implements java.io.Serializable {
    private java.lang.String contactType;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact contactDetails;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress contactAddress;

    public AssetContacts() {
    }

    public AssetContacts(
           java.lang.String contactType,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact contactDetails,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress contactAddress) {
           this.contactType = contactType;
           this.contactDetails = contactDetails;
           this.contactAddress = contactAddress;
    }


    /**
     * Gets the contactType value for this AssetContacts.
     * 
     * @return contactType
     */
    public java.lang.String getContactType() {
        return contactType;
    }


    /**
     * Sets the contactType value for this AssetContacts.
     * 
     * @param contactType
     */
    public void setContactType(java.lang.String contactType) {
        this.contactType = contactType;
    }


    /**
     * Gets the contactDetails value for this AssetContacts.
     * 
     * @return contactDetails
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact getContactDetails() {
        return contactDetails;
    }


    /**
     * Sets the contactDetails value for this AssetContacts.
     * 
     * @param contactDetails
     */
    public void setContactDetails(com.lexmark.SiebelShared.createServiceRequest.client.SiebelContact contactDetails) {
        this.contactDetails = contactDetails;
    }


    /**
     * Gets the contactAddress value for this AssetContacts.
     * 
     * @return contactAddress
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress getContactAddress() {
        return contactAddress;
    }


    /**
     * Sets the contactAddress value for this AssetContacts.
     * 
     * @param contactAddress
     */
    public void setContactAddress(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress contactAddress) {
        this.contactAddress = contactAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetContacts)) return false;
        AssetContacts other = (AssetContacts) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contactType==null && other.getContactType()==null) || 
             (this.contactType!=null &&
              this.contactType.equals(other.getContactType()))) &&
            ((this.contactDetails==null && other.getContactDetails()==null) || 
             (this.contactDetails!=null &&
              this.contactDetails.equals(other.getContactDetails()))) &&
            ((this.contactAddress==null && other.getContactAddress()==null) || 
             (this.contactAddress!=null &&
              this.contactAddress.equals(other.getContactAddress())));
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
        if (getContactType() != null) {
            _hashCode += getContactType().hashCode();
        }
        if (getContactDetails() != null) {
            _hashCode += getContactDetails().hashCode();
        }
        if (getContactAddress() != null) {
            _hashCode += getContactAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssetContacts.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContacts"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContactType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContactDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContactAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress"));
        elemField.setMinOccurs(0);
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
