package com.lexmark.domain;

import java.io.Serializable;

public class CatalogCartViewForm implements Serializable {
	private static final long serialVersionUID = -6323900787987985163L;

	private String rowID;
	private String partNumber;
	private String description;
	private String partType;
	private String yield;
	private String quantity;
	
	public String getRowID() {
		return rowID;
	}

	public void setRowID(String rowID) {
		this.rowID = rowID;
	}	
	
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
	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}	
	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
