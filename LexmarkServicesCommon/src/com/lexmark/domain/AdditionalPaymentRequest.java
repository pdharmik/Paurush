package com.lexmark.domain;

import java.io.Serializable;

public class AdditionalPaymentRequest implements Serializable {
	private static final long serialVersionUID = -3127565053649043985L;
	private String paymentRequestId;
	private ListOfValues paymentType;
	private int quantity;
	private double unitPrice;
	private String description;
	private double totalAmount;
	private String paymentCurrency;
	private boolean paymentRequestUpdateFlag;
	
	public boolean isPaymentRequestUpdateFlag() {
		return paymentRequestUpdateFlag;
	}
	public void setPaymentRequestUpdateFlag(boolean paymentRequestUpdateFlag) {
		this.paymentRequestUpdateFlag = paymentRequestUpdateFlag;
	}
	public String getPaymentRequestId() {
		return paymentRequestId;
	}
	public void setPaymentRequestId(String paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
	}
	
	public ListOfValues getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(ListOfValues paymentType) {
		this.paymentType = paymentType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getPaymentCurrency() {
		return paymentCurrency;
	}
	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
