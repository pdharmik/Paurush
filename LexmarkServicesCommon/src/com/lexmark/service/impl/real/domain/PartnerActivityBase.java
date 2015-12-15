package com.lexmark.service.impl.real.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import com.lexmark.util.LangUtil;

public class PartnerActivityBase extends AccountBasedDo {
	private static final long serialVersionUID = 5604114140704818827L;
	
	private String serviceRequestType;
	private String serviceRequestId;
	private String serviceRequestNumber;
	private String assetSerialNumber;
	private String assetProductLine;
	private String assetMachineTypeModel;
	private String assetProductTLI;
	private String assetActivityNumber;
	private String activityType;
	private String activityStatus;
	private String activitySubStatus;
	private String activityDate;	
	private String activityId;
	private String assetId;
	private String primaryContactId;
	private String primaryContactFirstName;
	private String primaryContactLastName;
	private String primaryContactWorkPhone;
	private String primaryContactAlternateWorkPhone;
	private String primaryContactEmailAddress;
	private String partnerAccountName;
	private String customerAccountName;
	private String overridecustomerAccountName;
	private String technicianContactId;
	private String technicianContactFirstName;
	private String technicianContactLastName;
	private String responseMatric;
	private String accountId;
	private String assetSerialNumberOverride;
	private String customerRequestedResponseDate;
	private String reviewComments;
	private String actualFailureCode;
	private String serviceRequestDate;
	private String problemDescription;
	private String customerReferenceNumber;
	private String customerAccountId;
	private String debriefExpectedStartDate;
	private String debriefServiceStartDate;
	private String debriefServiceEndDate;
	private String debriefResolutionCode;
	private String debriefRepairDescription;
	private String debriefActionStatus;
	private String statusUpdateDate;
	private String addressStatus;
	private String partQuantityOrderLimit;
	private String partnerAccountId;
	private String serviceProviderReferenceNumber;
	private String primaryAddressId;
	private String primaryAddressLine1;
	private String primaryAddressName;
	private String primaryAddressLine2;
	private String primaryAddressLine3;
	private String primaryAddressCity;
	private String primaryAddressState;
	private String primaryAddressCountry;	
	private String primaryAddressProvince;
	private String primaryAddressPostalCode;
	private String organizationID;
	private String serviceAddressId;
	private String serviceAddressName;
	private String serviceAddressLine1;
	private String serviceAddressLine2;
	private String serviceAddressLine3;
	private String serviceAddressLine4;
	private String serviceCity;
	private String serviceState;
	private String serviceProvince;
	private String servicePostalCode;
	private String serviceCountry;
	private String serviceProviderEmailAddress;
	//pickup Addresses
	private String serviceCountryCode;
	private String serviceDistrict;
	private String serviceHouseNo;
	private String serviceCounty;
	private String serviceRegion;
	private String SRAddressName;
	private String serviceTrilliumErrorMessage;
	private String serviceStateFullName;
	private String serviceLatitude;
	private String serviceLongitude;
	
	private String serviceRequestStatus;
	private String resolutionMetric;
	private String resolutionDate;
	private String resolutionCode;
	private String serviceActivityWithin30Days;
	private String serviceSummary;
	private String accountSpecialHandling;
	private String assetWarningMessage;
	private String estimatedArrivalTime;
	private String serviceProviderAttemptNumber;
	private String secondaryContactId;
	private String secondaryContactFirstName;
	private String secondaryContactLastName;
	private String secondaryContactWorkPhone;
	private String secondaryContactAlternateWorkPhone;
	private String secondaryContactEmailAddress;
	private String assetDeviceTag;
	private Boolean assetNetworkConnectedFlag;
	private String assetIPAddress;
	private String assetMACAddress;
	private String assetIPGateway;
	private String debriefTravelDistance;
	private String debriefTravelUnitOfMeasure;
	private String debriefTravelDuration;
	private String debriefActualFailureCode;	
	private String debriefProblemDescription;
	private String debriefGenuineLexmarkSuppliesUsedFlag;
	private String installedAssetDeviceTag;
	private Boolean installedAssetNetworkConnectedFlag;
	private String installedAssetIPAddress;
	private String installedAssetMACAddress;
	private String debriefSupplyManufacturer;
	private String installedAssetModelNumber;
	private String installedAssetSerialNumber;
	private String debriefDeviceCondition;
	private String type;
	private String currency;
	private Boolean activityFlag;
	private String overrideActualFailureCode;
	private String repairedAssetDeviceTag;
	private String repairedAssetModelNumber;
	private String installedAssetId;
	private String repairedAssetId;
	private String machineTypeModelOverride;
	private String activityDateWithOutMarker;
	private String customerRequestedResponseDateWithOutMarker;
	private String debriefStatus;
	private String srType;
		// added by nelson for CI5
	
	private String county;
	private String district;
	private String officeNumber;
	private String statusDetail;
	private String hardwareDebriefAddressName;
	private String primarySuppliesContactFirstName;
	private String primarySuppliesContactLastName;
	private String primarySuppliesContactId;
	private String primarySuppliesContactWorkPhone;
	private String primarySuppliesContactEmail;
	private String primarySuppliesContactAlternatePhone;
	private String secondarySuppliesContactFirstName;
	private String secondarySuppliesContactLastName;
	private String createdDate;
	private String assetDepartment;
	private String assetDescription;
	private String assetSubnetMask;
	private String assetMac;
	private String assetCostCenter;
	private String assetTopBill;
	private String ipV6;
	private String portNumber;
	private String assetHostName;
	private String assetDevTypeModelNumber;
	private String assetWiringClosestNetworkPoint;
	private String assetFaxConnected;
	private String assetFaxPortNumber;
	private String assetInstallDate;
	private String assetOperatingSystem;
	private String assetSpecialUsage;
	private String assetOperatingSystemVersion;
	private String assetLifeCycle;
	private String assetFirmware;
	private String assetNetworkTopology;
	private String assetHierarchyLevel;
	
	private String assetInstalledAddressLine1;
	private String assetInstalledAddressLine2;
	private String assetInstalledAddressLine3;
	private String assetInstalledAddressName;
	private String assetInstalledCity;
	private String assetInstalledCountry;
	private String assetInstalledCounty;
	private String assetInstalledState;
	private String assetInstalledZipcode;
	private String assetInstalledAddressDistrict;
	private String assetInstalledProvince;
	private String assetInstalledAddressBuilding;
	private String assetInstalledAddressFloor;
	private String assetInstalledAddressOffice;
	
	private String projectName;
	private String projectStartDate;
	private String projectPhase;
	private String storeFrontName;
	private String assetDeviceName;
	private String assetProductImageURL;
	
	private String secondaryAddressId;
	private String secondaryAddressLine1;
	private String secondaryAddressName;
	private String secondaryAddressLine2;
	private String secondaryAddressLine3;
	private String secondaryAddressCity;
	private String secondaryAddressState;
	private String secondaryAddressCountry;	
	private String secondaryAddressProvince;
	private String secondaryAddressPostalCode;
	
	private String expectedStartDate;
	private Date expectedCompletionDate;
	private String serviceProviderETA;
	
	private String productModelNumber;
	private String moveToAddressGrouped;
	private String madcResponseMatric;
	private String madcProductLine;
	private String madcMachineTypeModel;
	private String partnerPortalPhysicaLocation1;
	private String committedDate;
	
	private String rfvErrors;
	
	private String technicianName;
	
	private String assetField1; //added for 11599 MPS 2.1
	private String assetField2; //added for 11599 MPS 2.1
	private String assetField3; //added for 11599 MPS 2.1
	
	//added for CR 12032
	
	private String massUploadContactLastName; 
	private String massUploadContactFirstName;
	private String massUploadContactWorkPhoneNum;
	private String massUploadContactHomePhone;
	private String massUploadContactEmailAddress;
	private String massUploadContactJobRole;
	private String massUploadUploaderCountry;
	private String massUploadInstalledAssetAddressName;
	private String massUploadInstalledAddressLine1;
	private String massUploadInstalledAddressLine2;
	private String massUploadInstalledCity;
	private String massUploadInstalledCountry;
	private String massUploadInstalledState;
	private String massUploadInstalledProvince;
	private String massUploadInstalledPostalCode;
	private String massUploadInstalledCounty;
	private String massUploadInstalledAssetPhyLoc1;
	private String massUploadInstalledAssetPhyLoc2;
	private String massUploadInstalledAssetPhyLoc3;
	private String massUploadInstalledAddressDistrict;
	private String massUploadInstalledAddressOfficeNum;
	
	
	private boolean massUploadNetworkConnected;
	private String massUploadIPAddress;
	private String massUploadIPGateway;
	private String massUploadIPSubmask;
	private String massUploadIPv6;
	private String massUploadAssetMacAddress;
	private String massUploadAssetDeviceCondition;
	private boolean massUploadFaxConnected;
	private String massUploadFaxPortNumber;
	private String massUploadHostName;
	private String massUploadComputerName;
	private String massUploadPortNumber;
	private String massUploadWiringClosetNetwkPt;
	private String massUploadAccountName;
	private String massUploadAssetCostCenter;
	private String massUploadCustomerDeviceTag;
	private String massUploadDeptId;
	private String massUploadDeptName;
	private String massUploadFirmware;
	private String massUploadNetworkTopology;
	private String massUploadOS;
	private String massUploadOSVersion;
	private String massUploadTopBill;
	private String massUploadSpecialUsage;
	private String massUploadAssetDesc;  //asset
	
	
	
	//Move From Address fields for MPS CR 11055
	private String moveFromMadcAddressID;
	private String moveFromMadcAddressLine1;
	private String moveFromMadcAddressLine2;
	private String moveFromMadcCity;
	private String moveFromMadcCountry;
	private String moveFromMadcISOCountryCode;
	private String moveFromMadcCounty;
	private String moveFromMadcDistrict;
	private String moveFromMadcState;
	private String moveFromMadcStateFullName;
	private String moveFromMadcHouseNum;
	private String moveFromMadcFirstLogicErrorMessage;
	private String moveFromMadcLatitude;
	private String moveFromMadcLongitude;
	private String moveFromMadcRegion;
	private String moveFromMadcPostalCode;
	private String moveFromMadcProvince;
	private String moveFromMadcBuilding;
	private String moveFromMadcFloor;
	private String moveFromMadcOffice;
	private Integer massUploadServiceProviderReferenceNum;
	private String deviceCondition;
	private String agencyContactFirstName;
	private String agencyContactLastName;
	private String agencyContacteMail;
	private Date dispatchDate;														 
	private String parentSRNum; 	
	// LBS fields
	private String buildingIdPredebrief;
	private String floorIdPredebrief;
	private String xCoordinatePreDebrief;
	private String yCoordinatePreDebrief;
	private String latitudePreDebrief;
	private String longitutdePreDebrief;
	private String siteIdPredDebrief;
	private String sitePredDebrief;
	private String zoneIdPredDebrief;
	private String zonePredDebrief;
	private Boolean lbsAddressFlag;
	private String addressId;
	private String campusName;
	private String zoneName;
	private ArrayList<ServiceRequestActivityAttachmentsDo> serviceRequestAttachmentss;
	
	private String deinstSerialNumber;
	private String deinstPartNumber;
	private String deinstModel;
	private String deinstBrand;
	private String deinstIpAddress;
	private String deinstHostName;
	private Date deinstRemovalDate;
	private String deinstComments;
	private String deinstAssetTag;
	private String deinstDeviceCondition;

	public String getBuildingIdPredebrief() {
		return buildingIdPredebrief;
	}
	public void setBuildingIdPredebrief(String buildingIdPredebrief) {
		this.buildingIdPredebrief = buildingIdPredebrief;
	}
	public String getFloorIdPredebrief() {
		return floorIdPredebrief;
	}
	public void setFloorIdPredebrief(String floorIdPredebrief) {
		this.floorIdPredebrief = floorIdPredebrief;
	}
	public String getxCoordinatePreDebrief() {
		return xCoordinatePreDebrief;
	}
	public void setxCoordinatePreDebrief(String xCoordinatePreDebrief) {
		this.xCoordinatePreDebrief = xCoordinatePreDebrief;
	}
	public String getyCoordinatePreDebrief() {
		return yCoordinatePreDebrief;
	}
	public void setyCoordinatePreDebrief(String yCoordinatePreDebrief) {
		this.yCoordinatePreDebrief = yCoordinatePreDebrief;
	}
	public String getLatitudePreDebrief() {
		return latitudePreDebrief;
	}
	public void setLatitudePreDebrief(String latitudePreDebrief) {
		this.latitudePreDebrief = latitudePreDebrief;
	}
	public String getLongitutdePreDebrief() {
		return longitutdePreDebrief;
	}
	public void setLongitutdePreDebrief(String longitutdePreDebrief) {
		this.longitutdePreDebrief = longitutdePreDebrief;
	}
	public String getSiteIdPredDebrief() {
		return siteIdPredDebrief;
	}
	public void setSiteIdPredDebrief(String siteIdPredDebrief) {
		this.siteIdPredDebrief = siteIdPredDebrief;
	}
	public String getSitePredDebrief() {
		return sitePredDebrief;
	}
	public void setSitePredDebrief(String sitePredDebrief) {
		this.sitePredDebrief = sitePredDebrief;
	}
	public String getZoneIdPredDebrief() {
		return zoneIdPredDebrief;
	}
	public void setZoneIdPredDebrief(String zoneIdPredDebrief) {
		this.zoneIdPredDebrief = zoneIdPredDebrief;
	}
	public String getZonePredDebrief() {
		return zonePredDebrief;
	}
	public void setZonePredDebrief(String zonePredDebrief) {
		this.zonePredDebrief = zonePredDebrief;
	}
	public String getParentSRNum() {
		return parentSRNum;
	}
	public void setParentSRNum(String parentSRNum) {
		this.parentSRNum = parentSRNum;
	}
	public Date getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getDeviceCondition() {
		return deviceCondition;
	}
	public void setDeviceCondition(String deviceCondition) {
		this.deviceCondition = deviceCondition;
	}
	public Integer getMassUploadServiceProviderReferenceNum() {
		return massUploadServiceProviderReferenceNum;
	}
	public void setMassUploadServiceProviderReferenceNum(
			Integer massUploadServiceProviderReferenceNum) {
		this.massUploadServiceProviderReferenceNum = massUploadServiceProviderReferenceNum;
	}
	
	public String getPartnerPortalPhysicaLocation1() {
		return partnerPortalPhysicaLocation1;
	}
	public void setPartnerPortalPhysicaLocation1(
			String partnerPortalPhysicaLocation1) {
		this.partnerPortalPhysicaLocation1 = partnerPortalPhysicaLocation1;
	}
	public String getPartnerPortalPhysicaLocation2() {
		return partnerPortalPhysicaLocation2;
	}
	public void setPartnerPortalPhysicaLocation2(
			String partnerPortalPhysicaLocation2) {
		this.partnerPortalPhysicaLocation2 = partnerPortalPhysicaLocation2;
	}
	public String getPartnerPortalPhysicaLocation3() {
		return partnerPortalPhysicaLocation3;
	}
	public void setPartnerPortalPhysicaLocation3(
			String partnerPortalPhysicaLocation3) {
		this.partnerPortalPhysicaLocation3 = partnerPortalPhysicaLocation3;
	}
	private String partnerPortalPhysicaLocation2;
	private String partnerPortalPhysicaLocation3;
	
	public String getMadcMachineTypeModel() {
		return madcMachineTypeModel;
	}
	public void setMadcMachineTypeModel(String madcMachineTypeModel) {
		this.madcMachineTypeModel = madcMachineTypeModel;
	}
	public String getMadcProductLine() {
		return madcProductLine;
	}
	public void setMadcProductLine(String madcProductLine) {
		this.madcProductLine = madcProductLine;
	}
	public String getMadcProductTLI() {
		return madcProductTLI;
	}
	public void setMadcProductTLI(String madcProductTLI) {
		this.madcProductTLI = madcProductTLI;
	}
	private String madcProductTLI;
	
	
	public String getMadcResponseMatric() {
		return madcResponseMatric;
	}
	public void setMadcResponseMatric(String madcResponseMatric) {
		this.madcResponseMatric = madcResponseMatric;
	}
	
	public String getSrType() {
		return srType;
	}
	public void setSrType(String srType) {
		this.srType = srType;
	}
	private String customerReportingName;
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getServiceRequestType() {
		return serviceRequestType;
	}
	public void setServiceRequestType(String serviceRequestType) {
		this.serviceRequestType = serviceRequestType;
	}
	public String getServiceRequestId() {
		return serviceRequestId;
	}
	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}
	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}
	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}
	public String getAssetSerialNumber() {
		return assetSerialNumber;
	}
	public void setAssetSerialNumber(String assetSerialNumber) {
		this.assetSerialNumber = assetSerialNumber;
	}
	public String getAssetProductLine() {
		return assetProductLine;
	}
	public void setAssetProductLine(String assetProductLine) {
		this.assetProductLine = assetProductLine;
	}
	public String getAssetMachineTypeModel() {
		return assetMachineTypeModel;
	}
	public void setAssetMachineTypeModel(String assetMachineTypeModel) {
		this.assetMachineTypeModel = assetMachineTypeModel;
	}
	public String getAssetProductTLI() {
		return assetProductTLI;
	}
	public void setAssetProductTLI(String assetProductTLI) {
		this.assetProductTLI = assetProductTLI;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	public String getActivitySubStatus() {
		return activitySubStatus;
	}
	public void setActivitySubStatus(String activitySubStatus) {
		this.activitySubStatus = activitySubStatus;
	}
	public Date getActivityDate() {
		return convertStringToDateWithMarker(activityDate);
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getAssetId() {
		return assetId;
	}
	public String getPrimaryContactFirstName() {
		return primaryContactFirstName;
	}

	public void setPrimaryContactFirstName(String primaryContactFirstName) {
		this.primaryContactFirstName = primaryContactFirstName;
	}

	public String getPrimaryContactLastName() {
		return primaryContactLastName;
	}

	public void setPrimaryContactLastName(String primaryContactLastName) {
		this.primaryContactLastName = primaryContactLastName;
	}

	public String getPartnerAccountName() {
		return partnerAccountName;
	}

	public void setPartnerAccountName(String partnerAccountName) {
		this.partnerAccountName = partnerAccountName;
	}

	public String getCustomerAccountName() {
		return customerAccountName;
	}

	public void setCustomerAccountName(String customerAccountName) {
		this.customerAccountName = customerAccountName;
	}

	public String getTechnicianContactId() {
		return technicianContactId;
	}

	public void setTechnicianContactId(String technicianContactId) {
		this.technicianContactId = technicianContactId;
	}

	public String getTechnicianContactFirstName() {
		return technicianContactFirstName;
	}

	public void setTechnicianContactFirstName(String technicianContactFirstName) {
		this.technicianContactFirstName = technicianContactFirstName;
	}

	public String getTechnicianContactLastName() {
		return technicianContactLastName;
	}

	public void setTechnicianContactLastName(String technicianContactLastName) {
		this.technicianContactLastName = technicianContactLastName;
	}

	public Date getCustomerRequestedResponseDate() {
		return convertStringToDateWithMarker(customerRequestedResponseDate);
	}

	public void setCustomerRequestedResponseDate(String customerRequestedResponseDate) {
		this.customerRequestedResponseDate = customerRequestedResponseDate;
	}
	
	public void setResponseMatric(String responseMatric) {
		this.responseMatric = responseMatric;
	}

	public String getResponseMatric() {
		return responseMatric;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAssetSerialNumberOverride(String assetSerialNumberOverride) {
		this.assetSerialNumberOverride = assetSerialNumberOverride;
	}

	public String getAssetSerialNumberOverride() {
		return assetSerialNumberOverride;
	}

	public String getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	public String getActualFailureCode() {
		return actualFailureCode;
	}

	public void setActualFailureCode(String actualFailureCode) {
		this.actualFailureCode = actualFailureCode;
	}
	public Date getServiceRequestDate() {
		return convertStringToDateWithMarker(serviceRequestDate);
	}
	public void setServiceRequestDate(String serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public String getCustomerReferenceNumber() {
		return customerReferenceNumber;
	}

	public void setCustomerReferenceNumber(String customerReferenceNumber) {
		this.customerReferenceNumber = customerReferenceNumber;
	}

	public String getPrimaryContactId() {
		return primaryContactId;
	}

	public void setPrimaryContactId(String primaryContactId) {
		this.primaryContactId = primaryContactId;
	}

	public String getPrimaryContactWorkPhone() {
		if(primaryContactWorkPhone != null && primaryContactWorkPhone.startsWith("+")) {
			primaryContactWorkPhone = primaryContactWorkPhone.replace("+", "");
		}
		return primaryContactWorkPhone;
	}

	public void setPrimaryContactWorkPhone(String primaryContactWorkPhone) {
		this.primaryContactWorkPhone = primaryContactWorkPhone;
	}

	public String getPrimaryContactEmailAddress() {
		return primaryContactEmailAddress;
	}

	public void setPrimaryContactEmailAddress(String primaryContactEmailAddress) {
		this.primaryContactEmailAddress = primaryContactEmailAddress;
	}
	public String getCustomerAccountId() {
		return customerAccountId;
	}
	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
	public Date getDebriefExpectedStartDate() {
		return convertStringToDateWithMarker(debriefExpectedStartDate);
	}
	public void setDebriefExpectedStartDate(String debriefExpectedStartDate) {
		this.debriefExpectedStartDate = debriefExpectedStartDate;
	}
	public Date getDebriefServiceStartDate() {
		return convertStringToDateWithMarker(debriefServiceStartDate);
	}

	public void setDebriefServiceStartDate(String debriefServiceStartDate) {
		this.debriefServiceStartDate = debriefServiceStartDate;
	}

	public Date getDebriefServiceEndDate() {
		return convertStringToDateWithMarker(debriefServiceEndDate);
	}

	public void setDebriefServiceEndDate(String debriefServiceEndDate) {
		this.debriefServiceEndDate = debriefServiceEndDate;
	}


	public String getDebriefResolutionCode() {
		return debriefResolutionCode;
	}

	public void setDebriefResolutionCode(String debriefResolutionCode) {
		this.debriefResolutionCode = debriefResolutionCode;
	}

	public String getDebriefRepairDescription() {
		return debriefRepairDescription;
	}

	public void setDebriefRepairDescription(String debriefRepairDescription) {
		this.debriefRepairDescription = debriefRepairDescription;
	}

	public String getDebriefActionStatus() {
		return debriefActionStatus;
	}

	public void setDebriefActionStatus(String debriefActionStatus) {
		this.debriefActionStatus = debriefActionStatus;
	}

	public void setStatusUpdateDate(String statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public Date getStatusUpdateDate() {
		return convertStringToDateWithMarker(statusUpdateDate);
	}
	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}

	public String getAddressStatus() {
		return addressStatus;
	}

	public void setPartQuantityOrderLimit(String partQuantityOrderLimit) {
		this.partQuantityOrderLimit = partQuantityOrderLimit;
	}

	public String getPartQuantityOrderLimit() {
		return partQuantityOrderLimit;
	}

	public void setPartnerAccountId(String partnerAccountId) {
		this.partnerAccountId = partnerAccountId;
	}

	public String getPartnerAccountId() {
		return partnerAccountId;
	}
	
	public String getServiceProviderReferenceNumber() {
		return serviceProviderReferenceNumber;
	}

	public void setServiceProviderReferenceNumber(String serviceProviderReferenceNumber) {
		this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
	}

	public String getPrimaryAddressLine1() {
		return primaryAddressLine1;
	}

	public void setPrimaryAddressLine1(String primaryAddressLine1) {
		this.primaryAddressLine1 = primaryAddressLine1;
	}

	public String getPrimaryAddressName() {
		return primaryAddressName;
	}

	public void setPrimaryAddressName(String primaryAddressName) {
		this.primaryAddressName = primaryAddressName;
	}

	public String getPrimaryAddressLine2() {
		return primaryAddressLine2;
	}

	public void setPrimaryAddressLine2(String primaryAddressLine2) {
		this.primaryAddressLine2 = primaryAddressLine2;
	}

	public String getPrimaryAddressLine3() {
		return primaryAddressLine3;
	}

	public void setPrimaryAddressLine3(String primaryAddressLine3) {
		this.primaryAddressLine3 = primaryAddressLine3;
	}

	public String getPrimaryAddressCity() {
		return primaryAddressCity;
	}

	public void setPrimaryAddressCity(String primaryAddressCity) {
		this.primaryAddressCity = primaryAddressCity;
	}

	public String getPrimaryAddressState() {
		return primaryAddressState;
	}

	public void setPrimaryAddressState(String primaryAddressState) {
		this.primaryAddressState = primaryAddressState;
	}

	public String getPrimaryAddressCountry() {
		return primaryAddressCountry;
	}

	public void setPrimaryAddressCountry(String primaryAddressCountry) {
		this.primaryAddressCountry = primaryAddressCountry;
	}

	public String getPrimaryAddressProvince() {
		return primaryAddressProvince;
	}

	public void setPrimaryAddressProvince(String primaryAddressProvince) {
		this.primaryAddressProvince = primaryAddressProvince;
	}

	public String getPrimaryAddressPostalCode() {
		return primaryAddressPostalCode;
	}

	public void setPrimaryAddressPostalCode(String primaryAddressPostalCode) {
		this.primaryAddressPostalCode = primaryAddressPostalCode;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}
	public String getServiceAddressId() {
		return serviceAddressId;
	}
	public void setServiceAddressId(String serviceAddressId) {
		this.serviceAddressId = serviceAddressId;
	}
	public String getServiceAddressName() {
		return serviceAddressName;
	}
	public void setServiceAddressName(String serviceAddressName) {
		this.serviceAddressName = serviceAddressName;
	}
	public String getServiceAddressLine1() {
		return serviceAddressLine1;
	}
	public void setServiceAddressLine1(String serviceAddressLine1) {
		this.serviceAddressLine1 = serviceAddressLine1;
	}
	public String getServiceAddressLine2() {
		return serviceAddressLine2;
	}
	public void setServiceAddressLine2(String serviceAddressLine2) {
		this.serviceAddressLine2 = serviceAddressLine2;
	}
	public String getServiceAddressLine3() {
		return serviceAddressLine3;
	}
	public void setServiceAddressLine3(String serviceAddressLine3) {
		this.serviceAddressLine3 = serviceAddressLine3;
	}
	public String getServiceAddressLine4() {
		return serviceAddressLine4;
	}
	public void setServiceAddressLine4(String serviceAddressLine4) {
		this.serviceAddressLine4 = serviceAddressLine4;
	}
	public String getServiceCity() {
		return serviceCity;
	}
	public void setServiceCity(String serviceCity) {
		this.serviceCity = serviceCity;
	}
	public String getServiceState() {
		return serviceState;
	}
	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}
	public String getServiceProvince() {
		return serviceProvince;
	}
	public void setServiceProvince(String serviceProvince) {
		this.serviceProvince = serviceProvince;
	}
	public String getServicePostalCode() {
		return servicePostalCode;
	}
	public void setServicePostalCode(String servicePostalCode) {
		this.servicePostalCode = servicePostalCode;
	}
	public String getServiceCountry() {
		return serviceCountry;
	}
	public void setServiceCountry(String serviceCountry) {
		this.serviceCountry = serviceCountry;
	}

	public void setServiceProviderEmailAddress(
			String serviceProviderEmailAddress) {
		this.serviceProviderEmailAddress = serviceProviderEmailAddress;
	}

	public String getServiceProviderEmailAddress() {
		return serviceProviderEmailAddress;
	}

	public String getServiceCountryCode() {
		return serviceCountryCode;
	}
	public void setServiceCountryCode(String serviceCountryCode) {
		this.serviceCountryCode = serviceCountryCode;
	}
	public String getServiceDistrict() {
		return serviceDistrict;
	}
	public void setServiceDistrict(String serviceDistrict) {
		this.serviceDistrict = serviceDistrict;
	}
	public String getServiceHouseNo() {
		return serviceHouseNo;
	}
	public void setServiceHouseNo(String serviceHouseNo) {
		this.serviceHouseNo = serviceHouseNo;
	}
	public String getServiceCounty() {
		return serviceCounty;
	}
	public void setServiceCounty(String serviceCounty) {
		this.serviceCounty = serviceCounty;
	}
	public String getServiceRegion() {
		return serviceRegion;
	}
	public void setServiceRegion(String serviceRegion) {
		this.serviceRegion = serviceRegion;
	}
	public String getSRAddressName() {
		return SRAddressName;
	}
	public void setSRAddressName(String sRAddressName) {
		SRAddressName = sRAddressName;
	}
	public String getServiceTrilliumErrorMessage() {
		return serviceTrilliumErrorMessage;
	}
	public void setServiceTrilliumErrorMessage(String serviceTrilliumErrorMessage) {
		this.serviceTrilliumErrorMessage = serviceTrilliumErrorMessage;
	}
	public String getServiceStateFullName() {
		return serviceStateFullName;
	}
	public void setServiceStateFullName(String serviceStateFullName) {
		this.serviceStateFullName = serviceStateFullName;
	}
	public String getServiceLatitude() {
		return serviceLatitude;
	}
	public void setServiceLatitude(String serviceLatitude) {
		this.serviceLatitude = serviceLatitude;
	}
	public String getServiceLongitude() {
		return serviceLongitude;
	}
	public void setServiceLongitude(String serviceLongitude) {
		this.serviceLongitude = serviceLongitude;
	}
	public String getServiceRequestStatus() {
		return serviceRequestStatus;
	}

	public void setServiceRequestStatus(String serviceRequestStatus) {
		this.serviceRequestStatus = serviceRequestStatus;
	}
	public String getResolutionMetric() {
		return resolutionMetric;
	}
	public void setResolutionMetric(String resolutionMetric) {
		this.resolutionMetric = resolutionMetric;
	}
	public Date getResolutionDate() {
		return convertStringToDateWithMarker(resolutionDate);
	}
	public void setResolutionDate(String resolutionDate) {
		this.resolutionDate = resolutionDate;
	}
	public String getServiceActivityWithin30Days() {
		return serviceActivityWithin30Days;
	}
	public void setServiceActivityWithin30Days(String serviceActivityWithin30Days) {
		this.serviceActivityWithin30Days = serviceActivityWithin30Days;
	}
	public String getServiceSummary() {
		return serviceSummary;
	}
	public void setServiceSummary(String serviceSummary) {
		this.serviceSummary = serviceSummary;
	}
	public String getAccountSpecialHandling() {
		return accountSpecialHandling;
	}
	public void setAccountSpecialHandling(String accountSpecialHandling) {
		this.accountSpecialHandling = accountSpecialHandling;
	}
	public String getAssetWarningMessage() {
		return assetWarningMessage;
	}
	public void setAssetWarningMessage(String assetWarningMessage) {
		this.assetWarningMessage = assetWarningMessage;
	}
	public Date getEstimatedArrivalTime() {
		return convertStringToDateWithMarker(estimatedArrivalTime);
	}
	public void setEstimatedArrivalTime(String estimatedArrivalTime) {
		this.estimatedArrivalTime = estimatedArrivalTime;
	}
	public void setResolutionCode(String resolutionCode) {
		this.resolutionCode = resolutionCode;
	}
	public String getResolutionCode() {
		return resolutionCode;
	}

	public void setServiceProviderAttemptNumber(
			String serviceProviderAttemptNumber) {
		this.serviceProviderAttemptNumber = serviceProviderAttemptNumber;
	}

	public String getServiceProviderAttemptNumber() {
		return serviceProviderAttemptNumber;
	}

	public String getSecondaryContactId() {
		return secondaryContactId;
	}

	public void setSecondaryContactId(String secondaryContactId) {
		this.secondaryContactId = secondaryContactId;
	}

	public String getSecondaryContactFirstName() {
		return secondaryContactFirstName;
	}

	public void setSecondaryContactFirstName(String secondaryContactFirstName) {
		this.secondaryContactFirstName = secondaryContactFirstName;
	}

	public String getSecondaryContactLastName() {
		return secondaryContactLastName;
	}

	public void setSecondaryContactLastName(String secondaryContactLastName) {
		this.secondaryContactLastName = secondaryContactLastName;
	}

	public String getSecondaryContactWorkPhone() {
		if(secondaryContactWorkPhone != null && secondaryContactWorkPhone.startsWith("+")) {
			secondaryContactWorkPhone = secondaryContactWorkPhone.replace("+", "");
		}
		return secondaryContactWorkPhone;
	}

	public void setSecondaryContactWorkPhone(String secondaryContactWorkPhone) {
		this.secondaryContactWorkPhone = secondaryContactWorkPhone;
	}

	public String getSecondaryContactEmailAddress() {
		return secondaryContactEmailAddress;
	}

	public void setSecondaryContactEmailAddress(String secondaryContactEmailAddress) {
		this.secondaryContactEmailAddress = secondaryContactEmailAddress;
	}
	public String getAssetDeviceTag() {
		return assetDeviceTag;
	}
	public void setAssetDeviceTag(String assetDeviceTag) {
		this.assetDeviceTag = assetDeviceTag;
	}

	public String getAssetIPAddress() {
		return assetIPAddress;
	}
	public void setAssetIPAddress(String assetIPAddress) {
		this.assetIPAddress = assetIPAddress;
	}
	public String getAssetMACAddress() {
		return assetMACAddress;
	}
	public void setAssetMACAddress(String assetMACAddress) {
		this.assetMACAddress = assetMACAddress;
	}
	public void setAssetNetworkConnectedFlag(Boolean assetNetworkConnectedFlag) {
		this.assetNetworkConnectedFlag = assetNetworkConnectedFlag;
	}
	public Boolean getAssetNetworkConnectedFlag() {
		if(assetNetworkConnectedFlag == null) {
			assetNetworkConnectedFlag = false;
		}
		return assetNetworkConnectedFlag;
	}

	public String getDebriefTravelDistance() {
		return debriefTravelDistance;
	}
	public void setDebriefTravelDistance(String debriefTravelDistance) {
		this.debriefTravelDistance = debriefTravelDistance;
	}
	public String getDebriefTravelUnitOfMeasure() {
		return debriefTravelUnitOfMeasure;
	}
	public void setDebriefTravelUnitOfMeasure(String debriefTravelUnitOfMeasure) {
		this.debriefTravelUnitOfMeasure = debriefTravelUnitOfMeasure;
	}
	public String getDebriefTravelDuration() {
		return debriefTravelDuration;
	}
	public void setDebriefTravelDuration(String debriefTravelDuration) {
		this.debriefTravelDuration = debriefTravelDuration;
	}
	public String getDebriefActualFailureCode() {
		return debriefActualFailureCode;
	}
	public void setDebriefActualFailureCode(String debriefActualFailureCode) {
		this.debriefActualFailureCode = debriefActualFailureCode;
	}
	public String getDebriefProblemDescription() {
		return debriefProblemDescription;
	}
	public void setDebriefProblemDescription(String debriefProblemDescription) {
		this.debriefProblemDescription = debriefProblemDescription;
	}
	public String getDebriefGenuineLexmarkSuppliesUsedFlag() {
		return debriefGenuineLexmarkSuppliesUsedFlag;
	}
	public void setDebriefGenuineLexmarkSuppliesUsedFlag(String debriefGenuineLexmarkSuppliesUsedFlag) {
		this.debriefGenuineLexmarkSuppliesUsedFlag = debriefGenuineLexmarkSuppliesUsedFlag;
	}
	public String getInstalledAssetDeviceTag() {
		return installedAssetDeviceTag;
	}
	public void setInstalledAssetDeviceTag(String installedAssetDeviceTag) {
		this.installedAssetDeviceTag = installedAssetDeviceTag;
	}
	public Boolean getInstalledAssetNetworkConnectedFlag() {
		if(installedAssetNetworkConnectedFlag == null) {
			installedAssetNetworkConnectedFlag = false;
		}
		return installedAssetNetworkConnectedFlag;
	}
	public void setInstalledAssetNetworkConnectedFlag(
			Boolean installedAssetNetworkConnectedFlag) {
		this.installedAssetNetworkConnectedFlag = installedAssetNetworkConnectedFlag;
	}
	public String getInstalledAssetIPAddress() {
		return installedAssetIPAddress;
	}
	public void setInstalledAssetIPAddress(String installedAssetIPAddress) {
		this.installedAssetIPAddress = installedAssetIPAddress;
	}
	public String getInstalledAssetMACAddress() {
		return installedAssetMACAddress;
	}
	public void setInstalledAssetMACAddress(String installedAssetMACAddress) {
		this.installedAssetMACAddress = installedAssetMACAddress;
	}
	public String getDebriefSupplyManufacturer() {
		return debriefSupplyManufacturer;
	}
	public void setDebriefSupplyManufacturer(String debriefSupplyManufacturer) {
		this.debriefSupplyManufacturer = debriefSupplyManufacturer;
	}
	public String getInstalledAssetModelNumber() {
		return installedAssetModelNumber;
	}
	public void setInstalledAssetModelNumber(String installedAssetModelNumber) {
		this.installedAssetModelNumber = installedAssetModelNumber;
	}
	public String getInstalledAssetSerialNumber() {
		return installedAssetSerialNumber;
	}
	public void setInstalledAssetSerialNumber(String installedAssetSerialNumber) {
		this.installedAssetSerialNumber = installedAssetSerialNumber;
	}
	public String getDebriefDeviceCondition() {
		return debriefDeviceCondition;
	}
	public void setDebriefDeviceCondition(String debriefDeviceCondition) {
		this.debriefDeviceCondition = debriefDeviceCondition;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrency() {
		return currency;
	}
	public void setActivityFlag(Boolean activityFlag) {
		this.activityFlag = activityFlag;
	}
	public Boolean getActivityFlag() {
		return activityFlag;
	}
	public void setOverrideActualFailureCode(String overrideActualFailureCode) {
		this.overrideActualFailureCode = overrideActualFailureCode;
	}
	public String getOverrideActualFailureCode() {
		return overrideActualFailureCode;
	}
	public void setPrimaryAddressId(String primaryAddressId) {
		this.primaryAddressId = primaryAddressId;
	}
	public String getPrimaryAddressId() {
		return primaryAddressId;
	}
	public void setOverridecustomerAccountName(
			String overridecustomerAccountName) {
		this.overridecustomerAccountName = overridecustomerAccountName;
	}
	public String getOverridecustomerAccountName() {
		return overridecustomerAccountName;
	}
	public void setRepairedAssetDeviceTag(String repairedAssetDeviceTag) {
		this.repairedAssetDeviceTag = repairedAssetDeviceTag;
	}
	public String getRepairedAssetDeviceTag() {
		return repairedAssetDeviceTag;
	}
	public void setRepairedAssetModelNumber(String repairedAssetModelNumber) {
		this.repairedAssetModelNumber = repairedAssetModelNumber;
	}
	public String getRepairedAssetModelNumber() {
		return repairedAssetModelNumber;
	}
	public void setInstalledAssetId(String installedAssetId) {
		this.installedAssetId = installedAssetId;
	}
	public String getInstalledAssetId() {
		return installedAssetId;
	}
	public void setRepairedAssetId(String repairedAssetId) {
		this.repairedAssetId = repairedAssetId;
	}
	public String getRepairedAssetId() {
		return repairedAssetId;
	}
	public void setMachineTypeModelOverride(String machineTypeModelOverride) {
		this.machineTypeModelOverride = machineTypeModelOverride;
	}
	public String getMachineTypeModelOverride() {
		return machineTypeModelOverride;
	}
	
	protected static Date convertStringToDateWithMarker (String dateString)  {
		Date date =  null;
 		if(dateString != null && !dateString.isEmpty()) {
//			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			try {
				date = formatter.parse(dateString);
			} catch (ParseException e) {
				try {
					date = LangUtil.convertStringToGMTDate(dateString);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return date;
	}
	
	protected static Date convertStringToDateWithOutMarker (String dateString)  {
		return LangUtil.convertStringToGMTDate(dateString);
	}
	public void setActivityDateWithOutMarker(String activityDateWithOutMarker) {
		this.activityDateWithOutMarker = activityDateWithOutMarker;
	}
	public Date getActivityDateWithOutMarker() {
		return convertStringToDateWithOutMarker(activityDateWithOutMarker);
	}
	public void setCustomerRequestedResponseDateWithOutMarker(
			String customerRequestedResponseDateWithOutMarker) {
		this.customerRequestedResponseDateWithOutMarker = customerRequestedResponseDateWithOutMarker;
	}
	public Date getCustomerRequestedResponseDateWithOutMarker() {
		return convertStringToDateWithOutMarker(customerRequestedResponseDateWithOutMarker);
	}
	public void setDebriefStatus(String debriefStatus) {
		this.debriefStatus = debriefStatus;
	}
	public String getDebriefStatus() {
		return debriefStatus;
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
	public String getAssetActivityNumber() {
		return assetActivityNumber;
	}
	public void setAssetActivityNumber(String assetActivityNumber) {
		this.assetActivityNumber = assetActivityNumber;
	}
	public String getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(String statusDetail) {
		this.statusDetail = statusDetail;
	}
	public String getHardwareDebriefAddressName() {
		return hardwareDebriefAddressName;
	}
	public void setHardwareDebriefAddressName(String hardwareDebriefAddressName) {
		this.hardwareDebriefAddressName = hardwareDebriefAddressName;
	}
	public String getPrimarySuppliesContactFirstName() {
		return primarySuppliesContactFirstName;
	}
	public void setPrimarySuppliesContactFirstName(
			String primarySuppliesContactFirstName) {
		this.primarySuppliesContactFirstName = primarySuppliesContactFirstName;
	}
	public String getPrimarySuppliesContactLastName() {
		return primarySuppliesContactLastName;
	}
	public void setPrimarySuppliesContactLastName(
			String primarySuppliesContactLastName) {
		this.primarySuppliesContactLastName = primarySuppliesContactLastName;
	}
	public String getPrimarySuppliesContactId() {
		return primarySuppliesContactId;
	}
	public void setPrimarySuppliesContactId(String primarySuppliesContactId) {
		this.primarySuppliesContactId = primarySuppliesContactId;
	}
	public String getPrimarySuppliesContactWorkPhone() {
		return primarySuppliesContactWorkPhone;
	}
	public void setPrimarySuppliesContactWorkPhone(
			String primarySuppliesContactWorkPhone) {
		this.primarySuppliesContactWorkPhone = primarySuppliesContactWorkPhone;
	}
	public String getPrimarySuppliesContactEmail() {
		return primarySuppliesContactEmail;
	}
	public void setPrimarySuppliesContactEmail(String primarySuppliesContactEmail) {
		this.primarySuppliesContactEmail = primarySuppliesContactEmail;
	}
	public String getPrimarySuppliesContactAlternatePhone() {
		return primarySuppliesContactAlternatePhone;
	}
	public void setPrimarySuppliesContactAlternatePhone(
			String primarySuppliesContactAlternatePhone) {
		this.primarySuppliesContactAlternatePhone = primarySuppliesContactAlternatePhone;
	}
	public Date getCreatedDate() {
		return convertStringToDateWithOutMarker(createdDate);
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getAssetDepartment() {
		return assetDepartment;
	}
	public void setAssetDepartment(String assetDepartment) {
		this.assetDepartment = assetDepartment;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	public String getAssetSubnetMask() {
		return assetSubnetMask;
	}
	public void setAssetSubnetMask(String assetSubnetMask) {
		this.assetSubnetMask = assetSubnetMask;
	}
	public String getAssetMac() {
		return assetMac;
	}
	public void setAssetMac(String assetMac) {
		this.assetMac = assetMac;
	}
	public String getAssetCostCenter() {
		return assetCostCenter;
	}
	public void setAssetCostCenter(String assetCostCenter) {
		this.assetCostCenter = assetCostCenter;
	}
	public String getAssetTopBill() {
		return assetTopBill;
	}
	public void setAssetTopBill(String assetTopBill) {
		this.assetTopBill = assetTopBill;
	}
	public String getIpV6() {
		return ipV6;
	}
	public void setIpV6(String ipV6) {
		this.ipV6 = ipV6;
	}
	public String getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	public String getAssetHostName() {
		return assetHostName;
	}
	public void setAssetHostName(String assetHostName) {
		this.assetHostName = assetHostName;
	}
	public String getAssetDevTypeModelNumber() {
		return assetDevTypeModelNumber;
	}
	public void setAssetDevTypeModelNumber(String assetDevTypeModelNumber) {
		this.assetDevTypeModelNumber = assetDevTypeModelNumber;
	}
	public String getAssetWiringClosestNetworkPoint() {
		return assetWiringClosestNetworkPoint;
	}
	public void setAssetWiringClosestNetworkPoint(
			String assetWiringClosestNetworkPoint) {
		this.assetWiringClosestNetworkPoint = assetWiringClosestNetworkPoint;
	}
	public String getAssetFaxConnected() {
		return assetFaxConnected;
	}
	public void setAssetFaxConnected(String assetFaxConnected) {
		this.assetFaxConnected = assetFaxConnected;
	}
	public String getAssetFaxPortNumber() {
		return assetFaxPortNumber;
	}
	public void setAssetFaxPortNumber(String assetFaxPortNumber) {
		this.assetFaxPortNumber = assetFaxPortNumber;
	}
	public Date getAssetInstallDate() {
		return convertStringToDateWithOutMarker(assetInstallDate);
	}
	public void setAssetInstallDate(String assetInstallDate) {
		this.assetInstallDate = assetInstallDate;
	}
	public String getAssetSpecialUsage() {
		return assetSpecialUsage;
	}
	public void setAssetSpecialUsage(String assetSpecialUsage) {
		this.assetSpecialUsage = assetSpecialUsage;
	}
	public String getAssetOperatingSystem() {
		return assetOperatingSystem;
	}
	public void setAssetOperatingSystem(String assetOperatingSystem) {
		this.assetOperatingSystem = assetOperatingSystem;
	}
	public String getAssetOperatingSystemVersion() {
		return assetOperatingSystemVersion;
	}
	public void setAssetOperatingSystemVersion(String assetOperatingSystemVersion) {
		this.assetOperatingSystemVersion = assetOperatingSystemVersion;
	}
	public String getAssetLifeCycle() {
		return assetLifeCycle;
	}
	public void setAssetLifeCycle(String assetLifeCycle) {
		this.assetLifeCycle = assetLifeCycle;
	}
	public String getAssetFirmware() {
		return assetFirmware;
	}
	public void setAssetFirmware(String assetFirmware) {
		this.assetFirmware = assetFirmware;
	}
	public String getAssetNetworkTopology() {
		return assetNetworkTopology;
	}
	public void setAssetNetworkTopology(String assetNetworkTopology) {
		this.assetNetworkTopology = assetNetworkTopology;
	}
	public String getAssetHierarchyLevel() {
		return assetHierarchyLevel;
	}
	public void setAssetHierarchyLevel(String assetHierarchyLevel) {
		this.assetHierarchyLevel = assetHierarchyLevel;
	}
	public String getAssetInstalledAddressLine1() {
		return assetInstalledAddressLine1;
	}
	public void setAssetInstalledAddressLine1(String assetInstalledAddressLine1) {
		this.assetInstalledAddressLine1 = assetInstalledAddressLine1;
	}
	public String getAssetInstalledAddressLine2() {
		return assetInstalledAddressLine2;
	}
	public void setAssetInstalledAddressLine2(String assetInstalledAddressLine2) {
		this.assetInstalledAddressLine2 = assetInstalledAddressLine2;
	}
	public String getAssetInstalledAddressLine3() {
		return assetInstalledAddressLine3;
	}
	public void setAssetInstalledAddressLine3(String assetInstalledAddressLine3) {
		this.assetInstalledAddressLine3 = assetInstalledAddressLine3;
	}
	public String getAssetInstalledAddressName() {
		return assetInstalledAddressName;
	}
	public void setAssetInstalledAddressName(String assetInstalledAddressName) {
		this.assetInstalledAddressName = assetInstalledAddressName;
	}
	public String getAssetInstalledCity() {
		return assetInstalledCity;
	}
	public void setAssetInstalledCity(String assetInstalledCity) {
		this.assetInstalledCity = assetInstalledCity;
	}
	public String getAssetInstalledCountry() {
		return assetInstalledCountry;
	}
	public void setAssetInstalledCountry(String assetInstalledCountry) {
		this.assetInstalledCountry = assetInstalledCountry;
	}
	public String getAssetInstalledCounty() {
		return assetInstalledCounty;
	}
	public void setAssetInstalledCounty(String assetInstalledCounty) {
		this.assetInstalledCounty = assetInstalledCounty;
	}
	public String getAssetInstalledState() {
		return assetInstalledState;
	}
	public void setAssetInstalledState(String assetInstalledState) {
		this.assetInstalledState = assetInstalledState;
	}
	public String getAssetInstalledZipcode() {
		return assetInstalledZipcode;
	}
	public void setAssetInstalledZipcode(String assetInstalledZipcode) {
		this.assetInstalledZipcode = assetInstalledZipcode;
	}
	public String getAssetInstalledAddressDistrict() {
		return assetInstalledAddressDistrict;
	}
	public void setAssetInstalledAddressDistrict(
			String assetInstalledAddressDistrict) {
		this.assetInstalledAddressDistrict = assetInstalledAddressDistrict;
	}
	public String getAssetInstalledAddressOffice() {
		return assetInstalledAddressOffice;
	}
	public void setAssetInstalledAddressOffice(String assetInstalledAddressOffice) {
		this.assetInstalledAddressOffice = assetInstalledAddressOffice;
	}
	public String getAssetInstalledAddressFloor() {
		return assetInstalledAddressFloor;
	}
	public void setAssetInstalledAddressFloor(String assetInstalledAddressFloor) {
		this.assetInstalledAddressFloor = assetInstalledAddressFloor;
	}
	public String getAssetInstalledAddressBuilding() {
		return assetInstalledAddressBuilding;
	}
	public void setAssetInstalledAddressBuilding(
			String assetInstalledAddressBuilding) {
		this.assetInstalledAddressBuilding = assetInstalledAddressBuilding;
	}
	public String getAssetInstalledProvince() {
		return assetInstalledProvince;
	}
	public void setAssetInstalledProvince(String assetInstalledProvince) {
		this.assetInstalledProvince = assetInstalledProvince;
	}
	public String getPrimaryContactAlternateWorkPhone() {
		return primaryContactAlternateWorkPhone;
	}
	public void setPrimaryContactAlternateWorkPhone(
			String primaryContactAlternateWorkPhone) {
		this.primaryContactAlternateWorkPhone = primaryContactAlternateWorkPhone;
	}
	public String getSecondaryContactAlternateWorkPhone() {
		return secondaryContactAlternateWorkPhone;
	}
	public void setSecondaryContactAlternateWorkPhone(
			String secondaryContactAlternateWorkPhone) {
		this.secondaryContactAlternateWorkPhone = secondaryContactAlternateWorkPhone;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getProjectStartDate() {
		return convertStringToDateWithMarker(projectStartDate);
	}
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getStoreFrontName() {
		return storeFrontName;
	}
	public void setStoreFrontName(String storeFrontName) {
		this.storeFrontName = storeFrontName;
	}
	public String getAssetDeviceName() {
		return assetDeviceName;
	}
	public void setAssetDeviceName(String assetDeviceName) {
		this.assetDeviceName = assetDeviceName;
	}
	public String getAssetProductImageURL() {
		return assetProductImageURL;
	}
	public void setAssetProductImageURL(String assetProductImageURL) {
		this.assetProductImageURL = assetProductImageURL;
	}
	public String getSecondarySuppliesContactFirstName() {
		return secondarySuppliesContactFirstName;
	}
	public void setSecondarySuppliesContactFirstName(
			String secondarySuppliesContactFirstName) {
		this.secondarySuppliesContactFirstName = secondarySuppliesContactFirstName;
	}
	public String getSecondarySuppliesContactLastName() {
		return secondarySuppliesContactLastName;
	}
	public void setSecondarySuppliesContactLastName(
			String secondarySuppliesContactLastName) {
		this.secondarySuppliesContactLastName = secondarySuppliesContactLastName;
	}
	public String getSecondaryAddressId() {
		return secondaryAddressId;
	}
	public void setSecondaryAddressId(String secondaryAddressId) {
		this.secondaryAddressId = secondaryAddressId;
	}
	public String getSecondaryAddressLine1() {
		return secondaryAddressLine1;
	}
	public void setSecondaryAddressLine1(String secondaryAddressLine1) {
		this.secondaryAddressLine1 = secondaryAddressLine1;
	}
	public String getSecondaryAddressName() {
		return secondaryAddressName;
	}
	public void setSecondaryAddressName(String secondaryAddressName) {
		this.secondaryAddressName = secondaryAddressName;
	}
	public String getSecondaryAddressLine2() {
		return secondaryAddressLine2;
	}
	public void setSecondaryAddressLine2(String secondaryAddressLine2) {
		this.secondaryAddressLine2 = secondaryAddressLine2;
	}
	public String getSecondaryAddressLine3() {
		return secondaryAddressLine3;
	}
	public void setSecondaryAddressLine3(String secondaryAddressLine3) {
		this.secondaryAddressLine3 = secondaryAddressLine3;
	}
	public String getSecondaryAddressCity() {
		return secondaryAddressCity;
	}
	public void setSecondaryAddressCity(String secondaryAddressCity) {
		this.secondaryAddressCity = secondaryAddressCity;
	}
	public String getSecondaryAddressState() {
		return secondaryAddressState;
	}
	public void setSecondaryAddressState(String secondaryAddressState) {
		this.secondaryAddressState = secondaryAddressState;
	}
	public String getSecondaryAddressCountry() {
		return secondaryAddressCountry;
	}
	public void setSecondaryAddressCountry(String secondaryAddressCountry) {
		this.secondaryAddressCountry = secondaryAddressCountry;
	}
	public String getSecondaryAddressProvince() {
		return secondaryAddressProvince;
	}
	public void setSecondaryAddressProvince(String secondaryAddressProvince) {
		this.secondaryAddressProvince = secondaryAddressProvince;
	}
	public String getSecondaryAddressPostalCode() {
		return secondaryAddressPostalCode;
	}
	public void setSecondaryAddressPostalCode(String secondaryAddressPostalCode) {
		this.secondaryAddressPostalCode = secondaryAddressPostalCode;
	}
	public String getProjectPhase() {
		return projectPhase;
	}
	public void setProjectPhase(String projectPhase) {
		this.projectPhase = projectPhase;
	}
	public String getCustomerReportingName() {
		return customerReportingName;
	}
	public void setCustomerReportingName(String customerReportingName) {
		this.customerReportingName = customerReportingName;
	}
	public String getAssetIPGateway() {
		return assetIPGateway;
	}
	public void setAssetIPGateway(String assetIPGateway) {
		this.assetIPGateway = assetIPGateway;
	}
	public Date getExpectedStartDate() {
		return convertStringToDateWithMarker(expectedStartDate);
	}
	public void setExpectedStartDate(String expectedStartDate) {
		this.expectedStartDate = expectedStartDate;
	}
	public Date getExpectedCompletionDate() {
		return expectedCompletionDate;
	}
	public void setExpectedCompletionDate(Date expectedCompletionDate) {
		this.expectedCompletionDate = expectedCompletionDate;
	}
	public Date getServiceProviderETA() {
		return convertStringToDateWithMarker(serviceProviderETA);
	}
	public void setServiceProviderETA(String serviceProviderETA) {
		this.serviceProviderETA = serviceProviderETA;
	}
	public String getProductModelNumber() {
		return productModelNumber;
	}
	public void setProductModelNumber(String productModelNumber) {
		this.productModelNumber = productModelNumber;
	}
	public String getMoveToAddressGrouped() {
		return moveToAddressGrouped;
	}
	public void setMoveToAddressGrouped(String moveToAddressGrouped) {
		this.moveToAddressGrouped = moveToAddressGrouped;
	}
	public String getRfvErrors() {
		return rfvErrors;
	}
	public void setRfvErrors(String rfvErrors) {
		this.rfvErrors = rfvErrors;
	}
	public Date getCommittedDate() {
		return convertStringToDateWithMarker(committedDate);
	}
	public void setCommittedDate(String committedDate) {
		this.committedDate = committedDate;
	}
	public String getTechnicianName() {
		return technicianName;
	}
	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
	}
	public String getAssetField1() {
		return assetField1;
	}
	public void setAssetField1(String assetField1) {
		this.assetField1 = assetField1;
	}
	public String getAssetField2() {
		return assetField2;
	}
	public void setAssetField2(String assetField2) {
		this.assetField2 = assetField2;
	}
	public String getAssetField3() {
		return assetField3;
	}
	public void setAssetField3(String assetField3) {
		this.assetField3 = assetField3;
	}
	
	//added for CR 12032
	public boolean getMassUploadNetworkConnected() {
		return massUploadNetworkConnected;
	}
	public void setMassUploadNetworkConnected(boolean massUploadNetworkConnected) {
		this.massUploadNetworkConnected = massUploadNetworkConnected;
	}
	public String getMassUploadIPAddress() {
		return massUploadIPAddress;
	}
	public void setMassUploadIPAddress(String massUploadIPAddress) {
		this.massUploadIPAddress = massUploadIPAddress;
	}
	public String getMassUploadIPGateway() {
		return massUploadIPGateway;
	}
	public void setMassUploadIPGateway(String massUploadIPGateway) {
		this.massUploadIPGateway = massUploadIPGateway;
	}
	public String getMassUploadIPSubmask() {
		return massUploadIPSubmask;
	}
	public void setMassUploadIPSubmask(String massUploadIPSubmask) {
		this.massUploadIPSubmask = massUploadIPSubmask;
	}
	public String getMassUploadIPv6() {
		return massUploadIPv6;
	}
	public void setMassUploadIPv6(String massUploadIPv6) {
		this.massUploadIPv6 = massUploadIPv6;
	}
	public String getMassUploadAssetMacAddress() {
		return massUploadAssetMacAddress;
	}
	public void setMassUploadAssetMacAddress(String massUploadAssetMacAddress) {
		this.massUploadAssetMacAddress = massUploadAssetMacAddress;
	}
	public String getMassUploadAssetDeviceCondition() {
		return massUploadAssetDeviceCondition;
	}
	public void setMassUploadAssetDeviceCondition(
			String massUploadAssetDeviceCondition) {
		this.massUploadAssetDeviceCondition = massUploadAssetDeviceCondition;
	}
	public boolean getMassUploadFaxConnected() {
		return massUploadFaxConnected;
	}
	public void setMassUploadFaxConnected(boolean massUploadFaxConnected) {
		this.massUploadFaxConnected = massUploadFaxConnected;
	}
	public String getMassUploadFaxPortNumber() {
		return massUploadFaxPortNumber;
	}
	public void setMassUploadFaxPortNumber(String massUploadFaxPortNumber) {
		this.massUploadFaxPortNumber = massUploadFaxPortNumber;
	}
	public String getMassUploadHostName() {
		return massUploadHostName;
	}
	public void setMassUploadHostName(String massUploadHostName) {
		this.massUploadHostName = massUploadHostName;
	}
	public String getMassUploadComputerName() {
		return massUploadComputerName;
	}
	public void setMassUploadComputerName(String massUploadComputerName) {
		this.massUploadComputerName = massUploadComputerName;
	}
	public String getMassUploadPortNumber() {
		return massUploadPortNumber;
	}
	public void setMassUploadPortNumber(String massUploadPortNumber) {
		this.massUploadPortNumber = massUploadPortNumber;
	}
	public String getMassUploadWiringClosetNetwkPt() {
		return massUploadWiringClosetNetwkPt;
	}
	public void setMassUploadWiringClosetNetwkPt(
			String massUploadWiringClosetNetwkPt) {
		this.massUploadWiringClosetNetwkPt = massUploadWiringClosetNetwkPt;
	}
	public String getMassUploadAccountName() {
		return massUploadAccountName;
	}
	public void setMassUploadAccountName(String massUploadAccountName) {
		this.massUploadAccountName = massUploadAccountName;
	}
	public String getMassUploadAssetCostCenter() {
		return massUploadAssetCostCenter;
	}
	public void setMassUploadAssetCostCenter(String massUploadAssetCostCenter) {
		this.massUploadAssetCostCenter = massUploadAssetCostCenter;
	}
	public String getMassUploadCustomerDeviceTag() {
		return massUploadCustomerDeviceTag;
	}
	public void setMassUploadCustomerDeviceTag(String massUploadCustomerDeviceTag) {
		this.massUploadCustomerDeviceTag = massUploadCustomerDeviceTag;
	}
	public String getMassUploadDeptId() {
		return massUploadDeptId;
	}
	public void setMassUploadDeptId(String massUploadDeptId) {
		this.massUploadDeptId = massUploadDeptId;
	}
	public String getMassUploadDeptName() {
		return massUploadDeptName;
	}
	public void setMassUploadDeptName(String massUploadDeptName) {
		this.massUploadDeptName = massUploadDeptName;
	}
	public String getMassUploadFirmware() {
		return massUploadFirmware;
	}
	public void setMassUploadFirmware(String massUploadFirmware) {
		this.massUploadFirmware = massUploadFirmware;
	}
	public String getMassUploadNetworkTopology() {
		return massUploadNetworkTopology;
	}
	public void setMassUploadNetworkTopology(String massUploadNetworkTopology) {
		this.massUploadNetworkTopology = massUploadNetworkTopology;
	}
	public String getMassUploadOS() {
		return massUploadOS;
	}
	public void setMassUploadOS(String massUploadOS) {
		this.massUploadOS = massUploadOS;
	}
	public String getMassUploadOSVersion() {
		return massUploadOSVersion;
	}
	public void setMassUploadOSVersion(String massUploadOSVersion) {
		this.massUploadOSVersion = massUploadOSVersion;
	}
	public String getMassUploadTopBill() {
		return massUploadTopBill;
	}
	public void setMassUploadTopBill(String massUploadTopBill) {
		this.massUploadTopBill = massUploadTopBill;
	}
	public String getMassUploadSpecialUsage() {
		return massUploadSpecialUsage;
	}
	public void setMassUploadSpecialUsage(String massUploadSpecialUsage) {
		this.massUploadSpecialUsage = massUploadSpecialUsage;
	}
	public String getMassUploadAssetDesc() {
		return massUploadAssetDesc;
	}
	public void setMassUploadAssetDesc(String massUploadAssetDesc) {
		this.massUploadAssetDesc = massUploadAssetDesc;
	}
	public String getMassUploadContactLastName() {
		return massUploadContactLastName;
	}
	public void setMassUploadContactLastName(String massUploadContactLastName) {
		this.massUploadContactLastName = massUploadContactLastName;
	}
	public String getMassUploadContactFirstName() {
		return massUploadContactFirstName;
	}
	public void setMassUploadContactFirstName(String massUploadContactFirstName) {
		this.massUploadContactFirstName = massUploadContactFirstName;
	}
	public String getMassUploadContactWorkPhoneNum() {
		return massUploadContactWorkPhoneNum;
	}
	public void setMassUploadContactWorkPhoneNum(
			String massUploadContactWorkPhoneNum) {
		this.massUploadContactWorkPhoneNum = massUploadContactWorkPhoneNum;
	}
	public String getMassUploadContactHomePhone() {
		return massUploadContactHomePhone;
	}
	public void setMassUploadContactHomePhone(String massUploadContactHomePhone) {
		this.massUploadContactHomePhone = massUploadContactHomePhone;
	}
	public String getMassUploadContactEmailAddress() {
		return massUploadContactEmailAddress;
	}
	public void setMassUploadContactEmailAddress(
			String massUploadContactEmailAddress) {
		this.massUploadContactEmailAddress = massUploadContactEmailAddress;
	}
	public String getMassUploadContactJobRole() {
		return massUploadContactJobRole;
	}
	public void setMassUploadContactJobRole(String massUploadContactJobRole) {
		this.massUploadContactJobRole = massUploadContactJobRole;
	}
	public String getMassUploadUploaderCountry() {
		return massUploadUploaderCountry;
	}
	public void setMassUploadUploaderCountry(String massUploadUploaderCountry) {
		this.massUploadUploaderCountry = massUploadUploaderCountry;
	}
	public String getMassUploadInstalledAssetAddressName() {
		return massUploadInstalledAssetAddressName;
	}
	public void setMassUploadInstalledAssetAddressName(
			String massUploadInstalledAssetAddressName) {
		this.massUploadInstalledAssetAddressName = massUploadInstalledAssetAddressName;
	}
	public String getMassUploadInstalledAddressLine1() {
		return massUploadInstalledAddressLine1;
	}
	public void setMassUploadInstalledAddressLine1(
			String massUploadInstalledAddressLine1) {
		this.massUploadInstalledAddressLine1 = massUploadInstalledAddressLine1;
	}
	public String getMassUploadInstalledAddressLine2() {
		return massUploadInstalledAddressLine2;
	}
	public void setMassUploadInstalledAddressLine2(
			String massUploadInstalledAddressLine2) {
		this.massUploadInstalledAddressLine2 = massUploadInstalledAddressLine2;
	}
	public String getMassUploadInstalledCity() {
		return massUploadInstalledCity;
	}
	public void setMassUploadInstalledCity(String massUploadInstalledCity) {
		this.massUploadInstalledCity = massUploadInstalledCity;
	}
	public String getMassUploadInstalledCountry() {
		return massUploadInstalledCountry;
	}
	public void setMassUploadInstalledCountry(String massUploadInstalledCountry) {
		this.massUploadInstalledCountry = massUploadInstalledCountry;
	}
	public String getMassUploadInstalledState() {
		return massUploadInstalledState;
	}
	public void setMassUploadInstalledState(String massUploadInstalledState) {
		this.massUploadInstalledState = massUploadInstalledState;
	}
	public String getMassUploadInstalledProvince() {
		return massUploadInstalledProvince;
	}
	public void setMassUploadInstalledProvince(String massUploadInstalledProvince) {
		this.massUploadInstalledProvince = massUploadInstalledProvince;
	}
	public String getMassUploadInstalledPostalCode() {
		return massUploadInstalledPostalCode;
	}
	public void setMassUploadInstalledPostalCode(
			String massUploadInstalledPostalCode) {
		this.massUploadInstalledPostalCode = massUploadInstalledPostalCode;
	}
	public String getMassUploadInstalledCounty() {
		return massUploadInstalledCounty;
	}
	public void setMassUploadInstalledCounty(String massUploadInstalledCounty) {
		this.massUploadInstalledCounty = massUploadInstalledCounty;
	}
	public String getMassUploadInstalledAssetPhyLoc1() {
		return massUploadInstalledAssetPhyLoc1;
	}
	public void setMassUploadInstalledAssetPhyLoc1(
			String massUploadInstalledAssetPhyLoc1) {
		this.massUploadInstalledAssetPhyLoc1 = massUploadInstalledAssetPhyLoc1;
	}
	public String getMassUploadInstalledAssetPhyLoc2() {
		return massUploadInstalledAssetPhyLoc2;
	}
	public void setMassUploadInstalledAssetPhyLoc2(
			String massUploadInstalledAssetPhyLoc2) {
		this.massUploadInstalledAssetPhyLoc2 = massUploadInstalledAssetPhyLoc2;
	}
	public String getMassUploadInstalledAssetPhyLoc3() {
		return massUploadInstalledAssetPhyLoc3;
	}
	public void setMassUploadInstalledAssetPhyLoc3(
			String massUploadInstalledAssetPhyLoc3) {
		this.massUploadInstalledAssetPhyLoc3 = massUploadInstalledAssetPhyLoc3;
	}
	public String getMassUploadInstalledAddressDistrict() {
		return massUploadInstalledAddressDistrict;
	}
	public void setMassUploadInstalledAddressDistrict(
			String massUploadInstalledAddressDistrict) {
		this.massUploadInstalledAddressDistrict = massUploadInstalledAddressDistrict;
	}
	public String getMassUploadInstalledAddressOfficeNum() {
		return massUploadInstalledAddressOfficeNum;
	}
	public void setMassUploadInstalledAddressOfficeNum(
			String massUploadInstalledAddressOfficeNum) {
		this.massUploadInstalledAddressOfficeNum = massUploadInstalledAddressOfficeNum;
	}
	
	public String getMoveFromMadcAddressID() {
		return moveFromMadcAddressID;
	}
	public void setMoveFromMadcAddressID(String moveFromMadcAddressID) {
		this.moveFromMadcAddressID = moveFromMadcAddressID;
	}
	public String getMoveFromMadcAddressLine1() {
		return moveFromMadcAddressLine1;
	}
	public void setMoveFromMadcAddressLine1(String moveFromMadcAddressLine1) {
		this.moveFromMadcAddressLine1 = moveFromMadcAddressLine1;
	}
	public String getMoveFromMadcAddressLine2() {
		return moveFromMadcAddressLine2;
	}
	public void setMoveFromMadcAddressLine2(String moveFromMadcAddressLine2) {
		this.moveFromMadcAddressLine2 = moveFromMadcAddressLine2;
	}
	public String getMoveFromMadcCity() {
		return moveFromMadcCity;
	}
	public void setMoveFromMadcCity(String moveFromMadcCity) {
		this.moveFromMadcCity = moveFromMadcCity;
	}
	public String getMoveFromMadcCountry() {
		return moveFromMadcCountry;
	}
	public void setMoveFromMadcCountry(String moveFromMadcCountry) {
		this.moveFromMadcCountry = moveFromMadcCountry;
	}
	public String getMoveFromMadcISOCountryCode() {
		return moveFromMadcISOCountryCode;
	}
	public void setMoveFromMadcISOCountryCode(String moveFromMadcISOCountryCode) {
		this.moveFromMadcISOCountryCode = moveFromMadcISOCountryCode;
	}
	public String getMoveFromMadcCounty() {
		return moveFromMadcCounty;
	}
	public void setMoveFromMadcCounty(String moveFromMadcCounty) {
		this.moveFromMadcCounty = moveFromMadcCounty;
	}
	public String getMoveFromMadcDistrict() {
		return moveFromMadcDistrict;
	}
	public void setMoveFromMadcDistrict(String moveFromMadcDistrict) {
		this.moveFromMadcDistrict = moveFromMadcDistrict;
	}
	public String getMoveFromMadcState() {
		return moveFromMadcState;
	}
	public void setMoveFromMadcState(String moveFromMadcState) {
		this.moveFromMadcState = moveFromMadcState;
	}
	public String getMoveFromMadcStateFullName() {
		return moveFromMadcStateFullName;
	}
	public void setMoveFromMadcStateFullName(String moveFromMadcStateFullName) {
		this.moveFromMadcStateFullName = moveFromMadcStateFullName;
	}
	public String getMoveFromMadcHouseNum() {
		return moveFromMadcHouseNum;
	}
	public void setMoveFromMadcHouseNum(String moveFromMadcHouseNum) {
		this.moveFromMadcHouseNum = moveFromMadcHouseNum;
	}
	public String getMoveFromMadcFirstLogicErrorMessage() {
		return moveFromMadcFirstLogicErrorMessage;
	}
	public void setMoveFromMadcFirstLogicErrorMessage(
			String moveFromMadcFirstLogicErrorMessage) {
		this.moveFromMadcFirstLogicErrorMessage = moveFromMadcFirstLogicErrorMessage;
	}
	public String getMoveFromMadcLatitude() {
		return moveFromMadcLatitude;
	}
	public void setMoveFromMadcLatitude(String moveFromMadcLatitude) {
		this.moveFromMadcLatitude = moveFromMadcLatitude;
	}
	public String getMoveFromMadcLongitude() {
		return moveFromMadcLongitude;
	}
	public void setMoveFromMadcLongitude(String moveFromMadcLongitude) {
		this.moveFromMadcLongitude = moveFromMadcLongitude;
	}
	public String getMoveFromMadcRegion() {
		return moveFromMadcRegion;
	}
	public void setMoveFromMadcRegion(String moveFromMadcRegion) {
		this.moveFromMadcRegion = moveFromMadcRegion;
	}
	public String getMoveFromMadcPostalCode() {
		return moveFromMadcPostalCode;
	}
	public void setMoveFromMadcPostalCode(String moveFromMadcPostalCode) {
		this.moveFromMadcPostalCode = moveFromMadcPostalCode;
	}
	public String getMoveFromMadcProvince() {
		return moveFromMadcProvince;
	}
	public void setMoveFromMadcProvince(String moveFromMadcProvince) {
		this.moveFromMadcProvince = moveFromMadcProvince;
	}
	public String getMoveFromMadcBuilding() {
		return moveFromMadcBuilding;
	}
	public void setMoveFromMadcBuilding(String moveFromMadcBuilding) {
		this.moveFromMadcBuilding = moveFromMadcBuilding;
	}
	public String getMoveFromMadcFloor() {
		return moveFromMadcFloor;
	}
	public void setMoveFromMadcFloor(String moveFromMadcFloor) {
		this.moveFromMadcFloor = moveFromMadcFloor;
	}
	public String getMoveFromMadcOffice() {
		return moveFromMadcOffice;
	}
	public void setMoveFromMadcOffice(String moveFromMadcOffice) {
		this.moveFromMadcOffice = moveFromMadcOffice;
	}
	public String getAgencyContactFirstName() {
		return agencyContactFirstName;
	}
	public void setAgencyContactFirstName(String agencyContactFirstName) {
		this.agencyContactFirstName = agencyContactFirstName;
	}
	public String getAgencyContactLastName() {
		return agencyContactLastName;
	}
	public void setAgencyContactLastName(String agencyContactLastName) {
		this.agencyContactLastName = agencyContactLastName;
	}
	public String getAgencyContacteMail() {
		return agencyContacteMail;
	}
	public void setAgencyContacteMail(String agencyContacteMail) {
		this.agencyContacteMail = agencyContacteMail;
	}
	public ArrayList<ServiceRequestActivityAttachmentsDo> getServiceRequestAttachmentss() {
		return serviceRequestAttachmentss;
	}
	public void setServiceRequestAttachmentss(
			ArrayList<ServiceRequestActivityAttachmentsDo> serviceRequestAttachmentss) {
		this.serviceRequestAttachmentss = serviceRequestAttachmentss;
	}
	public Boolean getLbsAddressFlag() {
		return lbsAddressFlag;
	}
	public void setLbsAddressFlag(Boolean lbsAddressFlag) {
		this.lbsAddressFlag = lbsAddressFlag;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getCampusName() {
		return campusName;
	}
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
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
	public String getDeinstDeviceCondition() {
		return deinstDeviceCondition;
	}
	public void setDeinstDeviceCondition(String deinstDeviceCondition) {
		this.deinstDeviceCondition = deinstDeviceCondition;
	}
}
