package com.lexmark.domain;

import java.io.Serializable;

public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 6739978157401790422L;

	private String orderNumber;
	private AccountContact customer;
	private Activity activity;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public AccountContact getCustomer() {
		return customer;
	}

	public void setCustomer(AccountContact customer) {
		this.customer = customer;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
