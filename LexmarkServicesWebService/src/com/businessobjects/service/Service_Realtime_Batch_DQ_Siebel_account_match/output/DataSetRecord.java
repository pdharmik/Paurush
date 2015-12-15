/**
 * DataSetRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output;

public class DataSetRecord  implements java.io.Serializable {
    private java.lang.String DQMasterRecordsRowID;

    private com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecordDuplicateRecord[] duplicateRecord;

    public DataSetRecord() {
    }

    public DataSetRecord(
           java.lang.String DQMasterRecordsRowID,
           com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecordDuplicateRecord[] duplicateRecord) {
           this.DQMasterRecordsRowID = DQMasterRecordsRowID;
           this.duplicateRecord = duplicateRecord;
    }


    /**
     * Gets the DQMasterRecordsRowID value for this DataSetRecord.
     * 
     * @return DQMasterRecordsRowID
     */
    public java.lang.String getDQMasterRecordsRowID() {
        return DQMasterRecordsRowID;
    }


    /**
     * Sets the DQMasterRecordsRowID value for this DataSetRecord.
     * 
     * @param DQMasterRecordsRowID
     */
    public void setDQMasterRecordsRowID(java.lang.String DQMasterRecordsRowID) {
        this.DQMasterRecordsRowID = DQMasterRecordsRowID;
    }


    /**
     * Gets the duplicateRecord value for this DataSetRecord.
     * 
     * @return duplicateRecord
     */
    public com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecordDuplicateRecord[] getDuplicateRecord() {
        return duplicateRecord;
    }


    /**
     * Sets the duplicateRecord value for this DataSetRecord.
     * 
     * @param duplicateRecord
     */
    public void setDuplicateRecord(com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecordDuplicateRecord[] duplicateRecord) {
        this.duplicateRecord = duplicateRecord;
    }

    public com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecordDuplicateRecord getDuplicateRecord(int i) {
        return this.duplicateRecord[i];
    }

    public void setDuplicateRecord(int i, com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecordDuplicateRecord _value) {
        this.duplicateRecord[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataSetRecord)) return false;
        DataSetRecord other = (DataSetRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DQMasterRecordsRowID==null && other.getDQMasterRecordsRowID()==null) || 
             (this.DQMasterRecordsRowID!=null &&
              this.DQMasterRecordsRowID.equals(other.getDQMasterRecordsRowID()))) &&
            ((this.duplicateRecord==null && other.getDuplicateRecord()==null) || 
             (this.duplicateRecord!=null &&
              java.util.Arrays.equals(this.duplicateRecord, other.getDuplicateRecord())));
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
        if (getDQMasterRecordsRowID() != null) {
            _hashCode += getDQMasterRecordsRowID().hashCode();
        }
        if (getDuplicateRecord() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDuplicateRecord());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDuplicateRecord(), i);
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
        new org.apache.axis.description.TypeDesc(DataSetRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_account_match/output", ">>DataSet>Record"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DQMasterRecordsRowID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DQ.MasterRecordsRowID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("duplicateRecord");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DuplicateRecord"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_account_match/output", ">>>DataSet>Record>DuplicateRecord"));
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
