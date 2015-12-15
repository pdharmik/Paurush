package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.GlobalAccount;

public class GlobalLegalEntityResult implements Serializable {

	private static final long serialVersionUID = 371012606361299836L;
	
	private GlobalAccount account;

	public void setAccount(GlobalAccount account) {
		this.account = account;
	}

	public GlobalAccount getAccount() {
		return account;
	}

}
