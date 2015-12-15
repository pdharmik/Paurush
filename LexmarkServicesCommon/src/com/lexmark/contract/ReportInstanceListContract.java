package com.lexmark.contract;

import java.io.Serializable;
import java.util.Date;

public class ReportInstanceListContract implements Serializable {

	private static final long serialVersionUID = 5072672644502451227L;
	private String reportDefinitionName;
	private Date startDate;
	private Date endDate;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setReportDefinitionName(String reportDefinitionName) {
		this.reportDefinitionName = reportDefinitionName;
	}
	public String getReportDefinitionName() {
		return reportDefinitionName;
	}
}
