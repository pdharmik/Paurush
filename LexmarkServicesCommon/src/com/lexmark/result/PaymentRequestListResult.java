package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Activity;

public class PaymentRequestListResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private int totalcount;
	private List<Activity> paymentRequestList = new ArrayList<Activity>();
	
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public List<Activity> getPaymentRequestList() {
		return paymentRequestList;
	}
	public void setPaymentRequestList(List<Activity> paymentRequestList) {
		this.paymentRequestList = paymentRequestList;
	}


}
