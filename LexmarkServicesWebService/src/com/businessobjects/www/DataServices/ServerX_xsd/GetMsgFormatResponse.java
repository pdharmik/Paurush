/**
 * GetMsgFormatResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class GetMsgFormatResponse  implements java.io.Serializable {
    private java.lang.String schema;

    private java.lang.String rootElement;

    private java.lang.String rootElementNS;

    private com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatResponseDependentFormat[] dependentFormat;

    private java.lang.String errorMessage;

    public GetMsgFormatResponse() {
    }

    public GetMsgFormatResponse(
           java.lang.String schema,
           java.lang.String rootElement,
           java.lang.String rootElementNS,
           com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatResponseDependentFormat[] dependentFormat,
           java.lang.String errorMessage) {
           this.schema = schema;
           this.rootElement = rootElement;
           this.rootElementNS = rootElementNS;
           this.dependentFormat = dependentFormat;
           this.errorMessage = errorMessage;
    }


    /**
     * Gets the schema value for this GetMsgFormatResponse.
     * 
     * @return schema
     */
    public java.lang.String getSchema() {
        return schema;
    }


    /**
     * Sets the schema value for this GetMsgFormatResponse.
     * 
     * @param schema
     */
    public void setSchema(java.lang.String schema) {
        this.schema = schema;
    }


    /**
     * Gets the rootElement value for this GetMsgFormatResponse.
     * 
     * @return rootElement
     */
    public java.lang.String getRootElement() {
        return rootElement;
    }


    /**
     * Sets the rootElement value for this GetMsgFormatResponse.
     * 
     * @param rootElement
     */
    public void setRootElement(java.lang.String rootElement) {
        this.rootElement = rootElement;
    }


    /**
     * Gets the rootElementNS value for this GetMsgFormatResponse.
     * 
     * @return rootElementNS
     */
    public java.lang.String getRootElementNS() {
        return rootElementNS;
    }


    /**
     * Sets the rootElementNS value for this GetMsgFormatResponse.
     * 
     * @param rootElementNS
     */
    public void setRootElementNS(java.lang.String rootElementNS) {
        this.rootElementNS = rootElementNS;
    }


    /**
     * Gets the dependentFormat value for this GetMsgFormatResponse.
     * 
     * @return dependentFormat
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatResponseDependentFormat[] getDependentFormat() {
        return dependentFormat;
    }


    /**
     * Sets the dependentFormat value for this GetMsgFormatResponse.
     * 
     * @param dependentFormat
     */
    public void setDependentFormat(com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatResponseDependentFormat[] dependentFormat) {
        this.dependentFormat = dependentFormat;
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatResponseDependentFormat getDependentFormat(int i) {
        return this.dependentFormat[i];
    }

    public void setDependentFormat(int i, com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatResponseDependentFormat _value) {
        this.dependentFormat[i] = _value;
    }


    /**
     * Gets the errorMessage value for this GetMsgFormatResponse.
     * 
     * @return errorMessage
     */
    public java.lang.String getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets the errorMessage value for this GetMsgFormatResponse.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(java.lang.String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetMsgFormatResponse)) return false;
        GetMsgFormatResponse other = (GetMsgFormatResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.schema==null && other.getSchema()==null) || 
             (this.schema!=null &&
              this.schema.equals(other.getSchema()))) &&
            ((this.rootElement==null && other.getRootElement()==null) || 
             (this.rootElement!=null &&
              this.rootElement.equals(other.getRootElement()))) &&
            ((this.rootElementNS==null && other.getRootElementNS()==null) || 
             (this.rootElementNS!=null &&
              this.rootElementNS.equals(other.getRootElementNS()))) &&
            ((this.dependentFormat==null && other.getDependentFormat()==null) || 
             (this.dependentFormat!=null &&
              java.util.Arrays.equals(this.dependentFormat, other.getDependentFormat()))) &&
            ((this.errorMessage==null && other.getErrorMessage()==null) || 
             (this.errorMessage!=null &&
              this.errorMessage.equals(other.getErrorMessage())));
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
        if (getSchema() != null) {
            _hashCode += getSchema().hashCode();
        }
        if (getRootElement() != null) {
            _hashCode += getRootElement().hashCode();
        }
        if (getRootElementNS() != null) {
            _hashCode += getRootElementNS().hashCode();
        }
        if (getDependentFormat() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDependentFormat());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDependentFormat(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getErrorMessage() != null) {
            _hashCode += getErrorMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetMsgFormatResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">GetMsgFormatResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schema");
        elemField.setXmlName(new javax.xml.namespace.QName("", "schema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rootElement");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rootElement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rootElementNS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rootElementNS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dependentFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dependentFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>GetMsgFormatResponse>dependentFormat"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorMessage"));
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
