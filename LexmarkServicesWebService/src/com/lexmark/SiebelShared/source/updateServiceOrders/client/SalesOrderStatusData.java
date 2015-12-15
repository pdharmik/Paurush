/**
 * SalesOrderStatusData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.source.updateServiceOrders.client;

public class SalesOrderStatusData  implements java.io.Serializable {
    private java.lang.String orderNumber;

    private java.lang.String sourceReferenceNumber;

    private java.lang.String vendorNumber;

    private java.lang.String vendorOrderNumber;

    private com.lexmark.SiebelShared.source.updateServiceOrders.client.LineDetails[] lineDetails;

    public SalesOrderStatusData() {
    }

    public SalesOrderStatusData(
           java.lang.String orderNumber,
           java.lang.String sourceReferenceNumber,
           java.lang.String vendorNumber,
           java.lang.String vendorOrderNumber,
           com.lexmark.SiebelShared.source.updateServiceOrders.client.LineDetails[] lineDetails) {
           this.orderNumber = orderNumber;
           this.sourceReferenceNumber = sourceReferenceNumber;
           this.vendorNumber = vendorNumber;
           this.vendorOrderNumber = vendorOrderNumber;
           this.lineDetails = lineDetails;
    }


    /**
     * Gets the orderNumber value for this SalesOrderStatusData.
     * 
     * @return orderNumber
     */
    public java.lang.String getOrderNumber() {
        return orderNumber;
    }


    /**
     * Sets the orderNumber value for this SalesOrderStatusData.
     * 
     * @param orderNumber
     */
    public void setOrderNumber(java.lang.String orderNumber) {
        this.orderNumber = orderNumber;
    }


    /**
     * Gets the sourceReferenceNumber value for this SalesOrderStatusData.
     * 
     * @return sourceReferenceNumber
     */
    public java.lang.String getSourceReferenceNumber() {
        return sourceReferenceNumber;
    }


    /**
     * Sets the sourceReferenceNumber value for this SalesOrderStatusData.
     * 
     * @param sourceReferenceNumber
     */
    public void setSourceReferenceNumber(java.lang.String sourceReferenceNumber) {
        this.sourceReferenceNumber = sourceReferenceNumber;
    }


    /**
     * Gets the vendorNumber value for this SalesOrderStatusData.
     * 
     * @return vendorNumber
     */
    public java.lang.String getVendorNumber() {
        return vendorNumber;
    }


    /**
     * Sets the vendorNumber value for this SalesOrderStatusData.
     * 
     * @param vendorNumber
     */
    public void setVendorNumber(java.lang.String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }


    /**
     * Gets the vendorOrderNumber value for this SalesOrderStatusData.
     * 
     * @return vendorOrderNumber
     */
    public java.lang.String getVendorOrderNumber() {
        return vendorOrderNumber;
    }


    /**
     * Sets the vendorOrderNumber value for this SalesOrderStatusData.
     * 
     * @param vendorOrderNumber
     */
    public void setVendorOrderNumber(java.lang.String vendorOrderNumber) {
        this.vendorOrderNumber = vendorOrderNumber;
    }


    /**
     * Gets the lineDetails value for this SalesOrderStatusData.
     * 
     * @return lineDetails
     */
    public com.lexmark.SiebelShared.source.updateServiceOrders.client.LineDetails[] getLineDetails() {
        return lineDetails;
    }


    /**
     * Sets the lineDetails value for this SalesOrderStatusData.
     * 
     * @param lineDetails
     */
    public void setLineDetails(com.lexmark.SiebelShared.source.updateServiceOrders.client.LineDetails[] lineDetails) {
        this.lineDetails = lineDetails;
    }

    public com.lexmark.SiebelShared.source.updateServiceOrders.client.LineDetails getLineDetails(int i) {
        return this.lineDetails[i];
    }

    public void setLineDetails(int i, com.lexmark.SiebelShared.source.updateServiceOrders.client.LineDetails _value) {
        this.lineDetails[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SalesOrderStatusData)) return false;
        SalesOrderStatusData other = (SalesOrderStatusData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.orderNumber==null && other.getOrderNumber()==null) || 
             (this.orderNumber!=null &&
              this.orderNumber.equals(other.getOrderNumber()))) &&
            ((this.sourceReferenceNumber==null && other.getSourceReferenceNumber()==null) || 
             (this.sourceReferenceNumber!=null &&
              this.sourceReferenceNumber.equals(other.getSourceReferenceNumber()))) &&
            ((this.vendorNumber==null && other.getVendorNumber()==null) || 
             (this.vendorNumber!=null &&
              this.vendorNumber.equals(other.getVendorNumber()))) &&
            ((this.vendorOrderNumber==null && other.getVendorOrderNumber()==null) || 
             (this.vendorOrderNumber!=null &&
              this.vendorOrderNumber.equals(other.getVendorOrderNumber()))) &&
            ((this.lineDetails==null && other.getLineDetails()==null) || 
             (this.lineDetails!=null &&
              java.util.Arrays.equals(this.lineDetails, other.getLineDetails())));
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
        if (getOrderNumber() != null) {
            _hashCode += getOrderNumber().hashCode();
        }
        if (getSourceReferenceNumber() != null) {
            _hashCode += getSourceReferenceNumber().hashCode();
        }
        if (getVendorNumber() != null) {
            _hashCode += getVendorNumber().hashCode();
        }
        if (getVendorOrderNumber() != null) {
            _hashCode += getVendorOrderNumber().hashCode();
        }
        if (getLineDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLineDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLineDetails(), i);
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
        new org.apache.axis.description.TypeDesc(SalesOrderStatusData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "SalesOrderStatusData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OrderNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SourceReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vendorNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VendorNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vendorOrderNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VendorOrderNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LineDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/SalesOrder/salesOrderWS", "LineDetails"));
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
