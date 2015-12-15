package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

public class AccountBasedDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -7177661788638774036L;

	private String mdmLevel1AccountId;
	private String mdmLevel2AccountId;
	private String mdmLevel3AccountId;
	private String mdmLevel4AccountId;
	private String mdmLevel5AccountId;
	private String accountTransFlag;
	private String entitledMdmLevel1AccountId;
	private String entitledMdmLevel2AccountId;
	private String entitledMdmLevel3AccountId;
	private String entitledMdmLevel4AccountId;
	private String entitledMdmLevel5AccountId;
	private String entitlementType;
	private Date entitlementEndDate;
	private String entitledMdmLevel;
	public String getMdmLevel1AccountId() {
		return mdmLevel1AccountId;
	}

	public void setMdmLevel1AccountId(String mdmLevel1AccountId) {
		this.mdmLevel1AccountId = mdmLevel1AccountId;
	}

	public String getMdmLevel2AccountId() {
		return mdmLevel2AccountId;
	}

	public void setMdmLevel2AccountId(String mdmLevel2AccountId) {
		this.mdmLevel2AccountId = mdmLevel2AccountId;
	}

	public String getMdmLevel3AccountId() {
		return mdmLevel3AccountId;
	}

	public void setMdmLevel3AccountId(String mdmLevel3AccountId) {
		this.mdmLevel3AccountId = mdmLevel3AccountId;
	}

	public String getMdmLevel4AccountId() {
		return mdmLevel4AccountId;
	}

	public void setMdmLevel4AccountId(String mdmLevel4AccountId) {
		this.mdmLevel4AccountId = mdmLevel4AccountId;
	}

	public String getAccountTransFlag() {
		return accountTransFlag;
	}

	public void setAccountTransFlag(String accountTransFlag) {
		this.accountTransFlag = accountTransFlag;
	}

	public void setMdmLevel5AccountId(String mdmLevel5AccountId) {
		this.mdmLevel5AccountId = mdmLevel5AccountId;
	}

	public String getMdmLevel5AccountId() {
		return mdmLevel5AccountId;
	}

	public String getEntitledMdmLevel1AccountId() {
		return entitledMdmLevel1AccountId;
	}

	public void setEntitledMdmLevel1AccountId(String entitledMdmLevel1AccountId) {
		this.entitledMdmLevel1AccountId = entitledMdmLevel1AccountId;
	}

	public String getEntitledMdmLevel2AccountId() {
		return entitledMdmLevel2AccountId;
	}

	public void setEntitledMdmLevel2AccountId(String entitledMdmLevel2AccountId) {
		this.entitledMdmLevel2AccountId = entitledMdmLevel2AccountId;
	}

	public String getEntitledMdmLevel3AccountId() {
		return entitledMdmLevel3AccountId;
	}

	public void setEntitledMdmLevel3AccountId(String entitledMdmLevel3AccountId) {
		this.entitledMdmLevel3AccountId = entitledMdmLevel3AccountId;
	}

	public String getEntitledMdmLevel4AccountId() {
		return entitledMdmLevel4AccountId;
	}

	public void setEntitledMdmLevel4AccountId(String entitledMdmLevel4AccountId) {
		this.entitledMdmLevel4AccountId = entitledMdmLevel4AccountId;
	}

	public String getEntitledMdmLevel5AccountId() {
		return entitledMdmLevel5AccountId;
	}

	public void setEntitledMdmLevel5AccountId(String entitledMdmLevel5AccountId) {
		this.entitledMdmLevel5AccountId = entitledMdmLevel5AccountId;
	}

	public String getEntitlementType() {
		return entitlementType;
	}

	public void setEntitlementType(String entitlementType) {
		this.entitlementType = entitlementType;
	}

	public Date getEntitlementEndDate() {
		return entitlementEndDate;
	}

	public void setEntitlementEndDate(Date entitlementEndDate) {
		this.entitlementEndDate = entitlementEndDate;
	}

	public String getEntitledMdmLevel() {
		return entitledMdmLevel;
	}

	public void setEntitledMdmLevel(String entitledMdmLevel) {
		this.entitledMdmLevel = entitledMdmLevel;
	}
	
	
}
