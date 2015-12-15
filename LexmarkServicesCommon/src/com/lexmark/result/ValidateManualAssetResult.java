package com.lexmark.result;

import java.io.Serializable;

public class ValidateManualAssetResult implements Serializable {

	private static final long serialVersionUID = 8598994378667643778L;
	
	private Boolean result;

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Boolean getResult() {
		return result;
	}
}
