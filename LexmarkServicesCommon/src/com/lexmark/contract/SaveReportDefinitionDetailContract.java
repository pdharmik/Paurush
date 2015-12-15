package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.ReportDefinition;

public class SaveReportDefinitionDetailContract implements Serializable {

	private static final long serialVersionUID = 941455584553112789L;
	private ReportDefinition reportDefinition;
	private String localeId = null;
	
	public void setReportDefinition(ReportDefinition reportDefinition) {
		this.reportDefinition = reportDefinition;
	}
	public ReportDefinition getReportDefinition() {
		return reportDefinition;
	}
	public String getLocaleId() {
		return localeId;
	}
	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}
}
