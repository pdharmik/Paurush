package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportListResult implements Serializable{

	private static final long serialVersionUID = 3775616788148726271L;
	private List<ReportListRow> reportListRows = new ArrayList<ReportListRow>();
	
	public List<ReportListRow> getReportListRows() {
		return reportListRows;
	}
	public void setReportListRows(List<ReportListRow> reportListRows) {
		this.reportListRows = reportListRows;
	}
}
