package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmailNotification implements Serializable{
	private static final long serialVersionUID = -1932947772136917060L;
	
	private Integer emailNotificationId;
	private String emailName;
	private String emailDescription;
	private List<EmailNotificationLocale> emailNotificationLocaleList = new ArrayList<EmailNotificationLocale>();
	
	public Integer getEmailNotificationId() {
		return emailNotificationId;
	}
	public void setEmailNotificationId(Integer emailNotificationId) {
		this.emailNotificationId = emailNotificationId;
	}
	public String getEmailName() {
		return emailName;
	}
	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}
	public String getEmailDescription() {
		return emailDescription;
	}
	public void setEmailDescription(String emailDescription) {
		this.emailDescription = emailDescription;
	}
	public List<EmailNotificationLocale> getEmailNotificationLocaleList() {
		return emailNotificationLocaleList;
	}
	public void setEmailNotificationLocaleList(
			List<EmailNotificationLocale> emailNotificationLocaleList) {
		this.emailNotificationLocaleList = emailNotificationLocaleList;
	}
}
