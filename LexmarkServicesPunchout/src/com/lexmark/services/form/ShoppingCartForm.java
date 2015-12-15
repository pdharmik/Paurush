package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;

public class ShoppingCartForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cartType;
	private List<Object> cartItems;
	/**
	 * @param cartType 
	 */
	public void setCartType(String cartType) {
		this.cartType = cartType;
	}
	/**
	 * @return String 
	 */
	public String getCartType() {
		return cartType;
	}
	/**
	 * @param cartItems 
	 */
	public void setCartItems(List<Object> cartItems) {
		this.cartItems = cartItems;
	}
	/**
	 * @return List 
	 */
	public List<Object> getCartItems() {
		return cartItems;
	}
	
}
