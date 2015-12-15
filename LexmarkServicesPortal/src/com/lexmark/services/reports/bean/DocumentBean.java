package com.lexmark.services.reports.bean;

public class DocumentBean {

	private String documentId;	
	private String fileName;
	private int fileSize;
	private String objectname;
	private String acontenttype;		
	private String f5;	
	
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}	
	public String getF5() {
		return f5;
	}
	public void setF5(String f5) {
		this.f5 = f5;
	}
	public String getObjectname() {
		return objectname;
	}
	public void setObjectname(String objectname) {
		this.objectname = objectname;
	}
	public String getAcontenttype() {
		return acontenttype;
	}
	public void setAcontenttype(String acontenttype) {
		this.acontenttype = acontenttype;
	}	
}
