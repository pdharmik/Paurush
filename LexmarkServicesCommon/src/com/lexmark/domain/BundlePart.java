package com.lexmark.domain;

import java.io.Serializable;

public class BundlePart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String partNumber;
	private String description;
	private String qty;
	private String CatalogId;
	private String supplyId;
	private String parentLineId;
	private String lineId;
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getCatalogId() {
		return CatalogId;
	}
	public void setCatalogId(String catalogId) {
		CatalogId = catalogId;
	}
	public String getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}
	public String getParentLineId() {
		return parentLineId;
	}
	public void setParentLineId(String parentLineId) {
		this.parentLineId = parentLineId;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	

}
