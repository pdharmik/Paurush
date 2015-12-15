/**
 * GetServiceRequestStatusInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class GetServiceRequestStatusInput  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusWSInput getServiceRequestStatusWSInput;

    public GetServiceRequestStatusInput() {
    }

    public GetServiceRequestStatusInput(
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusWSInput getServiceRequestStatusWSInput) {
           this.getServiceRequestStatusWSInput = getServiceRequestStatusWSInput;
    }


    /**
     * Gets the getServiceRequestStatusWSInput value for this GetServiceRequestStatusInput.
     * 
     * @return getServiceRequestStatusWSInput
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusWSInput getGetServiceRequestStatusWSInput() {
        return getServiceRequestStatusWSInput;
    }


    /**
     * Sets the getServiceRequestStatusWSInput value for this GetServiceRequestStatusInput.
     * 
     * @param getServiceRequestStatusWSInput
     */
    public void setGetServiceRequestStatusWSInput(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.GetServiceRequestStatusWSInput getServiceRequestStatusWSInput) {
        this.getServiceRequestStatusWSInput = getServiceRequestStatusWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetServiceRequestStatusInput)) return false;
        GetServiceRequestStatusInput other = (GetServiceRequestStatusInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getServiceRequestStatusWSInput==null && other.getGetServiceRequestStatusWSInput()==null) || 
             (this.getServiceRequestStatusWSInput!=null &&
              this.getServiceRequestStatusWSInput.equals(other.getGetServiceRequestStatusWSInput())));
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
        if (getGetServiceRequestStatusWSInput() != null) {
            _hashCode += getGetServiceRequestStatusWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetServiceRequestStatusInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getServiceRequestStatusWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "getServiceRequestStatusWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "getServiceRequestStatusWSInput"));
        elemField.setNillable(true);
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
