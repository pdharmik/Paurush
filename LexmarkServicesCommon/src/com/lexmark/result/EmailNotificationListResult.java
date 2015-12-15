package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.EmailNotification;

public class EmailNotificationListResult implements Serializable{
	private static final long serialVersionUID = -6357830724992727918L;
	
	private List<EmailNotification> emailNotificationList = new ArrayList<EmailNotification>();

	public List<EmailNotification> getEmailNotificationList() {
		return emailNotificationList;
	}

	public void setEmailNotificationList(
			List<EmailNotification> emailNotificationList) {
		this.emailNotificationList = emailNotificationList;
	}
	

}
