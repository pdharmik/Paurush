package com.lexmark.result;

import java.io.Serializable;
import java.util.List;
import com.lexmark.domain.ServiceRequest;



public class ServiceRequestListResult implements Serializable {

	private static final long serialVersionUID = -7056715490202902546L;
	private int totalCount;
	private  List<ServiceRequest> serviceRequests;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<ServiceRequest> getServiceRequests() {
		return serviceRequests;
	}
	public void setServiceRequests(List<ServiceRequest> serviceRequests) {
		this.serviceRequests = serviceRequests;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

