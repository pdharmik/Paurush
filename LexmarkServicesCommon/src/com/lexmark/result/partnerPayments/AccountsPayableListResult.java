package com.lexmark.result.partnerPayments;

import java.io.Serializable;
import java.util.List;
import com.lexmark.domain.AccountsPayable;

public class AccountsPayableListResult implements Serializable{
	private static final long serialVersionUID = 1L;

	private int totalCount;
	private List<AccountsPayable> accountsPayableList;
	
	

    public void setTotalCount(int totalcount) {
		this.totalCount = totalcount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setAccountsPayableList(List<AccountsPayable> accountsPayableList) {
		this.accountsPayableList = accountsPayableList;
	}

	public List<AccountsPayable> getAccountsPayableList() {
		return accountsPayableList;
	}
}