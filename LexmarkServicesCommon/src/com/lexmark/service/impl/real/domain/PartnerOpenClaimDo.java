package com.lexmark.service.impl.real.domain;

import java.util.Date;
/**
 * mapping file: "do-partneropenclaim-mapping.xml"
 */
public class PartnerOpenClaimDo extends AccountBasedDo {
	private static final long serialVersionUID = -5555883075581166226L;
	
	private String assetId;
	private String activityId;
	private String serviceRequestId;
	private String serviceRequestNumber;
	private Date serviceRequestDate;
	private String serviceProviderReferenceNumber;
	private String accountName;
	private String actualFailureCode;
	private String mdmLevel;
	private Date activityDate;
	private String activityStatus;
	
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
	public Date getServiceRequestDate() {
		return serviceRequestDate;
	}
	public void setServiceRequestDate(Date serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}
	public String getServiceProviderReferenceNumber() {
		return serviceProviderReferenceNumber;
	}
	public void setServiceProviderReferenceNumber(
			String serviceProviderReferenceNumber) {
		this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getActualFailureCode() {
		return actualFailureCode;
	}
	public void setActualFailureCode(String actualFailureCode) {
		this.actualFailureCode = actualFailureCode;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	public String getActivityStatus() {
		return activityStatus;
	}
	
	
}
