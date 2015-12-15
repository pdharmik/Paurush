package com.lexmark.result;

import java.io.Serializable;

public class NotificationDisplayOrderResult implements Serializable {

	private static final long serialVersionUID = -3461358828880485568L;
	private Boolean successfulFlag;
	
	public Boolean getSuccessfulFlag() {
		return successfulFlag;
	}
	public void setSuccessfulFlag(Boolean successfulFlag) {
		this.successfulFlag = successfulFlag;
	}
}
