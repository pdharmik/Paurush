
package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.AccountPayable;

public class AccountPayableListResult implements Serializable{

	private static final long serialVersionUID = 1L;

	private int totalCount;
	private List<AccountPayable> accountPayableList;
	
	public AccountPayableListResult(List<AccountPayable> accountPayableList, int totalCount) {
	    this.accountPayableList = accountPayableList;
	    this.totalCount = totalCount;
    }

    public void setTotalCount(int totalcount) {
		this.totalCount = totalcount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setAccountPayableList(List<AccountPayable> accountPayableList) {
		this.accountPayableList = accountPayableList;
	}

	public List<AccountPayable> getAccountPayableList() {
		return accountPayableList;
	}
}
