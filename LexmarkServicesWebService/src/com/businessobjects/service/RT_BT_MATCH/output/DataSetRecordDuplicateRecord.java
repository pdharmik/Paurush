/**
 * DataSetRecordDuplicateRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.service.RT_BT_MATCH.output;

public class DataSetRecordDuplicateRecord  implements java.io.Serializable {
    private java.lang.String accountId;

    private java.lang.String DQMatchScore;

    public DataSetRecordDuplicateRecord() {
    }

    public DataSetRecordDuplicateRecord(
           java.lang.String accountId,
           java.lang.String DQMatchScore) {
           this.accountId = accountId;
           this.DQMatchScore = DQMatchScore;
    }


    /**
     * Gets the accountId value for this DataSetRecordDuplicateRecord.
     * 
     * @return accountId
     */
    public java.lang.String getAccountId() {
        return accountId;
    }


    /**
     * Sets the accountId value for this DataSetRecordDuplicateRecord.
     * 
     * @param accountId
     */
    public void setAccountId(java.lang.String accountId) {
        this.accountId = accountId;
    }


    /**
     * Gets the DQMatchScore value for this DataSetRecordDuplicateRecord.
     * 
     * @return DQMatchScore
     */
    public java.lang.String getDQMatchScore() {
        return DQMatchScore;
    }


    /**
     * Sets the DQMatchScore value for this DataSetRecordDuplicateRecord.
     * 
     * @param DQMatchScore
     */
    public void setDQMatchScore(java.lang.String DQMatchScore) {
        this.DQMatchScore = DQMatchScore;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataSetRecordDuplicateRecord)) return false;
        DataSetRecordDuplicateRecord other = (DataSetRecordDuplicateRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountId==null && other.getAccountId()==null) || 
             (this.accountId!=null &&
              this.accountId.equals(other.getAccountId()))) &&
            ((this.DQMatchScore==null && other.getDQMatchScore()==null) || 
             (this.DQMatchScore!=null &&
              this.DQMatchScore.equals(other.getDQMatchScore())));
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
        if (getAccountId() != null) {
            _hashCode += getAccountId().hashCode();
        }
        if (getDQMatchScore() != null) {
            _hashCode += getDQMatchScore().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataSetRecordDuplicateRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_BT_MATCH/output", ">>>DataSet>Record>DuplicateRecord"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Account.Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DQMatchScore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DQ.MatchScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
