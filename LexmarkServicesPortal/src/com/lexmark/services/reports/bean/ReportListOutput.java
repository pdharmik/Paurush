package com.lexmark.services.reports.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportListOutput implements Serializable{

	private static final long serialVersionUID = 3775616788148726271L;
	private List<ReportListRecord> reportListRows = new ArrayList<ReportListRecord>();
	
	public List<ReportListRecord> getReportListRows() {
		return reportListRows;
	}
	public void setReportListRows(List<ReportListRecord> reportListRows) {
		this.reportListRows = reportListRows;
	}
}

