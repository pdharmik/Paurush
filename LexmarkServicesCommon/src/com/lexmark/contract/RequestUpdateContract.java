package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.Activity;

public class RequestUpdateContract implements Serializable {
	private static final long serialVersionUID = 4022786791810388402L;
	private Activity activity;
	
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
