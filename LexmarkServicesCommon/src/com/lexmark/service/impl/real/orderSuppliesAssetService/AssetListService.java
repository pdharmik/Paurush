package com.lexmark.service.impl.real.orderSuppliesAssetService;

import static com.lexmark.service.impl.real.deviceService.AmindContractedDeviceDataConversionUtil.convertAssetDoToAssetList;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.accountvaluedFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.allFavoriteFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.allFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.contractedvaluedFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.favoriteAccountvaluedFieldMap;
import static com.lexmark.service.impl.real.orderSuppliesAssetService.OrderSuppliesAssetSiebelFieldMap.favoriteContractedvaluedFieldMap;
import static com.lexmark.util.LangUtil.isEmpty;
import static com.lexmark.util.LangUtil.isNotEmpty;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.amind.common.service.SiebelBusinessServiceProxy;
import com.amind.data.service.ActionEvent;
import com.amind.data.service.QueryObject;
import com.amind.session.Session;
import com.lexmark.contract.AssetListContract;
import com.lexmark.result.AssetListResult;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.AmindCrmSessionHandle;
import com.lexmark.service.impl.real.domain.AccountAssetFavorites;
import com.lexmark.service.impl.real.domain.AssetBase;
import com.lexmark.service.impl.real.domain.CHLDo;
import com.lexmark.service.impl.real.domain.ConsumableAssetTypeDo;
import com.lexmark.service.impl.real.domain.ContractedAndEntitledAssetDo;
import com.lexmark.service.impl.real.util.AmindServiceUtil;
import com.lexmark.util.LangUtil;

import org.apache.logging.log4j.Logger;

/**
 * 
 * @author vpetruchok
 * @version 1.0, 2012-08-30
 */
public class AssetListService {

	//private static Log logger = LogFactory.getLog(AssetListService.class);
	private static Logger logger = LogManager.getLogger(AssetListService.class);
	private static final Class<?> DO_CLASS = ContractedAndEntitledAssetDo.class;
	private static final Class<?> FAVORITE_DO_CLASS = AccountAssetFavorites.class;
	private static final Map<String, String> ACCOUNT_VALUED_FIELD_MAP = accountvaluedFieldMap();
	private static final Map<String, String> CONTRACTED_VALUED_FIELD_MAP = contractedvaluedFieldMap();
	private static final Map<String, String> ALL_FIELD_MAP = allDeviceFinderFieldMap();
	private static final Map<String, String> FAVORITE_FIELD_MAP = allFavoriteFieldMap();
	private static final Map<String, String> FAVORITE_ACCOUNT_VALUED_FIELD_MAP = favoriteAccountvaluedFieldMap();
	private static final Map<String, String> FAVORITE_CONTRACTED_VALUED_FIELD_MAP = favoriteContractedvaluedFieldMap();
	
	public static final String GLOBAL_MDMLEVEL = "Global";
	public static final String DOMESTIC_MDMLEVEL = "Domestic";
	public static final String LEGAL_MDMLEVEL = "Legal";
	public static final String ACCOUNT_MDMLEVEL = "Account";
	public static final String SIEBEL_MDMLEVEL = "Siebel";

	private Session session;
	private Session chldSession;
	private AmindCrmSessionHandle crmSessionHandle;
	private AssetListContract contract;
	private boolean favoriteFlag;
	private Session totalCountSession;
	private Session favoritesSession;

	public AssetListService(Session session, Session chldSession, AmindCrmSessionHandle crmSessionHandle, AssetListContract contract) {
		this.session = session;
		this.chldSession = chldSession;
		this.crmSessionHandle = crmSessionHandle;
		this.contract = contract;
	}

	public AssetListResult retrieveAllDeviceList() throws InterruptedException, ExecutionException  {
		logger.debug("[IN] retrieveAllDeviceList");
		 long t = System.currentTimeMillis();
		checkRequiredFields(contract);
				
		AssetListResult assetListResult = new AssetListResult();
		favoriteFlag = contract.isFavoriteFlag();
		
		ExecutorService executor = null;
		
		try
		
		{
			final String searchExpression = buildAssetSearchExpression();
			final String childSearchSpec = "[Id] IS NULL";
			
			executor = Executors.newFixedThreadPool(3);
				
			Future<List<ContractedAndEntitledAssetDo>> assetListFuture = executor.submit(new Callable<List<ContractedAndEntitledAssetDo>>() {
				@Override
				public List<ContractedAndEntitledAssetDo> call() throws Exception {
					QueryObject criteria = buildQueryObject(searchExpression, childSearchSpec);
					return queryAndGetAssetList(criteria);
				}
			});
			
			Future<Set<String>> favoritesFuture = executor.submit(new Callable<Set<String>>() {
				@Override
				public Set<String> call() throws Exception {
					if (!favoriteFlag) {
						return processFavoriteSet();
					} else {
						return new HashSet<String>();
					}
				}
			});
			
			Future<Integer> totalCountFuture = executor.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return processRecordsTotalCount(searchExpression);
				}
			});
			
			List<ContractedAndEntitledAssetDo> assetList = assetListFuture.get();
			Set<String> favoriteSet = favoritesFuture.get();
			int count = totalCountFuture.get();
			
			executor.shutdown();
			
			assetListResult.setTotalCount(count);
			assetListResult.setAccountAssets(convertAssetDoToAssetList(assetList,favoriteSet));
		} finally {
			if (executor!=null && !executor.isShutdown()) {
				executor.shutdown();
			}
			logger.debug("[OUT] retrieveDeviceList");
		}
		return assetListResult;
	}

	private QueryObject buildQueryObject(String searchExpression, String childSearchSpec) {
		QueryObject criteria = null;
		if (favoriteFlag) {
			logger.equals("In favorite ");
			criteria = new QueryObject(FAVORITE_DO_CLASS, ActionEvent.QUERY);
			criteria.addComponentSearchSpec(FAVORITE_DO_CLASS, searchExpression);
			criteria.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), FAVORITE_FIELD_MAP));
		} else {
			logger.equals("In DO_Clss ");
			criteria = new QueryObject(DO_CLASS,ActionEvent.QUERY);
			criteria.addComponentSearchSpec(DO_CLASS, searchExpression);
			if (contract.isDownloadCall()) {
				criteria.addComponentSearchSpec(ConsumableAssetTypeDo.class,
						childSearchSpec);
			}
			criteria.setSortString(AmindServiceUtil.buildSortString(contract.getSortCriteria(), ALL_FIELD_MAP));
		}

		AmindServiceUtil.buildBasicQueryObject(criteria, contract.getIncrement(), contract.getStartRecordNumber());

		return criteria;
	}
	
	private String buildAssetSearchExpression()
	{

		StringBuilder expr = new StringBuilder();
//		expr.append("(:status= 'New' OR :status= 'Created' OR :status = 'Active')"
//                .replaceAll(":status", "[Operating Status]"));
		expr.append("(:status <> 'Unassigned')"
              .replaceAll(":status", "[LXK MPS Asset Life Cycle]"));
		
		expr.append(filterCriteriaExpr() );
		expr.append(searchCriteriaExpr() );
		
		if ("Y".equalsIgnoreCase(contract.getLbsFilterCriteriaFlag())) {
			expr.append(" AND [LXK LBS Address Flag] = 'Y'");
		} else if ("N".equalsIgnoreCase(contract.getLbsFilterCriteriaFlag())) {
			expr.append(" AND (([LXK LBS Address Flag] = 'N') OR ([LXK LBS Address Flag] IS NULL))");
		}
		
		if (favoriteFlag) {
			expr.append(" AND (");
			expr.append(favoriteListSearchExpression());
			if (!contract.isAlliancePartner()) {
				expr.append(")");
				expr.append(buildSearchExpressionForNonAlliancePartnerFavortie(
						contract.getMdmId(), contract.getMdmLevel()));
			} else { 
				expr.append(")");
				expr.append(" AND (");
				expr.append(buildSearchExpressionForMDMFavorite());
				expr.append(")");
			}
			
		} else if (isSearchByChlNodeId()) {
			expr.append(buildCHLNodeExpression());

		} else if (isSearchByMdmId()) {
			if (!contract.isAlliancePartner()) {
				expr.append(buildmdmSearchExpressionForNonAlliancePartnerAllDeviceList(
						contract.getMdmId(), contract.getMdmLevel()));
			} else { 
				expr.append(buildmdmSearchExpressionForAllDeviceList(
						contract.getMdmId(), contract.getMdmLevel()));
			}
		} else {
			throw new SiebelCrmServiceException(
					"No Contact Id, CHLNodeId or mdmId specified for retrieveDeviceList");
		}
      logger.error("Query for searching device "+expr.toString());
		return expr.toString();
	}
	
	private StringBuilder buildCHLNodeExpression () {
		CHLDo chl = AmindServiceUtil.getParentChainFromCHLNodeId(contract.getChlNodeId(), chldSession.getDataManager());
		StringBuilder expr = new StringBuilder();
		if(chl == null) {
			throw new SiebelCrmServiceException("Chl Domian Object is null");
		}
		String parentChain = chl.getParentChain();

		expr.append(" AND ");
		String topLevelAccountId = chl.getTopLevelAccountId();
		if (isNotEmpty(topLevelAccountId))
		{
			expr.append("[Owner Account Id]");
			expr.append("='");
			expr.append(topLevelAccountId);
			expr.append("'");
			
		}
	
		if(isNotEmpty(parentChain)) {
			expr.append(" AND");
			expr.append(" (EXISTS(");
			expr.append("[LXK SW Covered Asset CHL Parent Chain]");
			expr.append(" LIKE '");
			expr.append(parentChain);
			expr.append("*')");
			
			expr.append(" OR ");
			expr.append("[CHL Parent Chain]");
			expr.append(" LIKE '");
			expr.append(parentChain);
			expr.append("*')");
			
//			throw new SiebelCrmServiceException("No Parent Chain found using CHL Node ID: " + contract.getChlNodeId());
		}
		
		expr.append(" AND EXISTS ( [LXK MPS CSS Ent Type] <> 'Warranty' " +
				"AND ([LXK MPS CSS Ent End Date] >= '" +  contract.getEntitlementEndDate() + "'))") ;
		
		return expr;
	}
	@SuppressWarnings("unchecked")
	private List<ContractedAndEntitledAssetDo> queryAndGetAssetList(QueryObject criteria){
		long t = System.currentTimeMillis();
		List<ContractedAndEntitledAssetDo> assetList = null;
		if (contract.isNewQueryIndicator()) {
			assetList = session.getDataManager().query(criteria);
		} else {
			assetList = session.getDataManager().queryNext(criteria);
		}
		 
		 logger.debug("[OUT] Exec time Asset Data List : "+ + (System.currentTimeMillis() - t)
  				/ 1000.0);
		return assetList;
	}

	@SuppressWarnings("unchecked")
	private Set<String> processFavoriteSet() {
		Set<String> favoriteSet = new HashSet<String>();
		StringBuilder expr = new StringBuilder();
		expr.append(favoriteListSearchExpression()); 
		expr.append(" AND (");
		expr.append(buildSearchExpressionForMDMFavorite());
		expr.append(")");

		QueryObject criteria = new QueryObject(AccountAssetFavorites.class, ActionEvent.QUERY);
		criteria.setQueryString(expr.toString());
		criteria.addComponentSearchSpec(FAVORITE_DO_CLASS, expr.toString());

		List<AssetBase> favoriteList = ( List<AssetBase>)getFavoritesSession().getDataManager().query(criteria); 
		if(LangUtil.isNotEmpty(favoriteList)) {

			for (AssetBase assetBase : favoriteList)
			{ 
				favoriteSet.add(assetBase.getAssetId());
			}
		}
		logger.error("processFavoriteSet"+expr);
		crmSessionHandle.setAssetFavoriteSet(favoriteSet) ;
		return  favoriteSet;
	}
	//buildSearchExpressionForNonAlliancePartnerFavortie
	public String buildSearchExpressionForNonAlliancePartnerFavortie(String mdmId, String mdmLevel) {   // Performace related search spec for favorite call 

		StringBuilder expr = new StringBuilder();
		//expr.append(" AND (EXISTS ([LXK SW Entitlement Type] <> 'Warranty' AND [LXK MPS Entitlement End Date] >="+"'"+contract.getEntitlementEndDate()+"'");
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) { // l5 level login/Siebel
			expr.append(" AND");
			expr.append(" [Owner Account Id] = '");
			expr.append(contract.getMdmId() + "' ");
		}

		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) { // l3 level login
			expr.append(" AND");
			expr.append(" [LXM MDM Legal Entity ID #] = '");
			expr.append(contract.getMdmId() + "' ");
		}

		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) { // l1 level login/Global

			// logger.debug("VALUE BEING PASSED >>"+mdmLevel);
			expr.append(" AND");
			expr.append(" [LXK MPS Account Global DUNS Number] = '");
			expr.append(contract.getMdmId() + "' ");
		}

		else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) { // l2 level
														// login/Domestic.
			// logger.debug("VALUE BEING PASSED >>"+mdmLevel);
			expr.append(" AND");
			expr.append(" [LXK MPS Account Domestic DUNS Number] = '");
			expr.append(contract.getMdmId() + "' ");
		}

		else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) { // l4 level login  
			// logger.debug("VALUE BEING PASSED >>"+mdmLevel);
			expr.append(" AND");
			expr.append(" [LXM MDM Account #] = '");
			expr.append(contract.getMdmId() + "' ");

		} else {
			throw new IllegalArgumentException(
					"mdmLevel must be one of 'Global, Legal, Siebel'.  Value was "
							+ mdmLevel);
		}
		expr.append("AND  ([LXK MPS Agree Status] = '");
		expr.append("Active"+"'");
		expr.append(" OR [LXK MPS Agree Status] ='" );
		expr.append("Current"+"')");
//		expr.append	("AND [LXK MPS Asset Life Cycle] <> 'Unassigned' ");
    	return expr.toString();
	}

	public String buildmdmSearchExpressionForNonAlliancePartnerAllDeviceList(String mdmId, String mdmLevel) {

		StringBuilder expr = new StringBuilder();
		//expr.append(" AND (EXISTS ([LXK SW Entitlement Type] <> 'Warranty' AND [LXK MPS Entitlement End Date] >="+"'"+contract.getEntitlementEndDate()+"'");
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) { // l5 level login/Siebel
			expr.append(" AND");
			expr.append(" [Owner Account Id] = '");
			expr.append(contract.getMdmId() + "' ");
		}

		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) { // l3 level login
			expr.append(" AND");
			expr.append(" [LXM MDM Legal Entity ID #] = '");
			expr.append(contract.getMdmId() + "' ");
		}

		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) { // l1 level login/Global

			// logger.debug("VALUE BEING PASSED >>"+mdmLevel);
			expr.append(" AND");
			expr.append(" [LXK Account Global DUNS Number] = '");
			expr.append(contract.getMdmId() + "' ");
		}

		else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) { // l2 level
														// login/Domestic.
			// logger.debug("VALUE BEING PASSED >>"+mdmLevel);
			expr.append(" AND");
			expr.append(" [LXK Account Domestic DUNS Number] = '");
			expr.append(contract.getMdmId() + "' ");
		}

		else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) { // l4 level login  
			// logger.debug("VALUE BEING PASSED >>"+mdmLevel);
			expr.append(" AND");
			expr.append(" [LXM MDM Account #] = '");
			expr.append(contract.getMdmId() + "' ");

		} else {
			throw new IllegalArgumentException(
					"mdmLevel must be one of 'Global, Legal, Siebel'.  Value was "
							+ mdmLevel);
		}
		expr.append("AND  ([LXK MPS Agree Status] = '");
		expr.append("Active"+"'");
		expr.append(" OR [LXK MPS Agree Status] ='" );
		expr.append("Current"+"')");
//		expr.append	("AND [LXK MPS Asset Life Cycle] <> 'Unassigned' ");
    	return expr.toString();
	}

	public String buildmdmSearchExpressionForAllDeviceList(String mdmId, String mdmLevel) {

		StringBuilder expr = new StringBuilder();
		expr.append(" AND (EXISTS ([LXK SW Entitlement Type] <> 'Warranty' AND [LXK MPS Entitlement End Date] >="+"'"+contract.getEntitlementEndDate()+"'");
		if (SIEBEL_MDMLEVEL.equals(mdmLevel)) {          			//l5 level login
				expr.append(" AND");
				expr.append(" [LXK MPS Owner Account Id] = '" );
				expr.append(contract.getMdmId() + "') ");
				expr.append("OR EXISTS (");
				expr.append("[LXK SW Agreement Account Id] ='");
				expr.append(contract.getMdmId() + "')");
				expr.append(" OR EXISTS ( ([LXK MPS CSS Account Id] = '");
				expr.append(contract.getMdmId() + "')");
				expr.append(" AND [LXK MPS CSS Ent Type] <> 'Warranty' AND [LXK MPS CSS Ent End Date] >= '"  +  contract.getEntitlementEndDate() + "'");
				expr.append	(") )");
		}

		else if (LEGAL_MDMLEVEL.equals(mdmLevel)) {							//l3 level login
			
				expr.append(" AND");
				expr.append(" [LXK MPS  Account MDM Legal Entity ID #] = '" );
				expr.append(contract.getMdmId() + "') ");
				expr.append("OR EXISTS (");
				expr.append("[LXK SW Agreement Account LEGAL MDM ID] ='");
				expr.append(contract.getMdmId() + "')");
				expr.append(" OR EXISTS ( ([LXK MPS CSS Legal] = '");
				expr.append(contract.getMdmId() + "' ) ");
				expr.append(" AND [LXK MPS CSS Ent Type] <> 'Warranty' AND [LXK MPS CSS Ent End Date] >= '"  +  contract.getEntitlementEndDate() + "'");
				expr.append	(" ) )");
		}

		else if (GLOBAL_MDMLEVEL.equals(mdmLevel)) {                          // l1 level login
			
				//logger.debug("VALUE BEING PASSED >>"+mdmLevel);
				expr.append(" AND");
				expr.append(" [LXK MPS Account Global DUNS Number] = '" );
				expr.append(contract.getMdmId() + "') ");
				expr.append("OR EXISTS ([LXK SW Agreement Account Global DUNS] = '");
				expr.append(contract.getMdmId() + "' ) ");
				expr.append(" OR EXISTS ( ([LXK MPS CSS Global DUNS] ='" );
				expr.append(contract.getMdmId() + "')");
				expr.append(" AND [LXK MPS CSS Ent Type] <> 'Warranty' AND [LXK MPS CSS Ent End Date] >= '"  +  contract.getEntitlementEndDate() + "'");
				expr.append	(" ) )");
			
			
		}
		
		else if (DOMESTIC_MDMLEVEL.equals(mdmLevel)) {                                      //l2 level login.
			
			//logger.debug("VALUE BEING PASSED >>"+mdmLevel);
			expr.append(" AND");
			expr.append(" [LXK MPS Account Domestic DUNS Number] = '" );
			expr.append(contract.getMdmId() + "') ");
			expr.append("OR EXISTS ([LXK SW Agreement Account Domestic DUNS] = '");
			expr.append(contract.getMdmId() + "' ) ");
			expr.append(" OR EXISTS ( ([LXK MPS CSS Domestic DUNS] ='" ) ;
			expr.append(contract.getMdmId() + "')");
			expr.append(" AND [LXK MPS CSS Ent Type] <> 'Warranty' AND [LXK MPS CSS Ent End Date] >= '"  +  contract.getEntitlementEndDate() + "'");
			expr.append	(" ) )");
		
		
	}
	
		else if (ACCOUNT_MDMLEVEL.equals(mdmLevel)) {                              // l4  level login
			//logger.debug("VALUE BEING PASSED >>"+mdmLevel);
			expr.append(" AND");
			expr.append(" [LXK MPS Account MDM Account #] = '");
			expr.append(mdmId);
			expr.append("')");
//			expr.append(" AND ([LXK MPS Agreement Status]='Current' AND [LXK MPS Agreement Type]='MPS Agreement' )");
			//expr.append(")");
			expr.append(" OR EXISTS ([LXK SW Agreement Account MDM Account] = '");
			expr.append(mdmId);
			expr.append("')  OR EXISTS ( ([LXK MPS CSS MDM] ='");
			expr.append(mdmId);
			expr.append("') AND [LXK MPS CSS Ent Type] <> 'Warranty'  AND [LXK MPS CSS Ent End Date] >= '");
			expr.append(contract.getEntitlementEndDate());
			expr.append("') )");
			
	}
	
		
		else {
	        throw new IllegalArgumentException("mdmLevel must be one of 'Global, Legal, Siebel'.  Value was " + mdmLevel);
		}
		
    	return expr.toString();
	}

	private String buildSearchExpressionForMDMFavorite() {
		StringBuilder searchSpec = new StringBuilder();
		searchSpec.append(" EXISTS (");
		//Contracted
		searchSpec.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(contract.getMdmId(), contract.getMdmLevel(), FAVORITE_CONTRACTED_VALUED_FIELD_MAP, false, "LXK SW Agreement Account MDM Level"));
		searchSpec.append(") ");
		searchSpec.append("OR ");
		searchSpec.append("((");
		searchSpec.append("EXISTS (");
		searchSpec.append(AmindServiceUtil.buildMdmSearchExpressionForMdmLevel(contract.getMdmId(), contract.getMdmLevel(), FAVORITE_ACCOUNT_VALUED_FIELD_MAP, false, null));
		searchSpec.append(")))");
		return searchSpec.toString();
	}

	private int processRecordsTotalCount(String searchExpression) {
		int count = 0;
		if (contract.isNewQueryIndicator()) {
			logger.debug("[IN] Count Method");
			count = countRecords(searchExpression);
			crmSessionHandle.setDeviceCount(count);
			logger.debug("[OUT] Count Method");
		}
		else {
			count =  crmSessionHandle.getDeviceCount();
		}

		return count;
	}

	private int countRecords(String searchExpression) {
		String businessObject    =  "LXK MPS Contracted Consumable Asset List_POC";
		String businessComponent =  "LXK MPS Contracted Consumable Asset List_POC";
		String favoriteBusinessObject    =  "LXK SW Contact Favorite Assets_POC";
		String favoriteBusinessComponent =  "LXK SW Contact Favorite Assets_POC";
		long t = System.currentTimeMillis();
		SiebelBusinessServiceProxy businessServiceProxy = getTotalCountSession().getSiebelBusServiceProxy();
		int count = 0;
		if(favoriteFlag){
			count = AmindServiceUtil.getTotalCount(favoriteBusinessObject, favoriteBusinessComponent, searchExpression, businessServiceProxy);
		}else {
			count = AmindServiceUtil.getTotalCount(businessObject, businessComponent, searchExpression, businessServiceProxy);
		}
		 
		 logger.debug("[OUT]Exec time for total count: "+ + (System.currentTimeMillis() - t)
	  				/ 1000.0);
		return count;

	}

	private boolean isSearchByMdmId() {
		return isNotEmpty(contract.getMdmId());
	}

	private boolean isSearchByChlNodeId() {
		return isNotEmpty(contract.getChlNodeId());
	}

	public String favoriteListSearchExpression() {

		String s =  String.format("[LXK SW Asset Favorite Flag] = 'Y' AND [Contact Id] = '%s'", contract.getContactId()); 
		return s;
	}

	private String searchCriteriaExpr() {
		String searchSpec = "";
		if (isNotEmpty(contract.getSearchCriteria())) {
			searchSpec =  contract.isFavoriteFlag()
			? AmindServiceUtil.buildSearchCriteria(contract.getSearchCriteria(), FAVORITE_FIELD_MAP)
					: AmindServiceUtil.buildSearchCriteria(contract.getSearchCriteria(), ALL_FIELD_MAP);
		}

		return searchSpec;
	}

	private String filterCriteriaExpr() {
		String filterSearchSpec = "";
		if (isNotEmpty(contract.getFilterCriteria())) {
			filterSearchSpec = favoriteFlag
			? AmindServiceUtil.buildCriteria(contract.getFilterCriteria(), FAVORITE_FIELD_MAP, true, true)
					: AmindServiceUtil.buildCriteria(contract.getFilterCriteria(), ALL_FIELD_MAP, true, true);
		}
		return filterSearchSpec;

	}
	
	private static void checkRequiredFields(AssetListContract contract) {
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
		
		if (isEmpty(contract.getChlNodeId()) 
		     && isEmpty(contract.getEntitlementEndDate())) {
				throw new IllegalArgumentException("[mdm-search] EntitlementEndDate is null or empty");
		}
	}
	
	
	public Session getTotalCountSession() {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session has not been set!");
		} else {
			return totalCountSession;
		}
	}

	public void setTotalCountSession(Session totalCountSession) {
		if (totalCountSession == null) {
			throw new IllegalStateException("total count session can not be null!");
		} else {
			this.totalCountSession = totalCountSession;
		}
	}
	
	public Session getFavoritesSession() {
		if (favoritesSession == null) {
			throw new IllegalStateException("favorites session has not been set!");
		} else {
			return favoritesSession;
		}
	}

	public void setFavoritesSession(Session favoritesSession) {
		this.favoritesSession = favoritesSession;
	}


	private static Map<String, String> allDeviceFinderFieldMap() {
		Map<String, String> map = new HashMap<String, String>(allFieldMap());
		map.put("productLine", "LXK C Customer Reporting Name");
		map.put("assetCostCenter", "LXK MPS Asset Cost Center");
		return Collections.unmodifiableMap(map);
	}
}
