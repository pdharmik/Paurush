package com.lexmark.contract;

import java.io.Serializable;

public class CategoryDetailContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
	private int categoryId;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
