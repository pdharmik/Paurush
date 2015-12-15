/**
 * BatchJobResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class BatchJobResponse  implements java.io.Serializable {
    private int pid;

    private int cid;

    private int rid;

    private java.lang.String repoName;

    private java.lang.String errorMessage;

    public BatchJobResponse() {
    }

    public BatchJobResponse(
           int pid,
           int cid,
           int rid,
           java.lang.String repoName,
           java.lang.String errorMessage) {
           this.pid = pid;
           this.cid = cid;
           this.rid = rid;
           this.repoName = repoName;
           this.errorMessage = errorMessage;
    }


    /**
     * Gets the pid value for this BatchJobResponse.
     * 
     * @return pid
     */
    public int getPid() {
        return pid;
    }


    /**
     * Sets the pid value for this BatchJobResponse.
     * 
     * @param pid
     */
    public void setPid(int pid) {
        this.pid = pid;
    }


    /**
     * Gets the cid value for this BatchJobResponse.
     * 
     * @return cid
     */
    public int getCid() {
        return cid;
    }


    /**
     * Sets the cid value for this BatchJobResponse.
     * 
     * @param cid
     */
    public void setCid(int cid) {
        this.cid = cid;
    }


    /**
     * Gets the rid value for this BatchJobResponse.
     * 
     * @return rid
     */
    public int getRid() {
        return rid;
    }


    /**
     * Sets the rid value for this BatchJobResponse.
     * 
     * @param rid
     */
    public void setRid(int rid) {
        this.rid = rid;
    }


    /**
     * Gets the repoName value for this BatchJobResponse.
     * 
     * @return repoName
     */
    public java.lang.String getRepoName() {
        return repoName;
    }


    /**
     * Sets the repoName value for this BatchJobResponse.
     * 
     * @param repoName
     */
    public void setRepoName(java.lang.String repoName) {
        this.repoName = repoName;
    }


    /**
     * Gets the errorMessage value for this BatchJobResponse.
     * 
     * @return errorMessage
     */
    public java.lang.String getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets the errorMessage value for this BatchJobResponse.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(java.lang.String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BatchJobResponse)) return false;
        BatchJobResponse other = (BatchJobResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.pid == other.getPid() &&
            this.cid == other.getCid() &&
            this.rid == other.getRid() &&
            ((this.repoName==null && other.getRepoName()==null) || 
             (this.repoName!=null &&
              this.repoName.equals(other.getRepoName()))) &&
            ((this.errorMessage==null && other.getErrorMessage()==null) || 
             (this.errorMessage!=null &&
              this.errorMessage.equals(other.getErrorMessage())));
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
        _hashCode += getPid();
        _hashCode += getCid();
        _hashCode += getRid();
        if (getRepoName() != null) {
            _hashCode += getRepoName().hashCode();
        }
        if (getErrorMessage() != null) {
            _hashCode += getErrorMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BatchJobResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">BatchJobResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repoName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repoName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorMessage"));
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
