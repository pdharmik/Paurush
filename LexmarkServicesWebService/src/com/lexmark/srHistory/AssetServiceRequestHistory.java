/**
 * AssetServiceRequestHistory.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.srHistory;

public class AssetServiceRequestHistory  implements java.io.Serializable {
    private com.lexmark.srHistory.AssetServiceRequestHistory2 assetServiceRequestHistory;

    public AssetServiceRequestHistory() {
    }

    public AssetServiceRequestHistory(
           com.lexmark.srHistory.AssetServiceRequestHistory2 assetServiceRequestHistory) {
           this.assetServiceRequestHistory = assetServiceRequestHistory;
    }


    /**
     * Gets the assetServiceRequestHistory value for this AssetServiceRequestHistory.
     * 
     * @return assetServiceRequestHistory
     */
    public com.lexmark.srHistory.AssetServiceRequestHistory2 getAssetServiceRequestHistory() {
        return assetServiceRequestHistory;
    }


    /**
     * Sets the assetServiceRequestHistory value for this AssetServiceRequestHistory.
     * 
     * @param assetServiceRequestHistory
     */
    public void setAssetServiceRequestHistory(com.lexmark.srHistory.AssetServiceRequestHistory2 assetServiceRequestHistory) {
        this.assetServiceRequestHistory = assetServiceRequestHistory;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetServiceRequestHistory)) return false;
        AssetServiceRequestHistory other = (AssetServiceRequestHistory) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.assetServiceRequestHistory==null && other.getAssetServiceRequestHistory()==null) || 
             (this.assetServiceRequestHistory!=null &&
              this.assetServiceRequestHistory.equals(other.getAssetServiceRequestHistory())));
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
        if (getAssetServiceRequestHistory() != null) {
            _hashCode += getAssetServiceRequestHistory().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssetServiceRequestHistory.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "AssetServiceRequestHistory"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetServiceRequestHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetServiceRequestHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wmamzdev.lexmark.com/LXKServiceRequest.webservices:serviceRequestWS", "AssetServiceRequestHistory2"));
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
