/**
 * ConfirmServiceAppointmentData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class ConfirmServiceAppointmentData  implements java.io.Serializable {
    private java.lang.String activityId;

    private java.lang.String serviceRegion;

    private java.lang.String requesterId;

    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestId;

    private java.lang.String serviceRequestToken;

    private java.lang.String appointmentStartDateTime;

    private java.lang.String timeZone;

    public ConfirmServiceAppointmentData() {
    }

    public ConfirmServiceAppointmentData(
           java.lang.String activityId,
           java.lang.String serviceRegion,
           java.lang.String requesterId,
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestId,
           java.lang.String serviceRequestToken,
           java.lang.String appointmentStartDateTime,
           java.lang.String timeZone) {
           this.activityId = activityId;
           this.serviceRegion = serviceRegion;
           this.requesterId = requesterId;
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestId = serviceRequestId;
           this.serviceRequestToken = serviceRequestToken;
           this.appointmentStartDateTime = appointmentStartDateTime;
           this.timeZone = timeZone;
    }


    /**
     * Gets the activityId value for this ConfirmServiceAppointmentData.
     * 
     * @return activityId
     */
    public java.lang.String getActivityId() {
        return activityId;
    }


    /**
     * Sets the activityId value for this ConfirmServiceAppointmentData.
     * 
     * @param activityId
     */
    public void setActivityId(java.lang.String activityId) {
        this.activityId = activityId;
    }


    /**
     * Gets the serviceRegion value for this ConfirmServiceAppointmentData.
     * 
     * @return serviceRegion
     */
    public java.lang.String getServiceRegion() {
        return serviceRegion;
    }


    /**
     * Sets the serviceRegion value for this ConfirmServiceAppointmentData.
     * 
     * @param serviceRegion
     */
    public void setServiceRegion(java.lang.String serviceRegion) {
        this.serviceRegion = serviceRegion;
    }


    /**
     * Gets the requesterId value for this ConfirmServiceAppointmentData.
     * 
     * @return requesterId
     */
    public java.lang.String getRequesterId() {
        return requesterId;
    }


    /**
     * Sets the requesterId value for this ConfirmServiceAppointmentData.
     * 
     * @param requesterId
     */
    public void setRequesterId(java.lang.String requesterId) {
        this.requesterId = requesterId;
    }


    /**
     * Gets the serviceRequestNumber value for this ConfirmServiceAppointmentData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this ConfirmServiceAppointmentData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestId value for this ConfirmServiceAppointmentData.
     * 
     * @return serviceRequestId
     */
    public java.lang.String getServiceRequestId() {
        return serviceRequestId;
    }


    /**
     * Sets the serviceRequestId value for this ConfirmServiceAppointmentData.
     * 
     * @param serviceRequestId
     */
    public void setServiceRequestId(java.lang.String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }


    /**
     * Gets the serviceRequestToken value for this ConfirmServiceAppointmentData.
     * 
     * @return serviceRequestToken
     */
    public java.lang.String getServiceRequestToken() {
        return serviceRequestToken;
    }


    /**
     * Sets the serviceRequestToken value for this ConfirmServiceAppointmentData.
     * 
     * @param serviceRequestToken
     */
    public void setServiceRequestToken(java.lang.String serviceRequestToken) {
        this.serviceRequestToken = serviceRequestToken;
    }


    /**
     * Gets the appointmentStartDateTime value for this ConfirmServiceAppointmentData.
     * 
     * @return appointmentStartDateTime
     */
    public java.lang.String getAppointmentStartDateTime() {
        return appointmentStartDateTime;
    }


    /**
     * Sets the appointmentStartDateTime value for this ConfirmServiceAppointmentData.
     * 
     * @param appointmentStartDateTime
     */
    public void setAppointmentStartDateTime(java.lang.String appointmentStartDateTime) {
        this.appointmentStartDateTime = appointmentStartDateTime;
    }


    /**
     * Gets the timeZone value for this ConfirmServiceAppointmentData.
     * 
     * @return timeZone
     */
    public java.lang.String getTimeZone() {
        return timeZone;
    }


    /**
     * Sets the timeZone value for this ConfirmServiceAppointmentData.
     * 
     * @param timeZone
     */
    public void setTimeZone(java.lang.String timeZone) {
        this.timeZone = timeZone;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConfirmServiceAppointmentData)) return false;
        ConfirmServiceAppointmentData other = (ConfirmServiceAppointmentData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.activityId==null && other.getActivityId()==null) || 
             (this.activityId!=null &&
              this.activityId.equals(other.getActivityId()))) &&
            ((this.serviceRegion==null && other.getServiceRegion()==null) || 
             (this.serviceRegion!=null &&
              this.serviceRegion.equals(other.getServiceRegion()))) &&
            ((this.requesterId==null && other.getRequesterId()==null) || 
             (this.requesterId!=null &&
              this.requesterId.equals(other.getRequesterId()))) &&
            ((this.serviceRequestNumber==null && other.getServiceRequestNumber()==null) || 
             (this.serviceRequestNumber!=null &&
              this.serviceRequestNumber.equals(other.getServiceRequestNumber()))) &&
            ((this.serviceRequestId==null && other.getServiceRequestId()==null) || 
             (this.serviceRequestId!=null &&
              this.serviceRequestId.equals(other.getServiceRequestId()))) &&
            ((this.serviceRequestToken==null && other.getServiceRequestToken()==null) || 
             (this.serviceRequestToken!=null &&
              this.serviceRequestToken.equals(other.getServiceRequestToken()))) &&
            ((this.appointmentStartDateTime==null && other.getAppointmentStartDateTime()==null) || 
             (this.appointmentStartDateTime!=null &&
              this.appointmentStartDateTime.equals(other.getAppointmentStartDateTime()))) &&
            ((this.timeZone==null && other.getTimeZone()==null) || 
             (this.timeZone!=null &&
              this.timeZone.equals(other.getTimeZone())));
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
        if (getActivityId() != null) {
            _hashCode += getActivityId().hashCode();
        }
        if (getServiceRegion() != null) {
            _hashCode += getServiceRegion().hashCode();
        }
        if (getRequesterId() != null) {
            _hashCode += getRequesterId().hashCode();
        }
        if (getServiceRequestNumber() != null) {
            _hashCode += getServiceRequestNumber().hashCode();
        }
        if (getServiceRequestId() != null) {
            _hashCode += getServiceRequestId().hashCode();
        }
        if (getServiceRequestToken() != null) {
            _hashCode += getServiceRequestToken().hashCode();
        }
        if (getAppointmentStartDateTime() != null) {
            _hashCode += getAppointmentStartDateTime().hashCode();
        }
        if (getTimeZone() != null) {
            _hashCode += getTimeZone().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConfirmServiceAppointmentData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "confirmServiceAppointmentData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRegion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRegion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requesterId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequesterId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appointmentStartDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AppointmentStartDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeZone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TimeZone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
