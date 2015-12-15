package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.RoleCategory;

public class CategoryAdministrationListResult implements Serializable {

	private static final long serialVersionUID = 589321179062135321L;
	private List<RoleCategory> roleCategoryList;
	
	public List<RoleCategory> getRoleCategoryList() {
		return roleCategoryList;
	}
	public void setRoleCategoryList(List<RoleCategory> roleCategoryList) {
		this.roleCategoryList = roleCategoryList;
	}
}
