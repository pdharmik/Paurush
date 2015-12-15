package com.lexmark.service.impl.mock;

import java.util.Calendar;
import java.util.List;

import com.lexmark.contract.DeleteNotificationContract;
import com.lexmark.contract.NotificationDetailContract;
import com.lexmark.contract.NotificationDisplayOrderContract;
import com.lexmark.contract.SaveNotificationContract;
import com.lexmark.contract.UserNotificationContract;
import com.lexmark.contract.UserNotificationListContract;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Notification;
import com.lexmark.domain.NotificationDetail;
import com.lexmark.result.AdminNotificationListResult;
import com.lexmark.result.DeleteNotificationResult;
import com.lexmark.result.NotificationDetailResult;
import com.lexmark.result.NotificationDisplayOrderResult;
import com.lexmark.result.SaveNotificationResult;
import com.lexmark.result.UserNotificationListResult;
import com.lexmark.result.UserNotificationResult;
import com.lexmark.service.api.NotificationService;
import com.lexmark.util.CollectionSorter;

/**
 * Implementation Class of NotificationService.
 * @author Roger.Lin
 *
 */
public class NotificationServiceImpl implements NotificationService {

	public AdminNotificationListResult retrieveAdminNotificationList()
            throws Exception {
		AdminNotificationListResult result = new AdminNotificationListResult();
		List<Notification> notificationList = DomainMockDataGenerator.getNotifications();
		String sortCriteria = "displayOrder:ASENDING";
		
		CollectionSorter sorter = new CollectionSorter();
		List<Notification> sortResult = sorter.sort(notificationList, sortCriteria);
		
		int start = 0;
		int end = (1 + start)> sortResult.size()? sortResult.size(): (1 + start);

		result.setNotificationList((sortResult.subList(start, end)));
		
		result.setNotificationList(notificationList);
		result.setTotalCount(notificationList.size());
		return result;
	}

	public DeleteNotificationResult deleteNotificationDisplay(
			DeleteNotificationContract contract) throws Exception {
		Integer notificationId = contract.getNotificationId();
		Integer displayOrder = null;
		final List<Notification> notificationList = DomainMockDataGenerator.getNotifications();
		for (Notification notification : notificationList) {
			if (notificationId.intValue() == notification.getNotificationId().intValue()) {
				displayOrder = notification.getDisplayOrder();
				notificationList.remove(notification);
				break;
			}
		}
		
		int newDisplayOrder;
		for (Notification notification : notificationList) {
			if (notification.getDisplayOrder().intValue() > displayOrder.intValue()) {
				newDisplayOrder = -(notification.getDisplayOrder().intValue() - 1);
				notification.setDisplayOrder(new Integer(newDisplayOrder));
			}
		}
		
		for (Notification notification : notificationList) {
			if (notification.getDisplayOrder().intValue() < 0) {
				notification.setDisplayOrder(-(notification.getDisplayOrder().intValue()));
			}
		}
		DeleteNotificationResult result = new DeleteNotificationResult();
		result.setResult(Boolean.TRUE);
		return result;
	}

	public NotificationDetailResult retrieveNotificationDetail(
			NotificationDetailContract contract) throws Exception {
		NotificationDetailResult result = new NotificationDetailResult();
		Integer notificationId = contract.getNotificationId();
		NotificationDetail notificationDetail = new NotificationDetail();
		if (notificationId == null) {
			notificationDetail = DomainMockDataGenerator.getEmptyNotificationDetail();
		} else {
    		List<Notification> allNotifications = DomainMockDataGenerator.getNotifications();
    		for (Notification notification : allNotifications) {
    			if (notificationId.equals(notification.getNotificationId())) {
    				notificationDetail.setNotification(notification);
    				notificationDetail.setNotificationLocaleList(DomainMockDataGenerator.
    						getNotificationLocaleList(notificationId, null));
    				result.setNotificationDetail(notificationDetail);
    				return result;
    			}
    		}
		}
		result.setNotificationDetail(notificationDetail);
		return result;
	}

	public SaveNotificationResult saveNotificationDetail(
			SaveNotificationContract contract) throws Exception {
		SaveNotificationResult result = new SaveNotificationResult();
		if (contract.getNotificationDetail() != null &&
				contract.getNotificationDetail().getNotification() != null) {
			result.setSuccess(Boolean.TRUE);
		} else {
			result.setSuccess(Boolean.FALSE);
		}
		return result;
	}
	public UserNotificationListResult retrieveUserNotificationList(UserNotificationListContract contract)throws Exception{
		UserNotificationListResult result = new UserNotificationListResult();
		List<NotificationDetail> notificationDetailList = DomainMockDataGenerator.getNotificationDetailList(contract.getLocale());
		
		Calendar currentCal = Calendar.getInstance();
		Calendar dispalyCal = Calendar.getInstance();
		Calendar removeCal = Calendar.getInstance();
		
		for(int i = 0;i < notificationDetailList.size() ;i++)
		{
			dispalyCal.setTime(notificationDetailList.get(i).getNotification().getDisplayDate());
			removeCal.setTime(notificationDetailList.get(i).getNotification().getRemoveDate());
			if(currentCal.before(dispalyCal) || currentCal.after(removeCal)){
				notificationDetailList.remove(i);
			}
		}
		result.setNotificationDetailList(notificationDetailList);
		result.setTotalCount(notificationDetailList.size());
		return result;
	}
	
	public UserNotificationResult hideUserNotification(UserNotificationContract contract) throws Exception{
		UserNotificationResult result = new UserNotificationResult();
		result.setResult(Boolean.TRUE);
		return result;
	}

	public NotificationDisplayOrderResult updateNotificationDisplayOrder(
			NotificationDisplayOrderContract contract) throws Exception {
		final List<Notification> notificationList = DomainMockDataGenerator.getNotifications();
		Integer displayOrder = contract.getDisplayOrder();
		Integer increment = contract.getIncrement();
		Notification temp = null;
		for (Notification notification : notificationList) {
			if (displayOrder.intValue() + increment.intValue() == notification.getDisplayOrder().intValue()) {
				temp = notification;
				notification.setDisplayOrder(Integer.valueOf(-1));
				break;
			}
		}
		for (Notification notification : notificationList) {
			if (displayOrder.intValue() == notification.getDisplayOrder().intValue()) {
				notification.setDisplayOrder(Integer.valueOf(displayOrder.intValue() + increment.intValue()));
				break;
			}
		}
		temp.setDisplayOrder(displayOrder);
		NotificationDisplayOrderResult result = new NotificationDisplayOrderResult();
		result.setSuccessfulFlag(Boolean.TRUE);
		return result;
	}
}
