/**
 * ErrorLogRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class ErrorLogRequest  implements java.io.Serializable {
    private int runID;

    private java.lang.String repoName;

    private java.lang.Integer page;

    public ErrorLogRequest() {
    }

    public ErrorLogRequest(
           int runID,
           java.lang.String repoName,
           java.lang.Integer page) {
           this.runID = runID;
           this.repoName = repoName;
           this.page = page;
    }


    /**
     * Gets the runID value for this ErrorLogRequest.
     * 
     * @return runID
     */
    public int getRunID() {
        return runID;
    }


    /**
     * Sets the runID value for this ErrorLogRequest.
     * 
     * @param runID
     */
    public void setRunID(int runID) {
        this.runID = runID;
    }


    /**
     * Gets the repoName value for this ErrorLogRequest.
     * 
     * @return repoName
     */
    public java.lang.String getRepoName() {
        return repoName;
    }


    /**
     * Sets the repoName value for this ErrorLogRequest.
     * 
     * @param repoName
     */
    public void setRepoName(java.lang.String repoName) {
        this.repoName = repoName;
    }


    /**
     * Gets the page value for this ErrorLogRequest.
     * 
     * @return page
     */
    public java.lang.Integer getPage() {
        return page;
    }


    /**
     * Sets the page value for this ErrorLogRequest.
     * 
     * @param page
     */
    public void setPage(java.lang.Integer page) {
        this.page = page;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErrorLogRequest)) return false;
        ErrorLogRequest other = (ErrorLogRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.runID == other.getRunID() &&
            ((this.repoName==null && other.getRepoName()==null) || 
             (this.repoName!=null &&
              this.repoName.equals(other.getRepoName()))) &&
            ((this.page==null && other.getPage()==null) || 
             (this.page!=null &&
              this.page.equals(other.getPage())));
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
        _hashCode += getRunID();
        if (getRepoName() != null) {
            _hashCode += getRepoName().hashCode();
        }
        if (getPage() != null) {
            _hashCode += getPage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErrorLogRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ErrorLogRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("runID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "runID"));
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
        elemField.setFieldName("page");
        elemField.setXmlName(new javax.xml.namespace.QName("", "page"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
