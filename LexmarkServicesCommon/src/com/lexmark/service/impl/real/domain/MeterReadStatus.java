package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

/**
 * mapping file: "do-meterreadstatus-mapping.xml"
 */
public class MeterReadStatus extends com.amind.common.domain.Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3356729985920056689L;
	private String submittedOn;
	private String completedOn;
	private String status;
	private String category;
	private String contactId;
	private String bulkUploadStartDate;

	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(String submittedOn) {
		this.submittedOn = submittedOn;
	}
	public String getCompletedOn() {
		return completedOn;
	}
	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setBulkUploadStartDate(String bulkUploadStartDate) {
		this.bulkUploadStartDate = bulkUploadStartDate;
	}
	public String getBulkUploadStartDate() {
		return bulkUploadStartDate;
	}

}
