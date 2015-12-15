/**
 * WriteDashboardPrompt.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public class WriteDashboardPrompt  implements java.io.Serializable {
    private com.siebel.analytics.web.soap.v5.CatalogObject obj;

    private java.lang.String path;

    private boolean resolveLinks;

    private boolean allowOverwrite;

    private java.lang.String sessionID;

    public WriteDashboardPrompt() {
    }

    public WriteDashboardPrompt(
           com.siebel.analytics.web.soap.v5.CatalogObject obj,
           java.lang.String path,
           boolean resolveLinks,
           boolean allowOverwrite,
           java.lang.String sessionID) {
           this.obj = obj;
           this.path = path;
           this.resolveLinks = resolveLinks;
           this.allowOverwrite = allowOverwrite;
           this.sessionID = sessionID;
    }


    /**
     * Gets the obj value for this WriteDashboardPrompt.
     * 
     * @return obj
     */
    public com.siebel.analytics.web.soap.v5.CatalogObject getObj() {
        return obj;
    }


    /**
     * Sets the obj value for this WriteDashboardPrompt.
     * 
     * @param obj
     */
    public void setObj(com.siebel.analytics.web.soap.v5.CatalogObject obj) {
        this.obj = obj;
    }


    /**
     * Gets the path value for this WriteDashboardPrompt.
     * 
     * @return path
     */
    public java.lang.String getPath() {
        return path;
    }


    /**
     * Sets the path value for this WriteDashboardPrompt.
     * 
     * @param path
     */
    public void setPath(java.lang.String path) {
        this.path = path;
    }


    /**
     * Gets the resolveLinks value for this WriteDashboardPrompt.
     * 
     * @return resolveLinks
     */
    public boolean isResolveLinks() {
        return resolveLinks;
    }


    /**
     * Sets the resolveLinks value for this WriteDashboardPrompt.
     * 
     * @param resolveLinks
     */
    public void setResolveLinks(boolean resolveLinks) {
        this.resolveLinks = resolveLinks;
    }


    /**
     * Gets the allowOverwrite value for this WriteDashboardPrompt.
     * 
     * @return allowOverwrite
     */
    public boolean isAllowOverwrite() {
        return allowOverwrite;
    }


    /**
     * Sets the allowOverwrite value for this WriteDashboardPrompt.
     * 
     * @param allowOverwrite
     */
    public void setAllowOverwrite(boolean allowOverwrite) {
        this.allowOverwrite = allowOverwrite;
    }


    /**
     * Gets the sessionID value for this WriteDashboardPrompt.
     * 
     * @return sessionID
     */
    public java.lang.String getSessionID() {
        return sessionID;
    }


    /**
     * Sets the sessionID value for this WriteDashboardPrompt.
     * 
     * @param sessionID
     */
    public void setSessionID(java.lang.String sessionID) {
        this.sessionID = sessionID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WriteDashboardPrompt)) return false;
        WriteDashboardPrompt other = (WriteDashboardPrompt) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.obj==null && other.getObj()==null) || 
             (this.obj!=null &&
              this.obj.equals(other.getObj()))) &&
            ((this.path==null && other.getPath()==null) || 
             (this.path!=null &&
              this.path.equals(other.getPath()))) &&
            this.resolveLinks == other.isResolveLinks() &&
            this.allowOverwrite == other.isAllowOverwrite() &&
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
        if (getObj() != null) {
            _hashCode += getObj().hashCode();
        }
        if (getPath() != null) {
            _hashCode += getPath().hashCode();
        }
        _hashCode += (isResolveLinks() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAllowOverwrite() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSessionID() != null) {
            _hashCode += getSessionID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WriteDashboardPrompt.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">writeDashboardPrompt"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("obj");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "obj"));
        elemField.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "CatalogObject"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("path");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "path"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resolveLinks");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "resolveLinks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowOverwrite");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "allowOverwrite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionID");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "sessionID"));
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
