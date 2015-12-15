package com.lexmark.contract;

import java.io.Serializable;

public class DocumentDeleteContract implements Serializable{
    
    private static final long serialVersionUID = 1L;
	private String objectId;
	private String batchId;
	private String reportJobId;
	private String reportType;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String ObjectId) {
		this.objectId = ObjectId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportJobId() {
		return reportJobId;
	}

	public void setReportJobId(String reportJobId) {
		this.reportJobId = reportJobId;
	}
}
