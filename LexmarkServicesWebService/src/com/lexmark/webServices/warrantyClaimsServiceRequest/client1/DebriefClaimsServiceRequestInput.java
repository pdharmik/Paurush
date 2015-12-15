/**
 * DebriefClaimsServiceRequestInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public class DebriefClaimsServiceRequestInput  implements java.io.Serializable {
    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestWSInput debriefClaimsServiceRequestWSInput;

    public DebriefClaimsServiceRequestInput() {
    }

    public DebriefClaimsServiceRequestInput(
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestWSInput debriefClaimsServiceRequestWSInput) {
           this.debriefClaimsServiceRequestWSInput = debriefClaimsServiceRequestWSInput;
    }


    /**
     * Gets the debriefClaimsServiceRequestWSInput value for this DebriefClaimsServiceRequestInput.
     * 
     * @return debriefClaimsServiceRequestWSInput
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestWSInput getDebriefClaimsServiceRequestWSInput() {
        return debriefClaimsServiceRequestWSInput;
    }


    /**
     * Sets the debriefClaimsServiceRequestWSInput value for this DebriefClaimsServiceRequestInput.
     * 
     * @param debriefClaimsServiceRequestWSInput
     */
    public void setDebriefClaimsServiceRequestWSInput(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.DebriefClaimsServiceRequestWSInput debriefClaimsServiceRequestWSInput) {
        this.debriefClaimsServiceRequestWSInput = debriefClaimsServiceRequestWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DebriefClaimsServiceRequestInput)) return false;
        DebriefClaimsServiceRequestInput other = (DebriefClaimsServiceRequestInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.debriefClaimsServiceRequestWSInput==null && other.getDebriefClaimsServiceRequestWSInput()==null) || 
             (this.debriefClaimsServiceRequestWSInput!=null &&
              this.debriefClaimsServiceRequestWSInput.equals(other.getDebriefClaimsServiceRequestWSInput())));
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
        if (getDebriefClaimsServiceRequestWSInput() != null) {
            _hashCode += getDebriefClaimsServiceRequestWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DebriefClaimsServiceRequestInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "debriefClaimsServiceRequestInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debriefClaimsServiceRequestWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "debriefClaimsServiceRequestWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "debriefClaimsServiceRequestWSInput"));
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
