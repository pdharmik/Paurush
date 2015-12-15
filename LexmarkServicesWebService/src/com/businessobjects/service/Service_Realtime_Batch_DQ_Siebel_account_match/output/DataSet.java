/**
 * DataSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output;

public class DataSet  implements java.io.Serializable {
    private java.lang.String action;

    private java.math.BigInteger batchID;

    private java.lang.String status;

    private com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecord[] record;

    public DataSet() {
    }

    public DataSet(
           java.lang.String action,
           java.math.BigInteger batchID,
           java.lang.String status,
           com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecord[] record) {
           this.action = action;
           this.batchID = batchID;
           this.status = status;
           this.record = record;
    }


    /**
     * Gets the action value for this DataSet.
     * 
     * @return action
     */
    public java.lang.String getAction() {
        return action;
    }


    /**
     * Sets the action value for this DataSet.
     * 
     * @param action
     */
    public void setAction(java.lang.String action) {
        this.action = action;
    }


    /**
     * Gets the batchID value for this DataSet.
     * 
     * @return batchID
     */
    public java.math.BigInteger getBatchID() {
        return batchID;
    }


    /**
     * Sets the batchID value for this DataSet.
     * 
     * @param batchID
     */
    public void setBatchID(java.math.BigInteger batchID) {
        this.batchID = batchID;
    }


    /**
     * Gets the status value for this DataSet.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this DataSet.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the record value for this DataSet.
     * 
     * @return record
     */
    public com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecord[] getRecord() {
        return record;
    }


    /**
     * Sets the record value for this DataSet.
     * 
     * @param record
     */
    public void setRecord(com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecord[] record) {
        this.record = record;
    }

    public com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecord getRecord(int i) {
        return this.record[i];
    }

    public void setRecord(int i, com.businessobjects.service.Service_Realtime_Batch_DQ_Siebel_account_match.output.DataSetRecord _value) {
        this.record[i] = _value;
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
            ((this.action==null && other.getAction()==null) || 
             (this.action!=null &&
              this.action.equals(other.getAction()))) &&
            ((this.batchID==null && other.getBatchID()==null) || 
             (this.batchID!=null &&
              this.batchID.equals(other.getBatchID()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.record==null && other.getRecord()==null) || 
             (this.record!=null &&
              java.util.Arrays.equals(this.record, other.getRecord())));
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
        if (getAction() != null) {
            _hashCode += getAction().hashCode();
        }
        if (getBatchID() != null) {
            _hashCode += getBatchID().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getRecord() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecord());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecord(), i);
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
        new org.apache.axis.description.TypeDesc(DataSet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_account_match/output", ">DataSet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("action");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Action"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BatchID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("record");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Record"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_Batch_DQ_Siebel_account_match/output", ">>DataSet>Record"));
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
