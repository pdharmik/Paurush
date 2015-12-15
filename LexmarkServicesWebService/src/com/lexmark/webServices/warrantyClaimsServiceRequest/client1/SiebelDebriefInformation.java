/**
 * SiebelDebriefInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public class SiebelDebriefInformation  implements java.io.Serializable {
    private java.lang.String repairDescription;

    private java.lang.String resolutionCode;

    private java.lang.String serviceRequestedDate;

    private java.lang.String serviceStartDate;

    private java.lang.String serviceEndDate;

    private java.lang.String debriefPartStatus;

    private java.lang.String pageCount;

    public SiebelDebriefInformation() {
    }

    public SiebelDebriefInformation(
           java.lang.String repairDescription,
           java.lang.String resolutionCode,
           java.lang.String serviceRequestedDate,
           java.lang.String serviceStartDate,
           java.lang.String serviceEndDate,
           java.lang.String debriefPartStatus,
           java.lang.String pageCount) {
           this.repairDescription = repairDescription;
           this.resolutionCode = resolutionCode;
           this.serviceRequestedDate = serviceRequestedDate;
           this.serviceStartDate = serviceStartDate;
           this.serviceEndDate = serviceEndDate;
           this.debriefPartStatus = debriefPartStatus;
           this.pageCount = pageCount;
    }


    /**
     * Gets the repairDescription value for this SiebelDebriefInformation.
     * 
     * @return repairDescription
     */
    public java.lang.String getRepairDescription() {
        return repairDescription;
    }


    /**
     * Sets the repairDescription value for this SiebelDebriefInformation.
     * 
     * @param repairDescription
     */
    public void setRepairDescription(java.lang.String repairDescription) {
        this.repairDescription = repairDescription;
    }


    /**
     * Gets the resolutionCode value for this SiebelDebriefInformation.
     * 
     * @return resolutionCode
     */
    public java.lang.String getResolutionCode() {
        return resolutionCode;
    }


    /**
     * Sets the resolutionCode value for this SiebelDebriefInformation.
     * 
     * @param resolutionCode
     */
    public void setResolutionCode(java.lang.String resolutionCode) {
        this.resolutionCode = resolutionCode;
    }


    /**
     * Gets the serviceRequestedDate value for this SiebelDebriefInformation.
     * 
     * @return serviceRequestedDate
     */
    public java.lang.String getServiceRequestedDate() {
        return serviceRequestedDate;
    }


    /**
     * Sets the serviceRequestedDate value for this SiebelDebriefInformation.
     * 
     * @param serviceRequestedDate
     */
    public void setServiceRequestedDate(java.lang.String serviceRequestedDate) {
        this.serviceRequestedDate = serviceRequestedDate;
    }


    /**
     * Gets the serviceStartDate value for this SiebelDebriefInformation.
     * 
     * @return serviceStartDate
     */
    public java.lang.String getServiceStartDate() {
        return serviceStartDate;
    }


    /**
     * Sets the serviceStartDate value for this SiebelDebriefInformation.
     * 
     * @param serviceStartDate
     */
    public void setServiceStartDate(java.lang.String serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }


    /**
     * Gets the serviceEndDate value for this SiebelDebriefInformation.
     * 
     * @return serviceEndDate
     */
    public java.lang.String getServiceEndDate() {
        return serviceEndDate;
    }


    /**
     * Sets the serviceEndDate value for this SiebelDebriefInformation.
     * 
     * @param serviceEndDate
     */
    public void setServiceEndDate(java.lang.String serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }


    /**
     * Gets the debriefPartStatus value for this SiebelDebriefInformation.
     * 
     * @return debriefPartStatus
     */
    public java.lang.String getDebriefPartStatus() {
        return debriefPartStatus;
    }


    /**
     * Sets the debriefPartStatus value for this SiebelDebriefInformation.
     * 
     * @param debriefPartStatus
     */
    public void setDebriefPartStatus(java.lang.String debriefPartStatus) {
        this.debriefPartStatus = debriefPartStatus;
    }


    /**
     * Gets the pageCount value for this SiebelDebriefInformation.
     * 
     * @return pageCount
     */
    public java.lang.String getPageCount() {
        return pageCount;
    }


    /**
     * Sets the pageCount value for this SiebelDebriefInformation.
     * 
     * @param pageCount
     */
    public void setPageCount(java.lang.String pageCount) {
        this.pageCount = pageCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelDebriefInformation)) return false;
        SiebelDebriefInformation other = (SiebelDebriefInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.repairDescription==null && other.getRepairDescription()==null) || 
             (this.repairDescription!=null &&
              this.repairDescription.equals(other.getRepairDescription()))) &&
            ((this.resolutionCode==null && other.getResolutionCode()==null) || 
             (this.resolutionCode!=null &&
              this.resolutionCode.equals(other.getResolutionCode()))) &&
            ((this.serviceRequestedDate==null && other.getServiceRequestedDate()==null) || 
             (this.serviceRequestedDate!=null &&
              this.serviceRequestedDate.equals(other.getServiceRequestedDate()))) &&
            ((this.serviceStartDate==null && other.getServiceStartDate()==null) || 
             (this.serviceStartDate!=null &&
              this.serviceStartDate.equals(other.getServiceStartDate()))) &&
            ((this.serviceEndDate==null && other.getServiceEndDate()==null) || 
             (this.serviceEndDate!=null &&
              this.serviceEndDate.equals(other.getServiceEndDate()))) &&
            ((this.debriefPartStatus==null && other.getDebriefPartStatus()==null) || 
             (this.debriefPartStatus!=null &&
              this.debriefPartStatus.equals(other.getDebriefPartStatus()))) &&
            ((this.pageCount==null && other.getPageCount()==null) || 
             (this.pageCount!=null &&
              this.pageCount.equals(other.getPageCount())));
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
        if (getRepairDescription() != null) {
            _hashCode += getRepairDescription().hashCode();
        }
        if (getResolutionCode() != null) {
            _hashCode += getResolutionCode().hashCode();
        }
        if (getServiceRequestedDate() != null) {
            _hashCode += getServiceRequestedDate().hashCode();
        }
        if (getServiceStartDate() != null) {
            _hashCode += getServiceStartDate().hashCode();
        }
        if (getServiceEndDate() != null) {
            _hashCode += getServiceEndDate().hashCode();
        }
        if (getDebriefPartStatus() != null) {
            _hashCode += getDebriefPartStatus().hashCode();
        }
        if (getPageCount() != null) {
            _hashCode += getPageCount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelDebriefInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelDebriefInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repairDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RepairDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resolutionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ResolutionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceStartDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceStartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debriefPartStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DebriefPartStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PageCount"));
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
