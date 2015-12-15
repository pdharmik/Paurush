package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.SearchContractBase;



public class ServiceRequestListContract extends SearchContractBase implements Serializable {
	
	
	private static final long serialVersionUID = 3639748073450834260L;
	private String mdmID;
	private String mdmLevel;
	private String status;
	private String contactID;
	private Locale locale;
	private String chlNodeId; 
	private String accountId;
	private String serviceRequestNumber;
	private String assetType;
	
	private boolean assetFavoriteFlag;//added by sagar
	private boolean onlyDraftRequestFlag;//added by sagar
	private boolean showAllFlag;
	
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}
	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getMdmID() {
		return mdmID;
	}
	public void setMdmID(String mdmID) {
		this.mdmID = mdmID;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContactID() {
		return contactID;
	}
	public void setContactID(String contactID) {
		this.contactID = contactID;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getChlNodeId() {
		return chlNodeId;
	}
	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}
	/**
	 * @return the assetFavoriteFlag
	 */
	public boolean isAssetFavoriteFlag() {
		return assetFavoriteFlag;
	}
	/**
	 * @param assetFavoriteFlag the assetFavoriteFlag to set
	 */
	public void setAssetFavoriteFlag(boolean assetFavoriteFlag) {
		this.assetFavoriteFlag = assetFavoriteFlag;
	}
	/**
	 * @return the onlyDraftRequestFlag
	 */
	public boolean isOnlyDraftRequestFlag() {
		return onlyDraftRequestFlag;
	}
	/**
	 * @param onlyDraftRequestFlag the onlyDraftRequestFlag to set
	 */
	public void setOnlyDraftRequestFlag(boolean onlyDraftRequestFlag) {
		this.onlyDraftRequestFlag = onlyDraftRequestFlag;
	}
	public void setShowAllFlag(boolean showAllFlag) {
		this.showAllFlag = showAllFlag;
	}
	public boolean isShowAllFlag() {
		return showAllFlag;
	}
}

