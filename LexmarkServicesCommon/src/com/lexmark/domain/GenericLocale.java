package com.lexmark.domain;

import java.io.Serializable;

public class GenericLocale  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String name;
	private SupportedLocale supportedLocale;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SupportedLocale getSupportedLocale() {
		return supportedLocale;
	}

	public void setSupportedLocale(SupportedLocale supportedLocale) {
		this.supportedLocale = supportedLocale;
	}
	@Override
	public String toString() {
		return "["+supportedLocale+"]"+getName();
	}
}