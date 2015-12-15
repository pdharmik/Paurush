/**
 * AssetManagementStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;

public class AssetManagementStatus  implements java.io.Serializable {
    private com.lexmark.SiebelShared.debriefs.AssetManagementStatus2 assetManagementStatus;

    public AssetManagementStatus() {
    }

    public AssetManagementStatus(
           com.lexmark.SiebelShared.debriefs.AssetManagementStatus2 assetManagementStatus) {
           this.assetManagementStatus = assetManagementStatus;
    }


    /**
     * Gets the assetManagementStatus value for this AssetManagementStatus.
     * 
     * @return assetManagementStatus
     */
    public com.lexmark.SiebelShared.debriefs.AssetManagementStatus2 getAssetManagementStatus() {
        return assetManagementStatus;
    }


    /**
     * Sets the assetManagementStatus value for this AssetManagementStatus.
     * 
     * @param assetManagementStatus
     */
    public void setAssetManagementStatus(com.lexmark.SiebelShared.debriefs.AssetManagementStatus2 assetManagementStatus) {
        this.assetManagementStatus = assetManagementStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetManagementStatus)) return false;
        AssetManagementStatus other = (AssetManagementStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.assetManagementStatus==null && other.getAssetManagementStatus()==null) || 
             (this.assetManagementStatus!=null &&
              this.assetManagementStatus.equals(other.getAssetManagementStatus())));
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
        if (getAssetManagementStatus() != null) {
            _hashCode += getAssetManagementStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssetManagementStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AssetManagementStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetManagementStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetManagementStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AssetManagementStatus2"));
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
