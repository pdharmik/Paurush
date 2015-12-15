package com.lexmark.service.api;

import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.PageCountsContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.PageCountsResult;

/**
 * @author vpetruchok
 * @version 1.0, 2012-03-15
 */
public interface OrderSuppliesAssetService {
	
	AssetListResult retrieveDeviceList(AssetListContract contract);
	
	AssetResult retrieveDeviceDetail(AssetContract contract);
	
	AssetLocationsResult retrieveAssetLocations(LocationReportingHierarchyContract contract);
	
	FavoriteResult updateUserFavoriteAsset(UserFavoriteAssetContract contract) throws Exception;
	
	AssetListResult retrieveAllDeviceList(AssetListContract contract);
	
	AssetResult retrieveProducts(AssetContract contract);

	PageCountsResult retrievePageCounts(PageCountsContract contract);
}
