package com.lexmark.result;

import java.io.Serializable;

public class ActivityDebriefSubmitResult implements Serializable {
	private static final long serialVersionUID = -3347046173213248973L;
	private Boolean success;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
