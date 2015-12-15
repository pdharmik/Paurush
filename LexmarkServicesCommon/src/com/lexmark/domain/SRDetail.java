package com.lexmark.domain;

import java.io.Serializable;

public class SRDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7916965827069806785L;
	private String partNo;
	private String suppliesDesc;
	private int qty;
	private double partFee;
	private double fulfillmentFee;
	private double amount;
	
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getSuppliesDesc() {
		return suppliesDesc;
	}
	public void setSuppliesDesc(String suppliesDesc) {
		this.suppliesDesc = suppliesDesc;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPartFee() {
		return partFee;
	}
	public void setPartFee(double partFee) {
		this.partFee = partFee;
	}
	public double getFulfillmentFee() {
		return fulfillmentFee;
	}
	public void setFulfillmentFee(double fulfillmentFee) {
		this.fulfillmentFee = fulfillmentFee;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
