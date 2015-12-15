package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.EmailNotification;
import com.lexmark.domain.EmailNotificationLocale;

public class EmailNotificationLocaleContract implements Serializable{
	private static final long serialVersionUID = 6195096422087124476L;
	
	private Integer emailNotificationLocaleId;
	private EmailNotificationLocale emailNotificationLocale;
	private EmailNotification emailNotification;

	public Integer getEmailNotificationLocaleId() {
		return emailNotificationLocaleId;
	}

	public void setEmailNotificationLocaleId(Integer emailNotificationLocaleId) {
		this.emailNotificationLocaleId = emailNotificationLocaleId;
	}

	public void setEmailNotificationLocale(EmailNotificationLocale emailNotificationLocale) {
		this.emailNotificationLocale = emailNotificationLocale;
	}

	public EmailNotificationLocale getEmailNotificationLocale() {
		return emailNotificationLocale;
	}

	public void setEmailNotification(EmailNotification emailNotification) {
		this.emailNotification = emailNotification;
	}

	public EmailNotification getEmailNotification() {
		return emailNotification;
	}
}
