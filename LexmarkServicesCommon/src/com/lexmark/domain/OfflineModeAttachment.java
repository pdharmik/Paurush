package com.lexmark.domain;

import java.io.Serializable;

public class OfflineModeAttachment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String created;
	private String autoUploadFlag;
	private String fileDate;
	private String fileExtension;
	private String fileName;
	private String fileSize;
	private String fileSourcePath;
	private String fileSourceType;
	private String attachmentId;
	private String status;
	private String visibility;
	private String comments;
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getAutoUploadFlag() {
		return autoUploadFlag;
	}
	public void setAutoUploadFlag(String autoUploadFlag) {
		this.autoUploadFlag = autoUploadFlag;
	}
	public String getFileDate() {
		return fileDate;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileSourcePath() {
		return fileSourcePath;
	}
	public void setFileSourcePath(String fileSourcePath) {
		this.fileSourcePath = fileSourcePath;
	}
	public String getFileSourceType() {
		return fileSourceType;
	}
	public void setFileSourceType(String fileSourceType) {
		this.fileSourceType = fileSourceType;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
