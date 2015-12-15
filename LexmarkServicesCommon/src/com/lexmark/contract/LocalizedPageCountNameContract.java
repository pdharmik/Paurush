package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

public  class LocalizedPageCountNameContract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1992985713034417509L;
	private Locale locale;
	private String siebelValue;
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getSiebelValue() {
		return siebelValue;
	}
	public void setSiebelValue(String siebelValue) {
		this.siebelValue = siebelValue;
	}
}
