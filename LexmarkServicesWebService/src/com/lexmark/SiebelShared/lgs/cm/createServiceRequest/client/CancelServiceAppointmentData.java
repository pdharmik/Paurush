/**
 * CancelServiceAppointmentData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class CancelServiceAppointmentData  implements java.io.Serializable {
    private java.lang.String activityId;

    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestId;

    private java.lang.String serviceRequestToken;

    private java.lang.String serviceRegion;

    public CancelServiceAppointmentData() {
    }

    public CancelServiceAppointmentData(
           java.lang.String activityId,
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestId,
           java.lang.String serviceRequestToken,
           java.lang.String serviceRegion) {
           this.activityId = activityId;
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestId = serviceRequestId;
           this.serviceRequestToken = serviceRequestToken;
           this.serviceRegion = serviceRegion;
    }


    /**
     * Gets the activityId value for this CancelServiceAppointmentData.
     * 
     * @return activityId
     */
    public java.lang.String getActivityId() {
        return activityId;
    }


    /**
     * Sets the activityId value for this CancelServiceAppointmentData.
     * 
     * @param activityId
     */
    public void setActivityId(java.lang.String activityId) {
        this.activityId = activityId;
    }


    /**
     * Gets the serviceRequestNumber value for this CancelServiceAppointmentData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this CancelServiceAppointmentData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestId value for this CancelServiceAppointmentData.
     * 
     * @return serviceRequestId
     */
    public java.lang.String getServiceRequestId() {
        return serviceRequestId;
    }


    /**
     * Sets the serviceRequestId value for this CancelServiceAppointmentData.
     * 
     * @param serviceRequestId
     */
    public void setServiceRequestId(java.lang.String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }


    /**
     * Gets the serviceRequestToken value for this CancelServiceAppointmentData.
     * 
     * @return serviceRequestToken
     */
    public java.lang.String getServiceRequestToken() {
        return serviceRequestToken;
    }


    /**
     * Sets the serviceRequestToken value for this CancelServiceAppointmentData.
     * 
     * @param serviceRequestToken
     */
    public void setServiceRequestToken(java.lang.String serviceRequestToken) {
        this.serviceRequestToken = serviceRequestToken;
    }


    /**
     * Gets the serviceRegion value for this CancelServiceAppointmentData.
     * 
     * @return serviceRegion
     */
    public java.lang.String getServiceRegion() {
        return serviceRegion;
    }


    /**
     * Sets the serviceRegion value for this CancelServiceAppointmentData.
     * 
     * @param serviceRegion
     */
    public void setServiceRegion(java.lang.String serviceRegion) {
        this.serviceRegion = serviceRegion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelServiceAppointmentData)) return false;
        CancelServiceAppointmentData other = (CancelServiceAppointmentData) obj;
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
            ((this.serviceRequestNumber==null && other.getServiceRequestNumber()==null) || 
             (this.serviceRequestNumber!=null &&
              this.serviceRequestNumber.equals(other.getServiceRequestNumber()))) &&
            ((this.serviceRequestId==null && other.getServiceRequestId()==null) || 
             (this.serviceRequestId!=null &&
              this.serviceRequestId.equals(other.getServiceRequestId()))) &&
            ((this.serviceRequestToken==null && other.getServiceRequestToken()==null) || 
             (this.serviceRequestToken!=null &&
              this.serviceRequestToken.equals(other.getServiceRequestToken()))) &&
            ((this.serviceRegion==null && other.getServiceRegion()==null) || 
             (this.serviceRegion!=null &&
              this.serviceRegion.equals(other.getServiceRegion())));
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
        if (getServiceRequestNumber() != null) {
            _hashCode += getServiceRequestNumber().hashCode();
        }
        if (getServiceRequestId() != null) {
            _hashCode += getServiceRequestId().hashCode();
        }
        if (getServiceRequestToken() != null) {
            _hashCode += getServiceRequestToken().hashCode();
        }
        if (getServiceRegion() != null) {
            _hashCode += getServiceRegion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelServiceAppointmentData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "cancelServiceAppointmentData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("serviceRegion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRegion"));
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
