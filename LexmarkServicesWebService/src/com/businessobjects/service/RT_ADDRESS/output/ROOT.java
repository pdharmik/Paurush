/**
 * ROOT.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.service.RT_ADDRESS.output;

public class ROOT  implements java.io.Serializable {
    private java.lang.String STREET1;

    private java.lang.String STREET2;

    private java.lang.String CITY;

    private java.lang.String REGION;

    private java.lang.String POSTCODE;

    private java.lang.String COUNTRY;

    private java.lang.String DESC;

    public ROOT() {
    }

    public ROOT(
           java.lang.String STREET1,
           java.lang.String STREET2,
           java.lang.String CITY,
           java.lang.String REGION,
           java.lang.String POSTCODE,
           java.lang.String COUNTRY,
           java.lang.String DESC) {
           this.STREET1 = STREET1;
           this.STREET2 = STREET2;
           this.CITY = CITY;
           this.REGION = REGION;
           this.POSTCODE = POSTCODE;
           this.COUNTRY = COUNTRY;
           this.DESC = DESC;
    }


    /**
     * Gets the STREET1 value for this ROOT.
     * 
     * @return STREET1
     */
    public java.lang.String getSTREET1() {
        return STREET1;
    }


    /**
     * Sets the STREET1 value for this ROOT.
     * 
     * @param STREET1
     */
    public void setSTREET1(java.lang.String STREET1) {
        this.STREET1 = STREET1;
    }


    /**
     * Gets the STREET2 value for this ROOT.
     * 
     * @return STREET2
     */
    public java.lang.String getSTREET2() {
        return STREET2;
    }


    /**
     * Sets the STREET2 value for this ROOT.
     * 
     * @param STREET2
     */
    public void setSTREET2(java.lang.String STREET2) {
        this.STREET2 = STREET2;
    }


    /**
     * Gets the CITY value for this ROOT.
     * 
     * @return CITY
     */
    public java.lang.String getCITY() {
        return CITY;
    }


    /**
     * Sets the CITY value for this ROOT.
     * 
     * @param CITY
     */
    public void setCITY(java.lang.String CITY) {
        this.CITY = CITY;
    }


    /**
     * Gets the REGION value for this ROOT.
     * 
     * @return REGION
     */
    public java.lang.String getREGION() {
        return REGION;
    }


    /**
     * Sets the REGION value for this ROOT.
     * 
     * @param REGION
     */
    public void setREGION(java.lang.String REGION) {
        this.REGION = REGION;
    }


    /**
     * Gets the POSTCODE value for this ROOT.
     * 
     * @return POSTCODE
     */
    public java.lang.String getPOSTCODE() {
        return POSTCODE;
    }


    /**
     * Sets the POSTCODE value for this ROOT.
     * 
     * @param POSTCODE
     */
    public void setPOSTCODE(java.lang.String POSTCODE) {
        this.POSTCODE = POSTCODE;
    }


    /**
     * Gets the COUNTRY value for this ROOT.
     * 
     * @return COUNTRY
     */
    public java.lang.String getCOUNTRY() {
        return COUNTRY;
    }


    /**
     * Sets the COUNTRY value for this ROOT.
     * 
     * @param COUNTRY
     */
    public void setCOUNTRY(java.lang.String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }


    /**
     * Gets the DESC value for this ROOT.
     * 
     * @return DESC
     */
    public java.lang.String getDESC() {
        return DESC;
    }


    /**
     * Sets the DESC value for this ROOT.
     * 
     * @param DESC
     */
    public void setDESC(java.lang.String DESC) {
        this.DESC = DESC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ROOT)) return false;
        ROOT other = (ROOT) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.STREET1==null && other.getSTREET1()==null) || 
             (this.STREET1!=null &&
              this.STREET1.equals(other.getSTREET1()))) &&
            ((this.STREET2==null && other.getSTREET2()==null) || 
             (this.STREET2!=null &&
              this.STREET2.equals(other.getSTREET2()))) &&
            ((this.CITY==null && other.getCITY()==null) || 
             (this.CITY!=null &&
              this.CITY.equals(other.getCITY()))) &&
            ((this.REGION==null && other.getREGION()==null) || 
             (this.REGION!=null &&
              this.REGION.equals(other.getREGION()))) &&
            ((this.POSTCODE==null && other.getPOSTCODE()==null) || 
             (this.POSTCODE!=null &&
              this.POSTCODE.equals(other.getPOSTCODE()))) &&
            ((this.COUNTRY==null && other.getCOUNTRY()==null) || 
             (this.COUNTRY!=null &&
              this.COUNTRY.equals(other.getCOUNTRY()))) &&
            ((this.DESC==null && other.getDESC()==null) || 
             (this.DESC!=null &&
              this.DESC.equals(other.getDESC())));
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
        if (getSTREET1() != null) {
            _hashCode += getSTREET1().hashCode();
        }
        if (getSTREET2() != null) {
            _hashCode += getSTREET2().hashCode();
        }
        if (getCITY() != null) {
            _hashCode += getCITY().hashCode();
        }
        if (getREGION() != null) {
            _hashCode += getREGION().hashCode();
        }
        if (getPOSTCODE() != null) {
            _hashCode += getPOSTCODE().hashCode();
        }
        if (getCOUNTRY() != null) {
            _hashCode += getCOUNTRY().hashCode();
        }
        if (getDESC() != null) {
            _hashCode += getDESC().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ROOT.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", ">ROOT"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STREET1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "STREET1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "DIType-varchar-200"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STREET2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "STREET2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "DIType-varchar-200"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CITY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "CITY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "DIType-varchar-200"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REGION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "REGION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "DIType-varchar-200"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POSTCODE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "POSTCODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "DIType-varchar-200"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COUNTRY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "COUNTRY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "DIType-varchar-200"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "DESC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/RT_ADDRESS/output", "DIType-varchar-200"));
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
