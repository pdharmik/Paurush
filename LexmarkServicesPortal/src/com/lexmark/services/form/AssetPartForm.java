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
	/**
	 * 
	 * @return String 
	 */
	public String getPartNumber() {
		return partNumber;
	}
	/**
	 * 
	 * @param partNumber 
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 
	 * @param description 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getPartType() {
		return partType;
	}
	/**
	 * 
	 * @param partType 
	 */
	public void setPartType(String partType) {
		this.partType = partType;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getOrderQuantity() {
		return orderQuantity;
	}
	/**
	 * 
	 * @param orderQuantity 
	 */
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
}
