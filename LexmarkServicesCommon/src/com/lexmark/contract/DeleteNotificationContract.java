package com.lexmark.contract;

import java.io.Serializable;

/**
 * The contract to delete notification.
 * @author Roger
 *
 */
public class DeleteNotificationContract implements Serializable {

	private static final long serialVersionUID = -1664362663094441706L;
	private Integer notificationId;
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

}
