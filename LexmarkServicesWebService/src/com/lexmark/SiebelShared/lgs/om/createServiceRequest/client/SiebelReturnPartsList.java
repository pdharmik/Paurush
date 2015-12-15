/**
 * SiebelReturnPartsList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class SiebelReturnPartsList  implements java.io.Serializable {
    private java.lang.String lineItemId;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.PartInfromation partInfromation;

    private java.lang.String carrier;

    private java.lang.String returnTrackingNumber;

    private java.lang.String returnPartUpdatedFlag;

    public SiebelReturnPartsList() {
    }

    public SiebelReturnPartsList(
           java.lang.String lineItemId,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.PartInfromation partInfromation,
           java.lang.String carrier,
           java.lang.String returnTrackingNumber,
           java.lang.String returnPartUpdatedFlag) {
           this.lineItemId = lineItemId;
           this.partInfromation = partInfromation;
           this.carrier = carrier;
           this.returnTrackingNumber = returnTrackingNumber;
           this.returnPartUpdatedFlag = returnPartUpdatedFlag;
    }


    /**
     * Gets the lineItemId value for this SiebelReturnPartsList.
     * 
     * @return lineItemId
     */
    public java.lang.String getLineItemId() {
        return lineItemId;
    }


    /**
     * Sets the lineItemId value for this SiebelReturnPartsList.
     * 
     * @param lineItemId
     */
    public void setLineItemId(java.lang.String lineItemId) {
        this.lineItemId = lineItemId;
    }


    /**
     * Gets the partInfromation value for this SiebelReturnPartsList.
     * 
     * @return partInfromation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.PartInfromation getPartInfromation() {
        return partInfromation;
    }


    /**
     * Sets the partInfromation value for this SiebelReturnPartsList.
     * 
     * @param partInfromation
     */
    public void setPartInfromation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.PartInfromation partInfromation) {
        this.partInfromation = partInfromation;
    }


    /**
     * Gets the carrier value for this SiebelReturnPartsList.
     * 
     * @return carrier
     */
    public java.lang.String getCarrier() {
        return carrier;
    }


    /**
     * Sets the carrier value for this SiebelReturnPartsList.
     * 
     * @param carrier
     */
    public void setCarrier(java.lang.String carrier) {
        this.carrier = carrier;
    }


    /**
     * Gets the returnTrackingNumber value for this SiebelReturnPartsList.
     * 
     * @return returnTrackingNumber
     */
    public java.lang.String getReturnTrackingNumber() {
        return returnTrackingNumber;
    }


    /**
     * Sets the returnTrackingNumber value for this SiebelReturnPartsList.
     * 
     * @param returnTrackingNumber
     */
    public void setReturnTrackingNumber(java.lang.String returnTrackingNumber) {
        this.returnTrackingNumber = returnTrackingNumber;
    }


    /**
     * Gets the returnPartUpdatedFlag value for this SiebelReturnPartsList.
     * 
     * @return returnPartUpdatedFlag
     */
    public java.lang.String getReturnPartUpdatedFlag() {
        return returnPartUpdatedFlag;
    }


    /**
     * Sets the returnPartUpdatedFlag value for this SiebelReturnPartsList.
     * 
     * @param returnPartUpdatedFlag
     */
    public void setReturnPartUpdatedFlag(java.lang.String returnPartUpdatedFlag) {
        this.returnPartUpdatedFlag = returnPartUpdatedFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelReturnPartsList)) return false;
        SiebelReturnPartsList other = (SiebelReturnPartsList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lineItemId==null && other.getLineItemId()==null) || 
             (this.lineItemId!=null &&
              this.lineItemId.equals(other.getLineItemId()))) &&
            ((this.partInfromation==null && other.getPartInfromation()==null) || 
             (this.partInfromation!=null &&
              this.partInfromation.equals(other.getPartInfromation()))) &&
            ((this.carrier==null && other.getCarrier()==null) || 
             (this.carrier!=null &&
              this.carrier.equals(other.getCarrier()))) &&
            ((this.returnTrackingNumber==null && other.getReturnTrackingNumber()==null) || 
             (this.returnTrackingNumber!=null &&
              this.returnTrackingNumber.equals(other.getReturnTrackingNumber()))) &&
            ((this.returnPartUpdatedFlag==null && other.getReturnPartUpdatedFlag()==null) || 
             (this.returnPartUpdatedFlag!=null &&
              this.returnPartUpdatedFlag.equals(other.getReturnPartUpdatedFlag())));
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
        if (getLineItemId() != null) {
            _hashCode += getLineItemId().hashCode();
        }
        if (getPartInfromation() != null) {
            _hashCode += getPartInfromation().hashCode();
        }
        if (getCarrier() != null) {
            _hashCode += getCarrier().hashCode();
        }
        if (getReturnTrackingNumber() != null) {
            _hashCode += getReturnTrackingNumber().hashCode();
        }
        if (getReturnPartUpdatedFlag() != null) {
            _hashCode += getReturnPartUpdatedFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelReturnPartsList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelReturnPartsList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineItemId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LineItemId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partInfromation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartInfromation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PartInfromation"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carrier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Carrier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnTrackingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnTrackingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnPartUpdatedFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnPartUpdatedFlag"));
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
