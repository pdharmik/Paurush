/**
 * CreditCardInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class CreditCardInformation  implements java.io.Serializable {
    private java.lang.String creditCardType;

    private java.lang.String creditCardNumber;

    private java.lang.String creditCardToken;

    private java.lang.String creditCardExpirationDate;

    private java.lang.String creditCardHolderName;

    private java.lang.String creditCardTransactionId;

    private java.lang.String creditCardAuthorizationNumber;

    public CreditCardInformation() {
    }

    public CreditCardInformation(
           java.lang.String creditCardType,
           java.lang.String creditCardNumber,
           java.lang.String creditCardToken,
           java.lang.String creditCardExpirationDate,
           java.lang.String creditCardHolderName,
           java.lang.String creditCardTransactionId,
           java.lang.String creditCardAuthorizationNumber) {
           this.creditCardType = creditCardType;
           this.creditCardNumber = creditCardNumber;
           this.creditCardToken = creditCardToken;
           this.creditCardExpirationDate = creditCardExpirationDate;
           this.creditCardHolderName = creditCardHolderName;
           this.creditCardTransactionId = creditCardTransactionId;
           this.creditCardAuthorizationNumber = creditCardAuthorizationNumber;
    }


    /**
     * Gets the creditCardType value for this CreditCardInformation.
     * 
     * @return creditCardType
     */
    public java.lang.String getCreditCardType() {
        return creditCardType;
    }


    /**
     * Sets the creditCardType value for this CreditCardInformation.
     * 
     * @param creditCardType
     */
    public void setCreditCardType(java.lang.String creditCardType) {
        this.creditCardType = creditCardType;
    }


    /**
     * Gets the creditCardNumber value for this CreditCardInformation.
     * 
     * @return creditCardNumber
     */
    public java.lang.String getCreditCardNumber() {
        return creditCardNumber;
    }


    /**
     * Sets the creditCardNumber value for this CreditCardInformation.
     * 
     * @param creditCardNumber
     */
    public void setCreditCardNumber(java.lang.String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }


    /**
     * Gets the creditCardToken value for this CreditCardInformation.
     * 
     * @return creditCardToken
     */
    public java.lang.String getCreditCardToken() {
        return creditCardToken;
    }


    /**
     * Sets the creditCardToken value for this CreditCardInformation.
     * 
     * @param creditCardToken
     */
    public void setCreditCardToken(java.lang.String creditCardToken) {
        this.creditCardToken = creditCardToken;
    }


    /**
     * Gets the creditCardExpirationDate value for this CreditCardInformation.
     * 
     * @return creditCardExpirationDate
     */
    public java.lang.String getCreditCardExpirationDate() {
        return creditCardExpirationDate;
    }


    /**
     * Sets the creditCardExpirationDate value for this CreditCardInformation.
     * 
     * @param creditCardExpirationDate
     */
    public void setCreditCardExpirationDate(java.lang.String creditCardExpirationDate) {
        this.creditCardExpirationDate = creditCardExpirationDate;
    }


    /**
     * Gets the creditCardHolderName value for this CreditCardInformation.
     * 
     * @return creditCardHolderName
     */
    public java.lang.String getCreditCardHolderName() {
        return creditCardHolderName;
    }


    /**
     * Sets the creditCardHolderName value for this CreditCardInformation.
     * 
     * @param creditCardHolderName
     */
    public void setCreditCardHolderName(java.lang.String creditCardHolderName) {
        this.creditCardHolderName = creditCardHolderName;
    }


    /**
     * Gets the creditCardTransactionId value for this CreditCardInformation.
     * 
     * @return creditCardTransactionId
     */
    public java.lang.String getCreditCardTransactionId() {
        return creditCardTransactionId;
    }


    /**
     * Sets the creditCardTransactionId value for this CreditCardInformation.
     * 
     * @param creditCardTransactionId
     */
    public void setCreditCardTransactionId(java.lang.String creditCardTransactionId) {
        this.creditCardTransactionId = creditCardTransactionId;
    }


    /**
     * Gets the creditCardAuthorizationNumber value for this CreditCardInformation.
     * 
     * @return creditCardAuthorizationNumber
     */
    public java.lang.String getCreditCardAuthorizationNumber() {
        return creditCardAuthorizationNumber;
    }


    /**
     * Sets the creditCardAuthorizationNumber value for this CreditCardInformation.
     * 
     * @param creditCardAuthorizationNumber
     */
    public void setCreditCardAuthorizationNumber(java.lang.String creditCardAuthorizationNumber) {
        this.creditCardAuthorizationNumber = creditCardAuthorizationNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreditCardInformation)) return false;
        CreditCardInformation other = (CreditCardInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.creditCardType==null && other.getCreditCardType()==null) || 
             (this.creditCardType!=null &&
              this.creditCardType.equals(other.getCreditCardType()))) &&
            ((this.creditCardNumber==null && other.getCreditCardNumber()==null) || 
             (this.creditCardNumber!=null &&
              this.creditCardNumber.equals(other.getCreditCardNumber()))) &&
            ((this.creditCardToken==null && other.getCreditCardToken()==null) || 
             (this.creditCardToken!=null &&
              this.creditCardToken.equals(other.getCreditCardToken()))) &&
            ((this.creditCardExpirationDate==null && other.getCreditCardExpirationDate()==null) || 
             (this.creditCardExpirationDate!=null &&
              this.creditCardExpirationDate.equals(other.getCreditCardExpirationDate()))) &&
            ((this.creditCardHolderName==null && other.getCreditCardHolderName()==null) || 
             (this.creditCardHolderName!=null &&
              this.creditCardHolderName.equals(other.getCreditCardHolderName()))) &&
            ((this.creditCardTransactionId==null && other.getCreditCardTransactionId()==null) || 
             (this.creditCardTransactionId!=null &&
              this.creditCardTransactionId.equals(other.getCreditCardTransactionId()))) &&
            ((this.creditCardAuthorizationNumber==null && other.getCreditCardAuthorizationNumber()==null) || 
             (this.creditCardAuthorizationNumber!=null &&
              this.creditCardAuthorizationNumber.equals(other.getCreditCardAuthorizationNumber())));
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
        if (getCreditCardType() != null) {
            _hashCode += getCreditCardType().hashCode();
        }
        if (getCreditCardNumber() != null) {
            _hashCode += getCreditCardNumber().hashCode();
        }
        if (getCreditCardToken() != null) {
            _hashCode += getCreditCardToken().hashCode();
        }
        if (getCreditCardExpirationDate() != null) {
            _hashCode += getCreditCardExpirationDate().hashCode();
        }
        if (getCreditCardHolderName() != null) {
            _hashCode += getCreditCardHolderName().hashCode();
        }
        if (getCreditCardTransactionId() != null) {
            _hashCode += getCreditCardTransactionId().hashCode();
        }
        if (getCreditCardAuthorizationNumber() != null) {
            _hashCode += getCreditCardAuthorizationNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreditCardInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "CreditCardInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreditCardType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreditCardNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreditCardToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardExpirationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreditCardExpirationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardHolderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreditCardHolderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardTransactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreditCardTransactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardAuthorizationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreditCardAuthorizationNumber"));
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
