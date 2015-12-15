package com.lexmark.result;

import java.io.Serializable;

import com.lexmark.domain.RoleCategory;

public class CategoryAdministrationDetailResult implements Serializable {

	private static final long serialVersionUID = -2299706841846365031L;
	private RoleCategory roleCategory;
	
	public void setRoleCategory(RoleCategory roleCategory) {
		this.roleCategory = roleCategory;
	}
	public RoleCategory getRoleCategory() {
		return roleCategory;
	}
}
