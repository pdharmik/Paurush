/**
 * PaymentDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class PaymentDetails  implements java.io.Serializable {
    private java.lang.String paymentMethod;

    private java.lang.String purchaseOrderNumber;

    private com.lexmark.SiebelShared.createServiceRequest.client.CreditCardInformation creditCardInformation;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress billToAddress;

    public PaymentDetails() {
    }

    public PaymentDetails(
           java.lang.String paymentMethod,
           java.lang.String purchaseOrderNumber,
           com.lexmark.SiebelShared.createServiceRequest.client.CreditCardInformation creditCardInformation,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress billToAddress) {
           this.paymentMethod = paymentMethod;
           this.purchaseOrderNumber = purchaseOrderNumber;
           this.creditCardInformation = creditCardInformation;
           this.billToAddress = billToAddress;
    }


    /**
     * Gets the paymentMethod value for this PaymentDetails.
     * 
     * @return paymentMethod
     */
    public java.lang.String getPaymentMethod() {
        return paymentMethod;
    }


    /**
     * Sets the paymentMethod value for this PaymentDetails.
     * 
     * @param paymentMethod
     */
    public void setPaymentMethod(java.lang.String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    /**
     * Gets the purchaseOrderNumber value for this PaymentDetails.
     * 
     * @return purchaseOrderNumber
     */
    public java.lang.String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }


    /**
     * Sets the purchaseOrderNumber value for this PaymentDetails.
     * 
     * @param purchaseOrderNumber
     */
    public void setPurchaseOrderNumber(java.lang.String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }


    /**
     * Gets the creditCardInformation value for this PaymentDetails.
     * 
     * @return creditCardInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.CreditCardInformation getCreditCardInformation() {
        return creditCardInformation;
    }


    /**
     * Sets the creditCardInformation value for this PaymentDetails.
     * 
     * @param creditCardInformation
     */
    public void setCreditCardInformation(com.lexmark.SiebelShared.createServiceRequest.client.CreditCardInformation creditCardInformation) {
        this.creditCardInformation = creditCardInformation;
    }


    /**
     * Gets the billToAddress value for this PaymentDetails.
     * 
     * @return billToAddress
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress getBillToAddress() {
        return billToAddress;
    }


    /**
     * Sets the billToAddress value for this PaymentDetails.
     * 
     * @param billToAddress
     */
    public void setBillToAddress(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress billToAddress) {
        this.billToAddress = billToAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaymentDetails)) return false;
        PaymentDetails other = (PaymentDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paymentMethod==null && other.getPaymentMethod()==null) || 
             (this.paymentMethod!=null &&
              this.paymentMethod.equals(other.getPaymentMethod()))) &&
            ((this.purchaseOrderNumber==null && other.getPurchaseOrderNumber()==null) || 
             (this.purchaseOrderNumber!=null &&
              this.purchaseOrderNumber.equals(other.getPurchaseOrderNumber()))) &&
            ((this.creditCardInformation==null && other.getCreditCardInformation()==null) || 
             (this.creditCardInformation!=null &&
              this.creditCardInformation.equals(other.getCreditCardInformation()))) &&
            ((this.billToAddress==null && other.getBillToAddress()==null) || 
             (this.billToAddress!=null &&
              this.billToAddress.equals(other.getBillToAddress())));
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
        if (getPaymentMethod() != null) {
            _hashCode += getPaymentMethod().hashCode();
        }
        if (getPurchaseOrderNumber() != null) {
            _hashCode += getPurchaseOrderNumber().hashCode();
        }
        if (getCreditCardInformation() != null) {
            _hashCode += getCreditCardInformation().hashCode();
        }
        if (getBillToAddress() != null) {
            _hashCode += getBillToAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PaymentDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PaymentDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaymentMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purchaseOrderNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PurchaseOrderNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditCardInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CreditCardInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "CreditCardInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billToAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BillToAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress"));
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
