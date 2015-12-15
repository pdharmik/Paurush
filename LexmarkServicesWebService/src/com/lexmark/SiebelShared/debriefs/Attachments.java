/**
 * Attachments.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;

public class Attachments  implements java.io.Serializable {
    private java.lang.String attachmentName;

    private java.lang.String attachmentLink;

    public Attachments() {
    }

    public Attachments(
           java.lang.String attachmentName,
           java.lang.String attachmentLink) {
           this.attachmentName = attachmentName;
           this.attachmentLink = attachmentLink;
    }


    /**
     * Gets the attachmentName value for this Attachments.
     * 
     * @return attachmentName
     */
    public java.lang.String getAttachmentName() {
        return attachmentName;
    }


    /**
     * Sets the attachmentName value for this Attachments.
     * 
     * @param attachmentName
     */
    public void setAttachmentName(java.lang.String attachmentName) {
        this.attachmentName = attachmentName;
    }


    /**
     * Gets the attachmentLink value for this Attachments.
     * 
     * @return attachmentLink
     */
    public java.lang.String getAttachmentLink() {
        return attachmentLink;
    }


    /**
     * Sets the attachmentLink value for this Attachments.
     * 
     * @param attachmentLink
     */
    public void setAttachmentLink(java.lang.String attachmentLink) {
        this.attachmentLink = attachmentLink;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Attachments)) return false;
        Attachments other = (Attachments) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attachmentName==null && other.getAttachmentName()==null) || 
             (this.attachmentName!=null &&
              this.attachmentName.equals(other.getAttachmentName()))) &&
            ((this.attachmentLink==null && other.getAttachmentLink()==null) || 
             (this.attachmentLink!=null &&
              this.attachmentLink.equals(other.getAttachmentLink())));
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
        if (getAttachmentName() != null) {
            _hashCode += getAttachmentName().hashCode();
        }
        if (getAttachmentLink() != null) {
            _hashCode += getAttachmentLink().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Attachments.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "Attachments"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AttachmentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentLink");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AttachmentLink"));
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
