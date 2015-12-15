/**
 * DebriefDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class DebriefDetails  implements java.io.Serializable {
    private java.lang.String actualStartDate;

    private java.lang.String actualEndDate;

    private java.lang.String travelDistance;

    private java.lang.String travelDistanceUnitOfMeasure;

    private java.lang.String travelDuration;

    private java.lang.String travelDurationUnitOfMeasure;

    private java.lang.String problemCode;

    private java.lang.String problemDetails;

    private java.lang.String resolutionCode;

    private java.lang.String repairDescription;

    private java.lang.String additionalServiceRequired;

    private java.lang.String nonLexmarkSuppliesUsed;

    private java.lang.String supplyManufacturer;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OriginalDeviceInformation originalDeviceInformation;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ReplacementDeviceInformation replacementDeviceInformation;

    public DebriefDetails() {
    }

    public DebriefDetails(
           java.lang.String actualStartDate,
           java.lang.String actualEndDate,
           java.lang.String travelDistance,
           java.lang.String travelDistanceUnitOfMeasure,
           java.lang.String travelDuration,
           java.lang.String travelDurationUnitOfMeasure,
           java.lang.String problemCode,
           java.lang.String problemDetails,
           java.lang.String resolutionCode,
           java.lang.String repairDescription,
           java.lang.String additionalServiceRequired,
           java.lang.String nonLexmarkSuppliesUsed,
           java.lang.String supplyManufacturer,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OriginalDeviceInformation originalDeviceInformation,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ReplacementDeviceInformation replacementDeviceInformation) {
           this.actualStartDate = actualStartDate;
           this.actualEndDate = actualEndDate;
           this.travelDistance = travelDistance;
           this.travelDistanceUnitOfMeasure = travelDistanceUnitOfMeasure;
           this.travelDuration = travelDuration;
           this.travelDurationUnitOfMeasure = travelDurationUnitOfMeasure;
           this.problemCode = problemCode;
           this.problemDetails = problemDetails;
           this.resolutionCode = resolutionCode;
           this.repairDescription = repairDescription;
           this.additionalServiceRequired = additionalServiceRequired;
           this.nonLexmarkSuppliesUsed = nonLexmarkSuppliesUsed;
           this.supplyManufacturer = supplyManufacturer;
           this.originalDeviceInformation = originalDeviceInformation;
           this.replacementDeviceInformation = replacementDeviceInformation;
    }


    /**
     * Gets the actualStartDate value for this DebriefDetails.
     * 
     * @return actualStartDate
     */
    public java.lang.String getActualStartDate() {
        return actualStartDate;
    }


    /**
     * Sets the actualStartDate value for this DebriefDetails.
     * 
     * @param actualStartDate
     */
    public void setActualStartDate(java.lang.String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }


    /**
     * Gets the actualEndDate value for this DebriefDetails.
     * 
     * @return actualEndDate
     */
    public java.lang.String getActualEndDate() {
        return actualEndDate;
    }


    /**
     * Sets the actualEndDate value for this DebriefDetails.
     * 
     * @param actualEndDate
     */
    public void setActualEndDate(java.lang.String actualEndDate) {
        this.actualEndDate = actualEndDate;
    }


    /**
     * Gets the travelDistance value for this DebriefDetails.
     * 
     * @return travelDistance
     */
    public java.lang.String getTravelDistance() {
        return travelDistance;
    }


    /**
     * Sets the travelDistance value for this DebriefDetails.
     * 
     * @param travelDistance
     */
    public void setTravelDistance(java.lang.String travelDistance) {
        this.travelDistance = travelDistance;
    }


    /**
     * Gets the travelDistanceUnitOfMeasure value for this DebriefDetails.
     * 
     * @return travelDistanceUnitOfMeasure
     */
    public java.lang.String getTravelDistanceUnitOfMeasure() {
        return travelDistanceUnitOfMeasure;
    }


    /**
     * Sets the travelDistanceUnitOfMeasure value for this DebriefDetails.
     * 
     * @param travelDistanceUnitOfMeasure
     */
    public void setTravelDistanceUnitOfMeasure(java.lang.String travelDistanceUnitOfMeasure) {
        this.travelDistanceUnitOfMeasure = travelDistanceUnitOfMeasure;
    }


    /**
     * Gets the travelDuration value for this DebriefDetails.
     * 
     * @return travelDuration
     */
    public java.lang.String getTravelDuration() {
        return travelDuration;
    }


    /**
     * Sets the travelDuration value for this DebriefDetails.
     * 
     * @param travelDuration
     */
    public void setTravelDuration(java.lang.String travelDuration) {
        this.travelDuration = travelDuration;
    }


    /**
     * Gets the travelDurationUnitOfMeasure value for this DebriefDetails.
     * 
     * @return travelDurationUnitOfMeasure
     */
    public java.lang.String getTravelDurationUnitOfMeasure() {
        return travelDurationUnitOfMeasure;
    }


    /**
     * Sets the travelDurationUnitOfMeasure value for this DebriefDetails.
     * 
     * @param travelDurationUnitOfMeasure
     */
    public void setTravelDurationUnitOfMeasure(java.lang.String travelDurationUnitOfMeasure) {
        this.travelDurationUnitOfMeasure = travelDurationUnitOfMeasure;
    }


    /**
     * Gets the problemCode value for this DebriefDetails.
     * 
     * @return problemCode
     */
    public java.lang.String getProblemCode() {
        return problemCode;
    }


    /**
     * Sets the problemCode value for this DebriefDetails.
     * 
     * @param problemCode
     */
    public void setProblemCode(java.lang.String problemCode) {
        this.problemCode = problemCode;
    }


    /**
     * Gets the problemDetails value for this DebriefDetails.
     * 
     * @return problemDetails
     */
    public java.lang.String getProblemDetails() {
        return problemDetails;
    }


    /**
     * Sets the problemDetails value for this DebriefDetails.
     * 
     * @param problemDetails
     */
    public void setProblemDetails(java.lang.String problemDetails) {
        this.problemDetails = problemDetails;
    }


    /**
     * Gets the resolutionCode value for this DebriefDetails.
     * 
     * @return resolutionCode
     */
    public java.lang.String getResolutionCode() {
        return resolutionCode;
    }


    /**
     * Sets the resolutionCode value for this DebriefDetails.
     * 
     * @param resolutionCode
     */
    public void setResolutionCode(java.lang.String resolutionCode) {
        this.resolutionCode = resolutionCode;
    }


    /**
     * Gets the repairDescription value for this DebriefDetails.
     * 
     * @return repairDescription
     */
    public java.lang.String getRepairDescription() {
        return repairDescription;
    }


    /**
     * Sets the repairDescription value for this DebriefDetails.
     * 
     * @param repairDescription
     */
    public void setRepairDescription(java.lang.String repairDescription) {
        this.repairDescription = repairDescription;
    }


    /**
     * Gets the additionalServiceRequired value for this DebriefDetails.
     * 
     * @return additionalServiceRequired
     */
    public java.lang.String getAdditionalServiceRequired() {
        return additionalServiceRequired;
    }


    /**
     * Sets the additionalServiceRequired value for this DebriefDetails.
     * 
     * @param additionalServiceRequired
     */
    public void setAdditionalServiceRequired(java.lang.String additionalServiceRequired) {
        this.additionalServiceRequired = additionalServiceRequired;
    }


    /**
     * Gets the nonLexmarkSuppliesUsed value for this DebriefDetails.
     * 
     * @return nonLexmarkSuppliesUsed
     */
    public java.lang.String getNonLexmarkSuppliesUsed() {
        return nonLexmarkSuppliesUsed;
    }


    /**
     * Sets the nonLexmarkSuppliesUsed value for this DebriefDetails.
     * 
     * @param nonLexmarkSuppliesUsed
     */
    public void setNonLexmarkSuppliesUsed(java.lang.String nonLexmarkSuppliesUsed) {
        this.nonLexmarkSuppliesUsed = nonLexmarkSuppliesUsed;
    }


    /**
     * Gets the supplyManufacturer value for this DebriefDetails.
     * 
     * @return supplyManufacturer
     */
    public java.lang.String getSupplyManufacturer() {
        return supplyManufacturer;
    }


    /**
     * Sets the supplyManufacturer value for this DebriefDetails.
     * 
     * @param supplyManufacturer
     */
    public void setSupplyManufacturer(java.lang.String supplyManufacturer) {
        this.supplyManufacturer = supplyManufacturer;
    }


    /**
     * Gets the originalDeviceInformation value for this DebriefDetails.
     * 
     * @return originalDeviceInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OriginalDeviceInformation getOriginalDeviceInformation() {
        return originalDeviceInformation;
    }


    /**
     * Sets the originalDeviceInformation value for this DebriefDetails.
     * 
     * @param originalDeviceInformation
     */
    public void setOriginalDeviceInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.OriginalDeviceInformation originalDeviceInformation) {
        this.originalDeviceInformation = originalDeviceInformation;
    }


    /**
     * Gets the replacementDeviceInformation value for this DebriefDetails.
     * 
     * @return replacementDeviceInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ReplacementDeviceInformation getReplacementDeviceInformation() {
        return replacementDeviceInformation;
    }


    /**
     * Sets the replacementDeviceInformation value for this DebriefDetails.
     * 
     * @param replacementDeviceInformation
     */
    public void setReplacementDeviceInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.ReplacementDeviceInformation replacementDeviceInformation) {
        this.replacementDeviceInformation = replacementDeviceInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DebriefDetails)) return false;
        DebriefDetails other = (DebriefDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actualStartDate==null && other.getActualStartDate()==null) || 
             (this.actualStartDate!=null &&
              this.actualStartDate.equals(other.getActualStartDate()))) &&
            ((this.actualEndDate==null && other.getActualEndDate()==null) || 
             (this.actualEndDate!=null &&
              this.actualEndDate.equals(other.getActualEndDate()))) &&
            ((this.travelDistance==null && other.getTravelDistance()==null) || 
             (this.travelDistance!=null &&
              this.travelDistance.equals(other.getTravelDistance()))) &&
            ((this.travelDistanceUnitOfMeasure==null && other.getTravelDistanceUnitOfMeasure()==null) || 
             (this.travelDistanceUnitOfMeasure!=null &&
              this.travelDistanceUnitOfMeasure.equals(other.getTravelDistanceUnitOfMeasure()))) &&
            ((this.travelDuration==null && other.getTravelDuration()==null) || 
             (this.travelDuration!=null &&
              this.travelDuration.equals(other.getTravelDuration()))) &&
            ((this.travelDurationUnitOfMeasure==null && other.getTravelDurationUnitOfMeasure()==null) || 
             (this.travelDurationUnitOfMeasure!=null &&
              this.travelDurationUnitOfMeasure.equals(other.getTravelDurationUnitOfMeasure()))) &&
            ((this.problemCode==null && other.getProblemCode()==null) || 
             (this.problemCode!=null &&
              this.problemCode.equals(other.getProblemCode()))) &&
            ((this.problemDetails==null && other.getProblemDetails()==null) || 
             (this.problemDetails!=null &&
              this.problemDetails.equals(other.getProblemDetails()))) &&
            ((this.resolutionCode==null && other.getResolutionCode()==null) || 
             (this.resolutionCode!=null &&
              this.resolutionCode.equals(other.getResolutionCode()))) &&
            ((this.repairDescription==null && other.getRepairDescription()==null) || 
             (this.repairDescription!=null &&
              this.repairDescription.equals(other.getRepairDescription()))) &&
            ((this.additionalServiceRequired==null && other.getAdditionalServiceRequired()==null) || 
             (this.additionalServiceRequired!=null &&
              this.additionalServiceRequired.equals(other.getAdditionalServiceRequired()))) &&
            ((this.nonLexmarkSuppliesUsed==null && other.getNonLexmarkSuppliesUsed()==null) || 
             (this.nonLexmarkSuppliesUsed!=null &&
              this.nonLexmarkSuppliesUsed.equals(other.getNonLexmarkSuppliesUsed()))) &&
            ((this.supplyManufacturer==null && other.getSupplyManufacturer()==null) || 
             (this.supplyManufacturer!=null &&
              this.supplyManufacturer.equals(other.getSupplyManufacturer()))) &&
            ((this.originalDeviceInformation==null && other.getOriginalDeviceInformation()==null) || 
             (this.originalDeviceInformation!=null &&
              this.originalDeviceInformation.equals(other.getOriginalDeviceInformation()))) &&
            ((this.replacementDeviceInformation==null && other.getReplacementDeviceInformation()==null) || 
             (this.replacementDeviceInformation!=null &&
              this.replacementDeviceInformation.equals(other.getReplacementDeviceInformation())));
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
        if (getActualStartDate() != null) {
            _hashCode += getActualStartDate().hashCode();
        }
        if (getActualEndDate() != null) {
            _hashCode += getActualEndDate().hashCode();
        }
        if (getTravelDistance() != null) {
            _hashCode += getTravelDistance().hashCode();
        }
        if (getTravelDistanceUnitOfMeasure() != null) {
            _hashCode += getTravelDistanceUnitOfMeasure().hashCode();
        }
        if (getTravelDuration() != null) {
            _hashCode += getTravelDuration().hashCode();
        }
        if (getTravelDurationUnitOfMeasure() != null) {
            _hashCode += getTravelDurationUnitOfMeasure().hashCode();
        }
        if (getProblemCode() != null) {
            _hashCode += getProblemCode().hashCode();
        }
        if (getProblemDetails() != null) {
            _hashCode += getProblemDetails().hashCode();
        }
        if (getResolutionCode() != null) {
            _hashCode += getResolutionCode().hashCode();
        }
        if (getRepairDescription() != null) {
            _hashCode += getRepairDescription().hashCode();
        }
        if (getAdditionalServiceRequired() != null) {
            _hashCode += getAdditionalServiceRequired().hashCode();
        }
        if (getNonLexmarkSuppliesUsed() != null) {
            _hashCode += getNonLexmarkSuppliesUsed().hashCode();
        }
        if (getSupplyManufacturer() != null) {
            _hashCode += getSupplyManufacturer().hashCode();
        }
        if (getOriginalDeviceInformation() != null) {
            _hashCode += getOriginalDeviceInformation().hashCode();
        }
        if (getReplacementDeviceInformation() != null) {
            _hashCode += getReplacementDeviceInformation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DebriefDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DebriefDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualStartDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActualStartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActualEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelDistance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TravelDistance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelDistanceUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TravelDistanceUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelDuration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TravelDuration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelDurationUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TravelDurationUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProblemCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problemDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProblemDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resolutionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ResolutionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repairDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RepairDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalServiceRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AdditionalServiceRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nonLexmarkSuppliesUsed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NonLexmarkSuppliesUsed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supplyManufacturer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SupplyManufacturer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalDeviceInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OriginalDeviceInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OriginalDeviceInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replacementDeviceInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReplacementDeviceInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReplacementDeviceInformation"));
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
