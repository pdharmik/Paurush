package com.lexmark.result.partnerPayments;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.AccountPayable;

public class AccountsRecievableListResult implements Serializable{

	private static final long serialVersionUID = 1L;

	private int totalCount;
	private List<AccountPayable> accountsRecievableList;

    public List<AccountPayable> getAccountsRecievableList() {
		return accountsRecievableList;
	}

	public void setAccountsRecievableList(
			List<AccountPayable> accountsRecievableList) {
		this.accountsRecievableList = accountsRecievableList;
	}

	public void setTotalCount(int totalcount) {
		this.totalCount = totalcount;
	}

	public int getTotalCount() {
		return totalCount;
	}	
}
