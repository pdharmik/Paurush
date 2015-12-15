package com.lexmark.domain;

import java.io.Serializable;

public class ChartInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	private String chartName;
	private String chartId;
	private String chartUrl;
	private String flag;
	private String window;
	private String mdmId;
	private String mdmLevel;
	private String mdmRequired;
	
	public String getMdmRequired() {
		return mdmRequired;
	}
	public void setMdmRequired(String mdmRequired) {
		this.mdmRequired = mdmRequired;
	}
	public String getWindow() {
		return window;
	}
	public void setWindow(String window) {
		this.window = window;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	public String getChartUrl() {
		return chartUrl;
	}
	public void setChartUrl(String chartUrl) {
		this.chartUrl = chartUrl;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		StringBuffer sbr = new StringBuffer();
			sbr.append("\n mdmId : "+mdmId);
			sbr.append("\n mdmRequired : "+mdmRequired);
			sbr.append("\n chartId : "+chartId);
			sbr.append("\n chartName : "+chartName);
			sbr.append("\n chartUrl : "+chartUrl);
			sbr.append("\n window : "+window);
		return sbr.toString();
	}
}
