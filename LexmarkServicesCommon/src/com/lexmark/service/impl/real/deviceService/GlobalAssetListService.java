package com.lexmark.service.impl.real.deviceService;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtil.convertAssetDoToAssetList;
import static com.lexmark.util.LangUtil.isBlank;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.GlobalAssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.impl.real.domain.AssetBase;
import org.apache.logging.log4j.Logger;


/**
 * @author vpetruchok
 * @version 1.0, 2012-05-21
 */
public class GlobalAssetListService {
    
    //private static Log logger = LogFactory.getLog(GlobalAssetListService.class);
	private static Logger logger = LogManager.getLogger(GlobalAssetListService.class);
    private final Session session; 
    
    
    public GlobalAssetListService(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    public AssetListResult retrieveGlobalAssetList(GlobalAssetListContract contract) {
        logger.debug("[IN] retrieveGlobalAssetList");
        checkRequiredFields(contract);
        AssetListResult assetResult = new AssetListResult();
        try {
            IDataManager dataManager = session.getDataManager();

            StringBuilder expr = new StringBuilder();
            expr.append("[serialNumber]='");
            expr.append(contract.getSerialNumber());
            expr.append("'");

            List<AssetBase> list = dataManager.queryBySearchExpression(
                    AssetBase.class, expr.toString());
            assetResult.setAccountAssets(convertAssetDoToAssetList(list,null));
            assetResult.setTotalCount(assetResult.getAssets().size());
            return assetResult;
        } finally {
            logger.debug("[OUT] retrieveGlobalAssetList");
        }
    }
    
    private static void checkRequiredFields(GlobalAssetListContract contract) {
        if (isBlank(contract.getSerialNumber())) {
            throw new IllegalArgumentException("`serialNumber' is null or blank");
        }
    }


}
