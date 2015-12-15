package com.lexmark.result;



import java.io.Serializable;

import com.lexmark.domain.Account;

public class PaymentTypeResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
