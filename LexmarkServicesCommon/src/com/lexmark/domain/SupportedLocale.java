package com.lexmark.domain;

import java.io.Serializable;

public class SupportedLocale implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5235080603078401292L;
	private Integer supportedLocaleId;
	private String supportedLocaleName;
	private String supportedLocaleCode;
	public Integer getSupportedLocaleId() {
		return supportedLocaleId;
	}
	public void setSupportedLocaleId(Integer supportedLocaleId) {
		this.supportedLocaleId = supportedLocaleId;
	}
	public String getSupportedLocaleName() {
		return supportedLocaleName;
	}
	public void setSupportedLocaleName(String supportedLocaleName) {
		this.supportedLocaleName = supportedLocaleName;
	}
	public String getSupportedLocaleCode() {
		return supportedLocaleCode;
	}
	public void setSupportedLocaleCode(String supportedLocaleCode) {
		this.supportedLocaleCode = supportedLocaleCode;
	}
	
	@Override
	public String toString() {
		return supportedLocaleCode+" : "+supportedLocaleName;
	}
}
