package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Debrief implements Serializable {
	private static final long serialVersionUID = 4253597565608486682L;

	private String debriefNumber;
	private String debriefStatus;
	private Date serviceStartDate;
	private Date serviceEndDate;
	private ListOfValues resolutionCode;
	private ListOfValues actualFailureCode;
	private String repairDescription;
	private boolean repairCompleteFlag;
	private List<PartLineItem> partDebriefList;
	private String travelDistance;
	private ListOfValues travelUnitOfMeasure;
	private String travelDuration;
	private String serviceProvider;
	private ListOfValues deviceCondition;
	private String problemDescription;
	private AccountContact technician; 
	private boolean genuineLexmarkSuppliesUsedFlag;
	private boolean repairOrReplacementFlag;
	private boolean assetNetworkConnectedFlag;
	private String idAddress;
	private String macAddress;
	private String pageCountAll;
	private String lexmarkTag;
	private String replacementAssetModel;
	private String replacementAssetSerialNumber;
	private String serviceProviderReferenceNumber;
	private String debriefActionStatus;
	private String supplyManufacturer;
	private Asset installedAsset;
	private Date serviceRequestedDate; //Added for CI BRD13-10-07
	private Date expectedStartDate;	//Added for hardwaredebrief
	
	public String getDebriefNumber() {
		return debriefNumber;
	}
	public void setDebriefNumber(String debriefNumber) {
		this.debriefNumber = debriefNumber;
	}
	public String getDebriefStatus() {
		return debriefStatus;
	}
	public void setDebriefStatus(String debriefStatus) {
		this.debriefStatus = debriefStatus;
	}
	public Date getServiceStartDate() {
		return serviceStartDate;
	}
	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	public Date getServiceEndDate() {
		return serviceEndDate;
	}
	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}
	public ListOfValues getResolutionCode() {
		return resolutionCode;
	}
	public void setResolutionCode(ListOfValues resolutionCode) {
		this.resolutionCode = resolutionCode;
	}
	public ListOfValues getActualFailureCode() {
		return actualFailureCode;
	}
	public void setActualFailureCode(ListOfValues actualFailureCode) {
		this.actualFailureCode = actualFailureCode;
	}
	public String getRepairDescription() {
		return repairDescription;
	}
	public void setRepairDescription(String repairDescription) {
		this.repairDescription = repairDescription;
	}
	public boolean isRepairCompleteFlag() {
		return repairCompleteFlag;
	}
	public void setRepairCompleteFlag(boolean repairCompleteFlag) {
		this.repairCompleteFlag = repairCompleteFlag;
	}
	public List<PartLineItem> getPartDebriefList() {
		return partDebriefList;
	}
	public void setPartDebriefList(List<PartLineItem> partDebriefList) {
		this.partDebriefList = partDebriefList;
	}
	public String getTravelDistance() {
		return travelDistance;
	}
	public void setTravelDistance(String travelDistance) {
		this.travelDistance = travelDistance;
	}
	public ListOfValues getTravelUnitOfMeasure() {
		return travelUnitOfMeasure;
	}
	public void setTravelUnitOfMeasure(ListOfValues travelUnitOfMeasure) {
		this.travelUnitOfMeasure = travelUnitOfMeasure;
	}
	public String getTravelDuration() {
		return travelDuration;
	}
	public void setTravelDuration(String travelDuration) {
		this.travelDuration = travelDuration;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public ListOfValues getDeviceCondition() {
		return deviceCondition;
	}
	public void setDeviceCondition(ListOfValues deviceCondition) {
		this.deviceCondition = deviceCondition;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public AccountContact getTechnician() {
		return technician;
	}
	public void setTechnician(AccountContact technician) {
		this.technician = technician;
	}
	public boolean isGenuineLexmarkSuppliesUsedFlag() {
		return genuineLexmarkSuppliesUsedFlag;
	}
	public void setGenuineLexmarkSuppliesUsedFlag(
			boolean genuineLexmarkSuppliesUsedFlag) {
		this.genuineLexmarkSuppliesUsedFlag = genuineLexmarkSuppliesUsedFlag;
	}
	public boolean isRepairOrReplacementFlag() {
		return repairOrReplacementFlag;
	}
	public void setRepairOrReplacementFlag(boolean repairOrReplacementFlag) {
		this.repairOrReplacementFlag = repairOrReplacementFlag;
	}
	public boolean isAssetNetworkConnectedFlag() {
		return assetNetworkConnectedFlag;
	}
	public void setAssetNetworkConnectedFlag(boolean assetNetworkConnectedFlag) {
		this.assetNetworkConnectedFlag = assetNetworkConnectedFlag;
	}
	public String getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(String idAddress) {
		this.idAddress = idAddress;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getPageCountAll() {
		return pageCountAll;
	}
	public void setPageCountAll(String pageCountAll) {
		this.pageCountAll = pageCountAll;
	}
	public String getLexmarkTag() {
		return lexmarkTag;
	}
	public void setLexmarkTag(String lexmarkTag) {
		this.lexmarkTag = lexmarkTag;
	}
	public String getReplacementAssetModel() {
		return replacementAssetModel;
	}
	public void setReplacementAssetModel(String replacementAssetModel) {
		this.replacementAssetModel = replacementAssetModel;
	}
	public String getReplacementAssetSerialNumber() {
		return replacementAssetSerialNumber;
	}
	public void setReplacementAssetSerialNumber(String replacementAssetSerialNumber) {
		this.replacementAssetSerialNumber = replacementAssetSerialNumber;
	}
	public String getServiceProviderReferenceNumber() {
		return serviceProviderReferenceNumber;
	}
	public void setServiceProviderReferenceNumber(
			String serviceProviderReferenceNumber) {
		this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
	}
	public String getDebriefActionStatus() {
		return debriefActionStatus;
	}
	public void setDebriefActionStatus(String debriefActionStatus) {
		this.debriefActionStatus = debriefActionStatus;
	}
	public void setSupplyManufacturer(String supplyManufacturer) {
		this.supplyManufacturer = supplyManufacturer;
	}
	public String getSupplyManufacturer() {
		return supplyManufacturer;
	}
	public void setInstalledAsset(Asset installedAsset) {
		this.installedAsset = installedAsset;
	}
	public Asset getInstalledAsset() {
		return installedAsset;
	}
	public Date getServiceRequestedDate() {
		return serviceRequestedDate;
	}
	public void setServiceRequestedDate(Date serviceRequestedDate) {
		this.serviceRequestedDate = serviceRequestedDate;
	}
	public void setExpectedStartDate(Date expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}
	public Date getExpectedStartDate() {
		return expectedStartDate;
	}
}
