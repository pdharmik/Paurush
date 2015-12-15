package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.OrderPart;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class CartViewForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4466783334622272250L;
	private List<OrderPart> catalogPartList;
	/**
	 * 
	 * @return List 
	 */
	public List<OrderPart> getCatalogPartList() {
		return catalogPartList;
	}
	/**
	 * 
	 * @param catalogPartList 
	 */
	public void setCatalogPartList(List<OrderPart> catalogPartList) {
		this.catalogPartList = catalogPartList;
	}
}
