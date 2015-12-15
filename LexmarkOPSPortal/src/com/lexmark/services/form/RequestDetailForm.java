package com.lexmark.services.form;

import java.util.List;

import com.lexmark.domain.Attachment;
import com.lexmark.domain.ServiceRequest;

public class RequestDetailForm {
	
	private ServiceRequest serviceRequest;
	private String srNumber;
	private String srNotifyXML;
	private String srStausXML;
	private String srHistoryListXML;
	private List<ShipmentForm> serviceRequestShipments;
	private String associatedRequestHistoryListXML;
	private int serviceRequestTechnicianProgress;
	private String serviceRequestTechnicianXML;
	private String cancelledPartsXML;
	private String sourcePage;
	private ShipmentForm pendingRequest;
		
	private double reqProgress;
	private List<Attachment> attachList;
	private String creationDate;
	
	public ShipmentForm getPendingRequest() {
		return pendingRequest;
	}
	public void setPendingRequest(ShipmentForm pendingRequest) {
		this.pendingRequest = pendingRequest;
	}
	
	public double getReqProgress() {
		return reqProgress;
	}
	public void setReqProgress(double reqProgress) {
		this.reqProgress = reqProgress;
	}
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	
	public String getSrStausXML() {
		return srStausXML;
	}
	public void setSrStausXML(String srStausXML) {
		this.srStausXML = srStausXML;
	}
	public String getSrNotifyXML() {
		return srNotifyXML;
	}
	public void setSrNotifyXML(String srNotifyXML) {
		this.srNotifyXML = srNotifyXML;
	}
	public List<Attachment> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<Attachment> attachList) {
		this.attachList = attachList;
	}
	public String getSrHistoryListXML() {
		return srHistoryListXML;
	}
	public void setSrHistoryListXML(String srHistoryListXML) {
		this.srHistoryListXML = srHistoryListXML;
	}
	public List<ShipmentForm> getServiceRequestShipments() {
		return serviceRequestShipments;
	}
	public void setServiceRequestShipments(
			List<ShipmentForm> serviceRequestShipments) {
		this.serviceRequestShipments = serviceRequestShipments;
	}
	public String getAssociatedRequestHistoryListXML() {
		return associatedRequestHistoryListXML;
	}
	public void setAssociatedRequestHistoryListXML(
			String associatedRequestHistoryListXML) {
		this.associatedRequestHistoryListXML = associatedRequestHistoryListXML;
	}
	public int getServiceRequestTechnicianProgress() {
		return serviceRequestTechnicianProgress;
	}
	public void setServiceRequestTechnicianProgress(
			int serviceRequestTechnicianProgress) {
		this.serviceRequestTechnicianProgress = serviceRequestTechnicianProgress;
	}
	public String getServiceRequestTechnicianXML() {
		return serviceRequestTechnicianXML;
	}
	public void setServiceRequestTechnicianXML(String serviceRequestTechnicianXML) {
		this.serviceRequestTechnicianXML = serviceRequestTechnicianXML;
	}
	public String getSourcePage() {
		return sourcePage;
	}
	public void setSourcePage(String sourcePage) {
		this.sourcePage = sourcePage;
	}
	public String getSrNumber() {
		return srNumber;
	}
	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getCancelledPartsXML() {
		return cancelledPartsXML;
	}
	public void setCancelledPartsXML(String cancelledPartsXML) {
		this.cancelledPartsXML = cancelledPartsXML;
	}
	
	
	
}
