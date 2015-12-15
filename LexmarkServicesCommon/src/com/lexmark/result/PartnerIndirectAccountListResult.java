package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Account;

public class PartnerIndirectAccountListResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5513667656763130854L;
	private List<Account> accountList = new ArrayList<Account>();
	private int totalCount;
	
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	
	

}
