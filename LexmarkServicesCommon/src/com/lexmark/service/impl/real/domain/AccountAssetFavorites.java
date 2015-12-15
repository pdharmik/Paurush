package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * mapping file: "do-assetFavorites-mapping.xml"
 */
public class AccountAssetFavorites extends MeterReadBase implements Serializable {
	private static final long serialVersionUID = 1417674785149968704L;

    public static final String BO = "LXK SW Contact Favorite Assets_POC"; 
    public static final String BC = "LXK SW Contact Favorite Assets_POC"; 

	private String emailAddress;
	private String firstName;
	private String lastName;
	private String workPhone;
	private boolean assetFavFlag;
	private String meterType;
	private String assetType;
    private Boolean consumableAssetFlag;
    private String ownerAccountId;
    private String accountName;  
    private String cssLegal;
    private String cssAccountId;
    private String cssGlobalDuns;
    private String entitlementCSSType;
    private String entitlementCSSEndDate;
    
	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public boolean isAssetFavFlag() {
		return assetFavFlag;
	}

	public void setAssetFavFlag(boolean assetFavFlag) {
		this.assetFavFlag = assetFavFlag;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

    public Boolean isConsumableAssetFlag() {
        return consumableAssetFlag;
    }

    public void setConsumableAssetFlag(Boolean consumableAssetFlag) {
        this.consumableAssetFlag = consumableAssetFlag;
    }

    public String getOwnerAccountId() {
        return ownerAccountId;
    }

    public void setOwnerAccountId(String ownerAccountId) {
        this.ownerAccountId = ownerAccountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

	public String getCssLegal() {
		return cssLegal;
	}

	public void setCssLegal(String cssLegal) {
		this.cssLegal = cssLegal;
	}

	public String getCssAccountId() {
		return cssAccountId;
	}

	public void setCssAccountId(String cssAccountId) {
		this.cssAccountId = cssAccountId;
	}

	public String getCssGlobalDuns() {
		return cssGlobalDuns;
	}

	public void setCssGlobalDuns(String cssGlobalDuns) {
		this.cssGlobalDuns = cssGlobalDuns;
	}

	public Boolean getConsumableAssetFlag() {
		return consumableAssetFlag;
	}

	public String getEntitlementCSSType() {
		return entitlementCSSType;
	}

	public void setEntitlementCSSType(String entitlementCSSType) {
		this.entitlementCSSType = entitlementCSSType;
	}

	public String getEntitlementCSSEndDate() {
		return entitlementCSSEndDate;
	}

	public void setEntitlementCSSEndDate(String entitlementCSSEndDate) {
		this.entitlementCSSEndDate = entitlementCSSEndDate;
	}
    
    
}
