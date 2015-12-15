package com.lexmark.contract;

import java.io.Serializable;

public class AssignedTechnicianUpdateContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
	
	String employeeId;
	String activityId;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	

}
