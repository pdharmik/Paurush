package com.lexmark.service.impl.real.domain;

import org.apache.commons.lang.StringUtils;

import com.amind.common.domain.BaseEntity;

public class PartnerDetailExpenseBase extends BaseEntity {
	
	private String paymentType;
	private int quantity;
	private String unitPrice;
	private String totalAmount;
	private String paymentCurrency;
	private String description;
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnitPrice() {
		if(StringUtils.isEmpty(unitPrice)){
			return "0";
		}
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getTotalAmount() {
		if(StringUtils.isEmpty(totalAmount)){
			return "0";
		}
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
			this.totalAmount = totalAmount;
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

	
	

}
