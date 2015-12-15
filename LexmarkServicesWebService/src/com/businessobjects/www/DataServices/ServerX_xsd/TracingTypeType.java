/**
 * TracingTypeType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class TracingTypeType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected TracingTypeType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _job_trace_all = "job_trace_all";
    public static final java.lang.String _job_trace_row = "job_trace_row";
    public static final java.lang.String _job_trace_session = "job_trace_session";
    public static final java.lang.String _job_trace_workflow = "job_trace_workflow";
    public static final java.lang.String _job_trace_dataflow = "job_trace_dataflow";
    public static final java.lang.String _job_trace_transform = "job_trace_transform";
    public static final java.lang.String _job_trace_usertransform = "job_trace_usertransform";
    public static final java.lang.String _job_trace_userfunction = "job_trace_userfunction";
    public static final java.lang.String _job_trace_abapquery = "job_trace_abapquery";
    public static final java.lang.String _job_trace_sqlfunctions = "job_trace_sqlfunctions";
    public static final java.lang.String _job_trace_sqlreaders = "job_trace_sqlreaders";
    public static final java.lang.String _job_trace_sqlloaders = "job_trace_sqlloaders";
    public static final java.lang.String _job_trace_optimized_dataflow = "job_trace_optimized_dataflow";
    public static final java.lang.String _job_trace_table = "job_trace_table";
    public static final java.lang.String _job_trace_script = "job_trace_script";
    public static final java.lang.String _job_trace_ascomm = "job_trace_ascomm";
    public static final java.lang.String _job_trace_rfc_function = "job_trace_rfc_function";
    public static final java.lang.String _job_trace_table_reader = "job_trace_table_reader";
    public static final java.lang.String _job_trace_idoc_file = "job_trace_idoc_file";
    public static final java.lang.String _job_trace_adapter = "job_trace_adapter";
    public static final java.lang.String _job_trace_communication = "job_trace_communication";
    public static final java.lang.String _job_trace_parallel_execution = "job_trace_parallel_execution";
    public static final java.lang.String _job_trace_audit = "job_trace_audit";
    public static final TracingTypeType job_trace_all = new TracingTypeType(_job_trace_all);
    public static final TracingTypeType job_trace_row = new TracingTypeType(_job_trace_row);
    public static final TracingTypeType job_trace_session = new TracingTypeType(_job_trace_session);
    public static final TracingTypeType job_trace_workflow = new TracingTypeType(_job_trace_workflow);
    public static final TracingTypeType job_trace_dataflow = new TracingTypeType(_job_trace_dataflow);
    public static final TracingTypeType job_trace_transform = new TracingTypeType(_job_trace_transform);
    public static final TracingTypeType job_trace_usertransform = new TracingTypeType(_job_trace_usertransform);
    public static final TracingTypeType job_trace_userfunction = new TracingTypeType(_job_trace_userfunction);
    public static final TracingTypeType job_trace_abapquery = new TracingTypeType(_job_trace_abapquery);
    public static final TracingTypeType job_trace_sqlfunctions = new TracingTypeType(_job_trace_sqlfunctions);
    public static final TracingTypeType job_trace_sqlreaders = new TracingTypeType(_job_trace_sqlreaders);
    public static final TracingTypeType job_trace_sqlloaders = new TracingTypeType(_job_trace_sqlloaders);
    public static final TracingTypeType job_trace_optimized_dataflow = new TracingTypeType(_job_trace_optimized_dataflow);
    public static final TracingTypeType job_trace_table = new TracingTypeType(_job_trace_table);
    public static final TracingTypeType job_trace_script = new TracingTypeType(_job_trace_script);
    public static final TracingTypeType job_trace_ascomm = new TracingTypeType(_job_trace_ascomm);
    public static final TracingTypeType job_trace_rfc_function = new TracingTypeType(_job_trace_rfc_function);
    public static final TracingTypeType job_trace_table_reader = new TracingTypeType(_job_trace_table_reader);
    public static final TracingTypeType job_trace_idoc_file = new TracingTypeType(_job_trace_idoc_file);
    public static final TracingTypeType job_trace_adapter = new TracingTypeType(_job_trace_adapter);
    public static final TracingTypeType job_trace_communication = new TracingTypeType(_job_trace_communication);
    public static final TracingTypeType job_trace_parallel_execution = new TracingTypeType(_job_trace_parallel_execution);
    public static final TracingTypeType job_trace_audit = new TracingTypeType(_job_trace_audit);
    public java.lang.String getValue() { return _value_;}
    public static TracingTypeType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        TracingTypeType enumeration = (TracingTypeType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static TracingTypeType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(TracingTypeType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "TracingTypeType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
