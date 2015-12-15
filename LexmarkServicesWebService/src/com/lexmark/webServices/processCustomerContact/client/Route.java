/**
 * Route.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.processCustomerContact.client;

public class Route  implements java.io.Serializable {
    private java.lang.String broker;

    private java.util.Calendar recvTime;

    private java.util.Calendar enqueueTime;

    public Route() {
    }

    public Route(
           java.lang.String broker,
           java.util.Calendar recvTime,
           java.util.Calendar enqueueTime) {
           this.broker = broker;
           this.recvTime = recvTime;
           this.enqueueTime = enqueueTime;
    }


    /**
     * Gets the broker value for this Route.
     * 
     * @return broker
     */
    public java.lang.String getBroker() {
        return broker;
    }


    /**
     * Sets the broker value for this Route.
     * 
     * @param broker
     */
    public void setBroker(java.lang.String broker) {
        this.broker = broker;
    }


    /**
     * Gets the recvTime value for this Route.
     * 
     * @return recvTime
     */
    public java.util.Calendar getRecvTime() {
        return recvTime;
    }


    /**
     * Sets the recvTime value for this Route.
     * 
     * @param recvTime
     */
    public void setRecvTime(java.util.Calendar recvTime) {
        this.recvTime = recvTime;
    }


    /**
     * Gets the enqueueTime value for this Route.
     * 
     * @return enqueueTime
     */
    public java.util.Calendar getEnqueueTime() {
        return enqueueTime;
    }


    /**
     * Sets the enqueueTime value for this Route.
     * 
     * @param enqueueTime
     */
    public void setEnqueueTime(java.util.Calendar enqueueTime) {
        this.enqueueTime = enqueueTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Route)) return false;
        Route other = (Route) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.broker==null && other.getBroker()==null) || 
             (this.broker!=null &&
              this.broker.equals(other.getBroker()))) &&
            ((this.recvTime==null && other.getRecvTime()==null) || 
             (this.recvTime!=null &&
              this.recvTime.equals(other.getRecvTime()))) &&
            ((this.enqueueTime==null && other.getEnqueueTime()==null) || 
             (this.enqueueTime!=null &&
              this.enqueueTime.equals(other.getEnqueueTime())));
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
        if (getBroker() != null) {
            _hashCode += getBroker().hashCode();
        }
        if (getRecvTime() != null) {
            _hashCode += getRecvTime().hashCode();
        }
        if (getEnqueueTime() != null) {
            _hashCode += getEnqueueTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Route.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "route"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("broker");
        elemField.setXmlName(new javax.xml.namespace.QName("", "broker"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recvTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recvTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enqueueTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enqueueTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
