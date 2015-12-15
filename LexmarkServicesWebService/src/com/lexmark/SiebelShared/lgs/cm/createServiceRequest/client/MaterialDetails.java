/**
 * MaterialDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class MaterialDetails  implements java.io.Serializable {
    private java.lang.String materialNumber;

    private java.lang.String materialId;

    private java.lang.String quantity;

    private java.lang.String materialType;

    private java.lang.String printerNumber;

    private java.lang.String productId;

    private java.lang.String consumableType;

    private java.lang.String yield;

    private java.lang.String assetUsageType;

    private java.lang.String catalogId;

    private java.lang.String implicitCatalogEntryFlag;

    public MaterialDetails() {
    }

    public MaterialDetails(
           java.lang.String materialNumber,
           java.lang.String materialId,
           java.lang.String quantity,
           java.lang.String materialType,
           java.lang.String printerNumber,
           java.lang.String productId,
           java.lang.String consumableType,
           java.lang.String yield,
           java.lang.String assetUsageType,
           java.lang.String catalogId,
           java.lang.String implicitCatalogEntryFlag) {
           this.materialNumber = materialNumber;
           this.materialId = materialId;
           this.quantity = quantity;
           this.materialType = materialType;
           this.printerNumber = printerNumber;
           this.productId = productId;
           this.consumableType = consumableType;
           this.yield = yield;
           this.assetUsageType = assetUsageType;
           this.catalogId = catalogId;
           this.implicitCatalogEntryFlag = implicitCatalogEntryFlag;
    }


    /**
     * Gets the materialNumber value for this MaterialDetails.
     * 
     * @return materialNumber
     */
    public java.lang.String getMaterialNumber() {
        return materialNumber;
    }


    /**
     * Sets the materialNumber value for this MaterialDetails.
     * 
     * @param materialNumber
     */
    public void setMaterialNumber(java.lang.String materialNumber) {
        this.materialNumber = materialNumber;
    }


    /**
     * Gets the materialId value for this MaterialDetails.
     * 
     * @return materialId
     */
    public java.lang.String getMaterialId() {
        return materialId;
    }


    /**
     * Sets the materialId value for this MaterialDetails.
     * 
     * @param materialId
     */
    public void setMaterialId(java.lang.String materialId) {
        this.materialId = materialId;
    }


    /**
     * Gets the quantity value for this MaterialDetails.
     * 
     * @return quantity
     */
    public java.lang.String getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this MaterialDetails.
     * 
     * @param quantity
     */
    public void setQuantity(java.lang.String quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the materialType value for this MaterialDetails.
     * 
     * @return materialType
     */
    public java.lang.String getMaterialType() {
        return materialType;
    }


    /**
     * Sets the materialType value for this MaterialDetails.
     * 
     * @param materialType
     */
    public void setMaterialType(java.lang.String materialType) {
        this.materialType = materialType;
    }


    /**
     * Gets the printerNumber value for this MaterialDetails.
     * 
     * @return printerNumber
     */
    public java.lang.String getPrinterNumber() {
        return printerNumber;
    }


    /**
     * Sets the printerNumber value for this MaterialDetails.
     * 
     * @param printerNumber
     */
    public void setPrinterNumber(java.lang.String printerNumber) {
        this.printerNumber = printerNumber;
    }


    /**
     * Gets the productId value for this MaterialDetails.
     * 
     * @return productId
     */
    public java.lang.String getProductId() {
        return productId;
    }


    /**
     * Sets the productId value for this MaterialDetails.
     * 
     * @param productId
     */
    public void setProductId(java.lang.String productId) {
        this.productId = productId;
    }


    /**
     * Gets the consumableType value for this MaterialDetails.
     * 
     * @return consumableType
     */
    public java.lang.String getConsumableType() {
        return consumableType;
    }


    /**
     * Sets the consumableType value for this MaterialDetails.
     * 
     * @param consumableType
     */
    public void setConsumableType(java.lang.String consumableType) {
        this.consumableType = consumableType;
    }


    /**
     * Gets the yield value for this MaterialDetails.
     * 
     * @return yield
     */
    public java.lang.String getYield() {
        return yield;
    }


    /**
     * Sets the yield value for this MaterialDetails.
     * 
     * @param yield
     */
    public void setYield(java.lang.String yield) {
        this.yield = yield;
    }


    /**
     * Gets the assetUsageType value for this MaterialDetails.
     * 
     * @return assetUsageType
     */
    public java.lang.String getAssetUsageType() {
        return assetUsageType;
    }


    /**
     * Sets the assetUsageType value for this MaterialDetails.
     * 
     * @param assetUsageType
     */
    public void setAssetUsageType(java.lang.String assetUsageType) {
        this.assetUsageType = assetUsageType;
    }


    /**
     * Gets the catalogId value for this MaterialDetails.
     * 
     * @return catalogId
     */
    public java.lang.String getCatalogId() {
        return catalogId;
    }


    /**
     * Sets the catalogId value for this MaterialDetails.
     * 
     * @param catalogId
     */
    public void setCatalogId(java.lang.String catalogId) {
        this.catalogId = catalogId;
    }


    /**
     * Gets the implicitCatalogEntryFlag value for this MaterialDetails.
     * 
     * @return implicitCatalogEntryFlag
     */
    public java.lang.String getImplicitCatalogEntryFlag() {
        return implicitCatalogEntryFlag;
    }


    /**
     * Sets the implicitCatalogEntryFlag value for this MaterialDetails.
     * 
     * @param implicitCatalogEntryFlag
     */
    public void setImplicitCatalogEntryFlag(java.lang.String implicitCatalogEntryFlag) {
        this.implicitCatalogEntryFlag = implicitCatalogEntryFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MaterialDetails)) return false;
        MaterialDetails other = (MaterialDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.materialNumber==null && other.getMaterialNumber()==null) || 
             (this.materialNumber!=null &&
              this.materialNumber.equals(other.getMaterialNumber()))) &&
            ((this.materialId==null && other.getMaterialId()==null) || 
             (this.materialId!=null &&
              this.materialId.equals(other.getMaterialId()))) &&
            ((this.quantity==null && other.getQuantity()==null) || 
             (this.quantity!=null &&
              this.quantity.equals(other.getQuantity()))) &&
            ((this.materialType==null && other.getMaterialType()==null) || 
             (this.materialType!=null &&
              this.materialType.equals(other.getMaterialType()))) &&
            ((this.printerNumber==null && other.getPrinterNumber()==null) || 
             (this.printerNumber!=null &&
              this.printerNumber.equals(other.getPrinterNumber()))) &&
            ((this.productId==null && other.getProductId()==null) || 
             (this.productId!=null &&
              this.productId.equals(other.getProductId()))) &&
            ((this.consumableType==null && other.getConsumableType()==null) || 
             (this.consumableType!=null &&
              this.consumableType.equals(other.getConsumableType()))) &&
            ((this.yield==null && other.getYield()==null) || 
             (this.yield!=null &&
              this.yield.equals(other.getYield()))) &&
            ((this.assetUsageType==null && other.getAssetUsageType()==null) || 
             (this.assetUsageType!=null &&
              this.assetUsageType.equals(other.getAssetUsageType()))) &&
            ((this.catalogId==null && other.getCatalogId()==null) || 
             (this.catalogId!=null &&
              this.catalogId.equals(other.getCatalogId()))) &&
            ((this.implicitCatalogEntryFlag==null && other.getImplicitCatalogEntryFlag()==null) || 
             (this.implicitCatalogEntryFlag!=null &&
              this.implicitCatalogEntryFlag.equals(other.getImplicitCatalogEntryFlag())));
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
        if (getMaterialNumber() != null) {
            _hashCode += getMaterialNumber().hashCode();
        }
        if (getMaterialId() != null) {
            _hashCode += getMaterialId().hashCode();
        }
        if (getQuantity() != null) {
            _hashCode += getQuantity().hashCode();
        }
        if (getMaterialType() != null) {
            _hashCode += getMaterialType().hashCode();
        }
        if (getPrinterNumber() != null) {
            _hashCode += getPrinterNumber().hashCode();
        }
        if (getProductId() != null) {
            _hashCode += getProductId().hashCode();
        }
        if (getConsumableType() != null) {
            _hashCode += getConsumableType().hashCode();
        }
        if (getYield() != null) {
            _hashCode += getYield().hashCode();
        }
        if (getAssetUsageType() != null) {
            _hashCode += getAssetUsageType().hashCode();
        }
        if (getCatalogId() != null) {
            _hashCode += getCatalogId().hashCode();
        }
        if (getImplicitCatalogEntryFlag() != null) {
            _hashCode += getImplicitCatalogEntryFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MaterialDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "MaterialDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("materialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MaterialNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("materialId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MaterialId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Quantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("materialType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MaterialType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printerNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrinterNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consumableType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ConsumableType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("yield");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Yield"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetUsageType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetUsageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("catalogId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CatalogId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("implicitCatalogEntryFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ImplicitCatalogEntryFlag"));
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
