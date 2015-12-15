package com.lexmark.service.impl.real.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.contract.DeleteNotificationContract;
import com.lexmark.contract.NotificationDetailContract;
import com.lexmark.contract.NotificationDisplayOrderContract;
import com.lexmark.contract.SaveNotificationContract;
import com.lexmark.contract.UserNotificationContract;
import com.lexmark.contract.UserNotificationListContract;
import com.lexmark.domain.Notification;
import com.lexmark.domain.NotificationDetail;
import com.lexmark.domain.NotificationLocale;
import com.lexmark.domain.UserNotificationHiding;
import com.lexmark.result.AdminNotificationListResult;
import com.lexmark.result.DeleteNotificationResult;
import com.lexmark.result.NotificationDetailResult;
import com.lexmark.result.NotificationDisplayOrderResult;
import com.lexmark.result.SaveNotificationResult;
import com.lexmark.result.UserNotificationListResult;
import com.lexmark.result.UserNotificationResult;
import com.lexmark.service.api.NotificationService;
import com.lexmark.util.LocaleUtil;

public class NotificationServiceImpl implements NotificationService {
	private static final String QUERY_USER_NOTIFICATION_LIST = 
		"SELECT n.display_url, n.notification_id, nl.display_description, n.display_order " +
		"FROM notification n , notification_locale  nl, supported_locale locale " +
		" WHERE n.notification_id = nl.notification_id " +
			" AND nl.supported_locale_id = locale.supported_locale_id " +
			" AND TO_CHAR(sysdate,'yyyy-mm-dd') >= TO_CHAR(n.display_date,'yyyy-mm-dd') " +
			" AND TO_CHAR(sysdate,'yyyy-mm-dd') <= TO_CHAR(n.remove_date,'yyyy-mm-dd') " +
			" AND n.notification_id NOT IN " +
			"     (SELECT hiding.notification_id FROM user_notification_hiding hiding WHERE user_id = :user_id) " +
			" AND (locale.supported_locale_code = :language " +
			" OR locale.supported_locale_code = 'en')" +
			" ORDER BY n.display_order, " +
			" DECODE(locale.supported_locale_code, 'en',2,1)";
	
	public DeleteNotificationResult deleteNotificationDisplay(
			DeleteNotificationContract contract) throws Exception {
		DeleteNotificationResult result = new DeleteNotificationResult();
		
		Connection conn = null;
		Integer notificationId = contract.getNotificationId();
		try {			
			HibernateUtil.beginTransaction();			
			Notification  notification = (Notification) HibernateUtil.getSession().load(Notification.class, notificationId);
			Query query = HibernateUtil.getSession().createQuery("select locale from NotificationLocale locale where locale.notificationId=:notificationId");
			query.setParameter("notificationId", notificationId);
			for(Iterator it=query.iterate();it.hasNext();){
				NotificationLocale sl = (NotificationLocale) it.next();
				HibernateUtil.getSession().delete(sl);
			}
			query = HibernateUtil.getSession().createQuery("select hiding from UserNotificationHiding hiding where hiding.notificationId=:notificationId");
			query.setParameter("notificationId", notificationId);
			for(Iterator it=query.iterate();it.hasNext();){
				UserNotificationHiding sl = (UserNotificationHiding) it.next();
				HibernateUtil.getSession().delete(sl);
			}
			// Update other notifications whose display order is larger than the deleted one.
			conn = HibernateUtil.getSession().connection();
			PreparedStatement updateTarget = conn.prepareStatement("update Notification set DISPLAY_ORDER = DISPLAY_ORDER - 1 where DISPLAY_ORDER > ?");
			updateTarget.setInt(1, notification.getDisplayOrder());
			updateTarget.executeUpdate();
			updateTarget.close();
			conn.commit();
			
			HibernateUtil.getSession().delete(notification);
			HibernateUtil.getSession().flush();
			HibernateUtil.commitTransaction();
			result.setResult(true);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		finally {
			HibernateUtil.closeSession();
		}
		return result;
	}

	public UserNotificationResult hideUserNotification(
			UserNotificationContract contract) throws Exception {
		try {
			UserNotificationHiding userNotificationHiding = new UserNotificationHiding();
			userNotificationHiding.setUserId(contract.getUserId());
			userNotificationHiding.setNotificationId(contract.getNotificationId());
			HibernateUtil.beginTransaction();			
			HibernateUtil.getSession().saveOrUpdate(userNotificationHiding);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
		    HibernateUtil.closeSession();
		}
		
		UserNotificationResult result = new UserNotificationResult();
		result.setResult(true);
		return result;
	}

	public AdminNotificationListResult retrieveAdminNotificationList()
			throws Exception {
		AdminNotificationListResult result = new AdminNotificationListResult();
		List<Notification> notificationList = new ArrayList<Notification>();
		try {			
			Query query = HibernateUtil.getSession().createQuery("from Notification");
			notificationList = (List<Notification>) query.list();
			
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}finally {
		    HibernateUtil.closeSession();
		}
		result.setNotificationList(notificationList);
		result.setTotalCount(notificationList.size());
		return result;
	}

	public NotificationDetailResult retrieveNotificationDetail(
			NotificationDetailContract contract) throws Exception {
		NotificationDetailResult result = new NotificationDetailResult();
		Integer notificationId = contract.getNotificationId();
		NotificationDetail notificationDetail = new NotificationDetail();
		if (notificationId != null) {
			try {			
				Notification  notification = (Notification) HibernateUtil.getSession().get(Notification.class, notificationId);
				notificationDetail.setNotification(notification);
				
				Query query = HibernateUtil.getSession().createQuery("select locale from NotificationLocale locale where locale.notificationId=:notificationId");
				query.setParameter("notificationId", notificationId);
				for(Iterator it=query.iterate();it.hasNext();){
					NotificationLocale sl = (NotificationLocale) it.next();
					notificationDetail.getNotificationLocaleList().add(sl);
				}
			} catch (HibernateException ex) {
				throw new InfrastructureException(ex);
			} finally {
			    HibernateUtil.closeSession();
			}
		}
		result.setNotificationDetail(notificationDetail);
		return result;
	}

	public UserNotificationListResult retrieveUserNotificationList(
			UserNotificationListContract contract) throws Exception {
		UserNotificationListResult result = new UserNotificationListResult();
		String language = LocaleUtil.getSupportLocaleCode(contract.getLocale());

		String user = contract.getUserNumber();
		List<NotificationDetail> notificationDetailList = new ArrayList<NotificationDetail> ();
		try {			
			Query query = HibernateUtil.getSession().createSQLQuery(QUERY_USER_NOTIFICATION_LIST);
			query.setParameter("user_id", user);
			query.setParameter("language", language);
			List list = query.list();
			if(list != null && list.size() > 0) {
				populateQueryListValue(notificationDetailList, list);
			} 
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
		    HibernateUtil.closeSession();
		}
		result.setNotificationDetailList(notificationDetailList);
		result.setTotalCount(notificationDetailList.size());
		return result;
	}
	
	private void populateQueryListValue(List<NotificationDetail> notificationDetailList, List resultList) {
		Integer prevNotificationId = null;
		for(int i=0; i< resultList.size(); i++) {
			Object[] row = (Object[]) resultList.get(i);
			Integer notificationId = Integer.valueOf(row[1].toString());
			if (!notificationId.equals(prevNotificationId)) {
				String displayUrl = (String) row[0];
				String displayDescription = (String) row[2];
				Integer displayOrder = Integer.valueOf(row[3].toString());
				NotificationDetail notificationDetail = new NotificationDetail();
				Notification notification = new Notification();
				notification.setDisplayOrder(displayOrder);
				notification.setNotificationId(notificationId);
				notificationDetail.setNotification(notification);
				notification.setDisplayURL(displayUrl);
				NotificationLocale locale = new NotificationLocale();
				locale.setNotificationId(notificationId);
				locale.setDisplayDescription(displayDescription);
				notificationDetail.getNotificationLocaleList().add(locale);
				notificationDetailList.add(notificationDetail);
				prevNotificationId = notificationId;
			}
		}
	}

	public SaveNotificationResult saveNotificationDetail(
			SaveNotificationContract contract) throws Exception {
		SaveNotificationResult result = new SaveNotificationResult();
		if (contract.getNotificationDetail() != null &&
				contract.getNotificationDetail().getNotification() != null) {
			try {
				Notification notification = contract.getNotificationDetail().getNotification();
				HibernateUtil.beginTransaction();
				if(notification.getDisplayOrder() == null) {
					Query query = HibernateUtil.getSession().createQuery("select max(noti.displayOrder) from Notification noti");
					Iterator it=query.iterate();
					if(it.hasNext()) {
						Integer maxOrder = (Integer) it.next();
						
						// the maxOrder is null when insert the first notification
						if(maxOrder == null)
							maxOrder = 0;
						notification.setDisplayOrder(maxOrder + 1);
					} else {
						notification.setDisplayOrder(1);
					}
				}
				HibernateUtil.getSession().saveOrUpdate(notification);
				int length = contract.getNotificationDetail().getNotificationLocaleList().size();
				for(int i= length-1; i>=0; i--) {
					NotificationLocale locale = contract.getNotificationDetail().getNotificationLocaleList().get(i);
					locale.setNotificationId(notification.getNotificationId());
					if(locale.getNotificationLocaleId() == null) {
						if(isNotificationLocaleEmpty(locale)) {
							continue;
						} 
					} else {
						if(isNotificationLocaleEmpty(locale)) {
							HibernateUtil.getSession().delete(locale);
							continue;
						} 
					}
					HibernateUtil.getSession().saveOrUpdate(locale);
				}
				HibernateUtil.commitTransaction();
				result.setSuccess(true);

			} catch (HibernateException ex) {
				throw new InfrastructureException(ex);
			}finally {
			    HibernateUtil.closeSession();
			}
			
		}
		return result;
		
	}
	
	private boolean isNotificationLocaleEmpty(NotificationLocale locale) {
		if(locale == null) {
			return true;
		}
		if(locale.getDisplayDescription()== null || locale.getDisplayDescription().length()==0) {
			return true;
		}
		return false;
	}

	public NotificationDisplayOrderResult updateNotificationDisplayOrder(
			NotificationDisplayOrderContract contract) throws Exception {
		Integer displayOrder = contract.getDisplayOrder();
		Integer notificationId = null;
		Connection conn = null;
		NotificationDisplayOrderResult result = new NotificationDisplayOrderResult();
		try {
			conn = HibernateUtil.getSession().connection();
			PreparedStatement selectNotification = conn.prepareStatement("SELECT n.NOTIFICATION_ID FROM Notification n where n.DISPLAY_ORDER=?");
			selectNotification.setInt(1, displayOrder);
			java.sql.ResultSet rs = selectNotification.executeQuery();
			rs.next();
			notificationId = rs.getInt(1);
			if(notificationId == null) {
				throw new RuntimeException("no notification id for dispalyOrder" + displayOrder);
			}
			PreparedStatement updateSource = conn.prepareStatement("update Notification set DISPLAY_ORDER = ? where DISPLAY_ORDER = ?");
			updateSource.setInt(1, displayOrder + contract.getIncrement());
			updateSource.setInt(2, displayOrder);
			updateSource.executeUpdate();
			updateSource.close();
			PreparedStatement updateTarget = conn.prepareStatement("update Notification set DISPLAY_ORDER = ? where DISPLAY_ORDER = ? and NOTIFICATION_ID <> ?");
			updateTarget.setInt(1, displayOrder);
			updateTarget.setInt(2, displayOrder + contract.getIncrement());
			updateTarget.setInt(3, notificationId);
			updateTarget.executeUpdate();
			updateTarget.close();
			conn.commit();
			result.setSuccessfulFlag(Boolean.TRUE);
		} catch(Exception ex) {
			
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
		return result;
	}

}
