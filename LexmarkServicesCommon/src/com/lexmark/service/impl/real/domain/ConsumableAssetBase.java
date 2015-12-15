package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * mapping file: "do-consumableassetbase-mapping.xml"
 */

public class ConsumableAssetBase extends AccountAsset implements Serializable {


    private static final long serialVersionUID = 1L;
    
    public static final String BO = "LXK MPS Contracted Consumable Asset List";
    public static final String BC = "LXK MPS Contracted Consumable Asset List";
    
    private String contractedMdmLevel1AccountId;
    private String contractedMdmLevel2AccountId;
    private String contractedMdmLevel3AccountId;
    private String contractedMdmLevel4AccountId;
    private String contractedMdmLevel5AccountId;
    private String contractedMdmLevel;
    private ArrayList<ConsumableAssetTypeDo> type;
    private String entitlementType;
    private Date entitlementEndDate;
    private String entitledCHLParentChain;
    private String mdmLevel;
    private String ownerAccountId;
    private String accountName;

	public String getContractedMdmLevel1AccountId() {
		return contractedMdmLevel1AccountId;
	}

	public void setContractedMdmLevel1AccountId(String contractedMdmLevel1AccountId) {
		this.contractedMdmLevel1AccountId = contractedMdmLevel1AccountId;
	}

	public String getContractedMdmLevel2AccountId() {
		return contractedMdmLevel2AccountId;
	}

	public void setContractedMdmLevel2AccountId(String contractedMdmLevel2AccountId) {
		this.contractedMdmLevel2AccountId = contractedMdmLevel2AccountId;
	}

	public String getContractedMdmLevel3AccountId() {
		return contractedMdmLevel3AccountId;
	}

	public void setContractedMdmLevel3AccountId(String contractedMdmLevel3AccountId) {
		this.contractedMdmLevel3AccountId = contractedMdmLevel3AccountId;
	}

	public String getContractedMdmLevel4AccountId() {
		return contractedMdmLevel4AccountId;
	}

	public void setContractedMdmLevel4AccountId(String contractedMdmLevel4AccountId) {
		this.contractedMdmLevel4AccountId = contractedMdmLevel4AccountId;
	}

	public String getContractedMdmLevel5AccountId() {
		return contractedMdmLevel5AccountId;
	}

	public void setContractedMdmLevel5AccountId(String contractedMdmLevel5AccountId) {
		this.contractedMdmLevel5AccountId = contractedMdmLevel5AccountId;
	}

	public String getContractedMdmLevel() {
		return contractedMdmLevel;
	}

	public void setContractedMdmLevel(String contractedMdmLevel) {
		this.contractedMdmLevel = contractedMdmLevel;
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

	public void setEntitledCHLParentChain(String entitledCHLParentChain) {
		this.entitledCHLParentChain = entitledCHLParentChain;
	}

	public String getEntitledCHLParentChain() {
		return entitledCHLParentChain;
	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	public String getMdmLevel() {
		return mdmLevel;
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

	public void setType(ArrayList<ConsumableAssetTypeDo> type) {
		this.type = type;
	}

	public ArrayList<ConsumableAssetTypeDo> getType() {
		return type;
	}


}
