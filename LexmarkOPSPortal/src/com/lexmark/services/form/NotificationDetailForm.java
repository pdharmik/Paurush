package com.lexmark.services.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Notification;
import com.lexmark.domain.NotificationDetail;
import com.lexmark.domain.NotificationLocale;
import com.lexmark.domain.SupportedLocale;

public class NotificationDetailForm extends BaseForm implements Serializable {

	private static final long serialVersionUID = -5446480328922292846L;
	private NotificationDetail notificationDetail;
	
	public NotificationDetail getNotificationDetail() {
		return notificationDetail;
	}
	public void setNotificationDetail(NotificationDetail notificationDetail) {
		this.notificationDetail = notificationDetail;
	}
	public void assemble(NotificationDetail notificationDetail,
			List<SupportedLocale> supportedLocales) {
		List<NotificationLocale> notificationLocaleList = notificationDetail.
		        getNotificationLocaleList();
		List<NotificationLocale> allList = new ArrayList<NotificationLocale>();
		if(notificationDetail.getNotification()==null) {
			Notification notification = new Notification();
			notificationDetail.setNotification(notification);
		}
		Integer notificationId = notificationDetail.getNotification().getNotificationId();
		boolean matched;
		for (SupportedLocale supportedLocale : supportedLocales) {
			matched = false;
			for (NotificationLocale notificationLocale : notificationLocaleList) {
				if (supportedLocale.getSupportedLocaleId().equals(notificationLocale.getSupportedLocale().getSupportedLocaleId())) {
					allList.add(notificationLocale);
					matched = true;
					break;
				}
			}
			if (!matched) {
				NotificationLocale emptyDescription = new NotificationLocale();
				emptyDescription.setSupportedLocale(supportedLocale);
				emptyDescription.setNotificationId(notificationId);
				allList.add(emptyDescription);
			}
		}
		notificationDetail.setNotificationLocaleList(allList);
		setNotificationDetail(notificationDetail);
	}
}
