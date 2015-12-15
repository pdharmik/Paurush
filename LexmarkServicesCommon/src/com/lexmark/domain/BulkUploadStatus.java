package com.lexmark.domain;

import java.io.Serializable;

public class BulkUploadStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3380950808152420143L;
	private String comment;
	private String attachmentName;
	private String submittedOn;
	private int size;
	private String completedOn;
	private String status;
	private String uploadFileType;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(String submittedOn) {
		this.submittedOn = submittedOn;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
	public String getUploadFileType() {
		return uploadFileType;
	}
	public void setUploadFileType(String uploadFileType) {
		this.uploadFileType = uploadFileType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
