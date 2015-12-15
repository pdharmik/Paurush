package com.lexmark.service.api;

import com.lexmark.contract.DeleteNotificationContract;
import com.lexmark.contract.NotificationDetailContract;
import com.lexmark.contract.NotificationDisplayOrderContract;
import com.lexmark.contract.SaveNotificationContract;
import com.lexmark.contract.UserNotificationContract;
import com.lexmark.contract.UserNotificationListContract;
import com.lexmark.result.AdminNotificationListResult;
import com.lexmark.result.DeleteNotificationResult;
import com.lexmark.result.NotificationDetailResult;
import com.lexmark.result.NotificationDisplayOrderResult;
import com.lexmark.result.SaveNotificationResult;
import com.lexmark.result.UserNotificationListResult;
import com.lexmark.result.UserNotificationResult;

/**
 * This is interface for NotificationService. It defines all services
 * that related to Notification. 
 * @author Roger.Lin
 *
 */
public interface NotificationService {
	public AdminNotificationListResult retrieveAdminNotificationList() throws Exception;

	public DeleteNotificationResult deleteNotificationDisplay(
			DeleteNotificationContract contract) throws Exception;

	public NotificationDetailResult retrieveNotificationDetail(
			NotificationDetailContract contract) throws Exception;

	public SaveNotificationResult saveNotificationDetail(
			SaveNotificationContract contract) throws Exception;
	public UserNotificationListResult retrieveUserNotificationList(
			UserNotificationListContract contract) throws Exception;
	
	public UserNotificationResult hideUserNotification(
			UserNotificationContract contract) throws Exception;

	public NotificationDisplayOrderResult updateNotificationDisplayOrder(
			NotificationDisplayOrderContract contract) throws Exception;
}
