package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Notification;

/**
 * The class contains notification list and the total count, when administrator
 * admins notifications.
 * @author Roger.Lin
 *
 */
public class AdminNotificationListResult implements Serializable {
	private static final long serialVersionUID = -3496664520983804445L;
	private Integer totalCount;
	private List<Notification> notificationList = new ArrayList<Notification>(0);
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<Notification> getNotificationList() {
		return notificationList;
	}
	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}
}
