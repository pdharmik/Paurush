/**
 * SAWSessionParameters.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public class SAWSessionParameters  implements java.io.Serializable {
    private com.siebel.analytics.web.soap.v5.SAWLocale locale;

    private java.lang.String userAgent;

    private java.lang.String features;

    private boolean asyncLogon;

    private com.siebel.analytics.web.soap.v5.LogonParameter[] logonParams;

    private java.lang.String sessionID;

    public SAWSessionParameters() {
    }

    public SAWSessionParameters(
           com.siebel.analytics.web.soap.v5.SAWLocale locale,
           java.lang.String userAgent,
           java.lang.String features,
           boolean asyncLogon,
           com.siebel.analytics.web.soap.v5.LogonParameter[] logonParams,
           java.lang.String sessionID) {
           this.locale = locale;
           this.userAgent = userAgent;
           this.features = features;
           this.asyncLogon = asyncLogon;
           this.logonParams = logonParams;
           this.sessionID = sessionID;
    }


    /**
     * Gets the locale value for this SAWSessionParameters.
     * 
     * @return locale
     */
    public com.siebel.analytics.web.soap.v5.SAWLocale getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this SAWSessionParameters.
     * 
     * @param locale
     */
    public void setLocale(com.siebel.analytics.web.soap.v5.SAWLocale locale) {
        this.locale = locale;
    }


    /**
     * Gets the userAgent value for this SAWSessionParameters.
     * 
     * @return userAgent
     */
    public java.lang.String getUserAgent() {
        return userAgent;
    }


    /**
     * Sets the userAgent value for this SAWSessionParameters.
     * 
     * @param userAgent
     */
    public void setUserAgent(java.lang.String userAgent) {
        this.userAgent = userAgent;
    }


    /**
     * Gets the features value for this SAWSessionParameters.
     * 
     * @return features
     */
    public java.lang.String getFeatures() {
        return features;
    }


    /**
     * Sets the features value for this SAWSessionParameters.
     * 
     * @param features
     */
    public void setFeatures(java.lang.String features) {
        this.features = features;
    }


    /**
     * Gets the asyncLogon value for this SAWSessionParameters.
     * 
     * @return asyncLogon
     */
    public boolean isAsyncLogon() {
        return asyncLogon;
    }


    /**
     * Sets the asyncLogon value for this SAWSessionParameters.
     * 
     * @param asyncLogon
     */
    public void setAsyncLogon(boolean asyncLogon) {
        this.asyncLogon = asyncLogon;
    }


    /**
     * Gets the logonParams value for this SAWSessionParameters.
     * 
     * @return logonParams
     */
    public com.siebel.analytics.web.soap.v5.LogonParameter[] getLogonParams() {
        return logonParams;
    }


    /**
     * Sets the logonParams value for this SAWSessionParameters.
     * 
     * @param logonParams
     */
    public void setLogonParams(com.siebel.analytics.web.soap.v5.LogonParameter[] logonParams) {
        this.logonParams = logonParams;
    }

    public com.siebel.analytics.web.soap.v5.LogonParameter getLogonParams(int i) {
        return this.logonParams[i];
    }

    public void setLogonParams(int i, com.siebel.analytics.web.soap.v5.LogonParameter _value) {
        this.logonParams[i] = _value;
    }


    /**
     * Gets the sessionID value for this SAWSessionParameters.
     * 
     * @return sessionID
     */
    public java.lang.String getSessionID() {
        return sessionID;
    }


    /**
     * Sets the sessionID value for this SAWSessionParameters.
     * 
     * @param sessionID
     */
    public void setSessionID(java.lang.String sessionID) {
        this.sessionID = sessionID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SAWSessionParameters)) return false;
        SAWSessionParameters other = (SAWSessionParameters) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.userAgent==null && other.getUserAgent()==null) || 
             (this.userAgent!=null &&
              this.userAgent.equals(other.getUserAgent()))) &&
            ((this.features==null && other.getFeatures()==null) || 
             (this.features!=null &&
              this.features.equals(other.getFeatures()))) &&
            this.asyncLogon == other.isAsyncLogon() &&
            ((this.logonParams==null && other.getLogonParams()==null) || 
             (this.logonParams!=null &&
              java.util.Arrays.equals(this.logonParams, other.getLogonParams()))) &&
            ((this.sessionID==null && other.getSessionID()==null) || 
             (this.sessionID!=null &&
              this.sessionID.equals(other.getSessionID())));
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
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getUserAgent() != null) {
            _hashCode += getUserAgent().hashCode();
        }
        if (getFeatures() != null) {
            _hashCode += getFeatures().hashCode();
        }
        _hashCode += (isAsyncLogon() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLogonParams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLogonParams());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLogonParams(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSessionID() != null) {
            _hashCode += getSessionID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SAWSessionParameters.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SAWSessionParameters"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "SAWLocale"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userAgent");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "userAgent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("features");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "features"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asyncLogon");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "asyncLogon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logonParams");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "logonParams"));
        elemField.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "LogonParameter"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionID");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
