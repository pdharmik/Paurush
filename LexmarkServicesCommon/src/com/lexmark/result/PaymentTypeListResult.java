package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentTypeListResult implements Serializable {
	private static final long serialVersionUID = 7694899144909892278L;
	
	private ArrayList<String> paymentType;

	public ArrayList<String> getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(ArrayList<String> paymentType) {
		this.paymentType = paymentType;
	}


}
