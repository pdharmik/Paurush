package com.lexmark.service.api;

import com.lexmark.contract.AddressListContract;
import com.lexmark.result.AddressListResult;


public interface LBSLocationService {
	
	public AddressListResult retrieveLBSLocationList(AddressListContract contract) throws Exception;

	public AddressListResult retrieveLBSBuildingTypes(AddressListContract contract)	throws Exception;

}
