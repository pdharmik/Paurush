package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Account;

public class CustomerAccountListResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9113351240417317321L;
	private int totalCount;
	private List<Account> accountList = new ArrayList<Account>();

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	

}
