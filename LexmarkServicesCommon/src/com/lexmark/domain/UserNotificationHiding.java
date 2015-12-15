package com.lexmark.domain;

import java.io.Serializable;

/**
 * This is the business object of Notification.
 * @author Roger.Lin
 *
 */
public class UserNotificationHiding implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5294628055374872595L;
	private String userId;
	private Integer notificationId;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof UserNotificationHiding))
			return false;
		UserNotificationHiding pn = (UserNotificationHiding)o;
		return userId.equals(pn.getUserId()) && notificationId.equals(pn.getNotificationId());
	}
	
	private volatile int hashCode = 0; // (See Item 48)
	public int hashCode() {
		if (hashCode == 0) {
			int result = 17;
			result = 37*result + notificationId;
			result = 37*result + userId.hashCode();
			hashCode = result;
		}
		return hashCode;
	}
}