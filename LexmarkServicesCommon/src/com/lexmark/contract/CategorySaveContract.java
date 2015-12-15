package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.RoleCategory;

public class CategorySaveContract implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
	private RoleCategory category;

	public RoleCategory getCategory() {
		return category;
	}

	public void setCategory(RoleCategory category) {
		this.category = category;
	}
	
}
