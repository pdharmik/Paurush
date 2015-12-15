/**
 * GetResults.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.siebel.analytics.web.soap.v5;

public class GetResults  implements java.io.Serializable {
    private com.siebel.analytics.web.soap.v5.ReportRef report;

    private java.lang.String outputFormat;

    private boolean encodeInString;

    private com.siebel.analytics.web.soap.v5.ReportParams reportParams;

    private java.lang.String sessionID;

    public GetResults() {
    }

    public GetResults(
           com.siebel.analytics.web.soap.v5.ReportRef report,
           java.lang.String outputFormat,
           boolean encodeInString,
           com.siebel.analytics.web.soap.v5.ReportParams reportParams,
           java.lang.String sessionID) {
           this.report = report;
           this.outputFormat = outputFormat;
           this.encodeInString = encodeInString;
           this.reportParams = reportParams;
           this.sessionID = sessionID;
    }


    /**
     * Gets the report value for this GetResults.
     * 
     * @return report
     */
    public com.siebel.analytics.web.soap.v5.ReportRef getReport() {
        return report;
    }


    /**
     * Sets the report value for this GetResults.
     * 
     * @param report
     */
    public void setReport(com.siebel.analytics.web.soap.v5.ReportRef report) {
        this.report = report;
    }


    /**
     * Gets the outputFormat value for this GetResults.
     * 
     * @return outputFormat
     */
    public java.lang.String getOutputFormat() {
        return outputFormat;
    }


    /**
     * Sets the outputFormat value for this GetResults.
     * 
     * @param outputFormat
     */
    public void setOutputFormat(java.lang.String outputFormat) {
        this.outputFormat = outputFormat;
    }


    /**
     * Gets the encodeInString value for this GetResults.
     * 
     * @return encodeInString
     */
    public boolean isEncodeInString() {
        return encodeInString;
    }


    /**
     * Sets the encodeInString value for this GetResults.
     * 
     * @param encodeInString
     */
    public void setEncodeInString(boolean encodeInString) {
        this.encodeInString = encodeInString;
    }


    /**
     * Gets the reportParams value for this GetResults.
     * 
     * @return reportParams
     */
    public com.siebel.analytics.web.soap.v5.ReportParams getReportParams() {
        return reportParams;
    }


    /**
     * Sets the reportParams value for this GetResults.
     * 
     * @param reportParams
     */
    public void setReportParams(com.siebel.analytics.web.soap.v5.ReportParams reportParams) {
        this.reportParams = reportParams;
    }


    /**
     * Gets the sessionID value for this GetResults.
     * 
     * @return sessionID
     */
    public java.lang.String getSessionID() {
        return sessionID;
    }


    /**
     * Sets the sessionID value for this GetResults.
     * 
     * @param sessionID
     */
    public void setSessionID(java.lang.String sessionID) {
        this.sessionID = sessionID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetResults)) return false;
        GetResults other = (GetResults) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.report==null && other.getReport()==null) || 
             (this.report!=null &&
              this.report.equals(other.getReport()))) &&
            ((this.outputFormat==null && other.getOutputFormat()==null) || 
             (this.outputFormat!=null &&
              this.outputFormat.equals(other.getOutputFormat()))) &&
            this.encodeInString == other.isEncodeInString() &&
            ((this.reportParams==null && other.getReportParams()==null) || 
             (this.reportParams!=null &&
              this.reportParams.equals(other.getReportParams()))) &&
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
        if (getReport() != null) {
            _hashCode += getReport().hashCode();
        }
        if (getOutputFormat() != null) {
            _hashCode += getOutputFormat().hashCode();
        }
        _hashCode += (isEncodeInString() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getReportParams() != null) {
            _hashCode += getReportParams().hashCode();
        }
        if (getSessionID() != null) {
            _hashCode += getSessionID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetResults.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", ">getResults"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("report");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "report"));
        elemField.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ReportRef"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outputFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "outputFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("encodeInString");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "encodeInString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportParams");
        elemField.setXmlName(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "reportParams"));
        elemField.setXmlType(new javax.xml.namespace.QName("com.siebel.analytics.web/soap/v5", "ReportParams"));
        elemField.setMinOccurs(0);
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
