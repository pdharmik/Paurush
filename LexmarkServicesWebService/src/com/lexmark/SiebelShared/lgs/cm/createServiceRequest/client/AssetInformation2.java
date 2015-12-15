/**
 * AssetInformation2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class AssetInformation2  implements java.io.Serializable {
    private java.lang.String serialNumber;

    private java.lang.String assetTag;

    private java.lang.String productModel;

    private java.lang.String productNumber;

    private java.lang.String IPAddress;

    private java.lang.String assetInstalledDate;

    private java.lang.String hostName;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PageCountData[] pageCountData;

    public AssetInformation2() {
    }

    public AssetInformation2(
           java.lang.String serialNumber,
           java.lang.String assetTag,
           java.lang.String productModel,
           java.lang.String productNumber,
           java.lang.String IPAddress,
           java.lang.String assetInstalledDate,
           java.lang.String hostName,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PageCountData[] pageCountData) {
           this.serialNumber = serialNumber;
           this.assetTag = assetTag;
           this.productModel = productModel;
           this.productNumber = productNumber;
           this.IPAddress = IPAddress;
           this.assetInstalledDate = assetInstalledDate;
           this.hostName = hostName;
           this.pageCountData = pageCountData;
    }


    /**
     * Gets the serialNumber value for this AssetInformation2.
     * 
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this AssetInformation2.
     * 
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }


    /**
     * Gets the assetTag value for this AssetInformation2.
     * 
     * @return assetTag
     */
    public java.lang.String getAssetTag() {
        return assetTag;
    }


    /**
     * Sets the assetTag value for this AssetInformation2.
     * 
     * @param assetTag
     */
    public void setAssetTag(java.lang.String assetTag) {
        this.assetTag = assetTag;
    }


    /**
     * Gets the productModel value for this AssetInformation2.
     * 
     * @return productModel
     */
    public java.lang.String getProductModel() {
        return productModel;
    }


    /**
     * Sets the productModel value for this AssetInformation2.
     * 
     * @param productModel
     */
    public void setProductModel(java.lang.String productModel) {
        this.productModel = productModel;
    }


    /**
     * Gets the productNumber value for this AssetInformation2.
     * 
     * @return productNumber
     */
    public java.lang.String getProductNumber() {
        return productNumber;
    }


    /**
     * Sets the productNumber value for this AssetInformation2.
     * 
     * @param productNumber
     */
    public void setProductNumber(java.lang.String productNumber) {
        this.productNumber = productNumber;
    }


    /**
     * Gets the IPAddress value for this AssetInformation2.
     * 
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this AssetInformation2.
     * 
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the assetInstalledDate value for this AssetInformation2.
     * 
     * @return assetInstalledDate
     */
    public java.lang.String getAssetInstalledDate() {
        return assetInstalledDate;
    }


    /**
     * Sets the assetInstalledDate value for this AssetInformation2.
     * 
     * @param assetInstalledDate
     */
    public void setAssetInstalledDate(java.lang.String assetInstalledDate) {
        this.assetInstalledDate = assetInstalledDate;
    }


    /**
     * Gets the hostName value for this AssetInformation2.
     * 
     * @return hostName
     */
    public java.lang.String getHostName() {
        return hostName;
    }


    /**
     * Sets the hostName value for this AssetInformation2.
     * 
     * @param hostName
     */
    public void setHostName(java.lang.String hostName) {
        this.hostName = hostName;
    }


    /**
     * Gets the pageCountData value for this AssetInformation2.
     * 
     * @return pageCountData
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PageCountData[] getPageCountData() {
        return pageCountData;
    }


    /**
     * Sets the pageCountData value for this AssetInformation2.
     * 
     * @param pageCountData
     */
    public void setPageCountData(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PageCountData[] pageCountData) {
        this.pageCountData = pageCountData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetInformation2)) return false;
        AssetInformation2 other = (AssetInformation2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serialNumber==null && other.getSerialNumber()==null) || 
             (this.serialNumber!=null &&
              this.serialNumber.equals(other.getSerialNumber()))) &&
            ((this.assetTag==null && other.getAssetTag()==null) || 
             (this.assetTag!=null &&
              this.assetTag.equals(other.getAssetTag()))) &&
            ((this.productModel==null && other.getProductModel()==null) || 
             (this.productModel!=null &&
              this.productModel.equals(other.getProductModel()))) &&
            ((this.productNumber==null && other.getProductNumber()==null) || 
             (this.productNumber!=null &&
              this.productNumber.equals(other.getProductNumber()))) &&
            ((this.IPAddress==null && other.getIPAddress()==null) || 
             (this.IPAddress!=null &&
              this.IPAddress.equals(other.getIPAddress()))) &&
            ((this.assetInstalledDate==null && other.getAssetInstalledDate()==null) || 
             (this.assetInstalledDate!=null &&
              this.assetInstalledDate.equals(other.getAssetInstalledDate()))) &&
            ((this.hostName==null && other.getHostName()==null) || 
             (this.hostName!=null &&
              this.hostName.equals(other.getHostName()))) &&
            ((this.pageCountData==null && other.getPageCountData()==null) || 
             (this.pageCountData!=null &&
              java.util.Arrays.equals(this.pageCountData, other.getPageCountData())));
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
        if (getSerialNumber() != null) {
            _hashCode += getSerialNumber().hashCode();
        }
        if (getAssetTag() != null) {
            _hashCode += getAssetTag().hashCode();
        }
        if (getProductModel() != null) {
            _hashCode += getProductModel().hashCode();
        }
        if (getProductNumber() != null) {
            _hashCode += getProductNumber().hashCode();
        }
        if (getIPAddress() != null) {
            _hashCode += getIPAddress().hashCode();
        }
        if (getAssetInstalledDate() != null) {
            _hashCode += getAssetInstalledDate().hashCode();
        }
        if (getHostName() != null) {
            _hashCode += getHostName().hashCode();
        }
        if (getPageCountData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPageCountData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPageCountData(), i);
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
        new org.apache.axis.description.TypeDesc(AssetInformation2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetInformation2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SerialNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetTag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetTag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productModel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductNumber"));
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
        elemField.setFieldName("assetInstalledDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetInstalledDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hostName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HostName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageCountData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PageCountData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PageCountData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfPageCountDataItem"));
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
