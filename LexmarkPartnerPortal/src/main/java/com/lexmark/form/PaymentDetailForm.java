package com.lexmark.form;

import java.io.Serializable;

import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Payment;

public class PaymentDetailForm implements Serializable {

	private static final long serialVersionUID = 1204948647841212534L;
	
	private Payment payment;
	private ListOfValues localizedPaymentStatus;
	private boolean allowAdditionalPaymentRequest;

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public ListOfValues getLocalizedPaymentStatus() {
		return localizedPaymentStatus;
	}

	public void setLocalizedPaymentStatus(ListOfValues localizedPaymentStatus) {
		this.localizedPaymentStatus = localizedPaymentStatus;
	}

	public boolean isAllowAdditionalPaymentRequest() {
		return allowAdditionalPaymentRequest;
	}

	public void setAllowAdditionalPaymentRequest(
			boolean allowAdditionalPaymentRequest) {
		this.allowAdditionalPaymentRequest = allowAdditionalPaymentRequest;
	}
	

}
