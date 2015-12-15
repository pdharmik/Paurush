package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The business object contains a notification object and a list of
 * NotificationLocale objects (one per each locale).
 * @author roger.lin
 *
 */
public class NotificationDetail implements Serializable {

	private static final long serialVersionUID = -2965569565148971602L;
	private Notification notification;
	private List<NotificationLocale> notificationLocaleList = new ArrayList<NotificationLocale>();
	
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	public List<NotificationLocale> getNotificationLocaleList() {
		return notificationLocaleList;
	}
	public void setNotificationLocaleList(
			List<NotificationLocale> notificationLocaleList) {
		this.notificationLocaleList = notificationLocaleList;
	}

}
