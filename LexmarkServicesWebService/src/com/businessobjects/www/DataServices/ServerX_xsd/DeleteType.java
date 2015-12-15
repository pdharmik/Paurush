/**
 * DeleteType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class DeleteType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DeleteType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _BATCH_JOB = "BATCH_JOB";
    public static final java.lang.String _REALTIME_JOB = "REALTIME_JOB";
    public static final java.lang.String _WORKFLOW = "WORKFLOW";
    public static final java.lang.String _DATAFLOW = "DATAFLOW";
    public static final java.lang.String _ABAP_DATAFLOW = "ABAP_DATAFLOW";
    public static final java.lang.String _DATA_QUALITY_TRANSFORM_CONFIGURATION = "DATA_QUALITY_TRANSFORM_CONFIGURATION";
    public static final java.lang.String _DATASTORE = "DATASTORE";
    public static final java.lang.String _FILE_FORMAT = "FILE_FORMAT";
    public static final java.lang.String _XML_SCHEMA = "XML_SCHEMA";
    public static final java.lang.String _DTD = "DTD";
    public static final java.lang.String _CUSTOM_FUNCTION = "CUSTOM_FUNCTION";
    public static final java.lang.String _EXCEL_WORKBOOK = "EXCEL_WORKBOOK";
    public static final java.lang.String _COBOL_COPYBOOK = "COBOL_COPYBOOK";
    public static final java.lang.String _SYSTEM_PROFILE = "SYSTEM_PROFILE";
    public static final java.lang.String _SUBSTITUTION_CONFIGURATION = "SUBSTITUTION_CONFIGURATION";
    public static final java.lang.String _PROJECT = "PROJECT";
    public static final java.lang.String _TABLE = "TABLE";
    public static final java.lang.String _TEMPLATE_TABLE = "TEMPLATE_TABLE";
    public static final java.lang.String _DOMAIN = "DOMAIN";
    public static final java.lang.String _HIERARCHY = "HIERARCHY";
    public static final java.lang.String _STORED_PROCEDURE = "STORED_PROCEDURE";
    public static final java.lang.String _IDOC = "IDOC";
    public static final java.lang.String _BW_MASTER_TRANSFER_STRUCTURES = "BW_MASTER_TRANSFER_STRUCTURES";
    public static final java.lang.String _BW_MASTER_TEXT_TRANSFER_STRUCTURES = "BW_MASTER_TEXT_TRANSFER_STRUCTURES";
    public static final java.lang.String _BW_TRANSACTION_TRANSFER_STRUCTURES = "BW_TRANSACTION_TRANSFER_STRUCTURES";
    public static final java.lang.String _BW_HIERARCHY_TRANSFER = "BW_HIERARCHY_TRANSFER";
    public static final DeleteType BATCH_JOB = new DeleteType(_BATCH_JOB);
    public static final DeleteType REALTIME_JOB = new DeleteType(_REALTIME_JOB);
    public static final DeleteType WORKFLOW = new DeleteType(_WORKFLOW);
    public static final DeleteType DATAFLOW = new DeleteType(_DATAFLOW);
    public static final DeleteType ABAP_DATAFLOW = new DeleteType(_ABAP_DATAFLOW);
    public static final DeleteType DATA_QUALITY_TRANSFORM_CONFIGURATION = new DeleteType(_DATA_QUALITY_TRANSFORM_CONFIGURATION);
    public static final DeleteType DATASTORE = new DeleteType(_DATASTORE);
    public static final DeleteType FILE_FORMAT = new DeleteType(_FILE_FORMAT);
    public static final DeleteType XML_SCHEMA = new DeleteType(_XML_SCHEMA);
    public static final DeleteType DTD = new DeleteType(_DTD);
    public static final DeleteType CUSTOM_FUNCTION = new DeleteType(_CUSTOM_FUNCTION);
    public static final DeleteType EXCEL_WORKBOOK = new DeleteType(_EXCEL_WORKBOOK);
    public static final DeleteType COBOL_COPYBOOK = new DeleteType(_COBOL_COPYBOOK);
    public static final DeleteType SYSTEM_PROFILE = new DeleteType(_SYSTEM_PROFILE);
    public static final DeleteType SUBSTITUTION_CONFIGURATION = new DeleteType(_SUBSTITUTION_CONFIGURATION);
    public static final DeleteType PROJECT = new DeleteType(_PROJECT);
    public static final DeleteType TABLE = new DeleteType(_TABLE);
    public static final DeleteType TEMPLATE_TABLE = new DeleteType(_TEMPLATE_TABLE);
    public static final DeleteType DOMAIN = new DeleteType(_DOMAIN);
    public static final DeleteType HIERARCHY = new DeleteType(_HIERARCHY);
    public static final DeleteType STORED_PROCEDURE = new DeleteType(_STORED_PROCEDURE);
    public static final DeleteType IDOC = new DeleteType(_IDOC);
    public static final DeleteType BW_MASTER_TRANSFER_STRUCTURES = new DeleteType(_BW_MASTER_TRANSFER_STRUCTURES);
    public static final DeleteType BW_MASTER_TEXT_TRANSFER_STRUCTURES = new DeleteType(_BW_MASTER_TEXT_TRANSFER_STRUCTURES);
    public static final DeleteType BW_TRANSACTION_TRANSFER_STRUCTURES = new DeleteType(_BW_TRANSACTION_TRANSFER_STRUCTURES);
    public static final DeleteType BW_HIERARCHY_TRANSFER = new DeleteType(_BW_HIERARCHY_TRANSFER);
    public java.lang.String getValue() { return _value_;}
    public static DeleteType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DeleteType enumeration = (DeleteType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DeleteType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(DeleteType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "DeleteType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
