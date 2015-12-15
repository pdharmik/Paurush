package com.lexmark.result;

import java.io.Serializable;

public class RequestUpdateResult implements Serializable{

	private static final long serialVersionUID = -1645446892172761199L;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
