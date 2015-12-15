package com.lexmark.contract;

import java.io.Serializable;

public class NotificationDisplayOrderContract implements Serializable {

	private static final long serialVersionUID = 4792900178034391140L;
	private Integer displayOrder;
	private Integer increment;
	
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Integer getIncrement() {
		return increment;
	}
	public void setIncrement(Integer increment) {
		this.increment = increment;
	}
}
