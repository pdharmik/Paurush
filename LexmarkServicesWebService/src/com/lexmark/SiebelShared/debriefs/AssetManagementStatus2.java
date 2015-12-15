/**
 * AssetManagementStatus2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;

public class AssetManagementStatus2  implements java.io.Serializable {
    private com.lexmark.SiebelShared.debriefs.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.SiebelShared.debriefs.AssetManagementStatusDetails assetManagementStatusDetails;

    public AssetManagementStatus2() {
    }

    public AssetManagementStatus2(
           com.lexmark.SiebelShared.debriefs.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.SiebelShared.debriefs.AssetManagementStatusDetails assetManagementStatusDetails) {
           this.documentMetaData = documentMetaData;
           this.assetManagementStatusDetails = assetManagementStatusDetails;
    }


    /**
     * Gets the documentMetaData value for this AssetManagementStatus2.
     * 
     * @return documentMetaData
     */
    public com.lexmark.SiebelShared.debriefs.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this AssetManagementStatus2.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.SiebelShared.debriefs.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the assetManagementStatusDetails value for this AssetManagementStatus2.
     * 
     * @return assetManagementStatusDetails
     */
    public com.lexmark.SiebelShared.debriefs.AssetManagementStatusDetails getAssetManagementStatusDetails() {
        return assetManagementStatusDetails;
    }


    /**
     * Sets the assetManagementStatusDetails value for this AssetManagementStatus2.
     * 
     * @param assetManagementStatusDetails
     */
    public void setAssetManagementStatusDetails(com.lexmark.SiebelShared.debriefs.AssetManagementStatusDetails assetManagementStatusDetails) {
        this.assetManagementStatusDetails = assetManagementStatusDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetManagementStatus2)) return false;
        AssetManagementStatus2 other = (AssetManagementStatus2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentMetaData==null && other.getDocumentMetaData()==null) || 
             (this.documentMetaData!=null &&
              this.documentMetaData.equals(other.getDocumentMetaData()))) &&
            ((this.assetManagementStatusDetails==null && other.getAssetManagementStatusDetails()==null) || 
             (this.assetManagementStatusDetails!=null &&
              this.assetManagementStatusDetails.equals(other.getAssetManagementStatusDetails())));
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
        if (getDocumentMetaData() != null) {
            _hashCode += getDocumentMetaData().hashCode();
        }
        if (getAssetManagementStatusDetails() != null) {
            _hashCode += getAssetManagementStatusDetails().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssetManagementStatus2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AssetManagementStatus2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "WebServiceDocumentMetaData"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetManagementStatusDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetManagementStatusDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AssetManagementStatusDetails"));
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
