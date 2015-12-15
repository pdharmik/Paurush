package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;

public class FileObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String displayFileName;
	private String fileName;
	private String fileSize;
	private String fileSizeInBytes;
	//Added for MASS UPLOAD 
	private String uploadDate;
	
	public String getFileSizeInBytes() {
		return fileSizeInBytes;
	}
	public void setFileSizeInBytes(String fileSizeInBytes) {
		this.fileSizeInBytes = fileSizeInBytes;
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
	public String getDisplayFileName() {
		return displayFileName;
	}
	public void setDisplayFileName(String displayFileName) {
		this.displayFileName = displayFileName;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	

}
