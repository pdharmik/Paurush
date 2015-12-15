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
	/**
	 * 
	 * @return String 
	 */
	public String getReportPath() {
		return reportPath;
	}
	/**
	 * 
	 * @param reportPath 
	 */
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getParameterName() {
		return parameterName;
	}
	/**
	 * 
	 * @param parameterName 
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getParameterValue() {
		return parameterValue;
	}
	/**
	 * 
	 * @param parameterValue 
	 */
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
 
	
}
