package com.lexmark.domain;

import java.io.Serializable;

/**
 * The business object contains all notification info at locale level.
 * @author roger.lin
 *
 */
public class NotificationLocale implements Serializable {

	private static final long serialVersionUID = 1348317772230180410L;
	private Integer notificationLocaleId;
	private Integer notificationId;
	private SupportedLocale supportedLocale;
	private String displayDescription;
	
	public Integer getNotificationLocaleId() {
		return notificationLocaleId;
	}
	public void setNotificationLocaleId(Integer notificationLocaleId) {
		this.notificationLocaleId = notificationLocaleId;
	}
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public String getDisplayDescription() {
		return displayDescription;
	}
	public void setDisplayDescription(String displayDescription) {
		this.displayDescription = displayDescription;
	}
	public SupportedLocale getSupportedLocale() {
		return supportedLocale;
	}
	public void setSupportedLocale(SupportedLocale supportedLocale) {
		this.supportedLocale = supportedLocale;
	}
}
