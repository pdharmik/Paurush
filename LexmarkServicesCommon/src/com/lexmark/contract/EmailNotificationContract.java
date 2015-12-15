package com.lexmark.contract;

import java.io.Serializable;

public class EmailNotificationContract implements Serializable{
	private static final long serialVersionUID = -1482543707224083903L;
	
	private Integer emailNotificationId;

	public Integer getEmailNotificationId() {
		return emailNotificationId;
	}

	public void setEmailNotificationId(Integer emailNotificationId) {
		this.emailNotificationId = emailNotificationId;
	}

}
