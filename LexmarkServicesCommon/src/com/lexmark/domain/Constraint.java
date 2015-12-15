package com.lexmark.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Constraint {
	private List<Value> value;
	private String siebelLOV;
	private String defaultCSVValue;
	@XmlElement
	public void setValue(List<Value> value) {
		this.value = value;
	}

	public List<Value> getValue() {
		return value;
	}
	@XmlElement
	public void setSiebelLOV(String siebelLOV) {
		this.siebelLOV = siebelLOV;
	}

	public String getSiebelLOV() {
		return siebelLOV;
	}
	@XmlElement
	public void setDefaultCSVValue(String defaultCSVValue) {
		this.defaultCSVValue = defaultCSVValue;
	}

	public String getDefaultCSVValue() {
		return defaultCSVValue;
	}
	
}
