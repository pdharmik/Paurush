package com.lexmark.service.impl.real.deviceService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.result.AssetReportingHierarchyResult;
import org.apache.logging.log4j.Logger;


/**
 * @author vpetruchok
 * @version 1.0, 2012-05-21
 */
public class AssetReportingHierarchyService {
    
    //private static Log logger = LogFactory.getLog(AssetLocationService.class);
	private static Logger logger = LogManager.getLogger(AssetLocationService.class);
    private Session session;
    
    public AssetReportingHierarchyService(Session session) {
        super();
        this.session = session;
    }

    public AssetReportingHierarchyResult retrieveAssetReportingHierarchy(LocationReportingHierarchyContract contract) {
        logger.debug("[IN] retrieveAssetReportingHierarchy");
        try {
            IDataManager dataManager = session.getDataManager();
            return AmindContractedDeviceServiceUtil.retrieveAssetReportingHierarchy(dataManager, contract);
        } finally {
            logger.debug("[OUT] retrieveAssetReportingHierarchy");
        }
    }

}
