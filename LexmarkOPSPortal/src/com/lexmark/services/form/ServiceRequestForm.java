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
	
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	public boolean isCreateServiceRequestFlag() {
		return createServiceRequestFlag;
	}

	public void setCreateServiceRequestFlag(boolean createServiceRequestFlag) {
		this.createServiceRequestFlag = createServiceRequestFlag;
	}
	public List<ShipmentForm> getServiceRequestReturnShipments() {
		return serviceRequestReturnShipments;
	}
	public void setServiceRequestReturnShipments(
			List<ShipmentForm> serviceRequestReturnShipments) {
		this.serviceRequestReturnShipments = serviceRequestReturnShipments;
	}
	public int getServiceRequestTechnicianProgress() {
		return serviceRequestTechnicianProgress;
	}
	public void setServiceRequestTechnicianProgress(
			int serviceRequestTechnicianProgress) {
		this.serviceRequestTechnicianProgress = serviceRequestTechnicianProgress;
	}
	public ShipmentForm getInProcessForm() {
		return inProcessForm;
	}
	public void setInProcessForm(ShipmentForm inProcessForm) {
		this.inProcessForm = inProcessForm;
	}
	public List<ShipmentForm> getServiceRequestShipments() {
		return serviceRequestShipments;
	}
	public void setServiceRequestShipments(List<ShipmentForm> serviceRequestShipments) {
		this.serviceRequestShipments = serviceRequestShipments;
	}
	public String getServiceRequestTechnicianXML() {
		return serviceRequestTechnicianXML;
	}
	public void setServiceRequestTechnicianXML(String serviceRequestTechnicianXML) {
		this.serviceRequestTechnicianXML = serviceRequestTechnicianXML;
	}
	public String getServiceRequestShipmentXML() {
		return serviceRequestShipmentXML;
	}
	public void setServiceRequestShipmentXML(String serviceRequestShipmentXML) {
		this.serviceRequestShipmentXML = serviceRequestShipmentXML;
	}
	public String getServiceRequestNotificationsXML() {
		return serviceRequestNotificationsXML;
	}
	public void setServiceRequestNotificationsXML(
			String serviceRequestNotificationsXML) {
		this.serviceRequestNotificationsXML = serviceRequestNotificationsXML;
	}
	public String getServiceRequestStausXML() {
		return serviceRequestStausXML;
	}
	public void setServiceRequestStausXML(String serviceRequestStausXML) {
		this.serviceRequestStausXML = serviceRequestStausXML;
	}
	public String getAssociatedServiceTicketXML() {
		return associatedServiceTicketXML;
	}
	public void setAssociatedServiceTicketXML(String associatedServiceTicketXML) {
		this.associatedServiceTicketXML = associatedServiceTicketXML;
	}
	public String getBreadCrumb() {
		return breadCrumb;
	}
	public void setBreadCrumb(String breadCrumb) {
		this.breadCrumb = breadCrumb;
	}
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}
	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public void setServiceHost(String serviceHost) {
		this.serviceHost = serviceHost;
	}

	public String getServiceHost() {
		return serviceHost;
	}

	public ShipmentForm getInProgressReturnNoTrackNumForm() {
		return inProgressReturnNoTrackNumForm;
	}

	public void setInProgressReturnNoTrackNumForm(
			ShipmentForm inProgressReturnNoTrackNumForm) {
		this.inProgressReturnNoTrackNumForm = inProgressReturnNoTrackNumForm;
	}

	public ShipmentForm getDeliveredReturnNoTrackNumForm() {
		return deliveredReturnNoTrackNumForm;
	}

	public void setDeliveredReturnNoTrackNumForm(
			ShipmentForm deliveredReturnNoTrackNumForm) {
		this.deliveredReturnNoTrackNumForm = deliveredReturnNoTrackNumForm;
	}

	public String getServiceRequestETA() {
		return serviceRequestETA;
	}

	public void setServiceRequestETA(String serviceRequestETA) {
		this.serviceRequestETA = serviceRequestETA;
	}

	public String getServiceRequestSLA() {
		return serviceRequestSLA;
	}

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
