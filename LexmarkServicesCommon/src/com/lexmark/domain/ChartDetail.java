package com.lexmark.domain;

import java.io.Serializable;

public class ChartDetail implements Serializable{

    private static final long serialVersionUID = 1L;

	private String mdmId;
	private String leftChartId;
	private String rightChartId;
	
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getLeftChartId() {
		return leftChartId;
	}
	public void setLeftChartId(String leftChartId) {
		this.leftChartId = leftChartId;
	}
	public String getRightChartId() {
		return rightChartId;
	}
	public void setRightChartId(String rightChartId) {
		this.rightChartId = rightChartId;
	}
}
