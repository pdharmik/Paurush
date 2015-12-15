package com.lexmark.domain;

import java.io.Serializable;

public class ChartParameter implements Serializable {

	private static final long serialVersionUID = 789750956787050289L;
	
	private String paramType;
	private String paramName;
	private String paramValue;
	private String paramValue2;
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getParamValue2() {
		return paramValue2;
	}
	public void setParamValue2(String paramValue2) {
		this.paramValue2 = paramValue2;
	}

}
