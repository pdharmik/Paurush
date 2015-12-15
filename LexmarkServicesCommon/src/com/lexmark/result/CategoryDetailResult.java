package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.RoleCategory;

public class CategoryDetailResult implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private RoleCategory category;

	public RoleCategory getCategory() {
		return category;
	}

	public void setCategory(RoleCategory category) {
		this.category = category;
	}
	
}
