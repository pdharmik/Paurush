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
	private boolean editFlag;
	private EmailNotificationLocale emailNotificationLocale;
	private List<EmailNotificationLocale> emailNotificationLocaleList = new ArrayList<EmailNotificationLocale>();
	/**
	 * 
	 * @param emailNotificationId 
	 */
	public void setEmailNotificationId(Integer emailNotificationId) {
		this.emailNotificationId = emailNotificationId;
	}
	/**
	 * 
	 * @return Integer 
	 */
	public Integer getEmailNotificationId() {
		return emailNotificationId;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getEmailName() {
		return emailName;
	}
	/**
	 * 
	 * @param emailName 
	 */
	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getEmailDescription() {
		return emailDescription;
	}
	/**
	 * 
	 * @param emailDescription 
	 */
	public void setEmailDescription(String emailDescription) {
		this.emailDescription = emailDescription;
	}
	/**
	 * 
	 * @param editFlag 
	 */
	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}
	/**
	 * 
	 * @return Boolean 
	 */
	public boolean getEditFlag() {
		return editFlag;
	}
	/**
	 * 
	 * @param emailNotificationLocale 
	 */
	public void setEmailNotificationLocale(EmailNotificationLocale emailNotificationLocale) {
		this.emailNotificationLocale = emailNotificationLocale;
	}
	/**
	 * 
	 * @return emailNotificationLocale 
	 */
	public EmailNotificationLocale getEmailNotificationLocale() {
		return emailNotificationLocale;
	}
	/**
	 * 
	 * @param emailNotificationLocaleList 
	 */
	public void setEmailNotificationLocaleList(
			List<EmailNotificationLocale> emailNotificationLocaleList) {
		this.emailNotificationLocaleList = emailNotificationLocaleList;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<EmailNotificationLocale> getEmailNotificationLocaleList() {
		return emailNotificationLocaleList;
	}

}
