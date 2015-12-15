/**
 * FDM_EBPP_DMSELECTION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class FDM_EBPP_DMSELECTION  implements java.io.Serializable {
    private java.lang.String CLOSED;

    private java.lang.String OPEN;

    private java.lang.String FROM_DATE;

    public FDM_EBPP_DMSELECTION() {
    }

    public FDM_EBPP_DMSELECTION(
           java.lang.String CLOSED,
           java.lang.String OPEN,
           java.lang.String FROM_DATE) {
           this.CLOSED = CLOSED;
           this.OPEN = OPEN;
           this.FROM_DATE = FROM_DATE;
    }


    /**
     * Gets the CLOSED value for this FDM_EBPP_DMSELECTION.
     * 
     * @return CLOSED
     */
    public java.lang.String getCLOSED() {
        return CLOSED;
    }


    /**
     * Sets the CLOSED value for this FDM_EBPP_DMSELECTION.
     * 
     * @param CLOSED
     */
    public void setCLOSED(java.lang.String CLOSED) {
        this.CLOSED = CLOSED;
    }


    /**
     * Gets the OPEN value for this FDM_EBPP_DMSELECTION.
     * 
     * @return OPEN
     */
    public java.lang.String getOPEN() {
        return OPEN;
    }


    /**
     * Sets the OPEN value for this FDM_EBPP_DMSELECTION.
     * 
     * @param OPEN
     */
    public void setOPEN(java.lang.String OPEN) {
        this.OPEN = OPEN;
    }


    /**
     * Gets the FROM_DATE value for this FDM_EBPP_DMSELECTION.
     * 
     * @return FROM_DATE
     */
    public java.lang.String getFROM_DATE() {
        return FROM_DATE;
    }


    /**
     * Sets the FROM_DATE value for this FDM_EBPP_DMSELECTION.
     * 
     * @param FROM_DATE
     */
    public void setFROM_DATE(java.lang.String FROM_DATE) {
        this.FROM_DATE = FROM_DATE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FDM_EBPP_DMSELECTION)) return false;
        FDM_EBPP_DMSELECTION other = (FDM_EBPP_DMSELECTION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CLOSED==null && other.getCLOSED()==null) || 
             (this.CLOSED!=null &&
              this.CLOSED.equals(other.getCLOSED()))) &&
            ((this.OPEN==null && other.getOPEN()==null) || 
             (this.OPEN!=null &&
              this.OPEN.equals(other.getOPEN()))) &&
            ((this.FROM_DATE==null && other.getFROM_DATE()==null) || 
             (this.FROM_DATE!=null &&
              this.FROM_DATE.equals(other.getFROM_DATE())));
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
        if (getCLOSED() != null) {
            _hashCode += getCLOSED().hashCode();
        }
        if (getOPEN() != null) {
            _hashCode += getOPEN().hashCode();
        }
        if (getFROM_DATE() != null) {
            _hashCode += getFROM_DATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FDM_EBPP_DMSELECTION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "FDM_EBPP_DMSELECTION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLOSED");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLOSED"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPEN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FROM_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FROM_DATE"));
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
