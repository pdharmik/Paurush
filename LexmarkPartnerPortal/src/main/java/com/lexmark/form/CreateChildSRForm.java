package com.lexmark.form;

import com.lexmark.domain.Activity;
import com.lexmark.domain.ServiceRequest;

public class CreateChildSRForm {
	private ServiceRequest serviceRequest;
	private Activity activity;
	private String childSRStatus;
	private String fccCodeListLOV;
	private float timezoneOffset;
	
	public float getTimezoneOffset() {
		return timezoneOffset;
	}
	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	public String getFccCodeListLOV() {
		return fccCodeListLOV;
	}
	public void setFccCodeListLOV(String fccCodeListLOV) {
		this.fccCodeListLOV = fccCodeListLOV;
	}
	public String getChildSRStatus() {
		return childSRStatus;
	}
	public void setChildSRStatus(String childSRStatus) {
		this.childSRStatus = childSRStatus;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
		
}
