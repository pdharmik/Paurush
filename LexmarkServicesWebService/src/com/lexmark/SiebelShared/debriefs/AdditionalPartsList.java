/**
 * AdditionalPartsList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;


public class AdditionalPartsList  implements java.io.Serializable {
    private java.lang.String partNumber;

    private java.lang.String partDescription;

    private java.lang.String quantity;

    private java.lang.String usedQuantity;

    private java.lang.String notUsedQuantity;

    private java.lang.String DOAQuantity;

    public AdditionalPartsList() {
    }

    public AdditionalPartsList(
           java.lang.String partNumber,
           java.lang.String partDescription,
           java.lang.String quantity,
           java.lang.String usedQuantity,
           java.lang.String notUsedQuantity,
           java.lang.String DOAQuantity) {
           this.partNumber = partNumber;
           this.partDescription = partDescription;
           this.quantity = quantity;
           this.usedQuantity = usedQuantity;
           this.notUsedQuantity = notUsedQuantity;
           this.DOAQuantity = DOAQuantity;
    }


    /**
     * Gets the partNumber value for this AdditionalPartsList.
     * 
     * @return partNumber
     */
    public java.lang.String getPartNumber() {
        return partNumber;
    }


    /**
     * Sets the partNumber value for this AdditionalPartsList.
     * 
     * @param partNumber
     */
    public void setPartNumber(java.lang.String partNumber) {
        this.partNumber = partNumber;
    }


    /**
     * Gets the partDescription value for this AdditionalPartsList.
     * 
     * @return partDescription
     */
    public java.lang.String getPartDescription() {
        return partDescription;
    }


    /**
     * Sets the partDescription value for this AdditionalPartsList.
     * 
     * @param partDescription
     */
    public void setPartDescription(java.lang.String partDescription) {
        this.partDescription = partDescription;
    }


    /**
     * Gets the quantity value for this AdditionalPartsList.
     * 
     * @return quantity
     */
    public java.lang.String getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this AdditionalPartsList.
     * 
     * @param quantity
     */
    public void setQuantity(java.lang.String quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the usedQuantity value for this AdditionalPartsList.
     * 
     * @return usedQuantity
     */
    public java.lang.String getUsedQuantity() {
        return usedQuantity;
    }


    /**
     * Sets the usedQuantity value for this AdditionalPartsList.
     * 
     * @param usedQuantity
     */
    public void setUsedQuantity(java.lang.String usedQuantity) {
        this.usedQuantity = usedQuantity;
    }


    /**
     * Gets the notUsedQuantity value for this AdditionalPartsList.
     * 
     * @return notUsedQuantity
     */
    public java.lang.String getNotUsedQuantity() {
        return notUsedQuantity;
    }


    /**
     * Sets the notUsedQuantity value for this AdditionalPartsList.
     * 
     * @param notUsedQuantity
     */
    public void setNotUsedQuantity(java.lang.String notUsedQuantity) {
        this.notUsedQuantity = notUsedQuantity;
    }


    /**
     * Gets the DOAQuantity value for this AdditionalPartsList.
     * 
     * @return DOAQuantity
     */
    public java.lang.String getDOAQuantity() {
        return DOAQuantity;
    }


    /**
     * Sets the DOAQuantity value for this AdditionalPartsList.
     * 
     * @param DOAQuantity
     */
    public void setDOAQuantity(java.lang.String DOAQuantity) {
        this.DOAQuantity = DOAQuantity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AdditionalPartsList)) return false;
        AdditionalPartsList other = (AdditionalPartsList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.partNumber==null && other.getPartNumber()==null) || 
             (this.partNumber!=null &&
              this.partNumber.equals(other.getPartNumber()))) &&
            ((this.partDescription==null && other.getPartDescription()==null) || 
             (this.partDescription!=null &&
              this.partDescription.equals(other.getPartDescription()))) &&
            ((this.quantity==null && other.getQuantity()==null) || 
             (this.quantity!=null &&
              this.quantity.equals(other.getQuantity()))) &&
            ((this.usedQuantity==null && other.getUsedQuantity()==null) || 
             (this.usedQuantity!=null &&
              this.usedQuantity.equals(other.getUsedQuantity()))) &&
            ((this.notUsedQuantity==null && other.getNotUsedQuantity()==null) || 
             (this.notUsedQuantity!=null &&
              this.notUsedQuantity.equals(other.getNotUsedQuantity()))) &&
            ((this.DOAQuantity==null && other.getDOAQuantity()==null) || 
             (this.DOAQuantity!=null &&
              this.DOAQuantity.equals(other.getDOAQuantity())));
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
        if (getPartNumber() != null) {
            _hashCode += getPartNumber().hashCode();
        }
        if (getPartDescription() != null) {
            _hashCode += getPartDescription().hashCode();
        }
        if (getQuantity() != null) {
            _hashCode += getQuantity().hashCode();
        }
        if (getUsedQuantity() != null) {
            _hashCode += getUsedQuantity().hashCode();
        }
        if (getNotUsedQuantity() != null) {
            _hashCode += getNotUsedQuantity().hashCode();
        }
        if (getDOAQuantity() != null) {
            _hashCode += getDOAQuantity().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AdditionalPartsList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AdditionalPartsList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usedQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UsedQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notUsedQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NotUsedQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOAQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOAQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
