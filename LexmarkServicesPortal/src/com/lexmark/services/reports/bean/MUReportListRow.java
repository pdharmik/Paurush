package com.lexmark.services.reports.bean;

public class MUReportListRow {
	private int reportDefinitionId;
	private String reportName;
	private String suportedLocalId;
	private String supportedLocalCode;
	private String createdDate;
	private int reportJobId;
	private String reportType;
	private String filename;
	private int fileSize;
	private String documentId;

	public int getReportDefinitionId() {
		return reportDefinitionId;
	}

	public void setReportDefinitionId(int reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getSuportedLocalId() {
		return suportedLocalId;
	}

	public void setSuportedLocalId(String suportedLocalId) {
		this.suportedLocalId = suportedLocalId;
	}

	public String getSupportedLocalCode() {
		return supportedLocalCode;
	}

	public void setSupportedLocalCode(String supportedLocalCode) {
		this.supportedLocalCode = supportedLocalCode;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getReportJobId() {
		return reportJobId;
	}

	public void setReportJobId(int reportJobId) {
		this.reportJobId = reportJobId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
}
