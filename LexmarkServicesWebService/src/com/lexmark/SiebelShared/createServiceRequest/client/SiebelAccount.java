/**
 * SiebelAccount.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class SiebelAccount  implements java.io.Serializable {
    private java.lang.String accountId;

    private java.lang.String accountName;

    private java.lang.String MDMId;

    private java.lang.String MDMLevel;

    private java.lang.String accountCountry;

    private java.lang.String soldToNumber;

    private java.lang.String billToNumber;

    public SiebelAccount() {
    }

    public SiebelAccount(
           java.lang.String accountId,
           java.lang.String accountName,
           java.lang.String MDMId,
           java.lang.String MDMLevel,
           java.lang.String accountCountry,
           java.lang.String soldToNumber,
           java.lang.String billToNumber) {
           this.accountId = accountId;
           this.accountName = accountName;
           this.MDMId = MDMId;
           this.MDMLevel = MDMLevel;
           this.accountCountry = accountCountry;
           this.soldToNumber = soldToNumber;
           this.billToNumber = billToNumber;
    }


    /**
     * Gets the accountId value for this SiebelAccount.
     * 
     * @return accountId
     */
    public java.lang.String getAccountId() {
        return accountId;
    }


    /**
     * Sets the accountId value for this SiebelAccount.
     * 
     * @param accountId
     */
    public void setAccountId(java.lang.String accountId) {
        this.accountId = accountId;
    }


    /**
     * Gets the accountName value for this SiebelAccount.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this SiebelAccount.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the MDMId value for this SiebelAccount.
     * 
     * @return MDMId
     */
    public java.lang.String getMDMId() {
        return MDMId;
    }


    /**
     * Sets the MDMId value for this SiebelAccount.
     * 
     * @param MDMId
     */
    public void setMDMId(java.lang.String MDMId) {
        this.MDMId = MDMId;
    }


    /**
     * Gets the MDMLevel value for this SiebelAccount.
     * 
     * @return MDMLevel
     */
    public java.lang.String getMDMLevel() {
        return MDMLevel;
    }


    /**
     * Sets the MDMLevel value for this SiebelAccount.
     * 
     * @param MDMLevel
     */
    public void setMDMLevel(java.lang.String MDMLevel) {
        this.MDMLevel = MDMLevel;
    }


    /**
     * Gets the accountCountry value for this SiebelAccount.
     * 
     * @return accountCountry
     */
    public java.lang.String getAccountCountry() {
        return accountCountry;
    }


    /**
     * Sets the accountCountry value for this SiebelAccount.
     * 
     * @param accountCountry
     */
    public void setAccountCountry(java.lang.String accountCountry) {
        this.accountCountry = accountCountry;
    }


    /**
     * Gets the soldToNumber value for this SiebelAccount.
     * 
     * @return soldToNumber
     */
    public java.lang.String getSoldToNumber() {
        return soldToNumber;
    }


    /**
     * Sets the soldToNumber value for this SiebelAccount.
     * 
     * @param soldToNumber
     */
    public void setSoldToNumber(java.lang.String soldToNumber) {
        this.soldToNumber = soldToNumber;
    }


    /**
     * Gets the billToNumber value for this SiebelAccount.
     * 
     * @return billToNumber
     */
    public java.lang.String getBillToNumber() {
        return billToNumber;
    }


    /**
     * Sets the billToNumber value for this SiebelAccount.
     * 
     * @param billToNumber
     */
    public void setBillToNumber(java.lang.String billToNumber) {
        this.billToNumber = billToNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelAccount)) return false;
        SiebelAccount other = (SiebelAccount) obj;
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
              this.accountCountry.equals(other.getAccountCountry()))) &&
            ((this.soldToNumber==null && other.getSoldToNumber()==null) || 
             (this.soldToNumber!=null &&
              this.soldToNumber.equals(other.getSoldToNumber()))) &&
            ((this.billToNumber==null && other.getBillToNumber()==null) || 
             (this.billToNumber!=null &&
              this.billToNumber.equals(other.getBillToNumber())));
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
        if (getSoldToNumber() != null) {
            _hashCode += getSoldToNumber().hashCode();
        }
        if (getBillToNumber() != null) {
            _hashCode += getBillToNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelAccount.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAccount"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soldToNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SoldToNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billToNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BillToNumber"));
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
