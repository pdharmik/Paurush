/**
 * DataSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output;

public class DataSet  implements java.io.Serializable {
    private com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSetRecord record;

    public DataSet() {
    }

    public DataSet(
           com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSetRecord record) {
           this.record = record;
    }


    /**
     * Gets the record value for this DataSet.
     * 
     * @return record
     */
    public com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSetRecord getRecord() {
        return record;
    }


    /**
     * Sets the record value for this DataSet.
     * 
     * @param record
     */
    public void setRecord(com.businessobjects.service.Service_Realtime_DQ_Siebel_business_address_datacleanse.output.DataSetRecord record) {
        this.record = record;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataSet)) return false;
        DataSet other = (DataSet) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.record==null && other.getRecord()==null) || 
             (this.record!=null &&
              this.record.equals(other.getRecord())));
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
        if (getRecord() != null) {
            _hashCode += getRecord().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataSet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/output", ">DataSet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("record");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Record"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_business_address_datacleanse/output", ">>DataSet>Record"));
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
