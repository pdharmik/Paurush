package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexmark.domain.Report;

public class ReportInstanceListResult implements Serializable {

	private static final long serialVersionUID = 5361873229600764902L;
	private List<Report> reportInstanceList = new ArrayList<Report>();
	Map<String,String> rJMap = new HashMap<String,String> ();
	
	public Map<String, String> getRJMap() {
		return rJMap;
	}
	public void setRJMap(Map<String, String> map) {
		rJMap = map;
	}
	public void setReportInstanceList(List<Report> reportInstanceList) {
		this.reportInstanceList = reportInstanceList;
	}
	public List<Report> getReportInstanceList() {
		return reportInstanceList;
	}
	
}
