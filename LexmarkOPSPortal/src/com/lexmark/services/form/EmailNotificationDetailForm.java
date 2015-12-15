package com.lexmark.services.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.EmailNotificationLocale;

public class EmailNotificationDetailForm implements Serializable{
	private static final long serialVersionUID = 7300462668216883424L;
	private Integer emailNotificationId;
	private String emailName;
	private String emailDescription;
	private boolean editFlag = false;
	private EmailNotificationLocale emailNotificationLocale;
	private List<EmailNotificationLocale> emailNotificationLocaleList = new ArrayList<EmailNotificationLocale>();
	
	public void setEmailNotificationId(Integer emailNotificationId) {
		this.emailNotificationId = emailNotificationId;
	}
	public Integer getEmailNotificationId() {
		return emailNotificationId;
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
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	public boolean getEditFlag() {
		return editFlag;
	}
	public void setEmailNotificationLocale(EmailNotificationLocale emailNotificationLocale) {
		this.emailNotificationLocale = emailNotificationLocale;
	}
	public EmailNotificationLocale getEmailNotificationLocale() {
		return emailNotificationLocale;
	}
	public void setEmailNotificationLocaleList(
			List<EmailNotificationLocale> emailNotificationLocaleList) {
		this.emailNotificationLocaleList = emailNotificationLocaleList;
	}
	public List<EmailNotificationLocale> getEmailNotificationLocaleList() {
		return emailNotificationLocaleList;
	}

}
