package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.SupportedLocale;

public class SupportedLocaleListResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4966607648421365187L;
	private List<SupportedLocale> supportedLocales  = new ArrayList<SupportedLocale>(0);
	
	public List<SupportedLocale> getSupportedLocales() {
		return supportedLocales;
	}
	public void setSupportedLocales(List<SupportedLocale> supportedLocales) {
		this.supportedLocales = supportedLocales;
	}
}
