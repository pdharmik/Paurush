package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.ReportDefinition;

public class ReportAdministrationListResult implements Serializable {

	private static final long serialVersionUID = -1467824992356007685L;
	private List<ReportDefinition> reportDefinitionList;
	
	public void setReportDefinitionList(List<ReportDefinition> reportDefinitionList) {
		this.reportDefinitionList = reportDefinitionList;
	}
	public List<ReportDefinition> getReportDefinitionList() {
		return reportDefinitionList;
	}
}
