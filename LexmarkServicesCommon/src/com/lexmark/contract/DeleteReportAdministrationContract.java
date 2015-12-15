package com.lexmark.contract;

import java.io.Serializable;

public class DeleteReportAdministrationContract implements Serializable {

	private static final long serialVersionUID = -6978530183606439922L;
	private Integer reportDefinitionId;
	
	public void setReportDefinitionId(Integer reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}
	public Integer getReportDefinitionId() {
		return reportDefinitionId;
	}
}
