/**
 * SiebelAssetManagementInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.createServiceRequest.client;

public class SiebelAssetManagementInformation  implements java.io.Serializable {
    private java.lang.String assetId;

    private java.lang.String serialNumber;

    private java.lang.String assetTag;

    private java.lang.String deviceTag;

    private java.lang.String productModel;

    private java.lang.String productNumber;

    private java.lang.String productDescription;

    private java.lang.String productLine;

    private java.lang.String machineTypeModel;

    private java.lang.String assetType;

    private java.lang.String assetCostCenter;

    private java.lang.String assetDepartmentName;

    private java.lang.String assetDepartmentNumber;

    private java.lang.String IPAddress;

    private java.lang.String IPSubMask;

    private java.lang.String IPGateway;

    private java.lang.String IPV6;

    private java.lang.String MACAddress;

    private java.lang.String hostName;

    private java.lang.String computerName;

    private java.lang.String brand;

    private java.lang.String networkConnected;

    private java.lang.String portNumber;

    private java.lang.String wiringClosetNumber;

    private java.lang.String faxConnected;

    private java.lang.String faxPortNumber;

    private java.lang.String operatingSystem;

    private java.lang.String operatingSystemVersion;

    private java.lang.String firmware;

    private java.lang.String workingCondition;

    private java.lang.String networkTopology;

    private java.lang.String printServer;

    private java.lang.String specialUsage;

    private java.lang.String relatedCustomerHierarchyLevel;

    private java.lang.String installDate;

    private java.lang.String customerAssetData1;

    private java.lang.String customerAssetData2;

    private java.lang.String customerAssetData3;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress installedAtAddress;

    private com.lexmark.SiebelShared.createServiceRequest.client.SiebelMeterReadInformation[] meterReadInformation;

    public SiebelAssetManagementInformation() {
    }

    public SiebelAssetManagementInformation(
           java.lang.String assetId,
           java.lang.String serialNumber,
           java.lang.String assetTag,
           java.lang.String deviceTag,
           java.lang.String productModel,
           java.lang.String productNumber,
           java.lang.String productDescription,
           java.lang.String productLine,
           java.lang.String machineTypeModel,
           java.lang.String assetType,
           java.lang.String assetCostCenter,
           java.lang.String assetDepartmentName,
           java.lang.String assetDepartmentNumber,
           java.lang.String IPAddress,
           java.lang.String IPSubMask,
           java.lang.String IPGateway,
           java.lang.String IPV6,
           java.lang.String MACAddress,
           java.lang.String hostName,
           java.lang.String computerName,
           java.lang.String brand,
           java.lang.String networkConnected,
           java.lang.String portNumber,
           java.lang.String wiringClosetNumber,
           java.lang.String faxConnected,
           java.lang.String faxPortNumber,
           java.lang.String operatingSystem,
           java.lang.String operatingSystemVersion,
           java.lang.String firmware,
           java.lang.String workingCondition,
           java.lang.String networkTopology,
           java.lang.String printServer,
           java.lang.String specialUsage,
           java.lang.String relatedCustomerHierarchyLevel,
           java.lang.String installDate,
           java.lang.String customerAssetData1,
           java.lang.String customerAssetData2,
           java.lang.String customerAssetData3,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress installedAtAddress,
           com.lexmark.SiebelShared.createServiceRequest.client.SiebelMeterReadInformation[] meterReadInformation) {
           this.assetId = assetId;
           this.serialNumber = serialNumber;
           this.assetTag = assetTag;
           this.deviceTag = deviceTag;
           this.productModel = productModel;
           this.productNumber = productNumber;
           this.productDescription = productDescription;
           this.productLine = productLine;
           this.machineTypeModel = machineTypeModel;
           this.assetType = assetType;
           this.assetCostCenter = assetCostCenter;
           this.assetDepartmentName = assetDepartmentName;
           this.assetDepartmentNumber = assetDepartmentNumber;
           this.IPAddress = IPAddress;
           this.IPSubMask = IPSubMask;
           this.IPGateway = IPGateway;
           this.IPV6 = IPV6;
           this.MACAddress = MACAddress;
           this.hostName = hostName;
           this.computerName = computerName;
           this.brand = brand;
           this.networkConnected = networkConnected;
           this.portNumber = portNumber;
           this.wiringClosetNumber = wiringClosetNumber;
           this.faxConnected = faxConnected;
           this.faxPortNumber = faxPortNumber;
           this.operatingSystem = operatingSystem;
           this.operatingSystemVersion = operatingSystemVersion;
           this.firmware = firmware;
           this.workingCondition = workingCondition;
           this.networkTopology = networkTopology;
           this.printServer = printServer;
           this.specialUsage = specialUsage;
           this.relatedCustomerHierarchyLevel = relatedCustomerHierarchyLevel;
           this.installDate = installDate;
           this.customerAssetData1 = customerAssetData1;
           this.customerAssetData2 = customerAssetData2;
           this.customerAssetData3 = customerAssetData3;
           this.installedAtAddress = installedAtAddress;
           this.meterReadInformation = meterReadInformation;
    }


    /**
     * Gets the assetId value for this SiebelAssetManagementInformation.
     * 
     * @return assetId
     */
    public java.lang.String getAssetId() {
        return assetId;
    }


    /**
     * Sets the assetId value for this SiebelAssetManagementInformation.
     * 
     * @param assetId
     */
    public void setAssetId(java.lang.String assetId) {
        this.assetId = assetId;
    }


    /**
     * Gets the serialNumber value for this SiebelAssetManagementInformation.
     * 
     * @return serialNumber
     */
    public java.lang.String getSerialNumber() {
        return serialNumber;
    }


    /**
     * Sets the serialNumber value for this SiebelAssetManagementInformation.
     * 
     * @param serialNumber
     */
    public void setSerialNumber(java.lang.String serialNumber) {
        this.serialNumber = serialNumber;
    }


    /**
     * Gets the assetTag value for this SiebelAssetManagementInformation.
     * 
     * @return assetTag
     */
    public java.lang.String getAssetTag() {
        return assetTag;
    }


    /**
     * Sets the assetTag value for this SiebelAssetManagementInformation.
     * 
     * @param assetTag
     */
    public void setAssetTag(java.lang.String assetTag) {
        this.assetTag = assetTag;
    }


    /**
     * Gets the deviceTag value for this SiebelAssetManagementInformation.
     * 
     * @return deviceTag
     */
    public java.lang.String getDeviceTag() {
        return deviceTag;
    }


    /**
     * Sets the deviceTag value for this SiebelAssetManagementInformation.
     * 
     * @param deviceTag
     */
    public void setDeviceTag(java.lang.String deviceTag) {
        this.deviceTag = deviceTag;
    }


    /**
     * Gets the productModel value for this SiebelAssetManagementInformation.
     * 
     * @return productModel
     */
    public java.lang.String getProductModel() {
        return productModel;
    }


    /**
     * Sets the productModel value for this SiebelAssetManagementInformation.
     * 
     * @param productModel
     */
    public void setProductModel(java.lang.String productModel) {
        this.productModel = productModel;
    }


    /**
     * Gets the productNumber value for this SiebelAssetManagementInformation.
     * 
     * @return productNumber
     */
    public java.lang.String getProductNumber() {
        return productNumber;
    }


    /**
     * Sets the productNumber value for this SiebelAssetManagementInformation.
     * 
     * @param productNumber
     */
    public void setProductNumber(java.lang.String productNumber) {
        this.productNumber = productNumber;
    }


    /**
     * Gets the productDescription value for this SiebelAssetManagementInformation.
     * 
     * @return productDescription
     */
    public java.lang.String getProductDescription() {
        return productDescription;
    }


    /**
     * Sets the productDescription value for this SiebelAssetManagementInformation.
     * 
     * @param productDescription
     */
    public void setProductDescription(java.lang.String productDescription) {
        this.productDescription = productDescription;
    }


    /**
     * Gets the productLine value for this SiebelAssetManagementInformation.
     * 
     * @return productLine
     */
    public java.lang.String getProductLine() {
        return productLine;
    }


    /**
     * Sets the productLine value for this SiebelAssetManagementInformation.
     * 
     * @param productLine
     */
    public void setProductLine(java.lang.String productLine) {
        this.productLine = productLine;
    }


    /**
     * Gets the machineTypeModel value for this SiebelAssetManagementInformation.
     * 
     * @return machineTypeModel
     */
    public java.lang.String getMachineTypeModel() {
        return machineTypeModel;
    }


    /**
     * Sets the machineTypeModel value for this SiebelAssetManagementInformation.
     * 
     * @param machineTypeModel
     */
    public void setMachineTypeModel(java.lang.String machineTypeModel) {
        this.machineTypeModel = machineTypeModel;
    }


    /**
     * Gets the assetType value for this SiebelAssetManagementInformation.
     * 
     * @return assetType
     */
    public java.lang.String getAssetType() {
        return assetType;
    }


    /**
     * Sets the assetType value for this SiebelAssetManagementInformation.
     * 
     * @param assetType
     */
    public void setAssetType(java.lang.String assetType) {
        this.assetType = assetType;
    }


    /**
     * Gets the assetCostCenter value for this SiebelAssetManagementInformation.
     * 
     * @return assetCostCenter
     */
    public java.lang.String getAssetCostCenter() {
        return assetCostCenter;
    }


    /**
     * Sets the assetCostCenter value for this SiebelAssetManagementInformation.
     * 
     * @param assetCostCenter
     */
    public void setAssetCostCenter(java.lang.String assetCostCenter) {
        this.assetCostCenter = assetCostCenter;
    }


    /**
     * Gets the assetDepartmentName value for this SiebelAssetManagementInformation.
     * 
     * @return assetDepartmentName
     */
    public java.lang.String getAssetDepartmentName() {
        return assetDepartmentName;
    }


    /**
     * Sets the assetDepartmentName value for this SiebelAssetManagementInformation.
     * 
     * @param assetDepartmentName
     */
    public void setAssetDepartmentName(java.lang.String assetDepartmentName) {
        this.assetDepartmentName = assetDepartmentName;
    }


    /**
     * Gets the assetDepartmentNumber value for this SiebelAssetManagementInformation.
     * 
     * @return assetDepartmentNumber
     */
    public java.lang.String getAssetDepartmentNumber() {
        return assetDepartmentNumber;
    }


    /**
     * Sets the assetDepartmentNumber value for this SiebelAssetManagementInformation.
     * 
     * @param assetDepartmentNumber
     */
    public void setAssetDepartmentNumber(java.lang.String assetDepartmentNumber) {
        this.assetDepartmentNumber = assetDepartmentNumber;
    }


    /**
     * Gets the IPAddress value for this SiebelAssetManagementInformation.
     * 
     * @return IPAddress
     */
    public java.lang.String getIPAddress() {
        return IPAddress;
    }


    /**
     * Sets the IPAddress value for this SiebelAssetManagementInformation.
     * 
     * @param IPAddress
     */
    public void setIPAddress(java.lang.String IPAddress) {
        this.IPAddress = IPAddress;
    }


    /**
     * Gets the IPSubMask value for this SiebelAssetManagementInformation.
     * 
     * @return IPSubMask
     */
    public java.lang.String getIPSubMask() {
        return IPSubMask;
    }


    /**
     * Sets the IPSubMask value for this SiebelAssetManagementInformation.
     * 
     * @param IPSubMask
     */
    public void setIPSubMask(java.lang.String IPSubMask) {
        this.IPSubMask = IPSubMask;
    }


    /**
     * Gets the IPGateway value for this SiebelAssetManagementInformation.
     * 
     * @return IPGateway
     */
    public java.lang.String getIPGateway() {
        return IPGateway;
    }


    /**
     * Sets the IPGateway value for this SiebelAssetManagementInformation.
     * 
     * @param IPGateway
     */
    public void setIPGateway(java.lang.String IPGateway) {
        this.IPGateway = IPGateway;
    }


    /**
     * Gets the IPV6 value for this SiebelAssetManagementInformation.
     * 
     * @return IPV6
     */
    public java.lang.String getIPV6() {
        return IPV6;
    }


    /**
     * Sets the IPV6 value for this SiebelAssetManagementInformation.
     * 
     * @param IPV6
     */
    public void setIPV6(java.lang.String IPV6) {
        this.IPV6 = IPV6;
    }


    /**
     * Gets the MACAddress value for this SiebelAssetManagementInformation.
     * 
     * @return MACAddress
     */
    public java.lang.String getMACAddress() {
        return MACAddress;
    }


    /**
     * Sets the MACAddress value for this SiebelAssetManagementInformation.
     * 
     * @param MACAddress
     */
    public void setMACAddress(java.lang.String MACAddress) {
        this.MACAddress = MACAddress;
    }


    /**
     * Gets the hostName value for this SiebelAssetManagementInformation.
     * 
     * @return hostName
     */
    public java.lang.String getHostName() {
        return hostName;
    }


    /**
     * Sets the hostName value for this SiebelAssetManagementInformation.
     * 
     * @param hostName
     */
    public void setHostName(java.lang.String hostName) {
        this.hostName = hostName;
    }


    /**
     * Gets the computerName value for this SiebelAssetManagementInformation.
     * 
     * @return computerName
     */
    public java.lang.String getComputerName() {
        return computerName;
    }


    /**
     * Sets the computerName value for this SiebelAssetManagementInformation.
     * 
     * @param computerName
     */
    public void setComputerName(java.lang.String computerName) {
        this.computerName = computerName;
    }


    /**
     * Gets the brand value for this SiebelAssetManagementInformation.
     * 
     * @return brand
     */
    public java.lang.String getBrand() {
        return brand;
    }


    /**
     * Sets the brand value for this SiebelAssetManagementInformation.
     * 
     * @param brand
     */
    public void setBrand(java.lang.String brand) {
        this.brand = brand;
    }


    /**
     * Gets the networkConnected value for this SiebelAssetManagementInformation.
     * 
     * @return networkConnected
     */
    public java.lang.String getNetworkConnected() {
        return networkConnected;
    }


    /**
     * Sets the networkConnected value for this SiebelAssetManagementInformation.
     * 
     * @param networkConnected
     */
    public void setNetworkConnected(java.lang.String networkConnected) {
        this.networkConnected = networkConnected;
    }


    /**
     * Gets the portNumber value for this SiebelAssetManagementInformation.
     * 
     * @return portNumber
     */
    public java.lang.String getPortNumber() {
        return portNumber;
    }


    /**
     * Sets the portNumber value for this SiebelAssetManagementInformation.
     * 
     * @param portNumber
     */
    public void setPortNumber(java.lang.String portNumber) {
        this.portNumber = portNumber;
    }


    /**
     * Gets the wiringClosetNumber value for this SiebelAssetManagementInformation.
     * 
     * @return wiringClosetNumber
     */
    public java.lang.String getWiringClosetNumber() {
        return wiringClosetNumber;
    }


    /**
     * Sets the wiringClosetNumber value for this SiebelAssetManagementInformation.
     * 
     * @param wiringClosetNumber
     */
    public void setWiringClosetNumber(java.lang.String wiringClosetNumber) {
        this.wiringClosetNumber = wiringClosetNumber;
    }


    /**
     * Gets the faxConnected value for this SiebelAssetManagementInformation.
     * 
     * @return faxConnected
     */
    public java.lang.String getFaxConnected() {
        return faxConnected;
    }


    /**
     * Sets the faxConnected value for this SiebelAssetManagementInformation.
     * 
     * @param faxConnected
     */
    public void setFaxConnected(java.lang.String faxConnected) {
        this.faxConnected = faxConnected;
    }


    /**
     * Gets the faxPortNumber value for this SiebelAssetManagementInformation.
     * 
     * @return faxPortNumber
     */
    public java.lang.String getFaxPortNumber() {
        return faxPortNumber;
    }


    /**
     * Sets the faxPortNumber value for this SiebelAssetManagementInformation.
     * 
     * @param faxPortNumber
     */
    public void setFaxPortNumber(java.lang.String faxPortNumber) {
        this.faxPortNumber = faxPortNumber;
    }


    /**
     * Gets the operatingSystem value for this SiebelAssetManagementInformation.
     * 
     * @return operatingSystem
     */
    public java.lang.String getOperatingSystem() {
        return operatingSystem;
    }


    /**
     * Sets the operatingSystem value for this SiebelAssetManagementInformation.
     * 
     * @param operatingSystem
     */
    public void setOperatingSystem(java.lang.String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }


    /**
     * Gets the operatingSystemVersion value for this SiebelAssetManagementInformation.
     * 
     * @return operatingSystemVersion
     */
    public java.lang.String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }


    /**
     * Sets the operatingSystemVersion value for this SiebelAssetManagementInformation.
     * 
     * @param operatingSystemVersion
     */
    public void setOperatingSystemVersion(java.lang.String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }


    /**
     * Gets the firmware value for this SiebelAssetManagementInformation.
     * 
     * @return firmware
     */
    public java.lang.String getFirmware() {
        return firmware;
    }


    /**
     * Sets the firmware value for this SiebelAssetManagementInformation.
     * 
     * @param firmware
     */
    public void setFirmware(java.lang.String firmware) {
        this.firmware = firmware;
    }


    /**
     * Gets the workingCondition value for this SiebelAssetManagementInformation.
     * 
     * @return workingCondition
     */
    public java.lang.String getWorkingCondition() {
        return workingCondition;
    }


    /**
     * Sets the workingCondition value for this SiebelAssetManagementInformation.
     * 
     * @param workingCondition
     */
    public void setWorkingCondition(java.lang.String workingCondition) {
        this.workingCondition = workingCondition;
    }


    /**
     * Gets the networkTopology value for this SiebelAssetManagementInformation.
     * 
     * @return networkTopology
     */
    public java.lang.String getNetworkTopology() {
        return networkTopology;
    }


    /**
     * Sets the networkTopology value for this SiebelAssetManagementInformation.
     * 
     * @param networkTopology
     */
    public void setNetworkTopology(java.lang.String networkTopology) {
        this.networkTopology = networkTopology;
    }


    /**
     * Gets the printServer value for this SiebelAssetManagementInformation.
     * 
     * @return printServer
     */
    public java.lang.String getPrintServer() {
        return printServer;
    }


    /**
     * Sets the printServer value for this SiebelAssetManagementInformation.
     * 
     * @param printServer
     */
    public void setPrintServer(java.lang.String printServer) {
        this.printServer = printServer;
    }


    /**
     * Gets the specialUsage value for this SiebelAssetManagementInformation.
     * 
     * @return specialUsage
     */
    public java.lang.String getSpecialUsage() {
        return specialUsage;
    }


    /**
     * Sets the specialUsage value for this SiebelAssetManagementInformation.
     * 
     * @param specialUsage
     */
    public void setSpecialUsage(java.lang.String specialUsage) {
        this.specialUsage = specialUsage;
    }


    /**
     * Gets the relatedCustomerHierarchyLevel value for this SiebelAssetManagementInformation.
     * 
     * @return relatedCustomerHierarchyLevel
     */
    public java.lang.String getRelatedCustomerHierarchyLevel() {
        return relatedCustomerHierarchyLevel;
    }


    /**
     * Sets the relatedCustomerHierarchyLevel value for this SiebelAssetManagementInformation.
     * 
     * @param relatedCustomerHierarchyLevel
     */
    public void setRelatedCustomerHierarchyLevel(java.lang.String relatedCustomerHierarchyLevel) {
        this.relatedCustomerHierarchyLevel = relatedCustomerHierarchyLevel;
    }


    /**
     * Gets the installDate value for this SiebelAssetManagementInformation.
     * 
     * @return installDate
     */
    public java.lang.String getInstallDate() {
        return installDate;
    }


    /**
     * Sets the installDate value for this SiebelAssetManagementInformation.
     * 
     * @param installDate
     */
    public void setInstallDate(java.lang.String installDate) {
        this.installDate = installDate;
    }


    /**
     * Gets the customerAssetData1 value for this SiebelAssetManagementInformation.
     * 
     * @return customerAssetData1
     */
    public java.lang.String getCustomerAssetData1() {
        return customerAssetData1;
    }


    /**
     * Sets the customerAssetData1 value for this SiebelAssetManagementInformation.
     * 
     * @param customerAssetData1
     */
    public void setCustomerAssetData1(java.lang.String customerAssetData1) {
        this.customerAssetData1 = customerAssetData1;
    }


    /**
     * Gets the customerAssetData2 value for this SiebelAssetManagementInformation.
     * 
     * @return customerAssetData2
     */
    public java.lang.String getCustomerAssetData2() {
        return customerAssetData2;
    }


    /**
     * Sets the customerAssetData2 value for this SiebelAssetManagementInformation.
     * 
     * @param customerAssetData2
     */
    public void setCustomerAssetData2(java.lang.String customerAssetData2) {
        this.customerAssetData2 = customerAssetData2;
    }


    /**
     * Gets the customerAssetData3 value for this SiebelAssetManagementInformation.
     * 
     * @return customerAssetData3
     */
    public java.lang.String getCustomerAssetData3() {
        return customerAssetData3;
    }


    /**
     * Sets the customerAssetData3 value for this SiebelAssetManagementInformation.
     * 
     * @param customerAssetData3
     */
    public void setCustomerAssetData3(java.lang.String customerAssetData3) {
        this.customerAssetData3 = customerAssetData3;
    }


    /**
     * Gets the installedAtAddress value for this SiebelAssetManagementInformation.
     * 
     * @return installedAtAddress
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress getInstalledAtAddress() {
        return installedAtAddress;
    }


    /**
     * Sets the installedAtAddress value for this SiebelAssetManagementInformation.
     * 
     * @param installedAtAddress
     */
    public void setInstalledAtAddress(com.lexmark.SiebelShared.createServiceRequest.client.SiebelAddress installedAtAddress) {
        this.installedAtAddress = installedAtAddress;
    }


    /**
     * Gets the meterReadInformation value for this SiebelAssetManagementInformation.
     * 
     * @return meterReadInformation
     */
    public com.lexmark.SiebelShared.createServiceRequest.client.SiebelMeterReadInformation[] getMeterReadInformation() {
        return meterReadInformation;
    }


    /**
     * Sets the meterReadInformation value for this SiebelAssetManagementInformation.
     * 
     * @param meterReadInformation
     */
    public void setMeterReadInformation(com.lexmark.SiebelShared.createServiceRequest.client.SiebelMeterReadInformation[] meterReadInformation) {
        this.meterReadInformation = meterReadInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SiebelAssetManagementInformation)) return false;
        SiebelAssetManagementInformation other = (SiebelAssetManagementInformation) obj;
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
            ((this.deviceTag==null && other.getDeviceTag()==null) || 
             (this.deviceTag!=null &&
              this.deviceTag.equals(other.getDeviceTag()))) &&
            ((this.productModel==null && other.getProductModel()==null) || 
             (this.productModel!=null &&
              this.productModel.equals(other.getProductModel()))) &&
            ((this.productNumber==null && other.getProductNumber()==null) || 
             (this.productNumber!=null &&
              this.productNumber.equals(other.getProductNumber()))) &&
            ((this.productDescription==null && other.getProductDescription()==null) || 
             (this.productDescription!=null &&
              this.productDescription.equals(other.getProductDescription()))) &&
            ((this.productLine==null && other.getProductLine()==null) || 
             (this.productLine!=null &&
              this.productLine.equals(other.getProductLine()))) &&
            ((this.machineTypeModel==null && other.getMachineTypeModel()==null) || 
             (this.machineTypeModel!=null &&
              this.machineTypeModel.equals(other.getMachineTypeModel()))) &&
            ((this.assetType==null && other.getAssetType()==null) || 
             (this.assetType!=null &&
              this.assetType.equals(other.getAssetType()))) &&
            ((this.assetCostCenter==null && other.getAssetCostCenter()==null) || 
             (this.assetCostCenter!=null &&
              this.assetCostCenter.equals(other.getAssetCostCenter()))) &&
            ((this.assetDepartmentName==null && other.getAssetDepartmentName()==null) || 
             (this.assetDepartmentName!=null &&
              this.assetDepartmentName.equals(other.getAssetDepartmentName()))) &&
            ((this.assetDepartmentNumber==null && other.getAssetDepartmentNumber()==null) || 
             (this.assetDepartmentNumber!=null &&
              this.assetDepartmentNumber.equals(other.getAssetDepartmentNumber()))) &&
            ((this.IPAddress==null && other.getIPAddress()==null) || 
             (this.IPAddress!=null &&
              this.IPAddress.equals(other.getIPAddress()))) &&
            ((this.IPSubMask==null && other.getIPSubMask()==null) || 
             (this.IPSubMask!=null &&
              this.IPSubMask.equals(other.getIPSubMask()))) &&
            ((this.IPGateway==null && other.getIPGateway()==null) || 
             (this.IPGateway!=null &&
              this.IPGateway.equals(other.getIPGateway()))) &&
            ((this.IPV6==null && other.getIPV6()==null) || 
             (this.IPV6!=null &&
              this.IPV6.equals(other.getIPV6()))) &&
            ((this.MACAddress==null && other.getMACAddress()==null) || 
             (this.MACAddress!=null &&
              this.MACAddress.equals(other.getMACAddress()))) &&
            ((this.hostName==null && other.getHostName()==null) || 
             (this.hostName!=null &&
              this.hostName.equals(other.getHostName()))) &&
            ((this.computerName==null && other.getComputerName()==null) || 
             (this.computerName!=null &&
              this.computerName.equals(other.getComputerName()))) &&
            ((this.brand==null && other.getBrand()==null) || 
             (this.brand!=null &&
              this.brand.equals(other.getBrand()))) &&
            ((this.networkConnected==null && other.getNetworkConnected()==null) || 
             (this.networkConnected!=null &&
              this.networkConnected.equals(other.getNetworkConnected()))) &&
            ((this.portNumber==null && other.getPortNumber()==null) || 
             (this.portNumber!=null &&
              this.portNumber.equals(other.getPortNumber()))) &&
            ((this.wiringClosetNumber==null && other.getWiringClosetNumber()==null) || 
             (this.wiringClosetNumber!=null &&
              this.wiringClosetNumber.equals(other.getWiringClosetNumber()))) &&
            ((this.faxConnected==null && other.getFaxConnected()==null) || 
             (this.faxConnected!=null &&
              this.faxConnected.equals(other.getFaxConnected()))) &&
            ((this.faxPortNumber==null && other.getFaxPortNumber()==null) || 
             (this.faxPortNumber!=null &&
              this.faxPortNumber.equals(other.getFaxPortNumber()))) &&
            ((this.operatingSystem==null && other.getOperatingSystem()==null) || 
             (this.operatingSystem!=null &&
              this.operatingSystem.equals(other.getOperatingSystem()))) &&
            ((this.operatingSystemVersion==null && other.getOperatingSystemVersion()==null) || 
             (this.operatingSystemVersion!=null &&
              this.operatingSystemVersion.equals(other.getOperatingSystemVersion()))) &&
            ((this.firmware==null && other.getFirmware()==null) || 
             (this.firmware!=null &&
              this.firmware.equals(other.getFirmware()))) &&
            ((this.workingCondition==null && other.getWorkingCondition()==null) || 
             (this.workingCondition!=null &&
              this.workingCondition.equals(other.getWorkingCondition()))) &&
            ((this.networkTopology==null && other.getNetworkTopology()==null) || 
             (this.networkTopology!=null &&
              this.networkTopology.equals(other.getNetworkTopology()))) &&
            ((this.printServer==null && other.getPrintServer()==null) || 
             (this.printServer!=null &&
              this.printServer.equals(other.getPrintServer()))) &&
            ((this.specialUsage==null && other.getSpecialUsage()==null) || 
             (this.specialUsage!=null &&
              this.specialUsage.equals(other.getSpecialUsage()))) &&
            ((this.relatedCustomerHierarchyLevel==null && other.getRelatedCustomerHierarchyLevel()==null) || 
             (this.relatedCustomerHierarchyLevel!=null &&
              this.relatedCustomerHierarchyLevel.equals(other.getRelatedCustomerHierarchyLevel()))) &&
            ((this.installDate==null && other.getInstallDate()==null) || 
             (this.installDate!=null &&
              this.installDate.equals(other.getInstallDate()))) &&
            ((this.customerAssetData1==null && other.getCustomerAssetData1()==null) || 
             (this.customerAssetData1!=null &&
              this.customerAssetData1.equals(other.getCustomerAssetData1()))) &&
            ((this.customerAssetData2==null && other.getCustomerAssetData2()==null) || 
             (this.customerAssetData2!=null &&
              this.customerAssetData2.equals(other.getCustomerAssetData2()))) &&
            ((this.customerAssetData3==null && other.getCustomerAssetData3()==null) || 
             (this.customerAssetData3!=null &&
              this.customerAssetData3.equals(other.getCustomerAssetData3()))) &&
            ((this.installedAtAddress==null && other.getInstalledAtAddress()==null) || 
             (this.installedAtAddress!=null &&
              this.installedAtAddress.equals(other.getInstalledAtAddress()))) &&
            ((this.meterReadInformation==null && other.getMeterReadInformation()==null) || 
             (this.meterReadInformation!=null &&
              java.util.Arrays.equals(this.meterReadInformation, other.getMeterReadInformation())));
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
        if (getDeviceTag() != null) {
            _hashCode += getDeviceTag().hashCode();
        }
        if (getProductModel() != null) {
            _hashCode += getProductModel().hashCode();
        }
        if (getProductNumber() != null) {
            _hashCode += getProductNumber().hashCode();
        }
        if (getProductDescription() != null) {
            _hashCode += getProductDescription().hashCode();
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
        if (getAssetCostCenter() != null) {
            _hashCode += getAssetCostCenter().hashCode();
        }
        if (getAssetDepartmentName() != null) {
            _hashCode += getAssetDepartmentName().hashCode();
        }
        if (getAssetDepartmentNumber() != null) {
            _hashCode += getAssetDepartmentNumber().hashCode();
        }
        if (getIPAddress() != null) {
            _hashCode += getIPAddress().hashCode();
        }
        if (getIPSubMask() != null) {
            _hashCode += getIPSubMask().hashCode();
        }
        if (getIPGateway() != null) {
            _hashCode += getIPGateway().hashCode();
        }
        if (getIPV6() != null) {
            _hashCode += getIPV6().hashCode();
        }
        if (getMACAddress() != null) {
            _hashCode += getMACAddress().hashCode();
        }
        if (getHostName() != null) {
            _hashCode += getHostName().hashCode();
        }
        if (getComputerName() != null) {
            _hashCode += getComputerName().hashCode();
        }
        if (getBrand() != null) {
            _hashCode += getBrand().hashCode();
        }
        if (getNetworkConnected() != null) {
            _hashCode += getNetworkConnected().hashCode();
        }
        if (getPortNumber() != null) {
            _hashCode += getPortNumber().hashCode();
        }
        if (getWiringClosetNumber() != null) {
            _hashCode += getWiringClosetNumber().hashCode();
        }
        if (getFaxConnected() != null) {
            _hashCode += getFaxConnected().hashCode();
        }
        if (getFaxPortNumber() != null) {
            _hashCode += getFaxPortNumber().hashCode();
        }
        if (getOperatingSystem() != null) {
            _hashCode += getOperatingSystem().hashCode();
        }
        if (getOperatingSystemVersion() != null) {
            _hashCode += getOperatingSystemVersion().hashCode();
        }
        if (getFirmware() != null) {
            _hashCode += getFirmware().hashCode();
        }
        if (getWorkingCondition() != null) {
            _hashCode += getWorkingCondition().hashCode();
        }
        if (getNetworkTopology() != null) {
            _hashCode += getNetworkTopology().hashCode();
        }
        if (getPrintServer() != null) {
            _hashCode += getPrintServer().hashCode();
        }
        if (getSpecialUsage() != null) {
            _hashCode += getSpecialUsage().hashCode();
        }
        if (getRelatedCustomerHierarchyLevel() != null) {
            _hashCode += getRelatedCustomerHierarchyLevel().hashCode();
        }
        if (getInstallDate() != null) {
            _hashCode += getInstallDate().hashCode();
        }
        if (getCustomerAssetData1() != null) {
            _hashCode += getCustomerAssetData1().hashCode();
        }
        if (getCustomerAssetData2() != null) {
            _hashCode += getCustomerAssetData2().hashCode();
        }
        if (getCustomerAssetData3() != null) {
            _hashCode += getCustomerAssetData3().hashCode();
        }
        if (getInstalledAtAddress() != null) {
            _hashCode += getInstalledAtAddress().hashCode();
        }
        if (getMeterReadInformation() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMeterReadInformation());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMeterReadInformation(), i);
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
        new org.apache.axis.description.TypeDesc(SiebelAssetManagementInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAssetManagementInformation"));
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
        elemField.setFieldName("deviceTag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DeviceTag"));
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
        elemField.setFieldName("productDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProductDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("assetCostCenter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetCostCenter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetDepartmentName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetDepartmentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetDepartmentNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetDepartmentNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPSubMask");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPSubMask"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPGateway");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPGateway"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPV6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IPV6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MACAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MACAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hostName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HostName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("computerName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ComputerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("brand");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Brand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkConnected");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NetworkConnected"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PortNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wiringClosetNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WiringClosetNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxConnected");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FaxConnected"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxPortNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FaxPortNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operatingSystem");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OperatingSystem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operatingSystemVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OperatingSystemVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firmware");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Firmware"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workingCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WorkingCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkTopology");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NetworkTopology"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printServer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrintServer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("specialUsage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SpecialUsage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedCustomerHierarchyLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelatedCustomerHierarchyLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("installDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstallDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerAssetData1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerAssetData1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerAssetData2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerAssetData2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerAssetData3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerAssetData3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("installedAtAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InstalledAtAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("meterReadInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MeterReadInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelMeterReadInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfSiebelMeterReadInformationItem"));
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
