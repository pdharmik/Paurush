package com.lexmark.result;

import java.io.Serializable;

public class LocalizedServiceActivityStatusResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3727928104011047709L;
	private String localizedValue;
	
	public String getLocalizedValue() {
		return localizedValue;
	}
	public void setLocalizedValue(String localizedValue) {
		this.localizedValue = localizedValue;
	}
}
