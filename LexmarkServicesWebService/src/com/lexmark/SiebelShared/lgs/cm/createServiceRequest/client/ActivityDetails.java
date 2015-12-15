/**
 * ActivityDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ActivityDetails  implements java.io.Serializable {
    private java.lang.String activityId;

    private java.lang.String activityType;

    private java.lang.String activityDate;

    private java.lang.String activityStatus;

    private java.lang.String activityDescription;

    private java.lang.String recipientEmailAddress;

    private java.lang.String plannedStartTime;

    private java.lang.String plannedCompletionTime;

    private java.lang.String assignedTo;

    private java.lang.String priority;

    private java.lang.String severity;

    private java.lang.String noSoonerThan;

    public ActivityDetails() {
    }

    public ActivityDetails(
           java.lang.String activityId,
           java.lang.String activityType,
           java.lang.String activityDate,
           java.lang.String activityStatus,
           java.lang.String activityDescription,
           java.lang.String recipientEmailAddress,
           java.lang.String plannedStartTime,
           java.lang.String plannedCompletionTime,
           java.lang.String assignedTo,
           java.lang.String priority,
           java.lang.String severity,
           java.lang.String noSoonerThan) {
           this.activityId = activityId;
           this.activityType = activityType;
           this.activityDate = activityDate;
           this.activityStatus = activityStatus;
           this.activityDescription = activityDescription;
           this.recipientEmailAddress = recipientEmailAddress;
           this.plannedStartTime = plannedStartTime;
           this.plannedCompletionTime = plannedCompletionTime;
           this.assignedTo = assignedTo;
           this.priority = priority;
           this.severity = severity;
           this.noSoonerThan = noSoonerThan;
    }


    /**
     * Gets the activityId value for this ActivityDetails.
     * 
     * @return activityId
     */
    public java.lang.String getActivityId() {
        return activityId;
    }


    /**
     * Sets the activityId value for this ActivityDetails.
     * 
     * @param activityId
     */
    public void setActivityId(java.lang.String activityId) {
        this.activityId = activityId;
    }


    /**
     * Gets the activityType value for this ActivityDetails.
     * 
     * @return activityType
     */
    public java.lang.String getActivityType() {
        return activityType;
    }


    /**
     * Sets the activityType value for this ActivityDetails.
     * 
     * @param activityType
     */
    public void setActivityType(java.lang.String activityType) {
        this.activityType = activityType;
    }


    /**
     * Gets the activityDate value for this ActivityDetails.
     * 
     * @return activityDate
     */
    public java.lang.String getActivityDate() {
        return activityDate;
    }


    /**
     * Sets the activityDate value for this ActivityDetails.
     * 
     * @param activityDate
     */
    public void setActivityDate(java.lang.String activityDate) {
        this.activityDate = activityDate;
    }


    /**
     * Gets the activityStatus value for this ActivityDetails.
     * 
     * @return activityStatus
     */
    public java.lang.String getActivityStatus() {
        return activityStatus;
    }


    /**
     * Sets the activityStatus value for this ActivityDetails.
     * 
     * @param activityStatus
     */
    public void setActivityStatus(java.lang.String activityStatus) {
        this.activityStatus = activityStatus;
    }


    /**
     * Gets the activityDescription value for this ActivityDetails.
     * 
     * @return activityDescription
     */
    public java.lang.String getActivityDescription() {
        return activityDescription;
    }


    /**
     * Sets the activityDescription value for this ActivityDetails.
     * 
     * @param activityDescription
     */
    public void setActivityDescription(java.lang.String activityDescription) {
        this.activityDescription = activityDescription;
    }


    /**
     * Gets the recipientEmailAddress value for this ActivityDetails.
     * 
     * @return recipientEmailAddress
     */
    public java.lang.String getRecipientEmailAddress() {
        return recipientEmailAddress;
    }


    /**
     * Sets the recipientEmailAddress value for this ActivityDetails.
     * 
     * @param recipientEmailAddress
     */
    public void setRecipientEmailAddress(java.lang.String recipientEmailAddress) {
        this.recipientEmailAddress = recipientEmailAddress;
    }


    /**
     * Gets the plannedStartTime value for this ActivityDetails.
     * 
     * @return plannedStartTime
     */
    public java.lang.String getPlannedStartTime() {
        return plannedStartTime;
    }


    /**
     * Sets the plannedStartTime value for this ActivityDetails.
     * 
     * @param plannedStartTime
     */
    public void setPlannedStartTime(java.lang.String plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }


    /**
     * Gets the plannedCompletionTime value for this ActivityDetails.
     * 
     * @return plannedCompletionTime
     */
    public java.lang.String getPlannedCompletionTime() {
        return plannedCompletionTime;
    }


    /**
     * Sets the plannedCompletionTime value for this ActivityDetails.
     * 
     * @param plannedCompletionTime
     */
    public void setPlannedCompletionTime(java.lang.String plannedCompletionTime) {
        this.plannedCompletionTime = plannedCompletionTime;
    }


    /**
     * Gets the assignedTo value for this ActivityDetails.
     * 
     * @return assignedTo
     */
    public java.lang.String getAssignedTo() {
        return assignedTo;
    }


    /**
     * Sets the assignedTo value for this ActivityDetails.
     * 
     * @param assignedTo
     */
    public void setAssignedTo(java.lang.String assignedTo) {
        this.assignedTo = assignedTo;
    }


    /**
     * Gets the priority value for this ActivityDetails.
     * 
     * @return priority
     */
    public java.lang.String getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this ActivityDetails.
     * 
     * @param priority
     */
    public void setPriority(java.lang.String priority) {
        this.priority = priority;
    }


    /**
     * Gets the severity value for this ActivityDetails.
     * 
     * @return severity
     */
    public java.lang.String getSeverity() {
        return severity;
    }


    /**
     * Sets the severity value for this ActivityDetails.
     * 
     * @param severity
     */
    public void setSeverity(java.lang.String severity) {
        this.severity = severity;
    }


    /**
     * Gets the noSoonerThan value for this ActivityDetails.
     * 
     * @return noSoonerThan
     */
    public java.lang.String getNoSoonerThan() {
        return noSoonerThan;
    }


    /**
     * Sets the noSoonerThan value for this ActivityDetails.
     * 
     * @param noSoonerThan
     */
    public void setNoSoonerThan(java.lang.String noSoonerThan) {
        this.noSoonerThan = noSoonerThan;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ActivityDetails)) return false;
        ActivityDetails other = (ActivityDetails) obj;
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
            ((this.activityType==null && other.getActivityType()==null) || 
             (this.activityType!=null &&
              this.activityType.equals(other.getActivityType()))) &&
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
              this.recipientEmailAddress.equals(other.getRecipientEmailAddress()))) &&
            ((this.plannedStartTime==null && other.getPlannedStartTime()==null) || 
             (this.plannedStartTime!=null &&
              this.plannedStartTime.equals(other.getPlannedStartTime()))) &&
            ((this.plannedCompletionTime==null && other.getPlannedCompletionTime()==null) || 
             (this.plannedCompletionTime!=null &&
              this.plannedCompletionTime.equals(other.getPlannedCompletionTime()))) &&
            ((this.assignedTo==null && other.getAssignedTo()==null) || 
             (this.assignedTo!=null &&
              this.assignedTo.equals(other.getAssignedTo()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.severity==null && other.getSeverity()==null) || 
             (this.severity!=null &&
              this.severity.equals(other.getSeverity()))) &&
            ((this.noSoonerThan==null && other.getNoSoonerThan()==null) || 
             (this.noSoonerThan!=null &&
              this.noSoonerThan.equals(other.getNoSoonerThan())));
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
        if (getActivityType() != null) {
            _hashCode += getActivityType().hashCode();
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
        if (getPlannedStartTime() != null) {
            _hashCode += getPlannedStartTime().hashCode();
        }
        if (getPlannedCompletionTime() != null) {
            _hashCode += getPlannedCompletionTime().hashCode();
        }
        if (getAssignedTo() != null) {
            _hashCode += getAssignedTo().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getSeverity() != null) {
            _hashCode += getSeverity().hashCode();
        }
        if (getNoSoonerThan() != null) {
            _hashCode += getNoSoonerThan().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ActivityDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityType"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plannedStartTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PlannedStartTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plannedCompletionTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PlannedCompletionTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assignedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssignedTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("severity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Severity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noSoonerThan");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NoSoonerThan"));
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
