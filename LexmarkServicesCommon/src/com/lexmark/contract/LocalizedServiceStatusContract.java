package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.CachingContract;

public  class LocalizedServiceStatusContract extends CachingContract  implements Serializable{

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
	
	@Override
	public String getCacheKey() {
		StringBuffer sb = new StringBuffer();
		sb.append(locale == null? "":locale.toString());
		sb.append("-");
		sb.append(getSiebelValue() == null? "":getSiebelValue());
		return sb.toString(); 
	}

}
