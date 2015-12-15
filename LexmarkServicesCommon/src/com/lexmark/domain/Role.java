package com.lexmark.domain;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = -5007524051281474480L;

	private Integer id;
	private String name;
	private String targetPortal;
	private String roleType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTargetPortal() {
		return targetPortal;
	}

	public void setTargetPortal(String targetPortal) {
		this.targetPortal = targetPortal;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
}
