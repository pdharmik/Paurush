package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.service.impl.real.util.AssetConversionUtil.populateAssetContact;
import static com.lexmark.service.impl.real.util.AssetConversionUtil.populateContractNumber;
import static com.lexmark.service.impl.real.util.AssetConversionUtil.populateFavoriteFlag;
import static com.lexmark.service.impl.real.util.AssetConversionUtil.populateInstallAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.lexmark.domain.Account;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;
import com.lexmark.service.impl.real.domain.AssetBase;
import com.lexmark.service.impl.real.domain.AssetLocation;
import com.lexmark.service.impl.real.domain.ContractedAndEntitledAssetDo;
import com.lexmark.service.impl.real.domain.ContractedAndEntitledDeviceDo;

import com.lexmark.util.LangUtil;

/**
 * 
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetService
 * 
 * @author vpetruchok
 * @version 1.0, 2012-04-25
 */
public class AmindOrderSuppliesAssetConversionUtil {

    public static List<GenericAddress> toGenericAddress(List<AssetLocation> locationList) {
        List<GenericAddress> result = new ArrayList<GenericAddress>();
        for (AssetLocation a : LangUtil.notNull(locationList)) {
            GenericAddress genericAddress = toGenericAddress(a);
            result.add(genericAddress);
        }
        return result;
    }

    public static GenericAddress toGenericAddress(AssetLocation assetLocation) {
        GenericAddress genericAddress = new GenericAddress();
        genericAddress.setAddressName(assetLocation.getAddressName());
        genericAddress.setCity(assetLocation.getCity());
        genericAddress.setState(assetLocation.getState());
        genericAddress.setPostalCode(assetLocation.getPostalCode());
        genericAddress.setProvince(assetLocation.getProvince());
        genericAddress.setCountry(assetLocation.getCountry());
        genericAddress.setCounty(assetLocation.getCounty());
        genericAddress.setDistrict(assetLocation.getDistrict());
        genericAddress.setOfficeNumber(assetLocation.getOfficeNumber());
        return genericAddress;
    }
    
    public static List<Asset> toAssetList(List<? extends AssetBase> assetBaseList, Set<String> favoriteSet) {
        List<Asset> portalAssetList = new ArrayList<Asset>();
        for (AssetBase assetBase : LangUtil.notNull(assetBaseList)) {
            Asset asset = toAsset(assetBase, favoriteSet);
            portalAssetList.add(asset);
        }
        return portalAssetList;
    }

    private static Asset toAsset(AssetBase assetBase, Set<String> favoriteSet) {
        Asset asset = new Asset();
        asset.setAssetId(assetBase.getAssetId());
        asset.setAssetTag(assetBase.getAssetTag());
        asset.setDeviceTag(assetBase.getDeviceTagCustomer());
        asset.setHostName(assetBase.getHostName());
        asset.setIpAddress(assetBase.getIpAddress());
        asset.setProductLine(assetBase.getProductLine());
        asset.setProductTLI(assetBase.getProductTLI());
        asset.setSerialNumber(assetBase.getSerialNumber());
        asset.setModelNumber(assetBase.getMachineTypeModel());
        asset.setDevicePhase(assetBase.getDevicePhase());
        asset.setMacAddress(assetBase.getMacAddress());
		if (LangUtil.isBlank(assetBase.getDescriptionLocalLang()) // changes here for 13403
				&& LangUtil.isBlank(assetBase.getCustomerReportingName())) {
			asset.setDescriptionLocalLang(assetBase.getMdmModel());
		} else {
			asset.setDescriptionLocalLang(assetBase.getCustomerReportingName());
			if (LangUtil.isNotBlank(assetBase.getDescriptionLocalLang())) {
				asset.setDescriptionLocalLang(assetBase.getDescriptionLocalLang());
			}
		}
		populateInstallAddress(asset, assetBase);
        populateFavoriteFlag(asset,  assetBase, AccountAssetFavorites.class, favoriteSet); 
        populateAssetContact(asset,  assetBase);
        populateConsumableAssetFlag(asset, assetBase);
        populateContractNumber(asset, assetBase);
        populateAccount(asset, assetBase);
        return asset;
    }

    private static void populateAccount(Asset asset, AssetBase assetBase) {
    	Account account = new Account();
    	
    	if (assetBase instanceof ContractedAndEntitledDeviceDo) {
    		ContractedAndEntitledDeviceDo assetDo = (ContractedAndEntitledDeviceDo) assetBase;
    		account.setAccountId(assetDo.getOwnerAccountId());
    		account.setAccountName(assetDo.getAccountName());
    	} else if (assetBase instanceof AccountAssetFavorites) {
            AccountAssetFavorites assetDo = (AccountAssetFavorites) assetBase;
            account.setAccountId(assetDo.getOwnerAccountId());
         	account.setAccountName(assetDo.getAccountName());
        }
    	
    	asset.setAccount(account);
	}

	private static void populateConsumableAssetFlag(Asset asset, AssetBase assetBase) {
        if (assetBase instanceof ContractedAndEntitledDeviceDo) { // DO_CLASS_STANDARD 
        	ContractedAndEntitledDeviceDo assetDo = (ContractedAndEntitledDeviceDo) assetBase;
            asset.setConsumableAssetFlag(assetDo.isConsumableAssetFlag());
        } else if (assetBase instanceof AccountAssetFavorites) { // DO_CLASS_FAVORITE
            AccountAssetFavorites assetDo = (AccountAssetFavorites) assetBase;
            asset.setConsumableAssetFlag(assetDo.isConsumableAssetFlag());
        }
    }
}
