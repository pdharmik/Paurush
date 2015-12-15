/**
 * LineInformation2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.taxCalc;

public class LineInformation2  implements java.io.Serializable {
    private java.lang.String sourceReferenceLineId;

    private java.lang.String plant;

    private java.lang.String materialNumber;

    private java.lang.String netPrice;

    private java.lang.String taxAmount;

    private java.lang.String profitCenter;

    public LineInformation2() {
    }

    public LineInformation2(
           java.lang.String sourceReferenceLineId,
           java.lang.String plant,
           java.lang.String materialNumber,
           java.lang.String netPrice,
           java.lang.String taxAmount,
           java.lang.String profitCenter) {
           this.sourceReferenceLineId = sourceReferenceLineId;
           this.plant = plant;
           this.materialNumber = materialNumber;
           this.netPrice = netPrice;
           this.taxAmount = taxAmount;
           this.profitCenter = profitCenter;
    }


    /**
     * Gets the sourceReferenceLineId value for this LineInformation2.
     * 
     * @return sourceReferenceLineId
     */
    public java.lang.String getSourceReferenceLineId() {
        return sourceReferenceLineId;
    }


    /**
     * Sets the sourceReferenceLineId value for this LineInformation2.
     * 
     * @param sourceReferenceLineId
     */
    public void setSourceReferenceLineId(java.lang.String sourceReferenceLineId) {
        this.sourceReferenceLineId = sourceReferenceLineId;
    }


    /**
     * Gets the plant value for this LineInformation2.
     * 
     * @return plant
     */
    public java.lang.String getPlant() {
        return plant;
    }


    /**
     * Sets the plant value for this LineInformation2.
     * 
     * @param plant
     */
    public void setPlant(java.lang.String plant) {
        this.plant = plant;
    }


    /**
     * Gets the materialNumber value for this LineInformation2.
     * 
     * @return materialNumber
     */
    public java.lang.String getMaterialNumber() {
        return materialNumber;
    }


    /**
     * Sets the materialNumber value for this LineInformation2.
     * 
     * @param materialNumber
     */
    public void setMaterialNumber(java.lang.String materialNumber) {
        this.materialNumber = materialNumber;
    }


    /**
     * Gets the netPrice value for this LineInformation2.
     * 
     * @return netPrice
     */
    public java.lang.String getNetPrice() {
        return netPrice;
    }


    /**
     * Sets the netPrice value for this LineInformation2.
     * 
     * @param netPrice
     */
    public void setNetPrice(java.lang.String netPrice) {
        this.netPrice = netPrice;
    }


    /**
     * Gets the taxAmount value for this LineInformation2.
     * 
     * @return taxAmount
     */
    public java.lang.String getTaxAmount() {
        return taxAmount;
    }


    /**
     * Sets the taxAmount value for this LineInformation2.
     * 
     * @param taxAmount
     */
    public void setTaxAmount(java.lang.String taxAmount) {
        this.taxAmount = taxAmount;
    }


    /**
     * Gets the profitCenter value for this LineInformation2.
     * 
     * @return profitCenter
     */
    public java.lang.String getProfitCenter() {
        return profitCenter;
    }


    /**
     * Sets the profitCenter value for this LineInformation2.
     * 
     * @param profitCenter
     */
    public void setProfitCenter(java.lang.String profitCenter) {
        this.profitCenter = profitCenter;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LineInformation2)) return false;
        LineInformation2 other = (LineInformation2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sourceReferenceLineId==null && other.getSourceReferenceLineId()==null) || 
             (this.sourceReferenceLineId!=null &&
              this.sourceReferenceLineId.equals(other.getSourceReferenceLineId()))) &&
            ((this.plant==null && other.getPlant()==null) || 
             (this.plant!=null &&
              this.plant.equals(other.getPlant()))) &&
            ((this.materialNumber==null && other.getMaterialNumber()==null) || 
             (this.materialNumber!=null &&
              this.materialNumber.equals(other.getMaterialNumber()))) &&
            ((this.netPrice==null && other.getNetPrice()==null) || 
             (this.netPrice!=null &&
              this.netPrice.equals(other.getNetPrice()))) &&
            ((this.taxAmount==null && other.getTaxAmount()==null) || 
             (this.taxAmount!=null &&
              this.taxAmount.equals(other.getTaxAmount()))) &&
            ((this.profitCenter==null && other.getProfitCenter()==null) || 
             (this.profitCenter!=null &&
              this.profitCenter.equals(other.getProfitCenter())));
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
        if (getSourceReferenceLineId() != null) {
            _hashCode += getSourceReferenceLineId().hashCode();
        }
        if (getPlant() != null) {
            _hashCode += getPlant().hashCode();
        }
        if (getMaterialNumber() != null) {
            _hashCode += getMaterialNumber().hashCode();
        }
        if (getNetPrice() != null) {
            _hashCode += getNetPrice().hashCode();
        }
        if (getTaxAmount() != null) {
            _hashCode += getTaxAmount().hashCode();
        }
        if (getProfitCenter() != null) {
            _hashCode += getProfitCenter().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LineInformation2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "LineInformation2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceReferenceLineId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SourceReferenceLineId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plant");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Plant"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("materialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MaterialNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("netPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NetPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TaxAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profitCenter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProfitCenter"));
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
