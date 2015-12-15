package com.lexmark.contract;

import java.io.Serializable;

/**
 * The contract is used to retrieve a notification.
 * @author Roger.Lin
 *
 */
public class NotificationDetailContract implements Serializable {

	private static final long serialVersionUID = -2687014771643447185L;
	private Integer notificationId;
	
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public Integer getNotificationId() {
		return notificationId;
	}
}
