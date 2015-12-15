package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.AccountContact;

public class ProcessCustomerContactContract implements Serializable {

	private static final long serialVersionUID = 5806196640786478277L;
	
	private AccountContact employeeContact;
	//Added for MPS 2.1
	private AccountContact customerContact;
	private String mdmId;
	private String mdmLevel;
	private String userContactId;
	//Ends
	public AccountContact getEmployeeContact() {
		return employeeContact;
	}

	public void setEmployeeContact(AccountContact employeeContact) {
		this.employeeContact = employeeContact;
	}
	//Added for MPS 2.1
	public void setCustomerContact(AccountContact customerContact) {
		this.customerContact = customerContact;
	}

	public AccountContact getCustomerContact() {
		return customerContact;
	}


	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}

	public String getMdmId() {
		return mdmId;
	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	public String getMdmLevel() {
		return mdmLevel;
	}
	

	public void setUserContactId(String userContactId) {
		this.userContactId = userContactId;
	}

	public String getUserContactId() {
		return userContactId;
	}
	//Ends MPS 2.1
	
}
