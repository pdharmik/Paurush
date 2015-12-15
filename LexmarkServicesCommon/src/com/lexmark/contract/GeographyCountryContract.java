package com.lexmark.contract;

import java.io.Serializable;

public class GeographyCountryContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5511100970844747850L;
	private String countryName;
	private String countryCode;
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
