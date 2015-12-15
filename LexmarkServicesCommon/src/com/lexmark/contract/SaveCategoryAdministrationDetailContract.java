package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.RoleCategory;

public class SaveCategoryAdministrationDetailContract implements Serializable {

	private static final long serialVersionUID = -6876408590069300360L;
	private RoleCategory category;
	
	public void setCategory(RoleCategory category) {
		this.category = category;
	}
	public RoleCategory getCategory() {
		return category;
	}
}
