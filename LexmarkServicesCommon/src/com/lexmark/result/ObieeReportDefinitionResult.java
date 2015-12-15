package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.ReportDefinition;

public class ObieeReportDefinitionResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3233413603397882008L;
	private ReportDefinition ReportDefinition;

	public ReportDefinition getReportDefinition() {
		return ReportDefinition;
	}

	public void setReportDefinition(ReportDefinition reportDefinition) {
		ReportDefinition = reportDefinition;
	}

}
