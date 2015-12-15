package com.lexmark.result;

import java.io.Serializable;

public class EmailNotificationResult implements Serializable{
	private static final long serialVersionUID = 418540148399553464L;
	private Boolean result = Boolean.FALSE;
	
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}

}
