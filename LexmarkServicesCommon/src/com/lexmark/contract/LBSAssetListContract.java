package com.lexmark.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.contract.api.SearchContractBase;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.impl.real.domain.LBSAsset;

public class LBSAssetListContract extends SearchContractBase implements Serializable {
	
	private static final long serialVersionUID = -8909858949224665519L;
	private CrmSessionHandle sessionHandle;
	private String mdmId;
	private String mdmLevel;
	private boolean lbsFlag;
	private List<String> assetIds;
	private String accountId;
	private String productName;
	private String serialNumber;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public CrmSessionHandle getSessionHandle() {
		return sessionHandle;
	}
	public void setSessionHandle(CrmSessionHandle sessionHandle) {
		this.sessionHandle = sessionHandle;
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
	public boolean isLbsFlag() {
		return lbsFlag;
	}
	public void setLbsFlag(boolean lbsFlag) {
		this.lbsFlag = lbsFlag;
	}
	public List<String> getAssetIds() {
		return assetIds;
	}
	public void setAssetIds(List<String> assetIds) {
		this.assetIds = assetIds;
	}
}
