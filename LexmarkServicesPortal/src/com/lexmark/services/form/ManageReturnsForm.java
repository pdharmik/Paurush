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
	 * @return returnAddress 
	 */
	public GenericAddress getReturnAddress() {
		return returnAddress;
	}
	/**
	 * 
	 * @param returnAddress 
	 */
	public void setReturnAddress(GenericAddress returnAddress) {
		this.returnAddress = returnAddress;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getReturnsSubArea() {
		return returnsSubArea;
	}
	/**
	 * 
	 * @param returnsSubArea 
	 */
	public void setReturnsSubArea(String returnsSubArea) {
		this.returnsSubArea = returnsSubArea;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getReturnsSubAreaValue() {
		return returnsSubAreaValue;
	}
	/**
	 * 
	 * @param returnsSubAreaValue 
	 */
	public void setReturnsSubAreaValue(String returnsSubAreaValue) {
		this.returnsSubAreaValue = returnsSubAreaValue;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * 
	 * @param notes 
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
	
}
