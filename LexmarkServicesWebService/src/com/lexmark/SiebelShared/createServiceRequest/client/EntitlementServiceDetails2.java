/**
 * EntitlementServiceDetails2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class EntitlementServiceDetails2  implements java.io.Serializable {
    private java.lang.String serviceDetailId;

    private java.lang.String serviceDetailsDescription;

    private java.lang.String primaryFlag;

    public EntitlementServiceDetails2() {
    }

    public EntitlementServiceDetails2(
           java.lang.String serviceDetailId,
           java.lang.String serviceDetailsDescription,
           java.lang.String primaryFlag) {
           this.serviceDetailId = serviceDetailId;
           this.serviceDetailsDescription = serviceDetailsDescription;
           this.primaryFlag = primaryFlag;
    }


    /**
     * Gets the serviceDetailId value for this EntitlementServiceDetails2.
     * 
     * @return serviceDetailId
     */
    public java.lang.String getServiceDetailId() {
        return serviceDetailId;
    }


    /**
     * Sets the serviceDetailId value for this EntitlementServiceDetails2.
     * 
     * @param serviceDetailId
     */
    public void setServiceDetailId(java.lang.String serviceDetailId) {
        this.serviceDetailId = serviceDetailId;
    }


    /**
     * Gets the serviceDetailsDescription value for this EntitlementServiceDetails2.
     * 
     * @return serviceDetailsDescription
     */
    public java.lang.String getServiceDetailsDescription() {
        return serviceDetailsDescription;
    }


    /**
     * Sets the serviceDetailsDescription value for this EntitlementServiceDetails2.
     * 
     * @param serviceDetailsDescription
     */
    public void setServiceDetailsDescription(java.lang.String serviceDetailsDescription) {
        this.serviceDetailsDescription = serviceDetailsDescription;
    }


    /**
     * Gets the primaryFlag value for this EntitlementServiceDetails2.
     * 
     * @return primaryFlag
     */
    public java.lang.String getPrimaryFlag() {
        return primaryFlag;
    }


    /**
     * Sets the primaryFlag value for this EntitlementServiceDetails2.
     * 
     * @param primaryFlag
     */
    public void setPrimaryFlag(java.lang.String primaryFlag) {
        this.primaryFlag = primaryFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EntitlementServiceDetails2)) return false;
        EntitlementServiceDetails2 other = (EntitlementServiceDetails2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceDetailId==null && other.getServiceDetailId()==null) || 
             (this.serviceDetailId!=null &&
              this.serviceDetailId.equals(other.getServiceDetailId()))) &&
            ((this.serviceDetailsDescription==null && other.getServiceDetailsDescription()==null) || 
             (this.serviceDetailsDescription!=null &&
              this.serviceDetailsDescription.equals(other.getServiceDetailsDescription()))) &&
            ((this.primaryFlag==null && other.getPrimaryFlag()==null) || 
             (this.primaryFlag!=null &&
              this.primaryFlag.equals(other.getPrimaryFlag())));
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
        if (getServiceDetailId() != null) {
            _hashCode += getServiceDetailId().hashCode();
        }
        if (getServiceDetailsDescription() != null) {
            _hashCode += getServiceDetailsDescription().hashCode();
        }
        if (getPrimaryFlag() != null) {
            _hashCode += getPrimaryFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EntitlementServiceDetails2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "EntitlementServiceDetails2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceDetailId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceDetailId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceDetailsDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceDetailsDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrimaryFlag"));
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
