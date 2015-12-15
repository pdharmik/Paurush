package com.lexmark.result;

import java.io.Serializable;

public class LocalizedEntitlementServiceDetailResult implements Serializable {

	private static final long serialVersionUID = -8442843844050249374L;
	private String localizedString;
	
	public String getLocalizedString() {
		return localizedString;
	}
	public void setLocalizedString(String localizedString) {
		this.localizedString = localizedString;
	}

}
