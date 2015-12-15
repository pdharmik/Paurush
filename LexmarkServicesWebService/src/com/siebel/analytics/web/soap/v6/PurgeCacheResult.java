/**
 * PurgeCacheResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v6;

public class PurgeCacheResult  implements java.io.Serializable {
    private com.siebel.analytics.web.soap.v6.JobInfo jobInfo;

    public PurgeCacheResult() {
    }

    public PurgeCacheResult(
           com.siebel.analytics.web.soap.v6.JobInfo jobInfo) {
           this.jobInfo = jobInfo;
    }


    /**
     * Gets the jobInfo value for this PurgeCacheResult.
     * 
     * @return jobInfo
     */
    public com.siebel.analytics.web.soap.v6.JobInfo getJobInfo() {
        return jobInfo;
    }


    /**
     * Sets the jobInfo value for this PurgeCacheResult.
     * 
     * @param jobInfo
     */
    public void setJobInfo(com.siebel.analytics.web.soap.v6.JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PurgeCacheResult)) return false;
        PurgeCacheResult other = (PurgeCacheResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.jobInfo==null && other.getJobInfo()==null) || 
             (this.jobInfo!=null &&
              this.jobInfo.equals(other.getJobInfo())));
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
        if (getJobInfo() != null) {
            _hashCode += getJobInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PurgeCacheResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn://oracle.bi.webservices/v6", ">purgeCacheResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("urn://oracle.bi.webservices/v6", "jobInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn://oracle.bi.webservices/v6", "JobInfo"));
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
