/**
 * ServiceRequestDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ServiceRequestDetails  implements java.io.Serializable {
    private java.lang.String failureCode;

    private java.lang.String problemDescription;

    private java.lang.String requestLexmarkReviewFlag;

    private java.lang.String reviewComments;

    public ServiceRequestDetails() {
    }

    public ServiceRequestDetails(
           java.lang.String failureCode,
           java.lang.String problemDescription,
           java.lang.String requestLexmarkReviewFlag,
           java.lang.String reviewComments) {
           this.failureCode = failureCode;
           this.problemDescription = problemDescription;
           this.requestLexmarkReviewFlag = requestLexmarkReviewFlag;
           this.reviewComments = reviewComments;
    }


    /**
     * Gets the failureCode value for this ServiceRequestDetails.
     * 
     * @return failureCode
     */
    public java.lang.String getFailureCode() {
        return failureCode;
    }


    /**
     * Sets the failureCode value for this ServiceRequestDetails.
     * 
     * @param failureCode
     */
    public void setFailureCode(java.lang.String failureCode) {
        this.failureCode = failureCode;
    }


    /**
     * Gets the problemDescription value for this ServiceRequestDetails.
     * 
     * @return problemDescription
     */
    public java.lang.String getProblemDescription() {
        return problemDescription;
    }


    /**
     * Sets the problemDescription value for this ServiceRequestDetails.
     * 
     * @param problemDescription
     */
    public void setProblemDescription(java.lang.String problemDescription) {
        this.problemDescription = problemDescription;
    }


    /**
     * Gets the requestLexmarkReviewFlag value for this ServiceRequestDetails.
     * 
     * @return requestLexmarkReviewFlag
     */
    public java.lang.String getRequestLexmarkReviewFlag() {
        return requestLexmarkReviewFlag;
    }


    /**
     * Sets the requestLexmarkReviewFlag value for this ServiceRequestDetails.
     * 
     * @param requestLexmarkReviewFlag
     */
    public void setRequestLexmarkReviewFlag(java.lang.String requestLexmarkReviewFlag) {
        this.requestLexmarkReviewFlag = requestLexmarkReviewFlag;
    }


    /**
     * Gets the reviewComments value for this ServiceRequestDetails.
     * 
     * @return reviewComments
     */
    public java.lang.String getReviewComments() {
        return reviewComments;
    }


    /**
     * Sets the reviewComments value for this ServiceRequestDetails.
     * 
     * @param reviewComments
     */
    public void setReviewComments(java.lang.String reviewComments) {
        this.reviewComments = reviewComments;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestDetails)) return false;
        ServiceRequestDetails other = (ServiceRequestDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.failureCode==null && other.getFailureCode()==null) || 
             (this.failureCode!=null &&
              this.failureCode.equals(other.getFailureCode()))) &&
            ((this.problemDescription==null && other.getProblemDescription()==null) || 
             (this.problemDescription!=null &&
              this.problemDescription.equals(other.getProblemDescription()))) &&
            ((this.requestLexmarkReviewFlag==null && other.getRequestLexmarkReviewFlag()==null) || 
             (this.requestLexmarkReviewFlag!=null &&
              this.requestLexmarkReviewFlag.equals(other.getRequestLexmarkReviewFlag()))) &&
            ((this.reviewComments==null && other.getReviewComments()==null) || 
             (this.reviewComments!=null &&
              this.reviewComments.equals(other.getReviewComments())));
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
        if (getFailureCode() != null) {
            _hashCode += getFailureCode().hashCode();
        }
        if (getProblemDescription() != null) {
            _hashCode += getProblemDescription().hashCode();
        }
        if (getRequestLexmarkReviewFlag() != null) {
            _hashCode += getRequestLexmarkReviewFlag().hashCode();
        }
        if (getReviewComments() != null) {
            _hashCode += getReviewComments().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("failureCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FailureCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problemDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProblemDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestLexmarkReviewFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestLexmarkReviewFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reviewComments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReviewComments"));
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
