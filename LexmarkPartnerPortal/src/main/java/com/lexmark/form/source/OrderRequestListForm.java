package com.lexmark.form.source;

import java.util.Map;

public class OrderRequestListForm {
	
	
	private boolean displayRequestTypeFilterFlag;	
	private Map<String, String> orderRequestStatusMap;
	private Map<String, String> serviceTypeMap;	
	private Map<String, String> orderRequestStatusDetailMap;
	

	
	public Map<String, String> getOrderRequestStatusMap() {
		return orderRequestStatusMap;
	}

	public void setOrderRequestStatusMap(Map<String, String> orderRequestStatusMap) {
		this.orderRequestStatusMap = orderRequestStatusMap;
	}

	public boolean isDisplayRequestTypeFilterFlag() {
		return displayRequestTypeFilterFlag;
	}

	public void setDisplayRequestTypeFilterFlag(boolean displayRequestTypeFilterFlag) {
		this.displayRequestTypeFilterFlag = displayRequestTypeFilterFlag;
	}

	public Map<String, String> getServiceTypeMap() {
		return serviceTypeMap;
	}

	public void setServiceTypeMap(Map<String, String> serviceTypeMap) {
		this.serviceTypeMap = serviceTypeMap;
	}

	
	public Map<String, String> getOrderRequestStatusDetailMap() {
		return orderRequestStatusDetailMap;
	}

	public void setOrderRequestStatusDetailMap(Map<String, String> orderRequestStatusDetailMap) {
		this.orderRequestStatusDetailMap = orderRequestStatusDetailMap;
	}

}


