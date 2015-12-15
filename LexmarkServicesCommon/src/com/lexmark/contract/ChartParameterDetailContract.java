package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.ChartParameterDetail;

public class ChartParameterDetailContract implements Serializable {

	private static final long serialVersionUID = -2200784053789908060L;
	private ChartParameterDetail chartParameterDetail;
	public ChartParameterDetail getChartParameterDetail() {
		return chartParameterDetail;
	}
	public void setChartParameterDetail(ChartParameterDetail chartParameterDetail) {
		this.chartParameterDetail = chartParameterDetail;
	}
}
