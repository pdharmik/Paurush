package com.lexmark.result;

import java.io.Serializable;

public class DeleteReportAdministrationResult implements Serializable {

	private static final long serialVersionUID = 5304130896294871745L;
	private Boolean result;
	
	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult() {
		return result;
	}
}
