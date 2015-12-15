/**
 * Account.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public class Account  implements java.io.Serializable {
    private java.lang.String accountId;

    private java.lang.String accountName;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress accountAddress;

    private java.lang.String accountPriority;

    public Account() {
    }

    public Account(
           java.lang.String accountId,
           java.lang.String accountName,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress accountAddress,
           java.lang.String accountPriority) {
           this.accountId = accountId;
           this.accountName = accountName;
           this.accountAddress = accountAddress;
           this.accountPriority = accountPriority;
    }


    /**
     * Gets the accountId value for this Account.
     * 
     * @return accountId
     */
    public java.lang.String getAccountId() {
        return accountId;
    }


    /**
     * Sets the accountId value for this Account.
     * 
     * @param accountId
     */
    public void setAccountId(java.lang.String accountId) {
        this.accountId = accountId;
    }


    /**
     * Gets the accountName value for this Account.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this Account.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the accountAddress value for this Account.
     * 
     * @return accountAddress
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress getAccountAddress() {
        return accountAddress;
    }


    /**
     * Sets the accountAddress value for this Account.
     * 
     * @param accountAddress
     */
    public void setAccountAddress(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress accountAddress) {
        this.accountAddress = accountAddress;
    }


    /**
     * Gets the accountPriority value for this Account.
     * 
     * @return accountPriority
     */
    public java.lang.String getAccountPriority() {
        return accountPriority;
    }


    /**
     * Sets the accountPriority value for this Account.
     * 
     * @param accountPriority
     */
    public void setAccountPriority(java.lang.String accountPriority) {
        this.accountPriority = accountPriority;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Account)) return false;
        Account other = (Account) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountId==null && other.getAccountId()==null) || 
             (this.accountId!=null &&
              this.accountId.equals(other.getAccountId()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            ((this.accountAddress==null && other.getAccountAddress()==null) || 
             (this.accountAddress!=null &&
              this.accountAddress.equals(other.getAccountAddress()))) &&
            ((this.accountPriority==null && other.getAccountPriority()==null) || 
             (this.accountPriority!=null &&
              this.accountPriority.equals(other.getAccountPriority())));
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
        if (getAccountId() != null) {
            _hashCode += getAccountId().hashCode();
        }
        if (getAccountName() != null) {
            _hashCode += getAccountName().hashCode();
        }
        if (getAccountAddress() != null) {
            _hashCode += getAccountAddress().hashCode();
        }
        if (getAccountPriority() != null) {
            _hashCode += getAccountPriority().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Account.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "Account"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPriority");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountPriority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
