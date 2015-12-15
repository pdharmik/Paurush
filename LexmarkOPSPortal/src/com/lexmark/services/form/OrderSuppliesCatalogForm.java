/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: OrderSuppliesCatalogForm.java
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

import com.lexmark.domain.OrderSuppliesCatalog;

/**
 * This form bean class is used for catalog order section controller
 * @author wipro
 * @version 2.1, 2013-10-30
 * 
 */
public class OrderSuppliesCatalogForm {
	private String catalogPartListJSON;
	private String searchCriterias;
	private OrderSuppliesCatalog orderSuppliesCatalog;
	private boolean createServiceRequestFlag ;
	
	/**
	 * 
	 * @return
	 */
	public OrderSuppliesCatalog getOrderSuppliesCatalog() {
		return orderSuppliesCatalog;
	}
	
	/**
	 * 
	 * @param orderSuppliesCatalog
	 */
	public void setOrderSuppliesCatalog(OrderSuppliesCatalog orderSuppliesCatalog) {
		this.orderSuppliesCatalog = orderSuppliesCatalog;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCatalogPartListJSON() {
		return catalogPartListJSON;
	}
	
	/**
	 * 
	 * @param catalogPartListJSON
	 */
	public void setCatalogPartListJSON(String catalogPartListJSON) {
		this.catalogPartListJSON = catalogPartListJSON;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSearchCriterias() {
		return searchCriterias;
	}
	
	/**
	 * 
	 * @param searchCriterias
	 */
	public void setSearchCriterias(String searchCriterias) {
		this.searchCriterias = searchCriterias;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCreateServiceRequestFlag() {
		return createServiceRequestFlag;
	}
	
	/**
	 * 
	 * @param createServiceRequestFlag
	 */
	public void setCreateServiceRequestFlag(boolean createServiceRequestFlag) {
		this.createServiceRequestFlag = createServiceRequestFlag;
	}
}
