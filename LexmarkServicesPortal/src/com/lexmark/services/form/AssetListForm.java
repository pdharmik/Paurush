package com.lexmark.services.form;

import java.io.Serializable;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class AssetListForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -655330254633234971L;
	
	private String assetListJSON;
	private String searchCriterias;
	/**
	 * 
	 * @return String 
	 */
	public String getAssetListJSON() {
		return assetListJSON;
	}
	/**
	 * 
	 * @param assetListJSON 
	 */
	public void setAssetListJSON(String assetListJSON) {
		this.assetListJSON = assetListJSON;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getSearchCriterias() {
		return searchCriterias;
	}
	/**
	 * 
	 * @param searchCriterias 
	 */
	public void setSearchCriterias(String searchCriterias) {
		this.searchCriterias = searchCriterias;
	}
	
	

}
