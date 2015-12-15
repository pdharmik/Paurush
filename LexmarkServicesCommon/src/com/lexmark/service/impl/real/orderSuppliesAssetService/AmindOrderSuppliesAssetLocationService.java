package com.lexmark.service.impl.real.orderSuppliesAssetService;



import static com.lexmark.service.impl.real.orderSuppliesAssetService.AmindOrderSuppliesAssetConversionUtil.toGenericAddress;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.domain.GenericAddress;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.AssetLocation;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;
import org.apache.logging.log4j.Logger;


/**
 * @author vpetruchok
 * @version 1.0, 2012-05-21
 */
public class AmindOrderSuppliesAssetLocationService {
    
    //private static Log logger = LogFactory.getLog(AmindOrderSuppliesAssetLocationService.class);
	private static Logger logger = LogManager.getLogger(AmindOrderSuppliesAssetLocationService.class);
    private final Session session;
    private final Session chldSession; 
    
    
    private static final Map<String, String> ASSET_LOCATION_FIELD_MAP = assetLocationFieldMap();
    
    private static Map<String, String> assetLocationFieldMap() {
        Map<String, String> m = new HashMap<String, String>();
        m.put("mdmLevel1AccountId", "LXK MPS Ent Global DUNS"); 
        m.put("mdmLevel2AccountId", "LXK MPS Domestic DUNS"); 
        m.put("mdmLevel3AccountId", "LXK SW Agreement Account LEGAL MDM ID"); 
        m.put("mdmLevel4AccountId", "LXK SW Agreement Account MDM Account"); 
        m.put("mdmLevel5AccountId", "LXK MPS Account Id"); 
        return Collections.unmodifiableMap(m);
    }
    
    public AmindOrderSuppliesAssetLocationService(Session session, Session chldSession) {
        this.session = session;
        this.chldSession = chldSession;
    }

    @SuppressWarnings("unchecked")
    public AssetLocationsResult retrieveAssetLocations(LocationReportingHierarchyContract contract) {
        logger.debug("[IN] retrieveAssetLocations");
        checkRequiredFields(contract);
        AssetLocationsResult result = new AssetLocationsResult();
        Class<AssetLocation> doClass = AssetLocation.class;
        try {
            IDataManager dataManager = session.getDataManager();
            CHLDo chl = new CHLDo();
            if(LangUtil.isNotEmpty(contract.getChlNodeId())){
            chl = AmindServiceUtil.getParentChainFromCHLNodeId(contract.getChlNodeId(), chldSession.getDataManager());
            }
            QueryObject criteria = buildConsumableAssetLocatinCriteria(contract, doClass, chl);

            List<?> locationList = dataManager.query(criteria); 

            List<GenericAddress> addressList = toGenericAddress((List<AssetLocation>) locationList);
            result.setAddressList(addressList);
         //   ServiceUtil.getSiebelLogNumber(session);
        } catch (Exception e) {
            throw new SiebelCrmServiceException("retrieveAssetLocations failed", e);
        } finally {
            logger.debug("[OUT] retrieveAssetLocations");
        }
        return result;
    }    
    
    private static void checkRequiredFields(LocationReportingHierarchyContract contract) {
        if (isNotEmpty(contract.getChlNodeId())
             && isNotEmpty(contract.getEntitlementEndDate())) {
            return;  
        }
        
        if (isNotEmpty(contract.getMdmId())
              && isNotEmpty(contract.getMdmLevel())
              && isNotEmpty(contract.getEntitlementEndDate())) {
               return; 
        }
        
        
        throw new SiebelCrmServiceException("No (chlNodeId, entitlementEndDate) or ( mdmId, mdmLevel, entitlementEndDate) specified");
    }

    private static QueryObject buildConsumableAssetLocatinCriteria(LocationReportingHierarchyContract contract, Class<?> doClass,  CHLDo chl)  {
        QueryObject criteria = new QueryObject(doClass, ActionEvent.QUERY);
        StringBuilder expr = new StringBuilder();
        if (isNotEmpty(contract.getChlNodeId())) {
            expr.append("EXISTS(");
            expr.append(" [chlNodeId] LIKE '");
            expr.append(chl.getParentChain());
            expr.append("*'");
            expr.append(")");
            expr.append(" AND EXISTS (");
            expr.append(" [LXK SW Entitlement Type] <> 'Warranty'");
        	expr.append(String.format(" AND [LXK MPS Entitlement End Date] >= '%s'", contract.getEntitlementEndDate()));
        	expr.append(")");
        } else {
        	expr.append("EXISTS (");
            expr.append(AmindServiceUtil.buildmdmSearchExpression(contract.getMdmId(),
                    contract.getMdmLevel(), ASSET_LOCATION_FIELD_MAP, false, false));
            expr.append(" ) AND EXISTS ( [LXK SW Entitlement Type] <> 'Warranty'");
        	expr.append(String.format(" AND [LXK MPS Entitlement End Date] >= '%s'", contract.getEntitlementEndDate()));
        	expr.append(")");
        }
        criteria.addComponentSearchSpec(doClass, expr.toString());
        return criteria;
    } 

}
