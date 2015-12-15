package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

public class CheckedEntitlementServiceDetailContract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3850448140185517778L;
	private Locale locale;
	
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
