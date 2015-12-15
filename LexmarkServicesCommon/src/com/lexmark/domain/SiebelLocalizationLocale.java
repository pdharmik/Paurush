package com.lexmark.domain;

import java.io.Serializable;

public class SiebelLocalizationLocale implements Serializable {


	private static final long serialVersionUID = -3356310010246142353L;
	private Integer siebelLocalizationLocaleId;
	private SiebelLocalization siebelLocalization;
	private SupportedLocale supportedLocale;
	private String displayValue;
	public Integer getSiebelLocalizationLocaleId() {
		return siebelLocalizationLocaleId;
	}
	public void setSiebelLocalizationLocaleId(Integer siebelLocalizationLocaleId) {
		this.siebelLocalizationLocaleId = siebelLocalizationLocaleId;
	}

	public SupportedLocale getSupportedLocale() {
		return supportedLocale;
	}
	public void setSupportedLocale(SupportedLocale supportedLocale) {
		this.supportedLocale = supportedLocale;
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	public SiebelLocalization getSiebelLocalization() {
		return siebelLocalization;
	}
	public void setSiebelLocalization(SiebelLocalization siebelLocalization) {
		this.siebelLocalization = siebelLocalization;
	}
}
