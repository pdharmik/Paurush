package com.lexmark.service.api;

import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.ManualAssetContract;
import com.lexmark.contract.ModifyContactContract;
import com.lexmark.contract.ServiceRequestAssociatedListContract;
import com.lexmark.contract.ServiceRequestContract;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.ManualAssetContract;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.ManualAssetResult;
import com.lexmark.result.ModifyContactResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.result.ServiceRequestResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.result.ManualAssetResult;


public interface ServiceRequestService {

	public ServiceRequestListResult  retrieveServiceRequestList(ServiceRequestListContract contract)
		throws Exception;
	
	public CreateServiceRequestResult createServiceRequest(CreateServiceRequestContract contract)
	throws Exception;
	
	public ModifyContactResult modifyContact(ModifyContactContract modifyContactContract)
	throws Exception;

	//Create updateUserFavoriteAddress to pass the compile.
	//public void updateUserFavoriteAddress() throws Exception;
	public FavoriteResult updateUserFavoriteAddress(FavoriteAddressContract favoriteAddressContract) 
	throws Exception;
	
	public SiebelAccountListResult retrieveSiebelAccountList (SiebelAccountListContract contract);

	public AddressListResult retrieveAddressList(AddressListContract contract)throws Exception;
	
	public ManualAssetResult validateManualAsset (ManualAssetContract contract) throws Exception;

	public ServiceRequestListResult retrieveAssociatedServiceRequestList(
			ServiceRequestAssociatedListContract contract)throws Exception;
	
	public ServiceRequestListResult retrieveServiceRequestHistoryList(ServiceRequestHistoryListContract contract) throws Exception;

	public AddressListResult retrieveLBSAddressList(AddressListContract contract) throws Exception;
}
