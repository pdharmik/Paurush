package com.lexmark.contract;

import java.io.Serializable;

public class RetrieveServicesUserContract implements Serializable {

	private static final long serialVersionUID = 7450651683146914646L;
	private String userNumber;
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
}
