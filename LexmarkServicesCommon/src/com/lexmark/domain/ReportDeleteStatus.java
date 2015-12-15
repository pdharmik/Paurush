package com.lexmark.domain;

import java.io.Serializable;

public class ReportDeleteStatus implements Serializable{

	private static final long serialVersionUID = -757916791862063532L;
	private Long id;
	private String reportId;
	private Boolean isDeleted;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
