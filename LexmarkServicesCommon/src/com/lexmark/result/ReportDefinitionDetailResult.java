package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.ReportDefinition;

public class ReportDefinitionDetailResult implements Serializable {

	private static final long serialVersionUID = -9148384028703463527L;
	private ReportDefinition reportDefinition;
	
	public void setReportDefinition(ReportDefinition reportDefinition) {
		this.reportDefinition = reportDefinition;
	}
	public ReportDefinition getReportDefinition() {
		return reportDefinition;
	}
}
