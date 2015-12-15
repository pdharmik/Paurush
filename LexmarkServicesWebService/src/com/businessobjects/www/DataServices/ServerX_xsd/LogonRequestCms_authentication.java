/**
 * LogonRequestCms_authentication.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class LogonRequestCms_authentication implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected LogonRequestCms_authentication(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _secEnterprise = "secEnterprise";
    public static final java.lang.String _secLDAP = "secLDAP";
    public static final java.lang.String _secWinAD = "secWinAD";
    public static final java.lang.String _secSAPR3 = "secSAPR3";
    public static final LogonRequestCms_authentication secEnterprise = new LogonRequestCms_authentication(_secEnterprise);
    public static final LogonRequestCms_authentication secLDAP = new LogonRequestCms_authentication(_secLDAP);
    public static final LogonRequestCms_authentication secWinAD = new LogonRequestCms_authentication(_secWinAD);
    public static final LogonRequestCms_authentication secSAPR3 = new LogonRequestCms_authentication(_secSAPR3);
    public java.lang.String getValue() { return _value_;}
    public static LogonRequestCms_authentication fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        LogonRequestCms_authentication enumeration = (LogonRequestCms_authentication)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static LogonRequestCms_authentication fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LogonRequestCms_authentication.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>LogonRequest>cms_authentication"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
