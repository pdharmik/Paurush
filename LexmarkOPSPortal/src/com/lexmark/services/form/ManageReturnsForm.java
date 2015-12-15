/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageReturnsForm.java
 * Package     		: com.lexmark.services.form
 * Creation Date 	: 1st May 2012
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Sagar Sarkar			1st May 2012 		1.0             Initial Version
 * 
 *
 */
package com.lexmark.services.form;

import java.io.Serializable;

import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;

public class ManageReturnsForm extends BaseForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3673887667556162075L;
	
	private ServiceRequest serviceRequest;
	private GenericAddress returnAddress;
	private String returnsSubArea;
	private String returnsSubAreaValue;
	private String notes;
	
	
	
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public GenericAddress getReturnAddress() {
		return returnAddress;
	}
	public void setReturnAddress(GenericAddress returnAddress) {
		this.returnAddress = returnAddress;
	}
	public String getReturnsSubArea() {
		return returnsSubArea;
	}
	public void setReturnsSubArea(String returnsSubArea) {
		this.returnsSubArea = returnsSubArea;
	}
	public String getReturnsSubAreaValue() {
		return returnsSubAreaValue;
	}
	public void setReturnsSubAreaValue(String returnsSubAreaValue) {
		this.returnsSubAreaValue = returnsSubAreaValue;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
	
}
