/**
 * RecommendedPartsList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class RecommendedPartsList  implements java.io.Serializable {
    private java.lang.String lineItemId;

    private java.lang.String orderDate;

    private java.lang.String quantity;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PartInfromation partInfromation;

    private java.lang.String returnRequiredFlag;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ShipToAddress2 shipToAddress;

    private java.lang.String status;

    private java.lang.String errorCode1;

    private java.lang.String errorCode2;

    private java.lang.String carrier;

    private java.lang.String returnTrackingNumber;

    private java.lang.String recommendedPartUpdatedFlag;

    public RecommendedPartsList() {
    }

    public RecommendedPartsList(
           java.lang.String lineItemId,
           java.lang.String orderDate,
           java.lang.String quantity,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PartInfromation partInfromation,
           java.lang.String returnRequiredFlag,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ShipToAddress2 shipToAddress,
           java.lang.String status,
           java.lang.String errorCode1,
           java.lang.String errorCode2,
           java.lang.String carrier,
           java.lang.String returnTrackingNumber,
           java.lang.String recommendedPartUpdatedFlag) {
           this.lineItemId = lineItemId;
           this.orderDate = orderDate;
           this.quantity = quantity;
           this.partInfromation = partInfromation;
           this.returnRequiredFlag = returnRequiredFlag;
           this.shipToAddress = shipToAddress;
           this.status = status;
           this.errorCode1 = errorCode1;
           this.errorCode2 = errorCode2;
           this.carrier = carrier;
           this.returnTrackingNumber = returnTrackingNumber;
           this.recommendedPartUpdatedFlag = recommendedPartUpdatedFlag;
    }


    /**
     * Gets the lineItemId value for this RecommendedPartsList.
     * 
     * @return lineItemId
     */
    public java.lang.String getLineItemId() {
        return lineItemId;
    }


    /**
     * Sets the lineItemId value for this RecommendedPartsList.
     * 
     * @param lineItemId
     */
    public void setLineItemId(java.lang.String lineItemId) {
        this.lineItemId = lineItemId;
    }


    /**
     * Gets the orderDate value for this RecommendedPartsList.
     * 
     * @return orderDate
     */
    public java.lang.String getOrderDate() {
        return orderDate;
    }


    /**
     * Sets the orderDate value for this RecommendedPartsList.
     * 
     * @param orderDate
     */
    public void setOrderDate(java.lang.String orderDate) {
        this.orderDate = orderDate;
    }


    /**
     * Gets the quantity value for this RecommendedPartsList.
     * 
     * @return quantity
     */
    public java.lang.String getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this RecommendedPartsList.
     * 
     * @param quantity
     */
    public void setQuantity(java.lang.String quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the partInfromation value for this RecommendedPartsList.
     * 
     * @return partInfromation
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PartInfromation getPartInfromation() {
        return partInfromation;
    }


    /**
     * Sets the partInfromation value for this RecommendedPartsList.
     * 
     * @param partInfromation
     */
    public void setPartInfromation(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PartInfromation partInfromation) {
        this.partInfromation = partInfromation;
    }


    /**
     * Gets the returnRequiredFlag value for this RecommendedPartsList.
     * 
     * @return returnRequiredFlag
     */
    public java.lang.String getReturnRequiredFlag() {
        return returnRequiredFlag;
    }


    /**
     * Sets the returnRequiredFlag value for this RecommendedPartsList.
     * 
     * @param returnRequiredFlag
     */
    public void setReturnRequiredFlag(java.lang.String returnRequiredFlag) {
        this.returnRequiredFlag = returnRequiredFlag;
    }


    /**
     * Gets the shipToAddress value for this RecommendedPartsList.
     * 
     * @return shipToAddress
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ShipToAddress2 getShipToAddress() {
        return shipToAddress;
    }


    /**
     * Sets the shipToAddress value for this RecommendedPartsList.
     * 
     * @param shipToAddress
     */
    public void setShipToAddress(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ShipToAddress2 shipToAddress) {
        this.shipToAddress = shipToAddress;
    }


    /**
     * Gets the status value for this RecommendedPartsList.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this RecommendedPartsList.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the errorCode1 value for this RecommendedPartsList.
     * 
     * @return errorCode1
     */
    public java.lang.String getErrorCode1() {
        return errorCode1;
    }


    /**
     * Sets the errorCode1 value for this RecommendedPartsList.
     * 
     * @param errorCode1
     */
    public void setErrorCode1(java.lang.String errorCode1) {
        this.errorCode1 = errorCode1;
    }


    /**
     * Gets the errorCode2 value for this RecommendedPartsList.
     * 
     * @return errorCode2
     */
    public java.lang.String getErrorCode2() {
        return errorCode2;
    }


    /**
     * Sets the errorCode2 value for this RecommendedPartsList.
     * 
     * @param errorCode2
     */
    public void setErrorCode2(java.lang.String errorCode2) {
        this.errorCode2 = errorCode2;
    }


    /**
     * Gets the carrier value for this RecommendedPartsList.
     * 
     * @return carrier
     */
    public java.lang.String getCarrier() {
        return carrier;
    }


    /**
     * Sets the carrier value for this RecommendedPartsList.
     * 
     * @param carrier
     */
    public void setCarrier(java.lang.String carrier) {
        this.carrier = carrier;
    }


    /**
     * Gets the returnTrackingNumber value for this RecommendedPartsList.
     * 
     * @return returnTrackingNumber
     */
    public java.lang.String getReturnTrackingNumber() {
        return returnTrackingNumber;
    }


    /**
     * Sets the returnTrackingNumber value for this RecommendedPartsList.
     * 
     * @param returnTrackingNumber
     */
    public void setReturnTrackingNumber(java.lang.String returnTrackingNumber) {
        this.returnTrackingNumber = returnTrackingNumber;
    }


    /**
     * Gets the recommendedPartUpdatedFlag value for this RecommendedPartsList.
     * 
     * @return recommendedPartUpdatedFlag
     */
    public java.lang.String getRecommendedPartUpdatedFlag() {
        return recommendedPartUpdatedFlag;
    }


    /**
     * Sets the recommendedPartUpdatedFlag value for this RecommendedPartsList.
     * 
     * @param recommendedPartUpdatedFlag
     */
    public void setRecommendedPartUpdatedFlag(java.lang.String recommendedPartUpdatedFlag) {
        this.recommendedPartUpdatedFlag = recommendedPartUpdatedFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecommendedPartsList)) return false;
        RecommendedPartsList other = (RecommendedPartsList) obj;
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
            ((this.orderDate==null && other.getOrderDate()==null) || 
             (this.orderDate!=null &&
              this.orderDate.equals(other.getOrderDate()))) &&
            ((this.quantity==null && other.getQuantity()==null) || 
             (this.quantity!=null &&
              this.quantity.equals(other.getQuantity()))) &&
            ((this.partInfromation==null && other.getPartInfromation()==null) || 
             (this.partInfromation!=null &&
              this.partInfromation.equals(other.getPartInfromation()))) &&
            ((this.returnRequiredFlag==null && other.getReturnRequiredFlag()==null) || 
             (this.returnRequiredFlag!=null &&
              this.returnRequiredFlag.equals(other.getReturnRequiredFlag()))) &&
            ((this.shipToAddress==null && other.getShipToAddress()==null) || 
             (this.shipToAddress!=null &&
              this.shipToAddress.equals(other.getShipToAddress()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.errorCode1==null && other.getErrorCode1()==null) || 
             (this.errorCode1!=null &&
              this.errorCode1.equals(other.getErrorCode1()))) &&
            ((this.errorCode2==null && other.getErrorCode2()==null) || 
             (this.errorCode2!=null &&
              this.errorCode2.equals(other.getErrorCode2()))) &&
            ((this.carrier==null && other.getCarrier()==null) || 
             (this.carrier!=null &&
              this.carrier.equals(other.getCarrier()))) &&
            ((this.returnTrackingNumber==null && other.getReturnTrackingNumber()==null) || 
             (this.returnTrackingNumber!=null &&
              this.returnTrackingNumber.equals(other.getReturnTrackingNumber()))) &&
            ((this.recommendedPartUpdatedFlag==null && other.getRecommendedPartUpdatedFlag()==null) || 
             (this.recommendedPartUpdatedFlag!=null &&
              this.recommendedPartUpdatedFlag.equals(other.getRecommendedPartUpdatedFlag())));
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
        if (getOrderDate() != null) {
            _hashCode += getOrderDate().hashCode();
        }
        if (getQuantity() != null) {
            _hashCode += getQuantity().hashCode();
        }
        if (getPartInfromation() != null) {
            _hashCode += getPartInfromation().hashCode();
        }
        if (getReturnRequiredFlag() != null) {
            _hashCode += getReturnRequiredFlag().hashCode();
        }
        if (getShipToAddress() != null) {
            _hashCode += getShipToAddress().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getErrorCode1() != null) {
            _hashCode += getErrorCode1().hashCode();
        }
        if (getErrorCode2() != null) {
            _hashCode += getErrorCode2().hashCode();
        }
        if (getCarrier() != null) {
            _hashCode += getCarrier().hashCode();
        }
        if (getReturnTrackingNumber() != null) {
            _hashCode += getReturnTrackingNumber().hashCode();
        }
        if (getRecommendedPartUpdatedFlag() != null) {
            _hashCode += getRecommendedPartUpdatedFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecommendedPartsList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "RecommendedPartsList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineItemId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LineItemId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrderDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partInfromation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartInfromation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PartInfromation"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnRequiredFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnRequiredFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipToAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipToAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ShipToAddress2"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ErrorCode1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ErrorCode2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("recommendedPartUpdatedFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RecommendedPartUpdatedFlag"));
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
