package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.lexmark.domain.NotificationDetail;

/**
 * The class contains notification Detail list
 * get notificationDetials.
 * @author Lyn.chen
 *
 */
public class UserNotificationListResult implements Serializable {
	private static final long serialVersionUID = -1763784680249198807L;
	private List<NotificationDetail> notificationDetailList = new ArrayList<NotificationDetail>();
	private Integer totalCount;
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<NotificationDetail> getNotificationDetailList() {
		return notificationDetailList;
	}
	public void setNotificationDetailList(
			List<NotificationDetail> notificationDetailList) {
		this.notificationDetailList = notificationDetailList;
	}
	
}
