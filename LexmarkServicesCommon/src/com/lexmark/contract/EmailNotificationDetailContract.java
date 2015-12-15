package com.lexmark.contract;

import java.io.Serializable;

public class EmailNotificationDetailContract implements Serializable{
	private static final long serialVersionUID = 5044608555345072460L;
	
	private String emailNotificationId;

	public String getEmailNotificationId() {
		return emailNotificationId;
	}

	public void setEmailNotificationId(String emailNotificationId) {
		this.emailNotificationId = emailNotificationId;
	}
}
