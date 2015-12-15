package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DocumentDefinition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4376510594305960L;
	private String name;
	private String display_order;//Added for CI BRD 14-06-01
	private String mdmId;
	private String mdmLevel;
	private Integer id;
	private boolean isDeleted = false;
	//******* added for DOC Lib CR *******
	private Boolean limitFlag = false;
	private String customerAccount;

	private RoleCategory roleCategory;
	private List<DefinitionLocale> localeList = new ArrayList<DefinitionLocale>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMdmId() {
		return mdmId;
	}

	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}

	public String getMdmLevel() {
		return mdmLevel;
	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setRoleCategory(RoleCategory category) {
		this.roleCategory = category;
	}

	public RoleCategory getRoleCategory() {
		return roleCategory;
	}

	public List<DefinitionLocale> getLocaleList() {
		return localeList;
	}

	public void setLocaleList(List<DefinitionLocale> localeList) {
		this.localeList = localeList;
	}

	public String getDisplay_order() {
		return display_order;
	}

	public void setDisplay_order(String display_order) {
		this.display_order = display_order;
	}

	/**
	 * @return the customerAccount
	 */
	public String getCustomerAccount() {
		return customerAccount;
	}

	/**
	 * @param customerAccount the customerAccount to set
	 */
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	/**
	 * @return the limitFlag
	 */
	public Boolean getLimitFlag() {
		return limitFlag;
	}

	/**
	 * @param limitFlag the limitFlag to set
	 */
	public void setLimitFlag(Boolean limitFlag) {
		this.limitFlag = limitFlag;
	}



}
