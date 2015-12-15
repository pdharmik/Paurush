package com.lexmark.domain;

import javax.xml.bind.annotation.XmlElement;


public class Field {
	private String name;
	private String csvHeader;
	private String siebelHeader;
	private boolean editable;
	private int columnNumber;
	private boolean prePopulate;
	private Constraint constraint;
	
	public String getCsvHeader() {
		return csvHeader;
	}
	@XmlElement
	public void setCsvHeader(String csvHeader) {
		this.csvHeader = csvHeader;
	}
	public String getSiebelHeader() {
		return siebelHeader;
	}
	@XmlElement
	public void setSiebelHeader(String siebelHeader) {
		this.siebelHeader = siebelHeader;
	}
	public boolean isEditable() {
		return editable;
	}
	@XmlElement
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public int getColumnNumber() {
		return columnNumber;
	}
	@XmlElement
	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}
	public boolean isPrePopulate() {
		return prePopulate;
	}
	@XmlElement
	public void setPrePopulate(boolean prePopulate) {
		this.prePopulate = prePopulate;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setConstraint(Constraint constraint) {
		this.constraint = constraint;
	}
	public Constraint getConstraint() {
		return constraint;
	}
	
}
