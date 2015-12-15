package com.lexmark.service.impl.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.GlobalAssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.domain.Asset;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.service.api.DeviceService;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.CollectionSorter;

/**
 * Implementation class of DeviceService.
 * @author Roger.Lin
 *
 */
public class DeviceServiceImpl implements DeviceService {
	private static Logger LOGGER = LogManager.getLogger(DeviceServiceImpl.class);
	private static List<Asset> devices  = DomainMockDataGenerator.getDeviceList();
	public AssetListResult retrieveDeviceList(AssetListContract contract)
			throws Exception {
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
		LOGGER.info("++++++++++++++++++++++++++++++++size:+++++++++++++++"+sortResult.size());
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
		return deviceListResult;
	}

	public AssetResult retrieveDeviceDetail(AssetContract contract) throws Exception {
		if(contract.getAssetId().equals("-1")){
			return null;
		}
		Asset matchedAsset = null;
		String assetId = contract.getAssetId();
		for (Asset asset : DomainMockDataGenerator.getDeviceList()) {
			if (assetId.equals(asset.getAssetId())) {
				matchedAsset = asset;
				break;
			}
		}
		AssetResult assetResult = new AssetResult();
		assetResult.setAsset(matchedAsset);
		return assetResult;
	}

	public FavoriteResult updateUserFavoriteAsset(
			UserFavoriteAssetContract contract) throws Exception {
		FavoriteResult favouriteResult = new FavoriteResult();
		//the result only indicates if the webservice call is successful or not
		favouriteResult.setResult(true);
		return favouriteResult;
	}

	public AssetReportingHierarchyResult retrieveAssetReportingHierarchy(LocationReportingHierarchyContract contract) throws Exception {
		AssetReportingHierarchyResult result = new AssetReportingHierarchyResult();
		
		if (contract != null) {
			if (contract.getChlNodeId() != null) {
				result.setChlNodeList(DomainMockDataGenerator.getCHLNodeListByNodeId(contract.getChlNodeId()));	
			} else {
				result.setChlNodeList(DomainMockDataGenerator.getCHLNodeListByNodeId(contract.getMdmId()));
			}
		}
		return result;
	}

	public AssetLocationsResult retrieveAssetLocations(
			LocationReportingHierarchyContract contract) throws Exception {
		AssetLocationsResult result = new AssetLocationsResult();
		result.setAddressList(DomainMockDataGenerator.getGenericAddressList());
		return result;
	}

	//warning , you get the same thing every time you shoot a search. MOCK ONLY!!! 
	public AssetListResult retrieveGlobalAssetList(
			GlobalAssetListContract contract) {		
		AssetListResult alr = new AssetListResult();
		List<Asset> resultList = new ArrayList<Asset>();
		Asset matchedAsset = null;
		String assetId = "10216";
		for (Asset asset : DomainMockDataGenerator.getDeviceList()) {
			if (assetId.equals(asset.getAssetId())) {
				matchedAsset = asset;
				break;
			}
		}		
		resultList.add(matchedAsset);
		alr.setAccountAssets(resultList);
		alr.setTotalCount(1);
		return alr;
	}

	public AssetReportingHierarchyResult retrieveFullAssetReportingHierarchy(
			LocationReportingHierarchyContract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
