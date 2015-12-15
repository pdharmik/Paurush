package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.GenericAddress;

public class PartnerAddressListResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1407086597390240600L;
	
	private int totalCount;
	private List<GenericAddress> addressList  = new ArrayList<GenericAddress>(0);
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<GenericAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<GenericAddress> addressList) {
		this.addressList = addressList;
	}
	
	
	
	

}
