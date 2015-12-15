package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.AccountContact;

public class TechnicianListResult implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2886409230033784342L;
	
	private List<AccountContact> accountContactList;

	public List<AccountContact> getAccountContactList() {
		return accountContactList;
	}

	public void setAccountContactList(List<AccountContact> accountContactList) {
		this.accountContactList = accountContactList;
	}
	
	

}
