package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportDefinitionNameListResult implements Serializable {

	private static final long serialVersionUID = -7593944571106149700L;
	private List<String> reportDefinitionNameList = new ArrayList<String>();
	
	public void setReportDefinitionNameList(List<String> reportDefinitionNameList) {
		this.reportDefinitionNameList = reportDefinitionNameList;
	}
	public List<String> getReportDefinitionNameList() {
		return reportDefinitionNameList;
	}
	
}
