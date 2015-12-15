/**
 * WebServiceDocumentMetaData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class WebServiceDocumentMetaData  implements java.io.Serializable {
    private java.lang.String senderId;

    private java.lang.String senderName;

    private java.lang.String originalPartnerId;

    private java.lang.String originalPartnerName;

    private java.lang.String receiverId;

    private java.lang.String receiverName;

    public WebServiceDocumentMetaData() {
    }

    public WebServiceDocumentMetaData(
           java.lang.String senderId,
           java.lang.String senderName,
           java.lang.String originalPartnerId,
           java.lang.String originalPartnerName,
           java.lang.String receiverId,
           java.lang.String receiverName) {
           this.senderId = senderId;
           this.senderName = senderName;
           this.originalPartnerId = originalPartnerId;
           this.originalPartnerName = originalPartnerName;
           this.receiverId = receiverId;
           this.receiverName = receiverName;
    }


    /**
     * Gets the senderId value for this WebServiceDocumentMetaData.
     * 
     * @return senderId
     */
    public java.lang.String getSenderId() {
        return senderId;
    }


    /**
     * Sets the senderId value for this WebServiceDocumentMetaData.
     * 
     * @param senderId
     */
    public void setSenderId(java.lang.String senderId) {
        this.senderId = senderId;
    }


    /**
     * Gets the senderName value for this WebServiceDocumentMetaData.
     * 
     * @return senderName
     */
    public java.lang.String getSenderName() {
        return senderName;
    }


    /**
     * Sets the senderName value for this WebServiceDocumentMetaData.
     * 
     * @param senderName
     */
    public void setSenderName(java.lang.String senderName) {
        this.senderName = senderName;
    }


    /**
     * Gets the originalPartnerId value for this WebServiceDocumentMetaData.
     * 
     * @return originalPartnerId
     */
    public java.lang.String getOriginalPartnerId() {
        return originalPartnerId;
    }


    /**
     * Sets the originalPartnerId value for this WebServiceDocumentMetaData.
     * 
     * @param originalPartnerId
     */
    public void setOriginalPartnerId(java.lang.String originalPartnerId) {
        this.originalPartnerId = originalPartnerId;
    }


    /**
     * Gets the originalPartnerName value for this WebServiceDocumentMetaData.
     * 
     * @return originalPartnerName
     */
    public java.lang.String getOriginalPartnerName() {
        return originalPartnerName;
    }


    /**
     * Sets the originalPartnerName value for this WebServiceDocumentMetaData.
     * 
     * @param originalPartnerName
     */
    public void setOriginalPartnerName(java.lang.String originalPartnerName) {
        this.originalPartnerName = originalPartnerName;
    }


    /**
     * Gets the receiverId value for this WebServiceDocumentMetaData.
     * 
     * @return receiverId
     */
    public java.lang.String getReceiverId() {
        return receiverId;
    }


    /**
     * Sets the receiverId value for this WebServiceDocumentMetaData.
     * 
     * @param receiverId
     */
    public void setReceiverId(java.lang.String receiverId) {
        this.receiverId = receiverId;
    }


    /**
     * Gets the receiverName value for this WebServiceDocumentMetaData.
     * 
     * @return receiverName
     */
    public java.lang.String getReceiverName() {
        return receiverName;
    }


    /**
     * Sets the receiverName value for this WebServiceDocumentMetaData.
     * 
     * @param receiverName
     */
    public void setReceiverName(java.lang.String receiverName) {
        this.receiverName = receiverName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WebServiceDocumentMetaData)) return false;
        WebServiceDocumentMetaData other = (WebServiceDocumentMetaData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.senderId==null && other.getSenderId()==null) || 
             (this.senderId!=null &&
              this.senderId.equals(other.getSenderId()))) &&
            ((this.senderName==null && other.getSenderName()==null) || 
             (this.senderName!=null &&
              this.senderName.equals(other.getSenderName()))) &&
            ((this.originalPartnerId==null && other.getOriginalPartnerId()==null) || 
             (this.originalPartnerId!=null &&
              this.originalPartnerId.equals(other.getOriginalPartnerId()))) &&
            ((this.originalPartnerName==null && other.getOriginalPartnerName()==null) || 
             (this.originalPartnerName!=null &&
              this.originalPartnerName.equals(other.getOriginalPartnerName()))) &&
            ((this.receiverId==null && other.getReceiverId()==null) || 
             (this.receiverId!=null &&
              this.receiverId.equals(other.getReceiverId()))) &&
            ((this.receiverName==null && other.getReceiverName()==null) || 
             (this.receiverName!=null &&
              this.receiverName.equals(other.getReceiverName())));
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
        if (getSenderId() != null) {
            _hashCode += getSenderId().hashCode();
        }
        if (getSenderName() != null) {
            _hashCode += getSenderName().hashCode();
        }
        if (getOriginalPartnerId() != null) {
            _hashCode += getOriginalPartnerId().hashCode();
        }
        if (getOriginalPartnerName() != null) {
            _hashCode += getOriginalPartnerName().hashCode();
        }
        if (getReceiverId() != null) {
            _hashCode += getReceiverId().hashCode();
        }
        if (getReceiverName() != null) {
            _hashCode += getReceiverName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebServiceDocumentMetaData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "WebServiceDocumentMetaData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SenderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SenderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalPartnerId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OriginalPartnerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("originalPartnerName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OriginalPartnerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiverId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReceiverId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiverName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReceiverName"));
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
