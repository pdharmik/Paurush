package com.lexmark.domain;

import java.io.Serializable;

public class AccountAccess implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 185026255797644093L;
	
	private String createServiceReqFlag;
	private String showServiceReqFlag;
	private String createSuppliesReqFlag;
	private String showSuppliesReqFlag;
	private String createAccountMgmtReqFlag;
	private String showAccountMgmtReqFlag;
	private boolean catalogEntitlementFlag;
	private boolean assetEntitlementFlag;
	
	/* Added for Hardware Request in MPS2.1 */
	private String createHardwareReqFlag;
	private String showHardwareReqFlag;
	
	/* added for b2b requets*/
	private String showB2BRequestFlag;
	
	private String createMapSRFlag;
	private String fleetManagerFlag;
	private String lbsUtilization;
	private String lbsExpiring;
	private String lbsUtilizationExpiring;
	private String lbsNoAccess;
	
	public String getCreateServiceReqFlag() {
		return createServiceReqFlag;
	}
	public void setCreateServiceReqFlag(String createServiceReqFlag) {
		this.createServiceReqFlag = createServiceReqFlag;
	}
	public String getShowServiceReqFlag() {
		return showServiceReqFlag;
	}
	public void setShowServiceReqFlag(String showServiceReqFlag) {
		this.showServiceReqFlag = showServiceReqFlag;
	}
	public String getCreateSuppliesReqFlag() {
		return createSuppliesReqFlag;
	}
	public void setCreateSuppliesReqFlag(String createSuppliesReqFlag) {
		this.createSuppliesReqFlag = createSuppliesReqFlag;
	}
	public String getShowSuppliesReqFlag() {
		return showSuppliesReqFlag;
	}
	public void setShowSuppliesReqFlag(String showSuppliesReqFlag) {
		this.showSuppliesReqFlag = showSuppliesReqFlag;
	}
	public String getCreateAccountMgmtReqFlag() {
		return createAccountMgmtReqFlag;
	}
	public void setCreateAccountMgmtReqFlag(String createAccountMgmtReqFlag) {
		this.createAccountMgmtReqFlag = createAccountMgmtReqFlag;
	}
	public String getShowAccountMgmtReqFlag() {
		return showAccountMgmtReqFlag;
	}
	public void setShowAccountMgmtReqFlag(String showAccountMgmtReqFlag) {
		this.showAccountMgmtReqFlag = showAccountMgmtReqFlag;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setCatalogEntitlementFlag(boolean catalogEntitlementFlag) {
		this.catalogEntitlementFlag = catalogEntitlementFlag;
	}
	public boolean isCatalogEntitlementFlag() {
		return catalogEntitlementFlag;
	}
	public void setAssetEntitlementFlag(boolean assetEntitlementFlag) {
		this.assetEntitlementFlag = assetEntitlementFlag;
	}
	public boolean isAssetEntitlementFlag() {
		return assetEntitlementFlag;
	}
	
	public String getCreateHardwareReqFlag() {
		return createHardwareReqFlag;
	}
	public void setCreateHardwareReqFlag(String createHardwareReqFlag) {
		this.createHardwareReqFlag = createHardwareReqFlag;
	}
	public String getShowHardwareReqFlag() {
		return showHardwareReqFlag;
	}
	public void setShowHardwareReqFlag(String showHardwareReqFlag) {
		this.showHardwareReqFlag = showHardwareReqFlag;
	}
	
	public String getCreateMapSRFlag() {
		return createMapSRFlag;
	}
	public void setCreateMapSRFlag(String createMapSRFlag) {
		this.createMapSRFlag = createMapSRFlag;
	}

	
	public String getFleetManagerFlag() {
		return fleetManagerFlag;
	}
	public void setFleetManagerFlag(String fleetManagerFlag) {
		this.fleetManagerFlag = fleetManagerFlag;
	}


	public void setShowB2BRequestFlag(String showB2BRequestFlag) {
		this.showB2BRequestFlag = showB2BRequestFlag;
	}
	public String getShowB2BRequestFlag() {
		return showB2BRequestFlag;
	}


	public String getLbsUtilization() {
		return lbsUtilization;
	}
	public void setLbsUtilization(String lbsUtilization) {
		this.lbsUtilization = lbsUtilization;
	}


	public String getLbsExpiring() {
		return lbsExpiring;
	}
	public void setLbsExpiring(String lbsExpiring) {
		this.lbsExpiring = lbsExpiring;
	}


	public String getLbsUtilizationExpiring() {
		return lbsUtilizationExpiring;
	}
	public void setLbsUtilizationExpiring(String lbsUtilizationExpiring) {
		this.lbsUtilizationExpiring = lbsUtilizationExpiring;
	}


	public String getLbsNoAccess() {
		return lbsNoAccess;
	}
	public void setLbsNoAccess(String lbsNoAccess) {
		this.lbsNoAccess = lbsNoAccess;
	}


	private String accountID;
	private String accountName;
	
	
	

}
