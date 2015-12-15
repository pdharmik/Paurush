/**
 * PublishContacts.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.processCustomerContactCM;

public class PublishContacts  implements java.io.Serializable {
    private java.lang.String debug;

    private com.lexmark.webServices.processCustomerContactCM.CustomerContact contact;

    private java.lang.String synchOrAsynch;

    public PublishContacts() {
    }

    public PublishContacts(
           java.lang.String debug,
           com.lexmark.webServices.processCustomerContactCM.CustomerContact contact,
           java.lang.String synchOrAsynch) {
           this.debug = debug;
           this.contact = contact;
           this.synchOrAsynch = synchOrAsynch;
    }


    /**
     * Gets the debug value for this PublishContacts.
     * 
     * @return debug
     */
    public java.lang.String getDebug() {
        return debug;
    }


    /**
     * Sets the debug value for this PublishContacts.
     * 
     * @param debug
     */
    public void setDebug(java.lang.String debug) {
        this.debug = debug;
    }


    /**
     * Gets the contact value for this PublishContacts.
     * 
     * @return contact
     */
    public com.lexmark.webServices.processCustomerContactCM.CustomerContact getContact() {
        return contact;
    }


    /**
     * Sets the contact value for this PublishContacts.
     * 
     * @param contact
     */
    public void setContact(com.lexmark.webServices.processCustomerContactCM.CustomerContact contact) {
        this.contact = contact;
    }


    /**
     * Gets the synchOrAsynch value for this PublishContacts.
     * 
     * @return synchOrAsynch
     */
    public java.lang.String getSynchOrAsynch() {
        return synchOrAsynch;
    }


    /**
     * Sets the synchOrAsynch value for this PublishContacts.
     * 
     * @param synchOrAsynch
     */
    public void setSynchOrAsynch(java.lang.String synchOrAsynch) {
        this.synchOrAsynch = synchOrAsynch;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PublishContacts)) return false;
        PublishContacts other = (PublishContacts) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.debug==null && other.getDebug()==null) || 
             (this.debug!=null &&
              this.debug.equals(other.getDebug()))) &&
            ((this.contact==null && other.getContact()==null) || 
             (this.contact!=null &&
              this.contact.equals(other.getContact()))) &&
            ((this.synchOrAsynch==null && other.getSynchOrAsynch()==null) || 
             (this.synchOrAsynch!=null &&
              this.synchOrAsynch.equals(other.getSynchOrAsynch())));
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
        if (getDebug() != null) {
            _hashCode += getDebug().hashCode();
        }
        if (getContact() != null) {
            _hashCode += getContact().hashCode();
        }
        if (getSynchOrAsynch() != null) {
            _hashCode += getSynchOrAsynch().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PublishContacts.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "publishContacts"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debug");
        elemField.setXmlName(new javax.xml.namespace.QName("", "debug"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "CustomerContact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("synchOrAsynch");
        elemField.setXmlName(new javax.xml.namespace.QName("", "synchOrAsynch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
