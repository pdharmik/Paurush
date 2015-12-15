package com.lexmark.contract;

import java.io.Serializable;

public class UserNotificationContract implements Serializable{
	private static final long serialVersionUID = -4033526179394084992L;
	
	private String userId;
	private Integer notificationId;
	
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
