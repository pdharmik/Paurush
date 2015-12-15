package com.lexmark.domain;

import java.io.Serializable;

public class Price implements Serializable{
	private static final long serialVersionUID = 1L;	
	private String contractLineItemId;
	private String sourceReferenceLineId;
	private String partNumber;
	private String price;
	private String currency;
	private String tax;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	
	public String getContractLineItemId() {
		return contractLineItemId;
	}
	public void setContractLineItemId(String contractLineItemId) {
		this.contractLineItemId = contractLineItemId;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getSourceReferenceLineId() {
		return sourceReferenceLineId;
	}
	public void setSourceReferenceLineId(String sourceReferenceLineId) {
		this.sourceReferenceLineId = sourceReferenceLineId;
	}
	
}
