package com.lexmark.domain;

import java.io.Serializable;

public class UserGridSetting implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1569984543528543332L;
	private String userNumber;
	private String gridId;
	private String colsOrder;
	private String colsWidth;
	private String colsSorting;
	private String colsHidden;
	private String colsFilter;
	
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
	
}
