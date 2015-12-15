package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.ServiceRequest;

public class RequestResult implements Serializable {

	private static final long serialVersionUID = 6902498516620986849L;

	private ServiceRequest serviceRequest;
	private int totalCount;
	private String newChildSR;

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getNewChildSR() {
		return newChildSR;
	}

	public void setNewChildSR(String newChildSR) {
		this.newChildSR = newChildSR;
	}

}
