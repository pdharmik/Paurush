/**
 * AccountInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class AccountInformation  implements java.io.Serializable {
    private java.lang.String accountId;

    private java.lang.String accountName;

    private java.lang.String MDMId;

    private java.lang.String MDMLevel;

    private java.lang.String accountCountry;

    public AccountInformation() {
    }

    public AccountInformation(
           java.lang.String accountId,
           java.lang.String accountName,
           java.lang.String MDMId,
           java.lang.String MDMLevel,
           java.lang.String accountCountry) {
           this.accountId = accountId;
           this.accountName = accountName;
           this.MDMId = MDMId;
           this.MDMLevel = MDMLevel;
           this.accountCountry = accountCountry;
    }


    /**
     * Gets the accountId value for this AccountInformation.
     * 
     * @return accountId
     */
    public java.lang.String getAccountId() {
        return accountId;
    }


    /**
     * Sets the accountId value for this AccountInformation.
     * 
     * @param accountId
     */
    public void setAccountId(java.lang.String accountId) {
        this.accountId = accountId;
    }


    /**
     * Gets the accountName value for this AccountInformation.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this AccountInformation.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the MDMId value for this AccountInformation.
     * 
     * @return MDMId
     */
    public java.lang.String getMDMId() {
        return MDMId;
    }


    /**
     * Sets the MDMId value for this AccountInformation.
     * 
     * @param MDMId
     */
    public void setMDMId(java.lang.String MDMId) {
        this.MDMId = MDMId;
    }


    /**
     * Gets the MDMLevel value for this AccountInformation.
     * 
     * @return MDMLevel
     */
    public java.lang.String getMDMLevel() {
        return MDMLevel;
    }


    /**
     * Sets the MDMLevel value for this AccountInformation.
     * 
     * @param MDMLevel
     */
    public void setMDMLevel(java.lang.String MDMLevel) {
        this.MDMLevel = MDMLevel;
    }


    /**
     * Gets the accountCountry value for this AccountInformation.
     * 
     * @return accountCountry
     */
    public java.lang.String getAccountCountry() {
        return accountCountry;
    }


    /**
     * Sets the accountCountry value for this AccountInformation.
     * 
     * @param accountCountry
     */
    public void setAccountCountry(java.lang.String accountCountry) {
        this.accountCountry = accountCountry;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountInformation)) return false;
        AccountInformation other = (AccountInformation) obj;
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
            ((this.MDMId==null && other.getMDMId()==null) || 
             (this.MDMId!=null &&
              this.MDMId.equals(other.getMDMId()))) &&
            ((this.MDMLevel==null && other.getMDMLevel()==null) || 
             (this.MDMLevel!=null &&
              this.MDMLevel.equals(other.getMDMLevel()))) &&
            ((this.accountCountry==null && other.getAccountCountry()==null) || 
             (this.accountCountry!=null &&
              this.accountCountry.equals(other.getAccountCountry())));
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
        if (getMDMId() != null) {
            _hashCode += getMDMId().hashCode();
        }
        if (getMDMLevel() != null) {
            _hashCode += getMDMLevel().hashCode();
        }
        if (getAccountCountry() != null) {
            _hashCode += getAccountCountry().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AccountInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MDMId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MDMId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MDMLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MDMLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AccountCountry"));
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
