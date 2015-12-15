/**
 * GetListOfBatchJobsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class GetListOfBatchJobsRequest  implements java.io.Serializable {
    private java.lang.String repoName;

    private java.lang.Boolean allBatchJobs;

    public GetListOfBatchJobsRequest() {
    }

    public GetListOfBatchJobsRequest(
           java.lang.String repoName,
           java.lang.Boolean allBatchJobs) {
           this.repoName = repoName;
           this.allBatchJobs = allBatchJobs;
    }


    /**
     * Gets the repoName value for this GetListOfBatchJobsRequest.
     * 
     * @return repoName
     */
    public java.lang.String getRepoName() {
        return repoName;
    }


    /**
     * Sets the repoName value for this GetListOfBatchJobsRequest.
     * 
     * @param repoName
     */
    public void setRepoName(java.lang.String repoName) {
        this.repoName = repoName;
    }


    /**
     * Gets the allBatchJobs value for this GetListOfBatchJobsRequest.
     * 
     * @return allBatchJobs
     */
    public java.lang.Boolean getAllBatchJobs() {
        return allBatchJobs;
    }


    /**
     * Sets the allBatchJobs value for this GetListOfBatchJobsRequest.
     * 
     * @param allBatchJobs
     */
    public void setAllBatchJobs(java.lang.Boolean allBatchJobs) {
        this.allBatchJobs = allBatchJobs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetListOfBatchJobsRequest)) return false;
        GetListOfBatchJobsRequest other = (GetListOfBatchJobsRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.repoName==null && other.getRepoName()==null) || 
             (this.repoName!=null &&
              this.repoName.equals(other.getRepoName()))) &&
            ((this.allBatchJobs==null && other.getAllBatchJobs()==null) || 
             (this.allBatchJobs!=null &&
              this.allBatchJobs.equals(other.getAllBatchJobs())));
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
        if (getRepoName() != null) {
            _hashCode += getRepoName().hashCode();
        }
        if (getAllBatchJobs() != null) {
            _hashCode += getAllBatchJobs().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetListOfBatchJobsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">GetListOfBatchJobsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repoName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repoName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allBatchJobs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allBatchJobs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
