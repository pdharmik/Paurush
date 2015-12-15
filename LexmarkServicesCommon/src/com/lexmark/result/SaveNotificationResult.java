package com.lexmark.result;

import java.io.Serializable;

public class SaveNotificationResult implements Serializable {

	private static final long serialVersionUID = -4555372076766166076L;
	private Boolean success;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
