package com.lexmark.service.impl.real.deviceService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.result.FavoriteResult;
import com.lexmark.service.impl.real.domain.UserFavoriteAssetDo;
import org.apache.logging.log4j.Logger;


/**
 * @author vpetruchok
 * @version 1.0, 2012-05-21
 */
public class UpdateUserFavoriteAssetService {
    
   // private static Log logger = LogFactory.getLog(UpdateUserFavoriteAssetService.class);
	 private static Logger logger = LogManager.getLogger(UpdateUserFavoriteAssetService.class);
    private Session session; 
    
    public UpdateUserFavoriteAssetService(Session session) {
        super();
        this.session = session;
    }

    public FavoriteResult updateUserFavoriteAsset(UserFavoriteAssetContract contract) {
        logger.debug("[IN] updateUserFavoriteAsset");
        FavoriteResult result = new FavoriteResult();
        try
        {
            IDataManager dataManager = session.getDataManager();

            logger.debug("Favorite flag set to " + contract.isFavoriteFlag());
            UserFavoriteAssetDo assetDo = new UserFavoriteAssetDo();
            assetDo.setContactId(contract.getContactId());
            assetDo.setFavoriteAssetId(contract.getFavoriteAssetId());
            if (contract.isFavoriteFlag())
            {
                assetDo.setAssetFavFlag(true);
            }
            else
            {
                assetDo.setAssetFavFlag(false);
            }
            dataManager.update(assetDo);
            result.setResult(true);
        }
        finally
        {
            logger.debug("[OUT] updateUserFavoriteAsset");
        }
        return result;
    }


}
