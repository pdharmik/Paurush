package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;

import com.lexmark.services.domain.CartItem;

public class ShoppingCartForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cartType;
	private List<CartItem> cartItems;
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
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	/**
	 * @return List 
	 */
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
}
