package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Activity;

public class ActivityListResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private boolean countFlag;
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
	public boolean isCountFlag() {
		return countFlag;
	}
	public void setCountFlag(boolean countFlag) {
		this.countFlag = countFlag;
	}

	
	

}
