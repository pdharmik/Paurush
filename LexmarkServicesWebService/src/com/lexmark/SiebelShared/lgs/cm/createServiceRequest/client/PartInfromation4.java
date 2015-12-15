/**
 * PartInfromation4.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class PartInfromation4  implements java.io.Serializable {
    private java.lang.String partNumber;

    private java.lang.String partId;

    public PartInfromation4() {
    }

    public PartInfromation4(
           java.lang.String partNumber,
           java.lang.String partId) {
           this.partNumber = partNumber;
           this.partId = partId;
    }


    /**
     * Gets the partNumber value for this PartInfromation4.
     * 
     * @return partNumber
     */
    public java.lang.String getPartNumber() {
        return partNumber;
    }


    /**
     * Sets the partNumber value for this PartInfromation4.
     * 
     * @param partNumber
     */
    public void setPartNumber(java.lang.String partNumber) {
        this.partNumber = partNumber;
    }


    /**
     * Gets the partId value for this PartInfromation4.
     * 
     * @return partId
     */
    public java.lang.String getPartId() {
        return partId;
    }


    /**
     * Sets the partId value for this PartInfromation4.
     * 
     * @param partId
     */
    public void setPartId(java.lang.String partId) {
        this.partId = partId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PartInfromation4)) return false;
        PartInfromation4 other = (PartInfromation4) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.partNumber==null && other.getPartNumber()==null) || 
             (this.partNumber!=null &&
              this.partNumber.equals(other.getPartNumber()))) &&
            ((this.partId==null && other.getPartId()==null) || 
             (this.partId!=null &&
              this.partId.equals(other.getPartId())));
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
        if (getPartNumber() != null) {
            _hashCode += getPartNumber().hashCode();
        }
        if (getPartId() != null) {
            _hashCode += getPartId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PartInfromation4.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PartInfromation4"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartId"));
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
