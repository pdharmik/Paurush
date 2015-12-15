/**
 * BatchJobRunIDsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class BatchJobRunIDsResponse  implements java.io.Serializable {
    private com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponseRun[] run;

    private java.lang.String errorMessage;

    public BatchJobRunIDsResponse() {
    }

    public BatchJobRunIDsResponse(
           com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponseRun[] run,
           java.lang.String errorMessage) {
           this.run = run;
           this.errorMessage = errorMessage;
    }


    /**
     * Gets the run value for this BatchJobRunIDsResponse.
     * 
     * @return run
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponseRun[] getRun() {
        return run;
    }


    /**
     * Sets the run value for this BatchJobRunIDsResponse.
     * 
     * @param run
     */
    public void setRun(com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponseRun[] run) {
        this.run = run;
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponseRun getRun(int i) {
        return this.run[i];
    }

    public void setRun(int i, com.businessobjects.www.DataServices.ServerX_xsd.BatchJobRunIDsResponseRun _value) {
        this.run[i] = _value;
    }


    /**
     * Gets the errorMessage value for this BatchJobRunIDsResponse.
     * 
     * @return errorMessage
     */
    public java.lang.String getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets the errorMessage value for this BatchJobRunIDsResponse.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(java.lang.String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BatchJobRunIDsResponse)) return false;
        BatchJobRunIDsResponse other = (BatchJobRunIDsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.run==null && other.getRun()==null) || 
             (this.run!=null &&
              java.util.Arrays.equals(this.run, other.getRun()))) &&
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
        if (getRun() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRun());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRun(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getErrorMessage() != null) {
            _hashCode += getErrorMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BatchJobRunIDsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">batchJobRunIDsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("run");
        elemField.setXmlName(new javax.xml.namespace.QName("", "run"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>batchJobRunIDsResponse>run"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
