package com.lexmark.services.reports.bean;

import javax.xml.bind.annotation.XmlElement;

public class DocDelDownloadInfo {
	
	@XmlElement
	private String documentId;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
}
