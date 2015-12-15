package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-partnerdirectaccount-mapping.xml"
 */
public class PartnerDirectAccountDo extends BaseEntity {
	
	private String accountId;
	private String accountName;
	private String employeeId;
	private String organizationId;
	private Boolean primaryFlag;
	private ArrayList<PartnerDirectAccountFlagDo> flags;
	private ArrayList<PartnerDirectAccountTypeDo>  accountTypes;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public void setFlags(ArrayList<PartnerDirectAccountFlagDo> flags) {
		this.flags = flags;
	}
	public ArrayList<PartnerDirectAccountFlagDo> getFlags() {
		return flags;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public void setAccountTypes(ArrayList<PartnerDirectAccountTypeDo> accountTypes) {
		this.accountTypes = accountTypes;
	}
	public ArrayList<PartnerDirectAccountTypeDo> getAccountTypes() {
		return accountTypes;
	}
	public void setPrimaryFlag(Boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	public Boolean getPrimaryFlag() {
		return primaryFlag;
	}
}
