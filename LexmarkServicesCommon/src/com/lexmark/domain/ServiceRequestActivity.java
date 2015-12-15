package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ServiceRequestActivity implements Serializable {
    private static final long serialVersionUID = -3392679198071485787L;
    private String activityId;
    private Date activityDate;
    private String activityStatus;
    private Integer StatusOrder;
    private String activityDescription;
    private String recipientEmail;
    private String message;
    private String serviceRequestETA;
    private String serviceRequestSLA;
    private String comment;
    private String activityNumber;
    private String deviceType;
    private String statusDetail;
    private Date serviceRequestDateETA;
    //OPS
    private String activityType;
    private String severity;
    private String activitySubStatus;
    
    
    public String getActivitySubStatus() {
		return activitySubStatus;
	}

	public void setActivitySubStatus(String activitySubStatus) {
		this.activitySubStatus = activitySubStatus;
	}

	//Added for wave4
    private List<Asset> assetDetails;
    //End
	private String activitySerialNumber;
    
    public String getActivitySerialNumber() {
		return activitySerialNumber;
	}

	public void setActivitySerialNumber(String activitySerialNumber) {
		this.activitySerialNumber = activitySerialNumber;
	}

	public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public void setStatusOrder(Integer statusOrder) {
        StatusOrder = statusOrder;
    }

    public Integer getStatusOrder() {
        return StatusOrder;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getServiceRequestETA() {
        return serviceRequestETA;
    }

    public void setServiceRequestETA(String serviceRequestETA) {
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

    public void setComment(String comment) {
        this.comment = comment;
    }

	public List<Asset> getAssetDetails() {
		return assetDetails;
	}

	public void setAssetDetails(List<Asset> assetDetails) {
		this.assetDetails = assetDetails;
	}

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

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Date getServiceRequestDateETA() {
		return serviceRequestDateETA;
	}

	public void setServiceRequestDateETA(Date serviceRequestDateETA) {
		this.serviceRequestDateETA = serviceRequestDateETA;
	}

}
