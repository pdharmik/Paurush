package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.Activity;

public class PaymentLineItemDetailsResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	List<Activity> activities;
	int totalCount;

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}	
	
}
