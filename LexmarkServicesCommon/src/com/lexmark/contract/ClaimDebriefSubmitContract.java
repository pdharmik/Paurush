package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.Activity;

public class ClaimDebriefSubmitContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4090280595827398699L;
	private Activity activity;
	private String debriefStatus;
	
	public String getDebriefStatus() {
		return debriefStatus;
	}
	public void setDebriefStatus(String debriefStatus) {
		this.debriefStatus = debriefStatus;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
