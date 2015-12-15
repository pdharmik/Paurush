package com.lexmark.services.form;

import java.util.List;
import java.util.Map;

import com.lexmark.domain.Attachment;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.ServiceRequest;

public class ServiceRequestForm {
	private ServiceRequest serviceRequest;
	private String breadCrumb;
	private String associatedServiceTicketXML;
	private String serviceRequestStausXML;
	private String serviceRequestNotificationsXML;
	private String serviceRequestShipmentXML;
	private String serviceRequestTechnicianXML;
	private int serviceRequestTechnicianProgress;
	private ShipmentForm inProcessForm;
	private List<ShipmentForm> serviceRequestShipments;
	private List<ShipmentForm> serviceRequestReturnShipments;
	private ShipmentForm inProgressReturnNoTrackNumForm;
	private ShipmentForm deliveredReturnNoTrackNumForm;
	private boolean createServiceRequestFlag ;
	private String serviceHost;
	private String serviceRequestETA;
    private String serviceRequestSLA;
    
    private Map<String, FileObject> attachmentFileMap ;
    
 // Attachments Download
    private List<Attachment> attachmentList;
	/**
	 * 
	 * @return List 
	 */
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
/**
 * 
 * @param attachmentList 
 */
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
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
	 * @return List 
	 */
	public List<ShipmentForm> getServiceRequestReturnShipments() {
		return serviceRequestReturnShipments;
	}
	/**
	 * 
	 * @param serviceRequestReturnShipments 
	 */
	 
	public void setServiceRequestReturnShipments(
			List<ShipmentForm> serviceRequestReturnShipments) {
		this.serviceRequestReturnShipments = serviceRequestReturnShipments;
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
	 * @return inProcessForm 
	 */
	public ShipmentForm getInProcessForm() {
		return inProcessForm;
	}
	/**
	 * 
	 * @param inProcessForm 
	 */
	public void setInProcessForm(ShipmentForm inProcessForm) {
		this.inProcessForm = inProcessForm;
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
	public void setServiceRequestShipments(List<ShipmentForm> serviceRequestShipments) {
		this.serviceRequestShipments = serviceRequestShipments;
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
	public String getServiceRequestShipmentXML() {
		return serviceRequestShipmentXML;
	}
	/**
	 * 
	 * @param serviceRequestShipmentXML 
	 */
	public void setServiceRequestShipmentXML(String serviceRequestShipmentXML) {
		this.serviceRequestShipmentXML = serviceRequestShipmentXML;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getServiceRequestNotificationsXML() {
		return serviceRequestNotificationsXML;
	}
	/**
	 * 
	 * @param serviceRequestNotificationsXML 
	 */
	public void setServiceRequestNotificationsXML(
			String serviceRequestNotificationsXML) {
		this.serviceRequestNotificationsXML = serviceRequestNotificationsXML;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getServiceRequestStausXML() {
		return serviceRequestStausXML;
	}
	/**
	 * 
	 * @param serviceRequestStausXML 
	 */ 
	public void setServiceRequestStausXML(String serviceRequestStausXML) {
		this.serviceRequestStausXML = serviceRequestStausXML;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getAssociatedServiceTicketXML() {
		return associatedServiceTicketXML;
	}
	/**
	 * 
	 * @param associatedServiceTicketXML 
	 */
	public void setAssociatedServiceTicketXML(String associatedServiceTicketXML) {
		this.associatedServiceTicketXML = associatedServiceTicketXML;
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
 * @param serviceHost 
 */
	public void setServiceHost(String serviceHost) {
		this.serviceHost = serviceHost;
	}
/**
 * 
 * @return String 
 */
	public String getServiceHost() {
		return serviceHost;
	}
/**
 * 
 * @return inProgressReturnNoTrackNumForm 
 */
	public ShipmentForm getInProgressReturnNoTrackNumForm() {
		return inProgressReturnNoTrackNumForm;
	}
/**
 * 
 * @param inProgressReturnNoTrackNumForm 
 */
	public void setInProgressReturnNoTrackNumForm(
			ShipmentForm inProgressReturnNoTrackNumForm) {
		this.inProgressReturnNoTrackNumForm = inProgressReturnNoTrackNumForm;
	}
/**
 * 
 * @return deliveredReturnNoTrackNumForm 
 */
	public ShipmentForm getDeliveredReturnNoTrackNumForm() {
		return deliveredReturnNoTrackNumForm;
	}
/**
 * 
 * @param deliveredReturnNoTrackNumForm 
 */
	public void setDeliveredReturnNoTrackNumForm(
			ShipmentForm deliveredReturnNoTrackNumForm) {
		this.deliveredReturnNoTrackNumForm = deliveredReturnNoTrackNumForm;
	}
/**
 * 
 * @return String 
 */
	public String getServiceRequestETA() {
		return serviceRequestETA;
	}
/**
 * 
 * @param serviceRequestETA 
 */
	public void setServiceRequestETA(String serviceRequestETA) {
		this.serviceRequestETA = serviceRequestETA;
	}
/**
 * 
 * @return String 
 */
	public String getServiceRequestSLA() {
		return serviceRequestSLA;
	}
/**
 * 
 * @param serviceRequestSLA 
 */
	public void setServiceRequestSLA(String serviceRequestSLA) {
		this.serviceRequestSLA = serviceRequestSLA;
	}

	/**
	 * @return the attachmentFileMap 
	 */
	public Map<String, FileObject> getAttachmentFileMap() {
		return attachmentFileMap;
	}

	/**
	 * @param attachmentFileMap the attachmentFileMap to set 
	 */
	public void setAttachmentFileMap(Map<String, FileObject> attachmentFileMap) {
		this.attachmentFileMap = attachmentFileMap;
	}

	
}
