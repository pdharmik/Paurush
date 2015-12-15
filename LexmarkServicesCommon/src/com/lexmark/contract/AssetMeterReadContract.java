package com.lexmark.contract;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import com.lexmark.contract.api.ContractBase;
import com.lexmark.domain.Asset;

public class AssetMeterReadContract extends ContractBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String contactId;
	private Asset asset;
	private String mdmId;
	private String mdmLevel;
	private List<Asset> assets;
	private String userFileName;
	private InputStream fileStream;

	public String getUserFileName() {
		return userFileName;
	}
	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}
	public InputStream getFileStream() {
		return fileStream;
	}
	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
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
	public List<Asset> getAssets() {
		return assets;
	}
	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
	
	
	
}
