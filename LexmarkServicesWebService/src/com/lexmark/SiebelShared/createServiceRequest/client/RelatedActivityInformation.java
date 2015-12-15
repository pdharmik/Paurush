/**
 * RelatedActivityInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class RelatedActivityInformation  implements java.io.Serializable {
    private java.lang.String activityType;

    private java.lang.String activityComments;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetManagementInformation assetInformation;

    public RelatedActivityInformation() {
    }

    public RelatedActivityInformation(
           java.lang.String activityType,
           java.lang.String activityComments,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetManagementInformation assetInformation) {
           this.activityType = activityType;
           this.activityComments = activityComments;
           this.assetInformation = assetInformation;
    }


    /**
     * Gets the activityType value for this RelatedActivityInformation.
     * 
     * @return activityType
     */
    public java.lang.String getActivityType() {
        return activityType;
    }


    /**
     * Sets the activityType value for this RelatedActivityInformation.
     * 
     * @param activityType
     */
    public void setActivityType(java.lang.String activityType) {
        this.activityType = activityType;
    }


    /**
     * Gets the activityComments value for this RelatedActivityInformation.
     * 
     * @return activityComments
     */
    public java.lang.String getActivityComments() {
        return activityComments;
    }


    /**
     * Sets the activityComments value for this RelatedActivityInformation.
     * 
     * @param activityComments
     */
    public void setActivityComments(java.lang.String activityComments) {
        this.activityComments = activityComments;
    }


    /**
     * Gets the assetInformation value for this RelatedActivityInformation.
     * 
     * @return assetInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetManagementInformation getAssetInformation() {
        return assetInformation;
    }


    /**
     * Sets the assetInformation value for this RelatedActivityInformation.
     * 
     * @param assetInformation
     */
    public void setAssetInformation(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAssetManagementInformation assetInformation) {
        this.assetInformation = assetInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RelatedActivityInformation)) return false;
        RelatedActivityInformation other = (RelatedActivityInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.activityType==null && other.getActivityType()==null) || 
             (this.activityType!=null &&
              this.activityType.equals(other.getActivityType()))) &&
            ((this.activityComments==null && other.getActivityComments()==null) || 
             (this.activityComments!=null &&
              this.activityComments.equals(other.getActivityComments()))) &&
            ((this.assetInformation==null && other.getAssetInformation()==null) || 
             (this.assetInformation!=null &&
              this.assetInformation.equals(other.getAssetInformation())));
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
        if (getActivityType() != null) {
            _hashCode += getActivityType().hashCode();
        }
        if (getActivityComments() != null) {
            _hashCode += getActivityComments().hashCode();
        }
        if (getAssetInformation() != null) {
            _hashCode += getAssetInformation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RelatedActivityInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "RelatedActivityInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityComments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityComments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAssetManagementInformation"));
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
