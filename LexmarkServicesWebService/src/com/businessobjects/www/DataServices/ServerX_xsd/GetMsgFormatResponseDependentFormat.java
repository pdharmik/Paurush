/**
 * GetMsgFormatResponseDependentFormat.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class GetMsgFormatResponseDependentFormat  implements java.io.Serializable {
    private java.lang.String schemaName;

    private java.lang.String schema;

    public GetMsgFormatResponseDependentFormat() {
    }

    public GetMsgFormatResponseDependentFormat(
           java.lang.String schemaName,
           java.lang.String schema) {
           this.schemaName = schemaName;
           this.schema = schema;
    }


    /**
     * Gets the schemaName value for this GetMsgFormatResponseDependentFormat.
     * 
     * @return schemaName
     */
    public java.lang.String getSchemaName() {
        return schemaName;
    }


    /**
     * Sets the schemaName value for this GetMsgFormatResponseDependentFormat.
     * 
     * @param schemaName
     */
    public void setSchemaName(java.lang.String schemaName) {
        this.schemaName = schemaName;
    }


    /**
     * Gets the schema value for this GetMsgFormatResponseDependentFormat.
     * 
     * @return schema
     */
    public java.lang.String getSchema() {
        return schema;
    }


    /**
     * Sets the schema value for this GetMsgFormatResponseDependentFormat.
     * 
     * @param schema
     */
    public void setSchema(java.lang.String schema) {
        this.schema = schema;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMsgFormatResponseDependentFormat)) return false;
        GetMsgFormatResponseDependentFormat other = (GetMsgFormatResponseDependentFormat) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.schemaName==null && other.getSchemaName()==null) || 
             (this.schemaName!=null &&
              this.schemaName.equals(other.getSchemaName()))) &&
            ((this.schema==null && other.getSchema()==null) || 
             (this.schema!=null &&
              this.schema.equals(other.getSchema())));
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
        if (getSchemaName() != null) {
            _hashCode += getSchemaName().hashCode();
        }
        if (getSchema() != null) {
            _hashCode += getSchema().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetMsgFormatResponseDependentFormat.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>GetMsgFormatResponse>dependentFormat"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schemaName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "schemaName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schema");
        elemField.setXmlName(new javax.xml.namespace.QName("", "schema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
