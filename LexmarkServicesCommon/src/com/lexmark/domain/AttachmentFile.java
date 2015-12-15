package com.lexmark.domain;

import java.io.Serializable;

/**
 * @author nelson
 * @category CI5
 * 
 * this file holds the properties of the attached file
 */
public class AttachmentFile implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String fileName;
	private String fileFormat;
	private float fileSize;
	private String uploadDate;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}
	
	public float getFileSize() {
		return fileSize;
	}
}
