package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file "do-servicerequestactivity-mapping.xml"
 */
public class ServiceRequestActivity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 7231708309504429182L;

	private Date createdDate;
	private String activityId;
	private String status;
	private String emailAddress;
	private String description;
	private String overrideDesc;

	private String activitySRId;
	private String type;
	private boolean serviceActivityFlag;
	private String contactEmail;
	private Date serviceRequestETA;
	private String serviceRequestSLA;
	private String comment;
	private String activitySerialNumber;
	private String activitySerialNumberMADC;
	private String deviceType;
	private String activityNumber;
	private String statusDetail;
	private String serialNumber;
	private String shipToDefault;
	private String severity;
	private String productMadc;
	private String productNonMadc;
	private String deviceTypeMADC;
	private String deviceTypeNonMADC;
	
	public String getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
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

	
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public boolean isServiceActivityFlag() {
		return serviceActivityFlag;
	}

	public void setServiceActivityFlag(boolean serviceActivityFlag) {
		this.serviceActivityFlag = serviceActivityFlag;
	}

	public String getOverrideDesc() {
		return overrideDesc;
	}

	public void setOverrideDesc(String overrideDesc) {
		this.overrideDesc = overrideDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getActivitySRId() {
		return activitySRId;
	}

	public void setActivitySRId(String activitySRId) {
		this.activitySRId = activitySRId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getServiceRequestETA() {
		return serviceRequestETA;
	}

	public void setServiceRequestETA(Date serviceRequestETA) {
		this.serviceRequestETA = serviceRequestETA;
	}

	public String getServiceRequestSLA() {
		return serviceRequestSLA;
	}

	public void setServiceRequestSLA(String serviceRequestSLA) {
		this.serviceRequestSLA = serviceRequestSLA;
	}

    public String getComment() {
        return comment;
    }

    public void setComments(String comment) {
        this.comment = comment;
    }

	public String getActivitySerialNumber() {
		return activitySerialNumber;
	}

	public void setActivitySerialNumber(String activitySerialNumber) {
		this.activitySerialNumber = activitySerialNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getShipToDefault() {
		return shipToDefault;
	}

	public void setShipToDefault(String shipToDefault) {
		this.shipToDefault = shipToDefault;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getActivitySerialNumberMADC() {
		return activitySerialNumberMADC;
	}

	public void setActivitySerialNumberMADC(String activitySerialNumberMADC) {
		this.activitySerialNumberMADC = activitySerialNumberMADC;
	}

	public String getProductMadc() {
		return productMadc;
	}

	public void setProductMadc(String productMadc) {
		this.productMadc = productMadc;
	}

	public String getProductNonMadc() {
		return productNonMadc;
	}

	public void setProductNonMadc(String productNonMadc) {
		this.productNonMadc = productNonMadc;
	}

	public String getDeviceTypeMADC() {
		return deviceTypeMADC;
	}

	public void setDeviceTypeMADC(String deviceTypeMADC) {
		this.deviceTypeMADC = deviceTypeMADC;
	}

	public String getDeviceTypeNonMADC() {
		return deviceTypeNonMADC;
	}

	public void setDeviceTypeNonMADC(String deviceTypeNonMADC) {
		this.deviceTypeNonMADC = deviceTypeNonMADC;
	}
	
}
