package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;


public class ServiceRequestAP implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	private String requestNumber;
	private double amount;
	private String currencyType;
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
}
