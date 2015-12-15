/**
 * SiebelActivityDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class SiebelActivityDetails  implements java.io.Serializable {
    private java.lang.String activityId;

    private java.lang.String activityDate;

    private java.lang.String activityStatus;

    private java.lang.String activityDescription;

    private java.lang.String recipientEmailAddress;

    public SiebelActivityDetails() {
    }

    public SiebelActivityDetails(
           java.lang.String activityId,
           java.lang.String activityDate,
           java.lang.String activityStatus,
           java.lang.String activityDescription,
           java.lang.String recipientEmailAddress) {
           this.activityId = activityId;
           this.activityDate = activityDate;
           this.activityStatus = activityStatus;
           this.activityDescription = activityDescription;
           this.recipientEmailAddress = recipientEmailAddress;
    }


    /**
     * Gets the activityId value for this SiebelActivityDetails.
     * 
     * @return activityId
     */
    public java.lang.String getActivityId() {
        return activityId;
    }


    /**
     * Sets the activityId value for this SiebelActivityDetails.
     * 
     * @param activityId
     */
    public void setActivityId(java.lang.String activityId) {
        this.activityId = activityId;
    }


    /**
     * Gets the activityDate value for this SiebelActivityDetails.
     * 
     * @return activityDate
     */
    public java.lang.String getActivityDate() {
        return activityDate;
    }


    /**
     * Sets the activityDate value for this SiebelActivityDetails.
     * 
     * @param activityDate
     */
    public void setActivityDate(java.lang.String activityDate) {
        this.activityDate = activityDate;
    }


    /**
     * Gets the activityStatus value for this SiebelActivityDetails.
     * 
     * @return activityStatus
     */
    public java.lang.String getActivityStatus() {
        return activityStatus;
    }


    /**
     * Sets the activityStatus value for this SiebelActivityDetails.
     * 
     * @param activityStatus
     */
    public void setActivityStatus(java.lang.String activityStatus) {
        this.activityStatus = activityStatus;
    }


    /**
     * Gets the activityDescription value for this SiebelActivityDetails.
     * 
     * @return activityDescription
     */
    public java.lang.String getActivityDescription() {
        return activityDescription;
    }


    /**
     * Sets the activityDescription value for this SiebelActivityDetails.
     * 
     * @param activityDescription
     */
    public void setActivityDescription(java.lang.String activityDescription) {
        this.activityDescription = activityDescription;
    }


    /**
     * Gets the recipientEmailAddress value for this SiebelActivityDetails.
     * 
     * @return recipientEmailAddress
     */
    public java.lang.String getRecipientEmailAddress() {
        return recipientEmailAddress;
    }


    /**
     * Sets the recipientEmailAddress value for this SiebelActivityDetails.
     * 
     * @param recipientEmailAddress
     */
    public void setRecipientEmailAddress(java.lang.String recipientEmailAddress) {
        this.recipientEmailAddress = recipientEmailAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelActivityDetails)) return false;
        SiebelActivityDetails other = (SiebelActivityDetails) obj;
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
            ((this.activityDate==null && other.getActivityDate()==null) || 
             (this.activityDate!=null &&
              this.activityDate.equals(other.getActivityDate()))) &&
            ((this.activityStatus==null && other.getActivityStatus()==null) || 
             (this.activityStatus!=null &&
              this.activityStatus.equals(other.getActivityStatus()))) &&
            ((this.activityDescription==null && other.getActivityDescription()==null) || 
             (this.activityDescription!=null &&
              this.activityDescription.equals(other.getActivityDescription()))) &&
            ((this.recipientEmailAddress==null && other.getRecipientEmailAddress()==null) || 
             (this.recipientEmailAddress!=null &&
              this.recipientEmailAddress.equals(other.getRecipientEmailAddress())));
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
        if (getActivityDate() != null) {
            _hashCode += getActivityDate().hashCode();
        }
        if (getActivityStatus() != null) {
            _hashCode += getActivityStatus().hashCode();
        }
        if (getActivityDescription() != null) {
            _hashCode += getActivityDescription().hashCode();
        }
        if (getRecipientEmailAddress() != null) {
            _hashCode += getRecipientEmailAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelActivityDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelActivityDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipientEmailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RecipientEmailAddress"));
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
