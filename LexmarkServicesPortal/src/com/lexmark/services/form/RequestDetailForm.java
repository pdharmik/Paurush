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
	/**
	 * 
	 * @return pendingRequest 
	 */
	public ShipmentForm getPendingRequest() {
		return pendingRequest;
	}
	/**
	 * 
	 * @param pendingRequest 
	 */
	public void setPendingRequest(ShipmentForm pendingRequest) {
		this.pendingRequest = pendingRequest;
	}
	/**
	 * 
	 * @return double 
	 */
	public double getReqProgress() {
		return reqProgress;
	}
	/**
	 * 
	 * @param reqProgress 
	 */
	public void setReqProgress(double reqProgress) {
		this.reqProgress = reqProgress;
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
	public String getSrStausXML() {
		return srStausXML;
	}
	/**
	 * 
	 * @param srStausXML 
	 */
	public void setSrStausXML(String srStausXML) {
		this.srStausXML = srStausXML;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSrNotifyXML() {
		return srNotifyXML;
	}
	/**
	 * 
	 * @param srNotifyXML 
	 */
	public void setSrNotifyXML(String srNotifyXML) {
		this.srNotifyXML = srNotifyXML;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<Attachment> getAttachList() {
		return attachList;
	}
	/**
	 * 
	 * @param attachList 
	 */
	public void setAttachList(List<Attachment> attachList) {
		this.attachList = attachList;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSrHistoryListXML() {
		return srHistoryListXML;
	}
	/**
	 * 
	 * @param srHistoryListXML 
	 */
	public void setSrHistoryListXML(String srHistoryListXML) {
		this.srHistoryListXML = srHistoryListXML;
	}
	/**
	 * 
	 * @return List 
	 */
	public List<ShipmentForm> getServiceRequestShipments() {
		return serviceRequestShipments;
	}
	/**
	 * 
	 * @param serviceRequestShipments 
	 */
	public void setServiceRequestShipments(
			List<ShipmentForm> serviceRequestShipments) {
		this.serviceRequestShipments = serviceRequestShipments;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getAssociatedRequestHistoryListXML() {
		return associatedRequestHistoryListXML;
	}
	/**
	 * 
	 * @param associatedRequestHistoryListXML 
	 */
	public void setAssociatedRequestHistoryListXML(
			String associatedRequestHistoryListXML) {
		this.associatedRequestHistoryListXML = associatedRequestHistoryListXML;
	}
	/**
	 * 
	 * @return Integer 
	 */
	public int getServiceRequestTechnicianProgress() {
		return serviceRequestTechnicianProgress;
	}
	/**
	 * 
	 * @param serviceRequestTechnicianProgress 
	 */
	public void setServiceRequestTechnicianProgress(
			int serviceRequestTechnicianProgress) {
		this.serviceRequestTechnicianProgress = serviceRequestTechnicianProgress;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getServiceRequestTechnicianXML() {
		return serviceRequestTechnicianXML;
	}
	/**
	 * 
	 * @param serviceRequestTechnicianXML 
	 */
	public void setServiceRequestTechnicianXML(String serviceRequestTechnicianXML) {
		this.serviceRequestTechnicianXML = serviceRequestTechnicianXML;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSourcePage() {
		return sourcePage;
	}
	/**
	 * 
	 * @param sourcePage 
	 */ 
	public void setSourcePage(String sourcePage) {
		this.sourcePage = sourcePage;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSrNumber() {
		return srNumber;
	}
	/**
	 * 
	 * @param srNumber 
	 */
	public void setSrNumber(String srNumber) {
		this.srNumber = srNumber;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCreationDate() {
		return creationDate;
	}
	/**
	 * 
	 * @param creationDate 
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getCancelledPartsXML() {
		return cancelledPartsXML;
	}
	/**
	 * 
	 * @param cancelledPartsXML 
	 */
	public void setCancelledPartsXML(String cancelledPartsXML) {
		this.cancelledPartsXML = cancelledPartsXML;
	}
	
	
	
}
