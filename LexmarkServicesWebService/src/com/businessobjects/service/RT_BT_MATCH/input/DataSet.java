/**
 * DataSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.service.RT_BT_MATCH.input;

public class DataSet  implements java.io.Serializable {
    private java.math.BigInteger batchID;

    private java.lang.String action;

    private java.math.BigInteger startingRecordNumber;

    private java.math.BigInteger endingRecordNumber;

    private com.businessobjects.service.RT_BT_MATCH.input.DataSetRecord[] record;

    public DataSet() {
    }

    public DataSet(
           java.math.BigInteger batchID,
           java.lang.String action,
           java.math.BigInteger startingRecordNumber,
           java.math.BigInteger endingRecordNumber,
           com.businessobjects.service.RT_BT_MATCH.input.DataSetRecord[] record) {
           this.batchID = batchID;
           this.action = action;
           this.startingRecordNumber = startingRecordNumber;
           this.endingRecordNumber = endingRecordNumber;
           this.record = record;
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
     * Gets the startingRecordNumber value for this DataSet.
     * 
     * @return startingRecordNumber
     */
    public java.math.BigInteger getStartingRecordNumber() {
        return startingRecordNumber;
    }


    /**
     * Sets the startingRecordNumber value for this DataSet.
     * 
     * @param startingRecordNumber
     */
    public void setStartingRecordNumber(java.math.BigInteger startingRecordNumber) {
        this.startingRecordNumber = startingRecordNumber;
    }


    /**
     * Gets the endingRecordNumber value for this DataSet.
     * 
     * @return endingRecordNumber
     */
    public java.math.BigInteger getEndingRecordNumber() {
        return endingRecordNumber;
    }


    /**
     * Sets the endingRecordNumber value for this DataSet.
     * 
     * @param endingRecordNumber
     */
    public void setEndingRecordNumber(java.math.BigInteger endingRecordNumber) {
        this.endingRecordNumber = endingRecordNumber;
    }


    /**
     * Gets the record value for this DataSet.
     * 
     * @return record
     */
    public com.businessobjects.service.RT_BT_MATCH.input.DataSetRecord[] getRecord() {
        return record;
    }


    /**
     * Sets the record value for this DataSet.
     * 
     * @param record
     */
    public void setRecord(com.businessobjects.service.RT_BT_MATCH.input.DataSetRecord[] record) {
        this.record = record;
    }

    public com.businessobjects.service.RT_BT_MATCH.input.DataSetRecord getRecord(int i) {
        return this.record[i];
    }

    public void setRecord(int i, com.businessobjects.service.RT_BT_MATCH.input.DataSetRecord _value) {
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
            ((this.batchID==null && other.getBatchID()==null) || 
             (this.batchID!=null &&
              this.batchID.equals(other.getBatchID()))) &&
            ((this.action==null && other.getAction()==null) || 
             (this.action!=null &&
              this.action.equals(other.getAction()))) &&
            ((this.startingRecordNumber==null && other.getStartingRecordNumber()==null) || 
             (this.startingRecordNumber!=null &&
              this.startingRecordNumber.equals(other.getStartingRecordNumber()))) &&
            ((this.endingRecordNumber==null && other.getEndingRecordNumber()==null) || 
             (this.endingRecordNumber!=null &&
              this.endingRecordNumber.equals(other.getEndingRecordNumber()))) &&
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
        if (getBatchID() != null) {
            _hashCode += getBatchID().hashCode();
        }
        if (getAction() != null) {
            _hashCode += getAction().hashCode();
        }
        if (getStartingRecordNumber() != null) {
            _hashCode += getStartingRecordNumber().hashCode();
        }
        if (getEndingRecordNumber() != null) {
            _hashCode += getEndingRecordNumber().hashCode();
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
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_BT_MATCH/input", ">DataSet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BatchID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("action");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Action"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startingRecordNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StartingRecordNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endingRecordNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EndingRecordNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("record");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Record"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_BT_MATCH/input", ">>DataSet>Record"));
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
