/**
 * CreateClaimsServiceRequestInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public class CreateClaimsServiceRequestInput  implements java.io.Serializable {
    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestWSInput createClaimsServiceRequestWSInput;

    public CreateClaimsServiceRequestInput() {
    }

    public CreateClaimsServiceRequestInput(
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestWSInput createClaimsServiceRequestWSInput) {
           this.createClaimsServiceRequestWSInput = createClaimsServiceRequestWSInput;
    }


    /**
     * Gets the createClaimsServiceRequestWSInput value for this CreateClaimsServiceRequestInput.
     * 
     * @return createClaimsServiceRequestWSInput
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestWSInput getCreateClaimsServiceRequestWSInput() {
        return createClaimsServiceRequestWSInput;
    }


    /**
     * Sets the createClaimsServiceRequestWSInput value for this CreateClaimsServiceRequestInput.
     * 
     * @param createClaimsServiceRequestWSInput
     */
    public void setCreateClaimsServiceRequestWSInput(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.CreateClaimsServiceRequestWSInput createClaimsServiceRequestWSInput) {
        this.createClaimsServiceRequestWSInput = createClaimsServiceRequestWSInput;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateClaimsServiceRequestInput)) return false;
        CreateClaimsServiceRequestInput other = (CreateClaimsServiceRequestInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.createClaimsServiceRequestWSInput==null && other.getCreateClaimsServiceRequestWSInput()==null) || 
             (this.createClaimsServiceRequestWSInput!=null &&
              this.createClaimsServiceRequestWSInput.equals(other.getCreateClaimsServiceRequestWSInput())));
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
        if (getCreateClaimsServiceRequestWSInput() != null) {
            _hashCode += getCreateClaimsServiceRequestWSInput().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateClaimsServiceRequestInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "createClaimsServiceRequestInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createClaimsServiceRequestWSInput");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createClaimsServiceRequestWSInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "createClaimsServiceRequestWSInput"));
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
