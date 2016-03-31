package com.lexmark.services.domain;

import java.math.BigDecimal;

public class CartItem {
	private String itemId,
	itemName,
	quantity,
	itemDescription,
	configId,
	imgUrl,
	unspscCode,
	itemType,
	productId,
	sapLineId,
	contractNumber;
	private BigDecimal price;
	private boolean showOptions;
	
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public boolean isShowOptions() {
		return showOptions;
	}
	public void setShowOptions(boolean showOptions) {
		this.showOptions = showOptions;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * @return the unspscCode
	 */
	public String getUnspscCode() {
		return unspscCode;
	}
	/**
	 * @param unspscCode the unspscCode to set
	 */
	public void setUnspscCode(String unspscCode) {
		this.unspscCode = unspscCode;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the sapLineId
	 */
	public String getSapLineId() {
		return sapLineId;
	}
	/**
	 * @param sapLineId the sapLineId to set
	 */
	public void setSapLineId(String sapLineId) {
		this.sapLineId = sapLineId;
	}
	/**
	 * @return the contractNumber
	 */
	public String getContractNumber() {
		return contractNumber;
	}
	/**
	 * @param contractNumber the contractNumber to set
	 */
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	
}
