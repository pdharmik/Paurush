/**
 * ReturnedAssetInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class ReturnedAssetInformation  implements java.io.Serializable {
    private java.lang.String assetId;

    private java.lang.String serialNumber;

    private java.lang.String assetTag;

    private java.lang.String productModel;

    private java.lang.String productNumber;

    private java.lang.String productLine;

    private java.lang.String assetType;

    private com.lexmark.SiebelShared.createServiceRequest.client.InstalledAtAddress2 installedAtAddress;

    private com.lexmark.SiebelShared.createServiceRequest.client.Entitlement3 entitlement;

    private com.lexmark.SiebelShared.createServiceRequest.client.AssetContact2 assetContact;

    private com.lexmark.SiebelShared.createServiceRequest.client.Account2 account;

    private java.lang.String IPAddress;

    private java.lang.String supportURL;

    private java.lang.String controlPanelURL;

    private java.lang.String knowledgeBaseURL;

    private java.lang.String productImageURL;

    private java.lang.String hostName;

    private java.lang.String deviceTag;

    private java.lang.String deviceName;

    private java.lang.String physicalLocation;

    private java.lang.String newMeterReadDate;

    private java.lang.String colorCapableIndicator;

    private java.lang.String lastMonoPageReadDate;

    private java.lang.String lastPageCount;

    private java.lang.String newPageCount;

    private java.lang.String lastColorPageReadDate;

    private java.lang.String lastColorPageCount;

    private java.lang.String newColorPageCount;

    private java.lang.String notMyPrinterFlag;

    public ReturnedAssetInformation() {
    }

    public ReturnedAssetInformation(
           java.lang.String assetId,
           java.lang.String serialNumber,
           java.lang.String assetTag,
           java.lang.String productModel,
           java.lang.String productNumber,
           java.lang.String productLine,
           java.lang.String assetType,
           com.lexmark.SiebelShared.createServiceRequest.client.InstalledAtAddress2 installedAtAddress,
           com.lexmark.SiebelShared.createServiceRequest.client.Entitlement3 entitlement,
           com.lexmark.SiebelShared.createServiceRequest.client.AssetContact2 assetContact,
           com.lexmark.SiebelShared.createServiceRequest.client.Account2 account,
           java.lang.String IPAddress,
           java.lang.String supportURL,
           java.lang.String controlPanelURL,
           java.lang.String knowledgeBaseURL,
           java.lang.String productImageURL,
           java.lang.String hostName,
           java.lang.String deviceTag,
           java.lang.String deviceName,
           java.lang.String physicalLocation,
           java.lang.String newMeterReadDate,
           java.lang.String colorCapableIndicator,
           java.lang.String lastMonoPageReadDate,
           java.lang.String lastPageCount,
           java.lang.String newPageCount,
           java.lang.String lastColorPageReadDate,
           java.lang.String lastColorPageCount,
           java.lang.String newColorPageCount,
           java.lang.String notMyPrinterFlag) {
           this.assetId = assetId;
           this.serialNumber = serialNumber;
           this.assetTag = assetTag;
           this.productModel = productModel;
           this.productNumber = productNumber;
           this.productLine = productLine;
           this.assetType = assetType;
           this.installedAtAddress = installedAtAddress;
           this.entitlement = entitlement;
           this.assetContact = assetContact;
           this.account = account;
           this.IPAddress = IPAddress;
           this.supportURL = supportURL;
           this.controlPanelURL = controlPanelURL;
           this.knowledgeBaseURL = knowledgeBaseURL;
           this.productImageURL = productImageURL;
           this.hostName = hostName;
           this.deviceTag = deviceTag;
           this.deviceName = deviceName;
           this.physicalLocation = physicalLocation;
           this.newMeterReadDate = newMeterReadDate;
           this.colorCapableIndicator = colorCapableIndicator;
           this.lastMonoPageReadDate = lastMonoPageReadDate;
           this.lastPageCount = lastPageCount;
           this.newPageCount = newPageCount;
           this.lastColorPageReadDate = lastColorPageReadDate;
           this.lastColorPageCount = lastColorPageCount;
           this.newColorPageCount = newColorPageCount;
           this.notMyPrinterFlag = notMyPrinterFlag;
    }


    /**
     * Gets the assetId value for this ReturnedAssetInformation.
     * 
     * @return assetId
     */
    public java.lang.String getAssetId() {
        return assetId;
    }


    /**
     * Sets the assetId value for this ReturnedAssetInformation.
     * 
     * @param assetId
     */
    public void setAssetId(java.lang.String assetId) {
        this.assetId = assetId;
    }


    /**
     * Gets the serialNumber value for this ReturnedAssetInformation.
     * 
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this ReturnedAssetInformation.
     * 
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }


    /**
     * Gets the assetTag value for this ReturnedAssetInformation.
     * 
     * @return assetTag
     */
    public java.lang.String getAssetTag() {
        return assetTag;
    }


    /**
     * Sets the assetTag value for this ReturnedAssetInformation.
     * 
     * @param assetTag
     */
    public void setAssetTag(java.lang.String assetTag) {
        this.assetTag = assetTag;
    }


    /**
     * Gets the productModel value for this ReturnedAssetInformation.
     * 
     * @return productModel
     */
    public java.lang.String getProductModel() {
        return productModel;
    }


    /**
     * Sets the productModel value for this ReturnedAssetInformation.
     * 
     * @param productModel
     */
    public void setProductModel(java.lang.String productModel) {
        this.productModel = productModel;
    }


    /**
     * Gets the productNumber value for this ReturnedAssetInformation.
     * 
     * @return productNumber
     */
    public java.lang.String getProductNumber() {
        return productNumber;
    }


    /**
     * Sets the productNumber value for this ReturnedAssetInformation.
     * 
     * @param productNumber
     */
    public void setProductNumber(java.lang.String productNumber) {
        this.productNumber = productNumber;
    }


    /**
     * Gets the productLine value for this ReturnedAssetInformation.
     * 
     * @return productLine
     */
    public java.lang.String getProductLine() {
        return productLine;
    }


    /**
     * Sets the productLine value for this ReturnedAssetInformation.
     * 
     * @param productLine
     */
    public void setProductLine(java.lang.String productLine) {
        this.productLine = productLine;
    }


    /**
     * Gets the assetType value for this ReturnedAssetInformation.
     * 
     * @return assetType
     */
    public java.lang.String getAssetType() {
        return assetType;
    }


    /**
     * Sets the assetType value for this ReturnedAssetInformation.
     * 
     * @param assetType
     */
    public void setAssetType(java.lang.String assetType) {
        this.assetType = assetType;
    }


    /**
     * Gets the installedAtAddress value for this ReturnedAssetInformation.
     * 
     * @return installedAtAddress
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.InstalledAtAddress2 getInstalledAtAddress() {
        return installedAtAddress;
    }


    /**
     * Sets the installedAtAddress value for this ReturnedAssetInformation.
     * 
     * @param installedAtAddress
     */
    public void setInstalledAtAddress(com.lexmark.SiebelShared.createServiceRequest.client.InstalledAtAddress2 installedAtAddress) {
        this.installedAtAddress = installedAtAddress;
    }


    /**
     * Gets the entitlement value for this ReturnedAssetInformation.
     * 
     * @return entitlement
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.Entitlement3 getEntitlement() {
        return entitlement;
    }


    /**
     * Sets the entitlement value for this ReturnedAssetInformation.
     * 
     * @param entitlement
     */
    public void setEntitlement(com.lexmark.SiebelShared.createServiceRequest.client.Entitlement3 entitlement) {
        this.entitlement = entitlement;
    }


    /**
     * Gets the assetContact value for this ReturnedAssetInformation.
     * 
     * @return assetContact
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.AssetContact2 getAssetContact() {
        return assetContact;
    }


    /**
     * Sets the assetContact value for this ReturnedAssetInformation.
     * 
     * @param assetContact
     */
    public void setAssetContact(com.lexmark.SiebelShared.createServiceRequest.client.AssetContact2 assetContact) {
        this.assetContact = assetContact;
    }


    /**
     * Gets the account value for this ReturnedAssetInformation.
     * 
     * @return account
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.Account2 getAccount() {
        return account;
    }


    /**
     * Sets the account value for this ReturnedAssetInformation.
     * 
     * @param account
     */
    public void setAccount(com.lexmark.SiebelShared.createServiceRequest.client.Account2 account) {
        this.account = account;
    }


    /**
     * Gets the IPAddress value for this ReturnedAssetInformation.
     * 
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this ReturnedAssetInformation.
     * 
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the supportURL value for this ReturnedAssetInformation.
     * 
     * @return supportURL
     */
    public java.lang.String getSupportURL() {
        return supportURL;
    }


    /**
     * Sets the supportURL value for this ReturnedAssetInformation.
     * 
     * @param supportURL
     */
    public void setSupportURL(java.lang.String supportURL) {
        this.supportURL = supportURL;
    }


    /**
     * Gets the controlPanelURL value for this ReturnedAssetInformation.
     * 
     * @return controlPanelURL
     */
    public java.lang.String getControlPanelURL() {
        return controlPanelURL;
    }


    /**
     * Sets the controlPanelURL value for this ReturnedAssetInformation.
     * 
     * @param controlPanelURL
     */
    public void setControlPanelURL(java.lang.String controlPanelURL) {
        this.controlPanelURL = controlPanelURL;
    }


    /**
     * Gets the knowledgeBaseURL value for this ReturnedAssetInformation.
     * 
     * @return knowledgeBaseURL
     */
    public java.lang.String getKnowledgeBaseURL() {
        return knowledgeBaseURL;
    }


    /**
     * Sets the knowledgeBaseURL value for this ReturnedAssetInformation.
     * 
     * @param knowledgeBaseURL
     */
    public void setKnowledgeBaseURL(java.lang.String knowledgeBaseURL) {
        this.knowledgeBaseURL = knowledgeBaseURL;
    }


    /**
     * Gets the productImageURL value for this ReturnedAssetInformation.
     * 
     * @return productImageURL
     */
    public java.lang.String getProductImageURL() {
        return productImageURL;
    }


    /**
     * Sets the productImageURL value for this ReturnedAssetInformation.
     * 
     * @param productImageURL
     */
    public void setProductImageURL(java.lang.String productImageURL) {
        this.productImageURL = productImageURL;
    }


    /**
     * Gets the hostName value for this ReturnedAssetInformation.
     * 
     * @return hostName
     */
    public java.lang.String getHostName() {
        return hostName;
    }


    /**
     * Sets the hostName value for this ReturnedAssetInformation.
     * 
     * @param hostName
     */
    public void setHostName(java.lang.String hostName) {
        this.hostName = hostName;
    }


    /**
     * Gets the deviceTag value for this ReturnedAssetInformation.
     * 
     * @return deviceTag
     */
    public java.lang.String getDeviceTag() {
        return deviceTag;
    }


    /**
     * Sets the deviceTag value for this ReturnedAssetInformation.
     * 
     * @param deviceTag
     */
    public void setDeviceTag(java.lang.String deviceTag) {
        this.deviceTag = deviceTag;
    }


    /**
     * Gets the deviceName value for this ReturnedAssetInformation.
     * 
     * @return deviceName
     */
    public java.lang.String getDeviceName() {
        return deviceName;
    }


    /**
     * Sets the deviceName value for this ReturnedAssetInformation.
     * 
     * @param deviceName
     */
    public void setDeviceName(java.lang.String deviceName) {
        this.deviceName = deviceName;
    }


    /**
     * Gets the physicalLocation value for this ReturnedAssetInformation.
     * 
     * @return physicalLocation
     */
    public java.lang.String getPhysicalLocation() {
        return physicalLocation;
    }


    /**
     * Sets the physicalLocation value for this ReturnedAssetInformation.
     * 
     * @param physicalLocation
     */
    public void setPhysicalLocation(java.lang.String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }


    /**
     * Gets the newMeterReadDate value for this ReturnedAssetInformation.
     * 
     * @return newMeterReadDate
     */
    public java.lang.String getNewMeterReadDate() {
        return newMeterReadDate;
    }


    /**
     * Sets the newMeterReadDate value for this ReturnedAssetInformation.
     * 
     * @param newMeterReadDate
     */
    public void setNewMeterReadDate(java.lang.String newMeterReadDate) {
        this.newMeterReadDate = newMeterReadDate;
    }


    /**
     * Gets the colorCapableIndicator value for this ReturnedAssetInformation.
     * 
     * @return colorCapableIndicator
     */
    public java.lang.String getColorCapableIndicator() {
        return colorCapableIndicator;
    }


    /**
     * Sets the colorCapableIndicator value for this ReturnedAssetInformation.
     * 
     * @param colorCapableIndicator
     */
    public void setColorCapableIndicator(java.lang.String colorCapableIndicator) {
        this.colorCapableIndicator = colorCapableIndicator;
    }


    /**
     * Gets the lastMonoPageReadDate value for this ReturnedAssetInformation.
     * 
     * @return lastMonoPageReadDate
     */
    public java.lang.String getLastMonoPageReadDate() {
        return lastMonoPageReadDate;
    }


    /**
     * Sets the lastMonoPageReadDate value for this ReturnedAssetInformation.
     * 
     * @param lastMonoPageReadDate
     */
    public void setLastMonoPageReadDate(java.lang.String lastMonoPageReadDate) {
        this.lastMonoPageReadDate = lastMonoPageReadDate;
    }


    /**
     * Gets the lastPageCount value for this ReturnedAssetInformation.
     * 
     * @return lastPageCount
     */
    public java.lang.String getLastPageCount() {
        return lastPageCount;
    }


    /**
     * Sets the lastPageCount value for this ReturnedAssetInformation.
     * 
     * @param lastPageCount
     */
    public void setLastPageCount(java.lang.String lastPageCount) {
        this.lastPageCount = lastPageCount;
    }


    /**
     * Gets the newPageCount value for this ReturnedAssetInformation.
     * 
     * @return newPageCount
     */
    public java.lang.String getNewPageCount() {
        return newPageCount;
    }


    /**
     * Sets the newPageCount value for this ReturnedAssetInformation.
     * 
     * @param newPageCount
     */
    public void setNewPageCount(java.lang.String newPageCount) {
        this.newPageCount = newPageCount;
    }


    /**
     * Gets the lastColorPageReadDate value for this ReturnedAssetInformation.
     * 
     * @return lastColorPageReadDate
     */
    public java.lang.String getLastColorPageReadDate() {
        return lastColorPageReadDate;
    }


    /**
     * Sets the lastColorPageReadDate value for this ReturnedAssetInformation.
     * 
     * @param lastColorPageReadDate
     */
    public void setLastColorPageReadDate(java.lang.String lastColorPageReadDate) {
        this.lastColorPageReadDate = lastColorPageReadDate;
    }


    /**
     * Gets the lastColorPageCount value for this ReturnedAssetInformation.
     * 
     * @return lastColorPageCount
     */
    public java.lang.String getLastColorPageCount() {
        return lastColorPageCount;
    }


    /**
     * Sets the lastColorPageCount value for this ReturnedAssetInformation.
     * 
     * @param lastColorPageCount
     */
    public void setLastColorPageCount(java.lang.String lastColorPageCount) {
        this.lastColorPageCount = lastColorPageCount;
    }


    /**
     * Gets the newColorPageCount value for this ReturnedAssetInformation.
     * 
     * @return newColorPageCount
     */
    public java.lang.String getNewColorPageCount() {
        return newColorPageCount;
    }


    /**
     * Sets the newColorPageCount value for this ReturnedAssetInformation.
     * 
     * @param newColorPageCount
     */
    public void setNewColorPageCount(java.lang.String newColorPageCount) {
        this.newColorPageCount = newColorPageCount;
    }


    /**
     * Gets the notMyPrinterFlag value for this ReturnedAssetInformation.
     * 
     * @return notMyPrinterFlag
     */
    public java.lang.String getNotMyPrinterFlag() {
        return notMyPrinterFlag;
    }


    /**
     * Sets the notMyPrinterFlag value for this ReturnedAssetInformation.
     * 
     * @param notMyPrinterFlag
     */
    public void setNotMyPrinterFlag(java.lang.String notMyPrinterFlag) {
        this.notMyPrinterFlag = notMyPrinterFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReturnedAssetInformation)) return false;
        ReturnedAssetInformation other = (ReturnedAssetInformation) obj;
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
            ((this.productLine==null && other.getProductLine()==null) || 
             (this.productLine!=null &&
              this.productLine.equals(other.getProductLine()))) &&
            ((this.assetType==null && other.getAssetType()==null) || 
             (this.assetType!=null &&
              this.assetType.equals(other.getAssetType()))) &&
            ((this.installedAtAddress==null && other.getInstalledAtAddress()==null) || 
             (this.installedAtAddress!=null &&
              this.installedAtAddress.equals(other.getInstalledAtAddress()))) &&
            ((this.entitlement==null && other.getEntitlement()==null) || 
             (this.entitlement!=null &&
              this.entitlement.equals(other.getEntitlement()))) &&
            ((this.assetContact==null && other.getAssetContact()==null) || 
             (this.assetContact!=null &&
              this.assetContact.equals(other.getAssetContact()))) &&
            ((this.account==null && other.getAccount()==null) || 
             (this.account!=null &&
              this.account.equals(other.getAccount()))) &&
            ((this.IPAddress==null && other.getIPAddress()==null) || 
             (this.IPAddress!=null &&
              this.IPAddress.equals(other.getIPAddress()))) &&
            ((this.supportURL==null && other.getSupportURL()==null) || 
             (this.supportURL!=null &&
              this.supportURL.equals(other.getSupportURL()))) &&
            ((this.controlPanelURL==null && other.getControlPanelURL()==null) || 
             (this.controlPanelURL!=null &&
              this.controlPanelURL.equals(other.getControlPanelURL()))) &&
            ((this.knowledgeBaseURL==null && other.getKnowledgeBaseURL()==null) || 
             (this.knowledgeBaseURL!=null &&
              this.knowledgeBaseURL.equals(other.getKnowledgeBaseURL()))) &&
            ((this.productImageURL==null && other.getProductImageURL()==null) || 
             (this.productImageURL!=null &&
              this.productImageURL.equals(other.getProductImageURL()))) &&
            ((this.hostName==null && other.getHostName()==null) || 
             (this.hostName!=null &&
              this.hostName.equals(other.getHostName()))) &&
            ((this.deviceTag==null && other.getDeviceTag()==null) || 
             (this.deviceTag!=null &&
              this.deviceTag.equals(other.getDeviceTag()))) &&
            ((this.deviceName==null && other.getDeviceName()==null) || 
             (this.deviceName!=null &&
              this.deviceName.equals(other.getDeviceName()))) &&
            ((this.physicalLocation==null && other.getPhysicalLocation()==null) || 
             (this.physicalLocation!=null &&
              this.physicalLocation.equals(other.getPhysicalLocation()))) &&
            ((this.newMeterReadDate==null && other.getNewMeterReadDate()==null) || 
             (this.newMeterReadDate!=null &&
              this.newMeterReadDate.equals(other.getNewMeterReadDate()))) &&
            ((this.colorCapableIndicator==null && other.getColorCapableIndicator()==null) || 
             (this.colorCapableIndicator!=null &&
              this.colorCapableIndicator.equals(other.getColorCapableIndicator()))) &&
            ((this.lastMonoPageReadDate==null && other.getLastMonoPageReadDate()==null) || 
             (this.lastMonoPageReadDate!=null &&
              this.lastMonoPageReadDate.equals(other.getLastMonoPageReadDate()))) &&
            ((this.lastPageCount==null && other.getLastPageCount()==null) || 
             (this.lastPageCount!=null &&
              this.lastPageCount.equals(other.getLastPageCount()))) &&
            ((this.newPageCount==null && other.getNewPageCount()==null) || 
             (this.newPageCount!=null &&
              this.newPageCount.equals(other.getNewPageCount()))) &&
            ((this.lastColorPageReadDate==null && other.getLastColorPageReadDate()==null) || 
             (this.lastColorPageReadDate!=null &&
              this.lastColorPageReadDate.equals(other.getLastColorPageReadDate()))) &&
            ((this.lastColorPageCount==null && other.getLastColorPageCount()==null) || 
             (this.lastColorPageCount!=null &&
              this.lastColorPageCount.equals(other.getLastColorPageCount()))) &&
            ((this.newColorPageCount==null && other.getNewColorPageCount()==null) || 
             (this.newColorPageCount!=null &&
              this.newColorPageCount.equals(other.getNewColorPageCount()))) &&
            ((this.notMyPrinterFlag==null && other.getNotMyPrinterFlag()==null) || 
             (this.notMyPrinterFlag!=null &&
              this.notMyPrinterFlag.equals(other.getNotMyPrinterFlag())));
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
        if (getProductLine() != null) {
            _hashCode += getProductLine().hashCode();
        }
        if (getAssetType() != null) {
            _hashCode += getAssetType().hashCode();
        }
        if (getInstalledAtAddress() != null) {
            _hashCode += getInstalledAtAddress().hashCode();
        }
        if (getEntitlement() != null) {
            _hashCode += getEntitlement().hashCode();
        }
        if (getAssetContact() != null) {
            _hashCode += getAssetContact().hashCode();
        }
        if (getAccount() != null) {
            _hashCode += getAccount().hashCode();
        }
        if (getIPAddress() != null) {
            _hashCode += getIPAddress().hashCode();
        }
        if (getSupportURL() != null) {
            _hashCode += getSupportURL().hashCode();
        }
        if (getControlPanelURL() != null) {
            _hashCode += getControlPanelURL().hashCode();
        }
        if (getKnowledgeBaseURL() != null) {
            _hashCode += getKnowledgeBaseURL().hashCode();
        }
        if (getProductImageURL() != null) {
            _hashCode += getProductImageURL().hashCode();
        }
        if (getHostName() != null) {
            _hashCode += getHostName().hashCode();
        }
        if (getDeviceTag() != null) {
            _hashCode += getDeviceTag().hashCode();
        }
        if (getDeviceName() != null) {
            _hashCode += getDeviceName().hashCode();
        }
        if (getPhysicalLocation() != null) {
            _hashCode += getPhysicalLocation().hashCode();
        }
        if (getNewMeterReadDate() != null) {
            _hashCode += getNewMeterReadDate().hashCode();
        }
        if (getColorCapableIndicator() != null) {
            _hashCode += getColorCapableIndicator().hashCode();
        }
        if (getLastMonoPageReadDate() != null) {
            _hashCode += getLastMonoPageReadDate().hashCode();
        }
        if (getLastPageCount() != null) {
            _hashCode += getLastPageCount().hashCode();
        }
        if (getNewPageCount() != null) {
            _hashCode += getNewPageCount().hashCode();
        }
        if (getLastColorPageReadDate() != null) {
            _hashCode += getLastColorPageReadDate().hashCode();
        }
        if (getLastColorPageCount() != null) {
            _hashCode += getLastColorPageCount().hashCode();
        }
        if (getNewColorPageCount() != null) {
            _hashCode += getNewColorPageCount().hashCode();
        }
        if (getNotMyPrinterFlag() != null) {
            _hashCode += getNotMyPrinterFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReturnedAssetInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReturnedAssetInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serialNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SerialNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productLine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("installedAtAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstalledAtAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "InstalledAtAddress2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entitlement");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Entitlement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Entitlement3"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetContact2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Account"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Account2"));
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
        elemField.setFieldName("supportURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SupportURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("controlPanelURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ControlPanelURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("knowledgeBaseURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KnowledgeBaseURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productImageURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductImageURL"));
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
        elemField.setFieldName("deviceTag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeviceTag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deviceName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeviceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("physicalLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PhysicalLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newMeterReadDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NewMeterReadDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colorCapableIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ColorCapableIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastMonoPageReadDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LastMonoPageReadDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastPageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LastPageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newPageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NewPageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastColorPageReadDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LastColorPageReadDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastColorPageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LastColorPageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newColorPageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NewColorPageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notMyPrinterFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NotMyPrinterFlag"));
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
