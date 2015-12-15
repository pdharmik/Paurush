package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DownloadRequest implements Serializable  {

	private static final long serialVersionUID = 669551774137404089L;

	private String actionNarrative;
	private String actionSubStatus;
	private String activityId;	
	private String actualFailureCode;
	private Date actualEnd;
	private Date actualStart;
	private String addlPaymentDesc1;
	private String addlPaymentDesc2;
	private String addlPaymentQty1;
	private String addlPaymentQty2;
	private String addlPaymentType1;
	private String addlPaymentType2;
	private String addlPaymentUnitPrice1;
	private String addlPaymentUnitPrice2;
	private String addlServiceReq;
	private String comments;
	private Date customerRequestResponse;
	private Date estTimeArrival;
	private String installedDeviceCondition;
	private String installedIPAddress;
	private String installedMacAddress;
	private String networkConnected;
	private String newTechFirstName;
	private String newTechLastName;
	private String nonLexmarkSuppliesUsed;
	private String repairCompleteFlag;
	private String resolutionCode;
	private String sPReferenceNum;
	private Date statusAsOf;
	private String supplyManufacturer;
	private String technician;
	private String travelDistance;
	private String travelDistanceUM;
	private String travelDurationMin;
	private String deInstalledAssetTag;
	private String deInstalledPageCount;
	private String installedAssetTag;
	private String installedPageCount;
	
	private List<DownloadRequestPart> downloadRequestPart;
	private String debriefStatus;
	private String activitySubStatus ;
	private String srNum;


	/**
	 * @return the srNum
	 */
	public String getSrNum() {
		return srNum;
	}

	/**
	 * @param srNum the srNum to set
	 */
	public void setSrNum(String srNum) {
		this.srNum = srNum;
	}

	/**
	 * @return the debriefStatus
	 */
	public String getDebriefStatus() {
		return debriefStatus;
	}

	/**
	 * @param debriefStatus the debriefStatus to set
	 */
	public void setDebriefStatus(String debriefStatus) {
		this.debriefStatus = debriefStatus;
	}

	/**
	 * @return the activitySubStatus
	 */
	public String getActivitySubStatus() {
		return activitySubStatus;
	}

	/**
	 * @param activitySubStatus the activitySubStatus to set
	 */
	public void setActivitySubStatus(String activitySubStatus) {
		this.activitySubStatus = activitySubStatus;
	}

	/**
	 * @return the actionNarrative
	 */
	public String getActionNarrative() {
		return actionNarrative;
	}

	/**
	 * @param actionNarrative the actionNarrative to set
	 */
	public void setActionNarrative(String actionNarrative) {
		this.actionNarrative = actionNarrative;
	}

	/**
	 * @return the actionSubStatus
	 */
	public String getActionSubStatus() {
		return actionSubStatus;
	}

	/**
	 * @param actionSubStatus the actionSubStatus to set
	 */
	public void setActionSubStatus(String actionSubStatus) {
		this.actionSubStatus = actionSubStatus;
	}

	/**
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * @return the actualFailureCode
	 */
	public String getActualFailureCode() {
		return actualFailureCode;
	}

	/**
	 * @param actualFailureCode the actualFailureCode to set
	 */
	public void setActualFailureCode(String actualFailureCode) {
		this.actualFailureCode = actualFailureCode;
	}

	/**
	 * @return the actualEnd
	 */
	public Date getActualEnd() {
		return actualEnd;
	}

	/**
	 * @param actualEnd the actualEnd to set
	 */
	public void setActualEnd(Date actualEnd) {
		this.actualEnd = actualEnd;
	}

	/**
	 * @return the actualStart
	 */
	public Date getActualStart() {
		return actualStart;
	}

	/**
	 * @param actualStart the actualStart to set
	 */
	public void setActualStart(Date actualStart) {
		this.actualStart = actualStart;
	}

	/**
	 * @return the addlPaymentDesc1
	 */
	public String getAddlPaymentDesc1() {
		return addlPaymentDesc1;
	}

	/**
	 * @param addlPaymentDesc1 the addlPaymentDesc1 to set
	 */
	public void setAddlPaymentDesc1(String addlPaymentDesc1) {
		this.addlPaymentDesc1 = addlPaymentDesc1;
	}

	/**
	 * @return the addlPaymentDesc2
	 */
	public String getAddlPaymentDesc2() {
		return addlPaymentDesc2;
	}

	/**
	 * @param addlPaymentDesc2 the addlPaymentDesc2 to set
	 */
	public void setAddlPaymentDesc2(String addlPaymentDesc2) {
		this.addlPaymentDesc2 = addlPaymentDesc2;
	}

	/**
	 * @return the addlPaymentQty1
	 */
	public String getAddlPaymentQty1() {
		return addlPaymentQty1;
	}

	/**
	 * @param addlPaymentQty1 the addlPaymentQty1 to set
	 */
	public void setAddlPaymentQty1(String addlPaymentQty1) {
		this.addlPaymentQty1 = addlPaymentQty1;
	}

	/**
	 * @return the addlPaymentQty2
	 */
	public String getAddlPaymentQty2() {
		return addlPaymentQty2;
	}

	/**
	 * @param addlPaymentQty2 the addlPaymentQty2 to set
	 */
	public void setAddlPaymentQty2(String addlPaymentQty2) {
		this.addlPaymentQty2 = addlPaymentQty2;
	}

	/**
	 * @return the addlPaymentType1
	 */
	public String getAddlPaymentType1() {
		return addlPaymentType1;
	}

	/**
	 * @param addlPaymentType1 the addlPaymentType1 to set
	 */
	public void setAddlPaymentType1(String addlPaymentType1) {
		this.addlPaymentType1 = addlPaymentType1;
	}

	/**
	 * @return the addlPaymentType2
	 */
	public String getAddlPaymentType2() {
		return addlPaymentType2;
	}

	/**
	 * @param addlPaymentType2 the addlPaymentType2 to set
	 */
	public void setAddlPaymentType2(String addlPaymentType2) {
		this.addlPaymentType2 = addlPaymentType2;
	}

	/**
	 * @return the addlPaymentUnitPrice1
	 */
	public String getAddlPaymentUnitPrice1() {
		return addlPaymentUnitPrice1;
	}

	/**
	 * @param addlPaymentUnitPrice1 the addlPaymentUnitPrice1 to set
	 */
	public void setAddlPaymentUnitPrice1(String addlPaymentUnitPrice1) {
		this.addlPaymentUnitPrice1 = addlPaymentUnitPrice1;
	}

	/**
	 * @return the addlPaymentUnitPrice2
	 */
	public String getAddlPaymentUnitPrice2() {
		return addlPaymentUnitPrice2;
	}

	/**
	 * @param addlPaymentUnitPrice2 the addlPaymentUnitPrice2 to set
	 */
	public void setAddlPaymentUnitPrice2(String addlPaymentUnitPrice2) {
		this.addlPaymentUnitPrice2 = addlPaymentUnitPrice2;
	}

	/**
	 * @return the addlServiceReq
	 */
	public String getAddlServiceReq() {
		return addlServiceReq;
	}

	/**
	 * @param addlServiceReq the addlServiceReq to set
	 */
	public void setAddlServiceReq(String addlServiceReq) {
		this.addlServiceReq = addlServiceReq;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the customerRequestResponse
	 */
	public Date getCustomerRequestResponse() {
		return customerRequestResponse;
	}

	/**
	 * @param customerRequestResponse the customerRequestResponse to set
	 */
	public void setCustomerRequestResponse(Date customerRequestResponse) {
		this.customerRequestResponse = customerRequestResponse;
	}

	/**
	 * @return the estTimeArrival
	 */
	public Date getEstTimeArrival() {
		return estTimeArrival;
	}

	/**
	 * @param estTimeArrival the estTimeArrival to set
	 */
	public void setEstTimeArrival(Date estTimeArrival) {
		this.estTimeArrival = estTimeArrival;
	}

	/**
	 * @return the installedDeviceCondition
	 */
	public String getInstalledDeviceCondition() {
		return installedDeviceCondition;
	}

	/**
	 * @param installedDeviceCondition the installedDeviceCondition to set
	 */
	public void setInstalledDeviceCondition(String installedDeviceCondition) {
		this.installedDeviceCondition = installedDeviceCondition;
	}

	/**
	 * @return the installedIPAddress
	 */
	public String getInstalledIPAddress() {
		return installedIPAddress;
	}

	/**
	 * @param installedIPAddress the installedIPAddress to set
	 */
	public void setInstalledIPAddress(String installedIPAddress) {
		this.installedIPAddress = installedIPAddress;
	}

	/**
	 * @return the installedMacAddress
	 */
	public String getInstalledMacAddress() {
		return installedMacAddress;
	}

	/**
	 * @param installedMacAddress the installedMacAddress to set
	 */
	public void setInstalledMacAddress(String installedMacAddress) {
		this.installedMacAddress = installedMacAddress;
	}

	/**
	 * @return the networkConnected
	 */
	public String getNetworkConnected() {
		return networkConnected;
	}

	/**
	 * @param networkConnected the networkConnected to set
	 */
	public void setNetworkConnected(String networkConnected) {
		this.networkConnected = networkConnected;
	}

	/**
	 * @return the newTechFirstName
	 */
	public String getNewTechFirstName() {
		return newTechFirstName;
	}

	/**
	 * @param newTechFirstName the newTechFirstName to set
	 */
	public void setNewTechFirstName(String newTechFirstName) {
		this.newTechFirstName = newTechFirstName;
	}

	/**
	 * @return the newTechLastName
	 */
	public String getNewTechLastName() {
		return newTechLastName;
	}

	/**
	 * @param newTechLastName the newTechLastName to set
	 */
	public void setNewTechLastName(String newTechLastName) {
		this.newTechLastName = newTechLastName;
	}

	/**
	 * @return the nonLexmarkSuppliesUsed
	 */
	public String getNonLexmarkSuppliesUsed() {
		return nonLexmarkSuppliesUsed;
	}

	/**
	 * @param nonLexmarkSuppliesUsed the nonLexmarkSuppliesUsed to set
	 */
	public void setNonLexmarkSuppliesUsed(String nonLexmarkSuppliesUsed) {
		this.nonLexmarkSuppliesUsed = nonLexmarkSuppliesUsed;
	}

	/**
	 * @return the repairCompleteFlag
	 */
	public String getRepairCompleteFlag() {
		return repairCompleteFlag;
	}

	/**
	 * @param repairCompleteFlag the repairCompleteFlag to set
	 */
	public void setRepairCompleteFlag(String repairCompleteFlag) {
		this.repairCompleteFlag = repairCompleteFlag;
	}

	/**
	 * @return the resolutionCode
	 */
	public String getResolutionCode() {
		return resolutionCode;
	}

	/**
	 * @param resolutionCode the resolutionCode to set
	 */
	public void setResolutionCode(String resolutionCode) {
		this.resolutionCode = resolutionCode;
	}

	/**
	 * @return the sPReferenceNum
	 */
	public String getsPReferenceNum() {
		return sPReferenceNum;
	}

	/**
	 * @param sPReferenceNum the sPReferenceNum to set
	 */
	public void setsPReferenceNum(String sPReferenceNum) {
		this.sPReferenceNum = sPReferenceNum;
	}

	/**
	 * @return the statusAsOf
	 */
	public Date getStatusAsOf() {
		return statusAsOf;
	}

	/**
	 * @param statusAsOf the statusAsOf to set
	 */
	public void setStatusAsOf(Date statusAsOf) {
		this.statusAsOf = statusAsOf;
	}

	/**
	 * @return the supplyManufacturer
	 */
	public String getSupplyManufacturer() {
		return supplyManufacturer;
	}

	/**
	 * @param supplyManufacturer the supplyManufacturer to set
	 */
	public void setSupplyManufacturer(String supplyManufacturer) {
		this.supplyManufacturer = supplyManufacturer;
	}

	/**
	 * @return the technician
	 */
	public String getTechnician() {
		return technician;
	}

	/**
	 * @param technician the technician to set
	 */
	public void setTechnician(String technician) {
		this.technician = technician;
	}

	/**
	 * @return the travelDistance
	 */
	public String getTravelDistance() {
		return travelDistance;
	}

	/**
	 * @param travelDistance the travelDistance to set
	 */
	public void setTravelDistance(String travelDistance) {
		this.travelDistance = travelDistance;
	}

	/**
	 * @return the travelDistanceUM
	 */
	public String getTravelDistanceUM() {
		return travelDistanceUM;
	}

	/**
	 * @param travelDistanceUM the travelDistanceUM to set
	 */
	public void setTravelDistanceUM(String travelDistanceUM) {
		this.travelDistanceUM = travelDistanceUM;
	}

	/**
	 * @return the travelDurationMin
	 */
	public String getTravelDurationMin() {
		return travelDurationMin;
	}

	/**
	 * @param travelDurationMin the travelDurationMin to set
	 */
	public void setTravelDurationMin(String travelDurationMin) {
		this.travelDurationMin = travelDurationMin;
	}

	/**
	 * @return the deInstalledAssetTag
	 */
	public String getDeInstalledAssetTag() {
		return deInstalledAssetTag;
	}

	/**
	 * @param deInstalledAssetTag the deInstalledAssetTag to set
	 */
	public void setDeInstalledAssetTag(String deInstalledAssetTag) {
		this.deInstalledAssetTag = deInstalledAssetTag;
	}

	/**
	 * @return the deInstalledPageCount
	 */
	public String getDeInstalledPageCount() {
		return deInstalledPageCount;
	}

	/**
	 * @param deInstalledPageCount the deInstalledPageCount to set
	 */
	public void setDeInstalledPageCount(String deInstalledPageCount) {
		this.deInstalledPageCount = deInstalledPageCount;
	}

	/**
	 * @return the installedAssetTag
	 */
	public String getInstalledAssetTag() {
		return installedAssetTag;
	}

	/**
	 * @param installedAssetTag the installedAssetTag to set
	 */
	public void setInstalledAssetTag(String installedAssetTag) {
		this.installedAssetTag = installedAssetTag;
	}

	/**
	 * @return the installedPageCount
	 */
	public String getInstalledPageCount() {
		return installedPageCount;
	}

	/**
	 * @param installedPageCount the installedPageCount to set
	 */
	public void setInstalledPageCount(String installedPageCount) {
		this.installedPageCount = installedPageCount;
	}

	/**
	 * @return the downloadRequestPart
	 */
	public List<DownloadRequestPart> getDownloadRequestPart() {
		return downloadRequestPart;
	}

	/**
	 * @param downloadRequestPart the downloadRequestPart to set
	 */
	public void setDownloadRequestPart(List<DownloadRequestPart> downloadRequestPart) {
		this.downloadRequestPart = downloadRequestPart;
	}
		
}
