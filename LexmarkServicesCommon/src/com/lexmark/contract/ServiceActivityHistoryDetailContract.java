package com.lexmark.contract;

import java.io.Serializable;

public class ServiceActivityHistoryDetailContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
	
	private String activityId;
	private String serviceRequestId;
	
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public String getServiceRequestId() {
		return serviceRequestId;
	}
	
	

}
