package com.lexmark.services.form;

import com.lexmark.domain.ServiceRequest;

/**
 * @author Wipro 
 * @version 2.1 
 *
 */

public class ManageCHLOthersForm extends BaseForm{
	
	private ServiceRequest serviceRequest;
	private String prevSRNumber;
	private String cmArea;
    private String cmAreaValue;
	private String cmSubArea;
	private String cmSubAreaValue;
	private String notesOrComments;
	private String listOfFileTypes;
	private String attMaxCount;
	private String chlTempMaxCount;
	private String flowType;

	private String requestedFrom;	//ADded for JAN release cancel request
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
	public String getNotesOrComments() {
		return notesOrComments;
	}
	/**
	 * 
	 * @return cmSubAreaValue 
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
	/**
	 * 
	 * @param notesOrComments 
	 */
	public void setNotesOrComments(String notesOrComments) {
		this.notesOrComments = notesOrComments;
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
	public String getChlTempMaxCount() {
		return chlTempMaxCount;
	}
	/**
	 * 
	 * @param chlTempMaxCount 
	 */
	public void setChlTempMaxCount(String chlTempMaxCount) {
		this.chlTempMaxCount = chlTempMaxCount;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getFlowType() {
		return flowType;
	}
	/**
	 * 
	 * @param flowType 
	 */
	public void setFlowType(String flowType) {
		this.flowType = flowType;
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
	 * 
	 * @return cmSubAreaValue 
	 */
	public String getRequestedFrom() {
		return requestedFrom;
	}
	
	
	
		
	
		
}
