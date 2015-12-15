/**
 * Entitlement2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class Entitlement2  implements java.io.Serializable {
    private java.lang.String entitlementId;

    private java.lang.String entitlementName;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EntitlementServiceDetails2 entitlementServiceDetails;

    public Entitlement2() {
    }

    public Entitlement2(
           java.lang.String entitlementId,
           java.lang.String entitlementName,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EntitlementServiceDetails2 entitlementServiceDetails) {
           this.entitlementId = entitlementId;
           this.entitlementName = entitlementName;
           this.entitlementServiceDetails = entitlementServiceDetails;
    }


    /**
     * Gets the entitlementId value for this Entitlement2.
     * 
     * @return entitlementId
     */
    public java.lang.String getEntitlementId() {
        return entitlementId;
    }


    /**
     * Sets the entitlementId value for this Entitlement2.
     * 
     * @param entitlementId
     */
    public void setEntitlementId(java.lang.String entitlementId) {
        this.entitlementId = entitlementId;
    }


    /**
     * Gets the entitlementName value for this Entitlement2.
     * 
     * @return entitlementName
     */
    public java.lang.String getEntitlementName() {
        return entitlementName;
    }


    /**
     * Sets the entitlementName value for this Entitlement2.
     * 
     * @param entitlementName
     */
    public void setEntitlementName(java.lang.String entitlementName) {
        this.entitlementName = entitlementName;
    }


    /**
     * Gets the entitlementServiceDetails value for this Entitlement2.
     * 
     * @return entitlementServiceDetails
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EntitlementServiceDetails2 getEntitlementServiceDetails() {
        return entitlementServiceDetails;
    }


    /**
     * Sets the entitlementServiceDetails value for this Entitlement2.
     * 
     * @param entitlementServiceDetails
     */
    public void setEntitlementServiceDetails(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.EntitlementServiceDetails2 entitlementServiceDetails) {
        this.entitlementServiceDetails = entitlementServiceDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Entitlement2)) return false;
        Entitlement2 other = (Entitlement2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entitlementId==null && other.getEntitlementId()==null) || 
             (this.entitlementId!=null &&
              this.entitlementId.equals(other.getEntitlementId()))) &&
            ((this.entitlementName==null && other.getEntitlementName()==null) || 
             (this.entitlementName!=null &&
              this.entitlementName.equals(other.getEntitlementName()))) &&
            ((this.entitlementServiceDetails==null && other.getEntitlementServiceDetails()==null) || 
             (this.entitlementServiceDetails!=null &&
              this.entitlementServiceDetails.equals(other.getEntitlementServiceDetails())));
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
        if (getEntitlementId() != null) {
            _hashCode += getEntitlementId().hashCode();
        }
        if (getEntitlementName() != null) {
            _hashCode += getEntitlementName().hashCode();
        }
        if (getEntitlementServiceDetails() != null) {
            _hashCode += getEntitlementServiceDetails().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Entitlement2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entitlementId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EntitlementId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entitlementName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EntitlementName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entitlementServiceDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EntitlementServiceDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails2"));
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
