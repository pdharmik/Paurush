package com.lexmark.contract;

import java.io.Serializable;

public class SwapCategoryOrderNumberContract implements Serializable {

	private static final long serialVersionUID = 7530928649655376512L;
	private int orderNumber;
	private int increment;
	private String categoryType; // valid types: D (document category), and R (report category)
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getIncrement() {
		return increment;
	}
	public void setIncrement(int increment) {
		this.increment = increment;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
}
