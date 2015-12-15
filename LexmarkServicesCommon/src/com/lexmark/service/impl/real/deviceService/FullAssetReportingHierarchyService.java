package com.lexmark.service.impl.real.deviceService;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtil.convertCHLDoToCHLNode;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.service.impl.real.domain.CHLAccountDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import org.apache.logging.log4j.Logger;

/**
 * @author vpetruchok
 * @version 1.0, 2012-05-21
 */
public class FullAssetReportingHierarchyService {
    
    //private static Log logger = LogFactory.getLog(FullAssetReportingHierarchyService.class);
	private static Logger logger = LogManager.getLogger(FullAssetReportingHierarchyService.class);
    private Session session;
    
    public FullAssetReportingHierarchyService(Session session) {
        super();
        this.session = session;
    }  
    
    @SuppressWarnings("unchecked")
    public AssetReportingHierarchyResult retrieveFullAssetReportingHierarchy(LocationReportingHierarchyContract contract) {
        logger.debug("[IN] retrieveFullAssetReportingHierarchy");
        AssetReportingHierarchyResult result = new AssetReportingHierarchyResult();
        try {
            IDataManager dataManager = session.getDataManager();
            QueryObject criteria = new QueryObject(CHLAccountDo.class, ActionEvent.QUERY);
            String expr = AmindServiceUtil.buildSimpleMdmSearchExpression(contract.getMdmId(), contract.getMdmLevel());
            expr = expr + " AND [location] ~LIKE 'MPS*'";
            criteria.addComponentSearchSpec(CHLAccountDo.class, expr);
            List<CHLAccountDo> chlList = dataManager.queryBySearchExpression(CHLAccountDo.class, expr);
            result.setChlNodeList(convertCHLDoToCHLNode(chlList,null));
        } finally {
            logger.debug("[OUT] retrieveFullAssetReportingHierarchy");
        }
        return result;
    }    
    
    
    

}
