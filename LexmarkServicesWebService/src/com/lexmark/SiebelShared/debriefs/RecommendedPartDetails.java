/**
 * RecommendedPartDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;

public class RecommendedPartDetails  implements java.io.Serializable {
    private java.lang.String activityLineNumber;

    private java.lang.String partNumber;

    private java.lang.String partDescription;

    private java.lang.String quantity;

    private java.lang.String relationType;

    public RecommendedPartDetails() {
    }

    public RecommendedPartDetails(
           java.lang.String activityLineNumber,
           java.lang.String partNumber,
           java.lang.String partDescription,
           java.lang.String quantity,
           java.lang.String relationType) {
           this.activityLineNumber = activityLineNumber;
           this.partNumber = partNumber;
           this.partDescription = partDescription;
           this.quantity = quantity;
           this.relationType = relationType;
    }


    /**
     * Gets the activityLineNumber value for this RecommendedPartDetails.
     * 
     * @return activityLineNumber
     */
    public java.lang.String getActivityLineNumber() {
        return activityLineNumber;
    }


    /**
     * Sets the activityLineNumber value for this RecommendedPartDetails.
     * 
     * @param activityLineNumber
     */
    public void setActivityLineNumber(java.lang.String activityLineNumber) {
        this.activityLineNumber = activityLineNumber;
    }


    /**
     * Gets the partNumber value for this RecommendedPartDetails.
     * 
     * @return partNumber
     */
    public java.lang.String getPartNumber() {
        return partNumber;
    }


    /**
     * Sets the partNumber value for this RecommendedPartDetails.
     * 
     * @param partNumber
     */
    public void setPartNumber(java.lang.String partNumber) {
        this.partNumber = partNumber;
    }


    /**
     * Gets the partDescription value for this RecommendedPartDetails.
     * 
     * @return partDescription
     */
    public java.lang.String getPartDescription() {
        return partDescription;
    }


    /**
     * Sets the partDescription value for this RecommendedPartDetails.
     * 
     * @param partDescription
     */
    public void setPartDescription(java.lang.String partDescription) {
        this.partDescription = partDescription;
    }


    /**
     * Gets the quantity value for this RecommendedPartDetails.
     * 
     * @return quantity
     */
    public java.lang.String getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this RecommendedPartDetails.
     * 
     * @param quantity
     */
    public void setQuantity(java.lang.String quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the relationType value for this RecommendedPartDetails.
     * 
     * @return relationType
     */
    public java.lang.String getRelationType() {
        return relationType;
    }


    /**
     * Sets the relationType value for this RecommendedPartDetails.
     * 
     * @param relationType
     */
    public void setRelationType(java.lang.String relationType) {
        this.relationType = relationType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecommendedPartDetails)) return false;
        RecommendedPartDetails other = (RecommendedPartDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.activityLineNumber==null && other.getActivityLineNumber()==null) || 
             (this.activityLineNumber!=null &&
              this.activityLineNumber.equals(other.getActivityLineNumber()))) &&
            ((this.partNumber==null && other.getPartNumber()==null) || 
             (this.partNumber!=null &&
              this.partNumber.equals(other.getPartNumber()))) &&
            ((this.partDescription==null && other.getPartDescription()==null) || 
             (this.partDescription!=null &&
              this.partDescription.equals(other.getPartDescription()))) &&
            ((this.quantity==null && other.getQuantity()==null) || 
             (this.quantity!=null &&
              this.quantity.equals(other.getQuantity()))) &&
            ((this.relationType==null && other.getRelationType()==null) || 
             (this.relationType!=null &&
              this.relationType.equals(other.getRelationType())));
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
        if (getActivityLineNumber() != null) {
            _hashCode += getActivityLineNumber().hashCode();
        }
        if (getPartNumber() != null) {
            _hashCode += getPartNumber().hashCode();
        }
        if (getPartDescription() != null) {
            _hashCode += getPartDescription().hashCode();
        }
        if (getQuantity() != null) {
            _hashCode += getQuantity().hashCode();
        }
        if (getRelationType() != null) {
            _hashCode += getRelationType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecommendedPartDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "RecommendedPartDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityLineNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityLineNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PartDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
