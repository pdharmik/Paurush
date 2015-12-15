package com.lexmark.contract;

import java.io.Serializable;

public class ServicesUserContract implements Serializable {

	private static final long serialVersionUID = 4541052907677552702L;
	private String userNumber;
	private String emailAddress;
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
