/**
 * DebriefInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class DebriefInformation  implements java.io.Serializable {
    private java.lang.String installedProduct;

    private java.lang.String replacementProductSerialNumber;

    private java.lang.String replacementProductModel;

    private java.lang.String actualStartDateTime;

    private java.lang.String actualEndDateTime;

    private java.lang.String actualFailureCode;

    private java.lang.String actualResolutionCode;

    public DebriefInformation() {
    }

    public DebriefInformation(
           java.lang.String installedProduct,
           java.lang.String replacementProductSerialNumber,
           java.lang.String replacementProductModel,
           java.lang.String actualStartDateTime,
           java.lang.String actualEndDateTime,
           java.lang.String actualFailureCode,
           java.lang.String actualResolutionCode) {
           this.installedProduct = installedProduct;
           this.replacementProductSerialNumber = replacementProductSerialNumber;
           this.replacementProductModel = replacementProductModel;
           this.actualStartDateTime = actualStartDateTime;
           this.actualEndDateTime = actualEndDateTime;
           this.actualFailureCode = actualFailureCode;
           this.actualResolutionCode = actualResolutionCode;
    }


    /**
     * Gets the installedProduct value for this DebriefInformation.
     * 
     * @return installedProduct
     */
    public java.lang.String getInstalledProduct() {
        return installedProduct;
    }


    /**
     * Sets the installedProduct value for this DebriefInformation.
     * 
     * @param installedProduct
     */
    public void setInstalledProduct(java.lang.String installedProduct) {
        this.installedProduct = installedProduct;
    }


    /**
     * Gets the replacementProductSerialNumber value for this DebriefInformation.
     * 
     * @return replacementProductSerialNumber
     */
    public java.lang.String getReplacementProductSerialNumber() {
        return replacementProductSerialNumber;
    }


    /**
     * Sets the replacementProductSerialNumber value for this DebriefInformation.
     * 
     * @param replacementProductSerialNumber
     */
    public void setReplacementProductSerialNumber(java.lang.String replacementProductSerialNumber) {
        this.replacementProductSerialNumber = replacementProductSerialNumber;
    }


    /**
     * Gets the replacementProductModel value for this DebriefInformation.
     * 
     * @return replacementProductModel
     */
    public java.lang.String getReplacementProductModel() {
        return replacementProductModel;
    }


    /**
     * Sets the replacementProductModel value for this DebriefInformation.
     * 
     * @param replacementProductModel
     */
    public void setReplacementProductModel(java.lang.String replacementProductModel) {
        this.replacementProductModel = replacementProductModel;
    }


    /**
     * Gets the actualStartDateTime value for this DebriefInformation.
     * 
     * @return actualStartDateTime
     */
    public java.lang.String getActualStartDateTime() {
        return actualStartDateTime;
    }


    /**
     * Sets the actualStartDateTime value for this DebriefInformation.
     * 
     * @param actualStartDateTime
     */
    public void setActualStartDateTime(java.lang.String actualStartDateTime) {
        this.actualStartDateTime = actualStartDateTime;
    }


    /**
     * Gets the actualEndDateTime value for this DebriefInformation.
     * 
     * @return actualEndDateTime
     */
    public java.lang.String getActualEndDateTime() {
        return actualEndDateTime;
    }


    /**
     * Sets the actualEndDateTime value for this DebriefInformation.
     * 
     * @param actualEndDateTime
     */
    public void setActualEndDateTime(java.lang.String actualEndDateTime) {
        this.actualEndDateTime = actualEndDateTime;
    }


    /**
     * Gets the actualFailureCode value for this DebriefInformation.
     * 
     * @return actualFailureCode
     */
    public java.lang.String getActualFailureCode() {
        return actualFailureCode;
    }


    /**
     * Sets the actualFailureCode value for this DebriefInformation.
     * 
     * @param actualFailureCode
     */
    public void setActualFailureCode(java.lang.String actualFailureCode) {
        this.actualFailureCode = actualFailureCode;
    }


    /**
     * Gets the actualResolutionCode value for this DebriefInformation.
     * 
     * @return actualResolutionCode
     */
    public java.lang.String getActualResolutionCode() {
        return actualResolutionCode;
    }


    /**
     * Sets the actualResolutionCode value for this DebriefInformation.
     * 
     * @param actualResolutionCode
     */
    public void setActualResolutionCode(java.lang.String actualResolutionCode) {
        this.actualResolutionCode = actualResolutionCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DebriefInformation)) return false;
        DebriefInformation other = (DebriefInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.installedProduct==null && other.getInstalledProduct()==null) || 
             (this.installedProduct!=null &&
              this.installedProduct.equals(other.getInstalledProduct()))) &&
            ((this.replacementProductSerialNumber==null && other.getReplacementProductSerialNumber()==null) || 
             (this.replacementProductSerialNumber!=null &&
              this.replacementProductSerialNumber.equals(other.getReplacementProductSerialNumber()))) &&
            ((this.replacementProductModel==null && other.getReplacementProductModel()==null) || 
             (this.replacementProductModel!=null &&
              this.replacementProductModel.equals(other.getReplacementProductModel()))) &&
            ((this.actualStartDateTime==null && other.getActualStartDateTime()==null) || 
             (this.actualStartDateTime!=null &&
              this.actualStartDateTime.equals(other.getActualStartDateTime()))) &&
            ((this.actualEndDateTime==null && other.getActualEndDateTime()==null) || 
             (this.actualEndDateTime!=null &&
              this.actualEndDateTime.equals(other.getActualEndDateTime()))) &&
            ((this.actualFailureCode==null && other.getActualFailureCode()==null) || 
             (this.actualFailureCode!=null &&
              this.actualFailureCode.equals(other.getActualFailureCode()))) &&
            ((this.actualResolutionCode==null && other.getActualResolutionCode()==null) || 
             (this.actualResolutionCode!=null &&
              this.actualResolutionCode.equals(other.getActualResolutionCode())));
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
        if (getInstalledProduct() != null) {
            _hashCode += getInstalledProduct().hashCode();
        }
        if (getReplacementProductSerialNumber() != null) {
            _hashCode += getReplacementProductSerialNumber().hashCode();
        }
        if (getReplacementProductModel() != null) {
            _hashCode += getReplacementProductModel().hashCode();
        }
        if (getActualStartDateTime() != null) {
            _hashCode += getActualStartDateTime().hashCode();
        }
        if (getActualEndDateTime() != null) {
            _hashCode += getActualEndDateTime().hashCode();
        }
        if (getActualFailureCode() != null) {
            _hashCode += getActualFailureCode().hashCode();
        }
        if (getActualResolutionCode() != null) {
            _hashCode += getActualResolutionCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DebriefInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DebriefInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("installedProduct");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "InstalledProduct"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replacementProductSerialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReplacementProductSerialNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replacementProductModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReplacementProductModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualStartDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActualStartDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualEndDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActualEndDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualFailureCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActualFailureCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualResolutionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActualResolutionCode"));
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
