package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;
import com.lexmark.domain.GenericAddress;

public class AddressListForm implements Serializable{
	private static final long serialVersionUID = -655330254633234971L;
	private List<GenericAddress> addressList;
	private String searchName;
	private String searchCriteria ;
	/**
	 * 
	 * @return List
	 */
	public List<GenericAddress> getAddressList() {
		return addressList;
	}
	/**
	 * 
	 * @param addressList 
	 */
	public void setAddressList(List<GenericAddress> addressList) {
		this.addressList = addressList;
	}
	/**
	 * 
	 * @return String
	 */
	public String getSearchName() {
		return searchName;
	}
	/**
	 * 
	 * @param searchName 
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	/**
	 * 
	 * @return String
	 */
	public String getSearchCriteria() {
		return searchCriteria;
	}
	/**
	 * 
	 * @param searchCriteria 
	 */
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	/**
	 * 
	 * @return Long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	

}
