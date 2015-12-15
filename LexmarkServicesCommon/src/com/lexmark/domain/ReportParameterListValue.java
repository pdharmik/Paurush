package com.lexmark.domain;

import java.io.Serializable;

public class ReportParameterListValue implements Serializable{

	private static final long serialVersionUID = -3119551850827699409L;
	private String label;
	private String value;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
