package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * The mapping file: do-partneractivitydetail-mapping.xml 
 */
public class PartnerActivityDetailDo extends PartnerActivityDo {
	private static final long serialVersionUID = -4588416746863898854L;
	
	private String repairedSerialNumber;
	private String actionNarrative;
	private String actualFailureCode;
	private String AddlServiceReq;
	private String comments;
	private String customerRequestResponse;
	private String sPReferenceNum;
	private String statusAsOf;
	private String technician;
	private String serviceActivityFlag;
	private String serviceInstruction;
	private Boolean recommendedFlag;
	private ArrayList<PartnerActivityDetailExpenseDo> expenses;
	private ArrayList<PartnerActivityDetailInstructionDo> instructions;
	private ArrayList<PartnerActivityDetailNoteDo> notes;
	private ArrayList<PartnerActivityDetailOrderLineDo> orderLines;
	private ArrayList<PartnerActivityDetailPartDo> parts;
	private ArrayList<PartnerActivityDetailRepairedDeviceDo> repairedDevices;
	private ArrayList<PartnerActivityDetailInstalledAssetDo> installedDevices;
	private ArrayList<PartnerActivityDetailFlagDo> flags;
	private ArrayList<PartnerActivityAssetReadingDo> readings;
	private ArrayList<PartnerActivityNoteInstructionDo> noteDetails;
	private ArrayList<PartnerActivityContactInfoDo> contacts;
	private ArrayList<PartnerServiceRequestDetailAttachmentDo> attachments;
	private ArrayList<PartnerActivityCustomerProfileDo> customerProfile;
	private ArrayList<PartnerActivityDetailPartOptionsDo> partsOptions;
	private String requestorFirstName;
	private String requestorLastName;
	private String requestorEmail;
	private String requestorWorkPhone;
	
	private ArrayList<ServiceRequestActivityAttachmentsDo> serviceRequestAttachmentss;
	private String coordinatesXPreDebriefRFV;
	private String coordinatesYPreDebriefRFV;
	private String levelOfDetails;
	private String lbsAddressLod;
	private String lbsBuildingId;
	private String lbsFloorId;
	private String lbsFloorLod;
	
	public String getLbsFloorLod() {
		return lbsFloorLod;
	}

	public void setLbsFloorLod(String lbsFloorLod) {
		this.lbsFloorLod = lbsFloorLod;
	}

	public String getLbsBuildingId() {
		return lbsBuildingId;
	}

	public void setLbsBuildingId(String lbsBuildingId) {
		this.lbsBuildingId = lbsBuildingId;
	}

	public String getLbsFloorId() {
		return lbsFloorId;
	}

	public void setLbsFloorId(String lbsFloorId) {
		this.lbsFloorId = lbsFloorId;
	}
	
	public String getCoordinatesXPreDebriefRFV() {
			return coordinatesXPreDebriefRFV;
		}

		public void setCoordinatesXPreDebriefRFV(String coordinatesXPreDebriefRFV) {
			this.coordinatesXPreDebriefRFV = coordinatesXPreDebriefRFV;
		}

		public String getCoordinatesYPreDebriefRFV() {
			return coordinatesYPreDebriefRFV;
		}

		public void setCoordinatesYPreDebriefRFV(String coordinatesYPreDebriefRFV) {
			this.coordinatesYPreDebriefRFV = coordinatesYPreDebriefRFV;
	}

	public ArrayList<PartnerActivityCustomerProfileDo> getCustomerProfile() {
		return customerProfile;
	}
	public void setCustomerProfile(
			ArrayList<PartnerActivityCustomerProfileDo> customerProfile) {
		this.customerProfile = customerProfile;
	}
	public ArrayList<PartnerActivityContactInfoDo> getContacts() {
		return contacts;
	}
	public void setContacts(ArrayList<PartnerActivityContactInfoDo> contacts) {
		this.contacts = contacts;
	}
	public ArrayList<PartnerActivityNoteInstructionDo> getNoteDetails() {
		return noteDetails;
	}
	public void setNoteDetails(
			ArrayList<PartnerActivityNoteInstructionDo> noteDetails) {
		this.noteDetails = noteDetails;
	}
	public ArrayList<PartnerActivityAssetReadingDo> getReadings() {
		return readings;
	}
	public void setReadings(ArrayList<PartnerActivityAssetReadingDo> readings) {
		this.readings = readings;
	}

	public ArrayList<PartnerServiceRequestDetailAttachmentDo> getAttachments() {
		return attachments;
	}
	public void setAttachments(
			ArrayList<PartnerServiceRequestDetailAttachmentDo> attachments) {
		this.attachments = attachments;
	}
	public ArrayList<PartnerActivityDetailExpenseDo> getExpenses() {
		return expenses;
	}
	public void setExpenses(ArrayList<PartnerActivityDetailExpenseDo> expenses) {
		this.expenses = expenses;
	}
	public ArrayList<PartnerActivityDetailInstructionDo> getInstructions() {
		return instructions;
	}
	public void setInstructions(
			ArrayList<PartnerActivityDetailInstructionDo> instructions) {
		this.instructions = instructions;
	}
	public ArrayList<PartnerActivityDetailNoteDo> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<PartnerActivityDetailNoteDo> notes) {
		this.notes = notes;
	}
	public ArrayList<PartnerActivityDetailOrderLineDo> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(ArrayList<PartnerActivityDetailOrderLineDo> orderLines) {
		this.orderLines = orderLines;
	}
	public ArrayList<PartnerActivityDetailPartDo> getParts() {
		return parts;
	}
	public void setParts(ArrayList<PartnerActivityDetailPartDo> parts) {
		this.parts = parts;
	}
	public ArrayList<PartnerActivityDetailRepairedDeviceDo> getRepairedDevices() {
		return repairedDevices;
	}
	public void setRepairedDevices(
			ArrayList<PartnerActivityDetailRepairedDeviceDo> repairedDevices) {
		this.repairedDevices = repairedDevices;
	}
	public ArrayList<PartnerActivityDetailInstalledAssetDo> getInstalledDevices() {
		return installedDevices;
	}
	public void setInstalledDevices(
			ArrayList<PartnerActivityDetailInstalledAssetDo> installedDevices) {
		this.installedDevices = installedDevices;
	}
	public void setFlags(ArrayList<PartnerActivityDetailFlagDo> flags) {
		this.flags = flags;
	}
	public ArrayList<PartnerActivityDetailFlagDo> getFlags() {
		return flags;
	}
	public void setRepairedSerialNumber(String repairedSerialNumber) {
		this.repairedSerialNumber = repairedSerialNumber;
	}
	public String getRepairedSerialNumber() {
		return repairedSerialNumber;
	}
	public String getActionNarrative() {
		return actionNarrative;
	}
	public void setActionNarrative(String actionNarrative) {
		this.actionNarrative = actionNarrative;
	}
	public String getActualFailureCode() {
		return actualFailureCode;
	}
	public void setActualFailureCode(String actualFailureCode) {
		this.actualFailureCode = actualFailureCode;
	}
	public String getAddlServiceReq() {
		return AddlServiceReq;
	}
	public void setAddlServiceReq(String addlServiceReq) {
		AddlServiceReq = addlServiceReq;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCustomerRequestResponse() {
		return customerRequestResponse;
	}
	public void setCustomerRequestResponse(String customerRequestResponse) {
		this.customerRequestResponse = customerRequestResponse;
	}
	public String getSPReferenceNum() {
		return sPReferenceNum;
	}
	public void setSPReferenceNum(String sPReferenceNum) {
		this.sPReferenceNum = sPReferenceNum;
	}
	public Date getStatusAsOf() {
		return convertStringToDateWithMarker(statusAsOf);
	}
	public void setStatusAsOf(String statusAsOf) {
		this.statusAsOf = statusAsOf;
	}
	public String getTechnician() {
		return technician;
	}
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	public void setServiceActivityFlag(String serviceActivityFlag) {
		this.serviceActivityFlag = serviceActivityFlag;
	}
	public String getServiceActivityFlag() {
		return serviceActivityFlag;
	}
	public void setServiceInstruction(String serviceInstruction) {
		this.serviceInstruction = serviceInstruction;
	}
	public String getServiceInstruction() {
		return serviceInstruction;
	}
	public void setRecommendedFlag(Boolean recommendedFlag) {
		this.recommendedFlag = recommendedFlag;
	}
	public Boolean getRecommendedFlag() {
		if(recommendedFlag == null) {
			recommendedFlag = false;
		}
		return recommendedFlag;
	}
	public ArrayList<PartnerActivityDetailPartOptionsDo> getPartsOptions() {
		return partsOptions;
	}
	public void setPartsOptions(
			ArrayList<PartnerActivityDetailPartOptionsDo> partsOptions) {
		this.partsOptions = partsOptions;
	}	
	public String getRequestorFirstName() {
		return requestorFirstName;
	}
	public void setRequestorFirstName(String requestorFirstName) {
		this.requestorFirstName = requestorFirstName;
	}
	public String getRequestorLastName() {
		return requestorLastName;
	}
	public void setRequestorLastName(String requestorLastName) {
		this.requestorLastName = requestorLastName;
	}
	public String getRequestorEmail() {
		return requestorEmail;
	}
	public void setRequestorEmail(String requestorEmail) {
		this.requestorEmail = requestorEmail;
	}
	public String getRequestorWorkPhone() {
		return requestorWorkPhone;
	}
	public void setRequestorWorkPhone(String requestorWorkPhone) {
		this.requestorWorkPhone = requestorWorkPhone;
	}		
	public ArrayList<ServiceRequestActivityAttachmentsDo> getServiceRequestAttachmentss() {
		return serviceRequestAttachmentss;
	}
	public void setServiceRequestAttachmentss(
			ArrayList<ServiceRequestActivityAttachmentsDo> serviceRequestAttachmentss) {
		this.serviceRequestAttachmentss = serviceRequestAttachmentss;
	}

	public String getLevelOfDetails() {
		return levelOfDetails;
	}

	public void setLevelOfDetails(String levelOfDetails) {
		this.levelOfDetails = levelOfDetails;
	}

	/**
	 * @param lbsAddressLod the lbsAddressLod to set
	 */
	public void setLbsAddressLod(String lbsAddressLod) {
		this.lbsAddressLod = lbsAddressLod;
	}

	/**
	 * @return the lbsAddressLod
	 */
	public String getLbsAddressLod() {
		return lbsAddressLod;
	}
	

}
