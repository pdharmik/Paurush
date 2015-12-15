package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.ContractBase;
import com.lexmark.domain.Activity;

public class HardwareDebriefContract extends ContractBase implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Activity activity;
	private Activity userEnteredActivity;
	private String debriefStatus;
	private float timeZoneOffset;
	private String srType;
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public void setUserEnteredActivity(Activity userEnteredActivity) {
		this.userEnteredActivity = userEnteredActivity;
	}

	public Activity getUserEnteredActivity() {
		return userEnteredActivity;
	}

	public String getDebriefStatus() {
		return debriefStatus;
	}

	public void setDebriefStatus(String debriefStatus) {
		this.debriefStatus = debriefStatus;
	}

	public void setTimeZoneOffset(float timeZoneOffset) {
		this.timeZoneOffset = timeZoneOffset;
	}

	public float getTimeZoneOffset() {
		return timeZoneOffset;
	}

	public void setSrType(String srType) {
		this.srType = srType;
	}

	public String getSrType() {
		return srType;
	}
	
	
	
	

}
