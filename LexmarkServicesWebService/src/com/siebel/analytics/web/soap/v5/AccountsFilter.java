/**
 * AccountsFilter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public class AccountsFilter  implements java.io.Serializable {
    private boolean includeUsers;

    private boolean includeGroups;

    public AccountsFilter() {
    }

    public AccountsFilter(
           boolean includeUsers,
           boolean includeGroups) {
           this.includeUsers = includeUsers;
           this.includeGroups = includeGroups;
    }


    /**
     * Gets the includeUsers value for this AccountsFilter.
     * 
     * @return includeUsers
     */
    public boolean isIncludeUsers() {
        return includeUsers;
    }


    /**
     * Sets the includeUsers value for this AccountsFilter.
     * 
     * @param includeUsers
     */
    public void setIncludeUsers(boolean includeUsers) {
        this.includeUsers = includeUsers;
    }


    /**
     * Gets the includeGroups value for this AccountsFilter.
     * 
     * @return includeGroups
     */
    public boolean isIncludeGroups() {
        return includeGroups;
    }


    /**
     * Sets the includeGroups value for this AccountsFilter.
     * 
     * @param includeGroups
     */
    public void setIncludeGroups(boolean includeGroups) {
        this.includeGroups = includeGroups;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountsFilter)) return false;
        AccountsFilter other = (AccountsFilter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.includeUsers == other.isIncludeUsers() &&
            this.includeGroups == other.isIncludeGroups();
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
        _hashCode += (isIncludeUsers() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIncludeGroups() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountsFilter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "AccountsFilter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeUsers");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "includeUsers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeGroups");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "includeGroups"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
