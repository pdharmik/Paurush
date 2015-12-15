package com.lexmark.result;

import java.io.Serializable;

public class ClaimDebriefSubmitResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5540821033165252589L;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
