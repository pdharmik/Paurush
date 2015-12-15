package com.lexmark.services.form;

import com.lexmark.domain.ServiceRequest;

public class ServiceRequestListForm {
	private String serviceRequestListJSON;
	private String searchCriterias;
	private ServiceRequest serviceRequest;
	private String breadCrumb;
	private String deviceLocationTreeXMLURL;
	private String chlTreeXMLURL;
	private boolean createServiceRequestFlag ;
	
	
	public String getDeviceLocationTreeXMLURL() {
		return deviceLocationTreeXMLURL;
	}

	public void setDeviceLocationTreeXMLURL(String deviceLocationTreeXMLURL) {
		this.deviceLocationTreeXMLURL = deviceLocationTreeXMLURL;
	}

	public String getChlTreeXMLURL() {
		return chlTreeXMLURL;
	}

	public void setChlTreeXMLURL(String chlTreeXMLURL) {
		this.chlTreeXMLURL = chlTreeXMLURL;
	}

	public boolean isCreateServiceRequestFlag() {
		return createServiceRequestFlag;
	}

	public void setCreateServiceRequestFlag(boolean createServiceRequestFlag) {
		this.createServiceRequestFlag = createServiceRequestFlag;
	}

	public String getBreadCrumb() {
		return breadCrumb;
	}

	public void setBreadCrumb(String breadCrumb) {
		this.breadCrumb = breadCrumb;
	}

	public String getServiceRequestListJSON() {
		return serviceRequestListJSON;
	}

	public void setServiceRequestListJSON(String serviceRequestListJSON) {
		this.serviceRequestListJSON = serviceRequestListJSON;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public String getSearchCriterias() {
		return searchCriterias;
	}

	public void setSearchCriterias(String searchCriterias) {
		this.searchCriterias = searchCriterias;
	}
}
