package com.lexmark.contract;

import java.io.Serializable;
import java.util.Locale;

import com.lexmark.contract.api.SearchContractBase;


public class AssetListContract extends SearchContractBase implements Serializable {
	private static final long serialVersionUID = 6271593374797175651L;
	private String mdmId;
	private String mdmLevel;
	private String contactId;
	private boolean favoriteFlag;
	private String chlNodeId;
	private Locale locale;
	// It is to distinguish whether load all results initially
	private boolean loadAllFlag;
	private String assetType;
	private String languageName;
	private String entitlementEndDate;
	private boolean alliancePartner;                    //flag created for Dell/Boeing split calls
	private boolean downloadCall;
	private String lbsFilterCriteriaFlag;
	
	public boolean isDownloadCall() {
		return downloadCall;
	}
	public void setDownloadCall(boolean downloadCall) {
		this.downloadCall = downloadCall;
	}
	public boolean isAlliancePartner() {
		return alliancePartner;
	}
	public void setAlliancePartner(boolean alliancePartner) {
		this.alliancePartner = alliancePartner;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public boolean isFavoriteFlag() {
		return favoriteFlag;
	}
	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	public boolean getLoadAllFlag() {
		return loadAllFlag;
	}
	public void setLoadAllFlag(boolean loadAllFlag) {
		this.loadAllFlag = loadAllFlag;
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
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getChlNodeId() {
		return chlNodeId;
	}
	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}
    public String getLanguageName() {
        return languageName;
    }
    
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
	public void setEntitlementEndDate(String entitlementEndDate) {
		this.entitlementEndDate = entitlementEndDate;
	}
	public String getEntitlementEndDate() {
		return entitlementEndDate;
	}
	public void setLbsFilterCriteriaFlag(String lbsFilterCriteriaFlag) {
		this.lbsFilterCriteriaFlag = lbsFilterCriteriaFlag;
	}
	public String getLbsFilterCriteriaFlag() {
		return lbsFilterCriteriaFlag;
	}
	
	
}
