package com.lexmark.result;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.lexmark.domain.Account;
import com.lexmark.domain.Payment;
import com.lexmark.result.api.AccountSecuredResult;

public class PaymentDetailsResult implements Serializable, AccountSecuredResult {

	private static final long serialVersionUID = 6843608086919469417L;
	
	private Payment payment;
	private List<Account> authorizedAccounts = new LinkedList<Account>();

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
		authorizedAccounts.clear();
        authorizedAccounts.add(payment.getPartnerAccount());
	}

	@Override
	public List<Account> getAuthorizedAccounts() {
		return authorizedAccounts;
	}	
}
