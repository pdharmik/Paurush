package com.lexmark.result.source;

import java.io.Serializable;

public class OrderAcceptResult implements Serializable {
	
	private Boolean result;

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Boolean getResult() {
		return result;
	}

}
