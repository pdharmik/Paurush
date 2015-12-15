/**
 * LineDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.source.updateServiceOrders.client;

public class LineDetails  implements java.io.Serializable {
    private java.lang.String lineNumber;

    private java.lang.String lexmarkPartNumber;

    private java.lang.String status;

    private java.lang.String statusDate;

    private com.lexmark.SiebelShared.source.updateServiceOrders.client.ShippedQuantity shippedQuantity;

    private com.lexmark.SiebelShared.source.updateServiceOrders.client.OrderedQuantity orderedQuantity;

    private com.lexmark.SiebelShared.source.updateServiceOrders.client.BackOrderedQuantity backOrderedQuantity;

    private java.lang.String carrierId;

    private java.lang.String trackingNumber;

    private java.lang.String customerRequestedDate;

    private java.lang.String expectedShipDate;

    private java.lang.String actualShipDate;

    private java.lang.String deliveredDate;

    private java.lang.String shipmentMethod;

    private java.lang.String shipmentLineReferenceNumber;

    private com.lexmark.SiebelShared.source.updateServiceOrders.client.SerialNumbers[] serialNumbers;

    public LineDetails() {
    }

    public LineDetails(
           java.lang.String lineNumber,
           java.lang.String lexmarkPartNumber,
           java.lang.String status,
           java.lang.String statusDate,
           com.lexmark.SiebelShared.source.updateServiceOrders.client.ShippedQuantity shippedQuantity,
           com.lexmark.SiebelShared.source.updateServiceOrders.client.OrderedQuantity orderedQuantity,
           com.lexmark.SiebelShared.source.updateServiceOrders.client.BackOrderedQuantity backOrderedQuantity,
           java.lang.String carrierId,
           java.lang.String trackingNumber,
           java.lang.String customerRequestedDate,
           java.lang.String expectedShipDate,
           java.lang.String actualShipDate,
           java.lang.String deliveredDate,
           java.lang.String shipmentMethod,
           java.lang.String shipmentLineReferenceNumber,
           com.lexmark.SiebelShared.source.updateServiceOrders.client.SerialNumbers[] serialNumbers) {
           this.lineNumber = lineNumber;
           this.lexmarkPartNumber = lexmarkPartNumber;
           this.status = status;
           this.statusDate = statusDate;
           this.shippedQuantity = shippedQuantity;
           this.orderedQuantity = orderedQuantity;
           this.backOrderedQuantity = backOrderedQuantity;
           this.carrierId = carrierId;
           this.trackingNumber = trackingNumber;
           this.customerRequestedDate = customerRequestedDate;
           this.expectedShipDate = expectedShipDate;
           this.actualShipDate = actualShipDate;
           this.deliveredDate = deliveredDate;
           this.shipmentMethod = shipmentMethod;
           this.shipmentLineReferenceNumber = shipmentLineReferenceNumber;
           this.serialNumbers = serialNumbers;
    }


    /**
     * Gets the lineNumber value for this LineDetails.
     * 
     * @return lineNumber
     */
    public java.lang.String getLineNumber() {
        return lineNumber;
    }


    /**
     * Sets the lineNumber value for this LineDetails.
     * 
     * @param lineNumber
     */
    public void setLineNumber(java.lang.String lineNumber) {
        this.lineNumber = lineNumber;
    }


    /**
     * Gets the lexmarkPartNumber value for this LineDetails.
     * 
     * @return lexmarkPartNumber
     */
    public java.lang.String getLexmarkPartNumber() {
        return lexmarkPartNumber;
    }


    /**
     * Sets the lexmarkPartNumber value for this LineDetails.
     * 
     * @param lexmarkPartNumber
     */
    public void setLexmarkPartNumber(java.lang.String lexmarkPartNumber) {
        this.lexmarkPartNumber = lexmarkPartNumber;
    }


    /**
     * Gets the status value for this LineDetails.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this LineDetails.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the statusDate value for this LineDetails.
     * 
     * @return statusDate
     */
    public java.lang.String getStatusDate() {
        return statusDate;
    }


    /**
     * Sets the statusDate value for this LineDetails.
     * 
     * @param statusDate
     */
    public void setStatusDate(java.lang.String statusDate) {
        this.statusDate = statusDate;
    }


    /**
     * Gets the shippedQuantity value for this LineDetails.
     * 
     * @return shippedQuantity
     */
    public com.lexmark.SiebelShared.source.updateServiceOrders.client.ShippedQuantity getShippedQuantity() {
        return shippedQuantity;
    }


    /**
     * Sets the shippedQuantity value for this LineDetails.
     * 
     * @param shippedQuantity
     */
    public void setShippedQuantity(com.lexmark.SiebelShared.source.updateServiceOrders.client.ShippedQuantity shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }


    /**
     * Gets the orderedQuantity value for this LineDetails.
     * 
     * @return orderedQuantity
     */
    public com.lexmark.SiebelShared.source.updateServiceOrders.client.OrderedQuantity getOrderedQuantity() {
        return orderedQuantity;
    }


    /**
     * Sets the orderedQuantity value for this LineDetails.
     * 
     * @param orderedQuantity
     */
    public void setOrderedQuantity(com.lexmark.SiebelShared.source.updateServiceOrders.client.OrderedQuantity orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }


    /**
     * Gets the backOrderedQuantity value for this LineDetails.
     * 
     * @return backOrderedQuantity
     */
    public com.lexmark.SiebelShared.source.updateServiceOrders.client.BackOrderedQuantity getBackOrderedQuantity() {
        return backOrderedQuantity;
    }


    /**
     * Sets the backOrderedQuantity value for this LineDetails.
     * 
     * @param backOrderedQuantity
     */
    public void setBackOrderedQuantity(com.lexmark.SiebelShared.source.updateServiceOrders.client.BackOrderedQuantity backOrderedQuantity) {
        this.backOrderedQuantity = backOrderedQuantity;
    }


    /**
     * Gets the carrierId value for this LineDetails.
     * 
     * @return carrierId
     */
    public java.lang.String getCarrierId() {
        return carrierId;
    }


    /**
     * Sets the carrierId value for this LineDetails.
     * 
     * @param carrierId
     */
    public void setCarrierId(java.lang.String carrierId) {
        this.carrierId = carrierId;
    }


    /**
     * Gets the trackingNumber value for this LineDetails.
     * 
     * @return trackingNumber
     */
    public java.lang.String getTrackingNumber() {
        return trackingNumber;
    }


    /**
     * Sets the trackingNumber value for this LineDetails.
     * 
     * @param trackingNumber
     */
    public void setTrackingNumber(java.lang.String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }


    /**
     * Gets the customerRequestedDate value for this LineDetails.
     * 
     * @return customerRequestedDate
     */
    public java.lang.String getCustomerRequestedDate() {
        return customerRequestedDate;
    }


    /**
     * Sets the customerRequestedDate value for this LineDetails.
     * 
     * @param customerRequestedDate
     */
    public void setCustomerRequestedDate(java.lang.String customerRequestedDate) {
        this.customerRequestedDate = customerRequestedDate;
    }


    /**
     * Gets the expectedShipDate value for this LineDetails.
     * 
     * @return expectedShipDate
     */
    public java.lang.String getExpectedShipDate() {
        return expectedShipDate;
    }


    /**
     * Sets the expectedShipDate value for this LineDetails.
     * 
     * @param expectedShipDate
     */
    public void setExpectedShipDate(java.lang.String expectedShipDate) {
        this.expectedShipDate = expectedShipDate;
    }


    /**
     * Gets the actualShipDate value for this LineDetails.
     * 
     * @return actualShipDate
     */
    public java.lang.String getActualShipDate() {
        return actualShipDate;
    }


    /**
     * Sets the actualShipDate value for this LineDetails.
     * 
     * @param actualShipDate
     */
    public void setActualShipDate(java.lang.String actualShipDate) {
        this.actualShipDate = actualShipDate;
    }


    /**
     * Gets the deliveredDate value for this LineDetails.
     * 
     * @return deliveredDate
     */
    public java.lang.String getDeliveredDate() {
        return deliveredDate;
    }


    /**
     * Sets the deliveredDate value for this LineDetails.
     * 
     * @param deliveredDate
     */
    public void setDeliveredDate(java.lang.String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }


    /**
     * Gets the shipmentMethod value for this LineDetails.
     * 
     * @return shipmentMethod
     */
    public java.lang.String getShipmentMethod() {
        return shipmentMethod;
    }


    /**
     * Sets the shipmentMethod value for this LineDetails.
     * 
     * @param shipmentMethod
     */
    public void setShipmentMethod(java.lang.String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }


    /**
     * Gets the shipmentLineReferenceNumber value for this LineDetails.
     * 
     * @return shipmentLineReferenceNumber
     */
    public java.lang.String getShipmentLineReferenceNumber() {
        return shipmentLineReferenceNumber;
    }


    /**
     * Sets the shipmentLineReferenceNumber value for this LineDetails.
     * 
     * @param shipmentLineReferenceNumber
     */
    public void setShipmentLineReferenceNumber(java.lang.String shipmentLineReferenceNumber) {
        this.shipmentLineReferenceNumber = shipmentLineReferenceNumber;
    }


    /**
     * Gets the serialNumbers value for this LineDetails.
     * 
     * @return serialNumbers
     */
    public com.lexmark.SiebelShared.source.updateServiceOrders.client.SerialNumbers[] getSerialNumbers() {
        return serialNumbers;
    }


    /**
     * Sets the serialNumbers value for this LineDetails.
     * 
     * @param serialNumbers
     */
    public void setSerialNumbers(com.lexmark.SiebelShared.source.updateServiceOrders.client.SerialNumbers[] serialNumbers) {
        this.serialNumbers = serialNumbers;
    }

    public com.lexmark.SiebelShared.source.updateServiceOrders.client.SerialNumbers getSerialNumbers(int i) {
        return this.serialNumbers[i];
    }

    public void setSerialNumbers(int i, com.lexmark.SiebelShared.source.updateServiceOrders.client.SerialNumbers _value) {
        this.serialNumbers[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LineDetails)) return false;
        LineDetails other = (LineDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lineNumber==null && other.getLineNumber()==null) || 
             (this.lineNumber!=null &&
              this.lineNumber.equals(other.getLineNumber()))) &&
            ((this.lexmarkPartNumber==null && other.getLexmarkPartNumber()==null) || 
             (this.lexmarkPartNumber!=null &&
              this.lexmarkPartNumber.equals(other.getLexmarkPartNumber()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusDate==null && other.getStatusDate()==null) || 
             (this.statusDate!=null &&
              this.statusDate.equals(other.getStatusDate()))) &&
            ((this.shippedQuantity==null && other.getShippedQuantity()==null) || 
             (this.shippedQuantity!=null &&
              this.shippedQuantity.equals(other.getShippedQuantity()))) &&
            ((this.orderedQuantity==null && other.getOrderedQuantity()==null) || 
             (this.orderedQuantity!=null &&
              this.orderedQuantity.equals(other.getOrderedQuantity()))) &&
            ((this.backOrderedQuantity==null && other.getBackOrderedQuantity()==null) || 
             (this.backOrderedQuantity!=null &&
              this.backOrderedQuantity.equals(other.getBackOrderedQuantity()))) &&
            ((this.carrierId==null && other.getCarrierId()==null) || 
             (this.carrierId!=null &&
              this.carrierId.equals(other.getCarrierId()))) &&
            ((this.trackingNumber==null && other.getTrackingNumber()==null) || 
             (this.trackingNumber!=null &&
              this.trackingNumber.equals(other.getTrackingNumber()))) &&
            ((this.customerRequestedDate==null && other.getCustomerRequestedDate()==null) || 
             (this.customerRequestedDate!=null &&
              this.customerRequestedDate.equals(other.getCustomerRequestedDate()))) &&
            ((this.expectedShipDate==null && other.getExpectedShipDate()==null) || 
             (this.expectedShipDate!=null &&
              this.expectedShipDate.equals(other.getExpectedShipDate()))) &&
            ((this.actualShipDate==null && other.getActualShipDate()==null) || 
             (this.actualShipDate!=null &&
              this.actualShipDate.equals(other.getActualShipDate()))) &&
            ((this.deliveredDate==null && other.getDeliveredDate()==null) || 
             (this.deliveredDate!=null &&
              this.deliveredDate.equals(other.getDeliveredDate()))) &&
            ((this.shipmentMethod==null && other.getShipmentMethod()==null) || 
             (this.shipmentMethod!=null &&
              this.shipmentMethod.equals(other.getShipmentMethod()))) &&
            ((this.shipmentLineReferenceNumber==null && other.getShipmentLineReferenceNumber()==null) || 
             (this.shipmentLineReferenceNumber!=null &&
              this.shipmentLineReferenceNumber.equals(other.getShipmentLineReferenceNumber()))) &&
            ((this.serialNumbers==null && other.getSerialNumbers()==null) || 
             (this.serialNumbers!=null &&
              java.util.Arrays.equals(this.serialNumbers, other.getSerialNumbers())));
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
        if (getLineNumber() != null) {
            _hashCode += getLineNumber().hashCode();
        }
        if (getLexmarkPartNumber() != null) {
            _hashCode += getLexmarkPartNumber().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusDate() != null) {
            _hashCode += getStatusDate().hashCode();
        }
        if (getShippedQuantity() != null) {
            _hashCode += getShippedQuantity().hashCode();
        }
        if (getOrderedQuantity() != null) {
            _hashCode += getOrderedQuantity().hashCode();
        }
        if (getBackOrderedQuantity() != null) {
            _hashCode += getBackOrderedQuantity().hashCode();
        }
        if (getCarrierId() != null) {
            _hashCode += getCarrierId().hashCode();
        }
        if (getTrackingNumber() != null) {
            _hashCode += getTrackingNumber().hashCode();
        }
        if (getCustomerRequestedDate() != null) {
            _hashCode += getCustomerRequestedDate().hashCode();
        }
        if (getExpectedShipDate() != null) {
            _hashCode += getExpectedShipDate().hashCode();
        }
        if (getActualShipDate() != null) {
            _hashCode += getActualShipDate().hashCode();
        }
        if (getDeliveredDate() != null) {
            _hashCode += getDeliveredDate().hashCode();
        }
        if (getShipmentMethod() != null) {
            _hashCode += getShipmentMethod().hashCode();
        }
        if (getShipmentLineReferenceNumber() != null) {
            _hashCode += getShipmentLineReferenceNumber().hashCode();
        }
        if (getSerialNumbers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSerialNumbers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSerialNumbers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LineDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "LineDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LineNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lexmarkPartNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LexmarkPartNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("statusDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StatusDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shippedQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShippedQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "ShippedQuantity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderedQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrderedQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "OrderedQuantity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("backOrderedQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BackOrderedQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "BackOrderedQuantity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carrierId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CarrierId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TrackingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerRequestedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerRequestedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectedShipDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ExpectedShipDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualShipDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActualShipDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveredDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeliveredDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipmentMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipmentMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipmentLineReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipmentLineReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serialNumbers");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SerialNumbers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "SerialNumbers"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
