package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

import com.lexmark.domain.GenericAddress;

public class AssetLocationsResult implements Serializable {

	private static final long serialVersionUID = -4107725999061221777L;
	private List<GenericAddress> addressList;
	public List<GenericAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<GenericAddress> addressList) {
		this.addressList = addressList;
	}
}
