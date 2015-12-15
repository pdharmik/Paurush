/**
 * CustomerContact.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.processCustomerContact.client;

public class CustomerContact  implements java.io.Serializable {
    private com.lexmark.webServices.processCustomerContact.client.CustomerContact2 customerContact;

    private com.lexmark.webServices.processCustomerContact.client.Envelope _env;

    public CustomerContact() {
    }

    public CustomerContact(
           com.lexmark.webServices.processCustomerContact.client.CustomerContact2 customerContact,
           com.lexmark.webServices.processCustomerContact.client.Envelope _env) {
           this.customerContact = customerContact;
           this._env = _env;
    }


    /**
     * Gets the customerContact value for this CustomerContact.
     * 
     * @return customerContact
     */
    public com.lexmark.webServices.processCustomerContact.client.CustomerContact2 getCustomerContact() {
        return customerContact;
    }


    /**
     * Sets the customerContact value for this CustomerContact.
     * 
     * @param customerContact
     */
    public void setCustomerContact(com.lexmark.webServices.processCustomerContact.client.CustomerContact2 customerContact) {
        this.customerContact = customerContact;
    }


    /**
     * Gets the _env value for this CustomerContact.
     * 
     * @return _env
     */
    public com.lexmark.webServices.processCustomerContact.client.Envelope get_env() {
        return _env;
    }


    /**
     * Sets the _env value for this CustomerContact.
     * 
     * @param _env
     */
    public void set_env(com.lexmark.webServices.processCustomerContact.client.Envelope _env) {
        this._env = _env;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CustomerContact)) return false;
        CustomerContact other = (CustomerContact) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.customerContact==null && other.getCustomerContact()==null) || 
             (this.customerContact!=null &&
              this.customerContact.equals(other.getCustomerContact()))) &&
            ((this._env==null && other.get_env()==null) || 
             (this._env!=null &&
              this._env.equals(other.get_env())));
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
        if (getCustomerContact() != null) {
            _hashCode += getCustomerContact().hashCode();
        }
        if (get_env() != null) {
            _hashCode += get_env().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustomerContact.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "CustomerContact"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "CustomerContact2"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_env");
        elemField.setXmlName(new javax.xml.namespace.QName("", "_env"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "envelope"));
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
