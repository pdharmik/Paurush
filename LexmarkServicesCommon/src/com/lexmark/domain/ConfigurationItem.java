package com.lexmark.domain;

import java.io.Serializable;

public class ConfigurationItem implements Serializable {

	private static final long serialVersionUID = 2479778912794157031L;
	private int id = 0;
	private String itemKey;
	private String itemValue;

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String key) {
		this.itemKey = key;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String value) {
		this.itemValue = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
@Override
public String toString() {
	return "<"+itemKey+", "+itemValue+">";
}
}