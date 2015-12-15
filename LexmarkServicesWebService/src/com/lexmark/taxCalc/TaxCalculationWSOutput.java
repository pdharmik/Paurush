/**
 * TaxCalculationWSOutput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.taxCalc;

public class TaxCalculationWSOutput  implements java.io.Serializable {
    private com.lexmark.taxCalc.TaxCalculationWSOutput2 taxCalculationWSOutput;

    public TaxCalculationWSOutput() {
    }

    public TaxCalculationWSOutput(
           com.lexmark.taxCalc.TaxCalculationWSOutput2 taxCalculationWSOutput) {
           this.taxCalculationWSOutput = taxCalculationWSOutput;
    }


    /**
     * Gets the taxCalculationWSOutput value for this TaxCalculationWSOutput.
     * 
     * @return taxCalculationWSOutput
     */
    public com.lexmark.taxCalc.TaxCalculationWSOutput2 getTaxCalculationWSOutput() {
        return taxCalculationWSOutput;
    }


    /**
     * Sets the taxCalculationWSOutput value for this TaxCalculationWSOutput.
     * 
     * @param taxCalculationWSOutput
     */
    public void setTaxCalculationWSOutput(com.lexmark.taxCalc.TaxCalculationWSOutput2 taxCalculationWSOutput) {
        this.taxCalculationWSOutput = taxCalculationWSOutput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxCalculationWSOutput)) return false;
        TaxCalculationWSOutput other = (TaxCalculationWSOutput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.taxCalculationWSOutput==null && other.getTaxCalculationWSOutput()==null) || 
             (this.taxCalculationWSOutput!=null &&
              this.taxCalculationWSOutput.equals(other.getTaxCalculationWSOutput())));
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
        if (getTaxCalculationWSOutput() != null) {
            _hashCode += getTaxCalculationWSOutput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxCalculationWSOutput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "TaxCalculationWSOutput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxCalculationWSOutput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TaxCalculationWSOutput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "TaxCalculationWSOutput2"));
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
