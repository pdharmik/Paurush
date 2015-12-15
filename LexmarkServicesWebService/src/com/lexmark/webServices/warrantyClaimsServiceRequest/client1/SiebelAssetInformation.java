/**
 * SiebelAssetInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public class SiebelAssetInformation  implements java.io.Serializable {
    private java.lang.String assetId;

    private java.lang.String serialNumber;

    private java.lang.String assetTag;

    private java.lang.String productModel;

    private java.lang.String productNumber;

    private java.lang.String productLine;

    private java.lang.String machineTypeModel;

    private java.lang.String assetType;

    private java.lang.String priorityAsset;

    private java.lang.String costCenter;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress installedAtAddress;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress serviceAddress;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Entitlement entitlement;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact assetContact;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Account account;

    private java.lang.String IPAddress;

    private java.lang.String supportURL;

    private java.lang.String controlPanelURL;

    private java.lang.String knowledgeBaseURL;

    private java.lang.String productImageURL;

    private java.lang.String hostName;

    private java.lang.String operatingSystem;

    private java.lang.String connectivityType;

    private java.lang.String deviceTag;

    private java.lang.String installedDate;

    private java.lang.String cartridgeDateCode;

    private java.lang.String newMeterReadDate;

    private java.lang.String colorCapableIndicator;

    private java.lang.String lastMonoPageReadDate;

    private java.lang.String lastPageCount;

    private java.lang.String newPageCount;

    private java.lang.String lastColorPageReadDate;

    private java.lang.String lastColorPageCount;

    private java.lang.String newColorPageCount;

    private java.lang.String lastMonoPageCount;

    private java.lang.String lastFaxPageCount;

    private java.lang.String lastScanPageCount;

    private java.lang.String totalLifetimePageCount;

    private java.lang.String monoLifetimePageCount;

    private java.lang.String colorLifetimePageCount;

    private java.lang.String scanLifetimePageCount;

    private java.lang.String faxLifetimePageCount;

    private java.lang.String notMyPrinterFlag;

    public SiebelAssetInformation() {
    }

    public SiebelAssetInformation(
           java.lang.String assetId,
           java.lang.String serialNumber,
           java.lang.String assetTag,
           java.lang.String productModel,
           java.lang.String productNumber,
           java.lang.String productLine,
           java.lang.String machineTypeModel,
           java.lang.String assetType,
           java.lang.String priorityAsset,
           java.lang.String costCenter,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress installedAtAddress,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress serviceAddress,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Entitlement entitlement,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact assetContact,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Account account,
           java.lang.String IPAddress,
           java.lang.String supportURL,
           java.lang.String controlPanelURL,
           java.lang.String knowledgeBaseURL,
           java.lang.String productImageURL,
           java.lang.String hostName,
           java.lang.String operatingSystem,
           java.lang.String connectivityType,
           java.lang.String deviceTag,
           java.lang.String installedDate,
           java.lang.String cartridgeDateCode,
           java.lang.String newMeterReadDate,
           java.lang.String colorCapableIndicator,
           java.lang.String lastMonoPageReadDate,
           java.lang.String lastPageCount,
           java.lang.String newPageCount,
           java.lang.String lastColorPageReadDate,
           java.lang.String lastColorPageCount,
           java.lang.String newColorPageCount,
           java.lang.String lastMonoPageCount,
           java.lang.String lastFaxPageCount,
           java.lang.String lastScanPageCount,
           java.lang.String totalLifetimePageCount,
           java.lang.String monoLifetimePageCount,
           java.lang.String colorLifetimePageCount,
           java.lang.String scanLifetimePageCount,
           java.lang.String faxLifetimePageCount,
           java.lang.String notMyPrinterFlag) {
           this.assetId = assetId;
           this.serialNumber = serialNumber;
           this.assetTag = assetTag;
           this.productModel = productModel;
           this.productNumber = productNumber;
           this.productLine = productLine;
           this.machineTypeModel = machineTypeModel;
           this.assetType = assetType;
           this.priorityAsset = priorityAsset;
           this.costCenter = costCenter;
           this.installedAtAddress = installedAtAddress;
           this.serviceAddress = serviceAddress;
           this.entitlement = entitlement;
           this.assetContact = assetContact;
           this.account = account;
           this.IPAddress = IPAddress;
           this.supportURL = supportURL;
           this.controlPanelURL = controlPanelURL;
           this.knowledgeBaseURL = knowledgeBaseURL;
           this.productImageURL = productImageURL;
           this.hostName = hostName;
           this.operatingSystem = operatingSystem;
           this.connectivityType = connectivityType;
           this.deviceTag = deviceTag;
           this.installedDate = installedDate;
           this.cartridgeDateCode = cartridgeDateCode;
           this.newMeterReadDate = newMeterReadDate;
           this.colorCapableIndicator = colorCapableIndicator;
           this.lastMonoPageReadDate = lastMonoPageReadDate;
           this.lastPageCount = lastPageCount;
           this.newPageCount = newPageCount;
           this.lastColorPageReadDate = lastColorPageReadDate;
           this.lastColorPageCount = lastColorPageCount;
           this.newColorPageCount = newColorPageCount;
           this.lastMonoPageCount = lastMonoPageCount;
           this.lastFaxPageCount = lastFaxPageCount;
           this.lastScanPageCount = lastScanPageCount;
           this.totalLifetimePageCount = totalLifetimePageCount;
           this.monoLifetimePageCount = monoLifetimePageCount;
           this.colorLifetimePageCount = colorLifetimePageCount;
           this.scanLifetimePageCount = scanLifetimePageCount;
           this.faxLifetimePageCount = faxLifetimePageCount;
           this.notMyPrinterFlag = notMyPrinterFlag;
    }


    /**
     * Gets the assetId value for this SiebelAssetInformation.
     * 
     * @return assetId
     */
    public java.lang.String getAssetId() {
        return assetId;
    }


    /**
     * Sets the assetId value for this SiebelAssetInformation.
     * 
     * @param assetId
     */
    public void setAssetId(java.lang.String assetId) {
        this.assetId = assetId;
    }


    /**
     * Gets the serialNumber value for this SiebelAssetInformation.
     * 
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this SiebelAssetInformation.
     * 
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }


    /**
     * Gets the assetTag value for this SiebelAssetInformation.
     * 
     * @return assetTag
     */
    public java.lang.String getAssetTag() {
        return assetTag;
    }


    /**
     * Sets the assetTag value for this SiebelAssetInformation.
     * 
     * @param assetTag
     */
    public void setAssetTag(java.lang.String assetTag) {
        this.assetTag = assetTag;
    }


    /**
     * Gets the productModel value for this SiebelAssetInformation.
     * 
     * @return productModel
     */
    public java.lang.String getProductModel() {
        return productModel;
    }


    /**
     * Sets the productModel value for this SiebelAssetInformation.
     * 
     * @param productModel
     */
    public void setProductModel(java.lang.String productModel) {
        this.productModel = productModel;
    }


    /**
     * Gets the productNumber value for this SiebelAssetInformation.
     * 
     * @return productNumber
     */
    public java.lang.String getProductNumber() {
        return productNumber;
    }


    /**
     * Sets the productNumber value for this SiebelAssetInformation.
     * 
     * @param productNumber
     */
    public void setProductNumber(java.lang.String productNumber) {
        this.productNumber = productNumber;
    }


    /**
     * Gets the productLine value for this SiebelAssetInformation.
     * 
     * @return productLine
     */
    public java.lang.String getProductLine() {
        return productLine;
    }


    /**
     * Sets the productLine value for this SiebelAssetInformation.
     * 
     * @param productLine
     */
    public void setProductLine(java.lang.String productLine) {
        this.productLine = productLine;
    }


    /**
     * Gets the machineTypeModel value for this SiebelAssetInformation.
     * 
     * @return machineTypeModel
     */
    public java.lang.String getMachineTypeModel() {
        return machineTypeModel;
    }


    /**
     * Sets the machineTypeModel value for this SiebelAssetInformation.
     * 
     * @param machineTypeModel
     */
    public void setMachineTypeModel(java.lang.String machineTypeModel) {
        this.machineTypeModel = machineTypeModel;
    }


    /**
     * Gets the assetType value for this SiebelAssetInformation.
     * 
     * @return assetType
     */
    public java.lang.String getAssetType() {
        return assetType;
    }


    /**
     * Sets the assetType value for this SiebelAssetInformation.
     * 
     * @param assetType
     */
    public void setAssetType(java.lang.String assetType) {
        this.assetType = assetType;
    }


    /**
     * Gets the priorityAsset value for this SiebelAssetInformation.
     * 
     * @return priorityAsset
     */
    public java.lang.String getPriorityAsset() {
        return priorityAsset;
    }


    /**
     * Sets the priorityAsset value for this SiebelAssetInformation.
     * 
     * @param priorityAsset
     */
    public void setPriorityAsset(java.lang.String priorityAsset) {
        this.priorityAsset = priorityAsset;
    }


    /**
     * Gets the costCenter value for this SiebelAssetInformation.
     * 
     * @return costCenter
     */
    public java.lang.String getCostCenter() {
        return costCenter;
    }


    /**
     * Sets the costCenter value for this SiebelAssetInformation.
     * 
     * @param costCenter
     */
    public void setCostCenter(java.lang.String costCenter) {
        this.costCenter = costCenter;
    }


    /**
     * Gets the installedAtAddress value for this SiebelAssetInformation.
     * 
     * @return installedAtAddress
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress getInstalledAtAddress() {
        return installedAtAddress;
    }


    /**
     * Sets the installedAtAddress value for this SiebelAssetInformation.
     * 
     * @param installedAtAddress
     */
    public void setInstalledAtAddress(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress installedAtAddress) {
        this.installedAtAddress = installedAtAddress;
    }


    /**
     * Gets the serviceAddress value for this SiebelAssetInformation.
     * 
     * @return serviceAddress
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress getServiceAddress() {
        return serviceAddress;
    }


    /**
     * Sets the serviceAddress value for this SiebelAssetInformation.
     * 
     * @param serviceAddress
     */
    public void setServiceAddress(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAddress serviceAddress) {
        this.serviceAddress = serviceAddress;
    }


    /**
     * Gets the entitlement value for this SiebelAssetInformation.
     * 
     * @return entitlement
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Entitlement getEntitlement() {
        return entitlement;
    }


    /**
     * Sets the entitlement value for this SiebelAssetInformation.
     * 
     * @param entitlement
     */
    public void setEntitlement(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Entitlement entitlement) {
        this.entitlement = entitlement;
    }


    /**
     * Gets the assetContact value for this SiebelAssetInformation.
     * 
     * @return assetContact
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact getAssetContact() {
        return assetContact;
    }


    /**
     * Sets the assetContact value for this SiebelAssetInformation.
     * 
     * @param assetContact
     */
    public void setAssetContact(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact assetContact) {
        this.assetContact = assetContact;
    }


    /**
     * Gets the account value for this SiebelAssetInformation.
     * 
     * @return account
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Account getAccount() {
        return account;
    }


    /**
     * Sets the account value for this SiebelAssetInformation.
     * 
     * @param account
     */
    public void setAccount(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.Account account) {
        this.account = account;
    }


    /**
     * Gets the IPAddress value for this SiebelAssetInformation.
     * 
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this SiebelAssetInformation.
     * 
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the supportURL value for this SiebelAssetInformation.
     * 
     * @return supportURL
     */
    public java.lang.String getSupportURL() {
        return supportURL;
    }


    /**
     * Sets the supportURL value for this SiebelAssetInformation.
     * 
     * @param supportURL
     */
    public void setSupportURL(java.lang.String supportURL) {
        this.supportURL = supportURL;
    }


    /**
     * Gets the controlPanelURL value for this SiebelAssetInformation.
     * 
     * @return controlPanelURL
     */
    public java.lang.String getControlPanelURL() {
        return controlPanelURL;
    }


    /**
     * Sets the controlPanelURL value for this SiebelAssetInformation.
     * 
     * @param controlPanelURL
     */
    public void setControlPanelURL(java.lang.String controlPanelURL) {
        this.controlPanelURL = controlPanelURL;
    }


    /**
     * Gets the knowledgeBaseURL value for this SiebelAssetInformation.
     * 
     * @return knowledgeBaseURL
     */
    public java.lang.String getKnowledgeBaseURL() {
        return knowledgeBaseURL;
    }


    /**
     * Sets the knowledgeBaseURL value for this SiebelAssetInformation.
     * 
     * @param knowledgeBaseURL
     */
    public void setKnowledgeBaseURL(java.lang.String knowledgeBaseURL) {
        this.knowledgeBaseURL = knowledgeBaseURL;
    }


    /**
     * Gets the productImageURL value for this SiebelAssetInformation.
     * 
     * @return productImageURL
     */
    public java.lang.String getProductImageURL() {
        return productImageURL;
    }


    /**
     * Sets the productImageURL value for this SiebelAssetInformation.
     * 
     * @param productImageURL
     */
    public void setProductImageURL(java.lang.String productImageURL) {
        this.productImageURL = productImageURL;
    }


    /**
     * Gets the hostName value for this SiebelAssetInformation.
     * 
     * @return hostName
     */
    public java.lang.String getHostName() {
        return hostName;
    }


    /**
     * Sets the hostName value for this SiebelAssetInformation.
     * 
     * @param hostName
     */
    public void setHostName(java.lang.String hostName) {
        this.hostName = hostName;
    }


    /**
     * Gets the operatingSystem value for this SiebelAssetInformation.
     * 
     * @return operatingSystem
     */
    public java.lang.String getOperatingSystem() {
        return operatingSystem;
    }


    /**
     * Sets the operatingSystem value for this SiebelAssetInformation.
     * 
     * @param operatingSystem
     */
    public void setOperatingSystem(java.lang.String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }


    /**
     * Gets the connectivityType value for this SiebelAssetInformation.
     * 
     * @return connectivityType
     */
    public java.lang.String getConnectivityType() {
        return connectivityType;
    }


    /**
     * Sets the connectivityType value for this SiebelAssetInformation.
     * 
     * @param connectivityType
     */
    public void setConnectivityType(java.lang.String connectivityType) {
        this.connectivityType = connectivityType;
    }


    /**
     * Gets the deviceTag value for this SiebelAssetInformation.
     * 
     * @return deviceTag
     */
    public java.lang.String getDeviceTag() {
        return deviceTag;
    }


    /**
     * Sets the deviceTag value for this SiebelAssetInformation.
     * 
     * @param deviceTag
     */
    public void setDeviceTag(java.lang.String deviceTag) {
        this.deviceTag = deviceTag;
    }


    /**
     * Gets the installedDate value for this SiebelAssetInformation.
     * 
     * @return installedDate
     */
    public java.lang.String getInstalledDate() {
        return installedDate;
    }


    /**
     * Sets the installedDate value for this SiebelAssetInformation.
     * 
     * @param installedDate
     */
    public void setInstalledDate(java.lang.String installedDate) {
        this.installedDate = installedDate;
    }


    /**
     * Gets the cartridgeDateCode value for this SiebelAssetInformation.
     * 
     * @return cartridgeDateCode
     */
    public java.lang.String getCartridgeDateCode() {
        return cartridgeDateCode;
    }


    /**
     * Sets the cartridgeDateCode value for this SiebelAssetInformation.
     * 
     * @param cartridgeDateCode
     */
    public void setCartridgeDateCode(java.lang.String cartridgeDateCode) {
        this.cartridgeDateCode = cartridgeDateCode;
    }


    /**
     * Gets the newMeterReadDate value for this SiebelAssetInformation.
     * 
     * @return newMeterReadDate
     */
    public java.lang.String getNewMeterReadDate() {
        return newMeterReadDate;
    }


    /**
     * Sets the newMeterReadDate value for this SiebelAssetInformation.
     * 
     * @param newMeterReadDate
     */
    public void setNewMeterReadDate(java.lang.String newMeterReadDate) {
        this.newMeterReadDate = newMeterReadDate;
    }


    /**
     * Gets the colorCapableIndicator value for this SiebelAssetInformation.
     * 
     * @return colorCapableIndicator
     */
    public java.lang.String getColorCapableIndicator() {
        return colorCapableIndicator;
    }


    /**
     * Sets the colorCapableIndicator value for this SiebelAssetInformation.
     * 
     * @param colorCapableIndicator
     */
    public void setColorCapableIndicator(java.lang.String colorCapableIndicator) {
        this.colorCapableIndicator = colorCapableIndicator;
    }


    /**
     * Gets the lastMonoPageReadDate value for this SiebelAssetInformation.
     * 
     * @return lastMonoPageReadDate
     */
    public java.lang.String getLastMonoPageReadDate() {
        return lastMonoPageReadDate;
    }


    /**
     * Sets the lastMonoPageReadDate value for this SiebelAssetInformation.
     * 
     * @param lastMonoPageReadDate
     */
    public void setLastMonoPageReadDate(java.lang.String lastMonoPageReadDate) {
        this.lastMonoPageReadDate = lastMonoPageReadDate;
    }


    /**
     * Gets the lastPageCount value for this SiebelAssetInformation.
     * 
     * @return lastPageCount
     */
    public java.lang.String getLastPageCount() {
        return lastPageCount;
    }


    /**
     * Sets the lastPageCount value for this SiebelAssetInformation.
     * 
     * @param lastPageCount
     */
    public void setLastPageCount(java.lang.String lastPageCount) {
        this.lastPageCount = lastPageCount;
    }


    /**
     * Gets the newPageCount value for this SiebelAssetInformation.
     * 
     * @return newPageCount
     */
    public java.lang.String getNewPageCount() {
        return newPageCount;
    }


    /**
     * Sets the newPageCount value for this SiebelAssetInformation.
     * 
     * @param newPageCount
     */
    public void setNewPageCount(java.lang.String newPageCount) {
        this.newPageCount = newPageCount;
    }


    /**
     * Gets the lastColorPageReadDate value for this SiebelAssetInformation.
     * 
     * @return lastColorPageReadDate
     */
    public java.lang.String getLastColorPageReadDate() {
        return lastColorPageReadDate;
    }


    /**
     * Sets the lastColorPageReadDate value for this SiebelAssetInformation.
     * 
     * @param lastColorPageReadDate
     */
    public void setLastColorPageReadDate(java.lang.String lastColorPageReadDate) {
        this.lastColorPageReadDate = lastColorPageReadDate;
    }


    /**
     * Gets the lastColorPageCount value for this SiebelAssetInformation.
     * 
     * @return lastColorPageCount
     */
    public java.lang.String getLastColorPageCount() {
        return lastColorPageCount;
    }


    /**
     * Sets the lastColorPageCount value for this SiebelAssetInformation.
     * 
     * @param lastColorPageCount
     */
    public void setLastColorPageCount(java.lang.String lastColorPageCount) {
        this.lastColorPageCount = lastColorPageCount;
    }


    /**
     * Gets the newColorPageCount value for this SiebelAssetInformation.
     * 
     * @return newColorPageCount
     */
    public java.lang.String getNewColorPageCount() {
        return newColorPageCount;
    }


    /**
     * Sets the newColorPageCount value for this SiebelAssetInformation.
     * 
     * @param newColorPageCount
     */
    public void setNewColorPageCount(java.lang.String newColorPageCount) {
        this.newColorPageCount = newColorPageCount;
    }


    /**
     * Gets the lastMonoPageCount value for this SiebelAssetInformation.
     * 
     * @return lastMonoPageCount
     */
    public java.lang.String getLastMonoPageCount() {
        return lastMonoPageCount;
    }


    /**
     * Sets the lastMonoPageCount value for this SiebelAssetInformation.
     * 
     * @param lastMonoPageCount
     */
    public void setLastMonoPageCount(java.lang.String lastMonoPageCount) {
        this.lastMonoPageCount = lastMonoPageCount;
    }


    /**
     * Gets the lastFaxPageCount value for this SiebelAssetInformation.
     * 
     * @return lastFaxPageCount
     */
    public java.lang.String getLastFaxPageCount() {
        return lastFaxPageCount;
    }


    /**
     * Sets the lastFaxPageCount value for this SiebelAssetInformation.
     * 
     * @param lastFaxPageCount
     */
    public void setLastFaxPageCount(java.lang.String lastFaxPageCount) {
        this.lastFaxPageCount = lastFaxPageCount;
    }


    /**
     * Gets the lastScanPageCount value for this SiebelAssetInformation.
     * 
     * @return lastScanPageCount
     */
    public java.lang.String getLastScanPageCount() {
        return lastScanPageCount;
    }


    /**
     * Sets the lastScanPageCount value for this SiebelAssetInformation.
     * 
     * @param lastScanPageCount
     */
    public void setLastScanPageCount(java.lang.String lastScanPageCount) {
        this.lastScanPageCount = lastScanPageCount;
    }


    /**
     * Gets the totalLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @return totalLifetimePageCount
     */
    public java.lang.String getTotalLifetimePageCount() {
        return totalLifetimePageCount;
    }


    /**
     * Sets the totalLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @param totalLifetimePageCount
     */
    public void setTotalLifetimePageCount(java.lang.String totalLifetimePageCount) {
        this.totalLifetimePageCount = totalLifetimePageCount;
    }


    /**
     * Gets the monoLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @return monoLifetimePageCount
     */
    public java.lang.String getMonoLifetimePageCount() {
        return monoLifetimePageCount;
    }


    /**
     * Sets the monoLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @param monoLifetimePageCount
     */
    public void setMonoLifetimePageCount(java.lang.String monoLifetimePageCount) {
        this.monoLifetimePageCount = monoLifetimePageCount;
    }


    /**
     * Gets the colorLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @return colorLifetimePageCount
     */
    public java.lang.String getColorLifetimePageCount() {
        return colorLifetimePageCount;
    }


    /**
     * Sets the colorLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @param colorLifetimePageCount
     */
    public void setColorLifetimePageCount(java.lang.String colorLifetimePageCount) {
        this.colorLifetimePageCount = colorLifetimePageCount;
    }


    /**
     * Gets the scanLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @return scanLifetimePageCount
     */
    public java.lang.String getScanLifetimePageCount() {
        return scanLifetimePageCount;
    }


    /**
     * Sets the scanLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @param scanLifetimePageCount
     */
    public void setScanLifetimePageCount(java.lang.String scanLifetimePageCount) {
        this.scanLifetimePageCount = scanLifetimePageCount;
    }


    /**
     * Gets the faxLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @return faxLifetimePageCount
     */
    public java.lang.String getFaxLifetimePageCount() {
        return faxLifetimePageCount;
    }


    /**
     * Sets the faxLifetimePageCount value for this SiebelAssetInformation.
     * 
     * @param faxLifetimePageCount
     */
    public void setFaxLifetimePageCount(java.lang.String faxLifetimePageCount) {
        this.faxLifetimePageCount = faxLifetimePageCount;
    }


    /**
     * Gets the notMyPrinterFlag value for this SiebelAssetInformation.
     * 
     * @return notMyPrinterFlag
     */
    public java.lang.String getNotMyPrinterFlag() {
        return notMyPrinterFlag;
    }


    /**
     * Sets the notMyPrinterFlag value for this SiebelAssetInformation.
     * 
     * @param notMyPrinterFlag
     */
    public void setNotMyPrinterFlag(java.lang.String notMyPrinterFlag) {
        this.notMyPrinterFlag = notMyPrinterFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelAssetInformation)) return false;
        SiebelAssetInformation other = (SiebelAssetInformation) obj;
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
            ((this.machineTypeModel==null && other.getMachineTypeModel()==null) || 
             (this.machineTypeModel!=null &&
              this.machineTypeModel.equals(other.getMachineTypeModel()))) &&
            ((this.assetType==null && other.getAssetType()==null) || 
             (this.assetType!=null &&
              this.assetType.equals(other.getAssetType()))) &&
            ((this.priorityAsset==null && other.getPriorityAsset()==null) || 
             (this.priorityAsset!=null &&
              this.priorityAsset.equals(other.getPriorityAsset()))) &&
            ((this.costCenter==null && other.getCostCenter()==null) || 
             (this.costCenter!=null &&
              this.costCenter.equals(other.getCostCenter()))) &&
            ((this.installedAtAddress==null && other.getInstalledAtAddress()==null) || 
             (this.installedAtAddress!=null &&
              this.installedAtAddress.equals(other.getInstalledAtAddress()))) &&
            ((this.serviceAddress==null && other.getServiceAddress()==null) || 
             (this.serviceAddress!=null &&
              this.serviceAddress.equals(other.getServiceAddress()))) &&
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
            ((this.operatingSystem==null && other.getOperatingSystem()==null) || 
             (this.operatingSystem!=null &&
              this.operatingSystem.equals(other.getOperatingSystem()))) &&
            ((this.connectivityType==null && other.getConnectivityType()==null) || 
             (this.connectivityType!=null &&
              this.connectivityType.equals(other.getConnectivityType()))) &&
            ((this.deviceTag==null && other.getDeviceTag()==null) || 
             (this.deviceTag!=null &&
              this.deviceTag.equals(other.getDeviceTag()))) &&
            ((this.installedDate==null && other.getInstalledDate()==null) || 
             (this.installedDate!=null &&
              this.installedDate.equals(other.getInstalledDate()))) &&
            ((this.cartridgeDateCode==null && other.getCartridgeDateCode()==null) || 
             (this.cartridgeDateCode!=null &&
              this.cartridgeDateCode.equals(other.getCartridgeDateCode()))) &&
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
            ((this.lastMonoPageCount==null && other.getLastMonoPageCount()==null) || 
             (this.lastMonoPageCount!=null &&
              this.lastMonoPageCount.equals(other.getLastMonoPageCount()))) &&
            ((this.lastFaxPageCount==null && other.getLastFaxPageCount()==null) || 
             (this.lastFaxPageCount!=null &&
              this.lastFaxPageCount.equals(other.getLastFaxPageCount()))) &&
            ((this.lastScanPageCount==null && other.getLastScanPageCount()==null) || 
             (this.lastScanPageCount!=null &&
              this.lastScanPageCount.equals(other.getLastScanPageCount()))) &&
            ((this.totalLifetimePageCount==null && other.getTotalLifetimePageCount()==null) || 
             (this.totalLifetimePageCount!=null &&
              this.totalLifetimePageCount.equals(other.getTotalLifetimePageCount()))) &&
            ((this.monoLifetimePageCount==null && other.getMonoLifetimePageCount()==null) || 
             (this.monoLifetimePageCount!=null &&
              this.monoLifetimePageCount.equals(other.getMonoLifetimePageCount()))) &&
            ((this.colorLifetimePageCount==null && other.getColorLifetimePageCount()==null) || 
             (this.colorLifetimePageCount!=null &&
              this.colorLifetimePageCount.equals(other.getColorLifetimePageCount()))) &&
            ((this.scanLifetimePageCount==null && other.getScanLifetimePageCount()==null) || 
             (this.scanLifetimePageCount!=null &&
              this.scanLifetimePageCount.equals(other.getScanLifetimePageCount()))) &&
            ((this.faxLifetimePageCount==null && other.getFaxLifetimePageCount()==null) || 
             (this.faxLifetimePageCount!=null &&
              this.faxLifetimePageCount.equals(other.getFaxLifetimePageCount()))) &&
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
        if (getMachineTypeModel() != null) {
            _hashCode += getMachineTypeModel().hashCode();
        }
        if (getAssetType() != null) {
            _hashCode += getAssetType().hashCode();
        }
        if (getPriorityAsset() != null) {
            _hashCode += getPriorityAsset().hashCode();
        }
        if (getCostCenter() != null) {
            _hashCode += getCostCenter().hashCode();
        }
        if (getInstalledAtAddress() != null) {
            _hashCode += getInstalledAtAddress().hashCode();
        }
        if (getServiceAddress() != null) {
            _hashCode += getServiceAddress().hashCode();
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
        if (getOperatingSystem() != null) {
            _hashCode += getOperatingSystem().hashCode();
        }
        if (getConnectivityType() != null) {
            _hashCode += getConnectivityType().hashCode();
        }
        if (getDeviceTag() != null) {
            _hashCode += getDeviceTag().hashCode();
        }
        if (getInstalledDate() != null) {
            _hashCode += getInstalledDate().hashCode();
        }
        if (getCartridgeDateCode() != null) {
            _hashCode += getCartridgeDateCode().hashCode();
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
        if (getLastMonoPageCount() != null) {
            _hashCode += getLastMonoPageCount().hashCode();
        }
        if (getLastFaxPageCount() != null) {
            _hashCode += getLastFaxPageCount().hashCode();
        }
        if (getLastScanPageCount() != null) {
            _hashCode += getLastScanPageCount().hashCode();
        }
        if (getTotalLifetimePageCount() != null) {
            _hashCode += getTotalLifetimePageCount().hashCode();
        }
        if (getMonoLifetimePageCount() != null) {
            _hashCode += getMonoLifetimePageCount().hashCode();
        }
        if (getColorLifetimePageCount() != null) {
            _hashCode += getColorLifetimePageCount().hashCode();
        }
        if (getScanLifetimePageCount() != null) {
            _hashCode += getScanLifetimePageCount().hashCode();
        }
        if (getFaxLifetimePageCount() != null) {
            _hashCode += getFaxLifetimePageCount().hashCode();
        }
        if (getNotMyPrinterFlag() != null) {
            _hashCode += getNotMyPrinterFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SiebelAssetInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelAssetInformation"));
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
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("machineTypeModel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MachineTypeModel"));
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
        elemField.setFieldName("priorityAsset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PriorityAsset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("costCenter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CostCenter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("installedAtAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstalledAtAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entitlement");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Entitlement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "Entitlement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelContact"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Account"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "Account"));
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
        elemField.setFieldName("operatingSystem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OperatingSystem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("connectivityType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ConnectivityType"));
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
        elemField.setFieldName("installedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstalledDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cartridgeDateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CartridgeDateCode"));
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
        elemField.setFieldName("lastMonoPageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LastMonoPageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastFaxPageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LastFaxPageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastScanPageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LastScanPageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalLifetimePageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TotalLifetimePageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monoLifetimePageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MonoLifetimePageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colorLifetimePageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ColorLifetimePageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scanLifetimePageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ScanLifetimePageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxLifetimePageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FaxLifetimePageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
