package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.RoleCategory;

public class ReportHierarchyResult  implements Serializable{

	private static final long serialVersionUID = 8498346232736225505L;
	private List<RoleCategory> roleCategories = new ArrayList<RoleCategory>();
	
	
	public List<RoleCategory> getRoleCategories() {
		return roleCategories;
	}
	public void setRoleCategories(List<RoleCategory> roleCategories) {
		this.roleCategories = roleCategories;
	}
}
