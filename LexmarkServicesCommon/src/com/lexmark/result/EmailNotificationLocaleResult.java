package com.lexmark.result;

import java.io.Serializable;

public class EmailNotificationLocaleResult implements Serializable{
	private static final long serialVersionUID = 8085352693472109151L;
	
	private Boolean result = Boolean.FALSE;

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Boolean getResult() {
		return result;
	}
}
