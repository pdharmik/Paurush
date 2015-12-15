package com.lexmark.services.form;

import java.io.Serializable;

/**
 * @author wipro 
 * @version 2.1 
 *
 */
public class AssetPartForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9186616551474163709L;
	private String partNumber;
	private String description;
	private String partType;
	private String orderQuantity;
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
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}
