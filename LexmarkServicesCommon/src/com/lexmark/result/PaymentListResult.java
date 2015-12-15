
package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.Payment;

public class PaymentListResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8191347567155881210L;

	private int totalcount;
	private List<Payment> paymentList;
	
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}
	
}
