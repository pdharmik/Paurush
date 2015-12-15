package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

public class UserNotificationListContract implements Serializable{
	
	private static final long serialVersionUID = 830170400710903535L;
	
	private String userNumber;
	private Locale locale;
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

}
