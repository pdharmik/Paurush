package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexmark.domain.ReportParameters;

public class ReportDefinitionParameterResult implements Serializable {
	private static final long serialVersionUID = 4128957096866064773L;
	
	private List<ReportParameters> parameters = new ArrayList<ReportParameters>();
	private Map<String, String> parametersValue = new HashMap<String, String>();
	
	private String scheduleFrequency;
	private Integer runInterval;
	private Date effectiveDate;
	private Date expirationDate;
	private Integer specificDayInMonth;
	private Integer specificDayInWeek;
	private Integer reportScheduleId;
	private Integer reportDefinitionId;
	private String perferredTimeZone;

	public List<ReportParameters> getParameters() {
		return parameters;
	}

	public void setParameters(List<ReportParameters> parameters) {
		this.parameters = parameters;
	}

	public Map<String, String> getParametersValue() {
		return parametersValue;
	}

	public void setParametersValue(Map<String, String> parametersValue) {
		this.parametersValue = parametersValue;
	}

	public String getScheduleFrequency() {
		return scheduleFrequency;
	}

	public void setScheduleFrequency(String scheduleFrequency) {
		this.scheduleFrequency = scheduleFrequency;
	}

	public Integer getRunInterval() {
		return runInterval;
	}

	public void setRunInterval(Integer runInterval) {
		this.runInterval = runInterval;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getSpecificDayInMonth() {
		return specificDayInMonth;
	}

	public void setSpecificDayInMonth(Integer specificDayInMonth) {
		this.specificDayInMonth = specificDayInMonth;
	}

	public Integer getSpecificDayInWeek() {
		return specificDayInWeek;
	}

	public void setSpecificDayInWeek(Integer specificDayInWeek) {
		this.specificDayInWeek = specificDayInWeek;
	}

	public Integer getReportScheduleId() {
		return reportScheduleId;
	}

	public void setReportScheduleId(Integer reportScheduleId) {
		this.reportScheduleId = reportScheduleId;
	}

	public Integer getReportDefinitionId() {
		return reportDefinitionId;
	}

	public void setReportDefinitionId(Integer reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}

	public String getPerferredTimeZone() {
		return perferredTimeZone;
	}

	public void setPerferredTimeZone(String perferredTimeZone) {
		this.perferredTimeZone = perferredTimeZone;
	}

	
}
