package com.lexmark.contract.source;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RequestAcceptContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
	
	private String activityId;
	private String status;	
	private String requestNumber;
	private String comment;
	

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

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
