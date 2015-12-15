package com.lexmark.api.bean;

public class InInfo {
	private String name;
	private String location;
	private InKeys keys = new InKeys();

	/**
	 * 
	 * @return String 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return String 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * @param location 
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @return keys 
	 */
	public InKeys getKeys() {
		return keys;
	}

	/**
	 * 
	 * @param keys 
	 */
	public void setKeys(InKeys keys) {
		this.keys = keys;
	}
}