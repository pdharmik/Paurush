package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.Activity;

public class ServiceActivityHistoryDetailResult implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private Activity activity;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	

}
