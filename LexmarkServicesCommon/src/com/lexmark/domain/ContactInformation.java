package com.lexmark.domain;

import java.io.Serializable;

public class ContactInformation implements Serializable {

	private static final long serialVersionUID = 8203616292492748790L;
	
	private String roleName;
	private String contactData;
	private String emailAddress;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getContactData() {
		return contactData;
	}
	public void setContactData(String contactData) {
		this.contactData = contactData;
	}
}
