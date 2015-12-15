package com.lexmark.result;

import java.io.Serializable;

public class ValidateInstalledPrinterSerialNumberResult implements Serializable {
	
	private static final long serialVersionUID = 5240138908959490481L;
	private Boolean success;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}

