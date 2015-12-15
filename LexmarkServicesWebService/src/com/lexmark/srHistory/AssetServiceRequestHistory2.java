/**
 * AssetServiceRequestHistory2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class AssetServiceRequestHistory2  implements java.io.Serializable {
    private com.lexmark.srHistory.WebServiceDocumentMetaData documentMetaData;

    private com.lexmark.srHistory.AssetInformation assetInformation;

    private com.lexmark.srHistory.ServiceRequest2[] serviceRequest;

    public AssetServiceRequestHistory2() {
    }

    public AssetServiceRequestHistory2(
           com.lexmark.srHistory.WebServiceDocumentMetaData documentMetaData,
           com.lexmark.srHistory.AssetInformation assetInformation,
           com.lexmark.srHistory.ServiceRequest2[] serviceRequest) {
           this.documentMetaData = documentMetaData;
           this.assetInformation = assetInformation;
           this.serviceRequest = serviceRequest;
    }


    /**
     * Gets the documentMetaData value for this AssetServiceRequestHistory2.
     * 
     * @return documentMetaData
     */
    public com.lexmark.srHistory.WebServiceDocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }


    /**
     * Sets the documentMetaData value for this AssetServiceRequestHistory2.
     * 
     * @param documentMetaData
     */
    public void setDocumentMetaData(com.lexmark.srHistory.WebServiceDocumentMetaData documentMetaData) {
        this.documentMetaData = documentMetaData;
    }


    /**
     * Gets the assetInformation value for this AssetServiceRequestHistory2.
     * 
     * @return assetInformation
     */
    public com.lexmark.srHistory.AssetInformation getAssetInformation() {
        return assetInformation;
    }


    /**
     * Sets the assetInformation value for this AssetServiceRequestHistory2.
     * 
     * @param assetInformation
     */
    public void setAssetInformation(com.lexmark.srHistory.AssetInformation assetInformation) {
        this.assetInformation = assetInformation;
    }


    /**
     * Gets the serviceRequest value for this AssetServiceRequestHistory2.
     * 
     * @return serviceRequest
     */
    public com.lexmark.srHistory.ServiceRequest2[] getServiceRequest() {
        return serviceRequest;
    }


    /**
     * Sets the serviceRequest value for this AssetServiceRequestHistory2.
     * 
     * @param serviceRequest
     */
    public void setServiceRequest(com.lexmark.srHistory.ServiceRequest2[] serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public com.lexmark.srHistory.ServiceRequest2 getServiceRequest(int i) {
        return this.serviceRequest[i];
    }

    public void setServiceRequest(int i, com.lexmark.srHistory.ServiceRequest2 _value) {
        this.serviceRequest[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetServiceRequestHistory2)) return false;
        AssetServiceRequestHistory2 other = (AssetServiceRequestHistory2) obj;
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
            ((this.assetInformation==null && other.getAssetInformation()==null) || 
             (this.assetInformation!=null &&
              this.assetInformation.equals(other.getAssetInformation()))) &&
            ((this.serviceRequest==null && other.getServiceRequest()==null) || 
             (this.serviceRequest!=null &&
              java.util.Arrays.equals(this.serviceRequest, other.getServiceRequest())));
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
        if (getAssetInformation() != null) {
            _hashCode += getAssetInformation().hashCode();
        }
        if (getServiceRequest() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getServiceRequest());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getServiceRequest(), i);
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
        new org.apache.axis.description.TypeDesc(AssetServiceRequestHistory2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "AssetServiceRequestHistory2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMetaData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DocumentMetaData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "WebServiceDocumentMetaData"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "AssetInformation"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "ServiceRequest2"));
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
