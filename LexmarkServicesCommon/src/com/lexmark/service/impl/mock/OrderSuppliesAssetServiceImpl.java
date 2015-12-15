package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.PageCountsContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.PageCountsResult;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.CollectionSorter;

public class OrderSuppliesAssetServiceImpl implements OrderSuppliesAssetService {
	private static final Logger LOGGER = LogManager.getLogger(OrderSuppliesAssetServiceImpl.class);
	private static List<Asset> devices  = DomainMockDataGenerator.getDeviceList();
	private static List<Part> assetPartList =  new ArrayList<Part>();
	static{
		assetPartList =  DomainMockDataGenerator.getAssetOrderPartsList();
	}
	@Override
	public AssetListResult retrieveDeviceList(AssetListContract contract) {
		LOGGER.debug("----------- Entry to retrieveDeviceList ------------------");
		AssetListResult deviceListResult = new AssetListResult();

		String sortCriteria=null;
		for(String sort : contract.getSortCriteria().keySet()) {
			sortCriteria = sort + ":" + contract.getSortCriteria().get(sort);
			break;
		}

		CollectionFilter filter = new CollectionFilter(Locale.getDefault());
		CollectionSorter sorter = new CollectionSorter();
		List<Asset> filterResult = filter.filter(devices, contract.getFilterCriteria(), contract.getSearchCriteria());
		List<Asset> sortResult = sorter.sort(filterResult, sortCriteria);
		
		if(contract.isFavoriteFlag()){
			List<Asset> tempResult= new ArrayList<Asset>();
			for(Asset asset:sortResult){
				if(asset.getUserFavoriteFlag())
					tempResult.add(asset);
			}
			sortResult =tempResult;
		} else if(contract.getAssetType()!= null){
			List<Asset> tempResult= new ArrayList<Asset>();
			for(Asset asset:sortResult){
				if(contract.getAssetType().equalsIgnoreCase(asset.getAssetType()))
					tempResult.add(asset);
			}
			sortResult =tempResult;
		} 
		LOGGER.debug("++++++++++++++++++++++++++++++++size:+++++++++++++++"+sortResult.size());
		if (contract.getLoadAllFlag()) {
			deviceListResult.setAccountAssets(sortResult);
		}else if(contract.getIncrement() == -1){
			int start = 0;
			int end = sortResult.size();
			deviceListResult.setAccountAssets(sortResult.subList(start, end));
		} else {
			int start = contract.getStartRecordNumber();
			int end = (contract.getIncrement()+ start)> sortResult.size()? sortResult.size(): (contract.getIncrement()+start);

			deviceListResult.setAccountAssets(sortResult.subList(start, end));
		}
		deviceListResult.setTotalCount(sortResult.size());
		LOGGER.debug("---------------------- Exit from retrieveDeviceList ---------------------------");
		return deviceListResult;
	}
		
	@Override
	public FavoriteResult updateUserFavoriteAsset(
			UserFavoriteAssetContract contract) throws Exception {
		FavoriteResult favouriteResult = new FavoriteResult();
		//the result only indicates if the webservice call is successful or not
		favouriteResult.setResult(true);
		return favouriteResult;
	}
	@Override
	public AssetResult retrieveDeviceDetail(AssetContract contract) {
		AccountContact primaryContact = new AccountContact();
		primaryContact.setFirstName("Chick");
		primaryContact.setLastName("Dpian");
		primaryContact.setWorkPhone("1234567890");
		primaryContact.setEmailAddress("abc@mymail.com");
		//This portion is for asset information
		Asset asset = new Asset();
		asset.setSerialNumber("1-2345678");
		asset.setProductTLI("Lexmark Product");
		asset.setModelNumber("Lexmark P 12");
		asset.setInstallDate(new Date("12/12/12"));
		asset.setIpAddress("10.141.27.100");
		asset.setHostName("HO304L30");
		asset.setAssetTag("0230485");
		asset.setAddressFlag(true);
		//This portion is for PRimary contact
		
		Account account = new Account();
        asset.setAccount(account);
        account.setAccountId("");
        account.setAccountName("");
        account.setAssetExpediteFlag(true);
        
        account.setShowPriceFlag("Ship and Bill");
        account.setPoNumberFlag(true);
        account.setCreditNumberFlag(true);
		
		//This portion is for service address
		GenericAddress serviceAddress = new GenericAddress();
		serviceAddress.setStoreFrontName("ABC agency");
		serviceAddress.setAddressLine1("123 lake road");
		serviceAddress.setAddressLine2("Apt 10");
		serviceAddress.setAddressLine3("Opp to Walmart");
		serviceAddress.setCity("Lexington");
		serviceAddress.setState("Kentucky");
		serviceAddress.setProvince("MN");
		serviceAddress.setPostalCode("40111");
		serviceAddress.setCountry("USA");
		serviceAddress.setPhysicalLocation1("Lex-082");
		serviceAddress.setPhysicalLocation2("2");
		serviceAddress.setPhysicalLocation3("Lexmark");
		//This portion is for asset
		AssetResult result = new AssetResult();
		result.setAsset(asset);
		//THis portion is for Shipto address
		GenericAddress shipToAddress = new GenericAddress();
		shipToAddress.setStoreFrontName("storeFrontName");
		shipToAddress.setAddressLine1("addressLine1");
		shipToAddress.setAddressLine2("addressLine2");
		shipToAddress.setAddressLine3("addressLine3");
		shipToAddress.setCity("city");
		//shipToAddress.setState("null");
		shipToAddress.setProvince("province");
		shipToAddress.setPostalCode("postalCode");
		shipToAddress.setCountry("country");
		//Sending two values of pagecount
		List<PageCounts> pageCountList = new ArrayList<PageCounts>(); 
		PageCounts pageCounts1 = new PageCounts();
		pageCounts1.setName("color");
		pageCounts1.setCount("color 1000");
		pageCounts1.setDate("color date");
		pageCountList.add(pageCounts1);
		PageCounts pageCounts2 = new PageCounts();
		pageCounts2.setName("mono");
		pageCounts2.setCount("mono 1000");
		pageCounts2.setDate("mono date");
		pageCountList.add(pageCounts2);
		result.getAsset().setPageCounts(pageCountList);
		//End pagecount
		result.getAsset().setInstallAddress(shipToAddress);
		result.getAsset().setPartList(assetPartList);
		result.getAsset().setAssetContact(primaryContact);
		result.getAsset().setConsumableAddress(serviceAddress);
		result.getAsset().setDefaultSpecialInstruction("Lexmark is going to provide detail instruction with the printer");
		return result;
	}

	@Override
	public AssetLocationsResult retrieveAssetLocations(
			LocationReportingHierarchyContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssetListResult retrieveAllDeviceList(AssetListContract contract) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AssetResult retrieveProducts(AssetContract contract) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageCountsResult retrievePageCounts(PageCountsContract contract) {
		// TODO Auto-generated method stub
		return null;
	}
}
