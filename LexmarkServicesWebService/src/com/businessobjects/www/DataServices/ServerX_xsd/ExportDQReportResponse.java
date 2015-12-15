/**
 * ExportDQReportResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.www.DataServices.ServerX_xsd;

public class ExportDQReportResponse  implements java.io.Serializable {
    private boolean exportStatus;

    private java.lang.String exportPath;

    private java.lang.String[] processMessage;

    private com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponseReport[] report;

    public ExportDQReportResponse() {
    }

    public ExportDQReportResponse(
           boolean exportStatus,
           java.lang.String exportPath,
           java.lang.String[] processMessage,
           com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponseReport[] report) {
           this.exportStatus = exportStatus;
           this.exportPath = exportPath;
           this.processMessage = processMessage;
           this.report = report;
    }


    /**
     * Gets the exportStatus value for this ExportDQReportResponse.
     * 
     * @return exportStatus
     */
    public boolean isExportStatus() {
        return exportStatus;
    }


    /**
     * Sets the exportStatus value for this ExportDQReportResponse.
     * 
     * @param exportStatus
     */
    public void setExportStatus(boolean exportStatus) {
        this.exportStatus = exportStatus;
    }


    /**
     * Gets the exportPath value for this ExportDQReportResponse.
     * 
     * @return exportPath
     */
    public java.lang.String getExportPath() {
        return exportPath;
    }


    /**
     * Sets the exportPath value for this ExportDQReportResponse.
     * 
     * @param exportPath
     */
    public void setExportPath(java.lang.String exportPath) {
        this.exportPath = exportPath;
    }


    /**
     * Gets the processMessage value for this ExportDQReportResponse.
     * 
     * @return processMessage
     */
    public java.lang.String[] getProcessMessage() {
        return processMessage;
    }


    /**
     * Sets the processMessage value for this ExportDQReportResponse.
     * 
     * @param processMessage
     */
    public void setProcessMessage(java.lang.String[] processMessage) {
        this.processMessage = processMessage;
    }

    public java.lang.String getProcessMessage(int i) {
        return this.processMessage[i];
    }

    public void setProcessMessage(int i, java.lang.String _value) {
        this.processMessage[i] = _value;
    }


    /**
     * Gets the report value for this ExportDQReportResponse.
     * 
     * @return report
     */
    public com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponseReport[] getReport() {
        return report;
    }


    /**
     * Sets the report value for this ExportDQReportResponse.
     * 
     * @param report
     */
    public void setReport(com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponseReport[] report) {
        this.report = report;
    }

    public com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponseReport getReport(int i) {
        return this.report[i];
    }

    public void setReport(int i, com.businessobjects.www.DataServices.ServerX_xsd.ExportDQReportResponseReport _value) {
        this.report[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExportDQReportResponse)) return false;
        ExportDQReportResponse other = (ExportDQReportResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.exportStatus == other.isExportStatus() &&
            ((this.exportPath==null && other.getExportPath()==null) || 
             (this.exportPath!=null &&
              this.exportPath.equals(other.getExportPath()))) &&
            ((this.processMessage==null && other.getProcessMessage()==null) || 
             (this.processMessage!=null &&
              java.util.Arrays.equals(this.processMessage, other.getProcessMessage()))) &&
            ((this.report==null && other.getReport()==null) || 
             (this.report!=null &&
              java.util.Arrays.equals(this.report, other.getReport())));
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
        _hashCode += (isExportStatus() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getExportPath() != null) {
            _hashCode += getExportPath().hashCode();
        }
        if (getProcessMessage() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProcessMessage());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProcessMessage(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReport() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReport());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReport(), i);
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
        new org.apache.axis.description.TypeDesc(ExportDQReportResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">ExportDQReportResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exportStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportPath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exportPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "processMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("report");
        elemField.setXmlName(new javax.xml.namespace.QName("", "report"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.businessobjects.com/DataServices/ServerX.xsd", ">>ExportDQReportResponse>report"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
