/**
 * EBPP_PARTNER.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_PARTNER  implements java.io.Serializable {
    private java.lang.String PARTNERTYPE;

    private java.lang.String PARTNERKEY;

    private java.lang.String UNAME;

    private java.lang.String AGENT_ACTIVE;

    private java.lang.String CC_ACTIVE;

    private java.lang.String DESCRIP_LONG;

    public EBPP_PARTNER() {
    }

    public EBPP_PARTNER(
           java.lang.String PARTNERTYPE,
           java.lang.String PARTNERKEY,
           java.lang.String UNAME,
           java.lang.String AGENT_ACTIVE,
           java.lang.String CC_ACTIVE,
           java.lang.String DESCRIP_LONG) {
           this.PARTNERTYPE = PARTNERTYPE;
           this.PARTNERKEY = PARTNERKEY;
           this.UNAME = UNAME;
           this.AGENT_ACTIVE = AGENT_ACTIVE;
           this.CC_ACTIVE = CC_ACTIVE;
           this.DESCRIP_LONG = DESCRIP_LONG;
    }


    /**
     * Gets the PARTNERTYPE value for this EBPP_PARTNER.
     * 
     * @return PARTNERTYPE
     */
    public java.lang.String getPARTNERTYPE() {
        return PARTNERTYPE;
    }


    /**
     * Sets the PARTNERTYPE value for this EBPP_PARTNER.
     * 
     * @param PARTNERTYPE
     */
    public void setPARTNERTYPE(java.lang.String PARTNERTYPE) {
        this.PARTNERTYPE = PARTNERTYPE;
    }


    /**
     * Gets the PARTNERKEY value for this EBPP_PARTNER.
     * 
     * @return PARTNERKEY
     */
    public java.lang.String getPARTNERKEY() {
        return PARTNERKEY;
    }


    /**
     * Sets the PARTNERKEY value for this EBPP_PARTNER.
     * 
     * @param PARTNERKEY
     */
    public void setPARTNERKEY(java.lang.String PARTNERKEY) {
        this.PARTNERKEY = PARTNERKEY;
    }


    /**
     * Gets the UNAME value for this EBPP_PARTNER.
     * 
     * @return UNAME
     */
    public java.lang.String getUNAME() {
        return UNAME;
    }


    /**
     * Sets the UNAME value for this EBPP_PARTNER.
     * 
     * @param UNAME
     */
    public void setUNAME(java.lang.String UNAME) {
        this.UNAME = UNAME;
    }


    /**
     * Gets the AGENT_ACTIVE value for this EBPP_PARTNER.
     * 
     * @return AGENT_ACTIVE
     */
    public java.lang.String getAGENT_ACTIVE() {
        return AGENT_ACTIVE;
    }


    /**
     * Sets the AGENT_ACTIVE value for this EBPP_PARTNER.
     * 
     * @param AGENT_ACTIVE
     */
    public void setAGENT_ACTIVE(java.lang.String AGENT_ACTIVE) {
        this.AGENT_ACTIVE = AGENT_ACTIVE;
    }


    /**
     * Gets the CC_ACTIVE value for this EBPP_PARTNER.
     * 
     * @return CC_ACTIVE
     */
    public java.lang.String getCC_ACTIVE() {
        return CC_ACTIVE;
    }


    /**
     * Sets the CC_ACTIVE value for this EBPP_PARTNER.
     * 
     * @param CC_ACTIVE
     */
    public void setCC_ACTIVE(java.lang.String CC_ACTIVE) {
        this.CC_ACTIVE = CC_ACTIVE;
    }


    /**
     * Gets the DESCRIP_LONG value for this EBPP_PARTNER.
     * 
     * @return DESCRIP_LONG
     */
    public java.lang.String getDESCRIP_LONG() {
        return DESCRIP_LONG;
    }


    /**
     * Sets the DESCRIP_LONG value for this EBPP_PARTNER.
     * 
     * @param DESCRIP_LONG
     */
    public void setDESCRIP_LONG(java.lang.String DESCRIP_LONG) {
        this.DESCRIP_LONG = DESCRIP_LONG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_PARTNER)) return false;
        EBPP_PARTNER other = (EBPP_PARTNER) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PARTNERTYPE==null && other.getPARTNERTYPE()==null) || 
             (this.PARTNERTYPE!=null &&
              this.PARTNERTYPE.equals(other.getPARTNERTYPE()))) &&
            ((this.PARTNERKEY==null && other.getPARTNERKEY()==null) || 
             (this.PARTNERKEY!=null &&
              this.PARTNERKEY.equals(other.getPARTNERKEY()))) &&
            ((this.UNAME==null && other.getUNAME()==null) || 
             (this.UNAME!=null &&
              this.UNAME.equals(other.getUNAME()))) &&
            ((this.AGENT_ACTIVE==null && other.getAGENT_ACTIVE()==null) || 
             (this.AGENT_ACTIVE!=null &&
              this.AGENT_ACTIVE.equals(other.getAGENT_ACTIVE()))) &&
            ((this.CC_ACTIVE==null && other.getCC_ACTIVE()==null) || 
             (this.CC_ACTIVE!=null &&
              this.CC_ACTIVE.equals(other.getCC_ACTIVE()))) &&
            ((this.DESCRIP_LONG==null && other.getDESCRIP_LONG()==null) || 
             (this.DESCRIP_LONG!=null &&
              this.DESCRIP_LONG.equals(other.getDESCRIP_LONG())));
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
        if (getPARTNERTYPE() != null) {
            _hashCode += getPARTNERTYPE().hashCode();
        }
        if (getPARTNERKEY() != null) {
            _hashCode += getPARTNERKEY().hashCode();
        }
        if (getUNAME() != null) {
            _hashCode += getUNAME().hashCode();
        }
        if (getAGENT_ACTIVE() != null) {
            _hashCode += getAGENT_ACTIVE().hashCode();
        }
        if (getCC_ACTIVE() != null) {
            _hashCode += getCC_ACTIVE().hashCode();
        }
        if (getDESCRIP_LONG() != null) {
            _hashCode += getDESCRIP_LONG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_PARTNER.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_PARTNER"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PARTNERTYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PARTNERTYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PARTNERKEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PARTNERKEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UNAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UNAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGENT_ACTIVE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AGENT_ACTIVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CC_ACTIVE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CC_ACTIVE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIP_LONG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DESCRIP_LONG"));
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
