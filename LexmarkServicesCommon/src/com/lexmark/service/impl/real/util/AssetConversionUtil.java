package com.lexmark.service.impl.real.util;

import java.util.Collection;

import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;
import com.lexmark.service.impl.real.domain.AssetBase;
import com.lexmark.service.impl.real.domain.ContractedAndEntitledAssetDo;
import com.lexmark.service.impl.real.domain.ContractedAndEntitledDeviceDo;
import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, 2012-05-10
 */
public class AssetConversionUtil {
    
    private AssetConversionUtil() {
    }
     
    public static <T extends AssetBase> void  populateFavoriteFlag(Asset asset,  AssetBase assetBase,   Class<T> favClass, Collection<String> favoriteSet) {
        if (favClass.isInstance(assetBase)
              || (LangUtil.isNotEmpty(favoriteSet) && favoriteSet.contains(assetBase.getAssetId())))  {
             asset.setUserFavoriteFlag(true);
        } else {
             asset.setUserFavoriteFlag(false);
        }
    }
    
    public static void populateAssetContact(Asset asset, AssetBase assetBase) {
      if (assetBase instanceof ContractedAndEntitledDeviceDo) {
    		ContractedAndEntitledDeviceDo assetDo = (ContractedAndEntitledDeviceDo) assetBase;
            AccountContact ac = new AccountContact();
            ac.setFirstName(assetDo.getContactFirstName());
            ac.setLastName(assetDo.getContactLastName());
            ac.setEmailAddress(assetDo.getContactEmailAddress());
            ac.setWorkPhone(assetDo.getContactWorkPhone());
            ac.setContactId(assetDo.getContactId());
            asset.setAssetContact(ac);
        } else if (assetBase instanceof AccountAssetFavorites) {
        	AccountAssetFavorites assetDo = (AccountAssetFavorites) assetBase;
            AccountContact ac = new AccountContact();
            ac.setFirstName(assetDo.getFirstName());
            ac.setLastName(assetDo.getLastName());
            ac.setEmailAddress(assetDo.getEmailAddress());
            ac.setWorkPhone(assetDo.getWorkPhone());
            ac.setContactId(assetDo.getContactId());
            asset.setAssetContact(ac);
        }
    }
    
    public static void populateContractNumber(Asset asset, AssetBase assetBase) {
    	if(assetBase instanceof ContractedAndEntitledDeviceDo) {
    		ContractedAndEntitledDeviceDo deviceDo = (ContractedAndEntitledDeviceDo) assetBase;
    		asset.setContractNumber(deviceDo.getContractNumber());
    	}
    }

    public static void populateInstallAddress(Asset asset, AssetBase assetBase) {
        GenericAddress installAddress = new GenericAddress();
        installAddress.setAddressId(assetBase.getAddressId());
        installAddress.setAddressName(assetBase.getAddressName());
        installAddress.setAddressLine1(assetBase.getAddress1());
        installAddress.setAddressLine2(assetBase.getAddress2());
        installAddress.setAddressLine3(assetBase.getAddress3());
        installAddress.setCity(assetBase.getInstallCity());
        installAddress.setState(assetBase.getInstallState());
        installAddress.setCountry(assetBase.getInstallCountry());
        installAddress.setPostalCode(assetBase.getInstallPostalCode());
        installAddress.setProvince(assetBase.getInstallProvince());
        
        installAddress.setCounty(assetBase.getCounty());
        installAddress.setDistrict(assetBase.getDistrict());
        installAddress.setOfficeNumber(assetBase.getOfficeNumber());
        
        installAddress.setLevelOfDetails(assetBase.getLevelOfDetails());
        
//        installAddress.setStoreFrontName(assetBase.getStoreFrontName());
        asset.setInstallAddress(installAddress);
    }
}
