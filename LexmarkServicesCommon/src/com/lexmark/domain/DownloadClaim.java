package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DownloadClaim implements Serializable {

	private static final long serialVersionUID = -6905209433257748664L;

	private String srId;
	private String activityId;
	private String addlPaymentDesc1;
	private String addlPaymentDesc2;
	private String addlPaymentQty1;
	private String addlPaymentQty2;
	private String addlPaymentType1;
	private String addlPaymentType2;
	private String assetMTM;
	private String assetProduct;
	private String astSerialNumber;
	private String newCustomerAccount;
	private String newCustomerAddress;
	private String newTechFirstName;
	private String newTechLastName;
	private String partnerAddress1;
	private String partnerAddress2;
	private String partnerAddress3;
	private String partnerCity;
	private String partnerCountry;
	private String partnerName;
	private String partnerPostal;
	private String partnerProvince;
	private String partnerRefNumber;
	private String partnerSite;
	private String prContactEmail;
	private String prContactFN;
	private String prContactLN;
	private String prContactWorkPhone;
	private String printerCondition;	
	private String problemCode;
	private String problemDetails;
	private String repairCompleteFlag;
	private String repairDesc;	
	private String reqContactEmail;
	private String reqContactFN;
	private String reqContactLN;
	private String reqContactWorkPhone;
	private String requestReviewFlag;
	private String resolutionCode;
	private String reviewComments;
	private String serviceAddress1;
	private String serviceAddress2;	
	private String serviceAddress3;
	private String serviceCity;
	private String serviceCountry;
	private String servicePostal;
	private String serviceProvince;	
	private String serviceState;
	private Date srvcEndDate; //
	private Date srvcStartDate; //
	private String techLoginName;
	private String addlPaymentUnitPrice1;	
	private String addlPaymentUnitPrice2;
	private String partnerOrg;
	private String partnerState;

	private List<DownloadClaimPart> downloadClaimPart;
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
	 * @return the srId
	 */
	public String getSrId() {
		return srId;
	}

	/**
	 * @param srId the srId to set
	 */
	public void setSrId(String srId) {
		this.srId = srId;
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
	 * @return the assetMTM
	 */
	public String getAssetMTM() {
		return assetMTM;
	}

	/**
	 * @param assetMTM the assetMTM to set
	 */
	public void setAssetMTM(String assetMTM) {
		this.assetMTM = assetMTM;
	}

	/**
	 * @return the assetProduct
	 */
	public String getAssetProduct() {
		return assetProduct;
	}

	/**
	 * @param assetProduct the assetProduct to set
	 */
	public void setAssetProduct(String assetProduct) {
		this.assetProduct = assetProduct;
	}

	/**
	 * @return the astSerialNumber
	 */
	public String getAstSerialNumber() {
		return astSerialNumber;
	}

	/**
	 * @param astSerialNumber the astSerialNumber to set
	 */
	public void setAstSerialNumber(String astSerialNumber) {
		this.astSerialNumber = astSerialNumber;
	}

	/**
	 * @return the newCustomerAccount
	 */
	public String getNewCustomerAccount() {
		return newCustomerAccount;
	}

	/**
	 * @param newCustomerAccount the newCustomerAccount to set
	 */
	public void setNewCustomerAccount(String newCustomerAccount) {
		this.newCustomerAccount = newCustomerAccount;
	}

	/**
	 * @return the newCustomerAddress
	 */
	public String getNewCustomerAddress() {
		return newCustomerAddress;
	}

	/**
	 * @param newCustomerAddress the newCustomerAddress to set
	 */
	public void setNewCustomerAddress(String newCustomerAddress) {
		this.newCustomerAddress = newCustomerAddress;
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
	 * @return the partnerAddress1
	 */
	public String getPartnerAddress1() {
		return partnerAddress1;
	}

	/**
	 * @param partnerAddress1 the partnerAddress1 to set
	 */
	public void setPartnerAddress1(String partnerAddress1) {
		this.partnerAddress1 = partnerAddress1;
	}

	/**
	 * @return the partnerAddress2
	 */
	public String getPartnerAddress2() {
		return partnerAddress2;
	}

	/**
	 * @param partnerAddress2 the partnerAddress2 to set
	 */
	public void setPartnerAddress2(String partnerAddress2) {
		this.partnerAddress2 = partnerAddress2;
	}

	/**
	 * @return the partnerAddress3
	 */
	public String getPartnerAddress3() {
		return partnerAddress3;
	}

	/**
	 * @param partnerAddress3 the partnerAddress3 to set
	 */
	public void setPartnerAddress3(String partnerAddress3) {
		this.partnerAddress3 = partnerAddress3;
	}

	/**
	 * @return the partnerCity
	 */
	public String getPartnerCity() {
		return partnerCity;
	}

	/**
	 * @param partnerCity the partnerCity to set
	 */
	public void setPartnerCity(String partnerCity) {
		this.partnerCity = partnerCity;
	}

	/**
	 * @return the partnerCountry
	 */
	public String getPartnerCountry() {
		return partnerCountry;
	}

	/**
	 * @param partnerCountry the partnerCountry to set
	 */
	public void setPartnerCountry(String partnerCountry) {
		this.partnerCountry = partnerCountry;
	}

	/**
	 * @return the partnerName
	 */
	public String getPartnerName() {
		return partnerName;
	}

	/**
	 * @param partnerName the partnerName to set
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	/**
	 * @return the partnerPostal
	 */
	public String getPartnerPostal() {
		return partnerPostal;
	}

	/**
	 * @param partnerPostal the partnerPostal to set
	 */
	public void setPartnerPostal(String partnerPostal) {
		this.partnerPostal = partnerPostal;
	}

	/**
	 * @return the partnerProvince
	 */
	public String getPartnerProvince() {
		return partnerProvince;
	}

	/**
	 * @param partnerProvince the partnerProvince to set
	 */
	public void setPartnerProvince(String partnerProvince) {
		this.partnerProvince = partnerProvince;
	}

	/**
	 * @return the partnerRefNumber
	 */
	public String getPartnerRefNumber() {
		return partnerRefNumber;
	}

	/**
	 * @param partnerRefNumber the partnerRefNumber to set
	 */
	public void setPartnerRefNumber(String partnerRefNumber) {
		this.partnerRefNumber = partnerRefNumber;
	}

	/**
	 * @return the partnerSite
	 */
	public String getPartnerSite() {
		return partnerSite;
	}

	/**
	 * @param partnerSite the partnerSite to set
	 */
	public void setPartnerSite(String partnerSite) {
		this.partnerSite = partnerSite;
	}

	/**
	 * @return the prContactEmail
	 */
	public String getPrContactEmail() {
		return prContactEmail;
	}

	/**
	 * @param prContactEmail the prContactEmail to set
	 */
	public void setPrContactEmail(String prContactEmail) {
		this.prContactEmail = prContactEmail;
	}

	/**
	 * @return the prContactFN
	 */
	public String getPrContactFN() {
		return prContactFN;
	}

	/**
	 * @param prContactFN the prContactFN to set
	 */
	public void setPrContactFN(String prContactFN) {
		this.prContactFN = prContactFN;
	}

	/**
	 * @return the prContactLN
	 */
	public String getPrContactLN() {
		return prContactLN;
	}

	/**
	 * @param prContactLN the prContactLN to set
	 */
	public void setPrContactLN(String prContactLN) {
		this.prContactLN = prContactLN;
	}

	/**
	 * @return the prContactWorkPhone
	 */
	public String getPrContactWorkPhone() {
		return prContactWorkPhone;
	}

	/**
	 * @param prContactWorkPhone the prContactWorkPhone to set
	 */
	public void setPrContactWorkPhone(String prContactWorkPhone) {
		this.prContactWorkPhone = prContactWorkPhone;
	}

	/**
	 * @return the printerCondition
	 */
	public String getPrinterCondition() {
		return printerCondition;
	}

	/**
	 * @param printerCondition the printerCondition to set
	 */
	public void setPrinterCondition(String printerCondition) {
		this.printerCondition = printerCondition;
	}

	/**
	 * @return the problemCode
	 */
	public String getProblemCode() {
		return problemCode;
	}

	/**
	 * @param problemCode the problemCode to set
	 */
	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	/**
	 * @return the problemDetails
	 */
	public String getProblemDetails() {
		return problemDetails;
	}

	/**
	 * @param problemDetails the problemDetails to set
	 */
	public void setProblemDetails(String problemDetails) {
		this.problemDetails = problemDetails;
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
	 * @return the repairDesc
	 */
	public String getRepairDesc() {
		return repairDesc;
	}

	/**
	 * @param repairDesc the repairDesc to set
	 */
	public void setRepairDesc(String repairDesc) {
		this.repairDesc = repairDesc;
	}

	/**
	 * @return the reqContactEmail
	 */
	public String getReqContactEmail() {
		return reqContactEmail;
	}

	/**
	 * @param reqContactEmail the reqContactEmail to set
	 */
	public void setReqContactEmail(String reqContactEmail) {
		this.reqContactEmail = reqContactEmail;
	}

	/**
	 * @return the reqContactFN
	 */
	public String getReqContactFN() {
		return reqContactFN;
	}

	/**
	 * @param reqContactFN the reqContactFN to set
	 */
	public void setReqContactFN(String reqContactFN) {
		this.reqContactFN = reqContactFN;
	}

	/**
	 * @return the reqContactLN
	 */
	public String getReqContactLN() {
		return reqContactLN;
	}

	/**
	 * @param reqContactLN the reqContactLN to set
	 */
	public void setReqContactLN(String reqContactLN) {
		this.reqContactLN = reqContactLN;
	}

	/**
	 * @return the reqContactWorkPhone
	 */
	public String getReqContactWorkPhone() {
		return reqContactWorkPhone;
	}

	/**
	 * @param reqContactWorkPhone the reqContactWorkPhone to set
	 */
	public void setReqContactWorkPhone(String reqContactWorkPhone) {
		this.reqContactWorkPhone = reqContactWorkPhone;
	}

	/**
	 * @return the requestReviewFlag
	 */
	public String getRequestReviewFlag() {
		return requestReviewFlag;
	}

	/**
	 * @param requestReviewFlag the requestReviewFlag to set
	 */
	public void setRequestReviewFlag(String requestReviewFlag) {
		this.requestReviewFlag = requestReviewFlag;
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
	 * @return the reviewComments
	 */
	public String getReviewComments() {
		return reviewComments;
	}

	/**
	 * @param reviewComments the reviewComments to set
	 */
	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	/**
	 * @return the serviceAddress1
	 */
	public String getServiceAddress1() {
		return serviceAddress1;
	}

	/**
	 * @param serviceAddress1 the serviceAddress1 to set
	 */
	public void setServiceAddress1(String serviceAddress1) {
		this.serviceAddress1 = serviceAddress1;
	}

	/**
	 * @return the serviceAddress2
	 */
	public String getServiceAddress2() {
		return serviceAddress2;
	}

	/**
	 * @param serviceAddress2 the serviceAddress2 to set
	 */
	public void setServiceAddress2(String serviceAddress2) {
		this.serviceAddress2 = serviceAddress2;
	}

	/**
	 * @return the serviceAddress3
	 */
	public String getServiceAddress3() {
		return serviceAddress3;
	}

	/**
	 * @param serviceAddress3 the serviceAddress3 to set
	 */
	public void setServiceAddress3(String serviceAddress3) {
		this.serviceAddress3 = serviceAddress3;
	}

	/**
	 * @return the serviceCity
	 */
	public String getServiceCity() {
		return serviceCity;
	}

	/**
	 * @param serviceCity the serviceCity to set
	 */
	public void setServiceCity(String serviceCity) {
		this.serviceCity = serviceCity;
	}

	/**
	 * @return the serviceCountry
	 */
	public String getServiceCountry() {
		return serviceCountry;
	}

	/**
	 * @param serviceCountry the serviceCountry to set
	 */
	public void setServiceCountry(String serviceCountry) {
		this.serviceCountry = serviceCountry;
	}

	/**
	 * @return the servicePostal
	 */
	public String getServicePostal() {
		return servicePostal;
	}

	/**
	 * @param servicePostal the servicePostal to set
	 */
	public void setServicePostal(String servicePostal) {
		this.servicePostal = servicePostal;
	}

	/**
	 * @return the serviceProvince
	 */
	public String getServiceProvince() {
		return serviceProvince;
	}

	/**
	 * @param serviceProvince the serviceProvince to set
	 */
	public void setServiceProvince(String serviceProvince) {
		this.serviceProvince = serviceProvince;
	}

	/**
	 * @return the serviceState
	 */
	public String getServiceState() {
		return serviceState;
	}

	/**
	 * @param serviceState the serviceState to set
	 */
	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}

	/**
	 * @return the srvcEndDate
	 */
	public Date getSrvcEndDate() {
		return srvcEndDate;
	}

	/**
	 * @param srvcEndDate the srvcEndDate to set
	 */
	public void setSrvcEndDate(Date srvcEndDate) {
		this.srvcEndDate = srvcEndDate;
	}

	/**
	 * @return the srvcStartDate
	 */
	public Date getSrvcStartDate() {
		return srvcStartDate;
	}

	/**
	 * @param srvcStartDate the srvcStartDate to set
	 */
	public void setSrvcStartDate(Date srvcStartDate) {
		this.srvcStartDate = srvcStartDate;
	}

	/**
	 * @return the techLoginName
	 */
	public String getTechLoginName() {
		return techLoginName;
	}

	/**
	 * @param techLoginName the techLoginName to set
	 */
	public void setTechLoginName(String techLoginName) {
		this.techLoginName = techLoginName;
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
	 * @return the partnerOrg
	 */
	public String getPartnerOrg() {
		return partnerOrg;
	}

	/**
	 * @param partnerOrg the partnerOrg to set
	 */
	public void setPartnerOrg(String partnerOrg) {
		this.partnerOrg = partnerOrg;
	}

	/**
	 * @return the partnerState
	 */
	public String getPartnerState() {
		return partnerState;
	}

	/**
	 * @param partnerState the partnerState to set
	 */
	public void setPartnerState(String partnerState) {
		this.partnerState = partnerState;
	}

	/**
	 * @return the downloadClaimPart
	 */
	public List<DownloadClaimPart> getDownloadClaimPart() {
		return downloadClaimPart;
	}

	/**
	 * @param downloadClaimPart the downloadClaimPart to set
	 */
	public void setDownloadClaimPart(List<DownloadClaimPart> downloadClaimPart) {
		this.downloadClaimPart = downloadClaimPart;
	}
	}
