package com.lexmark.services.form;

import java.util.List;

import com.lexmark.domain.NotificationDetail;

public class NotificationForm {
	private List<NotificationDetail> notificationDetailList;
	private String userId;
	/**
	 * 
	 * @return String 
	 */
	 
	 
	public String getUserId() {
		return userId;
	}
	/**
	 * 
	 * @param userId 
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<NotificationDetail> getNotificationDetailList() {
		return notificationDetailList;
	}
	/**
	 * 
	 * @param notificationDetailList 
	 */
	public void setNotificationDetailList(
			List<NotificationDetail> notificationDetailList) {
		this.notificationDetailList = notificationDetailList;
	}
	
}
