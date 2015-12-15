/**
 * UpdateACLParams.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public class UpdateACLParams  implements java.io.Serializable {
    private boolean allowUnknownAccounts;

    private com.siebel.analytics.web.soap.v5.UpdateACLMode updateFlag;

    public UpdateACLParams() {
    }

    public UpdateACLParams(
           boolean allowUnknownAccounts,
           com.siebel.analytics.web.soap.v5.UpdateACLMode updateFlag) {
           this.allowUnknownAccounts = allowUnknownAccounts;
           this.updateFlag = updateFlag;
    }


    /**
     * Gets the allowUnknownAccounts value for this UpdateACLParams.
     * 
     * @return allowUnknownAccounts
     */
    public boolean isAllowUnknownAccounts() {
        return allowUnknownAccounts;
    }


    /**
     * Sets the allowUnknownAccounts value for this UpdateACLParams.
     * 
     * @param allowUnknownAccounts
     */
    public void setAllowUnknownAccounts(boolean allowUnknownAccounts) {
        this.allowUnknownAccounts = allowUnknownAccounts;
    }


    /**
     * Gets the updateFlag value for this UpdateACLParams.
     * 
     * @return updateFlag
     */
    public com.siebel.analytics.web.soap.v5.UpdateACLMode getUpdateFlag() {
        return updateFlag;
    }


    /**
     * Sets the updateFlag value for this UpdateACLParams.
     * 
     * @param updateFlag
     */
    public void setUpdateFlag(com.siebel.analytics.web.soap.v5.UpdateACLMode updateFlag) {
        this.updateFlag = updateFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateACLParams)) return false;
        UpdateACLParams other = (UpdateACLParams) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.allowUnknownAccounts == other.isAllowUnknownAccounts() &&
            ((this.updateFlag==null && other.getUpdateFlag()==null) || 
             (this.updateFlag!=null &&
              this.updateFlag.equals(other.getUpdateFlag())));
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
        _hashCode += (isAllowUnknownAccounts() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getUpdateFlag() != null) {
            _hashCode += getUpdateFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateACLParams.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "UpdateACLParams"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowUnknownAccounts");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "allowUnknownAccounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "updateFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "UpdateACLMode"));
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
