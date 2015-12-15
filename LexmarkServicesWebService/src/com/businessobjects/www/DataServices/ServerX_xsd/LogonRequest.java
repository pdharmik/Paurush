/**
 * LogonRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class LogonRequest  implements java.io.Serializable {
    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String cms_system;

    private com.businessobjects.www.DataServices.ServerX_xsd.LogonRequestCms_authentication cms_authentication;

    public LogonRequest() {
    }

    public LogonRequest(
           java.lang.String username,
           java.lang.String password,
           java.lang.String cms_system,
           com.businessobjects.www.DataServices.ServerX_xsd.LogonRequestCms_authentication cms_authentication) {
           this.username = username;
           this.password = password;
           this.cms_system = cms_system;
           this.cms_authentication = cms_authentication;
    }


    /**
     * Gets the username value for this LogonRequest.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this LogonRequest.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this LogonRequest.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this LogonRequest.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the cms_system value for this LogonRequest.
     * 
     * @return cms_system
     */
    public java.lang.String getCms_system() {
        return cms_system;
    }


    /**
     * Sets the cms_system value for this LogonRequest.
     * 
     * @param cms_system
     */
    public void setCms_system(java.lang.String cms_system) {
        this.cms_system = cms_system;
    }


    /**
     * Gets the cms_authentication value for this LogonRequest.
     * 
     * @return cms_authentication
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.LogonRequestCms_authentication getCms_authentication() {
        return cms_authentication;
    }


    /**
     * Sets the cms_authentication value for this LogonRequest.
     * 
     * @param cms_authentication
     */
    public void setCms_authentication(com.businessobjects.www.DataServices.ServerX_xsd.LogonRequestCms_authentication cms_authentication) {
        this.cms_authentication = cms_authentication;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LogonRequest)) return false;
        LogonRequest other = (LogonRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.cms_system==null && other.getCms_system()==null) || 
             (this.cms_system!=null &&
              this.cms_system.equals(other.getCms_system()))) &&
            ((this.cms_authentication==null && other.getCms_authentication()==null) || 
             (this.cms_authentication!=null &&
              this.cms_authentication.equals(other.getCms_authentication())));
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
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getCms_system() != null) {
            _hashCode += getCms_system().hashCode();
        }
        if (getCms_authentication() != null) {
            _hashCode += getCms_authentication().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LogonRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">LogonRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cms_system");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cms_system"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cms_authentication");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cms_authentication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>LogonRequest>cms_authentication"));
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
