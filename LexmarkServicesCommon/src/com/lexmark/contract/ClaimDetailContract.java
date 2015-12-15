package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.SecureContract;

public class ClaimDetailContract extends SecureContract implements Serializable {
	private static final long serialVersionUID = 3438250893252552847L;
	private String serviceRequestId;
	private Boolean debriefFlag;
	private String activityId;
	public String getServiceRequestId() {
		return serviceRequestId;
	}
	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}
	public Boolean getDebriefFlag() {
		return debriefFlag;
	}
	public void setDebriefFlag(Boolean debriefFlag) {
		this.debriefFlag = debriefFlag;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
}
