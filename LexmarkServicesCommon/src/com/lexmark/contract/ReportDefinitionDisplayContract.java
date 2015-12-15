package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

public class ReportDefinitionDisplayContract implements Serializable {

	private static final long serialVersionUID = 3557263270780897674L;
	private String definitionId;
	private Locale locale;
	
	public String getDefinitionId() {
		return definitionId;
	}
	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
