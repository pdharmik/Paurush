package com.lexmark.service.impl.real.deviceService;

import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.Map;

import com.amind.data.service.IDataManager;
import com.lexmark.contract.AssetListContract;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;

/**
 *
 * @author vpetruchok
 * @version 1.0, 2012-04-11
 */
public class AmindContractedDeviceSearchExpressionUtil {

    private AmindContractedDeviceSearchExpressionUtil() {
    }

    public static String buildAssetSearchExpression(AssetListContract contract, IDataManager dataManager, Map<String, String> fieldMap,
            Map<String, String> boFavFieldMap)
    {
        
        StringBuilder expr = new StringBuilder();
        
        if (contract.isFavoriteFlag()) {
            expr.append(favoriteListSearchExpression(contract)); 
        } else if (isSearchByChlNodeId(contract)) {
            expr.append(AmindServiceUtil.buildChlNodeExpression(contract.getChlNodeId(), dataManager, false, contract.getAssetType()));
        } else if (isSearchByMdmId(contract)) {
            expr.append("EXISTS (");
            expr.append(mdmSearchExpr(contract, fieldMap));
            expr.append(assetTypeFilterExpr(contract));  /* assetType filter */
            expr.append(")");
        } else {
            throw new SiebelCrmServiceException("No Contact Id, CHLNodeId or mdmId specified for retrieveDeviceList");
        }

        expr.append(searchCriteriaExpr(contract, fieldMap, boFavFieldMap));
        expr.append(filterCriteriaExpr(contract, fieldMap, boFavFieldMap));

        
        if (!contract.isFavoriteFlag()) {
            String parentChlNodeId = getChlNodeId(contract);
            
            if (isNotEmpty(parentChlNodeId)) {
                expr.deleteCharAt(expr.length() - 1);
                expr.append(parentChlNodeIdExpr(parentChlNodeId, contract, dataManager));
            }

            expr.append(footerExpr(contract));
        }
        return expr.toString();
    }

   public static String favoriteListSearchExpression(AssetListContract contract) {
        if (isEmpty(contract.getContactId())) {
            throw new IllegalArgumentException("contract.contactId is null or empty");
        }
    
        String s =  String.format("[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = '%s'", contract.getContactId()); 
        return s;
    }

    private static CharSequence parentChlNodeIdExpr(String chlNodeId, AssetListContract contract, IDataManager dataManager) {
        CHLDo chl = AmindServiceUtil.getParentChainFromCHLNodeId(chlNodeId, dataManager);
        if (chl == null || isEmpty(chl.getParentChain())) {
            throw new SiebelCrmServiceException("No Parent Chain found for CHL Node ID: " + chlNodeId);
        }

        StringBuilder expr = new StringBuilder();
        boolean filterFlag = isNotEmpty(contract.getFilterCriteria());

        if (isNotEmpty(contract.getSearchCriteria())
                && contract.getSearchCriteria().size() > 1) {
            expr.append(" OR ");  
        }
        expr.append("EXISTS ([LXK SW Covered Asset CHL Parent Chain] LIKE '");
        if (filterFlag) {
            expr.append("*");
        }
        expr.append(chl.getParentChain());
        expr.append("*') )");

        return expr;
    }

    private static String getChlNodeId(AssetListContract contract) {
        String s = null;

        if (isNotEmpty(contract.getSearchCriteria())) {
            s = (String) contract.getSearchCriteria().get("chlNodeId");
        }

        if (isNotEmpty(contract.getFilterCriteria())) {
            s = (String) contract.getFilterCriteria().get("chlNodeId");
        }
        return s;
    }

    private static String footerExpr(AssetListContract contract) {
        return contract.isFavoriteFlag()
                ? ""
                :  " AND (:status= 'New' OR :status= 'Created' OR :status = 'Active')"
                     .replaceAll(":status", "[Operating Status]");

    }

    static String filterCriteriaExpr(AssetListContract contract, Map<String, String> fieldMap, Map<String, String> boFavFieldMap) {
        String filterSearchSpec = "";
    	if (isNotEmpty(contract.getFilterCriteria())) {
    		filterSearchSpec = contract.isFavoriteFlag()
            ? AmindServiceUtil.buildCriteria(contract.getFilterCriteria(), boFavFieldMap, true, true)
            : AmindServiceUtil.buildCriteria(contract.getFilterCriteria(), fieldMap, true, true);
        }
    	return filterSearchSpec;
    	
    }

    private static String searchCriteriaExpr(AssetListContract contract, Map<String, String> fieldMap, Map<String, String> boFavFieldMap) {
        String searchSpec = "";
    	if (isNotEmpty(contract.getSearchCriteria())) {
    		searchSpec =  contract.isFavoriteFlag()
            ? AmindServiceUtil.buildSearchCriteria(contract.getSearchCriteria(), boFavFieldMap)
                    : AmindServiceUtil.buildSearchCriteria(contract.getSearchCriteria(), fieldMap);
        }

        return searchSpec;
    }

    static String assetTypeFilterExpr(AssetListContract contract) {
        String s = "";
        if (isNotEmpty(contract.getAssetType())) {
            if (contract.getAssetType().equalsIgnoreCase("MPS")) {
                s = " AND [LXK SW Agreement Type] = 'MPS Agreement'";
            } else if (contract.getAssetType().equalsIgnoreCase("CSS")) {
                s = " AND [LXK SW Agreement Type] = 'CSS Agreement'";
            }
        }
        return s;
    }

    private static String mdmSearchExpr(AssetListContract contract, Map<String, String> fieldMap) {
    	return AmindServiceUtil.buildmdmSearchExpression(contract.getMdmId(), contract.getMdmLevel(), fieldMap, false, true);
    }

    private static boolean isSearchByMdmId(AssetListContract contract) {
        return isNotEmpty(contract.getMdmId());
    }

    private static boolean isSearchByChlNodeId(AssetListContract contract) {
        return isNotEmpty(contract.getChlNodeId()) && !contract.isFavoriteFlag();
    }

}
