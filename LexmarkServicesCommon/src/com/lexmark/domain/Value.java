package com.lexmark.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Value {
	private String csvValue;
	private String val;
	@XmlAttribute
	public void setCsvValue(String csvValue) {
		this.csvValue = csvValue;
	}
	public String getCsvValue() {
		return csvValue;
	}
	@XmlValue
	public void setValue(String value) {
		this.val = value;
	}
	public String getValue() {
		return val;
	}
	
}
