package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.ServiceRequest;

public class ServiceRequestResult implements Serializable {
	private static final long serialVersionUID = -5309057435006560112L;
	private ServiceRequest serviceRequest;
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

}
