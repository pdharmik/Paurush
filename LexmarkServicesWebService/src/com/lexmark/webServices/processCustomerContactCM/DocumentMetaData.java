/**
 * DocumentMetaData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.processCustomerContactCM;

public class DocumentMetaData  implements java.io.Serializable {
    private java.lang.String enterpriseDocumentId;

    private java.lang.String sourceSystem;

    private java.lang.String businessProcess;

    private java.lang.String sourceDocumentTrackingId;

    private java.util.Calendar sourceCreationDateTime;

    private com.lexmark.webServices.processCustomerContactCM.ObjectChangeActionType objectChangeActionType;

    private java.util.Calendar integrationCreationDateTime;

    private com.lexmark.webServices.processCustomerContactCM.IntegrationFrequency integrationFrequency;

    private java.util.Calendar integrationResubmissionDateTime;

    private java.lang.String documentResubmissionAttemptNumber;

    private com.lexmark.webServices.processCustomerContactCM.LargeDocumentHandling largeDocumentHandling;

    private com.lexmark.webServices.processCustomerContactCM.RelevantGeographies relevantGeographies;

    private com.lexmark.webServices.processCustomerContactCM.TradingPartnerMetaData tradingPartnerMetaData;

    public DocumentMetaData() {
    }

    public DocumentMetaData(
           java.lang.String enterpriseDocumentId,
           java.lang.String sourceSystem,
           java.lang.String businessProcess,
           java.lang.String sourceDocumentTrackingId,
           java.util.Calendar sourceCreationDateTime,
           com.lexmark.webServices.processCustomerContactCM.ObjectChangeActionType objectChangeActionType,
           java.util.Calendar integrationCreationDateTime,
           com.lexmark.webServices.processCustomerContactCM.IntegrationFrequency integrationFrequency,
           java.util.Calendar integrationResubmissionDateTime,
           java.lang.String documentResubmissionAttemptNumber,
           com.lexmark.webServices.processCustomerContactCM.LargeDocumentHandling largeDocumentHandling,
           com.lexmark.webServices.processCustomerContactCM.RelevantGeographies relevantGeographies,
           com.lexmark.webServices.processCustomerContactCM.TradingPartnerMetaData tradingPartnerMetaData) {
           this.enterpriseDocumentId = enterpriseDocumentId;
           this.sourceSystem = sourceSystem;
           this.businessProcess = businessProcess;
           this.sourceDocumentTrackingId = sourceDocumentTrackingId;
           this.sourceCreationDateTime = sourceCreationDateTime;
           this.objectChangeActionType = objectChangeActionType;
           this.integrationCreationDateTime = integrationCreationDateTime;
           this.integrationFrequency = integrationFrequency;
           this.integrationResubmissionDateTime = integrationResubmissionDateTime;
           this.documentResubmissionAttemptNumber = documentResubmissionAttemptNumber;
           this.largeDocumentHandling = largeDocumentHandling;
           this.relevantGeographies = relevantGeographies;
           this.tradingPartnerMetaData = tradingPartnerMetaData;
    }


    /**
     * Gets the enterpriseDocumentId value for this DocumentMetaData.
     * 
     * @return enterpriseDocumentId
     */
    public java.lang.String getEnterpriseDocumentId() {
        return enterpriseDocumentId;
    }


    /**
     * Sets the enterpriseDocumentId value for this DocumentMetaData.
     * 
     * @param enterpriseDocumentId
     */
    public void setEnterpriseDocumentId(java.lang.String enterpriseDocumentId) {
        this.enterpriseDocumentId = enterpriseDocumentId;
    }


    /**
     * Gets the sourceSystem value for this DocumentMetaData.
     * 
     * @return sourceSystem
     */
    public java.lang.String getSourceSystem() {
        return sourceSystem;
    }


    /**
     * Sets the sourceSystem value for this DocumentMetaData.
     * 
     * @param sourceSystem
     */
    public void setSourceSystem(java.lang.String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }


    /**
     * Gets the businessProcess value for this DocumentMetaData.
     * 
     * @return businessProcess
     */
    public java.lang.String getBusinessProcess() {
        return businessProcess;
    }


    /**
     * Sets the businessProcess value for this DocumentMetaData.
     * 
     * @param businessProcess
     */
    public void setBusinessProcess(java.lang.String businessProcess) {
        this.businessProcess = businessProcess;
    }


    /**
     * Gets the sourceDocumentTrackingId value for this DocumentMetaData.
     * 
     * @return sourceDocumentTrackingId
     */
    public java.lang.String getSourceDocumentTrackingId() {
        return sourceDocumentTrackingId;
    }


    /**
     * Sets the sourceDocumentTrackingId value for this DocumentMetaData.
     * 
     * @param sourceDocumentTrackingId
     */
    public void setSourceDocumentTrackingId(java.lang.String sourceDocumentTrackingId) {
        this.sourceDocumentTrackingId = sourceDocumentTrackingId;
    }


    /**
     * Gets the sourceCreationDateTime value for this DocumentMetaData.
     * 
     * @return sourceCreationDateTime
     */
    public java.util.Calendar getSourceCreationDateTime() {
        return sourceCreationDateTime;
    }


    /**
     * Sets the sourceCreationDateTime value for this DocumentMetaData.
     * 
     * @param sourceCreationDateTime
     */
    public void setSourceCreationDateTime(java.util.Calendar sourceCreationDateTime) {
        this.sourceCreationDateTime = sourceCreationDateTime;
    }


    /**
     * Gets the objectChangeActionType value for this DocumentMetaData.
     * 
     * @return objectChangeActionType
     */
    public com.lexmark.webServices.processCustomerContactCM.ObjectChangeActionType getObjectChangeActionType() {
        return objectChangeActionType;
    }


    /**
     * Sets the objectChangeActionType value for this DocumentMetaData.
     * 
     * @param objectChangeActionType
     */
    public void setObjectChangeActionType(com.lexmark.webServices.processCustomerContactCM.ObjectChangeActionType objectChangeActionType) {
        this.objectChangeActionType = objectChangeActionType;
    }


    /**
     * Gets the integrationCreationDateTime value for this DocumentMetaData.
     * 
     * @return integrationCreationDateTime
     */
    public java.util.Calendar getIntegrationCreationDateTime() {
        return integrationCreationDateTime;
    }


    /**
     * Sets the integrationCreationDateTime value for this DocumentMetaData.
     * 
     * @param integrationCreationDateTime
     */
    public void setIntegrationCreationDateTime(java.util.Calendar integrationCreationDateTime) {
        this.integrationCreationDateTime = integrationCreationDateTime;
    }


    /**
     * Gets the integrationFrequency value for this DocumentMetaData.
     * 
     * @return integrationFrequency
     */
    public com.lexmark.webServices.processCustomerContactCM.IntegrationFrequency getIntegrationFrequency() {
        return integrationFrequency;
    }


    /**
     * Sets the integrationFrequency value for this DocumentMetaData.
     * 
     * @param integrationFrequency
     */
    public void setIntegrationFrequency(com.lexmark.webServices.processCustomerContactCM.IntegrationFrequency integrationFrequency) {
        this.integrationFrequency = integrationFrequency;
    }


    /**
     * Gets the integrationResubmissionDateTime value for this DocumentMetaData.
     * 
     * @return integrationResubmissionDateTime
     */
    public java.util.Calendar getIntegrationResubmissionDateTime() {
        return integrationResubmissionDateTime;
    }


    /**
     * Sets the integrationResubmissionDateTime value for this DocumentMetaData.
     * 
     * @param integrationResubmissionDateTime
     */
    public void setIntegrationResubmissionDateTime(java.util.Calendar integrationResubmissionDateTime) {
        this.integrationResubmissionDateTime = integrationResubmissionDateTime;
    }


    /**
     * Gets the documentResubmissionAttemptNumber value for this DocumentMetaData.
     * 
     * @return documentResubmissionAttemptNumber
     */
    public java.lang.String getDocumentResubmissionAttemptNumber() {
        return documentResubmissionAttemptNumber;
    }


    /**
     * Sets the documentResubmissionAttemptNumber value for this DocumentMetaData.
     * 
     * @param documentResubmissionAttemptNumber
     */
    public void setDocumentResubmissionAttemptNumber(java.lang.String documentResubmissionAttemptNumber) {
        this.documentResubmissionAttemptNumber = documentResubmissionAttemptNumber;
    }


    /**
     * Gets the largeDocumentHandling value for this DocumentMetaData.
     * 
     * @return largeDocumentHandling
     */
    public com.lexmark.webServices.processCustomerContactCM.LargeDocumentHandling getLargeDocumentHandling() {
        return largeDocumentHandling;
    }


    /**
     * Sets the largeDocumentHandling value for this DocumentMetaData.
     * 
     * @param largeDocumentHandling
     */
    public void setLargeDocumentHandling(com.lexmark.webServices.processCustomerContactCM.LargeDocumentHandling largeDocumentHandling) {
        this.largeDocumentHandling = largeDocumentHandling;
    }


    /**
     * Gets the relevantGeographies value for this DocumentMetaData.
     * 
     * @return relevantGeographies
     */
    public com.lexmark.webServices.processCustomerContactCM.RelevantGeographies getRelevantGeographies() {
        return relevantGeographies;
    }


    /**
     * Sets the relevantGeographies value for this DocumentMetaData.
     * 
     * @param relevantGeographies
     */
    public void setRelevantGeographies(com.lexmark.webServices.processCustomerContactCM.RelevantGeographies relevantGeographies) {
        this.relevantGeographies = relevantGeographies;
    }


    /**
     * Gets the tradingPartnerMetaData value for this DocumentMetaData.
     * 
     * @return tradingPartnerMetaData
     */
    public com.lexmark.webServices.processCustomerContactCM.TradingPartnerMetaData getTradingPartnerMetaData() {
        return tradingPartnerMetaData;
    }


    /**
     * Sets the tradingPartnerMetaData value for this DocumentMetaData.
     * 
     * @param tradingPartnerMetaData
     */
    public void setTradingPartnerMetaData(com.lexmark.webServices.processCustomerContactCM.TradingPartnerMetaData tradingPartnerMetaData) {
        this.tradingPartnerMetaData = tradingPartnerMetaData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentMetaData)) return false;
        DocumentMetaData other = (DocumentMetaData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enterpriseDocumentId==null && other.getEnterpriseDocumentId()==null) || 
             (this.enterpriseDocumentId!=null &&
              this.enterpriseDocumentId.equals(other.getEnterpriseDocumentId()))) &&
            ((this.sourceSystem==null && other.getSourceSystem()==null) || 
             (this.sourceSystem!=null &&
              this.sourceSystem.equals(other.getSourceSystem()))) &&
            ((this.businessProcess==null && other.getBusinessProcess()==null) || 
             (this.businessProcess!=null &&
              this.businessProcess.equals(other.getBusinessProcess()))) &&
            ((this.sourceDocumentTrackingId==null && other.getSourceDocumentTrackingId()==null) || 
             (this.sourceDocumentTrackingId!=null &&
              this.sourceDocumentTrackingId.equals(other.getSourceDocumentTrackingId()))) &&
            ((this.sourceCreationDateTime==null && other.getSourceCreationDateTime()==null) || 
             (this.sourceCreationDateTime!=null &&
              this.sourceCreationDateTime.equals(other.getSourceCreationDateTime()))) &&
            ((this.objectChangeActionType==null && other.getObjectChangeActionType()==null) || 
             (this.objectChangeActionType!=null &&
              this.objectChangeActionType.equals(other.getObjectChangeActionType()))) &&
            ((this.integrationCreationDateTime==null && other.getIntegrationCreationDateTime()==null) || 
             (this.integrationCreationDateTime!=null &&
              this.integrationCreationDateTime.equals(other.getIntegrationCreationDateTime()))) &&
            ((this.integrationFrequency==null && other.getIntegrationFrequency()==null) || 
             (this.integrationFrequency!=null &&
              this.integrationFrequency.equals(other.getIntegrationFrequency()))) &&
            ((this.integrationResubmissionDateTime==null && other.getIntegrationResubmissionDateTime()==null) || 
             (this.integrationResubmissionDateTime!=null &&
              this.integrationResubmissionDateTime.equals(other.getIntegrationResubmissionDateTime()))) &&
            ((this.documentResubmissionAttemptNumber==null && other.getDocumentResubmissionAttemptNumber()==null) || 
             (this.documentResubmissionAttemptNumber!=null &&
              this.documentResubmissionAttemptNumber.equals(other.getDocumentResubmissionAttemptNumber()))) &&
            ((this.largeDocumentHandling==null && other.getLargeDocumentHandling()==null) || 
             (this.largeDocumentHandling!=null &&
              this.largeDocumentHandling.equals(other.getLargeDocumentHandling()))) &&
            ((this.relevantGeographies==null && other.getRelevantGeographies()==null) || 
             (this.relevantGeographies!=null &&
              this.relevantGeographies.equals(other.getRelevantGeographies()))) &&
            ((this.tradingPartnerMetaData==null && other.getTradingPartnerMetaData()==null) || 
             (this.tradingPartnerMetaData!=null &&
              this.tradingPartnerMetaData.equals(other.getTradingPartnerMetaData())));
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
        if (getEnterpriseDocumentId() != null) {
            _hashCode += getEnterpriseDocumentId().hashCode();
        }
        if (getSourceSystem() != null) {
            _hashCode += getSourceSystem().hashCode();
        }
        if (getBusinessProcess() != null) {
            _hashCode += getBusinessProcess().hashCode();
        }
        if (getSourceDocumentTrackingId() != null) {
            _hashCode += getSourceDocumentTrackingId().hashCode();
        }
        if (getSourceCreationDateTime() != null) {
            _hashCode += getSourceCreationDateTime().hashCode();
        }
        if (getObjectChangeActionType() != null) {
            _hashCode += getObjectChangeActionType().hashCode();
        }
        if (getIntegrationCreationDateTime() != null) {
            _hashCode += getIntegrationCreationDateTime().hashCode();
        }
        if (getIntegrationFrequency() != null) {
            _hashCode += getIntegrationFrequency().hashCode();
        }
        if (getIntegrationResubmissionDateTime() != null) {
            _hashCode += getIntegrationResubmissionDateTime().hashCode();
        }
        if (getDocumentResubmissionAttemptNumber() != null) {
            _hashCode += getDocumentResubmissionAttemptNumber().hashCode();
        }
        if (getLargeDocumentHandling() != null) {
            _hashCode += getLargeDocumentHandling().hashCode();
        }
        if (getRelevantGeographies() != null) {
            _hashCode += getRelevantGeographies().hashCode();
        }
        if (getTradingPartnerMetaData() != null) {
            _hashCode += getTradingPartnerMetaData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentMetaData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "DocumentMetaData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterpriseDocumentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EnterpriseDocumentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SourceSystem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessProcess");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BusinessProcess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceDocumentTrackingId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SourceDocumentTrackingId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceCreationDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SourceCreationDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectChangeActionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ObjectChangeActionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "ObjectChangeActionType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("integrationCreationDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IntegrationCreationDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("integrationFrequency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IntegrationFrequency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "IntegrationFrequency"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("integrationResubmissionDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IntegrationResubmissionDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentResubmissionAttemptNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentResubmissionAttemptNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("largeDocumentHandling");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LargeDocumentHandling"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "LargeDocumentHandling"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relevantGeographies");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelevantGeographies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "RelevantGeographies"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradingPartnerMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TradingPartnerMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "TradingPartnerMetaData"));
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
