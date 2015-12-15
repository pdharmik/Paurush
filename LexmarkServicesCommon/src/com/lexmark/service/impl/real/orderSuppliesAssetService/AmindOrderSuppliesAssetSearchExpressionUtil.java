package com.lexmark.service.impl.real.orderSuppliesAssetService;


import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.Map;

import com.amind.data.service.IDataManager;
import com.amind.session.Session;
import com.lexmark.contract.AssetListContract;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.service.impl.real.util.ChlNodeExprBuilder;
import com.lexmark.util.LangUtil;

/**
 *
 * @see com.lexmark.service.impl.real.deviceService.AmindContractedDeviceSearchExpressionUtil#buildAssetSearchExpression(AssetListContract, IDataManager, Map, Map)
 * @see com.lexmark.service.impl.real.AmindOrderSuppliesAssetService
 *
 * @author vpetruchok
 * @version 1.0, 2012-05-04
 */
public class AmindOrderSuppliesAssetSearchExpressionUtil {

    private AmindOrderSuppliesAssetSearchExpressionUtil() {
    }

    public static String buildAssetSearchExpression(AssetListContract contract, Session chldSession, Map<String, String> fieldMap)
    {
    	 StringBuilder expr = new StringBuilder();
    	/*expr.append(consumableTypeExpr()); Commented by sankha for LEX:AIR00075005 */
    	/* Added by sankha for LEX:AIR00075005 start*/
//		expr.append("EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*' AND [LXK MPS Entitlement End Date] >= '"+ contract.getEntitlementEndDate() + "')");
    	/*End */
    	 
    	expr.append("([Operating Status]= 'New' OR [Operating Status]= 'Created' OR [Operating Status] = 'Active')");
    	expr.append("AND (EXISTS ([LXK SW Entitlement Type] LIKE 'Consumable*' ");
    	expr.append("AND [LXK MPS Entitlement End Date] >= '");
    	expr.append(contract.getEntitlementEndDate()+"'");
    	
		if (isSearchByChlNodeId(contract)) {
			IDataManager chldDataManager = chldSession.getDataManager();
			expr.append(") AND ("
					+ buildChlNodeExpression(contract, chldDataManager));
			expr.append(")");
			expr.append(")");
		} else if (isSearchByMdm(contract) && contract.isAlliancePartner()) {
			expr.append(" AND " + mdmSearchExpr(contract, fieldMap));
			if (LangUtil.isEmpty(contract.getChlNodeId())) {
				if ("Global".equalsIgnoreCase(contract.getMdmLevel())) { // l1
																			// login
					expr.append(") OR EXISTS ([LXK SW Agreement Account Global DUNS]='"
							+ contract.getMdmId() + "'");
				} else if ("Domestic".equalsIgnoreCase(contract.getMdmLevel())) { // l2
																					// login
					expr.append(") OR EXISTS ([LXK SW Agreement Account Domestic DUNS]='"
							+ contract.getMdmId() + "'");
				} else if ("Legal".equalsIgnoreCase(contract.getMdmLevel())) { // l3
																				// login
					expr.append(") OR EXISTS ([LXK SW Agreement Account LEGAL MDM ID]='"
							+ contract.getMdmId() + "'");
				} else if ("Account".equalsIgnoreCase(contract.getMdmLevel())) { // l4
																					// login
					expr.append(") OR EXISTS ([LXK SW Agreement Account MDM Account]='"
							+ contract.getMdmId() + "'");
				} else if ("Siebel".equalsIgnoreCase(contract.getMdmLevel())) { // l5
																				// login
					expr.append(") OR EXISTS ([LXK SW Agreement Account Id]='"
							+ contract.getMdmId() + "'");
				}
				// allSearchBuilder.append(" AND [LXK MPS Agreement Status]='Current' AND [LXK MPS Agreement Type]='MPS Agreement'");

			}
			expr.append(")");
			expr.append(")");
		} else if (isSearchByMdm(contract)) { // changes to improve performance.
												// As per Split Logic Technical
												// doc
			expr.append(")");
			expr.append(")");
			expr.append(" AND ");
			if ("Global".equalsIgnoreCase(contract.getMdmLevel())) { // l1
																		// login
				expr.append("[LXK Account Global DUNS Number]='"
						+ contract.getMdmId() + "'");
			} else if ("Domestic".equalsIgnoreCase(contract.getMdmLevel())) { // l2
																				// login
				expr.append("[LXK Account Domestic DUNS Number]='"
						+ contract.getMdmId() + "'");
			} else if ("Legal".equalsIgnoreCase(contract.getMdmLevel())) { // l3
																			// login
				expr.append("[LXM MDM Legal Entity ID #]='"
						+ contract.getMdmId() + "'");
			} else if ("Account".equalsIgnoreCase(contract.getMdmLevel())) { // l4
																				// login
				expr.append("[LXM MDM Account #]='" + contract.getMdmId() + "'");
			} else if ("Siebel".equalsIgnoreCase(contract.getMdmLevel())) { // l5
																			// login
				expr.append("[Owner Account Id]='" + contract.getMdmId() + "'");
			}
			expr.append(" AND  ([LXK MPS Agree Status] = '");
			expr.append("Active" + "'");
			expr.append(" OR [LXK MPS Agree Status] ='");
			expr.append("Current" + "')");
//			expr.append("AND [LXK MPS Asset Life Cycle] <> 'Unassigned' ");
		} else {
			throw new SiebelCrmServiceException(
					"No Contact Id, CHLNodeId or (MdmId, MdmLevel) specified for retrieveDeviceList");
		}

        return expr.toString();
    }
    
    static String buildFavoriteSearchExpression(AssetListContract contract, IDataManager dataManager, Map<String, String> boFavFieldMap) 
    {
    	 StringBuilder expr = new StringBuilder();
         expr.append(favoriteListSearchExpression(contract,boFavFieldMap));
         expr.append(filterCriteriaExpr(contract, boFavFieldMap));
         /* expr.append(consumableFavoriteTypeExpr());  Commented by sankha for LEX:AIR00075005 */
         /* Added by sankha for LEX:AIR00075005 start*/
//         expr.append(" AND EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*' AND [LXK MPS Entitlement End Date] >= '"+ contract.getEntitlementEndDate() + "')");
         /*End */

         return expr.toString();
    }

    private static Object consumableAssetFlagExpr() {
        return " AND [LXK MPS Consumable Flag] = 'Y'";
    }
    
    private static Object consumableTypeExpr() {
        return " EXISTS ([LXK SW Entitlement Type] ~LIKE 'Consumable*')";
    }
    
    private static Object consumableFavoriteTypeExpr() {
        return " AND EXISTS ([LXK MPS Ent Type] ~LIKE 'Consumable*')"; // entitlementEndDate in do-assetFavorites-mapping.xml
    }
    
    public static String favoriteListSearchExpression(AssetListContract contract,Map<String, String> boFavFieldMap ) {
        StringBuilder searchExpression = new StringBuilder();
        searchExpression.append("EXISTS(");
        searchExpression.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(contract.getMdmId(), contract.getMdmLevel(), boFavFieldMap, false, boFavFieldMap.get("mdmLevel")));
        searchExpression.append(")");
        searchExpression.append(" AND [LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = '" +  contract.getContactId() + "'"); 
        return searchExpression.toString();
    }

    private static boolean isSearchByMdm(AssetListContract contract) {
        return isNotEmpty(contract.getMdmId()) && isNotEmpty(contract.getMdmLevel());
    }

    private static String mdmSearchExpr(AssetListContract contract, Map<String, String> fieldMap) {
    	return AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(contract.getMdmId(), contract.getMdmLevel(), fieldMap, false, null);
    }

    /**
     * Originally copied  from {@link com.lexmark.service.impl.real.util.AmindServiceUtil#buildChlNodeExpression(String, IDataManager, boolean, String) buildChlNodeExpression}
     */
    private static String buildChlNodeExpression(AssetListContract contract, IDataManager dataManager)
    {
            String ownerAccountIdField = "Owner Account Id";
//            String parentChainField = "LXK SW Covered Asset CHL Parent Chain";
            String parentChainField = "CHL Parent Chain";  
            
//    	    return new ChlNodeExprBuilder(dataManager, contract.getChlNodeId())
//    	         .topLevelAccountIdExpr(ownerAccountIdField)
//    	         .and().existsBegin().parentChainExpr(parentChainField).existsEnd().toString();
            
    	    return new ChlNodeExprBuilder(dataManager, contract.getChlNodeId())
	         .topLevelAccountIdExpr(ownerAccountIdField)
	         .and().parentChainExpr(parentChainField).toString();
    	    
    }

    static String statusConstraintExpr(AssetListContract contract) {
        return contract.isFavoriteFlag()
                ? ""
                :  " AND (:status= 'New' OR :status= 'Created' OR :status = 'Active')"
                     .replaceAll(":status", "[Operating Status]");
    }

    static String filterCriteriaExpr(AssetListContract contract, Map<String, String> fieldMap) {
        String filterSearchExpression = "";
    	if (!isEmpty(contract.getFilterCriteria())) {
    		filterSearchExpression = AmindServiceUtil.buildCriteria(contract.getFilterCriteria(),fieldMap, true, true);
        }
    	
    	return filterSearchExpression;
   }

    private static boolean isSearchByChlNodeId(AssetListContract contract) {
        return isNotEmpty(contract.getChlNodeId())
                  && !contract.isFavoriteFlag();
    }

}
