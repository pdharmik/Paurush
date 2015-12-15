/**
 * ValidateObjectDefinitionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class ValidateObjectDefinitionRequest  implements java.io.Serializable {
    private java.lang.String objName;

    private com.businessobjects.www.DataServices.ServerX_xsd.ValidateType objType;

    private java.lang.String repoName;

    private java.lang.String jobServer;

    private java.lang.String serverGroup;

    private java.lang.String systemProfile;

    private com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequestSubstitutionParametersParameter[] substitutionParameters;

    private java.lang.Boolean traceOn;

    public ValidateObjectDefinitionRequest() {
    }

    public ValidateObjectDefinitionRequest(
           java.lang.String objName,
           com.businessobjects.www.DataServices.ServerX_xsd.ValidateType objType,
           java.lang.String repoName,
           java.lang.String jobServer,
           java.lang.String serverGroup,
           java.lang.String systemProfile,
           com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequestSubstitutionParametersParameter[] substitutionParameters,
           java.lang.Boolean traceOn) {
           this.objName = objName;
           this.objType = objType;
           this.repoName = repoName;
           this.jobServer = jobServer;
           this.serverGroup = serverGroup;
           this.systemProfile = systemProfile;
           this.substitutionParameters = substitutionParameters;
           this.traceOn = traceOn;
    }


    /**
     * Gets the objName value for this ValidateObjectDefinitionRequest.
     * 
     * @return objName
     */
    public java.lang.String getObjName() {
        return objName;
    }


    /**
     * Sets the objName value for this ValidateObjectDefinitionRequest.
     * 
     * @param objName
     */
    public void setObjName(java.lang.String objName) {
        this.objName = objName;
    }


    /**
     * Gets the objType value for this ValidateObjectDefinitionRequest.
     * 
     * @return objType
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.ValidateType getObjType() {
        return objType;
    }


    /**
     * Sets the objType value for this ValidateObjectDefinitionRequest.
     * 
     * @param objType
     */
    public void setObjType(com.businessobjects.www.DataServices.ServerX_xsd.ValidateType objType) {
        this.objType = objType;
    }


    /**
     * Gets the repoName value for this ValidateObjectDefinitionRequest.
     * 
     * @return repoName
     */
    public java.lang.String getRepoName() {
        return repoName;
    }


    /**
     * Sets the repoName value for this ValidateObjectDefinitionRequest.
     * 
     * @param repoName
     */
    public void setRepoName(java.lang.String repoName) {
        this.repoName = repoName;
    }


    /**
     * Gets the jobServer value for this ValidateObjectDefinitionRequest.
     * 
     * @return jobServer
     */
    public java.lang.String getJobServer() {
        return jobServer;
    }


    /**
     * Sets the jobServer value for this ValidateObjectDefinitionRequest.
     * 
     * @param jobServer
     */
    public void setJobServer(java.lang.String jobServer) {
        this.jobServer = jobServer;
    }


    /**
     * Gets the serverGroup value for this ValidateObjectDefinitionRequest.
     * 
     * @return serverGroup
     */
    public java.lang.String getServerGroup() {
        return serverGroup;
    }


    /**
     * Sets the serverGroup value for this ValidateObjectDefinitionRequest.
     * 
     * @param serverGroup
     */
    public void setServerGroup(java.lang.String serverGroup) {
        this.serverGroup = serverGroup;
    }


    /**
     * Gets the systemProfile value for this ValidateObjectDefinitionRequest.
     * 
     * @return systemProfile
     */
    public java.lang.String getSystemProfile() {
        return systemProfile;
    }


    /**
     * Sets the systemProfile value for this ValidateObjectDefinitionRequest.
     * 
     * @param systemProfile
     */
    public void setSystemProfile(java.lang.String systemProfile) {
        this.systemProfile = systemProfile;
    }


    /**
     * Gets the substitutionParameters value for this ValidateObjectDefinitionRequest.
     * 
     * @return substitutionParameters
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequestSubstitutionParametersParameter[] getSubstitutionParameters() {
        return substitutionParameters;
    }


    /**
     * Sets the substitutionParameters value for this ValidateObjectDefinitionRequest.
     * 
     * @param substitutionParameters
     */
    public void setSubstitutionParameters(com.businessobjects.www.DataServices.ServerX_xsd.ValidateObjectDefinitionRequestSubstitutionParametersParameter[] substitutionParameters) {
        this.substitutionParameters = substitutionParameters;
    }


    /**
     * Gets the traceOn value for this ValidateObjectDefinitionRequest.
     * 
     * @return traceOn
     */
    public java.lang.Boolean getTraceOn() {
        return traceOn;
    }


    /**
     * Sets the traceOn value for this ValidateObjectDefinitionRequest.
     * 
     * @param traceOn
     */
    public void setTraceOn(java.lang.Boolean traceOn) {
        this.traceOn = traceOn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidateObjectDefinitionRequest)) return false;
        ValidateObjectDefinitionRequest other = (ValidateObjectDefinitionRequest) obj;
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
              this.objName.equals(other.getObjName()))) &&
            ((this.objType==null && other.getObjType()==null) || 
             (this.objType!=null &&
              this.objType.equals(other.getObjType()))) &&
            ((this.repoName==null && other.getRepoName()==null) || 
             (this.repoName!=null &&
              this.repoName.equals(other.getRepoName()))) &&
            ((this.jobServer==null && other.getJobServer()==null) || 
             (this.jobServer!=null &&
              this.jobServer.equals(other.getJobServer()))) &&
            ((this.serverGroup==null && other.getServerGroup()==null) || 
             (this.serverGroup!=null &&
              this.serverGroup.equals(other.getServerGroup()))) &&
            ((this.systemProfile==null && other.getSystemProfile()==null) || 
             (this.systemProfile!=null &&
              this.systemProfile.equals(other.getSystemProfile()))) &&
            ((this.substitutionParameters==null && other.getSubstitutionParameters()==null) || 
             (this.substitutionParameters!=null &&
              java.util.Arrays.equals(this.substitutionParameters, other.getSubstitutionParameters()))) &&
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
            _hashCode += getObjName().hashCode();
        }
        if (getObjType() != null) {
            _hashCode += getObjType().hashCode();
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
        if (getSystemProfile() != null) {
            _hashCode += getSystemProfile().hashCode();
        }
        if (getSubstitutionParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubstitutionParameters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubstitutionParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTraceOn() != null) {
            _hashCode += getTraceOn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidateObjectDefinitionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ValidateObjectDefinitionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "objType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "ValidateType"));
        elemField.setNillable(false);
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
        elemField.setFieldName("systemProfile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "systemProfile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("substitutionParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("", "substitutionParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>>ValidateObjectDefinitionRequest>substitutionParameters>parameter"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "parameter"));
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
