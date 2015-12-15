/**
 * TaxCalculationWSInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.taxCalc;

public class TaxCalculationWSInput  implements java.io.Serializable {
    private com.lexmark.taxCalc.TaxCalculationWSInput2 taxCalculationWSInput;

    public TaxCalculationWSInput() {
    }

    public TaxCalculationWSInput(
           com.lexmark.taxCalc.TaxCalculationWSInput2 taxCalculationWSInput) {
           this.taxCalculationWSInput = taxCalculationWSInput;
    }


    /**
     * Gets the taxCalculationWSInput value for this TaxCalculationWSInput.
     * 
     * @return taxCalculationWSInput
     */
    public com.lexmark.taxCalc.TaxCalculationWSInput2 getTaxCalculationWSInput() {
        return taxCalculationWSInput;
    }


    /**
     * Sets the taxCalculationWSInput value for this TaxCalculationWSInput.
     * 
     * @param taxCalculationWSInput
     */
    public void setTaxCalculationWSInput(com.lexmark.taxCalc.TaxCalculationWSInput2 taxCalculationWSInput) {
        this.taxCalculationWSInput = taxCalculationWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxCalculationWSInput)) return false;
        TaxCalculationWSInput other = (TaxCalculationWSInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.taxCalculationWSInput==null && other.getTaxCalculationWSInput()==null) || 
             (this.taxCalculationWSInput!=null &&
              this.taxCalculationWSInput.equals(other.getTaxCalculationWSInput())));
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
        if (getTaxCalculationWSInput() != null) {
            _hashCode += getTaxCalculationWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxCalculationWSInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "TaxCalculationWSInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxCalculationWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TaxCalculationWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "TaxCalculationWSInput2"));
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
