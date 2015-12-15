/**
 * ReplacementDeviceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ReplacementDeviceInformation  implements java.io.Serializable {
    private java.lang.String assetId;

    private java.lang.String totalPageCount;

    private java.lang.String lexmarkAssetTag;

    private java.lang.String networkConnected;

    private java.lang.String IPAddress;

    private java.lang.String MACAddress;

    private java.lang.String machineTypeModel;

    private java.lang.String serialNumber;

    public ReplacementDeviceInformation() {
    }

    public ReplacementDeviceInformation(
           java.lang.String assetId,
           java.lang.String totalPageCount,
           java.lang.String lexmarkAssetTag,
           java.lang.String networkConnected,
           java.lang.String IPAddress,
           java.lang.String MACAddress,
           java.lang.String machineTypeModel,
           java.lang.String serialNumber) {
           this.assetId = assetId;
           this.totalPageCount = totalPageCount;
           this.lexmarkAssetTag = lexmarkAssetTag;
           this.networkConnected = networkConnected;
           this.IPAddress = IPAddress;
           this.MACAddress = MACAddress;
           this.machineTypeModel = machineTypeModel;
           this.serialNumber = serialNumber;
    }


    /**
     * Gets the assetId value for this ReplacementDeviceInformation.
     * 
     * @return assetId
     */
    public java.lang.String getAssetId() {
        return assetId;
    }


    /**
     * Sets the assetId value for this ReplacementDeviceInformation.
     * 
     * @param assetId
     */
    public void setAssetId(java.lang.String assetId) {
        this.assetId = assetId;
    }


    /**
     * Gets the totalPageCount value for this ReplacementDeviceInformation.
     * 
     * @return totalPageCount
     */
    public java.lang.String getTotalPageCount() {
        return totalPageCount;
    }


    /**
     * Sets the totalPageCount value for this ReplacementDeviceInformation.
     * 
     * @param totalPageCount
     */
    public void setTotalPageCount(java.lang.String totalPageCount) {
        this.totalPageCount = totalPageCount;
    }


    /**
     * Gets the lexmarkAssetTag value for this ReplacementDeviceInformation.
     * 
     * @return lexmarkAssetTag
     */
    public java.lang.String getLexmarkAssetTag() {
        return lexmarkAssetTag;
    }


    /**
     * Sets the lexmarkAssetTag value for this ReplacementDeviceInformation.
     * 
     * @param lexmarkAssetTag
     */
    public void setLexmarkAssetTag(java.lang.String lexmarkAssetTag) {
        this.lexmarkAssetTag = lexmarkAssetTag;
    }


    /**
     * Gets the networkConnected value for this ReplacementDeviceInformation.
     * 
     * @return networkConnected
     */
    public java.lang.String getNetworkConnected() {
        return networkConnected;
    }


    /**
     * Sets the networkConnected value for this ReplacementDeviceInformation.
     * 
     * @param networkConnected
     */
    public void setNetworkConnected(java.lang.String networkConnected) {
        this.networkConnected = networkConnected;
    }


    /**
     * Gets the IPAddress value for this ReplacementDeviceInformation.
     * 
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this ReplacementDeviceInformation.
     * 
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the MACAddress value for this ReplacementDeviceInformation.
     * 
     * @return MACAddress
     */
    public java.lang.String getMACAddress() {
        return MACAddress;
    }


    /**
     * Sets the MACAddress value for this ReplacementDeviceInformation.
     * 
     * @param MACAddress
     */
    public void setMACAddress(java.lang.String MACAddress) {
        this.MACAddress = MACAddress;
    }


    /**
     * Gets the machineTypeModel value for this ReplacementDeviceInformation.
     * 
     * @return machineTypeModel
     */
    public java.lang.String getMachineTypeModel() {
        return machineTypeModel;
    }


    /**
     * Sets the machineTypeModel value for this ReplacementDeviceInformation.
     * 
     * @param machineTypeModel
     */
    public void setMachineTypeModel(java.lang.String machineTypeModel) {
        this.machineTypeModel = machineTypeModel;
    }


    /**
     * Gets the serialNumber value for this ReplacementDeviceInformation.
     * 
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this ReplacementDeviceInformation.
     * 
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReplacementDeviceInformation)) return false;
        ReplacementDeviceInformation other = (ReplacementDeviceInformation) obj;
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
              this.MACAddress.equals(other.getMACAddress()))) &&
            ((this.machineTypeModel==null && other.getMachineTypeModel()==null) || 
             (this.machineTypeModel!=null &&
              this.machineTypeModel.equals(other.getMachineTypeModel()))) &&
            ((this.serialNumber==null && other.getSerialNumber()==null) || 
             (this.serialNumber!=null &&
              this.serialNumber.equals(other.getSerialNumber())));
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
        if (getMachineTypeModel() != null) {
            _hashCode += getMachineTypeModel().hashCode();
        }
        if (getSerialNumber() != null) {
            _hashCode += getSerialNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReplacementDeviceInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReplacementDeviceInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineTypeModel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MachineTypeModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SerialNumber"));
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
