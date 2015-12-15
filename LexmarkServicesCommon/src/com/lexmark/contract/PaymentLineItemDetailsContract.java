package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.SearchContractBase;

public class PaymentLineItemDetailsContract extends SearchContractBase implements Serializable{
	
	private static final long serialVersionUID = -985440688913395076L;
	
	private String paymentId;
	private String serviceSessionId;

	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getServiceSessionId() {
		return serviceSessionId;
	}
	public void setServiceSessionId(String serviceSessionId) {
		this.serviceSessionId = serviceSessionId;
	}

}
