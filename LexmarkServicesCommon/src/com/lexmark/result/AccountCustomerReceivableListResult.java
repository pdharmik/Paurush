package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.AccountCustomerReceivable;

public class AccountCustomerReceivableListResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int totalCount;
	private List<AccountCustomerReceivable> accountReceivableList;
	
	public AccountCustomerReceivableListResult(List<AccountCustomerReceivable> accountReceivableList, int totalCount) {
	    this.accountReceivableList = accountReceivableList;
	    this.totalCount = totalCount;
    }

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<AccountCustomerReceivable> getAccountReceivableList() {
		return accountReceivableList;
	}

	public void setAccountReceivableList(
			List<AccountCustomerReceivable> accountReceivableList) {
		this.accountReceivableList = accountReceivableList;
	}
	
	

}
