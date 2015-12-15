/**
 * ZGGET_AP_DATA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AR.serviceRequests.client;

public class ZGGET_AP_DATA  implements java.io.Serializable {
    private java.lang.String SRCDOCNO;

    private java.math.BigDecimal RWBTR;

    private java.lang.String WAERS;

    public ZGGET_AP_DATA() {
    }

    public ZGGET_AP_DATA(
           java.lang.String SRCDOCNO,
           java.math.BigDecimal RWBTR,
           java.lang.String WAERS) {
           this.SRCDOCNO = SRCDOCNO;
           this.RWBTR = RWBTR;
           this.WAERS = WAERS;
    }


    /**
     * Gets the SRCDOCNO value for this ZGGET_AP_DATA.
     * 
     * @return SRCDOCNO
     */
    public java.lang.String getSRCDOCNO() {
        return SRCDOCNO;
    }


    /**
     * Sets the SRCDOCNO value for this ZGGET_AP_DATA.
     * 
     * @param SRCDOCNO
     */
    public void setSRCDOCNO(java.lang.String SRCDOCNO) {
        this.SRCDOCNO = SRCDOCNO;
    }


    /**
     * Gets the RWBTR value for this ZGGET_AP_DATA.
     * 
     * @return RWBTR
     */
    public java.math.BigDecimal getRWBTR() {
        return RWBTR;
    }


    /**
     * Sets the RWBTR value for this ZGGET_AP_DATA.
     * 
     * @param RWBTR
     */
    public void setRWBTR(java.math.BigDecimal RWBTR) {
        this.RWBTR = RWBTR;
    }


    /**
     * Gets the WAERS value for this ZGGET_AP_DATA.
     * 
     * @return WAERS
     */
    public java.lang.String getWAERS() {
        return WAERS;
    }


    /**
     * Sets the WAERS value for this ZGGET_AP_DATA.
     * 
     * @param WAERS
     */
    public void setWAERS(java.lang.String WAERS) {
        this.WAERS = WAERS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ZGGET_AP_DATA)) return false;
        ZGGET_AP_DATA other = (ZGGET_AP_DATA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SRCDOCNO==null && other.getSRCDOCNO()==null) || 
             (this.SRCDOCNO!=null &&
              this.SRCDOCNO.equals(other.getSRCDOCNO()))) &&
            ((this.RWBTR==null && other.getRWBTR()==null) || 
             (this.RWBTR!=null &&
              this.RWBTR.equals(other.getRWBTR()))) &&
            ((this.WAERS==null && other.getWAERS()==null) || 
             (this.WAERS!=null &&
              this.WAERS.equals(other.getWAERS())));
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
        if (getSRCDOCNO() != null) {
            _hashCode += getSRCDOCNO().hashCode();
        }
        if (getRWBTR() != null) {
            _hashCode += getRWBTR().hashCode();
        }
        if (getWAERS() != null) {
            _hashCode += getWAERS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZGGET_AP_DATA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "ZGGET_AP_DATA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SRCDOCNO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SRCDOCNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RWBTR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RWBTR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("WAERS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WAERS"));
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
