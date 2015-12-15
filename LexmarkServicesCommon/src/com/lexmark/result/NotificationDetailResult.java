package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.NotificationDetail;

public class NotificationDetailResult implements Serializable {

	private static final long serialVersionUID = 6193882094488104371L;
	private NotificationDetail notificationDetail;
	
	public void setNotificationDetail(NotificationDetail notificationDetail) {
		this.notificationDetail = notificationDetail;
	}
	public NotificationDetail getNotificationDetail() {
		return notificationDetail;
	}
}
