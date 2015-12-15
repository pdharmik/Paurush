/**
 * OrderInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class OrderInformation  implements java.io.Serializable {
    private java.lang.String expediteOrderFlag;

    private java.lang.String specialHandlingInstructions;

    private java.lang.String requestedDeliveryDate;

    public OrderInformation() {
    }

    public OrderInformation(
           java.lang.String expediteOrderFlag,
           java.lang.String specialHandlingInstructions,
           java.lang.String requestedDeliveryDate) {
           this.expediteOrderFlag = expediteOrderFlag;
           this.specialHandlingInstructions = specialHandlingInstructions;
           this.requestedDeliveryDate = requestedDeliveryDate;
    }


    /**
     * Gets the expediteOrderFlag value for this OrderInformation.
     * 
     * @return expediteOrderFlag
     */
    public java.lang.String getExpediteOrderFlag() {
        return expediteOrderFlag;
    }


    /**
     * Sets the expediteOrderFlag value for this OrderInformation.
     * 
     * @param expediteOrderFlag
     */
    public void setExpediteOrderFlag(java.lang.String expediteOrderFlag) {
        this.expediteOrderFlag = expediteOrderFlag;
    }


    /**
     * Gets the specialHandlingInstructions value for this OrderInformation.
     * 
     * @return specialHandlingInstructions
     */
    public java.lang.String getSpecialHandlingInstructions() {
        return specialHandlingInstructions;
    }


    /**
     * Sets the specialHandlingInstructions value for this OrderInformation.
     * 
     * @param specialHandlingInstructions
     */
    public void setSpecialHandlingInstructions(java.lang.String specialHandlingInstructions) {
        this.specialHandlingInstructions = specialHandlingInstructions;
    }


    /**
     * Gets the requestedDeliveryDate value for this OrderInformation.
     * 
     * @return requestedDeliveryDate
     */
    public java.lang.String getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }


    /**
     * Sets the requestedDeliveryDate value for this OrderInformation.
     * 
     * @param requestedDeliveryDate
     */
    public void setRequestedDeliveryDate(java.lang.String requestedDeliveryDate) {
        this.requestedDeliveryDate = requestedDeliveryDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrderInformation)) return false;
        OrderInformation other = (OrderInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.expediteOrderFlag==null && other.getExpediteOrderFlag()==null) || 
             (this.expediteOrderFlag!=null &&
              this.expediteOrderFlag.equals(other.getExpediteOrderFlag()))) &&
            ((this.specialHandlingInstructions==null && other.getSpecialHandlingInstructions()==null) || 
             (this.specialHandlingInstructions!=null &&
              this.specialHandlingInstructions.equals(other.getSpecialHandlingInstructions()))) &&
            ((this.requestedDeliveryDate==null && other.getRequestedDeliveryDate()==null) || 
             (this.requestedDeliveryDate!=null &&
              this.requestedDeliveryDate.equals(other.getRequestedDeliveryDate())));
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
        if (getExpediteOrderFlag() != null) {
            _hashCode += getExpediteOrderFlag().hashCode();
        }
        if (getSpecialHandlingInstructions() != null) {
            _hashCode += getSpecialHandlingInstructions().hashCode();
        }
        if (getRequestedDeliveryDate() != null) {
            _hashCode += getRequestedDeliveryDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrderInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OrderInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expediteOrderFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExpediteOrderFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("specialHandlingInstructions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SpecialHandlingInstructions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedDeliveryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestedDeliveryDate"));
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
