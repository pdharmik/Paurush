/**
 * ImportObjectDefinitionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class ImportObjectDefinitionRequest  implements java.io.Serializable {
    private java.lang.String definition;

    private java.lang.String repoName;

    private java.lang.String jobServer;

    private java.lang.String serverGroup;

    private java.lang.String passphrase;

    private java.lang.Boolean traceOn;

    public ImportObjectDefinitionRequest() {
    }

    public ImportObjectDefinitionRequest(
           java.lang.String definition,
           java.lang.String repoName,
           java.lang.String jobServer,
           java.lang.String serverGroup,
           java.lang.String passphrase,
           java.lang.Boolean traceOn) {
           this.definition = definition;
           this.repoName = repoName;
           this.jobServer = jobServer;
           this.serverGroup = serverGroup;
           this.passphrase = passphrase;
           this.traceOn = traceOn;
    }


    /**
     * Gets the definition value for this ImportObjectDefinitionRequest.
     * 
     * @return definition
     */
    public java.lang.String getDefinition() {
        return definition;
    }


    /**
     * Sets the definition value for this ImportObjectDefinitionRequest.
     * 
     * @param definition
     */
    public void setDefinition(java.lang.String definition) {
        this.definition = definition;
    }


    /**
     * Gets the repoName value for this ImportObjectDefinitionRequest.
     * 
     * @return repoName
     */
    public java.lang.String getRepoName() {
        return repoName;
    }


    /**
     * Sets the repoName value for this ImportObjectDefinitionRequest.
     * 
     * @param repoName
     */
    public void setRepoName(java.lang.String repoName) {
        this.repoName = repoName;
    }


    /**
     * Gets the jobServer value for this ImportObjectDefinitionRequest.
     * 
     * @return jobServer
     */
    public java.lang.String getJobServer() {
        return jobServer;
    }


    /**
     * Sets the jobServer value for this ImportObjectDefinitionRequest.
     * 
     * @param jobServer
     */
    public void setJobServer(java.lang.String jobServer) {
        this.jobServer = jobServer;
    }


    /**
     * Gets the serverGroup value for this ImportObjectDefinitionRequest.
     * 
     * @return serverGroup
     */
    public java.lang.String getServerGroup() {
        return serverGroup;
    }


    /**
     * Sets the serverGroup value for this ImportObjectDefinitionRequest.
     * 
     * @param serverGroup
     */
    public void setServerGroup(java.lang.String serverGroup) {
        this.serverGroup = serverGroup;
    }


    /**
     * Gets the passphrase value for this ImportObjectDefinitionRequest.
     * 
     * @return passphrase
     */
    public java.lang.String getPassphrase() {
        return passphrase;
    }


    /**
     * Sets the passphrase value for this ImportObjectDefinitionRequest.
     * 
     * @param passphrase
     */
    public void setPassphrase(java.lang.String passphrase) {
        this.passphrase = passphrase;
    }


    /**
     * Gets the traceOn value for this ImportObjectDefinitionRequest.
     * 
     * @return traceOn
     */
    public java.lang.Boolean getTraceOn() {
        return traceOn;
    }


    /**
     * Sets the traceOn value for this ImportObjectDefinitionRequest.
     * 
     * @param traceOn
     */
    public void setTraceOn(java.lang.Boolean traceOn) {
        this.traceOn = traceOn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ImportObjectDefinitionRequest)) return false;
        ImportObjectDefinitionRequest other = (ImportObjectDefinitionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.definition==null && other.getDefinition()==null) || 
             (this.definition!=null &&
              this.definition.equals(other.getDefinition()))) &&
            ((this.repoName==null && other.getRepoName()==null) || 
             (this.repoName!=null &&
              this.repoName.equals(other.getRepoName()))) &&
            ((this.jobServer==null && other.getJobServer()==null) || 
             (this.jobServer!=null &&
              this.jobServer.equals(other.getJobServer()))) &&
            ((this.serverGroup==null && other.getServerGroup()==null) || 
             (this.serverGroup!=null &&
              this.serverGroup.equals(other.getServerGroup()))) &&
            ((this.passphrase==null && other.getPassphrase()==null) || 
             (this.passphrase!=null &&
              this.passphrase.equals(other.getPassphrase()))) &&
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
        if (getDefinition() != null) {
            _hashCode += getDefinition().hashCode();
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
        if (getPassphrase() != null) {
            _hashCode += getPassphrase().hashCode();
        }
        if (getTraceOn() != null) {
            _hashCode += getTraceOn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ImportObjectDefinitionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ImportObjectDefinitionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("definition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "definition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("passphrase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "passphrase"));
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
