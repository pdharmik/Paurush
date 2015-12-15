package com.lexmark.services.form;

import java.io.Serializable;

public class ObieeConfigurationForm extends BaseForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8650901617248249416L;
	/**
	 * 
	 */
	private String reportPath;
	private String parameterName;
	private String parameterValue;
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
 
	
}
