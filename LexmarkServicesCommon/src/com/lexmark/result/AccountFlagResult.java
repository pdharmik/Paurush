package com.lexmark.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexmark.domain.Account;


public class AccountFlagResult implements Serializable {
	
	private static final long serialVersionUID = 5862807388780613565L;
	
	private boolean assetEntitlementFlag;
	private List<Account> accountList;
	private boolean catalogEntitlementFlag;
	private Map<String, String> quantityServicesMap = new HashMap<String, String>();
	private Map<String, String> quantitySuppliesMap = new HashMap<String, String>();

	public Map<String, String> getQuantityServicesMap() {
		return quantityServicesMap;
	}
	public void setQuantityServicesMap(Map<String, String> quantityServicesMap) {
		this.quantityServicesMap = quantityServicesMap;
	}
	public Map<String, String> getQuantitySuppliesMap() {
		return quantitySuppliesMap;
	}
	public void setQuantitySuppliesMap(Map<String, String> quantitySuppliesMap) {
		this.quantitySuppliesMap = quantitySuppliesMap;
	}
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
