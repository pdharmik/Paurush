package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.SecureContract;

public class ActivityDetailContract extends SecureContract implements Serializable {
	private static final long serialVersionUID = -8298447966185294873L;
	private String serviceRequestId;

	/**
	 * The debriefFlag is set based on user actions performed on the screen and
	 * will need to be set to true only when loading the debrief (Close Out)
	 * screen, this will tell aMind to populate the partDebriefList. If the
	 * Detail screen is being loaded you will set the flag to false.
	 **/
	private boolean debriefFlag;

	private String activityId;
	private String pageName;

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public boolean isDebriefFlag() {
		return debriefFlag;
	}

	public void setDebriefFlag(boolean debriefFlag) {
		this.debriefFlag = debriefFlag;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

}
