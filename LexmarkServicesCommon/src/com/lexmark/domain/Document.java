package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {

	/**
	 * 
	 */
	private Long documentId;
	
	private static final long serialVersionUID = -8346673644484978790L;
	private String fileType;
	private String fileName;
	private String filePath;
	private String fileObjectId;
	private Long filedataLink;
	private Date lastUpdateTime;
	private int fileSize;
	private boolean isDirectory = false;
	private DocumentDefinition definition;
	
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public DocumentDefinition getDefinition() {
		return definition;
	}
	public void setDefinition(DocumentDefinition definition) {
		this.definition = definition;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileObjectId() {
		return fileObjectId;
	}
	public void setFileObjectId(String fileObjectId) {
		this.fileObjectId = fileObjectId;
	}
	public Long getFiledataLink() {
		return filedataLink;
	}
	public void setFiledataLink(Long filedataLink) {
		this.filedataLink = filedataLink;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	public boolean isDirectory() {
		return isDirectory;
	}
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Document)){
			return false;
		}
		Document other  = (Document) obj;
		if(getFilePath()==null)return other.getFilePath()==null; 
		return other.getFilePath().equalsIgnoreCase(other.getFilePath());
	}
	@Override
	public int hashCode() {
		if(getFilePath()==null)return 0;
		return getFilePath().hashCode();
	}
	@Override
	public String toString() {
		return getFilePath()+isDirectory;
	}
}
