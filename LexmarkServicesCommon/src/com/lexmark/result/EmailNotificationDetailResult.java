package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.EmailNotification;

public class EmailNotificationDetailResult implements Serializable {
	private static final long serialVersionUID = -4439512630836893584L;
	
	private EmailNotification emailNotification;

	public EmailNotification getEmailNotification() {
		return emailNotification;
	}

	public void setEmailNotification(EmailNotification emailNotification) {
		this.emailNotification = emailNotification;
	}
}
