package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;

public class Attachment implements Serializable {
	private static final long serialVersionUID = 4113834244724285294L;

	private String attachmentName;
	private Date submittedOn;
	private int size;
	private String sizeForDisplay;
	

	private Date completedOn;
	private String status;
	private String description;
	private String activityId;
	private String extension;
	private String visibility;
	private String displayAttachmentName;
	private String id;
	private String actualFileName;
	private String identifier;
	private String type;
	//Added for MASS UPLOAD 
	private String requestNumber;
	private String requestType;
	private String fileName;
	private String fileSourcePath;
	private String subArea;
	private String srRowId;
	
	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public Date getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Date getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(Date completedOn) {
		this.completedOn = completedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getVisibility() {
		return visibility;
	}
	public String getSizeForDisplay() {
		return sizeForDisplay;
	}

	public void setSizeForDisplay(String sizeForDisplay) {
		this.sizeForDisplay = sizeForDisplay;
	}

	public String getDisplayAttachmentName() {
		return displayAttachmentName;
	}

	public void setDisplayAttachmentName(String displayAttachmentName) {
		this.displayAttachmentName = displayAttachmentName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setActualFileName(String actualFileName) {
		this.actualFileName = actualFileName;
	}

	public String getActualFileName() {
		return actualFileName;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSourcePath() {
		return fileSourcePath;
	}

	public void setFileSourcePath(String fileSourcePath) {
		this.fileSourcePath = fileSourcePath;
	}

	public String getSubArea() {
		return subArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public void setSrRowId(String srRowId) {
		this.srRowId = srRowId;
	}

	public String getSrRowId() {
		return srRowId;
	}
	
}
