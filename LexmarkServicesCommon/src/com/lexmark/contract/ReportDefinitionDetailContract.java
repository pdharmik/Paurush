package com.lexmark.contract;

import java.io.Serializable;

public class ReportDefinitionDetailContract implements Serializable {

	private static final long serialVersionUID = -4798312356787086381L;
	private Integer reportDefinitionId;
	
	public void setReportDefinitionId(Integer reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}
	public Integer getReportDefinitionId() {
		return reportDefinitionId;
	}
}
