package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * mapping file: "do-accountassetdetaildo-mapping.xml"
 */
public class AccountAssetDetailDo extends AccountAsset {
    private static final long serialVersionUID = -2259695848315599167L;

    private String ipGateway;
    private String faxPort;
    private String customerCostCode;
    private String specialInstructions;
    private String storeFrontName;
    private String modelNumber;
    private String assetType;
    private String deviceName;
    private String physicalLocation1;
    private String physicalLocation2;
    private String physicalLocation3;
    private String accountName;
    private String accountId;
    private ArrayList<ServiceAgreementProductDo> entitlements;
    private ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads;
    private String hierarchyNodeId;
    private String hierarchyNodeValue;
	private boolean accountSplitterFlag;

    public ArrayList<AssetMeasurementCharacteristicsBCDo> getMeterReads() {
        return meterReads;
    }

    public void setMeterReads(ArrayList<AssetMeasurementCharacteristicsBCDo> meterReads) {
        this.meterReads = meterReads;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setEntitlements(ArrayList<ServiceAgreementProductDo> entitlements) {
        this.entitlements = entitlements;
    }

    public ArrayList<ServiceAgreementProductDo> getEntitlements() {
        return entitlements;
    }

    public String getIpGateway() {
        return ipGateway;
    }

    public void setIpGateway(String ipGateway) {
        this.ipGateway = ipGateway;
    }


    public String getFaxPort() {
        return faxPort;
    }

    public void setFaxPort(String faxPort) {
        this.faxPort = faxPort;
    }

    public String getCustomerCostCode() {
        return customerCostCode;
    }

    public void setCustomerCostCode(String customerCostCode) {
        this.customerCostCode = customerCostCode;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getStoreFrontName() {
        return storeFrontName;
    }

    public void setStoreFrontName(String storeFrontName) {
        this.storeFrontName = storeFrontName;
    }
    public void setHierarchyNodeId(String hierarchyNodeId) {
        this.hierarchyNodeId = hierarchyNodeId;
    }
    public String getHierarchyNodeId() {
        return hierarchyNodeId;
    }
    public void setHierarchyNodeValue(String hierarchyNodeValue) {
		this.hierarchyNodeValue = hierarchyNodeValue;
	}

	public String getHierarchyNodeValue() {
		return hierarchyNodeValue;
	}

	public boolean isAccountSplitterFlag() {
		return accountSplitterFlag;
	}

	public void setAccountSplitterFlag(boolean accountSplitterFlag) {
		this.accountSplitterFlag = accountSplitterFlag;
	}
	

}
