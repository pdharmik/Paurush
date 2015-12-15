/**
 * OriginalDeviceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class OriginalDeviceInformation  implements java.io.Serializable {
    private java.lang.String assetId;

    private java.lang.String printerWorkingCondition;

    private java.lang.String totalPageCount;

    private java.lang.String lexmarkAssetTag;

    private java.lang.String networkConnected;

    private java.lang.String IPAddress;

    private java.lang.String MACAddress;

    public OriginalDeviceInformation() {
    }

    public OriginalDeviceInformation(
           java.lang.String assetId,
           java.lang.String printerWorkingCondition,
           java.lang.String totalPageCount,
           java.lang.String lexmarkAssetTag,
           java.lang.String networkConnected,
           java.lang.String IPAddress,
           java.lang.String MACAddress) {
           this.assetId = assetId;
           this.printerWorkingCondition = printerWorkingCondition;
           this.totalPageCount = totalPageCount;
           this.lexmarkAssetTag = lexmarkAssetTag;
           this.networkConnected = networkConnected;
           this.IPAddress = IPAddress;
           this.MACAddress = MACAddress;
    }


    /**
     * Gets the assetId value for this OriginalDeviceInformation.
     * 
     * @return assetId
     */
    public java.lang.String getAssetId() {
        return assetId;
    }


    /**
     * Sets the assetId value for this OriginalDeviceInformation.
     * 
     * @param assetId
     */
    public void setAssetId(java.lang.String assetId) {
        this.assetId = assetId;
    }


    /**
     * Gets the printerWorkingCondition value for this OriginalDeviceInformation.
     * 
     * @return printerWorkingCondition
     */
    public java.lang.String getPrinterWorkingCondition() {
        return printerWorkingCondition;
    }


    /**
     * Sets the printerWorkingCondition value for this OriginalDeviceInformation.
     * 
     * @param printerWorkingCondition
     */
    public void setPrinterWorkingCondition(java.lang.String printerWorkingCondition) {
        this.printerWorkingCondition = printerWorkingCondition;
    }


    /**
     * Gets the totalPageCount value for this OriginalDeviceInformation.
     * 
     * @return totalPageCount
     */
    public java.lang.String getTotalPageCount() {
        return totalPageCount;
    }


    /**
     * Sets the totalPageCount value for this OriginalDeviceInformation.
     * 
     * @param totalPageCount
     */
    public void setTotalPageCount(java.lang.String totalPageCount) {
        this.totalPageCount = totalPageCount;
    }


    /**
     * Gets the lexmarkAssetTag value for this OriginalDeviceInformation.
     * 
     * @return lexmarkAssetTag
     */
    public java.lang.String getLexmarkAssetTag() {
        return lexmarkAssetTag;
    }


    /**
     * Sets the lexmarkAssetTag value for this OriginalDeviceInformation.
     * 
     * @param lexmarkAssetTag
     */
    public void setLexmarkAssetTag(java.lang.String lexmarkAssetTag) {
        this.lexmarkAssetTag = lexmarkAssetTag;
    }


    /**
     * Gets the networkConnected value for this OriginalDeviceInformation.
     * 
     * @return networkConnected
     */
    public java.lang.String getNetworkConnected() {
        return networkConnected;
    }


    /**
     * Sets the networkConnected value for this OriginalDeviceInformation.
     * 
     * @param networkConnected
     */
    public void setNetworkConnected(java.lang.String networkConnected) {
        this.networkConnected = networkConnected;
    }


    /**
     * Gets the IPAddress value for this OriginalDeviceInformation.
     * 
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this OriginalDeviceInformation.
     * 
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the MACAddress value for this OriginalDeviceInformation.
     * 
     * @return MACAddress
     */
    public java.lang.String getMACAddress() {
        return MACAddress;
    }


    /**
     * Sets the MACAddress value for this OriginalDeviceInformation.
     * 
     * @param MACAddress
     */
    public void setMACAddress(java.lang.String MACAddress) {
        this.MACAddress = MACAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OriginalDeviceInformation)) return false;
        OriginalDeviceInformation other = (OriginalDeviceInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.assetId==null && other.getAssetId()==null) || 
             (this.assetId!=null &&
              this.assetId.equals(other.getAssetId()))) &&
            ((this.printerWorkingCondition==null && other.getPrinterWorkingCondition()==null) || 
             (this.printerWorkingCondition!=null &&
              this.printerWorkingCondition.equals(other.getPrinterWorkingCondition()))) &&
            ((this.totalPageCount==null && other.getTotalPageCount()==null) || 
             (this.totalPageCount!=null &&
              this.totalPageCount.equals(other.getTotalPageCount()))) &&
            ((this.lexmarkAssetTag==null && other.getLexmarkAssetTag()==null) || 
             (this.lexmarkAssetTag!=null &&
              this.lexmarkAssetTag.equals(other.getLexmarkAssetTag()))) &&
            ((this.networkConnected==null && other.getNetworkConnected()==null) || 
             (this.networkConnected!=null &&
              this.networkConnected.equals(other.getNetworkConnected()))) &&
            ((this.IPAddress==null && other.getIPAddress()==null) || 
             (this.IPAddress!=null &&
              this.IPAddress.equals(other.getIPAddress()))) &&
            ((this.MACAddress==null && other.getMACAddress()==null) || 
             (this.MACAddress!=null &&
              this.MACAddress.equals(other.getMACAddress())));
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
        if (getAssetId() != null) {
            _hashCode += getAssetId().hashCode();
        }
        if (getPrinterWorkingCondition() != null) {
            _hashCode += getPrinterWorkingCondition().hashCode();
        }
        if (getTotalPageCount() != null) {
            _hashCode += getTotalPageCount().hashCode();
        }
        if (getLexmarkAssetTag() != null) {
            _hashCode += getLexmarkAssetTag().hashCode();
        }
        if (getNetworkConnected() != null) {
            _hashCode += getNetworkConnected().hashCode();
        }
        if (getIPAddress() != null) {
            _hashCode += getIPAddress().hashCode();
        }
        if (getMACAddress() != null) {
            _hashCode += getMACAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OriginalDeviceInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "OriginalDeviceInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printerWorkingCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrinterWorkingCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalPageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TotalPageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lexmarkAssetTag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LexmarkAssetTag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkConnected");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NetworkConnected"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MACAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MACAddress"));
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
