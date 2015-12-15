package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ServiceRequest;

public class RequestListResult implements Serializable {

	private static final long serialVersionUID = 8563048146829065525L;

	private int totalCount;
	private List<ServiceRequest> requestList = new ArrayList<ServiceRequest>();

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setRequestList(List<ServiceRequest> requestList) {
		this.requestList = requestList;
	}

	public List<ServiceRequest> getRequestList() {
		return requestList;
	}


}
