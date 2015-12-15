package com.lexmark.service.api;

import com.googlecode.ehcache.annotations.Cacheable;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.GlobalAssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.FavoriteResult;

/**
 * Interface to search asset, or retrieve asset.
 * @author Roger.Lin
 *
 */
public interface DeviceService {

	public FavoriteResult updateUserFavoriteAsset(UserFavoriteAssetContract contract) throws Exception;
//	@Cacheable(cacheName="assetReportingHierarchyCache", keyGeneratorName="myKeyGenerator")
	public AssetReportingHierarchyResult retrieveAssetReportingHierarchy(LocationReportingHierarchyContract contract) throws Exception;
	public AssetReportingHierarchyResult retrieveFullAssetReportingHierarchy(LocationReportingHierarchyContract contract) throws Exception;
//	@Cacheable(cacheName="assetLocationsCache", keyGeneratorName="myKeyGenerator")
	public AssetLocationsResult retrieveAssetLocations(LocationReportingHierarchyContract contract) throws Exception;
	public AssetListResult retrieveGlobalAssetList(GlobalAssetListContract contract);
}
