package com.lexmark.api.bean;

public class InProperty {
	/**
	 * 
	 * @param _id 
	 * @param _val 
	 */
	public InProperty(String _id, String _val) {
		id = _id;
		value = _val;
	}

	private String id;
	private String type = "STRING";
	private String value;

	/**
	 * 
	 * @return id 
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return value 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 
	 * @param value 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 
	 * @return type 
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type 
	 */
	public void setType(String type) {
		this.type = type;
	}

}