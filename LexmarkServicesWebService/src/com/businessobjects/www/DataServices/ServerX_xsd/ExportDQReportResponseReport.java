/**
 * ExportDQReportResponseReport.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class ExportDQReportResponseReport  implements java.io.Serializable {
    private java.lang.String reportName;

    private java.lang.String exportFileName;

    private int reportStatus;

    private java.lang.String statusMessage;

    public ExportDQReportResponseReport() {
    }

    public ExportDQReportResponseReport(
           java.lang.String reportName,
           java.lang.String exportFileName,
           int reportStatus,
           java.lang.String statusMessage) {
           this.reportName = reportName;
           this.exportFileName = exportFileName;
           this.reportStatus = reportStatus;
           this.statusMessage = statusMessage;
    }


    /**
     * Gets the reportName value for this ExportDQReportResponseReport.
     * 
     * @return reportName
     */
    public java.lang.String getReportName() {
        return reportName;
    }


    /**
     * Sets the reportName value for this ExportDQReportResponseReport.
     * 
     * @param reportName
     */
    public void setReportName(java.lang.String reportName) {
        this.reportName = reportName;
    }


    /**
     * Gets the exportFileName value for this ExportDQReportResponseReport.
     * 
     * @return exportFileName
     */
    public java.lang.String getExportFileName() {
        return exportFileName;
    }


    /**
     * Sets the exportFileName value for this ExportDQReportResponseReport.
     * 
     * @param exportFileName
     */
    public void setExportFileName(java.lang.String exportFileName) {
        this.exportFileName = exportFileName;
    }


    /**
     * Gets the reportStatus value for this ExportDQReportResponseReport.
     * 
     * @return reportStatus
     */
    public int getReportStatus() {
        return reportStatus;
    }


    /**
     * Sets the reportStatus value for this ExportDQReportResponseReport.
     * 
     * @param reportStatus
     */
    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }


    /**
     * Gets the statusMessage value for this ExportDQReportResponseReport.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this ExportDQReportResponseReport.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExportDQReportResponseReport)) return false;
        ExportDQReportResponseReport other = (ExportDQReportResponseReport) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.reportName==null && other.getReportName()==null) || 
             (this.reportName!=null &&
              this.reportName.equals(other.getReportName()))) &&
            ((this.exportFileName==null && other.getExportFileName()==null) || 
             (this.exportFileName!=null &&
              this.exportFileName.equals(other.getExportFileName()))) &&
            this.reportStatus == other.getReportStatus() &&
            ((this.statusMessage==null && other.getStatusMessage()==null) || 
             (this.statusMessage!=null &&
              this.statusMessage.equals(other.getStatusMessage())));
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
        if (getReportName() != null) {
            _hashCode += getReportName().hashCode();
        }
        if (getExportFileName() != null) {
            _hashCode += getExportFileName().hashCode();
        }
        _hashCode += getReportStatus();
        if (getStatusMessage() != null) {
            _hashCode += getStatusMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExportDQReportResponseReport.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>ExportDQReportResponse>report"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reportName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exportFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reportStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statusMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
