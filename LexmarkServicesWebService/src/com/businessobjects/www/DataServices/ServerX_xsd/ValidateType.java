/**
 * ValidateType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class ValidateType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ValidateType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _BATCH_JOB = "BATCH_JOB";
    public static final java.lang.String _REALTIME_JOB = "REALTIME_JOB";
    public static final java.lang.String _WORKFLOW = "WORKFLOW";
    public static final java.lang.String _DATAFLOW = "DATAFLOW";
    public static final java.lang.String _ABAP_DATAFLOW = "ABAP_DATAFLOW";
    public static final java.lang.String _DATA_QUALITY_TRANSFORM_CONFIGURATION = "DATA_QUALITY_TRANSFORM_CONFIGURATION";
    public static final java.lang.String _CUSTOM_FUNCTION = "CUSTOM_FUNCTION";
    public static final ValidateType BATCH_JOB = new ValidateType(_BATCH_JOB);
    public static final ValidateType REALTIME_JOB = new ValidateType(_REALTIME_JOB);
    public static final ValidateType WORKFLOW = new ValidateType(_WORKFLOW);
    public static final ValidateType DATAFLOW = new ValidateType(_DATAFLOW);
    public static final ValidateType ABAP_DATAFLOW = new ValidateType(_ABAP_DATAFLOW);
    public static final ValidateType DATA_QUALITY_TRANSFORM_CONFIGURATION = new ValidateType(_DATA_QUALITY_TRANSFORM_CONFIGURATION);
    public static final ValidateType CUSTOM_FUNCTION = new ValidateType(_CUSTOM_FUNCTION);
    public java.lang.String getValue() { return _value_;}
    public static ValidateType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ValidateType enumeration = (ValidateType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ValidateType fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidateType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ValidateType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
