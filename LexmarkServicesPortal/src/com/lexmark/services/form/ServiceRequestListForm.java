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
	
	/**
	 * 
	 * @return String 
	 */
	public String getDeviceLocationTreeXMLURL() {
		return deviceLocationTreeXMLURL;
	}
/**
 * 
 * @param deviceLocationTreeXMLURL 
 */
	public void setDeviceLocationTreeXMLURL(String deviceLocationTreeXMLURL) {
		this.deviceLocationTreeXMLURL = deviceLocationTreeXMLURL;
	}
/**
 * 
 * @return String 
 */
	public String getChlTreeXMLURL() {
		return chlTreeXMLURL;
	}
/**
 * 
 * @param chlTreeXMLURL 
 */
	public void setChlTreeXMLURL(String chlTreeXMLURL) {
		this.chlTreeXMLURL = chlTreeXMLURL;
	}
/**
 * 
 * @return Boolean 
 */
	public boolean isCreateServiceRequestFlag() {
		return createServiceRequestFlag;
	}
/**
 * 
 * @param createServiceRequestFlag 
 */
	public void setCreateServiceRequestFlag(boolean createServiceRequestFlag) {
		this.createServiceRequestFlag = createServiceRequestFlag;
	}
/**
 * 
 * @return String 
 */
	public String getBreadCrumb() {
		return breadCrumb;
	}
/**
 * 
 * @param breadCrumb 
 */
	public void setBreadCrumb(String breadCrumb) {
		this.breadCrumb = breadCrumb;
	}
/**
 * 
 * @return String 
 */
	public String getServiceRequestListJSON() {
		return serviceRequestListJSON;
	}
/**
 * 
 * @param serviceRequestListJSON 
 */
	public void setServiceRequestListJSON(String serviceRequestListJSON) {
		this.serviceRequestListJSON = serviceRequestListJSON;
	}
/**
 * 
 * @return serviceRequest 
 */
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
/**
 * 
 * @param serviceRequest 
 */
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
/**
 * 
 * @return String 
 */
	public String getSearchCriterias() {
		return searchCriterias;
	}
/**
 * 
 * @param searchCriterias 
 */
	public void setSearchCriterias(String searchCriterias) {
		this.searchCriterias = searchCriterias;
	}
}
