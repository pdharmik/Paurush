package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoleCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3948728097214669011L;
	private Integer categoryId;
	private String name;
	private String type;
	private int orderNumber;
	private boolean isDeleted = false;
	private List<Role> roles = new ArrayList<Role>();
	//Partner Type and Countries added for BRD 14-07-04
	private List<PartnerTypes> partnerTypes = new ArrayList<PartnerTypes>();
	private List<RoleCategoryLocale>  localeList = new ArrayList<RoleCategoryLocale>(0);
	private List<DocumentDefinition> documentList = new ArrayList<DocumentDefinition>(0);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<RoleCategoryLocale> getLocaleList() {
		return localeList;
	}
	public void setLocaleList(List<RoleCategoryLocale> localeList) {
		this.localeList = localeList;
	}
	public List<DocumentDefinition> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<DocumentDefinition> documentList) {
		this.documentList = documentList;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public List<PartnerTypes> getPartnerTypes() {
		return partnerTypes;
	}
	public void setPartnerTypes(List<PartnerTypes> partnerTypes) {
		this.partnerTypes = partnerTypes;
	}

}
