package com.lexmark.domain;

import java.io.Serializable;
import java.util.List;

public class ObieeReportParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 203064937757176902L;
	private Integer reportParameterId;
	private String contactId;
	private String parameterType;
	private String displayName;
	private String parameterName;
	private String parameterValue;
	private String parameterValue2;
	private boolean isParameterRequired;
	private List<ReportParameterListValue> listOptions;
	private Integer maxSize; 
	
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterValue2() {
		return parameterValue2;
	}
	public void setParameterValue2(String parameterValue2) {
		this.parameterValue2 = parameterValue2;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public Integer getReportParameterId() {
		return reportParameterId;
	}
	public void setReportParameterId(Integer reportParameterId) {
		this.reportParameterId = reportParameterId;
	}
	public boolean getIsParameterRequired() {
		return isParameterRequired;
	}
	public void setParameterRequired(boolean isParameterRequired) {
		this.isParameterRequired = isParameterRequired;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public List<ReportParameterListValue> getListOptions() {
		return listOptions;
	}
	public void setListOptions(List<ReportParameterListValue> listOptions) {
		this.listOptions = listOptions;
	}
	public Integer getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
}
