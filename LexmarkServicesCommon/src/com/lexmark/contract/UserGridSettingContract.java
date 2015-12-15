package com.lexmark.contract;

import java.io.Serializable;

public class UserGridSettingContract implements Serializable {

	private static final long serialVersionUID = -1212392811007985693L;
	private String userNumber;
	private String gridId;
	private String colsOrder;
	private String colsWidth;
	private String colsSorting;
	private String colsHidden;
	private String colsFilter;
	private String setting;
	private String settingType;
	//private List<UserGridSetting> userGridSettings;
	
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getGridId() {
		return gridId;
	}
	public void setGridId(String gridId) {
		this.gridId = gridId;
	}
	public String getColsOrder() {
		return colsOrder;
	}
	public void setColsOrder(String colsOrder) {
		this.colsOrder = colsOrder;
	}
	public String getColsWidth() {
		return colsWidth;
	}
	public void setColsWidth(String colsWidth) {
		this.colsWidth = colsWidth;
	}
	public String getColsSorting() {
		return colsSorting;
	}
	public void setColsSorting(String colsSorting) {
		this.colsSorting = colsSorting;
	}
	public String getColsHidden() {
		return colsHidden;
	}
	public void setColsHidden(String colsHidden) {
		this.colsHidden = colsHidden;
	}
	public String getColsFilter() {
		return colsFilter;
	}
	public void setColsFilter(String colsFilter) {
		this.colsFilter = colsFilter;
	}
	
	public String getSetting() {
		return setting;
	}
	public void setSetting(String setting) {
		this.setting = setting;
	}
	public String getSettingType() {
		return settingType;
	}
	public void setSettingType(String settingType) {
		this.settingType = settingType;
	}
	

}
