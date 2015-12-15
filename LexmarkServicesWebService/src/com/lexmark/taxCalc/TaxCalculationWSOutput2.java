/**
 * TaxCalculationWSOutput2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.taxCalc;

public class TaxCalculationWSOutput2  implements java.io.Serializable {
    private com.lexmark.taxCalc.DocumentMetaData2 documentMetaData;

    private com.lexmark.taxCalc.TaxData2 taxData;

    public TaxCalculationWSOutput2() {
    }

    public TaxCalculationWSOutput2(
           com.lexmark.taxCalc.DocumentMetaData2 documentMetaData,
           com.lexmark.taxCalc.TaxData2 taxData) {
           this.documentMetaData = documentMetaData;
           this.taxData = taxData;
    }


    /**
     * Gets the documentMetaData value for this TaxCalculationWSOutput2.
     * 
     * @return documentMetaData
     */
    public com.lexmark.taxCalc.DocumentMetaData2 getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this TaxCalculationWSOutput2.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.taxCalc.DocumentMetaData2 documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the taxData value for this TaxCalculationWSOutput2.
     * 
     * @return taxData
     */
    public com.lexmark.taxCalc.TaxData2 getTaxData() {
        return taxData;
    }


    /**
     * Sets the taxData value for this TaxCalculationWSOutput2.
     * 
     * @param taxData
     */
    public void setTaxData(com.lexmark.taxCalc.TaxData2 taxData) {
        this.taxData = taxData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxCalculationWSOutput2)) return false;
        TaxCalculationWSOutput2 other = (TaxCalculationWSOutput2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentMetaData==null && other.getDocumentMetaData()==null) || 
             (this.documentMetaData!=null &&
              this.documentMetaData.equals(other.getDocumentMetaData()))) &&
            ((this.taxData==null && other.getTaxData()==null) || 
             (this.taxData!=null &&
              this.taxData.equals(other.getTaxData())));
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
        if (getDocumentMetaData() != null) {
            _hashCode += getDocumentMetaData().hashCode();
        }
        if (getTaxData() != null) {
            _hashCode += getTaxData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxCalculationWSOutput2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "TaxCalculationWSOutput2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "DocumentMetaData2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TaxData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "TaxData2"));
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
