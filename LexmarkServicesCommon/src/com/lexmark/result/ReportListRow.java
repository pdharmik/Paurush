package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lexmark.domain.Report;

public class ReportListRow implements Serializable{

	private static final long serialVersionUID = 5238175187324461136L;
	private int reportDefinitionId;
	private String reportName;
	private String supportedLocalId;
	private String supportedLocalCode;
	private Date createdDate;
	private int reportJobId;
	private String reportType;
	private String filename;
	private int fileSize;
	private String documentId;
	private String fileType;
	private String categoryId;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
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
	public String getSupportedLocalId() {
		return supportedLocalId;
	}
	public void setSupportedLocalId(String supportedLocalId) {
		this.supportedLocalId = supportedLocalId;
	}
	public String getSupportedLocalCode() {
		return supportedLocalCode;
	}
	public void setSupportedLocalCode(String supportedLocalCode) {
		this.supportedLocalCode = supportedLocalCode;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	//-----------------------------------
	private String reportDefinitionName;
	private String reportCategoryName;
	private String definitionType;
	public String getDefinitionType() {
		return definitionType;
	}
	public void setDefinitionType(String definitionType) {
		this.definitionType = definitionType;
	}
	public String getReportCategoryName() {
		return reportCategoryName;
	}
	public void setReportCategoryName(String reportCategoryName) {
		this.reportCategoryName = reportCategoryName;
	}
	private Date lastUpdated;
	private String status;
	private int statusVoter = 0;
	public int getStatusVoter() {
		return statusVoter;
	}
	public void setStatusVoter(int statusVoter) {
		this.statusVoter = statusVoter;
	}
	private List<Report> reportList = new ArrayList<Report>(0);
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	public String getReportDefinitionName() {
		return reportDefinitionName;
	}
	public void setReportDefinitionName(String reportDefinitionName) {
		this.reportDefinitionName = reportDefinitionName;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public List<Report> getReportList() {
		return reportList;
	}
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}
}
