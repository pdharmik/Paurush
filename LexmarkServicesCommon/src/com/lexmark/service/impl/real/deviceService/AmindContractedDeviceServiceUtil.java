package com.lexmark.service.impl.real.deviceService;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtil.convertCHLIOtoCHLNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.IDataManager;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AssetListContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.AssetBase;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.CHLIODo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

/**
 * @author vpetruchok
 * @version 1.0, Mar 6, 2012
 */
public class AmindContractedDeviceServiceUtil {

    private AmindContractedDeviceServiceUtil() {
    }
    
	public static void checkRequiredFields(AssetListContract contract) {
		if (contract == null) {
			throw new IllegalArgumentException("Contract is null");
		}

		String contactId = contract.getContactId();
		if (contactId == null) {
			throw new IllegalArgumentException("Contact Id is null");
		} else if (contactId.isEmpty()) {
			throw new IllegalArgumentException("Contact Id is Empty");
		}

		if (!contract.isFavoriteFlag()) {
			String mdmId = contract.getMdmId();
			if (mdmId == null) {
				throw new IllegalArgumentException("MDM Id is null");
			} else if (mdmId.isEmpty()) {
				throw new IllegalArgumentException("MDM Id is empty");
			}
			String mdmLevel = contract.getMdmLevel();
			if (mdmLevel == null) {
				throw new IllegalArgumentException("MDM Level is null");
			} else if (mdmLevel.isEmpty()) {
				throw new IllegalArgumentException("MDM Level is empty");
			}
		}
	}

    @SuppressWarnings("unchecked")
    public static AssetReportingHierarchyResult retrieveAssetReportingHierarchy(IDataManager dataManager , LocationReportingHierarchyContract contract) {
        AssetReportingHierarchyResult result = new AssetReportingHierarchyResult();
        List<CHLDo> chlList = new ArrayList<CHLDo>();
        QueryObject query = new QueryObject(CHLDo.class, ActionEvent.QUERY);
        if (!StringUtils.isEmpty(contract.getChlNodeId()))
        {

           query.setQueryString("[parentAccountId] = '" + contract.getChlNodeId()+"' AND [activeFlag] = 'Y'");
           chlList = dataManager.query(query);
         }
         else
         {
              QueryObject criteria = new QueryObject(CHLIODo.class, ActionEvent.QUERY);
              String expr = AmindServiceUtil.buildSimpleMdmSearchExpression(contract.getMdmId(), contract.getMdmLevel());
              expr = expr + " AND [location] ~ LIKE 'MPS*'";
              expr = expr + " AND [activeFlag] = 'Y'";//Added by sankha for LEX:AIR00074246
              criteria.addComponentSearchSpec(CHLIODo.class, expr);
               List<CHLIODo> chlLIOist = dataManager.queryBySearchExpression(CHLIODo.class, expr);
               StringBuilder searchExp = new StringBuilder();
               if(chlLIOist != null && !chlLIOist.isEmpty())
               {
                    boolean firstRecord = false;
                    for(CHLIODo chlIO: chlLIOist)
                    {
                        if(firstRecord)
                        {
                             searchExp.append(" OR ");
                        }

                         searchExp.append("[id] = '" +
                                        chlIO.getMdmLevel5AccountId() + "'");

                         firstRecord = true;
                    }
                    query.setQueryString(searchExp.toString());
                    chlList = dataManager.query(query);
                  }
            }

            if(chlList != null && !chlList.isEmpty())
            {
                result.setChlNodeList(convertCHLIOtoCHLNode(chlList));
            }
        return result;
    }


   public static int countRecords(Session session, String searchExpression, boolean favoriteFlag) {
        String businessObject    =  "LXK SW Contracted Asset - Service Web" ;
        String businessComponent =  "LXK SW Contracted Asset - Service Web";
        if (favoriteFlag) {
            businessObject    = "LXK SW Contact Favorite Assets";
            businessComponent = "LXK SW Contact Favorite Assets";
        }
        SiebelBusinessServiceProxy businessServiceProxy = session.getSiebelBusServiceProxy();
        return AmindServiceUtil.getTotalCount(businessObject, businessComponent, searchExpression, businessServiceProxy);
    }
    
    public static <T extends AssetBase> Set<String> processFavoriteSet(AssetListContract contract,
                                                                       AmindCrmSessionHandle crmSessionHandle,
                                                                       IDataManager dataManager,
                                                                       Class<T> doFavoriteClass,
                                                                      Log logger) {
        if (contract.isFavoriteFlag()) {
            return Collections.<String> emptySet();
        }

        if (canReuseSessionFavoriteSet(contract, crmSessionHandle)) {
            return crmSessionHandle.getAssetFavoriteSet();
        }

        List<T> favoriteList = queryFavoriteList(contract, dataManager, doFavoriteClass, logger);
        crmSessionHandle.setAssetFavoriteSet(uniqueAssetIds(favoriteList));
        return crmSessionHandle.getAssetFavoriteSet();
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends AssetBase> List<T> queryFavoriteList(AssetListContract contract,
                                                                  IDataManager dataManager,
                                                                  Class<T> doFavoriteClass,
                                                                  Log logger) {
        logger.debug("[IN] Device List for bookmarked ");
        String searchSpec = AmindContractedDeviceSearchExpressionUtil.favoriteListSearchExpression(contract); 
        
        QueryObject queryReq = new QueryObject(doFavoriteClass, ActionEvent.QUERY);
        queryReq.setQueryString(searchSpec);
        queryReq.addComponentSearchSpec(doFavoriteClass, searchSpec);

        List<T> favoriteList = dataManager.query(queryReq); // query
        logger.debug("[OUT] Device List for bookmarked ");
        return LangUtil.notNull(favoriteList);
    }
    
    public static Set<String> uniqueAssetIds(List<? extends AssetBase> favoriteList) {
        Set<String> favoriteSet = new HashSet<String>();
        for (AssetBase assetBase : favoriteList) { favoriteSet.add(assetBase.getAssetId()); }
        return favoriteSet;
    }
    
    private static boolean canReuseSessionFavoriteSet(AssetListContract contract, AmindCrmSessionHandle crmSessionHandle) {
        Set<String> sessionFavoriteSet = crmSessionHandle.getAssetFavoriteSet();
        boolean favoriteFlag  = contract.isFavoriteFlag();
        boolean newQueryIndicator = contract.isNewQueryIndicator(); 
        return (sessionFavoriteSet != null) && (!favoriteFlag) && (!newQueryIndicator); 
    }
}
