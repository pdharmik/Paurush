package com.lexmark.service.impl.real.domain;

/**
 * mapping file: "do-requestattachment-mapping.xml"
 */

import java.io.Serializable;
import java.util.ArrayList;

import com.amind.common.domain.Attachment;


public class RequestAttachment extends Attachment implements Serializable {
	private static final long serialVersionUID = 523703257661876400L;

	private String mdmId;
	private String status;
	private String submittedOn;
	private String completedOn;
	private String requestNumber;
	private Integer size;
	private String attachmentId;
	private String type;
	private String fileName;
	private String subArea;
	private String srRowId;
	private String lastUpdatedOn;
	
	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(String submittedOn) {
		this.submittedOn = submittedOn;
	}
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getCompletedOn() {
		return completedOn;
	}
	public void setCompletedOn(String completedOn) {
		this.completedOn = completedOn;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSubArea() {
		return subArea;
	}
	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}
	public String getSrRowId() {
		return srRowId;
	}
	public void setSrRowId(String srRowId) {
		this.srRowId = srRowId;
	}
}
