package com.lexmark.services.form;

import com.lexmark.domain.ServiceRequest;

/**
 * @author Wipro 
 * @version 2.1 
 *
 */

public class MapsRequestForm extends BaseForm{
	
	private ServiceRequest serviceRequest;
	private String prevSRNumber;
	private String cmArea;
    private String cmAreaValue;
	private String cmSubArea;
	private String cmSubAreaValue;
	private String notesOrComments;
	private String notesForNewBuilding;
	private String listOfFileTypes;
	private String attMaxCount;
	private String chlTempMaxCount;
	public String getNotesForNewBuilding() {
		return notesForNewBuilding;
	}
	public void setNotesForNewBuilding(String notesForNewBuilding) {
		this.notesForNewBuilding = notesForNewBuilding;
	}
	private String flowType;
	private boolean moveAsset;
	private boolean moveContactSelect;

	public boolean isMoveAsset() {
		return moveAsset;
	}
	public void setMoveAsset(boolean moveAsset) {
		this.moveAsset = moveAsset;
	}
	public boolean isMoveContactSelect() {
		return moveContactSelect;
	}
	public void setMoveContactSelect(boolean moveContactSelect) {
		this.moveContactSelect = moveContactSelect;
	}
	private String requestedFrom;	//ADded for JAN release cancel request
	
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	public String getPrevSRNumber() {
		return prevSRNumber;
	}
	public void setPrevSRNumber(String prevSRNumber) {
		this.prevSRNumber = prevSRNumber;
	}
	public String getCmArea() {
		return cmArea;
	}
	public void setCmArea(String cmArea) {
		this.cmArea = cmArea;
	}
	public String getCmAreaValue() {
		return cmAreaValue;
	}
	public void setCmAreaValue(String cmAreaValue) {
		this.cmAreaValue = cmAreaValue;
	}
	public String getCmSubArea() {
		return cmSubArea;
	}
	public void setCmSubArea(String cmSubArea) {
		this.cmSubArea = cmSubArea;
	}
	public String getNotesOrComments() {
		return notesOrComments;
	}
	public String getCmSubAreaValue() {
		return cmSubAreaValue;
	}
	public void setCmSubAreaValue(String cmSubAreaValue) {
		this.cmSubAreaValue = cmSubAreaValue;
	}
	public void setNotesOrComments(String notesOrComments) {
		this.notesOrComments = notesOrComments;
	}
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}
	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}
	public String getAttMaxCount() {
		return attMaxCount;
	}
	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}
	public String getChlTempMaxCount() {
		return chlTempMaxCount;
	}
	public void setChlTempMaxCount(String chlTempMaxCount) {
		this.chlTempMaxCount = chlTempMaxCount;
	}
	public String getFlowType() {
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}
	public void setRequestedFrom(String requestedFrom) {
		this.requestedFrom = requestedFrom;
	}
	public String getRequestedFrom() {
		return requestedFrom;
	}
	
	
	
		
	
		
}
