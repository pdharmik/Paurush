package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.SecureContract;

public class PaymentDetailsContract extends SecureContract implements Serializable{
	private static final long serialVersionUID = -2147754655498117913L;

	private String paymentId;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}	
}
