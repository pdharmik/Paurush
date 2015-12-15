package com.lexmark.domain;

import java.io.Serializable;

/**
 * @author elmar.chen
 * This class is only used for hibernate mapping which holds the mapping for role and role category.
 */
public class ReportACL implements Serializable{
	private static final long serialVersionUID = 2543225148464124220L;
	private Integer categoryId;
	private Integer roleId;
	private Role role;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
