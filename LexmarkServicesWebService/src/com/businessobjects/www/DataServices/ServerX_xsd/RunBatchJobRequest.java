/**
 * RunBatchJobRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class RunBatchJobRequest  implements java.io.Serializable {
    private java.lang.String jobName;

    private java.lang.String repoName;

    private java.lang.String jobServer;

    private java.lang.String serverGroup;

    private com.businessobjects.www.DataServices.ServerX_xsd.Job_param_type jobParameters;

    private com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequestGlobalVariablesVariable[] globalVariables;

    public RunBatchJobRequest() {
    }

    public RunBatchJobRequest(
           java.lang.String jobName,
           java.lang.String repoName,
           java.lang.String jobServer,
           java.lang.String serverGroup,
           com.businessobjects.www.DataServices.ServerX_xsd.Job_param_type jobParameters,
           com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequestGlobalVariablesVariable[] globalVariables) {
           this.jobName = jobName;
           this.repoName = repoName;
           this.jobServer = jobServer;
           this.serverGroup = serverGroup;
           this.jobParameters = jobParameters;
           this.globalVariables = globalVariables;
    }


    /**
     * Gets the jobName value for this RunBatchJobRequest.
     * 
     * @return jobName
     */
    public java.lang.String getJobName() {
        return jobName;
    }


    /**
     * Sets the jobName value for this RunBatchJobRequest.
     * 
     * @param jobName
     */
    public void setJobName(java.lang.String jobName) {
        this.jobName = jobName;
    }


    /**
     * Gets the repoName value for this RunBatchJobRequest.
     * 
     * @return repoName
     */
    public java.lang.String getRepoName() {
        return repoName;
    }


    /**
     * Sets the repoName value for this RunBatchJobRequest.
     * 
     * @param repoName
     */
    public void setRepoName(java.lang.String repoName) {
        this.repoName = repoName;
    }


    /**
     * Gets the jobServer value for this RunBatchJobRequest.
     * 
     * @return jobServer
     */
    public java.lang.String getJobServer() {
        return jobServer;
    }


    /**
     * Sets the jobServer value for this RunBatchJobRequest.
     * 
     * @param jobServer
     */
    public void setJobServer(java.lang.String jobServer) {
        this.jobServer = jobServer;
    }


    /**
     * Gets the serverGroup value for this RunBatchJobRequest.
     * 
     * @return serverGroup
     */
    public java.lang.String getServerGroup() {
        return serverGroup;
    }


    /**
     * Sets the serverGroup value for this RunBatchJobRequest.
     * 
     * @param serverGroup
     */
    public void setServerGroup(java.lang.String serverGroup) {
        this.serverGroup = serverGroup;
    }


    /**
     * Gets the jobParameters value for this RunBatchJobRequest.
     * 
     * @return jobParameters
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.Job_param_type getJobParameters() {
        return jobParameters;
    }


    /**
     * Sets the jobParameters value for this RunBatchJobRequest.
     * 
     * @param jobParameters
     */
    public void setJobParameters(com.businessobjects.www.DataServices.ServerX_xsd.Job_param_type jobParameters) {
        this.jobParameters = jobParameters;
    }


    /**
     * Gets the globalVariables value for this RunBatchJobRequest.
     * 
     * @return globalVariables
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequestGlobalVariablesVariable[] getGlobalVariables() {
        return globalVariables;
    }


    /**
     * Sets the globalVariables value for this RunBatchJobRequest.
     * 
     * @param globalVariables
     */
    public void setGlobalVariables(com.businessobjects.www.DataServices.ServerX_xsd.RunBatchJobRequestGlobalVariablesVariable[] globalVariables) {
        this.globalVariables = globalVariables;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RunBatchJobRequest)) return false;
        RunBatchJobRequest other = (RunBatchJobRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.jobName==null && other.getJobName()==null) || 
             (this.jobName!=null &&
              this.jobName.equals(other.getJobName()))) &&
            ((this.repoName==null && other.getRepoName()==null) || 
             (this.repoName!=null &&
              this.repoName.equals(other.getRepoName()))) &&
            ((this.jobServer==null && other.getJobServer()==null) || 
             (this.jobServer!=null &&
              this.jobServer.equals(other.getJobServer()))) &&
            ((this.serverGroup==null && other.getServerGroup()==null) || 
             (this.serverGroup!=null &&
              this.serverGroup.equals(other.getServerGroup()))) &&
            ((this.jobParameters==null && other.getJobParameters()==null) || 
             (this.jobParameters!=null &&
              this.jobParameters.equals(other.getJobParameters()))) &&
            ((this.globalVariables==null && other.getGlobalVariables()==null) || 
             (this.globalVariables!=null &&
              java.util.Arrays.equals(this.globalVariables, other.getGlobalVariables())));
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
        if (getJobName() != null) {
            _hashCode += getJobName().hashCode();
        }
        if (getRepoName() != null) {
            _hashCode += getRepoName().hashCode();
        }
        if (getJobServer() != null) {
            _hashCode += getJobServer().hashCode();
        }
        if (getServerGroup() != null) {
            _hashCode += getServerGroup().hashCode();
        }
        if (getJobParameters() != null) {
            _hashCode += getJobParameters().hashCode();
        }
        if (getGlobalVariables() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGlobalVariables());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGlobalVariables(), i);
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
        new org.apache.axis.description.TypeDesc(RunBatchJobRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">RunBatchJobRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repoName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repoName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobServer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobServer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serverGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "job_param_type"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("globalVariables");
        elemField.setXmlName(new javax.xml.namespace.QName("", "globalVariables"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>>RunBatchJobRequest>globalVariables>variable"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "variable"));
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
