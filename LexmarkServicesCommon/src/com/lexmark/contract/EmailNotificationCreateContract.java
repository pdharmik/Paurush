package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.EmailNotification;

public class EmailNotificationCreateContract implements Serializable{
	private static final long serialVersionUID = 2233657385881164008L;
	
	private EmailNotification emailNotification;
	
	public void setEmailNotification(EmailNotification emailNotification) {
		this.emailNotification = emailNotification;
	}
	public EmailNotification getEmailNotification() {
		return emailNotification;
	}

}
