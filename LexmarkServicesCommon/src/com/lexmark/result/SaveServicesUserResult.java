package com.lexmark.result;

import java.io.Serializable;

public class SaveServicesUserResult implements Serializable {

	private static final long serialVersionUID = -3552268242894989412L;
	private Boolean result;
	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult() {
		return result;
	}
}
