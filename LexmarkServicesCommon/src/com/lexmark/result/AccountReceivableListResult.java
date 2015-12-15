
package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.AccountPayable;
import com.lexmark.domain.AccountReceivable;

public class AccountReceivableListResult implements Serializable{

	private static final long serialVersionUID = 1L;

	private int totalCount;
	private List<AccountReceivable> accountReceivableList;
	
	public AccountReceivableListResult(List<AccountReceivable> accountReceivableList, int totalCount) {
	    this.accountReceivableList = accountReceivableList;
	    this.totalCount = totalCount;
    }

    public void setTotalCount(int totalcount) {
		this.totalCount = totalcount;
	}

	public int getTotalCount() {
		return totalCount;
	}

    public List<AccountReceivable> getAccountReceivableList() {
        return accountReceivableList;
    }

    public void setAccountReceivableList(List<AccountReceivable> accountReceivableList) {
        this.accountReceivableList = accountReceivableList;
    }
}
