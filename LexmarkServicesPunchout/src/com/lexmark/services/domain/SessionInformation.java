package com.lexmark.services.domain;

import java.util.Map;

import com.lexmark.services.form.ShoppingCartForm;

public class SessionInformation {
	private String supplierId; 
	private String buyerCookie;
	private String supplierCookie;
	private String operation;
	private String userId;
	private String customerNumbers;
	private String country;
	private String language;
	private String duns;
	private String networkUserId;
	private String sharedSecret;
	private String identity;
	private Map<String, ShoppingCartForm> shoppingCartForm;
	private String formPostURL;
	
	
	
	/**
	 * @return String 
	 */
	public String getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId 
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return String 
	 */
	public String getBuyerCookie() {
		return buyerCookie;
	}

	/**
	 * @param buyerCookie 
	 */
	public void setBuyerCookie(String buyerCookie) {
		this.buyerCookie = buyerCookie;
	}
	
	/**
	 * @return String 
	 */
	public String getSupplierCookie() {
		return supplierCookie;
	}

	/**
	 * @param supplierCookie 
	 */
	public void setSupplierCookie(String supplierCookie) {
		this.supplierCookie = supplierCookie;
	}

	/**
	 * @return String 
	 */
	public String getOperation() {
		return operation;
	}
	/**.
	 * 
	 * This method sets the FormPostURL 
	 * 
	 * @param operation 
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	/**
	 * @return String 
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * @param country 
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @return String 
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * @param language 
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/**
	 * @return String 
	 */
	public String getDuns() {
		return duns;
	}
	/**.
	 * 
	 * This method sets the Duns 
	 * 
	 * @param duns 
	 */
	public void setDuns(String duns) {
		this.duns = duns;
	}
	
	/**
	 * @return String 
	 */
	public String getNetworkUserId() {
		return networkUserId;
	}
	/**
	 * @param networkUserId 
	 */
	public void setNetworkUserId(String networkUserId) {
		this.networkUserId = networkUserId;
	}
	
	/**
	 * @return String 
	 */
	public String getSharedSecret() {
		return sharedSecret;
	}
	/**
	 * @param sharedSecret 
	 */
	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}
	/**
	 * @param shoppingCartForm 
	 */
	public void setShoppingCartForm(Map<String, ShoppingCartForm> shoppingCartForm) {
		this.shoppingCartForm = shoppingCartForm;
	}

	/**
	 * @return ShoppingCartForm 
	 */
	public Map<String, ShoppingCartForm> getShoppingCartForm() {
		return shoppingCartForm;
	}
	/**
	 * @param customerNumbers 
	 */
	public void setCustomerNumbers(String customerNumbers) {
		this.customerNumbers = customerNumbers;
	}
	
	/**
	 * @return String 
	 */
	public String getCustomerNumbers() {
		return customerNumbers;
	}
	/**
	 * @param userId 
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return String 
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param identity 
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	/**
	 * @return String 
	 */
	public String getIdentity() {
		return identity;
	}
	/**.
	 * 
	 * This method sets the FormPostURL 
	 * 
	 * @param formPostURL 
	 */
	public void setFormPostURL(String formPostURL) {
		this.formPostURL = formPostURL;
	}

	/**
	 * @return String 
	 */
	public String getFormPostURL() {
		return formPostURL;
	}
}
