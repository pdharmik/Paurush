/**
 * AddedPropSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class AddedPropSet  implements java.io.Serializable {
    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TimeSlot[] timeSlot;

    private java.lang.String number_spcof_spcappointments;  // attribute

    private java.lang.String number_undof_undappointments;  // attribute

    public AddedPropSet() {
    }

    public AddedPropSet(
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TimeSlot[] timeSlot,
           java.lang.String number_spcof_spcappointments,
           java.lang.String number_undof_undappointments) {
           this.timeSlot = timeSlot;
           this.number_spcof_spcappointments = number_spcof_spcappointments;
           this.number_undof_undappointments = number_undof_undappointments;
    }


    /**
     * Gets the timeSlot value for this AddedPropSet.
     * 
     * @return timeSlot
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TimeSlot[] getTimeSlot() {
        return timeSlot;
    }


    /**
     * Sets the timeSlot value for this AddedPropSet.
     * 
     * @param timeSlot
     */
    public void setTimeSlot(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.TimeSlot[] timeSlot) {
        this.timeSlot = timeSlot;
    }


    /**
     * Gets the number_spcof_spcappointments value for this AddedPropSet.
     * 
     * @return number_spcof_spcappointments
     */
    public java.lang.String getNumber_spcof_spcappointments() {
        return number_spcof_spcappointments;
    }


    /**
     * Sets the number_spcof_spcappointments value for this AddedPropSet.
     * 
     * @param number_spcof_spcappointments
     */
    public void setNumber_spcof_spcappointments(java.lang.String number_spcof_spcappointments) {
        this.number_spcof_spcappointments = number_spcof_spcappointments;
    }


    /**
     * Gets the number_undof_undappointments value for this AddedPropSet.
     * 
     * @return number_undof_undappointments
     */
    public java.lang.String getNumber_undof_undappointments() {
        return number_undof_undappointments;
    }


    /**
     * Sets the number_undof_undappointments value for this AddedPropSet.
     * 
     * @param number_undof_undappointments
     */
    public void setNumber_undof_undappointments(java.lang.String number_undof_undappointments) {
        this.number_undof_undappointments = number_undof_undappointments;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddedPropSet)) return false;
        AddedPropSet other = (AddedPropSet) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.timeSlot==null && other.getTimeSlot()==null) || 
             (this.timeSlot!=null &&
              java.util.Arrays.equals(this.timeSlot, other.getTimeSlot()))) &&
            ((this.number_spcof_spcappointments==null && other.getNumber_spcof_spcappointments()==null) || 
             (this.number_spcof_spcappointments!=null &&
              this.number_spcof_spcappointments.equals(other.getNumber_spcof_spcappointments()))) &&
            ((this.number_undof_undappointments==null && other.getNumber_undof_undappointments()==null) || 
             (this.number_undof_undappointments!=null &&
              this.number_undof_undappointments.equals(other.getNumber_undof_undappointments())));
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
        if (getTimeSlot() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimeSlot());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTimeSlot(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumber_spcof_spcappointments() != null) {
            _hashCode += getNumber_spcof_spcappointments().hashCode();
        }
        if (getNumber_undof_undappointments() != null) {
            _hashCode += getNumber_undof_undappointments().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddedPropSet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "addedPropSet"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("number_spcof_spcappointments");
        attrField.setXmlName(new javax.xml.namespace.QName("", "number_spcof_spcappointments"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("number_undof_undappointments");
        attrField.setXmlName(new javax.xml.namespace.QName("", "number_undof_undappointments"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeSlot");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TimeSlot"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "TimeSlot"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfTimeSlotItem"));
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
