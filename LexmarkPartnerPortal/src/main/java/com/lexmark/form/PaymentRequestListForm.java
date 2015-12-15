package com.lexmark.form;

import java.util.List;

import com.lexmark.domain.ListOfValues;

public class PaymentRequestListForm {
	
	private List<ListOfValues> PaymentStatusList;
	private List<ListOfValues> requestStatusList;
	
	public List<ListOfValues> getPaymentStatusList() {
		return PaymentStatusList;
	}
	public void setPaymentStatusList(List<ListOfValues> paymentStatusList) {
		PaymentStatusList = paymentStatusList;
	}
	public List<ListOfValues> getRequestStatusList() {
		return requestStatusList;
	}
	public void setRequestStatusList(List<ListOfValues> requestStatusList) {
		this.requestStatusList = requestStatusList;
	}
	
}
