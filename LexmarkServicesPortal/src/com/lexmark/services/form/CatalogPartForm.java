/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CatalogPartForm.java
 * Package     		: com.lexmark.services.form
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.form;

import java.io.Serializable;

/**
 * This class is used to catalog parts
 * @version 2.1
 * @author wipro
 *
 */
public class CatalogPartForm implements Serializable {

	/**
	 * variable declaration
	 */
	private static final long serialVersionUID = 9186616551474163709L;
	/**
	 * variable declaration
	 */
	private String partNumber;
	/**
	 * variable declaration
	 */
	private String description;
	/**
	 * variable declaration
	 */
	private String partType;
	/**
	 * variable declaration
	 */
	private String yield;
	/**
	 * variable declaration
	 */
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
	public String getYield() {
		return yield;
	}
	/**
	 * 
	 * @param yield 
	 */
	public void setYield(String yield) {
		this.yield = yield;
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
