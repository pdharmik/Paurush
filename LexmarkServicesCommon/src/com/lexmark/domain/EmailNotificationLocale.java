package com.lexmark.domain;

import java.io.Serializable;

import com.lexmark.util.StringUtil;

public class EmailNotificationLocale implements Serializable{
	private static final long serialVersionUID = -5895117065976544060L;
	
	private Integer emailNotificationLocaleId;
	private String emailSubject;
	private String emailHeader;
	private String emailBody;
	private String emailFooter;
	private SupportedLocale locale;
	private EmailNotification emailNotification;
	
	public Integer getEmailNotificationLocaleId() {
		return emailNotificationLocaleId;
	}
	public void setEmailNotificationLocaleId(Integer emailNotificationLocaleId) {
		this.emailNotificationLocaleId = emailNotificationLocaleId;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailHeader() {
		return emailHeader;
	}
	public void setEmailHeader(String emailHeader) {
		this.emailHeader = StringUtil.replaceDoubleQuote(emailHeader);
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = StringUtil.replaceDoubleQuote(emailBody);
	}
	public String getEmailFooter() {
		return emailFooter;
	}
	public void setEmailFooter(String emailFooter) {
		this.emailFooter = StringUtil.replaceDoubleQuote(emailFooter);
	}
	public EmailNotification getEmailNotification() {
		return emailNotification;
	}
	public void setEmailNotification(EmailNotification emailNotification) {
		this.emailNotification = emailNotification;
	}
	public void setLocale(SupportedLocale locale) {
		this.locale = locale;
	}
	public SupportedLocale getLocale() {
		return locale;
	}
	
}
