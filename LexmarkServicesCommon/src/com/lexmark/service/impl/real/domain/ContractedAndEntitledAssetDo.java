package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file :  do-contractedandentitledasset-mapping.xml
 * 
 * @see com.lexmark.service.impl.real.orderSuppliesAssetService.AssetListService
 * 
 * @author vpetruchok
 * @version 1.0, 2012-08-29
 */
public class ContractedAndEntitledAssetDo extends AccountAsset implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String BO = "LXK MPS Contracted Consumable Asset List_POC";
    public static final String BC = "LXK MPS Contracted Consumable Asset List_POC";
    
    private String contractedMdmLevel1AccountId;
    private String contractedMdmLevel2AccountId;
    private String contractedMdmLevel3AccountId;
    private String contractedMdmLevel4AccountId;
    private String contractedMdmLevel5AccountId;
    private String contractedMdmLevel;
    private ArrayList<ConsumableAssetTypeDo> type;
    private Date entitlementEndDate;
    private String entitledCHLParentChain;
    private String mdmLevel;
    private String ownerAccountId;
    private String accountName;
    private String operatingStatus;
    private String entitlementCSSType;
    private String entitlementCSSEndDate;
    private String cssLegal;
    private String cssAccountId;
    private String cssGlobalDuns;
    private String contractNumber;

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

	public String getOperatingStatus() {
		return operatingStatus;
	}

	public void setOperatingStatus(String operatingStatus) {
		this.operatingStatus = operatingStatus;
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

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

}
