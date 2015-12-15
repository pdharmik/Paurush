package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.lexmark.domain.GenericAddress;

public class ServiceAddressListResult implements Serializable {
	private static final long serialVersionUID = 8963992268149540812L;
	private int totalCount;
	private List<GenericAddress> serviceAddresses  = new ArrayList<GenericAddress>(0);
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<GenericAddress> getServiceAddresses() {
		return serviceAddresses;
	}
	public void setServiceAddresses(List<GenericAddress> serviceAddresses) {
		this.serviceAddresses = serviceAddresses;
	}
	
}
