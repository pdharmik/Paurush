package com.lexmark.services.form;

import java.util.List;

import com.lexmark.domain.NotificationDetail;

public class NotificationForm {
	private List<NotificationDetail> notificationDetailList;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<NotificationDetail> getNotificationDetailList() {
		return notificationDetailList;
	}
	public void setNotificationDetailList(
			List<NotificationDetail> notificationDetailList) {
		this.notificationDetailList = notificationDetailList;
	}
	
}
