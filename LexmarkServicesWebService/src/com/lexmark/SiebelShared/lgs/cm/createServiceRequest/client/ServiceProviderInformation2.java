/**
 * ServiceProviderInformation2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ServiceProviderInformation2  implements java.io.Serializable {
    private java.lang.String serviceProviderName;

    private java.lang.String serviceProvideInstructions;

    private java.lang.String serviceProviderReferenceNumber;

    private java.lang.String serviceProviderEstimatedTimeOfArrival;

    private java.lang.String serviceProviderRequestedResponseDate;

    private java.lang.String serviceProviderStatus;

    private java.lang.String serviceProviderStatusAsOfDate;

    private java.lang.String serviceProviderComments;

    public ServiceProviderInformation2() {
    }

    public ServiceProviderInformation2(
           java.lang.String serviceProviderName,
           java.lang.String serviceProvideInstructions,
           java.lang.String serviceProviderReferenceNumber,
           java.lang.String serviceProviderEstimatedTimeOfArrival,
           java.lang.String serviceProviderRequestedResponseDate,
           java.lang.String serviceProviderStatus,
           java.lang.String serviceProviderStatusAsOfDate,
           java.lang.String serviceProviderComments) {
           this.serviceProviderName = serviceProviderName;
           this.serviceProvideInstructions = serviceProvideInstructions;
           this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
           this.serviceProviderEstimatedTimeOfArrival = serviceProviderEstimatedTimeOfArrival;
           this.serviceProviderRequestedResponseDate = serviceProviderRequestedResponseDate;
           this.serviceProviderStatus = serviceProviderStatus;
           this.serviceProviderStatusAsOfDate = serviceProviderStatusAsOfDate;
           this.serviceProviderComments = serviceProviderComments;
    }


    /**
     * Gets the serviceProviderName value for this ServiceProviderInformation2.
     * 
     * @return serviceProviderName
     */
    public java.lang.String getServiceProviderName() {
        return serviceProviderName;
    }


    /**
     * Sets the serviceProviderName value for this ServiceProviderInformation2.
     * 
     * @param serviceProviderName
     */
    public void setServiceProviderName(java.lang.String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }


    /**
     * Gets the serviceProvideInstructions value for this ServiceProviderInformation2.
     * 
     * @return serviceProvideInstructions
     */
    public java.lang.String getServiceProvideInstructions() {
        return serviceProvideInstructions;
    }


    /**
     * Sets the serviceProvideInstructions value for this ServiceProviderInformation2.
     * 
     * @param serviceProvideInstructions
     */
    public void setServiceProvideInstructions(java.lang.String serviceProvideInstructions) {
        this.serviceProvideInstructions = serviceProvideInstructions;
    }


    /**
     * Gets the serviceProviderReferenceNumber value for this ServiceProviderInformation2.
     * 
     * @return serviceProviderReferenceNumber
     */
    public java.lang.String getServiceProviderReferenceNumber() {
        return serviceProviderReferenceNumber;
    }


    /**
     * Sets the serviceProviderReferenceNumber value for this ServiceProviderInformation2.
     * 
     * @param serviceProviderReferenceNumber
     */
    public void setServiceProviderReferenceNumber(java.lang.String serviceProviderReferenceNumber) {
        this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
    }


    /**
     * Gets the serviceProviderEstimatedTimeOfArrival value for this ServiceProviderInformation2.
     * 
     * @return serviceProviderEstimatedTimeOfArrival
     */
    public java.lang.String getServiceProviderEstimatedTimeOfArrival() {
        return serviceProviderEstimatedTimeOfArrival;
    }


    /**
     * Sets the serviceProviderEstimatedTimeOfArrival value for this ServiceProviderInformation2.
     * 
     * @param serviceProviderEstimatedTimeOfArrival
     */
    public void setServiceProviderEstimatedTimeOfArrival(java.lang.String serviceProviderEstimatedTimeOfArrival) {
        this.serviceProviderEstimatedTimeOfArrival = serviceProviderEstimatedTimeOfArrival;
    }


    /**
     * Gets the serviceProviderRequestedResponseDate value for this ServiceProviderInformation2.
     * 
     * @return serviceProviderRequestedResponseDate
     */
    public java.lang.String getServiceProviderRequestedResponseDate() {
        return serviceProviderRequestedResponseDate;
    }


    /**
     * Sets the serviceProviderRequestedResponseDate value for this ServiceProviderInformation2.
     * 
     * @param serviceProviderRequestedResponseDate
     */
    public void setServiceProviderRequestedResponseDate(java.lang.String serviceProviderRequestedResponseDate) {
        this.serviceProviderRequestedResponseDate = serviceProviderRequestedResponseDate;
    }


    /**
     * Gets the serviceProviderStatus value for this ServiceProviderInformation2.
     * 
     * @return serviceProviderStatus
     */
    public java.lang.String getServiceProviderStatus() {
        return serviceProviderStatus;
    }


    /**
     * Sets the serviceProviderStatus value for this ServiceProviderInformation2.
     * 
     * @param serviceProviderStatus
     */
    public void setServiceProviderStatus(java.lang.String serviceProviderStatus) {
        this.serviceProviderStatus = serviceProviderStatus;
    }


    /**
     * Gets the serviceProviderStatusAsOfDate value for this ServiceProviderInformation2.
     * 
     * @return serviceProviderStatusAsOfDate
     */
    public java.lang.String getServiceProviderStatusAsOfDate() {
        return serviceProviderStatusAsOfDate;
    }


    /**
     * Sets the serviceProviderStatusAsOfDate value for this ServiceProviderInformation2.
     * 
     * @param serviceProviderStatusAsOfDate
     */
    public void setServiceProviderStatusAsOfDate(java.lang.String serviceProviderStatusAsOfDate) {
        this.serviceProviderStatusAsOfDate = serviceProviderStatusAsOfDate;
    }


    /**
     * Gets the serviceProviderComments value for this ServiceProviderInformation2.
     * 
     * @return serviceProviderComments
     */
    public java.lang.String getServiceProviderComments() {
        return serviceProviderComments;
    }


    /**
     * Sets the serviceProviderComments value for this ServiceProviderInformation2.
     * 
     * @param serviceProviderComments
     */
    public void setServiceProviderComments(java.lang.String serviceProviderComments) {
        this.serviceProviderComments = serviceProviderComments;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceProviderInformation2)) return false;
        ServiceProviderInformation2 other = (ServiceProviderInformation2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceProviderName==null && other.getServiceProviderName()==null) || 
             (this.serviceProviderName!=null &&
              this.serviceProviderName.equals(other.getServiceProviderName()))) &&
            ((this.serviceProvideInstructions==null && other.getServiceProvideInstructions()==null) || 
             (this.serviceProvideInstructions!=null &&
              this.serviceProvideInstructions.equals(other.getServiceProvideInstructions()))) &&
            ((this.serviceProviderReferenceNumber==null && other.getServiceProviderReferenceNumber()==null) || 
             (this.serviceProviderReferenceNumber!=null &&
              this.serviceProviderReferenceNumber.equals(other.getServiceProviderReferenceNumber()))) &&
            ((this.serviceProviderEstimatedTimeOfArrival==null && other.getServiceProviderEstimatedTimeOfArrival()==null) || 
             (this.serviceProviderEstimatedTimeOfArrival!=null &&
              this.serviceProviderEstimatedTimeOfArrival.equals(other.getServiceProviderEstimatedTimeOfArrival()))) &&
            ((this.serviceProviderRequestedResponseDate==null && other.getServiceProviderRequestedResponseDate()==null) || 
             (this.serviceProviderRequestedResponseDate!=null &&
              this.serviceProviderRequestedResponseDate.equals(other.getServiceProviderRequestedResponseDate()))) &&
            ((this.serviceProviderStatus==null && other.getServiceProviderStatus()==null) || 
             (this.serviceProviderStatus!=null &&
              this.serviceProviderStatus.equals(other.getServiceProviderStatus()))) &&
            ((this.serviceProviderStatusAsOfDate==null && other.getServiceProviderStatusAsOfDate()==null) || 
             (this.serviceProviderStatusAsOfDate!=null &&
              this.serviceProviderStatusAsOfDate.equals(other.getServiceProviderStatusAsOfDate()))) &&
            ((this.serviceProviderComments==null && other.getServiceProviderComments()==null) || 
             (this.serviceProviderComments!=null &&
              this.serviceProviderComments.equals(other.getServiceProviderComments())));
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
        if (getServiceProviderName() != null) {
            _hashCode += getServiceProviderName().hashCode();
        }
        if (getServiceProvideInstructions() != null) {
            _hashCode += getServiceProvideInstructions().hashCode();
        }
        if (getServiceProviderReferenceNumber() != null) {
            _hashCode += getServiceProviderReferenceNumber().hashCode();
        }
        if (getServiceProviderEstimatedTimeOfArrival() != null) {
            _hashCode += getServiceProviderEstimatedTimeOfArrival().hashCode();
        }
        if (getServiceProviderRequestedResponseDate() != null) {
            _hashCode += getServiceProviderRequestedResponseDate().hashCode();
        }
        if (getServiceProviderStatus() != null) {
            _hashCode += getServiceProviderStatus().hashCode();
        }
        if (getServiceProviderStatusAsOfDate() != null) {
            _hashCode += getServiceProviderStatusAsOfDate().hashCode();
        }
        if (getServiceProviderComments() != null) {
            _hashCode += getServiceProviderComments().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceProviderInformation2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceProviderInformation2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProvideInstructions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProvideInstructions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderEstimatedTimeOfArrival");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderEstimatedTimeOfArrival"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderRequestedResponseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderRequestedResponseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderStatusAsOfDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderStatusAsOfDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderComments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderComments"));
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
