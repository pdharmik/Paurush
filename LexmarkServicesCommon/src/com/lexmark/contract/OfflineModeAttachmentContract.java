package com.lexmark.contract;

import java.io.Serializable;

public class OfflineModeAttachmentContract implements Serializable {

	private static final long serialVersionUID = 1L;

	private String activityId;
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
}
