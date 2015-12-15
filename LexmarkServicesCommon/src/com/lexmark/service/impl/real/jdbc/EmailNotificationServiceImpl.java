package com.lexmark.service.impl.real.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.lexmark.contract.DocumentConfigurePathResult;
import com.lexmark.contract.EmailNotificationContract;
import com.lexmark.contract.EmailNotificationCreateContract;
import com.lexmark.contract.EmailNotificationDetailContract;
import com.lexmark.contract.EmailNotificationDetailForNameContract;
import com.lexmark.contract.EmailNotificationListContract;
import com.lexmark.contract.EmailNotificationLocaleContract;
import com.lexmark.domain.ConfigurationItem;
import com.lexmark.domain.EmailNotification;
import com.lexmark.domain.EmailNotificationLocale;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.result.EmailNotificationDetailResult;
import com.lexmark.result.EmailNotificationListResult;
import com.lexmark.result.EmailNotificationLocaleResult;
import com.lexmark.result.EmailNotificationResult;
import com.lexmark.service.api.EmailNotificationService;
import com.lexmark.service.impl.mock.DomainMockDataGenerator;

public class EmailNotificationServiceImpl implements EmailNotificationService {

	public EmailNotificationLocaleResult createEmailNotification(
			EmailNotificationCreateContract contract) throws Exception {
		EmailNotificationLocaleResult result = new EmailNotificationLocaleResult();
		try{
			HibernateUtil.beginTransaction();
			
			HibernateUtil.getSession().save(contract.getEmailNotification());
			
			HibernateUtil.commitTransaction();
			result.setResult(Boolean.TRUE);
		}catch (Exception e) {
			result.setResult(Boolean.FALSE);
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}
		result.setResult(Boolean.TRUE);
		return result;
	}

	public EmailNotificationResult deleteEmailNotificationDisplay(
			EmailNotificationContract contract) throws Exception {
		String emailNotificationId = String.valueOf(contract.getEmailNotificationId());
		EmailNotificationResult result = new EmailNotificationResult();
		try{
			HibernateUtil.beginTransaction();
			
			EmailNotification emailNotification = new EmailNotification();
			Query query = HibernateUtil.getSession().createQuery("from EmailNotification where emailNotificationId=?");
			query.setString(0,emailNotificationId);
			emailNotification = (EmailNotification)query.list().get(0);
			HibernateUtil.getSession().delete(emailNotification);
			
			HibernateUtil.commitTransaction();
			result.setResult(Boolean.TRUE);
		}catch (Exception e) {
			result.setResult(Boolean.FALSE);
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}

		return result;
	}

	public EmailNotificationLocaleResult deleteEmailNotificationLocale(
			EmailNotificationLocaleContract contract) throws Exception {
		String emailNotificationLocaleId = String.valueOf(contract.getEmailNotificationLocaleId());
		EmailNotificationLocaleResult result = new EmailNotificationLocaleResult();
		try{
			HibernateUtil.beginTransaction();
			
			Query query = HibernateUtil.getSession().createQuery("UPDATE EmailNotificationLocale SET emailSubject='',emailHeader ='',emailBody='',emailFooter='' where emailNotificationLocaleId=?");
			query.setString(0,emailNotificationLocaleId);
			query.executeUpdate();
			
			HibernateUtil.commitTransaction();
			result.setResult(Boolean.TRUE);
		}catch (Exception e) {
			result.setResult(Boolean.FALSE);
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}

		return result;
	}

	public EmailNotificationDetailResult retrieveEmailNotificationDetail(
			EmailNotificationDetailContract contract) throws Exception {
		
		EmailNotificationDetailResult result = new EmailNotificationDetailResult();
		if(null == contract.getEmailNotificationId()||"".equals(contract.getEmailNotificationId())){
			HibernateUtil.beginTransaction();
			Query query = HibernateUtil.getSession().createQuery("from SupportedLocale");
			List<SupportedLocale> list = query.list();
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession();
			EmailNotification emailNotification = new EmailNotification();
			List<EmailNotificationLocale> emailNotificationLocaleList = new ArrayList<EmailNotificationLocale>();
			
			for(SupportedLocale locale:list){
				EmailNotificationLocale emailNotificationLocale = new EmailNotificationLocale();
				emailNotificationLocale.setEmailNotification(emailNotification);
				emailNotificationLocale.setLocale(locale);
				emailNotificationLocaleList.add(emailNotificationLocale);
			}
			emailNotification.setEmailNotificationLocaleList(emailNotificationLocaleList);
			result.setEmailNotification(emailNotification);
		}else{
			HibernateUtil.beginTransaction();
			Query query = HibernateUtil.getSession().createQuery("from EmailNotification where emailNotificationId=?");
			query.setString(0, contract.getEmailNotificationId());
			List<EmailNotification> list = query.list();
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession(); 
			if(list != null && list.size() >0) {
				result.setEmailNotification(list.get(0));
			}
		}
		return result;
	}

	public EmailNotificationListResult retrieveEmailNotificationList(
			EmailNotificationListContract contract) throws Exception {
		EmailNotificationListResult emailNotificationListResult = new EmailNotificationListResult();
		HibernateUtil.beginTransaction();
		Query query = HibernateUtil.getSession().createQuery("from EmailNotification");
		List list = query.list();
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		emailNotificationListResult.setEmailNotificationList(list);
		
		return emailNotificationListResult;
	}

	public EmailNotificationLocaleResult saveEmailNotificationLocale(
			EmailNotificationLocaleContract contract) throws Exception {
		EmailNotificationLocaleResult result = new EmailNotificationLocaleResult();
		try{
			HibernateUtil.beginTransaction();
			HibernateUtil.getSession().saveOrUpdate(contract.getEmailNotification());
			result.setResult(Boolean.TRUE);
			HibernateUtil.commitTransaction();
		}catch (Exception e) {
			result.setResult(Boolean.FALSE);
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}
		
		return result;
	}

	@Override
	public EmailNotificationDetailResult retrieveEmailNotificationDetailByName(
			EmailNotificationDetailForNameContract contract) throws Exception {
		EmailNotificationDetailResult result = new EmailNotificationDetailResult();

		try{
			Query query = HibernateUtil.getSession().createQuery("from EmailNotification where emailName=?");
			query.setString(0, contract.getEmailNotificationName());
			List<EmailNotification> list = query.list();
			if(list != null && list.size()>0) {
				result.setEmailNotification(list.get(0));
			}
		}catch (Exception e) {
			throw new InfrastructureException(e);
		}finally{
			HibernateUtil.closeSession();
		}		
		return result;
	}
}
