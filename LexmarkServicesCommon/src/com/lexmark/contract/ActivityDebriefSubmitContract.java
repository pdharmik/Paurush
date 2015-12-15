package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.Activity;

public class ActivityDebriefSubmitContract implements Serializable{
	private static final long serialVersionUID = 298835220708162L;
	private Activity activity;
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
