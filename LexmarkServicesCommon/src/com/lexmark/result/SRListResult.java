package com.lexmark.result;

import java.io.Serializable;
import java.util.List;
import com.lexmark.domain.ServiceRequestAP;

public class SRListResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
	private List<ServiceRequestAP> serviceRequest;
	private int totalCount;

	public List<ServiceRequestAP> getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(List<ServiceRequestAP> serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
}
