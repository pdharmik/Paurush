package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * mapping file: "do-assetbase-mapping.xml"
 */
public class AssetBase extends AccountBasedDo implements Serializable {
	private static final long serialVersionUID = 1417674785149968704L;

	private String assetNumber;
	private String operationalStatus;
	private String contactId;

	// result data to populate
	private String assetId;
	private String serialNumber;
	private String assetTag;
	private String machineTypeModel;
	private String ipAddress;
	private String hostName;
	private String deviceTagCustomer;
	private String productId;
	private String productTLI;
	private String productLine;
	private String devicePhase;
	private String macAddress;
	private Date installDate;
	private String physicalLocation1;
	private String physicalLocation2;
	private String physicalLocation3;
	private String zone;
	private String zoneId;
	private String buildingId;
	
	private String addressName;
	private String address1;
	private String address2;
	private String address3;
	private String addressId;
	private String installCity;
	private String installState;
	private String installProvince;
	private String installPostalCode;
	private String installCountry;
	private String installAddressId;
	private String productName;
	private String campusName;
	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getCampusId() {
		return campusId;
	}

	public void setCampusId(String campusId) {
		this.campusId = campusId;
	}

	private String campusId;
    private String county;
	private String district;
	private String officeNumber;
	private String assetCostCenter;
	private String descriptionLocalLang;
	private String mdmModel;
    private String customerReportingName;
    private String lbsAddressFlag;
    
	public String getCustomerReportingName() {
		return customerReportingName;
	}

	public void setCustomerReportingName(String customerReportingName) {
		this.customerReportingName = customerReportingName;
	}

	public String getDescriptionLocalLang() {
		return descriptionLocalLang;
	}

	public void setDescriptionLocalLang(String descriptionLocalLang) {
		this.descriptionLocalLang = descriptionLocalLang;
	}

	public String getMdmModel() {
		return mdmModel;
	}

	public void setMdmModel(String mdmModel) {
		this.mdmModel = mdmModel;
	}
	    
    public String getAssetCostCenter() {
		return assetCostCenter;
	}

	public void setAssetCostCenter(String assetCostCenter) {
		this.assetCostCenter = assetCostCenter;
	}

	public String getDevicePhase() {
		return devicePhase;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getOperationalStatus() {
		return operationalStatus;
	}

	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}

	public void setDevicePhase(String devicePhase) {
		this.devicePhase = devicePhase;
	}

	public String getMachineTypeModel() {
		return machineTypeModel;
	}

	public void setMachineTypeModel(String machineTypeModel) {
		this.machineTypeModel = machineTypeModel;
	}

	public String getProductTLI() {
		return productTLI;
	}

	public void setProductTLI(String productTLI) {
		this.productTLI = productTLI;
	}

	public String getInstallProvince() {
		return installProvince;
	}

	public void setInstallProvince(String installProvince) {
		this.installProvince = installProvince;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getInstallCountry() {
		return installCountry;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAssetNumber() {
		return assetNumber;
	}

	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setInstallCountry(String installCountry) {
		this.installCountry = installCountry;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getAssetTag() {
		return assetTag;
	}

	public void setAssetTag(String assetTag) {
		this.assetTag = assetTag;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public void setSerialNumber(String value) {
		this.serialNumber = value;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String value) {
		this.ipAddress = value;
	}

	public String getDeviceTagCustomer() {
		return deviceTagCustomer;
	}

	public void setDeviceTagCustomer(String value) {
		this.deviceTagCustomer = value;
	}

	public String getInstallCity() {
		return installCity;
	}

	public void setInstallCity(String value) {
		this.installCity = value;
	}

	public String getInstallState() {
		return installState;
	}

	public void setInstallState(String value) {
		this.installState = value;
	}

	public String getInstallPostalCode() {
		return installPostalCode;
	}

	public void setInstallPostalCode(String value) {
		this.installPostalCode = value;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getAddressId() {
		return addressId;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public String getPhysicalLocation1() {
		return physicalLocation1;
	}

	public void setPhysicalLocation1(String physicalLocation1) {
		this.physicalLocation1 = physicalLocation1;
	}

	public String getPhysicalLocation2() {
		return physicalLocation2;
	}

	public void setPhysicalLocation2(String physicalLocation2) {
		this.physicalLocation2 = physicalLocation2;
	}

	public String getPhysicalLocation3() {
		return physicalLocation3;
	}

	public void setPhysicalLocation3(String physicalLocation3) {
		this.physicalLocation3 = physicalLocation3;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public void setInstallAddressId(String installAddressId) {
		this.installAddressId = installAddressId;
	}

	public String getInstallAddressId() {
		return installAddressId;
	}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getLbsAddressFlag() {
		return lbsAddressFlag;
	}

	public void setLbsAddressFlag(String lbsAddressFlag) {
		this.lbsAddressFlag = lbsAddressFlag;
	}
}
