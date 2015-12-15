package com.lexmark.services.form;


public class TechSpec {
	private String attribute;
	private String value;
	/**
	 * @param attribute 
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	/**
	 * @return String 
	 */
	public String getAttribute() {
		return attribute;
	}
	/**
	 * @param value 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return String 
	 */
	public String getValue() {
		return value;
	}
}
