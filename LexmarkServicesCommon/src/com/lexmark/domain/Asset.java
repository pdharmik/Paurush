package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Asset implements Serializable {
	private static final long serialVersionUID = 6914065634394245809L;
	
	private String assetId;
	private String serialNumber;
	private String assetTag;
	private String modelNumber;
	private String ipAddress;
	private GenericAddress installAddress;
	private GenericAddress consumableAddress;
	private GenericAddress pickupAddress;
	private GenericAddress moveToAddress;		
	private GenericAddress moveFromAddress;    			//added for MPS CR 11055

	private boolean userFavoriteFlag;
	private Entitlement entitlement;
	private String assetType;
	private String supportUrl;
	private String controlPanelURL;
	private String downloadsUrl;
	private String hostName;
	private String deviceTag;
	private AccountContact assetContact;
	private AccountContact consumableContact;
	private boolean notMyPrinter;
	private String productTLI;
	private String productImageURL;
	private String deviceName;
	private GenericAddress physicalLocationAddress;
	private String productLine;
	private Date meterReadDate;
	private String lastPageCount;
	private String newPageCount;
	private String lastColorPageCount;
	private String newColorPageCount;
	private Boolean colorCapableFlag;
	private Account account;
	private Date lastPageReadDate;
	private Date lastColorPageReadDate;
	private String devicePhase;
	private Boolean colorRequiredFlag;
	private String macAddress;
	private String machineTypeModel;
	private String problemDescription;
	private Account partnerAccount;
	private Boolean networkConnectedFlag;
	private Date installDate;
    private List<Part> partList;
    private ServiceRequest serviceRequest;
	/*****************Added/Modified for Manage Asset Information by offshor team ****************/
/*****MPS Offshore team comments*********/
	/****This field is not required for Change Mgmt, need to confirm whether productNo and
	 * ModelNumber are same *******/  
	private String productNo; // ModelNumber can be used
	private String chlNodeId;
	private String chlNodeValue; 
	
	private String assetCostCenter;
	private String defaultSpecialInstruction;
	private String poNumber;
	private String description;
	private String notes;
	private Boolean addressFlag;//For Brazil users this flag will return true.
	private List<PageCounts> pageCounts;
	private List<PageCounts> deInstAssetPageCounts;//Added for critical Path CR
    private Boolean consumableAssetFlag;
    private boolean installationOnlyFlag;
    private String building;
    private String floor;
    private String office;
    
    //Added for JAN release
    private String partsToBeInstalled;
    
    private List<Part> lastOrderPartList;
	
  
    
    //Added for Partner portal HardwareDebrief
    private Account customerAccount;
	private String activityNumber;
	private GenericAddress newAddress;
	private String department;
	private String subnet;
	private String gateway;
	private String portNumber;
	private boolean faxConnected;
	private String faxPortNumber;
	private String printerWorkingCondition;
	private String wiringClosestNetworkPoint;
	private String assetHierarchyLevel;
	private String operatingSystem;
	private String operatingSystemVersion;
	private String firmware;
	private String networkTopology;
	private String topBill;
	private String specialUsage;
	private String assetLifeCycle;
	private String ipV6;
	private String devTypeModelNumber;
	private String repairedAssetModelNumber;
	
	//Added for wave4
	private String deviceType;
	private String statusDetail;
	//End
	//Ends Added for Partner portal HardwareDebrief
	//Added for WAVE 4 Hardware Debrief template
	private Date storeDate;
	private String monoLPC;
	private String colorLPC;
	private String faxCount;
	private String scanCount;
	private String computerName;
	private String departmentId;
	private String activitynumber;
	private String storeFrontName;
	//Ends 
	//Added for device contact info in Manage Asset
	private String contractNumber;
	
	private List<AccountContact> deviceContact;    
	private String agreementId;
	
	private String customerReportingName;
	private String moveToAddressGrouped;
	
	private String productModelNumber;
	private String faxConnectedValue;//Added for hardware Debrief
	
	private String assetField1;//added for 11599 MPS 2.1
	private String assetField2;//added for 11599 MPS 2.1
	private String assetField3;//added for 11599 MPS 2.1
	
	private String massUploadpageCountType;    
	private String massUploadPageCount;		  
	private String descriptionLocalLang;
	private boolean lbsAddressFlag;
	private String lbsAddresFlagValue;
	
	private String deinstSerialNumber;
	private String deinstPartNumber;
	private String deinstModel;
	private String deinstBrand;
	private String deinstIpAddress;
	private String deinstHostName;
	private Date deinstRemovalDate;
	private String deinstComments;
	private String deinstAssetTag;
	private String deviceCondition;
	
	// added to check duplicate device
	private boolean duplicateDevice;
	private String mki;
	private String serviceProvider;
	private String displayWarning;
	
	
	private String placementId;
	private boolean placementMove;
	
	
	public String getDisplayWarning() {
		return displayWarning;
	}
	public void setDisplayWarning(String displayWarning) {
		this.displayWarning = displayWarning;
	}

	
	public String getMki() {
		return mki;
	}

	public void setMki(String mki) {
		this.mki = mki;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}


	public String getLbsAddresFlagValue() {
		return lbsAddresFlagValue;
	}

	public void setLbsAddresFlagValue(String lbsAddresFlagValue) {
		this.lbsAddresFlagValue = lbsAddresFlagValue;
	}

	public List<String> getTempSuppliesEntitlementType() {
			return tempSuppliesEntitlementType;
		}

		public void setTempSuppliesEntitlementType(
				List<String> tempSuppliesEntitlementType) {
			this.tempSuppliesEntitlementType = tempSuppliesEntitlementType;
		}

		public List<String> getTempServicesEntitlementType() {
			return tempServicesEntitlementType;
		}

		public void setTempServicesEntitlementType(
				List<String> tempServicesEntitlementType) {
			this.tempServicesEntitlementType = tempServicesEntitlementType;
		}

		private List<String> suppliesEntitlementType;
		private List<String> servicesEntitlementType;
		
		private List<String> tempSuppliesEntitlementType;
		private List<String> tempServicesEntitlementType;
		public String getTempSupplies() {
			return tempSupplies;
		}

		public void setTempSupplies(String tempSupplies) {
			this.tempSupplies = tempSupplies;
		}

		public String getTempServices() {
			return tempServices;
		}

		public void setTempServices(String tempServices) {
			this.tempServices = tempServices;
		}

		private String tempSupplies;
		private String tempServices;
		
		
		public List<String> getSuppliesEntitlementType() {
			return suppliesEntitlementType;
		}

		public void setSuppliesEntitlementType(List<String> suppliesEntitlementType) {
			this.suppliesEntitlementType = suppliesEntitlementType;
		}

		public List<String> getServicesEntitlementType() {
			return servicesEntitlementType;
		}

		public void setServicesEntitlementType(List<String> servicesEntitlementType) {
			this.servicesEntitlementType = servicesEntitlementType;
		}

		private GenericAddress massUploadInstallAddress;
	
	public GenericAddress getMassUploadInstallAddress() {
		return massUploadInstallAddress;
	}

	public void setMassUploadInstallAddress(GenericAddress massUploadInstallAddress) {
		this.massUploadInstallAddress = massUploadInstallAddress;
	}

	public String getAssetCostCenter() {
		return assetCostCenter;
	}

	public void setAssetCostCenter(String assetCostCenter) {
		this.assetCostCenter = assetCostCenter;
	}
	public String getChlNodeId() {
		return chlNodeId;
	}

	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}

	public String getChlNodeValue() {
		return chlNodeValue;
	}

	public void setChlNodeValue(String chlNodeValue) {
		this.chlNodeValue = chlNodeValue;
	}

    public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	/***********Added code by MPS offshore team ends here *************/
	
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getDevicePhase() {
		return devicePhase;
	}
	public void setDevicePhase(String devicePhase) {
		this.devicePhase = devicePhase;
	}
	public Date getMeterReadDate() {
		return meterReadDate;
	}
	public void setMeterReadDate(Date meterReadDate) {
		this.meterReadDate = meterReadDate;
	}
	public String getLastPageCount() {
		return lastPageCount;
	}
	public void setLastPageCount(String lastPageCount) {
		this.lastPageCount = lastPageCount;
	}
	public String getNewPageCount() {
		return newPageCount;
	}
	public void setNewPageCount(String newPageCount) {
		this.newPageCount = newPageCount;
	}
	public String getLastColorPageCount() {
		return lastColorPageCount;
	}
	public void setLastColorPageCount(String lastColorPageCount) {
		this.lastColorPageCount = lastColorPageCount;
	}
	public String getNewColorPageCount() {
		return newColorPageCount;
	}
	public void setNewColorPageCount(String newColorPageCount) {
		this.newColorPageCount = newColorPageCount;
	}
	public Boolean getColorCapableFlag() {
		return colorCapableFlag;
	}
	public void setColorCapableFlag(Boolean colorCapableFlag) {
		this.colorCapableFlag = colorCapableFlag;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Date getLastPageReadDate() {
		return lastPageReadDate;
	}
	public void setLastPageReadDate(Date lastPageReadDate) {
		this.lastPageReadDate = lastPageReadDate;
	}
	public Date getLastColorPageReadDate() {
		return lastColorPageReadDate;
	}
	public void setLastColorPageReadDate(Date lastColorPageReadDate) {
		this.lastColorPageReadDate = lastColorPageReadDate;
	}
	
	
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getAssetTag() {
		return assetTag;
	}
	public void setAssetTag(String assetTag) {
		this.assetTag = assetTag;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public GenericAddress getInstallAddress() {
		return installAddress;
	}
	public void setInstallAddress(GenericAddress installAddress) {
		this.installAddress = installAddress;
	}
	public GenericAddress getPickupAddress() {
		return pickupAddress;
	}
	public void setPickupAddress(GenericAddress pickupAddress) {
		this.pickupAddress = pickupAddress;
	}
	public GenericAddress getMoveToAddress() {
		return moveToAddress;
	}
	public void setMoveToAddress(GenericAddress moveToAddress) {
		this.moveToAddress = moveToAddress;
	}
    public boolean getUserFavoriteFlag() {
		return userFavoriteFlag;
	}
	public void setUserFavoriteFlag(boolean userFavoriteFlag) {
		this.userFavoriteFlag = userFavoriteFlag;
	}
	public Entitlement getEntitlement() {
		return entitlement;
	}
	public void setEntitlement(Entitlement entitlement) {
		this.entitlement = entitlement;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getControlPanelURL() {
		return controlPanelURL;
	}
	public void setControlPanelURL(String controlPanelURL) {
		this.controlPanelURL = controlPanelURL;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getDeviceTag() {
		return deviceTag;
	}
	public void setDeviceTag(String deviceTag) {
		this.deviceTag = deviceTag;
	}
	public AccountContact getAssetContact() {
		return assetContact;
	}
	public void setAssetContact(AccountContact assetContact) {
		this.assetContact = assetContact;
	}
	public GenericAddress getConsumableAddress() {
        return consumableAddress;
    }

    public void setConsumableAddress(GenericAddress consumableAddress) {
        this.consumableAddress = consumableAddress;
    }

	public boolean isNotMyPrinter() {
		return notMyPrinter;
	}
	public void setNotMyPrinter(boolean notMyPrinter) {
		this.notMyPrinter = notMyPrinter;
	}
	public String getProductTLI() {
		return productTLI;
	}
	public void setProductTLI(String productTLI) {
		this.productTLI = productTLI;
	}
	public String getProductImageURL() {
		return productImageURL;
	}
	public void setProductImageURL(String productImageURL) {
		this.productImageURL = productImageURL;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSupportUrl() {
		return supportUrl;
	}
	public void setSupportUrl(String supportUrl) {
		this.supportUrl = supportUrl;
	}
	public String getDownloadsUrl() {
		return downloadsUrl;
	}
	public void setDownloadsUrl(String downloadsUrl) {
		this.downloadsUrl = downloadsUrl;
	}
	public void setColorRequiredFlag(Boolean colorRequiredFlag) {
		this.colorRequiredFlag = colorRequiredFlag;
	}
	public Boolean getColorRequiredFlag() {
		return colorRequiredFlag;
	}
	public void setMachineTypeModel(String machineTypeModel) {
		this.machineTypeModel = machineTypeModel;
	}
	public String getMachineTypeModel() {
		return machineTypeModel;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public Account getPartnerAccount() {
		return partnerAccount;
	}
	public void setPartnerAccount(Account partnerAccount) {
		this.partnerAccount = partnerAccount;
	}
	public void setNetworkConnectedFlag(Boolean networkConnectedFlag) {
		this.networkConnectedFlag = networkConnectedFlag;
	}
	public Boolean getNetworkConnectedFlag() {
		return networkConnectedFlag;
	}
	public void setConsumableContact(AccountContact consumableContact) {
		this.consumableContact = consumableContact;
	}

	public AccountContact getConsumableContact() {
		return consumableContact;
	}

	public void setPartList(List<Part> partList) {
		this.partList = partList;
	}

	public List<Part> getPartList() {
		return partList;
	}

	public GenericAddress getPhysicalLocationAddress() {
		return physicalLocationAddress;
	}

	public void setPhysicalLocationAddress(GenericAddress physicalLocationAddress) {
		this.physicalLocationAddress = physicalLocationAddress;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public String getDefaultSpecialInstruction() {
		return defaultSpecialInstruction;
	}

	public void setDefaultSpecialInstruction(String defaultSpecialInstruction) {
		this.defaultSpecialInstruction = defaultSpecialInstruction;
	}
	

    public String getPoNumber() {
        return poNumber;
    }
    

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean isAddressFlag() {
        return addressFlag;
    }

    public void setAddressFlag(Boolean addressFlag) {
        this.addressFlag = addressFlag;
    }

	public List<PageCounts> getPageCounts() {
		return pageCounts;
	}

	public void setPageCounts(List<PageCounts> pageCounts) {
		this.pageCounts = pageCounts;
	}
	
    public Boolean isConsumableAssetFlag() {
        return consumableAssetFlag;
    }

    public void setConsumableAssetFlag(Boolean consumableAssetFlag) {
        this.consumableAssetFlag = consumableAssetFlag;
    }

    /**
     * Alias for {@link #isInstallationOnlyFlag()} 
     */
    public boolean getInstallationOnlyFlag() {
        return installationOnlyFlag;
    }
    
    public boolean isInstallationOnlyFlag() {
        return installationOnlyFlag;
    }
    
    public void setInstallationOnlyFlag(boolean installationOnlyFlag) {
        this.installationOnlyFlag = installationOnlyFlag;
    }

	public void setPartsToBeInstalled(String partsToBeInstalled) {
		this.partsToBeInstalled = partsToBeInstalled;
	}

	public String getPartsToBeInstalled() {
		return partsToBeInstalled;
	}

    public List<Part> getLastOrderPartList() {
        return lastOrderPartList;
    }

    public void setLastOrderPartList(List<Part> lastOrderPartList) {
        this.lastOrderPartList = lastOrderPartList;
    }

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
	}

	public String getActivityNumber() {
		return activityNumber;
	}

	public void setCustomerAccount(Account customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Account getCustomerAccount() {
		return customerAccount;
	}

	public void setNewAddress(GenericAddress newAddress) {
		this.newAddress = newAddress;
	}

	public GenericAddress getNewAddress() {
		return newAddress;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	public String getSubnet() {
		return subnet;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getGateway() {
		return gateway;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setFaxConnected(boolean faxConnected) {
		this.faxConnected = faxConnected;
	}

	public boolean isFaxConnected() {
		return faxConnected;
	}

	public void setFaxPortNumber(String faxPortNumber) {
		this.faxPortNumber = faxPortNumber;
	}

	public String getFaxPortNumber() {
		return faxPortNumber;
	}

	public void setPrinterWorkingCondition(String printerWorkingCondition) {
		this.printerWorkingCondition = printerWorkingCondition;
	}

	public String getPrinterWorkingCondition() {
		return printerWorkingCondition;
	}

	public void setWiringClosestNetworkPoint(String wiringClosestNetworkPoint) {
		this.wiringClosestNetworkPoint = wiringClosestNetworkPoint;
	}

	public String getWiringClosestNetworkPoint() {
		return wiringClosestNetworkPoint;
	}

	public void setAssetHierarchyLevel(String assetHierarchyLevel) {
		this.assetHierarchyLevel = assetHierarchyLevel;
	}

	public String getAssetHierarchyLevel() {
		return assetHierarchyLevel;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystemVersion(String operatingSystemVersion) {
		this.operatingSystemVersion = operatingSystemVersion;
	}

	public String getOperatingSystemVersion() {
		return operatingSystemVersion;
	}

	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}

	public String getFirmware() {
		return firmware;
	}

	public void setNetworkTopology(String networkTopology) {
		this.networkTopology = networkTopology;
	}

	public String getNetworkTopology() {
		return networkTopology;
	}

	public void setTopBill(String topBill) {
		this.topBill = topBill;
	}

	public String getTopBill() {
		return topBill;
	}

	public void setSpecialUsage(String specialUsage) {
		this.specialUsage = specialUsage;
	}

	public String getSpecialUsage() {
		return specialUsage;
	}

	public void setAssetLifeCycle(String assetLifeCycle) {
		this.assetLifeCycle = assetLifeCycle;
	}

	public String getAssetLifeCycle() {
		return assetLifeCycle;
	}

	public List<AccountContact> getDeviceContact() {
		return deviceContact;
	}

	public void setDeviceContact(List<AccountContact> deviceContact) {
		this.deviceContact = deviceContact;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}

	public String getIpV6() {
		return ipV6;
	}

	public void setIpV6(String ipV6) {
		this.ipV6 = ipV6;
	}

	public String getDevTypeModelNumber() {
		return devTypeModelNumber;
	}

	public void setDevTypeModelNumber(String devTypeModelNumber) {
		this.devTypeModelNumber = devTypeModelNumber;
	}

	public String getRepairedAssetModelNumber() {
		return repairedAssetModelNumber;
	}

	public void setRepairedAssetModelNumber(String repairedAssetModelNumber) {
		this.repairedAssetModelNumber = repairedAssetModelNumber;
	}

	public void setStoreDate(Date storeDate) {
		this.storeDate = storeDate;
	}

	public Date getStoreDate() {
		return storeDate;
	}

	public void setMonoLPC(String monoLPC) {
		this.monoLPC = monoLPC;
	}

	public String getMonoLPC() {
		return monoLPC;
	}

	public void setColorLPC(String colorLPC) {
		this.colorLPC = colorLPC;
	}

	public String getColorLPC() {
		return colorLPC;
	}

	public void setFaxCount(String faxCount) {
		this.faxCount = faxCount;
	}

	public String getFaxCount() {
		return faxCount;
	}

	public void setScanCount(String scanCount) {
		this.scanCount = scanCount;
	}

	public String getScanCount() {
		return scanCount;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public void setActivitynumber(String activitynumber) {
		this.activitynumber = activitynumber;
	}

	public String getActivitynumber() {
		return activitynumber;
	}

	public String getStoreFrontName() {
		return storeFrontName;
	}

	public void setStoreFrontName(String storeFrontName) {
		this.storeFrontName = storeFrontName;
	}

	public String getCustomerReportingName() {
		return customerReportingName;
	}

	public void setCustomerReportingName(String customerReportingName) {
		this.customerReportingName = customerReportingName;
	}

	/**
	 * @param moveToAddressGrouped the moveToAddressGrouped to set
	 */
	public void setMoveToAddressGrouped(String moveToAddressGrouped) {
		this.moveToAddressGrouped = moveToAddressGrouped;
	}

	/**
	 * @return the moveToAddressGrouped
	 */
	public String getMoveToAddressGrouped() {
		return moveToAddressGrouped;
	}

	public String getProductModelNumber() {
		return productModelNumber;
	}

	public void setProductModelNumber(String productModelNumber) {
		this.productModelNumber = productModelNumber;
	}

	public void setFaxConnectedValue(String faxConnectedValue) {
		this.faxConnectedValue = faxConnectedValue;
	}

	public String getFaxConnectedValue() {
		return faxConnectedValue;
	}

	public void setAssetField1(String assetField1) {
		this.assetField1 = assetField1;
	}

	public String getAssetField1() {
		return assetField1;
	}

	public void setAssetField2(String assetField2) {
		this.assetField2 = assetField2;
	}

	public String getAssetField2() {
		return assetField2;
	}

	public void setAssetField3(String assetField3) {
		this.assetField3 = assetField3;
	}

	public String getAssetField3() {
		return assetField3;
	}

	public GenericAddress getMoveFromAddress() {
		return moveFromAddress;
	}

	public void setMoveFromAddress(GenericAddress moveFromAddress) {
		this.moveFromAddress = moveFromAddress;
	}
	public String getDescriptionLocalLang() {
		return descriptionLocalLang;
	}

	public void setDescriptionLocalLang(String descriptionLocalLang) {
		this.descriptionLocalLang = descriptionLocalLang;
	}

	public boolean isLbsAddressFlag() {
		return lbsAddressFlag;
	}

	public void setLbsAddressFlag(boolean lbsAddressFlag) {
		this.lbsAddressFlag = lbsAddressFlag;
	}

	/**
	 * @return the duplicateDevice
	 */
	public boolean getDuplicateDevice() {
		return duplicateDevice;
	}

	/**
	 * @param duplicateDevice the duplicateDevice to set
	 */
	public void setDuplicateDevice(boolean duplicateDevice) {
		this.duplicateDevice = duplicateDevice;
	}

	public String getMassUploadpageCountType() {
		return massUploadpageCountType;
	}

	public void setMassUploadpageCountType(String massUploadpageCountType) {
		this.massUploadpageCountType = massUploadpageCountType;
	}

	public String getMassUploadPageCount() {
		return massUploadPageCount;
	}

	public void setMassUploadPageCount(String massUploadPageCount) {
		this.massUploadPageCount = massUploadPageCount;
	}

	public String getDeinstSerialNumber() {
		return deinstSerialNumber;
	}

	public void setDeinstSerialNumber(String deinstSerialNumber) {
		this.deinstSerialNumber = deinstSerialNumber;
	}

	public String getDeinstPartNumber() {
		return deinstPartNumber;
	}

	public void setDeinstPartNumber(String deinstPartNumber) {
		this.deinstPartNumber = deinstPartNumber;
	}

	public String getDeinstModel() {
		return deinstModel;
	}

	public void setDeinstModel(String deinstModel) {
		this.deinstModel = deinstModel;
	}

	public String getDeinstBrand() {
		return deinstBrand;
	}

	public void setDeinstBrand(String deinstBrand) {
		this.deinstBrand = deinstBrand;
	}

	public String getDeinstIpAddress() {
		return deinstIpAddress;
	}

	public void setDeinstIpAddress(String deinstIpAddress) {
		this.deinstIpAddress = deinstIpAddress;
	}

	public String getDeinstHostName() {
		return deinstHostName;
	}

	public void setDeinstHostName(String deinstHostName) {
		this.deinstHostName = deinstHostName;
	}

	public Date getDeinstRemovalDate() {
		return deinstRemovalDate;
	}

	public void setDeinstRemovalDate(Date deinstRemovalDate) {
		this.deinstRemovalDate = deinstRemovalDate;
	}

	public String getDeinstComments() {
		return deinstComments;
	}

	public void setDeinstComments(String deinstComments) {
		this.deinstComments = deinstComments;
	}

	public String getDeinstAssetTag() {
		return deinstAssetTag;
	}

	public void setDeinstAssetTag(String deinstAssetTag) {
		this.deinstAssetTag = deinstAssetTag;
	}

	public Boolean getAddressFlag() {
		return addressFlag;
	}

	public Boolean getConsumableAssetFlag() {
		return consumableAssetFlag;
	}

	public String getDeviceCondition() {
		return deviceCondition;
	}

	public void setDeviceCondition(String deviceCondition) {
		this.deviceCondition = deviceCondition;
	}

	public void setDeInstAssetPageCounts(List<PageCounts> deInstAssetPageCounts) {
		this.deInstAssetPageCounts = deInstAssetPageCounts;
	}

	public List<PageCounts> getDeInstAssetPageCounts() {
		return deInstAssetPageCounts;
	}
	public String getPlacementId() {
		return placementId;
	}
	public void setPlacementId(String placementId) {
		this.placementId = placementId;
	}
	/**
	 * @return the placementMove
	 */
	public boolean isPlacementMove() {
		return placementMove;
	}
	/**
	 * @param placementMove the placementMove to set
	 */
	public void setPlacementMove(boolean placementMove) {
		this.placementMove = placementMove;
	}


}
