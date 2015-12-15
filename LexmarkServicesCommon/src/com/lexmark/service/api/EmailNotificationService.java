package com.lexmark.service.api;

import com.lexmark.contract.EmailNotificationContract;
import com.lexmark.contract.EmailNotificationCreateContract;
import com.lexmark.contract.EmailNotificationDetailContract;
import com.lexmark.contract.EmailNotificationDetailForNameContract;
import com.lexmark.contract.EmailNotificationListContract;
import com.lexmark.contract.EmailNotificationLocaleContract;
import com.lexmark.result.EmailNotificationDetailResult;
import com.lexmark.result.EmailNotificationListResult;
import com.lexmark.result.EmailNotificationLocaleResult;
import com.lexmark.result.EmailNotificationResult;

public interface EmailNotificationService {
	public EmailNotificationListResult retrieveEmailNotificationList (EmailNotificationListContract contract) throws Exception;
	public EmailNotificationResult deleteEmailNotificationDisplay (EmailNotificationContract contract) throws Exception;
	public EmailNotificationDetailResult retrieveEmailNotificationDetail (EmailNotificationDetailContract contract) throws Exception;
	public EmailNotificationDetailResult retrieveEmailNotificationDetailByName (EmailNotificationDetailForNameContract contract) throws Exception;
	public EmailNotificationLocaleResult deleteEmailNotificationLocale (EmailNotificationLocaleContract contract) throws Exception;
	public EmailNotificationLocaleResult saveEmailNotificationLocale (EmailNotificationLocaleContract contract) throws Exception;
	public EmailNotificationLocaleResult createEmailNotification (EmailNotificationCreateContract contract) throws Exception;
}
