package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.FavoriteAddressContract;
import com.lexmark.contract.ModifyContactContract;
import com.lexmark.contract.ServiceAddressListContract;
import com.lexmark.contract.ServiceRequestAssociatedListContract;
import com.lexmark.contract.ServiceRequestContract;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.contract.ServiceRequestListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.ManualAssetContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.ModifyContactResult;
import com.lexmark.result.ServiceAddressListResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.result.ServiceRequestResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.result.ManualAssetResult;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.CollectionSorter;

public class ServiceRequestServiceImpl implements ServiceRequestService  {
	private static Logger logger = LogManager.getLogger(ServiceRequestServiceImpl.class);


	public ServiceRequestListResult retrieveServiceRequestList(
			ServiceRequestListContract contract) throws Exception {
		ServiceRequestListResult srlr = new ServiceRequestListResult();
		
		List<ServiceRequest> serviceRequests = DomainMockDataGenerator.getServiceRequestsList();
		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
		List<ServiceRequest> suitResult = serviceRequests;
		Map<String, Object> statusCriteria = new HashMap<String, Object>();
/*		if(contract.getStatus()!=null && contract.getStatus()!="" )
		{
			statusCriteria.put("serviceRequestStatus", contract.getStatus());
			suitResult = filter.filter(suitResult, null, statusCriteria);
		}else 
*/
		if(contract.getContactID() !=null && contract.getContactID() != "")
		{
			statusCriteria.put("requestor.contactId", contract.getAccountId());
			suitResult = filter.filter(suitResult, null, statusCriteria);
		} else if(contract.getAssetType() !=null && contract.getAssetType() != "")
		{
			statusCriteria.put("asset.assetType", contract.getAssetType());
			suitResult = filter.filter(suitResult, null, statusCriteria);
		}
 
		String sortCriteria=null;
		for(String sort : contract.getSortCriteria().keySet()) {
			sortCriteria = sort + ":" + contract.getSortCriteria().get(sort);
			break;
		}
		CollectionSorter sorter = new CollectionSorter();
		logger.info("searchCriteria="+contract.getSearchCriteria().toString());
		//ObjectDebugUtil.printObjectContent(contract, logger);
		List<ServiceRequest> filterResult = filter.filter(suitResult, contract.getFilterCriteria(), contract.getSearchCriteria());
		List<ServiceRequest> sortResult = sorter.sort(filterResult, sortCriteria);
		int start = contract.getStartRecordNumber();
		int end=0;
		if(contract.getIncrement()== Integer.MAX_VALUE){
			end = sortResult.size();// down load model
			}
		else{
			end = (contract.getIncrement() + start)> sortResult.size()? sortResult.size(): (contract.getIncrement()+start);
		}
		srlr.setServiceRequests(sortResult.subList(start, end));
		srlr.setTotalCount(sortResult.size());
		return srlr;
	}
	
	
	public ServiceRequestListResult retrieveServiceRequestHistoryList(
			ServiceRequestHistoryListContract contract) throws Exception {
		ServiceRequestListResult srlr = new ServiceRequestListResult();
		List<ServiceRequest> serviceRequests = DomainMockDataGenerator.getServiceRequestsList();
		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
		List<ServiceRequest> suitResult = serviceRequests;
		Map filterCriteria = contract.getFilterCriteria();
		List<ServiceRequest> filterResult = filter.filter(suitResult, filterCriteria, contract.getSearchCriteria());
		String sortCriteria=null;
		for(String sort : contract.getSortCriteria().keySet()) {
			sortCriteria = sort + ":" + contract.getSortCriteria().get(sort);
			break;
		}
		CollectionSorter sorter = new CollectionSorter();
		List<ServiceRequest> sortResult = sorter.sort(filterResult, sortCriteria);
		int start = contract.getStartRecordNumber();
		int end = (contract.getIncrement() + start)> suitResult.size()? suitResult.size(): (contract.getIncrement()+start);
		srlr.setServiceRequests(filterResult.subList(start, end));
		srlr.setTotalCount(filterResult.size());
		return srlr;
	}
	public ServiceRequestResult retrieveServiceRequest(ServiceRequestContract contract) throws Exception{
		ServiceRequestResult srlr = new ServiceRequestResult();
		ServiceRequest sr = new ServiceRequest();
		sr = DomainMockDataGenerator.getServiceRequest(Integer.parseInt(contract.getRequestNumber()));
		if (contract.getRequestNumber() != null)
			sr.setServiceRequestNumber(contract.getRequestNumber());
		srlr.setServiceRequest(sr);
		return srlr;
	}	

	public ModifyContactResult modifyContact (ModifyContactContract modifyContactContract)throws Exception{
		//TODO add implementation
		return new ModifyContactResult();
	}
	
	/*this web service features an asynchronize call to the sieble .
	 * the request number will be returned while the sieble is still busy processing the service request  
	 * user can check the result with the service request later on.
	 */
	public CreateServiceRequestResult createServiceRequest (CreateServiceRequestContract contract)
	throws Exception{
		CreateServiceRequestResult result = new CreateServiceRequestResult();
		result.setServiceRequestNumber("1234567216");
		return result;
	}

	public FavoriteResult updateUserFavoriteAddress(FavoriteAddressContract favoriteAddressContract)
	throws Exception{
		/*After all, it's just a mock. everything would just work the same way 
		 * updateUserFavoriteContact does. we won't do the full mock implementation for it
		 */
		FavoriteResult favouriteResult = new FavoriteResult();
		favouriteResult.setResult(true);
		return favouriteResult;
	}
	
    public SiebelAccountListResult retrieveSiebelAccountList(SiebelAccountListContract contract){
    	SiebelAccountListResult result = new SiebelAccountListResult();
    	String mdmAccountMDMId = contract.getMdmId();
    	List<Account> accountList = new ArrayList<Account>();
    	List<String> soldToList = new ArrayList<String>();
    	List<String> paymentTypeList = new ArrayList<String>();
    	paymentTypeList.add("Pay Now");
    	paymentTypeList.add("Pay Later");
    	paymentTypeList.add("UBB");
    	for(int i=0;i<4;i++){
    		String soldTo = "SoldTo"+i;
    		soldToList.add(soldTo);
    	}
    	Account account1 = new Account();
    	account1.setAccountId(mdmAccountMDMId + "-1");
    	account1.setAccountName("Siebel Account1");
    	account1.setCreateServiceRequest("update");
    	account1.setManualMeterRead("update");
    	account1.setUserConsumables("update");
    	account1.setAgreementId("agreement1");
    	account1.setAgreementName("agreement Name 1");
    	account1.setAccountType(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES);
    	account1.setCatalogEntitlementFlag(true);
    	account1.setAssetEntitlementFlag(true);
    	account1.setHardwareRequestFlag("true");
    	account1.setSoldToNumbers(soldToList);
    	account1.setPaymentTypes(paymentTypeList);
		account1.setShowPriceFlag("Delayed Purchase");
    	account1.setCreditNumberFlag(true);    	
    	account1.setPoNumberFlag(true);
		account1.setAccountSplitterFlag(true);
    	account1.setSalesOrgs(Arrays.asList("5050"));
    	
    	accountList.add(account1);
    	Account account2 = new Account();
    	account2.setAccountId(mdmAccountMDMId + "-2");
    	account2.setAccountName("Siebel Account2");
    	account2.setCreateServiceRequest("update");
    	account2.setAccountType(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES);
    	account2.setAgreementId("agreement2");
    	account2.setAgreementName("agreement Name 2");
    	account2.setCatalogEntitlementFlag(true);
    	account2.setAssetEntitlementFlag(true);
    	account2.setHardwareRequestFlag("true");
    	account2.setSoldToNumbers(soldToList);
    	account2.setPaymentTypes(paymentTypeList);
		account2.setShowPriceFlag("Both");
    	account2.setCreditNumberFlag(false);    	
		account2.setAccountSplitterFlag(false);
    	account2.setPoNumberFlag(true);
    	account2.setSalesOrgs(Arrays.asList("5050"));
    	accountList.add(account2);
    	result.setAccountList(accountList);
    	for(String soldTo:account2.getSoldToNumbers()){
    		logger.debug("Account SoldTo "+soldTo);
    	}
    	
    	return result;
    }

    public void updateUserFavoriteContact() throws Exception{
    	//TODO add implementation
    }
	
	public void updateUserFavoriteAddress() throws Exception{
		//TODO add implementation
	}

	public AddressListResult retrieveAddressList(AddressListContract contract)
			throws Exception {
		AddressListResult sar = new AddressListResult();
		List<GenericAddress> genericAddresses = DomainMockDataGenerator.getGenericAddressList();
		List<GenericAddress> genericFavAddresses = new ArrayList<GenericAddress>();
		for(GenericAddress ga:genericAddresses){
			if(ga.getUserFavorite())
				genericFavAddresses.add(ga);
		}
		if(contract.isFavoriteFlag()){			
			sar.setTotalCount(genericFavAddresses.size());
			sar.setAddressList(genericFavAddresses);
		}else{			
			sar.setTotalCount(genericAddresses.size());
			sar.setAddressList(genericAddresses);		
		}
		return  sar;
	}

	public ManualAssetResult validateManualAsset(ManualAssetContract contract)
			throws Exception {
		ManualAssetResult mar = new ManualAssetResult();
		String mn = contract.getModelNumber();	
		if(mn.length()==8){
			mar.setResult(true);
		}else{
			mar.setResult(false);
			return mar;
		}
		String PTLI = contract.getProductTLI();
		if(PTLI != null && !PTLI.equals("") ){
			if(PTLI.length()==7){
				mar.setResult(true);
			}else{
				mar.setResult(false);
				return mar;
			}
		}
		return mar;
	}
	



	public ServiceRequestListResult retrieveAssociatedServiceRequestList(
			ServiceRequestAssociatedListContract contract) throws Exception {
		ServiceRequestListResult srlr = new ServiceRequestListResult();
		List<ServiceRequest> serviceRequests = DomainMockDataGenerator.getServiceRequestsList();
		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
		List<ServiceRequest> suitResult = serviceRequests;
		Map filterCriteria = contract.getFilterCriteria();
		filterCriteria.put("serviceRequestNumber", contract.getServiceRequestNumber());
		List<ServiceRequest> filterResult = filter.filter(suitResult, filterCriteria, contract.getSearchCriteria());
		srlr.setServiceRequests(filterResult);
		return srlr;
	}


	@Override
	public AddressListResult retrieveLBSAddressList(AddressListContract contract)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
