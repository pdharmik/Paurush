/**
 * TaxData2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.taxCalc;

public class TaxData2  implements java.io.Serializable {
    private com.lexmark.taxCalc.Header2 header;

    private com.lexmark.taxCalc.LineInformation2[] lineInformation;

    public TaxData2() {
    }

    public TaxData2(
           com.lexmark.taxCalc.Header2 header,
           com.lexmark.taxCalc.LineInformation2[] lineInformation) {
           this.header = header;
           this.lineInformation = lineInformation;
    }


    /**
     * Gets the header value for this TaxData2.
     * 
     * @return header
     */
    public com.lexmark.taxCalc.Header2 getHeader() {
        return header;
    }


    /**
     * Sets the header value for this TaxData2.
     * 
     * @param header
     */
    public void setHeader(com.lexmark.taxCalc.Header2 header) {
        this.header = header;
    }


    /**
     * Gets the lineInformation value for this TaxData2.
     * 
     * @return lineInformation
     */
    public com.lexmark.taxCalc.LineInformation2[] getLineInformation() {
        return lineInformation;
    }


    /**
     * Sets the lineInformation value for this TaxData2.
     * 
     * @param lineInformation
     */
    public void setLineInformation(com.lexmark.taxCalc.LineInformation2[] lineInformation) {
        this.lineInformation = lineInformation;
    }

    public com.lexmark.taxCalc.LineInformation2 getLineInformation(int i) {
        return this.lineInformation[i];
    }

    public void setLineInformation(int i, com.lexmark.taxCalc.LineInformation2 _value) {
        this.lineInformation[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaxData2)) return false;
        TaxData2 other = (TaxData2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              this.header.equals(other.getHeader()))) &&
            ((this.lineInformation==null && other.getLineInformation()==null) || 
             (this.lineInformation!=null &&
              java.util.Arrays.equals(this.lineInformation, other.getLineInformation())));
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
        if (getHeader() != null) {
            _hashCode += getHeader().hashCode();
        }
        if (getLineInformation() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLineInformation());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLineInformation(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaxData2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "TaxData2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "Header2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LineInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "LineInformation2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
