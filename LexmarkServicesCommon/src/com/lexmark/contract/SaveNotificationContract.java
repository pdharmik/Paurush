package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.NotificationDetail;

public class SaveNotificationContract implements Serializable {

	private static final long serialVersionUID = 9049538337644806335L;
	private NotificationDetail notificationDetail;
	
	public NotificationDetail getNotificationDetail() {
		return notificationDetail;
	}
	public void setNotificationDetail(NotificationDetail notificationDetail) {
		this.notificationDetail = notificationDetail;
	}
}
