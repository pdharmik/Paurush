package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.EmailNotificationContract;
import com.lexmark.contract.EmailNotificationCreateContract;
import com.lexmark.contract.EmailNotificationDetailContract;
import com.lexmark.contract.EmailNotificationDetailForNameContract;
import com.lexmark.contract.EmailNotificationListContract;
import com.lexmark.contract.EmailNotificationLocaleContract;
import com.lexmark.domain.EmailNotification;
import com.lexmark.domain.EmailNotificationLocale;
import com.lexmark.result.EmailNotificationDetailResult;
import com.lexmark.result.EmailNotificationListResult;
import com.lexmark.result.EmailNotificationLocaleResult;
import com.lexmark.result.EmailNotificationResult;
import com.lexmark.service.api.EmailNotificationService;

public class EmailNotificationServiceImpl implements EmailNotificationService {
	private static Logger LOGGER = LogManager.getLogger(EmailNotificationServiceImpl.class);
	private static List<EmailNotification> emailNotificationList = new ArrayList<EmailNotification>();
	private static int nextId = 10;
	private static int nextEmailId = 10;
	static{
		emailNotificationList = DomainMockDataGenerator.getEmailNotificationList();
	}
	public EmailNotificationListResult retrieveEmailNotificationList(
			EmailNotificationListContract contract) throws Exception {
		EmailNotificationListResult emailNotificationListResult = new EmailNotificationListResult();
		emailNotificationListResult.setEmailNotificationList(emailNotificationList);
		
		return emailNotificationListResult;
	}

	public EmailNotificationResult deleteEmailNotificationDisplay(
			EmailNotificationContract contract) throws Exception {
		Integer emailNotificationId = contract.getEmailNotificationId();
		EmailNotificationResult result = new EmailNotificationResult();
		for (EmailNotification emailNotification:emailNotificationList){
			if(emailNotification.getEmailNotificationId().equals(emailNotificationId)){
				emailNotificationList.remove(emailNotification);
				result.setResult(Boolean.TRUE);
				break;
			}
		}
		return result;
	}

	public EmailNotificationDetailResult retrieveEmailNotificationDetail(
			EmailNotificationDetailContract contract) throws Exception {
		EmailNotificationDetailResult result = new EmailNotificationDetailResult();
		if(null == contract.getEmailNotificationId()||"".equals(contract.getEmailNotificationId())){
			List<EmailNotificationLocale> emailNotificationLocaleFields = new ArrayList<EmailNotificationLocale>();
			for(int m =0;m < 5;m++){
				emailNotificationLocaleFields.add(DomainMockDataGenerator.getEmailNotificationLocaleFields(m));
			}
			EmailNotification emailNotification = new EmailNotification();
			emailNotification.setEmailNotificationLocaleList(emailNotificationLocaleFields);
			result.setEmailNotification(emailNotification);
		}else{
			for(EmailNotification emailNotification :emailNotificationList){
				if(emailNotification.getEmailNotificationId().equals(contract.getEmailNotificationId())){
					result.setEmailNotification(emailNotification);
					break;
				}
			}
		}
		return result;
	}

	public EmailNotificationLocaleResult deleteEmailNotificationLocale(
			EmailNotificationLocaleContract contract) throws Exception {
		Integer emailNotificationLocaleId = contract.getEmailNotificationLocaleId();
		EmailNotificationLocaleResult result = new EmailNotificationLocaleResult();
		for (EmailNotification emailNotification:emailNotificationList){
			for(EmailNotificationLocale emailNotificationLocale:emailNotification.getEmailNotificationLocaleList()){
				if(emailNotificationLocale.getEmailNotificationLocaleId().equals(emailNotificationLocaleId)){
					emailNotificationLocale.setEmailHeader("");
					emailNotificationLocale.setEmailBody("");
					emailNotificationLocale.setEmailFooter("");
					result.setResult(Boolean.TRUE);
					return result;
				}
			}
		}
		return result;
	}

	public EmailNotificationLocaleResult saveEmailNotificationLocale(
			EmailNotificationLocaleContract contract) throws Exception {
		EmailNotificationLocale emailNotificationLocale = contract.getEmailNotificationLocale();
		EmailNotificationLocaleResult result = new EmailNotificationLocaleResult();
		
		LOGGER.info("_____________________________________save :"+emailNotificationLocale.getEmailNotificationLocaleId());
		for (EmailNotification emailNotification:emailNotificationList){
			for(int i = 0;i<emailNotification.getEmailNotificationLocaleList().size();i++){
				if(emailNotification.getEmailNotificationLocaleList().get(i).getEmailNotificationLocaleId().equals(emailNotificationLocale.getEmailNotificationLocaleId())){
					emailNotification.getEmailNotificationLocaleList().get(i).setEmailHeader(emailNotificationLocale.getEmailHeader());
					emailNotification.getEmailNotificationLocaleList().get(i).setEmailBody(emailNotificationLocale.getEmailBody());
					emailNotification.getEmailNotificationLocaleList().get(i).setEmailFooter(emailNotificationLocale.getEmailFooter());
					result.setResult(Boolean.TRUE);
					return result;
				}
			}
		}	
		
		return result;
	}
	public EmailNotificationLocaleResult createEmailNotification(
			 EmailNotificationCreateContract contract) throws Exception {
		//TODO
		/*
		Integer localeID[] = {10000,10001,10002,10003,10004};
		String localeName[] = {"English","Spanish","French","German","Japanse"};
		EmailNotificationLocale emailNotificationLocale = contract.getEmailNotificationLocale();
		EmailNotification emailNotification = new EmailNotification();
		emailNotification.setEmailNotificationId(new Integer("123456789"+nextId));
		emailNotification.setEmailName(contract.getEmailName());
		emailNotification.setEmailDescription(contract.getEmailDescription());
		for(int n =0;n<5;n++){
			if(localeID[n].equals(emailNotificationLocale.getLocale().getSupportedLocaleId())){
				emailNotificationLocale.setEmailNotificationLocaleId(new Integer("987654321"+nextId+n));
				//emailNotificationLocale.setEmailNotificationId(emailNotification.getEmailNotificationId());
				emailNotification.setEmailName(contract.getEmailName());
				emailNotification.setEmailDescription(contract.getEmailDescription());
				emailNotification.getEmailNotificationLocaleList().add(emailNotificationLocale);
			}else{
				EmailNotificationLocale locale =new EmailNotificationLocale();
				locale.setEmailNotificationLocaleId(new Integer("987654321"+nextId+n));
				//locale.setEmailNotificationId(emailNotification.getEmailNotificationId());
				//locale.setLocaleId(localeID[n]);
				//locale.setLocaleName(localeName[n]);
				emailNotification.getEmailNotificationLocaleList().add(locale);
			}
		}
		emailNotificationList.add(emailNotification);*/
		EmailNotificationLocaleResult result = new EmailNotificationLocaleResult();
		result.setResult(Boolean.TRUE);
		nextId++;
		return result;
	}

	@Override
	public EmailNotificationDetailResult retrieveEmailNotificationDetailByName(
			EmailNotificationDetailForNameContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
