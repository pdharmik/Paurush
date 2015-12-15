package com.lexmark.services.form;

import com.lexmark.domain.ServiceRequest;

public class RequestDetailsForm {
	private ServiceRequest serviceRequest;
	private String creationDate;
	private ShipmentForm pendingRequest;

	/**
	 * @param serviceRequest 
	 */
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	/**
	 * @return serviceRequest 
	 */
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	/**
	 * @param creationDate 
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return String 
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param pendingRequest 
	 */
	public void setPendingRequest(ShipmentForm pendingRequest) {
		this.pendingRequest = pendingRequest;
	}

	/**
	 * @return ShipmentForm 
	 */
	public ShipmentForm getPendingRequest() {
		return pendingRequest;
	}
}
