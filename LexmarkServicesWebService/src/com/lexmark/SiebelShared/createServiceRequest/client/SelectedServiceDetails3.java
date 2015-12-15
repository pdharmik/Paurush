/**
 * SelectedServiceDetails3.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class SelectedServiceDetails3  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.Entitlement6 entitlement;

    public SelectedServiceDetails3() {
    }

    public SelectedServiceDetails3(
           com.lexmark.SiebelShared.createServiceRequest.client.Entitlement6 entitlement) {
           this.entitlement = entitlement;
    }


    /**
     * Gets the entitlement value for this SelectedServiceDetails3.
     * 
     * @return entitlement
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.Entitlement6 getEntitlement() {
        return entitlement;
    }


    /**
     * Sets the entitlement value for this SelectedServiceDetails3.
     * 
     * @param entitlement
     */
    public void setEntitlement(com.lexmark.SiebelShared.createServiceRequest.client.Entitlement6 entitlement) {
        this.entitlement = entitlement;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectedServiceDetails3)) return false;
        SelectedServiceDetails3 other = (SelectedServiceDetails3) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entitlement==null && other.getEntitlement()==null) || 
             (this.entitlement!=null &&
              this.entitlement.equals(other.getEntitlement())));
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
        if (getEntitlement() != null) {
            _hashCode += getEntitlement().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SelectedServiceDetails3.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails3"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entitlement");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Entitlement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement6"));
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
