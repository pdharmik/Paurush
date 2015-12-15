package com.lexmark.contract;

import java.io.Serializable;

public class EmailNotificationDetailForNameContract implements Serializable{
	private static final long serialVersionUID = 5044608555345072460L;
	
	private String emailNotificationName;

	public String getEmailNotificationName() {
		return emailNotificationName;
	}

	public void setEmailNotificationName(String emailNotificationName) {
		this.emailNotificationName = emailNotificationName;
	}

}
