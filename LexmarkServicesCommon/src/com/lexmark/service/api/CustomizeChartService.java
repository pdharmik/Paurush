package com.lexmark.service.api;

import java.util.List;

import com.lexmark.domain.ChartDetail;
import com.lexmark.domain.ChartInfo;

public interface CustomizeChartService{
	public List<ChartInfo> getReportList()throws Exception;
	public List<ChartInfo> getWindowBasedReportList(ChartInfo contract)throws Exception;
	public boolean saveChartCustomization(ChartDetail contract)throws Exception;
}
