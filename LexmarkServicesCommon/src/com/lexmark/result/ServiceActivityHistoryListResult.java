package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Activity;

public class ServiceActivityHistoryListResult implements Serializable {

    private static final long serialVersionUID = 1L;
	private int totalcount;
	private List<Activity> activityList = new ArrayList<Activity>();
	
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	public List<Activity> getActivityList() {
		return activityList;
	}

	
}
