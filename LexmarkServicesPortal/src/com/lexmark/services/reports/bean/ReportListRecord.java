package com.lexmark.services.reports.bean;

import java.io.Serializable;
import java.util.Date;

 public class ReportListRecord implements Serializable{

	private static final long serialVersionUID = 5238175187324461136L;
	private int reportDefinitionId;
	private String reportName;
	private String supportedLocaleId;
	private String supportedLocaleCode;
	private Date createdDate;
	private Date scheduleCreateDate;
	private int reportJobId;
	private String reportType;
	private String filename;
	private int fileSize;
	private String documentId;
	private String fileType;
	private String categoryId;

	private String documentId2;
	private String batchId;
	private String status;
	private String reportScheduleId;
	private String parameterList;
	
	private String userEmail;
	private String errorMessage;
	
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
	public String getSupportedLocaleId() {
		return supportedLocaleId;
	}
	public void setSupportedLocaleId(String supportedLocaleId) {
		this.supportedLocaleId = supportedLocaleId;
	}
	public String getSupportedLocaleCode() {
		return supportedLocaleCode;
	}
	public void setSupportedLocaleCode(String supportedLocaleCode) {
		this.supportedLocaleCode = supportedLocaleCode;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
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
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getDocumentId2() {
		return documentId2;
	}

	public void setDocumentId2(String documentId2) {
		this.documentId2 = documentId2;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReportScheduleId() {
		return reportScheduleId;
	}

	public void setReportScheduleId(String reportScheduleId) {
		this.reportScheduleId = reportScheduleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getScheduleCreateDate() {
		return scheduleCreateDate;
	}

	public void setScheduleCreateDate(Date scheduleCreateDate) {
		this.scheduleCreateDate = scheduleCreateDate;
	}

	public String getParameterList() {
		return parameterList;
	}

	public void setParameterList(String parameterList) {
		this.parameterList = parameterList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}