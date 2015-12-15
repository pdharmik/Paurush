package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ServiceRequestActivity;

public class PartnerNotificationsResult implements Serializable{

	private static final long serialVersionUID = 1596287348257376176L;
	
	private List<ServiceRequestActivity> serviceRequestActivityList = new ArrayList<ServiceRequestActivity >();
	
	public List<ServiceRequestActivity> getServiceRequestActivityList() {
		return serviceRequestActivityList;
	}
	public void setServiceRequestActivityList(
			List<ServiceRequestActivity> serviceRequestActivityList) {
		this.serviceRequestActivityList = serviceRequestActivityList;
	}

}
