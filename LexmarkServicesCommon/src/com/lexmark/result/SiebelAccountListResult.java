package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Account;

public class SiebelAccountListResult implements Serializable {

	private static final long serialVersionUID = 2321726814911067948L;
	
	private List<Account> accountList = new ArrayList<Account>();

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

}
