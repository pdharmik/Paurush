package com.lexmark.contract;

import java.io.Serializable;

public class CategoryAdministrationDetailContract implements Serializable {

	private static final long serialVersionUID = 6028587525365019830L;
	private Integer categoryId;
	
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
}
