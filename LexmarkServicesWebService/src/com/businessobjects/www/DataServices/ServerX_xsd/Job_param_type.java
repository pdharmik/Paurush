/**
 * Job_param_type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class Job_param_type  implements java.io.Serializable {
    private java.lang.String job_system_profile;

    private java.lang.Integer sampling_rate;

    private java.lang.Boolean disableValidationStatisticsCollection;

    private java.lang.Boolean auditing;

    private java.lang.Boolean recovery;

    private java.lang.String job_server;

    private com.businessobjects.www.DataServices.ServerX_xsd.TracingTypeType[] trace;

    private com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeDistribution_level distribution_level;

    private com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeSubstitutionParametersParameter[] substitutionParameters;

    public Job_param_type() {
    }

    public Job_param_type(
           java.lang.String job_system_profile,
           java.lang.Integer sampling_rate,
           java.lang.Boolean disableValidationStatisticsCollection,
           java.lang.Boolean auditing,
           java.lang.Boolean recovery,
           java.lang.String job_server,
           com.businessobjects.www.DataServices.ServerX_xsd.TracingTypeType[] trace,
           com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeDistribution_level distribution_level,
           com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeSubstitutionParametersParameter[] substitutionParameters) {
           this.job_system_profile = job_system_profile;
           this.sampling_rate = sampling_rate;
           this.disableValidationStatisticsCollection = disableValidationStatisticsCollection;
           this.auditing = auditing;
           this.recovery = recovery;
           this.job_server = job_server;
           this.trace = trace;
           this.distribution_level = distribution_level;
           this.substitutionParameters = substitutionParameters;
    }


    /**
     * Gets the job_system_profile value for this Job_param_type.
     * 
     * @return job_system_profile
     */
    public java.lang.String getJob_system_profile() {
        return job_system_profile;
    }


    /**
     * Sets the job_system_profile value for this Job_param_type.
     * 
     * @param job_system_profile
     */
    public void setJob_system_profile(java.lang.String job_system_profile) {
        this.job_system_profile = job_system_profile;
    }


    /**
     * Gets the sampling_rate value for this Job_param_type.
     * 
     * @return sampling_rate
     */
    public java.lang.Integer getSampling_rate() {
        return sampling_rate;
    }


    /**
     * Sets the sampling_rate value for this Job_param_type.
     * 
     * @param sampling_rate
     */
    public void setSampling_rate(java.lang.Integer sampling_rate) {
        this.sampling_rate = sampling_rate;
    }


    /**
     * Gets the disableValidationStatisticsCollection value for this Job_param_type.
     * 
     * @return disableValidationStatisticsCollection
     */
    public java.lang.Boolean getDisableValidationStatisticsCollection() {
        return disableValidationStatisticsCollection;
    }


    /**
     * Sets the disableValidationStatisticsCollection value for this Job_param_type.
     * 
     * @param disableValidationStatisticsCollection
     */
    public void setDisableValidationStatisticsCollection(java.lang.Boolean disableValidationStatisticsCollection) {
        this.disableValidationStatisticsCollection = disableValidationStatisticsCollection;
    }


    /**
     * Gets the auditing value for this Job_param_type.
     * 
     * @return auditing
     */
    public java.lang.Boolean getAuditing() {
        return auditing;
    }


    /**
     * Sets the auditing value for this Job_param_type.
     * 
     * @param auditing
     */
    public void setAuditing(java.lang.Boolean auditing) {
        this.auditing = auditing;
    }


    /**
     * Gets the recovery value for this Job_param_type.
     * 
     * @return recovery
     */
    public java.lang.Boolean getRecovery() {
        return recovery;
    }


    /**
     * Sets the recovery value for this Job_param_type.
     * 
     * @param recovery
     */
    public void setRecovery(java.lang.Boolean recovery) {
        this.recovery = recovery;
    }


    /**
     * Gets the job_server value for this Job_param_type.
     * 
     * @return job_server
     */
    public java.lang.String getJob_server() {
        return job_server;
    }


    /**
     * Sets the job_server value for this Job_param_type.
     * 
     * @param job_server
     */
    public void setJob_server(java.lang.String job_server) {
        this.job_server = job_server;
    }


    /**
     * Gets the trace value for this Job_param_type.
     * 
     * @return trace
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.TracingTypeType[] getTrace() {
        return trace;
    }


    /**
     * Sets the trace value for this Job_param_type.
     * 
     * @param trace
     */
    public void setTrace(com.businessobjects.www.DataServices.ServerX_xsd.TracingTypeType[] trace) {
        this.trace = trace;
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.TracingTypeType getTrace(int i) {
        return this.trace[i];
    }

    public void setTrace(int i, com.businessobjects.www.DataServices.ServerX_xsd.TracingTypeType _value) {
        this.trace[i] = _value;
    }


    /**
     * Gets the distribution_level value for this Job_param_type.
     * 
     * @return distribution_level
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeDistribution_level getDistribution_level() {
        return distribution_level;
    }


    /**
     * Sets the distribution_level value for this Job_param_type.
     * 
     * @param distribution_level
     */
    public void setDistribution_level(com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeDistribution_level distribution_level) {
        this.distribution_level = distribution_level;
    }


    /**
     * Gets the substitutionParameters value for this Job_param_type.
     * 
     * @return substitutionParameters
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeSubstitutionParametersParameter[] getSubstitutionParameters() {
        return substitutionParameters;
    }


    /**
     * Sets the substitutionParameters value for this Job_param_type.
     * 
     * @param substitutionParameters
     */
    public void setSubstitutionParameters(com.businessobjects.www.DataServices.ServerX_xsd.Job_param_typeSubstitutionParametersParameter[] substitutionParameters) {
        this.substitutionParameters = substitutionParameters;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Job_param_type)) return false;
        Job_param_type other = (Job_param_type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.job_system_profile==null && other.getJob_system_profile()==null) || 
             (this.job_system_profile!=null &&
              this.job_system_profile.equals(other.getJob_system_profile()))) &&
            ((this.sampling_rate==null && other.getSampling_rate()==null) || 
             (this.sampling_rate!=null &&
              this.sampling_rate.equals(other.getSampling_rate()))) &&
            ((this.disableValidationStatisticsCollection==null && other.getDisableValidationStatisticsCollection()==null) || 
             (this.disableValidationStatisticsCollection!=null &&
              this.disableValidationStatisticsCollection.equals(other.getDisableValidationStatisticsCollection()))) &&
            ((this.auditing==null && other.getAuditing()==null) || 
             (this.auditing!=null &&
              this.auditing.equals(other.getAuditing()))) &&
            ((this.recovery==null && other.getRecovery()==null) || 
             (this.recovery!=null &&
              this.recovery.equals(other.getRecovery()))) &&
            ((this.job_server==null && other.getJob_server()==null) || 
             (this.job_server!=null &&
              this.job_server.equals(other.getJob_server()))) &&
            ((this.trace==null && other.getTrace()==null) || 
             (this.trace!=null &&
              java.util.Arrays.equals(this.trace, other.getTrace()))) &&
            ((this.distribution_level==null && other.getDistribution_level()==null) || 
             (this.distribution_level!=null &&
              this.distribution_level.equals(other.getDistribution_level()))) &&
            ((this.substitutionParameters==null && other.getSubstitutionParameters()==null) || 
             (this.substitutionParameters!=null &&
              java.util.Arrays.equals(this.substitutionParameters, other.getSubstitutionParameters())));
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
        if (getJob_system_profile() != null) {
            _hashCode += getJob_system_profile().hashCode();
        }
        if (getSampling_rate() != null) {
            _hashCode += getSampling_rate().hashCode();
        }
        if (getDisableValidationStatisticsCollection() != null) {
            _hashCode += getDisableValidationStatisticsCollection().hashCode();
        }
        if (getAuditing() != null) {
            _hashCode += getAuditing().hashCode();
        }
        if (getRecovery() != null) {
            _hashCode += getRecovery().hashCode();
        }
        if (getJob_server() != null) {
            _hashCode += getJob_server().hashCode();
        }
        if (getTrace() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTrace());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTrace(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDistribution_level() != null) {
            _hashCode += getDistribution_level().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Job_param_type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "job_param_type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("job_system_profile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "job_system_profile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sampling_rate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sampling_rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disableValidationStatisticsCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("", "disableValidationStatisticsCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("auditing");
        elemField.setXmlName(new javax.xml.namespace.QName("", "auditing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recovery");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recovery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("job_server");
        elemField.setXmlName(new javax.xml.namespace.QName("", "job_server"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", "TracingTypeType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("distribution_level");
        elemField.setXmlName(new javax.xml.namespace.QName("", "distribution_level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">job_param_type>distribution_level"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("substitutionParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("", "substitutionParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>job_param_type>substitutionParameters>parameter"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "parameter"));
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
