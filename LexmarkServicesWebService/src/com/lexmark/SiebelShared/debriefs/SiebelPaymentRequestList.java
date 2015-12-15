/**
 * SiebelPaymentRequestList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;

public class SiebelPaymentRequestList  implements java.io.Serializable {
    private java.lang.String paymentRequestId;

    private java.lang.String paymentType;

    private java.lang.String quantity;

    private java.lang.String unitPrice;

    private java.lang.String totalAmount;

    private java.lang.String currency;

    private java.lang.String description;

    private java.lang.String paymentRequestUpdateFlag;

    public SiebelPaymentRequestList() {
    }

    public SiebelPaymentRequestList(
           java.lang.String paymentRequestId,
           java.lang.String paymentType,
           java.lang.String quantity,
           java.lang.String unitPrice,
           java.lang.String totalAmount,
           java.lang.String currency,
           java.lang.String description,
           java.lang.String paymentRequestUpdateFlag) {
           this.paymentRequestId = paymentRequestId;
           this.paymentType = paymentType;
           this.quantity = quantity;
           this.unitPrice = unitPrice;
           this.totalAmount = totalAmount;
           this.currency = currency;
           this.description = description;
           this.paymentRequestUpdateFlag = paymentRequestUpdateFlag;
    }


    /**
     * Gets the paymentRequestId value for this SiebelPaymentRequestList.
     * 
     * @return paymentRequestId
     */
    public java.lang.String getPaymentRequestId() {
        return paymentRequestId;
    }


    /**
     * Sets the paymentRequestId value for this SiebelPaymentRequestList.
     * 
     * @param paymentRequestId
     */
    public void setPaymentRequestId(java.lang.String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }


    /**
     * Gets the paymentType value for this SiebelPaymentRequestList.
     * 
     * @return paymentType
     */
    public java.lang.String getPaymentType() {
        return paymentType;
    }


    /**
     * Sets the paymentType value for this SiebelPaymentRequestList.
     * 
     * @param paymentType
     */
    public void setPaymentType(java.lang.String paymentType) {
        this.paymentType = paymentType;
    }


    /**
     * Gets the quantity value for this SiebelPaymentRequestList.
     * 
     * @return quantity
     */
    public java.lang.String getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this SiebelPaymentRequestList.
     * 
     * @param quantity
     */
    public void setQuantity(java.lang.String quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the unitPrice value for this SiebelPaymentRequestList.
     * 
     * @return unitPrice
     */
    public java.lang.String getUnitPrice() {
        return unitPrice;
    }


    /**
     * Sets the unitPrice value for this SiebelPaymentRequestList.
     * 
     * @param unitPrice
     */
    public void setUnitPrice(java.lang.String unitPrice) {
        this.unitPrice = unitPrice;
    }


    /**
     * Gets the totalAmount value for this SiebelPaymentRequestList.
     * 
     * @return totalAmount
     */
    public java.lang.String getTotalAmount() {
        return totalAmount;
    }


    /**
     * Sets the totalAmount value for this SiebelPaymentRequestList.
     * 
     * @param totalAmount
     */
    public void setTotalAmount(java.lang.String totalAmount) {
        this.totalAmount = totalAmount;
    }


    /**
     * Gets the currency value for this SiebelPaymentRequestList.
     * 
     * @return currency
     */
    public java.lang.String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this SiebelPaymentRequestList.
     * 
     * @param currency
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }


    /**
     * Gets the description value for this SiebelPaymentRequestList.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this SiebelPaymentRequestList.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the paymentRequestUpdateFlag value for this SiebelPaymentRequestList.
     * 
     * @return paymentRequestUpdateFlag
     */
    public java.lang.String getPaymentRequestUpdateFlag() {
        return paymentRequestUpdateFlag;
    }


    /**
     * Sets the paymentRequestUpdateFlag value for this SiebelPaymentRequestList.
     * 
     * @param paymentRequestUpdateFlag
     */
    public void setPaymentRequestUpdateFlag(java.lang.String paymentRequestUpdateFlag) {
        this.paymentRequestUpdateFlag = paymentRequestUpdateFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelPaymentRequestList)) return false;
        SiebelPaymentRequestList other = (SiebelPaymentRequestList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paymentRequestId==null && other.getPaymentRequestId()==null) || 
             (this.paymentRequestId!=null &&
              this.paymentRequestId.equals(other.getPaymentRequestId()))) &&
            ((this.paymentType==null && other.getPaymentType()==null) || 
             (this.paymentType!=null &&
              this.paymentType.equals(other.getPaymentType()))) &&
            ((this.quantity==null && other.getQuantity()==null) || 
             (this.quantity!=null &&
              this.quantity.equals(other.getQuantity()))) &&
            ((this.unitPrice==null && other.getUnitPrice()==null) || 
             (this.unitPrice!=null &&
              this.unitPrice.equals(other.getUnitPrice()))) &&
            ((this.totalAmount==null && other.getTotalAmount()==null) || 
             (this.totalAmount!=null &&
              this.totalAmount.equals(other.getTotalAmount()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.paymentRequestUpdateFlag==null && other.getPaymentRequestUpdateFlag()==null) || 
             (this.paymentRequestUpdateFlag!=null &&
              this.paymentRequestUpdateFlag.equals(other.getPaymentRequestUpdateFlag())));
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
        if (getPaymentRequestId() != null) {
            _hashCode += getPaymentRequestId().hashCode();
        }
        if (getPaymentType() != null) {
            _hashCode += getPaymentType().hashCode();
        }
        if (getQuantity() != null) {
            _hashCode += getQuantity().hashCode();
        }
        if (getUnitPrice() != null) {
            _hashCode += getUnitPrice().hashCode();
        }
        if (getTotalAmount() != null) {
            _hashCode += getTotalAmount().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getPaymentRequestUpdateFlag() != null) {
            _hashCode += getPaymentRequestUpdateFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelPaymentRequestList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "SiebelPaymentRequestList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRequestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaymentRequestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaymentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unitPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UnitPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TotalAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRequestUpdateFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaymentRequestUpdateFlag"));
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
