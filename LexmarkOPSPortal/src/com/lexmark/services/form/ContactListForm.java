/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ContactListForm.java
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

/**
 * this class is used to list contact form
 * @author wipro
 *
 */
public class ContactListForm implements Serializable{

	private static final long serialVersionUID = -655330254633234971L;
	
	private String contactListJSON;
	private String searchCriterias;
	
	/**
	 * 
	 * @return 
	 */
	public String getContactListJSON() {
		return contactListJSON;
	}
	/**
	 * 
	 * @param contactListJSON 
	 */
	public void setContactListJSON(String contactListJSON) {
		this.contactListJSON = contactListJSON;
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

}
