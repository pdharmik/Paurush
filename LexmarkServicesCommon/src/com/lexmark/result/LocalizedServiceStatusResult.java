package com.lexmark.result;

import java.io.Serializable;

public class LocalizedServiceStatusResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4646330150712008158L;
	private String localizedString;
	
	public String getLocalizedString() {
		return localizedString;
	}
	public void setLocalizedString(String localizedString) {
		this.localizedString = localizedString;
	}
}
