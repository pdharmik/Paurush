package com.lexmark.contract;

import java.io.Serializable;

public class LdapUserDataContract implements Serializable {

	private static final long serialVersionUID = -3898772440088155325L;
	private String emailAddress;
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
}
