package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ChartParameter;

public class ChartParameterDetail implements Serializable {

	private static final long serialVersionUID = -7659110273460402678L;
	
	//private String chartName;
	private String chartPath;
	private boolean isRequireMdmParams;
	private List <ChartParameter> parameterList = new ArrayList<ChartParameter>();
	/*public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}*/
	public String getChartPath() {
		return chartPath;
	}
	public void setChartPath(String chartPath) {
		this.chartPath = chartPath;
	}
	public boolean getIsRequireMdmParams() {
		return isRequireMdmParams;
	}
	public void setIsRequireMdmParams(boolean isRequireMdmParams) {
		this.isRequireMdmParams = isRequireMdmParams;
	}
	public List<ChartParameter> getParameterList() {
		return parameterList;
	}
	public void setParameterList(List<ChartParameter> parameterList) {
		this.parameterList = parameterList;
	}
}
