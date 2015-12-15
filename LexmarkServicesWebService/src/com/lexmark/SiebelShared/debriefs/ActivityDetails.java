/**
 * ActivityDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;

public class ActivityDetails  implements java.io.Serializable {
    private java.lang.String activityID;

    private java.lang.String activityType;

    private java.lang.String activityComments;

    private com.lexmark.SiebelShared.debriefs.RecommendedPartDetails[] recommendedPartDetails;

    private com.lexmark.SiebelShared.debriefs.SiebelAssetManagementInformation assetInformation;

    private com.lexmark.SiebelShared.debriefs.RelatedActivityInformation[] relatedActivityInformation;

    public ActivityDetails() {
    }

    public ActivityDetails(
           java.lang.String activityID,
           java.lang.String activityType,
           java.lang.String activityComments,
           com.lexmark.SiebelShared.debriefs.RecommendedPartDetails[] recommendedPartDetails,
           com.lexmark.SiebelShared.debriefs.SiebelAssetManagementInformation assetInformation,
           com.lexmark.SiebelShared.debriefs.RelatedActivityInformation[] relatedActivityInformation) {
           this.activityID = activityID;
           this.activityType = activityType;
           this.activityComments = activityComments;
           this.recommendedPartDetails = recommendedPartDetails;
           this.assetInformation = assetInformation;
           this.relatedActivityInformation = relatedActivityInformation;
    }


    /**
     * Gets the activityID value for this ActivityDetails.
     * 
     * @return activityID
     */
    public java.lang.String getActivityID() {
        return activityID;
    }


    /**
     * Sets the activityID value for this ActivityDetails.
     * 
     * @param activityID
     */
    public void setActivityID(java.lang.String activityID) {
        this.activityID = activityID;
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
     * Gets the activityComments value for this ActivityDetails.
     * 
     * @return activityComments
     */
    public java.lang.String getActivityComments() {
        return activityComments;
    }


    /**
     * Sets the activityComments value for this ActivityDetails.
     * 
     * @param activityComments
     */
    public void setActivityComments(java.lang.String activityComments) {
        this.activityComments = activityComments;
    }


    /**
     * Gets the recommendedPartDetails value for this ActivityDetails.
     * 
     * @return recommendedPartDetails
     */
    public com.lexmark.SiebelShared.debriefs.RecommendedPartDetails[] getRecommendedPartDetails() {
        return recommendedPartDetails;
    }


    /**
     * Sets the recommendedPartDetails value for this ActivityDetails.
     * 
     * @param recommendedPartDetails
     */
    public void setRecommendedPartDetails(com.lexmark.SiebelShared.debriefs.RecommendedPartDetails[] recommendedPartDetails) {
        this.recommendedPartDetails = recommendedPartDetails;
    }

    public com.lexmark.SiebelShared.debriefs.RecommendedPartDetails getRecommendedPartDetails(int i) {
        return this.recommendedPartDetails[i];
    }

    public void setRecommendedPartDetails(int i, com.lexmark.SiebelShared.debriefs.RecommendedPartDetails _value) {
        this.recommendedPartDetails[i] = _value;
    }


    /**
     * Gets the assetInformation value for this ActivityDetails.
     * 
     * @return assetInformation
     */
    public com.lexmark.SiebelShared.debriefs.SiebelAssetManagementInformation getAssetInformation() {
        return assetInformation;
    }


    /**
     * Sets the assetInformation value for this ActivityDetails.
     * 
     * @param assetInformation
     */
    public void setAssetInformation(com.lexmark.SiebelShared.debriefs.SiebelAssetManagementInformation assetInformation) {
        this.assetInformation = assetInformation;
    }


    /**
     * Gets the relatedActivityInformation value for this ActivityDetails.
     * 
     * @return relatedActivityInformation
     */
    public com.lexmark.SiebelShared.debriefs.RelatedActivityInformation[] getRelatedActivityInformation() {
        return relatedActivityInformation;
    }


    /**
     * Sets the relatedActivityInformation value for this ActivityDetails.
     * 
     * @param relatedActivityInformation
     */
    public void setRelatedActivityInformation(com.lexmark.SiebelShared.debriefs.RelatedActivityInformation[] relatedActivityInformation) {
        this.relatedActivityInformation = relatedActivityInformation;
    }

    public com.lexmark.SiebelShared.debriefs.RelatedActivityInformation getRelatedActivityInformation(int i) {
        return this.relatedActivityInformation[i];
    }

    public void setRelatedActivityInformation(int i, com.lexmark.SiebelShared.debriefs.RelatedActivityInformation _value) {
        this.relatedActivityInformation[i] = _value;
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
            ((this.activityID==null && other.getActivityID()==null) || 
             (this.activityID!=null &&
              this.activityID.equals(other.getActivityID()))) &&
            ((this.activityType==null && other.getActivityType()==null) || 
             (this.activityType!=null &&
              this.activityType.equals(other.getActivityType()))) &&
            ((this.activityComments==null && other.getActivityComments()==null) || 
             (this.activityComments!=null &&
              this.activityComments.equals(other.getActivityComments()))) &&
            ((this.recommendedPartDetails==null && other.getRecommendedPartDetails()==null) || 
             (this.recommendedPartDetails!=null &&
              java.util.Arrays.equals(this.recommendedPartDetails, other.getRecommendedPartDetails()))) &&
            ((this.assetInformation==null && other.getAssetInformation()==null) || 
             (this.assetInformation!=null &&
              this.assetInformation.equals(other.getAssetInformation()))) &&
            ((this.relatedActivityInformation==null && other.getRelatedActivityInformation()==null) || 
             (this.relatedActivityInformation!=null &&
              java.util.Arrays.equals(this.relatedActivityInformation, other.getRelatedActivityInformation())));
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
        if (getActivityID() != null) {
            _hashCode += getActivityID().hashCode();
        }
        if (getActivityType() != null) {
            _hashCode += getActivityType().hashCode();
        }
        if (getActivityComments() != null) {
            _hashCode += getActivityComments().hashCode();
        }
        if (getRecommendedPartDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecommendedPartDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecommendedPartDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAssetInformation() != null) {
            _hashCode += getAssetInformation().hashCode();
        }
        if (getRelatedActivityInformation() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRelatedActivityInformation());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRelatedActivityInformation(), i);
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
        new org.apache.axis.description.TypeDesc(ActivityDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "ActivityDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("recommendedPartDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RecommendedPartDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "RecommendedPartDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "SiebelAssetManagementInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedActivityInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelatedActivityInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "RelatedActivityInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
