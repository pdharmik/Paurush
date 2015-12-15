/**
 * SiebelPaymentDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class SiebelPaymentDetails  implements java.io.Serializable {
    private com.lexmark.SiebelShared.createServiceRequest.client.PaymentDetails paymentDetails;

    public SiebelPaymentDetails() {
    }

    public SiebelPaymentDetails(
           com.lexmark.SiebelShared.createServiceRequest.client.PaymentDetails paymentDetails) {
           this.paymentDetails = paymentDetails;
    }


    /**
     * Gets the paymentDetails value for this SiebelPaymentDetails.
     * 
     * @return paymentDetails
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }


    /**
     * Sets the paymentDetails value for this SiebelPaymentDetails.
     * 
     * @param paymentDetails
     */
    public void setPaymentDetails(com.lexmark.SiebelShared.createServiceRequest.client.PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelPaymentDetails)) return false;
        SiebelPaymentDetails other = (SiebelPaymentDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paymentDetails==null && other.getPaymentDetails()==null) || 
             (this.paymentDetails!=null &&
              this.paymentDetails.equals(other.getPaymentDetails())));
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
        if (getPaymentDetails() != null) {
            _hashCode += getPaymentDetails().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelPaymentDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelPaymentDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaymentDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PaymentDetails"));
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
