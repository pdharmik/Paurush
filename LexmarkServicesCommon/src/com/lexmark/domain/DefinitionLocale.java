package com.lexmark.domain;


public class DefinitionLocale extends GenericLocale {

	private static final long serialVersionUID = 6340735139696473314L;
	
	private Integer definitionLocaleId;
	private Integer reportDefinitionId;
	private String name;
	private String description;
	private SupportedLocale supportedLocale;
	
	public Integer getDefinitionLocaleId() {
		return definitionLocaleId;
	}
	public void setDefinitionLocaleId(Integer definitionLocaleId) {
		this.definitionLocaleId = definitionLocaleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setReportDefinitionId(Integer reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}
	public Integer getReportDefinitionId() {
		return reportDefinitionId;
	}
	public SupportedLocale getSupportedLocale() {
		return supportedLocale;
	}
	public void setSupportedLocale(SupportedLocale supportedLocale) {
		this.supportedLocale = supportedLocale;
	}
}
