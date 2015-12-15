/**
 * RelevantGeographies.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.processCustomerContact.client;

public class RelevantGeographies  implements java.io.Serializable {
    private com.lexmark.webServices.processCustomerContact.client.Americas americas;

    private com.lexmark.webServices.processCustomerContact.client.EMEA EMEA;

    private com.lexmark.webServices.processCustomerContact.client.APG APG;

    public RelevantGeographies() {
    }

    public RelevantGeographies(
           com.lexmark.webServices.processCustomerContact.client.Americas americas,
           com.lexmark.webServices.processCustomerContact.client.EMEA EMEA,
           com.lexmark.webServices.processCustomerContact.client.APG APG) {
           this.americas = americas;
           this.EMEA = EMEA;
           this.APG = APG;
    }


    /**
     * Gets the americas value for this RelevantGeographies.
     * 
     * @return americas
     */
    public com.lexmark.webServices.processCustomerContact.client.Americas getAmericas() {
        return americas;
    }


    /**
     * Sets the americas value for this RelevantGeographies.
     * 
     * @param americas
     */
    public void setAmericas(com.lexmark.webServices.processCustomerContact.client.Americas americas) {
        this.americas = americas;
    }


    /**
     * Gets the EMEA value for this RelevantGeographies.
     * 
     * @return EMEA
     */
    public com.lexmark.webServices.processCustomerContact.client.EMEA getEMEA() {
        return EMEA;
    }


    /**
     * Sets the EMEA value for this RelevantGeographies.
     * 
     * @param EMEA
     */
    public void setEMEA(com.lexmark.webServices.processCustomerContact.client.EMEA EMEA) {
        this.EMEA = EMEA;
    }


    /**
     * Gets the APG value for this RelevantGeographies.
     * 
     * @return APG
     */
    public com.lexmark.webServices.processCustomerContact.client.APG getAPG() {
        return APG;
    }


    /**
     * Sets the APG value for this RelevantGeographies.
     * 
     * @param APG
     */
    public void setAPG(com.lexmark.webServices.processCustomerContact.client.APG APG) {
        this.APG = APG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RelevantGeographies)) return false;
        RelevantGeographies other = (RelevantGeographies) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.americas==null && other.getAmericas()==null) || 
             (this.americas!=null &&
              this.americas.equals(other.getAmericas()))) &&
            ((this.EMEA==null && other.getEMEA()==null) || 
             (this.EMEA!=null &&
              this.EMEA.equals(other.getEMEA()))) &&
            ((this.APG==null && other.getAPG()==null) || 
             (this.APG!=null &&
              this.APG.equals(other.getAPG())));
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
        if (getAmericas() != null) {
            _hashCode += getAmericas().hashCode();
        }
        if (getEMEA() != null) {
            _hashCode += getEMEA().hashCode();
        }
        if (getAPG() != null) {
            _hashCode += getAPG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RelevantGeographies.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "RelevantGeographies"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("americas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Americas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "Americas"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMEA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMEA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "EMEA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "APG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "APG"));
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
