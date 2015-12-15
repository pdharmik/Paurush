/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: EntitlementRoleMapping
 * Package     		: com.lexmark.domain
 * Creation Date 	: 16th April 2012
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Sandeep P		16th April 2012 		1.0             Initial Version
 *
 */
package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EntitlementRoleMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7831216178426516533L;
	private Integer entitlementId;
	private String shortDesc;
	private List<Role> roleList = new ArrayList<Role>();
	
	public Integer getEntitlementId() {
		return entitlementId;
	}
	public void setEntitlementId(Integer entitlementId) {
		this.entitlementId = entitlementId;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	

}
