package com.lexmark.contract;

import java.io.Serializable;

public class CategoryAdministrationListContract implements Serializable {

	private static final long serialVersionUID = 2770065507865934323L;
	private String categoryType;
	
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getCategoryType() {
		return categoryType;
	}
}
