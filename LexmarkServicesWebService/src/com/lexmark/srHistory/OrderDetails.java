/**
 * OrderDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class OrderDetails  implements java.io.Serializable {
    private java.lang.String PONumber;

    private java.lang.String expediteOrderFlag;

    private java.lang.String specialInstructions;

    private java.lang.String deliveryDate;

    private java.lang.String trackingNumber;

    private com.lexmark.srHistory.Address shipToAddress;

    public OrderDetails() {
    }

    public OrderDetails(
           java.lang.String PONumber,
           java.lang.String expediteOrderFlag,
           java.lang.String specialInstructions,
           java.lang.String deliveryDate,
           java.lang.String trackingNumber,
           com.lexmark.srHistory.Address shipToAddress) {
           this.PONumber = PONumber;
           this.expediteOrderFlag = expediteOrderFlag;
           this.specialInstructions = specialInstructions;
           this.deliveryDate = deliveryDate;
           this.trackingNumber = trackingNumber;
           this.shipToAddress = shipToAddress;
    }


    /**
     * Gets the PONumber value for this OrderDetails.
     * 
     * @return PONumber
     */
    public java.lang.String getPONumber() {
        return PONumber;
    }


    /**
     * Sets the PONumber value for this OrderDetails.
     * 
     * @param PONumber
     */
    public void setPONumber(java.lang.String PONumber) {
        this.PONumber = PONumber;
    }


    /**
     * Gets the expediteOrderFlag value for this OrderDetails.
     * 
     * @return expediteOrderFlag
     */
    public java.lang.String getExpediteOrderFlag() {
        return expediteOrderFlag;
    }


    /**
     * Sets the expediteOrderFlag value for this OrderDetails.
     * 
     * @param expediteOrderFlag
     */
    public void setExpediteOrderFlag(java.lang.String expediteOrderFlag) {
        this.expediteOrderFlag = expediteOrderFlag;
    }


    /**
     * Gets the specialInstructions value for this OrderDetails.
     * 
     * @return specialInstructions
     */
    public java.lang.String getSpecialInstructions() {
        return specialInstructions;
    }


    /**
     * Sets the specialInstructions value for this OrderDetails.
     * 
     * @param specialInstructions
     */
    public void setSpecialInstructions(java.lang.String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }


    /**
     * Gets the deliveryDate value for this OrderDetails.
     * 
     * @return deliveryDate
     */
    public java.lang.String getDeliveryDate() {
        return deliveryDate;
    }


    /**
     * Sets the deliveryDate value for this OrderDetails.
     * 
     * @param deliveryDate
     */
    public void setDeliveryDate(java.lang.String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    /**
     * Gets the trackingNumber value for this OrderDetails.
     * 
     * @return trackingNumber
     */
    public java.lang.String getTrackingNumber() {
        return trackingNumber;
    }


    /**
     * Sets the trackingNumber value for this OrderDetails.
     * 
     * @param trackingNumber
     */
    public void setTrackingNumber(java.lang.String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }


    /**
     * Gets the shipToAddress value for this OrderDetails.
     * 
     * @return shipToAddress
     */
    public com.lexmark.srHistory.Address getShipToAddress() {
        return shipToAddress;
    }


    /**
     * Sets the shipToAddress value for this OrderDetails.
     * 
     * @param shipToAddress
     */
    public void setShipToAddress(com.lexmark.srHistory.Address shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrderDetails)) return false;
        OrderDetails other = (OrderDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.PONumber==null && other.getPONumber()==null) || 
             (this.PONumber!=null &&
              this.PONumber.equals(other.getPONumber()))) &&
            ((this.expediteOrderFlag==null && other.getExpediteOrderFlag()==null) || 
             (this.expediteOrderFlag!=null &&
              this.expediteOrderFlag.equals(other.getExpediteOrderFlag()))) &&
            ((this.specialInstructions==null && other.getSpecialInstructions()==null) || 
             (this.specialInstructions!=null &&
              this.specialInstructions.equals(other.getSpecialInstructions()))) &&
            ((this.deliveryDate==null && other.getDeliveryDate()==null) || 
             (this.deliveryDate!=null &&
              this.deliveryDate.equals(other.getDeliveryDate()))) &&
            ((this.trackingNumber==null && other.getTrackingNumber()==null) || 
             (this.trackingNumber!=null &&
              this.trackingNumber.equals(other.getTrackingNumber()))) &&
            ((this.shipToAddress==null && other.getShipToAddress()==null) || 
             (this.shipToAddress!=null &&
              this.shipToAddress.equals(other.getShipToAddress())));
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
        if (getPONumber() != null) {
            _hashCode += getPONumber().hashCode();
        }
        if (getExpediteOrderFlag() != null) {
            _hashCode += getExpediteOrderFlag().hashCode();
        }
        if (getSpecialInstructions() != null) {
            _hashCode += getSpecialInstructions().hashCode();
        }
        if (getDeliveryDate() != null) {
            _hashCode += getDeliveryDate().hashCode();
        }
        if (getTrackingNumber() != null) {
            _hashCode += getTrackingNumber().hashCode();
        }
        if (getShipToAddress() != null) {
            _hashCode += getShipToAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrderDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "OrderDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PONumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PONumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expediteOrderFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExpediteOrderFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("specialInstructions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SpecialInstructions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeliveryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TrackingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipToAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipToAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "Address"));
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
