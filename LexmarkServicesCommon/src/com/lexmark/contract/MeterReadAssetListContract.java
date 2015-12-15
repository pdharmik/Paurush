package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.SearchContractBase;

/*
 * do-contractedmeterreadassetdo-mapping.xml
 */
public class MeterReadAssetListContract extends SearchContractBase implements Serializable {
	private static final long serialVersionUID = 4922946638916425263L;
	
	private String mdmID;
	private String mdmLevel;
	private String favoriteContactId;
	private String contactId;
	private String meterReadType;
	private String chlNodeId;
	private boolean favoriteFlag;
	private String entitlementEndDate;
	private boolean alliancePartner;                    //flag created for Dell/Boeing split calls
	private int numofDays; 
	
	public int getNumofDays() {
		return numofDays;
	}
	public void setNumofDays(int numofDays) {
		this.numofDays = numofDays;
	}
	public boolean isAlliancePartner() {
		return alliancePartner;
	}
	public void setAlliancePartner(boolean alliancePartner) {
		this.alliancePartner = alliancePartner;
	}
	public boolean isFavoriteFlag() {
		return favoriteFlag;
	}
	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	private Integer missingTerm;
	
	public String getMdmID() {
		return mdmID;
	}
	public void setMdmID(String mdmId) {
		this.mdmID = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getFavoriteContactId() {
		return favoriteContactId;
	}
	public void setFavoriteContactId(String favoriteContactId) {
		this.favoriteContactId = favoriteContactId;
	}
	public String getMeterReadType() {
		return meterReadType;
	}
	public void setMeterReadType(String meterReadType) {
		this.meterReadType = meterReadType;
	}
	public String getChlNodeId() {
		return chlNodeId;
	}
	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}
	public Integer getMissingTerm() {
		return missingTerm;
	}
	public void setMissingTerm(Integer missingTerm) {
		this.missingTerm = missingTerm;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getContactId() {
		return contactId;
	}
	public String getEntitlementEndDate() {
		return entitlementEndDate;
	}
	public void setEntitlementEndDate(String entitlementEndDate) {
		this.entitlementEndDate = entitlementEndDate;
	} 
}
