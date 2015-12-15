package com.lexmark.contract;

import java.io.Serializable;

public class PartnerNotificationsContract implements Serializable{
	
	private static final long serialVersionUID = 7511464589741634453L;
	
	private String serviceRequestId;
	private String emailAddress;

	public String getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
