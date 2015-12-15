/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: PageCountsUpdateForm.java
 * Package     		: com.lexmark.services.form
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wipro
 * for page counts pop-up data save
 */
public class PageCountsUpdateForm extends BaseForm implements Serializable {

	private static final long serialVersionUID = 11194564355L;
	
	private String assetId;
	private String serialNumber;
	private String lastPageCount;
	private String newPageCount;
	private String lastColorPageCount;
	private String newColorPageCount;
	private Boolean colorCapableFlag;
	private Date lastPageReadDate;
	private Date lastColorPageReadDate;
	private Date newPageReadDate;
	private Date newColorPageReadDate;
	/**
	 * 
	 * @return
	 */
	public String getAssetId() {
		return assetId;
	}
	/**
	 * 
	 * @param assetId
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	/**
	 * 
	 * @return
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	/**
	 * 
	 * @param serialNumber
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * 
	 * @return
	 */
	public String getLastPageCount() {
		return lastPageCount;
	}
	/**
	 * 
	 * @param lastPageCount
	 */
	public void setLastPageCount(String lastPageCount) {
		this.lastPageCount = lastPageCount;
	}
	/**
	 * 
	 * @return
	 */
	public String getNewPageCount() {
		return newPageCount;
	}
	/**
	 * 
	 * @param newPageCount
	 */
	public void setNewPageCount(String newPageCount) {
		this.newPageCount = newPageCount;
	}
	/**
	 * 
	 * @return
	 */
	public String getLastColorPageCount() {
		return lastColorPageCount;
	}
	/**
	 * 
	 * @param lastColorPageCount
	 */
	public void setLastColorPageCount(String lastColorPageCount) {
		this.lastColorPageCount = lastColorPageCount;
	}
	/**
	 * 
	 * @return
	 */
	public String getNewColorPageCount() {
		return newColorPageCount;
	}
	/**
	 * 
	 * @param newColorPageCount
	 */
	public void setNewColorPageCount(String newColorPageCount) {
		this.newColorPageCount = newColorPageCount;
	}
	/**
	 * 
	 * @return
	 */
	public Boolean getColorCapableFlag() {
		return colorCapableFlag;
	}
	/**
	 * 
	 * @param colorCapableFlag
	 */
	public void setColorCapableFlag(Boolean colorCapableFlag) {
		this.colorCapableFlag = colorCapableFlag;
	}
	/**
	 * 
	 * @return
	 */
	public Date getLastPageReadDate() {
		return lastPageReadDate;
	}
	/**
	 * 
	 * @param lastPageReadDate
	 */
	public void setLastPageReadDate(Date lastPageReadDate) {
		this.lastPageReadDate = lastPageReadDate;
	}
	/**
	 * 
	 * @return
	 */
	public Date getLastColorPageReadDate() {
		return lastColorPageReadDate;
	}
	/**
	 * 
	 * @param lastColorPageReadDate
	 */
	public void setLastColorPageReadDate(Date lastColorPageReadDate) {
		this.lastColorPageReadDate = lastColorPageReadDate;
	}
	/**
	 * 
	 * @return
	 */
	public Date getNewPageReadDate() {
		return newPageReadDate;
	}
	/**
	 * 
	 * @param newPageReadDate
	 */
	public void setNewPageReadDate(Date newPageReadDate) {
		this.newPageReadDate = newPageReadDate;
	}
	/**
	 * 
	 * @return
	 */
	public Date getNewColorPageReadDate() {
		return newColorPageReadDate;
	}
	/**
	 * 
	 * @param newColorPageReadDate
	 */
	public void setNewColorPageReadDate(Date newColorPageReadDate) {
		this.newColorPageReadDate = newColorPageReadDate;
	}
}
