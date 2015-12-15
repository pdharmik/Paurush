package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.Account;


public class AccountFlagResult implements Serializable {
	
	private static final long serialVersionUID = 5862807388780613565L;
	
	private boolean assetEntitlementFlag;
	private List<Account> accountList;
	private boolean catalogEntitlementFlag;
	
	public boolean isAssetEntitlementFlag() {
		return assetEntitlementFlag;
	}
	public void setAssetEntitlementFlag(boolean assetEntitlementFlag) {
		this.assetEntitlementFlag = assetEntitlementFlag;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public boolean isCatalogEntitlementFlag() {
		return catalogEntitlementFlag;
	}
	public void setCatalogEntitlementFlag(boolean catalogEntitlementFlag) {
		this.catalogEntitlementFlag = catalogEntitlementFlag;
	}
}
