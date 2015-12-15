package com.lexmark.result;

import java.io.Serializable;

public class SaveReportDefinitionDetailResult implements Serializable {

	private static final long serialVersionUID = -2077537405814671336L;
	private Boolean result;
	
	public void setResult(Boolean result) {
		this.result = result;
	}
	public Boolean getResult() {
		return result;
	}
}
