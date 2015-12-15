package com.lexmark.services.form;

import java.io.Serializable;
import java.util.List;
import com.lexmark.domain.GenericAddress;

public class AddressListForm implements Serializable{
	private static final long serialVersionUID = -655330254633234971L;
	private List<GenericAddress> addressList;
	private String searchName;
	private String searchCriteria ;
	public List<GenericAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<GenericAddress> addressList) {
		this.addressList = addressList;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	

}
