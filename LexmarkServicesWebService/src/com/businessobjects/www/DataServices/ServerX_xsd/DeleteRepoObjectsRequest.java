/**
 * DeleteRepoObjectsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class DeleteRepoObjectsRequest  implements java.io.Serializable {
    private com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequestObjName[] objName;

    private java.lang.String repoName;

    private java.lang.String jobServer;

    private java.lang.String serverGroup;

    private java.lang.Boolean traceOn;

    public DeleteRepoObjectsRequest() {
    }

    public DeleteRepoObjectsRequest(
           com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequestObjName[] objName,
           java.lang.String repoName,
           java.lang.String jobServer,
           java.lang.String serverGroup,
           java.lang.Boolean traceOn) {
           this.objName = objName;
           this.repoName = repoName;
           this.jobServer = jobServer;
           this.serverGroup = serverGroup;
           this.traceOn = traceOn;
    }


    /**
     * Gets the objName value for this DeleteRepoObjectsRequest.
     * 
     * @return objName
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequestObjName[] getObjName() {
        return objName;
    }


    /**
     * Sets the objName value for this DeleteRepoObjectsRequest.
     * 
     * @param objName
     */
    public void setObjName(com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequestObjName[] objName) {
        this.objName = objName;
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequestObjName getObjName(int i) {
        return this.objName[i];
    }

    public void setObjName(int i, com.businessobjects.www.DataServices.ServerX_xsd.DeleteRepoObjectsRequestObjName _value) {
        this.objName[i] = _value;
    }


    /**
     * Gets the repoName value for this DeleteRepoObjectsRequest.
     * 
     * @return repoName
     */
    public java.lang.String getRepoName() {
        return repoName;
    }


    /**
     * Sets the repoName value for this DeleteRepoObjectsRequest.
     * 
     * @param repoName
     */
    public void setRepoName(java.lang.String repoName) {
        this.repoName = repoName;
    }


    /**
     * Gets the jobServer value for this DeleteRepoObjectsRequest.
     * 
     * @return jobServer
     */
    public java.lang.String getJobServer() {
        return jobServer;
    }


    /**
     * Sets the jobServer value for this DeleteRepoObjectsRequest.
     * 
     * @param jobServer
     */
    public void setJobServer(java.lang.String jobServer) {
        this.jobServer = jobServer;
    }


    /**
     * Gets the serverGroup value for this DeleteRepoObjectsRequest.
     * 
     * @return serverGroup
     */
    public java.lang.String getServerGroup() {
        return serverGroup;
    }


    /**
     * Sets the serverGroup value for this DeleteRepoObjectsRequest.
     * 
     * @param serverGroup
     */
    public void setServerGroup(java.lang.String serverGroup) {
        this.serverGroup = serverGroup;
    }


    /**
     * Gets the traceOn value for this DeleteRepoObjectsRequest.
     * 
     * @return traceOn
     */
    public java.lang.Boolean getTraceOn() {
        return traceOn;
    }


    /**
     * Sets the traceOn value for this DeleteRepoObjectsRequest.
     * 
     * @param traceOn
     */
    public void setTraceOn(java.lang.Boolean traceOn) {
        this.traceOn = traceOn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeleteRepoObjectsRequest)) return false;
        DeleteRepoObjectsRequest other = (DeleteRepoObjectsRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objName==null && other.getObjName()==null) || 
             (this.objName!=null &&
              java.util.Arrays.equals(this.objName, other.getObjName()))) &&
            ((this.repoName==null && other.getRepoName()==null) || 
             (this.repoName!=null &&
              this.repoName.equals(other.getRepoName()))) &&
            ((this.jobServer==null && other.getJobServer()==null) || 
             (this.jobServer!=null &&
              this.jobServer.equals(other.getJobServer()))) &&
            ((this.serverGroup==null && other.getServerGroup()==null) || 
             (this.serverGroup!=null &&
              this.serverGroup.equals(other.getServerGroup()))) &&
            ((this.traceOn==null && other.getTraceOn()==null) || 
             (this.traceOn!=null &&
              this.traceOn.equals(other.getTraceOn())));
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
        if (getObjName() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjName());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjName(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getTraceOn() != null) {
            _hashCode += getTraceOn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeleteRepoObjectsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">DeleteRepoObjectsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>DeleteRepoObjectsRequest>objName"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repoName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repoName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("traceOn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traceOn"));
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
