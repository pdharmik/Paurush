package com.lexmark.domain;

public class RoleCategoryLocale extends GenericLocale {

	private static final long serialVersionUID = -2386640747461692842L;
	
	private Integer categoryLocaleId;
	private Integer categoryId;
	private String name;
	private SupportedLocale supportedLocale;

	@Override
    public SupportedLocale getSupportedLocale() {
		return supportedLocale;
	}

	@Override
    public void setSupportedLocale(SupportedLocale supportedLocale) {
		this.supportedLocale = supportedLocale;
	}

	public Integer getCategoryLocaleId() {
		return categoryLocaleId;
	}

	public void setCategoryLocaleId(Integer categoryLocaleId) {
		this.categoryLocaleId = categoryLocaleId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
    public String getName() {
		return name;
	}

	@Override
    public void setName(String name) {
		this.name = name;
	}

}
