/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: AssetAcceptanceForm.java
 * Package     		: com.lexmark.services.form
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		1.0             Initial Version
 * 
 */

package com.lexmark.services.form;



import com.lexmark.domain.ServiceRequest;

/**
 * This bean class extends BaseForm and is called from AssetAcceptanceController.java
 * @author wipro
 *
 */
public class AssetAcceptanceForm extends BaseForm  {
	
	private ServiceRequest serviceRequest;
	private String prevSRNumber;
	private String listOfFileTypes;
	private String attMaxCount;
	
	private String cmArea;
    private String cmAreaValue;
	private String cmSubArea;
	private String cmSubAreaValue;
	private String notes;
	
	private String requestedFrom;
	
	/**
	 * 
	 * @return ServiceRequest 
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
	public String getPrevSRNumber() {
		
		return prevSRNumber;
	}
	/**
	 * 
	 * @param prevSRNumber 
	 */
	public void setPrevSRNumber(String prevSRNumber) {
		
		this.prevSRNumber = prevSRNumber;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getListOfFileTypes() {
		
		return listOfFileTypes;
	}
	/**
	 * 
	 * @param listOfFileTypes 
	 */
	public void setListOfFileTypes(String listOfFileTypes) {
		
		this.listOfFileTypes = listOfFileTypes;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getAttMaxCount() {
		
		return attMaxCount;
	}
	/**
	 * 
	 * @param attMaxCount 
	 */
	public void setAttMaxCount(String attMaxCount) {
		
		this.attMaxCount = attMaxCount;
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
	/**
	 * 
	 * @return String 
	 */ 
	public String getRequestedFrom() {
		
		return requestedFrom;
	}
	/**
	 * 
	 * @param requestedFrom 
	 */
	public void setRequestedFrom(String requestedFrom) {
		
		this.requestedFrom = requestedFrom;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCmArea() {
		
		return cmArea;
	}
	/**
	 * 
	 * @param cmArea 
	 */
	public void setCmArea(String cmArea) {
		
		this.cmArea = cmArea;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCmAreaValue() {
		
		return cmAreaValue;
	}
	/**
	 * 
	 * @param cmAreaValue 
	 */
	public void setCmAreaValue(String cmAreaValue) {
		
		this.cmAreaValue = cmAreaValue;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCmSubArea() {
		
		return cmSubArea;
	}
	/**
	 * 
	 * @param cmSubArea 
	 */
	public void setCmSubArea(String cmSubArea) {
		
		this.cmSubArea = cmSubArea;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCmSubAreaValue() {
		
		return cmSubAreaValue;
	}
	/**
	 * 
	 * @param cmSubAreaValue 
	 */
	public void setCmSubAreaValue(String cmSubAreaValue) {
		
		this.cmSubAreaValue = cmSubAreaValue;
	}
}
