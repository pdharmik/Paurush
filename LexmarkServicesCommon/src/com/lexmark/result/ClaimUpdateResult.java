package com.lexmark.result;

import java.io.Serializable;

public class ClaimUpdateResult implements Serializable {
	private static final long serialVersionUID = -8275142509049611877L;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
