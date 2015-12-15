package com.lexmark.contract;

import java.io.Serializable;

public class CategoryDeleteContract implements Serializable {
	
	private static final long serialVersionUID = -7206718045962846693L;
	private int categoryId;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
