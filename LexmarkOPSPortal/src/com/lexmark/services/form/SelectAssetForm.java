package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.Asset;

public class SelectAssetForm implements Serializable{

private static final long serialVersionUID = -655330254633234971L;
	
	private List<Asset> assetList;
	private String searchName;
	private String searchCriteria ;
	
	public List<Asset> getAssetList()
	{
		return assetList;
	}
	public void setAssetList(List<Asset> assets)
	{
		this.assetList = assets;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
}
